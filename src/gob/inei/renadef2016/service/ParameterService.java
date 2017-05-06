package gob.inei.renadef2016.service;

import gob.inei.renadef2016.dao.ParameterDAO;
import gob.inei.renadef2016.modelo.Parameter;

import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ParameterService extends Service {

	public static ParameterService INSTANCE = null;
	
	private ParameterService(Context ctx) {
		super(ctx);
	}
	
	public static ParameterService getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new ParameterService(ctx);
		}
		return INSTANCE;
	}
	
	private ParameterDAO getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = ParameterDAO.getInstance(this.dbh);
		}
		return (ParameterDAO)sqliteDAO;
	}
	
	public Map<Integer,Parameter> getParameters() {
		return getDao().getParameters();
	}
	
	public boolean saveOrUpdate(Parameter bean) {
		return getDao().saveOrUpdate(bean);
	}
	
	public boolean saveOrUpdate(Parameter bean, SQLiteDatabase dbTX) {
		return getDao().saveOrUpdate(bean, dbTX);
	}
	
	public boolean executeQuery(String query) {	
		return getDao().executeQuery(query);
	}
}
