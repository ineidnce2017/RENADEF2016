package gob.inei.renadef2016.fragments;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.DecimalField;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.ImageComponent;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.RadioGroupOtherField;
import gob.inei.dnce.components.SpinnerField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.service.CoberturaService;
import gob.inei.renadef2016.service.INF_Caratula01Service;

import java.util.Arrays;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;

// 
public class C3CARAT_Fragment_C2 extends FragmentForm {

	@FieldAnnotation(orderIndex = 1)
	public SpinnerField spnI11_1;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtI11_1_O;
	@FieldAnnotation(orderIndex = 3)
	public TextField txtI11_2;
	@FieldAnnotation(orderIndex = 4)
	public TextField txtI11_3;
	@FieldAnnotation(orderIndex = 5)
	public TextField txtI11_4;
	@FieldAnnotation(orderIndex = 6)
	public TextField txtI11_5;
	@FieldAnnotation(orderIndex = 7)
	public TextField txtI11_6;
	@FieldAnnotation(orderIndex = 8)
	public TextField txtI11_7;
	@FieldAnnotation(orderIndex = 9)
	public TextField txtI11_8;
	@FieldAnnotation(orderIndex = 10)
	public DecimalField txtI11_9;
	@FieldAnnotation(orderIndex = 11)
	public IntegerField txtI11_10;
	@FieldAnnotation(orderIndex = 12)
	public RadioGroupOtherField rgESTADO_ENVIO;
	
	private LabelComponent lblTitulo, lblTitulo1, lblTitulo2;
	private INF_Caratula01Service caratulaService;
	private CoberturaService coberturaService;
	private INF_Caratula01 caratula;
	private IntegerField txtDp, txtRt;
	private GridComponent2 grid_B, grid_A, grid_C, grid_E;
	private ImageComponent imgNT, imgNV;
	private LinearLayout ly;

	public C3CARAT_Fragment_C2() {
	}

	public C3CARAT_Fragment_C2 parent(MasterActivity parent) {
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
				.text(R.string.lb_C_10Direccion).textSize(21).centrar()
				.negrita();
		lblTitulo2 = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_C_ESTADO_ENVIO).textSize(21).centrar()
				.negrita();
		LabelComponent lblDp = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbDepPol).size(altoComponente+15, 300).centrar();
		LabelComponent lblRt = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lbRT).size(altoComponente+15, 220).centrar();
		
		LabelComponent lblTv = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_TipoVia).size(altoComponente, 210);
		LabelComponent lblTv_o = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_EspecifiqueVia).size(altoComponente, 210);
		LabelComponent lblNv = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_NombreVia).size(altoComponente, 210);
		LabelComponent lblRd = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_Referencia).size(altoComponente, 210);
		LabelComponent lblNp = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_NumeroPuerta).size(altoComponente, 210);
		LabelComponent lblBl = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_Block).size(altoComponente, 210);
		LabelComponent lblPi = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_Piso).size(altoComponente, 210);
		LabelComponent lblMz = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_Manzana).size(altoComponente, 210);
		LabelComponent lblLt = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_Lote).size(altoComponente, 210);
		LabelComponent lblKm = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_Km).size(altoComponente, 210);
		LabelComponent lblNt = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_Telefono).size(altoComponente, 210);
		
		LabelComponent lblEE = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_C_EstadoEnvio).size(altoComponente, 210);
		rgESTADO_ENVIO = new RadioGroupOtherField(getActivity(), R.string.lb_C_EstadoEnvio_1,
				R.string.lb_C_EstadoEnvio_2).size(altoComponente, 320).centrar(Gravity.CENTER)
				.callback("onCheckedChange_PEE");
		
		spnI11_1 = new SpinnerField(getActivity()).size(altoComponente + 15, 480).
				callback("on_TPViaChangeValue");
//		cargarSpinner();
		MyUtil.llenarItems(getActivity(), spnI11_1, R.array.arrayTiposVia);
		
		txtDp = new IntegerField(getActivity(), false).size(altoComponente, 250).readOnly().
				centrar().negrita();
		txtRt = new IntegerField(getActivity(), false).size(altoComponente, 250).readOnly().
				centrar().negrita();
		txtI11_1_O = new TextField(getActivity(), false).size(altoComponente, 400);
		txtI11_2 = new TextField(getActivity(), false).size(altoComponente, 400);
		txtI11_3 = new TextField(getActivity(), false).size(altoComponente, 400);
		txtI11_4 = new TextField(getActivity(), false).size(altoComponente, 220);
		txtI11_5 = new TextField(getActivity(), false).size(altoComponente, 220);
		txtI11_6 = new TextField(getActivity(), false).size(altoComponente, 220);
		txtI11_7 = new TextField(getActivity(), false).size(altoComponente, 220);
		txtI11_8 = new TextField(getActivity(), false).size(altoComponente, 220);
		txtI11_9 = new DecimalField(getActivity(), false).size(altoComponente, 220).alinearIzquierda();
		txtI11_10 = new IntegerField(getActivity(), false).size(altoComponente, 220).alinearIzquierda();
		
		imgNT = new ImageComponent(getActivity(), R.drawable.nophone, R.drawable.nophone).
				size(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		imgNV = new ImageComponent(getActivity(), R.drawable.document_edit, R.drawable.document_edit).
				size(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		imgNT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txtI11_10.setText("999999999");
				rgESTADO_ENVIO.requestFocus();
			}
		});
		
		imgNV.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txtI11_2.setText("SIN NOMBRE");
				txtI11_3.requestFocus();
			}
		});

		grid_C = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_C.addComponent(lblDp);
		grid_C.addComponent(txtDp);
		grid_C.addComponent(lblRt);
		grid_C.addComponent(txtRt);
		
		grid_A = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_A.addComponent(lblTv);
		grid_A.addComponent(spnI11_1);
		grid_A.addComponent(lblTv_o);
		grid_A.addComponent(txtI11_1_O);
		grid_A.addComponent(lblNv);
		grid_A.addComponent(txtI11_2);
		grid_A.addComponent(lblRd);
		grid_A.addComponent(txtI11_3);

		grid_B = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_B.addComponent(lblNp);
		grid_B.addComponent(txtI11_4);
		grid_B.addComponent(lblBl);
		grid_B.addComponent(txtI11_5);
		grid_B.addComponent(lblPi);
		grid_B.addComponent(txtI11_6);
		grid_B.addComponent(lblMz);
		grid_B.addComponent(txtI11_7);
		grid_B.addComponent(lblLt);
		grid_B.addComponent(txtI11_8);
		grid_B.addComponent(lblKm);
		grid_B.addComponent(txtI11_9);
		grid_B.addComponent(lblNt);
		grid_B.addComponent(txtI11_10);
		
		grid_E = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_E.addComponent(lblEE);
		grid_E.addComponent(rgESTADO_ENVIO);
		
		Filtros.setFiltro(txtI11_1_O, Filtros.TIPO.ALFAN_U, 50, null);
		Filtros.setFiltro(txtI11_2, Filtros.TIPO.ALFAN_U, 100, null);
		Filtros.setFiltro(txtI11_3, Filtros.TIPO.ALFAN_U, 100, null);
		Filtros.setFiltro(txtI11_4, Filtros.TIPO.ALFAN_U, 4, null);
		Filtros.setFiltro(txtI11_5, Filtros.TIPO.ALFAN_U, 5, null);
		Filtros.setFiltro(txtI11_6, Filtros.TIPO.ONLYOTHERS, 2, 0, new char[]{'1','2','3','4','5','A','M','S'}, 1, 5);
		Filtros.setFiltro(txtI11_7, Filtros.TIPO.ALFAN_U, 4, null);
		Filtros.setFiltro(txtI11_8, Filtros.TIPO.ALFAN_U, 4, null);
		Filtros.setFiltro(txtI11_9, Filtros.TIPO.DECIMAL, 7, 2, null, 0, 4500);
		Filtros.setFiltro(txtI11_10, Filtros.TIPO.NUMBER, 14, new char[]{'*', '#', '/'});
		
	}

	@Override
	protected View createUI() {
		buildFields();
		/* Aca creamos las preguntas */
		LinearLayout q0 = createQuestionSection(lblTitulo);
		LinearLayout q1 = createQuestionSection(grid_C.component());
		LinearLayout q2 = createQuestionSection(lblTitulo1);
//		LinearLayout q3 = createQuestionSection(grid_A.component());
		ly = createLy(LinearLayout.VERTICAL, Gravity.CENTER, imgNV);
		ly.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, Util.getTamañoEscalado(getActivity(), 200)));
		LinearLayout q3 = createQuestionSection(0, Gravity.CENTER, LinearLayout.HORIZONTAL, grid_A.component(), ly);
		LinearLayout ly1 = createLy(LinearLayout.VERTICAL, Gravity.BOTTOM, imgNT);
		ly1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, Util.getTamañoEscalado(getActivity(), 420)));
		LinearLayout q4 = createQuestionSection(0, Gravity.CENTER|Gravity.BOTTOM, LinearLayout.HORIZONTAL, grid_B.component(), ly1);
		LinearLayout q5 = createQuestionSection(lblTitulo2);
		LinearLayout q6 = createQuestionSection(grid_E.component());

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
			if(!getCaratulaService().saveOrUpdate(caratula, caratula.getSecCap(getListFields(this)))){
				ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
						ToastMessage.MESSAGE_ERROR,
						ToastMessage.DURATION_LONG);
			}
		} catch (Exception e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			return false;
		}
		
//		if(!getCaratulaService().grabarCaratula(caratula, 1, Utilidades.getListFields(this))){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
		
		return true;
	}
	
	public void on_TPViaChangeValue(FieldComponent component){
		if(((SpinnerField)component).getSelectedItemKey() == null) return;
		SeeRowTable((SpinnerField)component, txtI11_1_O, txtI11_2);
	}
	
	public void onCheckedChange_PEE(FieldComponent component){
		if(component == null) return;
		Log.e("valor", "valor: "+((RadioGroupOtherField)component).isPressed());
		if(((RadioGroupOtherField)component).isPressed())
			opcionEnvio(Integer.valueOf(2).equals(component.getValue()));
//		if(Integer.valueOf(2).equals(component.getValue())){
//			TaskExecute task = new TaskExecute<Void, Void, Boolean>(C3CARAT_Fragment_C2.this);
//			task.addCallback("opcionEnvio", (Object)true);
//			task.execute();
//		}
	}
	
	public void opcionEnvio(Boolean result) {
//		if(result){
//			String mensaje = "";
//			if(result){
//				List<String> listadoCoberturaDEL  = Arrays.asList(getResources().getStringArray(R.array.cobertura_array_DEL));
//				for(int x=0;x<listadoCoberturaDEL.size();x++){
//					if(!CoberturaActivity.cargarCoberturasDel(getCoberturaService(), App.getInstance().getComisaria().id_n, x).equals("COMPLETO.")){
//						result = false;
//						break;
//					}
//				}
//			}
//
//			if(!result){
//				if(mensaje.equals("")){
//					mensaje = "Inconsistencia; No puede Cerrar el Envio; Verifique su Cobertura. Desea hacerlo?";
//				}
//				new AlertDialog.Builder(getActivity())
//				.setTitle("Resumen Estado de Envio")
//				.setMessage(mensaje)
//				.setPositiveButton("Si", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						Intent i = new Intent(getActivity(), CoberturaActivity.class);
//						startActivity(i);
//					}
//				})
//				.setNegativeButton("No", null)
//				.show();
//			} 
//			caratula.estado_envio = result?2:1;
//			saveEstEnv();
//			if(!result) rgESTADO_ENVIO.setTagSelected("1");
//		} else {
//			caratula.estado_envio = 1;
//			saveEstEnv();
//			rgESTADO_ENVIO.setTagSelected("1");
//		}
	}
	
	private void saveEstEnv(){
		try {
			getCaratulaService().saveOrUpdate(caratula, caratula.getSecCap(Util.getListList("ESTADO_ENVIO")));
		} catch (Exception e) {
		}
	}
	
	private void SeeRowTable(SpinnerField spn, View focusSi, View focusNo){
		if(spn.getSelectedItemKey() == null || !spn.getSelectedItemKey().toString().equals("7")){
			grid_A.getFila(1).setVisibility(View.GONE);
			txtI11_1_O.setText("");
			if(ly!=null){
				LayoutParams lp = ly.getLayoutParams();
				lp.height = Util.getTamañoEscalado(getActivity(), 200);
				ly.setPadding(0, 0, 0, 0);
				ly.setLayoutParams(lp);
			}
			if(focusNo!=null) focusNo.requestFocus();
		} else {
			grid_A.getFila(1).setVisibility(View.VISIBLE);
			if(ly!=null){
				LayoutParams lp = ly.getLayoutParams();
				lp.height = Util.getTamañoEscalado(getActivity(), 260);
				ly.setPadding(0, 70, 0, 0);
				ly.setLayoutParams(lp);
			}
			if(focusSi!=null) focusSi.requestFocus();
		}
	}
	
	private void cargarSpinner() {
		Object[] keys = new Object[]{null, 1,2,3,4,5,6,7};
		
		String []items = getActivity().getResources().getStringArray(R.array.arrayTiposVia);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity(), R.layout.spinner_item, /*R.id.textview,*/ items);
		spnI11_1.setAdapterWithKey(adapter, Arrays.asList(keys));
	}

	private boolean validar() {
		
		if(Filtros.getErrorFiltro()!=null){
            ToastMessage.msgBox(getActivity(), Filtros.getErrorFiltro().getValue(),
                         ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
            Filtros.getErrorFiltro().getKey().requestFocus();
            return false;
		}
		error = false;
		if( spnI11_1.getSelectedItemKey() == null ){
			mensaje = "Debe ingresar el tipo de v\u00eda";
			view = spnI11_1;
			error = true;
		}
		else if(spnI11_1.getSelectedItemKey().toString().equals("7")){
			if(Util.esVacio(txtI11_1_O)){
				mensaje = "Debe especificar la v\u00eda";
				view = txtI11_1_O;
				error = true;
			}
		} 
		if(!error) {
			if( Util.esVacio(txtI11_2) ){
				mensaje = "Debe ingresar el nombre de la v\u00eda";
				view = txtI11_2;
				error = true;
			}
			else if( Util.esVacio(txtI11_3) ){
				mensaje = "Debe ingresar la referencia de la direcci\u00f3n";
				view = txtI11_3;
				error = true;
			}
			else if( Util.esVacio(txtI11_4) ){
				mensaje = "Debe ingresar el n\u00famero de la puerta";
				view = txtI11_4;
				error = true;
			}
			else if(Util.contDigits(txtI11_4.getText().toString())>0 &&
					Util.sumDigits(txtI11_4.getText().toString()) == 0){
				mensaje="Verificar datos de la direcci\u00f3n.";
				view = txtI11_4;
				error = true;	
			}
			else if( !Util.esVacio(txtI11_5) && Util.contDigits(txtI11_5.getText().toString())>0
					&& Util.sumDigits(txtI11_5.getText().toString()) == 0){
				mensaje="Verificar datos de la direcci\u00f3n.";
				view = txtI11_5;
				error = true;	
			}
			else if( Util.esVacio(txtI11_6) ){
				mensaje = "Debe ingresar el n\u00famero de piso";
				view = txtI11_6;
				error = true;
			}
			else {
				String text = txtI11_6.getText().toString();
				String []ar = new String[]{"1","2","3","4","5","A","S1","S2","M"};
				boolean flag = false;
				for(String t:ar){
					if(text.equals(t)) {
						flag = true;
						break;
					}
				}
				if(!flag){
					mensaje = "Piso no valido.";
					view = txtI11_6;
					error = true;
				}
			}
			if(!error){
				if(spnI11_1.getSelectedItemKey().toString().equals("5")){
					if(Util.esVacio(txtI11_9)){
						mensaje = "Si indica Carretera en Tipo de V\u00eda, tambien debe indicar Kilometro.";
						view = txtI11_9;
						error = true;
					} else if(Util.contDigits(txtI11_4.getText().toString())>0 &&
							Util.sumDigits(txtI11_9.getText().toString()) == 0){
						mensaje="Verificar datos de la direcci\u00f3n.";
						view = txtI11_9;
						error = true;	
					}
				}
				if(!error){
					if(!Util.esVacio(txtI11_8) && Util.esVacio(txtI11_7)) {
						mensaje = "Si indica información lote también debe indicar información de manzana.";
						view = txtI11_7;
						error = true;
					}
					else if(Util.esVacio(txtI11_8) && !Util.esVacio(txtI11_7)) {
						mensaje = "Si indica información manzana también debe indicar información de Lote.";
						view = txtI11_8;
						error = true;
					}
					else if(!Util.esVacio(txtI11_7) && Util.contDigits(txtI11_7.getText().toString())>0
							&& Util.sumDigits(txtI11_7.getText().toString()) == 0){
						mensaje="Verificar datos de la direcci\u00f3n.";
						view = txtI11_7;
						error = true;	
					}
					else if(!Util.esVacio(txtI11_8) && Util.contDigits(txtI11_8.getText().toString())>0
							&& Util.sumDigits(txtI11_8.getText().toString()) == 0){
						mensaje="Verificar datos de la direcci\u00f3n.";
						view = txtI11_8;
						error = true;	
					}
					else if( Util.esVacio(txtI11_10) ){
						mensaje = "Debe ingresar el numero de telefono";
						view = txtI11_10;
						error = true;
					} 
					else {
						String txt = txtI11_10.getText().toString();
						if(sumDigits(txt) == 0 || contDigits(txt) < 6){
							mensaje="Verificar informaci\u00f3n en el tel\u00e9fono.";
							view = txtI11_10;
							error = true;	
						}
					}
				}
			}
		}
		
		if(error) return false;
		return true;
	}
	
	private int sumDigits(String txt){
    	int sumtDigits = 0;
		for(char c : txt.toCharArray()){
			if(Character.isDigit(c)) sumtDigits+=Integer.parseInt(""+c);
		}
		return sumtDigits;
    }
	
	private int contDigits(String txt){
    	int conttDigits = 0;
		for(char c : txt.toCharArray()){
			if(Character.isDigit(c)) conttDigits++;
		}
		return conttDigits;
    }

	@Override
	public void cargarDatos() {
		caratula = App.getInstance().getComisaria();
		txtDp.setText(App.getInstance().getMarco().getId_n());
		txtRt.setText(App.getInstance().getUsuario().ruta);
		entityToUI(caratula);
		inicio();
	}

	private void inicio() {
		spnI11_1.requestFocus();
		SeeRowTable(spnI11_1,null, null);
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
	
	public CoberturaService getCoberturaService() {
		if (coberturaService == null) {
			coberturaService = CoberturaService.getInstance(getActivity());
		}
		return coberturaService;
	}
}
