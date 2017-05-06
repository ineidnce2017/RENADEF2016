package gob.inei.renadef2016.fragments;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.CheckBoxField;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.SpinnerField;
import gob.inei.dnce.components.TableComponent;
import gob.inei.dnce.components.TableComponent.ALIGN;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.interfaces.IDetailEntityComponent;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil;
import gob.inei.renadef2016.common.MyUtil.Periodo;
import gob.inei.renadef2016.common.MyUtil.Ruta;
import gob.inei.renadef2016.controller.EnvioServidor;
import gob.inei.renadef2016.controller.Exportacion;
import gob.inei.renadef2016.interfaces.Exportable;
import gob.inei.renadef2016.modelo.Marco;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.renadef2016.service.SegmentacionService;
import gob.inei.renadef2016.service.UbigeoService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import paul.arian.fileselector.FolderSelectionActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

public class ExportacionFragment extends FragmentForm implements Respondible {
	
	private static enum PROCESS{EXPORTAR, ENVIAR };
	
	@FieldAnnotation(orderIndex = 1)
	public SpinnerField spnDEPASIG;
	@FieldAnnotation(orderIndex = 2)
	public SpinnerField spnRUTA;
	@FieldAnnotation(orderIndex = 3)
	public SpinnerField spnPERIODO; 
	
	private ImageComponent imgNT;
	private LabelComponent lblSel;
	private LabelComponent lblRes;

	private String TAG = ExportacionFragment.this.getClass().getSimpleName();
	private List<? extends Exportable> exportables;
	
	private TableComponent tcMarco;
	private LabelComponent lblTitulo;
	private UbigeoService ubigeoService;
	private GridComponent2 grid1;
	private MarcoService service;
	private SegmentacionService segmentacionService;
	private String ruta;
	private PROCESS accionp;
	private LabelComponent dummy;
	private ButtonComponent btnServidor;
	private ButtonComponent btnMemory;
	private GridComponent grid;
	private List<Exportable> registrosExportar;
	private List<RowExportar> exportarMarco;
	private Periodo periodoE;

	public ExportacionFragment parent(MasterActivity parent) {
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
		enlazarCajas();
		listening();
		cargarDatos();
		return rootView;
	}

	@Override
	public void onCancel() {
		Log.e(TAG, "onCancel()");
	}

	@Override
	public void onAccept() {
		if(accionp == null) return;
		switch (accionp) {
		case EXPORTAR:
			escribirEnTablet();
			break;
		case ENVIAR:
			enviarAlServidor();
			break;
		default:
			Log.e(TAG, "Operacion no programada");
			break;
		}
	}	
	
	private void escribirEnTablet() {
		Exportacion r = new Exportacion(this, ruta, "Exportando información. ");
		r.setRegistros(registrosExportar);
		r.execute();
	}
	
	private void enviarAlServidor() {
		ruta = App.RUTA_BASE + "backups/";			
		EnvioServidor s = new EnvioServidor(this, ruta, "Exportando información. ");
		s.setRegistro(registrosExportar.get(0));
		s.execute();
	}

	@Override
	protected View createUI() {
		buildFields();
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q1 = createQuestionSection(grid1.component());
//		LinearLayout q5 = createQuestionSection(q1);
		LinearLayout q2 = createQuestionSection(createLy(LinearLayout.HORIZONTAL, Gravity.CENTER, imgNT,lblSel,lblRes), 
					tcMarco.getTableView(), grid.component());
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
		form.addView(q1);
		form.addView(q2);
		// /*Aca agregamos las preguntas a la pantalla*/
		return contenedor;
	}

	@Override
	protected void buildFields() {
		lblTitulo = new LabelComponent(getActivity(),
				App.ESTILO).text(R.string.app_fullname_exp)
				.size(MATCH_PARENT, MATCH_PARENT).centrar().textSize(21);
		
		lblSel = new LabelComponent(getActivity()).text(R.string.c1_selall).size(WRAP_CONTENT, 505).negrita();
		lblRes = new LabelComponent(getActivity()).text("0 / 0").size(WRAP_CONTENT, WRAP_CONTENT).negrita();
		imgNT = new ImageComponent(getActivity(), R.drawable.symbol_check, R.drawable.symbol_check).
				size(LayoutParams.WRAP_CONTENT, Util.getTamañoEscalado(getActivity(), 60));
		
		imgNT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				on_SelAll();
			}
		});
		
		tcMarco = new TableComponent(getActivity(), this,
				App.ESTILO).size(600, 650)
				.dataColumHeight(65).headerHeight(60).headerTextSize(15);
		tcMarco.addHeader(R.string.m_seleccionado, 0.3f, CheckBoxField.class);

		tcMarco.addHeader(R.string.m_tc_exp_cod, 0.5f);
		tcMarco.addHeader(R.string.m_tc_exp_nom, 1.5f, ALIGN.LEFT);
		tcMarco.addCallback(0, "onSeleccionadoChangeValue");
		tcMarco.setCellColor(-1, 1, true);
		
		spnDEPASIG = new SpinnerField(getActivity()).size(altoComponente + 15,
				250).callback("onDepAsigChangeValue").readOnly();
		MyUtil.llenarDepAsig(getActivity(), getMarcoService(), spnDEPASIG, 
				App.getInstance().getUsuario().cod_sede_operativa);
		spnRUTA = new SpinnerField(getActivity()).size(altoComponente + 15,
				210).callback("onRutaChangeValue").title(MyUtil.getVacioRuta("RUTA"));
		MyUtil.llenarRuta(getActivity(), getMarcoService(), spnRUTA, 
				App.getInstance().getUsuario().cod_sede_operativa, App.getInstance().getUsuario().equipo, true);

		spnPERIODO = new SpinnerField(getActivity()).size(altoComponente + 15,
				260).callback("onPeriodoChangeValue").title(MyUtil.getVacioPer("PERIODO"));
		
		grid1 = new GridComponent2(getActivity(), 2);
		grid1.addComponent(spnDEPASIG,2);
		grid1.addComponent(spnRUTA);
		grid1.addComponent(spnPERIODO);

		dummy = new LabelComponent(getActivity()).text("")
				.size(MATCH_PARENT, 30).textSize(24)
				.colorFondo(R.color.WhiteSmoke);
		btnServidor = new ButtonComponent(getActivity(),
				App.ESTILO_BOTON).text(
				R.string.btnServidor).size(200, 60);
		btnMemory = new ButtonComponent(getActivity(),
				App.ESTILO_BOTON).text(
				R.string.btnTablet).size(200, 60);
		btnServidor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				enviar();
			}
		});
		btnMemory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				exportar();
			}
		});
		grid = new GridComponent(getActivity(), Gravity.CENTER, 3, 0);
		grid.addComponent(btnServidor);
		grid.addComponent(dummy);
		grid.addComponent(btnMemory);
	}
	
	public void on_SelAll(){
		if(exportables != null){
			int total = exportables.size();
			if(total == 0) return;
			int contador = 0;
			for (RowExportar loc : exportarMarco) {
				if(!loc.getResFin()) continue;
				loc.seleccionado = 1;
				contador++;
			}
			lblRes.setText(String.valueOf(contador+" / "+total));
			tcMarco.reloadData();
		}
    }
	
	private void enviar() {
		DialogComponent dlg = new DialogComponent(getActivity(), this, DialogComponent.TIPO_DIALOGO.NEUTRAL, 
				getResources().getString(R.string.app_name), "Seleccione algún item.");
		List<IDetailEntityComponent> seleccionados = tcMarco.getData();		
		registrosExportar = new ArrayList<Exportable>();
		if (seleccionados != null) {
			for (IDetailEntityComponent exp : seleccionados) {
				RowExportar row = (RowExportar) exp; 
				if (!Util.esDiferente(row.seleccionado, 1)) {
					registrosExportar.add(row.registro);	
				}
			}
		}		
		if (registrosExportar.size() == 0) {
			accionp = null;
			dlg.showDialog();
			return;
		}
		if (registrosExportar.size() > 1) {
			dlg = new DialogComponent(getActivity(), this, DialogComponent.TIPO_DIALOGO.NEUTRAL, 
					getResources().getString(R.string.app_name), "Seleccione solo un item.");
			accionp = null;
			dlg.showDialog();
			return;
		}

		accionp = PROCESS.ENVIAR;
		dlg = new DialogComponent(getActivity(), this, 
				DialogComponent.TIPO_DIALOGO.YES_NO, getResources().getString(R.string.app_name), "Desea enviar al servidor?");
		dlg.showDialog();
	}

	private void exportar() {
		DialogComponent dlg = new DialogComponent(getActivity(), this, DialogComponent.TIPO_DIALOGO.NEUTRAL, 
				getResources().getString(R.string.app_name), "Seleccione algún item.");
		List<IDetailEntityComponent> seleccionados = tcMarco.getData();		
		registrosExportar = new ArrayList<Exportable>();
		if (seleccionados != null) {
			for (IDetailEntityComponent exp : seleccionados) {
				RowExportar row = (RowExportar) exp; 
				if (!Util.esDiferente(row.seleccionado, 1)) {
					registrosExportar.add(row.registro);	
				}
			}
		}		
		if (registrosExportar.size() == 0) {
			accionp = null;
			dlg.showDialog();
			return;
		}

		accionp = PROCESS.EXPORTAR;
		seleccionarRuta();
	}
	
	private static int REQUEST_CODE_PICK_FOLDER = 1;
	
	private void seleccionarRuta() {
		ruta = App.RUTA_BASE + "/backups/";
		File directorio = new File(ruta);
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
		Intent intent = new Intent(this.getActivity(), FolderSelectionActivity.class);
		intent.putExtra(FolderSelectionActivity.START_FOLDER, ruta);
		startActivityForResult(intent, REQUEST_CODE_PICK_FOLDER);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if( resultCode != Activity.RESULT_OK ) return;
		File carpeta = (File) data.getExtras().getSerializable(FolderSelectionActivity.FILES_TO_UPLOAD);
		ruta = carpeta.getAbsolutePath();
		DialogComponent dlg = new DialogComponent(getActivity(), this, 
		DialogComponent.TIPO_DIALOGO.YES_NO, getResources().getString(R.string.app_name), 
			"Esta seguro de guardar los archivos en: "+ ruta+"?");
		dlg.showDialog();
	}	
	
	private class RowExportar implements IDetailEntityComponent {
		public Integer seleccionado;
		private Exportable registro;
		
		public RowExportar(int seleccionado, Exportable registro) {
			super();
			this.seleccionado = seleccionado;
			this.registro = registro;
		}
		
		@Override
		public String toString() {
			return "RowExportar [seleccionado=" + seleccionado + ", registro="
					+ registro.toString() + "]";
		}

		@Override
		public void cleanEntity() {
		}
		
		public String getCodigo() {
			return registro.getCodigoExportacion();
		}
		
		public String getNombre() {
			return registro.getDescripcionExportacion();
		}
		
		public String getUbigeo() {
			return registro.getUbigeo();
		}
		
		public boolean getResFin() {
			return registro.getResFin();
		}

		@Override
		public boolean isTitle() {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	public void onRutaChangeValue(FieldComponent component) {
		SpinnerField spn = (SpinnerField)component; 
		if(!spn.isTouched()) return;
		spn.setTouched(false);
		if(spnRUTA.getSelectedItemPosition() == 0){
			clearSpinner(spnPERIODO);
			clearCP(exportables);
			return;
		}
		Ruta ruta = (Ruta)component.getValue();
		MyUtil.llenarPeriodo(getActivity(), getMarcoService(), spnPERIODO, ruta.ruta, ruta.codigoSede, ruta.codruta);
		if(periodoE!=null) {
			spnPERIODO.setTouched(true);
			spnPERIODO.setSelectionKey(periodoE.periodo);
		}
		spnPERIODO.requestFocus();
	}
	
	public void onPeriodoChangeValue(FieldComponent component) {
		SpinnerField spn = (SpinnerField)component; 
		if(!spn.isTouched()) return;
		spn.setTouched(false);
		if(spn.getSelectedItemKey() == null) {
			clearCP(exportables);
			return;
		}
		Ruta ruta = (Ruta)spnRUTA.getValue();
		periodoE = ((Periodo)component.getValue());
		String rut = Ruta.TODOS.equals(ruta.codruta)?Ruta.TODOS:ruta.ruta;
		cargarMarco(rut, ruta.codigoSede, periodoE.periodo);
	}

//	public void onPeriodoChangeValue(FieldComponent component) {
//		if(!((SpinnerField)component).isTouched()) return;
//		if(((SpinnerField)component).getSelectedItemKey() == null || 
//				((SpinnerField)component).getSelectedItemKey().equals(0)) {clearCP(exportables); return;}
//		Periodo periodo = (Periodo)component.getValue();
//		cargarMarco(App.getInstance().getUsuario().ruta, periodo.periodo);
//	}
	
	public void cargarMarco(String ruta, String codSedeOp, Integer periodo){
		periodo = periodo;
		exportables = getMarcoService().listarmarcoComisarias(ruta, codSedeOp, periodo, OPCION.ONE);
		exportarMarco = new ArrayList<ExportacionFragment.RowExportar>();
		int index = -1;
		for (Exportable loc : exportables) {
			index++;
			exportarMarco.add(new RowExportar(0, loc));
			if(((Marco)loc).agregado != null && ((Marco)loc).agregado.equals(1))
				tcMarco.setCellColor(index, 1, R.color.super_light_green);
			else 
				tcMarco.resetRowColunmColor(index, 1);
		}
		tcMarco.setData(exportarMarco, "seleccionado", "getCodigo", "getNombre");
		lblRes.setText(String.valueOf("0 / "+exportarMarco.size()));
	}
	
	public void onDistritoChangeValue(FieldComponent component) {
		if(!((SpinnerField)component).isTouched()) return;
		clearCP(exportables);
		Periodo periodo = (Periodo)spnPERIODO.getValue();
		
//		cargarMarco(periodo, departamento, provincia, distrito);
	}
	
//	public void cargarMarco(Periodo periodo, Ubigeo departamento, Ubigeo provincia, Frente frente) {
//		if (periodo == null || !Util.esDiferente(periodo.periodo, 0) 
//				|| frente == null) {
//			return;
//		}
//		Log.e(TAG, "cargarMarco");
//		PERIODO = periodo;
//
//		exportables = getSegmentacionService().getMarcoCAxFrente(periodo.periodo, 
//				frente, App.getInstance().getUsuario().id);
//			 
//		exportarMarco = new ArrayList<ExportacionFragment.RowExportar>();
//		for (Exportable loc : exportables) {
//			exportarMarco.add(new RowExportar(0, loc));
//		}
//		lblRes.setText(String.valueOf("0 / "+exportarMarco.size()));
//		tcMarco.setData(exportarMarco, "seleccionado", "getCodigo", "getNombre" /*, "getMedidor"*/);
//	}
	
	public void clearCP(List<? extends Exportable> exportables){
		if(exportables!=null && !exportables.isEmpty()){
			exportables.clear();
			tcMarco.reloadAllDataForced(exportables);
		}
		lblRes.setText(String.valueOf("0 / 0"));
	}

	@Override
	public boolean grabar() {
		return true;
	}

	@Override
	public void cargarDatos() {
		inicio();
	}
	
	private void inicio() {
		if(App.getInstance().getUsuario().ruta != null) {
			spnRUTA.readOnly();
			loadSpinner(App.getInstance().getUsuario().ruta, App.getInstance().getUsuario().cod_sede_operativa,
					App.getInstance().getUsuario().ruta);
		} else {
			spnRUTA.requestFocus();
		}
	}
	
	private void loadSpinner(String ruta, String codSedeOp, String cod){
		spnRUTA.setSelectionKey(ruta);
		MyUtil.llenarPeriodo(getActivity(), getMarcoService(), spnPERIODO, ruta, codSedeOp, cod);
		spnPERIODO.requestFocus();
	}
	
	public void onSeleccionadoChangeValue(Object entity, Integer row, Integer opcion) {
		if (entity != null) {
			RowExportar tmp = (RowExportar) entity;
			if(opcion == 1 && !tmp.getResFin()){
				ToastMessage.msgBox(this.getActivity(), "Comisaria no tiene Resultado Final.",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
				tcMarco.reloadData();
				return;
			}
			tmp.seleccionado = opcion;
			int sum = 0;
			for (RowExportar loc : exportarMarco) {
				if(loc.seleccionado == 1)
					sum++;
			}
			lblRes.setText(String.valueOf(sum+" / "+exportarMarco.size()));
		}			
	}

	public MarcoService getMarcoService() {
		if (service == null) {
			service = MarcoService.getInstance(getActivity());
		}
		return service;
	}

	public SegmentacionService getSegmentacionService() {
		if (segmentacionService == null) {
			segmentacionService = SegmentacionService.getInstance(getActivity());
		}
		return segmentacionService;
	}

	public UbigeoService getUbigeoService() {
		if (ubigeoService == null) {
			ubigeoService = UbigeoService.getInstance(getActivity());
		}
		return ubigeoService;
	}
}
