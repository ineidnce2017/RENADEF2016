package gob.inei.renadef2016.service;

import gob.inei.dnce.components.Entity.SeccionCapitulo;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.dao.xml.XMLDataObject;
import gob.inei.renadef2016.common.App.MAINTENCE;
import gob.inei.renadef2016.common.MyUtil.Periodo;
import gob.inei.renadef2016.common.MyUtil.Ruta;
import gob.inei.renadef2016.dao.MarcoDAO;
import gob.inei.renadef2016.dao.MarcoDAO.CounterObservable;
import gob.inei.renadef2016.modelo.C100udt;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.modelo.ListaVictimas;
import gob.inei.renadef2016.modelo.Marco;
import gob.inei.renadef2016.modelo.Navegation;
import gob.inei.renadef2016.modelo.Search;
import gob.inei.renadef2016.modelo.Segmentacion;
import gob.inei.renadef2016.modelo.Ubigeo;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap300Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap400Delitos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MarcoService extends Service{
	
public static MarcoService INSTANCE = null;
	
	private MarcoService(Context ctx) {
		super(ctx);
	}
	
	public static MarcoService getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new MarcoService(ctx);
		}
		return INSTANCE;
	}
	
	private MarcoDAO getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = MarcoDAO.getInstance(this.dbh);
		}
		return (MarcoDAO)sqliteDAO;
	}
	
	public SQLiteDatabase startTX() {
		return getDao().startTX();
	}
	
	public void commitTX(SQLiteDatabase dbTX) {
		getDao().commitTX(dbTX);
	}

	public void endTX(SQLiteDatabase dbTX) {
		getDao().endTX(dbTX);
	}
	
	public boolean existeComisaria(Marco marco){
		return getDao().existeComisaria(marco);		
	}
	
//	public boolean saveUbigeo(Ubigeo ubigeo){
//		return getDao().saveUbigeo(ubigeo);
//	}
	
	public List<Marco> getMarco(String id_n){
		return getDao().getMarco(id_n);		
	}
	
	public Map<String, Object> getDepAsig(String codigo) {
		return getDao().getDepAsig(codigo);
	}
	
	public List<Ruta> getRutas(String codigoSedeOperativa, String equipo, Integer cargo) {
		return getDao().getRutas(codigoSedeOperativa, equipo, cargo);
	}
	
	public List<Periodo> getPeriodos(String ruta, String codSedeOp, Integer cargo, String cod) {
		return getDao().getPeriodos(ruta, codSedeOp, cargo, cod);
	}
	
//	public ArrayList<Marco> listarmarcoComisarias() {
//		return getDao().listarmarcoComisarias(null);
//	}
	
	public List<Marco> listarmarcoComisarias(String ruta, String codSedeOp, Integer periodo, FragmentForm.OPCION opcion) {
		return getDao().listarmarcoComisarias(ruta, codSedeOp, periodo, opcion);
	}
	
//	public List<Marco> listarmarcoComisarias(Integer id, Integer periodo) {
//		return getDao().listarmarcoComisarias(id, periodo);
//	}
	
//	public ArrayList<Marco> listarmarcoComisariasExportar() {
//		return getDao().listarmarcoComisariasExportar();		
//	}
	
	/*public List<Marco> getMarcoTodos(){
		return marcoData.getMarcoTodos();
	}*/
	
//	public List<Marco> getMarcoCaratula(String id_n){
//		return getDao().getMarcoCaratula(id_n);		
//	}

	
	public boolean existeRegistro(String id_n){
		return getDao().existeRegistro(id_n);
	}
	
//	public Marco getMarcoEdicion(String id_n){
//		return getDao().getMarcoEdicion(id_n);		
//	}
	
	public boolean getMarcoAgregado(String id_n){
		return getDao().existeRegistro(MarcoDAO.TABLA_MARCO, "ID_N='"+id_n+"'");
	}
	
//	public boolean grabarMarco(Marco marco){
//		return getDao().grabarMarco(marco);
//	}
	
	public Ubigeo getUbigeo(String ubigeo){
		return getDao().getUbigeo(ubigeo);
	}
	
	public boolean eliminarINF(String id_n){
		return getDao().eliminarINF(id_n);
	}
	
	public boolean eliminarAT(String id_n){
		return getDao().eliminarAT(id_n);
	}
	
	public boolean eliminarDE(String id_n){
		return getDao().eliminarDE(id_n);
	}
	
	public boolean deleteCap100Del(String id_n){
		return getDao().deleteCap100Del(id_n);
	}
	
	public boolean deleteCap200DelxId(String id_n){
		return deleteCap200Del(id_n, -1);
	}
	
	public boolean deleteCap200Del(String id_n, Integer nro_mreg){
		return getDao().deleteCap200Del(id_n, nro_mreg);
	}
	
	public boolean deleteCap300Del(String id_n, Integer nro_mreg, Integer nro_vfreg){
		return getDao().deleteCap300Del(id_n, nro_mreg, nro_vfreg);
	}
	
	public boolean deleteCap400Del(String id_n, Integer nro_mreg){
		return deleteCap400Del(id_n, nro_mreg, -1);
	}
	
	public boolean deleteCap400Del(String id_n, Integer nro_mreg, Integer nro_pvreg){
		return getDao().deleteCap400Del(id_n, nro_mreg, nro_pvreg);
	}
	
//	public boolean abrirDE(String id_n){
//		return getDao().abrirDE(id_n);
//	}
	
	public boolean eliminarComisaria(String id_n){
		return getDao().eliminarComisaria(id_n);
	}

	public boolean esAgregado(String id_n){
		return getDao().esAgregado(id_n);
	}
	
//	public boolean borrarGPSINF(String id_n){
//		return getDao().borrarGPSINF(id_n);
//	}
	
//	public boolean borrarGPSAT(String id_n){
//		return getDao().borrarGPSAT(id_n);
//	}
	
//	public boolean borrarGPSDE(String id_n){
//		return getDao().borrarGPSDE(id_n);
//	}
	
	
	
	
	
	
	
//	public ArrayList<Marco> fillMarcosAgregados(String id_n){
//		return getDao().fillMarcosAgregados(id_n);
//	}
	
//	public ArrayList<INF_Caratula01> fillCaratulas(String id_n){
//		return getDao().fillCaratulas(id_n);
//	}
	
//	public ArrayList<INF_Visita01> fillINF_Visitas(String id_n){
//		return marcoData.fillINF_Visitas(id_n);
//	}
//	
//	public ArrayList<INF_Cap100> fillINF_Capitulo100s(String id_n){
//		return marcoData.fillINF_Capitulo100s(id_n);
//	}
//	
//	public ArrayList<INF_Cap200> fillINF_Capitulo200s(String id_n){
//		return marcoData.fillINF_Capitulo200s(id_n);
//	}
//	
//	public ArrayList<INF_Cap300> fillINF_Capitulo300s(String id_n){
//		return marcoData.fillINF_Capitulo300s(id_n);
//	}
//	
//	public ArrayList<INF_Cap300_III> fillINF_Capitulo300_iiis(String id_n){
//		return marcoData.fillINF_Capitulo300_iiis(id_n);
//	}
//	
//	public ArrayList<INF_Cap400> fillINF_Capitulo400s(String id_n){
//		return marcoData.fillINF_Capitulo400s(id_n);
//	}
//	
//	public ArrayList<INF_Cap500> fillINF_Capitulo500s(String id_n){
//		return marcoData.fillINF_Capitulo500s(id_n);
//	}
//	
//	public ArrayList<AT_Muestra> fillAT_Muestra(String id_n){
//		return marcoData.fillAT_Muestra(id_n);
//	}
//	
//	public ArrayList<ATVisita> fillAT_Visitas(String id_n){
//		return marcoData.fillAT_Visitas(id_n);
//	}
//	
//	public ArrayList<ATCap100BE> fillATCapitulo100s(String id_n){
//		return marcoData.fillATCapitulo100s(id_n);
//	}
//	
//	public ArrayList<ATCap200BE> fillATCapitulo200s(String id_n){
//		return marcoData.fillATCapitulo200s(id_n);
//	}
//	
//	public ArrayList<INF_Cap600> fillINF_Capitulo600s(String id_n){
//		return marcoData.fillINF_Capitulo600s(id_n);
//	}
//	
//	public ArrayList<INF_Cap600_I> fillINF_Capitulo600is(String id_n){
//		return marcoData.fillINF_Capitulo600is(id_n);
//	}
//	
//	public ArrayList<Anexo_C100> fill_Anexo100s(String id_n){
//		return marcoData.fill_Anexo100s(id_n);
//	}
//	
//	public ArrayList<Anexo_C100_Det> fill_Anexo100_Dets(String id_n){
//		return marcoData.fill_Anexo100_Dets(id_n);
//	}
//	
//	public ArrayList<Anexo_C200_Det> fill_Anexo200_Dets(String id_n){
//		return marcoData.fill_Anexo200_Dets(id_n);
//	}
//	
//	public ArrayList<Anexo_C300_Det> fill_Anexo300_Dets(String id_n){
//		return marcoData.fill_Anexo300_Dets(id_n);
//	}
	
//	public ArrayList<CaratulaDelitos> fill_Caratula_Del(String id_n){
//		return marcoData.fill_Caratula_Del(id_n);
//	}
	
//	public ArrayList<DelVisita> fill_CaratulaVisit_Del(String id_n){
//		return getDao().fill_CaratulaVisit_Del(id_n);
//	}
	
//	public ArrayList<Cap100Delitos> fill_Cap100_Del(String id_n){
//		return getDao().fill_Cap100_Del(id_n);
//	}
	
//	public ArrayList<Cap100Delitos> fill_Cap100_Del(String id_n, List<String> fields){
//		return getDao().fill_Cap100_Del(id_n, fields);
//	}
	public Cap100Delitos getC100(INF_Caratula01 bean,SeccionCapitulo... secciones) { 
		return getDao().getC100(bean, secciones); 
	} 
	
	public String getDuplicidades300(int opcion, Integer nro_mreg, String... valores){
		return getDao().getDuplicidadesC300(opcion, nro_mreg, valores);
	}
	
	public String getDuplicidades400(int opcion, String... valores){
		return getDao().getDuplicidadesC400(opcion, valores);
	}
	
	public boolean getConteoDenunVict(String id_n){
		return getConteoDenunVict(id_n, -1);
	}
	
	public boolean getConteoDenunVict(String id_n, Integer nro_mreg){
		return getConteoDenunVict(id_n, nro_mreg, null);
	}
	
	public boolean getConteoDenunVict(String id_n, Integer nro_mreg, String where300){
		return getConteoDenunVict(id_n, nro_mreg, where300, ">=");
	}
	
	public boolean getConteoDenunVict(String id_n, Integer nro_mreg, String where300, String op){
		return getDao().getConteoDenunVict(id_n, nro_mreg, where300, op);
	}
	
	public Integer getConteoVictimas(String id_n){
		return getConteoVictimas(id_n, null);
	}
	
	public Integer getConteoVictimas(String id_n, Integer nro_mreg){
		return getDao().getConteoVictimas(id_n, nro_mreg, MarcoDAO.TABLA_C300);
	}
	
	public Integer getConteoVictimas308y309(String id_n, Integer nro_mreg){
		return getDao().getConteoVictimas(id_n, null, MarcoDAO.TABLA_C300, " AND NRO_MREG = "+
				nro_mreg+ " AND IVH309 = 2 AND IVH310 = 1");
	}
	
	public Integer getEdadVictima304y309(String id_n, Integer nro_mreg){
		return getDao().getConteoVictimas(id_n, null, "MIN(IVH305)", MarcoDAO.TABLA_C300, " AND NRO_MREG = "+
				nro_mreg+ " AND IVH310 = 6 AND IVH305 != 99");
	}
	
	public List<C100udt> getCountC200xP208(String id_n){
		return getDao().getCountC200xP208(id_n);
	}
	
	public boolean updateC100(String id_n, List<C100udt> c200s){
		return getDao().updateC100(id_n, c200s);
	}
	
	public boolean getConteoDenunVictimario(String id_n){
		return getConteoDenunVictimario(id_n, -1);
	}
	
	public boolean getConteoDenunVictimario(String id_n, Integer nro_mreg){
		return getConteoDenunVictimario(id_n, nro_mreg, null);
	}
	
	public boolean getConteoDenunVictimario(String id_n, Integer nro_mreg, String where400){
		return getConteoDenunVictimario(id_n, nro_mreg, where400, ">=");
	}
	
	public boolean getConteoDenunVictimario(String id_n, Integer nro_mreg, String where400, String op){
		return getDao().getConteoDenunVictimario(id_n, nro_mreg, where400, op);
	}
	
	public Integer getConteoVictimarios(String id_n){
		return getConteoVictimarios(id_n, null);
	}
	
	public Integer getConteoVictimasWhere(String id_n, Integer nro_mreg){
		return getDao().getConteoVictimasWhere(id_n, nro_mreg, MarcoDAO.TABLA_C300, "1,2,3,4,5,6,7,8,12,13,14,15,16,17");
	}
	
	public Integer getConteoVictimarios(String id_n, Integer nro_mreg){
		return getDao().getConteoVictimas(id_n, nro_mreg, MarcoDAO.TABLA_C400);
	}
	
	public Integer getSumaNroDelitos(String id_n){
		return getDao().getConteoVictimas(id_n, null, "IFNULL(TOTAL_DELITOS,0)", MarcoDAO.TABLA_C100, "");
	}
	
//	public ArrayList<Cap200Delitos> fill_Cap200_Del(String id_n){
//		return fill_Cap200_Del(id_n, false);
//	}
	
//	public ArrayList<Cap200Delitos> fill_Cap200_Del(String id_n, boolean borde){
//		return getDao().fill_Cap200_Del(id_n, borde);
//	}
	public List<Cap200Delitos> getC200s(INF_Caratula01 bean, SeccionCapitulo... secciones) { 
		return getC200s(bean, false, secciones); 
	} 
	public List<Cap200Delitos> getC200s(INF_Caratula01 bean, boolean borde, SeccionCapitulo... secciones) { 
		return getDao().getC200s(bean, borde, secciones); 
	} 
	public Cap200Delitos getC200(INF_Caratula01 bean, Integer nro_mreg, SeccionCapitulo... secciones) { 
		return getDao().getC200(bean, nro_mreg, secciones); 
	} 
	
//	public ArrayList<Cap200Delitos> fill_Cap200_Del(String id_n, List<String> fields){
//		return getDao().fill_Cap200_Del(id_n, fields);
//	}
	
//	public ArrayList<Cap200Delitos> fill_Cap200_Del(String id_n, Integer nro_mreg, List<String> fields){
//		return getDao().fill_Cap200_Del(id_n, nro_mreg, fields);
//	}
	
//	public ArrayList<Cap300Delitos> fill_Cap300_Del(String id_n){
//		return getDao().fill_Cap300_Del(id_n);
//	}
//	
//	public ArrayList<Cap300Delitos> fill_Cap300_Del(String id_n, Integer nro_mreg){
//		return getDao().fill_Cap300_Del(id_n, nro_mreg);
//	}
	public List<Cap300Delitos> getC300s(String id_n, Integer nro_mreg, SeccionCapitulo... secciones) { 
		return getDao().getC300s(id_n, nro_mreg, secciones); 
	} 
	
//	public ArrayList<Cap400Delitos> fill_Cap400_Del(String id_n){
//		return getDao().fill_Cap400_Del(id_n);
//	}
	
//	public ArrayList<Cap400Delitos> fill_Cap400_Del(String id_n, Integer nro_mreg){
//		return getDao().fill_Cap400_Del(id_n, nro_mreg);
//	}
	public List<Cap400Delitos> getC400s(String id_n, Integer nro_mreg, SeccionCapitulo... secciones) { 
		return getDao().getC400s(id_n, nro_mreg, secciones); 
	} 
	
	
	public boolean eliminarCuestionarios(String id_n){
		return getDao().eliminarCuestionarios(id_n);
	}
	
//	public boolean eliminarMarco(){
//		return getDao().eliminarMarco();
//	}
//	
//	public boolean eliminarUsuarios(){
//		return getDao().eliminarUsuarios();
//	}
	
//	public boolean eliminarSegmentaciones(){
//		return getDao().eliminarSegmentaciones();
//	}
	
//	public boolean saveMarco(List<Marco> marco, CounterObservable contadorObserver){
//		return getDao().saveMarco(marco, contadorObserver);
//	}

//	public boolean saveMarco(Marco marco){
//		return getDao().saveMarco(marco);
//	}
	
//	public boolean saveUsuario(Usuario usuario){
//		return getDao().saveUsuario(usuario);
//	}
	
//	public boolean saveUsuario(List<Usuario> usuario, CounterObservable contadorObserver){
//		return getDao().saveUsuario(usuario, contadorObserver);
//	}
	
//	public boolean saveSegmentacion(List<Segmentacion> segmentacion, CounterObservable contadorObserver){
//		return getDao().saveSegmentacion(segmentacion, contadorObserver);
//	}
	
	
//	public boolean saveCaratula(INF_Caratula01 caratula){
//		return getDao().saveCaratula(caratula);
//	}
	
//	public boolean saveINF_Visita(INF_Visita01 caratula){
//		return marcoData.saveINF_Visita(caratula);
//	}
//	
//	public boolean saveINF_Capitulo100(INF_Cap100 caratula){
//		return marcoData.saveINF_Capitulo100(caratula);
//	}
//	
//	public boolean saveINF_Capitulo200(INF_Cap200 caratula){
//		return marcoData.saveINF_Capitulo200(caratula);
//	}
//	
//	public boolean saveINF_Capitulo300(INF_Cap300 caratula){
//		return marcoData.saveINF_Capitulo300(caratula);
//	}
//	
//	public boolean saveINF_Capitulo300_III(INF_Cap300_III caratula){
//		return marcoData.saveINF_Capitulo300_III(caratula);
//	}
//	
//	public boolean saveINF_Capitulo400(INF_Cap400 caratula){
//		return marcoData.saveINF_Capitulo400(caratula);
//	}
//	
//	public boolean saveINF_Capitulo500(INF_Cap500 caratula){
//		return marcoData.saveINF_Capitulo500(caratula);
//	}
//	
//	
//	public boolean saveAT_Visita(ATVisita caratula){
//		return marcoData.saveAT_Visita(caratula);
//	}
//	
//	public boolean saveATCapitulo100(ATCap100BE caratula){
//		return marcoData.saveATCapitulo100(caratula);
//	}
//	
//	public boolean saveATCapitulo200(ATCap200BE caratula){
//		return marcoData.saveATCapitulo200(caratula);
//	}
	
	
	
//	public boolean saveCaratulas(List<INF_Caratula01> caratulas){
//		return getDao().saveCaratulas(caratulas);
//	}
	
//	public boolean saveINF_Visitas(List<INF_Visita01> caratulas){
//		return marcoData.saveINF_Visitas(caratulas);
//	}
//	
//	public boolean saveINF_Capitulo100s(List<INF_Cap100> caratulas){
//		return marcoData.saveINF_Capitulo100s(caratulas);
//	}
//	
//	public boolean saveINF_Capitulo200s(List<INF_Cap200> caratulas){
//		return marcoData.saveINF_Capitulo200s(caratulas);
//	}
//	
//	public boolean saveINF_Capitulo300s(List<INF_Cap300> caratulas){
//		return marcoData.saveINF_Capitulo300s(caratulas);
//	}
//	
//	public boolean saveINF_Capitulo300_IIIs(List<INF_Cap300_III> caratulas){
//		return marcoData.saveINF_Capitulo300_IIIs(caratulas);
//	}
//	
//	public boolean saveINF_Capitulo400s(List<INF_Cap400> caratulas){
//		return marcoData.saveINF_Capitulo400s(caratulas);
//	}
//	
//	public boolean saveINF_Capitulo500s(List<INF_Cap500> caratulas){
//		return marcoData.saveINF_Capitulo500s(caratulas);
//	}
//	
//	public boolean saveAT_Muestras(List<AT_Muestra> muestras){
//		return marcoData.saveAT_Muestras(muestras);
//	}
//	
//	public boolean saveAT_Visitas(List<ATVisita> caratulas){
//		return marcoData.saveAT_Visitas(caratulas);
//	}
//	
//	public boolean saveATCapitulo100s(List<ATCap100BE> caratulas){
//		return marcoData.saveATCapitulo100s(caratulas);
//	}
//	
//	public boolean saveATCapitulo200s(List<ATCap200BE> caratulas){
//		return marcoData.saveATCapitulo200s(caratulas);
//	}
//	
//	public boolean saveINF_Capitulo600s(List<INF_Cap600> caratulas){
//		return marcoData.saveINF_Capitulo600s(caratulas);
//	}
//	
//	public boolean saveINF_Capitulo600is(List<INF_Cap600_I> caratulas){
//		return marcoData.saveINF_Capitulo600is(caratulas);
//	}
//	
//	public boolean saveAnexo_100(List<Anexo_C100> anexo){
//		return marcoData.saveAnexo_100(anexo);
//	}
//	
//	public boolean saveAnexo_100_Dets(List<Anexo_C100_Det> anexo){
//		return marcoData.saveAnexo_100_Dets(anexo);
//	}
//	
//	public boolean saveAnexo_200_Dets(List<Anexo_C200_Det> anexo){
//		return marcoData.saveAnexo_200_Dets(anexo);
//	}
//	
//	public boolean saveAnexo_300_Dets(List<Anexo_C300_Det> anexo){
//		return marcoData.saveAnexo_300_Dets(anexo);
//	}
	
	
	/*DELITOS - OTIN*/
//	public ArrayList<CaratulaDelitos> fillCaratulasDelitos(String id_n){
//		return marcoData.fillCaratulasDelitos(id_n);
//	}
	
//	public ArrayList<VisitasDelitos> fillVisitasDelitos(String id_n){
//		return getDao().fillVisitasDelitos(id_n);
//	}
	
	public ArrayList<Cap100Delitos> fillCap100Delitos(String id_n){
		return getDao().fillCap100Delitos(id_n);
	}
	
//	public ArrayList<Cap200Delitos> fillCap200Delitos(String id_n){
//		return getDao().fillCap200Delitos(id_n);
//	}
	
	public ArrayList<Cap300Delitos> fillCap300Delitos(String id_n){
		return getDao().fillCap300Delitos(id_n);
	}
	
	public ArrayList<Cap400Delitos> fillCap400Delitos(String id_n){
		return getDao().fillCap400Delitos(id_n);
	}
	
	
//	public boolean saveCaratulaDelitos(CaratulaDelitos caratula){
//		return marcoData.saveCaratulaDelitos(caratula);
//	}
	
	public boolean saveOrUpdate(Marco bean, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return getDao().saveOrUpdate(bean, secciones); 
	} 
	public boolean saveOrUpdate(Segmentacion bean, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return getDao().saveOrUpdate(bean, secciones); 
	} 
	public boolean borrarSegmentacion(String id) throws SQLException { 
		return borrarSegmentacion(id, MAINTENCE.ONEONLY); 
	}
	public boolean borrarSegmentacion(String id, MAINTENCE opcion) throws SQLException { 
		return getDao().borrarSegmentacion(id, opcion); 
	}
	
//	public boolean saveVisitaDelitos(DelVisita caratula){
//		return getDao().saveVisitaDelitos(caratula);
//	}
//	
//	public boolean saveCap100Delitos(Cap100Delitos caratula){
//		return getDao().saveCap100Delitos(caratula);
//	}
//	
//	public boolean saveCap200Delitos(Cap200Delitos caratula){
//		return getDao().saveCap200Delitos(caratula);
//	}
	
	public boolean saveCap100Delitos(Cap100Delitos caratula, List<String> fields){
		return getDao().saveCap100Delitos(caratula, fields);
	}
	public boolean saveOrUpdate(Cap100Delitos bean, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return getDao().saveOrUpdate(bean, secciones); 
	} 
	
	public boolean saveCap200Delitos(Cap200Delitos caratula, List<String> fields){
		return getDao().saveCap200Delitos(caratula, fields);
	}
	public boolean saveOrUpdate(Cap200Delitos bean, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return saveOrUpdate(bean, MAINTENCE.ONEONLY, secciones); 
	} 
	public boolean saveOrUpdate(Cap200Delitos bean, MAINTENCE opcion, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return getDao().saveOrUpdate(bean, opcion, secciones); 
	} 
	
//	public boolean saveCap300Delitos(Cap300Delitos caratula){
//		return getDao().saveCap300Delitos(caratula);
//	}
	public boolean saveOrUpdate(Cap300Delitos bean, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return saveOrUpdate(bean, MAINTENCE.ONEONLY, secciones); 
	} 
	public boolean saveOrUpdate(Cap300Delitos bean, MAINTENCE opcion, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return getDao().saveOrUpdate(bean, opcion, secciones); 
	} 
	
	public boolean saveCap300Delitos(Cap300Delitos caratula, List<String> fields){
		return getDao().saveCap300Delitos(caratula, fields);
	}
	
//	public boolean saveCap400Delitos(Cap400Delitos caratula){
//		return getDao().saveCap400Delitos(caratula);
//	}
	
	public boolean saveCap400Delitos(Cap400Delitos caratula, List<String> fields){
		return getDao().saveCap400Delitos(caratula, fields);
	}
	public boolean saveOrUpdate(Cap400Delitos bean, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return saveOrUpdate(bean, MAINTENCE.ONEONLY, secciones); 
	} 
	public boolean saveOrUpdate(Cap400Delitos bean, MAINTENCE opcion, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return getDao().saveOrUpdate(bean, opcion, secciones); 
	} 
	
	
//	public boolean saveCaratulasDelitos(List<CaratulaDelitos> caratulas){
//		return marcoData.saveCaratulasDelitos(caratulas);
//	}
	
//	public boolean saveVisitasDelitos(List<DelVisita> caratulas){
//		return getDao().saveVisitasDelitos(caratulas);
//	}
//	
//	public boolean saveCap100Delitos(List<Cap100Delitos> caratulas){
//		return getDao().saveCap100Delitos(caratulas);
//	}
//	
//	public boolean saveCap200Delitos(List<Cap200Delitos> caratulas){
//		return getDao().saveCap200Delitos(caratulas);
//	}
//	
//	public boolean saveCap300Delitos(List<Cap300Delitos> caratulas){
//		return getDao().saveCap300Delitos(caratulas);
//	}
//	
//	public boolean saveCap400Delitos(List<Cap400Delitos> caratulas){
//		return getDao().saveCap400Delitos(caratulas);
//	}
	
	public boolean saveOrUpdate(SQLiteDatabase dbTX, XMLDataObject dataObjects, CounterObservable contadorObserver) throws java.sql.SQLException {
		return getDao().saveOrUpdate(dbTX, dataObjects, contadorObserver);
	}
	public Navegation getNavegacion(String id) { 
		return getDao().getNavegacion(id); 
	}
	
	public Map<String, Object> getRegistro(String tableName, String[] campos, String where, String...whereValues) {
		return getDao().getMap(tableName, campos, where, whereValues);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String where, String...whereValues) {
		return getDao().getRegistros(tableName, where, whereValues);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String[] campos, String where, String...whereValues) {
		return getDao().getRegistros(tableName, campos, where, whereValues);
	}
	
	public List<Search> getModalidades() {	
		return getDao().getModalidades();
	}
	
//	public List<ListVictimas> getListaVictimas() {
//		return getListaVictimas(null);
//	}
//
//	public List<ListVictimas> getListaVictimas(String codExep) {
//		return getDao().getListaVictimas(codExep);
//	}
	
	
	
	public List<ListaVictimas> getListaVictimas() {	
		return getDao().getListaVictimas();
	}
	
	public Search getModalidad(Integer codigo) {	
		return getDao().getModalidad(codigo);
	}
	
}
