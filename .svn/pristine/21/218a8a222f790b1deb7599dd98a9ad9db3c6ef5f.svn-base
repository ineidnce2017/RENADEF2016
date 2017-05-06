package gob.inei.renadef2016.fragments.dialog;

import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil;
import gob.inei.renadef2016.fragments.C3CAP200_Fragment_201;
import gob.inei.renadef2016.modelo.DelVisita;
import gob.inei.renadef2016.modelo.Ubigeo;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.service.ATVisitaService;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.renadef2016.service.UbigeoService;
import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DateTimeField;
import gob.inei.dnce.components.DateTimeField.SHOW_HIDE;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.SpinnerField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3CAP200_Fragment_201_01_1 extends DialogFragmentComponent {
	public interface C1CAP200_Fragment_201_01Listener {
		void onFinishEditDialog(String inputText);
	}

	@FieldAnnotation(orderIndex = 1)
	public DateTimeField txtIH203_DIA;
	@FieldAnnotation(orderIndex = 2)
	public DateTimeField txtIH203_MES;
	@FieldAnnotation(orderIndex = 3)
	public DateTimeField txtIH203_ANIO; 
	@FieldAnnotation(orderIndex = 4)
	public DateTimeField txtIH204;
	public IntegerField txtIH204_HOR;
	public IntegerField txtIH204_MIN; 
	@FieldAnnotation(orderIndex = 5)
	public SpinnerField spnIH205_DD;
	@FieldAnnotation(orderIndex = 6)
	public SpinnerField spnIH205_PP;
	@FieldAnnotation(orderIndex = 7)
	public SpinnerField spnIH205_DIS;
	@FieldAnnotation(orderIndex = 8)
	public RadioGroupOtherField rgIH206;
	@FieldAnnotation(orderIndex = 9)
	public TextField txtIH206_O;
	@FieldAnnotation(orderIndex = 10)
	public IntegerField txtIH207_A;
	@FieldAnnotation(orderIndex = 11)
	public TextField txtIH207_B;
	@FieldAnnotation(orderIndex = 12)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 13)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private static C3CAP200_Fragment_201 caller;
	private Cap200Delitos detalle;
	private GridComponent grid_P203, grid_P205, grid_P207;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;
	private static ATVisitaService atvisitaService;
	private UbigeoService ubigeoService;
	private static INF_Caratula01Service caratulaService;
	private ImageComponent imgNT;

	public static C3CAP200_Fragment_201_01_1 newInstance(FragmentForm pagina,
			Cap200Delitos detalle, MarcoService marcoService, INF_Caratula01Service caratService) {
		caller = (C3CAP200_Fragment_201) pagina;
		mimarcoService = marcoService;
		caratulaService = caratService;
		Filtros.clear();
		C3CAP200_Fragment_201_01_1 f = new C3CAP200_Fragment_201_01_1();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP200_Fragment_201_01_1() {
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
//		detalle.ih205_dd = detalle.ih205_dd==null?App.getInstance().getUsuario().cod_sede_operativa:detalle.ih205_dd;
		entityToUI(detalle);
		inicio();
	}

	private void inicio() {
		on_ChangeP206(txtIH204);
		txtIH203_ANIO.setValue(Util.getFecha(2016, 1, 1));
		if(!Util.esVacio(detalle.ih205_dd)){
			MyUtil.llenarProvincia(getActivity(), getUbigeoService(), spnIH205_PP, App.getInstance().getUsuario().cod_sede_operativa,
					detalle.ih205_dd);
			spnIH205_PP.setSelectionKey(detalle.ih205_pp);
			if(!Util.esVacio(detalle.ih205_pp)){
				MyUtil.llenarDistrito(getActivity(), getUbigeoService(), spnIH205_DIS, App.getInstance().getUsuario().cod_sede_operativa,
						detalle.ih205_dd, detalle.ih205_pp);
				spnIH205_DIS.setSelectionKey(detalle.ih205_dis);
			}
		}
		txtIH203_DIA.requestFocus();
	}

	@Override
	protected View createUI() {
		buildFields();
		
//		q0 = createQuestionSection(R.string.lb_c3_cap200_p205, txtIH205);
		LinearLayout q0 = createQuestionSection(R.string.lb_c3_cap200_p203, grid_P203.component());
		LinearLayout q1 = createQuestionSection(R.string.lb_c3_cap200_p204, txtIH204);
//		q2 = createQuestionSection(R.string.lb_c3_cap200_p207, rgIH207);
		LinearLayout q2 = createQuestionSection(R.string.lb_c3_cap200_p205, grid_P205.component());
		LinearLayout q3 = createQuestionSection(R.string.lb_c3_cap200_p206, rgIH206);
		LinearLayout ly = createLy(LinearLayout.VERTICAL, Gravity.TOP, imgNT);
		ly.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, Util.getTamañoEscalado(getActivity(), 120)));
		LinearLayout ly1 = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER, grid_P207.component(), ly);
		LinearLayout q4 = createQuestionSection(R.string.lb_c3_cap200_p207, ly1);
		
		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q0);
		form.addView(q1);
		form.addView(q2);
		form.addView(q3);
		form.addView(q4);

		form.addView(botones);

		return contenedor;
	}

	@Override
	protected void buildFields() {
	
//		txtIH203_DIA = new IntegerField(getActivity());
//		txtIH203_MES = new IntegerField(getActivity());
//		txtIH203_ANIO = new IntegerField(getActivity());
		
		txtIH204_HOR = new IntegerField(getActivity());
		txtIH204_MIN = new IntegerField(getActivity());
		
//		txtIH205 = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 200).
//				dateOrhour(txtIH203_DIA, txtIH203_MES, txtIH203_ANIO).showObject(SHOW_HIDE.CALENDAR).
//				setRangoMonth(7, 12).setRangoYear(2014, 2014);
		txtIH203_DIA = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 120).
				showObject(SHOW_HIDE.MONTH_YEAR).buttons("Aceptar", "Omision").callback("on_P205Change");
		txtIH203_DIA.setDialogParent(this);
		txtIH203_MES = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 120).
				showObject(SHOW_HIDE.DAY_YEAR).setRangoMonth(5, 12).buttons("Aceptar", "Omision").callback("on_P205Change");
		txtIH203_MES.setDialogParent(this);
		txtIH203_ANIO = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 120).
				showObject(SHOW_HIDE.DAY_MONTH).setRangoYear(2016, 2016).readOnly();
		txtIH204 = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.HORA).size(altoComponente, 200).
				dateOrhour(txtIH204_HOR, txtIH204_MIN, null).buttons("Aceptar", "Omision").callback("on_ChangeP206");
		txtIH204.setDialogParent(this);
		 
		txtIH206_O = new TextField(getActivity(), false).size(altoComponente, 400);
		
		rgIH206 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap200_p206_1,
				R.string.lb_c3_cap200_p206_2, R.string.lb_c3_cap200_p206_3, R.string.lb_c3_cap200_p206_4,
				R.string.lb_c3_cap200_p206_5, R.string.lb_c3_cap200_p206_6, R.string.lb_c3_cap200_p206_7
				).size(sizeHeigth, sizeWidth).orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
		rgIH206.agregarEspecifique(6, txtIH206_O); 
		
//		txtIH204.setFocusOnDissmis(rgIH207);
		
		LabelComponent lblDay = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p305_d).size(altoComponente, 120).centrar();
		LabelComponent lblMonth = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p305_m).size(altoComponente, 120).centrar();
		LabelComponent lblYear = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p305_a).size(altoComponente, 120).centrar();
		
		grid_P203 = new GridComponent(getActivity(), 3).colorFondo(R.color.blanco);
		grid_P203.addComponent(lblDay);
		grid_P203.addComponent(lblMonth);
		grid_P203.addComponent(lblYear);
		grid_P203.addComponent(txtIH203_DIA);
		grid_P203.addComponent(txtIH203_MES);
		grid_P203.addComponent(txtIH203_ANIO);
		
		LabelComponent lblDep = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap200_p205_dep).size(altoComponente, 160);
		LabelComponent lblPro = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap200_p205_pro).size(altoComponente, 120);
		LabelComponent lblDis = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap200_p205_dis).size(altoComponente, 120);
		
		spnIH205_DD = new SpinnerField(getActivity()).size(altoComponente + 15, 
				260).callback("onDepartamentoChangeValue").title(MyUtil.getVacio("CCDD"));
		spnIH205_PP = new SpinnerField(getActivity()).size(altoComponente + 15, 
				250).callback("onProvinciaChangeValue").title(MyUtil.getVacio("CCPP"));
		spnIH205_DIS = new SpinnerField(getActivity()).size(altoComponente + 15,
				250).callback("onDistritoChangeValue").title(MyUtil.getVacio("CCDI"));
		
		String codSede = (Util.getInt(App.getInstance().getComisaria().ii7) == 1)?"99":App.getInstance().getUsuario().cod_sede_operativa;
		MyUtil.llenarDepartamento(getActivity(), getUbigeoService(), spnIH205_DD, /*codSede*/"99");
		
		grid_P205 = new GridComponent(getActivity(), 2).colorFondo(FragmentForm.COLOR_LINEA_SECCION_PREGUNTA);
		grid_P205.addComponent(lblDep);
		grid_P205.addComponent(spnIH205_DD);
		grid_P205.addComponent(lblPro);
		grid_P205.addComponent(spnIH205_PP);
		grid_P205.addComponent(lblDis);
		grid_P205.addComponent(spnIH205_DIS);
		
		LabelComponent lblP207N = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap200_p207_a).size(altoComponente, 170);
		LabelComponent lblP207V = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap200_p207_b).size(altoComponente, 170);
		txtIH207_A = new IntegerField(getActivity(),false).size(altoComponente, 300).alinearIzquierda();
		txtIH207_B = new TextField(getActivity(),false).size(altoComponente, 300);
		
		imgNT = new ImageComponent(getActivity(), R.drawable.document_edit, R.drawable.document_edit).
				size(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		imgNT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txtIH207_A.setText("SN");
				txtIH207_B.requestFocus();
			}
		});
		
		grid_P207 = new GridComponent(getActivity(), 2).colorFondo(R.color.blanco);
		grid_P207.addComponent(lblP207N);
		grid_P207.addComponent(txtIH207_A);
		grid_P207.addComponent(lblP207V);
		grid_P207.addComponent(txtIH207_B);
		
//		MyUtil.llenarDepartamento(getActivity(), getUbigeoService(),
//				spnIH205_DD, App.getInstance().getUsuario().cod_sede_operativa);

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
				C3CAP200_Fragment_201_01_1.this.dismiss();
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
				FragmentManager fm = C3CAP200_Fragment_201_01_1.this.getFragmentManager();
				C3CAP200_Fragment_201_01 aperturaDialog = C3CAP200_Fragment_201_01
						.newInstance(caller, detalle, detalle.orden_200, mimarcoService, caratulaService);
				aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
				aperturaDialog.show(fm, "aperturaDialog");
				C3CAP200_Fragment_201_01_1.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		Filtros.setFiltro(txtIH206_O, Filtros.TIPO.ALFAN_U, 50, null);
		Filtros.setFiltro(txtIH207_A, Filtros.TIPO.NUMBER, 5, new char[]{'S','N'});
		Filtros.setFiltro(txtIH207_B, Filtros.TIPO.ALFAN_U, 50, null);

	}
	
	public void onDepartamentoChangeValue(FieldComponent component) {
		if(!((SpinnerField)component).isTouched()) return;
		Log.e("entrasss", "observastes: ");
		if(((SpinnerField)component).getSelectedItemKey() == null) { caller.clearSpinner(spnIH205_PP, spnIH205_DIS); return; }
		Ubigeo dpto = (Ubigeo) component.getValue();
		MyUtil.llenarProvincia(getActivity(), getUbigeoService(), spnIH205_PP, App.getInstance().getUsuario().cod_sede_operativa,
				dpto.ccdd);
		spnIH205_PP.requestFocus();
	}

	public void onProvinciaChangeValue(FieldComponent component) {
		if(!((SpinnerField)component).isTouched()) return;
		if(((SpinnerField)component).getSelectedItemKey() == null) { caller.clearSpinner(spnIH205_DIS); return; }
		Ubigeo provincia = (Ubigeo) component.getValue();
		MyUtil.llenarDistrito(getActivity(), getUbigeoService(), spnIH205_DIS, App.getInstance().getUsuario().cod_sede_operativa,
				provincia.ccdd, provincia.ccpp);
		spnIH205_DIS.requestFocus();
	}

	public void onDistritoChangeValue(FieldComponent component) {
		if(((SpinnerField)component).getSelectedItemKey() == null) {return;}
		if(!((SpinnerField)component).isTouched()) return;
		rgIH206.requestFocus();
//		spnIH205_DIS.setTouched(false);
//		Ubigeo departamento = (Ubigeo) spnIH205_DD.getSelectedItem();
//		Ubigeo provincia = (Ubigeo) spnIH205_PP.getSelectedItem();
//		Ubigeo distrito = (Ubigeo) spnIH205_DIS.getSelectedItem();	
	}
	
	public void on_P205Change(FieldComponent component){
		if(component.getValue() == null){
			if(((DateTimeField)component).equals(txtIH203_DIA)) {
				txtIH203_DIA.setValue(Util.getFecha(9999, 99, 99));
				txtIH203_MES.requestFocus();
			} else if(((DateTimeField)component).equals(txtIH203_MES)) {
				txtIH203_MES.setValue(Util.getFecha(9999, 99, 99));
				txtIH204.requestFocus();
			}
		} else {
			if(((DateTimeField)component).equals(txtIH203_DIA)) txtIH203_MES.requestFocus();
			else if(((DateTimeField)component).equals(txtIH203_MES)) txtIH204.requestFocus();
		}
	}
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3CAP200_Fragment_201_01_1.this.dismiss();
		
		FragmentManager fm = caller.getFragmentManager();
		C3CAP200_Fragment_201_02 aperturaDialog = C3CAP200_Fragment_201_02.
				newInstance(caller, detalle, mimarcoService, caratulaService);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}
	
	public void on_ChangeP206(FieldComponent component){
		if(((DateTimeField)component).isFocused()){
			if(component.getValue() == null){
				txtIH204.setValue(Util.getHora(99, 99, 99));
//				if(rgIH207.isEnabled()) rgIH207.requestFocus();
//				else btnAceptar.requestFocus();
			}
//			spnIH205_PP.requestFocus();
			spnIH205_DD.requestFocus();
		}
//		if(detalle!=null && detalle.ih208!=null &&
//				!Util.esDiferente(detalle.ih208, 17,18,19,20,21,22,23)){
//			caller.cleanAndLockView(rgIH207);
//		}
	}

	private boolean validar() {
		error=false;
		
		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
		
		if(Util.esVacio(txtIH203_DIA)){
			mensaje = "La pregunta 203 - D\u00eda no puede estar Vacia";
			view = txtIH203_DIA;
			error = true;
			return false;
		} else if(Util.esVacio(txtIH203_MES)){
			mensaje = "La pregunta 203 - Mes no puede estar Vacia";
			view = txtIH203_MES;
			error = true;
			return false;
		} else if(Util.esVacio(txtIH203_ANIO)){
			mensaje = "La pregunta 203 - A\u00f1o no puede estar Vacia";
			view = txtIH203_ANIO;
			error = true;
			return false;
		}
		if(Util.esVacio(txtIH204_HOR)){
			mensaje="Hora no debe estar vacio";
			view=txtIH204_HOR;
			error=true;
			return false;
		}
		
		int dia = Integer.parseInt(txtIH203_DIA.getText().toString());
		int mes = Integer.parseInt(txtIH203_MES.getText().toString());
		int anio = Integer.parseInt(txtIH203_ANIO.getText().toString());
		if(dia!=99 && mes!=99 && anio != 9999){
			SparseArray<String> spa;
			if((spa = Util.checkDatesValid(dia, mes, anio)).valueAt(0)!=null){
				mensaje = "Fecha Calendario incorrecto; " + spa.valueAt(0);
				view = spa.keyAt(0) == 1 ? txtIH203_DIA : txtIH203_MES;
				error = true;
			} else {
				DelVisita _vis = getVisitaService().getDelVisitaSec(App.getInstance().getComisaria().id_n, 1, 
						new DelVisita().getSecCap(Util.getListList("III_2_D","III_2_M","III_2_A")));
				if(_vis!=null){
					String fecha1 = String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+txtIH203_ANIO.getText().toString();
					String fecha2 = _vis.iii_2_d+"/"+_vis.iii_2_m+"/"+_vis.iii_2_a;
					if(Util.compareDate(fecha1, fecha2, "ddMMyyyy")>=0){
						mensaje="Fecha del Hecho no puede ser mayor o igual a la fecha de visita.";
		//				view=txtIH205;
						view=txtIH203_DIA;
						error=true;
						return false;	
					}
				}
			}
		}
//		if(spnIH205_DD.getSelectedItemPosition() == 0){
//			mensaje="Departamento no debe estar vacio";
//			view=spnIH205_DD;
//			error=true;
//			return false;
//		} else
			if(spnIH205_PP.getSelectedItemPosition() == 0){
			mensaje="Provincia no debe estar vacio";
			view=spnIH205_PP;
			error=true;
			return false;
		} else if(spnIH205_DIS.getSelectedItemPosition() == 0){
			mensaje="Distrito no debe estar vacio";
			view=spnIH205_DIS;
			error=true;
			return false;
		}
		
		if(Util.esVacio(rgIH206)){
			mensaje = "La pregunta 206 no puede estar Vacia";
			view = rgIH206;
			error = true;
			return false;
		} else if(rgIH206.isTagSelected(7) && Util.esVacio(txtIH206_O)){
			mensaje = "Especifique no puede estar Vacia";
			view = txtIH206_O;
			error = true;
			return false;
		}
		
		if(Util.esVacio(txtIH207_A)){
			mensaje = "La pregunta 207 n\u00B0 de Cuadra no puede estar Vacia";
			view = txtIH207_A;
			error = true;
			return false;
		} 
		if(Util.esVacio(txtIH207_B)){
			mensaje = "La pregunta 207 nombre de V\u00eda no puede estar Vacia";
			view = txtIH207_B;
			error = true;
			return false;
		} 

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
	
	public UbigeoService getUbigeoService() {
		if (ubigeoService == null) {
			ubigeoService = UbigeoService.getInstance(getActivity());
		}
		return ubigeoService;
	}
}
