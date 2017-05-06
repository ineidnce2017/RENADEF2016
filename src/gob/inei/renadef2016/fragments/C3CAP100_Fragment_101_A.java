package gob.inei.renadef2016.fragments;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.DialogComponent.TIPO_DIALOGO;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.activity.CuestionarioFragmentActivity;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;

import java.sql.SQLException;
import java.util.Random;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

// 
public class C3CAP100_Fragment_101_A extends FragmentForm implements Respondible {

	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtDN101;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtDN101_1;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtDN101_1_A;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtDN101_1_B;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtDN101_1_C;
	@FieldAnnotation(orderIndex = 6)
	public IntegerField txtDN101_1_D;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtDN101_1_E;
	@FieldAnnotation(orderIndex = 8)
	public IntegerField txtDN101_1_F;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtDN101_1_G;
	@FieldAnnotation(orderIndex = 10)
	public IntegerField txtDN101_1_H;
	@FieldAnnotation(orderIndex = 11)
	public IntegerField txtDN101_2;
	@FieldAnnotation(orderIndex = 12)
	public IntegerField txtDN101_2_A;
	@FieldAnnotation(orderIndex = 13)
	public IntegerField txtDN101_2_B;
	@FieldAnnotation(orderIndex = 14)
	public IntegerField txtDN101_2_C;
	@FieldAnnotation(orderIndex = 15)
	public IntegerField txtDN101_3;
	@FieldAnnotation(orderIndex = 16)
	public IntegerField txtDN101_4;
	@FieldAnnotation(orderIndex = 17)
	public IntegerField txtDN101_4_A;
	@FieldAnnotation(orderIndex = 18)
	public IntegerField txtDN101_5;
	@FieldAnnotation(orderIndex = 19)
	public IntegerField txtDN101_5_A;
	@FieldAnnotation(orderIndex = 20)
	public IntegerField txtDN101_5_B;
	@FieldAnnotation(orderIndex = 21)
	public IntegerField txtDN101_5_C;
	@FieldAnnotation(orderIndex = 22)
	public IntegerField txtDN101_5_D;

	private LabelComponent lblTitulo, lblSubTitulo;
	LinearLayout q0, q1, q2, q3;
	private boolean check, check100;
	private DialogComponent dlg;
	private int pleft = 45, psleft = 70;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private GridComponent2 gridDenuncias;
	private Cap100Delitos cap100Delitos;

	public C3CAP100_Fragment_101_A() {
	}

	public C3CAP100_Fragment_101_A parent(MasterActivity parent) {
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
		LabelComponent lblP101 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101)
		.textSize(16).negrita();
		txtDN101 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101A = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101a)
		.textSize(16).negrita().padding(pleft, 0, 0, 0);
		txtDN101_1 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101AA = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101aa)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_1_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101AB = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ab)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_1_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101AC = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ac)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_1_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101AD = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ad)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_1_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101AE = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ae)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_1_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101AF = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101af)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_1_F = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101AG = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ag)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_1_G = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101AH = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ah)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_1_H = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101B = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).textFormat(R.string.lb_c3_cap100_p101b, LabelComponent.STYLE.STYLE, 
				Typeface.BOLD, 0, 22).textSize(16).padding(pleft, 0, 0, 0);
		txtDN101_2 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101BA = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ba)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_2_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101BB = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101bb)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_2_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente+10, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101BC = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101bc)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_2_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101c)
		.textSize(16).negrita().padding(pleft, 0, 0, 0);
		txtDN101_3 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101D = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101d)
		.textSize(16).negrita().padding(pleft, 0, 0, 0);
		txtDN101_4 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101DA = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101da)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_4_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101E = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101e)
		.textSize(16).negrita().padding(pleft, 0, 0, 0);
		txtDN101_5 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101EA = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ea)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_5_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101EB = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p101eb)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_5_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101EC = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ec)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_5_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101ED = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ed)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_5_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
//		Calculo<IntegerField> op = new Calculo<IntegerField>(this, txtDN101_1, 0, 0, 
//				Util.getListList(txtDN101_1_A, txtDN101_1_B, txtDN101_1_C, txtDN101_1_D, 
//						txtDN101_1_E, txtDN101_1_F, txtDN101_1_G, txtDN101_1_H, txtDN101_3,
//						txtDN101_4_A, txtDN101_5_A, txtDN101_5_B, txtDN101_5_C, txtDN101_5_D));
//		op.setConfOperacion();
//		
//		Calculo<IntegerField> op1 = new Calculo<IntegerField>(this, txtDN101_2, 0, 0, 
//				Util.getListList(txtDN101_2_A, txtDN101_2_B, txtDN101_2_C));
//		op1.setConfOperacion();
//		
//		Calculo<IntegerField> op2 = new Calculo<IntegerField>(this, txtDN101_4, 0, 0, 
//				Util.getListList(txtDN101_4_A));
//		op2.setConfOperacion();
//		
//		Calculo<IntegerField> op3 = new Calculo<IntegerField>(this, txtDN101_5, 0, 0, 
//				Util.getListList(txtDN101_5_A, txtDN101_5_B, txtDN101_5_C, txtDN101_5_D));
//		op3.setConfOperacion();
//		
//		Calculo<IntegerField> opt = new Calculo<IntegerField>(this, txtDN101, 0, 0, 
//				Util.getListList(txtDN101_1_A, txtDN101_1_B, txtDN101_1_C, txtDN101_1_D, 
//						txtDN101_1_E, txtDN101_1_F, txtDN101_1_G, txtDN101_1_H, 
//						txtDN101_2_A, txtDN101_2_B, txtDN101_2_C, txtDN101_3, txtDN101_4_A,  
//						txtDN101_5_A, txtDN101_5_B, txtDN101_5_C, txtDN101_5_D));
//		opt.setConfOperacion();
		
		gridDenuncias = new GridComponent2(this.getActivity(), 2);
		gridDenuncias.addComponent(lblP101);
		gridDenuncias.addComponent(txtDN101);
		gridDenuncias.addComponent(lblP101A);
		gridDenuncias.addComponent(txtDN101_1);
		gridDenuncias.addComponent(lblP101AA);
		gridDenuncias.addComponent(txtDN101_1_A);
		gridDenuncias.addComponent(lblP101AB);
		gridDenuncias.addComponent(txtDN101_1_B);
		gridDenuncias.addComponent(lblP101AC);
		gridDenuncias.addComponent(txtDN101_1_C);
		gridDenuncias.addComponent(lblP101AD);
		gridDenuncias.addComponent(txtDN101_1_D);
		gridDenuncias.addComponent(lblP101AE);
		gridDenuncias.addComponent(txtDN101_1_E);
		gridDenuncias.addComponent(lblP101AF);
		gridDenuncias.addComponent(txtDN101_1_F);
		gridDenuncias.addComponent(lblP101AG);
		gridDenuncias.addComponent(txtDN101_1_G);
		gridDenuncias.addComponent(lblP101AH);
		gridDenuncias.addComponent(txtDN101_1_H);
		gridDenuncias.addComponent(lblP101B);
		gridDenuncias.addComponent(txtDN101_2);
		gridDenuncias.addComponent(lblP101BA);
		gridDenuncias.addComponent(txtDN101_2_A);
		gridDenuncias.addComponent(lblP101BB);
		gridDenuncias.addComponent(txtDN101_2_B);
		gridDenuncias.addComponent(lblP101BC);
		gridDenuncias.addComponent(txtDN101_2_C);
		gridDenuncias.addComponent(lblP101C);
		gridDenuncias.addComponent(txtDN101_3);
		gridDenuncias.addComponent(lblP101D);
		gridDenuncias.addComponent(txtDN101_4);
		gridDenuncias.addComponent(lblP101DA);
		gridDenuncias.addComponent(txtDN101_4_A);
		gridDenuncias.addComponent(lblP101E);
		gridDenuncias.addComponent(txtDN101_5);
		gridDenuncias.addComponent(lblP101EA);
		gridDenuncias.addComponent(txtDN101_5_A);
		gridDenuncias.addComponent(lblP101EB);
		gridDenuncias.addComponent(txtDN101_5_B);
		gridDenuncias.addComponent(lblP101EC);
		gridDenuncias.addComponent(txtDN101_5_C);
		gridDenuncias.addComponent(lblP101ED);
		gridDenuncias.addComponent(txtDN101_5_D);
		
		/*Filtros - Capitulo 100 - 101 (A) MODALIDADES.*/
		
		Filtros.setFiltro(txtDN101_1_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_1_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_1_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_1_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_1_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_1_F, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_1_G, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_1_H, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_2_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_2_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_2_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_4_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_5_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_5_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_5_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_5_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
		Filtros.setFiltro(txtDN101_2, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_3, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_4, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_5, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		q0 = createQuestionSection(lblTitulo, lblSubTitulo, gridDenuncias.component());
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
		
//		int sum_fallecidos = getSumaP101_1toP101_6();
//		INF_Caratula01 carat = App.getInstance().getComisaria();
//		if(sum_fallecidos < Util.getInt(carat.v3_2)){
//			if(!check && !saltoCap(sum_fallecidos, Util.getInt(carat.v3_2))) return false;
//		}
			
//		uiToEntity(cap100Delitos);
//		cap100Delitos.sum_fallecidos = sum_fallecidos;
//		String extras100[] = new String[]{"SUM_FALLECIDOS"};
//		if(Integer.valueOf(0).equals(cap100Delitos.sum_fallecidos)){
//			cap100Delitos.sum_ih213 = 0;
//			cap100Delitos.sum_ih214 = 0;
//			cap100Delitos.conte_reg200 = 0;
//			extras100 = new String[]{"SUM_FALLECIDOS", "SUM_IH213", "SUM_IH214", "CONTE_REG200"};
//		}
		
		try {
//			boolean flag = true;
			if(check100) {
				uiToEntity(cap100Delitos);
				if(!getMarcoService().saveOrUpdate(cap100Delitos, cap100Delitos.getSecCap(getListFields(cap100Delitos), false))){
//					flag = false;
					ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
				}
			} /*else if(!getMarcoService().saveOrUpdate(cap100Delitos, cap100Delitos.getSecCap(getListFields(this, extras100)))){
				flag = false;
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			} 
			if(flag){
				App.getInstance().getComisaria().v3_1 = getMarcoService().getSumaNroDelitos(cap100Delitos.id_n);
				String extras[] = new String[]{"V3_1"};
				if(Integer.valueOf(0).equals(cap100Delitos.sum_fallecidos)){
					App.getInstance().getComisaria().v3_2 = 0;
					App.getInstance().getComisaria().v3_3 = 0;
					App.getInstance().getComisaria().v3_4 = 0;
					extras = new String[]{"V3_1", "V3_2", "V3_3", "V3_4"};
					getMarcoService().deleteCap200DelxId(cap100Delitos.id_n);
				}
				getCaratulaService().saveOrUpdate(App.getInstance().getComisaria(), 
						App.getInstance().getComisaria().getSecCap(Util.getListList(extras)));
			}*/
		} catch (SQLException e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			return false;
		}

		return true;
	}
	
	private Integer getSumaP101_1toP101_6(){
		return Util.getInt(txtDN101_1)+Util.getInt(txtDN101_2)+Util.getInt(txtDN101_3)+
				Util.getInt(txtDN101_4)+Util.getInt(txtDN101_5)/*+Util.getInt(txtDN101_6)*/;
	}
	
	private boolean saltoCap(int val, int val1){
    	String message = "Usted ha disminuido la suma de fallecidos a: "+val+" y ya tiene "+val1+
    			" denuncias registradas. "+(val==0?"Por tanto \u00e9stas ser\u00e1n eliminadas. ":"")+
    			"Está seguro que desea continuar?.";
    	
		dlg = new DialogComponent(getActivity(), C3CAP100_Fragment_101_A.this, 
				TIPO_DIALOGO.YES_NO, getResources().getString(R.string.app_name), message);
		dlg.showDialog();
		return false;
    }

	private boolean validar() {
		error = false;

		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
		
		/*Capitulo 100 -101 (A)- MODALIDADES. */
		
//		if(Util.esVacio(txtDN101_1_A)){
//			mensaje="Debe registrar dato (A).Homicidio Simple";
//			view=txtDN101_1_A;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN101_1_B)){
//			mensaje="Debe registrar dato (B).Parricidio";
//			view=txtDN101_1_B;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN101_1_C)){
//			mensaje="Debe registrar dato (C).Homicido Calificado (Asesinato)";
//			view=txtDN101_1_C;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN101_1_D)){
//			mensaje="Debe registrar dato (D).Homicidio por Emocion Violenta";
//			view=txtDN101_1_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_1_D)){
//			mensaje="Debe registrar dato (D).Homicidio por emoción violenta";
//			view=txtDN101_1_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_1_E)){
//			mensaje="Debe registrar dato (E).Infanticidio";
//			view=txtDN101_1_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_1_F)){
//			mensaje="Debe registrar dato (F).Homicidio Piadoso";
//			view=txtDN101_1_F;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_1_G)){
//			mensaje="Debe registrar dato (G).Feminicidio";
//			view=txtDN101_1_G;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_1_H)){
//			mensaje="Debe registrar dato (H).Sicariato";
//			view=txtDN101_1_H;
//			error=true;
//			return false;	
//		}
//		
//		/*Capitulo 100 -101 (B)- MODALIDADES. */
//
//		if(Util.esVacio(txtDN101_2_A)){
//			mensaje="Debe registrar dato en Sec. B, preg. (a)";
//			view=txtDN101_2_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_2_B)){
//			mensaje="Debe registrar dato en Sec. B, preg. (b)";
//			view=txtDN101_2_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_2_C)){
//			mensaje="Debe registrar dato en Sec. B, preg. (c)";
//			view=txtDN101_2_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_3)){
//			mensaje="Debe registrar dato en Sec. C";
//			view=txtDN101_3;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_4_A)){
//			mensaje="Debe registrar dato en Sec. D, preg. (a)";
//			view=txtDN101_4_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_5_A)){
//			mensaje="Debe registrar dato en Sec. E, preg. (a)";
//			view=txtDN101_5_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_5_B)){
//			mensaje="Debe registrar dato en Sec. E, preg. (b)";
//			view=txtDN101_5_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_5_C)){
//			mensaje="Debe registrar dato en Sec. E, preg. (c)";
//			view=txtDN101_5_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_5_D)){
//			mensaje="Debe registrar dato en Sec. E, preg. (d)";
//			view=txtDN101_5_D;
//			error=true;
//			return false;	
//		}

		if(error) return false;	
		return true;
	}

	@Override
	public void cargarDatos() {
		check = false; check100 = false;
		cap100Delitos = getMarcoService().getC100(App.getInstance().getComisaria(),
				new Cap100Delitos().getSecCap(getListFields(this, new String[]{"SUM_FALLECIDOS", "OBS_03_100"})));
		if (cap100Delitos == null) {
			check100 = true;
			cap100Delitos = new Cap100Delitos();
			cap100Delitos.id_n = App.getInstance().getComisaria().id_n;
		} 
	
		entityToUI(cap100Delitos);
		inicio();
	}

	private void inicio() {
		txtDN101_1_A.requestFocus();
	}

	@Override
	public void onAccept() {
		eliminarC200300400();
	}

	@Override
	public void onCancel() {
	}
	
	@Override
	public boolean getSaltoNavegation() {
		return validar();
	}
	
	public void eliminarC200300400(){
		final Dialog dialog = new Dialog(getActivity());
		dialog.setTitle("Borrar Cap. 200, 300 y 400...");
		dialog.setContentView(R.layout.dialog_dig_01_prompt);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setCancelable(true);
		
		Button btnPromptAceptar = (Button) dialog.findViewById(R.id.btnPromptAceptar);
		Button btnPromptCancelar = (Button) dialog.findViewById(R.id.btnPromptCancelar);
		final TextView lblPromptQuestion = (TextView) dialog.findViewById(R.id.lblPromptQuestion);
		
		Random r = new Random();
		final int n1 = r.nextInt(10-1) + 1;	//[1,10>
		final int n2 = r.nextInt(10-1) + 1;	//[1,10>
		lblPromptQuestion.setText("Est\u00e1 a punto de borrar la informaci\u00f3n de los Cap. 200, 300 y 400."
				+ " Si no desea borrar la informaci\u00f3n presione \"Cancelar\" pero si est\u00e1 seguro que "
				+ "desea continuar entonces \u00bfCu\u00e1l es el resultado de " + n1 + " + " + n2 + "?");
		
		btnPromptAceptar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText txtPrompt = (EditText)dialog.findViewById(R.id.txtPrompt);
				if(txtPrompt.getText().toString().equals("" + (n1+n2))){
					dialog.dismiss();
					check = true;
					parent.nextFragment(CuestionarioFragmentActivity.CAP100TOT_DEL+1);
				}
			}
		});
		
		btnPromptCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		dialog.show();
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
