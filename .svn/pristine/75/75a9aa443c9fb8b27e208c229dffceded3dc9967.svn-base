package gob.inei.renadef2016.controller;

import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.dao.xml.XMLObject;
import gob.inei.dnce.dao.xml.XMLObject.BeansProcesados;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil;
import gob.inei.renadef2016.interfaces.Exportable;
import gob.inei.renadef2016.interfaces.IExportacion;
import gob.inei.renadef2016.service.ImpExpService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.app.ProgressDialog;
import android.database.SQLException;
import android.os.AsyncTask;
import android.util.Log;
//import gob.inei.renadef2016.modelo.IExportacion;
//import gob.inei.renadef2016.service.ImpExpService;

public class Exportacion extends AsyncTask<String, String, String> implements Respondible, Observer, IExportacion {
	
	private FragmentForm fragmentForm;
	private ProgressDialog pDialog;		
	private List<Exportable> exportables;	
	private String rutaArchivo;
	private int cantidadEscribir = 0;
	private int cantidadEscrito = 0;	
	private String msg;
	private String titulo;
	private ImpExpService impExpService;
	
	public Exportacion(FragmentForm fragmentForm, String rutaArchivo, String titulo) {
		super();
		this.fragmentForm = fragmentForm;
		this.rutaArchivo = rutaArchivo;
		this.titulo = titulo;		
	}
	
	public void setRegistros(List<Exportable> registrosCab) {
		this.exportables = registrosCab;
	}

	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(this.fragmentForm.getActivity());
		pDialog.setMessage(titulo+" Por favor espere...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(false);
		pDialog.show();
	}

	protected String doInBackground(String... args) {
		try {
			escribirXML();
			msg = "Exportación completada correctamente.";
		} catch (NullPointerException e) {
        	Log.e(this.getClass().toString(),e.getMessage(), e);
			msg = "Error de ausencia de datos.";
		} catch (SQLException e) {
        	Log.e(this.getClass().toString(),e.getMessage(), e);
			msg = e.getMessage();
		} catch (Exception e) {
        	Log.e(this.getClass().toString(),e.getMessage(), e);
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
		this.fragmentForm.getActivity().runOnUiThread(new Runnable() {
			public void run() {
				DialogComponent dlg = new DialogComponent(Exportacion.this.fragmentForm.getActivity(), Exportacion.this, 
						DialogComponent.TIPO_DIALOGO.NEUTRAL, titulo, msg);
				dlg.showDialog();
			}
		});
	}
	
	private void escribirXML() throws Exception {		
		try {			
			MyUtil.crearArchivo(this, App.getInstance().getVersion(this.fragmentForm.getActivity()),  
					getExportarService(), this.rutaArchivo, exportables.toArray(new Exportable[exportables.size()]));
        } catch (FileNotFoundException e) {
        	Log.e(this.getClass().toString(),e.getMessage(), e);
        	msg = "El archivo no pudo ser encontrado.";
        	throw new Exception(msg);
		} catch (IOException e) {
        	msg = "El archivo no pudo ser leido.";
        	throw new Exception(msg);
		} catch (IllegalArgumentException e) {
			msg = "No se pudo cargar los datos de la Base de Datos.";
        	throw new Exception(msg);
		} catch (IllegalAccessException e) {
			Log.e(this.getClass().toString(),e.getMessage(), e);
		} finally {
        }
	}		

	@Override
	public void onCancel() {
	}

	@Override
	public void onAccept() {
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data == null) {
			return;
		}
		XMLObject.BeansProcesados procesado = (BeansProcesados) data;
		this.cantidadEscribir = procesado.getTotal();
		this.cantidadEscrito = procesado.getProcesado();
		pDialog.setMax(this.cantidadEscribir);
		publishProgress(String.valueOf(this.cantidadEscrito));
	}
	
	private ImpExpService getExportarService() {
		if (impExpService == null) {
			impExpService = ImpExpService.getInstance(this.fragmentForm.getActivity().getApplicationContext());
		}
		return impExpService;
	}
	
	private String getErrorString(String msg, Exception e) {
		return msg + " Causado por:\n" + e.getMessage();
	}

	@Override
	public FragmentForm getFragmentForm() {
		return this.fragmentForm;
	}
}

