package gob.inei.renadef2016.service;

import gob.inei.renadef2016.dao.ReportesDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ReportesService extends Service {

	public static ReportesService INSTANCE = null;
	
	private ReportesService(Context ctx) {
		super(ctx);
	}
	
	public static ReportesService getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new ReportesService(ctx);			
		}
		return INSTANCE;
	}
	
	private ReportesDAO getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = ReportesDAO.getInstance(this.dbh);
		}
		return (ReportesDAO)sqliteDAO;
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
	
	public List<Map<String, Object>> getReport001() {
		return getDao().getReport001();
	}
	
	public List<Map<String, Object>> getReport002() {
		return getReport002("99");
	}
	public List<Map<String, Object>> getReport002(String id) {
		return getDao().getReport002(id);
	}
	public List<Map<String, Object>> getReport003(String id, Integer mreg) {
		return getDao().getReport003(id, mreg);
	}
	public List<Map<String, Object>> getReport004(String id, Integer mreg) {
		return getDao().getReport004(id, mreg);
	}
	
	public List<Map<String, Object>> getReport005() {
		return getDao().getReport005();
	}
	public List<Map<String, Object>> getReport006(String id) {
		return getDao().getReport006(id);
	}
	
	public boolean saveOrUpdate(SQLiteDatabase dbTX, String tableName, Map<String, Object> map, String... pks) throws SQLException {
		return getDao().saveOrUpdate(dbTX, tableName, map, pks);
	}
	
	public Integer nextID(SQLiteDatabase dbTX, String tableName, String campo, String where, String... valores) {
		return getDao().nextID(dbTX, tableName, campo, where, valores);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String where, String...whereValues) {
		return getDao().getRegistros(tableName, where, whereValues);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String[] campos, String where, String...whereValues) {
		return getDao().getRegistros(tableName, campos, where, whereValues);
	}
}
