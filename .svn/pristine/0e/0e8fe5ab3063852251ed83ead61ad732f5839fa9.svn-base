package gob.inei.renadef2016.fragments; 
// 
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.components.ui.GPSDialog;
import gob.inei.dnce.interfaces.IGPSDialog;
import gob.inei.dnce.util.CapturadorGPS;
import gob.inei.dnce.util.Util;

import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
// 
public class C3GPS extends FragmentForm implements IGPSDialog { 
	@FieldAnnotation(orderIndex = 1)
	public TextField txtGPSLATITUD;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtGPSLONGITUD;
    
	private CapturadorGPS tracker;
	private INF_Caratula01Service caratulaService;
// 
    public C3GPS() {} 
    
    @Override 
    public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
    } 
    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
		rootView = createUI(); 
		initObjectsWithoutXML(this, rootView); 
		enlazarCajas(); 
		listening(); 
		cargarDatos();
		return rootView; 
    } 
    
    @Override 
    protected void buildFields() { 
    	txtGPSLATITUD = new TextField(getActivity()).size(altoComponente, 300).alfanumerico().readOnly();
		txtGPSLONGITUD = new TextField(getActivity()).size(altoComponente, 300).alfanumerico().readOnly();
    } 
    
    @Override 
    protected View createUI() { 
		buildFields(); 
		/*Aca creamos las preguntas*/ 
		LinearLayout q2 = createQuestionSection(txtGPSLATITUD);
		LinearLayout q3 = createQuestionSection(txtGPSLONGITUD);
		///////////////////////////// 
		ScrollView contenedor = createForm(); 
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0); 
		/*Aca agregamos las preguntas a la pantalla*/ 
		form.addView(q2);
		form.addView(q3);
//		LayoutParams lp = form.getLayoutParams();
//		lp.height = 1;
//		lp.width = 1;
//		form.setLayoutParams(lp);
		/*Aca agregamos las preguntas a la pantalla*/ 
    return contenedor; 
    } 
    @Override 
    public boolean grabar() { 
		return true; 
    } 
    private boolean validar() { 
		return true; 
    } 
    @Override 
    public void cargarDatos() { 
    	tracker = new CapturadorGPS(getActivity());
		inicio();
		capturarGPS();
    } 
    
    private void capturarGPS() {
//		tracker.getLocation();
		FragmentManager fm = this.getFragmentManager();
		GPSDialog gps = GPSDialog.newInstance(this, tracker)
				.estilo(R.style.styleHeaderCeleste)
				.estiloBoton(R.style.btnStyleButtonCeleste)
				.property(IGPSDialog.ACCURACY);
		gps.setAncho(MATCH_PARENT);
		gps.show(fm, "gpsDialog");
	}
    private void inicio() { 
    	
    }
	@Override
	public FragmentForm getForm() {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	public void postShow(Map<String, String> properties) {
		String omision = "9999999999";
		if (properties == null) {
			if(Util.esVacio(txtGPSLATITUD)){
				txtGPSLATITUD.setText(omision);
				txtGPSLONGITUD.setText(omision);
			}
		} else {
			String longitud = omision;
			String latitud = omision;
			if (properties.get(IGPSDialog.LONGITUD) != null) {
				longitud = properties.get(IGPSDialog.LONGITUD);
			}
			if (properties.get(IGPSDialog.LATITUD) != null) {
				latitud = properties.get(IGPSDialog.LATITUD);
			}
			if(longitud.equals(omision)||latitud.equals(omision)){
				txtGPSLATITUD.setText(latitud);
				txtGPSLONGITUD.setText(longitud);
			} else {
				boolean existeGPS = getCaratulaService().existeGPS(latitud, longitud);
				if (existeGPS) {
					ToastMessage.msgBox(getActivity(),
							"Punto GPS ya existente, intente tomar otro",
							ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_SHORT);
				}
				txtGPSLATITUD.setText(latitud);
				txtGPSLONGITUD.setText(longitud);
			}
		}
//		if(Globales.txtLat!=null && Globales.txtLon!=null){
//			Globales.txtLat.setText(txtGPSLATITUD.getText().toString());
//			Globales.txtLon.setText(txtGPSLONGITUD.getText().toString());
//			Globales.txtLat = null;
//			Globales.txtLon = null;
//		}
		System.gc();
	} 
	
	public INF_Caratula01Service getCaratulaService() {
		if (caratulaService == null) {
			caratulaService = INF_Caratula01Service.getInstance(getActivity());
		}
		return caratulaService;
	}
} 
