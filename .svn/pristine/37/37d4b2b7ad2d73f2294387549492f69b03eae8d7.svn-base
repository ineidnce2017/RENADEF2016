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
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

@SuppressLint("ResourceAsColor")
public class ReporteFragment_002 extends FragmentForm implements Respondible {

	private String TAG = ReporteFragment_002.this.getClass().getSimpleName();
	
	private TableComponent tcReport01, tcReport02, tcReport03, tcReport04;
	private LabelComponent lblTitulo;
	private ReportesService reportService;
	private static List<Map<String, Object>> mpReport01, mpReport02, mpReport03, mpReport04;

	public ReporteFragment_002 parent(MasterActivity parent) {
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
		LinearLayout q3 = createQuestionSection(0,Gravity.CENTER,LinearLayout.HORIZONTAL,
				tcReport03.getTableView(), new LabelComponent(getActivity()).size(altoComponente, 5), 
				tcReport04.getTableView());
		
		registerForContextMenu(tcReport01.getListView());
		registerForContextMenu(tcReport02.getListView());
		
		tcReport01.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@SuppressLint("ResourceAsColor")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				tcReport01.resetCellColor();
//				tcReport01.setCellColor(position, -1, R.color.amarillo, true);
				Map<String, Object> mp = (Map<String, Object>)parent.getItemAtPosition(position);	
//				Log.e("id", "id: "+mp.get("ID_N"));
				mpReport02 = getReportesService().getReport002((String)mp.get("ID_N"));
				tcReport02.setMaps(mpReport02, getArray(ReportesDAO.r002, "NRO_MREG"));
				refreshColorR002();
				clearCP(tcReport03, tcReport04);
			}
		});
		
		tcReport02.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@SuppressLint("ResourceAsColor")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Map<String, Object> mp = (Map<String, Object>)parent.getItemAtPosition(position);	
//				Log.e("id", "id: "+mp.get("ID_N"));
//				Log.e("NRO_MREG", "NRO_MREG: "+mp.get("NRO_MREG"));
				mpReport03 = getReportesService().getReport003((String)mp.get("ID_N"), (Integer)mp.get("NRO_MREG"));
				tcReport03.setMaps(mpReport03, getArray(ReportesDAO.r003, "ID_N"));
				
				mpReport04 = getReportesService().getReport004((String)mp.get("ID_N"), (Integer)mp.get("NRO_MREG"));
				tcReport04.setMaps(mpReport04, getArray(ReportesDAO.r004, "ID_N"));
			}
		});
		
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
		form.addView(q1);
		form.addView(q2);
		form.addView(q3);
		// /*Aca agregamos las preguntas a la pantalla*/
		return contenedor;
	}

	@Override
	protected void buildFields() {
		lblTitulo = new LabelComponent(getActivity(),
			App.ESTILO).text(R.string.r_tc_reptit).size(75, MATCH_PARENT).textSize(22).centrar();
		tcReport01 = new TableComponent(getActivity(), this,
				App.ESTILO).size(270, 600)
				.dataColumHeight(65).headerHeight(60).headerTextSize(15);
		tcReport01.addHeader(R.string.r_tc_id, 0.55f);
		tcReport01.addHeader(R.string.r_tc_totdel, 0.75f);
		tcReport01.addHeader(R.string.r_tc_p121, 0.75f);
		tcReport01.addHeader(R.string.r_tc_totfall, 0.75f);
		tcReport01.addHeader(R.string.r_tc_tot200, 0.7f);
//		tcReport01.setColorCondition("estado", Util.getHMObject("1", R.color.celesteclarito));
		tcReport01.setCellColor(-1, 0, true);
//		tcReport01.setCellColor(-1, 14, R.color.amarillo, true);
		
		tcReport02 = new TableComponent(getActivity(), this,
				App.ESTILO).size(420, 700)
				.dataColumHeight(65).headerHeight(60).headerTextSize(15);
		tcReport02.addHeader(R.string.r_tc_id, 0.5f);
		tcReport02.addHeader(R.string.r_tc_orden, 0.5f);
		tcReport02.addHeader(R.string.r_tc_p214, 0.5f);
		tcReport02.addHeader(R.string.r_tc_p215, 0.5f);
		tcReport02.addHeader(R.string.r_tc_tot300, 0.6f);
		tcReport02.addHeader(R.string.r_tc_tot400, 0.6f);
		tcReport02.setCellColor(-1, 0, true);
		tcReport02.setCellColor(-1, 1, true);
		
		tcReport03 = new TableComponent(getActivity(), this,
				App.ESTILO).size(400, 370)
				.dataColumHeight(65).headerHeight(60).headerTextSize(15);
//		tcReport03.addHeader(R.string.r_tc_id, 0.35f);
		tcReport03.addHeader(R.string.r_tc_orden, 0.3f);
		tcReport03.addHeader(R.string.r_tc_nombre_vf, 1.2f, TableComponent.ALIGN.LEFT);
		tcReport03.setCellColor(-1, 0, true);
		
		tcReport04 = new TableComponent(getActivity(), this,
				App.ESTILO).size(400, 370)
				.dataColumHeight(65).headerHeight(60).headerTextSize(15);
//		tcReport04.addHeader(R.string.r_tc_id, 0.35f);
		tcReport04.addHeader(R.string.r_tc_orden, 0.3f);
		tcReport04.addHeader(R.string.r_tc_nombre_pv, 1.2f, TableComponent.ALIGN.LEFT);
		tcReport04.setCellColor(-1, 0, true);
	}
	
	private void clearCP(TableComponent... tcs){
		for(TableComponent tc:tcs){
			tc.setMaps(null, "");
		}
	}
	
	public void recargarLista() {
		tcReport01.setMaps(mpReport01, ReportesDAO.r001);
		refreshColorR001();
	}
	
	private String[] getArray(String[] ar, String... strs){
		ArrayList<String> lst = new ArrayList<String>();
		for(String s:ar){
			lst.add(s);
		}
		for(String s:strs){
			if(lst.contains(s)) lst.remove(s);
		}
		return lst.toArray(new String[lst.size()]);
	}
	
	private void refreshColorR001(){
		for(int x=0;x<mpReport01.size();x++){
			if(Util.getInt(mpReport01.get(x).get("TOTAL_DELITOS")) + Util.getInt(mpReport01.get(x).get("DN121"))
					!= Util.getInt(mpReport01.get(x).get("TOT200"))){
				tcReport01.setCellColor(x, 1, R.color.light_celeste, true);
				tcReport01.setCellColor(x, 2, R.color.light_celeste, true);
				tcReport01.setCellColor(x, 4, R.color.light_celeste, true);
			} else {
				tcReport01.resetRowColunmColor(x, 1,3);
			} 
		}
	}
	
	private void refreshColorR002(){
		for(int x=0;x<mpReport02.size();x++){
			refreshColorR002("IH214", "TOT300", x, R.color.amarillo, 2, 4);
			refreshColorR002("IH215", "TOT400", x, R.color.orange, 3, 5);
//			if(Util.getInt(mpReport02.get(x).get("IH214")) != Util.getInt(mpReport02.get(x).get("TOT300"))){
//				tcReport02.setCellColor(x, 2, R.color.amarillo, true);
//				tcReport02.setCellColor(x, 4, R.color.amarillo, true);
//			} else {
//				tcReport02.resetRowColunmColor(x, 2,4);
//			}
//			
//			if(Util.getInt(mpReport02.get(x).get("IH215")) != Util.getInt(mpReport02.get(x).get("TOT400"))){
//					tcReport02.setCellColor(x, 3, R.color.orange, true);
//					tcReport02.setCellColor(x, 5, R.color.orange, true);
//			} else {
//				tcReport02.resetRowColunmColor(x, 3,5);
//			}
		}
	}
	
	private void refreshColorR002(String field1, String field2, int row, int color, int...indexs){
		if(Util.getInt(mpReport02.get(row).get(field1)) != Util.getInt(mpReport02.get(row).get(field2))){
			for(int i:indexs)
				tcReport02.setCellColor(row, i, color, true);
		} else {
			tcReport02.resetRowColunmColor(row, indexs);
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
		mpReport01 = getReportesService().getReport001();
		
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
