package gob.inei.renadef2016.fragments;

import gob.inei.renadef2016.R;
import gob.inei.renadef2016.activity.CuestionarioFragmentActivity;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;
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
public class C3CAP100_Fragment_101_F extends FragmentForm implements Respondible {

	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtDN101_6;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtDN101_6_A;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtDN101_6_B;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtDN101_6_C;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtDN101_6_D;
	@FieldAnnotation(orderIndex = 6)
	public IntegerField txtDN101_6_E;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtDN101_6_F;
	@FieldAnnotation(orderIndex = 8)
	public IntegerField txtDN101_7;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtDN101_8;
	@FieldAnnotation(orderIndex = 10)
	public IntegerField txtDN101_9;
	@FieldAnnotation(orderIndex = 11)
	public IntegerField txtDN101_9_A;
	@FieldAnnotation(orderIndex = 12)
	public IntegerField txtDN101_9_B;
	@FieldAnnotation(orderIndex = 13)
	public IntegerField txtDN101_9_C;
	@FieldAnnotation(orderIndex = 14)
	public IntegerField txtDN101_9_D;
	@FieldAnnotation(orderIndex = 15)
	public IntegerField txtDN101_9_E;
	@FieldAnnotation(orderIndex = 16)
	public IntegerField txtDN101_10;
	@FieldAnnotation(orderIndex = 17)
	public IntegerField txtDN101_10_A;
	@FieldAnnotation(orderIndex = 18)
	public IntegerField txtDN101_10_B;
	@FieldAnnotation(orderIndex = 19)
	public IntegerField txtDN101_10_C;
	@FieldAnnotation(orderIndex = 20)
	public IntegerField txtDN101_10_D;
	@FieldAnnotation(orderIndex = 21)
	public IntegerField txtDN101_10_E;

	private LabelComponent lblTitulo, lblSubTitulo;
	LinearLayout q0, q1, q2, q3;
	private boolean check;
	private DialogComponent dlg;
	private int pleft = 45, psleft = 70;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private GridComponent2 gridDenuncias;
	private Cap100Delitos cap100Delitos;

	public C3CAP100_Fragment_101_F() {
	}

	public C3CAP100_Fragment_101_F parent(MasterActivity parent) {
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
		LabelComponent lblP101F = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).textFormat(R.string.lb_c3_cap100_p101f, LabelComponent.STYLE.STYLE,
				Typeface.BOLD, 0, 16).textSize(16).padding(pleft, 0, 0, 0);
		txtDN101_6 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101FA = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101fa)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_6_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101FB = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101fb)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_6_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101FC = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101fc)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_6_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101FD = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101fd)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_6_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101FE = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101fe)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_6_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101FF = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ff)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_6_F = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101G = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101g)
		.textSize(16).negrita().padding(pleft, 0, 0, 0);
		txtDN101_7 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101H = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101h)
		.textSize(16).negrita().padding(pleft, 0, 0, 0);
		txtDN101_8 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101I = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101i)
		.textSize(16).negrita().padding(pleft, 0, 0, 0);
		txtDN101_9 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101IA = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p101ia)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_9_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101IB = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p101ib)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_9_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101IC = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ic)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_9_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101ID = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101id)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_9_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101IE = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101ie)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_9_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101J = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p101j)
		.textSize(16).negrita().padding(pleft, 0, 0, 0);
		txtDN101_10 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP101JA = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p101ja)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_10_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101JB = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p101jb)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_10_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101JC = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p101jc)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_10_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101JD = new LabelComponent(this.getActivity())
		.size(altoComponente+57, 600).text(R.string.lb_c3_cap100_p101jd)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_10_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP101JE = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p101je)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN101_10_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
//		Calculo<IntegerField> op = new Calculo<IntegerField>(this, txtDN101_6, 0, 0, 
//				Util.getListList(txtDN101_6_A, txtDN101_6_B, txtDN101_6_C, txtDN101_6_D, 
//						txtDN101_6_E, txtDN101_6_F));
//		op.setConfOperacion();
//		
//		Calculo<IntegerField> op1 = new Calculo<IntegerField>(this, txtDN101_9, 0, 0, 
//				Util.getListList(txtDN101_9_A, txtDN101_9_B, txtDN101_9_C, txtDN101_9_D, 
//						txtDN101_9_E));
//		op1.setConfOperacion();
//		
//		Calculo<IntegerField> op2 = new Calculo<IntegerField>(this, txtDN101_10, 0, 0, 
//				Util.getListList(txtDN101_10_A, txtDN101_10_B, txtDN101_10_C, txtDN101_10_D, 
//						txtDN101_10_E));
//		op2.setConfOperacion();
		
		gridDenuncias = new GridComponent2(this.getActivity(), 2);
		gridDenuncias.addComponent(lblP101F);
		gridDenuncias.addComponent(txtDN101_6);
		gridDenuncias.addComponent(lblP101FA);
		gridDenuncias.addComponent(txtDN101_6_A);
		gridDenuncias.addComponent(lblP101FB);
		gridDenuncias.addComponent(txtDN101_6_B);
		gridDenuncias.addComponent(lblP101FC);
		gridDenuncias.addComponent(txtDN101_6_C);
		gridDenuncias.addComponent(lblP101FD);
		gridDenuncias.addComponent(txtDN101_6_D);
		gridDenuncias.addComponent(lblP101FE);
		gridDenuncias.addComponent(txtDN101_6_E);
		gridDenuncias.addComponent(lblP101FF);
		gridDenuncias.addComponent(txtDN101_6_F);
		gridDenuncias.addComponent(lblP101G);
		gridDenuncias.addComponent(txtDN101_7);
		gridDenuncias.addComponent(lblP101H);
		gridDenuncias.addComponent(txtDN101_8);
		gridDenuncias.addComponent(lblP101I);
		gridDenuncias.addComponent(txtDN101_9);
		gridDenuncias.addComponent(lblP101IA);
		gridDenuncias.addComponent(txtDN101_9_A);
		gridDenuncias.addComponent(lblP101IB);
		gridDenuncias.addComponent(txtDN101_9_B);
		gridDenuncias.addComponent(lblP101IC);
		gridDenuncias.addComponent(txtDN101_9_C);
		gridDenuncias.addComponent(lblP101ID);
		gridDenuncias.addComponent(txtDN101_9_D);
		gridDenuncias.addComponent(lblP101IE);
		gridDenuncias.addComponent(txtDN101_9_E);
		gridDenuncias.addComponent(lblP101J);
		gridDenuncias.addComponent(txtDN101_10);
		gridDenuncias.addComponent(lblP101JA);
		gridDenuncias.addComponent(txtDN101_10_A);
		gridDenuncias.addComponent(lblP101JB);
		gridDenuncias.addComponent(txtDN101_10_B);
		gridDenuncias.addComponent(lblP101JC);
		gridDenuncias.addComponent(txtDN101_10_C);
		gridDenuncias.addComponent(lblP101JD);
		gridDenuncias.addComponent(txtDN101_10_D);
		gridDenuncias.addComponent(lblP101JE);
		gridDenuncias.addComponent(txtDN101_10_E);
		
		/*Filtros - Capitulo 100 - 101 (A) MODALIDADES.*/
		
		Filtros.setFiltro(txtDN101_6_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_6_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_6_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_6_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_6_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_6_F, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_9_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_9_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_9_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_9_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_9_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_10_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_10_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_10_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_10_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_10_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
		Filtros.setFiltro(txtDN101_6, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_7, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_8, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_9, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN101_10, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
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
		
//		int sum_fallecidos = Util.getInt(cap100Delitos.sum_fallecidos) + getSumaP101_1toP101_6();
//		INF_Caratula01 carat = App.getInstance().getComisaria();
//		if(sum_fallecidos < Util.getInt(carat.v3_2)){
//			if(!check && !saltoCap(sum_fallecidos, Util.getInt(carat.v3_2))) return false;
//		}
//			
//		uiToEntity(cap100Delitos);
//		cap100Delitos.sum_fallecidos = sum_fallecidos;
//		String extras100[] = new String[]{"SUM_FALLECIDOS"};
//		if(Integer.valueOf(0).equals(cap100Delitos.sum_fallecidos)){
//			cap100Delitos.sum_ih213 = 0;
//			cap100Delitos.sum_ih214 = 0;
//			cap100Delitos.conte_reg200 = 0;
//			extras100 = new String[]{"SUM_FALLECIDOS", "SUM_IH213", "SUM_IH214", "CONTE_REG200"};
//		}
//		
//		try {
//			if(!getMarcoService().saveOrUpdate(cap100Delitos, cap100Delitos.getSecCap(getListFields(this, extras100)))){
//				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//						ToastMessage.MESSAGE_ERROR,
//						ToastMessage.DURATION_LONG);
//			} else {
//				App.getInstance().getComisaria().v3_1 = getMarcoService().getSumaNroDelitos(cap100Delitos.id_n);
//				String extras[] = new String[]{"V3_1"};
//				if(Integer.valueOf(0).equals(cap100Delitos.sum_fallecidos)){
//					App.getInstance().getComisaria().v3_2 = 0;
//					App.getInstance().getComisaria().v3_3 = 0;
//					App.getInstance().getComisaria().v3_4 = 0;
//					extras = new String[]{"V3_1", "V3_2", "V3_3", "V3_4"};
//					getMarcoService().deleteCap200DelxId(cap100Delitos.id_n);
//				}
//				getCaratulaService().saveOrUpdate(App.getInstance().getComisaria(), 
//						App.getInstance().getComisaria().getSecCap(Util.getListList(extras)));
//			}
//		} catch (SQLException e) {
//			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
//					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
//			return false;
//		}
		
		return true;
	}
	
	private Integer getSumaP101_1toP101_6(){
		return Util.getInt(txtDN101_6);
	}
	
	private boolean saltoCap(int val, int val1){
    	String message = "Usted ha disminuido la suma de fallecidos a: "+val+" y ya tiene "+val1+
    			" denuncias registradas. "+(val==0?"Por tanto \u00e9stas ser\u00e1n eliminadas. ":"")+
    			"Está seguro que desea continuar?.";
    	
		dlg = new DialogComponent(getActivity(), C3CAP100_Fragment_101_F.this, 
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
		
//		if(Util.esVacio(txtDN101_6_A)){
//			mensaje="Debe registrar dato en Sec. F, preg. (a)";
//			view=txtDN101_6_A;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN101_6_B)){
//			mensaje="Debe registrar dato en Sec. F, preg. (b)";
//			view=txtDN101_6_B;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN101_6_C)){
//			mensaje="Debe registrar dato en Sec. F, preg. (c)";
//			view=txtDN101_6_C;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN101_6_D)){
//			mensaje="Debe registrar dato en Sec. F, preg. (d)";
//			view=txtDN101_6_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_6_E)){
//			mensaje="Debe registrar dato en Sec. F, preg. (e)";
//			view=txtDN101_6_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_6_F)){
//			mensaje="Debe registrar dato en Sec. F, preg. (f)";
//			view=txtDN101_6_F;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_7)){
//			mensaje="Debe registrar dato en Sec. G";
//			view=txtDN101_7;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_8)){
//			mensaje="Debe registrar dato en Sec. H";
//			view=txtDN101_8;
//			error=true;
//			return false;	
//		}
//		
//		/*Capitulo 100 -101 (B)- MODALIDADES. */
//
//		if(Util.esVacio(txtDN101_9_A)){
//			mensaje="Debe registrar dato en Sec. I, preg. (a)";
//			view=txtDN101_9_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_9_B)){
//			mensaje="Debe registrar dato en Sec. I, preg. (b)";
//			view=txtDN101_9_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_9_C)){
//			mensaje="Debe registrar dato en Sec. I, preg. (c)";
//			view=txtDN101_9_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_9_D)){
//			mensaje="Debe registrar dato en Sec. I, preg. (d)";
//			view=txtDN101_9_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_9_E)){
//			mensaje="Debe registrar dato en Sec. I, preg. (e)";
//			view=txtDN101_9_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_10_A)){
//			mensaje="Debe registrar dato en Sec. J, preg. (a)";
//			view=txtDN101_10_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_10_B)){
//			mensaje="Debe registrar dato en Sec. J, preg. (b)";
//			view=txtDN101_10_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_10_C)){
//			mensaje="Debe registrar dato en Sec. J, preg. (c)";
//			view=txtDN101_10_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_10_D)){
//			mensaje="Debe registrar dato en Sec. J, preg. (d)";
//			view=txtDN101_10_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN101_10_E)){
//			mensaje="Debe registrar dato en Sec. J, preg. (e)";
//			view=txtDN101_10_E;
//			error=true;
//			return false;	
//		}

		if(error) return false;	
		return true;
	}

	@Override
	public void cargarDatos() {
		check = false;
		cap100Delitos = getMarcoService().getC100(App.getInstance().getComisaria(),
				new Cap100Delitos().getSecCap(getListFields(this, new String[]{"SUM_FALLECIDOS", "OBS_03_100"})));
		if (cap100Delitos == null) {
			cap100Delitos = new Cap100Delitos();
			cap100Delitos.id_n = App.getInstance().getComisaria().id_n;
		} 
	
		entityToUI(cap100Delitos);
		inicio();
	}

	private void inicio() {
		txtDN101_6_A.requestFocus();
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
