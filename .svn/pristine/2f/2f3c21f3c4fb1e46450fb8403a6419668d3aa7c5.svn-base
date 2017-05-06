package gob.inei.renadef2016.fragments;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.components.ui.GPSDialog;
import gob.inei.dnce.interfaces.IGPSDialog;
import gob.inei.dnce.util.CapturadorGPS;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.service.INF_Caratula01Service;

import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3CARAT_Fragment_C6 extends FragmentForm implements IGPSDialog {

	@FieldAnnotation(orderIndex = 1)
	public IntegerField txtV3_1;
	@FieldAnnotation(orderIndex = 2)
	public IntegerField txtV3_2; 
	@FieldAnnotation(orderIndex = 3)
	public IntegerField txtV3_3;
	@FieldAnnotation(orderIndex = 4)
	public IntegerField txtV3_4; 
	@FieldAnnotation(orderIndex = 5)
	public IntegerField txtVI1A;
	@FieldAnnotation(orderIndex = 6)
	public TextField txtVI1B;
	@FieldAnnotation(orderIndex = 7)
	public IntegerField txtVI2A;
	@FieldAnnotation(orderIndex = 8)
	public TextField txtVI2B;
	@FieldAnnotation(orderIndex = 9)
	public IntegerField txtVI3A;
	@FieldAnnotation(orderIndex = 10)
	public TextField txtVI3B;
	@FieldAnnotation(orderIndex = 11)
	public IntegerField txtVI4A;
	@FieldAnnotation(orderIndex = 12)
	public TextField txtVI4B;
	@FieldAnnotation(orderIndex = 13)
	public TextField txtGPSLATITUD;
	@FieldAnnotation(orderIndex = 14)
	public TextField txtGPSLONGITUD;
	@FieldAnnotation(orderIndex = 15)
	public ButtonComponent btnGPSPoint;
	
	private TextField txtGPSALTITUD;
	private LabelComponent lblTitulo, lblTitulo1, lblTitulo2;
	private INF_Caratula01Service caratulaService;
	private INF_Caratula01 caratula;
	private GridComponent2 grid_Fun, grid_Gps, grid_Tot;
	private CapturadorGPS tracker;

	public C3CARAT_Fragment_C6() {
	}

	public C3CARAT_Fragment_C6 parent(MasterActivity parent) {
		this.parent = parent;
		return this;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getActivity().setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = createUI();
		setHasOptionsMenu(true);
		initObjectsWithoutXML(this, rootView);
		enlazarCajas();
		listening();
	
		return rootView;
	}

	@Override
	protected void buildFields() {
		
		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_Totales).textSize(21).centrar()
				.negrita();
		lblTitulo1 = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_UbicacionGPS).textSize(21).centrar()
				.negrita();
		lblTitulo2 = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_13Funcionarios).textSize(21).centrar()
				.negrita();
		
		btnGPSPoint = new ButtonComponent(getActivity(), gob.inei.dnce.R.style.btnStyleButtonGreen).
				text(R.string.lb_C_EstablecerGPS).size(250, altoComponente);
		
		btnGPSPoint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				capturarGPS();
			}
		});
		
		LabelComponent lblNDel = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbNDelitos).size(altoComponente+10, 220);
		LabelComponent lblNDen = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbNDenuncias).size(altoComponente+10, 220);
		LabelComponent lblNDVF = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbNVictFallecidos).size(altoComponente+10, 220);
		LabelComponent lblNVHD = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbNVictxHomDolosos).size(altoComponente+10, 220);
		
		txtV3_1 = new IntegerField(getActivity()).size(altoComponente, 150).readOnly().negrita().centrar();
		txtV3_2 = new IntegerField(getActivity()).size(altoComponente, 150).readOnly().negrita().centrar();
		txtV3_3 = new IntegerField(getActivity()).size(altoComponente, 150).readOnly().negrita().centrar();
		txtV3_4 = new IntegerField(getActivity()).size(altoComponente, 150).readOnly().negrita().centrar();
		
		grid_Tot = new GridComponent2(getActivity(), 4).colorFondo(R.color.blanco);
		grid_Tot.addComponent(lblNDel);
		grid_Tot.addComponent(txtV3_1);
		grid_Tot.addComponent(lblNDen);
		grid_Tot.addComponent(txtV3_2);
		grid_Tot.addComponent(lblNDVF);
		grid_Tot.addComponent(txtV3_3);
		grid_Tot.addComponent(lblNVHD);
		grid_Tot.addComponent(txtV3_4);
		
		LabelComponent lblLat = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_Latitud).size(altoComponente, 160);
		LabelComponent lblLon = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_Longitud).size(altoComponente, 160);

		LabelComponent lblCar = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_6_FDP_Car).size(altoComponente, 235).centrar();
		LabelComponent lblDni = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_6_FDP_Dni).size(altoComponente, 140).centrar();
		LabelComponent lblNom = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_VII_6_FDP_Nom).size(altoComponente, 160).centrar();
		LabelComponent lblEnc = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbEmpadronador).size(altoComponente, 160);
		LabelComponent lblJef = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbJefeEquipo).size(altoComponente, 160);
		LabelComponent lblCoo = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbCoordinador).size(altoComponente, 160);
		LabelComponent lblSup = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbSupervisorNacional).size(altoComponente, 160);
		
		txtGPSALTITUD = new TextField(getActivity()).size(altoComponente, 300).alfanumerico().readOnly();
		txtGPSLATITUD = new TextField(getActivity()).size(altoComponente, 300).alfanumerico().readOnly();
		txtGPSLONGITUD = new TextField(getActivity()).size(altoComponente, 300).alfanumerico().readOnly();
		
		grid_Gps = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_Gps.addComponent(lblLat);
		grid_Gps.addComponent(txtGPSLATITUD);
		grid_Gps.addComponent(lblLon);
		grid_Gps.addComponent(txtGPSLONGITUD);

		txtVI1A = new IntegerField(getActivity(), false).size(altoComponente, 140);
		txtVI1B = new TextField(getActivity()).size(altoComponente, 400);
		txtVI2A = new IntegerField(getActivity()).size(altoComponente, 140);
		txtVI2B = new TextField(getActivity()).size(altoComponente, 400);
		txtVI3A = new IntegerField(getActivity()).size(altoComponente, 140);
		txtVI3B = new TextField(getActivity()).size(altoComponente, 400);
		txtVI4A = new IntegerField(getActivity()).size(altoComponente, 140);
		txtVI4B = new TextField(getActivity()).size(altoComponente, 400);

		grid_Fun = new GridComponent2(getActivity(), 3).colorFondo(R.color.blanco);
		grid_Fun.addComponent(lblCar);
		grid_Fun.addComponent(lblDni);
		grid_Fun.addComponent(lblNom);
		grid_Fun.addComponent(lblEnc);
		grid_Fun.addComponent(txtVI1A);
		grid_Fun.addComponent(txtVI1B);
//		grid_Fun.addComponent(lblJef);
//		grid_Fun.addComponent(txtVI2A);
//		grid_Fun.addComponent(txtVI2B);
		grid_Fun.addComponent(lblCoo);
		grid_Fun.addComponent(txtVI3A);
		grid_Fun.addComponent(txtVI3B);
		grid_Fun.addComponent(lblSup);
		grid_Fun.addComponent(txtVI4A);
		grid_Fun.addComponent(txtVI4B);
	
//		Filtros Agregados
		Filtros.setFiltro(txtVI1A, Filtros.TIPO.NUMBER,8,0, null,1,99999998,-1,8,1,8,8);
		Filtros.setFiltro(txtVI1B, Filtros.TIPO.ALFA_U, 50,null);
		Filtros.setFiltro(txtVI2A, Filtros.TIPO.NUMBER,8,0, null,1,99999998,-1,8,1,8,8);
		Filtros.setFiltro(txtVI2B, Filtros.TIPO.ALFA_U, 50,null);
		Filtros.setFiltro(txtVI3A, Filtros.TIPO.NUMBER,8,0, null,1,99999998,-1,8,1,8,8);
		Filtros.setFiltro(txtVI3B, Filtros.TIPO.ALFA_U, 50,null);
		Filtros.setFiltro(txtVI4A, Filtros.TIPO.NUMBER,8,0, null,1,99999998,-1,8,1,8,8);
		Filtros.setFiltro(txtVI4B, Filtros.TIPO.ALFA_U, 50,null);
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q1 = createQuestionSection(grid_Tot.component());
		LinearLayout q2 = createQuestionSection(lblTitulo1);
		LinearLayout q3 = createQuestionSection(createLy(LinearLayout.VERTICAL, Gravity.CENTER, grid_Gps.component(), 
				btnGPSPoint));
		LinearLayout q4 = createQuestionSection(lblTitulo2);
		LinearLayout q5 = createQuestionSection(grid_Fun.component());

		// ///////////////////////////
		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);
		/* Aca agregamos las preguntas a la pantalla */
		form.addView(q0);
		form.addView(q1);
		form.addView(q2);
		form.addView(q3);
		form.addView(q4);
		form.addView(q5);
		/* Aca agregamos las preguntas a la pantalla */
		return contenedor;
	}

	@Override
	public boolean grabar() {
		if (!validar()) {
			if (error) {
				if (!mensaje.equals(""))
					ToastMessage.msgBox(this.getActivity(), mensaje,
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
				if (view != null)
					view.requestFocus();
			}
			return false;
		}
		
		uiToEntity(caratula);
		try {
			if(!getCaratulaService().saveOrUpdate(caratula, caratula.getSecCap(getListFields(this, setDataxCuest())))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			}
		} catch (Exception e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			return false;
		}
		
//		uiToEntity(caratula);
//		if(!getCaratulaService().grabarCaratula(caratula, 1, Utilidades.getListFields(this, setDataxCuest()))){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
//		
		return true;
	}

	private String[] setDataxCuest() {
//		if( Globales.getInstance().CUESTIONARIO==Globales.getInstance().INFRAESTRUCTURA ){
//			caratula.gpslatitud_inf = txtGPSLATITUD.getText().toString();
//			caratula.gpslongitud_inf = txtGPSLONGITUD.getText().toString();
//			caratula.vi3a_inf = Util.esVacio(txtVI3A)?null:txtVI3A.getText().toString();
//			caratula.vi3b_inf = Util.esVacio(txtVI3B)?null:txtVI3B.getText().toString();
//			caratula.vi4a_inf = Util.esVacio(txtVI4A)?null:txtVI4A.getText().toString();
//			caratula.vi4b_inf = Util.esVacio(txtVI4B)?null:txtVI4B.getText().toString();
//			return new String[]{"GPSLATITUD_INF", "GPSLONGITUD_INF","VI3A_INF","VI3B_INF","VI4A_INF","VI4B_INF"};
//		}
//		if( Globales.getInstance().CUESTIONARIO==Globales.getInstance().ACCIDENTES_DE_TRANSITO ){
//			caratula.gpslatitud_at = txtGPSLATITUD.getText().toString();
//			caratula.gpslongitud_at = txtGPSLONGITUD.getText().toString();
//			caratula.vi3a_at = Util.esVacio(txtVI3A)?null:txtVI3A.getText().toString();
//			caratula.vi3b_at = Util.esVacio(txtVI3B)?null:txtVI3B.getText().toString();
//			caratula.vi4a_at = Util.esVacio(txtVI4A)?null:txtVI4A.getText().toString();
//			caratula.vi4b_at = Util.esVacio(txtVI4B)?null:txtVI4B.getText().toString();
//			return new String[]{"GPSLATITUD_AT", "GPSLONGITUD_AT","VI3A_AT","VI3B_AT","VI4A_AT","VI4B_AT"};
//		}
//		if( Globales.getInstance().CUESTIONARIO==Globales.getInstance().DELITOS ){
			caratula.gpslatitud_del = txtGPSLATITUD.getText().toString();
			caratula.gpslongitud_del = txtGPSLONGITUD.getText().toString();
			caratula.gpsaltitud_del = txtGPSALTITUD.getText().toString();
			caratula.vi3a_de = Util.esVacio(txtVI3A)?null:txtVI3A.getText().toString();
			caratula.vi3b_de = Util.esVacio(txtVI3B)?null:txtVI3B.getText().toString();
			caratula.vi4a_de = Util.esVacio(txtVI4A)?null:txtVI4A.getText().toString();
			caratula.vi4b_de = Util.esVacio(txtVI4B)?null:txtVI4B.getText().toString();
			return new String[]{"GPSLATITUD_DEL", "GPSLONGITUD_DEL","GPSALTITUD_DEL","VI3A_DE","VI3B_DE","VI4A_DE","VI4B_DE"};
//		}
//		return null;
	}

	private boolean validar() {
		error = false;
		//validacion Agregada funcionario de la encuesta
		
		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}			
			
		//
		
		if(Util.esVacio(txtGPSLATITUD) || Util.esVacio(txtGPSLONGITUD)){
			mensaje = "Debe Obtener el Punto GPS; Latitud y Longitud.";
            error = true;
            view = btnGPSPoint;
		}
		
		else if (Util.esVacio(txtVI1A)) {
            mensaje = "Debe ingrese el numero de DNI del Encuestador(a)";
            error = true;
            view = txtVI1A;
			} 
		else if (txtVI1A.getText().toString().trim().length() != 8) {
            mensaje = "El Numero de DNI debe tener 8 digitos";
            error = true;
            view = txtVI1A;
			} 
     	else if (Util.esVacio(txtVI1B)) {
     		mensaje = "Debe Ingresar el nombre del Encuestador(a)";
            error = true;
            view = txtVI1B;
            return false;
     		}

		if(!error){
			if (! Util.esVacio(txtVI2A)){
				if(txtVI2A.getText().toString().trim().length() != 8) {
                   mensaje = "El Numero de DNI debe tener 8 digitos";
                   error = true;
                   view = txtVI2A;
                   return false;
				}
				if(Util.esVacio(txtVI2B)) {
                   mensaje = "Debe Ingresar el nombre del Jefe(a)";
                   error = true;
                   view = txtVI2B;
                   return false;
				}
			} 
		
			else if(!Util.esVacio(txtVI2B)){
				mensaje = "Debe Ingresar el numero de DNI del Jefe(a)";
				error = true;
				view = txtVI2A;
				return false;
			}	
				
			if(!Util.esVacio(txtVI3A)){
				if(txtVI3A.getText().toString().trim().length() !=8){
					mensaje="El numero de DNI debe tener 8 digitos";
					view=txtVI3A;
					error=true;	
					return false;
				}
				if(Util.esVacio(txtVI3B)){
					mensaje="Debe ingresar el nombre del Coordinador(a)";
					view=txtVI3B;
					error=true;					
				}
			}
			else if(!Util.esVacio(txtVI3B)){
					mensaje = "Debe Ingresar el numero de DNI del Coordinador(a)";
					error = true;
					view = txtVI3A;
					return false;
				}	
			
			//supervisor
			
			if(!Util.esVacio(txtVI4A)){
				if(txtVI4A.getText().toString().trim().length() !=8){
					mensaje="El numero de DNI debe tener 8 digitos";
					view=txtVI4A;
					error=true;	
					return false;
				}
				if(Util.esVacio(txtVI4B)){
					mensaje="Debe ingresar el nombre del Supervisor(a) Nacional";
					view=txtVI4B;
					error=true;					
				}
			}
			else if(!Util.esVacio(txtVI4B)){
					mensaje = "Debe Ingresar el numero de DNI del Supervisor(a) Nacional";
					error = true;
					view = txtVI4A;
					return false;
				}	
			
			
		}
		
		if(error) return false;
		return true;
	}

	@Override
	public void cargarDatos() {
		tracker = new CapturadorGPS(getActivity());
		caratula = App.getInstance().getComisaria();
		entityToUI(caratula);
		setUIxCuest();
		inicio();
	}

	private void setUIxCuest() {
//		if( Globales.getInstance().CUESTIONARIO==Globales.getInstance().INFRAESTRUCTURA ){
//			txtGPSLATITUD.setText(caratula.gpslatitud_inf);
//			txtGPSLONGITUD.setText(caratula.gpslongitud_inf);
//			txtVI3A.setText(caratula.vi3a_inf==null?"":caratula.vi3a_inf);
//			txtVI3B.setText(caratula.vi3b_inf==null?"":caratula.vi3b_inf);
//			txtVI4A.setText(caratula.vi4a_inf==null?"":caratula.vi4a_inf);
//			txtVI4B.setText(caratula.vi4b_inf==null?"":caratula.vi4b_inf);
//		} else if( Globales.getInstance().CUESTIONARIO==Globales.getInstance().ACCIDENTES_DE_TRANSITO ){
//			txtGPSLATITUD.setText(caratula.gpslatitud_at);
//			txtGPSLONGITUD.setText(caratula.gpslongitud_at);
//			txtVI3A.setText(caratula.vi3a_at==null?"":caratula.vi3a_at);
//			txtVI3B.setText(caratula.vi3b_at==null?"":caratula.vi3b_at);
//			txtVI4A.setText(caratula.vi4a_at==null?"":caratula.vi4a_at);
//			txtVI4B.setText(caratula.vi4b_at==null?"":caratula.vi4b_at);
//		} else if( Globales.getInstance().CUESTIONARIO==Globales.getInstance().DELITOS ){
			txtGPSLATITUD.setText(caratula.gpslatitud_del);
			txtGPSLONGITUD.setText(caratula.gpslongitud_del);
			txtVI3A.setText(caratula.vi3a_de==null?"":caratula.vi3a_de);
			txtVI3B.setText(caratula.vi3b_de==null?"":caratula.vi3b_de);
			txtVI4A.setText(caratula.vi4a_de==null?"":caratula.vi4a_de);
			txtVI4B.setText(caratula.vi4b_de==null?"":caratula.vi4b_de);
//		}
	}

//	private String[] getDataxCuest() {
//		if( Globales.getInstance().CUESTIONARIO==Globales.getInstance().INFRAESTRUCTURA ){
//			return new String[]{"ID_N", "GPSLATITUD_INF", "GPSLONGITUD_INF","VI3A_INF","VI3B_INF","VI4A_INF","VI4B_INF"};
//		}
//		if( Globales.getInstance().CUESTIONARIO==Globales.getInstance().ACCIDENTES_DE_TRANSITO ){
//			return new String[]{"ID_N", "GPSLATITUD_AT", "GPSLONGITUD_AT","VI3A_AT","VI3B_AT","VI4A_AT","VI4B_AT"};
//		}
//		if( Globales.getInstance().CUESTIONARIO==Globales.getInstance().DELITOS ){
//			return new String[]{"ID_N", "GPSLATITUD_DEL", "GPSLONGITUD_DEL","VI3A_DE","VI3B_DE","VI4A_DE","VI4B_DE"};
//		}
//		return new String[]{"ID_N"};
//	}

	private void inicio() {
		cleanAndLockView(txtVI2A, txtVI2B);
		btnGPSPoint.requestFocus();
	}
	
	@Override
	public boolean getSaltoNavegation() {
		return validar();
	}

	private void capturarGPS() {
		FragmentManager fm = this.getFragmentManager();
		GPSDialog gps = GPSDialog.newInstance(this, tracker)
				.estilo(R.style.btnStyleHeaderGreen)
				.estiloBoton(R.style.btnStyleButtonGreen)
				.property(IGPSDialog.ACCURACY);
		gps.setAncho(MATCH_PARENT);
		gps.show(fm, "gpsDialog");
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
				txtGPSALTITUD.setText(omision);
				txtVI1A.requestFocus();
			}
			return;
		}
		String longitud = omision;
		String latitud = omision;
		String altitud = omision;
		if (properties.get(IGPSDialog.LONGITUD) != null) {
			longitud = properties.get(IGPSDialog.LONGITUD);
		}
		if (properties.get(IGPSDialog.LATITUD) != null) {
			latitud = properties.get(IGPSDialog.LATITUD);
		}
		if (properties.get(IGPSDialog.ALTURA) != null) {
			altitud = properties.get(IGPSDialog.ALTURA);
		}
		if(longitud.equals(omision)||latitud.equals(omision)||altitud.equals(omision)){
			txtGPSLATITUD.setText(latitud);
			txtGPSLONGITUD.setText(longitud);
			txtGPSALTITUD.setText(altitud);
		} else {
			boolean existeGPS = getCaratulaService().existeGPS(latitud, longitud);
			if (existeGPS) {
				ToastMessage.msgBox(getActivity(),
						"Punto GPS ya existente, intente tomar otro",
						ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_SHORT);
			}
			txtGPSLATITUD.setText(latitud);
			txtGPSLONGITUD.setText(longitud);
			txtGPSALTITUD.setText(altitud);
		}
		txtVI1A.requestFocus();
	}
	
	public INF_Caratula01Service getCaratulaService() {
		if (caratulaService == null) {
			caratulaService = INF_Caratula01Service.getInstance(getActivity());
		}
		return caratulaService;
	}
}
