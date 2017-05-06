package gob.inei.renadef2016.dao;

import gob.inei.dnce.components.Entity;
import gob.inei.dnce.dao.SQLiteDAO;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.MyUtil.Periodo;
import gob.inei.renadef2016.modelo.Segmentacion;
import gob.inei.renadef2016.modelo.Ubigeo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.database.sqlite.SQLiteDatabase;

public class SegmentacionDAO extends SQLiteDAO {
	
	public static SegmentacionDAO INSTANCE;

	public static final String TABLA = "T_SEGMENTACION"; 
	public static final String VISTA_SEGMENTACION = "V_SEGMENTACION";
	public static final String VISTA_MARCO_ASIGNADO = "V_MARCO_ASIGNADO_I";
	public static final String VISTA_MARCO_ASIGNADO_SIN_ID = "V_MARCO_ASIGNADO_SIN_ID_I";
	public static final String VISTA_MARCO_X_USUARIO = "V_MARCO_X_USUARIO";
	public static final String VISTA_CONGLO_X_USUARIO = "V_CONGLOMERADOS_X_USUARIO_I";
	public static final String VISTA_CCCP_X_USUARIO = "V_CENTRO_POBLADO_X_USUARIO_I";
//	public static final String TABLA_SEDES_OPERATIVA = "T_SEDES_OPERATIVAS";
//	public static final String TABLA_SEDES_OPERATIVA_UBIGEO = "T_SEDES_OPERATIVAS_UBIGEO";
	public static final String VISTA_ZONAS = "V_ZONAS";
	public static final String VISTA_ZONAS_CARTO = "V_ZONAS_CARTO_I";
	public static final String VISTA_MANZANAS = "V_MANZANAS";
	public static final String VISTA_MANZANAS_CARTO = "V_MANZANAS_CARTO_I";
	public static final String VISTA_MANZANAS_DETALLE_CARTO = "V_MANZANAS_DETALLE_CARTO_I";
	public static final String VISTA_MANZANAS_REPLANTEO_CARTO = "V_MANZANAS_REPLANTEADA_CARTO_I";
	public static final String VISTA_FRENTES_CARTO = "V_FRENTES_CARTO_I";
	 
	public static final int PERIODO_TODOS = 99; 
	public static final int PERIODO_SELECCIONE = 0;
	private final String TAG = getClass().getSimpleName();

	private SegmentacionDAO(MyDatabaseHelper dbh) {
		super(dbh);
	}
	
	public static SegmentacionDAO getInstance(MyDatabaseHelper dbh) {
		if (INSTANCE == null) {
			INSTANCE = new SegmentacionDAO(dbh);
		}
		return INSTANCE;
	}

	public List<Periodo> getPeriodos() {
		List<Periodo> periodos = new ArrayList<Periodo>();
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT PERIODO, PERIODO_DESC").append(" ")
			.append("FROM ").append(VISTA_SEGMENTACION).append(" ")
			.append("ORDER BY PERIODO").append(" ");
		cursor = dbr.rawQuery(query.toString(), new String[]{});		
		while(cursor.moveToNext()){
			Periodo p = new Periodo();
			p.periodo = getInt("PERIODO");
			p.periodo_desc = getString("PERIODO_DESC");
			periodos.add(p);
		}		
		cursor.close();
		cursor = null;
		dbr.close();
		SQLiteDatabase.releaseMemory();
		return periodos;
	}
	
	public List<Periodo> getPeriodos(Integer usuarioId) {
		List<Periodo> periodos = new ArrayList<Periodo>();
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT PERIODO, PERIODO_DESC").append(" ")
//			.append("FROM ").append(VISTA_SEGMENTACION).append(" ")
		.append("FROM (").append(" ")
		.append(" SELECT 1 AS ORDEN, '-- SELECCIONE --' AS ZONA,  '000' AS MZA, '00' AS CCDD, '00' AS CCPP, '00' AS CCDI, 0 AS PERIODO, '000' AS EQUIPO, '00' AS RUTA, NULL USUARIO_ID, '-- SELECCIONE --' AS PERIODO_DESC, 0 AS ORDEN UNION ALL").append(" ")
		.append(" SELECT 2 AS ORDEN, NULL AS ZONA, NULL AS MZA, Seg.CCDD, Seg.CCPP, Seg.CCDI, Seg.PERIODO, Seg.EQUIPO, Seg.RUTA, Seg.USUARIO_ID,PERIODO||CASE PERIODO WHEN 1 THEN 'er' WHEN 2 THEN 'do' WHEN 3 THEN 'er' WHEN 4 THEN 'to' WHEN 5 THEN 'to' WHEN 6 THEN 'to' WHEN 7 THEN 'mo' WHEN 8 THEN 'vo' WHEN 9 THEN 'no' WHEN 10 THEN 'mo' ELSE 'vo' END||' PERIODO' AS PERIODO_DESC, NULL ORDEN").append(" ")
		.append("FROM (").append(MarcoDAO.querySelect(null,null,null,null,null,usuarioId,null)).append(") Seg UNION ALL").append(" ")
		.append(" SELECT 3 AS ORDEN, '-- TODOS --' AS ZONA,  '000' AS MZA, '00' AS CCDD, '00' AS CCPP, '00' AS CCDI, 99 AS PERIODO, '000' AS EQUIPO, '00' AS RUTA, NULL USUARIO_ID, '-- TODOS --' AS PERIODO_DESC, 0 AS ORDEN").append(" ")
		.append(" ORDER BY CCDD, CCPP, CCDI, PERIODO, ORDEN").append(") ")
			
			.append(" WHERE ORDEN IN (1,3) OR USUARIO_ID=").append(usuarioId).append(" ")
			.append("ORDER BY PERIODO").append(" ");
		cursor = dbr.rawQuery(query.toString(), new String[]{});		
		while(cursor.moveToNext()){
			Periodo p = new Periodo();
			p.periodo = getInt("PERIODO");
			p.periodo_desc = getString("PERIODO_DESC");
			periodos.add(p);
		}		
		cursor.close();
		cursor = null;
		dbr.close();
		SQLiteDatabase.releaseMemory();
		return periodos;
	}
	
	public List<Ubigeo> getDistritos(Integer periodo, String ccdd, String ccpp, Integer usuarioId) {
		StringBuilder query = new StringBuilder();		
		String[] campos = new Ubigeo().getFieldsNames();
		query.append("SELECT DISTINCT ").append(getCamposSelect("cp",campos)).append(" ")
			.append("FROM ").append(UbigeoDAO.TABLA).append(" cp").append(" ")
//			.append("JOIN ").append(VISTA_SEGMENTACION).append(" s ON s.CCDD||s.CCPP||s.CCDI = cp.UBIGEO").append(" ")
			.append("JOIN (").append(MarcoDAO.querySelect(null,null,null,null,null,usuarioId,periodo)).append(") s ON s.UBIGEO = cp.UBIGEO").append(" ")
			.append("WHERE cp.CCDD='").append(ccdd).append("' AND cp.CCPP='").append(ccpp).append("' ");
		if (periodo != PERIODO_TODOS) {
			query.append("  AND (s.PERIODO=").append(periodo).append(" OR s.PERIODO=0)").append(" "); 
		}
		if (App.getInstance().getUsuario().equipo != null) {
			query.append("  AND (s.EQUIPO='").append(App.getInstance().getUsuario().equipo).append("' OR s.EQUIPO='000')").append(" ");
		}
		if (App.getInstance().getUsuario().ruta != null) {
			query.append("  AND (s.RUTA='").append(App.getInstance().getUsuario().ruta).append("' OR s.RUTA='00')").append(" ");
		}
		query.append("ORDER BY cp.CCDI").append(" ");
		return getBeans(new Query(query.toString()), Ubigeo.class, campos);
	}
	
	public Map<String, Object> getTieneReCarga(String nsiscom) {
		return getMap(MarcoDAO.TABLA_RECARGA, new String[]{"MARCO_ID"}, "MARCO_ID=?", nsiscom);		
	}
	
	public boolean saveOrUpdate(SQLiteDatabase dbTX, String tableName, Map<String, Object> map, String... pks) throws SQLException {
		return super.saveOrUpdate(dbTX, tableName, map, pks);
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
	
	public Map<String, Object> getMaxRegistro(String tableName, String campo, String where, String...whereValues) {
		return super.getMaxMap(tableName, campo, where, whereValues);
	}	
	
	public Map<String, Object> getRegistro(String tableName, String[] campos, String where, String...whereValues) {
		return super.getMap(tableName, campos, where, whereValues);
	}
	
	public boolean delete(SQLiteDatabase dbTX, String tableName, Map<String, Object> map, String... pks) throws SQLException {
//		Log.e(TAG, "Tabla:" + tableName);
//		Log.e(TAG, "Map:" + map.toString());
//		Log.e(TAG, "PKs:" + Arrays.toString(pks));
		String where = "";
		String[] values = new String[pks.length];
		for (int i = 0; i < pks.length; i++) {
			where += pks[i] + "=? AND ";
			values[i] = Util.getText(map.get(pks[i]));
		}
		where = where.substring(0, where.length()-5);		
		return super.borrar(dbTX, tableName, where, values);
	}
	
	public boolean saveOrUpdate(Segmentacion bean, SQLiteDatabase dbTX) throws SQLException {
		String[] campos = bean.getFieldsSaveNames(); 
		if (bean.id == null) { 
			Integer nextID = super.nextID(dbTX, "ID", TABLA, null); 
			bean.id = nextID; 
		} else {
			boolean flag1 = true;
//			if (bean.zona == null || "".equals(bean.zona)) {
//				flag1 = existeRegistro(dbTX, "ID", TABLA, "CCDD=? AND CCPP=? AND CCDI=? AND CODCCPP=? AND PERIODO=?", 
//						bean.ccdd, bean.ccpp, bean.ccdi, bean.codccpp, bean.periodo.toString());
//			} else {
//				flag1 = existeRegistro(dbTX, "ID", TABLA, "CCDD=? AND CCPP=? AND CCDI=? AND CODCCPP=? AND ZONA=? AND PERIODO=?", 
//						bean.ccdd, bean.ccpp, bean.ccdi, bean.codccpp, bean.zona, bean.periodo.toString());
//			}
			if (flag1) {
				return true;
			} else {
				Integer nextID = super.nextID(dbTX, "ID", TABLA, null); 
				bean.id = nextID;
			}
		}
		return this.saveOrUpdate(dbTX, TABLA, "ID=?", bean,
				new String[]{"id"}, -1, -1, campos); 
	
	}	
	
	public static class Ruta extends Entity {
		public static final String NINGUNA="000";
		public static final String TODOS="999";
		private static final long serialVersionUID = 1L;
		public String codigoSede;
		public String equipo;
		public String ruta;
		public String rutaCodigo;
		
		@Override
		public String toString() {
			if (TODOS.equals(rutaCodigo) || NINGUNA.equals(rutaCodigo)) {
				return ruta;
			}
			return "RUTA " + ruta;
		}	
		
		public String getFullRuta() {
			return codigoSede + "[" + equipo + "-" + Util.getText(rutaCodigo,"") + "]";
		}
	}
	
	
	public List<Ruta> getRutas(String codigoSedeOperativa, String equipo) {
		List<Ruta> rutas = new ArrayList<Ruta>();
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT COD_SEDE_OPERATIVA, EQUIPO, RUTA").append(" ")
			.append("FROM ").append(TABLA).append(" ")
			.append("WHERE COD_SEDE_OPERATIVA = ? AND RUTA IS NOT NULL").append(" ");
		if (equipo != null) {
			query.append(" AND EQUIPO='").append(equipo).append("' ").append(" ");
		}
		query.append("ORDER BY RUTA").append(" ");
		cursor = dbr.rawQuery(query.toString(), new String[]{codigoSedeOperativa});		
		while(cursor.moveToNext()){
			Ruta p = new Ruta();
			p.equipo = getString("EQUIPO");
			p.ruta = getString("RUTA");
			p.rutaCodigo = getString("RUTA");
			p.codigoSede = getString("COD_SEDE_OPERATIVA");
			rutas.add(p);
		}		
		cursor.close();
		cursor = null;
		dbr.close();
		SQLiteDatabase.releaseMemory();
		return rutas;
	}
	
}

