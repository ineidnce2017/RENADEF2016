package gob.inei.renadef2016.fragments;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.RadioGroupOtherField.ORIENTATION;
import gob.inei.dnce.components.SpinnerField;
import gob.inei.dnce.components.TextBoxField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.service.INF_Caratula01Service;

import java.util.Arrays;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3CARAT_Fragment_C7 extends FragmentForm {

	@FieldAnnotation(orderIndex = 1)
	public TextField txtVII3_1A;
	@FieldAnnotation(orderIndex = 2)
	public SpinnerField spnVII3_1B;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtVII3_1C;
	@FieldAnnotation(orderIndex = 4)
	public RadioGroupOtherField rgVII3_1D_NT;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtVII3_1D;
	@FieldAnnotation(orderIndex = 6)
	public RadioGroupOtherField rgVII3_1E_NT;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtVII3_1E;
	@FieldAnnotation(orderIndex = 8)
	public RadioGroupOtherField rgVII3_1F_NT;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtVII3_1F;
	
	@FieldAnnotation(orderIndex = 10)
	public TextField txtVII3_2A;
	@FieldAnnotation(orderIndex = 11)
	public SpinnerField spnVII3_2B;
	@FieldAnnotation(orderIndex = 12)
	public IntegerField txtVII3_2C;
	@FieldAnnotation(orderIndex = 13)
	public RadioGroupOtherField rgVII3_2D_NT;
	@FieldAnnotation(orderIndex = 14)
	public IntegerField txtVII3_2D;
	@FieldAnnotation(orderIndex = 15)
	public RadioGroupOtherField rgVII3_2E_NT;
	@FieldAnnotation(orderIndex = 16)
	public IntegerField txtVII3_2E;
	@FieldAnnotation(orderIndex = 17)
	public RadioGroupOtherField rgVII3_2F_NT;
	@FieldAnnotation(orderIndex = 18)
	public IntegerField txtVII3_2F;
	
	
	private LabelComponent lblTitulo;
	private INF_Caratula01Service caratulaService;
	private INF_Caratula01 caratula;
	private GridComponent2 grid_Com, grid_TelC, grid_Fac, grid_TelF;
	private boolean check;

	public C3CARAT_Fragment_C7() {
	}

	public C3CARAT_Fragment_C7 parent(MasterActivity parent) {
		this.parent = parent;
		return this;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = createUI();
		setHasOptionsMenu(true);
		initObjectsWithoutXML(this, rootView);
		enlazarCajas();
		listening();
	
		return rootView;
	}

	@Override
	protected void buildFields() {
		
		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_VII_SECCION_FDP).textSize(21).centrar()
				.negrita();

		//para el comisario
		LabelComponent lblTC = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_2_FDP).size(50, 160).centrar();
		LabelComponent lblCom = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_6_FDP).size(50, 160).centrar();
		LabelComponent lblGrC = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_3_FDP).size(50, 160).centrar();
		LabelComponent lblNCIP = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_9_FDP).size(50, 140).centrar();
		
		txtVII3_1A = new TextField(getActivity()).size(altoComponente, 415);
		txtVII3_1C = new IntegerField(getActivity(), false).size(altoComponente, 140);
		spnVII3_1B = new SpinnerField(getActivity()).size(
				altoComponente+15, 210).callback("on_GradoChangeValue");
		cargarSpinner(spnVII3_1B);
		
		grid_Com = new GridComponent2(getActivity(), 3);
		grid_Com.addComponent(lblTC,3);
		grid_Com.addComponent(lblCom);
		grid_Com.addComponent(lblGrC);
		grid_Com.addComponent(lblNCIP);
		grid_Com.addComponent(txtVII3_1A);
		grid_Com.addComponent(spnVII3_1B);
		grid_Com.addComponent(txtVII3_1C);
		
		LabelComponent lblNT = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_5_FDP).size(altoComponente, 280).centrar();
		LabelComponent lblNTI = new LabelComponent(getActivity()).
				text(R.string.lb_C_VII_10_FDP).size(altoComponente, 133);
		LabelComponent lblNTP = new LabelComponent(getActivity()).
				text(R.string.lb_C_VII_11_FDP).size(altoComponente, 133);
		LabelComponent lblNTF = new LabelComponent(getActivity()).
				text(R.string.lb_C_VII_12_FDP).size(altoComponente, 133);
		
		txtVII3_1D = new IntegerField(getActivity(),false).size(altoComponente, 190);
		txtVII3_1E = new IntegerField(getActivity(),false).size(altoComponente, 190);
		txtVII3_1F = new IntegerField(getActivity(),false).size(altoComponente, 190);
		
		rgVII3_1D_NT = new RadioGroupOtherField(getActivity(), R.string.lb_C_VII_12_FDP_T, 
				R.string.lb_C_VII_12_FDP_NT, R.string.lb_C_VII_12_FDP_SR).orientation(ORIENTATION.HORIZONTAL).
				size(altoComponente+10, 435).callback("onPVII_ChangeValue");
		rgVII3_1E_NT = new RadioGroupOtherField(getActivity(), R.string.lb_C_VII_12_FDP_T,
				R.string.lb_C_VII_12_FDP_NT, R.string.lb_C_VII_12_FDP_SR).orientation(ORIENTATION.HORIZONTAL).
				size(altoComponente+10, 435).callback("onPVII_ChangeValue");
		rgVII3_1F_NT = new RadioGroupOtherField(getActivity(), R.string.lb_C_VII_12_FDP_T,
				R.string.lb_C_VII_12_FDP_NT, R.string.lb_C_VII_12_FDP_SR).orientation(ORIENTATION.HORIZONTAL).
				size(altoComponente+10, 435).callback("onPVII_ChangeValue");
		
		grid_TelC = new GridComponent2(getActivity(), 2);
		grid_TelC.addComponent(2, lblNT);
		LinearLayout ly = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER,lblNTI, txtVII3_1D);
		ly.setLayoutParams(new LayoutParams(Util.getTamañoEscalado(getActivity(), 335), LayoutParams.MATCH_PARENT));
		grid_TelC.addComponent(ly);
		grid_TelC.addComponent(rgVII3_1D_NT);
		LinearLayout ly1 = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER,lblNTP, txtVII3_1E);
		ly1.setLayoutParams(new LayoutParams(Util.getTamañoEscalado(getActivity(), 335), LayoutParams.MATCH_PARENT));
		grid_TelC.addComponent(ly1);
		grid_TelC.addComponent(rgVII3_1E_NT);
		LinearLayout ly2 = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER,lblNTF, txtVII3_1F);
		ly2.setLayoutParams(new LayoutParams(Util.getTamañoEscalado(getActivity(), 335), LayoutParams.MATCH_PARENT));
		grid_TelC.addComponent(ly2);
		grid_TelC.addComponent(rgVII3_1F_NT);
		
		//para el facilitador
		LabelComponent lblTf = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_2_FDP).size(50, 160).centrar();
		LabelComponent lblFac = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_100_SecIII_2).size(50, 160).centrar();
		LabelComponent lblGrF = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_3_FDP).size(50, 160).centrar();
		LabelComponent lblNCIPF = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_9_FDP).size(50, 140).centrar();
		
		txtVII3_2A = new TextField(getActivity()).size(altoComponente, 415);
		txtVII3_2C = new IntegerField(getActivity(), false).size(altoComponente, 140);
		spnVII3_2B = new SpinnerField(getActivity()).size(
				altoComponente+15, 210).callback("on_GradoChangeValue");
		cargarSpinner(spnVII3_2B);
		
		grid_Fac = new GridComponent2(getActivity(), 3);
		grid_Fac.addComponent(lblTf, 3);
		grid_Fac.addComponent(lblFac);
		grid_Fac.addComponent(lblGrF);
		grid_Fac.addComponent(lblNCIPF);
		grid_Fac.addComponent(txtVII3_2A);
		grid_Fac.addComponent(spnVII3_2B);
		grid_Fac.addComponent(txtVII3_2C);
		
		LabelComponent lblNTFFF = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_5_FDP).size(altoComponente, 280).centrar();
		LabelComponent lblNTIF = new LabelComponent(getActivity()).
				text(R.string.lb_C_VII_10_FDP).size(altoComponente, 133);
		LabelComponent lblNTPF = new LabelComponent(getActivity()).
				text(R.string.lb_C_VII_11_FDP).size(altoComponente, 133);
		LabelComponent lblNTFF = new LabelComponent(getActivity()).
				text(R.string.lb_C_VII_12_FDP).size(altoComponente, 133);
		
		txtVII3_2D = new IntegerField(getActivity(),false).size(altoComponente, 190);
		txtVII3_2E = new IntegerField(getActivity(),false).size(altoComponente, 190);
		txtVII3_2F = new IntegerField(getActivity(),false).size(altoComponente, 190);
		
		rgVII3_2D_NT = new RadioGroupOtherField(getActivity(), R.string.lb_C_VII_12_FDP_T, 
				R.string.lb_C_VII_12_FDP_NT, R.string.lb_C_VII_12_FDP_SR).orientation(ORIENTATION.HORIZONTAL).
				size(altoComponente+10, 435).callback("onPVII_ChangeValue");
		rgVII3_2E_NT = new RadioGroupOtherField(getActivity(), R.string.lb_C_VII_12_FDP_T,
				R.string.lb_C_VII_12_FDP_NT, R.string.lb_C_VII_12_FDP_SR).orientation(ORIENTATION.HORIZONTAL).
				size(altoComponente+10, 435).callback("onPVII_ChangeValue");
		rgVII3_2F_NT = new RadioGroupOtherField(getActivity(), R.string.lb_C_VII_12_FDP_T,
				R.string.lb_C_VII_12_FDP_NT, R.string.lb_C_VII_12_FDP_SR).orientation(ORIENTATION.HORIZONTAL).
				size(altoComponente+10, 435).callback("onPVII_ChangeValue");
		
		grid_TelF = new GridComponent2(getActivity(), 2);
		grid_TelF.addComponent(2, lblNTFFF);
		LinearLayout ly3 = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER,lblNTIF, txtVII3_2D);
		ly3.setLayoutParams(new LayoutParams(Util.getTamañoEscalado(getActivity(), 335), LayoutParams.MATCH_PARENT));
		grid_TelF.addComponent(ly3);
		grid_TelF.addComponent(rgVII3_2D_NT);
		LinearLayout ly4 = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER,lblNTPF, txtVII3_2E);
		ly4.setLayoutParams(new LayoutParams(Util.getTamañoEscalado(getActivity(), 335), LayoutParams.MATCH_PARENT));
		grid_TelF.addComponent(ly4);
		grid_TelF.addComponent(rgVII3_2E_NT);
		LinearLayout ly5 = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER,lblNTFF, txtVII3_2F);
		ly5.setLayoutParams(new LayoutParams(Util.getTamañoEscalado(getActivity(), 335), LayoutParams.MATCH_PARENT));
		grid_TelF.addComponent(ly5);
		grid_TelF.addComponent(rgVII3_2F_NT);
		
		//Filtros Agregados
		/*comisario*/
		Filtros.setFiltro(txtVII3_1A, Filtros.TIPO.ALFA_U,50,null);
		Filtros.setFiltro(txtVII3_1C, Filtros.TIPO.NUMBER,8,0,null,1,99999998);
		Filtros.setFiltro(txtVII3_1D, Filtros.TIPO.NUMBER, 12, new char[]{'*', '#', '/','-'});
		Filtros.setFiltro(txtVII3_1E, Filtros.TIPO.NUMBER, 12, new char[]{'*', '#', '/','-'});
		Filtros.setFiltro(txtVII3_1F, Filtros.TIPO.NUMBER, 12, new char[]{'*', '#', '/','-'});
		
//		txtVII3_2D.addTextChangedListener(new TextWatcherBlock(txtVII3_2D, rgVII3_2D_NT)); 
//		txtVII3_2E.addTextChangedListener(new TextWatcherBlock(txtVII3_2E, rgVII3_2E_NT));
//		txtVII3_2F.addTextChangedListener(new TextWatcherBlock(txtVII3_2F, rgVII3_2F_NT));
		
		/* FACILITADOR*/
		Filtros.setFiltro(txtVII3_2A, Filtros.TIPO.ALFA_U,50,null);
		Filtros.setFiltro(txtVII3_2C, Filtros.TIPO.NUMBER,8,0,null,1,99999998);
		Filtros.setFiltro(txtVII3_2D, Filtros.TIPO.NUMBER, 12, new char[]{'*', '#', '/','-'});
		Filtros.setFiltro(txtVII3_2E, Filtros.TIPO.NUMBER, 12, new char[]{'*', '#', '/','-'});
		Filtros.setFiltro(txtVII3_2F, Filtros.TIPO.NUMBER, 12, new char[]{'*', '#', '/','-'});
		
	}
	
	private void cargarSpinner(SpinnerField spn) {
		Object[] keys = new Object[]{null, 1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		String []items = getActivity().getResources().getStringArray(R.array.INF103);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity(), R.layout.spinner_item2, items);
//		spnVII3_1B.setAdapterWithKey(adapter, Arrays.asList(keys));
		
		spn.setAdapterWithKey(adapter, Arrays.asList(keys));
		
	}
	
	public void on_GradoChangeValue(FieldComponent component){
		if(component.getValue() == null) return;
		if(((SpinnerField)component).isFocused()){
			Integer opc = Integer.valueOf(((SpinnerField)component).getSelectedItemKey().toString());
			if(((SpinnerField)component).equals(spnVII3_1B)){
				txtVII3_1C.requestFocus();
				changeProperties(txtVII3_1C, opc);
			} else {
				txtVII3_2C.requestFocus();
				changeProperties(txtVII3_2C, opc);
			}
		}
	}
	
	private void changeProperties(TextBoxField txt, Integer result){
		InputFilter[] ifs =  txt.getFilters();
		if(ifs[0] instanceof Filtros){
			if(result <= 6){
				((Filtros)ifs[0]).setProperties(6,1,999999,6,1,6,6);
    		} else {
    			((Filtros)ifs[0]).setProperties(8,1,99999999,8,1,8,8);
    		}
		}
    }
	
	public void onPVII_ChangeValue(FieldComponent component){
		// Institucional - Comisario
		if(rgVII3_1D_NT.equals(component)){
			if(rgVII3_1D_NT.isTagSelectedBetween(2, 3)){
				cleanAndLockView(txtVII3_1D);
				rgVII3_1E_NT.requestFocus();		
			}
			else {
				lockView(false,txtVII3_1D);
				txtVII3_1D.requestFocus();
			}
		}
		// Personal- Facilitador
		else if(rgVII3_1E_NT.equals(component)){
			if(rgVII3_1E_NT.isTagSelectedBetween(2, 3)){
				cleanAndLockView(txtVII3_1E);
				rgVII3_1F_NT.requestFocus();		
			}
			else {
				lockView(false,txtVII3_1E);
				txtVII3_1E.requestFocus();
			}
		}
		 //Fijo - Facilitador
		else if(rgVII3_1F_NT.equals(component)){
			if(rgVII3_1F_NT.isTagSelectedBetween(2, 3)){
				cleanAndLockView(txtVII3_1F);
				txtVII3_2A.requestFocus();	
			}	
			else {
				lockView(false,txtVII3_1F);	
				txtVII3_1F.requestFocus();
			}
		}
		
		// SE HIZO CON TEXTCHANGELISTENER
		// Insitucional Facilitador

		if(rgVII3_2D_NT.equals(component)){
			if(rgVII3_2D_NT.isTagSelectedBetween(2, 3)){
				cleanAndLockView(txtVII3_2D);
				rgVII3_2E_NT.requestFocus();		
			}
			else {
				lockView(false,txtVII3_2D);
				txtVII3_2D.requestFocus();
			}
		}
		// Personal- Facilitador
		else if(rgVII3_2E_NT.equals(component)){
			if(rgVII3_2E_NT.isTagSelectedBetween(2, 3)){
				cleanAndLockView(txtVII3_2E);
				rgVII3_2F_NT.requestFocus();		
			}
			else {
				lockView(false,txtVII3_2E);
				txtVII3_2E.requestFocus();
			}
		}
		 //Fijo - Facilitador
		else if(rgVII3_2F_NT.equals(component)){
			if(rgVII3_2F_NT.isTagSelectedBetween(2, 3)){
				cleanAndLockView(txtVII3_2F);
			}	
			else {
				lockView(false,txtVII3_2F);	
				txtVII3_2F.requestFocus();
			}
		}
						
	}

	private LinearLayout createLayout(View v1, View v2){
		return createLy(LinearLayout.VERTICAL, Gravity.CENTER, v1, v2);
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo);
//		q1 = createQuestionSection(0,Gravity.CENTER,LinearLayout.HORIZONTAL,lblCom,txtVII3_1A);
		LinearLayout q2 = createQuestionSection(createLayout(grid_Com.component(), grid_TelC.component()));
//		q3 = createQuestionSection(0,Gravity.CENTER,LinearLayout.HORIZONTAL,lblFac,txtVII3_2A);
		LinearLayout q4 = createQuestionSection(createLayout(grid_Fac.component(), grid_TelF.component()));

		// ///////////////////////////
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
//		form.addView(q1);
		form.addView(q2);
//		form.addView(q3);
		form.addView(q4);
		/* Aca agregamos las preguntas a la pantalla */
		return contenedor;
	}

	@Override
	public boolean grabar() {
		if (!validar()) {
			if (error) {
				if (!mensaje.equals(""))
					ToastMessage.msgBox(this.getActivity(), mensaje,
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
				if (view != null)
					view.requestFocus();
			}
			return false;
		}
		
		uiToEntity(caratula);
		try {
			if(!getCaratulaService().saveOrUpdate(caratula, caratula.getSecCap(getListFields(this)))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			}
		} catch (Exception e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			return false;
		}
		
//		uiToEntity(caratula);
//		if(!getCaratulaService().grabarCaratula(caratula, 1, Utilidades.getListFields(this))){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
		
		return true;
	}

	private boolean validar() {
		error = false;
		check = false;

		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
		
       /* COMISARIO */
		
		if(Util.esVacio(txtVII3_1A)){
			mensaje="Debe ingresar el nombre del Comisario";
			error=true;
			view=txtVII3_1A;
			return false;
			}
		if(spnVII3_1B.getSelectedItemPosition()==0){
			mensaje="Seleccione un GRADO del Comisario";
			view=spnVII3_1B;
			error=true;
			return false;
		}
		if(spnVII3_1B.getSelectedItemPosition()!=0 && Util.esVacio(txtVII3_1C)){
			mensaje="Ingresar N° de CIP correspondiente";
			view=txtVII3_1C;
			error=true;
			return false;
		}
		else if((Integer.valueOf(spnVII3_1B.getSelectedItemKey().toString()).compareTo(6)<=0 &&
				txtVII3_1C.getText().toString().trim().length() !=6)){
			mensaje="El campo debe contener exactamente 6 d\u00edgitos.";
			error=true;
			view=txtVII3_1C;
			return false;
		}
		else if(Integer.valueOf(spnVII3_1B.getSelectedItemKey().toString()).compareTo(6)>0 &&
						txtVII3_1C.getText().toString().trim().length() !=8){
			mensaje="El campo debe contener exactamente 8 d\u00edgitos.";
			error=true;
			view=txtVII3_1C;
			return false;
		}
		
		if(!validarFono(rgVII3_1D_NT, txtVII3_1D, "Tel\u00e9fono Institucional")) return false;
//		if(Util.esVacio(rgVII3_1D_NT)){
//			mensaje="Tel\u00e9fono Institucional no puede estar vacio.";
//			view = rgVII3_1D_NT;
//			error = true;
//			return false;
//		}
//		Integer rg1d = rgVII3_1D_NT.getTagSelectedInteger(-1);
//		if(rg1d == 1) {
//			if(Util.esVacio(txtVII3_1D)){
//				mensaje="Tel\u00e9fono institucional no puede estar vacio.";
//				view = txtVII3_1D;
//				error = true;
//				return false;	
//			}
//			String txt = txtVII3_1D.getText().toString();
//			if(txt.length() < 6 || sumDigits(txt) == 0 || contDigits(txt) < 6){
//				mensaje="Verificar informaci\u00f3n en el tel\u00e9fono institucional.";
//				view = txtVII3_1D;
//				error = true;
//				return false;	
//			}
//		} 
		
		if(!validarFono(rgVII3_1E_NT, txtVII3_1E, "Tel\u00e9fono Personal")) return false;
//		if(Util.esVacio(rgVII3_1E_NT)){
//			mensaje="Tel\u00e9fono Personal no puede estar vacio.";
//			view = rgVII3_1E_NT;
//			error = true;
//			return false;
//		}
//		Integer rg1e = rgVII3_1E_NT.getTagSelectedInteger(-1);
//		if(rg1e == 1){
//			if(Util.esVacio(txtVII3_1E)){
//				mensaje="Tel\u00e9fono personal no puede estar vacio.";
//				view=txtVII3_1E;
//				error = true;
//				return false;
//			}
//			String txt = txtVII3_1E.getText().toString();
//			if(txt.length() < 6 || sumDigits(txt) == 0 || contDigits(txt) < 6){
//				mensaje="Verificar informaci\u00f3n en el tel\u00e9fono personal.";
//				view = txtVII3_1E;
//				error = true;
//				return false;	
//			}
//		} 
		
		if(!validarFono(rgVII3_1F_NT, txtVII3_1F, "Tel\u00e9fono Fijo")) return false;
//		if(Util.esVacio(rgVII3_1F_NT)){
//			mensaje="Tel\u00e9fono Fijo no puede estar vacio.";
//			view = rgVII3_1F_NT;
//			error = true;
//			return false;
//		}
//		else if(rgVII3_1F_NT.isTagSelected(1) && Util.esVacio(txtVII3_1F)){
//			mensaje="Verificar informaci\u00f3n en el tel\u00e9fono fijo.";
//			view=txtVII3_1F;
//			error=true;
//			return false;
//		} else if(rgVII3_1F_NT.isTagSelected(1)){
//			String txt = txtVII3_1F.getText().toString();
//			if(txt.length() < 6 || sumDigits(txt) == 0 || contDigits(txt) < 6){
//				mensaje="Verificar informaci\u00f3n en el tel\u00e9fono fijo.";
//				view = txtVII3_1F;
//				error = true;
//				return false;	
//			}
//		}
		
		
	/* FACILITADOR */
		
		if(Util.esVacio(txtVII3_2A)){
			mensaje="Debe ingresar el nombre del Facilitador";
			error=true;
			view=txtVII3_2A;
			return false;
			}
		if(spnVII3_2B.getSelectedItemPosition()==0){
			mensaje="Seleccione un GRADO del Facilitador";
			view=spnVII3_2B;
			error=true;
			return false;
		}
		if(spnVII3_2B.getSelectedItemPosition()!=0 && Util.esVacio(txtVII3_2C)){
			mensaje="Ingresar N° de CIP correspondiente";
			view=txtVII3_2C;
			error=true;
			return false;
		}
		else if(Integer.valueOf(spnVII3_2B.getSelectedItemKey().toString()).compareTo(6)>0 &&
						txtVII3_2C.getText().toString().trim().length() !=8){
			mensaje="El campo debe contener exactamente 8 d\u00edgitos.";
			error=true;
			view=txtVII3_2C;
			return false;
		}
		else if(Integer.valueOf(spnVII3_2B.getSelectedItemKey().toString()).compareTo(6)<=0 &&
				txtVII3_2C.getText().toString().trim().length() !=6){
			mensaje="El campo debe contener exactamente 6 d\u00edgitos.";
			error=true;
			view=txtVII3_2C;
			return false;
		}
		
		if(!validarFono(rgVII3_2D_NT, txtVII3_2D, "Tel\u00e9fono Institucional")) return false;
//		if(Util.esVacio(rgVII3_2D_NT)){
//			mensaje="Tel\u00e9fono Institucional no puede estar vacio.";
//			view = rgVII3_2D_NT;
//			error = true;
//			return false;
//		}
//		else if(rgVII3_2D_NT.isTagSelected(1) && Util.esVacio(txtVII3_2D)){
//			mensaje="Verificar informaci\u00f3n en el tel\u00e9fono institucional.";
//			view = txtVII3_2D;
//			error = true;
//			return false;			
//		} else if(rgVII3_2D_NT.isTagSelected(1)){
//			String txt = txtVII3_2D.getText().toString();
//			if(txt.length() < 6 || sumDigits(txt) == 0 || contDigits(txt) < 6){
//				mensaje="Verificar informaci\u00f3n en el tel\u00e9fono institucional.";
//				view = txtVII3_2D;
//				error = true;
//				return false;	
//			}
//		}
		
		if(!validarFono(rgVII3_2E_NT, txtVII3_2E, "Tel\u00e9fono Personal")) return false;
//		if(Util.esVacio(rgVII3_2E_NT)){
//			mensaje="Tel\u00e9fono Personal no puede estar vacio.";
//			view = rgVII3_2E_NT;
//			error = true;
//			return false;
//		}
//		else if(rgVII3_2E_NT.isTagSelected(1) && Util.esVacio(txtVII3_2E)){
//			mensaje="Verificar informaci\u00f3n en el tel\u00e9fono personal.";
//			view=txtVII3_2E;
//			error = true;
//			return false;
//		} else if(rgVII3_2E_NT.isTagSelected(1)){
//			String txt = txtVII3_2E.getText().toString();
//			if(txt.length() < 6 || sumDigits(txt) == 0 || contDigits(txt) < 6){
//				mensaje="Verificar informaci\u00f3n en el tel\u00e9fono personal.";
//				view = txtVII3_2E;
//				error = true;
//				return false;	
//			}
//		}
		
		if(!validarFono(rgVII3_2F_NT, txtVII3_2F, "Tel\u00e9fono Fijo")) return false;
//		if(Util.esVacio(rgVII3_2F_NT)){
//			mensaje="Tel\u00e9fono Fijo no puede estar vacio.";
//			view = rgVII3_2F_NT;
//			error = true;
//			return false;
//		}
//		else if(rgVII3_2F_NT.isTagSelected(1) && Util.esVacio(txtVII3_2F)){
//			mensaje="Verificar informaci\u00f3n en el tel\u00e9fono fijo.";
//			view=txtVII3_2F;
//			error=true;
//			return false;
//		} else if(rgVII3_2F_NT.isTagSelected(1)){
//			String txt = txtVII3_2F.getText().toString();
//			if(txt.length() < 6 || sumDigits(txt) == 0 || contDigits(txt) < 6){
//				mensaje="Verificar informaci\u00f3n en el tel\u00e9fono fijo.";
//				view = txtVII3_2F;
//				error = true;
//				return false;	
//			}
//		}

		if(!check) {
			mensaje="Al menos un N\u00B0 de Tel\u00e9fono no debe estar vacio.";
			view = rgVII3_1D_NT;
			error = true;
			return false;	
		}
		
		if(error) return false;		
		return true;
	}
	
	private boolean validarFono(RadioGroupOtherField rg, TextBoxField txt, String text){
		if(Util.esVacio(rg)){
			mensaje="$ no puede estar vacio.".replace("$", text);
			view = rg;
			return !(error = true);
		}
		Integer rg1e = rg.getTagSelectedInteger(-1);
		if(rg1e == 1){
			if(Util.esVacio(txt)){
				mensaje="N\u00B0 de $ no puede estar vacio.".replace("$", text);
				view=txt;
				return !(error = true);
			}
			String texto = txt.getText().toString();
			if(texto.length() < 6 || sumDigits(texto) == 0 || contDigits(texto) < 6){
				mensaje="Verificar informaci\u00f3n en el $.".replace("$", text);
				view = txt;
				return !(error = true);
			}
		} 
		if(rg1e == 1) check = true;
		return true;
	}
	
	private int sumDigits(String txt){
    	int sumtDigits = 0;
		for(char c : txt.toCharArray()){
			if(Character.isDigit(c)) sumtDigits+=Integer.parseInt(""+c);
		}
		return sumtDigits;
    }
	
	private int contDigits(String txt){
    	int conttDigits = 0;
		for(char c : txt.toCharArray()){
			if(Character.isDigit(c)) conttDigits++;
		}
		return conttDigits;
    }

	@Override
	public void cargarDatos() {
		caratula = App.getInstance().getComisaria();
		entityToUI(caratula);
		inicio();
	}

	private void inicio() {		
		onPVII_ChangeValue(rgVII3_1D_NT);
		onPVII_ChangeValue(rgVII3_1E_NT);
		onPVII_ChangeValue(rgVII3_1F_NT);
		
		onPVII_ChangeValue(rgVII3_2D_NT);
		onPVII_ChangeValue(rgVII3_2E_NT);
		onPVII_ChangeValue(rgVII3_2F_NT);
		
		if(caratula.vii3_1b != null) changeProperties(txtVII3_1C, caratula.vii3_1b);
		if(caratula.vii3_2b != null) changeProperties(txtVII3_2C, caratula.vii3_2b);
		
//		Bloqueo para Facilitador
//		if(rgVII3_2D_NT.isTagSelectedBetween(2, 3)){
//			cleanAndLockView(txtVII3_2D);				
//		}
//		
//		if(rgVII3_2E_NT.isTagSelectedBetween(2, 3)){
//			cleanAndLockView(txtVII3_2E);				
//		}
//		
//		if(rgVII3_2F_NT.isTagSelectedBetween(2, 3)){
//			cleanAndLockView(txtVII3_2F);				
//		}
		
		//	Bloqueo para Facilitador
//		if(rgVII3_2D_NT.isTagSelectedBetween(2, 3)){
//			cleanAndLockView(txtVII3_2D);				
//		}
//		
//		if(rgVII3_2E_NT.isTagSelectedBetween(2, 3)){
//			cleanAndLockView(txtVII3_2E);				
//		}
//		
//		if(rgVII3_2F_NT.isTagSelectedBetween(2, 3)){
//			cleanAndLockView(txtVII3_2F);				
//		}
		
		txtVII3_1A.requestFocus();
	}
	
	@Override
	public boolean getSaltoNavegation() {
		return validar();
	}
	
	public INF_Caratula01Service getCaratulaService() {
		if (caratulaService == null) {
			caratulaService = INF_Caratula01Service.getInstance(getActivity());
		}
		return caratulaService;
	}
}
