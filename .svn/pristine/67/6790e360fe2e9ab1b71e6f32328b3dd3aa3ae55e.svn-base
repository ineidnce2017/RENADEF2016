package gob.inei.renadef2016.fragments;

import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.TableComponent;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.dao.ReportesDAO;
import gob.inei.renadef2016.service.ReportesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

@SuppressLint("ResourceAsColor")
public class ReporteFragment_001 extends FragmentForm implements Respondible {

	private String TAG = ReporteFragment_001.this.getClass().getSimpleName();
	
	private TableComponent tcReport01, tcReport02;
	private LabelComponent lblTitulo;
	private ReportesService reportService;
	private static List<Map<String, Object>> mpReport01, mpReport02;

	public ReporteFragment_001 parent(MasterActivity parent) {
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
		cargarDatos();
		enlazarCajas();
		listening();
		return rootView;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
//		AdapterView.AdapterContextMenuInfo info;
//		info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//		if (v.equals(tcReport01.getListView())) {
//			menu.setHeaderTitle("Opciones de Reporte");
////			/* 0 */menu.add(1, 0, 1, "Aperturar");
//		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (!getUserVisibleHint())
			return false;
//		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
//				.getMenuInfo();
		return true;
	}
	
	@Override
	protected View createUI() {
		buildFields();
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q1 = createQuestionSection(tcReport01.getTableView());
		LinearLayout q2 = createQuestionSection(tcReport02.getTableView());
		
		registerForContextMenu(tcReport01.getListView());
		registerForContextMenu(tcReport02.getListView());
		
		tcReport01.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@SuppressLint("ResourceAsColor")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Map<String, Object> mp = (Map<String, Object>)parent.getItemAtPosition(position);	
//				Log.e("id", "id: "+mp.get("ID_N"));
				mpReport02 = getReportesService().getReport006((String)mp.get("ID_N"));
				List<Map<String, Object>> lmap = null;
				tcReport02.setMaps((lmap = getListPers(mpReport02)), ReportesDAO.r006);
				refreshColorR002(lmap);
//				clearCP(tcReport03, tcReport04);
			}
		});
//		
//		tcReport02.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
//			@SuppressLint("ResourceAsColor")
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				Map<String, Object> mp = (Map<String, Object>)parent.getItemAtPosition(position);	
//				Log.e("id", "id: "+mp.get("ID_N"));
//				Log.e("NRO_MREG", "NRO_MREG: "+mp.get("NRO_MREG"));
//				mpReport03 = getReportesService().getReport003((String)mp.get("ID_N"), (Integer)mp.get("NRO_MREG"));
//				tcReport03.setMaps(mpReport03, getArray(ReportesDAO.r003, "ID_N"));
//				
//				mpReport04 = getReportesService().getReport004((String)mp.get("ID_N"), (Integer)mp.get("NRO_MREG"));
//				tcReport04.setMaps(mpReport04, getArray(ReportesDAO.r004, "ID_N"));
//			}
//		});
		
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
		form.addView(q1);
		form.addView(q2);
//		form.addView(q3);
		// /*Aca agregamos las preguntas a la pantalla*/
		return contenedor;
	}

	@Override
	protected void buildFields() {
		lblTitulo = new LabelComponent(getActivity(),
			App.ESTILO).text(R.string.r_tc_reptit).size(75, MATCH_PARENT).textSize(22).centrar();
		tcReport01 = new TableComponent(getActivity(), this,
				App.ESTILO).size(270, 750)
				.dataColumHeight(65).headerHeight(60).headerTextSize(15);
		tcReport01.addHeader(R.string.r_tc_id, 0.55f);
		tcReport01.addHeader(R.string.r_tc_ncom, 2f, TableComponent.ALIGN.LEFT);
		tcReport01.addHeader(R.string.r_tc_gps, 0.7f);
		tcReport01.addHeader(R.string.r_tc_resfin, 0.55f);
//		tcReport01.addHeader(R.string.r_tc_totfall, 0.75f);
//		tcReport01.addHeader(R.string.r_tc_tot200, 0.7f);
		tcReport01.setCellColor(-1, 0, true);
		tcReport01.setCellColor(-1, 2, true);
		
		tcReport02 = new TableComponent(getActivity(), this,
				App.ESTILO).size(620, 750)
				.dataColumHeight(65).headerHeight(60).headerTextSize(15);
		tcReport02.addHeader(R.string.r_tc_id, 0.5f);
		tcReport02.addHeader(R.string.r_tc_ptipmod, 3f, TableComponent.ALIGN.LEFT);
		tcReport02.addHeader(R.string.r_tc_total, 0.5f);
//		tcReport02.addHeader(R.string.r_tc_p215, 0.5f);
//		tcReport02.addHeader(R.string.r_tc_tot300, 0.6f);
//		tcReport02.addHeader(R.string.r_tc_tot400, 0.6f);
		tcReport02.setCellColor(-1, 0, true);
		tcReport02.setCellColor(-1, 2, true);
		
//		tcReport03 = new TableComponent(getActivity(), this,
//				App.ESTILO).size(400, 370)
//				.dataColumHeight(65).headerHeight(60).headerTextSize(15);
////		tcReport03.addHeader(R.string.r_tc_id, 0.35f);
//		tcReport03.addHeader(R.string.r_tc_orden, 0.3f);
//		tcReport03.addHeader(R.string.r_tc_nombre_vf, 1.2f);
//		tcReport03.setCellColor(-1, 0, true);
//		
//		tcReport04 = new TableComponent(getActivity(), this,
//				App.ESTILO).size(400, 370)
//				.dataColumHeight(65).headerHeight(60).headerTextSize(15);
////		tcReport04.addHeader(R.string.r_tc_id, 0.35f);
//		tcReport04.addHeader(R.string.r_tc_orden, 0.3f);
//		tcReport04.addHeader(R.string.r_tc_nombre_pv, 1.2f);
//		tcReport04.setCellColor(-1, 0, true);
	}
	
//	private void clearCP(TableComponent... tcs){
//		for(TableComponent tc:tcs){
//			tc.setMaps(null, "");
//		}
//	}
	
	public void recargarLista() {
		tcReport01.setMaps(mpReport01, ReportesDAO.r005);
		refreshColorR001();
	}
	
	private List<Map<String, Object>> getListPers(List<Map<String, Object>> lmap){
		List<Map<String, Object>> lmp = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> map:lmap){
			for(String s:ReportesDAO.r0061){
				if(s.equals("ID_N")) continue;
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("ID_N", map.get("ID_N"));
				m.put("TIPOMODALIDAD", getTipo(s));
				m.put("TOTAL", map.get(s));
				lmp.add(m);
			}
		}
		return lmp;
	}
	
	private String getTipo(String tipo){
		if(tipo.equals("DN101")) return getResources().getString(R.string.lb_c3_cap100_p101);
		if(tipo.equals("DN102")) return getResources().getString(R.string.lb_c3_cap100_p102);
		if(tipo.equals("DN103")) return getResources().getString(R.string.lb_c3_cap100_p103);
		if(tipo.equals("DN104")) return getResources().getString(R.string.lb_c3_cap100_p104);
		if(tipo.equals("DN105")) return getResources().getString(R.string.lb_c3_cap100_p105);
		if(tipo.equals("DN106")) return getResources().getString(R.string.lb_c3_cap100_p106);
		if(tipo.equals("DN107")) return getResources().getString(R.string.lb_c3_cap100_p107);
		if(tipo.equals("DN108")) return getResources().getString(R.string.lb_c3_cap100_p108);
		if(tipo.equals("DN109")) return getResources().getString(R.string.lb_c3_cap100_p109);
		if(tipo.equals("DN110")) return getResources().getString(R.string.lb_c3_cap100_p110);
		if(tipo.equals("DN111")) return getResources().getString(R.string.lb_c3_cap100_p111);
		if(tipo.equals("DN112")) return getResources().getString(R.string.lb_c3_cap100_p112);
		if(tipo.equals("DN113")) return getResources().getString(R.string.lb_c3_cap100_p113);
		if(tipo.equals("DN114")) return getResources().getString(R.string.lb_c3_cap100_p114);
		if(tipo.equals("DN115")) return getResources().getString(R.string.lb_c3_cap100_p115);
		if(tipo.equals("DN116")) return getResources().getString(R.string.lb_c3_cap100_p116);
		if(tipo.equals("DN117")) return getResources().getString(R.string.lb_c3_cap100_p117);
		if(tipo.equals("DN118")) return getResources().getString(R.string.lb_c3_cap100_p118);
		if(tipo.equals("DN119")) return getResources().getString(R.string.lb_c3_cap100_p119);
		if(tipo.equals("DN120")) return getResources().getString(R.string.lb_c3_cap100_p120);
		return tipo;
	}
	
	private void refreshColorR001(){
		for(int x=0;x<mpReport01.size();x++){
			refreshColor(tcReport01,Util.getInt(mpReport01.get(x).get("RESFIN")),"=",0,x,3,R.color.orange);
			if(Util.getText(mpReport01.get(x).get("GPS")).equals("OMISION")){
				tcReport01.setCellColor(x, 2, R.color.amarillo, true);
			} else if(Util.getText(mpReport01.get(x).get("GPS")).equals("NO")){
				tcReport01.setCellColor(x, 2, R.color.orange);
			} else {
				tcReport01.resetRowColunmColor(x, 2);
			} 
		}
	}
	
	private void refreshColorR002(List<Map<String, Object>> lmap){
		if(lmap == null) return;
		for(int x=0;x<lmap.size();x++){
			refreshColor(tcReport02,Util.getInt(lmap.get(x).get("TOTAL")),"!=",0,x,2,R.color.super_light_green);
		}
	}
	
	private void refreshColor(TableComponent tc, int val, String op, int res, int row, int col, int color){
		boolean oper = (op.equals("!=")?val!=res:(op.equals("=")?val==res:(op.equals(">")?val>res:
			(op.equals("<")?val<res:false))));
		if(oper){
			tc.setCellColor(row, col, color, true);
		} else {
			tc.resetRowColunmColor(row, col);
		} 
	}
	
	public void cargarVertices() {				
		recargarLista();
	}
	
	@Override
	public boolean grabar() {
		return true;
	}

	@Override
	public void cargarDatos() {
		App.getInstance().setCap200(null);
		App.getInstance().setComisaria(null);
		App.getInstance().setMarco(null);;
		mpReport01 = getReportesService().getReport005();
		
		cargarVertices();
		inicio();
	}
	
	private void inicio(){
		
	}
	
	public ReportesService getReportesService() {
		if (reportService == null) {
			reportService = ReportesService.getInstance(getActivity());
		}
		return reportService;
	}

	@Override
	public void onCancel() {		
	}

	@Override
	public void onAccept() {
		
	}
}
