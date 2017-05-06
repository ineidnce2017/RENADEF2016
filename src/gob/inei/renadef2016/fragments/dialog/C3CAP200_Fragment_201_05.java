package gob.inei.renadef2016.fragments.dialog;

import java.sql.SQLException;

import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP200_Fragment_201;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3CAP200_Fragment_201_05 extends DialogFragmentComponent {
	public interface C1CAP200_Fragment_201_01Listener {
		void onFinishEditDialog(String inputText);
	}

	@FieldAnnotation(orderIndex = 1)
	public RadioGroupOtherField rgIH212;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtIH213;
	@FieldAnnotation(orderIndex = 3)
	public TextField txtIH213_OFICIO;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtIH214;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtIH215;
	@FieldAnnotation(orderIndex = 6)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 7)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;  
	private static C3CAP200_Fragment_201 caller;
	private GridComponent2 grid_P213;
	private Cap200Delitos detalle;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;
	private static INF_Caratula01Service caratulaService;
	private boolean p215;

	public static C3CAP200_Fragment_201_05 newInstance(FragmentForm pagina,
			Cap200Delitos detalle, MarcoService marcoService, INF_Caratula01Service caratService) {
		caller = (C3CAP200_Fragment_201) pagina;
		mimarcoService = marcoService;
		caratulaService = caratService;
		Filtros.clear();
		C3CAP200_Fragment_201_05 f = new C3CAP200_Fragment_201_05();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP200_Fragment_201_05() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detalle = (Cap200Delitos) getArguments().getSerializable("detalle");
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
		
		return C3CAP200_Fragment_201.createTitle(getActivity(), "Denuncia N\u00B0: "+detalle.orden_200, 
				detalle.getP208CodC(), rootView, btnAtras, btnAdelante);

	}
//////////////////////////////////////////////
	private void cargarDatos() {
		entityToUI(detalle);
		inicio();
	}

	private void inicio() {
		onCheckedChange_P212(rgIH212);
		p215 = false;
		if(detalle!=null && detalle.getP208()!=null &&
				!Util.esDiferente(detalle.getP208(), 18,19,20,21,22,23)){
			caller.cleanAndLockView(txtIH215); p215 = true;
		} 
		rgIH212.requestFocus();
	}

	@Override
	protected View createUI() {
		buildFields();
		
		LinearLayout q0 = createQuestionSection(R.string.lb_c3_cap200_p212, rgIH212);
		LinearLayout q2 = createQuestionSection(R.string.lb_c3_cap200_p213, grid_P213.component());
		LinearLayout q3 = createQuestionSection(R.string.lb_c3_cap200_p214, txtIH214);
		LinearLayout q4 = createQuestionSection(R.string.lb_c3_cap200_p215, txtIH215);

		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q0);
		form.addView(q2);
		form.addView(q3);
		form.addView(q4);
		form.addView(botones);

		return contenedor;
	}

	@Override
	protected void buildFields() {

		LabelComponent lblP212F = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap200_p213_F).size(altoComponente, 170);
		LabelComponent lblP212N = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap200_p213_N).size(altoComponente, 170);
		
		rgIH212 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap200_p212_1,
				R.string.lb_c3_cap200_p212_2).size(sizeHeigth, sizeWidth).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL).callback("onCheckedChange_P212");
		
		txtIH213 = new TextField(getActivity(),false).size(altoComponente, 300);
		txtIH213_OFICIO = new TextField(getActivity(),false).size(altoComponente, 300);
		txtIH214 = new IntegerField(getActivity(),false).size(altoComponente, 120);
		txtIH215 = new IntegerField(getActivity(),false).size(altoComponente, 120);
		
		grid_P213 = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_P213.addComponent(lblP212F);
		grid_P213.addComponent(txtIH213);
		grid_P213.addComponent(lblP212N);
		grid_P213.addComponent(txtIH213_OFICIO);

		btnAceptar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnAceptar).size(200, 60);
		btnCancelar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnCancelar).size(200, 60);
		btnAtras = new ImageComponent(getParent().getActivity(), R.drawable.previous, R.drawable.previous).
				size(65, 65);
		btnAdelante = new ImageComponent(getParent().getActivity(), R.drawable.next, R.drawable.next).
				size(65, 65);
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				C3CAP200_Fragment_201_05.this.dismiss();
			}
		});
		btnAceptar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		btnAtras.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = C3CAP200_Fragment_201_05.this.getFragmentManager();
				C3CAP200_Fragment_201_04 aperturaDialog = C3CAP200_Fragment_201_04
						.newInstance(caller, detalle, mimarcoService, caratulaService);
				aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
				aperturaDialog.show(fm, "aperturaDialog");
				C3CAP200_Fragment_201_05.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		
		//Filtro
		Filtros.setFiltro(txtIH213, Filtros.TIPO.ALFAN_U, 100, null);
		Filtros.setFiltro(txtIH213_OFICIO, Filtros.TIPO.ALFAN_U, 50, new char[]{'-','/'});
		Filtros.setFiltro(txtIH214, Filtros.TIPO.NUMBER, 3, 0,null,1,998);
		Filtros.setFiltro(txtIH215, Filtros.TIPO.NUMBER, 3, 0,null,1,998);


	}  
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3CAP200_Fragment_201_05.this.dismiss();
		
		FragmentManager fm = caller.getFragmentManager();
		C3CAP200_Fragment_201_Obs aperturaDialog = C3CAP200_Fragment_201_Obs.
				newInstance(caller, detalle, mimarcoService, caratulaService);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}

	private boolean validar() {
		
		error = false;
		if (!isInRange())
			return false;
		
		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
	
		if(Util.esVacio(rgIH212)){
			mensaje="La pregunta 212 no puede estar Vacia";
			view=rgIH212;
			error=true;
			return false;
		}
		
		else if(rgIH212.isTagSelected(1) ){
			if (Util.esVacio(txtIH213) || Util.esVacio(txtIH213_OFICIO)){
				mensaje=Util.esVacio(txtIH213) ? "Ingrese Fiscalia" : "Ingrese N\u00B0 de Oficio";
				view=Util.esVacio(txtIH213) ? txtIH213 : txtIH213_OFICIO;
				error=true;
				return false;
			} else {
				String txt = txtIH213_OFICIO.getText().toString().trim();
				if(Util.contDigits(txt) == txt.length() && Util.sumDigits(txt) == 0){
					mensaje="Verifique informaci\u00f3n en N\u00B0 de Oficio.";
					view=txtIH213_OFICIO;
					error=true;
					return false;
				}
			}
		}
		
		if(Util.esVacio(txtIH214)){
			mensaje="La pregunta 214 no puede estar vacia";
			view=txtIH214;
			error=true;
			return false;
		}
		if(!p215){
			if(Util.esVacio(txtIH215)){
				mensaje="La pregunta 215 no puede estar vacia";
				view=txtIH215;
				return !(error=true);
			} 
		}

		if (error) return false;
		return true;
	}
	//Alex rgIH211
	
	public void onCheckedChange_P212(FieldComponent component){
		if(component.getValue() == null) return;
		String result = component.getValue().toString();
		if(result.equals("1")){	
			caller.lockView(false, txtIH213, txtIH213_OFICIO);
			txtIH213.requestFocus();
		} else if(result.equals("2")){
			caller.cleanAndLockView(txtIH213,txtIH213_OFICIO);
			txtIH214.requestFocus();
		}		
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
		
		uiToEntity(detalle);
		try {
			if(!mimarcoService.saveOrUpdate(detalle, detalle.getSecCap(caller.getListFields(this)))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			} else {
				caller.reloadData(detalle, 1);
			}
		} catch (SQLException e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
		}
		
//		uiToEntity(detalle);
//		if(!mimarcoService.saveCap200Delitos(detalle, Utilidades.getListFields(this))){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
//		else {
//			caller.reloadData(detalle, 1);
//		}
		
		return true;
	}
	
	
}


