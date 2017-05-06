package gob.inei.renadef2016.service;

import gob.inei.renadef2016.dao.SegmentacionDAO.Ruta;
import gob.inei.renadef2016.dao.UbigeoDAO;
import gob.inei.renadef2016.modelo.Ubigeo;
import gob.inei.renadef2016.modelo.Usuario;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UbigeoService extends Service {

	public static UbigeoService INSTANCE = null;
	
	private UbigeoService(Context ctx) {
		super(ctx);
	}
	
	public static UbigeoService getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new UbigeoService(ctx);
		}
		return INSTANCE;
	}
	
	private UbigeoDAO getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = UbigeoDAO.getInstance(this.dbh);
		}
		return (UbigeoDAO)sqliteDAO;
	}
	
	public boolean saveOrUpdate(Ubigeo bean) {
		return getDao().saveOrUpdate(bean);
	}
	
	public boolean saveOrUpdate(Ubigeo bean, SQLiteDatabase dbTX) {
		return getDao().saveOrUpdate(bean, dbTX);
	}
	
	public List<Ubigeo> getDepartamentos(String cod_sede) {
		return getDao().getDepartamentos(cod_sede);
	}
	
	public Ubigeo getDepartamento(String ccdd) {
		return getDao().getDepartamento(ccdd);
	}
	
	public List<Ubigeo> getProvincias(String cod_sede, String ccdd) {
		return getDao().getProvincias(cod_sede, ccdd);
	}
	
	public Ubigeo getProvincia(String ccdd, String ccpp) {
		return getDao().getProvincia(ccdd, ccpp);
	}
	
	public List<Ubigeo> getDistritos(String cod_sede, String ccdd, String ccpp) {
		return getDao().getDistritos(cod_sede, ccdd, ccpp);		
	}
	
//	public List<Ubigeo> getDistritos(Integer periodo, String cod_sede, String ccdd, String ccpp, Integer usuarioId) {
//		return getDao().getDistritos(periodo, cod_sede, ccdd, ccpp, usuarioId);		
//	}
	
	public Ubigeo getDistrito(String ccdd, String ccpp, String ccdi) {
		return getDao().getDistrito(ccdd, ccpp, ccdi);
	}
	
	public List<Usuario> getUser(String ccdd, String ccpp, String ccdi, String codccpp, String zonaid) {
		return getDao().getUser(ccdd, ccpp, ccdi, codccpp, zonaid);
	}
}
