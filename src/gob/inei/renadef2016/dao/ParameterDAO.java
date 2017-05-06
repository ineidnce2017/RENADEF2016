package gob.inei.renadef2016.dao;

import gob.inei.renadef2016.modelo.Parameter;
import gob.inei.dnce.dao.SQLiteDAO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ParameterDAO extends SQLiteDAO {
	
	public static ParameterDAO INSTANCE; 
	public static final String TABLA = "T_SISTEMA"; 

	private ParameterDAO(MyDatabaseHelper dbh) {
		super(dbh);
	}
	
	public static ParameterDAO getInstance(MyDatabaseHelper dbh) {
		if (INSTANCE == null) {
			INSTANCE = new ParameterDAO(dbh);
		}
		return INSTANCE;
	} 
	
	public Map<Integer,Parameter> getParameters() {
		Map<Integer,Parameter> parameter = new HashMap<Integer,Parameter>();
		Parameter bean = new Parameter();
		String[] campos = bean.getFieldsNames();
		SQLiteDatabase dbr = dbh.getReadableDatabase();		
		StringBuilder query = new StringBuilder();
		String camposSelect = getCamposSelect(Arrays.asList(campos));
		query.append("SELECT ").append(camposSelect).append(" ")
			.append("FROM ").append(TABLA).append(" ")
			.append("ORDER BY ID");		
		Cursor result = dbr.rawQuery(query.toString(), null);		
		while(result.moveToNext()){
			bean = new Parameter();
			bean = (Parameter)bean.fillEntity(result, campos);
			parameter.put(bean.id, bean);
		}		
		result.close();
		result = null;
		dbr.close();
		SQLiteDatabase.releaseMemory();
		return parameter;
	}
	
	public boolean saveOrUpdate(Parameter bean) {
		return this.saveOrUpdate(bean, null);
	}
	
	public boolean saveOrUpdate(Parameter bean, SQLiteDatabase dbTX) {
		if (dbTX == null) {
			dbTX = dbh.getWritableDatabase();
		}
		String oper = existeRegistro(dbTX, "ID", TABLA, "ID = ?", bean.id.toString()) ? "edit":"add";
		ContentValues content = new ContentValues();	
		String[] campos = bean.getFieldsSaveNames();	
		content = bean.getContentValues(content, -1, -1, campos);		
		boolean result;
		if (oper.equals("add")) {
			result = dbTX.insertOrThrow(TABLA, null, content)!=-1;
		} else {
			result = dbTX.update(TABLA, content, "ID = ?", new String[]{bean.id.toString()})>0;
		}		
		SQLiteDatabase.releaseMemory();		
		return result;
	}
		
	public boolean executeQuery(String query, SQLiteDatabase dbTX) {
		if (dbTX == null) {
			dbTX = dbh.getWritableDatabase();
		}				
		if (query == null) {
			return true;
		}
		if (query.trim().length() == 0) {
			return true;
		}
		boolean result = true;
		try {
			dbTX.execSQL(query);
		} catch (Exception e) {
			result = false;
		}		
		SQLiteDatabase.releaseMemory();		
		return result;
	}
	
	public boolean executeQuery(String query) {			
		return this.executeQuery(query, null);
	}
}
