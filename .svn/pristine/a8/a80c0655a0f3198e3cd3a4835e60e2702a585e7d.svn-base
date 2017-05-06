package gob.inei.renadef2016.fragments;

import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.DialogComponent.TIPO_DIALOGO;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.TableComponent;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.activity.CuestionarioFragmentActivity;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.dialog.C3CAP200_Fragment_201_01;
import gob.inei.renadef2016.modelo.C100udt;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.modelo.Search;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.renadef2016.utilities.TaskExecute;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
public class C3CAP200_Fragment_201 extends FragmentForm implements Respondible {

	private TableComponent tcCap200;
	private LabelComponent lblTitulo;
	private ImageComponent imgC;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private static List<Cap200Delitos> lstData;
	public static Cap100Delitos cap200cap100;
	private static C3CAP200_Fragment_201 _this;
	private DialogComponent dc;

	public C3CAP200_Fragment_201() {
	}

	public C3CAP200_Fragment_201 parent(MasterActivity parent) {
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
		registerForContextMenu(tcCap200.getListView());
		initObjectsWithoutXML(this, rootView);
		enlazarCajas();
		listening();

		return rootView;
	}

	@Override
	protected void buildFields() {
		
		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_c3_cap200_title).textSize(21).centrar()
				.negrita();
		
		imgC = new ImageComponent(getActivity(), R.drawable.add, R.drawable.add);
		imgC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				if(Integer.valueOf(0).equals(cap200cap100.sum_fallecidos)){
//					ToastMessage.msgBox(getActivity(), "Total de Denuncias con Fallecidos es Cero.",
//							ToastMessage.MESSAGE_ERROR,
//							ToastMessage.DURATION_LONG);
//					return;
//				} else {
//					if(lstData.size()+1>cap200cap100.sum_fallecidos){
//						ToastMessage.msgBox(getActivity(), "N\u00famero de registros del CAP200 no "
//								+ "puede ser mayor al total de denuncias con fallecidos de CAP100.",
//								ToastMessage.MESSAGE_ERROR,
//								ToastMessage.DURATION_LONG);
//						return;
//					}
//				}
				Integer nro_mreg = 0;
				if(lstData.size()>0) nro_mreg = lstData.get(lstData.size()-1).nro_mreg;
				abrirDetalle(new Cap200Delitos(App.getInstance().getComisaria().id_n, nro_mreg+1, lstData.size()+1),
						lstData.size()+1);
			}
		});
		
		tcCap200 = new TableComponent(getActivity(), this,
				gob.inei.dnce.R.style.btnStyleHeaderGreen).size(497, 3900)
				.headerHeight(70).dataColumHeight(60).filter("getP202");
		tcCap200.setHeaderTextSize(14);
		tcCap200.addHeader(R.string.lb_c3_cap200_p200, 0.7f, TableComponent.ALIGN.CENTER);
		tcCap200.addHeader(R.string.lb_c3_cap200_p201_tr, 2f, TableComponent.ALIGN.LEFT);
		tcCap200.addHeader(R.string.lb_c3_cap200_p202_tr, 1.6f);
		tcCap200.addHeader(R.string.lb_c3_cap200_p203_tr, 1.5f);
		tcCap200.addHeader(R.string.lb_c3_cap200_p204_tr, 1.3f);
		tcCap200.addHeader(R.string.lb_c3_cap200_p205_tr, 1.6f);
		tcCap200.addHeader(R.string.lb_c3_cap200_p206_tr, 1.8f, TableComponent.ALIGN.LEFT);
		tcCap200.addHeader(R.string.lb_c3_cap200_p207_tr, 2f, TableComponent.ALIGN.LEFT);
		tcCap200.addHeader(R.string.lb_c3_cap200_p208_tr, 4f, TableComponent.ALIGN.LEFT);
		tcCap200.addHeader(R.string.lb_c3_cap200_p209_tr, 2.4f, TableComponent.ALIGN.LEFT);
		tcCap200.addHeader(R.string.lb_c3_cap200_p210_tr, 2.2f, TableComponent.ALIGN.LEFT);
		tcCap200.addHeader(R.string.lb_c3_cap200_p211_tr, 1.8f);
		tcCap200.addHeader(R.string.lb_c3_cap200_p212_tr, 1.1f);
		tcCap200.addHeader(R.string.lb_c3_cap200_p213_tr, 2f, TableComponent.ALIGN.LEFT);
		tcCap200.addHeader(R.string.lb_c3_cap200_p214_tr, 1.3f);
		tcCap200.addHeader(R.string.lb_c3_cap200_p215_tr, 1.3f);
		tcCap200.addHeader(R.string.lb_C_OBS, 5f, TableComponent.ALIGN.LEFT);
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
		Cap200Delitos cap200 = (Cap200Delitos) info.targetView.getTag();

		if (v.getId() == tcCap200.getListView().getId()){
	    	menu.setHeaderTitle("Opciones");
	    	menu.add(0, 1, 1, "Editar");
	    	menu.add(0, 2, 2, "Eliminar");
	    	menu.add(0, 3, 3, "Cap 300");
	    	menu.add(0, 4, 4, "Cap 400");
	    } 
		
		if(cap200.ih214==null){
			menu.findItem(3).setVisible(false);
		}
		
		if(cap200.ih215==null){
			menu.findItem(4).setVisible(false);
		} else {
			if(getMarcoService().getConteoDenunVict(cap200.id_n, cap200.nro_mreg, " AND IVH310 IS NOT NULL", "!=")){
				menu.findItem(4).setVisible(false);
			}
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if(!getUserVisibleHint()) return false;
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		Cap200Delitos cap200 = (Cap200Delitos) info.targetView.getTag();
		
		if( item.getItemId() == 1){
			abrirDetalle(cap200);
		}
		else if( item.getItemId() == 2){
			tryDeleteRow(cap200);
		}
		else if( item.getItemId() == 3){
//			Globales.getInstance().setVnro_mreg(cap200.nro_mreg);
			App.getInstance().setCap200(cap200);
			parent.nextFragment(CuestionarioFragmentActivity.CAP300_DEL);
		}
		else if( item.getItemId() == 4){
			App.getInstance().setCap200(cap200);
//			Globales.getInstance().setVnro_mreg(cap200.nro_mreg);
			parent.nextFragment(CuestionarioFragmentActivity.CAP400_DEL);
		}

		return true;
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q1 = createQuestionSection(imgC);
		LinearLayout q2 = createQuestionSection(tcCap200.getTableView());
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
	
	public void reloadData(Cap200Delitos detalle, int opcion){
		switch (opcion) {
		case 1:
			if(lstData.isEmpty() || lstData.get(lstData.size()-1).nro_mreg<detalle.nro_mreg){
				lstData.add(detalle);
				tcCap200.getData().add(detalle);
			} 
			break;
		case 2:
			lstData.remove(detalle);
			tcCap200.getData().remove(detalle);
			updateConteo();
			break;
		default:
			break;
		}
		tcCap200.reloadData();
//		updateCaratulaV3_2();
		updateCap100();
	}
	
	private void updateCaratulaV3_2() {
		INF_Caratula01 carat = App.getInstance().getComisaria();
		carat.v3_2 = lstData.size();
		try {
			getCaratulaService().saveOrUpdate(App.getInstance().getComisaria(), 
					App.getInstance().getComisaria().getSecCap(Util.getListList("V3_2")));
		} catch (SQLException e) {
		}
//		getCaratulaService().grabarCaratula(carat, 1, Utilidades.getListFields(new String[]{"V3_2"}));	
	}
	
	private void updateCap100() {
		int tot214 = 0, tot215 = 0, conte200 = 0;
		for(Cap200Delitos c:lstData) {
			tot214 = tot214 + (c.ih214==null?0:c.ih214); 
			tot215 = tot215 + (c.ih215==null?0:c.ih215);
			conte200++;
		}
		cap200cap100.sum_ih213 = tot214;
		cap200cap100.sum_ih214 = tot215;
		cap200cap100.conte_reg200 = conte200;
		try {
			getMarcoService().saveOrUpdate(cap200cap100, cap200cap100.getSecCap(
					Util.getListList("SUM_IH213", "SUM_IH214", "CONTE_REG200")));
		} catch (SQLException e) {
		}
	}

	private void updateConteo(){
		if(lstData.size()>0){
			for(int x=0;x<lstData.size();x++){
				lstData.get(x).orden_200 = x+1;
			}
		}
	}
	
	private void tryDeleteRow(Cap200Delitos cap200){
		dc = new DialogComponent(getActivity(), this,
				TIPO_DIALOGO.YES_NO, getResources()
						.getString(R.string.app_name),
				"Eliminar una denuncia; eliminar\u00e1 tambien sus registros del Cap. 300 y 400. "
				+ "Considere que este proceso es irreversible. Esta seguro que desea continuar?");
		dc.put("objeto", cap200);
		dc.showDialog();
	}
	
	private void abrirDetalle(Cap200Delitos cap200) {
		abrirDetalle(cap200, cap200.orden_200);
	}
	
	private void abrirDetalle(Cap200Delitos cap200, Integer nro) {
		FragmentManager fm = C3CAP200_Fragment_201.this.getFragmentManager();
		C3CAP200_Fragment_201_01 aperturaDialog = C3CAP200_Fragment_201_01
				.newInstance(C3CAP200_Fragment_201.this, cap200, nro, getMarcoService(), getCaratulaService());
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}

	@Override
	public boolean grabar() {
		return true;
	}

	private boolean validar() {			
		return true;
	}
	
	public static List<Cap200Delitos> getData(){
		return lstData;
	}

	@Override
	public void cargarDatos() {
//		Log.e("peruuuuu", "perrrrruuuuu");
		Cap100Delitos cap100 = new Cap100Delitos(App.getInstance().getComisaria().id_n);
		cap200cap100 = getMarcoService().getC100(App.getInstance().getComisaria(),
				cap100.getSecCap(Util.getListList("SUM_FALLECIDOS")));
		if(cap200cap100 == null){
			cap200cap100 = cap100;
		}
		
		Cap200Delitos cap200 = new Cap200Delitos();
		lstData = getMarcoService().getC200s(App.getInstance().getComisaria(), true, 
				cap200.getSecCap(getListFields(cap200), false));
		setValues();
//		Log.e("terminastessssss", "terminastessssss");
		tcCap200.setData(lstData, "ORDEN_200", "IH201_NRO_DOC", "getP202", "getP203", "getP204", "getP205", "getP206", "getP207", "getP208Cod",
				"getP209Cod", "getP210Cod", "getP211Cod", "IH212", "IH213", "IH214", "IH215", "OBS_03_200");
		tcCap200.setBorder("borde");
		inicio();
	}
	
	private void setValues() {
		if(lstData!=null && lstData.size() > 0){
			for(Cap200Delitos detalle : lstData){
				if(detalle.ih208!=null && Util.getInt(detalle.ih208) <= 35){
					detalle.ih208_cod = detalle.ih208;
					detalle.ih208_cod_o = detalle.ih208_o;
					detalle.p208nom = getModalidad(detalle.ih208).nombre.trim();
				}
				int p209 = Util.getInt(detalle.ih209, -1);
				if(p209!=-1 && detalle.ih209_cod == null){
					if(p209 <= 8 || (p209 >= 11 && p209 <= 12)) detalle.ih209_cod = p209;
					if(p209 >= 9 && p209 <= 10) detalle.ih209_cod = 9;
					if(p209 == 13) detalle.ih209_cod = 20;
					if(p209 == 14) {
						detalle.ih209_cod = 21;
						detalle.ih209_cod_o = detalle.ih209_o;
					}
				}
				int p210 = Util.getInt(detalle.ih210, -1);
				if(p210!=-1 && detalle.ih210_cod == null){
					if(p210 <= 25) detalle.ih210_cod = p210;
					if(p210 == 26) detalle.ih210_cod = 33;
					if(p210 == 27) {
						detalle.ih210_cod = 34;
						detalle.ih210_cod_o = detalle.ih210_o;
					}
				}
				if(Util.getInt(detalle.ih211_21)!=0 && Util.getInt(detalle.ih211_25)==0)  {
					detalle.ih211_25 = detalle.ih211_21;
					detalle.ih211_21 = null;
				}
				if(Util.getInt(detalle.ih211_22)!=0 && Util.getInt(detalle.ih211_26)==0) {
					detalle.ih211_26 = detalle.ih211_22;
					detalle.ih211_26_o = detalle.ih211_22_o;
					detalle.ih211_22 = null;
				}
			}
		}
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

	private void inicio() {
		App.getInstance().setCap200(null);
	}

	@Override
	public void onAccept() {
		Cap200Delitos cap200 = (Cap200Delitos)dc.get("objeto");
		if(cap200 != null){
			getMarcoService().deleteCap200Del(cap200.id_n, cap200.nro_mreg);
			App.getInstance().getComisaria().v3_3 = getMarcoService().getConteoVictimas(App.getInstance().getComisaria().id_n);
			App.getInstance().getComisaria().v3_4 = getMarcoService().getConteoVictimasWhere(App.getInstance().getComisaria().id_n, null);
			reloadData(cap200, 2);
			new TaskExecute<String,String,Boolean>(this).addCallback("updateCap1002plano", cap200.id_n).execute();
//			updateCap1002plano(cap200.id_n);
		}
	}

	public void updateCap1002plano(String id_n) {
		try {
			Cap100Delitos cap100 = getFieldName(getMarcoService().getCountC200xP208(id_n));
			if(getMarcoService().saveOrUpdate(cap100, cap100.getSecCap(getListFields(cap100, "[101-120]", "dn",
					new String[]{"TOTAL_DELITOS","SUM_HDOLOSOS","TOTAL_DENUNCIAS","SUM_FALLECIDOS","DNTOTREST"}), false))){
				App.getInstance().getComisaria().v3_1 = getCountDistinct18to23(); //Util.getInt(cap100.total_delitos);
				getCaratulaService().saveOrUpdate(App.getInstance().getComisaria(), 
						App.getInstance().getComisaria().getSecCap(Util.getListList("V3_1")));
			}
		} catch (SQLException e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
		}
	}

	@Override
	public void onCancel() {
	}
	
	@Override
	public boolean getSaltoNavegation() {
		return validar();
	}
	
	private int getCountDistinct18to23(){
		int cont = 0;
		if(lstData!=null && !lstData.isEmpty()){
			for(Cap200Delitos c : lstData){
				if((c.ih208!=null && Util.esDiferente(c.ih208, 18,19,20,21,22,23)) || 
						(c.ih208_cod!=null && Util.esDiferente(c.ih208_cod, 18,19,20,21,22,23))){
					cont++;
				}
			}
		}
		return cont;
	}
	
	public Cap100Delitos getFieldName(List<C100udt> c200) {
		Cap100Delitos cap100 =  new Cap100Delitos(App.getInstance().getComisaria().id_n);
		for (C100udt c : c200) {
			switch (c.cod) {
				case 1: cap100.dn101_1_a = c.value; break;
				case 2: cap100.dn101_1_b = c.value; break;
				case 3: cap100.dn101_1_c = c.value; break;
				case 4: cap100.dn101_1_d = c.value; break;
				case 5: cap100.dn101_1_e = c.value; break;
				case 6: cap100.dn101_1_f = c.value; break;
				case 7: cap100.dn101_1_g = c.value; break;
				case 8: cap100.dn101_1_h = c.value; break;
				case 9: cap100.dn101_2_a = c.value; break;
				case 10: cap100.dn101_2_b = c.value; break;
				case 11: cap100.dn101_2_c = c.value; break;
				case 12: cap100.dn101_3 = c.value; break;
				case 13: cap100.dn101_4_a = c.value; break;
				case 14: cap100.dn101_5_a = c.value; break;
				case 15: cap100.dn101_5_b = c.value; break;
				case 16: cap100.dn101_5_c = c.value; break;
				case 17: cap100.dn101_5_d = c.value; break;
				case 18: cap100.dn101_6_a = c.value; break;
				case 19: cap100.dn101_6_b = c.value; break;
				case 20: cap100.dn101_6_c = c.value; break;
				case 21: cap100.dn101_6_d = c.value; break;
				case 22: cap100.dn101_6_e = c.value; break;
				case 23: cap100.dn101_6_f = c.value; break;
				case 24: cap100.dn101_7 = c.value; break;
				case 25: cap100.dn101_8 = c.value; break;
				case 26: cap100.dn101_9_a = c.value; break;
				case 27: cap100.dn101_9_b = c.value; break;
				case 28: cap100.dn101_9_c = c.value; break;
				case 29: cap100.dn101_9_d = c.value; break;
				case 30: cap100.dn101_9_e = c.value; break;
				case 31: cap100.dn101_10_a = c.value; break;
				case 32: cap100.dn101_10_b = c.value; break;
				case 33: cap100.dn101_10_c = c.value; break;
				case 34: cap100.dn101_10_d = c.value; break;
				case 35: cap100.dn101_10_e = c.value; break;
				case 36: cap100.dn102_a = c.value; break;
				case 37: cap100.dn103_a = c.value; break;
				case 38: cap100.dn103_b = c.value; break;
				case 39: cap100.dn103_c = c.value; break;
				case 40: cap100.dn103_d = c.value; break;
				case 41: cap100.dn103_e = c.value; break;
				case 42: cap100.dn104_a = c.value; break;
				case 43: cap100.dn104_b = c.value; break;
				case 44: cap100.dn104_c = c.value; break;
				case 45: cap100.dn104_d = c.value; break;
				case 46: cap100.dn104_e = c.value; break;
				case 47: cap100.dn104_f = c.value; break;
				case 48: cap100.dn104_g = c.value; break;
				case 49: cap100.dn104_h = c.value; break;
				case 50: cap100.dn104_i = c.value; break;
				case 51: cap100.dn104_j = c.value; break;
				case 52: cap100.dn104_k = c.value; break;
				case 53: cap100.dn104_l = c.value; break;
				case 54: cap100.dn105_a = c.value; break;
				case 55: cap100.dn105_b = c.value; break;
				case 56: cap100.dn105_c = c.value; break;
				case 57: cap100.dn105_d = c.value; break;
				case 58: cap100.dn105_e = c.value; break;
				case 59: cap100.dn105_f = c.value; break;
				case 60: cap100.dn105_g = c.value; break;
				case 61: cap100.dn105_h = c.value; break;
				case 62: cap100.dn105_i = c.value; break;
				case 63: cap100.dn105_j = c.value; break;
				case 64: cap100.dn105_k = c.value; break;
				case 65: cap100.dn105_l = c.value; break;
				case 66: cap100.dn106_a = c.value; break;
				case 67: cap100.dn106_b = c.value; break;
				case 68: cap100.dn106_c = c.value; break;
				case 69: cap100.dn106_d = c.value; break;
				case 70: cap100.dn107_a = c.value; break;
				case 71: cap100.dn107_b = c.value; break;
				case 72: cap100.dn107_c = c.value; break;
				case 73: cap100.dn108_a = c.value; break;
				case 74: cap100.dn109_a = c.value; break;
				case 75: cap100.dn109_b = c.value; break;
				case 76: cap100.dn109_c = c.value; break;
				case 77: cap100.dn109_d = c.value; break;
				case 78: cap100.dn109_e = c.value; break;
				case 79: cap100.dn110_a = c.value; break;
				case 80: cap100.dn110_b = c.value; break;
				case 81: cap100.dn110_c = c.value; break;
				case 82: cap100.dn111_a = c.value; break;
				case 83: cap100.dn111_b = c.value; break;
				case 84: cap100.dn111_c = c.value; break;
				case 85: cap100.dn111_d = c.value; break;
				case 86: cap100.dn112_a = c.value; break;
				case 87: cap100.dn112_b = c.value; break;
				case 88: cap100.dn112_c = c.value; break;
				case 89: cap100.dn112_d = c.value; break;
				case 90: cap100.dn112_e = c.value; break;
				case 91: cap100.dn113_a = c.value; break;
				case 92: cap100.dn113_b = c.value; break;
				case 93: cap100.dn113_c = c.value; break;
				case 94: cap100.dn113_d = c.value; break;
				case 95: cap100.dn113_e = c.value; break;
				case 96: cap100.dn114_a = c.value; break;
				case 97: cap100.dn114_b = c.value; break;
				case 98: cap100.dn114_c = c.value; break;
				case 99: cap100.dn115_a = c.value; break;
				case 100: cap100.dn115_b = c.value; break;
				case 101: cap100.dn115_c = c.value; break;
				case 102: cap100.dn115_d = c.value; break;
				case 103: cap100.dn115_e = c.value; break;
				case 104: cap100.dn115_f = c.value; break;
				case 105: cap100.dn116_a = c.value; break;
				case 106: cap100.dn116_b = c.value; break;
				case 107: cap100.dn116_c = c.value; break;
				case 108: cap100.dn116_d = c.value; break;
				case 109: cap100.dn117_a = c.value; break;
				case 110: cap100.dn117_b = c.value; break;
				case 111: cap100.dn118_a = c.value; break;
				case 112: cap100.dn118_b = c.value; break;
				case 113: cap100.dn119_a = c.value; break;
				case 114: cap100.dn119_b = c.value; break;
				case 115: cap100.dn119_c = c.value; break;
				case 116: cap100.dn120_a = c.value; break;
				case 117: cap100.dn120_b = c.value; break;
				case 118: cap100.dn120_c = c.value; break;
				
				default: cap100.dntotrest = c.value; break;
			}
		}
		return sumasTotales(cap100);
	}
	
	public Cap100Delitos sumasTotales(Cap100Delitos cap100){
		cap100.dn101_1 = Util.getInt(cap100.dn101_1_a)+Util.getInt(cap100.dn101_1_b)+Util.getInt(cap100.dn101_1_c)+
				Util.getInt(cap100.dn101_1_d)+Util.getInt(cap100.dn101_1_e)+Util.getInt(cap100.dn101_1_f)+Util.getInt(cap100.dn101_1_g)+
				Util.getInt(cap100.dn101_1_h)/*+Util.getInt(cap100.dn101_3)+Util.getInt(cap100.dn101_4_a)+Util.getInt(cap100.dn101_5_a)+
				Util.getInt(cap100.dn101_5_b)+Util.getInt(cap100.dn101_5_c)+Util.getInt(cap100.dn101_5_d)*/;
		cap100.sum_hdolosos = Util.getInt(cap100.dn101_1_a)+Util.getInt(cap100.dn101_1_b)+Util.getInt(cap100.dn101_1_c)+
				Util.getInt(cap100.dn101_1_d)+Util.getInt(cap100.dn101_1_e)+Util.getInt(cap100.dn101_1_f)+Util.getInt(cap100.dn101_1_g)+
				Util.getInt(cap100.dn101_1_h)+Util.getInt(cap100.dn101_3)+Util.getInt(cap100.dn101_4_a)+Util.getInt(cap100.dn101_5_a)+
				Util.getInt(cap100.dn101_5_b)+Util.getInt(cap100.dn101_5_c)+Util.getInt(cap100.dn101_5_d);
		cap100.dn101_2 = Util.getInt(cap100.dn101_2_a)+Util.getInt(cap100.dn101_2_b)+Util.getInt(cap100.dn101_2_c);
		cap100.dn101_4 = Util.getInt(cap100.dn101_4_a);
		cap100.dn101_5 = Util.getInt(cap100.dn101_5_a)+Util.getInt(cap100.dn101_5_b)+Util.getInt(cap100.dn101_5_c)+
				Util.getInt(cap100.dn101_5_d);
		cap100.dn101_6 = Util.getInt(cap100.dn101_6_a)+Util.getInt(cap100.dn101_6_b)+Util.getInt(cap100.dn101_6_c)+
				Util.getInt(cap100.dn101_6_d)+Util.getInt(cap100.dn101_6_e)+Util.getInt(cap100.dn101_6_f);
		cap100.dn101_9 = Util.getInt(cap100.dn101_9_a)+Util.getInt(cap100.dn101_9_b)+Util.getInt(cap100.dn101_9_c)+
				Util.getInt(cap100.dn101_9_d)+Util.getInt(cap100.dn101_9_e);
		cap100.dn101_10 = Util.getInt(cap100.dn101_10_a)+Util.getInt(cap100.dn101_10_b)+Util.getInt(cap100.dn101_10_c)+
				Util.getInt(cap100.dn101_10_d)+Util.getInt(cap100.dn101_10_e);
		cap100.dn101 = Util.getInt(cap100.dn101_1)+Util.getInt(cap100.dn101_2)+Util.getInt(cap100.dn101_3)+
				Util.getInt(cap100.dn101_4_a)+Util.getInt(cap100.dn101_5_a)+Util.getInt(cap100.dn101_5_b)+
				Util.getInt(cap100.dn101_5_c)+Util.getInt(cap100.dn101_5_d)+Util.getInt(cap100.dn101_7)+
				Util.getInt(cap100.dn101_8)+Util.getInt(cap100.dn101_9)+Util.getInt(cap100.dn101_10);
		cap100.dn102 = Util.getInt(cap100.dn102_a);
		cap100.dn103 = Util.getInt(cap100.dn103_a)+Util.getInt(cap100.dn103_b)+Util.getInt(cap100.dn103_c)+
				Util.getInt(cap100.dn103_d)+Util.getInt(cap100.dn103_e);
		cap100.dn104 = Util.getInt(cap100.dn104_a)+Util.getInt(cap100.dn104_b)+Util.getInt(cap100.dn104_c)+Util.getInt(cap100.dn104_d)+
				Util.getInt(cap100.dn104_e)+Util.getInt(cap100.dn104_f)+Util.getInt(cap100.dn104_g)+Util.getInt(cap100.dn104_h)+
				Util.getInt(cap100.dn104_i)+Util.getInt(cap100.dn104_j)+Util.getInt(cap100.dn104_k)+Util.getInt(cap100.dn104_l);
		cap100.dn105 = Util.getInt(cap100.dn105_a)+Util.getInt(cap100.dn105_b)+Util.getInt(cap100.dn105_c)+Util.getInt(cap100.dn105_d)+
				Util.getInt(cap100.dn105_e)+Util.getInt(cap100.dn105_f)+Util.getInt(cap100.dn105_g)+Util.getInt(cap100.dn105_h)+
				Util.getInt(cap100.dn105_i)+Util.getInt(cap100.dn105_j)+Util.getInt(cap100.dn105_k)+Util.getInt(cap100.dn105_l);
		cap100.dn106 = Util.getInt(cap100.dn106_a)+Util.getInt(cap100.dn106_b)+Util.getInt(cap100.dn106_c)+Util.getInt(cap100.dn106_d);
		cap100.dn107 = Util.getInt(cap100.dn107_a)+Util.getInt(cap100.dn107_b)+Util.getInt(cap100.dn107_c);
		cap100.dn108 = Util.getInt(cap100.dn108_a);
		cap100.dn109 = Util.getInt(cap100.dn109_a)+Util.getInt(cap100.dn109_b)+Util.getInt(cap100.dn109_c)+Util.getInt(cap100.dn109_d)+
				Util.getInt(cap100.dn109_e);
		cap100.dn110 = Util.getInt(cap100.dn110_a)+Util.getInt(cap100.dn110_b)+Util.getInt(cap100.dn110_c);
		cap100.dn111 = Util.getInt(cap100.dn111_a)+Util.getInt(cap100.dn111_b)+Util.getInt(cap100.dn111_c)+Util.getInt(cap100.dn111_d);
		cap100.dn112 = Util.getInt(cap100.dn112_a)+Util.getInt(cap100.dn112_b)+Util.getInt(cap100.dn112_c)+Util.getInt(cap100.dn112_d)+
				Util.getInt(cap100.dn112_e);
		cap100.dn113 = Util.getInt(cap100.dn113_a)+Util.getInt(cap100.dn113_b)+Util.getInt(cap100.dn113_c)+Util.getInt(cap100.dn113_d)+
				Util.getInt(cap100.dn113_e);
		cap100.dn114 = Util.getInt(cap100.dn114_a)+Util.getInt(cap100.dn114_b)+Util.getInt(cap100.dn114_c);
		cap100.dn115 = Util.getInt(cap100.dn115_a)+Util.getInt(cap100.dn115_b)+Util.getInt(cap100.dn115_c)+Util.getInt(cap100.dn115_d)+
				Util.getInt(cap100.dn115_e)+Util.getInt(cap100.dn115_f);
		cap100.dn116 = Util.getInt(cap100.dn116_a)+Util.getInt(cap100.dn116_b)+Util.getInt(cap100.dn116_c)+Util.getInt(cap100.dn116_d);
		cap100.dn117 = Util.getInt(cap100.dn117_a)+Util.getInt(cap100.dn117_b);
		cap100.dn118 = Util.getInt(cap100.dn118_a)+Util.getInt(cap100.dn118_b);
		cap100.dn119 = Util.getInt(cap100.dn119_a)+Util.getInt(cap100.dn119_b)+Util.getInt(cap100.dn119_c);
		cap100.dn120 = Util.getInt(cap100.dn120_a)+Util.getInt(cap100.dn120_b)+Util.getInt(cap100.dn120_c);
		cap100.dn121 = Util.getInt(cap100.dn101_6);
		cap100.sum_fallecidos = Util.getInt(cap100.dn101_1)+Util.getInt(cap100.dn101_2)+Util.getInt(cap100.dn101_3)+
				Util.getInt(cap100.dn101_4)+Util.getInt(cap100.dn101_5)+Util.getInt(cap100.dn101_6);
		cap100.total_delitos = Util.getInt(cap100.dn101)+Util.getInt(cap100.dn102)+Util.getInt(cap100.dn103)+Util.getInt(cap100.dn104)+
				Util.getInt(cap100.dn105)+Util.getInt(cap100.dn106)+Util.getInt(cap100.dn107)+Util.getInt(cap100.dn108)+
				Util.getInt(cap100.dn109)+Util.getInt(cap100.dn110)+Util.getInt(cap100.dn111)+Util.getInt(cap100.dn112)+
				Util.getInt(cap100.dn113)+Util.getInt(cap100.dn114)+Util.getInt(cap100.dn115)+Util.getInt(cap100.dn116)+
				Util.getInt(cap100.dn117)+Util.getInt(cap100.dn118)+Util.getInt(cap100.dn119)+Util.getInt(cap100.dn120)+
				+Util.getInt(cap100.dntotrest);
		cap100.total_denuncias = Util.getInt(cap100.total_delitos)+Util.getInt(cap100.dn121)+Util.getInt(cap100.total_faltas);
		
		return cap100;
	}
	
	public Search getModalidad(Integer codigo){
		List<Search> modalidades = App.MODALIDADES != null ? App.MODALIDADES : getMarcoService().getModalidades();
		if(modalidades != null){
			for(Search s : modalidades){
				if(s.codigo.equals(codigo))
					return s;
			}
		}
		return new Search(-1,"No Identifacado", -1);
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
