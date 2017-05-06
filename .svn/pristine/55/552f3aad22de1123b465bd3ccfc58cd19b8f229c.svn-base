package gob.inei.renadef2016.fragments.dialog;

import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.TextAreaField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3CAPObservaciones extends DialogFragmentComponent {

	@FieldAnnotation(orderIndex=1) 
	public TextAreaField txtOBS;
	@FieldAnnotation(orderIndex = 2)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 3)
	public ButtonComponent btnCancelar;
	private LabelComponent lblObs;
//	private static C3CAP200_Fragment_201 caller;
	LinearLayout q0, q1, q2, q3, q4, q5,q7;
	private Entity detalle;
	private int sizeWidth = 650; 
	private int opcion;

	public static C3CAPObservaciones newInstance(FragmentForm pagina,
			Entity detalle, int opcion) {
		Filtros.clear();
		C3CAPObservaciones f = new C3CAPObservaciones();
		f.detalle = detalle;
		f.opcion = opcion;
		f.setParent(pagina);
		Bundle args = new Bundle();
		f.setArguments(args);
		return f;
	}
 
	public C3CAPObservaciones() {
		super();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = createUI();
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getDialog().setTitle("Denuncia N\u00B0: "+detalle.nro_mreg);
		initObjectsWithoutXML(this, rootView);

		cargarDatos();
		enlazarCajas(this);
		listening();
		
		return rootView;

	}
//////////////////////////////////////////////
	private void cargarDatos() {
//		entityToUI(detalle);
		switch (opcion) {
			case 1:
				if(detalle instanceof INF_Caratula01)
					txtOBS.setText(Util.getText(((INF_Caratula01)detalle).obs_03_car));
				break;
			case 2:
				if(detalle instanceof INF_Caratula01)
					txtOBS.setText(Util.getText(((INF_Caratula01)detalle).obs_car));
				break;
			default:
				break;
		}
		
		inicio();
	}

	private void inicio() {
		txtOBS.requestFocus();
	}

	@Override
	protected View createUI() {
		buildFields();
		
		q0 = createQuestionSection(lblObs);
		q1 = createQuestionSection(txtOBS);

		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q0);
		form.addView(q1);
		
		form.addView(botones);

		return contenedor;
	}

	@Override
	protected void buildFields() {

		lblObs = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				size(altoComponente, sizeWidth).text(R.string.lb_C_OBS).centrar();
		txtOBS=new TextAreaField(this.getActivity()).maxLength(500).size(500, sizeWidth); 

		btnAceptar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnAceptar).size(200, 60);
		btnCancelar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnCancelar).size(200, 60);
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				C3CAPObservaciones.this.dismiss();
			}
		});
		btnAceptar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		Filtros.setFiltro(txtOBS, Filtros.TIPO.ALFAN_U, 2000, null);
	}  
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3CAPObservaciones.this.dismiss();
		
	}

	private boolean validar() {
		return true;
	}
	
	private boolean grabar() {
		if (!validar()) {
			if (error) {
				if (!mensaje.equals("")) {
					ToastMessage.msgBox(this.getActivity(), mensaje,
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
				}
				if (view != null) {
					view.requestFocus();
				}
			}
			return false;
		}
		
		switch (opcion) {
		case 1: case 2:
			if(detalle instanceof INF_Caratula01){
				try {
					INF_Caratula01Service caratulaService = INF_Caratula01Service.getInstance(getActivity());
					setValue();
					if(!caratulaService.saveOrUpdate((INF_Caratula01)detalle, ((INF_Caratula01)detalle).getSecCap(
							Util.getListList(getValue())))){
						ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
								ToastMessage.MESSAGE_ERROR,
								ToastMessage.DURATION_LONG);
					}
				} catch (Exception e) {
					ToastMessage.msgBox(this.getActivity(), e.getMessage(),
							ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
					return false;
				}
				
//				INF_Caratula01Service caratulaService = INF_Caratula01Service.getInstance(getActivity());
//				setValue();
//				if(!caratulaService.grabarCaratula((INF_Caratula01)detalle, 1, Utilidades.getListFields(
//						new String[]{getValue()}))){
//					ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//							ToastMessage.MESSAGE_ERROR,
//							ToastMessage.DURATION_LONG);
//				}
			}
			break;

		default:
			break;
		}
		
		
		return true;
	}

	private String getValue() {
		switch (opcion) {
			case 1: return "OBS_03_CAR";
			case 2: return "OBS_CAR";
			default:return "";
		}
	}

	private void setValue() {
		switch (opcion) {
			case 1: ((INF_Caratula01)detalle).obs_03_car = txtOBS.getText().toString();	break;
			case 2: ((INF_Caratula01)detalle).obs_car = txtOBS.getText().toString(); break;
			default: break;
		}
	}
}


