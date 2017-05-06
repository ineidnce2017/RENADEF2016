package gob.inei.renadef2016.fragments.dialog;

import gob.inei.dnce.annotations.FieldAnnotation;
import gob.inei.dnce.components.ButtonComponent;
import gob.inei.dnce.components.DateTimeField;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.DialogFragmentComponent;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.GridComponent2;
import gob.inei.dnce.components.IntegerField;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.components.SpinnerField;
import gob.inei.dnce.components.TextField;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.FieldComponent;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Caretaker;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil;
import gob.inei.renadef2016.fragments.C3VISITAS_Fragment_S3;
import gob.inei.renadef2016.fragments.C3VISITAS_Fragment_S3.ACCION;
import gob.inei.renadef2016.modelo.DelVisita;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.service.ATVisitaService;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class C3VISITAS_Fragment_S3_01 extends DialogFragmentComponent implements Respondible {
	public interface C1CAP200_Fragment_201_01Listener {
		void onFinishEditDialog(String inputText);
	}

	public DateTimeField txtIII_2;
	public IntegerField txtIII_2_D;
	public IntegerField txtIII_2_M;
	public IntegerField txtIII_2_A;
	public DateTimeField txtIII_3A;
	public IntegerField txtIII_3A_IH;
	public IntegerField txtIII_3A_IM;
	public DateTimeField txtIII_3B;
	public IntegerField txtIII_3B_FH;
	public IntegerField txtIII_3B_FM;
	@FieldAnnotation(orderIndex = 1)
	public SpinnerField spnIII_5;
	@FieldAnnotation(orderIndex = 2)
	public TextField txtIII_5_O;
	public DateTimeField txtIII_4A;
	public IntegerField txtIII_4A_D;
	public IntegerField txtIII_4A_M;
	public IntegerField txtIII_4A_A;
	public DateTimeField txtIII_4B;
	public IntegerField txtIII_4B_H;
	public IntegerField txtIII_4B_M; 
	@FieldAnnotation(orderIndex = 3) 
	public ButtonComponent btnAceptar;
	@FieldAnnotation(orderIndex = 4)
	public ButtonComponent btnCancelar;
	private static C3VISITAS_Fragment_S3 caller;
	LinearLayout q0, q1, q2, q3, q4, q5;
	private DelVisita detalle;
	private LabelComponent lblTitulo, lblSecProxV;
	private LabelComponent lblF, lblHD, lblHA, lblR, lblPVF, lblPVH;
	private GridComponent2 grid_F, grid_PV;
	private ATVisitaService atvisitaService;
	private INF_Caratula01 caratula;
	private DialogComponent dlg;
	private Integer resp;
	private ACCION accion;

	public static C3VISITAS_Fragment_S3_01 newInstance(FragmentForm pagina,
			DelVisita detalle, ATVisitaService atVisitaService, C3VISITAS_Fragment_S3.ACCION accion) {
		caller = (C3VISITAS_Fragment_S3) pagina;
		Filtros.clear();
		C3VISITAS_Fragment_S3_01 f = new C3VISITAS_Fragment_S3_01();
		f.setParent(pagina);
		f.atvisitaService = atVisitaService;
		f.accion = accion;
		Bundle args = new Bundle();
		args.putSerializable("detalle", detalle);
		f.setArguments(args);
		return f;
	}

	public C3VISITAS_Fragment_S3_01() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detalle = (DelVisita) getArguments().getSerializable("detalle");
		caretaker = new Caretaker<Entity>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = createUI();
		getDialog().setTitle("Visita N\u00B0: "+detalle.iii_1);
		initObjectsWithoutXML(this, rootView);

		cargarDatos();
		enlazarCajas(this);
		listening();
		
		return rootView;

	}

	private void cargarDatos() {
		entityToUI(detalle);
		caretaker.addMemento("antes", detalle.saveToMemento(DelVisita.class));
		inicio();
	}

	private void inicio() {
		resp = detalle.iii_5;
		if(accion == ACCION.ADD){
			grid_F.hideColumn(2);
			caller.cleanView(txtIII_3B);
			q3.setVisibility(View.GONE);
			q4.setVisibility(View.GONE);
			q5.setVisibility(View.GONE);
			btnAceptar.requestFocus();
		} else if(accion == ACCION.EDIT){
			spnIII_5.requestFocus();
			if(!((Object)2).equals(spnIII_5.getSelectedItemKey())){
				q4.setVisibility(View.GONE);
				q5.setVisibility(View.GONE);
			}
			if(!((Object)6).equals(spnIII_5.getSelectedItemKey()))
				txtIII_5_O.setVisibility(View.GONE);
		} else if(accion == ACCION.FINISH){
			spnIII_5.requestFocus();
			txtIII_5_O.setVisibility(View.GONE);
			q4.setVisibility(View.GONE);
			q5.setVisibility(View.GONE);
		}
		txtIII_2.readOnly();
		txtIII_3A.readOnly();
		txtIII_3B.readOnly();
	}

	@Override
	protected View createUI() {
		buildFields();

		q0 = createQuestionSection(lblTitulo);
		q2 = createQuestionSection(grid_F.component());
		q3 = createQuestionSection(createLayout());
		q4 = createQuestionSection(lblSecProxV);
		q5 = createQuestionSection(grid_PV.component());

		LinearLayout botones = createButtonSection(btnAceptar, btnCancelar);

		ScrollView contenedor = createForm();
		LinearLayout form = (LinearLayout) contenedor.getChildAt(0);

		form.addView(q0);
		form.addView(q2);
		form.addView(q3);
		form.addView(q4);
		form.addView(q5);
		form.addView(botones);

		return contenedor;
	}
	
	private LinearLayout createLayout(){
		LinearLayout ly = createLy(LinearLayout.HORIZONTAL, Gravity.CENTER, lblR, spnIII_5);
		return createLy(LinearLayout.VERTICAL, Gravity.CENTER, ly, txtIII_5_O);
	}

	@Override
	protected void buildFields() {
		int title = (accion == ACCION.ADD ? R.string.lb_V_Iniciar : (accion == ACCION.EDIT ?
				R.string.lb_V_Editar : R.string.lb_V_Terminar));
		lblTitulo = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(title).textSize(21).centrar().negrita();
		lblSecProxV = new LabelComponent(this.getActivity(),
				R.style.btnStyleHeaderGreen).size(MATCH_PARENT, MATCH_PARENT)
				.text(R.string.lb_V_Proxima).textSize(21).centrar()
				.negrita();

		lblF = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Fecha_m).size(altoComponente, 170).centrar();
		lblHD = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Hora_m).size(altoComponente, 170).centrar();
		lblHA = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Hora_m_a).size(altoComponente, 170).centrar();
		lblR = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Resultado_m).size(altoComponente, 170);
		lblPVF = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Fecha_m).size(altoComponente, 170);
		lblPVH = new LabelComponent(getActivity(), R.style.btnStyleHeaderGreen).
				text(R.string.lb_V_Hora_m_p).size(altoComponente, 170);
		
		txtIII_2_D = new IntegerField(getActivity());
		txtIII_2_M = new IntegerField(getActivity());
		txtIII_2_A = new IntegerField(getActivity());
		txtIII_3A_IH = new IntegerField(getActivity());
		txtIII_3A_IM = new IntegerField(getActivity());
		txtIII_3B_FH = new IntegerField(getActivity());
		txtIII_3B_FM = new IntegerField(getActivity());
		
		txtIII_4A_D = new IntegerField(getActivity());
		txtIII_4A_M = new IntegerField(getActivity());
		txtIII_4A_A = new IntegerField(getActivity());
		txtIII_4B_H = new IntegerField(getActivity());
		txtIII_4B_M = new IntegerField(getActivity());
		
		txtIII_2 = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA, true).
				size(altoComponente, 200).dateOrhour(txtIII_2_D, txtIII_2_M, txtIII_2_A).
				setRangoDate("01/01/2017", "31/12/2017");
		txtIII_3A = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.HORA, true).
				size(altoComponente, 200).dateOrhour(txtIII_3A_IH, txtIII_3A_IM, null);
		txtIII_3B = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.HORA, true).
				size(altoComponente, 200).dateOrhour(txtIII_3B_FH, txtIII_3B_FM, null);
		txtIII_4A = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.FECHA).
				size(altoComponente, 200).dateOrhour(txtIII_4A_D, txtIII_4A_M, txtIII_4A_A).
				setRangoYear(2017, 2017);
		txtIII_4B = new DateTimeField(getActivity(), DateTimeField.TIPO_DIALOGO.HORA).
				size(altoComponente, 200).dateOrhour(txtIII_4B_H, txtIII_4B_M, null);
		txtIII_5_O = new TextField(getActivity()).size(altoComponente, 520);

		grid_F = new GridComponent2(getActivity(), 3).colorFondo(R.color.blanco);
		grid_F.addComponent(lblF);
		grid_F.addComponent(lblHD);
		grid_F.addComponent(lblHA);
		grid_F.addComponent(txtIII_2);
		grid_F.addComponent(txtIII_3A);
		grid_F.addComponent(txtIII_3B);
		
		spnIII_5 = new SpinnerField(getActivity()).size(
				altoComponente + 15, 350).callback("on_RVISITAChangeValue");
		MyUtil.llenarItems(getActivity(), spnIII_5, R.array.arrayResultadosDE);
		
		grid_PV = new GridComponent2(getActivity(), 2).colorFondo(R.color.blanco);
		grid_PV.addComponent(lblPVF);
		grid_PV.addComponent(txtIII_4A);
		grid_PV.addComponent(lblPVH);
		grid_PV.addComponent(txtIII_4B);

		btnAceptar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnAceptar).size(200, 60);
		btnCancelar = new ButtonComponent(getParent().getActivity(), App.ESTILO_BOTON).text(
				R.string.btnCancelar).size(200, 60);
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				C3VISITAS_Fragment_S3_01.this.dismiss();
			}
		});
		btnAceptar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean flag = grabar();
				if (!flag) {
					return;
				}
				C3VISITAS_Fragment_S3_01.this.dismiss();
			}
		});
		
		txtIII_4A.setFocusOnDissmis(txtIII_4B);
		txtIII_4B.setFocusOnDissmis(btnAceptar);
	}
	
	public void on_RVISITAChangeValue(FieldComponent component){
		String resultadoStr = (String) component.getValue();
		if (resultadoStr.substring(0, 1).equals("6")) {
			caller.lockView(false, txtIII_5_O);
			txtIII_5_O.setVisibility(View.VISIBLE);
			txtIII_5_O.requestFocus();
		} else {
			caller.cleanAndLockView(txtIII_5_O);
			txtIII_5_O.setVisibility(View.GONE);
		}
		
		if (resultadoStr.substring(0, 1).equals("2") || resultadoStr.substring(0, 1).equals("4")) {
			q4.setVisibility(View.VISIBLE);
			q5.setVisibility(View.VISIBLE);
			txtIII_4A.requestFocus();
		} else {
			if(q4.getVisibility() == View.VISIBLE){
				caller.cleanView(txtIII_4A, txtIII_4B);
				q4.setVisibility(View.GONE);
				q5.setVisibility(View.GONE);
			}
		}
	}

	private boolean validar() {
		String mensaje = "";
		boolean error = false;
		View view = null;
		
		if(accion == ACCION.ADD){
			if(!txtIII_2.isInRange()) return false;
		}
		else if(accion != ACCION.ADD){
			
			int _visAnt2 = 0;
			if(caller.lstData.size()>0){
				for(DelVisita _v:caller.lstData){
					if(detalle.iii_1!=null && detalle.iii_1.equals(_v.iii_1)) continue;
					if(Integer.valueOf(2).equals(_v.iii_5)) _visAnt2++;
				}
			}
			
			if( spnIII_5.getSelectedItemKey() == null ){
				mensaje = "Debe indicar el resultado de la visita";
				error = true;
				view = spnIII_5;
			}
			if(!error){
				caratula = App.getInstance().getComisaria();
				int _res = Integer.parseInt(spnIII_5.getSelectedItemKey().toString());
				if((_res == 3 ) && caller.lstData.size() > 1){
					mensaje = "Resultado 3 solo puede estar en la 1era visita.";
					error = true;
					view = spnIII_5;
				}
				else if(( _res == 3 || _res == 6) &&
						(_visAnt2 > 0)){
					mensaje = "Resultado no puede ser 3 \u00f3 6.";
					error = true;
					view = spnIII_5;
				}
//				else if((_res == 3 || _res == 6) && caratula.v3_1 != null ){
//					mensaje = "Tiene informacion en el Cap. 100 no puede poner resultado 3 \u00f3 6.";
//					error = true;
//					view = spnIII_5;
//				}
				else if((_res == 3 || _res == 5 || _res == 6) && caratula.v3_1 != null){
					if(C3VISITAS_Fragment_S3.c100!=null){
						if(!(Integer.valueOf(0).equals(C3VISITAS_Fragment_S3.c100.total_delitos) && 
								Integer.valueOf(0).equals(C3VISITAS_Fragment_S3.c100.dn121) && 
								Integer.valueOf(0).equals(C3VISITAS_Fragment_S3.c100.total_faltas))){
							mensaje = "Tiene informacion en el Cap. 100 no puede poner resultado 3,5 \u00f3 6.";
							error = true;
							view = spnIII_5;
						}
					}
				}
				if(!error){
					if( _res == 1 ) return true;
//					if((_res == 3 || _res == 4 ) && caller.lstData.size() == 1 && caratula.v3_1 != null){
//						mensaje = "Tiene informacion en el Cap. 100 no puede poner resultado 3 \u00f3 4.";
//						error = true;
//						view = spnIII_5;
//					}
					/*else*/ if( _res == 1 ){
//						CoberturaService cs = CoberturaService.getInstance(getActivity());
////						if(Integer.valueOf(1).equals(App.currentMarco.getVdeli())){
//							List<String> listadoCobertura = Arrays.asList(getActivity().getResources().getStringArray(R.array.cobertura_array_DEL));
//							caratula.ivresfin_03 = 1;
//							try {
//								caratulaService.saveOrUpdate(caratula, caratula.getSecCap(Util.getListList("IVRESFIN_03")));
//							} catch (Exception e) {
//							}
////							caratulaService.grabarCaratula(caratula,1,Utilidades.getList("IVRESFIN_03"));
//							for(int x=0;x<listadoCobertura.size();x++){
//								if(!CoberturaActivity.cargarCoberturasDel(cs, App.getInstance().getComisaria().id_n, x).equals("COMPLETO.")){
//										mensaje = "Inconsistencia; No puede Completar la visita con resultado 1; Verifique su Cobertura. Desea hacerlo?";		
//										new AlertDialog.Builder(getActivity())
//										.setTitle("Resumen...")
//										.setMessage(mensaje)
//										.setPositiveButton("Si", new DialogInterface.OnClickListener() {
//											@Override
//											public void onClick(DialogInterface dialog, int which) {
//												Intent i = new Intent(caller.getActivity(), CoberturaActivity.class);
//												i.putExtra("key", "_visErrorDEL");
//												caller.startActivity(i);
//											}
//										})
//										.setNegativeButton("No", null)
//										.show();
//										return false;
//									} 
//								}
////							}
						return true;
					}
					else if( _res == 6 && Util.esVacio(txtIII_5_O) ) {
						mensaje = "El resultado final debe ser especificado";
						error = true;
						view = txtIII_5_O;
					}
					else if( !(_res == 6) && !Util.esVacio(txtIII_5_O) ) {
						mensaje = "El resultado final no debe ser especificado";
						error = true;
						view = txtIII_5_O;
					}
					
					else {
						if(!Util.esVacio(txtIII_3A_IH) && !Util.esVacio(txtIII_3A_IM) && 
								!Util.esVacio(txtIII_3B_FH) && !Util.esVacio(txtIII_3B_FM)){
							String hora1 = txtIII_3A.getStringValue();
							String hora2 = txtIII_3B.getStringValue();
							if(Util.compareTime(hora1, hora2, "HHmm")>=0){
								mensaje="Hora de Inicio no puede ser menor o igual a la Hora de final de visita.";
								error=true;	
							}
						}
						if(!error){
							int _resF = -1;
							if(_res == 2 || _res == 4){
								if(Util.esVacio(txtIII_4A) || Util.esVacio(txtIII_4B)){
									mensaje = "Proxima visita no puede estar vacia.";
									view = Util.esVacio(txtIII_4A)?txtIII_4A:txtIII_4B;
									error = true;
								}
								else if( (!Util.esVacio(txtIII_4A_D) 
										&& Util.esVacio(txtIII_4B_H)) || 
										(Util.esVacio(txtIII_4A_D) && !Util.esVacio(txtIII_4B_H) )){
									mensaje = "Si registra una pr\u00f3xima visita entonces debe indicar la fecha y hora";
									view = txtIII_4A_D;
									error = true;
								}
								else if(!Util.esVacio(txtIII_4A) && !Util.esVacio(txtIII_2)) {
									String fecha1 = txtIII_4A.getStringValue();
									String fecha2 = txtIII_2.getStringValue();
									if((_resF=Util.compareDate(fecha1, fecha2, "ddMMyyyy"))<0){
										mensaje="Fecha de PV no puede ser menor a la fecha de inicio de visita.";
										view=txtIII_4A;
										error=true;	
									}
								}
							}
							if(!error){
								if(_resF==0 && !Util.esVacio(txtIII_4B_H) && !Util.esVacio(txtIII_3B)) {
									String hora1 = txtIII_4B.getStringValue();
									String hora2 = txtIII_3B.getStringValue();
									if(Util.compareTime(hora1, hora2, "HHmm")<=0){
										mensaje="Hora de PV no puede ser menor o igual a la Hora de fin de visita.";
										view=txtIII_4B;
										error=true;	
									}
								}
							}
						}
					}
				}
				
				if(!error){
					if( Util.esVacio(txtIII_4A_D) && Util.esVacio(txtIII_4B_H) ){
						return true;
					}
				}
			}
		}
		
		if(error){
			if(!mensaje.equals("")) ToastMessage.msgBox(this.getActivity(), mensaje,
					ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
			if(view != null) view.requestFocus();
			return false;
		}

		return true;
	}

	private boolean grabar() {
		if (!validar()) {
			if (error) {
				if (!mensaje.equals("")) {
					ToastMessage.msgBox(this.getActivity(), mensaje,
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
				}
				if (view != null) {
					view.requestFocus();
				}
			}
			return false;
		}
		
		uiToEntity(detalle);
		try {
			boolean flag = true;
			if(accion != ACCION.ADD){
				Integer resfin = null;
				if((resfin = Util.getInt(caller.grabarCaratulaCobertura(detalle.iii_5,2)))!=2) {
					CoberturaDialog c = checkCobertura();
					if(!c.isResultCobertura()){
						dlg = new DialogComponent(getActivity(),
								this, DialogComponent.TIPO_DIALOGO.YES_NO, getResources().getString(R.string.app_name), 
								"No puede Terminar su visita con Resultado: "+detalle.iii_5+"; "
										+ "Siendo su Resultado Final: ["+resfin+"]; Por favor Verifique su Cobertura."
										+ " Desea Continuar?");
						caller.grabarCaratulaCobertura(resp);
						dlg.put("objeto", c);
						dlg.showDialog();
						flag = false;
					} 
				}
			}
			if(!flag) detalle.restoreFromMemento(caretaker.get("antes"));
			if(flag){
				if(!atvisitaService.saveOrUpdate(detalle, detalle.getSecCap(caller.getListFields(this)))){
					ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
							ToastMessage.MESSAGE_ERROR,
							ToastMessage.DURATION_LONG);
				} else {
					caller.reloadData(detalle, 1);
					caller.grabarCaratula();
				}
			}
		} catch (Exception e) {
			ToastMessage.msgBox(this.getActivity(), e.getMessage(),
					ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
			return false;
		}
		
//		uiToEntity(detalle);
//		if(!atvisitaService.guardarVisita(detalle, Utilidades.getListFields(this, new String[]{"III2_1"}))){
//			ToastMessage.msgBox(this.getActivity(), "Los datos no se guardaron",
//					ToastMessage.MESSAGE_ERROR,
//					ToastMessage.DURATION_LONG);
//		}
//		else {
//			caller.reloadData(detalle, 1);
//			caller.grabarCaratula();
//			
//			if(accion != ACCION.ADD){
//				new AlertDialog.Builder(getActivity())
//				.setTitle("Resumen Cobertura")
//				.setMessage("La Visita ha sido Terminada. \u00bfDesea ir a la Cobertura?")
//				.setPositiveButton("Si", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						Intent i = new Intent(caller.getActivity(), CoberturaActivity.class);
//						i.putExtra("key", "_visChecKDEL");
//						caller.startActivity(i);
//					}
//				})
//				.setNegativeButton("No", null)
//				.show();
//			}
//		}
		
		return true;
	}
	
	private CoberturaDialog checkCobertura(){
		CoberturaDialog aperturaDialog = CoberturaDialog
				.newInstance(caller, FragmentForm.OPCION.ONE);
		aperturaDialog.setAncho(LinearLayout.LayoutParams.MATCH_PARENT);
		aperturaDialog.inicio(false);
		return aperturaDialog;
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccept() {
		CoberturaDialog c = (CoberturaDialog)dlg.get("objeto");
		FragmentManager fm = caller.getFragmentManager();
		c.show(fm, "aperturaDialog");
	}
}
