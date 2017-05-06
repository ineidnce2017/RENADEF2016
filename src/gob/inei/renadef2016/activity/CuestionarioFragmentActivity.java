package gob.inei.renadef2016.activity;

import gob.inei.dnce.adapter.DepthPageTransformer;
import gob.inei.dnce.adapter.MyFragmentPagerAdapter;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.DialogComponent.TIPO_DIALOGO;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.components.FragmentViewPager;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.components.ToastMessage;
import gob.inei.dnce.interfaces.Respondible;
import gob.inei.dnce.util.Filtros;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.R;
import gob.inei.renadef2016.adapter.MyDrawerAdapter;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.controller.Importacion;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_101_A;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_101_F;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_102;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_105;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_108;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_113;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_117;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_117_1;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_117_2;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_122;
import gob.inei.renadef2016.fragments.C3CAP200_Fragment_201;
import gob.inei.renadef2016.fragments.C3CAP300_Fragment_301;
import gob.inei.renadef2016.fragments.C3CAP400_Fragment_401;
import gob.inei.renadef2016.fragments.C3CARAT_Fragment_C1;
import gob.inei.renadef2016.fragments.C3CARAT_Fragment_C2;
import gob.inei.renadef2016.fragments.C3CARAT_Fragment_C3;
import gob.inei.renadef2016.fragments.C3CARAT_Fragment_C6;
import gob.inei.renadef2016.fragments.C3CARAT_Fragment_C7;
import gob.inei.renadef2016.fragments.C3CARAT_Fragment_C8;
import gob.inei.renadef2016.fragments.C3CUEST_OBS;
import gob.inei.renadef2016.fragments.C3MARCO_Fragment;
import gob.inei.renadef2016.fragments.C3VISITAS_Fragment_S3;
import gob.inei.renadef2016.listener.MyNavigationClickListener;
import gob.inei.renadef2016.modelo.DelVisita;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.modelo.Navegation;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.service.ATVisitaService;
import gob.inei.renadef2016.service.MarcoService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
//import org.apache.log4j.lf5.Log4JLogRecord;













import paul.arian.fileselector.FileSelectionActivity;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class CuestionarioFragmentActivity extends MasterActivity implements
		ActionBar.TabListener, Respondible, Observer {

	private enum PROCCES {
		MARCO, DATA
	}

	private static int REQUEST_CODE_PICK_MARCO = 1;
	private static int REQUEST_CODE_PICK_IMPORT = 2;
	private PROCCES action = null;
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle drawerToggle;
	private Vector<String> opcionesMenu = new Vector<String>();
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private static String TAG = "CuestionarioRuralFragmentActivity";
	private static String TITULO = "";

	SharedPreferences preferencias;
	SharedPreferences.Editor editor;

	private static String PREFERENCIAS = "preferencias";
	private CharSequence mTitle;

	public static Context baseContext;
	public Integer estado;
	private MarcoService mimarcoService;
	private static ATVisitaService atvisitaService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseContext = getApplicationContext();
		mimarcoService = MarcoService.getInstance(baseContext);
		atvisitaService = ATVisitaService.getInstance(baseContext);
		TAG = this.getClass().toString();
		setContentView(R.layout.activity_principal);
		mTitle = getTitle();
		preferencias = this.getSharedPreferences(PREFERENCIAS,
				Context.MODE_PRIVATE);
		editor = preferencias.edit();
		List<FragmentForm> forms = createFragments();
		pageAdapter = new MyFragmentPagerAdapter(this, forms);
		viewPager = (FragmentViewPager) findViewById(R.id.pagerPrincipal);
		viewPager.setAdapter(pageAdapter);
		viewPager.setFragments(forms);
		final CuestionarioSimpleOnPageChangeListener viewPagerListener = new CuestionarioSimpleOnPageChangeListener();
		viewPager.setPageTransformer(true, new DepthPageTransformer());
		viewPager.setOnPageChangeListener(viewPagerListener);
		
		for (String s : getResources().getStringArray(R.array.drawer_array)) {
			opcionesMenu.add(s);
		}

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_principal_layout);
		drawerList = (ListView) findViewById(R.id.left_principal_drawer);
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		drawerList.setAdapter(new MyDrawerAdapter(opcionesMenu, this));
		navigationClickListener = new MyNavigationClickListener(this, 0,
				drawerList, drawerLayout);
		drawerList.setOnItemClickListener(navigationClickListener);
		TITULO = getResources().getString(R.string.app_name);
		final ActionBar actionBar = getActionBar();
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_navigation_drawer,
				R.string.navigation_drawer_open,
				R.string.navigation_drawer_close) {

			public void onDrawerClosed(View view) {
				getActionBar().setTitle(TITULO);
				ActivityCompat
						.invalidateOptionsMenu(CuestionarioFragmentActivity.this);
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Menu");
				ActivityCompat
						.invalidateOptionsMenu(CuestionarioFragmentActivity.this);
			}
		};
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		drawerLayout.setDrawerListener(drawerToggle);
		
		debeGrabar = true;
		refreshSubTitle();
	}
	
	public void refreshSubTitle(){
		String ruta = App.getInstance().getUsuario().ruta == null ? "" : " - " + App.getInstance().getUsuario().ruta;
		Cap200Delitos c200 = App.getInstance().getCap200();
		getActionBar().setSubtitle(Util.getText(App.getInstance().getUsuario().usuario, "") +" ["+Util.getText(App.getInstance().getUsuario().equipo,"") + ruta +"]" +
				(App.getInstance().getComisaria()!=null?(App.getInstance().getComisaria().ivresfin_03!=null?" - RESFIN [ "+App.getInstance().getComisaria().ivresfin_03+" ]":""):"") +
				(c200!=null?(c200.orden_200!=null?" - DN [ "+c200.orden_200+" ]" +(c200.ih214!=null?" - P214 [ "+c200.ih214+" ]" + (c200.ih215!=null?" - P215 [ "+c200.ih215+" ]":"")
				:""):""):"")
				);
	}

	public static final Integer MARCO = 0;
	public static final Integer CARATULA = 3;
	public static final Integer VISIT_DEL = 4;
	public static final Integer CAP100TOT_DEL = 8;
	public static final Integer CAP200_DEL = 9; //*/16;
	public static final Integer CAP300_DEL = 10; //*/17;
	public static final Integer CAP400_DEL = 11; //*/18;
	public static final Integer CAPOBS_CUEST = 12; //*/19;
	public static final Integer CAP100_DEL = 13;

	private List<FragmentForm> createFragments() {
		List<FragmentForm> fragments = new ArrayList<FragmentForm>();
		// INICIAL - CAPÍTULO 1
		/* 00 */fragments.add(new C3MARCO_Fragment().parent(this));
		/* 01 */fragments.add(new C3CARAT_Fragment_C1().parent(this));
		/* 02 */fragments.add(new C3CARAT_Fragment_C2().parent(this));
		/* 03 */fragments.add(new C3CARAT_Fragment_C3().parent(this));
		/* 04 */fragments.add(new C3VISITAS_Fragment_S3().parent(this));
		/* 05 */fragments.add(new C3CARAT_Fragment_C6().parent(this));
		/* 06 */fragments.add(new C3CARAT_Fragment_C7().parent(this));
		/* 07 */fragments.add(new C3CARAT_Fragment_C8().parent(this));
//		/* 07 */fragments.add(new C3CAP100_Fragment_101_A().parent(this));
//		/* 07 */fragments.add(new C3CAP100_Fragment_101_F().parent(this));
//		/* 08 */fragments.add(new C3CAP100_Fragment_102().parent(this));
//		/* 08 */fragments.add(new C3CAP100_Fragment_105().parent(this));
//		/* 08 */fragments.add(new C3CAP100_Fragment_108().parent(this));
//		/* 09 */fragments.add(new C3CAP100_Fragment_113().parent(this));
//		/* 09 */fragments.add(new C3CAP100_Fragment_117().parent(this));
//		/* 09 */fragments.add(new C3CAP100_Fragment_122().parent(this));
//		/* 08 */fragments.add(new C3CAP100_Fragment_117_1().parent(this));
		/* 08 */fragments.add(new C3CAP100_Fragment_117_2().parent(this));
		/* 09 */fragments.add(new C3CAP200_Fragment_201().parent(this));
		/* 10 */fragments.add(new C3CAP300_Fragment_301().parent(this));
		/* 11 */fragments.add(new C3CAP400_Fragment_401().parent(this));
		/* 12 */fragments.add(new C3CUEST_OBS());
		/* 13 */fragments.add(new C3CAP100_Fragment_101_A().parent(this));
		/* 14 */fragments.add(new C3CAP100_Fragment_101_F().parent(this));
		/* 15 */fragments.add(new C3CAP100_Fragment_102().parent(this));
		/* 16 */fragments.add(new C3CAP100_Fragment_105().parent(this));
		/* 17 */fragments.add(new C3CAP100_Fragment_108().parent(this));
		/* 18 */fragments.add(new C3CAP100_Fragment_113().parent(this));
		/* 19 */fragments.add(new C3CAP100_Fragment_117().parent(this));
		/* 20 */fragments.add(new C3CAP100_Fragment_122().parent(this));
		return fragments;

	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean menuAbierto = drawerLayout.isDrawerOpen(drawerList);
		
		if(viewPager.getCurrentItem() <= MARCO) {
			return true;
		} else {
			if(App.getInstance().getComisaria()==null) return false;
			Navegation nav = mimarcoService.getNavegacion(App.getInstance().getComisaria().id_n);
			if(nav == null){
				menu.findItem(R.id.btnSaltarMarco).setVisible(false);
				menu.findItem(R.id.btnSaltarCaratula).setVisible(false);
				menu.findItem(R.id.btnSaltarVisita).setVisible(false);
				menu.findItem(R.id.btnSaltarCap100).setVisible(false);
				menu.findItem(R.id.btnSaltarCap200).setVisible(false);
			} else {
				menu.findItem(R.id.btnSaltarCaratula).setVisible(nav.car != null);
				menu.findItem(R.id.btnSaltarVisita).setVisible(nav.capv != null);
				menu.findItem(R.id.btnSaltarCap100).setVisible(nav.cap100 != null);
				menu.findItem(R.id.btnSaltarCap200).setVisible(nav.cap200 != null);
			}
		}
		
//		if(viewPager.getCurrentItem() == MARCO) return true;
//		INF_Caratula01 caratula = App.getInstance().getComisaria();
//		if(caratula == null || caratula.ii1 == null){
//			menu.findItem(R.id.btnSaltarCaratula).setVisible(false);
//			menu.findItem(R.id.btnSaltarVisita).setVisible(false);
//			menu.findItem(R.id.btnSaltarCap100).setVisible(false);
//			menu.findItem(R.id.btnSaltarCap200).setVisible(false);
//		} 
//		if(viewPager.getCurrentItem() != CARATULA && viewPager.getCurrentItem() != VISIT_DEL)
//			menu.findItem(R.id.addObservation).setVisible(false);
//		if(viewPager.getCurrentItem() == VISIT_DEL){
//			if(Util.getInt(caratula.v3_2) == 0) menu.findItem(R.id.btnSaltarCap200).setVisible(false);
//		}
//		if(viewPager.getCurrentItem() <= CARATULA){
//			menu.findItem(R.id.btnSaltarVisita).setVisible(false);
//			menu.findItem(R.id.btnSaltarCap100).setVisible(false);
//			menu.findItem(R.id.btnSaltarCap200).setVisible(false);
//		}
//		if(caratula == null || caratula.v3_1 == null || viewPager.getCurrentItem() >= CAP100_DEL){
//			menu.findItem(R.id.btnSaltarCap100).setVisible(false);
//			menu.findItem(R.id.btnSaltarCap200).setVisible(false);
//		} 
//		if(caratula == null || Util.getInt(caratula.v3_2) == 0) {
//			menu.findItem(R.id.btnSaltarCap200).setVisible(false);
//		}
		
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if(viewPager.getCurrentItem() == MARCO) {
			return true;
		} else {
			getMenuInflater().inflate(R.menu.savef, menu);
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		int id = item.getItemId();
		if (id == R.id.btnSaltarMarco) {
			irA(MARCO);
		} else if (id == R.id.btnSaltarCaratula) {
			irA(CARATULA);
		} else if (id == R.id.btnSaltarVisita) {
			irA(VISIT_DEL);
		} else if (id == R.id.btnSaltarCap100) {
			irA(CAP100TOT_DEL);
		} else if (id == R.id.btnSaltarCap200) {
			irA(CAP200_DEL);
		} else if (id == R.id.savebutton) {
			grabarYContinuar();
		} 
		
		return super.onOptionsItemSelected(item);
	}

	private void grabarYContinuar() {
		this.grabarYContinuar(viewPager.getCurrentItem() + 1);
	}

	private void irA(int position) {
		int posicionActual = viewPager.getCurrentItem();
		boolean flag = CuestionarioFragmentActivity.this.pageAdapter.getItem(
				posicionActual).getSaltoNavegation();
		if(flag) nextFragment(position);
	}

	private void grabarYContinuar(int position) {
		int posicionActual = viewPager.getCurrentItem();
		boolean flag = CuestionarioFragmentActivity.this.pageAdapter.getItem(
				posicionActual).grabar();
		if (flag && posicionActual != MARCO) {
			esArrastre = true;
			if (posicionActual == viewPager.getCount() - 1) {
				ToastMessage.msgBox(this, "Registro Finalizado",
						ToastMessage.MESSAGE_INFO, ToastMessage.DURATION_LONG);
				debeGrabar = true;
				nextFragment(VISIT_DEL);
			} else {
				debeGrabar = false;
				nextFragment(position);
			}
			return;
		} else {
			esArrastre = false;
		}
	}

	public void setTitulo(String titulo) {
		TITULO = getResources().getString(R.string.app_name);
		if (titulo != null) {
			if (!"".equals(titulo)) {
				TITULO += " " + titulo;
			}
		}
		getActionBar().setTitle(TITULO);
	}

	public void setSubTitulo(String titulo) {
		String subtitle = "";
		if (titulo != null) {
			if (!"".equals(titulo) && titulo != null) {
				subtitle = titulo;
			}
		}
		getActionBar().setSubtitle(subtitle);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("Salir")
					.setMessage(
							"Esta a punto de salir de la aplicaci\u00f3n, se perder\u00e1 todo aquello que no haya guardado. \u00bfEsta seguro que desea salir del sistema?")
					.setPositiveButton("Si",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									App.MODALIDADES = null;
									C3MARCO_Fragment.RUTA = null;
									C3MARCO_Fragment.PERIODO = null;
									finish();    
									System.gc();    
									Runtime.getRuntime().exit(0);
								}
							}).setNegativeButton("No", null).show();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (preferencias.getBoolean("primera_ejecucion", true)) {
			editor.putBoolean("primera_ejecucion", false);
			editor.commit();
			drawerLayout.postDelayed(new Runnable() {
				@Override
				public void run() {
					drawerLayout.openDrawer(Gravity.LEFT);
				}
			}, 500);
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	}

	private class CuestionarioSimpleOnPageChangeListener extends
			FragmentViewPager.SimpleOnPageChangeListener {		
		private boolean pasada = true;
		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_DRAGGING) {
				CuestionarioFragmentActivity.this.setPrevPage(viewPager
						.getCurrentItem());
				esArrastre = true;
			}
			super.onPageScrollStateChanged(state);
		}

		@Override
		public void onPageSelected(final int position) {
			CuestionarioFragmentActivity.this.setCurPage(position);
			invalidateOptionsMenu();
			int prevPage = CuestionarioFragmentActivity.this.getPrevPage();

			boolean flag = false;
			
			if(position == MARCO){
				App.getInstance().setMarco(null);
				App.getInstance().setComisaria(null);
			}
			if(position == VISIT_DEL){
				App.ONLY_VISUALITATION = false;
			}
			
			if(prevPage == MARCO && prevPage < position && App.getInstance().getMarco() == null){
				esArrastre = false;
				nextFragment(prevPage);
				return;
			}
			
			if(prevPage == CAP200_DEL && prevPage < position &&	App.getInstance().getCap200() == null && pasada){
				esArrastre = false;
				nextFragment(prevPage);
				return;
			}
			
			if(prevPage == CAP300_DEL && prevPage < position &&	App.getInstance().getCap200() != null){
				Cap200Delitos c200 = App.getInstance().getCap200();
				if(c200!=null && c200.ih215!=null && c200.ih215.intValue()>0){
					if(mimarcoService.getConteoDenunVict(App.getInstance().getComisaria().id_n, 
								c200.nro_mreg, " AND IVH310 IS NOT NULL", "!=")){
						esArrastre = false;
						nextFragment(prevPage);
						return;
					}
				} else if(c200!=null && c200.ih215==null){
					if(!mimarcoService.getConteoDenunVict(App.getInstance().getComisaria().id_n, c200.nro_mreg, 
							" AND ((IVH304 = 1 AND IVH306_ANIO IS NOT NULL) OR "
							+ "(IVH304 = 2 AND IVH307 IS NOT NULL) OR "
							+ "(IVH304 > 2 AND IVH309 IS NOT NULL))", "!=")){
						esArrastre = true;
						nextFragment(CAP200_DEL);
						return;
					} else{
						esArrastre = false;
						nextFragment(prevPage);
						return;
					}
				}
			}
			
			if(prevPage == CAP400_DEL && prevPage < position &&	App.getInstance().getCap200() != null){
				List<Cap200Delitos> c200 = mimarcoService.getC200s(App.getInstance().getComisaria(),
						new Cap200Delitos().getSecCap(Util.getListList("NRO_MREG", "IH215")));				
				Log.e("entras", "valor: "+c200.size());
				
				Cap200Delitos c200d = App.getInstance().getCap200();
				if(!c200.isEmpty()){
//					for(Cap200Delitos c : c200){
//						if(c.nro_mreg.equals(App.getInstance().getCap200().nro_mreg)){
//							c200d = c; break;
//						}
//					}
				
					if(c200d!=null && c200d.ih215!=null && c200d.ih215.intValue()>0){
						if(mimarcoService.getConteoDenunVictimario(App.getInstance().getComisaria().id_n, 
								App.getInstance().getCap200().nro_mreg, " AND IVM408 IS NOT NULL", "!=")){
							esArrastre = false;
							nextFragment(prevPage);
							return;
						} else if(c200d.ih215!=null){
							Cap100Delitos c100Del = mimarcoService.getC100(App.getInstance().getComisaria(),
									new Cap100Delitos().getSecCap(Util.getListList("TOTAL_DELITOS")));
							if(c100Del!=null)	{
								if(c200.size() < Util.getInt(c100Del.total_delitos)){
//								if(Integer.valueOf(c200.size()).compareTo(c100Del.total_delitos)<0){
									ToastMessage.msgBox(getApplicationContext(), "Aun Tiene Denuncias por Registrar...",
											ToastMessage.MESSAGE_ERROR,
											ToastMessage.DURATION_LONG);
									esArrastre = true;
									nextFragment(CAP200_DEL);
									return;
								} else {
									esArrastre = true;
									nextFragment(CAP200_DEL);
									return;
								}
							}
						}
					} 
				}
			}

			if(position == CAPOBS_CUEST - 1 && prevPage == CAPOBS_CUEST){
				Cap100Delitos c100Del = mimarcoService.getC100(App.getInstance().getComisaria(),
						new Cap100Delitos().getSecCap(Util.getListList("SUM_FALLECIDOS", "DN120")));
				if(c100Del!=null)	{
					if(Integer.valueOf(0).equals(c100Del.sum_fallecidos) && 
							Integer.valueOf(0).compareTo(c100Del.dn120)<0){
						esArrastre = true;
						nextFragment(CAP100TOT_DEL);
						return;
					}
				}
			}
			
			if(position == CAP100_DEL - 1 && prevPage == CAP100_DEL){
				esArrastre = true;
				nextFragment(VISIT_DEL);
				return;
			}
			
			if (prevPage < position) {
				flag = CuestionarioFragmentActivity.this.pageAdapter.getItem(
						prevPage).grabar();
				if (!flag) {
					esArrastre = false;
					nextFragment(prevPage);
					return;
				}
				if(prevPage == VISIT_DEL+1 && prevPage < position){
					List<DelVisita> _vis = atvisitaService.getDelVisitasSec(App.getInstance().getComisaria(), 
							new DelVisita().getSecCap(Util.getListList("III_5")));
					if(!_vis.isEmpty() && _vis.size()>0){
						if(Integer.valueOf(3).equals(_vis.get(_vis.size()-1).iii_5) || 
								Integer.valueOf(6).equals(_vis.get(_vis.size()-1).iii_5)){
							nextFragment(VISIT_DEL);
							return;
						}
					}
				}
				if(prevPage == VISIT_DEL+2 && prevPage < position){
					List<DelVisita> _vis = atvisitaService.getDelVisitasSec(App.getInstance().getComisaria(), 
							new DelVisita().getSecCap(Util.getListList("III_5")));
					if(!_vis.isEmpty() && _vis.size()>0){
						if(Integer.valueOf(5).equals(_vis.get(_vis.size()-1).iii_5)){
							nextFragment(VISIT_DEL);
							return;
						}
					}
				}
//				if(prevPage == CAP100TOT_DEL && prevPage < position){
//					Integer checkSalto = CuestionarioFragmentActivity.this.pageAdapter.getItem(prevPage).getSalto();
//					if(checkSalto.equals(1)) {
//						pasada = false;
//						CuestionarioFragmentActivity.this.pageAdapter.getItem(position).cargarDatos();
//						return;
//					}
//					pasada = true;
//					if(checkSalto.equals(2)) {
//						nextFragment(VISIT_DEL);
//						return;
//					}
//				}
				esArrastre = true;
			}

			if (esArrastre) {
				CuestionarioFragmentActivity.this.pageAdapter.getItem(position).cargarDatos();	
				Filtros.clear();
			}
			
			setTitlePag(position);
		}

		private void setTitlePag(int position) {
			if(position <= CARATULA){
				setTitle(mTitle+(App.getInstance().getMarco()!=null?" - ID:"+
						App.getInstance().getMarco().id_n:""));
			} else if(position >= VISIT_DEL && position < CAP100TOT_DEL){
				setTitle("ID:"+App.getInstance().getMarco().id_n+" - DELITOS");
			} else if(position >= CAP100TOT_DEL && position < CAP200_DEL){
				setTitle("ID:"+App.getInstance().getMarco().id_n+" - Cap. 100: N\u00B0 DE DENUNCIAS");
			} else if(position >= CAP200_DEL && position < CAP300_DEL){
				setTitle("ID:"+App.getInstance().getMarco().id_n+" - Cap. 200: INF. DE DENUNCIAS");
			} else if(position >= CAP300_DEL && position < CAP400_DEL){
				setTitle("ID:"+App.getInstance().getMarco().id_n+" - Cap. 300: CARAT. DE LAS V\u00cdCTIMAS");
			} else if(position >= CAP400_DEL && position < CAPOBS_CUEST){
				setTitle("ID:"+App.getInstance().getMarco().id_n+" - Cap. 400: INF. DE VICTIMARIOS");
			}
			refreshSubTitle();
		}
	}

	public void uploadData() {
		action = PROCCES.DATA;
		DialogComponent dialog = new DialogComponent(this, this,
				TIPO_DIALOGO.YES_NO, getResources()
						.getString(R.string.app_name),
				"Desea importar archivos de respaldo?");
		dialog.showDialog();
	}

	public void uploadMarco() {
		action = PROCCES.MARCO;
		DialogComponent dialog = new DialogComponent(this, this,
				TIPO_DIALOGO.YES_NO, getResources()
						.getString(R.string.app_name),
				"Desea importar archivos de marco?");
		dialog.showDialog();
	}

	@Override
	public void onCancel() {
		action = null;
	}

	@Override
	public void onAccept() {
		Intent intent = new Intent(this, FileSelectionActivity.class);
		String ruta = App.RUTA_BASE;
		if (action == PROCCES.MARCO) {
			ruta += "/config";
			File directorio = new File(ruta);
			if (!directorio.exists()) {
				directorio.mkdirs();
			}
			intent.putExtra(FileSelectionActivity.START_FOLDER, ruta);
			intent.putExtra("FILTER_EXTENSION", new String[] { "cfg", "zip" });
			startActivityForResult(intent, REQUEST_CODE_PICK_MARCO);
		} else if (action == PROCCES.DATA) {
			ruta += "/backups";
			File directorio = new File(ruta);
			if (!directorio.exists()) {
				directorio.mkdirs();
			}
			intent.putExtra(FileSelectionActivity.START_FOLDER, ruta);
			intent.putExtra("FILTER_EXTENSION", new String[] { "xml", "zip" });
			startActivityForResult(intent, REQUEST_CODE_PICK_IMPORT);
		}
		action = null;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK)
			return;
		ArrayList<File> files = (ArrayList<File>) data
				.getSerializableExtra(FileSelectionActivity.FILES_TO_UPLOAD);
		if (files != null && files.size() == 0) {
			ToastMessage.msgBox(this,
					"Ning\u00fan archivo ha sido seleccionado",
					ToastMessage.MESSAGE_ERROR, ToastMessage.DURATION_LONG);
		}
		if (requestCode == REQUEST_CODE_PICK_MARCO) {
			importar(files, requestCode);
		} else if (requestCode == REQUEST_CODE_PICK_IMPORT) {
			importar(files, requestCode);
		}
	}

	private void importar(ArrayList<File> archivos, int requestCode) {
		DialogComponent dlg = new DialogComponent(this, this,
				DialogComponent.TIPO_DIALOGO.NEUTRAL, getResources().getString(
						R.string.app_name), "Seleccione algún item.");
		if (archivos.size() == 0) {
			dlg.showDialog();
			return;
		}
		Importacion r = new Importacion(this, "Importando información. ");
		r.setArchivos(archivos);
		r.execute();
	}

	@Override
	public void update(Observable observable, Object data) {

	}

	@Override
	public void calificar() {
		// TODO Auto-generated method stub

	}
}