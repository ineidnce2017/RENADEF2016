package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DateTimeField;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.SpinnerField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.util.Filtros;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil;
import gob.inei.renadef2016.fragments.C3VISITAS_Fragment_S3;
import gob.inei.renadef2016.fragments.C3VISITAS_Fragment_S3.ACCION;
import gob.inei.renadef2016.modelo.DelVisita;
import gob.inei.renadef2016.service.ATVisitaService;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3VISITAS_Fragment_S3_02 extends DialogFragmentComponent {
	public interface C1CAP200_Fragment_201_01Listener {
		void onFinishEditDialog(String inputText);
	}

	public DateTimeField txtIII_6;
	public IntegerField txtIII_6_D;
	public IntegerField txtIII_6_M;
	public IntegerField txtIII_6_A;
	public DateTimeField txtIII_7A;
	public IntegerField txtIII_7A_H;
	public IntegerField txtIII_7A_M;
	public DateTimeField txtIII_7B;
	public IntegerField txtIII_7B_H;
	public IntegerField txtIII_7B_M;
	@FieldAnnotation(orderIndex = 1)
	public SpinnerField spnIII_8;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtIII_8_O;
	@FieldAnnotation(orderIndex = 3)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 4)
	public ButtonComponent btnCancelar; 
	private static C3VISITAS_Fragment_S3 caller;
	LinearLayout q0, q1, q2, q3, q4, q5;
	private DelVisita detalle;
	private LabelComponent lblTitulo; 
	private LabelComponent lblR;
	private GridComponent2 grid_F;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private ATVisitaService atvisitaService;
	private ACCION accion;

	public static C3VISITAS_Fragment_S3_02 newInstance(FragmentForm pagina,
			DelVisita detalle, ATVisitaService atVisitaService, C3VISITAS_Fragment_S3.ACCION accion) {
		caller = (C3VISITAS_Fragment_S3) pagina;
		Filtros.clear();
		C3VISITAS_Fragment_S3_02 f = new C3VISITAS_Fragment_S3_02();
		f.setParent(pagina);
		f.atvisitaService = atVisitaService;
		f.accion = accion;
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3VISITAS_Fragment_S3_02() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detalle = (DelVisita) getArguments().getSerializable("detalle");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = createUI();
		getDialog().setTitle("Visita N\u00B0: "+detalle.iii_1);
		initObjectsWithoutXML(this, rootView);

		cargarDatos();
		enlazarCajas(this);
		listening();
		
		return rootView;

	}

	private void cargarDatos() {
		entityToUI(detalle);
		inicio();
	}

	private void inicio() {
		if(accion == ACCION.ADD){
			grid_F.hideColumn(2);
			caller.cleanView(txtIII_7B);
			q3.setVisibility(View.GONE);
			btnAceptar.requestFocus();
		} else if(accion == ACCION.EDIT){
			if(!((Object)6).equals(spnIII_8.getSelectedItemKey()))
				txtIII_8_O.setVisibility(View.GONE);
		} else if(accion == ACCION.FINISH){
			txtIII_8_O.setVisibility(View.GONE);
			spnIII_8.requestFocus();
		}
		txtIII_6.readOnly();
		txtIII_7A.readOnly();
		txtIII_7B.readOnly();
	}

	@Override
	protected View createUI() {
		buildFields();

		q0 = createQuestionSection(lblTitulo);
		q2 = createQuestionSection(grid_F.component());
		q3 = createQuestionSection(createLayout());

		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q0);
		form.addView(q2);
		form.addView(q3);
		form.addView(botones);

		return contenedor;
	}
	
	private LinearLayout createLayout(){
		LinearLayout ly = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER, lblR, spnIII_8);
		return createLy(LinearLayout.VERTICAL, Gravity.CENTER, ly, txtIII_8_O);
	}

	@Override
	protected void buildFields() {

		int title = (accion == ACCION.ADD ? R.string.lb_V_Iniciar_sup : (accion == ACCION.EDIT ?
				R.string.lb_V_Editar_sup : R.string.lb_V_Terminar_sup));
		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(title).textSize(21).centrar().negrita();

		LabelComponent lblF = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Fecha_m).size(altoComponente, 170).centrar();
		LabelComponent lblHD = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Hora_m).size(altoComponente, 170).centrar();
		LabelComponent lblHA = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Hora_m_a).size(altoComponente, 170).centrar();
		lblR = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Resultado_m).size(altoComponente, 170);
		
		txtIII_6_D = new IntegerField(getActivity());
		txtIII_6_M = new IntegerField(getActivity());
		txtIII_6_A = new IntegerField(getActivity());
		txtIII_7A_H = new IntegerField(getActivity());
		txtIII_7A_M = new IntegerField(getActivity());
		txtIII_7B_H = new IntegerField(getActivity());
		txtIII_7B_M = new IntegerField(getActivity());
		
		txtIII_6 = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA, true).
				size(altoComponente, 200).dateOrhour(txtIII_6_D, txtIII_6_M, txtIII_6_A).
				setRangoDate("01/01/2017", "31/12/2017");
		txtIII_7A = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.HORA, true).
				size(altoComponente, 200).dateOrhour(txtIII_7A_H, txtIII_7A_M, null);
		txtIII_7B = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.HORA, true).
				size(altoComponente, 200).dateOrhour(txtIII_7B_H, txtIII_7B_M, null);
		txtIII_8_O = new TextField(getActivity()).size(altoComponente, 520);

		grid_F = new GridComponent2(getActivity(), 3).colorFondo(R.color.blanco);
		grid_F.addComponent(lblF);
		grid_F.addComponent(lblHD);
		grid_F.addComponent(lblHA);
		grid_F.addComponent(txtIII_6);
		grid_F.addComponent(txtIII_7A);
		grid_F.addComponent(txtIII_7B);
		
		spnIII_8 = new SpinnerField(getActivity()).size(
				altoComponente + 15, 350).callback("on_RVISITAChangeValue");
		MyUtil.llenarItems(getActivity(), spnIII_8, R.array.arrayResultadosDE);

		btnAceptar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnAceptar).size(200, 60);
		btnCancelar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnCancelar).size(200, 60);
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				C3VISITAS_Fragment_S3_02.this.dismiss();
			}
		});
		btnAceptar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean flag = grabar();
				if (!flag) {
					return;
				}
				C3VISITAS_Fragment_S3_02.this.dismiss();
			}
		});
	}
	
	public void on_RVISITAChangeValue(FieldComponent component){
		String resultadoStr = (String) component.getValue();
		if (resultadoStr.substring(0, 1).equals("6")) {
			caller.lockView(false, txtIII_8_O);
			txtIII_8_O.setVisibility(View.VISIBLE);
			txtIII_8_O.requestFocus();
		} else {
			caller.cleanAndLockView(txtIII_8_O);
			txtIII_8_O.setVisibility(View.GONE);
		}
	}

	private boolean validar() {
		String mensaje = "";
		boolean error = false;
		View view = null;
		
		if(accion == ACCION.ADD){
			if(!txtIII_6.isInRange()) return false;
		}
		else if(accion != ACCION.ADD){
			if( spnIII_8.getSelectedItemKey() == null ){
				mensaje = "Debe indicar el resultado de la visita";
				error = true;
				view = spnIII_8;
			}
		}
		
		if(error){
			if(!mensaje.equals("")) ToastMessage.msgBox(this.getActivity(), mensaje,
					ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
			if(view != null) view.requestFocus();
			return false;
		}

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
			if(!atvisitaService.saveOrUpdate(detalle, detalle.getSecCap(caller.getListFields(this, new String[]{"III_1"})))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			} else {
				caller.reloadData(detalle, 1);
			}
		} catch (Exception e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			return false;
		}
		
		
//		uiToEntity(detalle);
//		if(!atvisitaService.guardarVisita(detalle, Utilidades.getListFields(this, new String[]{"III2_1"}))){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
//		else {
//			caller.reloadData(detalle, 1);
//		}
		
		return true;
	}
}
