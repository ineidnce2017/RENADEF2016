package gob.inei.renadef2016.fragments; 
// 
import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.TextAreaField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
// 
public class C3CUEST_OBS extends FragmentForm { 
    @FieldAnnotation(orderIndex=1) 
    public TextAreaField txtOBS_03_CAR; 
    
    private LabelComponent lblTitulo;
    private INF_Caratula01Service caratulaService;
	private INF_Caratula01 caratula;
// 
    public C3CUEST_OBS() {} 
    @Override 
    public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
    } 
    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
		rootView = createUI(); 
		initObjectsWithoutXML(this, rootView); 
		enlazarCajas(); 
		listening(); 
		return rootView; 
    } 
    
    @Override 
    protected void buildFields() { 
    	lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_OBS).textSize(21).centrar()
				.negrita();
    	
    	txtOBS_03_CAR=new TextAreaField(this.getActivity()).maxLength(500).size(700, 700); 
    } 
    
    @Override 
    protected View createUI() { 
		buildFields(); 
		/*Aca creamos las preguntas*/ 
		LinearLayout q1 = createQuestionSection(lblTitulo);
		LinearLayout q2 = createQuestionSection(txtOBS_03_CAR);
		///////////////////////////// 
		ScrollView contenedor = createForm(); 
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0); 
		/*Aca agregamos las preguntas a la pantalla*/ 
		form.addView(q1);
		form.addView(q2);
		/*Aca agregamos las preguntas a la pantalla*/ 
    return contenedor; 
    } 
    @Override 
    public boolean grabar() { 
    	uiToEntity(caratula);
		if (!validar()) {
			if (error) {
				if (!mensaje.equals(""))
					ToastMessage.msgBox(this.getActivity(), mensaje,
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
				if (view != null)
					view.requestFocus();
			}
			return false;
		}
		
		try {
			if(!getCaratulaService().saveOrUpdate(caratula, caratula.getSecCap(getListFields(this)))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			}
		} catch (Exception e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			return false;
		}
		
//		if(!getCaratulaService().grabarCaratula(caratula, 1, Utilidades.getListFields(this))){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
		return true; 
    } 
    private boolean validar() { 
		return true; 
    } 
    @Override 
    public void cargarDatos() { 
    	caratula = App.getInstance().getComisaria();
    	entityToUI(caratula);
		inicio();
    } 
    private void inicio() { 
    	txtOBS_03_CAR.requestFocus();
    } 
    
    @Override
	public boolean getSaltoNavegation() {
		return validar();
	}
    
    public INF_Caratula01Service getCaratulaService() {
		if (caratulaService == null) {
			caratulaService = INF_Caratula01Service.getInstance(getActivity());
		}
		return caratulaService;
	}
} 
