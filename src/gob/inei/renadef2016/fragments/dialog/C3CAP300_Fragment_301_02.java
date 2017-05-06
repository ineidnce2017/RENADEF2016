package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DateTimeField;
import gob.inei.dnce.components.DateTimeField.SHOW_HIDE;
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
import gob.inei.dnce.util.Edad;
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
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3CAP300_Fragment_301_02 extends DialogFragmentComponent {
	public interface C1CAP300_Fragment_301_01Listener {
		void onFinishEditDialog(String inputText);
	}
	
	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtIVH305;
	@FieldAnnotation(orderIndex = 2)
	public DateTimeField txtIVH306_DIA;
	@FieldAnnotation(orderIndex = 3)
	public DateTimeField txtIVH306_MES;
	@FieldAnnotation(orderIndex = 4)
	public DateTimeField txtIVH306_ANIO;
	@FieldAnnotation(orderIndex = 5)
	public RadioGroupOtherField rgIVH307;
	@FieldAnnotation(orderIndex = 6)
	public RadioGroupOtherField rgIVH308;
	@FieldAnnotation(orderIndex = 7)
	public TextField txtIVH308_O; 
	@FieldAnnotation(orderIndex = 8)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 9)
	public ButtonComponent btnCancelar; 
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private static C3CAP300_Fragment_301 caller;
	private GridComponent2 grid_P306;
	LinearLayout q0, q1, q2, q3;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private Cap300Delitos detalle;
	private static MarcoService mimarcoService;

	public static C3CAP300_Fragment_301_02 newInstance(FragmentForm pagina,
			Cap300Delitos detalle, MarcoService marcoService) {
		caller = (C3CAP300_Fragment_301) pagina;
		mimarcoService = marcoService;
		Filtros.clear();
		C3CAP300_Fragment_301_02 f = new C3CAP300_Fragment_301_02();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP300_Fragment_301_02() {
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
		if(!Util.esDiferente(detalle.ivh304, 4)){
			txtIVH305.setText("99");
			txtIVH305.readOnly();
			txtIVH306_DIA.requestFocus();
		} else {
			if(!Util.esDiferente(detalle.ivh304, 1)){
				caller.cleanAndLockView(rgIVH307, rgIVH308);
			}
			else if(!Util.esDiferente(detalle.ivh304, 2)){
				caller.cleanAndLockView(rgIVH308);
			}
			txtIVH305.requestFocus();
		}
	}

	@Override
	protected View createUI() {
		buildFields();
		
		q0 = createQuestionSection(R.string.lb_c3_cap300_p305, txtIVH305);
		q1 = createQuestionSection(R.string.lb_c3_cap300_p306, grid_P306.component());
		q2 = createQuestionSection(R.string.lb_c3_cap300_p307, rgIVH307);
		q3 = createQuestionSection(R.string.lb_c3_cap300_p308, rgIVH308);

		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q0);
		form.addView(q1);
		form.addView(q2);
		form.addView(q3);
		form.addView(botones);

		return contenedor;
	}

	@Override
	protected void buildFields() {

		txtIVH305 = new IntegerField(getActivity(),false).size(altoComponente, 120);//.callback("onKeyChange_P304");
		
		txtIVH306_DIA = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 120).
				showObject(SHOW_HIDE.MONTH_YEAR).buttons("Aceptar", "Omision").callback("on_P305Change");
		txtIVH306_DIA.setDialogParent(this);
		txtIVH306_MES = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 120).
				showObject(SHOW_HIDE.DAY_YEAR).buttons("Aceptar", "Omision").callback("on_P305Change");
		txtIVH306_MES.setDialogParent(this);
		txtIVH306_ANIO = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 120).
				showObject(SHOW_HIDE.DAY_MONTH).setRangoYear(1900, 2017).buttons("Aceptar", "Omision").callback("on_P305Change");
		txtIVH306_ANIO.setDialogParent(this);
		txtIVH308_O = new TextField(getActivity()).size(altoComponente, 400);
		
		rgIVH307 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap300_p306_1,
				R.string.lb_c3_cap300_p306_2, R.string.lb_c3_cap300_p306_3, R.string.lb_c3_cap300_p306_4,
				R.string.lb_c3_cap300_p306_5, R.string.lb_c3_cap300_p306_6, R.string.lb_c3_cap300_p306_7,
				R.string.lb_c3_cap300_p306_8).size(sizeHeigth, sizeWidth).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);//.callback("onCheckedChange_P306");
		rgIVH308 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap300_p307_1,
				R.string.lb_c3_cap300_p307_2, R.string.lb_c3_cap300_p307_3, R.string.lb_c3_cap300_p307_4,
				R.string.lb_c3_cap300_p307_5, R.string.lb_c3_cap300_p307_6, R.string.lb_c3_cap300_p307_7,
				R.string.lb_c3_cap300_p307_8, R.string.lb_c3_cap300_p307_9, R.string.lb_c3_cap300_p307_10,
				R.string.lb_c3_cap300_p307_11, R.string.lb_c3_cap300_p307_12, R.string.lb_c3_cap300_p307_13,
				R.string.lb_c3_cap300_p307_14, R.string.lb_c3_cap300_p307_15, R.string.lb_c3_cap300_p307_16,
				R.string.lb_c3_cap300_p307_17, R.string.lb_c3_cap300_p307_18).size(sizeHeigth, sizeWidth).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
		rgIVH308.agregarEspecifique(17, txtIVH308_O);
		
		txtIVH306_DIA.setFocusOnDissmis(txtIVH306_MES);
		txtIVH306_MES.setFocusOnDissmis(txtIVH306_ANIO);
		txtIVH306_ANIO.setFocusOnDissmis(rgIVH307);
		
		LabelComponent lblDay = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p305_d).size(altoComponente, 120).centrar();
		LabelComponent lblMonth = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p305_m).size(altoComponente, 120).centrar();
		LabelComponent lblYear = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p305_a).size(altoComponente, 120).centrar();
		
		grid_P306 = new GridComponent2(getActivity(), 3).colorFondo(R.color.blanco);
		grid_P306.addComponent(lblDay);
		grid_P306.addComponent(lblMonth);
		grid_P306.addComponent(lblYear);
		grid_P306.addComponent(txtIVH306_DIA);
		grid_P306.addComponent(txtIVH306_MES);
		grid_P306.addComponent(txtIVH306_ANIO);

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
				C3CAP300_Fragment_301_02.this.dismiss();
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
				FragmentManager fm = C3CAP300_Fragment_301_02.this.getFragmentManager();
				C3CAP300_Fragment_301_01 aperturaDialog = C3CAP300_Fragment_301_01
						.newInstance(caller, detalle, detalle.orden_300, mimarcoService);
				aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
				aperturaDialog.show(fm, "aperturaDialog");
				C3CAP300_Fragment_301_02.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		Filtros.setFiltro(txtIVH305, Filtros.TIPO.NUMBER, 2, 0,null,0,99);
		Filtros.setFiltro(txtIVH308_O, Filtros.TIPO.ALFAN_U, 50, null);
	}
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		
		FragmentManager fm = caller.getFragmentManager();
		if(!Util.esDiferente(detalle.ivh304,1,2) && App.getInstance().getCap200().ih215 == null){
			C3CAP300_Fragment_301_Obs aperturaDialog = C3CAP300_Fragment_301_Obs.
					newInstance(caller, detalle, mimarcoService);
			aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
			aperturaDialog.show(fm, "aperturaDialog");
		} else {
			C3CAP300_Fragment_301_03 aperturaDialog = C3CAP300_Fragment_301_03.
					newInstance(caller, detalle, mimarcoService);
			aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
			aperturaDialog.show(fm, "aperturaDialog");
		}
		C3CAP300_Fragment_301_02.this.dismiss();
	}
	
	public void on_P305Change(FieldComponent component){
		if(component.getValue() == null){
			if(((DateTimeField)component).equals(txtIVH306_DIA)) {
				txtIVH306_DIA.setValue(Util.getFecha(9999, 99, 99));
				txtIVH306_MES.requestFocus();
			} else if(((DateTimeField)component).equals(txtIVH306_MES)) {
				txtIVH306_MES.setValue(Util.getFecha(9999, 99, 99));
				txtIVH306_ANIO.requestFocus();
			} else if(((DateTimeField)component).equals(txtIVH306_ANIO)) {
				txtIVH306_ANIO.setValue(Util.getFecha(9999, 99, 99));
				if(rgIVH307.isEnabled()) rgIVH307.requestFocus();
				else if(rgIVH308.isEnabled()) rgIVH308.requestFocus();
				else btnAceptar.requestFocus();
			}
		} else {
			if(((DateTimeField)component).equals(txtIVH306_DIA)) txtIVH306_MES.requestFocus();
			else if(((DateTimeField)component).equals(txtIVH306_MES)) txtIVH306_ANIO.requestFocus();
			else if(((DateTimeField)component).equals(txtIVH306_ANIO)) {
				if(rgIVH307.isEnabled()) rgIVH307.requestFocus();
				else if(rgIVH308.isEnabled()) rgIVH308.requestFocus();
				else btnAceptar.requestFocus();
			}
		}
	}

	private boolean validar() {
		error = false;		
		
//		if(Util.esDiferente(detalle.ivh304a, 4)){
			if(Util.esVacio(txtIVH305)){
				mensaje="Edad no puede estar vacio";
				view = txtIVH305;
				error=true;
				return false;
			} 
//			else if(Integer.valueOf(5).equals(C3CAP300_Fragment_301.cap300cap200.ih208) && 
//					Integer.parseInt(txtIVH305.getText().toString())>0){
//				mensaje ="Si es INFANTICIDIO corrija la edad de la v\u00edctima.";		
//				view = txtIVH305;
//				error=true;
//				return false;	
//			}

			int edad = Integer.parseInt(txtIVH305.getText().toString());
			if(edad<18 && !Util.esDiferente(detalle.ivh302, 2,4,5)){
				mensaje="La edad no se relaciona con tipo de documento";
				view = txtIVH305;
				error=true;
				return false;
			}
			else if(edad>=3 && edad!=99	&& !Util.esDiferente(detalle.ivh304, 1)){
				mensaje="Las características de la edad no se relacionan con la edad";
				view = txtIVH305;
				error=true;
				return false;
			}
			else if((edad<3 || edad>=12) && edad!=99 && !Util.esDiferente(detalle.ivh304, 2)){
				mensaje="Las características de la edad no se relacionan con la edad";
				view = txtIVH305;
				error=true;
				return false;
			}
			else if(edad<12 && edad!=99 && !Util.esDiferente(detalle.ivh304, 3)){
				mensaje="Las características de la edad no se relacionan con la edad";
				view = txtIVH305;
				error=true;
				return false;
			}
			
			if(!error){
				if(Util.esVacio(txtIVH306_DIA)){
					mensaje = "La pregunta 306 - D\u00eda no puede estar Vacia";
					view = txtIVH306_DIA;
					error = true;
					return false;
				} else if(Util.esVacio(txtIVH306_MES)){
					mensaje = "La pregunta 306 - Mes no puede estar Vacia";
					view = txtIVH306_MES;
					error = true;
					return false;
				} else if(Util.esVacio(txtIVH306_ANIO)){
					mensaje = "La pregunta 306 - A\u00f1o no puede estar Vacia";
					view = txtIVH306_ANIO;
					error = true;
					return false;
				}
				
				int anio = Integer.valueOf(txtIVH306_ANIO.getText().toString());
				int mes = Integer.valueOf(txtIVH306_MES.getText().toString());
				int dia = Integer.valueOf(txtIVH306_DIA.getText().toString());
				if(dia!=99 && mes!=99){
					SparseArray<String> spa;
					if((spa = Util.checkDatesValid(dia, mes, anio)).valueAt(0)!=null){
						mensaje = "Fecha Calendario incorrecto; " + spa.valueAt(0);
						view = spa.keyAt(0) == 1 ? txtIVH306_DIA : txtIVH306_MES;
						error = true;
						return false;
					}
				}
				
				if(edad != 99 && anio!=9999){
					int anioA = Integer.valueOf(C3CAP300_Fragment_301.cap300cap200.ih203_anio);
					int mesA = Integer.valueOf(C3CAP300_Fragment_301.cap300cap200.ih203_mes);
					int diaA = Integer.valueOf(C3CAP300_Fragment_301.cap300cap200.ih203_dia);
					String fecha1 = Util.getFechaFormateada(anio, mes==99?1:mes, mes==99?1:(dia==99?1:dia), "ddMMyyyy");
					String fecha2 = Util.getFechaFormateada(anioA, mesA, (diaA==99?1:diaA),"ddMMyyyy");
					if(((diaA!=99 && mesA!=99) || mesA!=99) && ((dia!=99 && mes!=99 && anio!=9999) || 
							(mes!=99 && anio!=9999))){
						Log.e("fecha1", "fecha1: "+fecha1);
						Log.e("fecha2", "fecha2: "+fecha2);
						if(Util.compareDate(fecha1, fecha2, "ddMMyyyy")>0){
							mensaje="Fecha de Nacimiento no puede ser mayor a la Fecha del Hecho Preg. 203.";
							view=txtIVH306_DIA;
							error=true;	
							return false;
						} 
					}
					
					Edad edadCalculada = Util.calcularEdad(anioA, mesA, diaA, anio, mes, dia);
					int edadCalc = edadCalculada.getAnios();
					Log.e("edad Calculada", "edad anios: "+edadCalculada.getAnios());
//					if(edadCalc!=99 && /*edadCalc!=0 &&*/ edadCalc!=edad){
					if(edadCalc!=99 && !(edadCalc>=edad-1 && edadCalc<=edad+1)){
						mensaje = "Edad Calculada a partir de Fecha de Nacimiento no coincide "
								+ "con la Edad registrada en Preg. 305.";
						view = txtIVH305;
						error = true;
						return false;
					}
				}
				
				if(edad>=3){
					String msg = "La edad no corresponde al nivel educativo alcanzado.";
					if(rgIVH307.isEnabled()){
						if(Util.esVacio(rgIVH307)){
							mensaje = "La pregunta 307 no puede estar Vacia";
							view = rgIVH307;
							error = true;
							return false;
						} 
						else if((rgIVH307.isTagSelected(3) && edad >= 0 && edad <= 4) ||
								(rgIVH307.isTagSelected(4) && edad >= 0 && edad <= 10) ||
								(rgIVH307.isTagSelectedBetween(5, 6) && edad >= 0 && edad <= 15) ||
								(rgIVH307.isTagSelected(7) && edad >= 0 && edad <= 21)){
							mensaje = msg;
							view = rgIVH307;
							error = true;
							return false;
						}
						else if((rgIVH307.isTagSelectedBetween(5, 7) && !Util.esDiferente(detalle.ivh304, 2))){
							mensaje = "Las características de la edad no corresponde al nivel educativo alcanzado.";
							view = rgIVH307;
							error = true;
							return false;
						}
					}
					
					if(rgIVH308.isEnabled()){
						if(Util.esVacio(rgIVH308)){
							mensaje = "La pregunta 308 no puede estar Vacia";
							view = rgIVH308;
							error = true;
							return false;
						} 
						else if(edad!=99 && ((rgIVH308.isTagSelected(1) && edad >= 0 && edad <= 11) ||
								(rgIVH308.isTagSelected(2) && edad >= 0 && edad <= 12) ||
								(rgIVH308.isTagSelected(3) && edad >= 0 && edad <= 15) ||
								(rgIVH308.isTagSelected(4) && edad >= 0 && edad <= 17) ||
								(rgIVH308.isTagSelected(5) && edad >= 0 && edad <= 14) ||
								(rgIVH308.isTagSelected(6) && edad >= 0 && edad <= 15) ||
								(rgIVH308.isTagSelectedBetween(7, 8) && edad >= 0 && edad <= 11) ||
								(rgIVH308.isTagSelected(9) && ((edad >= 0 && edad < 18) || edad > 80)) ||
								(rgIVH308.isTagSelected(10) && edad >= 0 && edad <= 15) ||
								(rgIVH308.isTagSelected(11) && (edad < 3 || edad > 80)) ||
								(rgIVH308.isTagSelected(12) && ((edad >= 0 && edad <= 11) || edad > 80)) ||
								(rgIVH308.isTagSelected(13) && ((edad >= 0 && edad <= 15) || edad > 75)) ||
								(rgIVH308.isTagSelected(14) && ((edad >= 0 && edad <= 11) || edad > 80)) ||
								(rgIVH308.isTagSelected(15) && edad >=0 && edad <= 11) ||
								(rgIVH308.isTagSelected(16) && edad < 40) ||
								(rgIVH308.isTagSelectedBetween(17, 18) && edad >= 0 && edad <= 11))){
							mensaje = "La edad no corresponde a la ocupaci\u00f3n.";
							view = rgIVH308;
							error = true;
							return false;
						}
						else if(rgIVH308.isTagSelected(13) && rgIVH307.isTagSelectedBetween(1,3)){
							mensaje = "Es miembro de las FF.AA/PNP y el nivel educativo m\u00e1ximo alcanzado es primaria.";
							view = rgIVH308;
							error = true;
							return false;
						}
						else if(rgIVH308.isTagSelected(18) && Util.esVacio(txtIVH308_O)){
							mensaje = "Especifique no puede estar vacia.";
							view = txtIVH308_O;
							error = true;
							return false;
						}
						
						//VERIFICACIONES
						ArrayList<String> checkList= new ArrayList<String>();
						
						if((rgIVH308.isTagSelected(11) && rgIVH307.isTagSelected(1))){
							mensaje = "Es estudiante de ocupaci\u00f3n y sin nivel educativo alcanzado.";
							checkList.add(mensaje);		
						}
						if((rgIVH308.isTagSelectedBetween(9,10) && rgIVH307.isTagSelectedBetween(1,2))){
							mensaje = "Es Empleado de oficina/Empresario de ocupaci\u00f3n y sin nivel/educaci\u00f3n "
									+ "inicial como nivel educativo alcanzado.";
							checkList.add(mensaje);
						}
						if((rgIVH308.isTagSelected(12) && rgIVH307.isTagSelectedBetween(6,7))){
							mensaje = "Es empleado(a) del hogar y nivel educativo alcanzado es superior universitario o postgrado.";
							checkList.add(mensaje);
						}
						if(Integer.valueOf(5).equals(C3CAP300_Fragment_301.cap300cap200.getP208()) && 
								Util.getInt(txtIVH305)>0){
							mensaje ="Si es INFANTICIDIO corrija la edad de la v\u00edctima.";		
							checkList.add(mensaje);
						}
						
						if(checkList.size()>0){
							for(String s: checkList){
								ToastMessage.msgBox(this.getActivity(), s,
										ToastMessage.MESSAGE_INFO,
										ToastMessage.DURATION_LONG);
							}
						}
					}
				}
			}
//		}


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
		
		return true;
	}
}

