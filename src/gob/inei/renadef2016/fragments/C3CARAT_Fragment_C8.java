package gob.inei.renadef2016.fragments;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.CheckBoxField;
import gob.inei.dnce.components.DateTimeField;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.RadioGroupOtherField.ORIENTATION;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3CARAT_Fragment_C8 extends FragmentForm {

	@FieldAnnotation(orderIndex = 1)
	public RadioGroupOtherField rgVIII_A;
	@FieldAnnotation(orderIndex = 2)
	public DateTimeField txtVIII_DATE;
	public IntegerField txtVIII_B_DIA;
	public IntegerField txtVIII_B_MES;
	public IntegerField txtVIII_B_ANIO;
	@FieldAnnotation(orderIndex = 3)
	public CheckBoxField chbVIII_B;
	
	private LabelComponent lblTitulo, lblTitulo1;
	private INF_Caratula01Service caratulaService;
	private INF_Caratula01 caratula;

	public C3CARAT_Fragment_C8() {
	}

	public C3CARAT_Fragment_C8 parent(MasterActivity parent) {
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
				.text(R.string.app_fullname).textSize(21).centrar()
				.negrita();
		lblTitulo1 = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_VIII_SECCION_IDP).textSize(21).centrar()
				.negrita();
		
		txtVIII_B_DIA = new IntegerField(getActivity());
		txtVIII_B_MES = new IntegerField(getActivity());
		txtVIII_B_ANIO = new IntegerField(getActivity());
		
		rgVIII_A = new RadioGroupOtherField(getActivity(), R.string.lb_c3_cap200_p212_1, 
				R.string.lb_c3_cap200_p212_2).orientation(ORIENTATION.HORIZONTAL).
				size(altoComponente, 600).callback("onPVIII_A_ChangeValue");
		
		txtVIII_DATE = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).size(altoComponente, 200).
				dateOrhour(txtVIII_B_DIA, txtVIII_B_MES, txtVIII_B_ANIO).buttons("Aceptar", "Omision").callback("on_ChangeVIII")
				.setRangoYear(1900, 2017);
		txtVIII_DATE.setFormParent(this);
		
		chbVIII_B = new CheckBoxField(getActivity(), R.string.lb_C_VIII_2_1_IDP, "1:0").size(WRAP_CONTENT, WRAP_CONTENT).
				callback("onPVIII_A_ChangeValue");
	}
	
	public void onPVIII_A_ChangeValue(FieldComponent component){
		if(component.equals(rgVIII_A)){
			RadioGroupOtherField rg = ((RadioGroupOtherField)component);
			if(rg.isPressed()){
				lockP1(rg, txtVIII_DATE, null, txtVIII_DATE, chbVIII_B);
			}
		} else if(component.equals(chbVIII_B)){
			CheckBoxField chb = (CheckBoxField)component;
			if(chb.isPressed()){
				lockP2(chb, txtVIII_DATE, null, txtVIII_DATE);
			}
		}
	}
	
	public void lockP1(RadioGroupOtherField rg, View focusSi, View focusNo, View...views){
		Integer _val = rg.getTagSelectedInteger(-1);
		if(_val.equals(1)){	
			lockView(false, views);
			if(focusSi!=null) focusSi.requestFocus();
		} else if(_val.equals(2)){
			cleanAndLockView(views);
			if(focusNo!=null) focusNo.requestFocus();
		}	
	}
	
	public void lockP2(CheckBoxField chb, View focusSi, View focusNo, View...views){
		if(chb.isChecked()){
			cleanAndLockView(views);
			if(focusNo!=null) focusNo.requestFocus();
		} else {
			lockView(false, views);
			if(focusSi!=null) focusSi.requestFocus();
		}	
	}
	
	public void on_ChangeVIII(FieldComponent component){
		if(component.getValue() != null){
			if(((DateTimeField)component).equals(txtVIII_DATE)){
				cleanAndLockView(chbVIII_B);
			}
		} else {
			if(((DateTimeField)component).equals(txtVIII_DATE)){
				lockView(false, chbVIII_B);
				chbVIII_B.requestFocus();
			}
		}
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q5 = createQuestionSection(lblTitulo1);
		LinearLayout q6 = createQuestionSection(R.string.lb_C_VIII_1_IDP, rgVIII_A);
		LinearLayout q7 = createQuestionSection(R.string.lb_C_VIII_2_IDP, Gravity.CENTER, LinearLayout.VERTICAL, txtVIII_DATE, chbVIII_B);

		// ///////////////////////////
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
		form.addView(q5);
		form.addView(q6);
		form.addView(q7);
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
		
		return true;
	}

	private boolean validar() {
		error = false;

		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
		
		if(Util.esVacio(rgVIII_A)){
			mensaje="Preg 1 no debe estar vacia.";
			view=rgVIII_A;
			error=true;
			return false;	
		} else if(rgVIII_A.isTagSelected(1)){
			if(Util.esVacio(txtVIII_DATE) && !chbVIII_B.isChecked()){
				mensaje="Preg 2 no debe estar vacia.";
				view=txtVIII_DATE;
				error=true;
				return false;	
			}
		}


		if(error) return false;		
		return true;
	}

	@Override
	public void cargarDatos() {
		caratula = App.getInstance().getComisaria();
		entityToUI(caratula);
		inicio();
	}

	private void inicio() {		
		lockP1(rgVIII_A, null, null, txtVIII_DATE, chbVIII_B);
		if(rgVIII_A.isTagSelected(1)){
			lockP2(chbVIII_B, null, null, txtVIII_DATE);
			if(!Util.esVacio(txtVIII_DATE)){
				cleanAndLockView(chbVIII_B);
			}
		}
		rgVIII_A.requestFocus();
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
