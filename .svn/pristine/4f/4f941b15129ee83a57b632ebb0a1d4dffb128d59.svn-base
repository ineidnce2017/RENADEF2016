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
public class C3CAP100_Fragment_108 extends FragmentForm implements Respondible {

	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtDN108;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtDN108_A;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtDN109;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtDN109_A;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtDN109_B;
	@FieldAnnotation(orderIndex = 6)
	public IntegerField txtDN109_C;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtDN109_D;
	@FieldAnnotation(orderIndex = 8)
	public IntegerField txtDN109_E;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtDN110;
	@FieldAnnotation(orderIndex = 10)
	public IntegerField txtDN110_A;
	@FieldAnnotation(orderIndex = 11)
	public IntegerField txtDN110_B;
	@FieldAnnotation(orderIndex = 12)
	public IntegerField txtDN110_C;
	@FieldAnnotation(orderIndex = 13)
	public IntegerField txtDN111;
	@FieldAnnotation(orderIndex = 14)
	public IntegerField txtDN111_A;
	@FieldAnnotation(orderIndex = 15)
	public IntegerField txtDN111_B;
	@FieldAnnotation(orderIndex = 16)
	public IntegerField txtDN111_C;
	@FieldAnnotation(orderIndex = 17)
	public IntegerField txtDN111_D;
	@FieldAnnotation(orderIndex = 18)
	public IntegerField txtDN112;
	@FieldAnnotation(orderIndex = 19)
	public IntegerField txtDN112_A;
	@FieldAnnotation(orderIndex = 20)
	public IntegerField txtDN112_B;
	@FieldAnnotation(orderIndex = 21)
	public IntegerField txtDN112_C;
	@FieldAnnotation(orderIndex = 22)
	public IntegerField txtDN112_D;
	@FieldAnnotation(orderIndex = 23)
	public IntegerField txtDN112_E;

	private LabelComponent lblTitulo, lblSubTitulo;
	private int pleft = 45, psleft = 55;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private Cap100Delitos cap100delitos;
	private GridComponent2 gridDenuncias;

	public C3CAP100_Fragment_108() {
	}

	public C3CAP100_Fragment_108 parent(MasterActivity parent) {
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
		
		LabelComponent lblP108 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p108)
		.textSize(16).negrita();
		txtDN108 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP108A = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p108a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN108_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP109 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p109)
		.textSize(16).negrita();
		txtDN109 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP109A = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p109a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN109_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP109B = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p109b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN109_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP109C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p109c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN109_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP109D = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p109d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN109_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP109E = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p109e)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN109_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
		LabelComponent lblP110 = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p110).
		textSize(16).negrita();
		txtDN110 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP110A = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p110a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN110_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP110B = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p110b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN110_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP110C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p110c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN110_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
		LabelComponent lblP111 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p111)
		.textSize(16).negrita();
		txtDN111 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP111A = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p111a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN111_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP111B = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p111b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN111_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP111C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p111c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN111_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP111D = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p111d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN111_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
		LabelComponent lblP112 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p112)
		.textSize(16).negrita();
		txtDN112 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP112A = new LabelComponent(this.getActivity())
		.size(altoComponente+90, 600).text(R.string.lb_c3_cap100_p112a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN112_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP112B = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p112b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN112_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP112C = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p112c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN112_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP112D = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p112d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN112_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP112E = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p112e)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN112_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
//		Calculo<IntegerField> op = new Calculo<IntegerField>(this, txtDN108, 0, 0, 
//				Util.getListList(txtDN108_A));
//		op.setConfOperacion();
//		
//		Calculo<IntegerField> op1 = new Calculo<IntegerField>(this, txtDN109, 0, 0, 
//				Util.getListList(txtDN109_A, txtDN109_B, txtDN109_C, txtDN109_D, txtDN109_E));
//		op1.setConfOperacion();
//		
//		Calculo<IntegerField> op2 = new Calculo<IntegerField>(this, txtDN110, 0, 0, 
//				Util.getListList(txtDN110_A, txtDN110_B, txtDN110_C));
//		op2.setConfOperacion();
//		
//		Calculo<IntegerField> op3 = new Calculo<IntegerField>(this, txtDN111, 0, 0, 
//				Util.getListList(txtDN111_A, txtDN111_B, txtDN111_C, txtDN111_D));
//		op3.setConfOperacion();
//		
//		Calculo<IntegerField> op4 = new Calculo<IntegerField>(this, txtDN112, 0, 0, 
//				Util.getListList(txtDN112_A, txtDN112_B, txtDN112_C, txtDN112_D, txtDN112_E));
//		op4.setConfOperacion();
		
		gridDenuncias = new GridComponent2(this.getActivity(), 2);
		gridDenuncias.addComponent(lblP108);
		gridDenuncias.addComponent(txtDN108);
		gridDenuncias.addComponent(lblP108A);
		gridDenuncias.addComponent(txtDN108_A);
		gridDenuncias.addComponent(lblP109);
		gridDenuncias.addComponent(txtDN109);
		gridDenuncias.addComponent(lblP109A);
		gridDenuncias.addComponent(txtDN109_A);
		gridDenuncias.addComponent(lblP109B);
		gridDenuncias.addComponent(txtDN109_B);
		gridDenuncias.addComponent(lblP109C);
		gridDenuncias.addComponent(txtDN109_C);
		gridDenuncias.addComponent(lblP109D);
		gridDenuncias.addComponent(txtDN109_D);
		gridDenuncias.addComponent(lblP109E);
		gridDenuncias.addComponent(txtDN109_E);
		gridDenuncias.addComponent(lblP110);
		gridDenuncias.addComponent(txtDN110);
		gridDenuncias.addComponent(lblP110A);
		gridDenuncias.addComponent(txtDN110_A);
		gridDenuncias.addComponent(lblP110B);
		gridDenuncias.addComponent(txtDN110_B);
		gridDenuncias.addComponent(lblP110C);
		gridDenuncias.addComponent(txtDN110_C);
		gridDenuncias.addComponent(lblP111);
		gridDenuncias.addComponent(txtDN111);
		gridDenuncias.addComponent(lblP111A);
		gridDenuncias.addComponent(txtDN111_A);
		gridDenuncias.addComponent(lblP111B);
		gridDenuncias.addComponent(txtDN111_B);
		gridDenuncias.addComponent(lblP111C);
		gridDenuncias.addComponent(txtDN111_C);
		gridDenuncias.addComponent(lblP111D);
		gridDenuncias.addComponent(txtDN111_D);
		gridDenuncias.addComponent(lblP112);
		gridDenuncias.addComponent(txtDN112);
		gridDenuncias.addComponent(lblP112A);
		gridDenuncias.addComponent(txtDN112_A);
		gridDenuncias.addComponent(lblP112B);
		gridDenuncias.addComponent(txtDN112_B);
		gridDenuncias.addComponent(lblP112C);
		gridDenuncias.addComponent(txtDN112_C);
		gridDenuncias.addComponent(lblP112D);
		gridDenuncias.addComponent(txtDN112_D);
		gridDenuncias.addComponent(lblP112E);
		gridDenuncias.addComponent(txtDN112_E);
		
		/*Filtros - Capitulo 102 - MODALIDADES.*/

		Filtros.setFiltro(txtDN108_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN109_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN109_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN109_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN109_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN109_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN110_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN110_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN110_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN111_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN111_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN111_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN111_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN112_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN112_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN112_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN112_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN112_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
		Filtros.setFiltro(txtDN108, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN109, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN110, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN111, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN112, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
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
		LinearLayout q0 = createQuestionSection(lblTitulo, lblSubTitulo,
				gridDenuncias.component());
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

//		if(Util.esVacio(txtDN108_A)){
//			mensaje="Debe registrar dato en Preg 108. (a)";
//			view=txtDN108_A;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN109_A)){
//			mensaje="Debe registrar dato en Preg 109. (a)";
//			view=txtDN109_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN109_B)){
//			mensaje="Debe registrar dato en Preg 109. (b)";
//			view=txtDN109_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN109_C)){
//			mensaje="Debe registrar dato en Preg 109. (c)";
//			view=txtDN109_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN109_D)){
//			mensaje="Debe registrar dato en Preg 109. (d)";
//			view=txtDN109_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN109_E)){
//			mensaje="Debe registrar dato en Preg 109. (e)";
//			view=txtDN109_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN110_A)){
//			mensaje="Debe registrar dato en Preg 110. (a)";
//			view=txtDN110_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN110_B)){
//			mensaje="Debe registrar dato en Preg 110. (b)";
//			view=txtDN110_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN110_C)){
//			mensaje="Debe registrar dato en Preg 110. (c)";
//			view=txtDN110_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN111_A)){
//			mensaje="Debe registrar dato en Preg 111. (a)";
//			view=txtDN111_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN111_B)){
//			mensaje="Debe registrar dato en Preg 111. (b)";
//			view=txtDN111_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN111_C)){
//			mensaje="Debe registrar dato en Preg 111. (c)";
//			view=txtDN111_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN111_D)){
//			mensaje="Debe registrar dato en Preg 111. (d)";
//			view=txtDN111_D;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN112_A)){
//			mensaje="Debe registrar dato en Preg 112. (a)";
//			view=txtDN112_A;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN112_B)){
//			mensaje="Debe registrar dato en Preg 112. (b)";
//			view=txtDN112_B;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN112_C)){
//			mensaje="Debe registrar dato en Preg 112. (c)";
//			view=txtDN112_C;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN112_D)){
//			mensaje="Debe registrar dato en Preg 112. (d)";
//			view=txtDN112_D;
//			error=true;
//			return false;
//		}
//		if(Util.esVacio(txtDN112_E)){
//			mensaje="Debe registrar dato en Preg 112. (e)";
//			view=txtDN112_E;
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
		txtDN108_A.requestFocus();
		
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
