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
import gob.inei.dnce.components.TextBoxField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.util.Edad;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP300_Fragment_301;
import gob.inei.renadef2016.fragments.C3CAP400_Fragment_401;
import gob.inei.renadef2016.modelo.delitos.Cap400Delitos;
import gob.inei.renadef2016.service.MarcoService;

import java.sql.SQLException;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.InputFilter;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3CAP400_Fragment_401_01 extends DialogFragmentComponent {
	public interface C1CAP300_Fragment_301_01Listener {
		void onFinishEditDialog(String inputText);
	}

	@FieldAnnotation(orderIndex = 1)
	public TextField txtIVM401A;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtIVM401B;
	@FieldAnnotation(orderIndex = 3)
	public TextField txtIVM401C;
	@FieldAnnotation(orderIndex = 4)
	public RadioGroupOtherField rgIVM402;
	@FieldAnnotation(orderIndex = 5)
	public TextField txtIVM402_N; 
	@FieldAnnotation(orderIndex = 6)
	public RadioGroupOtherField rgIVM403;
	@FieldAnnotation(orderIndex = 7) 
	public IntegerField txtIVM404;
	@FieldAnnotation(orderIndex = 8)
	public DateTimeField txtIVM405_DIA;
	@FieldAnnotation(orderIndex = 9)
	public DateTimeField txtIVM405_MES;
	@FieldAnnotation(orderIndex = 10)
	public DateTimeField txtIVM405_ANIO;
	@FieldAnnotation(orderIndex = 11) 
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 12)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private LabelComponent lblP402Ndoc; 
	private static C3CAP400_Fragment_401 caller;
	private GridComponent2 grid_P401, grid_P405;
	private Cap400Delitos detalle;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;
	private static Integer nroVistimario;
	private String nomb, apep, apem, numd, inin, inip, inim, inid;
	private boolean flgGuardar, flgdobleDig, flgSociedad;

	public static C3CAP400_Fragment_401_01 newInstance(FragmentForm pagina,
			Cap400Delitos detalle, Integer nro, MarcoService marcoService) {
		caller = (C3CAP400_Fragment_401) pagina;
		mimarcoService = marcoService;
		nroVistimario = nro;
		Filtros.clear();
		C3CAP400_Fragment_401_01 f = new C3CAP400_Fragment_401_01();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP400_Fragment_401_01() {
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
		
		return C3CAP400_Fragment_401.createTitle(getActivity(), "Victimario N\u00B0: "+nroVistimario+
				(C3CAP400_Fragment_401.cap400cap200.ih215==null?"":"/"+C3CAP400_Fragment_401.cap400cap200.ih215), 
				C3CAP400_Fragment_401.cap400cap200.getP208CodC(), rootView, btnAtras, btnAdelante);

	}

	private void cargarDatos() {
		entityToUI(detalle);
		inin = nomb = txtIVM401A.getText().toString().trim();
		inip = apep = txtIVM401B.getText().toString().trim();
		inim = apem = txtIVM401C.getText().toString().trim();
		inid = numd = txtIVM402_N.getText().toString().trim();
//		flgdobleDig = Integer.valueOf(18).compareTo(C3CAP400_Fragment_401.cap400cap200.ih208) < 0;
// 		flgSociedad = Integer.valueOf(23).compareTo(C3CAP300_Fragment_301.cap300cap200.getP208()) < 0; // aca se cae 29/03/17
		flgdobleDig = false;
		inicio();
	}

	private void inicio() {
		onCheckedChange_P402(rgIVM402);
		txtIVM401A.requestFocus();
	}

	@Override
	protected View createUI() {
		buildFields();
		
		LinearLayout q0 = createQuestionSection(R.string.lb_c3_cap400_p401, grid_P401.component());
		LinearLayout q1 = createQuestionSection(R.string.lb_c3_cap400_p402, rgIVM402, createLy(LinearLayout.HORIZONTAL, 
				Gravity.CENTER_VERTICAL|Gravity.LEFT, 20, 0, 20, lblP402Ndoc, txtIVM402_N));
		LinearLayout q2 = createQuestionSection(R.string.lb_c3_cap400_p403, rgIVM403);
		LinearLayout q3 = createQuestionSection(R.string.lb_c3_cap400_p404, txtIVM404);
//		q4 = createQuestionSection(R.string.lb_c3_cap400_p405, txtIVM405);
		LinearLayout q4 = createQuestionSection(R.string.lb_c3_cap400_p405, grid_P405.component());

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

		LabelComponent lblP401A = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p301A).size(altoComponente, 170);
		LabelComponent lblP401B = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p301B).size(altoComponente, 170);
		LabelComponent lblP401C = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p301C).size(altoComponente, 170);
		
		txtIVM401A = new TextField(getActivity(),false).size(altoComponente, 400).callbackOnFocus("on_P401AFocus_onChange");
		txtIVM401B = new TextField(getActivity(),false).size(altoComponente, 400).callbackOnFocus("on_P401BFocus_onChange");
		txtIVM401C = new TextField(getActivity(),false).size(altoComponente, 400).callbackOnFocus("on_P401CFocus_onChange");
		
		rgIVM402 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap400_p402_1,
				R.string.lb_c3_cap400_p402_2, R.string.lb_c3_cap400_p402_3, R.string.lb_c3_cap400_p402_4,
				R.string.lb_c3_cap400_p402_5, R.string.lb_c3_cap400_p402_6, R.string.lb_c3_cap400_p402_7).
				size(sizeHeigth, sizeWidth).orientation(RadioGroupOtherField.ORIENTATION.VERTICAL).callback("onCheckedChange_P402");
		rgIVM403 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap400_p403_1,
				R.string.lb_c3_cap400_p403_2, R.string.lb_c3_cap400_p403_3).size(sizeHeigth, sizeWidth).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
		
		lblP402Ndoc = new LabelComponent(getActivity()).text(R.string.lb_c3_cap200_p302_Ndoc).negrita();
		txtIVM402_N = new TextField(getActivity(),false).size(altoComponente, 300).callbackOnFocus("on_IVH402Focus_onChange");
		
		txtIVM404= new IntegerField(getActivity(),false).size(altoComponente, 120);
		
//		txtIVM405_DIA = new IntegerField(getActivity());
//		txtIVM405_MES = new IntegerField(getActivity());
//		txtIVM405_ANIO = new IntegerField(getActivity());
//		txtIVM405 = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 200).
//				dateOrhour(txtIVM405_DIA, txtIVM405_MES, txtIVM405_ANIO);
		
		txtIVM405_DIA = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 120).
				showObject(SHOW_HIDE.MONTH_YEAR).buttons("Aceptar", "Omision").callback("on_P405Change");
		txtIVM405_DIA.setDialogParent(this);
		txtIVM405_MES = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 120).
				showObject(SHOW_HIDE.DAY_YEAR).buttons("Aceptar", "Omision").callback("on_P405Change");
		txtIVM405_MES.setDialogParent(this);
		txtIVM405_ANIO = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 120).
				showObject(SHOW_HIDE.DAY_MONTH).setRangoYear(1900, 2015).buttons("Aceptar", "Omision").callback("on_P405Change");
		txtIVM405_ANIO.setDialogParent(this);
		
		grid_P401 = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_P401.addComponent(lblP401A);
		grid_P401.addComponent(txtIVM401A);
		grid_P401.addComponent(lblP401B);
		grid_P401.addComponent(txtIVM401B);
		grid_P401.addComponent(lblP401C);
		grid_P401.addComponent(txtIVM401C);

		btnAceptar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnAceptar).size(200, 60);
		btnCancelar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnCancelar).size(200, 60);
		btnAtras = new ImageComponent(getParent().getActivity(), R.drawable.previous, R.drawable.previous).
				size(65, 65);
		btnAdelante = new ImageComponent(getParent().getActivity(), R.drawable.next, R.drawable.next).
				size(65, 65);
		txtIVM405_DIA.setFocusOnDissmis(txtIVM405_MES);
		txtIVM405_MES.setFocusOnDissmis(txtIVM405_ANIO);
		txtIVM405_ANIO.setFocusOnDissmis(btnAceptar);
		
		LabelComponent lblDay = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p305_d).size(altoComponente, 120).centrar();
		LabelComponent lblMonth = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p305_m).size(altoComponente, 120).centrar();
		LabelComponent lblYear = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p305_a).size(altoComponente, 120).centrar();
		
		grid_P405 = new GridComponent2(getActivity(), 3).colorFondo(R.color.blanco);
		grid_P405.addComponent(lblDay);
		grid_P405.addComponent(lblMonth);
		grid_P405.addComponent(lblYear);
		grid_P405.addComponent(txtIVM405_DIA);
		grid_P405.addComponent(txtIVM405_MES);
		grid_P405.addComponent(txtIVM405_ANIO);
		
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				C3CAP400_Fragment_401_01.this.dismiss();
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
				C3CAP400_Fragment_401_01.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		/* FILTROS 400.*/
		
		Filtros.setFiltro(txtIVM401A, Filtros.TIPO.ALFA, 50, null);
		Filtros.setFiltro(txtIVM401B, Filtros.TIPO.ALFA, 50, null);
		Filtros.setFiltro(txtIVM401C, Filtros.TIPO.ALFA, 50, null);
		Filtros.setFiltro(txtIVM402_N, Filtros.TIPO.ALFAN_U, 12, null);
		Filtros.setFiltro(txtIVM404, Filtros.TIPO.NUMBER, 2, 0, null,0,99);

	}
	
	public void on_P401AFocus_onChange(){
		String text = txtIVM401A.getText().toString().trim();
		if(flgdobleDig){
			if(!inin.equals("") && !nomb.equals(text)) {nomb = ""; inin="";};
			if(nomb.equals(text)) {	inin=text; flgGuardar = false; return;	}
			if(nomb.equals("")) {
				txtIVM401A.setError("Doble Digitaci\u00f3n.");
				flgGuardar = true;
			}
			if(!nomb.equals("")) {
				ToastMessage.msgBox(getActivity(), "Error en Doble Digitaci\u00f3n",
	                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
				nomb = "";
			} else {
				nomb = text;
			}
			txtIVM401A.post(new Runnable() {
				@Override
				public void run() {
					txtIVM401A.setText("");
					txtIVM401A.requestFocus();
				}
			});
		} else {
			if((App.SOCIEDAD.equals(text) || App.ESTADO.equals(text)) && flgSociedad){
				txtIVM401B.setText("NEP");
				txtIVM401C.setText("NEP");
				rgIVM402.setTagIndexSelected(5);
				rgIVM403.setTagIndexSelected(2);
				txtIVM404.setText("99");
				txtIVM405_DIA.setValue(Util.getFecha(9999, 99, 99));
				txtIVM405_MES.setValue(Util.getFecha(9999, 99, 99));
				txtIVM405_ANIO.setValue(Util.getFecha(9999, 99, 99));
				btnAceptar.post(new Runnable() {
					@Override
					public void run() {
						btnAceptar.requestFocus();
					}
				});
			} 
		}
	}
	
	public void on_P401BFocus_onChange(){
		if(flgdobleDig){
			String text = txtIVM401B.getText().toString().trim();
			if(!inip.equals("") && !apep.equals(text)) {apep = ""; inip="";};
			if(apep.equals(text)) {	inip=text; flgGuardar = false; return;	}
			if(apep.equals("")) {
				txtIVM401B.setError("Doble Digitaci\u00f3n.");
				flgGuardar = true;
			}
			if(!apep.equals("")) {
				ToastMessage.msgBox(getActivity(), "Error en Doble Digitaci\u00f3n",
	                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
				apep = "";
			} else {
				apep = text;
			}
			txtIVM401B.post(new Runnable() {
				@Override
				public void run() {
					txtIVM401B.setText("");
					txtIVM401B.requestFocus();
				}
			});
		}
	}
	
	public void on_P401CFocus_onChange(){
		if(flgdobleDig){
			String text = txtIVM401C.getText().toString().trim();
			if(!inim.equals("") && !apem.equals(text)) {apem = ""; inim="";};
			if(apem.equals(text)) {	inim=text; flgGuardar = false; return;	}
			if(apem.equals("")) {
				txtIVM401C.setError("Doble Digitaci\u00f3n.");
				flgGuardar = true;
			}
			if(!apem.equals("")) {
				ToastMessage.msgBox(getActivity(), "Error en Doble Digitaci\u00f3n",
	                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
				apem = "";
			} else {
				apem = text;
			}
			txtIVM401C.post(new Runnable() {
				@Override
				public void run() {
					txtIVM401C.setText("");
					txtIVM401C.requestFocus();
				}
			});
		}
	}
	
	public void on_IVH402Focus_onChange(){
		if(flgdobleDig){
			String text = txtIVM402_N.getText().toString().trim();
			if(!inid.equals("") && !numd.equals(text)) {numd = ""; inid="";};
			if(numd.equals(text)) {	inid=text; flgGuardar = false; return;	}
			if(numd.equals("")) {
				txtIVM402_N.setError("Doble Digitaci\u00f3n.");
				flgGuardar = true;
			}
			if(!numd.equals("")) {
				ToastMessage.msgBox(getActivity(), "Error en Doble Digitaci\u00f3n",
	                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
				numd = "";
			} else {
				numd = text;
			}
			txtIVM402_N.post(new Runnable() {
				@Override
				public void run() {
					txtIVM402_N.setText("");
					txtIVM402_N.requestFocus();
				}
			});
		}
	}
	
	private void save(){
		btnAceptar.requestFocus();
		if(flgGuardar) return;
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		
		FragmentManager fm = caller.getFragmentManager();
		if((txtIVM401A.getText().toString().trim().equals(App.SOCIEDAD)
				|| txtIVM401A.getText().toString().trim().equals(App.ESTADO)) && flgSociedad){
			C3CAP400_Fragment_401_Obs aperturaDialog = C3CAP400_Fragment_401_Obs.
					newInstance(caller, detalle, mimarcoService);
			aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
			aperturaDialog.show(fm, "aperturaDialog");
		} else {
			C3CAP400_Fragment_401_02 aperturaDialog = C3CAP400_Fragment_401_02.
					newInstance(caller, detalle, mimarcoService);
			aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
			aperturaDialog.show(fm, "aperturaDialog");
		}
		C3CAP400_Fragment_401_01.this.dismiss();
	}
	
	public void on_P405Change(FieldComponent component){
		if(component.getValue() == null){
			if(((DateTimeField)component).equals(txtIVM405_DIA)) {
				txtIVM405_DIA.setValue(Util.getFecha(9999, 99, 99));
				txtIVM405_MES.requestFocus();
			} else if(((DateTimeField)component).equals(txtIVM405_MES)) {
				txtIVM405_MES.setValue(Util.getFecha(9999, 99, 99));
				txtIVM405_ANIO.requestFocus();
			} else if(((DateTimeField)component).equals(txtIVM405_ANIO)) {
				txtIVM405_ANIO.setValue(Util.getFecha(9999, 99, 99));
				btnAceptar.requestFocus();
			}
		} else {
			if(((DateTimeField)component).equals(txtIVM405_DIA)) txtIVM405_MES.requestFocus();
			else if(((DateTimeField)component).equals(txtIVM405_MES)) txtIVM405_ANIO.requestFocus();
			else if(((DateTimeField)component).equals(txtIVM405_ANIO)) btnAceptar.requestFocus();
		}
	}
	
	private int getP304y404(int edad){
		int cont = 0;
		int edad304 = mimarcoService.getEdadVictima304y309(detalle.id_n, detalle.nro_mreg);
		if(edad304 == 0) return -1;
		if(C3CAP400_Fragment_401.getData().size()>0){
			for(Cap400Delitos c:C3CAP400_Fragment_401.getData()){
				if(edad304 - edad < 11 /*&& detalle.nro_pvreg != c.nro_pvreg*/) cont++;
			}
		}
		return cont;
	}

	private boolean validar() {
		error = false;

		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
		
		if(Util.esVacio(txtIVM401A)){
			mensaje="Apellido Paterno no puede estar vacio";
			view=txtIVM401A;
			error=true;
			return false;
		}
		if(Util.esVacio(txtIVM401B)){
			mensaje="Apellido Materno no puede estar vacio";
			view=txtIVM401B;
			error=true;
			return false;
		}
		if(Util.esVacio(txtIVM401C)){
			mensaje="Nombres no puede estar Vacio";
			view=txtIVM401C;
			error=true;
			return false;
		} else {
			try {
				String no = txtIVM401A.getText().toString().trim();
				String ap = txtIVM401B.getText().toString().trim();
				String am = txtIVM401C.getText().toString().trim();
				String _resp = mimarcoService.getDuplicidades400(1, detalle.id_n, no+" "+ap+" "+am, 
						detalle.nro_mreg.toString(), detalle.nro_pvreg.toString());
				if(_resp != null){
					String[] rsp = _resp.split(",");
					mensaje="Apellidos y Nombres ya existen en: Denuncia N\u00B0: [ "+rsp[0]+" ] "
							+ "- Victimario N\u00B0: [ "+rsp[1]+" ]";
					view=txtIVM401A;
					error= true;
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
//			if(mimarcoService.getDuplicidades400(1, detalle.id_n, no+" "+ap+" "+am, 
//					detalle.nro_mreg.toString(), detalle.nro_pvreg.toString())){
//				mensaje="No debe haber dos personas con los mismos apellidos y "
//						+ "nombres en esta denuncia.";
//				view=txtIVM401A;
//				error= true;
//				return false;
//			}
		}
	
		if(Util.esVacio(rgIVM402)){
			mensaje="La pregunta 402 no puede estar Vacia";
			view=rgIVM402;
			error=true;
			return false;
		}
		
//		
	//  Validación para DNI	
		Integer p402 = rgIVM402.getTagSelectedInteger(-1);
		if(!(p402 >= 5 && p402 <= 6)){
			if(p402 == 1 && !checkChars(txtIVM402_N.getText().toString())){
				mensaje="Numero de DNI no debe tener letras";
				view = txtIVM402_N;
				error=true;
				return false;
			}
			else if(p402 == 1 && txtIVM402_N.getText().length()!=8){
				mensaje="El Numero de DNI debe tener 8 digitos";
				error=true;
				view = txtIVM402_N;
				return false;
			}		
	
		// Validación para Carnet y Pasaporte
			
//			else if((p402 >=2 && p402 <= 3) && !checkChars(txtIVM402_N.getText().toString())){
//				mensaje="Si Preg.402 es Carnet de extranjería o Pasaporte entonces el n\u00famero es a 12 dígitos";
//				error=true;
//				view = txtIVM402_N;
//				return false;
//				}	
			
//			else if((p402 >=2 && p402 <= 3) && txtIVM402_N.getText().length()!=12){
			else if((p402 >=2 && p402 <= 3) && !(txtIVM402_N.getText().length()>=6 && txtIVM402_N.getText().length()<=12)){
//				mensaje="Si Preg.402 es Carnet de extranjería o Pasaporte entonces el n\u00famero es a 12 dígitos";
				mensaje="Si Preg.402 es Carnet de extranjería o Pasaporte entonces el n\u00famero debe estar entre 6 a 12 dígitos";
				view = txtIVM402_N;
				error=true;
				return false;			
			}
		//Validación para Licencia de conducir
			
			int dig = countChars(txtIVM402_N.getText().toString());
			if(p402 == 4 && !(dig>=9 && dig<=10) ){
				mensaje="Si Preg.402 es Licencia de conducir entonces el n\u00famero debe estar entre 9 \u00f3 10 caracteres.";
				view = txtIVM402_N;
				error=true;
				return false;		
			}
	//		Si IVM402 = 4 & (IVM402_N  NO INICIA CON UNA LETRA) Þ Error
			else if(p402 == 4 && checkChars(txtIVM402_N.getText().toString().substring(0,1)) ){
				mensaje="Si Preg.402 es Licencia de Conducir entonces eL n\u00famero de documento debe de iniciar con una letra";
				view = txtIVM402_N;
				error=true;
				return false;
			}
	//		Si IVM402 = 4 & (IVM402_N  NO TERMINA CON 8 NUMEROS) Þ Error
			else if(p402 == 4 && !checkChars(txtIVM402_N.getText().toString().substring(dig==9?1:2,dig)) ){
				mensaje="Si Preg.402 es Licencia de conducir entonces eL n\u00famero de documento debe terminar con 8 n\u00fameros";
				view = txtIVM402_N;
				error=true;
				return false;
			}
			else if(!Util.esVacio(txtIVM402_N) && Util.sumDigits(txtIVM402_N.getText().toString())==0){
				mensaje="Verifique informaci\u00f3n del Numero de documento.";
				view = txtIVM402_N;
				error=true;
				return false;
			}		
			else {
				try {
					String nd = txtIVM402_N.getText().toString().trim();
					String _resp = mimarcoService.getDuplicidades400(2, detalle.id_n, nd, 
							detalle.nro_mreg.toString(), detalle.nro_pvreg.toString());
					if(_resp != null){
						String[] rsp = _resp.trim().split(",");
						Log.e("valores", "observastes: "+rsp.length);
						mensaje="N\u00B0 de Documento de Identidad ya existe en: Denuncia N\u00B0: [ "+rsp[0]+" ] "
								+ "- Victimario N\u00B0: [ "+rsp[1]+" ]";
						view=txtIVM402_N;
						error= true;
						return false;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
//				if(mimarcoService.getDuplicidades400(2, detalle.id_n, nd, 
//						detalle.nro_mreg.toString(), detalle.nro_pvreg.toString())){
//					mensaje="No debe haber dos personas con los mismos n\u00fameros de "
//							+ "documento de identidad.";
//					view=txtIVM402_N;
//					error= true;
//					return false;
//				}
			}
		}
		
		if(Util.esVacio(rgIVM403)){
			mensaje="La pregunta 403 no puede estar vacio.";
			view=rgIVM403;
			error=true;
			return false;	
		}
		
		if(Util.esVacio(txtIVM404)){
			mensaje="Edad no puede estar vacio";
			view=txtIVM404;
			error=true;
			return false;
		}
//		Si IVM404 < 18 & IVM402  = 2; 4, ERROR
		int edad = Integer.parseInt(txtIVM404.getText().toString());
		if(edad<18 && (rgIVM402.isTagSelectedBetween(new Integer[]{2,4}))){
			mensaje= "Edad del presunto victimario no corresponde con tipo de documento de identidad";
			error=true;
			return false;
		} 
		
//		if(Util.esVacio(txtIVM405)){
//			mensaje="Fecha de Nacimiento no puede estar vacio";
//			view=txtIVM405;
//			error=true;
//			return false;
//		}
		
		if(Util.esVacio(txtIVM405_DIA)){
			mensaje = "La pregunta 405 - D\u00eda no puede estar Vacia";
			view = txtIVM405_DIA;
			error = true;
			return false;
		} else if(Util.esVacio(txtIVM405_MES)){
			mensaje = "La pregunta 405 - Mes no puede estar Vacia";
			view = txtIVM405_MES;
			error = true;
			return false;
		} else if(Util.esVacio(txtIVM405_ANIO)){
			mensaje = "La pregunta 405 - A\u00f1o no puede estar Vacia";
			view = txtIVM405_ANIO;
			error = true;
			return false;
		}
		
		int anio = Integer.valueOf(txtIVM405_ANIO.getText().toString());
		int mes = Integer.valueOf(txtIVM405_MES.getText().toString());
		int dia = Integer.valueOf(txtIVM405_DIA.getText().toString());
		if(dia!=99 && mes!=99){
			SparseArray<String> spa;
			if((spa = Util.checkDatesValid(dia, mes, anio)).valueAt(0)!=null){
				mensaje = "Fecha Calendario incorrecto; " + spa.valueAt(0);
				view = spa.keyAt(0) == 1 ? txtIVM405_DIA : txtIVM405_MES;
				error = true;
				return false;
			}
		}
		
		if(edad != 99 && anio!=9999){
			int anioA = Integer.valueOf(C3CAP400_Fragment_401.cap400cap200.ih203_anio);
			int mesA = Integer.valueOf(C3CAP400_Fragment_401.cap400cap200.ih203_mes);
			int diaA = Integer.valueOf(C3CAP400_Fragment_401.cap400cap200.ih203_dia);
			String fecha1 = Util.getFechaFormateada(anio, mes==99?1:mes, mes==99?1:(dia==99?1:dia), "ddMMyyyy");
			String fecha2 = Util.getFechaFormateada(anioA, mesA, (diaA==99?1:diaA),"ddMMyyyy");
			if(((diaA!=99 && mesA!=99) || mesA!=99) && ((dia!=99 && mes!=99 && anio!=9999) || 
					(mes!=99 && anio!=9999))){
				Log.e("fecha1", "fecha1: "+fecha1);
				Log.e("fecha2", "fecha2: "+fecha2);
				if(Util.compareDate(fecha1, fecha2, "ddMMyyyy")>0){
					mensaje="Fecha de Nacimiento no puede ser mayor a la Fecha del Hecho Preg. 203.";
					view=txtIVM405_DIA;
					error=true;	
					return false;
				} 
			}
			
			Edad edadCalculada = Util.calcularEdad(anioA, mesA, diaA, anio, mes, dia);
			int edadCalc = edadCalculada.getAnios();
			Log.e("edad Calculada", "edad anios: "+edadCalculada.getAnios());
//			if(edadCalc!=99 && /*edadCalc!=0 &&*/ edadCalc!=edad){
			if(edadCalc!=99 && !(edadCalc>=edad-1 && edadCalc<=edad+1)){
				mensaje = "Edad Calculada a partir de Fecha de Nacimiento no coincide "
						+ "con la Edad registrada en Preg. 404.";
				view = txtIVM404;
				error = true;
				return false;
			}
		}
		
//		VERIFICACIONES
		ArrayList<String> checkList= new ArrayList<String>();
//		Si IVM404 < 15 VERIFICAR
		
		if(edad < 15){
			mensaje="VERIFICAR: Edad del presunto victimario menor a 15 a\u00f1os.";
			checkList.add(mensaje);	
		} else if(edad != 99 && (edad < 18 || edad > 60)){
			mensaje="VERIFICAR: Edad del presunto victimario "+(edad < 18?" menor a 18":
				"mayor a 60")+" a\u00f1os.";
			checkList.add(mensaje);	
		}
		if(edad < 18 && rgIVM402.isTagSelected(new Integer[]{2,4})){
			mensaje="VERIFICAR: Edad del presunto victimario no corresponde con tipo de documento de identidad.";
			checkList.add(mensaje);	
		}
		if(getP304y404(edad)>0){
			mensaje="VERIFICAR: Diferencia entre edades de alguna v\u00edctima (padre/madre) y algun presunto "
					+ "victimario (hijo/hija) es menor de 11.";
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
	
	private boolean checkChars(String txt){
      for(char c : txt.toCharArray()){
             if(Character.isLetter(c) || Character.isSpaceChar(c)) return false;
      }
      return true;
	}
	private int countChars(String txt){
	       int contChars = 0;
	             for(char c : txt.toCharArray()){
	                    if(Character.isLetter(c) || Character.isSpaceChar(c) || Character.isDigit(c)) contChars++;
	             }
	             return contChars;
	}
	public void onCheckedChange_P402(FieldComponent component){
		if(component.getValue() == null) return;
		Integer result = Integer.valueOf(component.getValue().toString());
		
		if(!Util.esDiferente(result, 1,2,3,4,5)){
			caller.lockView(false, txtIVM402_N);
			txtIVM402_N.requestFocus();	
			changeProperties(rgIVM402, txtIVM402_N, result);
		} else if(!Util.esDiferente(result, 6,7)){			
			caller.cleanAndLockView(txtIVM402_N);
			caller.lockView(true, txtIVM402_N);
			rgIVM403.requestFocus();
		}	
	}
	
	private void changeProperties(RadioGroupOtherField rg, TextBoxField txt, Integer result){
		InputFilter[] ifs =  txt.getFilters();
		if(ifs[0] instanceof Filtros){
			if(result == 1){
				((Filtros)ifs[0]).setProperties(Filtros.TIPO.NUMBER,8,1,99999999,8,1,8,8);
    		} else if(result == 4){
				((Filtros)ifs[0]).setProperties(Filtros.TIPO.ALFAN_U,10,1,9999999999l,10,1,9,10);
    		} else if(result == 5){
				((Filtros)ifs[0]).setProperties(Filtros.TIPO.NUMBER,11,1,99999999999l,11,1,11,11);
    		} else {
    			((Filtros)ifs[0]).setProperties(Filtros.TIPO.ALFAN_U,12,-1,-1,-1,-1,-1,-1);
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
		String [] saveExtras = new String[]{"NRO_MREG","NRO_PVREG","ORDEN_400"};
		try {
			if(!mimarcoService.saveOrUpdate(detalle, detalle.getSecCap(caller.getListFields(this,
					vEstado(saveExtras))))){
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
//		if(!mimarcoService.saveCap400Delitos(detalle, Utilidades.getListFields(this,
//				new String[]{"NRO_MREG", "NRO_PVREG", "ORDEN_400"}))){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
//		else {
//			caller.reloadData(detalle, 1);
//		}
		
		return true;
	}
	
	private String[] vEstado(String[] extras){
		if((App.ESTADO.equals(txtIVM401A.getText().toString().trim())
				|| App.SOCIEDAD.equals(txtIVM401A.getText().toString().trim())) && flgSociedad) {
			detalle.ivm406 = 8;
			detalle.ivm407 = 17;
			detalle.ivm407_o = null;
			detalle.ivm408 = 7;
			return new String[]{"NRO_MREG","NRO_PVREG","ORDEN_400","IVM406","IVM407","IVM407_O","IVM408"};
		}
		return extras;
	}
}
