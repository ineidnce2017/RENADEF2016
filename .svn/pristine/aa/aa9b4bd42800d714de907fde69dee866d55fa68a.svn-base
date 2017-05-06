package gob.inei.renadef2016.dao;


import gob.inei.dnce.components.Entity.SeccionCapitulo;
import gob.inei.dnce.dao.SQLiteDAO;
import gob.inei.renadef2016.modelo.Cobertura;

import java.util.List;
import java.util.Map;

public class CoberturaDAO extends SQLiteDAO {
	
	public static CoberturaDAO INSTANCE; 
	public static final String V_CPV0301_RURAL="V_COBERTURA_CENTRO_POBLADO_RURAL_CPV0301";
	public static final String V_CPV0301_URBANO="V_COBERTURA_CENTRO_POBLADO_URBANO_CPV0301";
	
	private final String TAG = getClass().getSimpleName();

	private CoberturaDAO(MyDatabaseHelper dbh) {
		super(dbh);
	}
	
	public static CoberturaDAO getInstance(MyDatabaseHelper dbh) {
		if (INSTANCE == null) {
			INSTANCE = new CoberturaDAO(dbh); 
		}
		return INSTANCE;
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String where, String...whereValues) {
		return super.getMaps(tableName, new String[]{"*"}, where, whereValues);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String[] campos, String where, String...whereValues) {
		return super.getMaps(tableName, campos, where, whereValues);
	}	
	
	public Cobertura getCapCob(String id, String vista) {
		Cobertura bean = (Cobertura) getBean(
				vista,
				"ID_N=?",
				new String[] { id }, "ID_N",
				Cobertura.class, -1,-1, "ESTADO");
		return bean;
	}	
}

