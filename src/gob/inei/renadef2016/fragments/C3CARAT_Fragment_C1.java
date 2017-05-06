package gob.inei.renadef2016.fragments;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.service.INF_Caratula01Service;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3CARAT_Fragment_C1 extends FragmentForm {

	@FieldAnnotation(orderIndex = 1)
	public TextField txtNOMREG;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtNOMBREDD;
	@FieldAnnotation(orderIndex = 3)
	public TextField txtNOMBREPP;
	@FieldAnnotation(orderIndex = 4)
	public TextField txtNOMBREDI;
	@FieldAnnotation(orderIndex = 5)
	public TextField txtNOMBRECP;
	@FieldAnnotation(orderIndex = 6)
	public TextField txtZONA;
//	@FieldAnnotation(orderIndex = 7)
//	public TextField txtZONALF;
	@FieldAnnotation(orderIndex = 7)
	public TextField txtMZA;
//	@FieldAnnotation(orderIndex = 9)
//	public TextField txtMZNALF;
//	@FieldAnnotation(orderIndex = 10)
//	public IntegerField txtFRENTE;
	@FieldAnnotation(orderIndex = 8)
	public IntegerField txtAER;
//	@FieldAnnotation(orderIndex = 12)
//	public IntegerField txtAER_FIN;
	
	private LabelComponent lblTitulo, lblTitulo1, lblTitulo2, lblTitulo3;
	private IntegerField txtDp, txtRt, txtDa;
	private INF_Caratula01Service caratulaService;
	private INF_Caratula01 caratula;
	private GridComponent2 grid_B, grid_A, grid_C;

	public C3CARAT_Fragment_C1() {
	}

	public C3CARAT_Fragment_C1 parent(MasterActivity parent) {
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
		initObjectsWithoutXML(this, rootView);
		enlazarCajas();
		listening();
	
		return rootView;
	}

	@Override
	protected void buildFields() {
		
		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.app_fullname).textSize(21).centrar()
				.negrita();
		lblTitulo1 = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_I_IDENTIFICACION).textSize(21).centrar()
				.negrita();
		lblTitulo2 = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_UbicacionGeografica).textSize(21).centrar()
				.negrita();
		lblTitulo3 = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_UbicacionMuestral).textSize(21).centrar()
				.negrita();
		
		LabelComponent lblDp = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbDepPol).size(altoComponente+15, 300).centrar();
		LabelComponent lblRt = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbRT).size(altoComponente+15, 220).centrar();
		LabelComponent lblda = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbDA).size(altoComponente+15, 220).centrar();
		
//		LabelComponent lblReg = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				text(R.string.lbRegion).size(altoComponente, 220);
		LabelComponent lblDep = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbDepartamento).size(altoComponente, 220);
		LabelComponent lblProv = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbProvincia).size(altoComponente, 220);
		LabelComponent lblDis = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbDistrito).size(altoComponente, 220);
		LabelComponent lblCep = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbCentroPoblado).size(altoComponente, 220);
		
		txtDp = new IntegerField(getActivity(), false).size(altoComponente, 250).readOnly().
				centrar().negrita();
		txtRt = new IntegerField(getActivity(), false).size(altoComponente, 250).readOnly().
				centrar().negrita();
		txtDa = new IntegerField(getActivity(), false).size(altoComponente, 250).readOnly().
				centrar().negrita();
		txtNOMREG = new TextField(getActivity(), false).size(altoComponente, 400).readOnly();
		txtNOMBREDD = new TextField(getActivity(), false).size(altoComponente, 400).readOnly();
		txtNOMBREPP = new TextField(getActivity(), false).size(altoComponente, 400).readOnly();
		txtNOMBREDI = new TextField(getActivity(), false).size(altoComponente, 400).readOnly();
		txtNOMBRECP = new TextField(getActivity(), false).size(altoComponente, 400);

		grid_C = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_C.addComponent(lblDp);
		grid_C.addComponent(txtDp);
		grid_C.addComponent(lblRt);
		grid_C.addComponent(txtRt);
		grid_C.addComponent(lblda);
		grid_C.addComponent(txtDa);
		
		grid_A = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
//		grid_A.addComponent(lblReg);
//		grid_A.addComponent(txtNOMREG);
		grid_A.addComponent(lblDep);
		grid_A.addComponent(txtNOMBREDD);
		grid_A.addComponent(lblProv);
		grid_A.addComponent(txtNOMBREPP);
		grid_A.addComponent(lblDis);
		grid_A.addComponent(txtNOMBREDI);
		grid_A.addComponent(lblCep);
		grid_A.addComponent(txtNOMBRECP);
		
		LabelComponent lblZon = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbZona).size(altoComponente, 220);
//		LabelComponent lblZonAlf = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				text(R.string.lbZona_alf).size(altoComponente, 220);
		LabelComponent lblMan = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbManzana).size(altoComponente, 220);
//		LabelComponent lblManAlf = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				text(R.string.lbManzana_alf).size(altoComponente, 220);
//		LabelComponent lblFre = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				text(R.string.lbFrente).size(altoComponente, 220);
		LabelComponent lblAIn = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbAer).size(altoComponente, 220);
//		LabelComponent lblAFi = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
//				text(R.string.lbAer).size(altoComponente, 220);
		
		txtZONA = new TextField(getActivity(), false).size(altoComponente, 120).alinearIzquierda()/*.
				completarCaracteres(3,"0",DIRECCION_COMPLETAR.IZQUIERDA)*/;
//		txtZONALF = new TextField(getActivity(), false).size(altoComponente, 120);
		txtMZA = new TextField(getActivity(), false).size(altoComponente, 120).alinearIzquierda()/*.
				completarCaracteres(3,"0",DIRECCION_COMPLETAR.IZQUIERDA)*/;
//		txtMZNALF = new TextField(getActivity(), false).size(altoComponente, 120);
//		txtFRENTE = new IntegerField(getActivity(), false).size(altoComponente, 467).alinearIzquierda();
		txtAER = new IntegerField(getActivity(), false).size(altoComponente, 170).alinearIzquierda()/*.
				completarCaracteres(3,"0",DIRECCION_COMPLETAR.IZQUIERDA)*/;
//		txtAER_FIN = new IntegerField(getActivity(), false).size(altoComponente, 467).alinearIzquierda().
//				completarCaracteres(3,"0",DIRECCION_COMPLETAR.IZQUIERDA);

//		grid_B = new GridComponent2(getActivity(), 4, false).colorFondo(R.color.blanco);
//		grid_B.addComponent(lblZon);
//		grid_B.addComponent(txtZONA);
//		grid_B.addComponent(lblZonAlf);
//		grid_B.addComponent(txtZONALF);
//		grid_B.addComponent(lblMan);
//		grid_B.addComponent(txtMZA);
//		grid_B.addComponent(lblManAlf);
//		grid_B.addComponent(txtMZNALF);
//		grid_B.addComponent(lblFre);
//		grid_B.addComponent(txtFRENTE,3);
//		grid_B.addComponent(lblAIn);
//		grid_B.addComponent(txtAER,3);
//		grid_B.addComponent(lblAFi);
//		grid_B.addComponent(txtAER_FIN,3);
		
		grid_B = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_B.addComponent(lblZon);
		grid_B.addComponent(txtZONA);
		grid_B.addComponent(lblMan);
		grid_B.addComponent(txtMZA);
		grid_B.addComponent(lblAIn);
		grid_B.addComponent(txtAER);
		
		Filtros.setFiltro(txtNOMBRECP, Filtros.TIPO.ALFAN_U, null);
//		Filtros.setFiltro(txtZONA, Filtros.TIPO.NUMBER, 4,0, null,1,140,-1,3,-1,3,3);
		Filtros.setFiltro(txtZONA, Filtros.TIPO.ALFAN_U, 4,0, null,1,140,-1,3,1,3,4);
//		Filtros.setFiltro(txtZONALF, Filtros.TIPO.ALFA_U, 1, null);
//		Filtros.setFiltro(txtMZA, Filtros.TIPO.NUMBER, 3, 0,null,1,99,-1,3,-1,3,3);
		Filtros.setFiltro(txtMZA, Filtros.TIPO.ALFAN_U, 4, 0,null,1,999,-1,3,1,3,4);
//		Filtros.setFiltro(txtMZNALF, Filtros.TIPO.ALFA_U, 1, null);
//		Filtros.setFiltro(txtFRENTE, Filtros.TIPO.NUMBER, 2, 0, null,1,99);
		Filtros.setFiltro(txtAER, Filtros.TIPO.NUMBER, 9, 0, new char[]{'-'}, 1,999999999);
//		Filtros.setFiltro(txtAER, Filtros.TIPO.NUMBER, 3, 0, null, 1,999,-1,3,-1,3,3);
//		Filtros.setFiltro(txtAER_FIN, Filtros.TIPO.NUMBER, 3, 0,null, 1,999,-1,3,-1,3,3);
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q1 = createQuestionSection(grid_C.component());
		LinearLayout q2 = createQuestionSection(lblTitulo1);
		LinearLayout q3 = createQuestionSection(lblTitulo2);
		LinearLayout q4 = createQuestionSection(grid_A.component());
		LinearLayout q5 = createQuestionSection(lblTitulo3);
		LinearLayout q6 = createQuestionSection(grid_B.component());
//		q4 = createQuestionSection(lblTitulo2);
//		q5 = createQuestionSection(grid_Fun.component());

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
		form.addView(q6);
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
			if(!getCaratulaService().saveOrUpdate(caratula, caratula.getSecCap(getListFields(this, 
					new String[]{"REG", "CCDD", "CCPP", "CCDI", "CCCP"})))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			}
		} catch (Exception e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			return false;
		}
		
		return true;
	}

	private boolean validar() {

		error = false;
		
		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}

//		if( Util.esVacio(txtNOMBRECP) ){
//			mensaje = "Debe ingresar el nombre del centro poblado.";
//			view = txtNOMBRECP;
//			error = true;
//		}
		if(!Util.esVacio(txtZONA)){
			if(Util.esVacio(txtMZA) ){
				mensaje = "Si registr\u00f3 Zona, tambien debe registrar Manzana.";
				view = txtMZA;
				error = true;
			} 
//			else if(Util.esVacio(txtFRENTE) ){
//				mensaje = "Si registr\u00f3 Zona, tambien debe registrar Frente.";
//				view = txtFRENTE;
//				error = true;
//			} 
			else if(!Util.esVacio(txtAER) ){
				mensaje = "Si registr\u00f3 Zona, no debe registrar AER.";
				view = txtAER;
				error = true;
			} 
//			else if(!Util.esVacio(txtAER_FIN) ){
//				mensaje = "Si registr\u00f3 Zona, no debe registrar AER_FIN.";
//				view = txtAER_FIN;
//				error = true;
//			}
		}
		else if( !Util.esVacio(txtMZA) && Util.esVacio(txtZONA) ){
			mensaje = "Si registr\u00f3 Mza, tambien debe registrar Zona";
			view = txtZONA;
			error = true;
		}
//		else if( Util.esVacio(txtZONA) && Util.esVacio(txtAER) ){
//			mensaje = "AER y Zona no pueden quedar en blanco a la vez";
//			view = txtZONA;
//			error = true;
//		}

		if(error) return false;
		return true;
	}

	@Override
	public void cargarDatos() {
		caratula = App.getInstance().getComisaria();
		txtDp.setText(App.getInstance().getMarco().getId_n());
		txtRt.setText(Util.getText(App.getInstance().getUsuario().ruta, "00"));
		txtDa.setText(App.getInstance().getMarco().cod_dep_asig);
		entityToUI(caratula);
		inicio();
	}

	private void inicio() {
		txtNOMBRECP.requestFocus();
	}
	
	@Override
	public boolean getSaltoNavegation() {
		return validar();
	}
	
	public INF_Caratula01Service getCaratulaService() {
		if (caratulaService == null) {
			caratulaService = INF_Caratula01Service.getInstance(getActivity());
		}
		return caratulaService;
	}
}
