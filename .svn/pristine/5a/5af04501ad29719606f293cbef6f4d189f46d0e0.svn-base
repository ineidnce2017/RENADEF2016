package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.SpinnerField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil;
import gob.inei.renadef2016.fragments.C3MARCO_Fragment;
import gob.inei.renadef2016.modelo.Marco;
import gob.inei.renadef2016.modelo.Segmentacion;
import gob.inei.renadef2016.modelo.Ubigeo;
import gob.inei.renadef2016.modelo.Usuario;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.renadef2016.service.UbigeoService;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3MARCO_Fragment_01 extends DialogFragmentComponent {
	public interface C1CAP200_Fragment_201_01Listener {
		void onFinishEditDialog(String inputText);
	}

	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtID_N;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtPERIODO;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtCOD_REGION;
	@FieldAnnotation(orderIndex = 4)
	public SpinnerField spnCCPP;
	@FieldAnnotation(orderIndex = 5)
	public SpinnerField spnCCDI;
//	@FieldAnnotation(orderIndex = 3)
//	public IntegerField txtUBIGEO;
	@FieldAnnotation(orderIndex = 6) 
	public TextField txtNOMCOMISARIA;
	@FieldAnnotation(orderIndex = 7)
	public TextField txtCOMISARIO;
//	@FieldAnnotation(orderIndex = 7)
//	public CheckBoxField chbVINFRA;
//	@FieldAnnotation(orderIndex = 8)
//	public CheckBoxField chbVAT; 
//	@FieldAnnotation(orderIndex = 9)
//	public CheckBoxField chbVDELI;
//	@FieldAnnotation(orderIndex = 10)
//	public CheckBoxField chbVADITERR;
	@FieldAnnotation(orderIndex = 8)
	public TextField txtREGION;
//	@FieldAnnotation(orderIndex = 10)
//	public TextField txtCCDD;
//	@FieldAnnotation(orderIndex = 9)
//	public TextField txtNOMBREDD;
//	@FieldAnnotation(orderIndex = 11)
//	public TextField txtCCPP;
//	@FieldAnnotation(orderIndex = 11)
//	public TextField txtNOMBREPP;
//	@FieldAnnotation(orderIndex = 12)
//	public TextField txtCCDI;
//	@FieldAnnotation(orderIndex = 13)
//	public TextField txtNOMBREDI;
	
	@FieldAnnotation(orderIndex = 9)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 10)
	public ButtonComponent btnCancelar;
	@FieldAnnotation(orderIndex = 11)
	public SpinnerField spnCCDD;
	private static C3MARCO_Fragment caller;
	private GridComponent2 grid_AC, grid_AC0;
	private LabelComponent lblTitulo /*, lblSubTitulo*/;
	private Marco detalle;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;
	private UbigeoService ubigeoService;

	public static C3MARCO_Fragment_01 newInstance(FragmentForm pagina,
			Marco detalle, MarcoService marcoService) {
		caller = (C3MARCO_Fragment) pagina;
		mimarcoService = marcoService;
		Filtros.clear();
		C3MARCO_Fragment_01 f = new C3MARCO_Fragment_01();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3MARCO_Fragment_01() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detalle = (Marco) getArguments().getSerializable("detalle");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = createUI();
//		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().setTitle(detalle.id_n==null?"Nueva Comisaria":"Comisaria ID N\u00b0: "+detalle.id_n);
		initObjectsWithoutXML(this, rootView);

		cargarDatos();
		enlazarCajas(this);
		listening();
		
		return rootView;

	}
//////////////////////////////////////////////
	private void cargarDatos() {
		detalle.ccdd = detalle.ccdd==null?App.getInstance().getUsuario().cod_sede_operativa:detalle.ccdd;
		entityToUI(detalle);
		inicio();
	}

	private void inicio() {
		if(C3MARCO_Fragment.PERIODO!=null) txtPERIODO.setText(Util.getText(C3MARCO_Fragment.PERIODO.periodo));
		txtID_N.requestFocus();
		if(!Util.esVacio(detalle.ccdd)){
			MyUtil.llenarProvincia(getActivity(), getUbigeoService(), spnCCPP, App.getInstance().getUsuario().cod_sede_operativa,
					detalle.ccdd);
			spnCCPP.setSelectionKey(detalle.ccpp);
			if(!Util.esVacio(detalle.ccpp)){
				MyUtil.llenarDistrito(getActivity(), getUbigeoService(), spnCCDI, App.getInstance().getUsuario().cod_sede_operativa,
						detalle.ccdd, detalle.ccpp);
				spnCCDI.setSelectionKey(detalle.ccdi);
			}
		}
	}

	@Override
	protected View createUI() {
		buildFields();
		
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q1 = createQuestionSection(grid_AC0.component());
		LinearLayout q2 = createQuestionSection(grid_AC.component());
//		LinearLayout q3 = createQuestionSection(chbVINFRA, chbVAT, chbVDELI, chbVADITERR);

		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q0);
		form.addView(q1);
		form.addView(q2);
//		form.addView(q3);
//		form.addView(q4);
		form.addView(botones);

		return contenedor;
	}

	@Override
	protected void buildFields() {

		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.marco_title).textSize(21).centrar()
				.negrita();
//		lblSubTitulo = new LabelComponent(this.getActivity(),
//				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
//				.text(R.string.marco_accesos).textSize(21).centrar()
//				.negrita();
		
		LabelComponent lblID_N = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.marco_id_n).size(altoComponente, 170).centrar();
		LabelComponent lblREG = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.marco_region).size(altoComponente, 170);
		LabelComponent lblUBI = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.marco_ubigeo).size(altoComponente, 170);
		LabelComponent lblDEP = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.marco_ccdd).size(altoComponente+15, 170);
		LabelComponent lblPRO = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.marco_ccpp).size(altoComponente+15, 170);
		LabelComponent lblDIS = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.marco_ccdi).size(altoComponente+15, 170);
		LabelComponent lblNC = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.marco_comisaria).size(altoComponente+10, 170);
		LabelComponent lblCO = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.marco_comisario).size(altoComponente+10, 170);
		LabelComponent lblPER = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.marco_periodo).size(altoComponente, 170).centrar();
		
		txtID_N = new IntegerField(getActivity(),false).size(altoComponente, 120)/*.alfanumerico()*/.centrar()
				.callbackOnFocus("on_PNCFocus_onChange");
		txtCOD_REGION = new IntegerField(getActivity(),false).size(altoComponente, 50)/*.alfanumerico()*/.centrar()
				.callbackOnFocus("on_PCRFocus_onChange");
		txtREGION = new TextField(getActivity()).size(altoComponente, 400).readOnly();
//		txtUBIGEO = new IntegerField(getActivity(),false).size(altoComponente, 150)/*.alfanumerico()*/.centrar()
//				.callbackOnFocus("on_PUBFocus_onChange");
//		txtCCDD = new TextField(getActivity()).size(altoComponente, 50).alfanumerico().centrar().readOnly();
		spnCCDD = new SpinnerField(getActivity()).size(altoComponente + 15, 
				460).callback("onDepartamentoChangeValue").title(MyUtil.getVacio("CCDD"));
		spnCCPP = new SpinnerField(getActivity()).size(altoComponente + 15, 
				460).callback("onProvinciaChangeValue").title(MyUtil.getVacio("CCPP"));
		spnCCDI = new SpinnerField(getActivity()).size(altoComponente + 15,
				460).callback("onDistritoChangeValue").title(MyUtil.getVacio("CCDI"));
		
		MyUtil.llenarDepartamento(getActivity(), getUbigeoService(), spnCCDD, App.getInstance().getUsuario().cod_sede_operativa);
		
//		txtNOMBREDD = new TextField(getActivity()).size(altoComponente, 400).alfanumerico().readOnly();
//		txtCCPP = new TextField(getActivity()).size(altoComponente, 50).alfanumerico().centrar().readOnly();
//		txtNOMBREPP = new TextField(getActivity()).size(altoComponente, 400).alfanumerico().readOnly();
//		txtCCDI = new TextField(getActivity()).size(altoComponente, 50).alfanumerico().centrar().readOnly();
//		txtNOMBREDI = new TextField(getActivity()).size(altoComponente, 400).alfanumerico().readOnly();
		txtNOMCOMISARIA = new TextField(getActivity()).size(altoComponente+5, 453);
		txtCOMISARIO = new TextField(getActivity()).size(altoComponente+5, 453);
		txtPERIODO = new IntegerField(getActivity(),false).size(altoComponente, 90).centrar();
		
//		chbVINFRA = new CheckBoxField(getActivity(), R.string.marco_inf, "1:0").
//				size(sizeHeigth, sizeWidth);
//		chbVAT = new CheckBoxField(getActivity(), R.string.marco_at, "1:0").
//				size(sizeHeigth, sizeWidth);
//		chbVDELI = new CheckBoxField(getActivity(), R.string.marco_de, "1:0").
//				size(sizeHeigth, sizeWidth);
//		chbVADITERR = new CheckBoxField(getActivity(), R.string.marco_adi_terr, "1:0").
//				size(sizeHeigth, sizeWidth);
		
		grid_AC0 = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_AC0.addComponent(lblID_N);
		grid_AC0.addComponent(txtID_N);
		grid_AC0.addComponent(lblPER);
		grid_AC0.addComponent(txtPERIODO);
		
		
		grid_AC = new GridComponent2(getActivity(), 3, false).colorFondo(R.color.blanco);
//		grid_AC.addComponent(lblID_N);
//		grid_AC.addComponent(txtID_N);
//		grid_AC.addComponent(lblPER);
//		grid_AC.addComponent(txtPERIODO);
		grid_AC.addComponent(lblREG);
		grid_AC.addComponent(txtCOD_REGION);
		grid_AC.addComponent(txtREGION);
//		grid_AC.addComponent(lblUBI);
//		grid_AC.addComponent(txtUBIGEO,2);
		grid_AC.addComponent(lblDEP);
//		grid_AC.addComponent(txtCCDD);
//		grid_AC.addComponent(txtNOMBREDD);
		grid_AC.addComponent(spnCCDD,2);
		grid_AC.addComponent(lblPRO);
//		grid_AC.addComponent(txtCCPP);
//		grid_AC.addComponent(txtNOMBREPP);
		grid_AC.addComponent(spnCCPP,2);
		grid_AC.addComponent(lblDIS);
//		grid_AC.addComponent(txtCCDI);
//		grid_AC.addComponent(txtNOMBREDI);
		grid_AC.addComponent(spnCCDI,2);
		grid_AC.addComponent(lblNC);
		grid_AC.addComponent(txtNOMCOMISARIA,2);
		grid_AC.addComponent(lblCO);
		grid_AC.addComponent(txtCOMISARIO,3);
		

		btnAceptar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnAceptar).size(200, 60);
		btnCancelar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnCancelar).size(200, 60);
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				C3MARCO_Fragment_01.this.dismiss();
			}
		});
		btnAceptar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		//Filtro
		Filtros.setFiltro(txtID_N, Filtros.TIPO.NUMBER, 4, null);
		Filtros.setFiltro(txtCOD_REGION, Filtros.TIPO.NUMBER, 2, null);
//		Filtros.setFiltro(txtUBIGEO, Filtros.TIPO.NUMBER, 6, null);
		Filtros.setFiltro(txtPERIODO, Filtros.TIPO.NUMBER, 2, null);
//		Filtros.setFiltro(txtIH213, Filtros.TIPO.NUMBER, 3, 0,null,1,998);
//		Filtros.setFiltro(txtIH214, Filtros.TIPO.NUMBER, 3, 0,null,1,998);
	}  
	
	public void onDepartamentoChangeValue(FieldComponent component) {
		if(!((SpinnerField)component).isTouched()) return;
		if(((SpinnerField)component).getSelectedItemKey() == null) { caller.clearSpinner(spnCCPP, spnCCDI); return; }
		Ubigeo dpto = (Ubigeo) component.getValue();
		MyUtil.llenarProvincia(getActivity(), getUbigeoService(), spnCCPP, App.getInstance().getUsuario().cod_sede_operativa,
				dpto.ccdd);
		spnCCPP.requestFocus();
	}

	public void onProvinciaChangeValue(FieldComponent component) {
		if(!((SpinnerField)component).isTouched()) return;
		if(((SpinnerField)component).getSelectedItemKey() == null) { caller.clearSpinner(spnCCDI); return; }
		Ubigeo provincia = (Ubigeo) component.getValue();
		MyUtil.llenarDistrito(getActivity(), getUbigeoService(), spnCCDI, App.getInstance().getUsuario().cod_sede_operativa,
				provincia.ccdd, provincia.ccpp);
		spnCCDI.requestFocus();
	}

	public void onDistritoChangeValue(FieldComponent component) {
		if(((SpinnerField)component).getSelectedItemKey() == null) {return;}
		if(!((SpinnerField)component).isTouched()) return;
		Ubigeo distrito = (Ubigeo) component.getValue();
		detalle.ubigeo = distrito.ubigeo;
		txtNOMCOMISARIA.requestFocus();
	}
	
	public void on_PNCFocus_onChange(){
		if(Util.esVacio(txtID_N)) return;
		String cr = txtID_N.getText().toString().trim();
		boolean exists = false;
		if(cr.length()==4 && detalle.id_n==null) exists = mimarcoService.getMarcoAgregado(cr);
		if(exists){
			ToastMessage.msgBox(getActivity(), "La comisar\u00eda con ID N\u00B0: "+cr+" ya existe \u00f3 no es un ID valido.",
                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
			txtID_N.post(new Runnable() {
				@Override
				public void run() {
					txtID_N.requestFocus();
					txtID_N.setText("");
				}
			});
			return;
		}
	}
	
	public void on_PCRFocus_onChange(){
		if(Util.esVacio(txtCOD_REGION)) return;
		String cr = txtCOD_REGION.getText().toString().trim();
		boolean strLen = false;
		if(cr.length() < 2) strLen = true;
		Ubigeo ub = null;
		if(!strLen) ub = mimarcoService.getUbigeo(cr);
		if(ub == null){
			ToastMessage.msgBox(getActivity(), "No se encontro datos para la regi\u00f3n especificada",
                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
//			txtCOD_REGION.setError("No se encontro datos para la regi\u00f3n especificada");
			txtCOD_REGION.post(new Runnable() {
				@Override
				public void run() {
					txtCOD_REGION.requestFocus();
					txtREGION.setText("");
				}
			});
			return;
		}
		txtREGION.setText(ub.departamento);
	}
	
//	public void on_PUBFocus_onChange(){
//		if(Util.esVacio(txtUBIGEO)) return;
//		String cr = txtUBIGEO.getText().toString().trim();
//		boolean strLen = false;
//		if(cr.length() < 6) strLen = true;
//		Ubigeo ub = null;
//		if(!strLen) ub = mimarcoService.getUbigeo(cr);
//		if(ub == null){
//			ToastMessage.msgBox(getActivity(), "No se encontro datos para el ubigeo especificado.",
//                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
//			txtUBIGEO.post(new Runnable() {
//				@Override
//				public void run() {
//					txtUBIGEO.requestFocus();
//					caller.cleanView(txtCCDD,txtNOMBREDD,txtCCPP,txtNOMBREPP,txtCCDI,txtNOMBREDI);
//				}
//			});
//			return;
//		}
//		txtCCDD.setText(ub.ccdd);
//		txtNOMBREDD.setText(ub.departamento);
//		txtCCPP.setText(ub.ccpp);
//		txtNOMBREPP.setText(ub.provincia);
//		txtCCDI.setText(ub.ccdi);
//		txtNOMBREDI.setText(ub.distrito);
//	}
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3MARCO_Fragment_01.this.dismiss();
		
	}

	private boolean validar() {
//		if(Filtros.getErrorFiltro()!=null){
//            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
//                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
//            Filtros.getErrorFiltro().getKey().requestFocus();
//            return false;
//		}
//	
		if(Util.esVacio(txtID_N)){
			mensaje="El ID no puede estar Vacio";
			view=txtID_N;
			error=true;
			return false;
		}	
		if(Util.esVacio(txtCOD_REGION)){
			mensaje="C\u00f3digo de Regi\u00f3n no puede estar Vacio";
			view=txtCOD_REGION;
			error=true;
			return false;
		}
		if(Util.esVacio(txtREGION)){
			mensaje="Nombre de Regi\u00f3n no puede estar Vacio";
			view=txtREGION;
			error=true;
			return false;
		}
//		if(Util.esVacio(txtUBIGEO)){
//			mensaje="Ubigeo no puede estar Vacio";
//			view=txtUBIGEO;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtCCDD)){
//			mensaje="C\u00f3digo de Departamento no puede estar Vacio";
//			view=txtCCDD;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtNOMBREDD)){
//			mensaje="Nombre de Departamento no puede estar Vacio";
//			view=txtNOMBREDD;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtCCPP)){
//			mensaje="C\u00f3digo de Provincia no puede estar Vacio";
//			view=txtCCPP;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtNOMBREPP)){
//			mensaje="Nombre de Provincia no puede estar Vacio";
//			view=txtNOMBREPP;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtCCPP)){
//			mensaje="C\u00f3digo de Provincia no puede estar Vacio";
//			view=txtCCPP;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtNOMBREPP)){
//			mensaje="Nombre de Provincia no puede estar Vacio";
//			view=txtNOMBREPP;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtCCDI)){
//			mensaje="C\u00f3digo de Distrito no puede estar Vacio";
//			view=txtCCDI;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtNOMBREDI)){
//			mensaje="Nombre de Distrito no puede estar Vacio";
//			view=txtNOMBREDI;
//			error=true;
//			return false;
//		}
		if(spnCCDD.getSelectedItemPosition() == 0){
			mensaje="Departamento no debe estar vacio";
			view=spnCCDD;
			error=true;
			return false;
		} 
		if(spnCCPP.getSelectedItemPosition() == 0){
			mensaje="Provincia no debe estar vacio";
			view=spnCCPP;
			error=true;
			return false;
		} 
		if(spnCCDI.getSelectedItemPosition() == 0){
			mensaje="Distrito no debe estar vacio";
			view=spnCCDI;
			error=true;
			return false;
		}
		if(Util.esVacio(txtPERIODO)){
			mensaje="Periodo no puede estar Vacio";
			view=txtPERIODO;
			error=true;
			return false;
		}
//		if(!chbVINFRA.isChecked() && !chbVAT.isChecked() && !chbVDELI.isChecked() && !chbVADITERR.isChecked()){
//			mensaje="Debe tener al menos una alternativa marcada.";
//			view=chbVINFRA;
//			error=true;
//			return false;
//		}
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
		
//		String oper = detalle.id_n==null?"add":"edit";
//		detalle.id_n = App.getInstance().getComisaria().id_n;
		uiToEntity(detalle);
		detalle.cod_dep_asig = App.getInstance().getUsuario().cod_sede_operativa;
		detalle.area = 1;
		detalle.vdeli = 1;
		detalle.agregado=1;
//		if(oper.equals("add")) detalle.agregado=1;
		
		try {
			if(!mimarcoService.saveOrUpdate(detalle, detalle.getSecCap(caller.getListFields(this,
					new String[]{"UBIGEO","AGREGADO","AREA","VDELI","COD_DEP_ASIG"})))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			} else {
				Segmentacion seg = getObjSeg();
				mimarcoService.saveOrUpdate(seg, seg.getSecCap(caller.getListFields(seg), false));
				caller.reloadData(seg.id_n, seg.periodo, 1);
			}
		} catch (Exception e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			return false;
		}
		
//		if(!mimarcoService.grabarMarco(detalle)){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
//		else {
//			caller.reloadData(detalle, 1);
//		}
		
		return true;
	}
	
	private Segmentacion getObjSeg(){
		Segmentacion seg = new Segmentacion();
		Usuario us = App.getInstance().getUsuario();
		seg.supervisa = us.cargo_id != App.REGISTRADOR ? us.id.toString() : null;
		seg.equipo = us.equipo;
		seg.ruta = us.cargo_id != App.REGISTRADOR ? "00" : us.ruta;
		seg.cod_sede_operativa = us.cod_sede_operativa;
		seg.ccdd = detalle.ccdd;
		seg.ccpp = detalle.ccpp;
		seg.ccdi = detalle.ccdi;
		seg.id_n = detalle.id_n;
		seg.periodo = Util.getInt(txtPERIODO);
		seg.estado = 1;
		return seg;
	}
	
	public UbigeoService getUbigeoService() {
		if (ubigeoService == null) {
			ubigeoService = UbigeoService.getInstance(getActivity());
		}
		return ubigeoService;
	}
	
}


