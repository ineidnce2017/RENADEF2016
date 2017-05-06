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
public class C3CAP100_Fragment_117 extends FragmentForm implements Respondible {
	
	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtDN117;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtDN117_A;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtDN117_B;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtDN118;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtDN118_A;
	@FieldAnnotation(orderIndex = 6)
	public IntegerField txtDN118_B;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtDN119;
	@FieldAnnotation(orderIndex = 8)
	public IntegerField txtDN119_A;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtDN119_B;
	@FieldAnnotation(orderIndex = 10)
	public IntegerField txtDN119_C;
	@FieldAnnotation(orderIndex = 11)
	public IntegerField txtDN120;
	@FieldAnnotation(orderIndex = 12)
	public IntegerField txtDN120_A;
	@FieldAnnotation(orderIndex = 13)
	public IntegerField txtDN120_B;
	@FieldAnnotation(orderIndex = 14)
	public IntegerField txtDN120_C;
	@FieldAnnotation(orderIndex = 15)
	public IntegerField txtFALTAS;
	@FieldAnnotation(orderIndex = 16)
	public IntegerField txtFALTAS_A;
	@FieldAnnotation(orderIndex = 17)
	public IntegerField txtFALTAS_B;
	@FieldAnnotation(orderIndex = 18)
	public IntegerField txtFALTAS_C;
	@FieldAnnotation(orderIndex = 19)
	public IntegerField txtFALTAS_D;
	@FieldAnnotation(orderIndex = 20)
	public IntegerField txtFALTAS_E;
	@FieldAnnotation(orderIndex = 21)
	public IntegerField txtFALTAS_F;

	private LabelComponent lblTitulo, lblSubTitulo;
	private int pleft = 45, psleft = 55;
	private GridComponent2 gridDenuncias;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private Cap100Delitos cap100delitos;

	public C3CAP100_Fragment_117() {
	}

	public C3CAP100_Fragment_117 parent(MasterActivity parent) {
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
		
		LabelComponent lblP117 = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p117)
		.textSize(16).negrita();
		txtDN117 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP117A = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p117a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN117_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP117B = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p117b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN117_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
		LabelComponent lblP118 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p118)
		.textSize(16).negrita();
		txtDN118 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP118A = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p118a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN118_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP118B = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p118b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN118_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
		LabelComponent lblP119 = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p119)
		.textSize(16).negrita();
		txtDN119 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP119A = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p119a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN119_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP119B = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p119b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN119_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP119C = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p119c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN119_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
		LabelComponent lblP120 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p120)
		.textSize(16).negrita();
		txtDN120 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP120A = new LabelComponent(this.getActivity())
		.size(altoComponente+58, 600).text(R.string.lb_c3_cap100_p120a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN120_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP120B = new LabelComponent(this.getActivity())
		.size(altoComponente+58, 600).text(R.string.lb_c3_cap100_p120b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN120_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP120C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p120c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN120_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
		LabelComponent lblFALTAS = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121)
		.textSize(16).negrita();
		txtFALTAS = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblFALTASA = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p121a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly();
		LabelComponent lblFALTASB = new LabelComponent(this.getActivity())
		.size(altoComponente+58, 600).text(R.string.lb_c3_cap100_p121b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly();
		LabelComponent lblFALTASC = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p121c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly();
		LabelComponent lblFALTASD = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly();
		LabelComponent lblFALTASE = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121e)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly();
		LabelComponent lblFALTASF = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p121f)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtFALTAS_F = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).centrar().readOnly();
		
//		Calculo<IntegerField> op = new Calculo<IntegerField>(this, txtDN117, 0, 0, 
//				Util.getListList(txtDN117_A, txtDN117_B));
//		op.setConfOperacion();
//		
//		Calculo<IntegerField> op1 = new Calculo<IntegerField>(this, txtDN118, 0, 0, 
//				Util.getListList(txtDN118_A, txtDN118_B));
//		op1.setConfOperacion();
//		
//		Calculo<IntegerField> op2 = new Calculo<IntegerField>(this, txtDN119, 0, 0, 
//				Util.getListList(txtDN119_A, txtDN119_B, txtDN119_C));
//		op2.setConfOperacion();
//		
//		Calculo<IntegerField> op3 = new Calculo<IntegerField>(this, txtDN120, 0, 0, 
//				Util.getListList(txtDN120_A, txtDN120_B, txtDN120_C));
//		op3.setConfOperacion();
		
//		Calculo<IntegerField> op4 = new Calculo<IntegerField>(this, txtFALTAS, 0, 0, 
//				Util.getListList(txtFALTAS_A, txtFALTAS_B, txtFALTAS_C, txtFALTAS_D, txtFALTAS_E, txtFALTAS_F));
//		op4.setConfOperacion();
		
		gridDenuncias = new GridComponent2(this.getActivity(), 2);
		gridDenuncias.addComponent(lblP117);
		gridDenuncias.addComponent(txtDN117);
		gridDenuncias.addComponent(lblP117A);
		gridDenuncias.addComponent(txtDN117_A);
		gridDenuncias.addComponent(lblP117B);
		gridDenuncias.addComponent(txtDN117_B);
		gridDenuncias.addComponent(lblP118);
		gridDenuncias.addComponent(txtDN118);
		gridDenuncias.addComponent(lblP118A);
		gridDenuncias.addComponent(txtDN118_A);
		gridDenuncias.addComponent(lblP118B);
		gridDenuncias.addComponent(txtDN118_B);
		gridDenuncias.addComponent(lblP119);
		gridDenuncias.addComponent(txtDN119);
		gridDenuncias.addComponent(lblP119A);
		gridDenuncias.addComponent(txtDN119_A);
		gridDenuncias.addComponent(lblP119B);
		gridDenuncias.addComponent(txtDN119_B);
		gridDenuncias.addComponent(lblP119C);
		gridDenuncias.addComponent(txtDN119_C);
		gridDenuncias.addComponent(lblP120);
		gridDenuncias.addComponent(txtDN120);
		gridDenuncias.addComponent(lblP120A);
		gridDenuncias.addComponent(txtDN120_A);
		gridDenuncias.addComponent(lblP120B);
		gridDenuncias.addComponent(txtDN120_B);
		gridDenuncias.addComponent(lblP120C);
		gridDenuncias.addComponent(txtDN120_C);
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
		
		/*Filtros - 112 - MODALIDADES.*/
		
		Filtros.setFiltro(txtDN117_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN117_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN118_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN118_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN119_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN119_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN119_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN120_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN120_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN120_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS_F, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
		Filtros.setFiltro(txtDN117, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN118, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN119, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN120, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtFALTAS, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
//		lblP112.setOnClickListener(new ClickPopup001(getActivity(), lblP112.getText()
//				.toString(), R.string.lb_c3_cap100_p112_desc));
//		lblP113.setOnClickListener(new ClickPopup001(getActivity(), lblP113.getText()
//				.toString(), R.string.lb_c3_cap100_p113_desc));
//		lblP114.setOnClickListener(new ClickPopup001(getActivity(), lblP114.getText()
//				.toString(), R.string.lb_c3_cap100_p114_desc));
//		lblP115.setOnClickListener(new ClickPopup001(getActivity(), lblP115.getText()
//				.toString(), R.string.lb_c3_cap100_p115_desc));
//		lblP116.setOnClickListener(new ClickPopup001(getActivity(), lblP116.getText()
//				.toString(), R.string.lb_c3_cap100_p116_desc));
//		lblP117.setOnClickListener(new ClickPopup001(getActivity(), lblP117.getText()
//				.toString(), R.string.lb_c3_cap100_p117_desc));
//		lblP118.setOnClickListener(new ClickPopup001(getActivity(), lblP118.getText()
//				.toString(), R.string.lb_c3_cap100_p118_desc));
//		lblP119.setOnClickListener(new ClickPopup001(getActivity(), lblP119.getText()
//				.toString(), R.string.lb_c3_cap100_p119_desc));
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo, lblSubTitulo, gridDenuncias.component());
//		q1 = createQuestionSection(lblSubTitulo1, txtOBS_03_100);
		// ///////////////////////////
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
//		form.addView(q1);
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
		
//		try {
//			cap100delitos.total_faltas = Util.getInt(txtFALTAS);
//			if(!getMarcoService().saveOrUpdate(cap100delitos, cap100delitos.getSecCap(
//					Util.getListList("FALTAS","FALTAS_A","FALTAS_B","FALTAS_C","FALTAS_D","FALTAS_E","FALTAS_F","TOTAL_FALTAS")))){
//				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//						ToastMessage.MESSAGE_ERROR,
//						ToastMessage.DURATION_LONG);
//			}
//		} catch (SQLException e) {
//			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
//					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
//			return false;
//		}

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
		
//		if(Util.esVacio(txtFALTAS_A)){
//			mensaje="Debe registrar dato en Preg 121. (a)";
//			view=txtFALTAS_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_A)){
//			mensaje="Debe registrar dato en Preg 121. (a)";
//			view=txtFALTAS_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_B)){
//			mensaje="Debe registrar dato en Preg 121. (b)";
//			view=txtFALTAS_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_C)){
//			mensaje="Debe registrar dato en Preg 121. (c)";
//			view=txtFALTAS_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_D)){
//			mensaje="Debe registrar dato en Preg 121. (d)";
//			view=txtFALTAS_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_E)){
//			mensaje="Debe registrar dato en Preg 121. (e)";
//			view=txtFALTAS_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_F)){
//			mensaje="Debe registrar dato en Preg 121. (f)";
//			view=txtFALTAS_F;
//			error=true;
//			return false;	
//		}

//		if(Util.esVacio(txtDN117_A)){
//			mensaje="Debe registrar dato en Preg 117. (a)";
//			view=txtDN117_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN117_B)){
//			mensaje="Debe registrar dato en Preg 117. (b)";
//			view=txtDN117_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN118_A)){
//			mensaje="Debe registrar dato en Preg 118. (a)";
//			view=txtDN118_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN118_B)){
//			mensaje="Debe registrar dato en Preg 118. (b)";
//			view=txtDN118_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN119_A)){
//			mensaje="Debe registrar dato en Preg 119. (a)";
//			view=txtDN119_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN119_B)){
//			mensaje="Debe registrar dato en Preg 119. (b)";
//			view=txtDN119_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN119_C)){
//			mensaje="Debe registrar dato en Preg 119. (c)";
//			view=txtDN119_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN120_A)){
//			mensaje="Debe registrar dato en Preg 120. (a)";
//			view=txtDN120_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN120_B)){
//			mensaje="Debe registrar dato en Preg 120. (b)";
//			view=txtDN120_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN120_C)){
//			mensaje="Debe registrar dato en Preg 120. (c)";
//			view=txtDN120_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_A)){
//			mensaje="Debe registrar dato en Preg FALTAS. (a)";
//			view=txtFALTAS_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_B)){
//			mensaje="Debe registrar dato en Preg FALTAS. (b)";
//			view=txtFALTAS_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_C)){
//			mensaje="Debe registrar dato en Preg FALTAS. (c)";
//			view=txtFALTAS_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_D)){
//			mensaje="Debe registrar dato en Preg FALTAS. (d)";
//			view=txtFALTAS_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_E)){
//			mensaje="Debe registrar dato en Preg FALTAS. (e)";
//			view=txtFALTAS_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtFALTAS_F)){
//			mensaje="Debe registrar dato en Preg FALTAS. (f)";
//			view=txtFALTAS_F;
//			error=true;
//			return false;	
//		}
		
		if(error) return false;	
		return true;
	}
	
	@Override
	public void cargarDatos() {
		cap100delitos = getMarcoService().getC100(App.getInstance().getComisaria(),
				new Cap100Delitos().getSecCap(getListFields(this /*, new String[]{"DN120"}*/)));
		if (cap100delitos == null) {
			cap100delitos = new Cap100Delitos();
			cap100delitos.id_n = App.getInstance().getComisaria().id_n;
		} 
		
		entityToUI(cap100delitos);
		inicio();
	}

	private void inicio() {
//		txtFALTAS_A.requestFocus();
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
