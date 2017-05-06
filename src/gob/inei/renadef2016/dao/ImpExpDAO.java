package gob.inei.renadef2016.dao;

import gob.inei.dnce.dao.SQLiteDAO;
import gob.inei.dnce.dao.xml.XMLDataObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import android.database.sqlite.SQLiteDatabase;

public class ImpExpDAO  extends SQLiteDAO {
	public static ImpExpDAO INSTANCE;
	private String TAG = ImpExpDAO.this.getClass().getSimpleName(); 
	public static final String VISTA_EXP_CA = "V_MARCO_X_USUARIO";
	public static final String VISTA_EXP_ZONAS = "V_ZONAS_TOT";
	public static final String VISTA_MARCO_USUARIO_ASIG = "V_MARCO_X_USUARIO_ASIG_II";
	public static final String VISTA_EXP_SUBZONA = "V_SUBZONA_X_USUARIO";
	public static final String VISTA_EXP_MANZANA = "V_MANZANAS_X_USUARIO";
	public static final String VISTA_EXP_SUBZONA_MANZANA = "V_SUBZONA_MANZANA_X_USUARIO";
	public static final String VISTA_EXP_MANZANA_DETALLE = "V_MANZANA_DETALLE";
	public static final String VISTA_EXP_MANZANA_PROBLEMA = "V_MANZANA_PROBLEMA";
	public static final String VISTA_EXP_FRENTE = "V_FRENTES_X_USUARIO"; 

	private ImpExpDAO(MyDatabaseHelper dbh) {
		super(dbh);
	}
	
	public static ImpExpDAO getInstance(MyDatabaseHelper dbh) {
		if (INSTANCE == null) {
			INSTANCE = new ImpExpDAO(dbh);
		}
		return INSTANCE;
	}	

	
	
	public List<Map<String, Object>> getRegistros(String tableName, String where, String...whereValues) {
		return super.getMaps(tableName, new String[]{"*"}, where, whereValues);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String[] campos, String where, String...whereValues) {
		return super.getMaps(tableName, campos, where, whereValues);
	}
	
	public String[] getCampos(String tableName) {
		return super.getColumnNames(tableName);
	}
	
	public boolean saveOrUpdate(SQLiteDatabase dbTX, XMLDataObject dataObjects) throws SQLException {
		boolean isTX = true;
		boolean flag = true;
		if (dbTX == null) {
			isTX = false;
			dbTX = this.dbh.getWritableDatabase();
		}
		for (int i = 0; i < dataObjects.getRegistros().size() && flag; i++) {
			if (dataObjects.getRegistros().get(i).get(dataObjects.getPkFields().get(dataObjects.getPkFields().size()-1)) == null) {
				int nextID = super.nextID(dbTX, dataObjects.getPkFields().get(dataObjects.getPkFields().size()-1), dataObjects.getTableName(), 
						dataObjects.getStringWhereNextID(), dataObjects.getWhereValuesNextID(dataObjects.getRegistros().get(i)));
				dataObjects.getRegistros().get(i).put(dataObjects.getPkFields().get(dataObjects.getPkFields().size()-1),nextID);
			}
			flag = super.saveOrUpdate(dbTX, dataObjects.getTableName(), dataObjects.getRegistros().get(i), dataObjects.getPkFields().toArray(new String[dataObjects.getPkFields().size()]));
		}
		if (!isTX) {
			dbTX.close();
			dbTX = null;
			SQLiteDatabase.releaseMemory();
		}
		return flag;
	}
}
