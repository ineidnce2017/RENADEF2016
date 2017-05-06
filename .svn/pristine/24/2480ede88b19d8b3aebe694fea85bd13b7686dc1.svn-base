package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP400_Fragment_401;
import gob.inei.renadef2016.modelo.delitos.Cap400Delitos;
import gob.inei.renadef2016.service.MarcoService;

import java.sql.SQLException;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3CAP400_Fragment_401_02 extends DialogFragmentComponent {
	public interface C1CAP300_Fragment_301_01Listener {
		void onFinishEditDialog(String inputText);
	}

	@FieldAnnotation(orderIndex = 1)
	public RadioGroupOtherField rgIVM406;
	@FieldAnnotation(orderIndex = 2)
	public RadioGroupOtherField rgIVM407;
	@FieldAnnotation(orderIndex = 3)
	public TextField txtIVM407_O;
	@FieldAnnotation(orderIndex = 4)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 5)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private static C3CAP400_Fragment_401 caller;
	LinearLayout q0, q1, q2, q3, q4, q5,q7; 
	private Cap400Delitos detalle;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;

	public static C3CAP400_Fragment_401_02 newInstance(FragmentForm pagina,
			Cap400Delitos detalle, MarcoService marcoService) {
		caller = (C3CAP400_Fragment_401) pagina;
		mimarcoService = marcoService; 
		Filtros.clear();
		C3CAP400_Fragment_401_02 f = new C3CAP400_Fragment_401_02();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP400_Fragment_401_02() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detalle = (Cap400Delitos) getArguments().getSerializable("detalle");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = createUI();
//		getDialog().setTitle("Victimario N\u00B0: "+detalle.nro_pvreg);
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		initObjectsWithoutXML(this, rootView);

		cargarDatos();
		enlazarCajas(this);
		listening();
		
		return C3CAP400_Fragment_401.createTitle(getActivity(), "Victimario N\u00B0: "+detalle.orden_400+
				(C3CAP400_Fragment_401.cap400cap200.ih215==null?"":"/"+C3CAP400_Fragment_401.cap400cap200.ih215),
				C3CAP400_Fragment_401.cap400cap200.getP208CodC(), rootView, btnAtras, btnAdelante);

	}

	private void cargarDatos() {
		entityToUI(detalle);
		inicio();
	}

	private void inicio() {
		rgIVM406.requestFocus();
		if(Integer.valueOf(10).equals(C3CAP400_Fragment_401.cap400cap200.getP208())){
			rgIVM407.lockButtons(true, 0,1,2,3,4,5,6,7,9,10,11,13,14,15);
		}
	}

	@Override
	protected View createUI() {
		buildFields();
		
		q0 = createQuestionSection(R.string.lb_c3_cap400_p406, rgIVM406);
		q1 = createQuestionSection(R.string.lb_c3_cap400_p407, rgIVM407);

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
	
		txtIVM407_O = new TextField(getActivity(),false).size(altoComponente, 400);
		
		rgIVM406 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap400_p406_1,
				R.string.lb_c3_cap400_p406_2, R.string.lb_c3_cap400_p406_3, R.string.lb_c3_cap400_p406_4,
				R.string.lb_c3_cap400_p406_5, R.string.lb_c3_cap400_p406_6, R.string.lb_c3_cap400_p406_7,
				R.string.lb_c3_cap400_p406_8).size(sizeHeigth, sizeWidth).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
		rgIVM407 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap400_p407_1,
				R.string.lb_c3_cap400_p407_2, R.string.lb_c3_cap400_p407_3, R.string.lb_c3_cap400_p407_4,
				R.string.lb_c3_cap400_p407_5, R.string.lb_c3_cap400_p407_6, R.string.lb_c3_cap400_p407_7,
				R.string.lb_c3_cap400_p407_8, R.string.lb_c3_cap400_p407_9, R.string.lb_c3_cap400_p407_10,
				R.string.lb_c3_cap400_p407_11, R.string.lb_c3_cap400_p407_12, R.string.lb_c3_cap400_p407_13,
				R.string.lb_c3_cap400_p407_14, R.string.lb_c3_cap400_p407_15, R.string.lb_c3_cap400_p407_16,
				R.string.lb_c3_cap400_p407_17, R.string.lb_c3_cap400_p407_18).size(sizeHeigth, sizeWidth).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
		rgIVM407.agregarEspecifique(17, txtIVM407_O);

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
				C3CAP400_Fragment_401_02.this.dismiss();
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
				FragmentManager fm = C3CAP400_Fragment_401_02.this.getFragmentManager();
				C3CAP400_Fragment_401_01 aperturaDialog = C3CAP400_Fragment_401_01
						.newInstance(caller, detalle, detalle.orden_400, mimarcoService);
				aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
				aperturaDialog.show(fm, "aperturaDialog");
				C3CAP400_Fragment_401_02.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		/*FILTROS 407.*/
		
		Filtros.setFiltro(txtIVM407_O, Filtros.TIPO.ALFAN_U, 50, null);
		
	}
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3CAP400_Fragment_401_02.this.dismiss();
		
		FragmentManager fm = caller.getFragmentManager();
		C3CAP400_Fragment_401_03 aperturaDialog = C3CAP400_Fragment_401_03.
				newInstance(caller, detalle, mimarcoService);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}

	private boolean validar() {
		error=false;
		
		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
		
		if(Util.esVacio(rgIVM406)){
			mensaje="La pregunta 406 no puede estar vacio.";
			view = rgIVM406;
			error=true;
			return false;
		}
		
		int edad = detalle.ivm404==null?-1:Integer.parseInt(detalle.ivm404);
		if((rgIVM406.isTagSelected(3) && edad >= 0 && edad <= 4) ||
				(rgIVM406.isTagSelected(4) && edad >= 0 && edad <= 10) ||
				(rgIVM406.isTagSelectedBetween(5, 6) && edad >= 0 && edad <= 15) ||
				(rgIVM406.isTagSelected(7) && edad >= 0 && edad <= 21)){
			mensaje = "La edad no corresponde al nivel educativo alcanzado.";
			view = rgIVM406;
			error = true;
			return false;
		}
		
		if(Util.esVacio(rgIVM407)){
			mensaje="La pregunta 407 no puede estar vacio.";
			view = rgIVM407;
			error=true;
			return false;
		}
		else if(edad!=99 && ((rgIVM407.isTagSelected(1) && edad >= 0 && edad <= 11) ||
				(rgIVM407.isTagSelected(2) && edad >= 0 && edad <= 12) ||
				(rgIVM407.isTagSelected(3) && edad >= 0 && edad <= 15) ||
				(rgIVM407.isTagSelected(4) && edad >= 0 && edad <= 17) ||
				(rgIVM407.isTagSelected(5) && edad >= 0 && edad <= 15) ||
				(rgIVM407.isTagSelected(6) && edad >= 0 && edad <= 15) ||
				(rgIVM407.isTagSelectedBetween(7, 8) && edad >= 0 && edad <= 11) ||
				(rgIVM407.isTagSelected(9) && ((edad >= 0 && edad <= 18) || edad > 80)) ||
				(rgIVM407.isTagSelected(10) && edad >= 0 && edad <= 15) ||
				(rgIVM407.isTagSelected(11) && (edad < 3 || edad > 80)) ||
				(rgIVM407.isTagSelected(12) && ((edad >= 0 && edad <= 11) || edad > 80)) ||
				(rgIVM407.isTagSelected(13) && ((edad >= 0 && edad <= 15) || edad > 75)) ||
				(rgIVM407.isTagSelected(14) && ((edad >= 0 && edad <= 11) || edad > 80)) ||
				(rgIVM407.isTagSelected(15) && edad >=0 && edad <= 11) ||
				(rgIVM407.isTagSelected(16) && edad < 40) ||
				(rgIVM407.isTagSelectedBetween(17, 18) && edad >= 0 && edad <= 11))){
			mensaje = "La edad no corresponde a la ocupaci\u00f3n.";
			view = rgIVM407;
			error = true;
			return false;
		}
		else if(rgIVM407.isTagSelected(13) && rgIVM406.isTagSelectedBetween(1,3)){
			mensaje = "Es miembro de las FF.AA/PNP y el nivel educativo m\u00e1ximo alcanzado es primaria.";
			view = rgIVM407;
			error = true;
			return false;
		}
		else if(rgIVM407.isTagSelected(18) && Util.esVacio(txtIVM407_O)){
			mensaje = "Especifique no puede estar vacio.";
			view = txtIVM407_O;
			error = true;
			return false;
		}
		
//		VERIFICACIONES
		ArrayList<String> checkList= new ArrayList<String>();
		
		if(rgIVM407.isTagSelected(2) && edad < 10){
			mensaje="VERIFICAR: Ama de casa con menos de 10 a\u00f1os de edad.";
			checkList.add(mensaje);	
		}
		if(rgIVM407.isTagSelected(11) && rgIVM406.isTagSelected(1)){
			mensaje="VERIFICAR: Es estudiante de ocupación y sin nivel educativo alcanzado.";
			checkList.add(mensaje);	
		}
		
		if(checkList.size()>0){
			for(String s: checkList){
				ToastMessage.msgBox(this.getActivity(), s,
						ToastMessage.MESSAGE_INFO,
						ToastMessage.DURATION_LONG);
			}
		}

		if (error) return false;
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
//		if(!mimarcoService.saveCap400Delitos(detalle, Utilidades.getListFields(this))){
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
