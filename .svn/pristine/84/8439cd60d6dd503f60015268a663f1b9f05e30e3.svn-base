package gob.inei.renadef2016.service;

import gob.inei.renadef2016.common.App.MAINTENCE;
import gob.inei.renadef2016.dao.ATVisitaDAO;
import gob.inei.renadef2016.dao.MyDatabaseHelper;
import gob.inei.renadef2016.dao.UsuarioDAO;
import gob.inei.renadef2016.modelo.DelVisita;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.dnce.components.Entity.SeccionCapitulo;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

public class ATVisitaService extends Service{

public static ATVisitaService INSTANCE = null;
	
	private ATVisitaService(Context ctx) {
		super(ctx);
	}
	
	public static ATVisitaService getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new ATVisitaService(ctx);
		}
		return INSTANCE;
	}
	
	private ATVisitaDAO getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = ATVisitaDAO.getInstance(this.dbh);
		}
		return (ATVisitaDAO)sqliteDAO;
	}
	
//	public List<ATVisita> getATVisitas(String pIDN){
//		return infVisitaData.getATVisitas(pIDN);		
//	}
	
//	public List<ATVisita> getATVisitas(String pIDN, List<String> fields){
//		return infVisitaData.getATVisitas(pIDN, fields);		
//	}
	
//	public boolean guardarVisita(ATVisita visita) {
//		return infVisitaData.guardarVisita(visita);
//	}
	
//	public boolean guardarVisita(ATVisita visita, List<String> fields) {
//		return infVisitaData.guardarVisita(visita, fields);
//	}
	
//	public boolean guardarVisitaSup(ATVisita visita) {
//		return infVisitaData.guardarVisitaSup(visita);
//	}
	
	public Boolean deleteATVisita(String id_n, Integer nrovis) {
		return getDao().deleteATVisita(id_n, nrovis);
	}
	
//	public List<DelVisita> getDelVisitas(String pIDN){
//		return infVisitaData.getDelVisitas(pIDN);		
//	}
	
	public List<DelVisita> getDelVisitas(String pIDN, List<String> fields){
		return getDelVisitas(pIDN, null, fields);		
	}
	public List<DelVisita> getDelVisitas(INF_Caratula01 caratula) { 
		return getDao().getVisitas(caratula); 
	} 
	public List<DelVisita> getDelVisitasSec(INF_Caratula01 caratula, SeccionCapitulo... secciones) { 
		return getDao().getDelVisitas(caratula, secciones); 
	} 
	public DelVisita getDelVisitaSec(String id_n, Integer nro_vis, SeccionCapitulo... secciones) { 
		return getDao().getDelVisita(id_n, nro_vis, secciones); 
	} 
	
	public List<DelVisita> getDelVisitas(String pIDN, Integer nro_vis, List<String> fields){
		return getDao().getDelVisitas(pIDN, nro_vis, fields);		
	}
	
//	public boolean guardarVisita(DelVisita visita) {
//		return infVisitaData.guardarVisita(visita);
//	}
	
	public boolean guardarVisita(DelVisita visita, List<String> fields) {
		return getDao().guardarVisita(visita, fields);
	}
	public boolean saveOrUpdate(DelVisita bean, SeccionCapitulo... secciones) throws java.sql.SQLException { 
		return getDao().saveOrUpdate(bean, secciones); 
	} 
	
//	public boolean guardarVisitaSup(DelVisita visita) {
//		return infVisitaData.guardarVisitaSup(visita);
//	}
	
	public Boolean deleteDelVisita(String id_n, Integer nrovis) {
		return getDao().deleteDelVisita(id_n, nrovis);
	}
	public boolean borrarVisita(DelVisita bean) throws SQLException { 
		return borrarVisita(bean, MAINTENCE.ONEONLY); 
	}
	public boolean borrarVisita(DelVisita bean, MAINTENCE opcion) throws SQLException { 
		return getDao().borrarVisita(bean, opcion); 
	}
	
}
