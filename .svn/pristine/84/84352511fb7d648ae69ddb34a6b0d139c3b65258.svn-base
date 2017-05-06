package gob.inei.renadef2016.fragments;

import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.TableComponent;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.components.DialogComponent.TIPO_DIALOGO;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.dialog.C3CAP400_Fragment_401_01;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap300Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap400Delitos;
import gob.inei.renadef2016.service.MarcoService;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3CAP400_Fragment_401 extends FragmentForm implements Respondible {

	private TableComponent tcCap400;
	private LabelComponent lblTitulo,lblT;
	private GridComponent2 gridTot;
	private ImageComponent imgC,imgR;
	private MarcoService mimarcoService;
	private static List<Cap400Delitos> lstData;
	private static C3CAP400_Fragment_401 _this;
	public static Cap200Delitos cap400cap200;
	private DialogComponent dc;

	public C3CAP400_Fragment_401() {
	}

	public C3CAP400_Fragment_401 parent(MasterActivity parent) {
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
		_this = this;
		registerForContextMenu(tcCap400.getListView());
		initObjectsWithoutXML(this, rootView);
		enlazarCajas();
		listening();
		
		return rootView;
	}

	@Override
	protected void buildFields() {
		
		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_c3_cap400_title).textSize(21).centrar()
				.negrita();
		
		imgR = new ImageComponent(getActivity(), R.drawable.add, R.drawable.add);
		dimenView(imgR, 0.1f, Gravity.RIGHT|Gravity.CENTER_VERTICAL);
		imgR.setEnabled(false);
		imgC = new ImageComponent(getActivity(), R.drawable.add, R.drawable.add);
		dimenView(imgC, 1.0f, Gravity.CENTER);
		imgC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(cap400cap200.ih215 == null || Integer.valueOf(0).equals(cap400cap200.ih215)){
					ToastMessage.msgBox(getActivity(), "No existe Total de Denuncias con victimarios.",
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
					return;
				} else if(lstData.size()+1>cap400cap200.ih215){
						ToastMessage.msgBox(getActivity(), "N\u00famero de registros del CAP400 no "
								+ "puede ser mayor al N\u00famero de Victimarios del CAP200 (P215).",
								ToastMessage.MESSAGE_ERROR,
								ToastMessage.DURATION_LONG);
						return;
				} else {
					if(getMarcoService().getConteoDenunVictimario(App.getInstance().getComisaria().id_n)){
						ToastMessage.msgBox(getActivity(), "Conteo de registros totales del Cap400. no concuerda"
								+ " con la suma del n\u00famero de victimarios en Cap200.",
								ToastMessage.MESSAGE_ERROR,
								ToastMessage.DURATION_LONG);
						return;
					}
				}
				Integer nro_pvreg = 0;
				if(lstData.size()>0) nro_pvreg = lstData.get(lstData.size()-1).nro_pvreg;
				abrirDetalle(new Cap400Delitos(App.getInstance().getComisaria().id_n, 
						cap400cap200.nro_mreg, nro_pvreg+1, lstData.size()+1), lstData.size()+1);
			}
		});
		
		LabelComponent lbl = new LabelComponent(this.getActivity(), App.ESTILO).size(altoComponente, 150)
				.text(R.string.lb_c3_cap400_p1).textSize(16).centrar();
		lblT = new LabelComponent(this.getActivity()).size(altoComponente, 90).textSize(20).negrita().centrar();
		
		gridTot = new GridComponent2(this.getActivity(), 1, false);
		gridTot.addComponent(lbl);
		gridTot.addComponent(lblT);
		
		tcCap400 = new TableComponent(getActivity(), this,
				gob.inei.dnce.R.style.btnStyleHeaderGreen).size(497, 2600)
				.headerHeight(70).dataColumHeight(60);
		tcCap400.setHeaderTextSize(14);
		tcCap400.addHeader(R.string.lb_c3_cap400_p400, 0.7f, TableComponent.ALIGN.CENTER);
		tcCap400.addHeader(R.string.lb_c3_cap400_p401_tr, 4f, TableComponent.ALIGN.LEFT);
		tcCap400.addHeader(R.string.lb_c3_cap400_p402_tr, 2f);
		tcCap400.addHeader(R.string.lb_c3_cap400_p403_tr, 1.2f);
		tcCap400.addHeader(R.string.lb_c3_cap400_p404_tr, 1.2f);
		tcCap400.addHeader(R.string.lb_c3_cap400_p405_tr, 1.6f);
		tcCap400.addHeader(R.string.lb_c3_cap400_p406_tr, 1.8f);
		tcCap400.addHeader(R.string.lb_c3_cap400_p407_tr, 2.2f);
		tcCap400.addHeader(R.string.lb_c3_cap400_p408_tr, 1.8f);
		tcCap400.addHeader(R.string.lb_C_OBS, 6f, TableComponent.ALIGN.LEFT);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (v.getId() == tcCap400.getListView().getId()){
	    	menu.setHeaderTitle("Opciones");
	    	menu.add(1, 1, 1, "Editar");
	    	menu.add(1, 2, 2, "Eliminar");
	    } 
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if(!getUserVisibleHint()) return false;
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		Cap400Delitos cap400 = (Cap400Delitos) info.targetView.getTag();
		
		if( item.getItemId() == 1){
			abrirDetalle(cap400);
		}
		else if( item.getItemId() == 2){
			tryDeleteRow(cap400);
		}

		return true;
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout lyy = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER, gridTot.component());
		dimenView(lyy, 0.1f, Gravity.LEFT);
		LinearLayout q1 = createQuestionSection(0,Gravity.CENTER,LinearLayout.HORIZONTAL,lyy, imgC, imgR);
//		LinearLayout q1 = createQuestionSection(imgC);
		LinearLayout q2 = createQuestionSection(tcCap400.getTableView());
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
	
	private void dimenView(View view, float weight, int gravity){
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)view.getLayoutParams();
		lp.height = WRAP_CONTENT;
		lp.width = WRAP_CONTENT;
		lp.gravity = gravity;
		lp.weight = weight;
		view.setLayoutParams(lp);
	}
	
	public void reloadData(Cap400Delitos detalle, int opcion){
		switch (opcion) {
		case 1:
			if(lstData.isEmpty() || lstData.get(lstData.size()-1).nro_pvreg<detalle.nro_pvreg){
				lstData.add(detalle);
				tcCap400.getData().add(detalle);
			}
			break;
		case 2:
			lstData.remove(detalle);
			tcCap400.getData().remove(detalle);
			updateConteo();
			break;
		default:
			break;
		}
		tcCap400.reloadData();
		updateCap200();
		refreshBorder();
	}
	
	private void updateCap200() {
		cap400cap200.conte_reg400 = lstData.size();
		try {
			getMarcoService().saveOrUpdate(cap400cap200, cap400cap200.getSecCap(Util.getListList("CONTE_REG400")));
		} catch (SQLException e) {
		}
//		getMarcoService().saveCap200Delitos(C3CAP400_Fragment_401.cap400cap200, 
//				Utilidades.getListFields(new String[]{"CONTE_REG400"}));	
	}

	private void updateConteo(){
		if(lstData.size()>0){
			for(int x=0;x<lstData.size();x++){
				lstData.get(x).orden_400 = x+1;
			}
		}
	}
	
	private void tryDeleteRow(Cap400Delitos cap400){
		dc = new DialogComponent(getActivity(), this,
				TIPO_DIALOGO.YES_NO, getResources()
						.getString(R.string.app_name),
				"Esta intentando eliminar el registro del Victimario N\u00B0: "+cap400.orden_400
				+ ". Este proceso es irreversible. Esta seguro que desea continuar?");
		dc.put("objeto", cap400);
		dc.showDialog();
	}
	
	private void abrirDetalle(Cap400Delitos cap400) {
		abrirDetalle(cap400, cap400.orden_400);
	}
	
	private void abrirDetalle(Cap400Delitos cap400, Integer nro) {
		FragmentManager fm = C3CAP400_Fragment_401.this.getFragmentManager();
		C3CAP400_Fragment_401_01 aperturaDialog = C3CAP400_Fragment_401_01
				.newInstance(C3CAP400_Fragment_401.this, cap400, nro, getMarcoService());
		aperturaDialog.show(fm, "aperturaDialog");
	}

	@Override
	public boolean grabar() {
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
		return true;
	}

	private boolean validar() {
String pregunta_completa = getString(R.string.pregunta_completa);
		
		if(lstData.size() > 0){
			for(int i=0; i<lstData.size(); i++){
				if (Util.esVacio(lstData.get(i).ivm408)) {
					ToastMessage.msgBox(
							this.getActivity(),
							pregunta_completa.replace("$", "Victimario N°: "									
									+ lstData.get(i).orden_400 + ". "  
									+ "“"
									+ lstData.get(i).ivm401a +" "+lstData.get(i).ivm401b +" "+ lstData.get(i).ivm401c + ".”" ),
							ToastMessage.MESSAGE_INFO,
							ToastMessage.DURATION_LONG);
					view = tcCap400;
					error = true;
					return false;
				}
			}
		}
		return true;
	}
	
	public static LinearLayout createTitle(Context context, String title, View view, View view1, View view2){
		return createTitle(context, title, null, view, view1, view2);
	}
	
	public static LinearLayout createTitle(Context context, String title, String stitle, View view, View view1, View view2){
		LinearLayout ly = createLY(context, LinearLayout.VERTICAL);
		LabelComponent lb = new LabelComponent(context).text(title).
				textSize(23).colorTexto(R.color.azulaceroclaro).negrita();
		LinearLayout sly = createLY(context, LinearLayout.VERTICAL);
		sly.addView(lb, new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT));
		if(stitle != null){
			LabelComponent lbs = new LabelComponent(context).text(stitle).
					textSize(15).colorTexto(R.color.orange).negrita();
			sly.addView(lbs, new LayoutParams(/*LinearLayout.LayoutParams.MATCH_PARENT*/ 570, 
					LinearLayout.LayoutParams.WRAP_CONTENT));
		}
		LinearLayout btnMant = _this.createQuestionSection(0, Gravity.CENTER, LinearLayout.HORIZONTAL, 
				view1, sly, view2);
		ly.addView(btnMant);
		ly.addView(view);
		return ly;
	}
	
	public static LinearLayout createLY(Context context, int orientacion){
		LinearLayout ly = new LinearLayout(context);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT);	
		ly.setLayoutParams(llp);
		ly.setOrientation(orientacion);
		return ly;
	}

	@Override
	public void cargarDatos() {
		cap400cap200 = App.getInstance().getCap200();
//		Log.e("cap400cap200", "cap400cap200:id_n: "+cap400cap200.id_n);
//		Log.e("cap400cap200", "cap400cap200:nro: "+cap400cap200.nro_mreg);
		lstData = getMarcoService().getC400s(App.getInstance().getComisaria().id_n, cap400cap200.nro_mreg,
				new Cap400Delitos().getSecCap(getListFields(new Cap400Delitos()), false));
		tcCap400.setData(lstData, "ORDEN_400", "getP401", "getP402", "getP403", "IVM404", "getP405", 
				"getP406", "getP407", "getP408", "OBS_03_400");
		
//		cap400cap200 = null;
//		List<Cap200Delitos> lstc200Del = getMarcoService().fill_Cap200_Del(Globales.getInstance().getVid_np(), 
//				Globales.getInstance().getVnro_mreg(), Utilidades.getListFields(new String[]{"IH204", "NRO_MREG",
//						"IH213", "IH214"}));
//		if(lstc200Del.size()>0)	cap400cap200 = lstc200Del.get(0);
//		
//		lstData = getMarcoService().fill_Cap400_Del(Globales.getInstance().getVid_np(), Globales.getInstance().getVnro_mreg());
//		tcCap400.setData(lstData, "ORDEN_400", "getP401", "getP402", "IVM403", "IVM404", "getP405", 
//				"IVM406", "IVM407", "IVM408", "OBS_03_400");
		
		refreshBorder();
		inicio();
	}
	
	public static List<Cap400Delitos> getData(){
		return lstData;
	}
	
	private void refreshBorder(){
		for(int x=0;x<lstData.size();x++){
			if(lstData.get(x).ivm402!=null && lstData.get(x).ivm408!=null){
				tcCap400.setBorderRow(x);
			} else {
				tcCap400.setBorderRow(x, true, R.color.red);
			}
		}
		lblT.setText(String.valueOf(lstData.size())+" / "+String.valueOf(cap400cap200.ih215));
		imgR.setImageDrawable(getResources().getDrawable(Util.getInt(cap400cap200.ih215) == lstData.size() ? 
				R.drawable.symbol_ok:R.drawable.warning_error));
	}

	private void inicio() {

	}

	@Override
	public void onAccept() {
		Cap400Delitos cap400 = (Cap400Delitos)dc.get("objeto");
		if(cap400 != null){
			getMarcoService().deleteCap400Del(cap400.id_n, cap400.nro_mreg, cap400.nro_pvreg);
			reloadData(cap400, 2);
		}
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
}
