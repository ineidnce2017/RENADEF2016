package gob.inei.renadef2016.fragments;

import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3CAP100_Fragment_117_2 extends FragmentForm implements Respondible {

	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtFALTAS;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtFALTAS_A;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtFALTAS_A_1;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtFALTAS_A_2;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtFALTAS_A_3;
	@FieldAnnotation(orderIndex = 6)
	public IntegerField txtFALTAS_B;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtFALTAS_B_1;
	@FieldAnnotation(orderIndex = 8)
	public IntegerField txtFALTAS_B_2;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtFALTAS_B_3;
	@FieldAnnotation(orderIndex = 10)
	public IntegerField txtFALTAS_B_4;
	@FieldAnnotation(orderIndex = 11)
	public IntegerField txtFALTAS_B_5;
	@FieldAnnotation(orderIndex = 12)
	public IntegerField txtFALTAS_B_6;
	@FieldAnnotation(orderIndex = 13)
	public IntegerField txtFALTAS_C;
	@FieldAnnotation(orderIndex = 14)
	public IntegerField txtFALTAS_C_1;
	@FieldAnnotation(orderIndex = 15)
	public IntegerField txtFALTAS_C_2;
	@FieldAnnotation(orderIndex = 16)
	public IntegerField txtFALTAS_D;
	@FieldAnnotation(orderIndex = 17)
	public IntegerField txtFALTAS_D_1;
	@FieldAnnotation(orderIndex = 18)
	public IntegerField txtFALTAS_E;
	@FieldAnnotation(orderIndex = 19)
	public IntegerField txtFALTAS_E_1;
//	@FieldAnnotation(orderIndex = 20)
//	public IntegerField txtFALTAS_F;
	@FieldAnnotation(orderIndex = 20)
	public IntegerField txtDN121;
	@FieldAnnotation(orderIndex = 21)
	public IntegerField txtTOTAL_FALTAS;
//	@FieldAnnotation(orderIndex = 22)
	public IntegerField txtTOTAL_DELITOS;
	@FieldAnnotation(orderIndex = 22)
	public TextAreaField txtOBS_03_100;

	private LabelComponent lblTitulo, lblSubTitulo, lblSubTitulo1;
	private int pleft = 45, psleft = 55;
	private GridComponent gridDenuncias, gridTotales;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private Cap100Delitos cap100delitos;
	private DialogComponent dlg;
	private boolean check, check100;

	public C3CAP100_Fragment_117_2() {
	}

	public C3CAP100_Fragment_117_2 parent(MasterActivity parent) {
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
		.textSize(17).negrita();
		txtFALTAS = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblFALTASA = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121ac).negrita()
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_A = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly().negrita();
		LabelComponent lblFALTASA_1 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121ac_1)
		.textSize(16).padding(psleft + 25, 0, 0, 0);
		txtFALTAS_A_1 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASA_2 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121ac_2)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_A_2 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASA_3 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121ac_3)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_A_3 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASB = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121bc).negrita()
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_B = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly().negrita();
		LabelComponent lblFALTASB_1 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121bc_1)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_B_1 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASB_2 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121bc_2)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_B_2 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASB_3 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121bc_3)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_B_3 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASB_4 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121bc_4)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_B_4 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASB_5 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121bc_5)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_B_5 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASB_6 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121bc_6)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_B_6 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASC = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121cc).negrita()
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_C = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly().negrita();
		LabelComponent lblFALTASC_1 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121cc_1)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_C_1 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASC_2 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121cc_2)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_C_2 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASD = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121dc).negrita()
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_D = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly().negrita();
		LabelComponent lblFALTASD_1 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121dc_1)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_D_1 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		LabelComponent lblFALTASE = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121ec).negrita()
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_E = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly().negrita();
		LabelComponent lblFALTASE_1 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121ec_1)
		.textSize(16).padding(psleft+25, 0, 0, 0);
		txtFALTAS_E_1 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
//		LabelComponent lblFALTASF = new LabelComponent(this.getActivity())
//		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121f)
//		.textSize(16).padding(psleft, 0, 0, 0);
//		txtFALTAS_F = new IntegerField(this.getActivity()).maxLength(50)
//				.size(altoComponente, 120).hint(R.string.hintNDN).centrar();
		
		
		
		LabelComponent lblP122 = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p122)
		.textSize(16).negrita().colorFondo(R.color.griscabece).colorTexto(R.color.blanco);
		txtDN121 = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).textAutomatico().centrar();
		LabelComponent lblTF = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p123)
		.textSize(16).negrita().colorFondo(R.color.griscabece).colorTexto(R.color.blanco);
		txtTOTAL_FALTAS = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).textAutomatico().centrar();
		LabelComponent lblTD = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p124)
		.textSize(16).negrita().colorFondo(R.color.griscabece).colorTexto(R.color.blanco);
		txtTOTAL_DELITOS = new IntegerField(this.getActivity())
				.size(altoComponente, 120).hint(R.string.hintNDN).textAutomatico().centrar();
		
		txtOBS_03_100 = new TextAreaField(this.getActivity()).size(400, 720);
		
		setCalculo();
		
		gridDenuncias = new GridComponent(this.getActivity(), 2).colorFondo(FragmentForm.COLOR_LINEA_SECCION_PREGUNTA);
		gridDenuncias.addComponent(lblFALTAS);
		gridDenuncias.addComponent(txtFALTAS);
		gridDenuncias.addComponent(lblFALTASA);
		gridDenuncias.addComponent(txtFALTAS_A);
		gridDenuncias.addComponent(lblFALTASA_1);
		gridDenuncias.addComponent(txtFALTAS_A_1);
		gridDenuncias.addComponent(lblFALTASA_2);
		gridDenuncias.addComponent(txtFALTAS_A_2);
		gridDenuncias.addComponent(lblFALTASA_3);
		gridDenuncias.addComponent(txtFALTAS_A_3);
		gridDenuncias.addComponent(lblFALTASB);
		gridDenuncias.addComponent(txtFALTAS_B);
		gridDenuncias.addComponent(lblFALTASB_1);
		gridDenuncias.addComponent(txtFALTAS_B_1);
		gridDenuncias.addComponent(lblFALTASB_2);
		gridDenuncias.addComponent(txtFALTAS_B_2);
		gridDenuncias.addComponent(lblFALTASB_3);
		gridDenuncias.addComponent(txtFALTAS_B_3);
		gridDenuncias.addComponent(lblFALTASB_4);
		gridDenuncias.addComponent(txtFALTAS_B_4);
		gridDenuncias.addComponent(lblFALTASB_5);
		gridDenuncias.addComponent(txtFALTAS_B_5);
		gridDenuncias.addComponent(lblFALTASB_6);
		gridDenuncias.addComponent(txtFALTAS_B_6);
		gridDenuncias.addComponent(lblFALTASC);
		gridDenuncias.addComponent(txtFALTAS_C);
		gridDenuncias.addComponent(lblFALTASC_1);
		gridDenuncias.addComponent(txtFALTAS_C_1);
		gridDenuncias.addComponent(lblFALTASC_2);
		gridDenuncias.addComponent(txtFALTAS_C_2);
		gridDenuncias.addComponent(lblFALTASD);
		gridDenuncias.addComponent(txtFALTAS_D);
		gridDenuncias.addComponent(lblFALTASD_1);
		gridDenuncias.addComponent(txtFALTAS_D_1);
		gridDenuncias.addComponent(lblFALTASE);
		gridDenuncias.addComponent(txtFALTAS_E);
		gridDenuncias.addComponent(lblFALTASE_1);
		gridDenuncias.addComponent(txtFALTAS_E_1);
//		gridDenuncias.addComponent(lblFALTASF);
//		gridDenuncias.addComponent(txtFALTAS_F);
		
		gridTotales = new GridComponent(this.getActivity(), 2).colorFondo(FragmentForm.COLOR_LINEA_SECCION_PREGUNTA);
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
//		Filtros.setFiltro(txtFALTAS_F, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
		Filtros.setFiltro(txtFALTAS_A_1, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_A_2, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_A_3, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_B_1, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_B_2, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_B_3, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_B_4, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_B_5, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_B_6, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_C_1, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_C_2, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_D_1, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_E_1, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
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
						getListFields(this)))){
					ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
				} 
				App.getInstance().getComisaria().v3_2 = cap100delitos.total_faltas;
				getCaratulaService().saveOrUpdate(App.getInstance().getComisaria(), 
						App.getInstance().getComisaria().getSecCap(Util.getListList("V3_2")));
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
		
		if(Util.getInt(App.getInstance().getComisaria().ii4) == 1) {
			if(Util.esVacio(txtFALTAS_A)){
				mensaje="Debe registrar dato en Preg 121.A.(a)";
				view=txtFALTAS_A_1;
				error=true;
				return false;	
			}
			if(!Util.esVacio(txtFALTAS_A_1) || !Util.esVacio(txtFALTAS_A_2) || !Util.esVacio(txtFALTAS_A_3)){
				if(Util.esVacio(txtFALTAS_A_1)){
					mensaje="Debe registrar dato en Preg 121.A.(a)";
					view=txtFALTAS_A_1;
					error=true;
					return false;	
				}
				if(Util.esVacio(txtFALTAS_A_2)){
					mensaje="Debe registrar dato en Preg 121.A.(b)";
					view=txtFALTAS_A_2;
					error=true;
					return false;	
				}
				if(Util.esVacio(txtFALTAS_A_3)){
					mensaje="Debe registrar dato en Preg 121.A.(c)";
					view=txtFALTAS_A_3;
					error=true;
					return false;	
				}
			}
			if(Util.esVacio(txtFALTAS_B)){
				mensaje="Debe registrar dato en Preg 121.B.(a)";
				view=txtFALTAS_B_1;
				error=true;
				return false;	
			}
			if(!Util.esVacio(txtFALTAS_B_1) || !Util.esVacio(txtFALTAS_B_2) || !Util.esVacio(txtFALTAS_B_3) ||
					!Util.esVacio(txtFALTAS_B_4) || !Util.esVacio(txtFALTAS_B_5) || !Util.esVacio(txtFALTAS_B_6)){
				if(Util.esVacio(txtFALTAS_B_1)){
					mensaje="Debe registrar dato en Preg 121.B.(a)";
					view=txtFALTAS_B_1;
					error=true;
					return false;	
				}
				if(Util.esVacio(txtFALTAS_B_2)){
					mensaje="Debe registrar dato en Preg 121.B.(b)";
					view=txtFALTAS_B_2;
					error=true;
					return false;	
				}
				if(Util.esVacio(txtFALTAS_B_3)){
					mensaje="Debe registrar dato en Preg 121.B.(c)";
					view=txtFALTAS_B_3;
					error=true;
					return false;	
				}
				if(Util.esVacio(txtFALTAS_B_4)){
					mensaje="Debe registrar dato en Preg 121.B.(d)";
					view=txtFALTAS_B_4;
					error=true;
					return false;	
				}
				if(Util.esVacio(txtFALTAS_B_5)){
					mensaje="Debe registrar dato en Preg 121.B.(e)";
					view=txtFALTAS_B_5;
					error=true;
					return false;	
				}
				if(Util.esVacio(txtFALTAS_B_6)){
					mensaje="Debe registrar dato en Preg 121.B.(f)";
					view=txtFALTAS_B_6;
					error=true;
					return false;	
				}
			}
			if(Util.esVacio(txtFALTAS_C)){
				mensaje="Debe registrar dato en Preg 121.C.(a)";
				view=txtFALTAS_C_1;
				error=true;
				return false;	
			}
			if(!Util.esVacio(txtFALTAS_C_1) /*|| cap100delitos.faltas_c_2!=null*/ ){
				if(Util.esVacio(txtFALTAS_C_1)){
					mensaje="Debe registrar dato en Preg 121.C.(a)";
					view=txtFALTAS_C_1;
					error=true;
					return false;	
				}
				if(Util.esVacio(txtFALTAS_C_2)){
					mensaje="Debe registrar dato en Preg 121.C.(b)";
					view=txtFALTAS_C_2;
					error=true;
					return false;	
				}
			}
			if(Util.esVacio(txtFALTAS_D)){
				mensaje="Debe registrar dato en Preg 121.D.(a)";
				view=txtFALTAS_D_1;
				error=true;
				return false;	
			}
			if(Util.esVacio(txtFALTAS_E)){
				mensaje="Debe registrar dato en Preg 121.E.(a)";
				view=txtFALTAS_E_1;
				error=true;
				return false;	
			}
		}
		
		if(error) return false;	
		return true;
	}
	
	@Override
	public void cargarDatos() {
		check = false; check100 = false;
		cap100delitos = getMarcoService().getC100(App.getInstance().getComisaria(),
				new Cap100Delitos().getSecCap(getListFields(this, new String[]{"FALTAS_F","TOTAL_DELITOS"})));
		if (cap100delitos == null) {
			check100 = true;
			cap100delitos = new Cap100Delitos();
			cap100delitos.id_n = App.getInstance().getComisaria().id_n;
		} 
		if(cap100delitos.faltas_c_2 == null)
			cap100delitos.faltas_c_2 = cap100delitos.faltas_f;
		txtTOTAL_DELITOS.setText(cap100delitos.total_delitos==null?"":String.valueOf(cap100delitos.total_delitos));
		
		entityToUI(cap100delitos);
		inicio();
	}
	
	private void setCalculo(){
		Calculo<IntegerField> op1 = new Calculo<IntegerField>(this, txtFALTAS_A, 0, 0, 
				Util.getListList(txtFALTAS_A_1, txtFALTAS_A_2, txtFALTAS_A_3));
		op1.setConfOperacion();
		Calculo<IntegerField> op2 = new Calculo<IntegerField>(this, txtFALTAS_B, 0, 0, 
				Util.getListList(txtFALTAS_B_1, txtFALTAS_B_2, txtFALTAS_B_3, txtFALTAS_B_4, txtFALTAS_B_5, txtFALTAS_B_6));
		op2.setConfOperacion();
		Calculo<IntegerField> op3 = new Calculo<IntegerField>(this, txtFALTAS_C, 0, 0, 
				Util.getListList(txtFALTAS_C_1, txtFALTAS_C_2));
		op3.setConfOperacion();
		Calculo<IntegerField> op4 = new Calculo<IntegerField>(this, txtFALTAS_D, 0, 0, 
				Util.getListList(txtFALTAS_D_1));
		op4.setConfOperacion();
		Calculo<IntegerField> op5 = new Calculo<IntegerField>(this, txtFALTAS_E, 0, 0, 
				Util.getListList(txtFALTAS_E_1));
		op5.setConfOperacion();
		Calculo<IntegerField> op6 = new Calculo<IntegerField>(this, txtFALTAS, 0, 0, 
				Util.getListList(txtFALTAS_A_1, txtFALTAS_A_2, txtFALTAS_A_3, txtFALTAS_B_1, txtFALTAS_B_2, txtFALTAS_B_3, 
						txtFALTAS_B_4, txtFALTAS_B_5, txtFALTAS_B_6, txtFALTAS_C_1, txtFALTAS_C_2, txtFALTAS_D_1, txtFALTAS_E_1/*, txtFALTAS_F*/));
		op6.setConfOperacion();
		op6.setCallback("onResult");
//		Calculo<IntegerField> op7 = new Calculo<IntegerField>(this, txtTOTAL_FALTAS, 0, 0, 
//				Util.getListList(txtFALTAS_A_1, txtFALTAS_A_2, txtFALTAS_A_3, txtFALTAS_B_1, txtFALTAS_B_2, txtFALTAS_B_3, 
//						txtFALTAS_B_4, txtFALTAS_B_5, txtFALTAS_B_6, txtFALTAS_C_1, txtFALTAS_C_2, txtFALTAS_D_1, txtFALTAS_E_1, txtFALTAS_F));
//		op7.setConfOperacion();
	}
	
	public void onResult(Integer result){
//		Log.e("observalo", "observalo: "+result);
		long tot = Util.sumar(txtFALTAS_A,txtFALTAS_B,txtFALTAS_C,txtFALTAS_D,txtFALTAS_E);
		txtFALTAS.setText(String.valueOf(tot));
		txtTOTAL_FALTAS.setText(String.valueOf(tot));
	}

	private void inicio() {
		if(Util.getInt(App.getInstance().getComisaria().ii4) == 2){
			cleanAndLockView(txtFALTAS,txtFALTAS_A,txtFALTAS_A_1,txtFALTAS_A_2,txtFALTAS_A_3,txtFALTAS_B,txtFALTAS_B_1,txtFALTAS_B_2,txtFALTAS_B_3,txtFALTAS_B_4,txtFALTAS_B_5,txtFALTAS_B_6,
					txtFALTAS_C,txtFALTAS_C_1,txtFALTAS_C_2,txtFALTAS_D,txtFALTAS_D_1,txtFALTAS_E,txtFALTAS_E_1,txtDN121,txtTOTAL_FALTAS,txtTOTAL_DELITOS,txtOBS_03_100);
		} else {
			lockView(false, txtFALTAS_A_1,txtFALTAS_A_2,txtFALTAS_A_3,txtFALTAS_B_1,txtFALTAS_B_2,txtFALTAS_B_3,txtFALTAS_B_4,txtFALTAS_B_5,txtFALTAS_B_6,
					txtFALTAS_C_1,txtFALTAS_C_2,txtFALTAS_D_1,txtFALTAS_E_1,txtOBS_03_100);
			txtFALTAS_A_1.requestFocus();
		}
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
