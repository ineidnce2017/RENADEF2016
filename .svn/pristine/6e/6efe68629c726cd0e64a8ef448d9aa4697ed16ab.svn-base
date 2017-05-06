package gob.inei.renadef2016.service;

import java.sql.SQLException;
import java.util.List;

import gob.inei.renadef2016.dao.MyDatabaseHelper;
import gob.inei.renadef2016.dao.UsuarioDAO;
import gob.inei.renadef2016.modelo.Usuario;
import gob.inei.renadef2016.dao.UsuarioDAO;
import gob.inei.renadef2016.modelo.Personal;
import gob.inei.renadef2016.service.Service;
import gob.inei.renadef2016.service.UsuarioService;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioService extends Service {
	
public static UsuarioService INSTANCE = null;
	
	private UsuarioService(Context ctx) {
		super(ctx);
	}
	
	public static UsuarioService getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioService(ctx);
		}
		return INSTANCE;
	}
	
	private UsuarioDAO getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = UsuarioDAO.getInstance(this.dbh);
		}
		return (UsuarioDAO)sqliteDAO;
	}
	
	public Usuario getUsuario(String login, String clave) {
		return getDao().getUsuario(login, clave);
	}
	
	public boolean saveOrUpdate(Usuario bean) throws SQLException {
		return getDao().saveOrUpdate(bean);
	}
	
	public boolean saveOrUpdate(Usuario bean, SQLiteDatabase dbTX) throws SQLException {
		return getDao().saveOrUpdate(bean, dbTX);
	}
	
	public List<Usuario> getUsuarios(String cod_sede_operativa, String ccdd, String ccpp, 
			String ccdi, String equipo, Integer usuarioId, Integer tipoSegmentacion) {
		return getDao().getUsuarios(cod_sede_operativa, ccdd, ccpp, ccdi, equipo, usuarioId, tipoSegmentacion);
	}
	
	public List<Usuario> getUsuarios(String cod_sede_operativa, String ccdd, String ccpp, 
			String ccdi, String equipo, Integer usuarioId) {
		return getDao().getUsuarios(cod_sede_operativa, ccdd, ccpp, ccdi, equipo, usuarioId);
	}
	
	public boolean saveOrUpdate(Personal bean) throws SQLException {
		return getDao().saveOrUpdate(bean);
	}
	
	public boolean saveOrUpdate(Personal persona, Usuario usuario) throws SQLException {
		return getDao().saveOrUpdate(persona, usuario);
	}
	
	public boolean deleteOrSave(Personal persona, Usuario usuario, String dni) throws SQLException {
		return getDao().deleteOrSave(persona, usuario, dni);
	}
	
	public Personal getPersonal(String dni) {
		return getDao().getPersonal(dni);
	}
	
	public Personal getUsuarioPersonal(Integer id) {
		return getDao().getUsuarioPersonal(id);
	}

}
