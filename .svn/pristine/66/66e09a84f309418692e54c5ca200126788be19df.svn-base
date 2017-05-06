package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.CheckBoxField;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP200_Fragment_201;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3CAP200_Fragment_201_04 extends DialogFragmentComponent {
	public interface C1CAP200_Fragment_201_01Listener {
		void onFinishEditDialog(String inputText);
	}

	@FieldAnnotation(orderIndex = 1)
	public CheckBoxField chbIH211_1;
	@FieldAnnotation(orderIndex = 2)
	public CheckBoxField chbIH211_2;
	@FieldAnnotation(orderIndex = 3)
	public CheckBoxField chbIH211_3;
	@FieldAnnotation(orderIndex = 4)
	public CheckBoxField chbIH211_4;
	@FieldAnnotation(orderIndex = 5) 
	public CheckBoxField chbIH211_5;
	@FieldAnnotation(orderIndex = 6)
	public CheckBoxField chbIH211_6;
	@FieldAnnotation(orderIndex = 7)
	public CheckBoxField chbIH211_7;
	@FieldAnnotation(orderIndex = 8)
	public CheckBoxField chbIH211_8; 
	@FieldAnnotation(orderIndex = 9)
	public CheckBoxField chbIH211_9;
	@FieldAnnotation(orderIndex = 10)
	public CheckBoxField chbIH211_10;
	@FieldAnnotation(orderIndex = 11)
	public CheckBoxField chbIH211_11;
	@FieldAnnotation(orderIndex = 12)
	public CheckBoxField chbIH211_12;
	@FieldAnnotation(orderIndex = 13)
	public CheckBoxField chbIH211_13;
	@FieldAnnotation(orderIndex = 14)
	public CheckBoxField chbIH211_14;
	@FieldAnnotation(orderIndex = 15)
	public CheckBoxField chbIH211_15;
	@FieldAnnotation(orderIndex = 16)
	public CheckBoxField chbIH211_16;
	@FieldAnnotation(orderIndex = 17)
	public CheckBoxField chbIH211_17;
	@FieldAnnotation(orderIndex = 18)
	public CheckBoxField chbIH211_18;
	@FieldAnnotation(orderIndex = 19)
	public CheckBoxField chbIH211_19;
	@FieldAnnotation(orderIndex = 20)
	public CheckBoxField chbIH211_20;
	@FieldAnnotation(orderIndex = 21)
	public CheckBoxField chbIH211_21_COD;
	@FieldAnnotation(orderIndex = 22)
	public CheckBoxField chbIH211_22_COD;
	@FieldAnnotation(orderIndex = 23)
	public CheckBoxField chbIH211_23;
	@FieldAnnotation(orderIndex = 24)
	public CheckBoxField chbIH211_24;
	@FieldAnnotation(orderIndex = 25)
	public CheckBoxField chbIH211_25;
	@FieldAnnotation(orderIndex = 26)
	public CheckBoxField chbIH211_26;
	@FieldAnnotation(orderIndex = 27)
	public TextField txtIH211_26_O;
	@FieldAnnotation(orderIndex = 28)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 29)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private static C3CAP200_Fragment_201 caller;
	private Cap200Delitos detalle;
	private GridComponent2 grdEsp;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;
	private static INF_Caratula01Service caratulaService;

	public static C3CAP200_Fragment_201_04 newInstance(FragmentForm pagina,
			Cap200Delitos detalle, MarcoService marcoService, INF_Caratula01Service caratService) {
		caller = (C3CAP200_Fragment_201) pagina;
		mimarcoService = marcoService;
		caratulaService = caratService;
		Filtros.clear();
		C3CAP200_Fragment_201_04 f = new C3CAP200_Fragment_201_04();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP200_Fragment_201_04() {
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
		set211Cod();
		entityToUI(detalle);
		inicio();
	}
	
	private void set211Cod() {
//		if(Util.getInt(detalle.ih211_21)!=0 && Util.getInt(detalle.ih211_25)==0)  {
//			detalle.ih211_25 = detalle.ih211_21;
//			detalle.ih211_21 = null;
//		}
//		if(Util.getInt(detalle.ih211_22)!=0 && Util.getInt(detalle.ih211_26)==0) {
//			detalle.ih211_26 = detalle.ih211_22;
//			detalle.ih211_26_o = detalle.ih211_22_o;
//			detalle.ih211_22 = null;
//		}
	}

	private void inicio() {
		lockP211(chbIH211_25, null, null);
	}
	
	private View getViewEnablebP211(){
		return chbIH211_1.isEnabled()?chbIH211_1:(chbIH211_2.isEnabled()?chbIH211_2:
			(chbIH211_3.isEnabled()?chbIH211_3:(chbIH211_4.isEnabled()?chbIH211_4:
				(chbIH211_5.isEnabled()?chbIH211_5:(chbIH211_6.isEnabled()?chbIH211_6:
					(chbIH211_7.isEnabled()?chbIH211_7:(chbIH211_8.isEnabled()?chbIH211_8:
						(chbIH211_9.isEnabled()?chbIH211_9:(chbIH211_10.isEnabled()?chbIH211_10:
							(chbIH211_11.isEnabled()?chbIH211_11:(chbIH211_12.isEnabled()?chbIH211_12:
								(chbIH211_13.isEnabled()?chbIH211_13:(chbIH211_14.isEnabled()?chbIH211_14:
									(chbIH211_15.isEnabled()?chbIH211_15:(chbIH211_16.isEnabled()?chbIH211_16:
										(chbIH211_17.isEnabled()?chbIH211_17:(chbIH211_18.isEnabled()?chbIH211_18:
											(chbIH211_19.isEnabled()?chbIH211_19:(chbIH211_20.isEnabled()?chbIH211_20:
												(chbIH211_21_COD.isEnabled()?chbIH211_19:(chbIH211_20.isEnabled()?chbIH211_22_COD:
													(chbIH211_23.isEnabled()?chbIH211_19:(chbIH211_20.isEnabled()?chbIH211_24:
														(chbIH211_25.isEnabled()?chbIH211_25:chbIH211_26))))))))))))))))))))))));
	}
	
	private void lockP211(int cod){
		switch (cod) {
			case 1: caller.cleanAndLockView(chbIH211_4, chbIH211_5, chbIH211_6, chbIH211_7, chbIH211_9,
					chbIH211_10, chbIH211_11, chbIH211_12, chbIH211_13, chbIH211_14);	break;
			case 2: caller.cleanAndLockView(chbIH211_6,	chbIH211_14); break;
			case 3: caller.cleanAndLockView(chbIH211_3, chbIH211_6, chbIH211_10, chbIH211_11, chbIH211_12); break;
			case 4: caller.cleanAndLockView(chbIH211_6, chbIH211_12); break;
			case 5: caller.cleanAndLockView(chbIH211_3, chbIH211_6, chbIH211_11, chbIH211_12, chbIH211_14, 
					chbIH211_16); break;
			case 6: caller.cleanAndLockView(chbIH211_1, chbIH211_2, chbIH211_3, chbIH211_4, chbIH211_5, chbIH211_6,
					chbIH211_7, chbIH211_8, chbIH211_9, chbIH211_10, chbIH211_11, chbIH211_14, chbIH211_15, chbIH211_16,
					chbIH211_17, chbIH211_18, chbIH211_19, chbIH211_20, chbIH211_23); break;
			case 7: caller.cleanAndLockView(chbIH211_6, chbIH211_14, chbIH211_16); break;
			case 8: caller.cleanAndLockView(chbIH211_3, chbIH211_6, chbIH211_7, chbIH211_9, chbIH211_10, chbIH211_11, 
					chbIH211_12, chbIH211_13, chbIH211_16, chbIH211_18, chbIH211_19, chbIH211_20, chbIH211_23); break;
			case 9: caller.cleanAndLockView(chbIH211_1, chbIH211_2, chbIH211_3, chbIH211_4, chbIH211_5, chbIH211_8, 
					chbIH211_9, chbIH211_12, chbIH211_13, chbIH211_14, chbIH211_15, chbIH211_17,
					chbIH211_18, chbIH211_19, chbIH211_20, chbIH211_21_COD, chbIH211_23); break;
			case 10: caller.cleanAndLockView(chbIH211_1, chbIH211_2, chbIH211_3, chbIH211_4, chbIH211_5, chbIH211_6, chbIH211_7, 
					chbIH211_8, chbIH211_9, chbIH211_10, chbIH211_12, chbIH211_13, chbIH211_14, chbIH211_15, chbIH211_17, 
					chbIH211_18, chbIH211_19, chbIH211_20, chbIH211_21_COD, chbIH211_23); break;
			case 12: caller.cleanAndLockView(chbIH211_1, chbIH211_3, chbIH211_4, chbIH211_5, chbIH211_6, chbIH211_7, chbIH211_8,
					chbIH211_9, chbIH211_10, chbIH211_11, chbIH211_14, chbIH211_16, chbIH211_17); break;
			case 14: caller.cleanAndLockView(chbIH211_12, chbIH211_13); break;
			case 15: caller.cleanAndLockView(chbIH211_3, chbIH211_4, chbIH211_5, chbIH211_6, chbIH211_14); break;
			case 16: caller.cleanAndLockView(chbIH211_2, chbIH211_3, chbIH211_6, chbIH211_11, chbIH211_12,	chbIH211_13,
					chbIH211_15, chbIH211_16, chbIH211_19, chbIH211_20); break;
			case 18: caller.cleanAndLockView(chbIH211_1, chbIH211_2, chbIH211_3, chbIH211_4, chbIH211_5, chbIH211_6, chbIH211_7, 
					chbIH211_8, chbIH211_9, chbIH211_11, chbIH211_13, chbIH211_14, chbIH211_15, chbIH211_16, chbIH211_17, 
					chbIH211_18, chbIH211_19, chbIH211_20, chbIH211_21_COD, chbIH211_22_COD, chbIH211_23, chbIH211_24); break;
			case 19: caller.cleanAndLockView(chbIH211_1, chbIH211_3, chbIH211_4, chbIH211_5, chbIH211_6, chbIH211_10, chbIH211_11, 
					chbIH211_14, chbIH211_16); break;
			case 20: caller.cleanAndLockView(chbIH211_1, chbIH211_2, chbIH211_3, chbIH211_4, chbIH211_5, chbIH211_8, chbIH211_10, 
					chbIH211_11, chbIH211_12, chbIH211_13, chbIH211_14, chbIH211_15, chbIH211_16, chbIH211_17, chbIH211_18, 
					chbIH211_19, chbIH211_20); break;
			case 21: caller.cleanAndLockView(chbIH211_2, chbIH211_3, chbIH211_4, chbIH211_5, chbIH211_7, chbIH211_8, chbIH211_10, 
					chbIH211_12, chbIH211_13, chbIH211_14, chbIH211_15, chbIH211_16, chbIH211_17, chbIH211_18, chbIH211_19, 
					chbIH211_20, chbIH211_24); break;
	
			default: break;
		}
	}
	
	private void lockEspecifique(CheckBoxField chb, View to, View focusSi, View focusNo){
		if(chb.isChecked()) {
			caller.lockView(false, to);
			if(focusSi!=null) focusSi.requestFocus();
		} else {
			caller.cleanAndLockView(to);
			if(focusNo!=null) focusNo.requestFocus();
		}
    }

	@Override
	protected View createUI() {
		buildFields();
		
		LinearLayout q1 = createQuestionSection(R.string.lb_c3_cap200_p211, chbIH211_1,chbIH211_2,chbIH211_3,chbIH211_4,
				chbIH211_5,chbIH211_6,chbIH211_7,chbIH211_8,chbIH211_9,chbIH211_10,chbIH211_11,chbIH211_12,chbIH211_13,
				chbIH211_14,chbIH211_15,chbIH211_16,chbIH211_17,chbIH211_18,chbIH211_19,chbIH211_20,chbIH211_21_COD,
				chbIH211_22_COD,chbIH211_23,chbIH211_24,chbIH211_25,grdEsp.component());

		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q1);

		form.addView(botones);

		return contenedor;
	}

	@Override
	protected void buildFields() {

		txtIH211_26_O = new TextField(getActivity(), false).size(altoComponente, 400);
		
		chbIH211_1 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_1, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_2 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_2, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_3 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_3, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_4 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_4, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_5 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_5, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_6 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_6, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_7 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_7, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_8 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_8, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_9 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_9, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_10 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_10, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_11 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_11, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_12 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_12, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_13 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_13, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_14 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_14, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_15 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_15, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_16 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_16, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_17 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_17, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_18 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_18, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_19 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_19, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_20 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_20, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_21_COD = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_21c, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_22_COD = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_22c, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_23 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_23c, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_24 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_24c, "1:0").
				size(sizeHeigth, sizeWidth);
		chbIH211_25 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_25c, "1:0").
				size(sizeHeigth, sizeWidth).callback("on_P211_25_CheckedChanged");
		chbIH211_26 = new CheckBoxField(getActivity(), R.string.lb_c3_cap200_p211_26c, "1:0").
				size(sizeHeigth, 130).callback("onP211_26_ChangeValue");
		
		grdEsp = new GridComponent2(getActivity(), Gravity.LEFT, 3, 0);
		grdEsp.addComponent(chbIH211_26);
		grdEsp.addComponent(txtIH211_26_O);
		grdEsp.addComponent(new LabelComponent(getActivity()).size(sizeHeigth, 120));

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
				C3CAP200_Fragment_201_04.this.dismiss();
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
				FragmentManager fm = C3CAP200_Fragment_201_04.this.getFragmentManager();
				if(detalle!=null && !Util.esDiferente(detalle.getP208(), 18,19,20,21,22,23)){
					C3CAP200_Fragment_201_02 aperturaDialog = C3CAP200_Fragment_201_02
							.newInstance(caller, detalle, mimarcoService, caratulaService);
					aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
					aperturaDialog.show(fm, "aperturaDialog");
				} else {
					C3CAP200_Fragment_201_03_1 aperturaDialog = C3CAP200_Fragment_201_03_1
							.newInstance(caller, detalle, mimarcoService, caratulaService);
					aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
					aperturaDialog.show(fm, "aperturaDialog");
				}
				C3CAP200_Fragment_201_04.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
		
		// Filtro
		Filtros.setFiltro(txtIH211_26_O, Filtros.TIPO.ALFA, 50, null);
		
		
	}
	
	private void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
		C3CAP200_Fragment_201_04.this.dismiss();
		
		FragmentManager fm = caller.getFragmentManager();
		C3CAP200_Fragment_201_05 aperturaDialog = C3CAP200_Fragment_201_05.
				newInstance(caller, detalle, mimarcoService, caratulaService);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}
	
	public void on_P211_25_CheckedChanged(FieldComponent component){
		if(((CheckBoxField)component).isPressed())
			lockP211((CheckBoxField)component, btnAceptar, chbIH211_1);
	}
	
	private void lockP211(CheckBoxField chb, View focusSi, View focusNo){
		List<View> lst = caller.getViews(this, "211");
		if(chb.isChecked()){
			if(lst.contains(chb)) lst.remove(chb);
			caller.cleanAndLockView(lst.toArray(new View[lst.size()]));
			if(focusSi!=null) focusSi.requestFocus();
		} else {
			caller.lockView(lst, false);
			lockEspecifique(chbIH211_26, txtIH211_26_O, null, null);
//			if(focusNo!=null) focusNo.requestFocus();
			if(detalle!=null && detalle.getP208()!=null) {
				lockP211(detalle.getP208());
			}
			if(focusNo!=null) getViewEnablebP211().requestFocus();
		}
	}
	
	public void onP211_26_ChangeValue(FieldComponent component){
		lockEspecifique((CheckBoxField)component, txtIH211_26_O, txtIH211_26_O, btnAceptar);
	}

	private boolean validar() {
		error = false;
		
		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
	
//		if(rgIH208.isEnabled()){
//			if(Util.esVacio(rgIH208)){
//				mensaje="La pregunta 208 no puede estar Vacia";
//				view=rgIH208;
//				error=true;
//				return false;
//			}
//			else if((!Util.esDiferente(detalle.ih208, 1,2,3,4,5,6,7) && rgIH208.isTagSelectedBetween(9, 10)) ||
//					(Integer.valueOf(8).equals(detalle.ih208) && (rgIH208.isTagSelectedBetween(1, 3) ||
//							rgIH208.isTagSelected(5) || rgIH208.isTagSelected(9))) ||
//					(Integer.valueOf(9).equals(detalle.ih208) && !rgIH208.isTagSelected(9))	||	
//					(Integer.valueOf(11).equals(detalle.ih208) && rgIH208.isTagSelectedBetween(9, 10)) ||
//					(Integer.valueOf(13).equals(detalle.ih208) && rgIH208.isTagSelected(9)) ||
//					(Integer.valueOf(15).equals(detalle.ih208) && rgIH208.isTagSelectedBetween(9, 10)) ||
//					(Integer.valueOf(16).equals(detalle.ih208) && rgIH208.isTagSelected(9)) ){
//				mensaje="Es errado ya que su medio/modalidad NO CONCUERDA CON PREG. 204.";
//				view=rgIH208;
//				error=true;
//				return false;
//			}
//			else if(rgIH208.getTagSelected("").toString().equals("12") && Util.esVacio(txtIH208_O)){ 
//				mensaje="Especifique no puede estar vacio";
//				view=txtIH208_O;
//				error=true;
//				return false;
//			}
//		}
		
		if(Util.verificaNoVacio(caller.getViews(this, "211"), CheckBoxField.class)==null){
			mensaje="Debe seleccionar al menos una alternativa.";
			view=getViewEnablebP211();
			error=true;
			return false;
		} 
		
		if(chbIH211_26.isChecked() && Util.esVacio(txtIH211_26_O)){
			mensaje="Especifique no puede estar vacio.";
			view=txtIH211_26_O;
			error=true;
			return false;
		}
		
		//VERIFICACIONES
//		ArrayList<String> checkList = new ArrayList<String>();
//		if(Integer.valueOf(1).equals(detalle.ih208) && Integer.valueOf(1).equals(detalle.ih207) &&
//				rgIH208.isTagSelected(8)){
//			mensaje="VERIFICAR: Si es homicidio simple en v\u00eda p\u00fablica y su medio/modalidad "
//					+ "utilizada es o envenenamiento.";
//			checkList.add(mensaje);
//		}
//		
//		if(Integer.valueOf(10).equals(detalle.ih208) && rgIH208.isTagSelectedBetween(1, 3)){
//			mensaje="VERIFICAR: Medio/modalidad NO CONCUERDA CON PREGUNTA 204.";
//			checkList.add(mensaje);
//		}
//
//		if(checkList.size()>0){
//			for(String s:checkList){
//				ToastMessage.msgBox(this.getActivity(), s, ToastMessage.MESSAGE_INFO,	
//						ToastMessage.DURATION_LONG);
//			}
//		}

		if (error) return false;
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
					new String[]{"IH211_21", "IH211_22"})))){
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
//		if(!mimarcoService.saveCap200Delitos(detalle, Utilidades.getListFields(this))){
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
