package gob.inei.renadef2016.fragments;

import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
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
import gob.inei.dnce.components.TextAreaField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Calculo;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;

import java.sql.SQLException;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3CAP100_Fragment_117_1 extends FragmentForm implements Respondible {

	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtFALTAS;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtFALTAS_A;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtFALTAS_B;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtFALTAS_C;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtFALTAS_D;
	@FieldAnnotation(orderIndex = 6)
	public IntegerField txtFALTAS_E;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtFALTAS_F;
	@FieldAnnotation(orderIndex = 8)
	public IntegerField txtDN121;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtTOTAL_FALTAS;
	@FieldAnnotation(orderIndex = 10)
	public IntegerField txtTOTAL_DELITOS;
	@FieldAnnotation(orderIndex = 11)
	public TextAreaField txtOBS_03_100;

	private LabelComponent lblTitulo, lblSubTitulo, lblSubTitulo1;
	private int pleft = 45, psleft = 55;
	private GridComponent2 gridDenuncias, gridTotales;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private Cap100Delitos cap100delitos;
	private DialogComponent dlg;
	private boolean check, check100;

	public C3CAP100_Fragment_117_1() {
	}

	public C3CAP100_Fragment_117_1 parent(MasterActivity parent) {
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
		initObjectsWithoutXML(this, rootView);
		enlazarCajas();
		listening();
		return rootView;
	}

	@Override
	protected void buildFields() {
		
		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_c3_cap100_title).textSize(21).centrar()
				.negrita();
		lblSubTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_c3_cap100_sub_title).textSize(21).centrar()
				.negrita();

		lblSubTitulo1 = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_OBS).textSize(21).centrar()
				.negrita();
		
		LabelComponent lblFALTAS = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121)
		.textSize(16).negrita();
		txtFALTAS = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblFALTASA = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p121a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASB = new LabelComponent(this.getActivity())
		.size(altoComponente+58, 600).text(R.string.lb_c3_cap100_p121b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASC = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p121c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASD = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASE = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121e)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASF = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121f)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_F = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		
		
		
		LabelComponent lblP122 = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p122)
		.textSize(16).negrita().colorFondo(R.color.griscabece).colorTexto(R.color.blanco);
		txtDN121 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).textAutomatico().centrar();
		LabelComponent lblTF = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p123)
		.textSize(16).negrita().colorFondo(R.color.griscabece).colorTexto(R.color.blanco);
		txtTOTAL_FALTAS = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).textAutomatico().centrar();
		LabelComponent lblTD = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p124)
		.textSize(16).negrita().colorFondo(R.color.griscabece).colorTexto(R.color.blanco);
		txtTOTAL_DELITOS = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).textAutomatico().centrar();
		
		txtOBS_03_100 = new TextAreaField(this.getActivity()).size(400, 720);
		
		Calculo<IntegerField> op4 = new Calculo<IntegerField>(this, txtFALTAS, 0, 0, 
				Util.getListList(txtFALTAS_A, txtFALTAS_B, txtFALTAS_C, txtFALTAS_D, txtFALTAS_E, txtFALTAS_F));
		op4.setConfOperacion();
		
		Calculo<IntegerField> op5 = new Calculo<IntegerField>(this, txtTOTAL_FALTAS, 0, 0, 
				Util.getListList(txtFALTAS_A, txtFALTAS_B, txtFALTAS_C, txtFALTAS_D, txtFALTAS_E, txtFALTAS_F));
		op5.setConfOperacion();
		
		gridDenuncias = new GridComponent2(this.getActivity(), 2);
		gridDenuncias.addComponent(lblFALTAS);
		gridDenuncias.addComponent(txtFALTAS);
		gridDenuncias.addComponent(lblFALTASA);
		gridDenuncias.addComponent(txtFALTAS_A);
		gridDenuncias.addComponent(lblFALTASB);
		gridDenuncias.addComponent(txtFALTAS_B);
		gridDenuncias.addComponent(lblFALTASC);
		gridDenuncias.addComponent(txtFALTAS_C);
		gridDenuncias.addComponent(lblFALTASD);
		gridDenuncias.addComponent(txtFALTAS_D);
		gridDenuncias.addComponent(lblFALTASE);
		gridDenuncias.addComponent(txtFALTAS_E);
		gridDenuncias.addComponent(lblFALTASF);
		gridDenuncias.addComponent(txtFALTAS_F);
		
		gridTotales = new GridComponent2(this.getActivity(), 2);
		gridTotales.addComponent(lblP122);
		gridTotales.addComponent(txtDN121);
		gridTotales.addComponent(lblTF);
		gridTotales.addComponent(txtTOTAL_FALTAS);
		gridTotales.addComponent(lblTD);
		gridTotales.addComponent(txtTOTAL_DELITOS);
		
		
		/*Filtros - 112 - MODALIDADES.*/
		
		Filtros.setFiltro(txtFALTAS_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_F, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
		Filtros.setFiltro(txtFALTAS, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtOBS_03_100, Filtros.TIPO.ALFAN_U, 1000, new char[]{'.', ','});
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo, lblSubTitulo, gridDenuncias.component());
		LinearLayout q1 = createQuestionSection(gridTotales.component());
		LinearLayout q2 = createQuestionSection(lblSubTitulo1, txtOBS_03_100);
//		q1 = createQuestionSection(lblSubTitulo1, txtOBS_03_100);
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
		uiToEntity(cap100delitos);
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
		
		try {
			cap100delitos.total_faltas = Util.getInt(txtFALTAS);
//			if(!getMarcoService().saveOrUpdate(cap100delitos, cap100delitos.getSecCap(
//					Util.getListList("FALTAS","FALTAS_A","FALTAS_B","FALTAS_C","FALTAS_D","FALTAS_E","FALTAS_F","TOTAL_FALTAS","OBS_03_100")))){
//				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//						ToastMessage.MESSAGE_ERROR,
//						ToastMessage.DURATION_LONG);
//			} 
			if(check100) {
				uiToEntity(cap100delitos);
				if(!getMarcoService().saveOrUpdate(cap100delitos, cap100delitos.getSecCap(getListFields(cap100delitos), false))){
					ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
				}
			} else {
				if(!getMarcoService().saveOrUpdate(cap100delitos, cap100delitos.getSecCap(
						Util.getListList("FALTAS","FALTAS_A","FALTAS_B","FALTAS_C","FALTAS_D","FALTAS_E","FALTAS_F","TOTAL_FALTAS","OBS_03_100")))){
					ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
				} 
			}
		} catch (SQLException e) {
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
		
		/*Filtros - Capitulo 102 - MODALIDADES.*/
		
		if(Util.esVacio(txtFALTAS_A)){
			mensaje="Debe registrar dato en Preg 121. (a)";
			view=txtFALTAS_A;
			error=true;
			return false;	
		}
		if(Util.esVacio(txtFALTAS_A)){
			mensaje="Debe registrar dato en Preg 121. (a)";
			view=txtFALTAS_A;
			error=true;
			return false;	
		}
		if(Util.esVacio(txtFALTAS_B)){
			mensaje="Debe registrar dato en Preg 121. (b)";
			view=txtFALTAS_B;
			error=true;
			return false;	
		}
		if(Util.esVacio(txtFALTAS_C)){
			mensaje="Debe registrar dato en Preg 121. (c)";
			view=txtFALTAS_C;
			error=true;
			return false;	
		}
		if(Util.esVacio(txtFALTAS_D)){
			mensaje="Debe registrar dato en Preg 121. (d)";
			view=txtFALTAS_D;
			error=true;
			return false;	
		}
		if(Util.esVacio(txtFALTAS_E)){
			mensaje="Debe registrar dato en Preg 121. (e)";
			view=txtFALTAS_E;
			error=true;
			return false;	
		}
		if(Util.esVacio(txtFALTAS_F)){
			mensaje="Debe registrar dato en Preg 121. (f)";
			view=txtFALTAS_F;
			error=true;
			return false;	
		}
		
		if(error) return false;	
		return true;
	}
	
	@Override
	public void cargarDatos() {
		check = false; check100 = false;
		cap100delitos = getMarcoService().getC100(App.getInstance().getComisaria(),
				new Cap100Delitos().getSecCap(getListFields(this /*, new String[]{"DN120"}*/)));
		if (cap100delitos == null) {
			check100 = true;
			cap100delitos = new Cap100Delitos();
			cap100delitos.id_n = App.getInstance().getComisaria().id_n;
		} 
		
		entityToUI(cap100delitos);
		inicio();
	}

	private void inicio() {
		txtFALTAS_A.requestFocus();
	}

	@Override
	public void onAccept() {
		
	}

	@Override
	public void onCancel() {
	}
	
	@Override
	public boolean getSaltoNavegation() {
		return validar();
	}
	
	public MarcoService getMarcoService() {
		if (mimarcoService == null) {
			mimarcoService = MarcoService.getInstance(getActivity());
		}
		return mimarcoService;
	}
	
	public INF_Caratula01Service getCaratulaService() {
		if (caratulaService == null) {
			caratulaService = INF_Caratula01Service.getInstance(getActivity());
		}
		return caratulaService;
	}
}
