package gob.inei.renadef2016.controller;

import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.dao.xml.XMLObject;
import gob.inei.dnce.dao.xml.XMLObject.BeansProcesados;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil;
import gob.inei.renadef2016.interfaces.Exportable;
import gob.inei.renadef2016.interfaces.IExportacion;
import gob.inei.renadef2016.modelo.Marco;
import gob.inei.renadef2016.service.ImpExpService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import android.app.ProgressDialog;
import android.database.SQLException;
import android.os.AsyncTask;
import android.util.Log;


public class EnvioServidor extends AsyncTask<String, String, String> implements Respondible, Observer, IExportacion {

	private FragmentForm fragmentForm;
	private ProgressDialog pDialog;		
	private Exportable marco;	
	private String rutaArchivo;
	private int cantidadEscribir = 0;
	private int cantidadEscrito = 0;	
	private String msg;
	private String titulo;
	private ImpExpService impExpService;
	private String fechaEnvio;
	private String rutaServidor;
	private int cantidadEnviado;
	private int cantidadEnviadoProcesado;
	
	public EnvioServidor(FragmentForm fragmentForm, String ruta, String titulo) {
		super();
		this.fragmentForm = fragmentForm;
		this.rutaArchivo = ruta;
		this.titulo = titulo;
	}
	
	public void setRegistro(Exportable marco) {
		this.marco = marco;
	}
	
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
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
			enviar();
//			msg = "Exportación completada correctamente.";
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
				DialogComponent dlg = new DialogComponent(fragmentForm.getActivity(), EnvioServidor.this, 
						DialogComponent.TIPO_DIALOGO.NEUTRAL, titulo, msg);
				dlg.showDialog();
			}
		});
	}	

	private int enviar() throws Exception {
		int serverResponseCode = 0;
		try {			
			Log.e(getClass().getSimpleName(), "iniciando");
			calcularPorcentajeProcesado();
			//File archivo = crearArchivo(rutaArchivo, marco);
			String proyecto = this.fragmentForm.getResources().getString(gob.inei.renadef2016.R.string.app_name);
			List<File> archivos = MyUtil.exportarComisaria((Observer)(IExportacion)this, App.getInstance().getVersion(this.fragmentForm.getActivity()), 
					getExportarService(), rutaArchivo, proyecto, (Marco)marco);
			if(archivos!=null && archivos.size()==1) {
				File archivo = archivos.get(0);
			
				String rutaZip = archivo.getAbsolutePath().replace("xml", "zip");
				Util.zip(rutaZip, archivo);
				archivo = new File(rutaZip);									
				Log.e(getClass().getSimpleName(), "finalizo el archivo ["+archivo.getName()+"]");
				
				msg = "";
				try {
					serverResponseCode = uploadFile(archivo);
					if (serverResponseCode == 200
							|| serverResponseCode == HttpURLConnection.HTTP_OK) {
						msg = "Archivo XML enviado con exito.";					
					} else {
						msg = "Archivo(s) no pudo ser enviado(s).";
					}
					Log.e(getClass().getSimpleName(), "finalizo envio");
	//			} catch (MalformedURLException ex) {
	//				Log.e("Subir mi xml al servidor", "error: " + ex.getMessage(),
	//						ex);
	//				msg = "Error en el URL.";
	//				throw new Exception(msg);
				} catch (NullPointerException e) {
					e.printStackTrace();
					Log.e("Subir mi xml al servidor",
							"Exception : " + e.getMessage(), e);
					msg = "Error: NullPointer.";
					throw new Exception(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("Subir mi xml al servidor",
							"Exception : " + e.getMessage(), e);
					msg = "Error: " + e.getMessage();
					throw new Exception(msg);
				} 
			} 
		}catch (IllegalArgumentException e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
			throw new Exception(
					"No se pudo cargar los datos en la Base de Datos.");
		} 
		return serverResponseCode;
	}	
	
	private int uploadFile(File archivo) throws IOException {
		int serverResponseCode = 0; 
		rutaServidor = App.getInstance().getParameters(this.fragmentForm.getActivity()).get(1).value1;
//		Log.e("ruta Servidor", "ruta: "+rutaServidor);
		String vindexrural = getNumeroLineasArchivo(archivo);
		String usuario = App.getInstance().getUsuario().usuario;
		String modoEnvio = "MOVIL";
		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1 * 1024 * 1024;
Log.e("mirate", "mirate: "+ vindexrural);
		FileInputStream fileInputStream = null;
		fileInputStream = new FileInputStream(archivo);
		bytesAvailable = fileInputStream.available();
		cantidadEnviado = bytesAvailable;
		cantidadEnviadoProcesado = 0;
		calcularPorcentajeProcesado();
		bufferSize = Math.min(bytesAvailable, maxBufferSize);
		buffer = new byte[bufferSize];
		bytesRead = fileInputStream.read(buffer, 0, bufferSize);
		URL url = new URL(rutaServidor);
		conn = (HttpURLConnection) url.openConnection();
		Log.e(getClass().getSimpleName(), "conecto a: " + rutaServidor);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("ENCTYPE", "multipart/form-data");
		conn.setRequestProperty("Content-Type",
				"multipart/form-data;boundary=" + boundary);
		conn.setRequestProperty("uploaded_file", archivo.getName());
		dos = new DataOutputStream(conn.getOutputStream());
		dos.writeBytes(twoHyphens + boundary + lineEnd);
		dos.writeBytes("Content-Disposition: form-data; name=\"indexrural\""
				+ lineEnd + lineEnd + vindexrural + lineEnd);
		dos.writeBytes(twoHyphens + boundary + lineEnd);
		dos.writeBytes("Content-Disposition: form-data; name=\"evaluador\""
				+ lineEnd + lineEnd + usuario + lineEnd);
		dos.writeBytes(twoHyphens + boundary + lineEnd);
		dos.writeBytes("Content-Disposition: form-data; name=\"modoenvio\""
				+ lineEnd + lineEnd + modoEnvio + lineEnd);
		dos.writeBytes(twoHyphens + boundary + lineEnd);
		dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
				+ archivo.getName() + "\"" + lineEnd);
		dos.writeBytes(lineEnd);	
		while (bytesRead > 0) {
			dos.write(buffer, 0, bufferSize);
			cantidadEnviadoProcesado += bytesRead;
			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			calcularPorcentajeProcesado();
		}
		dos.writeBytes(lineEnd);
		dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
		serverResponseCode = conn.getResponseCode();
		String serverResponseMessage = conn.getResponseMessage();
		Log.e("cargaxml", "HTTP codigo de respuesta : "
				+ serverResponseMessage + ": " + serverResponseCode);
		
		conn.disconnect();
		fileInputStream.close();
		if (dos != null) {
			dos.flush();
			dos.close();	
		}
//		Map<String, List<String>> p = conn.getRequestProperties();
//		for(Entry<String, List<String>> s : p.entrySet()){
//			Log.e("checkalo: "+s.getKey(), "ruta post: "+s.getValue().toArray());
//		}
		return serverResponseCode;
	}
	
	private String getNumeroLineasArchivo(File file) {
		int nroLineas = 0;
		FileReader fr = null;
		BufferedReader bf = null;
		String linea;
		try {
			fr = new FileReader(file);
			bf = new BufferedReader (fr);
			while ((linea = bf.readLine())!=null) {
				nroLineas++;
			}
		} catch (FileNotFoundException e) {
			Log.e(getClass().getSimpleName(), e.getMessage(), e);
		} catch (IOException e) {
			Log.e(getClass().getSimpleName(), e.getMessage(), e);
		} finally {
			if (fr != null) {
				try {
					if (bf != null) bf.close();
					fr.close();
				} catch (IOException e) {
					Log.e(getClass().getSimpleName(), e.getMessage(), e);
				}	
			}
		}
		return String.valueOf(nroLineas);
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data == null) {
			return;
		}
		XMLObject.BeansProcesados procesado = (BeansProcesados) data;
		this.cantidadEscribir = procesado.getTotal();
		this.cantidadEscrito = procesado.getProcesado();
		calcularPorcentajeProcesado();
	}
	
	private void calcularPorcentajeProcesado() {
		int cantidadProcesado = 0;
		if (cantidadEscribir == cantidadEscrito) {
			cantidadProcesado = 50;
		} else {
			cantidadProcesado += (cantidadEscrito*50)/cantidadEscribir;
		}
		if (cantidadEnviado == cantidadEnviadoProcesado) {
			cantidadProcesado = 50;
		} else {
			cantidadProcesado += (cantidadEnviadoProcesado*50)/cantidadEnviado;
		}		
		publishProgress(String.valueOf(cantidadProcesado));
	}
		
	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAccept() {
		// TODO Auto-generated method stub
	}

//	private File crearArchivo(String ruta, Marco r) throws Exception {
//		String fechaActual = Util.getFechaActualToString();
//		String nombreArchivo = r.ccdd+r.ccpp+r.ccdi + r.id_n;
//		
//		File xmlregistros = new File(ruta + "/"+ nombreArchivo +".xml");
//		if (xmlregistros.exists()) {
//			xmlregistros.delete();
//		}			
//		xmlregistros.createNewFile();
//		List<Marco> registro = new ArrayList<Marco>();
//		registro.add(r);
//		XMLWriter.getInstance().deleteObservers();
//		XMLWriter.getInstance().addObserver(this);
//		List<Caratula> caratulas;
//		List<Visita> visitas;
//		List<Paciente> pacientes;
//		List<CAP_01_01> etapa1;
//		List<CAP_01_02> etapa2;
//		List<Entrevista> entrevistas;
//		if (App.getInstance().getUsuarioSupervisor() != null) {
//			caratulas = getExportarService().getCaratulas(r.id, App.getInstance().getUsuario().id);
//			visitas = getExportarService().getVisitas(r.id, App.getInstance().getUsuario().id);
//			pacientes = getExportarService().getPacientes(r.id, App.getInstance().getUsuario().id);
//			etapa1 = getExportarService().getCAP_01_01s(r.id, App.getInstance().getUsuario().id);
//			etapa2 = getExportarService().getCAP_01_02s(r.id, App.getInstance().getUsuario().id);
//			entrevistas = getExportarService().getEntrevistas(r.id, App.getInstance().getUsuario().id);	
//		} else {
//			caratulas = getExportarService().getCaratulas(r.id, App.getInstance().getUsuario().id);
//			visitas = getExportarService().getVisitas(r.id, App.getInstance().getUsuario().id);
//			pacientes = getExportarService().getPacientes(r.id, App.getInstance().getUsuario().id);
//			etapa1 = getExportarService().getCAP_01_01s(r.id, App.getInstance().getUsuario().id);
//			etapa2 = getExportarService().getCAP_01_02s(r.id, App.getInstance().getUsuario().id);
//			entrevistas = getExportarService().getEntrevistas(r.id, App.getInstance().getUsuario().id);
//		}
//		for (Caratula c : caratulas) {
//			c.version_exportacion = App.getInstance().getVersion(this.activity);
//			c.fecenv = fechaActual;
//		}
//		for (Visita c : visitas) {
//			c.fecenv = fechaActual;
//		}
//		for (Paciente c : pacientes) {
//			c.fecenv = fechaActual;
//		}
//		for (CAP_01_01 c : etapa1) {
//			c.fecenv = fechaActual;
//		}
//		for (CAP_01_02 c : etapa2) {
//			c.fecenv = fechaActual;
//		}
//		for (Entrevista c : entrevistas) {
//			c.fecenv = fechaActual;
//		}
//		
//		XMLWriter.getInstance().putMaps(xmlregistros, this.activity.getResources().getString(gob.inei.renadef2016.R.string.app_name, null,
//				new XMLDataObject(SegmentacionDAO.TABLA, "Segmentacion", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(SegmentacionDAO.TABLA, "ID_N=? AND ESTADO IN (1)", cp.id_n)),
//				new XMLDataObject(MarcoDAO.TABLA_MARCO, "Marco", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_MARCO, "ID_N=? AND AGREGADO IN (1)", cp.id_n)),
//				new XMLDataObject(MarcoDAO.TABLA_CARATULA, "INF_Caratula01", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_CARATULA, "ID_N=?", cp.id_n)),
//				new XMLDataObject(MarcoDAO.TABLA_VISITA, "DelVisita", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_VISITA, "ID_N=?", cp.id_n)),
//				new XMLDataObject(MarcoDAO.TABLA_C100, "Cap100Delitos", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_C100, "ID_N=?", cp.id_n)),
//				new XMLDataObject(MarcoDAO.TABLA_C200, "Cap200Delitos", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_C200, "ID_N=?", cp.id_n)),
//				new XMLDataObject(MarcoDAO.TABLA_C300, "Cap300Delitos", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_C300, "ID_N=?", cp.id_n)),
//				new XMLDataObject(MarcoDAO.TABLA_C300, "Cap400Delitos", XMLDataObject.BORRAR_PRIMERO, service.getRegistros(MarcoDAO.TABLA_C400, "ID_N=?", cp.id_n))
//		);
//		
//		XMLWriter.getInstance().putBeans(xmlregistros, this.activity.getResources().getString(gob.inei.renadef2016.R.string.app_name),
//		new XMLObject(CuestionarioDAO.TABLA_CARATULA, Caratula.class, caratulas),
//		new XMLObject(VisitaDAO.TABLA_VISITA, Visita.class, visitas),
//		new XMLObject(CuestionarioDAO.TABLA_PACIENTE, Paciente.class, pacientes),
//		new XMLObject(CuestionarioDAO.TABLA_CAP_01_01, CAP_01_01.class, etapa1),
//		new XMLObject(CuestionarioDAO.TABLA_CAP_01_02, CAP_01_02.class, etapa2),
//		new XMLObject(CuestionarioDAO.TABLA_ENTREVISTA, Entrevista.class, entrevistas));
//		String rutaZip = xmlregistros.getAbsolutePath().replace("xml","zip");
//		Util.zip(rutaZip, xmlregistros);
//		return xmlregistros;
//	}
	
	private ImpExpService getExportarService() {
		if (impExpService == null) {
			impExpService = ImpExpService.getInstance(this.fragmentForm.getActivity().getApplicationContext());
		}
		return impExpService;
	}

	@Override
	public FragmentForm getFragmentForm() {
		return this.fragmentForm;
	}
}
