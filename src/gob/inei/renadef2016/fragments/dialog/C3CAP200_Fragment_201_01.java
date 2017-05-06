package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP200_Fragment_201;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;

import java.sql.SQLException;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3CAP200_Fragment_201_01 extends DialogFragmentComponent {
	public interface C1CAP200_Fragment_201_01Listener {
		void onFinishEditDialog(String inputText);
	}

//	@FieldAnnotation(orderIndex = 1)
//	public RadioGroupOtherField rgIH201;
//	@FieldAnnotation(orderIndex = 2)
//	public TextField txtIH201_O;
	@FieldAnnotation(orderIndex = 1)
	public TextField txtIH201_NRO_DOC;
	@FieldAnnotation(orderIndex = 2)
	public RadioGroupOtherField rgIH202;
	@FieldAnnotation(orderIndex = 3)
	public TextField txtIH202_O;
//	@FieldAnnotation(orderIndex = 5)
//	public RadioGroupOtherField rgIH203; 
//	@FieldAnnotation(orderIndex = 6)
//	public TextField txtIH203_O; 
//	@FieldAnnotation(orderIndex = 7)
//	public TextField txtIH203_NRO_DOC;
	@FieldAnnotation(orderIndex = 4)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 5)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private LabelComponent lblP203Ndoc;
	private static C3CAP200_Fragment_201 caller;
	private static Integer nroDen;
	LinearLayout q0, q1, q2;
	private Cap200Delitos detalle;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;
	private static INF_Caratula01Service caratulaService;

	public static C3CAP200_Fragment_201_01 newInstance(FragmentForm pagina,
			Cap200Delitos detalle, Integer nro, MarcoService marcoService, INF_Caratula01Service caratService) {
		caller = (C3CAP200_Fragment_201) pagina;
		mimarcoService = marcoService;
		caratulaService = caratService;
		nroDen = nro;
		Filtros.clear();
		C3CAP200_Fragment_201_01 f = new C3CAP200_Fragment_201_01();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP200_Fragment_201_01() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detalle = (Cap200Delitos) getArguments().getSerializable("detalle");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = createUI();
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getDialog().setTitle("Denuncia N\u00B0: "+detalle.nro_mreg);
		initObjectsWithoutXML(this, rootView);

		cargarDatos();
		enlazarCajas(this);
		listening();
		
		return C3CAP200_Fragment_201.createTitle(getActivity(), "Denuncia N\u00B0: "+detalle.orden_200, 
				detalle.getP208CodC(), rootView, btnAtras, btnAdelante);
	}

	private void cargarDatos() {
		entityToUI(detalle);
		inicio();
	}

	private void inicio() {
//		rgIH201.requestFocus();
		txtIH201_NRO_DOC.requestFocus();
	}

	@Override
	protected View createUI() {
		buildFields();
		
//		q0 = createQuestionSection(R.string.lb_c3_cap200_p201, rgIH201);
		q0 = createQuestionSection(R.string.lb_c3_cap200_p201, txtIH201_NRO_DOC);
		q1 = createQuestionSection(R.string.lb_c3_cap200_p202, rgIH202);
//		q2 = createQuestionSection(R.string.lb_c3_cap200_p203, rgIH203, 
//				createLy(LinearLayout.HORIZONTAL, Gravity.CENTER_VERTICAL|Gravity.LEFT, 
//						20, 0, 20, lblP203Ndoc, txtIH203_NRO_DOC));

		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
		
		form.addView(q0);
		form.addView(q1);
//		form.addView(q2);

		form.addView(botones);

		return contenedor;
	}

	@Override
	protected void buildFields() {
		
		lblP203Ndoc = new LabelComponent(getActivity()).text(R.string.lb_c3_cap200_p203_Ndoc).negrita();
		
//		txtIH201_O = new TextField(getActivity(),false).size(altoComponente, 400);
		txtIH201_NRO_DOC = new TextField(getActivity(), false).size(altoComponente, 300);
		txtIH202_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH203_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH203_NRO_DOC = new TextField(getActivity(), false).size(altoComponente, 300);
		
//		rgIH201 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap200_p201_1,
//				R.string.lb_c3_cap200_p201_2, R.string.lb_c3_cap200_p201_3, R.string.lb_c3_cap200_p201_4).
//				size(sizeHeigth, sizeWidth).orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
//		rgIH201.agregarEspecifique(3, txtIH201_O);
//		rgIH202 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap200_p201_1,
//				R.string.lb_c3_cap200_p201_2, R.string.lb_c3_cap200_p201_3, R.string.lb_c3_cap200_p201_4).
//				size(sizeHeigth, sizeWidth).orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
//		rgIH202.agregarEspecifique(3, txtIH202_O);
		rgIH202 = new RadioGroupOtherField(this.getActivity(), /*R.string.lb_c3_cap200_p203_1,*/
				R.string.lb_c3_cap200_p203_2, R.string.lb_c3_cap200_p203_3, R.string.lb_c3_cap200_p203_4,
				R.string.lb_c3_cap200_p203_5, R.string.lb_c3_cap200_p203_6, R.string.lb_c3_cap200_p203_7,
				R.string.lb_c3_cap200_p203_8, /*R.string.lb_c3_cap200_p203_9, R.string.lb_c3_cap200_p203_10,
				R.string.lb_c3_cap200_p203_11, R.string.lb_c3_cap200_p203_12,*/ R.string.lb_c3_cap200_p203_13).
				size(sizeHeigth, sizeWidth).orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
//		rgIH202.agregarEspecifique(12, txtIH202_O);
		rgIH202.agregarEspecifique(7, txtIH202_O);

		btnAceptar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnAceptar).size(200, 60);
		btnCancelar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnCancelar).size(200, 60);
		btnAtras = new ImageComponent(getParent().getActivity(), R.drawable.previous, R.drawable.previous).
				size(65, 65);
		btnAdelante = new ImageComponent(getParent().getActivity(), R.drawable.next, R.drawable.next).
				size(65, 65);
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				C3CAP200_Fragment_201_01.this.dismiss();
			}
		});
		btnAceptar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		btnAtras.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				C3CAP200_Fragment_201_01.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		Filtros.setFiltro(txtIH201_NRO_DOC, Filtros.TIPO.ALFAN_U, 20,new char[]{'/', '-'});
//		Filtros.setFiltro(txtIH201_O, Filtros.TIPO.ALFAN_U, 50, null);
		Filtros.setFiltro(txtIH202_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH203_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH203_NRO_DOC, Filtros.TIPO.ALFAN_U, 20,new char[]{'/', '-'});
	}
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3CAP200_Fragment_201_01.this.dismiss();
		
		FragmentManager fm = caller.getFragmentManager();
		C3CAP200_Fragment_201_01_1 aperturaDialog = C3CAP200_Fragment_201_01_1.
				newInstance(caller, detalle, mimarcoService, caratulaService);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}

	private boolean validar() {
		error = false;
		
		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
//		INF_Caratula01 caratula = caratulaService.getINFCaratula(Globales.getInstance().getVid_np(), 
//				Utilidades.getListFields(new String[]{"II4"}));
//		INF_Caratula01 caratula = App.getInstance().getComisaria();
		if(Util.esVacio(txtIH201_NRO_DOC)){
			mensaje="La pregunta 201 no puede estar Vacia";
			view=txtIH201_NRO_DOC;
			error=true;
			return false;
		} else {
			String txt = txtIH201_NRO_DOC.getText().toString().trim();
			if((Util.contDigits(txt) == txt.length() && Util.sumDigits(txt) == 0) 
					|| txt.equals("SN") || txt.equals("S/N") || txt.equals("99999")){
				mensaje="Verifique informacion en N° de Documento";
				view=txtIH201_NRO_DOC;
				error=true;
				return false;
			}
//			if(Integer.valueOf(1).equals(caratula.ii4) && rgIH201.isTagSelectedBetween(new Integer[]{2,3})){
//				mensaje="Tipo de dependencia policial de la car\u00e1tula no se relaciona con "
//						+ "unidad que interviene.";
//				view=rgIH201;
//				error=true;
//				return false;	
//			}
		}
		
//		if(rgIH201.getTagSelected("").toString().equals("4") && Util.esVacio(txtIH201_O)){
//			mensaje="Especifique no puede estar vacio";
//			view=txtIH201_O;
//			error=true;
//			return false;	
//		}

		if(Util.esVacio(rgIH202)){
			mensaje="La pregunta 202 no puede estar Vacia";
			view=rgIH202;
			error=true;
			return false;
		} else if(rgIH202.isTagSelected(13) && Util.esVacio(txtIH202_O)){
			mensaje="Especifique no puede estar vacio";
			view=txtIH202_O;
			error=true;
			return false;	
		}
	//
//		if(Util.esVacio(rgIH203)){
//			mensaje="La pregunta 203 no puede estar Vacia";
//			view=rgIH203;
//			error=true;
//			return false;
//		}
//		else if(rgIH203.getTagSelected("").toString().equals("12") && Util.esVacio(txtIH203_O)){
//			mensaje="Especifique no puede estar vacio";
//			view=txtIH203_O;
//			error=true;
//			return false;			
//		}
	//
//		if(Util.esVacio(txtIH203_NRO_DOC)){
//			mensaje="N° de Documento no puede estar Vacio";
//			view=txtIH203_NRO_DOC;
//			error=true;
//			return false;
//		} else {
//			String txt = txtIH203_NRO_DOC.getText().toString().trim();
//			if((Util.contDigits(txt) == txt.length() && Util.sumDigits(txt) == 0) 
//					|| txt.equals("SN") || txt.equals("S/N") || txt.equals("99999")){
//				mensaje="Verifique informacion en N° de Documento";
//				view=txtIH203_NRO_DOC;
//				error=true;
//				return false;
//			}
//		}
	
		if(error) return false;	
		return true;
		
	}

	private boolean grabar() {
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
		
		uiToEntity(detalle);
		try {
			if(!mimarcoService.saveOrUpdate(detalle, detalle.getSecCap(caller.getListFields(this,
					new String[]{"NRO_MREG", "ORDEN_200"})))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			} else {
				caller.reloadData(detalle, 1);	
			}
		} catch (SQLException e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			return false;
		}
		
		return true;
	}
}
