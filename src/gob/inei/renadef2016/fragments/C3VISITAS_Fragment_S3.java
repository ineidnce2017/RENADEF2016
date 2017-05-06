package gob.inei.renadef2016.fragments;

import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.DialogComponent.TIPO_DIALOGO;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.TableComponent;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Util;
import gob.inei.dnce.util.Util.COMPLETAR;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.activity.CuestionarioFragmentActivity;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.dialog.C3CAPObservaciones;
import gob.inei.renadef2016.fragments.dialog.C3VISITAS_Fragment_S3_01;
import gob.inei.renadef2016.fragments.dialog.C3VISITAS_Fragment_S3_02;
import gob.inei.renadef2016.modelo.DelVisita;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.service.ATVisitaService;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3VISITAS_Fragment_S3 extends FragmentForm implements Respondible {

	private TableComponent tcCapVis;
	private LabelComponent lblTitulo, lblTitulo1, lblFecR, lblEspr, lblCodR;
	private INF_Caratula01Service caratulaService;
	private ATVisitaService atvisitaService;
	private MarcoService mimarcoService;
	public List<DelVisita> lstData;
	private INF_Caratula01 caratula;
	private GridComponent2 grid_Res;
	public static Cap100Delitos c100;
	public static C3VISITAS_Fragment_S3 _this;
	public static enum ACCION {ADD, EDIT, FINISH, DELETE, RESUMEN}
	private ACCION action;
	private String[] results;
	private DialogComponent dc;

	public C3VISITAS_Fragment_S3() {
	}

	public C3VISITAS_Fragment_S3 parent(MasterActivity parent) {
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
		_this = this;
		rootView = createUI();
		setHasOptionsMenu(true);
		caratula = App.getInstance().getComisaria();
		results = getActivity().getResources().getStringArray(R.array.arrayResultadosDE);
		registerForContextMenu(tcCapVis.getListView());
		initObjectsWithoutXML(this, rootView);
		enlazarCajas();
		listening();
		
		return rootView;
	}

	@Override
	protected void buildFields() {
		
		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_11Entrevista).textSize(21).centrar()
				.negrita();
		lblTitulo1 = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_12Resultado).textSize(21).centrar()
				.negrita();
		
		tcCapVis = new TableComponent(getActivity(), this,
				gob.inei.dnce.R.style.btnStyleHeaderGreen).size(440, 1400)
				.headerHeight(60).dataColumHeight(75);
		tcCapVis.setHeaderTextSize(14);
		tcCapVis.addHeader(R.string.lb_V_Visita, 0.6f, TableComponent.ALIGN.CENTER);
		tcCapVis.addHeader(R.string.lb_V_Fecha, 1.4f, TableComponent.ALIGN.CENTER);
		tcCapVis.addHeader(R.string.lb_V_HI, 1f);
		tcCapVis.addHeader(R.string.lb_V_HF, 1f);
		tcCapVis.addHeader(R.string.lb_V_PVF, 1.4f);
		tcCapVis.addHeader(R.string.lb_V_PVH, 1.2f);
		tcCapVis.addHeader(R.string.lb_V_RV, 1.2f);
		tcCapVis.addHeader(R.string.lb_V_Fecha, 1.4f, TableComponent.ALIGN.CENTER);
		tcCapVis.addHeader(R.string.lb_V_HI, 1f);
		tcCapVis.addHeader(R.string.lb_V_HF, 1f);
		tcCapVis.addHeader(R.string.lb_V_RV, 1.2f);
		tcCapVis.setCellColor(-1, 6, true);
		
		LabelComponent lblFRes = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Visita_FecR).size(altoComponente, 200);
		lblFecR = new LabelComponent(getActivity()).size(altoComponente, 160).negrita().centrar();
		LabelComponent lblCRes = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Visita_ResF).size(altoComponente, 200);
		lblCodR = new LabelComponent(getActivity()).size(altoComponente, 160).negrita().centrar();
		LabelComponent lblEspc = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Visita_Espc).size(altoComponente, 350).centrar();
		lblEspr = new LabelComponent(getActivity()).size(altoComponente, 350);
		
		grid_Res = new GridComponent2(getActivity(), 3).colorFondo(R.color.blanco);
		grid_Res.addComponent(lblFRes);
		grid_Res.addComponent(lblFecR);
		grid_Res.addComponent(lblEspc);
		grid_Res.addComponent(lblCRes);
		grid_Res.addComponent(lblCodR);
		grid_Res.addComponent(lblEspr);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.visitf, menu);
		super.onCreateOptionsMenu(menu,inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if( item.getItemId() == R.id.btnIniciar ){
			tryAddVisit(ACCION.ADD);
		}
		else if( item.getItemId() == R.id.btnTerminar ){
			tryFinalizeVisit(ACCION.FINISH);
		}
		else if( item.getItemId() == R.id.addObservation ){
			addObservacion();
		}
//		else if( item.getItemId() == R.id.savebutton ){
////			guardar(null);
//		}
		return true;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		DelVisita visit = (DelVisita)info.targetView.getTag();
		
		if( v.getId() == tcCapVis.getListView().getId() ){
			menu.setHeaderTitle("Opciones");
			menu.add(0, 1, 1, "Editar visita");
			menu.add(0, 2, 2, "Eliminar visita");
			menu.add(0, 3, 3, "Iniciar visita supervisor");
			menu.add(0, 4, 4, "Terminar visita supervisor");
		}
		
		if( visit.iii_1 < lstData.size() ){
			menu.findItem(1).setVisible(false);
			menu.findItem(2).setVisible(false);
			menu.findItem(3).setVisible(false);
			menu.findItem(4).setVisible(false);
		}
		
		if(isEstadoCerrado()){
			menu.findItem(1).setVisible(false);
			menu.findItem(2).setVisible(false);
		}
		
		if(visit.iii_5==null){
			menu.findItem(1).setVisible(false);
		}
		
		if(visit.iii_6_d!=null){
			menu.findItem(3).setVisible(false);
		} else {
			menu.findItem(4).setVisible(false);
		}
		
		if(visit.iii_8!=null){
			menu.findItem(4).setVisible(false);
		}
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		DelVisita visit = (DelVisita)info.targetView.getTag();
		
		if( item.getItemId() == 1 ){
			editVisit(visit, ACCION.EDIT);
			return true;
		}
		if( item.getItemId() == 2 ){
			tryDeleteVisit(visit);
			return true;
		}
		if( item.getItemId() == 3 ){
			addVisitSup(visit);
			return true;
		}
		if( item.getItemId() == 4 ){
			finalizeVisitSup(ACCION.FINISH);
			return true;
		}

		return super.onContextItemSelected(item);
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q1 = createQuestionSection(tcCapVis.getTableView());
		LinearLayout q2 = createQuestionSection(lblTitulo1);
		LinearLayout q3 = createQuestionSection(grid_Res.component());
		// ///////////////////////////
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
		form.addView(q1);
		form.addView(q2);
		form.addView(q3);
		/* Aca agregamos las preguntas a la pantalla */
		return contenedor;
	}
	
	private void addObservacion(){
		FragmentManager fm = C3VISITAS_Fragment_S3.this.getFragmentManager();
		C3CAPObservaciones aperturaDialog = C3CAPObservaciones
				.newInstance(C3VISITAS_Fragment_S3.this, caratula, 1);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}
	
	private boolean isEstadoCerrado(){
		return Integer.valueOf(2).equals(caratula.estado_envio);
	}
	
	public void reloadData(DelVisita visita, int opcion){
		switch (opcion) {
		case 1:
			if(lstData.size()<visita.iii_1.intValue()){
				lstData.add(visita);
				tcCapVis.getData().add(visita);
			}
			break;
		case 2:
			removeObject(visita);
			break;
		default:
			break;
		}
		tcCapVis.reloadData();
	}
	
	private void removeObject(DelVisita detalle){
		if(lstData.size()>0){
			for(int x=0;x<lstData.size();x++){
				if(lstData.get(x).iii_1.equals(detalle.iii_1)){
					lstData.remove(x);
					tcCapVis.getData().remove(x);
				}
			}
		}
	}
	
	private void tryDeleteVisit(final DelVisita visit){
		action = ACCION.DELETE;
		dc = new DialogComponent(getActivity(), this,
				TIPO_DIALOGO.YES_NO, getResources()
						.getString(R.string.app_name),
			"Esta a punto de eliminar una visita, este proceso es irreversible. "
			+ "\u00bfEsta seguro que desea continuar?");
		dc.put("obj", visit);
		dc.showDialog();
	}
	
	public DelVisita getDownCodVisit(){
		Integer down = 100; 
		DelVisita _vis = null;
		if(lstData.size()>0){
			for(DelVisita _v:lstData){
				if(_v.iii_5 == null) return null;
				if(_v.iii_5.equals(5) || _v.iii_5.equals(6)) return _v;
				if(down > _v.iii_5){
					down = _v.iii_5;
					_vis = _v;
				}
			}
		}
		return _vis;
	}
	
	public Integer grabarCaratulaCobertura(Integer result){
		return grabarCaratulaCobertura(result, null);
	}
	public Integer grabarCaratulaCobertura(Integer result, Integer val){
		if(lstData.size()>0) lstData.get(lstData.size()-1).iii_5 = result;
		DelVisita lastVisit = getDownCodVisit();
		if(val != null && lastVisit!=null && val.equals(lastVisit.iii_5)) return val;
		String []extra = new String[]{"IVRESFIN_03"};
		if(lstData.size()>0 && lastVisit!=null){
			caratula.ivresfin_03 = lastVisit.iii_5;
		} else {
			caratula.ivresfin_03 = null;
		}
		try {
			getCaratulaService().saveOrUpdate(caratula, caratula.getSecCap(Util.getListList(extra)));
		} catch (SQLException e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
		} 
		return caratula.ivresfin_03;
	}
	public void grabarCaratula(){
		//Ultima visita
		DelVisita lastVisit = getDownCodVisit();
		String []extra = new String[]{"IV3_D", "IV3_M", "IV3_A", "IV3_1", "IVRESFIN_03", "IVRESFIN_03_O"};
		if(lstData.size()>0 && lastVisit!=null){
			DelVisita _visit = lstData.get(lstData.size() - 1);
			caratula.iv3_1 = lastVisit.iii_5==null?null:Util.getFechaFormateada(""+_visit.iii_2_a, 
					_visit.iii_2_m, _visit.iii_2_d, "dd/MM/yyyy");//lastVisit.iii2_2_d;
			caratula.iv3_d = Util.completarCadena(_visit.iii_2_d, "0", 2, COMPLETAR.IZQUIERDA);
			caratula.iv3_m = Util.completarCadena(_visit.iii_2_m, "0", 2, COMPLETAR.IZQUIERDA);
			caratula.iv3_a = _visit.iii_2_a;
			caratula.ivresfin_03 = lastVisit.iii_5;
			caratula.ivresfin_03_o = lastVisit.iii_5_o;
			if(!Util.esDiferente(Util.getInt(caratula.ivresfin_03),3,5,6,7) && c100!=null &&
					Integer.valueOf(0).equals(c100.total_delitos) && 
					Integer.valueOf(0).equals(c100.dn121) &&
					Integer.valueOf(0).equals(c100.total_faltas)){
				getMarcoService().deleteCap100Del(caratula.id_n);
				caratula.v3_1 = null; caratula.v3_2 = null;
				caratula.v3_3 = null; caratula.v3_4 = null;
				extra = new String[]{"IV3_D", "IV3_M", "IV3_A", "IV3_1", "IVRESFIN_03", "IVRESFIN_03_O",
						"V3_1", "V3_2", "V3_3", "V3_4"};
			}
		} else {
			caratula.iv3_d = null;
			caratula.iv3_m = null;
			caratula.iv3_a = null;
			caratula.iv3_1 = null;
			caratula.ivresfin_03 = null;
			caratula.ivresfin_03_o = null;
		}
		
		try {
			getCaratulaService().saveOrUpdate(caratula, caratula.getSecCap(Util.getListList(extra)));
			reloadResult();
			int _res = Util.getInt(caratula.ivresfin_03);
			verResumenCap100(lstData.size()>0 && lastVisit!=null && action != ACCION.DELETE
					&& _res != 6 && _res != 3);
		} catch (Exception e) {
		}
		 
//		VERIFICACIONES
		ArrayList<String> checkList= new ArrayList<String>();
		if(caratula!=null && caratula.ivresfin_03!=null && caratula.ivresfin_03!=1){
			String mensaje = "Recuerde que debe registrar Observaciones si es el Resultado Final.";
			checkList.add(mensaje);
		}
		
		if(checkList.size()>0){
			for(String s: checkList){
				ToastMessage.msgBox(this.getActivity(), s,
						ToastMessage.MESSAGE_INFO,
						ToastMessage.DURATION_LONG);
			}
		}
	}
	
	private void reloadResult() {
		String result="";
		lblFecR.setText(caratula.iv3_1);
		if(caratula.ivresfin_03!=null){
			if(results.length>caratula.ivresfin_03.intValue()){
				result = results[caratula.ivresfin_03.intValue()];
			}
			if(caratula.ivresfin_03.intValue() != 6){
				grid_Res.hideColumn(2);
			} else {
				grid_Res.hideColumn(2, View.VISIBLE);
				lblEspr.setText(caratula.ivresfin_03_o);
			}
		} else {
			grid_Res.hideColumn(2);
		}
		lblCodR.setText(result);
	}
	
	private void verResumenCap100(boolean view){
		if(view){
			action = ACCION.RESUMEN;
			DialogComponent dc =  new DialogComponent(getActivity(), this,
					TIPO_DIALOGO.NEUTRAL, getResources()
					.getString(R.string.app_name), "Visita Terminada. "
							+ "Se mostrar\u00e1 Resumen del Cap100.");
			dc.showDialog();
		}
	}

	private void deleteVisit(DelVisita visit){
		try {
			getVisitaService().borrarVisita(visit);
			reloadData(visit,2);
			grabarCaratula();
		} catch (SQLException e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
		}
	}
	
	private void addVisitSup(DelVisita visita){
		maintenanceVisitSup(visita, ACCION.ADD);
	}
	
	private void finalizeVisitSup(ACCION accion){
		DelVisita visita = lstData.get(lstData.size()-1);
		maintenanceVisitSup(visita, accion);
	}
	
	private void tryAddVisit(final ACCION accion){
		if( lstData.size()>0 ){
			if(isEstadoCerrado()){
				ToastMessage.msgBox(this.getActivity(), "Estado de Envio Cerrado. No puede Iniciar una Visita.",
						ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
				return;
			}
			DelVisita visita = lstData.get(lstData.size()-1);
			if( visita.iii_5 == null ){
				ToastMessage.msgBox(this.getActivity(), "Debe terminar la visita actual antes de iniciar una nueva.",
						ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
				return;
				
			} 
			else if(visita.iii_5.intValue() == 1 || visita.iii_5.intValue() == 3 || 
					visita.iii_5.intValue() == 5 || visita.iii_5.intValue() == 6){
				ToastMessage.msgBox(this.getActivity(), "Entrevista Finalizada; No puede iniciar otra visita.",
						ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
				return;
			}
		}
		
		addVisit(accion);
	}
	
	private void addVisit(ACCION accion){
		DelVisita visita = new DelVisita();
		visita.id_n = caratula.id_n;
		visita.iii_1 = lstData.size()+1;

		maintenanceVisit(visita, accion);
	}
	
	private void tryFinalizeVisit(final ACCION accion){
		if( lstData.size() == 0 ){
			ToastMessage.msgBox(this.getActivity(), "A\u00fan no ha iniciado ninguna visita",
					ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
			return;
		}
		else if( lstData.get(lstData.size()-1).iii_5 != null ){
			ToastMessage.msgBox(this.getActivity(), "No existe ninguna visita que necesite ser finalizada",
					ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
			return;
		}
		
		finalizeVisit(accion);
	}
	
	private void finalizeVisit(ACCION accion){
		DelVisita visita = lstData.get(lstData.size()-1);		
		maintenanceVisit(visita, accion);
	}
	
	private void editVisit(DelVisita visita, ACCION accion){
		maintenanceVisit(visita, accion);
	}
	
	private void maintenanceVisit(DelVisita visita, ACCION accion){
		FragmentManager fm = C3VISITAS_Fragment_S3.this.getFragmentManager();
		C3VISITAS_Fragment_S3_01 aperturaDialog = C3VISITAS_Fragment_S3_01
				.newInstance(C3VISITAS_Fragment_S3.this, visita, getVisitaService(),accion);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}
	
	private void maintenanceVisitSup(DelVisita visita, ACCION accion){
		FragmentManager fm = C3VISITAS_Fragment_S3.this.getFragmentManager();
		C3VISITAS_Fragment_S3_02 aperturaDialog = C3VISITAS_Fragment_S3_02
				.newInstance(C3VISITAS_Fragment_S3.this, visita, getVisitaService(),accion);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}
	
	@Override
	public boolean grabar() {
		if( !validar() ) return false;
		return true;
	}

	private boolean validar() {
		String mensaje = "";
		boolean error = false;
		boolean warning = false;
		
		if(App.ONLY_VISUALITATION) return true;
		if( lstData.size() == 0 ){
			mensaje = "Debe iniciar una visita para poder continuar";
			error = true;
		}
		else if( lstData.get(lstData.size()-1).iii_5 != null ){
			if(lstData.get(lstData.size()-1).iii_5.intValue() == 3 || 
					lstData.get(lstData.size()-1).iii_5.intValue() == 5 ||
							lstData.get(lstData.size()-1).iii_5.intValue() == 6);
			else {
				mensaje = "No existe ninguna visita abierta. Inicie una nueva visita antes de continuar";
				error = true;
			}
		}
		
		if (error) {
			if (!mensaje.equals("")) ToastMessage.msgBox(this.getActivity(), mensaje,
					ToastMessage.MESSAGE_ERROR,
					ToastMessage.DURATION_LONG);
			return false;
		}
		
		if( warning ){
			if(!mensaje.equals("")) ToastMessage.msgBox(this.getActivity(), mensaje,
					ToastMessage.MESSAGE_INFO,
					ToastMessage.DURATION_LONG);
		}
		
		return true;
	}

	@Override
	public void cargarDatos() {
//		c100 = getMarcoService().fill_Cap100_Del(caratula.id_n,	getListFields(new String[]{"SUM_FALLECIDOS", "DN120", "DN121"}));
		c100 = getMarcoService().getC100(App.getInstance().getComisaria(),
				new Cap100Delitos().getSecCap(Util.getListList("TOTAL_DELITOS", "DN121", "TOTAL_FALTAS")));
		lstData = getVisitaService().getDelVisitas(caratula);
		tcCapVis.setData(lstData, "III_1", "getFechaE", "getHoraE_from", "getHoraE_to", "getFechaEPV", "getHoraE_pv", 
				"III_5", "getFechaS", "getHoraS_from", "getHoraS_to", "III_8");
	
		inicio();
	}

	private void inicio() {
		reloadResult();
	}
	
	@Override
	public boolean getSaltoNavegation() {
		return validar();
	}

	@Override
	public void onAccept() {
		switch (action) {
			case RESUMEN:
				action = null;
				App.ONLY_VISUALITATION = true;
				parent.nextFragment(CuestionarioFragmentActivity.CAP100_DEL);
				return;
			case DELETE:
				DelVisita obj = (DelVisita)dc.get("obj");
				if(obj != null)	deleteVisit(obj);
				action = null;
				return;
			default:
				break;
		}
//		if(action == ACCION.RESUMEN){
//			action = null;
//			App.ONLY_VISUALITATION = true;
//			parent.nextFragment(CuestionarioFragmentActivity.CAP100_DEL);
//			return;
//		} 
//		DelVisita obj = (DelVisita)dc.get("obj");
//		if(obj != null)
//			deleteVisit(obj);
	}

	@Override
	public void onCancel() {
		action = null;
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
	
	public ATVisitaService getVisitaService() {
		if (atvisitaService == null) {
			atvisitaService = ATVisitaService.getInstance(getActivity());
		}
		return atvisitaService;
	}
	
	public static LinearLayout createTitle(Context context, View view, View... views){
		LinearLayout ly = createLY(context, LinearLayout.VERTICAL);
		for(View v:views){
			ly.addView(_this.createQuestionSection(v));
		}
		ly.addView(view);
		return ly;
	}
	
	public static LinearLayout createLY(Context context, int orientacion){
		LinearLayout ly = new LinearLayout(context);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT);	
		ly.setLayoutParams(llp);
		ly.setOrientation(orientacion);
		ly.setBackgroundColor(_this.getResources().getColor(COLOR_LINEA_SECCION_PREGUNTA));
		return ly;
	}
}
