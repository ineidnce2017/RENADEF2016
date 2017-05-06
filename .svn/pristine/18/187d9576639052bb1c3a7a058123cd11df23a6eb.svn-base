package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.TableComponent;
import gob.inei.dnce.components.TableComponent.ALIGN;
import gob.inei.dnce.util.Caretaker;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3VISITAS_Fragment_S3;
import gob.inei.renadef2016.modelo.Cobertura;
import gob.inei.renadef2016.service.CoberturaService;
import gob.inei.renadef2016.service.MarcoService;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class CoberturaDialog extends DialogFragmentComponent{
	
	private TableComponent tcMarco;
	@FieldAnnotation(orderIndex = 1)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 2)
	public ButtonComponent btnCancelar;
	private LinearLayout q0;
	private static FragmentForm caller;
	private MarcoService service;
	private CoberturaService coberturaService;
	private List<Cobertura> marcosCoberturas;
	private FragmentForm.OPCION opcion;
	private LabelComponent lblTitulo;
	private boolean resultCobertura;

	public CoberturaDialog() { 
	}
	
	public interface C1_Cap00Fragment_001_01Listener {
		void onFinishEditDialog(String inputText);
	}
	
	public static CoberturaDialog newInstance(FragmentForm pagina, FragmentForm.OPCION opcion) {
		caller = pagina;
		CoberturaDialog f = new CoberturaDialog(); 
		f.setParent(pagina);
		f.opcion = opcion;
		Bundle args = new Bundle();
		f.setArguments(args);
		return f;
	}
	
	@Override    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		caretaker = new Caretaker<Entity>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) { 
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		final View rootView = createUI();	
		initObjectsWithoutXML(this, rootView);	
		enlazarCajas(this); 
		listening(); 
		cargarDatos();
		return C3VISITAS_Fragment_S3.createTitle(getActivity(), rootView, q0);
//		return rootView;		
    }

	@Override
	protected View createUI() {
		buildFields();
		q0 = createQuestionSection(lblTitulo);
		LinearLayout q6 = createQuestionSection(tcMarco.getTableView());		
		LinearLayout botones = createButtonSection(btnAceptar,btnCancelar); 
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
//		form.addView(q0);
		form.addView(q6);
		form.addView(botones);
		return contenedor;
	}

	@Override
	protected void buildFields() {
		String title = getResources().getString(R.string.c1p3_cob_id);
		title = title.replace("$", App.getInstance().getComisaria().id_n);
		lblTitulo = new LabelComponent(this.getActivity(),
				App.ESTILO).size(MATCH_PARENT, 750)
				.text(title).textSize(21).centrar().negrita();
		
		tcMarco = new TableComponent(getActivity(), this,
				App.ESTILO).size(730, 680)
				.dataColumHeight(65).headerHeight(55).headerTextSize(15);
		tcMarco.addHeader(R.string.c1p3_cap, 2f, ALIGN.LEFT);
		tcMarco.addHeader(R.string.c1p3_estado, 0.5f, ImageComponent.class, R.drawable.yes, R.drawable.delete);
		tcMarco.setCellColor(-1, 0, true);
		
		btnAceptar = new ButtonComponent(getActivity(), App.ESTILO_BOTON)
			.text(R.string.btnAceptar).size(200, 60);
    	btnCancelar = new ButtonComponent(getActivity(), App.ESTILO_BOTON)
    		.text(R.string.btnCancelar).size(200, 60);
    	btnCancelar.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {					
				CoberturaDialog.this.dismiss();
			}
		});
    	btnAceptar.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				boolean flag = grabar();
				if (!flag) {
					return;
				}		
				CoberturaDialog.this.dismiss();
			}
		});
	}

	private boolean grabar() {	
		return true;
	}

	private boolean validar() {
		return true;
	}
	
	private void cargarDatos() {
		inicio(); 
	}
	
	private void recargarLista() {
		tcMarco.setData(marcosCoberturas, "cap", "estado");
	}

	private void inicio(){
		inicio(true);
	}
	
	public void inicio(boolean flag) {	
		if(!flag) { 
			String[] caps = caller.getResources().getStringArray(R.array.cobertura_arr);
			marcosCoberturas = new ArrayList<Cobertura>();
			resultCobertura = true;
			for(int s=0;s<caps.length;s++){
				Cobertura c = getCoberturaService().getCapCob(App.getInstance().getComisaria().id_n, getInfo(s));
				c.cap = caps[s];
				if("1".equals(c.estado)) resultCobertura = false;
				marcosCoberturas.add(c);
			}
		}
		if(flag) recargarLista();
	}
	
	public boolean isResultCobertura() {
		return resultCobertura;
	}
	
	private String getInfo(int index){
		switch (index) {
			case 0: return "V_CARATULA_DEL";
			case 1: return "V_CAP100_DEL";
			case 2: return "V_CAP200_TD_DEL";
			case 3: return "V_CAP200_V100200_DEL";
			case 4: return "V_CAP200_FVO_DEL";
			case 5: return "V_CAP200_FVA_DEL";
			case 6: return "V_CAP300_PC_DEL";
			case 7: return "V_CAP300_PD_DEL";
			case 8: return "V_CAP300_PVF_DEL";
			case 9: return "V_CAP400_PC_DEL";
			case 10: return "V_CAP400_PD_DEL";
			case 11: return "V_CAP400_PV_DEL";
			default: return "";
		}
	}

	public MarcoService getService() {
		if (service == null) {
			service = MarcoService.getInstance(getActivity());
		}
		return service;
	}	

	public CoberturaService getCoberturaService() {
		if (coberturaService == null) {
			coberturaService = CoberturaService.getInstance(caller.getActivity());
		}
		return coberturaService;
	}
	
}
