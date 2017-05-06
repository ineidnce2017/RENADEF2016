package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP300_Fragment_301;
import gob.inei.renadef2016.modelo.delitos.Cap300Delitos;
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

public class C3CAP300_Fragment_301_03 extends DialogFragmentComponent {
	public interface C1CAP300_Fragment_301_01Listener {
		void onFinishEditDialog(String inputText);
	}
	
	@FieldAnnotation(orderIndex = 1)
	public RadioGroupOtherField rgIVH309;
	@FieldAnnotation(orderIndex = 2)
	public RadioGroupOtherField rgIVH310;
	@FieldAnnotation(orderIndex = 3)
	public TextField txtIVH310_O;
	@FieldAnnotation(orderIndex = 4)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 5)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private static C3CAP300_Fragment_301 caller;
	private Cap300Delitos detalle;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;
 
	public static C3CAP300_Fragment_301_03 newInstance(FragmentForm pagina,
			Cap300Delitos detalle, MarcoService marcoService) {
		caller = (C3CAP300_Fragment_301) pagina;
		mimarcoService = marcoService; 
		Filtros.clear();
		C3CAP300_Fragment_301_03 f = new C3CAP300_Fragment_301_03();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP300_Fragment_301_03() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detalle = (Cap300Delitos) getArguments().getSerializable("detalle");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = createUI();
//		getDialog().setTitle("Victima/Fallecido N\u00B0: "+detalle.nro_vfreg);
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		initObjectsWithoutXML(this, rootView);

		cargarDatos();
		enlazarCajas(this);
		listening();
		
		return C3CAP300_Fragment_301.createTitle(getActivity(), "V\u00cdctima/Fallecido N\u00B0: "+detalle.orden_300+ 
				(C3CAP300_Fragment_301.cap300cap200.ih214==null?"":"/"+C3CAP300_Fragment_301.cap300cap200.ih214), 
				C3CAP300_Fragment_301.cap300cap200.getP208CodC(), rootView, btnAtras, btnAdelante);

	}

	private void cargarDatos() {
		entityToUI(detalle);
		inicio();
	}

	private void inicio() {
		if(Util.getInt(detalle.ivh304) == 2){
			caller.cleanAndLockView(rgIVH309);
		} else {
			onCheckedChange_P309(rgIVH309);
		}
		if(App.getInstance().getCap200().ih215 == null){
			caller.cleanAndLockView(rgIVH310);
		} 
	}

	@Override
	protected View createUI() {
		buildFields();
		
		LinearLayout q0 = createQuestionSection(R.string.lb_c3_cap300_p309, rgIVH309);
		LinearLayout q1 = createQuestionSection(R.string.lb_c3_cap300_p310, rgIVH310);

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
		
		txtIVH310_O = new TextField(getActivity()).size(altoComponente, 400);
		
		rgIVH309 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap300_p308_1,
				R.string.lb_c3_cap300_p308_2, R.string.lb_c3_cap300_p308_3, R.string.lb_c3_cap300_p308_4,
				R.string.lb_c3_cap300_p308_5, R.string.lb_c3_cap300_p308_6, R.string.lb_c3_cap300_p308_7).
				size(sizeHeigth, sizeWidth).orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);//.callback("onCheckedChange_P309");
		rgIVH310 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap300_p309_1,
				R.string.lb_c3_cap300_p309_2, R.string.lb_c3_cap300_p309_3, R.string.lb_c3_cap300_p309_4,
				R.string.lb_c3_cap300_p309_5, R.string.lb_c3_cap300_p309_6, R.string.lb_c3_cap300_p309_7,
				R.string.lb_c3_cap300_p309_8, R.string.lb_c3_cap300_p309_9, R.string.lb_c3_cap300_p309_10,
				R.string.lb_c3_cap300_p309_11).size(sizeHeigth, sizeWidth).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL); //.callback("onCheckedChange_P309");
		rgIVH310.agregarEspecifique(10, txtIVH310_O);

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
				C3CAP300_Fragment_301_03.this.dismiss();
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
				FragmentManager fm = C3CAP300_Fragment_301_03.this.getFragmentManager();
				C3CAP300_Fragment_301_02 aperturaDialog = C3CAP300_Fragment_301_02
						.newInstance(caller, detalle, mimarcoService);
				aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
				aperturaDialog.show(fm, "aperturaDialog");
				C3CAP300_Fragment_301_03.this.dismiss();
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
		C3CAP300_Fragment_301_03.this.dismiss();
		
		FragmentManager fm = caller.getFragmentManager();
		C3CAP300_Fragment_301_Obs aperturaDialog = C3CAP300_Fragment_301_Obs.
				newInstance(caller, detalle, mimarcoService);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}

	private boolean validar() {
		error = false;
	
//		Si (IVH308 <> blanco) & ((IVH304 < 12) or (IVH304A < 3)) => Error 
		if(rgIVH309.isEnabled()){
			if(Util.esVacio(rgIVH309)){
				mensaje = "La pregunta 309 no puede estar Vacia";
				view = rgIVH309;
				error = true;
				return false;
			}
			else if(Integer.valueOf(12).compareTo(detalle.ivh305)>0 || Integer.valueOf(3).compareTo(detalle.ivh305)>0){
				mensaje = "Estado civil y edad no se relacionan";
				view = rgIVH309;
				error = true;
				return false;			
			}			
			else if(rgIVH309.isTagSelectedBetween(2, 5) && Integer.valueOf(detalle.ivh305.toString())<16 ){
				mensaje= "Estado civil y edad no se relacionan";
				view = rgIVH309;
				error=true;
				return false;
			}
		}
		
		if(rgIVH310.isEnabled()){
			int edad = detalle.ivh305 == null ? -1 : detalle.ivh305.intValue();
			if(Util.esVacio(rgIVH310)){
				mensaje = "La pregunta 310 no puede estar Vacia";
				view = rgIVH310;
				error = true;
				return false;
			}
			else if((rgIVH310.isTagSelected(1) && edad >=0 && edad <= 15) ||
					(rgIVH310.isTagSelected(2) && edad >=0 && edad <= 18) ||
					(rgIVH310.isTagSelected(3) && edad >=0 && edad <= 12) ||
					(rgIVH310.isTagSelected(4) && edad >=0 && edad <= 15) ||
					(rgIVH310.isTagSelected(5) && edad >=80 && edad <= 98) /*||
					(rgIVH310.isTagSelected(6) && edad >=0 && edad <= 19)*/
					){
				mensaje="La edad no corresponde a relación de parentesco con el presunto victimario";
				view = rgIVH310;
				error=true;
				return false;			
			}
			else if(rgIVH310.isTagSelected(11) && Util.esVacio(txtIVH310_O)){
				mensaje="Especifique debe ser contestado.";
				view = txtIVH310_O;
				error=true;
				return false;			
			}
			else if((rgIVH310.isTagSelectedBetween(1, 4) || rgIVH310.isTagSelected(6))
					&& edad==99 && !Util.esDiferente(detalle.ivh304, 1,2)){
				mensaje="La caracteristica de la edad no corresponde a relaci\u00f3n de parentesco "
						+ "con el presunto vistimario.";
				view = rgIVH310;
				error=true;
				return false;			
			}
			else if((!rgIVH310.isTagSelected(5) && C3CAP300_Fragment_301.cap300cap200!=null &&
					Integer.valueOf(5).equals(C3CAP300_Fragment_301.cap300cap200.getP208()))){
				mensaje="Relaci\u00f3n de parentesco no se relaciona con el Tipo de Delito.";
				view = rgIVH310;
				error=true;
				return false;			
			}
		}
		
//		VERIFICACIONES
		ArrayList<String> checkList= new ArrayList<String>();    
		
		if(rgIVH309.isEnabled()){
			if(rgIVH309.isTagSelected(1) && Integer.valueOf(detalle.ivh305)<18){
				mensaje= "VERIFICAR: Menor de Edad.";			
				checkList.add(mensaje);				
			}
			if(rgIVH309.isTagSelectedBetween(2, 5) && (Integer.valueOf(detalle.ivh305)>15 && Integer.valueOf(detalle.ivh305)<18)){
				mensaje= "VERIFICAR: Menor de Edad.";			
				checkList.add(mensaje);				
			}    
			if(rgIVH309.isTagSelected(6) && rgIVH310.isTagSelectedBetween(1, 4)){
				mensaje = "VERIFICAR: Soltero/a y relacion de Parentesco con victimario.";			
				checkList.add(mensaje);
			}
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
	
	public void onCheckedChange_P309(FieldComponent component){		
		if(!Util.esVacio(detalle.ivh304)){
			if(Util.getInt(detalle.ivh304) < 3){
				caller.cleanAndLockView(rgIVH309);
			} 
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
//		if(!mimarcoService.saveCap300Delitos(detalle, Utilidades.getListFields(this))){
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
