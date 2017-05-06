package gob.inei.renadef2016.controller;

import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.dao.xml.XMLDataObject;
import gob.inei.dnce.dao.xml.XMLObject;
import gob.inei.dnce.dao.xml.XMLObject.BeansProcesados;
import gob.inei.dnce.dao.xml.XMLReader;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil;
import gob.inei.renadef2016.dao.MarcoDAO;
import gob.inei.renadef2016.dao.MarcoDAO.CounterObservable;
import gob.inei.renadef2016.dao.ParameterDAO;
import gob.inei.renadef2016.dao.PermisoDAO;
import gob.inei.renadef2016.dao.SegmentacionDAO;
import gob.inei.renadef2016.dao.UbigeoDAO;
import gob.inei.renadef2016.dao.UsuarioDAO;
import gob.inei.renadef2016.modelo.Parameter;
import gob.inei.renadef2016.service.MarcoService;
import gob.inei.renadef2016.service.ParameterService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

public class Importacion extends AsyncTask<String, String, String> implements
		Respondible, Observer {

	private Activity activity;
	private ProgressDialog pDialog;
	private String extensionArchivo;
	private MarcoService marcoService;
	private ParameterService parameterService;
	private String versionImportacion;
	private String msg;
	private String titulo;
	private List<File> archivos;
	private int cantidadCargar;
	private int cantidadCargado; 

	public Importacion(Activity activity, String titulo) {
		super();
		this.activity = activity;
		this.titulo = titulo; 
		try {
			this.versionImportacion = App.getInstance().getVersion(activity);
		} catch (NameNotFoundException e) {
			this.versionImportacion = "No definida";
		}
	}

	public List<File> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<File> archivos) {
		this.archivos = archivos;
	}

	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(this.activity);
		pDialog.setMessage(titulo + " Por favor espere...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pDialog.setCancelable(false);
		pDialog.show();
	}

	protected String doInBackground(String... args) {
		try {
			procesarXML2();
			msg = "Importación completada correctamente.";
		} catch (NullPointerException e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
			msg = "Error de ausencia de datos.";
		} catch (SQLException e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
			msg = e.getMessage();
		} catch (Exception e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
			msg = e.getMessage();
		} finally {
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(String... progress) {
		pDialog.setProgress(Integer.parseInt(progress[0]));
	}

	protected void onPostExecute(String file_url) {
		pDialog.dismiss();
		this.activity.runOnUiThread(new Runnable() {
			public void run() {
				DialogComponent dlg = new DialogComponent(activity,
						Importacion.this, DialogComponent.TIPO_DIALOGO.NEUTRAL,
						titulo, msg);
				dlg.showDialog();
			}
		});
	}

	private void procesarXML2() throws Exception {
		try {
			String proyecto = this.activity.getResources().getString(gob.inei.renadef2016.R.string.app_name);
			File carpetaTemporal = null;
			for (File archivo : archivos) {	
				cantidadCargar = 0;
				cantidadCargado = 0;			
				XMLDataObject[] dataObjects = crearColecciones();
				File xml;
				extensionArchivo = archivo.getName().substring(archivo.getName().length()-3, archivo.getName().length());
				if (extensionArchivo.equals("zip")) {
					extensionArchivo = "xml";
					String rutaCarpeta = archivo.getAbsolutePath().replace(archivo.getName(), "");
//					Log.e("checka", "mira");
					carpetaTemporal = new File(rutaCarpeta+"tmp");
					if (!carpetaTemporal.exists()) {
						carpetaTemporal.mkdirs();
					}
//					Util.unzip(archivo.getAbsolutePath(), carpetaTemporal.getAbsolutePath(), "Rena2014-159753-superClave");
					Util.unzip(archivo.getAbsolutePath(), carpetaTemporal.getAbsolutePath());
					String rutaXML = carpetaTemporal.getAbsolutePath() + File.separator + archivo.getName().replace("zip", "xml");
					xml = new File(rutaXML);
					if (!xml.exists()) {
						extensionArchivo = "cfg";
						rutaXML = carpetaTemporal.getAbsolutePath() + File.separator + archivo.getName().replace("zip", "cfg");
						xml = new File(rutaXML);
						if (!xml.exists()) {
							File[] fotos = carpetaTemporal.listFiles();
							for (int i = 0; i < fotos.length; i++) {
								if (!fotos[i].getName().endsWith("jpg")) {
									continue;
								}
								File outFile = new File(App.RUTA_IMAGENES + "conexiones" + File.separator + fotos[i].getName());
								if (outFile.exists()) {
									outFile.delete();
								}
								try {
									Util.copy(fotos[i], outFile);
								} catch (IOException e) {
									Log.e(getClass().getSimpleName(), e.getMessage());
									outFile = null;
								}
							}
							continue;
						}
					}
				} else {
					xml = archivo;
				}
				XMLReader.getInstance().deleteObservers();
				XMLReader.getInstance().addObserver(this);
				XMLReader.getInstance().getMaps(xml, proyecto, dataObjects);
				grabar(dataObjects);				
			}		
			Log.e(this.getClass().toString(), "Acabo de grabar.");
			if (carpetaTemporal != null) {
				if (carpetaTemporal.exists()) {
					Util.deleteRecursive(carpetaTemporal);
				}
			}
		} catch (FileNotFoundException e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
			throw new Exception("El archivo no pudo ser encontrado.");
		} catch (XmlPullParserException e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
			throw new Exception("El archivo no pudo ser cargado.");
		} catch (IOException e) {
			throw new Exception("El archivo no pudo ser leido.");
		} catch (IllegalArgumentException e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
			throw new Exception(
					"No se pudo cargar los datos en la Base de Datos.");
		} catch (IllegalAccessException e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
		} catch (SQLException e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
		}
	}
	
	private void grabar(XMLDataObject[] dataObjects) throws Exception {
		boolean flag = true;
		String versionImportacion = App.getInstance().getVersion(activity);
		SQLiteDatabase dbTX = getMarcoService().startTX();
		XMLDataObject parameters = null;
		try {
			MarcoDAO.CounterObservable contadorObserver = new CounterObservable(cantidadCargado); 
			contadorObserver.deleteObservers();
			contadorObserver.addObserver(this);
			for (int i = 0; i < dataObjects.length && flag; i++) {
				if (dataObjects[i].getRegistros() == null) {
					continue;
				}
				if (MarcoDAO.TABLA_CARATULA.equals(dataObjects[i].getTableName())) {
					MyUtil.inMap(dataObjects[i].getRegistros(), Util.getHMObject("VERSION_IMPORTACION", versionImportacion));
				}
				if (ParameterDAO.TABLA.equals(dataObjects[i].getTableName())) {
					parameters = dataObjects[i];
				}
				Log.e(getClass().getSimpleName(), "Grabar " + dataObjects[i].getTableName() + ":" + dataObjects[i].getRegistros().size());// + " INI: " + sdf.format(new Date()));
				if(!getMarcoService().saveOrUpdate(dbTX, dataObjects[i], contadorObserver)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				getMarcoService().commitTX(dbTX);			
			}	
		} catch (java.sql.SQLException e) {
			throw new Exception("Error: " + e.getMessage());
		} finally {
			getMarcoService().endTX(dbTX);			
		}
		if (parameters == null) {
			return;
		}
		for (Map<String, Object> map : parameters.getRegistros()) {
			if (Util.getText(map.get("PARAMETER")).startsWith("V_")) {
				if (!Util.getText(map.get("VALUE1"),"").isEmpty()) {
					getParameterService().executeQuery(Util.getText(map.get("VALUE1")));
				}				
				if (!Util.getText(map.get("VALUE2"),"").isEmpty()) {
					getParameterService().executeQuery(Util.getText(map.get("VALUE2")));
				}
				if (!Util.getText(map.get("VALUE3"),"").isEmpty()) {
					getParameterService().executeQuery(Util.getText(map.get("VALUE3")));
				}
			}
		}
	}
	
	private XMLDataObject[] crearColecciones() {
		List<XMLDataObject> objects = new ArrayList<XMLDataObject>();
		//MARCO
		objects.add(new XMLDataObject(UbigeoDAO.TABLA, "Ubigeo", XMLDataObject.ACTUALIZAR_PRIMERO).pk("UBIGEO"));
//		objects.add(new XMLDataObject(SegmentacionDAO.TABLA_SEDES_OPERATIVA, "SedeOperativa", XMLDataObject.ACTUALIZAR_PRIMERO).pk("COD_SEDE"));
//		objects.add(new XMLDataObject(SegmentacionDAO.TABLA_SEDES_OPERATIVA_UBIGEO, "SedeOperativaUbigeo", XMLDataObject.ACTUALIZAR_PRIMERO).pk("COD_SEDE").pk("UBIGEO"));
		objects.add(new XMLDataObject(UsuarioDAO.TABLA_USUARIO, "Usuario", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID"));
		objects.add(new XMLDataObject(UsuarioDAO.TABLA_PERSONAL, "Personal", XMLDataObject.ACTUALIZAR_PRIMERO).pk("DNI"));
		objects.add(new XMLDataObject(UsuarioDAO.TABLA_USUARIO_PERSONAL, "UsuarioPersonal", XMLDataObject.ACTUALIZAR_PRIMERO).pk("DNI").pk("USUARIO_ID"));
//		Log.e("mirapues", "dera");
		//SEGMENTACION
//		//MARCO
		objects.add(new XMLDataObject(PermisoDAO.TABLA_ROL, "Rol", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID"));
		objects.add(new XMLDataObject(PermisoDAO.TABLA_PERMISO, "Permiso", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID"));
		objects.add(new XMLDataObject(PermisoDAO.TABLA_ROLES_PERMISOS, "RolPermiso", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID"));
		objects.add(new XMLDataObject(PermisoDAO.TABLA_PREGUNTAS, "Pregunta", XMLDataObject.BORRAR_PRIMERO).pk("ID"));
		objects.add(new XMLDataObject(PermisoDAO.TABLA_RANGOS, "Rango", XMLDataObject.BORRAR_PRIMERO).pk("ID"));
		objects.add(new XMLDataObject(PermisoDAO.TABLA_RANGO_PREGUNTA, "RangoPregunta", XMLDataObject.BORRAR_PRIMERO).pk("RANGO_ID").pk("TABLA").pk("CAMPO"));
		objects.add(new XMLDataObject(PermisoDAO.TABLA_CAMPOS, "Campo", XMLDataObject.BORRAR_PRIMERO).pk("TABLA").pk("CAMPO"));
		objects.add(new XMLDataObject(PermisoDAO.TABLA_ALTERNATIVAS, "Alternativa", XMLDataObject.BORRAR_PRIMERO).pk("TABLA").pk("CAMPO").pk("ALTERNATIVA_VALOR"));
		objects.add(new XMLDataObject(ParameterDAO.TABLA, "Parameter", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID"));
		
		objects.add(new XMLDataObject(MarcoDAO.TABLA_MARCO, "Marco", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID_N"));
		objects.add(new XMLDataObject(SegmentacionDAO.TABLA, "Segmentacion", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID"));
		
		objects.add(new XMLDataObject(MarcoDAO.TABLA_CARATULA, "INF_Caratula01", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID_N"));
		objects.add(new XMLDataObject(MarcoDAO.TABLA_VISITA, "DelVisita", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID_N").pk("III_1"));
		objects.add(new XMLDataObject(MarcoDAO.TABLA_C100, "Cap100Delitos", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID_N"));
		objects.add(new XMLDataObject(MarcoDAO.TABLA_C200, "Cap200Delitos", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID_N").pk("NRO_MREG"));
		objects.add(new XMLDataObject(MarcoDAO.TABLA_C300, "Cap300Delitos", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID_N").pk("NRO_MREG").pk("NRO_VFREG"));
		objects.add(new XMLDataObject(MarcoDAO.TABLA_C400, "Cap400Delitos", XMLDataObject.ACTUALIZAR_PRIMERO).pk("ID_N").pk("NRO_MREG").pk("NRO_PVREG"));
		
		return objects.toArray(new XMLDataObject[objects.size()]);
	}

	private void calcularPorcentajeProcesado() {
		pDialog.incrementProgressBy(1);		
		publishProgress(String.valueOf(cantidadCargado));		
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data == null) {
			return;
		}
		if (data instanceof Map) {
			Map<String, Integer> procesado = (Map<String, Integer>) data;
			this.cantidadCargado = Util.getInt(procesado.get("INSERTADO"));
			calcularPorcentajeProcesado();	
		} else if (data instanceof XMLObject.BeansProcesados) {
			XMLObject.BeansProcesados bp = (BeansProcesados) data;
			this.cantidadCargado = bp.getProcesado();
			this.cantidadCargar = bp.getTotal();
			calcularPorcentajeProcesado();
			pDialog.setMax(this.cantidadCargar);
		}
	}

	private MarcoService getMarcoService() {
		if (marcoService == null) {
			marcoService = MarcoService.getInstance(this.activity
					.getApplicationContext());
		}
		return marcoService;
	}

	private ParameterService getParameterService() {
		if (parameterService == null) {
			parameterService = ParameterService.getInstance(this.activity
					.getApplicationContext());
		}
		return parameterService;
	}

	@Override
	public void onCancel() {
	}

	@Override
	public void onAccept() {
	}
}
