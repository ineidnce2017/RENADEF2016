package gob.inei.renadef2016.common;

import gob.inei.dnce.R;
import gob.inei.dnce.components.DateTimeField;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.ListViewComponent;
import gob.inei.dnce.components.camera.CameraClass;
import gob.inei.dnce.components.ui.GaleriaDialog;
import gob.inei.dnce.dao.SQLiteDAO;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.modelo.Marco;
import gob.inei.renadef2016.modelo.Parameter;
import gob.inei.renadef2016.modelo.Personal;
import gob.inei.renadef2016.modelo.Search;
import gob.inei.renadef2016.modelo.Ubigeo;
import gob.inei.renadef2016.modelo.Usuario;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.service.ParameterService;

import java.util.List;
import java.util.Map;

import android.os.Environment;
//import de.mindpipe.android.logging.log4j.LogConfigurator;
//import org.apache.log4j.Level;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.Context;

public final class App {
	
	private int textQuestionSize = 19;
	private int leftMargin = 30;
	private int rightMargin = 30;
	private int topMargin = 10;
	private int bottomMargin = 10;
	private int cellspacing = 2;
	private int leftPadding = 7;
	private int rightPadding = 7;
	private int topPadding = 7;
	private int bottomPadding = 7;
	private int heightSize = 54;
	private String version = null;
	private Personal personal = null;
	private Usuario usuario = null;
	private Ubigeo ubigeo = null;
	private Marco marco = null;
	private INF_Caratula01 comisaria = null;
	private Cap200Delitos cap200 = null;
	private Map<Integer, Parameter> parameters = null;
	public static final String RUTA_BASE = Environment.getExternalStorageDirectory().getPath() + "/RENADEF2016/";
	public static final String RUTA_CONFIG = RUTA_BASE + "config/";
	public static final String RUTA_BACKUP = RUTA_BASE + "backups/";
	public static final String RUTA_IMAGENES = RUTA_BASE + "pictures/";
	public static final String RUTA_IMAGENES_GALERIA = RUTA_IMAGENES + "galeria/";
	public static final String RUTA_IMAGENES_CONEXIONES = RUTA_IMAGENES + "conexiones/";
	public static final String RUTA_PRACTICAS = RUTA_BASE + "practicas/";
	public static final String RUTA_BD = RUTA_BASE + "bd/";
	public static final String RUTA_GPS = RUTA_BASE + "gps/";
	public static final int REGISTRADOR = 1;
	public static final int ESTILO = R.style.btnStyleHeaderGreen;
	public static final int ESTILO_BOTON = R.style.btnStyleButtonGreen;
	public static boolean ONLY_VISUALITATION = false;;
	public static final int REGISTRO = 2;
	public static enum MAINTENCE{ALL, ONEONLY}
	public static final String GPS_OMISION = "9999999999";
	public static final String UBI_OMISION = "999999";
	public static final int SEARCHVIAS = 1;
	public static final int SEARCHEDIF = 2;
	public static final int SEARCHNURB = 3;
	public static final int SEARCHUUSO = 4;
	public static final String SEARCHOMIS = "88";
	public static final String SEARCHOMIS8 = "88888888";
	public static final String CODEDIFNOCORRESP = "08";
	public static final int APPSDKAPI = 17;
	// Estados de la Conexion
	public static final int SACTIVO = 1;
	public static final int SFACTIBLE = 2;
	public static final int SCLANDESTINO = 3;
	public static final int SANULADA = 4;
	public static final int SLOTEVACIO = 5;
	public static final int SCNXNOUBIC = 6;
	public static final int SDRCNOUBIC = 7;
	private int modoApp=0;
	public static final String ESTADO = "ESTADO";
	public static final String SOCIEDAD = "SOCIEDAD";
	public static final int SEARCH = gob.inei.renadef2016.R.drawable.green37;
	public static List<Search> MODALIDADES;
	public static List<Search> VICTIMAS;
	private App() {
		Entity.PatronVariable pv = new Entity.PatronVariable("P$[A-Z0-9_]*#;", -1, -1, 1);
		Entity.PATRON_VARIABLE = pv;
		DateTimeField.THEME = DateTimeField.DEFAULT;
		ListViewComponent.HAS_ANIMATION = true;
		FragmentForm.COLOR_LINEA_SECCION_PREGUNTA = R.color.azulaceroclaro;
		DialogFragmentComponent.COLOR_LINEA_SECCION_PREGUNTA = R.color.azulaceroclaro;
		GridComponent2.COLOR_FONDO = R.color.azulaceroclaro;
		CameraClass.RUTA_FOTOS = RUTA_IMAGENES;
		GaleriaDialog.RUTA_FOTOS = RUTA_IMAGENES_GALERIA;
		Util.VALIDAESCALA = false;
		
//		final LogConfigurator logConfigurator = new LogConfigurator();        
//        logConfigurator.setFileName(RUTA_BASE + "divies.log");
//        logConfigurator.setRootLevel(Level.ERROR);
        // Set log level of a specific logger
//        logConfigurator.setLevel("org.apache", Level.ERROR);
//        logConfigurator.configure();
	}
	
	public static App getInstance() {
		return AppHolder.INSTANCE;
	}
	
	private static class AppHolder {
		private static App INSTANCE = new App();		
	}

	public int getTextQuestionSize() {
		return textQuestionSize;
	}

	public int getLeftMargin() {
		return leftMargin;
	}

	public int getRightMargin() {
		return rightMargin;
	}

	public int getTopMargin() {
		return topMargin;
	}

	public int getBottomMargin() {
		return bottomMargin;
	}

	public int getCellspacing() {
		return cellspacing;
	}

	public int getLeftPadding() {
		return leftPadding;
	}

	public int getRightPadding() {
		return rightPadding;
	}

	public int getTopPadding() {
		return topPadding;
	}

	public int getBottomPadding() {
		return bottomPadding;
	}

	public String getVersion(Context context) throws NameNotFoundException {
		if (version == null || "".equals(version)) {
			PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			version = pInfo.versionName;
		}
		return version;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		SQLiteDAO.USER_ID = usuario == null ? null : usuario.id;
		this.usuario = usuario;
	}

	public int getDefaultHeightSize() {
		return heightSize;
	}

	public Map<Integer, Parameter> getParameters(Context context) {
		if (parameters == null) {
			parameters = ParameterService.getInstance(context).getParameters();
		}
		return parameters;
	}

	public void setParameters(Map<Integer, Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public Marco getMarco() {
		return marco;
	}

	public void setMarco(Marco marco) {
		this.marco = marco;
	}
	
	public INF_Caratula01 getComisaria() {
		return comisaria;
	}

	public void setComisaria(INF_Caratula01 comisaria) {
		this.comisaria = comisaria;
	}
	
	public Cap200Delitos getCap200() {
		return cap200;
	}

	public void setCap200(Cap200Delitos cap200) {
		this.cap200 = cap200;
	}
	
	public Ubigeo getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}

	public int getModoApp() {
		return modoApp;
	}

	public void setModoApp(int modoApp) {
		this.modoApp = modoApp;
	}	
	
	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	
}