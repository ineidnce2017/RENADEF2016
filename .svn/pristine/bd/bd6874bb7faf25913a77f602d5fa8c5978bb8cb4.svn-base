package gob.inei.renadef2016.fragments;

import gob.inei.renadef2016.R;
import gob.inei.renadef2016.activity.CuestionarioFragmentActivity;
import gob.inei.renadef2016.common.App;
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
import gob.inei.dnce.components.TextAreaField;
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
public class C3CAP100_Fragment_122 extends FragmentForm implements Respondible {
	
	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtDN121;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtTOTAL_FALTAS;
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtTOTAL_DELITOS;
	@FieldAnnotation(orderIndex = 4)
	public TextAreaField txtOBS_03_100;

	private IntegerField v3_1, v3_2, v3_3, v3_4; 
	private LabelComponent lblTitulo, lblSubTitulo, lblSubTitulo1;
	private int pleft = 45, psleft = 80;
	private GridComponent2 gridDenuncias, grid_Tot;;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private Cap100Delitos cap100delitos;
	private DialogComponent dlg;
	private boolean check;

	public C3CAP100_Fragment_122() {
	}

	public C3CAP100_Fragment_122 parent(MasterActivity parent) {
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
		
//		Calculo<IntegerField> op = new Calculo<IntegerField>(this, txtDN120, 0, 0,
//				Util.getListList(txtDN112, txtDN113, txtDN114, txtDN115, txtDN116, txtDN117, txtDN118, txtDN119));
//		op.setConfOperacion(false);
//		op.setCallback("on_P120Result");
		
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
		lblSubTitulo1 = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_OBS).textSize(21).centrar()
				.negrita();
		
		LabelComponent lblP122 = new LabelComponent(this.getActivity())
		.size(altoComponente+35, 600).text(R.string.lb_c3_cap100_p122)
		.textSize(16).negrita().colorFondo(R.color.griscabece).colorTexto(R.color.blanco);
		txtDN121 = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).textAutomatico().centrar();
		LabelComponent lblTF = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p123)
		.textSize(16).negrita().colorFondo(R.color.griscabece).colorTexto(R.color.blanco);
		txtTOTAL_FALTAS = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).textAutomatico().centrar();
		LabelComponent lblTD = new LabelComponent(this.getActivity())
		.size(altoComponente, 600).text(R.string.lb_c3_cap100_p124)
		.textSize(16).negrita().colorFondo(R.color.griscabece).colorTexto(R.color.blanco);
		txtTOTAL_DELITOS = new IntegerField(this.getActivity()).maxLength(50)
				.size(altoComponente, 120).hint(R.string.hintNDN).textAutomatico().centrar();
		
		LabelComponent lblNDel = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbNDelitos).size(altoComponente+10, 220);
		LabelComponent lblNDen = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbNDenuncias).size(altoComponente+10, 220);
		LabelComponent lblNDVF = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbNVictFallecidos).size(altoComponente+10, 220);
		LabelComponent lblNVHD = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbNVictxHomDolosos).size(altoComponente+10, 220);
		
		v3_1 = new IntegerField(getActivity()).size(altoComponente, 140).readOnly().negrita().centrar();
		v3_2 = new IntegerField(getActivity()).size(altoComponente, 140).readOnly().negrita().centrar();
		v3_3 = new IntegerField(getActivity()).size(altoComponente, 140).readOnly().negrita().centrar();
		v3_4 = new IntegerField(getActivity()).size(altoComponente, 140).readOnly().negrita().centrar();
		
		grid_Tot = new GridComponent2(getActivity(), 4).colorFondo(R.color.blanco);
		grid_Tot.addComponent(lblNDel);
		grid_Tot.addComponent(v3_1);
		grid_Tot.addComponent(lblNDen);
		grid_Tot.addComponent(v3_2);
		grid_Tot.addComponent(lblNDVF);
		grid_Tot.addComponent(v3_3);
		grid_Tot.addComponent(lblNVHD);
		grid_Tot.addComponent(v3_4);
		
		txtOBS_03_100 = new TextAreaField(this.getActivity()).size(400, 720);
		
		gridDenuncias = new GridComponent2(this.getActivity(), 2);
		gridDenuncias.addComponent(lblP122);
		gridDenuncias.addComponent(txtDN121);
		gridDenuncias.addComponent(lblTF);
		gridDenuncias.addComponent(txtTOTAL_FALTAS);
		gridDenuncias.addComponent(lblTD);
		gridDenuncias.addComponent(txtTOTAL_DELITOS);
		
		
		/*Filtros - 112 - MODALIDADES.*/
		
		Filtros.setFiltro(txtDN121, Filtros.TIPO.NUMBER, 4, 0,null,0,9998);
		Filtros.setFiltro(txtOBS_03_100, Filtros.TIPO.ALFAN_U, 1000, null);
		
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
		LinearLayout q1 = createQuestionSection(grid_Tot.component());
		LinearLayout q2 = createQuestionSection(lblSubTitulo1, txtOBS_03_100);
		// ///////////////////////////
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
		form.addView(q1);
		form.addView(q2);
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
//			if(!getMarcoService().saveOrUpdate(cap100delitos, cap100delitos.getSecCap(Util.getListList("txtOBS_03_100")))){
//				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//						ToastMessage.MESSAGE_ERROR,
//						ToastMessage.DURATION_LONG);
//			} 
//		} catch (SQLException e) {
//			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
//					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
//			return false;
//		}
		
		return true;
	}
	
	private boolean saltoCap(int salto){
//    	int den = mimarcoService.fill_Cap200_Del(Globales.getInstance().getVid_np(), 
//    			Utilidades.getListFields(new String[]{"NRO_MREG"})).size();
//    	String message = den > 0 ? "No Existe Denuncias. Pero ya tiene Denuncias Registradas; "
//    			+ "por tanto \u00e9stas ser\u00e1n eliminadas. Esta seguro que desea continuar?":
//    				"No Existe Denuncias. Esta seguro que desea continuar?";
		String message = "No Existe Denuncias. Esta seguro que desea continuar?";
//    	if(den > 0){
			dlg = new DialogComponent(getActivity(), this, 
					TIPO_DIALOGO.YES_NO, getResources().getString(R.string.app_name), message);
			dlg.showDialog();
			return false;
//		} else {
//			ToastMessage.msgBox(this.getActivity(), message,
//					ToastMessage.MESSAGE_INFO,
//					ToastMessage.DURATION_LONG);
//			if(salto>0) parent.nextFragment(salto);
//		}
//    	return true;
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

//		if(Util.esVacio(txtDN121)){
//			mensaje="Preg 122. No puede estar vacia.";
//			view=txtDN121;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtTOTAL_FALTAS)){
//			mensaje="Total Faltas, No puede estar vacia.";
//			view=txtTOTAL_FALTAS;
//			error=true;
//			return false;	
//		}
//		if(Util.esVacio(txtTOTAL_DELITOS)){
//			mensaje="Total Delitos, No puede estar vacia.";
//			view=txtTOTAL_DELITOS;
//			error=true;
//			return false;	
//		}

		if(error) return false;	
		return true;
	}
	
	@Override
	public void cargarDatos() {
		check = false;
		cap100delitos = getMarcoService().getC100(App.getInstance().getComisaria(),
				new Cap100Delitos().getSecCap(getListFields(this/*, new String[]{"SUM_FALLECIDOS","DN101_1","DN101_2","DN101_3","DN101_4",
						"DN101_5","DN101_6","DN101", "DN102", "DN103", "DN104", "DN105", "DN106", "DN107", "DN108", "DN109", "DN110", "DN111",
						"DN112","DN113","DN114","DN115","DN116","DN117","DN118","DN119","DN120","FALTAS"}*/)));
		if (cap100delitos == null) {
			cap100delitos = new Cap100Delitos();
			cap100delitos.id_n = App.getInstance().getComisaria().id_n;
		} 
		
//		cap100delitos = null; check = false;
//		List<Cap100Delitos> lstc100Del = getMarcoService().fill_Cap100_Del(Globales.getInstance().getVid_np(),
//				Utilidades.getListFields(this, new String[]{"SUM_FALLECIDOS", "OBS_03_100","DN101_1","DN101_2","DN101_3","DN101_4","DN101_5","DN101_6",
//						"DN101", "DN102", "DN103", "DN104", "DN105", "DN106", "DN107", "DN108", "DN109", "DN110", "DN111"}));
//		if(lstc100Del.size()>0)
//			cap100delitos = lstc100Del.get(0);
//		if (cap100delitos == null) {
//			cap100delitos = new Cap100Delitos();
//		}
		
		entityToUI(cap100delitos);
//		txtDN121.setText(String.valueOf(cap100delitos.dn101_6));
		inicio();
	}
	
	private Integer getSumaP101toPP120() {
		return Util.getInt(cap100delitos.dn101)+Util.getInt(cap100delitos.dn102)+
				Util.getInt(cap100delitos.dn103)+Util.getInt(cap100delitos.dn104)+
				Util.getInt(cap100delitos.dn105)+Util.getInt(cap100delitos.dn106)+
				Util.getInt(cap100delitos.dn107)+Util.getInt(cap100delitos.dn108)+
				Util.getInt(cap100delitos.dn109)+Util.getInt(cap100delitos.dn110)+
				Util.getInt(cap100delitos.dn111)+Util.getInt(cap100delitos.dn112)+
				Util.getInt(cap100delitos.dn113)+Util.getInt(cap100delitos.dn114)+
				Util.getInt(cap100delitos.dn115)+Util.getInt(cap100delitos.dn116)+
				Util.getInt(cap100delitos.dn117)+Util.getInt(cap100delitos.dn118)+
				Util.getInt(cap100delitos.dn119)+Util.getInt(cap100delitos.dn120);
	}
	
	public void on_P120Result(Integer result){
//		txtDN120.setText(String.valueOf(getSumaP101toPP120()+result));
	}

	private void inicio() {
		v3_1.setText(Util.getText(App.getInstance().getComisaria().v3_1));
		v3_2.setText(Util.getText(App.getInstance().getComisaria().v3_2));
		v3_3.setText(Util.getText(App.getInstance().getComisaria().v3_3));
		v3_4.setText(Util.getText(App.getInstance().getComisaria().v3_4));
//		txtOBS_03_100.requestFocus();
	}
	
	@Override
	public Integer getSalto() {
//		if (Integer.valueOf(0).equals(cap100delitos.sum_fallecidos) && 
//				!Util.esVacio(txtDN120) && Integer.valueOf(txtDN120.getText().toString())>0) {
//			return 1;
//		}
//		if (!Util.esVacio(txtDN120) && Integer.parseInt(txtDN120.getText().toString())==0 && 
//				!Util.esVacio(txtDN121) && Integer.parseInt(txtDN121.getText().toString())==0) {
//			return 2;
//		}
		return super.getSalto();
	}

	@Override
	public void onAccept() {
		check = true;
		parent.nextFragment(CuestionarioFragmentActivity.CAPOBS_CUEST);
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
