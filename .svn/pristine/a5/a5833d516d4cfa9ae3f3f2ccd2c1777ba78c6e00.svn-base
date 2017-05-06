package gob.inei.renadef2016.common;

import gob.inei.dnce.adapter.EntitySpinnerAdapter;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.components.SpinnerField;
import gob.inei.dnce.components.TextBoxField;
import gob.inei.dnce.dao.xml.XMLDataObject;
import gob.inei.dnce.dao.xml.XMLWriter;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.dao.MarcoDAO;
import gob.inei.renadef2016.dao.SegmentacionDAO;
import gob.inei.renadef2016.interfaces.Exportable;
import gob.inei.renadef2016.interfaces.IExportacion;
import gob.inei.renadef2016.modelo.Marco;
import gob.inei.renadef2016.modelo.Ubigeo;
import gob.inei.renadef2016.service.ImpExpService;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.renadef2016.service.UbigeoService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observer;

import android.R.integer;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class MyUtil {
	
	
	private static Periodo vacioper = null;
	private static Ubigeo vacio = null;
	private static Ruta vaciorut = null;
	private static Ruta todorut = null;
	
	private MyUtil() {}
	
	public static Ruta getVacioRuta(String text) {
		return getVacioRuta(Ruta.NINGUNA, text);
	}
	
	public static Ruta getVacioRuta(String codigo, String text) {
		return getVRuta(codigo.equals(Ruta.NINGUNA)?vaciorut:todorut, codigo, text);
	}
	private static Ruta getVRuta(Ruta ruta, String codigo, String text) {
		if (ruta == null) {
			ruta = new Ruta();
			ruta.codigoSede = App.getInstance().getUsuario().cod_sede_operativa;
			ruta.codruta = codigo;
		}
		ruta.ruta = "-- "+(text==null?"SELECCIONE":text)+" --";
		return ruta;
	}
	
	public static Periodo getVacioPer(String text) {
		if (vacioper == null) {
			vacioper = new Periodo();
			vacioper.periodo = null;
		}
		vacioper.periodo_desc = "-- "+(text==null?"SELECCIONE":text)+" --";
		return vacioper;
	}
	
	public static Ubigeo getVacio(String tipo) {
		Ubigeo vacio = new Ubigeo();
		vacio.ubigeo = null;
		vacio.ccdd = null;
		vacio.ccpp = null;
		vacio.ccdi = null;
		if (tipo.equals("CCDD")) {
			vacio.departamento = "-- DEPARTAMENTO --";
		} else if (tipo.equals("CCPP")) {
			vacio.departamento = "-- PROVINCIA --";
		} else if (tipo.equals("CCDI")) {
			vacio.departamento = "-- DISTRITO --";
		}			 
		return vacio;
	}
	
	private static Ubigeo getVacio() {
		if (vacio == null) {
			vacio = new Ubigeo();
			vacio.ubigeo = null;
			vacio.ccdd = null;
			vacio.ccpp = null;
			vacio.ccdi = null;
			vacio.departamento = "-- SELECCIONE --";
			vacio.provincia = "-- SELECCIONE --";
			vacio.distrito = "-- SELECCIONE --";
		}
		return vacio;
	}
	
	public static void llenarDepAsig(Activity activity, MarcoService service, SpinnerField spinner, String codigo) {
		Map<String, Object> mp = service.getDepAsig(codigo);
		if(mp == null || mp.isEmpty()) return;
		llenarItems(activity, spinner, Util.getListList((String)mp.get("NOMBRE")));
	}
	
	public static void llenarRuta(Activity activity, MarcoService service, SpinnerField spinner, String codigoSede, String equipo) {
		llenarRuta(activity, service, spinner, codigoSede, equipo, false);
	}
	public static void llenarRuta(Activity activity, MarcoService service, SpinnerField spinner, String codigoSede, String equipo, boolean all) {
		List<Ruta> lista = service.getRutas(codigoSede, equipo, App.getInstance().getUsuario().cargo_id);
		if(spinner.gettitle()!=null) lista.add(0, (Ruta)spinner.gettitle());
		if(all) lista.add(getVacioRuta(Ruta.TODOS, "TODOS"));
		EntitySpinnerAdapter<Ruta> spinnerAdapter = new EntitySpinnerAdapter<Ruta>(
				activity, android.R.layout.simple_spinner_item, lista);
		List<Object> keysAdapter = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			Ruta d = (Ruta) lista.get(i);
			keysAdapter.add(d.ruta);
		}
		spinner.setAdapterWithKey(spinnerAdapter, keysAdapter);
	}

	public static void llenarPeriodo(Activity activity, MarcoService service, SpinnerField spinner, String ruta, String codigoSede, String cod) {
		List<Periodo> lista = service.getPeriodos(ruta, codigoSede, App.getInstance().getUsuario().cargo_id, cod);
		if(spinner.gettitle()!=null) lista.add(0, (Periodo)spinner.gettitle());
		EntitySpinnerAdapter<Periodo> spinnerAdapter = new EntitySpinnerAdapter<Periodo>(
				activity, android.R.layout.simple_spinner_item, lista);
		List<Object> keysAdapter = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			Periodo d = (Periodo) lista.get(i);
			keysAdapter.add(d.periodo);
		}
		spinner.setAdapterWithKey(spinnerAdapter, keysAdapter);
	}
	
	public static void llenarDepartamento(Activity activity, UbigeoService service, SpinnerField spinner, String cod_sede) {	
		List<Ubigeo> departamentos = new ArrayList<Ubigeo>();
//		departamentos.add(getVacio());
		departamentos.add(spinner.gettitle()==null?getVacio():(Ubigeo)spinner.gettitle());
		departamentos.addAll(service.getDepartamentos(cod_sede));
		EntitySpinnerAdapter<Ubigeo> spinnerAdapter = new EntitySpinnerAdapter<Ubigeo>(
				activity, android.R.layout.simple_spinner_item, departamentos);
		List<Object> keysAdapter = new ArrayList<Object>();
		for (Ubigeo u : departamentos) {
			keysAdapter.add(u.ccdd);
		}
		spinner.setAdapterWithKey(spinnerAdapter, keysAdapter);
	}
	
	public static void llenarProvincia(Activity activity, UbigeoService service, SpinnerField spinner, String cod_sede, String ccdd) {	
		if (ccdd == null) {
			return;
		}
		List<Ubigeo> provincias = new ArrayList<Ubigeo>();
//		provincias.add(getVacio());
		provincias.add(spinner.gettitle()==null?getVacio():(Ubigeo)spinner.gettitle());
		provincias.addAll(service.getProvincias(cod_sede, ccdd));
		EntitySpinnerAdapter<Ubigeo> spinnerAdapter = new EntitySpinnerAdapter<Ubigeo>(
				activity, android.R.layout.simple_spinner_item, provincias);
		List<Object> keysAdapter = new ArrayList<Object>();
		for (Ubigeo u : provincias) {
			keysAdapter.add(u.ccpp);
		}
		spinner.setAdapterWithKey(spinnerAdapter, keysAdapter);
	}
	
	public static void llenarDistrito(Activity activity, UbigeoService service, SpinnerField spinner, String cod_sede, String ccdd, String ccpp) {
		if (ccdd == null || ccpp == null) {
			return;
		}
		List<Ubigeo> distritos = new ArrayList<Ubigeo>();
//		distritos.add(getVacio());
		distritos.add(spinner.gettitle()==null?getVacio():(Ubigeo)spinner.gettitle());
		distritos.addAll(service.getDistritos(cod_sede, ccdd, ccpp));
		EntitySpinnerAdapter<Ubigeo> spinnerAdapter = new EntitySpinnerAdapter<Ubigeo>(
				activity, android.R.layout.simple_spinner_item, distritos);
		List<Object> keysAdapter = new ArrayList<Object>();
		for (Ubigeo u : distritos) {
			keysAdapter.add(u.ccdi);
		}
		spinner.setAdapterWithKey(spinnerAdapter, keysAdapter);
	}
	
	public static void llenarItems(Activity activity, SpinnerField spinner, int resId) {
		llenarItems(activity, spinner, Util.getListList(activity.getResources().getStringArray(resId)));
	}
	public static void llenarItems(Activity activity, SpinnerField spinner, List<String> lista) {
		llenarItems(activity, spinner, lista, null);
	}
	public static void llenarItems(Activity activity, SpinnerField spinner, List<String> lista, String title) {
		if(lista.isEmpty()) return;
		if(title != null) lista.add(0, "-- "+title+" --");
		EntitySpinnerAdapter<String> spinnerAdapter = new EntitySpinnerAdapter<String>(
				activity, android.R.layout.simple_spinner_item, lista);
		List<Object> keysAdapter = new ArrayList<Object>();
		for (int i = 0; i < lista.size(); i++) {
			keysAdapter.add(i==0?null:i);
		}
		spinner.setAdapterWithKey(spinnerAdapter, keysAdapter);
	}
	
//	public static void llenarItems(Activity activity, SpinnerField spinner, int resId) {
//		List<String> lista = Util.getListList(activity.getResources().getStringArray(resId));
//		EntitySpinnerAdapter<String> spinnerAdapter = new EntitySpinnerAdapter<String>(
//				activity, android.R.layout.simple_spinner_item, lista);
//		List<Object> keysAdapter = new ArrayList<Object>();
//		for (int i = 0; i < lista.size(); i++) {
//			keysAdapter.add(i==0?null:i);
//		}
//		spinner.setAdapterWithKey(spinnerAdapter, keysAdapter);
//	}
	
	public static List<String> crearArchivo(IExportacion controller, String version, ImpExpService service, String ruta, Exportable... exportable) throws Exception {
		String proyecto = controller.getFragmentForm().getResources().getString(gob.inei.renadef2016.R.string.app_name);
		List<File> xmlregistros=null;
		List<String> rutas = new ArrayList<String>();
		String rutaZip=null;
		for (Exportable ex : exportable) {
			if (ex instanceof Marco) {
				xmlregistros = exportarComisaria((Observer) controller, version, service, ruta, proyecto, (Marco)ex);
			} 
			
			if(xmlregistros != null){
				for (File file : xmlregistros) {
					rutaZip = "";
					if (file.getName().endsWith("xml")) {
						rutaZip = file.getAbsolutePath().replace(".xml", ".zip");
						Util.zip(rutaZip, file);
						file.delete();					
					} 
					if (!rutaZip.isEmpty()) {
						rutas.add(rutaZip);					
					}
				}
			}
		}
		return rutas;
	}
	
	public static List<File> exportarComisaria(Observer controller, String version, ImpExpService service, String ruta, String proyecto, Marco cp) throws Exception {
		String nombreArchivo = cp.ccdd+cp.ccpp+cp.ccdi+cp.id_n;
		File xmlregistros = new File(ruta + "/RD"+ nombreArchivo +".xml");
		if (xmlregistros.exists()) {
			xmlregistros.delete();
		}		
		List<File> archivos = new ArrayList<File>();
		xmlregistros.createNewFile();
		XMLWriter.getInstance().deleteObservers();
		XMLWriter.getInstance().addObserver(controller);
		
		XMLWriter.getInstance().putMaps(xmlregistros, proyecto, null,
				new XMLDataObject(SegmentacionDAO.TABLA, "Segmentacion", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(SegmentacionDAO.TABLA, "ID_N=? AND ESTADO IN (1)", cp.id_n)),
				new XMLDataObject(MarcoDAO.TABLA_MARCO, "Marco", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_MARCO, "ID_N=? AND AGREGADO IN (1)", cp.id_n)),
				new XMLDataObject(MarcoDAO.TABLA_CARATULA, "INF_Caratula01", XMLDataObject.BORRAR_PRIMERO, inMap(service.getRegistros(MarcoDAO.TABLA_CARATULA, "ID_N=?", cp.id_n), Util.getHMObject("VERSION_EXPORTACION",version))),
				new XMLDataObject(MarcoDAO.TABLA_VISITA, "DelVisita", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_VISITA, "ID_N=?", cp.id_n)),
				new XMLDataObject(MarcoDAO.TABLA_C100, "Cap100Delitos", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_C100, "ID_N=?", cp.id_n)),
				new XMLDataObject(MarcoDAO.TABLA_C200, "Cap200Delitos", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_C200, "ID_N=?", cp.id_n)),
				new XMLDataObject(MarcoDAO.TABLA_C300, "Cap300Delitos", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_C300, "ID_N=?", cp.id_n)),
				new XMLDataObject(MarcoDAO.TABLA_C400, "Cap400Delitos", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_C400, "ID_N=?", cp.id_n))
		);
		
		archivos.add(xmlregistros);

		return archivos;
	}
	
	public static List<Map<String, Object>> inMap(List<Map<String, Object>> lmap, Map<String, String> mp){
		if (lmap != null) {
			for ( Map<String, Object> map : lmap ) {
				for(Entry<String, String> e : mp.entrySet()){
					map.put(e.getKey(), e.getValue());
				}
			}
		}
		return lmap;
	}
	
	public static class LockViewCondition<T extends EditText, E extends View> implements TextWatcher {
		private T view;
		private E[] views;
		private boolean inverse;
		
		public LockViewCondition(T t, E... e) {
			this(true, t, e);
		}
		public LockViewCondition(boolean equal, T t, E... e) {
			inverse = equal;
			view = t;
			views = e;
		}
		
		@Override
		public void afterTextChanged(Editable arg0) {
			if(!Util.esVacio((TextBoxField)view)){
				Log.e("entras", "mirateeee: "+inverse);
				if(inverse) Util.cleanAndLockView(((TextBoxField)view).getContext(), views);
				else Util.lockView(((TextBoxField)view).getContext(), false, views);
			} else {
				if(inverse)	Util.lockView(((TextBoxField)view).getContext(), false, views);
				else Util.cleanAndLockView(((TextBoxField)view).getContext(), views);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
		}
		
	}
	
	public static String getString(Object text, String other){
		if(text == null) return other;
		String result = "";
		try {
			result = text.toString();
		} catch (Exception e) {
			
		}
		return result;
	}
	
	public static class Ruta extends Entity {
		public static final String NINGUNA="---";
		public static final String TODOS="999";
		private static final long serialVersionUID = 1L;
		public String codigoSede;
		public String equipo;
		public String ruta;
		public String codruta;
		
		@Override
		public String toString() {
			if (TODOS.equals(codruta) || NINGUNA.equals(codruta)) {
				return ruta;
			}
			return "RUTA " + ruta;
		}	
		
		public String getFullRuta() {
			return codigoSede + "[" + equipo + "-" + Util.getText(ruta,"") + "]";
		}
	}
	
	public static class Periodo extends Entity {
		private static final long serialVersionUID = 1L;
		public Integer periodo;
		public String periodo_desc;
		
		@Override
		public String toString() {
			return periodo_desc;
		}		
	}
}
