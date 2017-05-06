package gob.inei.renadef2016.fragments;

import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.Respondible;
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
public class C3CAP100_Fragment_105 extends FragmentForm implements Respondible {

	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtDN105;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtDN105_A;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtDN105_B;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtDN105_C;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtDN105_D;
	@FieldAnnotation(orderIndex = 6)
	public IntegerField txtDN105_E;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtDN105_F;
	@FieldAnnotation(orderIndex = 8)
	public IntegerField txtDN105_G;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtDN105_H;
	@FieldAnnotation(orderIndex = 10)
	public IntegerField txtDN105_I;
	@FieldAnnotation(orderIndex = 11)
	public IntegerField txtDN105_J;
	@FieldAnnotation(orderIndex = 12)
	public IntegerField txtDN105_K;
	@FieldAnnotation(orderIndex = 13)
	public IntegerField txtDN105_L;
	@FieldAnnotation(orderIndex = 14)
	public IntegerField txtDN106;
	@FieldAnnotation(orderIndex = 15)
	public IntegerField txtDN106_A;
	@FieldAnnotation(orderIndex = 16)
	public IntegerField txtDN106_B;
	@FieldAnnotation(orderIndex = 17)
	public IntegerField txtDN106_C;
	@FieldAnnotation(orderIndex = 18)
	public IntegerField txtDN106_D;
	@FieldAnnotation(orderIndex = 19)
	public IntegerField txtDN107;
	@FieldAnnotation(orderIndex = 20)
	public IntegerField txtDN107_A;
	@FieldAnnotation(orderIndex = 21)
	public IntegerField txtDN107_B;
	@FieldAnnotation(orderIndex = 22)
	public IntegerField txtDN107_C;

	private LabelComponent lblTitulo, lblSubTitulo;
	private int pleft = 45, psleft = 55;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private Cap100Delitos cap100delitos;
	private GridComponent2 gridDenuncias;

	public C3CAP100_Fragment_105() {
	}

	public C3CAP100_Fragment_105 parent(MasterActivity parent) {
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
		
		LabelComponent lblP105 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p105)
		.textSize(16).negrita();
		txtDN105 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP105A = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p105a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105B = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p105b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p105c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105D = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p105d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105E = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p105e)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105F = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p105f)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_F = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105G = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p105g)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_G = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105H = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p105h)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_H = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105I = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p105i)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_I = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105J = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p105j)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_J = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105K = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p105k)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_K = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP105L = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p105l)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN105_L = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
		LabelComponent lblP106 = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p106).
		textSize(16).negrita();
		txtDN106 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP106A = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p106a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN106_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP106B = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p106b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN106_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP106C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p106c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN106_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP106D = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p106d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN106_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP107 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p107)
		.textSize(16).negrita();
		txtDN107 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP107A = new LabelComponent(this.getActivity())
		.size(altoComponente+58, 600).text(R.string.lb_c3_cap100_p107a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN107_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP107B = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p107b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN107_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP107C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p107c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN107_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
//		Calculo<IntegerField> op = new Calculo<IntegerField>(this, txtDN105, 0, 0, 
//				Util.getListList(txtDN105_A, txtDN105_B, txtDN105_C, txtDN105_D, txtDN105_E, txtDN105_F,
//						txtDN105_G, txtDN105_H, txtDN105_I, txtDN105_J, txtDN105_K, txtDN105_L));
//		op.setConfOperacion();
//		
//		Calculo<IntegerField> op1 = new Calculo<IntegerField>(this, txtDN106, 0, 0, 
//				Util.getListList(txtDN106_A, txtDN106_B, txtDN106_C, txtDN106_D));
//		op1.setConfOperacion();
//
//		Calculo<IntegerField> op2 = new Calculo<IntegerField>(this, txtDN107, 0, 0, 
//				Util.getListList(txtDN107_A, txtDN107_B, txtDN107_C));
//		op2.setConfOperacion();
		
		gridDenuncias = new GridComponent2(this.getActivity(), 2);
		gridDenuncias.addComponent(lblP105);
		gridDenuncias.addComponent(txtDN105);
		gridDenuncias.addComponent(lblP105A);
		gridDenuncias.addComponent(txtDN105_A);
		gridDenuncias.addComponent(lblP105B);
		gridDenuncias.addComponent(txtDN105_B);
		gridDenuncias.addComponent(lblP105C);
		gridDenuncias.addComponent(txtDN105_C);
		gridDenuncias.addComponent(lblP105D);
		gridDenuncias.addComponent(txtDN105_D);
		gridDenuncias.addComponent(lblP105E);
		gridDenuncias.addComponent(txtDN105_E);
		gridDenuncias.addComponent(lblP105F);
		gridDenuncias.addComponent(txtDN105_F);
		gridDenuncias.addComponent(lblP105G);
		gridDenuncias.addComponent(txtDN105_G);
		gridDenuncias.addComponent(lblP105H);
		gridDenuncias.addComponent(txtDN105_H);
		gridDenuncias.addComponent(lblP105I);
		gridDenuncias.addComponent(txtDN105_I);
		gridDenuncias.addComponent(lblP105J);
		gridDenuncias.addComponent(txtDN105_J);
		gridDenuncias.addComponent(lblP105K);
		gridDenuncias.addComponent(txtDN105_K);
		gridDenuncias.addComponent(lblP105L);
		gridDenuncias.addComponent(txtDN105_L);
		gridDenuncias.addComponent(lblP106);
		gridDenuncias.addComponent(txtDN106);
		gridDenuncias.addComponent(lblP106A);
		gridDenuncias.addComponent(txtDN106_A);
		gridDenuncias.addComponent(lblP106B);
		gridDenuncias.addComponent(txtDN106_B);
		gridDenuncias.addComponent(lblP106C);
		gridDenuncias.addComponent(txtDN106_C);
		gridDenuncias.addComponent(lblP106D);
		gridDenuncias.addComponent(txtDN106_D);
		gridDenuncias.addComponent(lblP107);
		gridDenuncias.addComponent(txtDN107);
		gridDenuncias.addComponent(lblP107A);
		gridDenuncias.addComponent(txtDN107_A);
		gridDenuncias.addComponent(lblP107B);
		gridDenuncias.addComponent(txtDN107_B);
		gridDenuncias.addComponent(lblP107C);
		gridDenuncias.addComponent(txtDN107_C);
		
		/*Filtros - Capitulo 102 - MODALIDADES.*/

		Filtros.setFiltro(txtDN105_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_F, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_G, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_H, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_I, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_J, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_K, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN105_L, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN106_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN106_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN106_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN106_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN107_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN107_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN107_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
		Filtros.setFiltro(txtDN105, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN106, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN107, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
//		lblP102.setOnClickListener(new ClickPopup001(getActivity(), lblP102.getText()
//				.toString(), R.string.lb_c3_cap100_p102_desc));
//		lblP103.setOnClickListener(new ClickPopup001(getActivity(), lblP103.getText()
//				.toString(), R.string.lb_c3_cap100_p103_desc));
//		lblP104.setOnClickListener(new ClickPopup001(getActivity(), lblP104.getText()
//				.toString(), R.string.lb_c3_cap100_p104_desc));
//		lblP105.setOnClickListener(new ClickPopup001(getActivity(), lblP105.getText()
//				.toString(), R.string.lb_c3_cap100_p105_desc));
//		lblP106.setOnClickListener(new ClickPopup001(getActivity(), lblP106.getText()
//				.toString(), R.string.lb_c3_cap100_p106_desc));
//		lblP107.setOnClickListener(new ClickPopup001(getActivity(), lblP107.getText()
//				.toString(), R.string.lb_c3_cap100_p107_desc));
//		lblP108.setOnClickListener(new ClickPopup001(getActivity(), lblP108.getText()
//				.toString(), R.string.lb_c3_cap100_p108_desc));
//		lblP109.setOnClickListener(new ClickPopup001(getActivity(), lblP109.getText()
//				.toString(), R.string.lb_c3_cap100_p109_desc));
//		lblP110.setOnClickListener(new ClickPopup001(getActivity(), lblP110.getText()
//				.toString(), R.string.lb_c3_cap100_p110_desc));
//		lblP111.setOnClickListener(new ClickPopup001(getActivity(), lblP111.getText()
//				.toString(), R.string.lb_c3_cap100_p111_desc));
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo, lblSubTitulo, gridDenuncias.component());
		// ///////////////////////////
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
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
//			if(!getMarcoService().saveOrUpdate(cap100delitos, cap100delitos.getSecCap(getListFields(this)))){
//				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//						ToastMessage.MESSAGE_ERROR,
//						ToastMessage.DURATION_LONG);
//			} else {
//				App.getInstance().getComisaria().v3_1 = getMarcoService().getSumaNroDelitos(cap100delitos.id_n);
//				getCaratulaService().saveOrUpdate(App.getInstance().getComisaria(), 
//						App.getInstance().getComisaria().getSecCap(Util.getListList(new String[]{"V3_1"})));	
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

//		if(Util.esVacio(txtDN105_A)){
//			mensaje="Debe registrar dato en Preg 105. (a)";
//			view=txtDN105_A;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN105_B)){
//			mensaje="Debe registrar dato en Preg 105. (b)";
//			view=txtDN105_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN105_C)){
//			mensaje="Debe registrar dato en Preg 105. (c)";
//			view=txtDN105_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN105_D)){
//			mensaje="Debe registrar dato en Preg 105. (d)";
//			view=txtDN105_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN105_E)){
//			mensaje="Debe registrar dato en Preg 105. (e)";
//			view=txtDN105_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN105_F)){
//			mensaje="Debe registrar dato en Preg 105. (f)";
//			view=txtDN105_F;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN105_G)){
//			mensaje="Debe registrar dato en Preg 105. (g)";
//			view=txtDN105_G;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN105_H)){
//			mensaje="Debe registrar dato en Preg 105. (h)";
//			view=txtDN105_H;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN105_I)){
//			mensaje="Debe registrar dato en Preg 105. (i)";
//			view=txtDN105_I;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN105_J)){
//			mensaje="Debe registrar dato en Preg 105. (j)";
//			view=txtDN105_J;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN105_K)){
//			mensaje="Debe registrar dato en Preg 105. (k)";
//			view=txtDN105_K;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN105_L)){
//			mensaje="Debe registrar dato en Preg 105. (l)";
//			view=txtDN105_L;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN106_A)){
//			mensaje="Debe registrar dato en Preg 106. (a)";
//			view=txtDN106_A;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN106_B)){
//			mensaje="Debe registrar dato en Preg 106. (b)";
//			view=txtDN106_B;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN106_C)){
//			mensaje="Debe registrar dato en Preg 106. (c)";
//			view=txtDN106_C;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN106_D)){
//			mensaje="Debe registrar dato en Preg 106. (d)";
//			view=txtDN106_D;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN107_A)){
//			mensaje="Debe registrar dato en Preg 107. (a)";
//			view=txtDN107_A;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN107_B)){
//			mensaje="Debe registrar dato en Preg 107. (b)";
//			view=txtDN107_B;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN107_C)){
//			mensaje="Debe registrar dato en Preg 107. (c)";
//			view=txtDN107_C;
//			error=true;
//			return false;
//		}

		if(error) return false;	
		return true;
	}

	@Override
	public void cargarDatos() {
		cap100delitos = getMarcoService().getC100(App.getInstance().getComisaria(),
				new Cap100Delitos().getSecCap(getListFields(this)));
		if (cap100delitos == null) {
			cap100delitos = new Cap100Delitos();
			cap100delitos.id_n = App.getInstance().getComisaria().id_n;
		} 
		
		entityToUI(cap100delitos);
		inicio();
	}

	private void inicio() {
		txtDN105_A.requestFocus();
		
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
