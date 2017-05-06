package gob.inei.renadef2016.fragments;

import java.sql.SQLException;

import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.TextAreaField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.components.DialogComponent.TIPO_DIALOGO;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3CARAT_Fragment_C3 extends FragmentForm implements Respondible{

	@FieldAnnotation(orderIndex = 1)
	public TextField txtII1;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtII2;
	@FieldAnnotation(orderIndex = 3)
	public TextField txtII3;
	@FieldAnnotation(orderIndex = 4)
	public RadioGroupOtherField rgII4;
	@FieldAnnotation(orderIndex = 5)
	public RadioGroupOtherField rgII5;
	@FieldAnnotation(orderIndex = 6)
	public RadioGroupOtherField rgII6;
	@FieldAnnotation(orderIndex = 7)
	public RadioGroupOtherField rgII7;
	@FieldAnnotation(orderIndex = 8)
	public TextField txtII7_O;
	
	private LabelComponent lblTitulo, lblTitulo1;
	private INF_Caratula01Service caratulaService;
	private MarcoService marcoService;
	private INF_Caratula01 caratula;
	private GridComponent2 grid_A;
	private int sizeWidth = 550, sizeHeigth = MATCH_PARENT;
	private DialogComponent dc;

	public C3CARAT_Fragment_C3() {
	}

	public C3CARAT_Fragment_C3 parent(MasterActivity parent) {
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
				.text(R.string.lb_C_II_IDENTIFICACION).textSize(21).centrar()
				.negrita();
		
		LabelComponent lblRp = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_II1).size(altoComponente+25, 210);
		LabelComponent lblDt = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_II2).size(altoComponente+28, 210);
		LabelComponent lblNd = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_II3).size(altoComponente+27, 210);
		
		txtII1 = new TextField(getActivity(), false).size(altoComponente, 420);
		txtII2 = new TextField(getActivity(), false).size(altoComponente, 400);
		txtII3 = new TextField(getActivity(), false).size(altoComponente, 400);
		txtII7_O = new TextField(getActivity(), false).size(altoComponente, 400);
		
		rgII4 = new RadioGroupOtherField(this.getActivity(), R.string.lb_C_II4_1,
				R.string.lb_C_II4_2).size(118, sizeWidth).centrar(Gravity.LEFT|Gravity.CENTER_VERTICAL).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL).callback("onCheckedChange_P4");
		rgII5 = new RadioGroupOtherField(this.getActivity(), R.string.lb_C_II6_1,
				R.string.lb_C_II6_2, R.string.lb_C_II6_3, R.string.lb_C_II6_4,
				R.string.lb_C_II6_5).size(60, sizeWidth).centrar(Gravity.LEFT|Gravity.CENTER_VERTICAL).
				orientation(RadioGroupOtherField.ORIENTATION.HORIZONTAL).setTagsReplace("A","B","C","D","E");
		rgII6 = new RadioGroupOtherField(this.getActivity(), R.string.lb_C_II7_1,
				R.string.lb_C_II7_2).size(118, sizeWidth).centrar(Gravity.LEFT|Gravity.CENTER_VERTICAL).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL).callback("onCheckedChange_P7");
		rgII7 = new RadioGroupOtherField(this.getActivity(), R.string.lb_C_II9_1,
				R.string.lb_C_II9_2, R.string.lb_C_II9_3).size(162, sizeWidth).
				centrar(Gravity.LEFT|Gravity.CENTER_VERTICAL).
				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
		rgII7.agregarEspecifique(2, txtII7_O);
		
		LabelComponent lblTd = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_II4).size(altoComponente, 210);
		LabelComponent lblTi = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_II6).size(altoComponente, 210);
		LabelComponent lblCa = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_II7).size(altoComponente, 210);
		LabelComponent lblTe = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_II9).size(altoComponente, 210);

		grid_A = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_A.addComponent(lblRp);
		grid_A.addComponent(txtII1);
		grid_A.addComponent(lblDt);
		grid_A.addComponent(txtII2);
		grid_A.addComponent(lblNd);
		grid_A.addComponent(txtII3);
		grid_A.addComponent(lblTd);
		grid_A.addComponent(rgII4);
		grid_A.addComponent(lblTi);
		grid_A.addComponent(rgII5);
		grid_A.addComponent(lblCa);
		grid_A.addComponent(rgII6);
		grid_A.addComponent(lblTe);
		grid_A.addComponent(rgII7);
		
		Filtros.setFiltro(txtII1, Filtros.TIPO.ALFA_U, 100, null);
		Filtros.setFiltro(txtII2, Filtros.TIPO.ALFA_U, 100, null);
		Filtros.setFiltro(txtII3, Filtros.TIPO.ALFAN_U, 100, null);
		Filtros.setFiltro(txtII7_O, Filtros.TIPO.ALFAN_U, 50, null);
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q1 = createQuestionSection(lblTitulo1);
		LinearLayout q2 = createQuestionSection(grid_A.component());

		// ///////////////////////////
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
		form.addView(q1);
		form.addView(q2);

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
			if(rgII4.isTagSelected(2)) {
				Cap100Delitos cap100 = getMarcoService().getC100(App.getInstance().getComisaria(),
						new Cap100Delitos().getSecCap(Util.getListList("FALTAS")));
				if(cap100!= null && cap100.faltas != null){
					eliminarC100(cap100);
					caratula.v3_2 = 0;
					return false;
				}
				caratula.v3_2 = null;
			}
			if(!getCaratulaService().saveOrUpdate(caratula, caratula.getSecCap(getListFields(this, new String[]{"V3_2"})))){
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
	
	private void eliminarC100(Cap100Delitos cap100){
    	dc = new DialogComponent(getActivity(), this,
				TIPO_DIALOGO.YES_NO, getResources()
						.getString(R.string.app_name),
    					"Esta Seguro que desea Eliminar Toda las Faltas registradas en el Cap. 100.\n"
    							+ "Esta Seguro que desea continuar?");
		dc.put("objeto", cap100);
		dc.showDialog();
    }
	
	public void onCheckedChange_P4(FieldComponent component){
		if(component.getValue() == null) return;
		String result = component.getValue().toString();
		if(result.equals("2")){
			cleanAndLockView(rgII5, rgII6);
			lockView(false, rgII7);
			rgII7.requestFocus();
		} else if(result.equals("1")){
			lockView(false, rgII5, rgII6);
			cleanAndLockView(rgII7);
			rgII5.requestFocus();
		}
	}
	
	public void onCheckedChange_P7(FieldComponent component){
		if(component.getValue() == null) return;
		cleanAndLockView(rgII7);
	}

	private boolean validar() {
		error = false;
		if( Util.esVacio(txtII1) ){
			mensaje = "Debe registrar la Regi\u00f3n Policial";
			view = txtII1;
			error = true;
		}
		else if( Util.esVacio(txtII2) ){
			mensaje = "Debe registrar la DIRTEPOL";
			view = txtII2;
			error = true;
		}
		else if( Util.esVacio(txtII3) ){
			mensaje = "Debe registrar el nombre de la Dependencia";
			view = txtII3;
			error = true;
		}
		else if( rgII4.getTagSelected() == null ){
			mensaje = "Debe registrar el tipo de Dependencia Policial";
			view = rgII4;
			error = true;
		}
		else if( rgII4.isTagSelected(1) ){
			if(Util.esVacio(rgII5)){
				mensaje = "Debe registrar Tipo de Comisar\u00eda";
				view = rgII5;
				error = true;
			} else if(Util.esVacio(rgII6)){
				mensaje = "Debe registrar Categor\u00eda de Comisar\u00eda";
				view = rgII6;
				error = true;
			}
		}
		else if( rgII4.isTagSelected(2) ){
			if( rgII7.getTagSelected()==null ){
				mensaje = "Si indica Unidad Especializada debe tener Unidad Especializada";
				view = rgII7;
				error = true;
			}
			else if((App.getInstance().getComisaria().id_n.equals("4045") && !rgII7.isTagSelected(1))
					|| (!App.getInstance().getComisaria().id_n.equals("4045") && rgII7.isTagSelected(1))){
				mensaje = "Solo EXISTE UNA DIRINCRI que esta ubicada en Lima (DIRINCRI ESPA\u00d1A)";
				view = rgII7;
				error = true;
			}
			else if( rgII7.isTagSelected(3) && Util.esVacio(txtII7_O) ){
				mensaje = "La pregunta 7 debe ser especificada";
				view = txtII7_O;
				error = true;
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
		onCheckedChange_P4(rgII4);
		onCheckedChange_P7(rgII6);
		txtII1.requestFocus();
	}
	
	@Override
	public boolean getSaltoNavegation() {
		return validar();
	}
	
	public MarcoService getMarcoService() {
		if (marcoService == null) {
			marcoService = MarcoService.getInstance(getActivity());
		}
		return marcoService;
	}
	
	public INF_Caratula01Service getCaratulaService() {
		if (caratulaService == null) {
			caratulaService = INF_Caratula01Service.getInstance(getActivity());
		}
		return caratulaService;
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccept() {
		Cap100Delitos cap100 = (Cap100Delitos)dc.get("objeto");
		if(cap100!=null) {
			try {
				cap100.faltas = null;
				if(!getMarcoService().saveOrUpdate(cap100, cap100.getSecCap(Util.getListList("FALTAS","FALTAS_A","FALTAS_A_1","FALTAS_A_2","FALTAS_A_3","FALTAS_B",
						"FALTAS_B_1","FALTAS_B_2","FALTAS_B_3","FALTAS_B_4","FALTAS_B_5","FALTAS_B_6","FALTAS_C","FALTAS_C_1","FALTAS_C_2","FALTAS_D","FALTAS_D_1",
						"FALTAS_E", "FALTAS_E_1", "DN121", "TOTAL_FALTAS", "TOTAL_DELITOS", "OBS_03_100")))){
				} else {
					parent.nextFragment(parent.getCurPage() + 1);
				}
			} catch (SQLException e) {
				ToastMessage.msgBox(this.getActivity(), e.getMessage(),
						ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			}
		}
	}
}
