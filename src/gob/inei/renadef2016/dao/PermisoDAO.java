package gob.inei.renadef2016.dao;

import gob.inei.renadef2016.modelo.Permiso;
import gob.inei.renadef2016.modelo.Rol;
import gob.inei.renadef2016.modelo.Rol.RolPermiso;
import gob.inei.dnce.dao.SQLiteDAO;

import java.sql.SQLException;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;

public class PermisoDAO extends SQLiteDAO {
	
	public static PermisoDAO INSTANCE; 
	public static final String TABLA_PERMISO = "T_PERMISO"; 
	public static final String TABLA_ROL = "T_ROL";
	public static final String TABLA_ROLES_PERMISOS = "T_ROLES_PERMISOS";
	public static final String TABLA_PREGUNTAS = "T_PREGUNTAS";
	public static final String TABLA_RANGOS = "T_RANGOS";
	public static final String TABLA_RANGO_PREGUNTA = "T_RANGO_PREGUNTA";
	public static final String TABLA_CAMPOS = "T_CAMPOS";
	public static final String TABLA_ALTERNATIVAS = "T_ALTERNATIVAS";
	public static final String VISTA_PERMISO = "V_PERMISOS";
 
	private PermisoDAO(MyDatabaseHelper dbh) {
		super(dbh);
	}
	
	public static PermisoDAO getInstance(MyDatabaseHelper dbh) {
		if (INSTANCE == null) {
			INSTANCE = new PermisoDAO(dbh);
		}
		return INSTANCE;
	}
	
	public List<Permiso> getPermisos(Integer rol_id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ID, ROL_ID, DESCRIPCION").append(" ")
			.append("FROM ").append(VISTA_PERMISO).append(" ")			
			.append("WHERE ROL_ID=? ").append(" ")
			.append("ORDER BY ID");		
		return getBeans(new Query(query.toString(), rol_id.toString()), Permiso.class, "ID", "ROL_ID", "DESCRIPCION");
	}
	
	public boolean saveOrUpdate(Permiso bean) throws SQLException {
		return this.saveOrUpdate(bean, null);
	}
	
	public boolean saveOrUpdate(Permiso bean, SQLiteDatabase dbTX) throws SQLException {
		String[] campos = bean.getFieldsSaveNames(); 
		return saveOrUpdate(dbTX, TABLA_PERMISO, "ID=?", bean, new String[]{"id"}, -1, -1, campos);
	}		
	
	public boolean saveOrUpdate(Rol bean) throws SQLException {
		return this.saveOrUpdate(bean, null);
	}
	
	public boolean saveOrUpdate(Rol bean, SQLiteDatabase dbTX) throws SQLException {
		String[] campos = bean.getFieldsSaveNames(); 
		return saveOrUpdate(dbTX, TABLA_ROL, "ID=?", bean, new String[]{"id"}, -1, -1, campos);
	}		
	
	public boolean saveOrUpdate(RolPermiso bean) throws SQLException {
		return this.saveOrUpdate(bean, null);
	}
	
	public boolean saveOrUpdate(RolPermiso bean, SQLiteDatabase dbTX) throws SQLException {
		String[] campos = bean.getFieldsSaveNames(); 
		return saveOrUpdate(dbTX, TABLA_ROLES_PERMISOS, "ID=?", bean, new String[]{"id"}, -1, -1, campos);
	}	
}
