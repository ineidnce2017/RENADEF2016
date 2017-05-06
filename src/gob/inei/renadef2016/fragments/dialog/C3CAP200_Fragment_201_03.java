package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP200_Fragment_201;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.service.ATVisitaService;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;

import java.sql.SQLException;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3CAP200_Fragment_201_03 extends DialogFragmentComponent {
	public interface C1CAP200_Fragment_201_01Listener {
		void onFinishEditDialog(String inputText);
	}

//	@FieldAnnotation(orderIndex = 1)
//	public RadioGroupOtherField rgIH209;
//	@FieldAnnotation(orderIndex = 2)
//	public TextField txtIH209_O;
	@FieldAnnotation(orderIndex = 1)
	public RadioGroupOtherField rgIH209_COD;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtIH209_COD_O;
//	@FieldAnnotation(orderIndex = 5)
//	public RadioGroupOtherField rgIH210;
//	@FieldAnnotation(orderIndex = 6)
//	public TextField txtIH210_O; 
	@FieldAnnotation(orderIndex = 3)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 4)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private static C3CAP200_Fragment_201 caller;
	private Cap200Delitos detalle; 
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;
	private static ATVisitaService atvisitaService;
	private static INF_Caratula01Service caratulaService;
	private LabelComponent lblNom0;

	public static C3CAP200_Fragment_201_03 newInstance(FragmentForm pagina,
			Cap200Delitos detalle, MarcoService marcoService, INF_Caratula01Service caratService) {
		caller = (C3CAP200_Fragment_201) pagina;
		mimarcoService = marcoService;
		caratulaService = caratService;
		Filtros.clear();
		C3CAP200_Fragment_201_03 f = new C3CAP200_Fragment_201_03();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP200_Fragment_201_03() {
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

	private void cargarDatos() {
		set209Cod();
		entityToUI(detalle);
		inicio();
	}
	
	private void set209Cod() {
//		int p209 = Util.getInt(detalle.ih209, -1);
//		boolean p209c = detalle.ih209_cod == null;
//		if(p209!=-1 && p209c){
//			if(p209 <= 8) detalle.ih209_cod = p209;
//			if(p209 >= 9 && p209 <= 10) detalle.ih209_cod = 9;
//			if(p209 == 13) detalle.ih209_cod = 20;
//			if(p209 == 14) {
//				detalle.ih209_cod = 21;
//				detalle.ih209_cod_o = detalle.ih209_o;
//			}
//		}
		
		if(detalle.ih209 == null || detalle.ih209_cod != null){
			lblNom0.setVisibility(View.GONE);
		} else {
			lblNom0.setText(detalle.getP209C());
		}
	}

	private void inicio() {
		switch (detalle.getP208()) {
			case 6: rgIH209_COD.lockButtons(true, 0); break;
//			case 8: rgIH209.lockButtons(true, 2,3,4,5); break;
			case 9: rgIH209_COD.lockButtons(true, 7); break;
//			case 14: rgIH209_COD.lockButtons(true, 13); break;
			default: break;
		}
//		switch (detalle.getP208()) {
//			case 1:case 2:case 6:case 7: rgIH210.lockButtons(true, 0,4,5,8,9,10,11,14,15,18,21,22,23,24); break;
//			case 3: rgIH210.lockButtons(true, 0,8,9,10,11,14,18,21,22,23,24); break;
//			case 4: rgIH210.lockButtons(true, 0,4,5,8,9,10,14,15,18,21,22,23,24); break;
//			case 5: rgIH210.lockButtons(true, 0,4,5,8,9,10,11,12,14,15,18,21,22,23,24); break;
//			case 8: rgIH210.lockButtons(true, 0,4,5,8,9,10,11,14,15,21,22,23,24); break;
//			case 9: rgIH210.lockButtons(true, 0,3,4,5,6,7,8,9,10,11,12,14,15,16,18,19,20,21,22,23,24); break;
//			case 10: rgIH210.lockButtons(true, 0,1,3,4,5,8,9,10,11,12,14,18,21,22,23,24); break;
//			case 12: rgIH210.lockButtons(true, 1,2,3,4,5,8,9,10,11,14,15,18,21,22,23,24); break;
//			case 14: rgIH210.lockButtons(true, 0,4,5,8,9,10,11,14,15,21,22,23,24); break;
//			case 15: rgIH210.lockButtons(true, 0,1,4,5,8,9,10,11,14,18,21,22,23,24); break;
//			case 16: rgIH210.lockButtons(true, 0,1,4,5,8,9,10,14,15,19,20,21,22,23,24); break;
//			default: break;
//		}

		rgIH209_COD.requestFocus();
	}

	@Override
	protected View createUI() {
		buildFields();
		
//		LinearLayout q0 = createQuestionSection(R.string.lb_c3_cap200_p209, rgIH209);
		LinearLayout q0 = createQuestionSection(R.string.lb_c3_cap200_p209, lblNom0, rgIH209_COD);
//		LinearLayout q1 = createQuestionSection(R.string.lb_c3_cap200_p210, rgIH210);
		
		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q0);
//		form.addView(q1);

		form.addView(botones);

		return contenedor;
	}

	@Override
	protected void buildFields() {
		
//		txtIH209_O = new TextField(getActivity(), false).size(altoComponente, 400);
		txtIH209_COD_O = new TextField(getActivity(), false).size(altoComponente, 400);
//		txtIH210_O = new TextField(getActivity(), false).size(altoComponente, 400);
		
//		rgIH209 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap200_p209_1,
//				R.string.lb_c3_cap200_p209_2, R.string.lb_c3_cap200_p209_3, R.string.lb_c3_cap200_p209_4,
//				R.string.lb_c3_cap200_p209_5, R.string.lb_c3_cap200_p209_6, R.string.lb_c3_cap200_p209_7,
//				R.string.lb_c3_cap200_p209_8, R.string.lb_c3_cap200_p209_9, R.string.lb_c3_cap200_p209_10,
//				R.string.lb_c3_cap200_p209_11, R.string.lb_c3_cap200_p209_12, R.string.lb_c3_cap200_p209_13,
//				R.string.lb_c3_cap200_p209_14).size(sizeHeigth, sizeWidth).
//				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
//		rgIH209.agregarEspecifique(13, txtIH209_O);
		
		lblNom0 = new LabelComponent(getActivity()).
				size(altoComponente, 650).centrar().negrita()
				.colorFondo(R.color.celesteclarito);
		rgIH209_COD = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap200_p209_1,
				R.string.lb_c3_cap200_p209_2, R.string.lb_c3_cap200_p209_3, R.string.lb_c3_cap200_p209_4,
				R.string.lb_c3_cap200_p209_5, R.string.lb_c3_cap200_p209_6, R.string.lb_c3_cap200_p209_7,
				R.string.lb_c3_cap200_p209_8, R.string.lb_c3_cap200_p209_9c, R.string.lb_c3_cap200_p209_10c,
				R.string.lb_c3_cap200_p209_11, R.string.lb_c3_cap200_p209_12, R.string.lb_c3_cap200_p209_13c,
				R.string.lb_c3_cap200_p209_14c, R.string.lb_c3_cap200_p209_15c, R.string.lb_c3_cap200_p209_16c,
				R.string.lb_c3_cap200_p209_17c, R.string.lb_c3_cap200_p209_18c, R.string.lb_c3_cap200_p209_19c,
				R.string.lb_c3_cap200_p209_20c, R.string.lb_c3_cap200_p209_21c).size(sizeHeigth, sizeWidth).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
		rgIH209_COD.agregarEspecifique(20, txtIH209_COD_O);
		
//		rgIH210 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap200_p210_1,
//				R.string.lb_c3_cap200_p210_2, R.string.lb_c3_cap200_p210_3, R.string.lb_c3_cap200_p210_4,
//				R.string.lb_c3_cap200_p210_5, R.string.lb_c3_cap200_p210_6, R.string.lb_c3_cap200_p210_7,
//				R.string.lb_c3_cap200_p210_8, R.string.lb_c3_cap200_p210_9, R.string.lb_c3_cap200_p210_10,
//				R.string.lb_c3_cap200_p210_11, R.string.lb_c3_cap200_p210_12, R.string.lb_c3_cap200_p210_13,
//				R.string.lb_c3_cap200_p210_14, R.string.lb_c3_cap200_p210_15, R.string.lb_c3_cap200_p210_16,
//				R.string.lb_c3_cap200_p210_17, R.string.lb_c3_cap200_p210_18, R.string.lb_c3_cap200_p210_19,
//				R.string.lb_c3_cap200_p210_20, R.string.lb_c3_cap200_p210_21, R.string.lb_c3_cap200_p210_22,
//				R.string.lb_c3_cap200_p210_23, R.string.lb_c3_cap200_p210_24, R.string.lb_c3_cap200_p210_25,
//				R.string.lb_c3_cap200_p210_26, R.string.lb_c3_cap200_p210_27).size(sizeHeigth, sizeWidth).
//				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
//		rgIH210.agregarEspecifique(26, txtIH210_O);

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
				C3CAP200_Fragment_201_03.this.dismiss();
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
				FragmentManager fm = C3CAP200_Fragment_201_03.this.getFragmentManager();
				C3CAP200_Fragment_201_02 aperturaDialog = C3CAP200_Fragment_201_02
						.newInstance(caller, detalle, mimarcoService, caratulaService);
				aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
				aperturaDialog.show(fm, "aperturaDialog");
				C3CAP200_Fragment_201_03.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		
//		Filtros.setFiltro(txtIH209_O, Filtros.TIPO.ALFAN_U, 50, null);
		Filtros.setFiltro(txtIH209_COD_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH210_O, Filtros.TIPO.ALFAN_U, 50, null);

	}
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3CAP200_Fragment_201_03.this.dismiss();
		
		FragmentManager fm = caller.getFragmentManager();
		C3CAP200_Fragment_201_03_1 aperturaDialog = C3CAP200_Fragment_201_03_1.
				newInstance(caller, detalle, mimarcoService, caratulaService);
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
		
		if(detalle.ih209 == null && Util.esVacio(rgIH209_COD)){
			mensaje="La pregunta 209 no puede estar Vacia";
			view=rgIH209_COD;
			error=true;
			return false;
		}
		
		if(!Util.esVacio(rgIH209_COD) && rgIH209_COD.isTagSelected(21) && Util.esVacio(txtIH209_COD_O)){ 
			mensaje="Especifique no puede estar vacio";
			view=txtIH209_COD_O;
			error=true;
			return false;			
		}
//		if(Util.esVacio(rgIH210)){
//			mensaje="La pregunta 210 no puede estar Vacia";
//			view=rgIH210;
//			error=true;
//			return false;
//		} else if(rgIH210.isTagSelected(27) && Util.esVacio(txtIH210_O)){ 
//			mensaje="Especifique no puede estar vacio";
//			view=txtIH210_O;
//			error=true;
//			return false;			
//		} else if(Integer.valueOf(1).equals(detalle.getP208()) && rgIH209_COD.isTagSelected(11) && rgIH210.isTagSelected(21)){
//			mensaje="Verificar; Si es homicidio simple en v\u00eda p\u00fablica y su medio/modalidad "
//					+ "utilizada es envenenamiento.";
//			view=rgIH210;
//			error=true;
//			return false;
//		}
		
		//VERIFICACIONES
//		ArrayList<String> checkList = new ArrayList<String>();
//		if(Integer.valueOf(1).equals(detalle.ih208) && rgIH209.isTagSelected(11) && rgIH210.isTagSelected(21)){
//			mensaje="VERIFICAR: Si es homicidio simple en v\u00eda p\u00fablica y su medio/modalidad "
//					+ "utilizada es o envenenamiento.";
//			checkList.add(mensaje);
//		}
		
//		if(Integer.valueOf(10).equals(detalle.ih208) && rgIH208.isTagSelectedBetween(1, 3)){
//			mensaje="VERIFICAR: Medio/modalidad NO CONCUERDA CON PREGUNTA 204.";
//			checkList.add(mensaje);
//		}
//
//		if(checkList.size()>0){
//			for(String s:checkList){
//				ToastMessage.msgBox(this.getActivity(), s, ToastMessage.MESSAGE_INFO,	
//						ToastMessage.DURATION_LONG);
//			}
//		}
		
		if(error) return false;	
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
		
		return true;
	}
	
	public ATVisitaService getVisitaService() {
		if (atvisitaService == null) {
			atvisitaService = ATVisitaService.getInstance(getActivity());
		}
		return atvisitaService;
	}
}

