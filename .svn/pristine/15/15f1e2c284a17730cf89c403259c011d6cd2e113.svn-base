package gob.inei.renadef2016.dao;

import gob.inei.dnce.dao.SQLiteDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import android.database.sqlite.SQLiteDatabase;

public class ReportesDAO extends SQLiteDAO {
	
	public static ReportesDAO INSTANCE;
	public static final String VISTA_REPORT_001 = "V_REPORT_001";
	public static final String VISTA_REPORT_002 = "V_REPORT_002";
	public static final String VISTA_REPORT_003 = "V_REPORT_003";
	public static final String VISTA_REPORT_004 = "V_REPORT_004";
	public static final String[] r001 = {"ID_N","TOTAL_DELITOS","DN121","SUM_FALLECIDOS","TOT200"};
	public static final String[] r002 = {"ID_N","NRO_MREG","ORDEN_200","IH214","IH215","TOT300","TOT400"};
	public static final String[] r003 = {"ID_N","ORDEN_300","NOMBRE"};
	public static final String[] r004 = {"ID_N","ORDEN_400","NOMBRE"};
	
	public static final String VISTA_REPORT_005 = "V_REPORT_005";
	public static final String VISTA_REPORT_006 = "V_REPORT_006";
	public static final String[] r005 = {"ID_N","NOMBRE","GPS","RESFIN"};
	public static final String[] r006 = {"ID_N","TIPOMODALIDAD","TOTAL"};
	public static final String[] r0061 = {"ID_N","DN101","DN102","DN103","DN104","DN105","DN106","DN107","DN108", 
		"DN109","DN110","DN111","DN112","DN113","DN114","DN115","DN116","DN117","DN118","DN119","DN120","FALTAS"};
	 
	private final String TAG = getClass().getSimpleName();

	private ReportesDAO(MyDatabaseHelper dbh) {
		super(dbh);
	}
	
	public static ReportesDAO getInstance(MyDatabaseHelper dbh) {
		if (INSTANCE == null) {
			INSTANCE = new ReportesDAO(dbh);
		}
		return INSTANCE;
	}
	
	public List<Map<String, Object>> getReport001() {
		return getRegistros(ReportesDAO.VISTA_REPORT_001, r001, null //"ID=? AND CODCCPP=? AND ZONA_ID=? AND MANZANA_ID=? AND FRENTE_ID=?", 
				/*frente.id.toString(), frente.codccpp, frente.zona_id, frente.manzana_id, frente.frente_id.toString()*/);		
	}
	
	public List<Map<String, Object>> getReport002(String id) {
		return getRegistros(ReportesDAO.VISTA_REPORT_002, r002, "? IN (\"99\", ID_N)",id);		
	}
	public List<Map<String, Object>> getReport003(String id, Integer mreg) {
		return getRegistros(ReportesDAO.VISTA_REPORT_003, r003, "? IN (ID_N) AND NRO_MREG=?",id, String.valueOf(mreg));		
	}
	public List<Map<String, Object>> getReport004(String id, Integer mreg) {
		return getRegistros(ReportesDAO.VISTA_REPORT_004, r004, "? IN (ID_N) AND NRO_MREG=?",id, String.valueOf(mreg));		
	}
	
	public boolean saveOrUpdate(SQLiteDatabase dbTX, String tableName, Map<String, Object> map, String... pks) throws SQLException {
		return super.saveOrUpdate(dbTX, tableName, map, pks);
	}
	
	public List<Map<String, Object>> getReport005() {
		return getRegistros(ReportesDAO.VISTA_REPORT_005, r005, null);		
	}
	public List<Map<String, Object>> getReport006(String id) {
		return getRegistros(ReportesDAO.VISTA_REPORT_006, r0061, "? IN (ID_N)",id);		
	}
	
	public Integer nextID(SQLiteDatabase dbTX, String tableName, String campo, String where, String... valores) {
		Integer id = null;
		id = super.nextID(dbTX, campo, tableName, where, valores);
		return id;
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String where, String...whereValues) {
		return super.getMaps(tableName, new String[]{"*"}, where, whereValues);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String[] campos, String where, String...whereValues) {
		return super.getMaps(tableName, campos, where, whereValues);
	}	
	
}

