package gob.inei.renadef2016.activity;

import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.controller.Inicializacion;
import gob.inei.renadef2016.service.Service;
import gob.inei.renadef2016.service.UsuarioService;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private EditText txtDNI, txtUSUARIO, txtCLAVE;
	private TextView tvVERSION;
	private UsuarioService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
//		txtDNI = (EditText) findViewById(R.id.txtDNI);
		txtUSUARIO = (EditText) findViewById(R.id.txtUSUARIO);
		txtCLAVE = (EditText) findViewById(R.id.txtCLAVE);
		tvVERSION = (TextView) findViewById(R.id.tvVERSION);
		String version = getResources().getString(R.string.app_version);
		try {
			version +=  " v" + App.getInstance().getVersion(this);
		} catch (NotFoundException e) {
			Log.e(this.getClass().toString(), e.toString(),e);
		} catch (NameNotFoundException e) {
			Log.e(this.getClass().toString(), e.toString(),e);
		}
		tvVERSION.setText(version);
		// TODO QUITAR
//		txtUSUARIO.setText("REG1");
//		txtCLAVE.setText("8829");
	}

	@Override
	public void onBackPressed() {
		super.onDestroy();
		finish();
		return;
	}

	private UsuarioService getService() {
		if (service == null) {
			service = UsuarioService.getInstance(getApplicationContext());
		}
		return service;
	}
	
	public void restoreBD(View v) {
		try {
			File f = new File(App.RUTA_BASE + "bd/apurimac_bd/bdSedapar2014_produccion.db");
			if (!f.exists()) {
				ToastMessage.msgBox(this, "Error al ubicar la BD de backup", ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
				return;
			}
			String rutaBD = getApplicationContext().getDatabasePath(
					Service.DATABASE_NAME).getAbsolutePath();
			File dst = new File(rutaBD);
			try {
				if (dst.exists()) {
					dst.delete();
				}
				dst.createNewFile();
			} catch (IOException e) {
				Log.e("IOException", "exception en el metodo createNewFile()");
			}
			Log.e("Backup", f.getAbsolutePath());
			Util.copy(f, dst);

		} catch (IOException e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
		}
	}

	public void cerrar(View v) {
		try {
			File f = new File(App.RUTA_BASE + "bd/");
			if (!f.exists()) {
				f.mkdirs();
			}
			File dst = new File(App.RUTA_BASE + "bd/" + Service.DATABASE_NAME);
			try {
				if (dst.exists()) {
					dst.delete();
				}
				dst.createNewFile();
			} catch (IOException e) {
				Log.e("IOException", "exception en el metodo createNewFile()");
			}
			String rutaBD = getApplicationContext().getDatabasePath(
					Service.DATABASE_NAME).getAbsolutePath();
			Log.e("Backup", rutaBD);
			Util.copy(new File(rutaBD), dst);

		} catch (IOException e) {
			Log.e(this.getClass().toString(), e.getMessage(), e);
		}
		android.os.Process.killProcess(android.os.Process.myPid());
		finish();
	}

	public void validarLogin(View v) {
		File directorio = new File(App.RUTA_CONFIG);
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
		directorio = new File(App.RUTA_BACKUP);
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
		directorio = new File(App.RUTA_BD);
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
//		directorio = new File(App.RUTA_PRACTICAS);
//		if (!directorio.exists()) {
//			directorio.mkdirs();
//		}
//		directorio = new File(App.RUTA_GPS);
//		if (!directorio.exists()) {
//			directorio.mkdirs();
//		}
		directorio = new File(App.RUTA_IMAGENES);
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
		directorio = new File(App.RUTA_IMAGENES_GALERIA);
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
//		directorio = new File(App.RUTA_IMAGENES_CONEXIONES);
//		if (!directorio.exists()) {
//			directorio.mkdirs();
//		}
		v.setEnabled(false);
		if (valida()) {
			Inicializacion i = new Inicializacion(LoginActivity.this);
			i.setUsuario(txtUSUARIO.getText().toString().trim());
			i.setClave(txtCLAVE.getText().toString().trim());
//			i.setDNI(txtDNI.getText().toString().trim());
			i.setDNI("00000000");
			i.execute();
		}
		v.setEnabled(true);
	}

	public boolean valida() {
		if (Util.esVacio(txtUSUARIO)) {
			ToastMessage.msgBox(this, "El Usuario no puede estar vacio",
					ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
			txtUSUARIO.requestFocus();
			return false;
		}
		if (Util.esVacio(txtCLAVE)) {
			ToastMessage.msgBox(this, "La Clave no puede estar vacio",
					ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
			txtCLAVE.requestFocus();
			return false;
		}
//		if (!txtUSUARIO.getText().toString().equals("SIS") && Util.esVacio(txtDNI)) {
//			ToastMessage.msgBox(this, "El DNI no puede estar vacio",
//					ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
//			txtDNI.requestFocus();
//			return false;
//		}
		return true;
	}
}
