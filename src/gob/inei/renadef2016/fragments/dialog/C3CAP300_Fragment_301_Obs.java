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
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.activity.CuestionarioFragmentActivity;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP300_Fragment_301;
import gob.inei.renadef2016.modelo.delitos.Cap300Delitos;
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

public class C3CAP300_Fragment_301_Obs extends DialogFragmentComponent {

	@FieldAnnotation(orderIndex=1) 
    public TextAreaField txtOBS_03_300; 
	@FieldAnnotation(orderIndex = 2)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 3)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private LabelComponent lblObs;
	private static C3CAP300_Fragment_301 caller;
	private Cap300Delitos detalle;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService; 
	private boolean flgEstado;

	public static C3CAP300_Fragment_301_Obs newInstance(FragmentForm pagina,
			Cap300Delitos detalle, MarcoService marcoService) {
		caller = (C3CAP300_Fragment_301) pagina;
		mimarcoService = marcoService;
		Filtros.clear();
		C3CAP300_Fragment_301_Obs f = new C3CAP300_Fragment_301_Obs();
		f.setParent(pagina); 
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP300_Fragment_301_Obs() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detalle = (Cap300Delitos) getArguments().getSerializable("detalle");
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
		
		return C3CAP300_Fragment_301.createTitle(getActivity(), "V\u00cdctima/Fallecido N\u00B0: "+detalle.orden_300+ 
				(C3CAP300_Fragment_301.cap300cap200.ih214==null?"":"/"+C3CAP300_Fragment_301.cap300cap200.ih214), 
				C3CAP300_Fragment_301.cap300cap200.getP208CodC(), rootView, btnAtras, btnAdelante);

	}
//////////////////////////////////////////////
	private void cargarDatos() {
//		flgEstado = !Util.esDiferente(C3CAP300_Fragment_301.cap300cap200.getP208(), 73,74,75,76,77,78,79,80,81,82,83,84,85,
//				86,87,88,89,90,91,92,93,94,95,96,97,98,105,106,107,108,109,110);
		flgEstado = !Util.esDiferente(C3CAP300_Fragment_301.cap300cap200.getP208(), 
				/*73*/144,145,146,/*74,75,76,77,78*/147,148,149,150,151,152,153,154,155,156,157,158,
				159,160,161,162,163,/*79,80,81*/164,165,166,167,168,169,170,171,172,173,174,
				/*82,83,84,85*/175,176,177,178,179,180,181,
				/*86,87,88,89,90*/182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,
				/*91,92,93,94,95*/199,200,201,202,203,204,205,206,207,208,
				/*96,97,98*/209,210,211,212,213,214,215,
				/*105,106,107,108*/226,227,228,229,230,231,232,233,234,235,236,237,
				/*109,110*/238,239,240,241);
		entityToUI(detalle);
		inicio();
	}

	private void inicio() {
		txtOBS_03_300.requestFocus();
	}

	@Override
	protected View createUI() {
		buildFields();
		
		LinearLayout q0 = createQuestionSection(lblObs);
		LinearLayout q1 = createQuestionSection(txtOBS_03_300);

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
		txtOBS_03_300=new TextAreaField(this.getActivity()).maxLength(500).size(500, sizeWidth); 

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
				C3CAP300_Fragment_301_Obs.this.dismiss();
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
				FragmentManager fm = C3CAP300_Fragment_301_Obs.this.getFragmentManager();
				if(!Util.esDiferente(detalle.ivh304,1,2) && App.getInstance().getCap200().ih215 == null){
					C3CAP300_Fragment_301_02 aperturaDialog = C3CAP300_Fragment_301_02
							.newInstance(caller, detalle, mimarcoService);
					aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
					aperturaDialog.show(fm, "aperturaDialog");
				} else if(App.ESTADO.equals(detalle.ivh301a) && flgEstado){
					C3CAP300_Fragment_301_01 aperturaDialog = C3CAP300_Fragment_301_01
							.newInstance(caller, detalle, detalle.nro_mreg, mimarcoService);
					aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
					aperturaDialog.show(fm, "aperturaDialog");
				} else {
					C3CAP300_Fragment_301_03 aperturaDialog = C3CAP300_Fragment_301_03
							.newInstance(caller, detalle, mimarcoService);
					aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
					aperturaDialog.show(fm, "aperturaDialog");
				}
				C3CAP300_Fragment_301_Obs.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		Filtros.setFiltro(txtOBS_03_300, Filtros.TIPO.ALFAN_U, 2000, new char[]{'.', ','});
	}  
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3CAP300_Fragment_301_Obs.this.dismiss();
		if(Integer.valueOf(C3CAP300_Fragment_301.getData().size()).equals(C3CAP300_Fragment_301.cap300cap200.ih214)){
//			Globales.getInstance().setVnro_mreg(detalle.nro_mreg);
			((FragmentForm)getParent()).getParent().nextFragment(CuestionarioFragmentActivity.CAP400_DEL);
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
//		if(!mimarcoService.saveCap300Delitos(detalle, Utilidades.getListFields(this))){
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


