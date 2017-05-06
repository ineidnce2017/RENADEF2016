package gob.inei.renadef2016.fragments;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.DialogComponent.TIPO_DIALOGO;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.SpinnerField;
import gob.inei.dnce.components.TableComponent;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.activity.CuestionarioFragmentActivity;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil;
import gob.inei.renadef2016.common.MyUtil.Periodo;
import gob.inei.renadef2016.common.MyUtil.Ruta;
import gob.inei.renadef2016.fragments.dialog.C3MARCO_Fragment_01;
import gob.inei.renadef2016.interfaces.Exportable;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.modelo.Marco;
import gob.inei.renadef2016.modelo.Usuario;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.renadef2016.service.MarcoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

// 
public class C3MARCO_Fragment extends FragmentForm implements Respondible {

	@FieldAnnotation(orderIndex = 1)
	public SpinnerField spnDEPASIG;
	@FieldAnnotation(orderIndex = 2)
	public SpinnerField spnRUTA;
	@FieldAnnotation(orderIndex = 3)
	public SpinnerField spnPERIODO;
	
	private TableComponent tcMarco;
	private LabelComponent lblTitulo;
	private MarcoService mimarcoService;
	private INF_Caratula01Service caratulaService;
	private List<Marco> lstData;
	private DialogComponent dc;
	private GridComponent2 grid;
	public static Periodo PERIODO;
	public static Ruta RUTA;

	public C3MARCO_Fragment() {
	}

	public C3MARCO_Fragment parent(MasterActivity parent) {
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
		setHasOptionsMenu(true);
		registerForContextMenu(tcMarco.getListView());
		initObjectsWithoutXML(this, rootView);
		enlazarCajas();
		listening();
//		cargarDatos();
		inicio();

		return rootView;
	}

	@Override
	protected void buildFields() {
		
		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.app_fullname).textSize(21).centrar()
				.negrita();
		
		spnDEPASIG = new SpinnerField(getActivity()).size(altoComponente + 15,
				250).callback("onDepAsigChangeValue").readOnly();
		MyUtil.llenarDepAsig(getActivity(), getMarcoService(), spnDEPASIG, 
				App.getInstance().getUsuario().cod_sede_operativa);
		spnRUTA = new SpinnerField(getActivity()).size(altoComponente + 15,
				210).callback("onRutaChangeValue").title(MyUtil.getVacioRuta("RUTA"));
		MyUtil.llenarRuta(getActivity(), getMarcoService(), spnRUTA, 
				App.getInstance().getUsuario().cod_sede_operativa, App.getInstance().getUsuario().equipo, true);
		
		if(App.getInstance().getUsuario().id==Usuario.REGISTRADOR){
			spnRUTA.setSelectionKey(App.getInstance().getUsuario().ruta.trim());
			spnRUTA.setReadOnly();	
		}
		
		spnPERIODO = new SpinnerField(getActivity()).size(altoComponente + 15, 260).
				callback("onPeriodoChangeValue").title(MyUtil.getVacioPer("PERIODO"));
		
		grid = new GridComponent2(getActivity(), 2);
		grid.addComponent(spnDEPASIG,2);
		grid.addComponent(spnRUTA);
		grid.addComponent(spnPERIODO);
		
		tcMarco = new TableComponent(getActivity(), this,
				gob.inei.dnce.R.style.btnStyleHeaderGreen).size(450, 2800)
				.headerHeight(60).dataColumHeight(60).headerTextSize(15);
		tcMarco.addHeader(R.string.columna_marco_tit_1, 0.6f, TableComponent.ALIGN.CENTER);
		tcMarco.addHeader(R.string.columna_marco_tit_2, 2.8f, TableComponent.ALIGN.LEFT);
		tcMarco.addHeader(R.string.columna_marco_tit_9_1, 0.8f, TableComponent.ALIGN.CENTER);
		tcMarco.addHeader(R.string.columna_marco_tit_9_2, 0.8f, TableComponent.ALIGN.CENTER);
		tcMarco.addHeader(R.string.columna_marco_tit_3, 1.2f, TableComponent.ALIGN.CENTER);
		tcMarco.addHeader(R.string.columna_marco_tit_4, 1.2f, TableComponent.ALIGN.CENTER);
		tcMarco.addHeader(R.string.columna_marco_tit_5, 1.2f, TableComponent.ALIGN.CENTER);
		
		tcMarco.addHeader(R.string.columna_marco_tit_7, 2.5f, TableComponent.ALIGN.LEFT);
		tcMarco.addHeader(R.string.columna_marco_tit_8, 0.5f, TableComponent.ALIGN.CENTER);
		tcMarco.addHeader(R.string.columna_marco_tit_10, 0.5f, TableComponent.ALIGN.CENTER);
		tcMarco.addHeader(R.string.columna_marco_tit_11, 0.7f, TableComponent.ALIGN.CENTER);
		tcMarco.addHeader(R.string.columna_marco_tit_12, 1.5f, TableComponent.ALIGN.LEFT);
//		tcMarco.addHeader(R.string.columna_marco_tit_13, 0.6f, TableComponent.ALIGN.CENTER);
//		tcMarco.addHeader(R.string.columna_marco_tit_14, 0.5f, TableComponent.ALIGN.CENTER);
//		tcMarco.addHeader(R.string.columna_marco_tit_15, 0.5f, TableComponent.ALIGN.CENTER);
//		tcMarco.addHeader(R.string.columna_marco_tit_16, 0.5f, TableComponent.ALIGN.CENTER);
//		tcMarco.addHeader(R.string.columna_marco_tit_17, 0.5f, TableComponent.ALIGN.CENTER);
		tcMarco.addHeader(R.string.columna_marco_tit_18, 0.5f, TableComponent.ALIGN.CENTER);
		tcMarco.setCellColor(-1, 0, true);
		tcMarco.setColorCondition("agregado", Util.getHMObject("1", R.color.super_light_green));
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
	    if (v.getId() == tcMarco.getListView().getId()){
	        menu.setHeaderTitle("Opciones");
	        menu.add(0, 1, 1, "Ir a Car\u00e1tula");
//	        menu.add(0, 2, 2, "Ir a Cobertura");
//	        menu.add(0, 3, 3, "Borrar Infraestructura");
//	        menu.add(0, 4, 4, "Borrar Accidentes de tr\u00e1nsito");
	        menu.add(0, 5, 5, "Borrar Delitos");
	        menu.add(0, 6, 6, "Editar comisar\u00eda");
	        menu.add(0, 7, 7, "Eliminar comisar\u00eda");
//	        menu.add(0, 8, 8, "Formulario Adicional Territorial");
	    }
	    
//	    Integer vinfra=null, vat, vdeli=null;
		AdapterView.AdapterContextMenuInfo info;
		try {	            
			info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			Marco marco = (Marco)(info.targetView.getTag());
			Log.e("cod_deeee", "coddep__: "+marco.cod_dep_asig);
//			vinfra = marco.getVinfra();
//			vat = marco.getVat();
//			vdeli = marco.getVdeli();

			if( Util.getInt(marco.agregado) == 0 ){
				menu.findItem(6).setVisible(false);
				menu.findItem(7).setVisible(false);
			}
			if(Util.getInt(marco.area) == 0 ){
				menu.findItem(5).setVisible(false);
			}
			
//			INF_Caratula01 comisaria = getCaratulaService().getINFCaratula(marco.getId_n(), 
//					Utilidades.getListFields(new INF_Caratula01()), false);
//			if(comisaria == null) comisaria = new INF_Caratula01(marco.getId_n());
//			App.getInstance().setComisaria(comisaria);
			
			INF_Caratula01 comisaria = new INF_Caratula01();
			comisaria = getCaratulaService().getCaratulaMarco(marco.getId_n(), 
					comisaria.getSecCap(getListFields(comisaria, new String[]{"ID"}), false));
			if(comisaria == null) comisaria = new INF_Caratula01(marco.getId_n());
			App.getInstance().setComisaria(comisaria);
		} 
		catch (ClassCastException e) {}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.listar_marco, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.addButtonMarco){
			agregarComisaria(new Marco());
		}
		return true;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
//		String idcomi=null; 
		final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		Marco marco = (Marco)(info.targetView.getTag());
		
		App.getInstance().setMarco(marco);
//		idcomi = marco.getId_n();
		
		if( item.getItemId() == 1){
			parent.nextFragment(CuestionarioFragmentActivity.MARCO+1);
		}
		else if( item.getItemId() == 2 ){
//			Intent i = new Intent(getActivity(), CoberturaActivity.class);
//			startActivity(i);
		}
//		else if( item.getItemId()== 3 ){
//			eliminarCuest(marco, "INF");
//		}
//		else if( item.getItemId()== 4 ){
//			eliminarCuest(marco, "AT");
//		}
		else if( item.getItemId()== 5 ){
			eliminarCuest(marco, "DEL");
		}
		else if( item.getItemId()== 6 ){
			agregarComisaria(marco);
		}
		else if( item.getItemId()== 7 ){
			eliminarComisaria(marco);
		}
		
		else if( item.getItemId()== 8 ){
//			Intent i = new Intent(getActivity(), Anexo_Cap100_Cab.class);
//			Globales.getInstance().setVid_np(idcomi);
//			Globales.currentMarco = marco;
////			Globales.bolInfo = false;
//			Globales.bolInfoact = false;
//			startActivity(i);
////			finish();
		}
		
		return true;
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q1 = createQuestionSection(grid.component());
		LinearLayout q2 = createQuestionSection(tcMarco.getTableView());
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
	
//	public void reloadData(Marco detalle, int opcion){
//		inicio();
//	}
	
	public void reloadData(String id, Integer periodo, int opcion){
		switch (opcion) {
//		case 1:
//			if(lstData.isEmpty() || !lstData.contains(detalle)){
//				lstData.add(detalle);
//				tcMarco.getData().add(detalle);
//			}
//			break;
		case 2:
//			removeObject(detalle);
			removeSeg(id);
			break;
		default:
			break;
		}
//		tcMarco.reloadData();
		refreshPeriodo(periodo);
	}
	
//	private void removeObject(Marco detalle){
//		if(lstData.size()>0){
//			for(int x=0;x<lstData.size();x++){
//				if(lstData.get(x).id_n.equals(detalle.id_n)){
//					lstData.remove(x);
//					tcMarco.getData().remove(x);
//					break;
//				}
//			}
//		}
//	}
	
	private void removeSeg(String id){
		try {
			getMarcoService().borrarSegmentacion(id);
		} catch (SQLException e) {
		}
	}
	
	private void refreshPeriodo(Integer periodo){
		App.getInstance().setMarco(null);
		periodo = periodo == null ? PERIODO.periodo : periodo;
		MyUtil.llenarPeriodo(getActivity(), getMarcoService(),
				spnPERIODO, RUTA.ruta, RUTA.codigoSede, RUTA.codruta);
		spnPERIODO.setTouched(true);
		spnPERIODO.setSelectionKey(periodo);
	}
	
	public void agregarComisaria(Marco marco){
		if(spnPERIODO.getSelectedItemKey() == null) {
			ToastMessage.msgBox(getActivity(), "Debe seleccionar un periodo.",
					ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
			return;
		}
		FragmentManager fm = getFragmentManager();
		C3MARCO_Fragment_01 aperturaDialog = C3MARCO_Fragment_01.
				newInstance(this, marco, getMarcoService());
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.show(fm, "aperturaDialog");
	}
	
	public void eliminarCuest(Marco marco, String cuest){
		dc = new DialogComponent(getActivity(), this,
				TIPO_DIALOGO.YES_NO, getResources()
						.getString(R.string.app_name),
				"Esta apunto de eliminar toda la informaci\u00f3n correspondiente al cuestionario de "
				+ (cuest.equals("INF")?"Infraestructura.":(cuest.equals("AT")?"Accidentes de Tr\u00e1nsito.":
					cuest.equals("DEL")?"Delitos.":""))
				+ " Este proceso es irreversible. \u00bfEst\u00e1 seguro que desea continuar?");
		dc.put("key", marco);
		dc.put("cuest", cuest);
		dc.showDialog();
	}
	
	public void eliminarCuestionario(final String cuest, final Marco marco){
		final Dialog dialog = new Dialog(getActivity());
		dialog.setTitle("Borrar cuestionario...");
		dialog.setContentView(R.layout.dialog_dig_01_prompt);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setCancelable(true);
		
		Button btnPromptAceptar = (Button) dialog.findViewById(R.id.btnPromptAceptar);
		Button btnPromptCancelar = (Button) dialog.findViewById(R.id.btnPromptCancelar);
		final TextView lblPromptQuestion = (TextView) dialog.findViewById(R.id.lblPromptQuestion);
		
		Random r = new Random();
		final int n1 = r.nextInt(10-1) + 1;	//[1,10>
		final int n2 = r.nextInt(10-1) + 1;	//[1,10>
		final String text = cuest.equals("INF")?"Infraestructura":(cuest.equals("AT")?"Accidentes de tr\u00e1nsito":"Delitos");
		lblPromptQuestion.setText("Est\u00e1 a punto de borrar la informaci\u00f3n del cuestionario de " + text + ". Si no desea borrar la informaci\u00f3n presione \"Cancelar\" pero si est\u00e1 seguro que desea continuar entonces \u00bfCu\u00e1l es el resultado de " + n1 + " + " + n2 + "?");
		
		btnPromptAceptar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText txtPrompt = (EditText)dialog.findViewById(R.id.txtPrompt);
				if(txtPrompt.getText().toString().equals("" + (n1+n2))){
					dialog.dismiss();
					boolean eliminado = false;
					
					if(cuest.equals("INF")) eliminado = getMarcoService().eliminarINF(marco.getId_n());
					else if(cuest.equals("AT")) eliminado = getMarcoService().eliminarAT(marco.getId_n());
					else if(cuest.equals("DEL")) eliminado = getMarcoService().eliminarDE(marco.getId_n());
					
					if(eliminado){
						ToastMessage.msgBox(getActivity(), "La informaci\u00f3n ingresada en el cuestionario de " + text + ", "
								+ "para la comisaria " + marco.getId_n() + ", se elimin\u00f3 correctamente.",
								ToastMessage.MESSAGE_INFO,
								ToastMessage.DURATION_LONG);
//						recargarLista();
						refreshRowMarco(marco, cuest);
					}
					else {
						ToastMessage.msgBox(getActivity(), "La informaci\u00f3n ingresada en el cuestionario de " + text + ", "
								+ "para la comisaria " + marco.getId_n() + ", no pudo ser borrada",
								ToastMessage.MESSAGE_ERROR,
								ToastMessage.DURATION_LONG);
					}
				}
			}
		});
		
		btnPromptCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
	
	private void eliminarComisaria(final Marco marco){
		final Dialog dialog = new Dialog(getActivity());
		dialog.setTitle("Eliminar comisar\u00eda...");
		dialog.setContentView(R.layout.dialog_dig_01_prompt);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setCancelable(true);
		
		Button btnPromptAceptar = (Button) dialog.findViewById(R.id.btnPromptAceptar);
		Button btnPromptCancelar = (Button) dialog.findViewById(R.id.btnPromptCancelar);
		final TextView lblPromptQuestion = (TextView) dialog.findViewById(R.id.lblPromptQuestion);
		
		Random r = new Random();
		final int n1 = r.nextInt(10-1) + 1;	//[1,10>
		final int n2 = r.nextInt(10-1) + 1;	//[1,10>
		lblPromptQuestion.setText("Est\u00e1 a punto de borrar la informaci\u00f3n de una comisar\u00eda del marco de datos, si no desea borrar la comisar\u00eda presione \"Cancelar\" pero si est\u00e1 seguro que desea continuar entonces \u00bfcu\u00e1l es el resultado de " + n1 + " + " + n2 + "?");
		
		btnPromptAceptar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText txtPrompt = (EditText)dialog.findViewById(R.id.txtPrompt);
				if(txtPrompt.getText().toString().equals("" + (n1+n2))){
					dialog.dismiss();
					getMarcoService().eliminarINF(marco.getId_n());
					getMarcoService().eliminarAT(marco.getId_n());
					getMarcoService().eliminarDE(marco.getId_n());
					if(getMarcoService().eliminarComisaria(marco.id_n)){
						ToastMessage.msgBox(getActivity(), "La comisaria " + marco.getId_n() + ", se elimin\u00f3 correctamente del marco de datos.",
								ToastMessage.MESSAGE_INFO,
								ToastMessage.DURATION_LONG);
//						refreshRowMarco(marco, "");
						reloadData(marco.id_n, null, 2);
					}
					else {
						ToastMessage.msgBox(getActivity(), "La comisaria " + marco.getId_n() + ", no pudo ser borrada del marco de datos.",
								ToastMessage.MESSAGE_ERROR,
								ToastMessage.DURATION_LONG);
					}
				}
			}
		});
		
		btnPromptCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
	
	private void refreshRowMarco(Marco marco, String cuest){
//		if(cuest.equals("INF")) marco.eliminarINF = 0;
//		else if(cuest.equals("AT")) marco.eliminarAT = 0;
//		else if(cuest.equals("DEL")) marco.eliminarDEL = 0;
		tcMarco.refreshDataForced(marco);
	}
	
	public void onRutaChangeValue(FieldComponent component) {
		SpinnerField spn = (SpinnerField)component;
		if(!spn.isTouched()) return;
		spn.setTouched(false);
//		if(spnRUTA.getSelectedItemPosition() == 0){
//			clearSpinner(spnPERIODO);
//			clearCP(lstData);
//			return;
//		}
		Ruta ruta = (Ruta) component.getValue();
		MyUtil.llenarPeriodo(getActivity(), getMarcoService(), spnPERIODO, ruta.ruta, ruta.codigoSede, ruta.codruta);
		if(PERIODO!=null) {
			spnPERIODO.setTouched(true);
			spnPERIODO.setSelectionKey(PERIODO.periodo);
		}
		spnPERIODO.requestFocus();
	}
	
	public void onPeriodoChangeValue(FieldComponent component) {
		SpinnerField spn = (SpinnerField)component;
		if(!spn.isTouched()) return;
		spn.setTouched(false);
		if(spn.getSelectedItemPosition() == 0) {
			clearCP(lstData);
			return;
		}
		Ruta ruta = (Ruta) spnRUTA.getValue();
		Periodo periodo = (Periodo)component.getValue();
		mostrarlistadigit(ruta, periodo);
	}
	
	private void clearCP(List<? extends Exportable> exportables){
		if(exportables!=null && !exportables.isEmpty()){
			exportables.clear();
			tcMarco.reloadAllDataForced(exportables);
		}
	}
	
	private void mostrarlistadigit(Ruta ruta, Periodo periodo) {
		if (ruta == null || periodo == null) {
			return;
		}
		RUTA = ruta;
		PERIODO = periodo;
		String rut = Ruta.TODOS.equals(ruta.codruta)?Ruta.TODOS:ruta.ruta;
		lstData  = getMarcoService().listarmarcoComisarias(rut, ruta.codigoSede, periodo.periodo, FragmentForm.OPCION.TWO);
		tcMarco.setData(lstData, "ID_N", "NOMCOMISARIA", "FECHAI", "FECHAF", "NOMBREDD", "NOMBREPP", "NOMBREDI", "DIRTEPOL", "TIPO", "TIPOCPNP",
				"UBIGEO", "NOMBRECP", "AGREGADO");
	}
	
//	private void initSpinner(){
//		List<Integer> periodos = getMarcoService().getPeriodos(App.getInstance().getUsuario().ruta);
//		List<Object> keys = new ArrayList<Object>();
//		String[] values = new String[periodos.size()+1];
//		values[0] = "Seleccione Periodo";
//		keys.add(null);
//		
//		int k=1;
//		for(Integer periodo : periodos){
//			keys.add(periodo);
//			values[k] = "Periodo " + periodo ;
//			k++;
//		}
//		
//		ArrayAdapter<String> temp = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, values);
//		temp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spnPERIODO.setAdapterWithKey(temp, keys);
//	}

	@Override
	public boolean grabar() {
		return true;
	}

	private boolean validar() {
		return true;
	}

	@Override
	public void cargarDatos() {
//		Log.e("verifica", "verifca: "+isVisible());
		if(!isVisible()) return;
		inicio();
	}

	private void inicio() {
		getModalidades();
		if(App.getInstance().getUsuario().ruta != null) {
//			spnRUTA.readOnly();
			loadSpinner(App.getInstance().getUsuario().ruta, App.getInstance().getUsuario().cod_sede_operativa,
					App.getInstance().getUsuario().ruta);
		} else if (RUTA != null) {
			loadSpinner(RUTA.ruta, RUTA.codigoSede, RUTA.codruta);
		} else {
			spnRUTA.requestFocus();
		}
	}
	
	public void getModalidades() {
		if(App.MODALIDADES == null)
			App.MODALIDADES = getMarcoService().getModalidades();
	}
	
	private void loadSpinner(String ruta, String codSedeOp, String cod){
		spnRUTA.setSelectionKey(ruta);
		MyUtil.llenarPeriodo(getActivity(), getMarcoService(), spnPERIODO, ruta, codSedeOp, cod);
		spnPERIODO.requestFocus();
		if(PERIODO != null){
			spnPERIODO.setSelectionKey(PERIODO.periodo);
			mostrarlistadigit(RUTA, PERIODO);
		}
	}

	@Override
	public void onAccept() {
		Marco marco = (Marco)dc.get("key");
		String cuest = (String)dc.get("cuest");
		if(marco != null){
			eliminarCuestionario(cuest, marco);
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
}
