package gob.inei.renadef2016.service;
import gob.inei.renadef2016.dao.CoberturaDAO;
import gob.inei.renadef2016.modelo.Cobertura;

import java.util.List;
import java.util.Map;

import android.content.Context;

public class CoberturaService extends Service {

	public static CoberturaService INSTANCE = null;
	
	private CoberturaService(Context ctx) {
		super(ctx);
	}
	
	public static CoberturaService getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new CoberturaService(ctx);			
		}
		return INSTANCE;
	}
	
	private CoberturaDAO getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = CoberturaDAO.getInstance(this.dbh);
		}
		return (CoberturaDAO)sqliteDAO;
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String where, String...whereValues) {
		return getDao().getRegistros(tableName, where, whereValues);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String[] campos, String where, String...whereValues) {
		return getDao().getRegistros(tableName, campos, where, whereValues);
	}
	
	public Cobertura getCapCob(String id, String vista) {
		return getDao().getCapCob(id, vista);
	}
}
