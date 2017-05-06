package gob.inei.renadef2016.service;

import java.util.List;

import gob.inei.renadef2016.dao.ATVisitaDAO;
import gob.inei.renadef2016.dao.MyDatabaseHelper;
import gob.inei.renadef2016.dao.INF_Caratula01Data;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.dnce.components.Entity.SeccionCapitulo;
import android.content.Context;

public class INF_Caratula01Service extends Service{
	
public static INF_Caratula01Service INSTANCE = null;
	
	private INF_Caratula01Service(Context ctx) {
		super(ctx);
	}
	
	public static INF_Caratula01Service getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new INF_Caratula01Service(ctx);
		}
		return INSTANCE;
	}
	
	private INF_Caratula01Data getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = INF_Caratula01Data.getInstance(this.dbh);
		}
		return (INF_Caratula01Data)sqliteDAO;
	}
	
	public boolean existeComisariaEnDigitacion(String pIDN){
		return getDao().existeComisariaEnDigitacion(pIDN);		
	}
	
	public INF_Caratula01 getGPSDelitos(String pIDN){
		return getDao().getGPSDelitos(pIDN);		
	}
	
//	public INF_Caratula01 getINFCaratula(String pIDN){
//		return infCaratulaData.getINFCaratula(pIDN);		
//	}
	
	public INF_Caratula01 getINFCaratula(String pIDN, List<String> campos){
		return getINFCaratula(pIDN, campos, true);		
	}
	
	public INF_Caratula01 getINFCaratula(String pIDN, List<String> campos, boolean check){
		return getDao().getINFCaratula(pIDN, campos, check);		
	}
	public INF_Caratula01 getCaratulaMarco(String pIDN, SeccionCapitulo... secciones) { 
		return getDao().getCaratulaMarco(pIDN, secciones); 
	} 
	
//	public CaratulaDelitos getCaratulaDel(String pIDN){
//		return getDao().getCaratulaDel(pIDN);		
//	}
	
//	public boolean grabarCaratula(INF_Caratula01 infcaratula,List<INF_Visita01> listVisitas){
//		return infCaratulaData.grabarCaratula(infcaratula,listVisitas);
//	}
	
	public boolean grabarCaratula(INF_Caratula01 infcaratula,int pp,List<String> fields){
		return getDao().grabarCaratula(infcaratula,pp, fields);
	}
	public boolean saveOrUpdate(INF_Caratula01 bean, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return getDao().saveOrUpdate(bean, secciones); 
	} 
	
//	public boolean grabarCaratula(INF_Caratula01 infcaratula){
//		return infCaratulaData.grabarCaratula(infcaratula, null);
//	}
	
	public boolean existeGPS(String latitud, String longitud) {
		return getDao().existeGPS(latitud, longitud);
	}
	
	public boolean grabarVersionImpExp(String id_n, String version){
		return getDao().grabarVersionImpExp(id_n, version);
	}
	
	public boolean grabarGPSDelitos(INF_Caratula01 infcaratula){
		return getDao().grabarGPSDelitos(infcaratula);
	}
	
	public boolean grabarEstadoEnvio(INF_Caratula01 infcaratula, int cuestionario){
		return getDao().grabarEstadoEnvio(infcaratula, cuestionario);
	}
	
	public boolean grabarCaratulaExterno(INF_Caratula01 infcaratula){
		return getDao().grabarCaratulaExterno(infcaratula);		
	}
	
	public boolean existGPSPoint(double latitud, double longitud){
		return getDao().existGPSPoint(latitud, longitud);
	}
}
