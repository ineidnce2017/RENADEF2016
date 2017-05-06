package gob.inei.renadef2016.fragments;

import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.DialogComponent.TIPO_DIALOGO;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.TableComponent;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.components.ui.SearchDialog;
import gob.inei.dnce.interfaces.IDetailEntityComponent;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.interfaces.Searchable1;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.fragments.dialog.C3CAP300_Fragment_301_01;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.modelo.ListaVictimas;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap300Delitos;
import gob.inei.renadef2016.service.INF_Caratula01Service;
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
public class C3CAP300_Fragment_301 extends FragmentForm implements Respondible,
		Searchable1 {

	private TableComponent tcCap300;
	private LabelComponent lblTitulo, lblT, lblVictimas;
	private ImageComponent imgC, imgR;
	public ButtonComponent imgVictimas;
	private GridComponent2 gridTot;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private static List<Cap300Delitos> lstData;
	private static C3CAP300_Fragment_301 _this;
	public static Cap200Delitos cap300cap200;
	private Integer conteoVictimas, conteoVictimasWhere;
	private DialogComponent dc;
	private int buscado = 0;
	public C3CAP300_Fragment_301() {
	}

	public C3CAP300_Fragment_301 parent(MasterActivity parent) {
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
		registerForContextMenu(tcCap300.getListView());
		initObjectsWithoutXML(this, rootView);
		enlazarCajas();
		listening();

		return rootView;
	}

	@Override
	protected void buildFields() {

		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_c3_cap300_title).textSize(21).centrar()
				.negrita();
		lblVictimas = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text("Lista Victimas").textSize(21).centrar().negrita();

		imgR = new ImageComponent(getActivity(), R.drawable.add, R.drawable.add);
		dimenView(imgR, 0.1f, Gravity.RIGHT | Gravity.CENTER_VERTICAL);
		imgR.setEnabled(false);
		imgC = new ImageComponent(getActivity(), R.drawable.add, R.drawable.add);
		dimenView(imgC, 1.0f, Gravity.CENTER);
		imgC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Integer.valueOf(0).equals(cap300cap200.ih214)) {
					ToastMessage.msgBox(getActivity(),
							"Total de Denuncias con Fallecidos es Cero.",
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
					return;
				} else if (lstData.size() + 1 > cap300cap200.ih214) {
					ToastMessage
							.msgBox(getActivity(),
									"N\u00famero de registros del CAP300 no "
											+ "puede ser mayor al N\u00famero de V\u00edctimas del CAP200 (P214).",
									ToastMessage.MESSAGE_ERROR,
									ToastMessage.DURATION_LONG);
					return;
				} else {
					if (getMarcoService().getConteoDenunVict(
							App.getInstance().getComisaria().id_n)) {
						ToastMessage
								.msgBox(getActivity(),
										"Conteo de registros totales del Cap300. no concuerda"
												+ " con la suma del n\u00famero de v\u00edctimas fallecidos en Cap200.",
										ToastMessage.MESSAGE_ERROR,
										ToastMessage.DURATION_LONG);
						return;
					}
				}
				Integer nro_vfreg = 0;
				if (lstData.size() > 0)
					nro_vfreg = lstData.get(lstData.size() - 1).nro_vfreg;
				abrirDetalle(new Cap300Delitos(
						App.getInstance().getComisaria().id_n,
						cap300cap200.nro_mreg, nro_vfreg + 1,
						lstData.size() + 1));
			}
		});

		imgVictimas = new ButtonComponent(this.getActivity())
				.size(70, altoComponente + 10).drawableLeft(App.SEARCH)
				.padding(15, 0, 0, 0);
		imgVictimas.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				search(1);
			}
		});

		LabelComponent lbl = new LabelComponent(this.getActivity(), App.ESTILO)
				.size(altoComponente, 150).text(R.string.lb_c3_cap300_p1)
				.textSize(16).centrar();
		lblT = new LabelComponent(this.getActivity()).size(altoComponente, 90)
				.textSize(20).negrita().centrar();

		gridTot = new GridComponent2(this.getActivity(), 1, false);
		gridTot.addComponent(lbl);
		gridTot.addComponent(lblT);

		tcCap300 = new TableComponent(getActivity(), this,
				gob.inei.dnce.R.style.btnStyleHeaderGreen).size(497, 3000)
				.headerHeight(70).dataColumHeight(60).filter("getP302");
		tcCap300.setHeaderTextSize(14);
		tcCap300.addHeader(R.string.lb_c3_cap300_p300, 0.7f,
				TableComponent.ALIGN.CENTER);
		tcCap300.addHeader(R.string.lb_c3_cap300_p301_tr, 4f,
				TableComponent.ALIGN.LEFT);
		tcCap300.addHeader(R.string.lb_c3_cap300_p302_tr, 2f);
		tcCap300.addHeader(R.string.lb_c3_cap300_p303_tr, 1.2f);
		tcCap300.addHeader(R.string.lb_c3_cap300_p304_tr, 2.6f,
				TableComponent.ALIGN.LEFT);
		tcCap300.addHeader(R.string.lb_c3_cap300_p305_tr, 1.2f);
		tcCap300.addHeader(R.string.lb_c3_cap300_p306_tr, 1.6f);
		tcCap300.addHeader(R.string.lb_c3_cap300_p307_tr, 1.8f);
		tcCap300.addHeader(R.string.lb_c3_cap300_p308_tr, 2.4f);
		tcCap300.addHeader(R.string.lb_c3_cap300_p309_tr, 1.8f);
		tcCap300.addHeader(R.string.lb_c3_cap300_p310_tr, 2.4f,
				TableComponent.ALIGN.LEFT);
		tcCap300.addHeader(R.string.lb_C_OBS, 6f, TableComponent.ALIGN.LEFT);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (v.getId() == tcCap300.getListView().getId()) {
			menu.setHeaderTitle("Opciones");
			menu.add(1, 1, 1, "Editar");
			menu.add(1, 2, 2, "Eliminar");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (!getUserVisibleHint())
			return false;
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		Cap300Delitos cap300 = (Cap300Delitos) info.targetView.getTag();

		if (item.getItemId() == 1) {
			abrirDetalle(cap300);
		} else if (item.getItemId() == 2) {
			tryDeleteRow(cap300);
		}

		return true;
	}

	@Override
	protected View createUI() {
		buildFields();
		LinearLayout q0 = createQuestionSection(lblTitulo);

		LinearLayout lyy = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER,
				gridTot.component());
		dimenView(lyy, 0.1f, Gravity.LEFT);
		LinearLayout q1 = createQuestionSection(0, Gravity.CENTER,
				LinearLayout.HORIZONTAL, lyy, imgC, imgR);
		LinearLayout q2 = createQuestionSection(tcCap300.getTableView());
//		LinearLayout q3 = createQuestionSection(lblVictimas, imgVictimas);
		
		
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
		form.addView(q0);
		form.addView(q1);
		form.addView(q2);
//		form.addView(q3);
		return contenedor;
	}

	private void dimenView(View view, float weight, int gravity) {
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view
				.getLayoutParams();
		lp.height = WRAP_CONTENT;
		lp.width = WRAP_CONTENT;
		lp.gravity = gravity;
		lp.weight = weight;
		view.setLayoutParams(lp);
	}

	public void reloadData(Cap300Delitos detalle, int opcion) {
		switch (opcion) {
		case 1:
			if (lstData.isEmpty()
					|| lstData.get(lstData.size() - 1).nro_vfreg < detalle.nro_vfreg) {
				lstData.add(detalle);
				tcCap300.getData().add(detalle);
				conteoVictimas++;
				if (!Util.esDiferente(cap300cap200.getP208(), 1, 2, 3, 4, 5, 6,
						7, 8, 12, 13, 14, 15, 16, 17))
					conteoVictimasWhere++;
			}
			break;
		case 2:
			lstData.remove(detalle);
			tcCap300.getData().remove(detalle);
			updateConteo();
			conteoVictimas--;
			if (!Util.esDiferente(cap300cap200.getP208(), 1, 2, 3, 4, 5, 6, 7,
					8, 12, 13, 14, 15, 16, 17))
				conteoVictimasWhere--;
			break;
		default:
			break;
		}
		tcCap300.reloadData();
		updateCaratulaV3_3y4();
		updateCap200();
		refreshBorder();
	}

	private void updateCaratulaV3_3y4() {
		INF_Caratula01 carat = App.getInstance().getComisaria();
		carat.v3_3 = conteoVictimas;
		carat.v3_4 = conteoVictimasWhere;
		try {
			getCaratulaService().saveOrUpdate(
					App.getInstance().getComisaria(),
					App.getInstance().getComisaria()
							.getSecCap(Util.getListList("V3_3", "V3_4")));
		} catch (SQLException e) {
		}
		// getCaratulaService().grabarCaratula(carat, 1,
		// Utilidades.getListFields(new String[]{"V3_3", "V3_4"}));
	}

	private void updateCap200() {
		cap300cap200.conte_reg300 = lstData.size();
		try {
			mimarcoService.saveOrUpdate(cap300cap200,
					cap300cap200.getSecCap(Util.getListList("CONTE_REG300")));
		} catch (SQLException e) {
		}
		// getMarcoService().saveCap200Delitos(C3CAP300_Fragment_301.cap300cap200,
		// Utilidades.getListFields(new String[]{"CONTE_REG300"}));
	}

	private void updateConteo() {
		if (lstData.size() > 0) {
			for (int x = 0; x < lstData.size(); x++) {
				lstData.get(x).orden_300 = x + 1;
			}
		}
	}

	private void tryDeleteRow(Cap300Delitos cap300) {
		dc = new DialogComponent(
				getActivity(),
				this,
				TIPO_DIALOGO.YES_NO,
				getResources().getString(R.string.app_name),
				"Esta intentando eliminar el registro de la V\u00edctima N\u00B0: "
						+ cap300.orden_300
						+ ". Este proceso es irreversible. Esta seguro que desea continuar?");
		dc.put("objeto", cap300);
		dc.showDialog();
	}

	private void abrirDetalle(Cap300Delitos cap300) {
		abrirDetalle(cap300, cap300.orden_300);
	}

	private void abrirDetalle(Cap300Delitos cap300, Integer nro) {
		FragmentManager fm = C3CAP300_Fragment_301.this.getFragmentManager();
		C3CAP300_Fragment_301_01 aperturaDialog = C3CAP300_Fragment_301_01
				.newInstance(C3CAP300_Fragment_301.this, cap300, nro,
						getMarcoService());
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
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
				if (cap300cap200.ih215 != null && Util.esVacio(lstData.get(i).ivh310)) {
					ToastMessage.msgBox(
							this.getActivity(),
							pregunta_completa.replace("$", "Victima N°: "
									+ lstData.get(i).orden_300 + ". "
									+ "“"
									+ lstData.get(i).ivh301a +" "+lstData.get(i).ivh301b +" "+ lstData.get(i).ivh301c + ".”" ),
							ToastMessage.MESSAGE_INFO,
							ToastMessage.DURATION_LONG);
					view = tcCap300;
					error = true;
					return false;
				}
			}
		}
		
		return true;
	}

	public static LinearLayout createTitle(Context context, String title,
			View view, View view1, View view2) {
		return createTitle(context, title, null, view, view1, view2);
	}

	public static LinearLayout createTitle(Context context, String title,
			String stitle, View view, View view1, View view2) {
		LinearLayout ly = createLY(context, LinearLayout.VERTICAL);
		LabelComponent lb = new LabelComponent(context).text(title)
				.textSize(23).colorTexto(R.color.azulaceroclaro).negrita();
		LinearLayout sly = createLY(context, LinearLayout.VERTICAL);
		sly.addView(lb, new LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		if (stitle != null) {
			LabelComponent lbs = new LabelComponent(context).text(stitle)
					.textSize(15).colorTexto(R.color.orange).negrita();
			sly.addView(lbs, new LayoutParams(/*
											 * LinearLayout.LayoutParams.
											 * MATCH_PARENT
											 */570,
					LinearLayout.LayoutParams.WRAP_CONTENT));
		}
		LinearLayout btnMant = _this.createQuestionSection(0, Gravity.CENTER,
				LinearLayout.HORIZONTAL, view1, sly, view2);
		ly.addView(btnMant);
		ly.addView(view);
		return ly;
	}

	public static LinearLayout createLY(Context context, int orientacion) {
		LinearLayout ly = new LinearLayout(context);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		ly.setLayoutParams(llp);
		ly.setOrientation(orientacion);
		return ly;
	}

	@Override
	public void cargarDatos() {
		cap300cap200 = App.getInstance().getCap200();
		lstData = getMarcoService().getC300s(
				App.getInstance().getComisaria().id_n,
				cap300cap200.nro_mreg,
				new Cap300Delitos().getSecCap(
						getListFields(new Cap300Delitos()), false));
		tcCap300.setData(lstData, "ORDEN_300", "getP301", "getP302", "getP303",
				"getP304", "IVH305", "getP306", "getP307", "getP308",
				"getP309", "getP310", "OBS_03_300");

		// cap300cap200 = null;
		// List<Cap200Delitos> lstc200Del =
		// getMarcoService().fill_Cap200_Del(Globales.getInstance().getVid_np(),
		// Globales.getInstance().getVnro_mreg(), Utilidades.getListFields(new
		// String[]{"IH204", "IH213", "NRO_MREG",
		// "IH214","IH205_DIA", "IH205_MES", "IH205_ANIO"}));
		// if(lstc200Del.size()>0) cap300cap200 = lstc200Del.get(0);

		// lstData =
		// getMarcoService().fill_Cap300_Del(Globales.getInstance().getVid_np(),
		// Globales.getInstance().getVnro_mreg());
		// tcCap300.setData(lstData, "ORDEN_300", "getP301", "getP302",
		// "IVH303", "getP304a", "IVH304",
		// "getP305", "IVH306", "IVH307", "IVH308", "getP309", "OBS_03_300");

		refreshBorder();
		inicio();
	}

	private void refreshBorder() {
		for (int x = 0; x < lstData.size(); x++) {
			if (cap300cap200.ih215 != null
					&& lstData.get(x).ivh304 != null
					&& ((lstData.get(x).ivh304.compareTo(1) == 0
							&& lstData.get(x).ivh306_anio != null && lstData
							.get(x).ivh310 != null)
							|| (lstData.get(x).ivh304.compareTo(2) == 0
									&& lstData.get(x).ivh307 != null && lstData
									.get(x).ivh310 != null) || (!Util
							.esDiferente(lstData.get(x).ivh304, 3, 4)
							&& lstData.get(x).ivh307 != null && lstData.get(x).ivh310 != null))) {
				tcCap300.setBorderRow(x);
			} else if (cap300cap200.ih215 == null
					&& lstData.get(x).ivh304 != null
					&& ((lstData.get(x).ivh304.compareTo(1) == 0 && lstData
							.get(x).ivh306_anio != null)
							|| (lstData.get(x).ivh304.compareTo(2) == 0 && lstData
									.get(x).ivh307 != null) || (!Util
							.esDiferente(lstData.get(x).ivh304, 3, 4)
							&& lstData.get(x).ivh308 != null && lstData.get(x).ivh309 != null))) {
				tcCap300.setBorderRow(x);
			} else {
				tcCap300.setBorderRow(x, true, R.color.red);
			}
		}
		lblT.setText(String.valueOf(lstData.size()) + " / "
				+ String.valueOf(cap300cap200.ih214));
		imgR.setImageDrawable(getResources()
				.getDrawable(
						Util.getInt(cap300cap200.ih214) == lstData.size() ? R.drawable.symbol_ok
								: R.drawable.warning_error));
	}

	public static List<Cap300Delitos> getData() {
		return lstData;
	}

	private void inicio() {
		conteoVictimas = getMarcoService().getConteoVictimas(
				App.getInstance().getComisaria().id_n);
		conteoVictimasWhere = getMarcoService().getConteoVictimasWhere(
				App.getInstance().getComisaria().id_n, null);
	}

	@Override
	public void onAccept() {
		Cap300Delitos cap300 = (Cap300Delitos) dc.get("objeto");
		if (cap300 != null) {
			getMarcoService().deleteCap300Del(cap300.id_n, cap300.nro_mreg,
					cap300.nro_vfreg);
			reloadData(cap300, 2);
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

	public INF_Caratula01Service getCaratulaService() {
		if (caratulaService == null) {
			caratulaService = INF_Caratula01Service.getInstance(getActivity());
		}
		return caratulaService;
	}


	

	private void search(int buscar) {
		buscado = buscar;
		FragmentManager fm = this.getFragmentManager();
		SearchDialog aperturaDialog = SearchDialog.newInstance(this, this, "Buscar");
		aperturaDialog.setEstilo(App.ESTILO, App.ESTILO_BOTON, Filtros.TIPO.ALFA_U, 3);
		aperturaDialog.setSize(600, 800, 90);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}
	
	private List<ListaVictimas> getListaVictimas300() {
		return mimarcoService.getListaVictimas();
	}
	
	
	public <T extends IDetailEntityComponent> void postResultSearch(
			List<String> resps, T... result) {
		ListaVictimas s = (ListaVictimas) result[0];
	}

	public <T extends IDetailEntityComponent> List<T> getListData(NIVEL nivel,
			T selecc) {
		if (nivel == Searchable1.NIVEL.NIVEL1) {
			return (List<T>) getListaVictimas300();
		}
		return null;
	}

	public List<Object[]> getFieldsListData(NIVEL nivel) {
		if (nivel == NIVEL.NIVEL1)
			return Util.getListList(new Object[] { "CODIGO", "NOMBRE" },
					new Object[] { R.string.lb_c3_cap200_p208_tr_5, R.string.lb_c3_cap200_p208_tr_6 },
					new Object[] { 0.6f, 2f });
		return null;
	}

	public <T extends IDetailEntityComponent> boolean getHasEsp(T selecc,
			NIVEL nivel, KEY key) {
		return false;
	}

	public NIVEL getNiveles() {
		return NIVEL.NIVEL1;
	}
	

}
