package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.RadioGroupOtherField;
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

public class C3CAP400_Fragment_401_03 extends DialogFragmentComponent {
	public interface C1CAP300_Fragment_301_01Listener {
		void onFinishEditDialog(String inputText);
	}

	@FieldAnnotation(orderIndex = 1)
	public RadioGroupOtherField rgIVM408;
	@FieldAnnotation(orderIndex = 2)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 3)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private static C3CAP400_Fragment_401 caller;
	LinearLayout q0, q1, q2, q3, q4, q5,q7;
	private Cap400Delitos detalle;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT; 
	private static MarcoService mimarcoService;

	public static C3CAP400_Fragment_401_03 newInstance(FragmentForm pagina,
			Cap400Delitos detalle, MarcoService marcoService) {
		caller = (C3CAP400_Fragment_401) pagina;
		mimarcoService = marcoService;
		Filtros.clear();
		C3CAP400_Fragment_401_03 f = new C3CAP400_Fragment_401_03();
		f.setParent(pagina);
		Bundle args = new Bundle(); 
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP400_Fragment_401_03() {
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
		rgIVM408.requestFocus();
	}

	@Override
	protected View createUI() {
		buildFields();
		
		q0 = createQuestionSection(R.string.lb_c3_cap400_p408, rgIVM408);

		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q0);

		form.addView(botones);

		return contenedor;
	}

	@Override
	protected void buildFields() {
		
		rgIVM408 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap400_p408_1,
				R.string.lb_c3_cap400_p408_2, R.string.lb_c3_cap400_p408_3, R.string.lb_c3_cap400_p408_4,
				R.string.lb_c3_cap400_p408_5, R.string.lb_c3_cap400_p408_6, R.string.lb_c3_cap400_p408_7).
				size(sizeHeigth, sizeWidth).orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);

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
				C3CAP400_Fragment_401_03.this.dismiss();
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
				FragmentManager fm = C3CAP400_Fragment_401_03.this.getFragmentManager();
				C3CAP400_Fragment_401_02 aperturaDialog = C3CAP400_Fragment_401_02
						.newInstance(caller, detalle, mimarcoService);
				aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
				aperturaDialog.show(fm, "aperturaDialog");
				C3CAP400_Fragment_401_03.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
	}
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3CAP400_Fragment_401_03.this.dismiss();
		
		FragmentManager fm = caller.getFragmentManager();
		C3CAP400_Fragment_401_Obs aperturaDialog = C3CAP400_Fragment_401_Obs.
				newInstance(caller, detalle, mimarcoService);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}
	
	private int getP408dist2y5(){
		int cont = 0;
		if(C3CAP400_Fragment_401.getData().size()>0){
			for(Cap400Delitos c:C3CAP400_Fragment_401.getData()){
				if((Integer.valueOf(2).equals(c.ivm408) ||
						Integer.valueOf(5).equals(c.ivm408)) && 
						detalle.nro_pvreg != c.nro_pvreg) cont++;
			}
		}
		return cont;
	}

	private boolean validar() {
		error=false;

		if(Util.esVacio(rgIVM408)){
			mensaje="La pregunta 408 no puede estar vacia.";
			error=true;
			return false;
		}
		
		int edad = detalle.ivm404==null?-1:Integer.parseInt(detalle.ivm404);
		
		if(edad!=-1){
			if(edad < 12 && !rgIVM408.isTagSelected(7)){
				mensaje = "Estado civil y edad no se relacionan";
				view = rgIVM408;
				error = true;
				return false;	
			} else if(rgIVM408.isTagSelectedBetween(2, 5) && edad<16 ){
				mensaje= "Estado civil y edad no se relacionan";
				view = rgIVM408;
				error=true;
				return false;
			} else if((C3CAP400_Fragment_401.getData().size() == C3CAP400_Fragment_401.cap400cap200.ih214)
					&& (!rgIVM408.isTagSelectedBetween(new Integer[]{2, 5}) && getP408dist2y5()==0 &&
					mimarcoService.getConteoVictimas308y309(detalle.id_n, detalle.nro_mreg) > 0)){
				mensaje= "Estado civil debe estar de acuerdo a lo indicado en capítulo 300 "
						+ "(cuando relaci\u00f3n de parentesco con presunto victimario es esposo).";
				view = rgIVM408;
				error=true;
				return false;
			} 
		}			
		
//		VALIDACIONES
		ArrayList<String> checkList= new ArrayList<String>();
		
		if(rgIVM408.isTagSelected(1) && edad < 18){
			mensaje="VERIFICAR: Menor de Edad.";
			checkList.add(mensaje);	
		}
		if(rgIVM408.isTagSelectedBetween(2, 5) && edad > 15 && edad < 18){
			mensaje="VERIFICAR: Menor de Edad.";
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
