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
public class C3CAP100_Fragment_102 extends FragmentForm implements Respondible {
	
	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtDN102;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtDN102_A;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtDN103;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtDN103_A;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtDN103_B;
	@FieldAnnotation(orderIndex = 6)
	public IntegerField txtDN103_C;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtDN103_D;
	@FieldAnnotation(orderIndex = 8)
	public IntegerField txtDN103_E;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtDN104;
	@FieldAnnotation(orderIndex = 10)
	public IntegerField txtDN104_A;
	@FieldAnnotation(orderIndex = 11)
	public IntegerField txtDN104_B;
	@FieldAnnotation(orderIndex = 12)
	public IntegerField txtDN104_C;
	@FieldAnnotation(orderIndex = 13)
	public IntegerField txtDN104_D;
	@FieldAnnotation(orderIndex = 14)
	public IntegerField txtDN104_E;
	@FieldAnnotation(orderIndex = 15)
	public IntegerField txtDN104_F;
	@FieldAnnotation(orderIndex = 16)
	public IntegerField txtDN104_G;
	@FieldAnnotation(orderIndex = 17)
	public IntegerField txtDN104_H;
	@FieldAnnotation(orderIndex = 18)
	public IntegerField txtDN104_I;
	@FieldAnnotation(orderIndex = 19)
	public IntegerField txtDN104_J;
	@FieldAnnotation(orderIndex = 20)
	public IntegerField txtDN104_K;
	@FieldAnnotation(orderIndex = 21)
	public IntegerField txtDN104_L;

	private LabelComponent lblTitulo, lblSubTitulo;
	LinearLayout q0, q1, q2, q3;
	private int pleft = 45, psleft = 55;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private Cap100Delitos cap100delitos;
	private GridComponent2 gridDenuncias;

	public C3CAP100_Fragment_102() {
	}

	public C3CAP100_Fragment_102 parent(MasterActivity parent) {
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
		LabelComponent lblP102 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p102)
		.textSize(16).negrita();
		txtDN102 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP102A = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p102a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN102_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP103 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p103)
		.textSize(16).negrita();
		txtDN103 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP103A = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p103a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN103_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP103B = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p103b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN103_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP103C = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p103c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN103_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP103D = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p103d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN103_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP103E = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p103e)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN103_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p104)
		.textSize(16).negrita();
		txtDN104 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP104A = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p104a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104B = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p104b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p104c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104D = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p104d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104E = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p104e)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104F = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p104f)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_F = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104G = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p104g)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_G = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104H = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p104h)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_H = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104I = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p104i)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_I = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104J = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p104j)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_J = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104K = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p104k)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_K = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP104L = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p104l)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN104_L = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
//		Calculo<IntegerField> op = new Calculo<IntegerField>(this, txtDN102, 0, 0, 
//				Util.getListList(txtDN102_A));
//		op.setConfOperacion();
//		
//		Calculo<IntegerField> op1 = new Calculo<IntegerField>(this, txtDN103, 0, 0, 
//				Util.getListList(txtDN103_A, txtDN103_B, txtDN103_C, txtDN103_D, txtDN103_E));
//		op1.setConfOperacion();
//		
//		Calculo<IntegerField> op2 = new Calculo<IntegerField>(this, txtDN104, 0, 0, 
//				Util.getListList(txtDN104_A, txtDN104_B, txtDN104_C, txtDN104_D, txtDN104_E, txtDN104_F, 
//						txtDN104_G, txtDN104_H, txtDN104_I, txtDN104_J, txtDN104_K, txtDN104_L));
//		op2.setConfOperacion();
		
		gridDenuncias = new GridComponent2(this.getActivity(), 2);
		gridDenuncias.addComponent(lblP102);
		gridDenuncias.addComponent(txtDN102);
		gridDenuncias.addComponent(lblP102A);
		gridDenuncias.addComponent(txtDN102_A);
		gridDenuncias.addComponent(lblP103);
		gridDenuncias.addComponent(txtDN103);
		gridDenuncias.addComponent(lblP103A);
		gridDenuncias.addComponent(txtDN103_A);
		gridDenuncias.addComponent(lblP103B);
		gridDenuncias.addComponent(txtDN103_B);
		gridDenuncias.addComponent(lblP103C);
		gridDenuncias.addComponent(txtDN103_C);
		gridDenuncias.addComponent(lblP103D);
		gridDenuncias.addComponent(txtDN103_D);
		gridDenuncias.addComponent(lblP103E);
		gridDenuncias.addComponent(txtDN103_E);
		gridDenuncias.addComponent(lblP104);
		gridDenuncias.addComponent(txtDN104);
		gridDenuncias.addComponent(lblP104A);
		gridDenuncias.addComponent(txtDN104_A);
		gridDenuncias.addComponent(lblP104B);
		gridDenuncias.addComponent(txtDN104_B);
		gridDenuncias.addComponent(lblP104C);
		gridDenuncias.addComponent(txtDN104_C);
		gridDenuncias.addComponent(lblP104D);
		gridDenuncias.addComponent(txtDN104_D);
		gridDenuncias.addComponent(lblP104E);
		gridDenuncias.addComponent(txtDN104_E);
		gridDenuncias.addComponent(lblP104F);
		gridDenuncias.addComponent(txtDN104_F);
		gridDenuncias.addComponent(lblP104G);
		gridDenuncias.addComponent(txtDN104_G);
		gridDenuncias.addComponent(lblP104H);
		gridDenuncias.addComponent(txtDN104_H);
		gridDenuncias.addComponent(lblP104I);
		gridDenuncias.addComponent(txtDN104_I);
		gridDenuncias.addComponent(lblP104J);
		gridDenuncias.addComponent(txtDN104_J);
		gridDenuncias.addComponent(lblP104K);
		gridDenuncias.addComponent(txtDN104_K);
		gridDenuncias.addComponent(lblP104L);
		gridDenuncias.addComponent(txtDN104_L);
		
		/*Filtros - Capitulo 102 - MODALIDADES.*/

		Filtros.setFiltro(txtDN102_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN103_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN103_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN103_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN103_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN103_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_F, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_G, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_H, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_I, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_J, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_K, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104_L, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
		Filtros.setFiltro(txtDN102, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN103, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN104, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
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
//						App.getInstance().getComisaria().getSecCap(Util.getListList("V3_1")));
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

//		if(Util.esVacio(txtDN102_A)){
//			mensaje="Debe registrar dato en Preg 102. (a)";
//			view=txtDN102_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN103_A)){
//			mensaje="Debe registrar dato en Preg 103. (a)";
//			view=txtDN103_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN103_B)){
//			mensaje="Debe registrar dato en Preg 103. (b)";
//			view=txtDN103_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN103_C)){
//			mensaje="Debe registrar dato en Preg 103. (c)";
//			view=txtDN103_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN103_D)){
//			mensaje="Debe registrar dato en Preg 103. (d)";
//			view=txtDN103_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN103_E)){
//			mensaje="Debe registrar dato en Preg 103. (e)";
//			view=txtDN103_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_A)){
//			mensaje="Debe registrar dato en Preg 104. (a)";
//			view=txtDN104_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_B)){
//			mensaje="Debe registrar dato en Preg 104. (b)";
//			view=txtDN104_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_C)){
//			mensaje="Debe registrar dato en Preg 104. (c)";
//			view=txtDN104_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_D)){
//			mensaje="Debe registrar dato en Preg 104. (d)";
//			view=txtDN104_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_E)){
//			mensaje="Debe registrar dato en Preg 104. (e)";
//			view=txtDN104_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_F)){
//			mensaje="Debe registrar dato en Preg 104. (f)";
//			view=txtDN104_F;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_G)){
//			mensaje="Debe registrar dato en Preg 104. (g)";
//			view=txtDN104_G;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_H)){
//			mensaje="Debe registrar dato en Preg 104. (h)";
//			view=txtDN104_H;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_I)){
//			mensaje="Debe registrar dato en Preg 104. (i)";
//			view=txtDN104_I;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_J)){
//			mensaje="Debe registrar dato en Preg 104. (j)";
//			view=txtDN104_J;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_K)){
//			mensaje="Debe registrar dato en Preg 104. (k)";
//			view=txtDN104_K;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN104_L)){
//			mensaje="Debe registrar dato en Preg 104. (l)";
//			view=txtDN104_L;
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
		txtDN102.requestFocus();
		
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
