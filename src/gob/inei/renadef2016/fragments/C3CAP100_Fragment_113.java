package gob.inei.renadef2016.fragments;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Filtros;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3CAP100_Fragment_113 extends FragmentForm implements Respondible {

	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtDN113;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtDN113_A;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtDN113_B;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtDN113_C;
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtDN113_D;
	@FieldAnnotation(orderIndex = 6)
	public IntegerField txtDN113_E;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtDN114;
	@FieldAnnotation(orderIndex = 8)
	public IntegerField txtDN114_A;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtDN114_B;
	@FieldAnnotation(orderIndex = 10)
	public IntegerField txtDN114_C;
	@FieldAnnotation(orderIndex = 11)
	public IntegerField txtDN115;
	@FieldAnnotation(orderIndex = 12)
	public IntegerField txtDN115_A;
	@FieldAnnotation(orderIndex = 13)
	public IntegerField txtDN115_B;
	@FieldAnnotation(orderIndex = 14)
	public IntegerField txtDN115_C;
	@FieldAnnotation(orderIndex = 15)
	public IntegerField txtDN115_D;
	@FieldAnnotation(orderIndex = 16)
	public IntegerField txtDN115_E;
	@FieldAnnotation(orderIndex = 17)
	public IntegerField txtDN115_F;
	@FieldAnnotation(orderIndex = 18)
	public IntegerField txtDN116;
	@FieldAnnotation(orderIndex = 19)
	public IntegerField txtDN116_A;
	@FieldAnnotation(orderIndex = 20)
	public IntegerField txtDN116_B;
	@FieldAnnotation(orderIndex = 21)
	public IntegerField txtDN116_C;
	@FieldAnnotation(orderIndex = 22)
	public IntegerField txtDN116_D;

	private LabelComponent lblTitulo, lblSubTitulo;
	private int pleft = 45, psleft = 55;
	private GridComponent2 gridDenuncias;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private Cap100Delitos cap100delitos;

	public C3CAP100_Fragment_113() {
	}

	public C3CAP100_Fragment_113 parent(MasterActivity parent) {
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
		
		LabelComponent lblP113 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p113)
		.textSize(16).negrita();
		txtDN113 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP113A = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p113a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN113_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP113B = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p113b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN113_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP113C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p113c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN113_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP113D = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p113d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN113_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP113E = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p113e)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN113_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP114 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p114)
		.textSize(16).negrita();
		txtDN114 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP114A = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p114a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN114_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP114B = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p114b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN114_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP114C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p114c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN114_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP115 = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p115)
		.textSize(16).negrita();
		txtDN115 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP115A = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p115a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN115_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP115B = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p115b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN115_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP115C = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p115c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN115_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP115D = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p115d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN115_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP115E = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p115e)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN115_E = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP115F = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p115f)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN115_F = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP116 = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p116)
		.textSize(16).negrita();
		txtDN116 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().negrita().centrar();
		LabelComponent lblP116A = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p116a)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN116_A = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP116B = new LabelComponent(this.getActivity())
		.size(altoComponente+58, 600).text(R.string.lb_c3_cap100_p116b)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN116_B = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP116C = new LabelComponent(this.getActivity())
		.size(altoComponente+10, 600).text(R.string.lb_c3_cap100_p116c)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN116_C = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		LabelComponent lblP116D = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p116d)
		.textSize(16).padding(psleft, 0, 0, 0);
		txtDN116_D = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).readOnly().centrar();
		
//		Calculo<IntegerField> op = new Calculo<IntegerField>(this, txtDN113, 0, 0, 
//				Util.getListList(txtDN113_A, txtDN113_B, txtDN113_C, txtDN113_D, txtDN113_E));
//		op.setConfOperacion();
//		
//		Calculo<IntegerField> op1 = new Calculo<IntegerField>(this, txtDN114, 0, 0, 
//				Util.getListList(txtDN114_A, txtDN114_B, txtDN114_C));
//		op1.setConfOperacion();
//		
//		Calculo<IntegerField> op2 = new Calculo<IntegerField>(this, txtDN115, 0, 0, 
//				Util.getListList(txtDN115_A, txtDN115_B, txtDN115_C, txtDN115_D, txtDN115_E, txtDN115_F));
//		op2.setConfOperacion();
//		
//		Calculo<IntegerField> op3 = new Calculo<IntegerField>(this, txtDN116, 0, 0, 
//				Util.getListList(txtDN116_A, txtDN116_B, txtDN116_C, txtDN116_D));
//		op3.setConfOperacion();
		
		gridDenuncias = new GridComponent2(this.getActivity(), 2);
		gridDenuncias.addComponent(lblP113);
		gridDenuncias.addComponent(txtDN113);
		gridDenuncias.addComponent(lblP113A);
		gridDenuncias.addComponent(txtDN113_A);
		gridDenuncias.addComponent(lblP113B);
		gridDenuncias.addComponent(txtDN113_B);
		gridDenuncias.addComponent(lblP113C);
		gridDenuncias.addComponent(txtDN113_C);
		gridDenuncias.addComponent(lblP113D);
		gridDenuncias.addComponent(txtDN113_D);
		gridDenuncias.addComponent(lblP113E);
		gridDenuncias.addComponent(txtDN113_E);
		gridDenuncias.addComponent(lblP114);
		gridDenuncias.addComponent(txtDN114);
		gridDenuncias.addComponent(lblP114A);
		gridDenuncias.addComponent(txtDN114_A);
		gridDenuncias.addComponent(lblP114B);
		gridDenuncias.addComponent(txtDN114_B);
		gridDenuncias.addComponent(lblP114C);
		gridDenuncias.addComponent(txtDN114_C);
		gridDenuncias.addComponent(lblP115);
		gridDenuncias.addComponent(txtDN115);
		gridDenuncias.addComponent(lblP115A);
		gridDenuncias.addComponent(txtDN115_A);
		gridDenuncias.addComponent(lblP115B);
		gridDenuncias.addComponent(txtDN115_B);
		gridDenuncias.addComponent(lblP115C);
		gridDenuncias.addComponent(txtDN115_C);
		gridDenuncias.addComponent(lblP115D);
		gridDenuncias.addComponent(txtDN115_D);
		gridDenuncias.addComponent(lblP115E);
		gridDenuncias.addComponent(txtDN115_E);
		gridDenuncias.addComponent(lblP115F);
		gridDenuncias.addComponent(txtDN115_F);
		gridDenuncias.addComponent(lblP116);
		gridDenuncias.addComponent(txtDN116);
		gridDenuncias.addComponent(lblP116A);
		gridDenuncias.addComponent(txtDN116_A);
		gridDenuncias.addComponent(lblP116B);
		gridDenuncias.addComponent(txtDN116_B);
		gridDenuncias.addComponent(lblP116C);
		gridDenuncias.addComponent(txtDN116_C);
		gridDenuncias.addComponent(lblP116D);
		gridDenuncias.addComponent(txtDN116_D);
		
		/*Filtros - 112 - MODALIDADES.*/
		
		Filtros.setFiltro(txtDN113_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN113_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN113_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN113_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN113_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN114_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN114_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN114_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN115_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN115_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN115_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN115_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN115_E, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN115_F, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN116_A, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN116_B, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN116_C, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN116_D, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
		Filtros.setFiltro(txtDN113, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN114, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN115, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtDN116, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		
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
//			if(!getMarcoService().saveOrUpdate(cap100delitos, cap100delitos.getSecCap(getListFields(this)))){
//				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//						ToastMessage.MESSAGE_ERROR,
//						ToastMessage.DURATION_LONG);
//			} else {
//				App.getInstance().getComisaria().v3_1 = cap100delitos.dn120;
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

//		if(Util.esVacio(txtDN113_A)){
//			mensaje="Debe registrar dato en Preg 113. (a)";
//			view=txtDN113_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN113_B)){
//			mensaje="Debe registrar dato en Preg 113. (b)";
//			view=txtDN113_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN113_C)){
//			mensaje="Debe registrar dato en Preg 113. (c)";
//			view=txtDN113_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN113_D)){
//			mensaje="Debe registrar dato en Preg 113. (d)";
//			view=txtDN113_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN113_E)){
//			mensaje="Debe registrar dato en Preg 113. (e)";
//			view=txtDN113_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN114_A)){
//			mensaje="Debe registrar dato en Preg 114. (a)";
//			view=txtDN114_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN114_B)){
//			mensaje="Debe registrar dato en Preg 114. (b)";
//			view=txtDN114_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN114_C)){
//			mensaje="Debe registrar dato en Preg 114. (c)";
//			view=txtDN114_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN115_A)){
//			mensaje="Debe registrar dato en Preg 115. (a)";
//			view=txtDN115_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN115_B)){
//			mensaje="Debe registrar dato en Preg 115. (b)";
//			view=txtDN115_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN115_C)){
//			mensaje="Debe registrar dato en Preg 115. (c)";
//			view=txtDN115_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN115_D)){
//			mensaje="Debe registrar dato en Preg 115. (d)";
//			view=txtDN115_D;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN115_E)){
//			mensaje="Debe registrar dato en Preg 115. (e)";
//			view=txtDN115_E;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN115_F)){
//			mensaje="Debe registrar dato en Preg 115. (f)";
//			view=txtDN115_F;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN116_A)){
//			mensaje="Debe registrar dato en Preg 116. (a)";
//			view=txtDN116_A;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN116_B)){
//			mensaje="Debe registrar dato en Preg 116. (b)";
//			view=txtDN116_B;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN116_C)){
//			mensaje="Debe registrar dato en Preg 116. (c)";
//			view=txtDN116_C;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtDN116_D)){
//			mensaje="Debe registrar dato en Preg 116. (d)";
//			view=txtDN116_D;
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
		txtDN113_A.requestFocus();
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
