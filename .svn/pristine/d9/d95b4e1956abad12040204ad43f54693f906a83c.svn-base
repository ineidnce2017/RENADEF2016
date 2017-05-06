package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.DialogComponent.TIPO_DIALOGO;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.TextAreaField;
import gob.inei.dnce.components.TextBoxField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.components.ui.SearchDialog;
import gob.inei.dnce.interfaces.IDetailEntityComponent;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.interfaces.Searchable1;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.C3CAP200_Fragment_201;
import gob.inei.renadef2016.modelo.Search;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.renadef2016.utilities.TaskExecute;

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

public class C3CAP200_Fragment_201_02 extends DialogFragmentComponent implements Respondible, Searchable1 {
	public interface C1CAP200_Fragment_201_01Listener {
		void onFinishEditDialog(String inputText);
	}

//	@FieldAnnotation(orderIndex = 1)
//	public RadioGroupOtherField rgIH208;
//	@FieldAnnotation(orderIndex = 2)
//	public TextField txtIH208_B_O;
//	@FieldAnnotation(orderIndex = 3)
//	public TextField txtIH208_D_O;
//	@FieldAnnotation(orderIndex = 4)
//	public TextField txtIH208_E_O;
//	@FieldAnnotation(orderIndex = 5)
//	public TextField txtIH208_F_O;
//	@FieldAnnotation(orderIndex = 6)
//	public TextField txtIH208_I_O;
//	@FieldAnnotation(orderIndex = 7)
//	public TextField txtIH208_J_O; 
//	@FieldAnnotation(orderIndex = 8)
//	public TextField txtIH208_103_O;
//	@FieldAnnotation(orderIndex = 9)
//	public TextField txtIH208_104_O;
//	@FieldAnnotation(orderIndex = 10)
//	public TextField txtIH208_105_O;
//	@FieldAnnotation(orderIndex = 11)
//	public TextField txtIH208_106_O;
//	@FieldAnnotation(orderIndex = 12)
//	public TextField txtIH208_107_O;
//	@FieldAnnotation(orderIndex = 13)
//	public TextField txtIH208_110_O;
//	@FieldAnnotation(orderIndex = 14)
//	public TextField txtIH208_111_O;
//	@FieldAnnotation(orderIndex = 15)
//	public TextField txtIH208_112_O;
//	@FieldAnnotation(orderIndex = 16)
//	public TextField txtIH208_113_O;
//	@FieldAnnotation(orderIndex = 17)
//	public TextField txtIH208_114_O;
//	@FieldAnnotation(orderIndex = 18)
//	public TextField txtIH208_115_O;
//	@FieldAnnotation(orderIndex = 19)
//	public TextField txtIH208_116_O;
//	@FieldAnnotation(orderIndex = 20)
//	public TextField txtIH208_117_O;
//	@FieldAnnotation(orderIndex = 21)
//	public TextField txtIH208_118_O;
//	@FieldAnnotation(orderIndex = 22)
//	public TextField txtIH208_120_O;
	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtIH208;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtIH208_O;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtIH208_COD;
	@FieldAnnotation(orderIndex = 4)
	public TextField txtIH208_COD_O;
	public TextAreaField txtIH208NOM;
	@FieldAnnotation(orderIndex = 5)
    public ButtonComponent imgC;
	@FieldAnnotation(orderIndex = 6)
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 7)
	public ButtonComponent btnCancelar;
	private ImageComponent btnAtras;
	private ImageComponent btnAdelante;
	private static C3CAP200_Fragment_201 caller;
	private Cap200Delitos detalle;
	private int sizeWidth = 650, sizeHeigth = MATCH_PARENT;
	private static MarcoService mimarcoService;
	private static INF_Caratula01Service caratulaService;
	private static Cap100Delitos c100;
	private DialogComponent dc;
	private GridComponent gr;
	private LabelComponent lblModT, lblModS, lblEsp, lblNom0;
	private LinearLayout q0;

	public static C3CAP200_Fragment_201_02 newInstance(FragmentForm pagina,
			Cap200Delitos detalle, MarcoService marcoService, INF_Caratula01Service caratService) {
		caller = (C3CAP200_Fragment_201) pagina;
		mimarcoService = marcoService;
		caratulaService = caratService;
		Filtros.clear();
		C3CAP200_Fragment_201_02 f = new C3CAP200_Fragment_201_02();
		f.setParent(pagina);
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3CAP200_Fragment_201_02() {
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
		c100 = mimarcoService.getC100(App.getInstance().getComisaria(), new Cap100Delitos().getSecCap(Util.getListList("ID_N")));
		if (c100 == null) {
			c100 = new Cap100Delitos();
			c100.id_n = App.getInstance().getComisaria().id_n;
		} 
		
		set208Cod();
		entityToUI(detalle);
//		setEsp208();
		inicio();
	}

//	private void setEsp208() {
//		if(detalle.ih208 == null) return;
//		switch (detalle.ih208.intValue()) {
//			case 11: txtIH208_B_O.setText(detalle.ih208_o);break;
//			case 13: txtIH208_D_O.setText(detalle.ih208_o);break;
//			case 17: txtIH208_E_O.setText(detalle.ih208_o);break;
//			case 23: txtIH208_F_O.setText(detalle.ih208_o);break;
//			case 30: txtIH208_I_O.setText(detalle.ih208_o);break;
//			case 35: txtIH208_J_O.setText(detalle.ih208_o);break;
//			case 41: txtIH208_103_O.setText(detalle.ih208_o);break;
//			case 53: txtIH208_104_O.setText(detalle.ih208_o);break;
//			case 65: txtIH208_105_O.setText(detalle.ih208_o);break;
//			case 68: txtIH208_106_O.setText(detalle.ih208_o);break;
//			case 72: txtIH208_107_O.setText(detalle.ih208_o);break;
//			case 81: txtIH208_110_O.setText(detalle.ih208_o);break;
//			case 85: txtIH208_111_O.setText(detalle.ih208_o);break;
//			case 90: txtIH208_112_O.setText(detalle.ih208_o);break;
//			case 95: txtIH208_113_O.setText(detalle.ih208_o);break;
//			case 98: txtIH208_114_O.setText(detalle.ih208_o);break;
//			case 104: txtIH208_115_O.setText(detalle.ih208_o);break;
//			case 108: txtIH208_116_O.setText(detalle.ih208_o);break;
//			case 110: txtIH208_117_O.setText(detalle.ih208_o);break;
//			case 112: txtIH208_118_O.setText(detalle.ih208_o);break;
//			case 118: txtIH208_120_O.setText(detalle.ih208_o);break;
//			default:break;
//		}
//	}
	
	private void set208Cod() {
//		if(detalle.ih208!=null && Util.getInt(detalle.ih208) <= 35){
//			detalle.ih208_cod = detalle.ih208;
//			detalle.ih208_cod_o = detalle.ih208_o;
//		}
		if(detalle.ih208 == null || detalle.ih208_cod != null){
			q0.setVisibility(View.GONE);
		} else {
			lblNom0.setText(detalle.getP208Cod());
		}
		if(detalle.ih208_cod == null) {
			lblModS.setVisibility(View.GONE);
			lblEsp.setVisibility(View.GONE);
			txtIH208_COD_O.setVisibility(View.GONE);
			return;
		}
		if(detalle.ih208_cod_o == null){
			lblEsp.setVisibility(View.GONE);
			txtIH208_COD_O.setVisibility(View.GONE);
		}
		Search busacdo = caller.getModalidad(detalle.ih208_cod);
		if(busacdo != null){
			lblModS.setVisibility(View.VISIBLE);
			lblModT.setText(busacdo.titulo);
			lblModS.setText(busacdo.subtitulo);
			txtIH208NOM.setText(busacdo.nombre);
		}
	}

	private void inicio() {
		imgC.requestFocus();
//		rgIH208.requestFocus();
//		rgIH208.lockButtons(true, 93);
		
//		if (C3CAP200_Fragment_201.cap200cap100!=null) {
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_1_a)){
//				rgIH208.lockButtons(true, 0);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_1_a;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							Integer.valueOf(1).equals(cap200.ih208)) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 0);
//				}
//			}
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_1_b)){
//				rgIH208.lockButtons(true, 1);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_1_b;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							Integer.valueOf(2).equals(cap200.ih208)) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 1);
//				}
//			}
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_1_c)){
//				rgIH208.lockButtons(true, 2);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_1_c;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							Integer.valueOf(3).equals(cap200.ih208)) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 2);
//				}
//			}
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_1_d)){
//				rgIH208.lockButtons(true, 3);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_1_d;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							Integer.valueOf(4).equals(cap200.ih208)) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 3);
//				}
//			}
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_1_e)){
//				rgIH208.lockButtons(true, 4);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_1_e;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							Integer.valueOf(5).equals(cap200.ih208)) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 4);
//				}
//			}
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_1_f)){
//				rgIH208.lockButtons(true, 5);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_1_f;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							Integer.valueOf(6).equals(cap200.ih208)) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 5);
//				}
//			}
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_1_g)){
//				rgIH208.lockButtons(true, 6);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_1_g;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							Integer.valueOf(7).equals(cap200.ih208)) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 6);
//				}
//			}
//			//B
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_2)){
//				rgIH208.lockButtons(true, 7,8,9);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_2;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							(Integer.valueOf(8).equals(cap200.ih208) ||
//							Integer.valueOf(9).equals(cap200.ih208) ||
//							Integer.valueOf(10).equals(cap200.ih208))) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 7,8,9);
//				}
//			}
//			//C
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_3)){
//				rgIH208.lockButtons(true, 10);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_3;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							Integer.valueOf(11).equals(cap200.ih208)) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 10);
//				}
//			}
//			//D
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_4)){
//				rgIH208.lockButtons(true, 11);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_4;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							Integer.valueOf(12).equals(cap200.ih208)) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 11);
//				}
//			}
//			//E
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_5)){
//				rgIH208.lockButtons(true, 12,13,14,15);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_5;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							(Integer.valueOf(13).equals(cap200.ih208) ||
//							Integer.valueOf(14).equals(cap200.ih208) ||
//							Integer.valueOf(15).equals(cap200.ih208) ||
//							Integer.valueOf(16).equals(cap200.ih208))) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 12,13,14,15);
//				}
//			}
//			//F
//			if(Integer.valueOf(0).equals(C3CAP200_Fragment_201.cap200cap100.dn101_6)){
//				rgIH208.lockButtons(true, 16,17,18,19,20,21,22);
//			} else {
//				Integer cant = C3CAP200_Fragment_201.cap200cap100.dn101_6;
//				int cont = 0;
//				for(Cap200Delitos cap200:C3CAP200_Fragment_201.getData()){
//					if(cap200.nro_mreg!=detalle.nro_mreg && 
//							(Integer.valueOf(17).equals(cap200.ih208) ||
//							Integer.valueOf(18).equals(cap200.ih208) ||
//							Integer.valueOf(19).equals(cap200.ih208) ||
//							Integer.valueOf(20).equals(cap200.ih208) ||
//							Integer.valueOf(21).equals(cap200.ih208) ||
//							Integer.valueOf(22).equals(cap200.ih208) ||
//							Integer.valueOf(23).equals(cap200.ih208))) cont++;
//				}
//				if(cant!=null && cont >= cant.intValue()){
//					rgIH208.lockButtons(true, 16,17,18,19,20,21,22);
//				}
//			}
//		}
	}

	@Override
	protected View createUI() {
		buildFields();
		
		q0 = createQuestionSection(R.string.lb_c3_cap200_p208,
				gr.component());
		
		LabelComponent lblCod = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				size(altoComponente, 120).text(R.string.lb_c3_cap200_p208_tr_1).centrar();
		LinearLayout q1 = createQuestionSection(R.string.lb_c3_cap200_p208_1t,
				createLy(LinearLayout.HORIZONTAL, Gravity.CENTER, lblCod, txtIH208_COD, imgC),
				lblModT, lblModS, txtIH208NOM, lblEsp, txtIH208_COD_O);
		
//		LinearLayout q2 = createQuestionSection(R.string.lb_c3_cap200_p208, Gravity.CENTER, rgIH208);
		
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
//		LabelComponent lblDVS = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_101_dvs).centrar();
//		
//		LabelComponent lblHD = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_A);
//		LabelComponent lblHC = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_B);
//		LabelComponent lblIS = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_C);
//		LabelComponent lblOH = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_D);
//		LabelComponent lblHM = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_E);
//		LabelComponent lblOM = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_F);
//		LabelComponent lblTO = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_G);
//		LabelComponent lblAB = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_H);
//		LabelComponent lblLE = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_I);
//		LabelComponent lblEP = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_J);
//		
//		LabelComponent lblDH = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_2).centrar();
//		LabelComponent lblDF = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_3).centrar();
//		LabelComponent lblDL = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_4).centrar();
//		LabelComponent lblDP = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_5).centrar();
//		LabelComponent lblDC = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_6).centrar();
//		LabelComponent lblDD = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_7).centrar();
//		LabelComponent lblDPC = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_8).centrar();
//		LabelComponent lblDOE = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_9).centrar();
//		LabelComponent lblDOF = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_10).centrar();
//		LabelComponent lblDT = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_11).centrar();
//		LabelComponent lblDSP = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_12).centrar();
//		LabelComponent lblDEC = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_13).centrar();
//		LabelComponent lblDTP = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_14).centrar();
//		LabelComponent lblDHU = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_15).centrar();
//		LabelComponent lblDED = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_16).centrar();
//		LabelComponent lblDPE = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_17).centrar();
//		LabelComponent lblDVP = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_18).centrar();
//		LabelComponent lblDAP = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, sizeWidth).text(R.string.lb_c3_cap200_p208_t_19).centrar();
//		LabelComponent lblDFP = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				size(altoComponente, 200).text(R.string.lb_c3_cap200_p208_t_20).centrar();
		
//		LabelComponent lblCod0 = new LabelComponent(getActivity()).
//				size(altoComponente, 120).text(R.string.lb_c3_cap200_p208_tr_1).centrar();
		lblNom0 = new LabelComponent(getActivity()).
				size(altoComponente+35, 600).centrar().negrita()
				.colorFondo(R.color.celesteclarito);
		LabelComponent lblEsp0 = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				size(altoComponente, 120).text(R.string.lb_c3_cap200_p208_tr_2).centrar();
		txtIH208 = new IntegerField(getActivity(),false).size(altoComponente, 150).centrar().negrita().readOnly();
		txtIH208_O = new TextField(getActivity(),false).size(altoComponente, 480);
		
		gr = new GridComponent(getActivity(), 1).colorFondo(R.color.blanco);
//		gr.addComponent(lblCod0);
//		gr.addComponent(txtIH208);
		gr.addComponent(lblNom0);
//		gr.addComponent(lblEsp0);
//		gr.addComponent(txtIH208_O);
		
		lblEsp = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				size(altoComponente, 600).text(R.string.lb_c3_cap200_p208_tr_2).centrar();
		lblModT = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				size(altoComponente, 600).text(R.string.lb_c3_cap200_p208_tr_3).centrar();
		lblModS = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				size(altoComponente, 600).text(R.string.lb_c3_cap200_p208_tr_4).centrar();
		txtIH208_COD = new IntegerField(getActivity(),false).size(altoComponente, 150).centrar().negrita().readOnly();
		txtIH208_COD_O = new TextField(getActivity(),false).size(altoComponente, 600).readOnly();
		txtIH208NOM = new TextAreaField(getActivity()).size(170, 600).readOnly().centrar().negrita();
		
		imgC = new ButtonComponent(this.getActivity()).size(70, altoComponente+10).drawableLeft(App.SEARCH).padding(15,0,0,0);
		imgC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				search();
			}
		});
//		btnBuscar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
//				R.string.btnAceptar).size(200, 60);
//		btnBuscar.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				search();
//			}
//		});
		
//		gr = new GridComponent(getActivity(), 1).colorFondo(R.color.blanco);
//		gr.addComponent(lblModT);
//		gr.addComponent(txtIH208NOM);
//		gr.addComponent(lblEsp);
//		gr.addComponent(txtIH208_COD_O);
		
		Filtros.setFiltro(txtIH208_COD_O, Filtros.TIPO.ALFAN_U, 200, null);
		Filtros.setFiltro(txtIH208NOM, Filtros.TIPO.ALFAN_U, 500, null);
		
//		txtIH208_B_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_D_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_E_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_F_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_I_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_J_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_103_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_104_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_105_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_106_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_107_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_110_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_111_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_112_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_113_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_114_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_115_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_116_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_117_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_118_O = new TextField(getActivity(),false).size(altoComponente, 400);
//		txtIH208_120_O = new TextField(getActivity(),false).size(altoComponente, 400);
		
//		rgIH208 = new RadioGroupOtherField(this.getActivity(), R.string.lb_c3_cap200_p208_1,
//				R.string.lb_c3_cap200_p208_2, R.string.lb_c3_cap200_p208_3, R.string.lb_c3_cap200_p208_4,
//				R.string.lb_c3_cap200_p208_5, R.string.lb_c3_cap200_p208_6, R.string.lb_c3_cap200_p208_7,
//				R.string.lb_c3_cap200_p208_8, R.string.lb_c3_cap200_p208_9, R.string.lb_c3_cap200_p208_10,
//				R.string.lb_c3_cap200_p208_11, R.string.lb_c3_cap200_p208_12, R.string.lb_c3_cap200_p208_13,
//				R.string.lb_c3_cap200_p208_14, R.string.lb_c3_cap200_p208_15, R.string.lb_c3_cap200_p208_16,
//				R.string.lb_c3_cap200_p208_17, R.string.lb_c3_cap200_p208_18, R.string.lb_c3_cap200_p208_19,
//				R.string.lb_c3_cap200_p208_20, R.string.lb_c3_cap200_p208_21, R.string.lb_c3_cap200_p208_22,
//				R.string.lb_c3_cap200_p208_23, R.string.lb_c3_cap200_p208_24, R.string.lb_c3_cap200_p208_25,
//				R.string.lb_c3_cap200_p208_26, R.string.lb_c3_cap200_p208_27, R.string.lb_c3_cap200_p208_28,
//				R.string.lb_c3_cap200_p208_29, R.string.lb_c3_cap200_p208_30, R.string.lb_c3_cap200_p208_31,
//				R.string.lb_c3_cap200_p208_32, R.string.lb_c3_cap200_p208_33, R.string.lb_c3_cap200_p208_34,
//				R.string.lb_c3_cap200_p208_35, R.string.lb_c3_cap200_p208_36, R.string.lb_c3_cap200_p208_37,
//				R.string.lb_c3_cap200_p208_38, R.string.lb_c3_cap200_p208_39, R.string.lb_c3_cap200_p208_40,
//				R.string.lb_c3_cap200_p208_41, R.string.lb_c3_cap200_p208_42, R.string.lb_c3_cap200_p208_43,
//				R.string.lb_c3_cap200_p208_44, R.string.lb_c3_cap200_p208_45, R.string.lb_c3_cap200_p208_46,
//				R.string.lb_c3_cap200_p208_47, R.string.lb_c3_cap200_p208_48, R.string.lb_c3_cap200_p208_49,
//				R.string.lb_c3_cap200_p208_50, R.string.lb_c3_cap200_p208_51, R.string.lb_c3_cap200_p208_52,
//				R.string.lb_c3_cap200_p208_53, R.string.lb_c3_cap200_p208_54, R.string.lb_c3_cap200_p208_55,
//				R.string.lb_c3_cap200_p208_56, R.string.lb_c3_cap200_p208_57, R.string.lb_c3_cap200_p208_58,
//				R.string.lb_c3_cap200_p208_59, R.string.lb_c3_cap200_p208_60, R.string.lb_c3_cap200_p208_61,
//				R.string.lb_c3_cap200_p208_62, R.string.lb_c3_cap200_p208_63, R.string.lb_c3_cap200_p208_64,
//				R.string.lb_c3_cap200_p208_65, R.string.lb_c3_cap200_p208_66, R.string.lb_c3_cap200_p208_67,
//				R.string.lb_c3_cap200_p208_68, R.string.lb_c3_cap200_p208_69, R.string.lb_c3_cap200_p208_70,
//				R.string.lb_c3_cap200_p208_71, R.string.lb_c3_cap200_p208_72, R.string.lb_c3_cap200_p208_73,
//				R.string.lb_c3_cap200_p208_74, R.string.lb_c3_cap200_p208_75, R.string.lb_c3_cap200_p208_76,
//				R.string.lb_c3_cap200_p208_77, R.string.lb_c3_cap200_p208_78, R.string.lb_c3_cap200_p208_79,
//				R.string.lb_c3_cap200_p208_80, R.string.lb_c3_cap200_p208_81, R.string.lb_c3_cap200_p208_82,
//				R.string.lb_c3_cap200_p208_83, R.string.lb_c3_cap200_p208_84, R.string.lb_c3_cap200_p208_85,
//				R.string.lb_c3_cap200_p208_86, R.string.lb_c3_cap200_p208_87, R.string.lb_c3_cap200_p208_88,
//				R.string.lb_c3_cap200_p208_89, R.string.lb_c3_cap200_p208_90, R.string.lb_c3_cap200_p208_91,
//				R.string.lb_c3_cap200_p208_92, R.string.lb_c3_cap200_p208_93, R.string.lb_c3_cap200_p208_94,
//				R.string.lb_c3_cap200_p208_95, R.string.lb_c3_cap200_p208_96, R.string.lb_c3_cap200_p208_97,
//				R.string.lb_c3_cap200_p208_98, R.string.lb_c3_cap200_p208_99, R.string.lb_c3_cap200_p208_100,
//				R.string.lb_c3_cap200_p208_101, R.string.lb_c3_cap200_p208_102, R.string.lb_c3_cap200_p208_103,
//				R.string.lb_c3_cap200_p208_104, R.string.lb_c3_cap200_p208_105, R.string.lb_c3_cap200_p208_106,
//				R.string.lb_c3_cap200_p208_107, R.string.lb_c3_cap200_p208_108, R.string.lb_c3_cap200_p208_109,
//				R.string.lb_c3_cap200_p208_110, R.string.lb_c3_cap200_p208_111, R.string.lb_c3_cap200_p208_112,
//				R.string.lb_c3_cap200_p208_113, R.string.lb_c3_cap200_p208_114, R.string.lb_c3_cap200_p208_115,
//				R.string.lb_c3_cap200_p208_116, R.string.lb_c3_cap200_p208_117, R.string.lb_c3_cap200_p208_118
//				).size(sizeHeigth, sizeWidth).
//				orientation(RadioGroupOtherField.ORIENTATION.VERTICAL);
//		rgIH208.agregarEspecifique(10, txtIH208_B_O);
//		rgIH208.agregarEspecifique(12, txtIH208_D_O);
//		rgIH208.agregarEspecifique(16, txtIH208_E_O);
//		rgIH208.agregarEspecifique(22, txtIH208_F_O);
//		rgIH208.agregarEspecifique(29, txtIH208_I_O);
//		rgIH208.agregarEspecifique(34, txtIH208_J_O);
//		rgIH208.agregarEspecifique(40, txtIH208_103_O);
//		rgIH208.agregarEspecifique(52, txtIH208_104_O);
//		rgIH208.agregarEspecifique(64, txtIH208_105_O);
//		rgIH208.agregarEspecifique(68, txtIH208_106_O);
//		rgIH208.agregarEspecifique(71, txtIH208_107_O);
//		rgIH208.agregarEspecifique(80, txtIH208_110_O);
//		rgIH208.agregarEspecifique(84, txtIH208_111_O);
//		rgIH208.agregarEspecifique(89, txtIH208_112_O);
//		rgIH208.agregarEspecifique(94, txtIH208_113_O);
//		rgIH208.agregarEspecifique(97, txtIH208_114_O);
//		rgIH208.agregarEspecifique(103, txtIH208_115_O);
//		rgIH208.agregarEspecifique(107, txtIH208_116_O);
//		rgIH208.agregarEspecifique(109, txtIH208_117_O);
//		rgIH208.agregarEspecifique(111, txtIH208_118_O);
//		rgIH208.agregarEspecifique(117, txtIH208_120_O);
//		rgIH208.agregarTitle(0, lblDVS);
//		rgIH208.agregarTitle(1, lblHD);
//		rgIH208.agregarTitle(10, lblHC);
//		rgIH208.agregarTitle(14, lblIS);
//		rgIH208.agregarTitle(16, lblOH);
//		rgIH208.agregarTitle(18, lblHM);
//		rgIH208.agregarTitle(23, lblOM);
//		rgIH208.agregarTitle(30, lblTO);
//		rgIH208.agregarTitle(32, lblAB);
//		rgIH208.agregarTitle(34, lblLE);
//		rgIH208.agregarTitle(40, lblEP);
//		rgIH208.agregarTitle(46, lblDH);
//		rgIH208.agregarTitle(48, lblDF);
//		rgIH208.agregarTitle(54, lblDL);
//		rgIH208.agregarTitle(67, lblDP);
//		rgIH208.agregarTitle(80, lblDC);
//		rgIH208.agregarTitle(85, lblDD);
//		rgIH208.agregarTitle(89, lblDPC);
//		rgIH208.agregarTitle(91, lblDOE);
//		rgIH208.agregarTitle(97, lblDOF);
//		rgIH208.agregarTitle(101, lblDT);
//		rgIH208.agregarTitle(106, lblDSP);
//		rgIH208.agregarTitle(112, lblDEC);
//		rgIH208.agregarTitle(118, lblDTP);
//		rgIH208.agregarTitle(122, lblDHU);
//		rgIH208.agregarTitle(129, lblDED);
//		rgIH208.agregarTitle(134, lblDPE);
//		rgIH208.agregarTitle(137, lblDVP);
//		rgIH208.agregarTitle(140, lblDAP);
//		rgIH208.agregarTitle(144, lblDFP);

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
				C3CAP200_Fragment_201_02.this.dismiss();
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
				FragmentManager fm = C3CAP200_Fragment_201_02.this.getFragmentManager();
				C3CAP200_Fragment_201_01_1 aperturaDialog = C3CAP200_Fragment_201_01_1
						.newInstance(caller, detalle, mimarcoService, caratulaService);
				aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
				aperturaDialog.show(fm, "aperturaDialog");
				C3CAP200_Fragment_201_02.this.dismiss();
			}
		});
		btnAdelante.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});
		
//		Filtros.setFiltro(txtIH208_B_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_D_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_E_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_F_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_I_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_J_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_103_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_104_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_105_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_106_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_107_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_110_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_111_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_112_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_113_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_114_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_115_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_116_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_117_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_118_O, Filtros.TIPO.ALFAN_U, 50, null);
//		Filtros.setFiltro(txtIH208_120_O, Filtros.TIPO.ALFAN_U, 50, null);
		
	}
	
	private void search(){
		FragmentManager fm = caller.getFragmentManager();
		SearchDialog aperturaDialog = SearchDialog
				.newInstance(this, caller, "Resumen");
//		aperturaDialog.setTextButtons("Confirmar Exportacion", "Cancelar");
		aperturaDialog.setEstilo(App.ESTILO, App.ESTILO_BOTON);
		aperturaDialog.setSize(800);
//		aperturaDialog.setColorCondition("valor", Util.getHMObject(0, R.color.azulaceroclaro, 1, R.color.achurado), true, 16, R.color.black);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}
	
	public void save(){
		boolean flag = grabar();
		if (!flag) {
			return;
		}
	}

	private boolean validar() {
		error=false;
		
		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
		
		if(Util.esVacio(txtIH208) && Util.esVacio(txtIH208_COD)){
			mensaje="La pregunta 208 no puede estar Vacia";
			view=imgC;
			error=true;
			return false;
		}
		
//		if(!valEspecifique(rgIH208, 11, txtIH208_B_O)) return false;
//		if(!valEspecifique(rgIH208, 13, txtIH208_D_O)) return false;
//		if(!valEspecifique(rgIH208, 17, txtIH208_E_O)) return false;
//		if(!valEspecifique(rgIH208, 23, txtIH208_F_O)) return false;
//		if(!valEspecifique(rgIH208, 30, txtIH208_I_O)) return false;
//		if(!valEspecifique(rgIH208, 35, txtIH208_J_O)) return false;
//		if(!valEspecifique(rgIH208, 41, txtIH208_103_O)) return false;
//		if(!valEspecifique(rgIH208, 53, txtIH208_104_O)) return false;
//		if(!valEspecifique(rgIH208, 65, txtIH208_105_O)) return false;
//		if(!valEspecifique(rgIH208, 69, txtIH208_106_O)) return false;
//		if(!valEspecifique(rgIH208, 72, txtIH208_107_O)) return false;
//		if(!valEspecifique(rgIH208, 81, txtIH208_110_O)) return false;
//		if(!valEspecifique(rgIH208, 85, txtIH208_111_O)) return false;
//		if(!valEspecifique(rgIH208, 90, txtIH208_112_O)) return false;
//		if(!valEspecifique(rgIH208, 95, txtIH208_113_O)) return false;
//		if(!valEspecifique(rgIH208, 98, txtIH208_114_O)) return false;
//		if(!valEspecifique(rgIH208, 104, txtIH208_115_O)) return false;
//		if(!valEspecifique(rgIH208, 108, txtIH208_116_O)) return false;
//		if(!valEspecifique(rgIH208, 110, txtIH208_117_O)) return false;
//		if(!valEspecifique(rgIH208, 112, txtIH208_118_O)) return false;
//		if(!valEspecifique(rgIH208, 118, txtIH208_120_O)) return false;

		if(error) return false;	
		return true;
	}
	
	private boolean valEspecifique(RadioGroupOtherField rg, int resp, TextBoxField txt){
		if(rg.isTagSelected(resp) && Util.esVacio(txt)){
			mensaje="Especifique no puede estar vacio";
			view=txt;
			return !(error=true);
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
		
		int _msp = 0;
		if((_msp = validaData())!=0){
			dc = new DialogComponent(getActivity(), this,
					TIPO_DIALOGO.YES_NO, getResources()
							.getString(R.string.app_name),
					getMsg(_msp));
			dc.put("clave", _msp);
			dc.showDialog();
		} else {
			savePag(-1);
		}

		return true;
	}
	
	private int validaData(){
//		Integer p208 = rgIH208.getTagSelectedInteger(-1);
		Integer p208 = Util.getInt(txtIH208_COD, -1);
		if((p208 >= 18 && p208 <= 23) && (detalle.getP209()!=null || detalle.ih210!=null
				|| detalle.ih215!=null)){
			return 1;
		} 
		return 0;
	}
	
	private String getMsg(int cod){
		int _vict = mimarcoService.getConteoVictimarios(detalle.id_n, detalle.nro_mreg);
		switch (cod) {
			case 1: return "Tiene Informacion en P209, P210 \u00f3 P215, y al cambiar P208 > 17; La informacion de estas "
					+ "preguntas seran eliminadas;"+(_vict > 0 ? " Ademas de los "+_vict+" victimarios "
							+ "registrados en el Cap 400.":"")+" Esta seguro que desea continuar?";
			default: return "";
		}
	}
	
//	private String getEsp208() {
//		if(detalle.ih208 == null) return null;
//		switch (detalle.ih208.intValue()) {
//			case 11: return txtIH208_B_O.getText().toString().trim();
//			case 13: return txtIH208_D_O.getText().toString().trim();
//			case 17: return txtIH208_E_O.getText().toString().trim();
//			case 23: return txtIH208_F_O.getText().toString().trim();
//			case 30: return txtIH208_I_O.getText().toString().trim();
//			case 35: return txtIH208_J_O.getText().toString().trim();
//			case 41: return txtIH208_103_O.getText().toString().trim();
//			case 53: return txtIH208_104_O.getText().toString().trim();
//			case 65: return txtIH208_105_O.getText().toString().trim();
//			case 68: return txtIH208_106_O.getText().toString().trim();
//			case 72: return txtIH208_107_O.getText().toString().trim();
//			case 81: return txtIH208_110_O.getText().toString().trim();
//			case 85: return txtIH208_111_O.getText().toString().trim();
//			case 90: return txtIH208_112_O.getText().toString().trim();
//			case 95: return txtIH208_113_O.getText().toString().trim();
//			case 98: return txtIH208_114_O.getText().toString().trim();
//			case 104: return txtIH208_115_O.getText().toString().trim();
//			case 108: return txtIH208_116_O.getText().toString().trim();
//			case 110: return txtIH208_117_O.getText().toString().trim();
//			case 112: return txtIH208_118_O.getText().toString().trim();
//			case 118: return txtIH208_120_O.getText().toString().trim();
//			default:break;
//		}
//		return null;
//	}
	
	private void savePag(Integer cod){
		String [] extra = new String[]{"txtIH208_O"};
		switch (cod) {
			case 1: extra = new String[]{"txtIH208_O", "IH209", "IH209_O", "IH209_COD", "IH209_COD_O", "IH210", "IH210_O", 
					"IH210_COD", "IH210_COD_O", "IH215", "CONTE_REG400"}; break;
			default:break;
		}
		
		uiToEntity(detalle);
		detalle.p208nom = txtIH208NOM.getText().toString();
		try {
			if(!mimarcoService.saveOrUpdate(detalle, detalle.getSecCap(caller.getListFields(this, extra)))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			} else {
				if(!Util.esDiferente(cod,1,2)) mimarcoService.deleteCap400Del(detalle.id_n, detalle.nro_mreg);
//				Cap100Delitos cap100 = caller.getFieldName(mimarcoService.getCountC200xP208(detalle.id_n));
//				if(mimarcoService.saveOrUpdate(cap100, cap100.getSecCap(caller.getListFields(cap100, "[101-120]", "dn",
//						new String[]{"TOTAL_DELITOS", "SUM_FALLECIDOS"}), false))){
//					App.getInstance().getComisaria().v3_1 = Util.getInt(cap100.total_delitos);
//					caratulaService.saveOrUpdate(App.getInstance().getComisaria(), 
//							App.getInstance().getComisaria().getSecCap(Util.getListList("V3_1")));
//				}
				App.getInstance().getComisaria().v3_3 = mimarcoService.getConteoVictimas(App.getInstance().getComisaria().id_n);
				App.getInstance().getComisaria().v3_4 = mimarcoService.getConteoVictimasWhere(App.getInstance().getComisaria().id_n, null);
				caratulaService.saveOrUpdate(App.getInstance().getComisaria(), App.getInstance().getComisaria().getSecCap(Util.getListList("V3_3", "V3_4")));
				
				caller.reloadData(detalle, 1);
				new TaskExecute<String,String,Boolean>(caller).addCallback("updateCap1002plano", detalle.id_n).execute();
				
				C3CAP200_Fragment_201_02.this.dismiss();
				
				FragmentManager fm = caller.getFragmentManager();
				if(!Util.esDiferente(detalle.getP208(), 18,19,20,21,22,23)) {
					C3CAP200_Fragment_201_04 aperturaDialog = C3CAP200_Fragment_201_04.
							newInstance(caller, detalle, mimarcoService, caratulaService);
					aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
					aperturaDialog.show(fm, "aperturaDialog");
				} else {
					C3CAP200_Fragment_201_03 aperturaDialog = C3CAP200_Fragment_201_03.
							newInstance(caller, detalle, mimarcoService, caratulaService);
					aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
					aperturaDialog.show(fm, "aperturaDialog");
				}
			}
		} catch (SQLException e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
		}
//		if(!mimarcoService.saveCap200Delitos(detalle, Utilidades.getListFields(this, extra))){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
//		else {
//			if(cod.equals(1)) mimarcoService.deleteCap400Del(detalle.id_n, detalle.nro_mreg);
//			caller.reloadData(detalle, 1);
//			
//			C3CAP200_Fragment_201_02.this.dismiss();
//			
//			FragmentManager fm = caller.getFragmentManager();
//			C3CAP200_Fragment_201_03 aperturaDialog = C3CAP200_Fragment_201_03.
//					newInstance(caller, detalle, mimarcoService, caratulaService);
//			aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
//			aperturaDialog.show(fm, "aperturaDialog");
//		}
	}
	
	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccept() {
		Integer cod = (Integer)dc.get("clave");
		if(cod.equals(1)){
			detalle.ih209 = null; detalle.ih209_o = null; detalle.ih209_cod = null; detalle.ih209_cod_o = null; 
			detalle.ih210 = null; detalle.ih210_o = null; detalle.ih210_cod = null; detalle.ih210_cod_o = null; 
			detalle.ih215 = null; detalle.conte_reg400 = 0;
			savePag(cod);
		} 
	}

	@Override
	public <T extends IDetailEntityComponent> void postResultSearch(List<String> resps, T... result) {
		Search s = (Search)result[0];
		txtIH208_COD.setText(String.valueOf(s.codigo));
		txtIH208NOM.setText(s.nombre);
		txtIH208_COD_O.setText(resps.get(0));
		if(s.titulo != null){
			lblModT.setText(s.titulo);
		}
		if(s.subtitulo != null){
			lblModS.setVisibility(View.VISIBLE);
			lblModS.setText(s.subtitulo);
		} else {
			lblModS.setVisibility(View.GONE);
		}
		boolean esp = Util.esVacio(txtIH208_COD_O);
		lblEsp.setVisibility(esp ? View.GONE : View.VISIBLE);
		txtIH208_COD_O.setVisibility(esp ? View.GONE : View.VISIBLE);
	}

	@Override
	public <T extends IDetailEntityComponent> List<T> getListData(NIVEL nivel, T selecc) {
		if(nivel == Searchable1.NIVEL.NIVEL1){
			if(App.MODALIDADES != null) return (List<T>)App.MODALIDADES;
			return (List<T>)mimarcoService.getModalidades();
		}
		return null;
	}

	@Override
	public List<Object[]> getFieldsListData(NIVEL nivel) {
		if(nivel == NIVEL.NIVEL1) return Util.getListList(
				new Object[]{"CODIGO","NOMBRE"}, 
				new Object[]{R.string.lb_c3_cap200_p208_tr_5, R.string.lb_c3_cap200_p208_tr_6},
				new Object[]{0.7f, 2f});
		return null;
	}

	@Override
	public <T extends IDetailEntityComponent> boolean getHasEsp(T selecc, NIVEL nivel, KEY key) {
		if(key == KEY.ESPEC){
			if(nivel == NIVEL.NIVEL1) return !Util.esDiferente(((Search)selecc).codigo, 11,13,17,23,30,35,39,43,48,52,55,
					60,63,66,71,73,76,80,82,87,91,94,98,101,104,109,111,114,116,119,121,124,129,133,138,143,146,148,152,154,161,
					163,169,174,176,179,181,187,193,196,198,203,206,208,215,217,219,221,223,225,231,234,237,241,249,253,259,264,
					272,276); 
		}
		return false;
	}

	@Override
	public NIVEL getNiveles() {
		// TODO Auto-generated method stub
		return NIVEL.NIVEL1;
	}
}
