package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.DialogComponent.TIPO_DIALOGO;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.TextBoxField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP300_Fragment_301;
import gob.inei.renadef2016.modelo.delitos.Cap300Delitos;
import gob.inei.renadef2016.service.MarcoService;

import java.sql.SQLException;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3CAP300_Fragment_301_01 extends DialogFragmentComponent implements Respondible {
	public interface C1CAP300_Fragment_301_01Listener {
		void onFinishEditDialog(String inputText);
	}

	@FieldAnnotation(orderIndex = 1)
	public TextField txtIVH301A;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtIVH301B;
	@FieldAnnotation(orderIndex = 3)
	public TextField txtIVH301C;
	@FieldAnnotation(orderIndex = 4)
	public RadioGroupOtherField rgIVH302;
	@FieldAnnotation(orderIndex = 5)
	public TextField txtIVH302_N;
	@FieldAnnotation(orderIndex = 6)
	public RadioGroupOtherField rgIVH303;
	@FieldAnnotation(orderIndex = 7)
	public RadioGroupOtherField rgIVH304;
	@FieldAnnotation(orderIndex = 8)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 9)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private static C3CAP300_Fragment_301 caller;  
	private GridComponent2 grid_P301;
	private LabelComponent lblP302Ndoc;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private Cap300Delitos detalle;
	private static MarcoService mimarcoService;
	private static Integer nroVict;
	private String nomb, apep, apem, numd, inin, inip, inim, inid;
	private DialogComponent dc;
	private boolean flgGuardar, flgdobleDig, flgEstado, flgSociedad;

	public static C3CAP300_Fragment_301_01 newInstance(FragmentForm pagina,
			Cap300Delitos detalle, Integer nro, MarcoService marcoService) {
		caller = (C3CAP300_Fragment_301) pagina;
		mimarcoService = marcoService;
		nroVict = nro;
		Filtros.clear();
		C3CAP300_Fragment_301_01 f = new C3CAP300_Fragment_301_01();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP300_Fragment_301_01() {
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
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getDialog().setTitle("Victima/Fallecido N\u00B0: "+detalle.nro_vfreg);
		initObjectsWithoutXML(this, rootView);

		cargarDatos();
		enlazarCajas(this);
		listening();
		
		return C3CAP300_Fragment_301.createTitle(getActivity(), "V\u00cdctima/Fallecido N\u00B0: "+nroVict + 
				(C3CAP300_Fragment_301.cap300cap200.ih214==null?"":"/"+C3CAP300_Fragment_301.cap300cap200.ih214), 
				C3CAP300_Fragment_301.cap300cap200.getP208CodC(), rootView, btnAtras, btnAdelante);

	}

	private void cargarDatos() {
		entityToUI(detalle);
		inin = nomb = txtIVH301A.getText().toString().trim();
		inip = apep = txtIVH301B.getText().toString().trim();
		inim = apem = txtIVH301C.getText().toString().trim();
		inid = numd = txtIVH302_N.getText().toString().trim();
		flgdobleDig = Integer.valueOf(24).compareTo(C3CAP300_Fragment_301.cap300cap200.getP208()) > 0;
		flgEstado = !Util.esDiferente(C3CAP300_Fragment_301.cap300cap200.getP208(), 
				/*73*/144,145,146,/*74,75,76,77,78*/147,148,149,150,151,152,153,154,155,156,157,158,
				159,160,161,162,163,/*79,80,81*/164,165,166,167,168,169,170,171,172,173,174,
				/*82,83,84,85*/175,176,177,178,179,180,181,
				/*86,87,88,89,90*/182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,
				/*91,92,93,94,95*/199,200,201,202,203,204,205,206,207,208,
				/*96,97,98*/209,210,211,212,213,214,215,
				/*105,106,107,108*/226,227,228,229,230,231,232,233,234,235,236,237,
				/*109,110*/238,239,240,241,276,95,96,97,98);
		flgSociedad = Integer.valueOf(23).compareTo(C3CAP300_Fragment_301.cap300cap200.getP208()) < 0 ||
				!Util.esDiferente(C3CAP300_Fragment_301.cap300cap200.getP208(), 276,95,96,97,98);
		inicio();
	}

	private void inicio() {		
		onCheckedChange_P302(rgIVH302);
		txtIVH301A.requestFocus();
	}

	@Override
	protected View createUI() {
		buildFields();
		
		LinearLayout q0 = createQuestionSection(R.string.lb_c3_cap300_p301, grid_P301.component());
		LinearLayout q1 = createQuestionSection(R.string.lb_c3_cap300_p302, rgIVH302, createLy(LinearLayout.HORIZONTAL, 
				Gravity.CENTER_VERTICAL|Gravity.LEFT, 20, 0, 20, lblP302Ndoc, txtIVH302_N));
		LinearLayout q2 = createQuestionSection(R.string.lb_c3_cap300_p303, rgIVH303);
		LinearLayout q3 = createQuestionSection(R.string.lb_c3_cap300_p304, rgIVH304);

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

		LabelComponent lblP301A = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p301A).size(altoComponente, 170);
		LabelComponent lblP301B = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p301B).size(altoComponente, 170);
		LabelComponent lblP301C = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_c3_cap300_p301C).size(altoComponente, 170);
		
		txtIVH301A = new TextField(getActivity(),false).size(altoComponente, 400).callbackOnFocus("on_P301AFocus_onChange");//.callback("on_P301A_onChange");
		txtIVH301B = new TextField(getActivity(),false).size(altoComponente, 400).callbackOnFocus("on_P301BFocus_onChange");//.callback("on_P301B_onChange");
		txtIVH301C = new TextField(getActivity(),false).size(altoComponente, 400).callbackOnFocus("on_P301CFocus_onChange");//.callback("on_P301C_onChange");
		
		rgIVH302 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap300_p302_1,
				R.string.lb_c3_cap300_p302_2, R.string.lb_c3_cap300_p302_3, R.string.lb_c3_cap300_p302_4,
				R.string.lb_c3_cap300_p302_5, R.string.lb_c3_cap300_p302_6, R.string.lb_c3_cap300_p302_7).
				size(sizeHeigth, sizeWidth).orientation(RadioGroupOtherField.ORIENTATION.VERTICAL).callback("onCheckedChange_P302");
		rgIVH303 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap300_p303_1,
				R.string.lb_c3_cap300_p303_2, R.string.lb_c3_cap300_p303_3).
				size(sizeHeigth, sizeWidth).orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
		rgIVH304= new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap300_p304A_1,
				R.string.lb_c3_cap300_p304A_2, R.string.lb_c3_cap300_p304A_3, R.string.lb_c3_cap300_p304A_4).
				size(sizeHeigth, sizeWidth).orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
		lblP302Ndoc = new LabelComponent(getActivity()).text(R.string.lb_c3_cap200_p302_Ndoc).negrita();
		txtIVH302_N = new TextField(getActivity(), false).size(altoComponente, 300).callbackOnFocus("on_IVH302Focus_onChange");
		
		grid_P301 = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_P301.addComponent(lblP301A);
		grid_P301.addComponent(txtIVH301A);
		grid_P301.addComponent(lblP301B);
		grid_P301.addComponent(txtIVH301B);
		grid_P301.addComponent(lblP301C);
		grid_P301.addComponent(txtIVH301C);

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
				C3CAP300_Fragment_301_01.this.dismiss();
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
				C3CAP300_Fragment_301_01.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
				
		Filtros.setFiltro(txtIVH301A, Filtros.TIPO.ALFAN_U, 50, null);
		Filtros.setFiltro(txtIVH301B, Filtros.TIPO.ALFAN_U, 50, null);
		Filtros.setFiltro(txtIVH301C, Filtros.TIPO.ALFAN_U, 50, null);
		Filtros.setFiltro(txtIVH302_N, Filtros.TIPO.ALFAN,12,null);
		
	}
	
	public void on_P301AFocus_onChange(){
		String text = txtIVH301A.getText().toString().trim();
		if(flgdobleDig){
			if(!inin.equals("") && !nomb.equals(text)) {nomb = ""; inin="";};
			if(nomb.equals(text)) {	inin=text; flgGuardar = false; return;	}
			if(nomb.equals("")) {
				txtIVH301A.setError("Doble Digitaci\u00f3n.");
				flgGuardar = true;
			}
			if(!nomb.equals("")) {
				ToastMessage.msgBox(getActivity(), "Error en Doble Digitaci\u00f3n",
	                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
				nomb = "";
			} else {
				nomb = text;
			}
			txtIVH301A.post(new Runnable() {
				@Override
				public void run() {
					txtIVH301A.setText("");
					txtIVH301A.requestFocus();
				}
			});
		} else {
			if((App.ESTADO.equals(text) && flgEstado)
					|| (App.SOCIEDAD.equals(text) && flgSociedad)){
				txtIVH301B.setText("NEP");
				txtIVH301C.setText("NEP");
				rgIVH302.setTagIndexSelected(5);
				rgIVH303.setTagIndexSelected(2);
				rgIVH304.setTagIndexSelected(3);
				btnAceptar.post(new Runnable() {
					@Override
					public void run() {
						btnAceptar.requestFocus();
					}
				});
			} 
		}
	}
	
	public void on_P301BFocus_onChange(){
		if(flgdobleDig){
			String text = txtIVH301B.getText().toString().trim();
			if(!inip.equals("") && !apep.equals(text)) {apep = ""; inip="";};
			if(apep.equals(text)) {	inip=text; flgGuardar = false; return;	}
			if(apep.equals("")) {
				txtIVH301B.setError("Doble Digitaci\u00f3n.");
				flgGuardar = true;
			}
			if(!apep.equals("")) {
				ToastMessage.msgBox(getActivity(), "Error en Doble Digitaci\u00f3n",
	                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
				apep = "";
			} else {
				apep = text;
			}
			txtIVH301B.post(new Runnable() {
				@Override
				public void run() {
					txtIVH301B.setText("");
					txtIVH301B.requestFocus();
				}
			});
		}
	}
	
	public void on_P301CFocus_onChange(){
		if(flgdobleDig){
			String text = txtIVH301C.getText().toString().trim();
			if(!inim.equals("") && !apem.equals(text)) {apem = ""; inim="";};
			if(apem.equals(text)) {	inim=text; flgGuardar = false; return;	}
			if(apem.equals("")) {
				txtIVH301C.setError("Doble Digitaci\u00f3n.");
				flgGuardar = true;
			}
			if(!apem.equals("")) {
				ToastMessage.msgBox(getActivity(), "Error en Doble Digitaci\u00f3n",
	                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
				apem = "";
			} else {
				apem = text;
			}
			txtIVH301C.post(new Runnable() {
				@Override
				public void run() {
					txtIVH301C.setText("");
					txtIVH301C.requestFocus();
				}
			});
		}
	}
	
	public void on_IVH302Focus_onChange(){
		if(flgdobleDig){
			String text = txtIVH302_N.getText().toString().trim();
			if(!inid.equals("") && !numd.equals(text)) {numd = ""; inid="";};
			if(numd.equals(text)) {	inid=text; flgGuardar = false; return;	}
			if(numd.equals("")) {
				txtIVH302_N.setError("Doble Digitaci\u00f3n.");
				flgGuardar = true;
			}
			if(!numd.equals("")) {
				ToastMessage.msgBox(getActivity(), "Error en Doble Digitaci\u00f3n",
	                    ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_SHORT);
				numd = "";
			} else {
				numd = text;
			}
			txtIVH302_N.post(new Runnable() {
				@Override
				public void run() {
					txtIVH302_N.setText("");
					txtIVH302_N.requestFocus();
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
	}

	private boolean validar() {
		
		error = false;
		
		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		} 
		
		Integer mreg = Util.getInt(App.getInstance().getCap200().getP208(),24) <= 23 ? null : detalle.nro_mreg;
		if(Util.esVacio(txtIVH301A)){
			mensaje="Debe Ingresar el Apellido Paterno";
			view=txtIVH301A;
			error= true;
			return false;
		}
		else if(Util.esVacio(txtIVH301B)){
			mensaje="Debe Ingresar el Apellido Materno";
			view=txtIVH301B;
			error= true;
			return false;
		}
		else if(Util.esVacio(txtIVH301C)){
			mensaje="Debe Ingresar Nombres";
			view=txtIVH301C;
			error= true;
			return false;
		}
		else {
//			if(Util.getInt(App.getInstance().getCap200().ih208,24) <= 23){
			try {
				String no = txtIVH301A.getText().toString().trim();
				String ap = txtIVH301B.getText().toString().trim();
				String am = txtIVH301C.getText().toString().trim();
				String _resp = mimarcoService.getDuplicidades300(1, mreg, detalle.id_n, no+" "+ap+" "+am, 
						detalle.nro_mreg.toString()+" "+detalle.nro_vfreg.toString());
				if(_resp != null){
					String[] rsp = _resp.split(",");
					mensaje="Apellidos y Nombres ya existen en: Denuncia N\u00B0: [ "+rsp[0]+" ] "
							+ "- V\u00edctima N\u00B0: [ "+rsp[1]+" ]";
					view=txtIVH301A;
					error= true;
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
//			}
		}
		
		if( rgIVH302.getTagSelected()==null ){
			mensaje = "La pregunta 302 no puede estar Vacia";
			view = rgIVH302;
			error = true;
			return false;
		}
		Integer p302 = rgIVH302.getTagSelectedInteger(-1);
		if(!(p302 >= 5 && p302 <= 6)){
			if((rgIVH302.isTagSelectedBetween(1, 4)) && Util.esVacio(txtIVH302_N)){
				mensaje="N� de documento no debe de estar Vacio";
				view = txtIVH302_N;
				error=true;
				return false;
			}
		//  Validaci�n para DNI	
			else if(p302 == 1 && !checkChars(txtIVH302_N.getText().toString())){
				mensaje="El Numero de Documento no debe contener letras";
				view = txtIVH302_N;
				error=true;
				return false;
			}
							
			else if(p302 == 1 && txtIVH302_N.getText().length()!=8){
				mensaje="Si Preg.302 es DNI entonces el n\u00famero es a 8 d�gitos";
				view = txtIVH302_N;
				error=true;
				return false;					
			}
			// Validaci�n para Carnet y Pasaporte
//			else if((p302 >= 2 && p302 <= 3) && countDigits(txtIVH302_N.getText().toString())!=12){
			else if((p302 >= 2 && p302 <= 3) && !(txtIVH302_N.getText().length()>=6 && txtIVH302_N.getText().length()<=12)){
//				mensaje="Si Preg.302 es Carnet de Extranjer�a o Pasaporte entonces el n\u00famero es a 12 d�gitos";
				mensaje="Si Preg.302 es Carnet de extranjer�a o Pasaporte entonces el n\u00famero debe estar entre 6 a 12 d�gitos";
				view = txtIVH302_N;
				error=true;
				return false;
			}
			
			int dig = countChars(txtIVH302_N.getText().toString());
			if(p302 == 4 && !(dig>=9 && dig<=10)){
				mensaje="Si Preg.302 es Licencia de Conducir entonces el n\u00famero debe estar entre 9 \u00f3 10 caracteres.";
				view = txtIVH302_N;
				error=true;
				return false;			
			}
			// Preguntar a alex		
	//		//		Si IVH302 = 4 & (IVH302_N  NO INICIA CON UNA LETRA) � Error
			else if(p302 == 4 && checkChars(txtIVH302_N.getText().toString().substring(0, 1)) ){
					mensaje="Si Preg.302 es Licencia de Conducir entonces eL n\u00famero de documento debe de iniciar con una letra";
					view = txtIVH302_N;
					error=true;
					return false;
			}
	//		Si IVH302 = 4 & (IVH302_N  NO TERMINA CON 8 NUMEROS) � Error      -1,9
			else if(p302 == 4 && !checkChars(txtIVH302_N.getText().toString().substring(dig==9?1:2,dig)) ){
				mensaje="Si Preg.302 es Licencia de Conducir entonces EL n\u00famero de documento debe terminar con 8 n\u00fameros";
				view = txtIVH302_N;
				error=true;
				return false;
			}
			else if(!Util.esVacio(txtIVH302_N) && Util.sumDigits(txtIVH302_N.getText().toString())==0){
				mensaje="Verifique informaci\u00f3n del Numero de documento.";
				view = txtIVH302_N;
				error=true;
				return false;
			} 
			else {
//				if(Util.getInt(App.getInstance().getCap200().ih208,24) <= 23){
				try {
					String nd = txtIVH302_N.getText().toString().trim();
					String _resp = mimarcoService.getDuplicidades300(2, mreg, detalle.id_n, nd, 
							detalle.nro_mreg.toString()+" "+detalle.nro_vfreg.toString());
					if(_resp != null){
						String[] rsp = _resp.trim().split(",");
						Log.e("valores", "observastes: "+rsp.length);
						mensaje="N\u00B0 de Documento de Identidad ya existe en: Denuncia N\u00B0: [ "+rsp[0]+" ] "
								+ "- V\u00edctima N\u00B0: [ "+rsp[1]+" ]";
						view=txtIVH302_N;
						error= true;
						return false;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
//				}
			}
		}
		
		if(rgIVH303.getTagSelected()==null){
			mensaje="La pregunta 303 no puede estar Vacia";
			view = rgIVH303;
			error = true;
			return false;
		}
		else if(C3CAP300_Fragment_301.cap300cap200!=null && 
				C3CAP300_Fragment_301.cap300cap200.getP208() != null &&
				C3CAP300_Fragment_301.cap300cap200.getP208().intValue() == 7 &&
				!rgIVH303.isTagSelected(2)){ 
			mensaje="Si es Feminicidio identifique correctamente el sexo de la v\u00edctima.";
			view = rgIVH303;
			error = true;
			return false;
		}
		else if(rgIVH304.getTagSelected()==null){
			mensaje="La pregunta 304 no puede estar Vacia";
			view = rgIVH304;
			error = true;
			return false;
		}
		else if(rgIVH304.isTagSelectedBetween(1,2) && rgIVH302.isTagSelectedBetween(new Integer[]{2,4})){
			mensaje ="Las caracter�sticas de la edad no se relacionan con tipo de documento.";		
			view = rgIVH304;
			error=true;
			return false;			
		}
		else if(Integer.valueOf(5).equals(C3CAP300_Fragment_301.cap300cap200.getP208()) && 
				!rgIVH304.isTagSelected(1)){
			mensaje ="Si es INFANTICIDIO identifique correctamente la caracter\u00edstica de edad de la v\u00edctima.";		
			view = rgIVH304;
			error=true;
			return false;	
		}

		if (error) return false;
		return true;		
	} 

	public void onCheckedChange_P302(FieldComponent component){
		if(component.getValue() == null) return;
		Integer result = Integer.valueOf(component.getValue().toString());
		
		if(!Util.esDiferente(result, 1,2,3,4,5)){
			caller.lockView(false, txtIVH302_N);
			txtIVH302_N.requestFocus();
			changeProperties(txtIVH302_N, result);
		} else if(!Util.esDiferente(result, 6,7)){			
			caller.cleanAndLockView(txtIVH302_N);				
			caller.lockView(true, txtIVH302_N);
			rgIVH303.requestFocus();
		}		
	}
	
	private void changeProperties(TextBoxField txt, Integer result){
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
	
	private boolean checkChars(String txt){
	       int cChars = 0;
	             for(char c : txt.toCharArray()){
	                    if(Character.isLetter(c) || Character.isSpaceChar(c)) return false;
	             }
	             return true;
	}
	
	private int countDigits(String txt){
	       int contDigits = 0;
	             for(char c : txt.toCharArray()){
	                    if(Character.isDigit(c)) contDigits++;
	             }
	             return contDigits;
	}
	private int countChars(String txt){
	       int contChars = 0;
	             for(char c : txt.toCharArray()){
	                    if(Character.isLetter(c) || Character.isSpaceChar(c) || Character.isDigit(c)) contChars++;
	             }
	             return contChars;
	}
//	private boolean primerCaracterChars(String txt){
//	       int cChars = 0;
//	             for(char c : txt.toCharArray()){	            	 	
//	                    if(Character.isLetter(0) || Character.isDefined(c)) return false;
//	             }
//	             return true;
//	}
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
		
		int _msp = 0;
		if((_msp = validaData())!=0){
			dc = new DialogComponent(getActivity(), this,
					TIPO_DIALOGO.YES_NO, getResources()
							.getString(R.string.app_name),
					getMsg(_msp));
			dc.put("clave", _msp);
			dc.showDialog();
		} else {
			savePag(-1);
		}
		
		return true;
	}
	
	private int validaData(){
		Integer p304 = rgIVH304.getTagSelectedInteger(-1);
		if(p304 == 1) {
			boolean _del = detalle.ivh307!=null || detalle.ivh308!=null || detalle.ivh309!=null;
			if(App.getInstance().getCap200().ih215 != null && _del){
				return 1;
			} else if(App.getInstance().getCap200().ih215 == null && (_del || detalle.ivh310!=null)){
				return 3;
			} 
		} else if(p304 == 2) { 
			boolean _del2 = detalle.ivh308!=null || detalle.ivh309!=null;
			if(App.getInstance().getCap200().ih215 != null && _del2){
				return 2;
			} else if(App.getInstance().getCap200().ih215 == null && (_del2 || detalle.ivh310!=null)){
				return 4;
			} 
		} 
		return 0;
	}
	
	private String getMsg(int cod){
		switch (cod) {
			case 1: return "Tiene Informacion en P307 o P308 o P309, y al cambiar P304 = 1 siendo P215 > 0; "
					+ "Los datos en estas preguntas seran eliminados. Esta seguro que desea continuar?";
			case 2: return "Tiene Informacion en P308 o P309, y al cambiar P304 = 2 siendo P215 > 0; "
					+ "Los datos en estas preguntas seran eliminados. Esta seguro que desea continuar?";
			case 3: return "Tiene Informacion en P307 o P308 o P309 o P310, y al cambiar P304 = 1 siendo P215 vacio; "
					+ "Los datos en estas preguntas seran eliminados. Esta seguro que desea continuar?";
			case 4: return "Tiene Informacion en P308 o P309 o P310, y al cambiar P304 = 2 siendo P215 vacio; "
					+ "Los datos en estas preguntas seran eliminados. Esta seguro que desea continuar?";
			default: return "";
		}
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccept() {
		Integer cod = (Integer)dc.get("clave");
		if(cod.equals(1)){
			detalle.ivh307 = null; detalle.ivh308 = null; detalle.ivh309 = null; detalle.ivh308_o = null;
			savePag(cod);
		} else if(cod.equals(2)){
			detalle.ivh308 = null; detalle.ivh309 = null; detalle.ivh308_o = null;
			savePag(cod);
		} else if(cod.equals(3)){
			detalle.ivh307 = null; detalle.ivh308 = null; detalle.ivh309 = null; detalle.ivh308_o = null;
			detalle.ivh310 = null; detalle.ivh310_o = null;
			savePag(cod);
		} else if(cod.equals(4)){
			detalle.ivh308 = null; detalle.ivh309 = null; detalle.ivh308_o = null; detalle.ivh310 = null; 
			detalle.ivh310_o = null;
			savePag(cod);
		}
	}
	
	private void savePag(Integer cod){
		String [] saveExtras = new String[]{"NRO_MREG","NRO_VFREG","ORDEN_300"};
		switch (cod) {
			case 1: saveExtras = new String[]{"NRO_MREG","NRO_VFREG","ORDEN_300","IVH307","IVH308",
					"IVH308_O","IVH309"}; break;
			case 2: saveExtras = new String[]{"NRO_MREG","NRO_VFREG","ORDEN_300","IVH308",
					"IVH308_O","IVH309"}; break;
			case 3: saveExtras = new String[]{"NRO_MREG","NRO_VFREG","ORDEN_300","IVH307","IVH308",
					"IVH308_O","IVH309","IVH310","IVH310_O"}; break;
			case 4: saveExtras = new String[]{"NRO_MREG","NRO_VFREG","ORDEN_300","IVH308",
					"IVH308_O","IVH309","IVH310","IVH310_O"}; break;
			default:break;
		}
		
		uiToEntity(detalle);
		try {
			if(!mimarcoService.saveOrUpdate(detalle, detalle.getSecCap(caller.getListFields(this, vEstado(saveExtras))))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			} else {
				caller.reloadData(detalle, 1);
				
				FragmentManager fm = caller.getFragmentManager();
				if((txtIVH301A.getText().toString().trim().equals(App.ESTADO) && flgEstado)
						|| (txtIVH301A.getText().toString().trim().equals(App.SOCIEDAD) && flgSociedad)){
					C3CAP300_Fragment_301_Obs aperturaDialog = C3CAP300_Fragment_301_Obs.
							newInstance(caller, detalle, mimarcoService);
					aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
					aperturaDialog.show(fm, "aperturaDialog");
				} else {
					C3CAP300_Fragment_301_02 aperturaDialog = C3CAP300_Fragment_301_02.
							newInstance(caller, detalle, mimarcoService);
					aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
					aperturaDialog.show(fm, "aperturaDialog");
				}
				C3CAP300_Fragment_301_01.this.dismiss();
			}
		} catch (SQLException e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
		}
		
//		uiToEntity(detalle);
//		if(!mimarcoService.saveCap300Delitos(detalle, Utilidades.getListFields(this, saveExtras))){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
//		else {
//			caller.reloadData(detalle, 1);
//			
//			C3CAP300_Fragment_301_01.this.dismiss();
//			
//			FragmentManager fm = caller.getFragmentManager();
//			C3CAP300_Fragment_301_02 aperturaDialog = C3CAP300_Fragment_301_02.
//					newInstance(caller, detalle, mimarcoService);
//			aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
//			aperturaDialog.show(fm, "aperturaDialog");
//		}
	}
	
	private String[] vEstado(String[] extras){
		if((App.ESTADO.equals(txtIVH301A.getText().toString().trim()) && flgEstado)
				|| (App.SOCIEDAD.equals(txtIVH301A.getText().toString().trim()) && flgSociedad)) {
			detalle.ivh305 = 99;
			detalle.ivh306_dia = "99";
			detalle.ivh306_mes = "99";
			detalle.ivh306_anio = "9999";
			detalle.ivh307 = 8;
			detalle.ivh308 = 17;
			detalle.ivh308_o = null;
			detalle.ivh309 = 7;
			detalle.ivh310 = 10;
			detalle.ivh310_o = null;
			return new String[]{"NRO_MREG","NRO_VFREG","ORDEN_300","IVH305","IVH306_DIA","IVH306_MES","IVH306_ANIO",
					"IVH307","IVH308","IVH308_O","IVH309","IVH310","IVH310_O"};
		}
		return extras;
	}
}
