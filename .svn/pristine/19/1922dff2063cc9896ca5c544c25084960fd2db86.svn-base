package gob.inei.renadef2016.service;

import gob.inei.renadef2016.dao.PermisoDAO;
import gob.inei.renadef2016.modelo.Permiso;
import gob.inei.renadef2016.modelo.Rol;
import gob.inei.renadef2016.modelo.Rol.RolPermiso;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PermisoService extends Service {

	public static PermisoService INSTANCE = null;
	
	private PermisoService(Context ctx) {
		super(ctx);
	}
	
	public static PermisoService getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new PermisoService(ctx);
		}
		return INSTANCE;
	}
	
	private PermisoDAO getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = PermisoDAO.getInstance(this.dbh);
		}
		return (PermisoDAO)sqliteDAO;
	}
	
	public List<Permiso> getPermisos(Integer rol_id) {
		return getDao().getPermisos(rol_id);
	}
	
	public boolean saveOrUpdate(Permiso bean) throws SQLException {
		return this.saveOrUpdate(bean, null);
	}
	
	public boolean saveOrUpdate(Permiso bean, SQLiteDatabase dbTX) throws SQLException {
		return getDao().saveOrUpdate(bean, dbTX);
	}		
	
	public boolean saveOrUpdate(Rol bean) throws SQLException {
		return this.saveOrUpdate(bean, null);
	}
	
	public boolean saveOrUpdate(Rol bean, SQLiteDatabase dbTX) throws SQLException {
		return getDao().saveOrUpdate(bean, dbTX);
	}		
	
	public boolean saveOrUpdate(RolPermiso bean) throws SQLException {
		return this.saveOrUpdate(bean, null);
	}
	
	public boolean saveOrUpdate(RolPermiso bean, SQLiteDatabase dbTX) throws SQLException {
		return getDao().saveOrUpdate(bean, dbTX);
	}	
}
