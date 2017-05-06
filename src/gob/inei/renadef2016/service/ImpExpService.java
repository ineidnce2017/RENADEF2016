package gob.inei.renadef2016.service; 


import gob.inei.dnce.dao.xml.XMLDataObject;
import gob.inei.renadef2016.dao.ImpExpDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ImpExpService extends Service { 
	private static ImpExpService INSTANCE = null;
	
	private ImpExpService(Context ctx) {
		super(ctx);
	}
	
	public static ImpExpService getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new ImpExpService(ctx);
		}
		return INSTANCE;
	}
	
	private ImpExpDAO getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = ImpExpDAO.getInstance(this.dbh);
		}
		return (ImpExpDAO)sqliteDAO;
	}
	
	public String[] getColumnNames(String tabla) {
		return getDao().getColumnNames(tabla);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String where, String...whereValues) {
		return getDao().getRegistros(tableName, where, whereValues);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String[] campos, String where, String...whereValues) {
		return getDao().getRegistros(tableName, campos, where, whereValues);
	}
	
	public String[] getCampos(String tableName) {
		return getDao().getCampos(tableName);
	}
	
	public boolean saveOrUpdate(SQLiteDatabase dbTX, XMLDataObject dataObjects) throws SQLException {
		return getDao().saveOrUpdate(dbTX, dataObjects);
	}
} 
