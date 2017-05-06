package gob.inei.renadef2016.service;

import gob.inei.renadef2016.common.MyUtil.Periodo;
import gob.inei.renadef2016.dao.SegmentacionDAO;
import gob.inei.renadef2016.dao.SegmentacionDAO.Ruta;
import gob.inei.renadef2016.modelo.Marco;
import gob.inei.renadef2016.modelo.Segmentacion;
import gob.inei.renadef2016.modelo.Ubigeo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SegmentacionService extends Service {

	public static SegmentacionService INSTANCE = null;
	
	private SegmentacionService(Context ctx) {
		super(ctx);
	}
	
	public static SegmentacionService getInstance(Context ctx) {
		if (INSTANCE == null) {
			INSTANCE = new SegmentacionService(ctx);			
		}
		return INSTANCE;
	}
	
	private SegmentacionDAO getDao() {
		if (sqliteDAO == null) {
			sqliteDAO = SegmentacionDAO.getInstance(this.dbh);
		}
		return (SegmentacionDAO)sqliteDAO;
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

	public List<Periodo> getPeriodos() {
		return getDao().getPeriodos();
	}
	
	public List<Periodo> getPeriodos(Integer usuarioId) {
		return getDao().getPeriodos(usuarioId);
	}
	
	public List<Ubigeo> getDistritos(Integer periodo, String ccdd, String ccpp, Integer usuarioId) {
		return getDao().getDistritos(periodo, ccdd, ccpp, usuarioId);
	}
	
	public Map<String, Object> getTieneReCarga(String nsiscom) {
		return getDao().getTieneReCarga(nsiscom);
	}
	
	public boolean saveOrUpdate(Segmentacion bean, SQLiteDatabase dbTX) throws SQLException {
		return getDao().saveOrUpdate(bean, dbTX);
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
	
	public Map<String, Object> getMaxRegistro(String tableName, String campo, String where, String...whereValues) {
		return getDao().getMaxRegistro(tableName, campo, where, whereValues);
	}
	
	public Map<String, Object> getRegistro(String tableName, String[] campos, String where, String...whereValues) {
		return getDao().getRegistro(tableName, campos, where, whereValues);
	}
	
	public boolean delete(SQLiteDatabase dbTX, String tableName, Map<String, Object> map, String... pks) throws SQLException {
		return getDao().delete(dbTX, tableName, map, pks);
	}
	
	public List<Ruta> getRutas(String codigoSedeOperativa, String equipo) {
		return getDao().getRutas(codigoSedeOperativa, equipo);
	}
}
