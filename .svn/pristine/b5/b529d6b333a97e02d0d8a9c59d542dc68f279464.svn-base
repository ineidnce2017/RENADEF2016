package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.TextAreaField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.util.Filtros;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.activity.CuestionarioFragmentActivity;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP400_Fragment_401;
import gob.inei.renadef2016.modelo.delitos.Cap400Delitos;
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

public class C3CAP400_Fragment_401_Obs extends DialogFragmentComponent {

	@FieldAnnotation(orderIndex=1) 
    public TextAreaField txtOBS_03_400; 
	@FieldAnnotation(orderIndex = 2)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 3)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private LabelComponent lblObs; 
	private static C3CAP400_Fragment_401 caller;
	private Cap400Delitos detalle;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;

	public static C3CAP400_Fragment_401_Obs newInstance(FragmentForm pagina,
			Cap400Delitos detalle, MarcoService marcoService) {
		caller = (C3CAP400_Fragment_401) pagina;
		mimarcoService = marcoService;
		Filtros.clear();
		C3CAP400_Fragment_401_Obs f = new C3CAP400_Fragment_401_Obs();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	} 

	public C3CAP400_Fragment_401_Obs() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detalle = (Cap400Delitos) getArguments().getSerializable("detalle");
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
		
		return C3CAP400_Fragment_401.createTitle(getActivity(), "Victimario N\u00B0: "+detalle.orden_400+
				(C3CAP400_Fragment_401.cap400cap200.ih215==null?"":"/"+C3CAP400_Fragment_401.cap400cap200.ih215),
				C3CAP400_Fragment_401.cap400cap200.getP208CodC(), rootView, btnAtras, btnAdelante);

	}
//////////////////////////////////////////////
	private void cargarDatos() {
		entityToUI(detalle);
		inicio();
	}

	private void inicio() {
		txtOBS_03_400.requestFocus();
	}

	@Override
	protected View createUI() {
		buildFields();
		
		LinearLayout q0 = createQuestionSection(lblObs);
		LinearLayout q1 = createQuestionSection(txtOBS_03_400);

		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q0);
		form.addView(q1);
		
		form.addView(botones);

		return contenedor;
	}

	@Override
	protected void buildFields() {

		lblObs = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				size(altoComponente, sizeWidth).text(R.string.lb_C_OBS).centrar();
		txtOBS_03_400=new TextAreaField(this.getActivity()).maxLength(500).size(500, sizeWidth); 

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
				C3CAP400_Fragment_401_Obs.this.dismiss();
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
				FragmentManager fm = C3CAP400_Fragment_401_Obs.this.getFragmentManager();
				C3CAP400_Fragment_401_03 aperturaDialog = C3CAP400_Fragment_401_03
						.newInstance(caller, detalle, mimarcoService);
				aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
				aperturaDialog.show(fm, "aperturaDialog");
				C3CAP400_Fragment_401_Obs.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		Filtros.setFiltro(txtOBS_03_400, Filtros.TIPO.ALFAN_U, 2000, new char[]{'.', ','});
	}  
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3CAP400_Fragment_401_Obs.this.dismiss();
		if(Integer.valueOf(C3CAP400_Fragment_401.getData().size()).equals(C3CAP400_Fragment_401.cap400cap200.ih215)){
			((FragmentForm)getParent()).getParent().nextFragment(CuestionarioFragmentActivity.CAP400_DEL+1);
		}
	}

	private boolean validar() {
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
			if(!mimarcoService.saveOrUpdate(detalle, detalle.getSecCap(caller.getListFields(this)))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			} else {
				caller.reloadData(detalle, 1);
			}
		} catch (SQLException e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
		}
		
//		uiToEntity(detalle);
//		if(!mimarcoService.saveCap400Delitos(detalle, Utilidades.getListFields(this))){
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


