package gob.inei.renadef2016.dao;

import gob.inei.dnce.components.Entity.SeccionCapitulo;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.dao.SQLiteDAO;
import gob.inei.dnce.dao.xml.XMLDataObject;
import gob.inei.dnce.util.Util;
import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.common.App.MAINTENCE;
import gob.inei.renadef2016.common.MyUtil.Periodo;
import gob.inei.renadef2016.common.MyUtil.Ruta;
//import gob.inei.renadef2016.fragments.C3CAP300_Fragment_301.ListVictimas;
import gob.inei.renadef2016.modelo.C100udt;
import gob.inei.renadef2016.modelo.Cuest;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.renadef2016.modelo.ListaVictimas;
import gob.inei.renadef2016.modelo.Marco;
import gob.inei.renadef2016.modelo.Navegation;
import gob.inei.renadef2016.modelo.Search;
import gob.inei.renadef2016.modelo.Segmentacion;
import gob.inei.renadef2016.modelo.Ubigeo;
import gob.inei.renadef2016.modelo.Usuario;
import gob.inei.renadef2016.modelo.delitos.Cap100Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap200Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap300Delitos;
import gob.inei.renadef2016.modelo.delitos.Cap400Delitos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MarcoDAO extends SQLiteDAO {
	
	private static String TAG = "MarcoDAO";
	
	public static MarcoDAO INSTANCE;
	public static final String TABLA_USUARIO = "T_USUARIO"; 
	public static final String TABLA_MARCO = "T_MARCO";
	public static final String TABLA_CCCP = "T_CENTRO_POBLADO";
	public static final String TABLA_CCCP_FRACCIONADO = "T_CENTRO_POBLADO_FRACCIONADO";
	public static final String TABLA_ZONA = "T_ZONA";
	public static final String TABLA_SUBZONA = "T_SUBZONA";
	public static final String TABLA_CARGA = "T_CARGA";
	public static final String TABLA_RECARGA = "T_REUBICAR_CARGA";
	public static final String VISTA_MAX_CARGA = "V_MAX_CARGA";
	public static final String VISTA_MAX_FICHA = "V_MAX_FICHA";
	public static final String VISTA_MARCO_INFO = "V_MARCO_INFO";
	public static final String TABLA_SUBZONA_SEGMENTADA = "T_SUBZONA_MANZANA";   
	public static final String TABLA_MANZANA = "T_MANZANA";
	public static final String TABLA_MANZANA_DETALLE = "T_MANZANA_DETALLE";
	public static final String TABLA_MANZANA_REPLANTEO = "T_MANZANA_REPLANTEADA";
	public static final String TABLA_MANZANA_PROBLEMA = "T_MANZANA_PROBLEMA";
	public static final String TABLA_FRENTE = "T_FRENTE"; 
	public static final String VISTA_FRENTES = "V_FRENTES"; 
	public static final String TABLA_FRENTE_SECUNDARIO = "T_FRENTE_VERTICES"; 
	public static final String V_MANZANA_SEGMENTADA = "V_MANZANAS_SEGMENTADAS";
	public static final String TABLA_PREGUNTA = "T_PREGUNTAS"; 
	public static final String TABLA_ERROR = "T_ERRORES_MANZANAS"; 
	public static final String TABLA_TRACKS = "T_05_DIG_TRACKS"; 
	
	public static final String TABLA_DEP_ASIG = "T_DEP_ASIG";
	public static final String TABLA_CARATULA = "T_DIG_01";
	public static final String TABLA_VISITA = "T_DIG_03_VISITA";
	public static final String TABLA_C100 = "T_DIG_03_100";
	public static final String TABLA_C200 = "T_DIG_03_200";
	public static final String TABLA_C300 = "T_DIG_03_300";
	public static final String TABLA_C400 = "T_DIG_03_400";
	
	public static final String VISTA_NAVEGATION = "V_NAVEGATION";
	public static final String VISTA_MODALIDADES = "V_MODALIDADES";
	public static final String V_LISTAVICTIMAS = "V_LISTAVICTIMAS";
	public static final String TABLA_MODALIDADES = "T_MODALIDADES"; 

	private MarcoDAO(MyDatabaseHelper dbh) {
		super(dbh);
	}
	
	public static MarcoDAO getInstance(MyDatabaseHelper dbh) {
		if (INSTANCE == null) {
			INSTANCE = new MarcoDAO(dbh);
		}
		return INSTANCE;
	}
	
	public static String querySelect(Integer id, String codccpp, String zona_id, Integer usuarioId, Integer periodo){
		return querySelect(id, codccpp, zona_id, null, usuarioId, periodo);
	}
	public static String querySelect(Integer id, String codccpp, String zona_id, String mza, Integer usuarioId, Integer periodo){
		return querySelect(id, null, codccpp, zona_id, mza, usuarioId, periodo);
	}
	public static String querySelect(Integer id, String ubigeo, String codccpp, String zona_id, String mza, Integer usuarioId, Integer periodo){
		StringBuilder queryString = new StringBuilder();
		String where = (periodo!=null && periodo != SegmentacionDAO.PERIODO_TODOS ? " AND (PERIODO="+periodo+" OR PERIODO=0)":"");
		String whereU = (usuarioId != null ? " AND u.ID="+usuarioId+" ":"");
		String[] whereLog = new String[]{String.valueOf(id), codccpp, zona_id, mza, String.valueOf(usuarioId), String.valueOf(periodo)};
		Log.e("informacion", "informacion: "+Arrays.toString(whereLog));
		if(id!=null && codccpp!=null && zona_id !=null){
			String whereM = mza != null ? " AND m.MANZANA_ID = '"+mza+"' " : "";
//			Log.e("chekaaaaa", "vvvvvvv: "+(!zona_id.equals("999")));
			queryString.append("SELECT DISTINCT t.id, c.CODCCPP, d.ubigeo, u.ID AS USUARIO_ID, u.USUARIO, s.CCDD, s.CCPP, s.CCDI, s.PERIODO, z.ZONA_ID AS ZONA, m.MANZANA_ID AS MZAM, IFNULL(md.MANZANA_PADRE_ID,m.MANZANA_ID) AS MZA_ORIGEN, md.ESTADO, s.EQUIPO, s.RUTA, t.conglomerado "
					+ "FROM T_CONGLOMERADO t "
					+ "INNER JOIN T_CENTRO_POBLADO c ON c.id = t.id AND t.id = "+id+(!codccpp.equals("9999")?" AND c.CODCCPP='"+codccpp+"' ":" ") //" AND c.CODCCPP='"+codccpp+"' "
					+ "INNER JOIN T_ZONA z ON z.id = c.id AND z.CODCCPP = c.CODCCPP "+(!zona_id.equals("999")?" AND z.ZONA_ID = '"+zona_id+"' ":"")
					+ "INNER JOIN T_MANZANA m ON m.id = z.id AND m.CODCCPP = z.CODCCPP AND m.ZONA_ID = z.ZONA_ID "+whereM+" "
					+ "LEFT JOIN T_MANZANA_DETALLE md ON md.ID=m.ID AND md.CODCCPP=m.CODCCPP AND md.ZONA_ID=m.ZONA_ID AND md.MANZANA_FINAL_ID=m.MANZANA_ID "
					+ "INNER JOIN T_UBIGEO d ON d.UBIGEO = t.UBIGEO "
					+ "INNER JOIN T_SEGMENTACION s ON s.conglomerado = t.conglomerado  and s.CCDD||s.CCPP||s.CCDI=d.UBIGEO AND s.CODCCPP=c.CODCCPP "
					+ "AND IFNULL(trim(s.ZONA),z.ZONA_ID) = z.ZONA_ID AND (IFNULL(trim(s.MZA),m.MANZANA_ID) = m.MANZANA_ID OR trim(s.MZA) = md.MANZANA_PADRE_ID) "+where+ " "
					+ "INNER JOIN T_USUARIO u ON u.COD_SEDE_OPERATIVA = s.COD_SEDE_OPERATIVA "+whereU+" "
					+ "AND COALESCE(u.EQUIPO,s.EQUIPO,'00') = IFNULL(s.EQUIPO,'00') AND COALESCE(u.RUTA,s.RUTA,'00') = IFNULL(s.RUTA,'00') ").append(" ");
//			Log.e("chekaaaaa", "vvvvvvv: "+(queryString.toString()));
		} else if(id!=null && codccpp!=null){
			queryString.append("SELECT DISTINCT t.id, c.CODCCPP, d.ubigeo, u.ID AS USUARIO_ID, u.USUARIO, s.CCDD, s.CCPP, s.CCDI, s.PERIODO, z.ZONA_ID AS ZONA, s.EQUIPO, s.RUTA, t.conglomerado "
					+ "FROM T_CONGLOMERADO t "
					+ "INNER JOIN T_CENTRO_POBLADO c ON c.id = t.id AND t.id = "+id+(!codccpp.equals("9999")?" AND c.CODCCPP='"+codccpp+"' ":" ") //" AND c.CODCCPP='"+codccpp+"' "
					+ "INNER JOIN T_ZONA z ON z.id = c.id AND z.CODCCPP = c.CODCCPP "
					+ "INNER JOIN T_UBIGEO d ON d.UBIGEO = t.UBIGEO "
					+ "INNER JOIN T_SEGMENTACION s ON s.conglomerado = t.conglomerado  and s.CCDD||s.CCPP||s.CCDI=d.UBIGEO AND s.CODCCPP=c.CODCCPP "
					+ "AND IFNULL(trim(s.ZONA),z.ZONA_ID) = z.ZONA_ID "+where+ " "
					+ "INNER JOIN T_USUARIO u ON u.COD_SEDE_OPERATIVA = s.COD_SEDE_OPERATIVA "+whereU+" "
					+ "AND COALESCE(u.EQUIPO,s.EQUIPO,'00') = IFNULL(s.EQUIPO,'00') AND COALESCE(u.RUTA,s.RUTA,'00') = IFNULL(s.RUTA,'00') ").append(" ");
		} else if(id!=null){
			queryString.append("SELECT DISTINCT t.id, c.CODCCPP, d.ubigeo, u.ID AS USUARIO_ID, u.USUARIO, s.CCDD, s.CCPP, s.CCDI, s.PERIODO, s.EQUIPO, s.RUTA, t.conglomerado "
					+ "FROM T_CONGLOMERADO t "
					+ "INNER JOIN T_CENTRO_POBLADO c ON c.id = t.id AND t.id = "+id+" "
					+ "INNER JOIN T_UBIGEO d ON d.UBIGEO = t.UBIGEO "
					+ "INNER JOIN T_SEGMENTACION s ON s.conglomerado = t.conglomerado  and s.CCDD||s.CCPP||s.CCDI=d.UBIGEO AND s.CODCCPP=c.CODCCPP "+where+ " "
					+ "INNER JOIN T_USUARIO u ON u.COD_SEDE_OPERATIVA = s.COD_SEDE_OPERATIVA "+whereU+" "
					+ "AND COALESCE(u.EQUIPO,s.EQUIPO,'00') = IFNULL(s.EQUIPO,'00') AND COALESCE(u.RUTA,s.RUTA,'00') = IFNULL(s.RUTA,'00') ").append(" ");
		} else if(ubigeo!=null){
			queryString.append("SELECT DISTINCT t.id, c.CODCCPP, d.ubigeo, u.ID AS USUARIO_ID, u.USUARIO, s.CCDD, s.CCPP, s.CCDI, s.PERIODO, s.EQUIPO, s.RUTA, t.conglomerado "
					+ "FROM T_CONGLOMERADO t "
					+ "INNER JOIN T_CENTRO_POBLADO c ON c.id = t.id "
					+ "INNER JOIN T_UBIGEO d ON d.UBIGEO = t.UBIGEO AND t.UBIGEO = '"+ubigeo+"' "
					+ "INNER JOIN T_SEGMENTACION s ON s.conglomerado = t.conglomerado  and s.CCDD||s.CCPP||s.CCDI=d.UBIGEO AND s.CODCCPP=c.CODCCPP "+where+ " "
					+ "INNER JOIN T_USUARIO u ON u.COD_SEDE_OPERATIVA = s.COD_SEDE_OPERATIVA "+whereU+" "
					+ "AND COALESCE(u.EQUIPO,s.EQUIPO,'00') = IFNULL(s.EQUIPO,'00') AND COALESCE(u.RUTA,s.RUTA,'00') = IFNULL(s.RUTA,'00') ").append(" ");
		} else {
			queryString.append("SELECT DISTINCT t.id, c.CODCCPP, d.ubigeo, u.ID AS USUARIO_ID, u.USUARIO, s.CCDD, s.CCPP, s.CCDI, s.PERIODO, s.EQUIPO, s.RUTA, t.conglomerado "
					+ "FROM T_CONGLOMERADO t "
					+ "INNER JOIN T_CENTRO_POBLADO c ON c.id = t.id "
					+ "INNER JOIN T_UBIGEO d ON d.UBIGEO = t.UBIGEO "
					+ "INNER JOIN T_SEGMENTACION s ON s.conglomerado = t.conglomerado  and s.CCDD||s.CCPP||s.CCDI=d.UBIGEO AND s.CODCCPP=c.CODCCPP "+where+ " "
					+ "INNER JOIN T_USUARIO u ON u.COD_SEDE_OPERATIVA = s.COD_SEDE_OPERATIVA "+whereU+" "
					+ "AND COALESCE(u.EQUIPO,s.EQUIPO,'00') = IFNULL(s.EQUIPO,'00') AND COALESCE(u.RUTA,s.RUTA,'00') = IFNULL(s.RUTA,'00') ").append(" ");
		}
		return queryString.toString();
	}
	
	public static String querySelect1(Integer id, String codccpp, String zona_id, Integer usuarioId, Integer periodo){
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT DISTINCT vmu.conglomerado, m.*, vmu.USUARIO_ID, vmu.EQUIPO, vmu.RUTA, vmu.ubigeo, vmu.PERIODO, COALESCE(v.COUNTV, 0) AS TOTAL_FRENTES, md.ESTADO AS ESTADO_FINAL, /*IFNULL(x.COUNTS, 0)*/ 0 AS TOTAL_CNX").append(" ")
		.append("FROM T_MANZANA m").append(" ")
		.append("INNER JOIN (").append(querySelect(id,codccpp,zona_id,usuarioId,periodo)).append(") vmu ON vmu.id = m.id AND vmu.codccpp = m.codccpp AND vmu.zona = m.zona_id AND vmu.mzam = m.manzana_id").append(" ")
		.append("LEFT JOIN T_MANZANA_DETALLE md ON md.ID=m.ID AND md.CODCCPP=m.CODCCPP AND md.ZONA_ID=m.ZONA_ID AND md.MANZANA_FINAL_ID=m.MANZANA_ID").append(" ")
		.append("LEFT JOIN (SELECT ID, CODCCPP, ZONA_ID, MANZANA_ID, COUNT(ID) AS COUNTV FROM T_FRENTE").append(" ")
		.append("GROUP BY ID, CODCCPP, ZONA_ID, MANZANA_ID) v ON m.ID=v.ID AND m.CODCCPP=v.CODCCPP AND m.ZONA_ID=v.ZONA_ID AND m.MANZANA_ID=v.MANZANA_ID").append(" ");
		return queryString.toString();
	}
//	
//	public static String querySelect2(Integer id, String codccpp, String zona_id, Integer usuarioId, Integer periodo){
//		StringBuilder queryString = new StringBuilder();
//		queryString.append("SELECT t.id, m.conglomerado, m.CODCCPP, m.ZONA_I AS ZONA_ID, IFNULL(md.MANZANA_PADRE_ID,mz.MANZANA_ID) as MANZANA_ID, count(m.nsiscom) AS COUNTS FROM T_MARCO m").append(" ")
//		.append("INNER JOIN T_CONGLOMERADO t on t.conglomerado = trim(m.conglomerado)").append(" ")
//		.append("INNER JOIN T_CENTRO_POBLADO c ON c.id = t.id and c.CODCCPP = trim(m.CODCCPP)").append(" ")
//		.append("INNER JOIN T_ZONA z ON z.id = c.id AND z.CODCCPP = c.CODCCPP  and z.ZONA_ID = trim(m.zona_i)").append(" ")
//		.append("INNER JOIN T_MANZANA mz ON mz.id = z.id AND mz.CODCCPP = z.CODCCPP AND mz.ZONA_ID = z.ZONA_ID ").append(" ")
//		.append("LEFT JOIN T_MANZANA_DETALLE md ON md.ID=mz.ID AND md.CODCCPP=mz.CODCCPP AND md.ZONA_ID=mz.ZONA_ID AND md.MANZANA_FINAL_ID=mz.MANZANA_ID").append(" ")
//		.append("WHERE IFNULL(CASE WHEN md.ESTADO NOT IN (2) THEN md.MANZANA_PADRE_ID ELSE MZ.MANZANA_ID END,MZ.MANZANA_ID)  = trim(m.MZA_I) ").append(" ")
//		.append("AND t.id = ").append(id).append(" AND m.CODCCPP = '").append(codccpp).append("' AND m.ZONA_I = '").append(zona_id).append("' ")
//		.append("GROUP BY m.conglomerado, m.CODCCPP, m.ZONA_I, IFNULL(md.MANZANA_PADRE_ID,mz.MANZANA_ID)").append(" ");
//		return queryString.toString();
//	}
	
	public static String querySelect3(Integer id, String codccpp, Integer usuarioId, Integer periodo){
//		String where = (periodo != SegmentacionDAO.PERIODO_TODOS ? " AND (PERIODO="+periodo+" OR PERIODO=0)":"");
//		String whereU = (usuarioId != null ? " AND u.ID="+usuarioId+" ":"");
		StringBuilder queryString = new StringBuilder();
//		queryString.append("select m.id, m.codccpp, m.zona_id AS ZONA,u.id as USUARIO_ID,s.EQUIPO, count(m.id) AS CONTMZAS from T_MANZANA M").append(" ")
//		.append("LEFT JOIN T_MANZANA_DETALLE md ON md.ID=m.ID AND md.CODCCPP=m.CODCCPP AND md.ZONA_ID=m.ZONA_ID AND md.MANZANA_FINAL_ID=m.MANZANA_ID").append(" ")
//		.append("INNER JOIN T_CONGLOMERADO T ON T.ID = M.ID AND T.ID = ").append(id).append(" ")
//		.append("INNER JOIN T_UBIGEO d ON d.UBIGEO = t.UBIGEO ").append(" ")
//		.append("INNER JOIN T_SEGMENTACION s ON s.conglomerado = t.conglomerado  and s.CCDD||s.CCPP||s.CCDI=d.UBIGEO AND s.CODCCPP='").append(codccpp).append("' ")
//		.append("AND (IFNULL(trim(s.MZA),m.MANZANA_ID) = m.MANZANA_ID OR trim(s.MZA) = md.MANZANA_PADRE_ID) ").append(where).append(" ")
//		.append("INNER JOIN T_USUARIO u ON u.COD_SEDE_OPERATIVA = s.COD_SEDE_OPERATIVA ").append(whereU).append(" ")
//		.append("WHERE COALESCE(u.EQUIPO,s.EQUIPO,'00') = IFNULL(s.EQUIPO,'00') AND COALESCE(u.RUTA,s.RUTA,'00') = IFNULL(s.RUTA,'00')").append(" ")
//		.append("GROUP BY M.ID, M.[CODCCPP], M.ZONA_ID,u.ID,s.EQUIPO").append(" ");
		
//		String where = (periodo != SegmentacionDAO.PERIODO_TODOS ? " AND (PERIODO="+periodo+" OR PERIODO=0)":"");
//		String whereU = (usuarioId != null ? " WHERE ID="+usuarioId+" ":"");
//		queryString.append("select m.id, m.codccpp, m.zona_id AS ZONA, count(m.id) AS CONTMZAS from T_MANZANA M").append(" ")
//		.append("LEFT JOIN T_MANZANA_DETALLE md ON md.ID=m.ID AND md.CODCCPP=m.CODCCPP AND md.ZONA_ID=m.ZONA_ID AND md.MANZANA_FINAL_ID=m.MANZANA_ID").append(" ")
//		.append("INNER JOIN (SELECT DISTINCT MZA FROM T_CONGLOMERADO T").append(" ")
//		.append("INNER JOIN T_SEGMENTACION S ON T.CONGLOMERADO = S.CONGLOMERADO AND T.ID = ").append(id).append(" ")
//		.append("INNER JOIN (SELECT * FROM T_USUARIO ").append(whereU).append(") ").append(" u ON COALESCE(u.EQUIPO,s.EQUIPO,'00') = IFNULL(s.EQUIPO,'00') AND COALESCE(u.RUTA,s.RUTA,'00') = IFNULL(s.RUTA,'00') ").append(" ")
//		.append("WHERE s.CODCCPP='").append(codccpp).append("' ").append(where).append(") s ON (IFNULL(trim(s.MZA),m.MANZANA_ID) = m.MANZANA_ID OR trim(s.MZA) = md.MANZANA_PADRE_ID)").append(" ")
//		.append("GROUP BY M.ID, M.[CODCCPP], M.ZONA_ID ").append(" ");
//		return queryString.toString();
		
		String where = (periodo != SegmentacionDAO.PERIODO_TODOS ? " AND (PERIODO="+periodo+" OR PERIODO=0)":"");
		String whereU = (usuarioId != null ? " WHERE ID="+usuarioId+" ":"");
		queryString.append("select m.id, m.codccpp, m.zona_id AS ZONA, count(m.id) AS CONTMZAS FROM T_CONGLOMERADO T").append(" ")
		.append("INNER JOIN (SELECT DISTINCT * FROM T_SEGMENTACION) S ON T.CONGLOMERADO = S.CONGLOMERADO AND T.ID = ").append(id).append(" ")
		.append("INNER JOIN (SELECT * FROM T_MANZANA WHERE ID = ").append(id).append(" AND CODCCPP = '").append(codccpp).append("') M ON M.ZONA_ID = S.ZONA /*AND M.[MANZANA_ID] = S.MZA*/").append(" ")
		.append("LEFT JOIN (SELECT ID, CODCCPP, ZONA_ID, MANZANA_FINAL_ID, MIN(MANZANA_PADRE_ID) MANZANA_PADRE_ID FROM T_MANZANA_DETALLE").append(" ")
		.append("GROUP BY ID, CODCCPP, ZONA_ID, MANZANA_FINAL_ID) md ON md.ID=m.ID AND md.CODCCPP=m.CODCCPP AND md.ZONA_ID=m.ZONA_ID AND md.MANZANA_FINAL_ID=m.MANZANA_ID").append(" ")
		.append("INNER JOIN (SELECT * FROM T_USUARIO ").append(whereU).append(") u ON COALESCE(u.EQUIPO,s.EQUIPO,'00') = IFNULL(s.EQUIPO,'00') AND COALESCE(u.RUTA,s.RUTA,'00') = IFNULL(s.RUTA,'00')").append(" ")
		.append("WHERE s.CODCCPP='").append(codccpp).append("' ").append(where).append(" AND (IFNULL(trim(s.MZA),m.MANZANA_ID) = m.MANZANA_ID OR trim(s.MZA) = md.MANZANA_PADRE_ID)").append(" ")
		.append("GROUP BY M.ID, M.[CODCCPP], M.ZONA_ID ").append(" ");
		return queryString.toString();
	}
	
	public static String querySelect4(Integer id, String codccpp, String zona_id, String mza, String campos){
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT ").append(campos).append(" FROM ").append(SegmentacionDAO.VISTA_MARCO_ASIGNADO_SIN_ID).append(" ")
		.append("WHERE ORDEN IS NOT NULL AND ").append(" ")
		.append("(ID IS NULL OR (ID =").append(id).append(" AND (CODCCPP IS NULL OR (CODCCPP='").append(codccpp).append("' ")
		.append("AND (ZONA_I IS NULL OR (ZONA_I='").append(zona_id).append("' AND (MZA_I IS NULL OR MZA_I='").append(mza).append("'))))))) ")
		.append("ORDER BY ORDEN ").append(" ");
		return queryString.toString();
	}
	
	public boolean existeRegistro(String campo, String tabla,
			String condicion, String... valores) {
		return existeRegistro(dbh.getReadableDatabase(), campo,
				tabla, condicion, valores);
	}

	public boolean existeRegistro(SQLiteDatabase dbwTx, String campo,
			String tabla, String condicion, String... valores) {
		Cursor cursor = dbwTx.rawQuery("SELECT " + campo + " FROM " + tabla
				+ " WHERE " + condicion, valores);
		int count = cursor.getCount();
		cursor.close();
		cursor = null;
		return count > 0;
	}
	
	private String existeRegistro(String[] campos, String tabla,
			String condicion, String... valores) {
		return existeRegistro(dbh.getReadableDatabase(), campos,
				tabla, condicion, valores);
	}
	
	private String existeRegistro(SQLiteDatabase dbwTx, String[] campos,
			String tabla, String condicion, String... valores) {
		Cursor cursor = dbwTx.rawQuery("SELECT " + getFieldsSel(campos) + " FROM " + tabla
				+ " WHERE " + condicion, valores);
		String result = null;
		if(cursor.moveToNext()){
			result = "";
			for(String c : campos){
				result += cursor.getString(cursor.getColumnIndex(c)) + ",";
			}
		}
		cursor.close();
		cursor = null;
		dbwTx.close();
		dbwTx = null;
		return result;
	}
	
	private String getFieldsSel(String[] fields){
		String field = "";
		if(fields != null){
			for(String c : fields)
				field = field + c +", ";
		}
		int len = field.length();
		return len>1?field.substring(0, len-2):field;
	}
	
	public boolean existeComisaria(Marco marco) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		boolean bres=false;
		
		try {
			cursor=db.rawQuery("SELECT ID_N FROM T_DIG_02 WHERE ID_N ='" + marco.id_n + "'",null);
			bres= cursor.getCount()>0;
		} catch (Exception e) {
			Log.d(TAG, e.getMessage());
		} finally {
			cursor.close();
			db.close();
		}
		return bres;
	}
	
	public boolean existeCap100Delitos(String id_n) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		boolean bres=false;
		
		try {
			cursor=db.rawQuery("SELECT ID_N FROM "+TABLA_C100+" WHERE ID_N ='" + id_n +"'", null);
			bres= cursor.getCount()>0;
		} catch (Exception e) {
			Log.d(TAG, e.getMessage());
		} finally {
			cursor.close();
			db.close();
		}
		return bres;
	}
	
	public boolean existeCap200Delitos(String id_n, Integer nro_mreg) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		boolean bres=false;
		
		try {
			cursor=db.rawQuery("SELECT ID_N FROM "+TABLA_C200+" WHERE ID_N ='" + id_n + "' AND NRO_MREG="+nro_mreg,null);
			bres= cursor.getCount()>0;
		} catch (Exception e) {
			Log.d(TAG, e.getMessage());
		} finally {
			cursor.close();
			db.close();
		}
		return bres;
	}
	
	public boolean existeCap300Delitos(String id_n, Integer nro_mreg, Integer nro_vfreg) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		boolean bres=false;
		
		try {
			cursor=db.rawQuery("SELECT ID_N FROM "+TABLA_C300+" WHERE ID_N ='" + id_n + "' AND NRO_MREG="+nro_mreg+
					" AND NRO_VFREG="+nro_vfreg,null);
			bres= cursor.getCount()>0;
		} catch (Exception e) {
			Log.d(TAG, e.getMessage());
		} finally {
			cursor.close();
			db.close();
		}
		return bres;
	}
	
	public boolean existeCap400Delitos(String id_n, Integer nro_mreg, Integer nro_pvreg) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		boolean bres=false;
		
		try {
			cursor=db.rawQuery("SELECT ID_N FROM "+TABLA_C400+" WHERE ID_N ='" + id_n + "' AND NRO_MREG="+nro_mreg+
					" AND NRO_PVREG="+nro_pvreg,null);
			bres= cursor.getCount()>0;
		} catch (Exception e) {
			Log.d(TAG, e.getMessage());
		} finally {
			cursor.close();
			db.close();
		}
		return bres;
	}
	
	public List<Marco> getMarco(String id_n) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		List<Marco> lstMarcos = new ArrayList<Marco>();
		
		try {
	        //String[] filtro = new String[]{"%" + id_n + "%"};
	        //cursor=db.rawQuery("SELECT ID_N,NOMCOMISARIA,NOMBREDD,NOMBREPP,NOMBREDI FROM T_Marco WHERE ID_N like ?",filtro);
			
			if (id_n == "")
				cursor=db.rawQuery("SELECT ID_N,NOMCOMISARIA, /*NOMBREDD,NOMBREPP,NOMBREDI,*/ VINFRA, VAT, VDELI FROM T_Marco",null);
			else
				cursor=db.rawQuery("SELECT ID_N,NOMCOMISARIA, /*NOMBREDD,NOMBREPP,NOMBREDI,*/ VINFRA, VAT, VDELI FROM T_Marco WHERE ID_N like '%" + id_n + "%'",null);
	        
	        Marco marco;
	        while(cursor.moveToNext()){
	        	marco = new Marco();
	        	marco.id_n= cursor.getString(cursor.getColumnIndex("ID_N"));
	        	marco.nomcomisaria = cursor.getString(cursor.getColumnIndex("NOMCOMISARIA"));
	        	//marco.nombredd = cursor.getString(cursor.getColumnIndex("NOMBREDD"));
	        	//marco.nombrepp = cursor.getString(cursor.getColumnIndex("NOMBREPP"));
	        	//marco.nombredi = cursor.getString(cursor.getColumnIndex("NOMBREDI"));
	        	
	        	//*********
	        	marco.vdeli = cursor.getString(cursor.getColumnIndex("VDELI"))==null?null:cursor.getInt(cursor.getColumnIndex("VDELI"));
	        	lstMarcos.add(marco);
	        }
			
		} catch (Exception e) {
			Log.d(TAG,e.getMessage());
			
		} finally {
			cursor.close();
			db.close();
		}
		
		return lstMarcos;

	}	
	
	public List<Marco> listarmarcoComisarias(String ruta, String codSedeOp, Integer periodo, FragmentForm.OPCION opcion) {
		StringBuilder query = new StringBuilder();		
		String fields = "", fields1 = "";
		Usuario u = App.getInstance().getUsuario();
		switch (opcion) {
			case ONE: fields = null;break;// EXPORTACION
			case TWO: fields = "u.CCDI, u.DISTRITO AS NOMBREDI, u.CCDD, u.CCPP, c.V3_1 AS AREA,"
					+ "u.PROVINCIA AS NOMBREPP, u.DEPARTAMENTO AS NOMBREDD, "
					+ (u.cargo_id != App.REGISTRADOR ? "FECHAIC" : "FECHAI") + " AS FECHAI, "
					+ (u.cargo_id != App.REGISTRADOR ? "FECHAFC" : "FECHAF") + " AS FECHAF";
					fields1 = "CCDI,NOMBREDI,CCDD,CCPP,NOMBREPP,NOMBREDD,AREA,FECHAI,FECHAF";break;
			default:break;
		}
//		query.append("SELECT DISTINCT ").append(Marco.getFieldsExport("m",opcion,fields)).append(",c.IVRESFIN_03 AS RESFIN").append(" ")
//			.append("FROM ").append(TABLA_MARCO).append(" m ")
//			.append("LEFT JOIN (SELECT ID_N, V3_1, IVRESFIN_03 FROM ").append(TABLA_CARATULA).append(") c ON c.ID_N=m.ID_N").append(" ")
//			.append("INNER JOIN ").append(UbigeoDAO.TABLA).append(" u ON m.UBIGEO=u.UBIGEO").append(" ")
//			.append("INNER JOIN (SELECT DISTINCT S.ID_N, U.COD_SEDE_OPERATIVA, S.EQUIPO, S.RUTA, S.PERIODO, s.SUPERVISA FROM T_SEGMENTACION S").append(" ")
//			.append("INNER JOIN T_USUARIO U ON "+
//			(u.cargo_id!=App.REGISTRADOR?"CAST(s.SUPERVISA AS INT) = U.ID":"U.COD_SEDE_OPERATIVA = S.COD_SEDE_OPERATIVA")).append(") ")
//			.append("s on s.COD_SEDE_OPERATIVA='").append(codSedeOp).append("' ")
////			.append(" s ON s.COD_SEDE_OPERATIVA = m.COD_DEP_ASIG").append(" ")
//			.append("WHERE m.VDELI=1 AND s.periodo=").append(periodo.toString())
//			.append((Ruta.TODOS.equals(ruta)?" ":" AND s.ruta='"+ruta+"' "))
//			.append("AND m.ID_N = s.ID_N").append(" ")
////			.append("AND ").append(u.id).append(" = CASE WHEN "+u.cargo_id+" NOT IN (1) THEN CAST(s.SUPERVISA AS INT) ELSE "+u.id+" END").append(" ")
//			.append("ORDER BY m.FECHAI ASC").append(" ");
		
		
		query.append("SELECT DISTINCT ").append(Marco.getFieldsExport("m",opcion,fields)).append(",c.IVRESFIN_03 AS RESFIN").append(" ")
		.append("FROM ").append(TABLA_MARCO).append(" m ")
		.append("LEFT JOIN (SELECT ID_N, V3_1, IVRESFIN_03 FROM ").append(TABLA_CARATULA).append(") c ON c.ID_N=m.ID_N").append(" ")
		.append("INNER JOIN ").append(UbigeoDAO.TABLA).append(" u ON m.UBIGEO=u.UBIGEO").append(" ")
		.append("INNER JOIN (SELECT DISTINCT SE.ID_N, U.COD_SEDE_OPERATIVA, SE.EQUIPO, SE.RUTA, SE.PERIODO, SE.SUPERVISA FROM T_SEGMENTACION SE").append(" ")
		.append("INNER JOIN T_USUARIO U ON U.COD_SEDE_OPERATIVA = SE.COD_SEDE_OPERATIVA AND U.RUTA=SE.RUTA").append(") S").append(" ")
		.append("ON S.COD_SEDE_OPERATIVA= m.COD_SEDE_OPERATIVA AND S.ID_N=m.ID_N").append(" ")
		.append("WHERE m.VDELI=1 AND s.periodo=").append(periodo.toString()).append(" ")
		.append("AND S.COD_SEDE_OPERATIVA='").append(codSedeOp).append("' ")
		.append("AND S.RUTA='").append(ruta).append("' ")
		.append("AND m.ID_N = s.ID_N").append(" ")
		.append("ORDER BY m.FECHAI ASC").append(" ");
		
		return getBeans(new Query(query.toString()), Marco.class, 
				Marco.getFieldsExport(null,opcion,fields1+",RESFIN").split("\\,"));
		

	}

//	public ArrayList<Marco> listarmarcoComisarias(Integer periodo) {
//		periodo = periodo == null?-1:periodo;
//		ArrayList<Marco> listMarco = new ArrayList<Marco>();
//		SQLiteDatabase bd=dbh.getReadableDatabase();	
//	        try {
//	        String query = 
//	        "SELECT DISTINCT m.AGREGADO, s.PERIODO, m.COD_REGION, m.REGION, s.RUTA, m.VDELI, m.UBIGEO, U.CCDI AS IDDIST, u.DISTRITO NOMBDIST, u.CCDD IDDPTO, U.CCPP AS IDPROV, u.PROVINCIA NOMBPROV, u.DEPARTAMENTO NOMBDEP, "+
//	        " m.ID_N, m.NOMCOMISARIA, m.DIRTEPOL, m.TIPO, m.COMISARIO, m.TIPOCPNP, m.NOMBRECP, m.COD_DEP_ASIG "+
//	        "FROM T_Marco m "+
//	        "LEFT JOIN "+TABLA_CARATULA+" carinf ON carinf.ID_N=m.ID_N "+
//	        "INNER JOIN T_UBIGEO u ON m.UBIGEO=u.UBIGEO "+
//	        "INNER JOIN T_USUARIO us ON m.COD_REGION=us.COD_SEDE_OPERATIVA "+
//	        "INNER JOIN T_SEGMENTACION s ON s.COD_SEDE_OPERATIVA = us.COD_SEDE_OPERATIVA "
//	        + "AND s.RUTA=CASE WHEN us.RUTA IS NULL THEN s.RUTA ELSE us.RUTA END "+
//	        "AND m.[ID_N] = CASE WHEN s.[ID_N] IS NULL THEN m.[ID_N] ELSE s.[ID_N] END " +
//	        "WHERE m.VDELI=1 AND s.periodo=? AND us.id=? "+
//	        "ORDER BY m.ID_N ASC";
//	        
//	        cursor=bd.rawQuery(query, new String[]{periodo.toString(), App.getInstance().getUsuario().id.toString()});
//	        Log.e("cursor", "size: "+cursor.getCount()); 	     	
//	   	 	while (cursor.moveToNext()) {
//	   	 		
//	   	 	String id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//	   	 	String nombcomisaria = cursor.getString(cursor.getColumnIndex("NOMCOMISARIA"));
//	   	 	String codreg = cursor.getString(cursor.getColumnIndex("COD_REGION"));
//	   	 	String nomreg = cursor.getString(cursor.getColumnIndex("REGION"));
//	   	 	String ruta = cursor.getString(cursor.getColumnIndex("RUTA"));
//	   	    String ubigeo = cursor.getString(cursor.getColumnIndex("UBIGEO"));
//	   	 	String ccdd = cursor.getString(cursor.getColumnIndex("IDDPTO"));
//	   	 	String nombredd = cursor.getString(cursor.getColumnIndex("NOMBDEP"));
//	   	 	String ccpp = cursor.getString(cursor.getColumnIndex("IDPROV"));
//	   	 	String nombrepp = cursor.getString(cursor.getColumnIndex("NOMBPROV"));
//	   	 	String ccdi = cursor.getString(cursor.getColumnIndex("IDDIST"));
//	   	 	String nombredi = cursor.getString(cursor.getColumnIndex("NOMBDIST"));
//	   	    String cod_dep_asig = cursor.getString(cursor.getColumnIndex("COD_DEP_ASIG"));
//	   	 	Integer vdeli = cursor.getString(cursor.getColumnIndex("VDELI"))==null?null:cursor.getInt(cursor.getColumnIndex("VDELI"));
//	   	 	int agregado = cursor.getString(cursor.getColumnIndex("AGREGADO"))==null?0:cursor.getInt(cursor.getColumnIndex("AGREGADO"));
//	   	 		   	 
//	   	 	Marco marco = new Marco(id_n, nombcomisaria, nomreg, nombredd, nombrepp, nombredi, vdeli);
//	   	 	marco.agregado = agregado;
//	   	 	marco.cod_region = codreg;
//	   	 	marco.ubigeo = ubigeo;
//	   	 	marco.ccdd = ccdd;
//	   	 	marco.ccpp = ccpp;
//	   	 	marco.ccdi = ccdi;
//	   	 	marco.cod_dep_asig = cod_dep_asig;
//	   	 	marco.dirtepol = cursor.getString(cursor.getColumnIndex("DIRTEPOL"));
//	   	 	marco.tipo = cursor.getString(cursor.getColumnIndex("TIPO"));
//	   	 	marco.comisario = cursor.getString(cursor.getColumnIndex("COMISARIO"));
//	   	 	marco.tipocpnp = cursor.getString(cursor.getColumnIndex("TIPOCPNP"))==null?null:cursor.getInt(cursor.getColumnIndex("TIPOCPNP"));
//	   	 	marco.nombrecp = cursor.getString(cursor.getColumnIndex("NOMBRECP"));
//	   	 	marco.periodo = cursor.getString(cursor.getColumnIndex("PERIODO"))==null?null:cursor.getInt(cursor.getColumnIndex("PERIODO"));
//	   	 	listMarco.add(marco);
//	   	 			//Log.d("-++++++++++++++++---------", listMarco.get(listMarco.size()-1).id_n + "#_____#" + listMarco.get(listMarco.size()-1).digitadoinf);
//		        }	            	           
//	           } catch (Exception e) {
//	        	   Log.d("inei",e.getMessage());
//	         } finally {
//	        	cursor.close();
//	        	bd.close();
//	             }
//	        return listMarco;
//	        }
	
	public List<Ruta> getRutas(String codigoSedeOperativa, String equipo, Integer cargo) {
		List<Ruta> rutas = new ArrayList<Ruta>();
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		StringBuilder query = new StringBuilder();
		Integer uid = App.getInstance().getUsuario().id;
		query.append("SELECT DISTINCT S.COD_SEDE_OPERATIVA, S.EQUIPO, S.RUTA").append(" ")
			.append("FROM ").append(SegmentacionDAO.TABLA).append(" S ").append(" ")
			.append("INNER JOIN ").append(TABLA_USUARIO).append(" U ").append(" ON S.COD_SEDE_OPERATIVA=U.COD_SEDE_OPERATIVA AND S.RUTA=U.RUTA ")
			.append("WHERE S.COD_SEDE_OPERATIVA = ? AND S.RUTA IS NOT NULL").append(" ")
			.append("AND U.ID=").append(uid.toString()).append(" ");
//			.append("AND "+uid+" = CASE WHEN "+cargo+" NOT IN (1) THEN CAST(SUPERVISA AS INT) ELSE "+uid+" END ").append(" ");  
//			.append("AND ").append(uid).append(" = IFNULL(CAST(SUPERVISA AS INT), ").append(uid).append(") "); 
		if (equipo != null) {
			query.append(" AND S.EQUIPO='").append(equipo).append("' ").append(" ");
		}
		query.append("ORDER BY S.RUTA").append(" ");
		cursor = dbr.rawQuery(query.toString(), new String[]{codigoSedeOperativa});
		while(cursor.moveToNext()){
			Ruta p = new Ruta();
			p.equipo = getString("EQUIPO");
			p.ruta = getString("RUTA");
			p.codruta = getString("RUTA");
			p.codigoSede = getString("COD_SEDE_OPERATIVA");
			rutas.add(p);
		}		
		cursor.close();
		cursor = null;
		dbr.close();
		SQLiteDatabase.releaseMemory();
		return rutas;
	}
	
	public List<Periodo> getPeriodos(String ruta, String codSedeOp, Integer cargo, String cod) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		List<Periodo> periodos = new ArrayList<Periodo>();
		Integer uid = App.getInstance().getUsuario().id;
		try {                          	        
//	        cursor = db.rawQuery("SELECT DISTINCT s.PERIODO AS PERIODO, 'PERIODO N\u00B0 '||CAST(s.PERIODO AS VARCHAR) AS PERIODO_DESC FROM T_Marco m "
//	        		+ "INNER JOIN T_SEGMENTACION s ON s.COD_SEDE_OPERATIVA = m.COD_DEP_ASIG "
//	        		+ "INNER JOIN T_USUARIO u ON "+(cargo!=1?"CAST(S.SUPERVISA AS INT)=U.ID ":"u.COD_SEDE_OPERATIVA=s.COD_SEDE_OPERATIVA ")
////	        		+ "m.COD_SEDE_OPERATIVA=s.COD_SEDE_OPERATIVA "
//	        	+ "WHERE u.COD_SEDE_OPERATIVA=? "
//	        	+ (Ruta.TODOS.equals(cod)?"":"AND s.ruta= '"+ruta+"' ")
//	        	+" AND "+uid+" = CASE WHEN "+cargo+" NOT IN (1) THEN CAST(S.SUPERVISA AS INT) ELSE "+uid+" END "  
//	        	+ "ORDER BY s.PERIODO ASC", new String[]{codSedeOp}); 
	        cursor = db.rawQuery("SELECT DISTINCT S.PERIODO AS PERIODO, 'PERIODO N\u00B0 '||CAST(S.PERIODO AS VARCHAR) AS PERIODO_DESC FROM "
	        	+ "T_SEGMENTACION S "
	        	+ "INNER JOIN T_USUARIO U ON U.COD_SEDE_OPERATIVA=s.COD_SEDE_OPERATIVA AND U.RUTA=S.RUTA "
	        	+ "WHERE u.COD_SEDE_OPERATIVA=? AND S.RUTA=?"
	        	+ "ORDER BY S.PERIODO ASC", new String[]{codSedeOp,ruta}); 
	        
	        
	 	
	        
	        while(cursor.moveToNext()){
	        	Periodo p = new Periodo();
				p.periodo = getInt("PERIODO");
				p.periodo_desc = getString("PERIODO_DESC");
				periodos.add(p);
	        }
			
		} catch (Exception e) {
			Log.d(TAG,e.getMessage());	
		} finally {
		       Log.d("QUERY", "SELECT DISTINCT s.PERIODO AS PERIODO, 'PERIODO N\u00B0 '||CAST(s.PERIODO AS VARCHAR) AS PERIODO_DESC FROM "
			        	+ "T_SEGMENTACION s "
			        	+ "INNER JOIN T_USUARIO u ON u.COD_SEDE_OPERATIVA=s.COD_SEDE_OPERATIVA AND U.RUTA=S.RUTA "
			        	+ "WHERE u.COD_SEDE_OPERATIVA=? "
			        	+ "ORDER BY s.PERIODO ASC");
			cursor.close();
			cursor = null;
			db.close();
			SQLiteDatabase.releaseMemory();
		}
		
		return periodos;
	}
	
//	public ArrayList<Marco> listarmarcoComisariasExportar() {
//		ArrayList<Marco> listMarco = new ArrayList<Marco>();
//		SQLiteDatabase bd=dbh.getReadableDatabase();	
//	        try {
//	        String query = 
//	        "SELECT distinct m.REGION, m.VINFRA, m.VAT, m.VDELI, IDDIST, NOMBDIST, IDDPTO, IDPROV, NOMBPROV, NOMBDEP, "+
//	        "m.ID_N, m.NOMCOMISARIA, c.VTOTAL, c.CANTIDIGIT, "+
//	        "(CASE WHEN (((carinf.IVRESFIN_02 IS NOT NULL) AND ((carinf.IVRESFIN_02 = 1) OR (carinf.IVRESFIN_02 = 7) OR (carinf.IVRESFIN_02 = 8)))) THEN 'COBERTURADO' ELSE 'NO COBERTURADO' END) AS COBERTURAINF "+
//	        "FROM T_Marco m LEFT JOIN (SELECT DISTINCT(AT_TOT) AS VTOTAL,ID_N,count(ID_N) as CANTIDIGIT  FROM T_DIG_02_100 GROUP BY ID_N) c on c.ID_N = m.ID_N "+
//	        "LEFT JOIN "+TABLA_CARATULA+" carinf on carinf.ID_N=m.ID_N "+
//	        "INNER JOIN T_UBIGEO u ON m.UBIGEO=u.IDDIST "+     
//			"INNER JOIN T_USUARIO us ON /*m.COD_REGION=us.COD_SEDE_OPERATIVA AND*/ "+
//			"m.COD_DEP_ASIG=CASE WHEN CAST(us.COD_DEP_ASIG AS INT)>99 THEN m.COD_DEP_ASIG ELSE us.COD_DEP_ASIG END "+
//			"INNER JOIN T_SEGMENTACION s ON s.COD_DEP_ASIG = m.COD_DEP_ASIG "+
//			"AND m.RUTA=CASE WHEN us.RUTA IS NULL THEN m.RUTA ELSE us.RUTA END "+
//			"AND m.[ID_N] = CASE WHEN CAST(us.COD_DEP_ASIG AS INT)>99 THEN s.[ID_N] ELSE m.[ID_N] END " +
//			"AND us.[ID] = CASE WHEN CAST(us.COD_DEP_ASIG AS INT)>99 THEN s.[USUARIO_ID] ELSE us.[ID] END " +
//	        "WHERE (m.VINFRA = 1 OR m.VAT = 1 OR m.VDELI=1) AND us.id=? ORDER BY m.ID_N ASC";
//	        
//	        cursor=bd.rawQuery(query, new String[]{App.getInstance().getUsuario().id.toString()});
//	              	     	
//	   	 	while (cursor.moveToNext()) {
//	   	 		
//	   	 	String id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//	   	 	String nombcomisaria = cursor.getString(cursor.getColumnIndex("NOMCOMISARIA"));
//	   	 	String nomreg = cursor.getString(cursor.getColumnIndex("REGION"));
//	   	 	String nombredd = cursor.getString(cursor.getColumnIndex("NOMBDEP"));
//	   	 	String nombrepp = cursor.getString(cursor.getColumnIndex("NOMBPROV"));
//	   	 	String nombredi = cursor.getString(cursor.getColumnIndex("NOMBDIST"));
//	   	 	String digitadoinf = cursor.getString(cursor.getColumnIndex("COBERTURAINF"));
//	   	 	//Log.d("-********************---------", id_n + "#_____#" + digitadoinf);
//	   	 	/*String digitadoat = cursor.getString(cursor.getColumnIndex("COBERTURAAT"));*/
//	   	 	/*String digitadode = cursor.getString(cursor.getColumnIndex("COBERTURADE"));*/
//	   	 	/*String gpsinf = cursor.getString(cursor.getColumnIndex("GPSCONFIRMAINF"));*/
//	   	 	/*String gpsat = cursor.getString(cursor.getColumnIndex("GPSCONFIRMAAT"));*/
//	   	 	/*String gpsde = cursor.getString(cursor.getColumnIndex("GPSCONFIRMADE"));*/
//	   	 	Integer vinfra = cursor.getString(cursor.getColumnIndex("VINFRA"))==null?null:cursor.getInt(cursor.getColumnIndex("VINFRA"));
//	   	 	Integer vat = cursor.getString(cursor.getColumnIndex("VAT"))==null?null:cursor.getInt(cursor.getColumnIndex("VAT"));
//	   	 	Integer vdeli = cursor.getString(cursor.getColumnIndex("VDELI"))==null?null:cursor.getInt(cursor.getColumnIndex("VDELI"));
//	   	 		   	 
//	   	 	listMarco.add(new Marco(id_n, nombcomisaria, nomreg, nombredd, nombrepp, nombredi, vdeli));
//	   	 			//Log.d("-++++++++++++++++---------", listMarco.get(listMarco.size()-1).id_n + "#_____#" + listMarco.get(listMarco.size()-1).digitadoinf);
//		        }	            	           
//	           } catch (Exception e) {
//	        	   Log.d("inei",e.getMessage());
//	         } finally {
//	        	cursor.close();
//	        	bd.close();
//	             }
//	        return listMarco;
//	        }
	
//	public List<Marco> getMarcoCaratula(String id_n) {
//		SQLiteDatabase db =dbh.getReadableDatabase();
//		List<Marco> lstMarco = new ArrayList<Marco>();
//		
//		try {				
//			
//			//cursor=db.rawQuery("SELECT * FROM T_Marco WHERE ID_N ='" + id_n + "'",null);   
//			
//			StringBuilder sbtodomuestra= new StringBuilder();
//			sbtodomuestra.append("SELECT m.ID_N,m.COD_REGION,m.REGION,dp.ccdd,pv.ccpp,di.ccdi,dp.nombredd,pv.nombrepv,di.nombredi,m.AREA,m.ZONA,m.ZONALF,m.MZA,m.MZNALF,m.AER_INI,m.AER_FIN ").append(" ");		        
//	        sbtodomuestra.append("FROM T_Marco m").append(" ");
//	        sbtodomuestra.append("inner join DPTO dp on m.CCDD = dp.ccdd").append(" ");
//	        sbtodomuestra.append("inner join PROV pv on m.CCDD = pv.ccdd and M.CCPP = pv.ccpp").append(" ");
//	        sbtodomuestra.append("inner join DIST di on m.CCDD = di.ccdd and M.CCPP = di.ccpp and M.CCDI = di.ccdi").append(" ");
//	        sbtodomuestra.append("WHERE m.ID_N=").append("'");
//	        sbtodomuestra.append(id_n).append("'").append(" ");
//			
//	        cursor=db.rawQuery(sbtodomuestra.toString(),null);   
//	        
//	        Marco marco;
//	        while(cursor.moveToNext()){
//	        	marco = new Marco();
//	        	marco.id_n= cursor.getString(cursor.getColumnIndex("ID_N"));
//	        	marco.cod_region = cursor.getString(cursor.getColumnIndex("COD_REGION"));
//	        	marco.region = cursor.getString(cursor.getColumnIndex("REGION"));
//	        	marco.ccdd = cursor.getString(cursor.getColumnIndex("ccdd"));
//	        	marco.nombredd = cursor.getString(cursor.getColumnIndex("nombredd"));
//	        	marco.ccpp = cursor.getString(cursor.getColumnIndex("ccpp"));
//	        	marco.nombrepp = cursor.getString(cursor.getColumnIndex("nombrepv"));
//	        	marco.ccdi = cursor.getString(cursor.getColumnIndex("ccdi"));
//	        	marco.nombredi = cursor.getString(cursor.getColumnIndex("nombredi"));
//	        	//marco.cccp = cursor.getString(cursor.getColumnIndex("CCCP"));
//	        	marco.area = cursor.getInt(cursor.getColumnIndex("AREA"));
//	        	marco.zona = cursor.getString(cursor.getColumnIndex("ZONA"));
//	        	marco.zonalf = cursor.getString(cursor.getColumnIndex("ZONALF"));
//	        	marco.mza = cursor.getString(cursor.getColumnIndex("MZA"));
//	        	marco.mznalf = cursor.getString(cursor.getColumnIndex("MZNALF"));
////	        	marco.frente = cursor.getString(cursor.getColumnIndex("FRENTE"));
//	        	marco.aer_ini = cursor.getString(cursor.getColumnIndex("AER_INI"));
//	        	marco.aer_fin = cursor.getString(cursor.getColumnIndex("AER_FIN"));
//	        	/*marco.i11_1 = cursor.getInt(cursor.getColumnIndex("I11_1"));
//	        	marco.i11_2 = cursor.getString(cursor.getColumnIndex("I11_2"));
//	        	marco.i11_3 = cursor.getString(cursor.getColumnIndex("I11_3"));
//	        	marco.i11_4 = cursor.getString(cursor.getColumnIndex("I11_4"));
//	        	marco.i11_5 = cursor.getString(cursor.getColumnIndex("I11_5"));
//	        	marco.i11_6 = cursor.getString(cursor.getColumnIndex("I11_6"));
//	        	marco.i11_7 = cursor.getString(cursor.getColumnIndex("I11_7"));
//	        	marco.i11_8 = cursor.getFloat(cursor.getColumnIndex("I11_8"));
//	        	marco.i11_9 = cursor.getString(cursor.getColumnIndex("I11_9"));
//	        	marco.formul_n = cursor.getString(cursor.getColumnIndex("FORMUL_N"));
//	        	marco.formul_tot = cursor.getString(cursor.getColumnIndex("FORMUL_TOT"));
//	        	marco.rutan = cursor.getString(cursor.getColumnIndex("RUTAN"));
//	        	marco.ii1 = cursor.getString(cursor.getColumnIndex("II1"));
//	        	marco.ii2 = cursor.getString(cursor.getColumnIndex("II2"));
//	        	marco.ii3 = cursor.getString(cursor.getColumnIndex("II3"));
//	        	marco.ii4 = cursor.getInt(cursor.getColumnIndex("II4"));
//	        	marco.ii5 = cursor.getString(cursor.getColumnIndex("II5"));
//	        	marco.ii6 = cursor.getInt(cursor.getColumnIndex("II6"));
//	        	marco.ii7 = cursor.getInt(cursor.getColumnIndex("II7"));*/
//	        	lstMarco.add(marco);
//	        }
//			
//		} catch (Exception e) {
//			Log.d(TAG, e.getMessage());
//		} finally {
//			cursor.close();
//			db.close();
//		}
//		
//		return lstMarco;
//	}
	
	
//	public Marco getMarcoEdicion(String id_n){
//		Marco data = new Marco();
//		SQLiteDatabase db = dbh.getReadableDatabase();
//		
//		/*Cursor result = db.rawQuery("SELECT tm.*, dep.NOMBREDD AS NOMBREDD, pro.NOMBREPV AS NOMBREPV, dis.NOMBREDI AS NOMBREDI " +
//				"FROM T_Marco tm " +
//				"INNER JOIN DPTO dep ON tm.ccdd=dep.ccdd INNER JOIN PROV pro ON tm.ccpp=pro.ccpp AND dep.ccdd=pro.ccdd " +
//				"INNER JOIN DIST dis ON tm.ccdi=dis.ccdi AND pro.ccpp=dis.ccpp AND dis.ccdd=dep.ccdd " +
//				"WHERE ID_N='" + id_n + "'", null);*/
//		Cursor result = db.rawQuery("SELECT tm.*, u.NOMBDEP AS NOMBREDD, u.NOMBPROV AS NOMBREPV, u.NOMBDIST AS NOMBREDI " +
//				"FROM T_MARCO tm INNER JOIN T_UBIGEO u ON tm.UBIGEO=u.IDDIST " +
//				"WHERE ID_N='" + id_n + "'", null);
//		if(result.getCount()==0) return null;
//		result.moveToNext();
//		
//		data.id_n = result.getString(result.getColumnIndex("ID_N"));
//		data.cod_region = result.getString(result.getColumnIndex("COD_REGION"));
//		data.region = result.getString(result.getColumnIndex("REGION"));
//		data.ubigeo = result.getString(result.getColumnIndex("UBIGEO"));
//		data.ccdd = result.getString(result.getColumnIndex("CCDD"));
//		data.nombredd = result.getString(result.getColumnIndex("NOMBREDD"));
//		data.ccpp = result.getString(result.getColumnIndex("CCPP"));
//		data.nombrepp = result.getString(result.getColumnIndex("NOMBREPV"));
//		data.ccdi = result.getString(result.getColumnIndex("CCDI"));
//		data.nombredi = result.getString(result.getColumnIndex("NOMBREDI"));
//		data.cccp = result.getString(result.getColumnIndex("CCCP"));
//		data.nombrecp = result.getString(result.getColumnIndex("NOMBRECP"));
//		data.zona = result.getString(result.getColumnIndex("ZONA"));
//		data.zonalf = result.getString(result.getColumnIndex("ZONALF"));
//		data.mza = result.getString(result.getColumnIndex("MZA"));
//		data.mznalf = result.getString(result.getColumnIndex("MZNALF"));
//		data.area = result.getString(result.getColumnIndex("AREA"))==null?null:result.getInt(result.getColumnIndex("AREA"));
//		data.aer_ini = result.getString(result.getColumnIndex("AER_INI"));
//		data.aer_fin = result.getString(result.getColumnIndex("AER_FIN"));
////		data.frente = result.getString(result.getColumnIndex("FRENTE"));
//		data.dirtepol = result.getString(result.getColumnIndex("DIRTEPOL"));
//		data.capireferencia = result.getString(result.getColumnIndex("CAPIREFERENCIA"));
//		data.nomcomisaria = result.getString(result.getColumnIndex("NOMCOMISARIA"));
//		data.direccion = result.getString(result.getColumnIndex("DIRECCION"));
//		data.comisario = result.getString(result.getColumnIndex("COMISARIO"));
//		data.telefono1 = result.getString(result.getColumnIndex("TELEFONO1"));
//		data.telefono2 = result.getString(result.getColumnIndex("TELEFONO2"));
//		data.tipocpnp = result.getString(result.getColumnIndex("TIPOCPNP"))==null?null:result.getInt(result.getColumnIndex("TIPOCPNP"));
//		data.vdeli = result.getString(result.getColumnIndex("VDELI"))==null?null:result.getInt(result.getColumnIndex("VDELI"));
//		data.agregado = result.getString(result.getColumnIndex("AGREGADO"))==null?0:result.getInt(result.getColumnIndex("AGREGADO"));
//		data.periodo = result.getString(result.getColumnIndex("PERIODO"))==null?0:result.getInt(result.getColumnIndex("PERIODO"));
//		
//		result.close();
//		result = null;
//		db.close();
//		db = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return data;
//	}
	
	public boolean existeRegistro(String id_n){
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		Cursor cursor = dbr.rawQuery("SELECT ID_N FROM T_MARCO WHERE ID_N='" + id_n + "'", null);
		int count = cursor.getCount();
		cursor.close();
		cursor = null;
		dbr.close();
		dbr = null;
		SQLiteDatabase.releaseMemory();
		return count>0;
	}
	
	public boolean existeRegistro(String table, String clauseWhere){
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		Cursor cursor = dbr.rawQuery("SELECT * FROM "+table+" WHERE "+clauseWhere, null);
		int count = cursor.getCount();
		cursor.close();
//		dbr.close();
		SQLiteDatabase.releaseMemory();
		return count>0;
	}
	
//	public boolean grabarMarco(Marco marco){
//		String oper = existeRegistro(marco.id_n) ? "edit":"add";
//		ContentValues content = new ContentValues();
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		
//		content.put("ID_N", marco.id_n);
//		content.put("NOMCOMISARIA", marco.nomcomisaria );
//		content.put("DIRTEPOL", marco.dirtepol);
////		content.put("CAPIREFERENCIA", marco.capireferencia);
////		content.put("TIPO", marco.tipo);
////		content.put("DIRECCION", marco.direccion);
//		content.put("COMISARIO", marco.comisario);
////		content.put("TELEFONO1", marco.telefono1);
////		content.put("TELEFONO2", marco.telefono2);
////		content.put("TIPOCPNP", marco.tipocpnp);
////		content.put("TOTAL_AT2011", marco.total_at2011);
////		content.put("TOTAL_AT2012", marco.total_at2012);
////		content.put("TOTAL_HABIT2012", marco.total_habit2012);
////		content.put("TOTAL_HABIT2013", marco.total_habit2013);
//		content.put("COD_REGION", marco.cod_region);
//		content.put("CCDD", marco.ccdd);
//		content.put("CCPP", marco.ccpp);
//		content.put("CCDI", marco.ccdi);
////		content.put("CCCP", marco.cccp);
//		content.put("UBIGEO", marco.ubigeo);
//		content.put("REGION", marco.region);
////		content.put("NOMBRECP", marco.nombrecp);
//		content.put("AREA", marco.area);
////		content.put("ZONA", marco.zona);
////		content.put("ZONALF", marco.zonalf);
////		content.put("MZA", marco.mza);
////		content.put("MZNALF", marco.mznalf);
////		content.put("AER_INI", marco.aer_ini);
////		content.put("AER_FIN", marco.aer_fin);
////		content.put("FRENTE", marco.frente);
//		content.put("PERIODO", marco.periodo);
//		content.put("VDELI", marco.vdeli);
//		content.put("AGREGADO", marco.agregado);
//		
//		boolean result;
//		if(oper.equals("add")){
//			result = dbw.insert("T_MARCO", null, content)!=-1;
//		}
//		else{
//			result = dbw.update("T_MARCO", content, "ID_N=?", new String[]{marco.id_n})>0;
//		}
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
	public boolean saveOrUpdate(Segmentacion bean, SeccionCapitulo... secciones)
			throws java.sql.SQLException {
		return this
				.saveOrUpdate(
						SegmentacionDAO.TABLA,
						"ID_N=?",
						bean, new String[] { "id_n" },
						secciones);
	}
	
	public boolean borrarSegmentacion(String id, MAINTENCE opcion)
			throws java.sql.SQLException {
		
		return opcion == MAINTENCE.ONEONLY ?
				borrar(
				SegmentacionDAO.TABLA,
				"ID_N=? AND ESTADO = 1", id):
					borrar(
							SegmentacionDAO.TABLA,
							"ESTADO = ?", "1");
	}
	
	public boolean saveOrUpdate(Marco bean, SeccionCapitulo... secciones)
			throws java.sql.SQLException {
		return this
				.saveOrUpdate(
						TABLA_MARCO,
						"ID_N=? ",
						bean, new String[] { "id_n" },
						secciones);
	}
	
	public Ubigeo getUbigeo(String ubigeo){
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		Cursor cursor = dbr.rawQuery("SELECT CCDI, DISTRITO, CCDD, CCPP, PROVINCIA, DEPARTAMENTO "
				+ "FROM T_UBIGEO WHERE UBIGEO like '" + ubigeo + "%'", null);
		
		Ubigeo ubi = null;
		
		if(cursor.getCount()>0){
			cursor.moveToNext();
			ubi = new Ubigeo();
			ubi.ccdi = cursor.getString(cursor.getColumnIndex("CCDI"));
			ubi.ccpp = cursor.getString(cursor.getColumnIndex("CCPP"));
			ubi.ccdd = cursor.getString(cursor.getColumnIndex("CCDD"));
			ubi.departamento = cursor.getString(cursor.getColumnIndex("DEPARTAMENTO"));
			ubi.provincia = cursor.getString(cursor.getColumnIndex("PROVINCIA"));
			ubi.distrito = cursor.getString(cursor.getColumnIndex("DISTRITO"));
		}
		
		cursor.close();
		cursor = null;
		dbr.close();
		dbr = null;
		SQLiteDatabase.releaseMemory();
		
		return ubi;
	}
	
	private ContentValues getUpdateCaratula(String cuest){
		ContentValues cv = new ContentValues();
		if(cuest.equals("DEL")){
			cv.put("V3_1", (String)null);
			cv.put("V3_2", (String)null);
			cv.put("V3_3", (String)null);
			cv.put("V3_4", (String)null);
			cv.put("VII3_2A", (String)null);
			cv.put("VII3_2B", (String)null);
			cv.put("VII3_2C", (String)null);
			cv.put("VII3_2D", (String)null);
			cv.put("VII3_2D_NT", (String)null);
			cv.put("VII3_2E", (String)null);
			cv.put("VII3_2E_NT", (String)null);
			cv.put("VII3_2F", (String)null);
			cv.put("VII3_2F_NT", (String)null);
			cv.put("OBS_03_CAR", (String)null);
			cv.put("GPSLATITUD_DEL", (String)null);
			cv.put("GPSLONGITUD_DEL", (String)null);
			cv.put("IV3_1", (String)null);
			cv.put("IVRESFIN_03", (String)null);
			cv.put("IVRESFIN_03_O", (String)null);
		} else if(cuest.equals("AT")){
			cv.put("VI3A_AT", (String)null);
			cv.put("VI3B_AT", (String)null);
			cv.put("VI4A_AT", (String)null);
			cv.put("VI4B_AT", (String)null);
			cv.put("VAT_2013", (String)null);
			cv.put("VLO_2013", (String)null);
			cv.put("AT_INI", (String)null);
			cv.put("OBS_02_CAR", (String)null);
			cv.put("GPSLATITUD_AT", (String)null);
			cv.put("GPSLONGITUD_AT", (String)null);
			cv.put("IV2_1", (String)null);
			cv.put("IVRESFIN_02", (String)null);
			cv.put("IVRESFIN_02_O", (String)null);
		} else if(cuest.equals("INF")){
			cv.put("VI3A_INF", (String)null);
			cv.put("VI3B_INF", (String)null);
			cv.put("VI4A_INF", (String)null);
			cv.put("VI4B_INF", (String)null);
			cv.put("VHAB", (String)null);
			cv.put("VPCS", (String)null);
			cv.put("VEFEC", (String)null);
			cv.put("OBS_01_CAR", (String)null);
			cv.put("GPSLATITUD_INF", (String)null);
			cv.put("GPSLONGITUD_INF", (String)null);
			cv.put("IV1_1", (String)null);
			cv.put("IVRESFIN_01", (String)null);
			cv.put("IVRESFIN_01_O", (String)null);
		}
		return cv;
	}
	
	public boolean eliminarINF(String id_n){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		int i = 0;
//		i += dbw.delete("T_DIG_01_600_I", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_DIG_01_600", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_DIG_01_500", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_DIG_01_400", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_DIG_01_300_III", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_DIG_01_300", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_DIG_01_200", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_DIG_01_100", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_DIG_01_VISITA", "ID_N=?", new String[]{id_n});
//		/*i += dbw.delete("T_DIG_01", "ID_N=?", new String[]{id_n});*/
//		
//		dbw.close();
//		
//		return i > 0;
		return true;
	}
	
	public boolean eliminarAT(String id_n){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		int i = 0;
//		i += dbw.delete("T_DIG_02_200", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_DIG_02_100", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_MUESTRAAT", "ID_N=?", new String[]{id_n});
//		i += dbw.delete("T_DIG_02_VISITA", "ID_N=?", new String[]{id_n});
//		/*i += dbw.delete("T_DIG_02", "ID_N=?", new String[]{id_n});*/
//		
//		dbw.update(TABLA_CARATULA, getUpdateCaratula("AT"), "ID_N='" + id_n + "'", null);
//		
//		dbw.close();
//		
//		return i > 0;
		return true;
	}
	
	public boolean eliminarDE(String id_n){
		SQLiteDatabase dbw = dbh.getWritableDatabase();
		int i = 0;
		i += dbw.delete(TABLA_C400, "ID_N=?", new String[]{id_n});
		i += dbw.delete(TABLA_C300, "ID_N=?", new String[]{id_n});
		i += dbw.delete(TABLA_C200, "ID_N=?", new String[]{id_n});
		i += dbw.delete(TABLA_C100, "ID_N=?", new String[]{id_n});
		i += dbw.delete(TABLA_VISITA, "ID_N=?", new String[]{id_n});
		
		dbw.update(TABLA_CARATULA, getUpdateCaratula("DEL"), "ID_N='" + id_n + "'", null);
		dbw.close();
		
		return i > 0;
	}
	
	public boolean deleteCap100Del(String id_n){
		SQLiteDatabase dbw = dbh.getWritableDatabase();
		int i = 0;
		i += dbw.delete(TABLA_C100, "ID_N=?", new String[]{id_n});
		dbw.close();
		return i > 0;
	}
	
	public boolean deleteCap200Del(String id_n, Integer nro_mreg){
		SQLiteDatabase dbw = dbh.getWritableDatabase();
		int i = 0;
		String where = "";
		if(!nro_mreg.equals(-1)) where = "AND NRO_MREG="+nro_mreg;
		
		i += dbw.delete(TABLA_C200, "ID_N=? "+where, new String[]{id_n});
		i += dbw.delete(TABLA_C300, "ID_N=? "+where, new String[]{id_n});
		i += dbw.delete(TABLA_C400, "ID_N=? "+where, new String[]{id_n});
		
		if(!nro_mreg.equals(-1)) {
			updateConteo200(dbw, id_n);
			updateResults(dbw, id_n, nro_mreg);
		}
		dbw.close();
		return i > 0;
	}
	
	public boolean deleteCap300Del(String id_n, Integer nro_mreg, Integer nro_vfreg){
		SQLiteDatabase dbw = dbh.getWritableDatabase();
		int i = 0;
		
		i += dbw.delete(TABLA_C300, "ID_N=? AND NRO_MREG=? AND NRO_VFREG=?", 
				new String[]{id_n, nro_mreg.toString(), nro_vfreg.toString()});
		updateConteo300(dbw, id_n, nro_mreg);
		
		dbw.close();
		
		return i > 0;
	}
	
	public boolean deleteCap400Del(String id_n, Integer nro_mreg, Integer nro_pvreg){
		String where = nro_pvreg.equals(-1) ? "" : "AND NRO_PVREG = "+nro_pvreg.toString();
		SQLiteDatabase dbw = dbh.getWritableDatabase();
		int i = 0;
		
		i += dbw.delete(TABLA_C400, "ID_N=? AND NRO_MREG=? "+where, 
				new String[]{id_n, nro_mreg.toString()});
		updateConteo400(dbw, id_n, nro_mreg);
		
		dbw.close();
		dbw = null;
		
		return i > 0;
	}
	
//	public boolean abrirDE(String id_n){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		ContentValues content = new ContentValues();
//		content.put("VDELI", 1);
//		return dbw.update("T_MARCO", content, "ID_N=?", new String[]{id_n})>0;
//	}
	
	public boolean eliminarComisaria(String id_n){
		/*TODO: Adolfo, falta verificar si este modo de eliminacion es correcto*/
		eliminarINF(id_n);
		eliminarAT(id_n);
		eliminarDE(id_n);
		
		SQLiteDatabase dbw = dbh.getWritableDatabase();
		int i = dbw.delete(MarcoDAO.TABLA_MARCO, "ID_N=?", new String[]{id_n});		
		dbw.close();
		dbw = null;
		
		return i>0;
	}
	
	public boolean esAgregado(String id_n){
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		Cursor cursor = dbr.rawQuery("SELECT AGREGADO FROM "+MarcoDAO.TABLA_MARCO+" WHERE ID_N='" + id_n + "'", null);
		cursor.moveToNext();
		int agregado = cursor.getString(cursor.getColumnIndex("AGREGADO"))==null?0:cursor.getInt(cursor.getColumnIndex("AGREGADO"));
		cursor.close();
		cursor = null;
		dbr.close();
		dbr = null;
		SQLiteDatabase.releaseMemory();
		return agregado==1;
	}
	
//	public boolean borrarGPSINF(String id_n){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		ContentValues content = new ContentValues();
//		content.putNull("GPSLATITUD");
//		content.putNull("GPSLONGITUD");
//		
//		return dbw.update(TABLA_CARATULA, content, "ID_N=?", new String[]{id_n})>0;
//	}
	
//	public boolean borrarGPSAT(String id_n){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		ContentValues content = new ContentValues();
//		content.putNull("GPSLATITUD");
//		content.putNull("GPSLONGITUD");
//		
//		return dbw.update("T_DIG_02", content, "ID_N=?", new String[]{id_n})>0;
//	}
	
//	public boolean borrarGPSDE(String id_n){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		ContentValues content = new ContentValues();
//		content.putNull("GPSLATITUD");
//		content.putNull("GPSLONGITUD");
//		
//		return dbw.update("T_DIG_03", content, "ID_N=?", new String[]{id_n})>0;
//	}

//	public ArrayList<Marco> fillMarcosAgregados(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Marco> caratulas = new ArrayList<Marco>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_MARCO WHERE AGREGADO=1 AND ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			Marco caratula = new Marco();
//
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.nomcomisaria = cursor.getString(cursor.getColumnIndex("NOMCOMISARIA"));
//			caratula.cod_region = cursor.getString(cursor.getColumnIndex("COD_REGION"));
//			caratula.dirtepol = cursor.getString(cursor.getColumnIndex("DIRTEPOL"));
//			caratula.capireferencia = cursor.getString(cursor.getColumnIndex("CAPIREFERENCIA"));
//			caratula.tipo = cursor.getString(cursor.getColumnIndex("TIPO"));
//			caratula.direccion = cursor.getString(cursor.getColumnIndex("DIRECCION"));
//			caratula.comisario = cursor.getString(cursor.getColumnIndex("COMISARIO"));
//			caratula.telefono1 = cursor.getString(cursor.getColumnIndex("TELEFONO1"));
//			caratula.telefono2 = cursor.getString(cursor.getColumnIndex("TELEFONO2"));
//			caratula.tipocpnp = cursor.getString(cursor.getColumnIndex("TIPOCPNP"))==null?null:cursor.getInt(cursor.getColumnIndex("TIPOCPNP"));
//			caratula.ccdd = cursor.getString(cursor.getColumnIndex("CCDD"));
//			caratula.ccpp = cursor.getString(cursor.getColumnIndex("CCPP"));
//			caratula.ccdi = cursor.getString(cursor.getColumnIndex("CCDI"));
//			caratula.cccp = cursor.getString(cursor.getColumnIndex("CCCP"));
//			caratula.ubigeo = cursor.getString(cursor.getColumnIndex("UBIGEO"));
//			caratula.region = cursor.getString(cursor.getColumnIndex("REGION"));
//			caratula.nombrecp = cursor.getString(cursor.getColumnIndex("NOMBRECP"));
//			caratula.area = cursor.getString(cursor.getColumnIndex("AREA"))==null?null:cursor.getInt(cursor.getColumnIndex("AREA"));
//			caratula.zona = cursor.getString(cursor.getColumnIndex("ZONA"));
//			caratula.zonalf = cursor.getString(cursor.getColumnIndex("ZONALF"));
//			caratula.mza = cursor.getString(cursor.getColumnIndex("MZA"));
//			caratula.mznalf = cursor.getString(cursor.getColumnIndex("MZNALF"));
//			caratula.aer_ini = cursor.getString(cursor.getColumnIndex("AER_INI"));
//			caratula.aer_fin = cursor.getString(cursor.getColumnIndex("AER_FIN"));
////			caratula.frente = cursor.getString(cursor.getColumnIndex("FRENTE"));
//			caratula.vdeli = cursor.getString(cursor.getColumnIndex("VDELI"))==null?null:cursor.getInt(cursor.getColumnIndex("VDELI"));
//			/*caratula.usureg = cursor.getString(cursor.getColumnIndex("USUREG"));*/
//			caratula.agregado = cursor.getString(cursor.getColumnIndex("AGREGADO"))==null?null:cursor.getInt(cursor.getColumnIndex("AGREGADO"));
//			/*caratula.fecreg = cursor.getString(cursor.getColumnIndex("FECREG"));*/
//			caratula.periodo = cursor.getString(cursor.getColumnIndex("PERIODO"))==null?null:cursor.getInt(cursor.getColumnIndex("PERIODO"));
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Caratula01> fillCaratulas(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Caratula01> caratulas = new ArrayList<INF_Caratula01>();
//
////		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01 WHERE ID_N=?", new String[]{id_n});
////		
////		while(cursor.moveToNext()){
////			INF_Caratula01 data = new INF_Caratula01();
////
////			List<String> allFieldMatches = data.getFieldMatches(1, 10, "ID_N", "REG", "NOMREG", 
////					"CCDD","NOMBREDD","CCPP","NOMBREPP","CCDI","NOMBREDI","CCCP","NOMBRECP",
////					"AREA","ZONA","ZONALF","MZA","MZNALF","FRENTE","AER_INI","AER_FIN","FORMUL_N",
////					"FORMUL_TOT","RUTAN","IVRESFIN","IVRESFIN_O","IVRESFIN_01","IVRESFIN_01_O",
////					"IVRESFIN_02","IVRESFIN_02_O","IVRESFIN_03","IVRESFIN_03_O","OBS_CAR",
////					"OBS_01_CAR","OBS_02_CAR","OBS_03_CAR","GPSLATITUD_INF",
////					"GPSLONGITUD_INF","GPSLATITUD_AT","GPSLONGITUD_AT","GPSLATITUD_DEL","GPSLONGITUD_DEL",
////					"VHAB","VPCS","VEFEC","VAT_2013","VLO_2013","AT_INI","AT_CAP100", "ESTADO_ENVIO",
////					"VERSION_IMPORTACION","VERSION_EXPORTACION","USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
////			data = (INF_Caratula01)data.fillEntity(cursor, allFieldMatches);
////			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
////			
////			caratulas.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Caratula01> fillCaratulas(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Caratula01> caratulas = new ArrayList<INF_Caratula01>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Caratula01 caratula = new INF_Caratula01();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.reg = cursor.getString(cursor.getColumnIndex("REG"));
//			caratula.nomreg = cursor.getString(cursor.getColumnIndex("NOMREG"));
//			caratula.ccdd = cursor.getString(cursor.getColumnIndex("CCDD"));
//			caratula.nombredd = cursor.getString(cursor.getColumnIndex("NOMBREDD"));
//			caratula.ccpp = cursor.getString(cursor.getColumnIndex("CCPP"));
//			caratula.nombrepp = cursor.getString(cursor.getColumnIndex("NOMBREPP"));
//			caratula.ccdi = cursor.getString(cursor.getColumnIndex("CCDI"));
//			caratula.nombredi = cursor.getString(cursor.getColumnIndex("NOMBREDI"));
//			caratula.cccp = cursor.getString(cursor.getColumnIndex("CCCP"));
//			caratula.nombrecp = cursor.getString(cursor.getColumnIndex("NOMBRECP"));
//			caratula.area = cursor.getString(cursor.getColumnIndex("AREA"))==null?null:cursor.getInt(cursor.getColumnIndex("AREA"));
//			caratula.zona = cursor.getString(cursor.getColumnIndex("ZONA"));
//			caratula.zonalf = cursor.getString(cursor.getColumnIndex("ZONALF"));
//			caratula.mza = cursor.getString(cursor.getColumnIndex("MZA"));
//			caratula.mznalf = cursor.getString(cursor.getColumnIndex("MZNALF"));
//			caratula.frente = cursor.getString(cursor.getColumnIndex("FRENTE"));
//			caratula.aer_ini = cursor.getString(cursor.getColumnIndex("AER_INI"));
//			caratula.aer_fin = cursor.getString(cursor.getColumnIndex("AER_FIN"));
//			caratula.i11_1 = cursor.getString(cursor.getColumnIndex("I11_1"))==null?null:cursor.getInt(cursor.getColumnIndex("I11_1"));
//			caratula.i11_2 = cursor.getString(cursor.getColumnIndex("I11_2"));
//			caratula.i11_3 = cursor.getString(cursor.getColumnIndex("I11_3"));
//			caratula.i11_4 = cursor.getString(cursor.getColumnIndex("I11_4"));
//			caratula.i11_5 = cursor.getString(cursor.getColumnIndex("I11_5"));
//			caratula.i11_6 = cursor.getString(cursor.getColumnIndex("I11_6"));
//			caratula.i11_7 = cursor.getString(cursor.getColumnIndex("I11_7"));
//			caratula.i11_8 = cursor.getString(cursor.getColumnIndex("I11_8"));
////			caratula.i11_9 = cursor.getString(cursor.getColumnIndex("I11_9"))==null?null:cursor.getFloat(cursor.getColumnIndex("I11_9"));
//			caratula.i11_10 = cursor.getString(cursor.getColumnIndex("I11_10"));
//			/*caratula.formul_n = cursor.getString(cursor.getColumnIndex("FORMUL_N"));
//			caratula.formul_tot = cursor.getString(cursor.getColumnIndex("FORMUL_TOT"));*/
//			caratula.rutan = cursor.getString(cursor.getColumnIndex("RUTAN"));
//			caratula.ii1 = cursor.getString(cursor.getColumnIndex("II1"));
//			caratula.ii2 = cursor.getString(cursor.getColumnIndex("II2"));
//			caratula.ii3 = cursor.getString(cursor.getColumnIndex("II3"));
//			caratula.ii4 = cursor.getString(cursor.getColumnIndex("II4"))==null?null:cursor.getInt(cursor.getColumnIndex("II4"));
//			caratula.ii5 = cursor.getString(cursor.getColumnIndex("II5"))==null?null:cursor.getInt(cursor.getColumnIndex("II5"));
//			caratula.ii6 = cursor.getString(cursor.getColumnIndex("II6"));
//			caratula.ii7 = cursor.getString(cursor.getColumnIndex("II7"))==null?null:cursor.getInt(cursor.getColumnIndex("II7"));
//			caratula.ii8 = cursor.getString(cursor.getColumnIndex("II8"))==null?null:cursor.getInt(cursor.getColumnIndex("II8"));
//			caratula.ii8_o = cursor.getString(cursor.getColumnIndex("II8_O"));
//			caratula.ii9 = cursor.getString(cursor.getColumnIndex("II9"))==null?null:cursor.getInt(cursor.getColumnIndex("II9"));
//			caratula.ii9_o = cursor.getString(cursor.getColumnIndex("II9_O"));
//			caratula.iv2_1_d = cursor.getString(cursor.getColumnIndex("IV2_1_D"));
//			caratula.iv2_1_m = cursor.getString(cursor.getColumnIndex("IV2_1_M"));
//			caratula.iv1_1 = cursor.getString(cursor.getColumnIndex("IV1_1"));
//			caratula.ivresfin_01 = cursor.getString(cursor.getColumnIndex("IVRESFIN_01"))==null?null:cursor.getInt(cursor.getColumnIndex("IVRESFIN_01"));
//			caratula.ivresfin_01_o = cursor.getString(cursor.getColumnIndex("IVRESFIN_01_O"));
//			caratula.iv2_1 = cursor.getString(cursor.getColumnIndex("IV2_1"));
//			caratula.ivresfin_02 = cursor.getString(cursor.getColumnIndex("IVRESFIN_02"))==null?null:cursor.getInt(cursor.getColumnIndex("IVRESFIN_02"));
//			caratula.ivresfin_02_o = cursor.getString(cursor.getColumnIndex("IVRESFIN_02_O"));
//			caratula.vi1a = cursor.getString(cursor.getColumnIndex("VI1A"));
//			caratula.vi1b = cursor.getString(cursor.getColumnIndex("VI1B"));
//			caratula.vi2a = cursor.getString(cursor.getColumnIndex("VI2A"));
//			caratula.vi2b = cursor.getString(cursor.getColumnIndex("VI2B"));
//			caratula.vi3a = cursor.getString(cursor.getColumnIndex("VI3A"));
//			caratula.vi3b = cursor.getString(cursor.getColumnIndex("VI3B"));
//			caratula.vi4a = cursor.getString(cursor.getColumnIndex("VI4A"));
//			caratula.vi4b = cursor.getString(cursor.getColumnIndex("VI4B"));
//			/*caratula.vii1a = cursor.getString(cursor.getColumnIndex("VII1A"));
//			caratula.vii1b = cursor.getString(cursor.getColumnIndex("VII1B"));
//			caratula.vii2a = cursor.getString(cursor.getColumnIndex("VII2A"));
//			caratula.vii2b = cursor.getString(cursor.getColumnIndex("VII2B"));*/
//			caratula.obs_01_car = cursor.getString(cursor.getColumnIndex("OBS_01_CAR"));
//			caratula.obs_02_car = cursor.getString(cursor.getColumnIndex("OBS_02_CAR"));
//			caratula.obs_car = cursor.getString(cursor.getColumnIndex("OBS_CAR"));
//			caratula.gpslatitud_inf = cursor.getString(cursor.getColumnIndex("GPSLATITUD_INF"));
//			caratula.gpslongitud_inf = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_INF"));
//			caratula.gpslatitud_at = cursor.getString(cursor.getColumnIndex("GPSLATITUD_AT"));
//			caratula.gpslongitud_at = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_AT"));
//			caratula.vhab = cursor.getString(cursor.getColumnIndex("VHAB"))==null?null:cursor.getInt(cursor.getColumnIndex("VHAB"));
//			caratula.vpcs = cursor.getString(cursor.getColumnIndex("VPCS"))==null?null:cursor.getInt(cursor.getColumnIndex("VPCS"));
//			caratula.vefec = cursor.getString(cursor.getColumnIndex("VEFEC"))==null?null:cursor.getInt(cursor.getColumnIndex("VEFEC"));
//			caratula.vat_2013 = cursor.getString(cursor.getColumnIndex("VAT_2013"))==null?null:cursor.getInt(cursor.getColumnIndex("VAT_2013"));
//			caratula.vlo_2013 = cursor.getString(cursor.getColumnIndex("VLO_2013"))==null?null:cursor.getInt(cursor.getColumnIndex("VLO_2013"));
//			caratula.at_ini = cursor.getString(cursor.getColumnIndex("AT_INI"))==null?null:cursor.getInt(cursor.getColumnIndex("AT_INI"));
//			
//			caratula.version_exportacion = cursor.getString(cursor.getColumnIndex("VERSION_EXPORTACION"));
//			caratula.version_importacion = cursor.getString(cursor.getColumnIndex("VERSION_IMPORTACION"));
//			caratula.estado_envio = cursor.getString(cursor.getColumnIndex("ESTADO_ENVIO"))==null?null:cursor.getInt(cursor.getColumnIndex("ESTADO_ENVIO"));
//			
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Visita01> fillINF_Visitas(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Visita01> caratulas = new ArrayList<INF_Visita01>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_VISITA WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Visita01 data = new INF_Visita01();
//
//			List<String> allFieldMatches = data.getFieldMatches(-1, -1, "ID_N", "NROVIS", "EFECHA", 
//					"EHORADE","EHORAA","EPVFECHA","EPVHORA","ERESVIS","ERESVIS_O","SFECHA","SHORADE",
//					"SHORAA","SRESVIS","SRESVIS_O","GPSLATITUD","GPSLONGITUD",
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (INF_Visita01)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			caratulas.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Visita01> fillINF_Visitas(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Visita01> caratulas = new ArrayList<INF_Visita01>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_VISITA WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Visita01 caratula = new INF_Visita01();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.nrovis = cursor.getString(cursor.getColumnIndex("NROVIS"))==null?null:cursor.getInt(cursor.getColumnIndex("NROVIS"));
//			caratula.efecha = cursor.getString(cursor.getColumnIndex("EFECHA"));
//			caratula.ehorade = cursor.getString(cursor.getColumnIndex("EHORADE"));
//			caratula.ehoraa = cursor.getString(cursor.getColumnIndex("EHORAA"));
//			caratula.epvfecha = cursor.getString(cursor.getColumnIndex("EPVFECHA"));
//			caratula.epvhora = cursor.getString(cursor.getColumnIndex("EPVHORA"));
//			caratula.eresvis = cursor.getString(cursor.getColumnIndex("ERESVIS"))==null?null:cursor.getInt(cursor.getColumnIndex("ERESVIS"));
//			caratula.eresvis_o = cursor.getString(cursor.getColumnIndex("ERESVIS_O"));
//			caratula.sfecha = cursor.getString(cursor.getColumnIndex("SFECHA"));
//			caratula.shorade = cursor.getString(cursor.getColumnIndex("SHORADE"));
//			caratula.shoraa = cursor.getString(cursor.getColumnIndex("SHORAA"));
//			caratula.sresvis = cursor.getString(cursor.getColumnIndex("SRESVIS"))==null?null:cursor.getInt(cursor.getColumnIndex("SRESVIS"));
//			caratula.sresvis_o = cursor.getString(cursor.getColumnIndex("SRESVIS_O"));
//			
//			caratula.gpslatitud = cursor.getString(cursor.getColumnIndex("GPSLATITUD"));
//			caratula.gpslongitud = cursor.getString(cursor.getColumnIndex("GPSLONGITUD"));
//			
////			caratula.usucre = cursor.getString(cursor.getColumnIndex("USUCRE"));
////			caratula.feccre = cursor.getString(cursor.getColumnIndex("FECCRE"));
////			caratula.usureg = cursor.getString(cursor.getColumnIndex("USUREG"));
////			caratula.fecreg = cursor.getString(cursor.getColumnIndex("FECREG"));
////			caratula.fecenv = cursor.getString(cursor.getColumnIndex("FECENV"));
//
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap100> fillINF_Capitulo100s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap100> caratulas = new ArrayList<INF_Cap100>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_100 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap100 data = new INF_Cap100();
//
//			List<String> allFieldMatches = data.getFieldMatches(101, 120, "ID_N", "OBS_CAP_100",
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (INF_Cap100)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			caratulas.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap100> fillINF_Capitulo100s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap100> caratulas = new ArrayList<INF_Cap100>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_100 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap100 caratula = new INF_Cap100();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.inf101_1 = cursor.getString(cursor.getColumnIndex("INF101_1"));
//			caratula.inf102_1 = cursor.getString(cursor.getColumnIndex("INF102_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF102_1"));
//			caratula.inf103_1 = cursor.getString(cursor.getColumnIndex("INF103_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF103_1"));
//			caratula.inf104_1 = cursor.getString(cursor.getColumnIndex("INF104_1"));
//			caratula.inf105_1 = cursor.getString(cursor.getColumnIndex("INF105_1"));
//			caratula.inf106_1 = cursor.getString(cursor.getColumnIndex("INF106_1"));
//			caratula.inf107_1_a = cursor.getString(cursor.getColumnIndex("INF107_1_A"));
//			caratula.inf107_1_b = cursor.getString(cursor.getColumnIndex("INF107_1_B"));
//			caratula.inf107_1_c = cursor.getString(cursor.getColumnIndex("INF107_1_C"));
//			caratula.inf108_1_inst = cursor.getString(cursor.getColumnIndex("INF108_1_INST"));
//			caratula.inf108_1_pers = cursor.getString(cursor.getColumnIndex("INF108_1_PERS"));
//			caratula.inf101_2 = cursor.getString(cursor.getColumnIndex("INF101_2"));
//			caratula.inf102_2 = cursor.getString(cursor.getColumnIndex("INF102_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF102_2"));
//			caratula.inf103_2 = cursor.getString(cursor.getColumnIndex("INF103_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF103_2"));
//			caratula.inf104_2 = cursor.getString(cursor.getColumnIndex("INF104_2"));
//			caratula.inf105_2 = cursor.getString(cursor.getColumnIndex("INF105_2"));
//			caratula.inf106_2 = cursor.getString(cursor.getColumnIndex("INF106_2"));
//			caratula.inf107_2_a = cursor.getString(cursor.getColumnIndex("INF107_2_A"));
//			caratula.inf107_2_b = cursor.getString(cursor.getColumnIndex("INF107_2_B"));
//			caratula.inf107_2_c = cursor.getString(cursor.getColumnIndex("INF107_2_C"));
//			caratula.inf108_2_inst = cursor.getString(cursor.getColumnIndex("INF108_2_INST"));
//			caratula.inf108_2_pers = cursor.getString(cursor.getColumnIndex("INF108_2_PERS"));
//			caratula.inf109 = cursor.getString(cursor.getColumnIndex("INF109"))==null?null:cursor.getInt(cursor.getColumnIndex("INF109"));
//			caratula.inf110_tot = cursor.getString(cursor.getColumnIndex("INF110_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF110_TOT"));
//			caratula.inf110_tot_ofi_h = cursor.getString(cursor.getColumnIndex("INF110_TOT_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF110_TOT_OFI_H"));
//			caratula.inf110_tot_ofi_m = cursor.getString(cursor.getColumnIndex("INF110_TOT_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF110_TOT_OFI_M"));
//			caratula.inf110_tot_sof_h = cursor.getString(cursor.getColumnIndex("INF110_TOT_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF110_TOT_SOF_H"));
//			caratula.inf110_tot_sof_m = cursor.getString(cursor.getColumnIndex("INF110_TOT_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF110_TOT_SOF_M"));
//			caratula.inf110_tot_esp_h = cursor.getString(cursor.getColumnIndex("INF110_TOT_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF110_TOT_ESP_H"));
//			caratula.inf110_tot_esp_m = cursor.getString(cursor.getColumnIndex("INF110_TOT_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF110_TOT_ESP_M"));
//			caratula.inf110_tot2 = cursor.getString(cursor.getColumnIndex("INF110_TOT2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF110_TOT2"));
//			caratula.inf110_tot_ser = cursor.getString(cursor.getColumnIndex("INF110_TOT_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF110_TOT_SER"));
//			caratula.inf110_tot_fra = cursor.getString(cursor.getColumnIndex("INF110_TOT_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF110_TOT_FRA"));
//			caratula.inf111_adm = cursor.getString(cursor.getColumnIndex("INF111_ADM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF111_ADM"));
//			caratula.inf111_adm_ofi_h = cursor.getString(cursor.getColumnIndex("INF111_ADM_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF111_ADM_OFI_H"));
//			caratula.inf111_adm_ofi_m = cursor.getString(cursor.getColumnIndex("INF111_ADM_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF111_ADM_OFI_M"));
//			caratula.inf111_adm_sof_h = cursor.getString(cursor.getColumnIndex("INF111_ADM_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF111_ADM_SOF_H"));
//			caratula.inf111_adm_sof_m = cursor.getString(cursor.getColumnIndex("INF111_ADM_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF111_ADM_SOF_M"));
//			caratula.inf111_adm_esp_h = cursor.getString(cursor.getColumnIndex("INF111_ADM_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF111_ADM_ESP_H"));
//			caratula.inf111_adm_esp_m = cursor.getString(cursor.getColumnIndex("INF111_ADM_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF111_ADM_ESP_M"));
//			caratula.inf111_adm2 = cursor.getString(cursor.getColumnIndex("INF111_ADM2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF111_ADM2"));
//			caratula.inf111_adm_ser = cursor.getString(cursor.getColumnIndex("INF111_ADM_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF111_ADM_SER"));
//			caratula.inf111_adm_fra = cursor.getString(cursor.getColumnIndex("INF111_ADM_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF111_ADM_FRA"));
//			caratula.inf112_mot = cursor.getString(cursor.getColumnIndex("INF112_MOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF112_MOT"));
//			caratula.inf112_mot_ofi_h = cursor.getString(cursor.getColumnIndex("INF112_MOT_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF112_MOT_OFI_H"));
//			caratula.inf112_mot_ofi_m = cursor.getString(cursor.getColumnIndex("INF112_MOT_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF112_MOT_OFI_M"));
//			caratula.inf112_mot_sof_h = cursor.getString(cursor.getColumnIndex("INF112_MOT_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF112_MOT_SOF_H"));
//			caratula.inf112_mot_sof_m = cursor.getString(cursor.getColumnIndex("INF112_MOT_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF112_MOT_SOF_M"));
//			caratula.inf112_mot_esp_h = cursor.getString(cursor.getColumnIndex("INF112_MOT_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF112_MOT_ESP_H"));
//			caratula.inf112_mot_esp_m = cursor.getString(cursor.getColumnIndex("INF112_MOT_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF112_MOT_ESP_M"));
//			caratula.inf112_mot2 = cursor.getString(cursor.getColumnIndex("INF112_MOT2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF112_MOT2"));
//			caratula.inf112_mot_ser = cursor.getString(cursor.getColumnIndex("INF112_MOT_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF112_MOT_SER"));
//			caratula.inf112_mot_fra = cursor.getString(cursor.getColumnIndex("INF112_MOT_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF112_MOT_FRA"));
//			caratula.inf113_pie = cursor.getString(cursor.getColumnIndex("INF113_PIE"))==null?null:cursor.getInt(cursor.getColumnIndex("INF113_PIE"));
//			caratula.inf113_pie_ofi_h = cursor.getString(cursor.getColumnIndex("INF113_PIE_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF113_PIE_OFI_H"));
//			caratula.inf113_pie_ofi_m = cursor.getString(cursor.getColumnIndex("INF113_PIE_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF113_PIE_OFI_M"));
//			caratula.inf113_pie_sof_h = cursor.getString(cursor.getColumnIndex("INF113_PIE_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF113_PIE_SOF_H"));
//			caratula.inf113_pie_sof_m = cursor.getString(cursor.getColumnIndex("INF113_PIE_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF113_PIE_SOF_M"));
//			caratula.inf113_pie_esp_h = cursor.getString(cursor.getColumnIndex("INF113_PIE_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF113_PIE_ESP_H"));
//			caratula.inf113_pie_esp_m = cursor.getString(cursor.getColumnIndex("INF113_PIE_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF113_PIE_ESP_M"));
//			caratula.inf113_pie2 = cursor.getString(cursor.getColumnIndex("INF113_PIE2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF113_PIE2"));
//			caratula.inf113_pie_ser = cursor.getString(cursor.getColumnIndex("INF113_PIE_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF113_PIE_SER"));
//			caratula.inf113_pie_fra = cursor.getString(cursor.getColumnIndex("INF113_PIE_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF113_PIE_FRA"));
//			caratula.inf114_inv = cursor.getString(cursor.getColumnIndex("INF114_INV"))==null?null:cursor.getInt(cursor.getColumnIndex("INF114_INV"));
//			caratula.inf114_inv_ofi_h = cursor.getString(cursor.getColumnIndex("INF114_INV_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF114_INV_OFI_H"));
//			caratula.inf114_inv_ofi_m = cursor.getString(cursor.getColumnIndex("INF114_INV_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF114_INV_OFI_M"));
//			caratula.inf114_inv_sof_h = cursor.getString(cursor.getColumnIndex("INF114_INV_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF114_INV_SOF_H"));
//			caratula.inf114_inv_sof_m = cursor.getString(cursor.getColumnIndex("INF114_INV_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF114_INV_SOF_M"));
//			caratula.inf114_inv_esp_h = cursor.getString(cursor.getColumnIndex("INF114_INV_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF114_INV_ESP_H"));
//			caratula.inf114_inv_esp_m = cursor.getString(cursor.getColumnIndex("INF114_INV_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF114_INV_ESP_M"));
//			caratula.inf114_inv2 = cursor.getString(cursor.getColumnIndex("INF114_INV2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF114_INV2"));
//			caratula.inf114_inv_ser = cursor.getString(cursor.getColumnIndex("INF114_INV_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF114_INV_SER"));
//			caratula.inf114_inv_fra = cursor.getString(cursor.getColumnIndex("INF114_INV_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF114_INV_FRA"));
//			caratula.inf115_inv_at = cursor.getString(cursor.getColumnIndex("INF115_INV_AT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF115_INV_AT"));
//			caratula.inf115_inv_at_ofi_h = cursor.getString(cursor.getColumnIndex("INF115_INV_AT_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF115_INV_AT_OFI_H"));
//			caratula.inf115_inv_at_ofi_m = cursor.getString(cursor.getColumnIndex("INF115_INV_AT_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF115_INV_AT_OFI_M"));
//			caratula.inf115_inv_at_sof_h = cursor.getString(cursor.getColumnIndex("INF115_INV_AT_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF115_INV_AT_SOF_H"));
//			caratula.inf115_inv_at_sof_m = cursor.getString(cursor.getColumnIndex("INF115_INV_AT_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF115_INV_AT_SOF_M"));
//			caratula.inf115_inv_at_esp_h = cursor.getString(cursor.getColumnIndex("INF115_INV_AT_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF115_INV_AT_ESP_H"));
//			caratula.inf115_inv_at_esp_m = cursor.getString(cursor.getColumnIndex("INF115_INV_AT_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF115_INV_AT_ESP_M"));
//			caratula.inf115_inv2_at = cursor.getString(cursor.getColumnIndex("INF115_INV2_AT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF115_INV2_AT"));
//			caratula.inf115_inv_at_ser = cursor.getString(cursor.getColumnIndex("INF115_INV_AT_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF115_INV_AT_SER"));
//			caratula.inf115_inv_at_fra = cursor.getString(cursor.getColumnIndex("INF115_INV_AT_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF115_INV_AT_FRA"));
//			caratula.inf116_inv_vf = cursor.getString(cursor.getColumnIndex("INF116_INV_VF"))==null?null:cursor.getInt(cursor.getColumnIndex("INF116_INV_VF"));
//			caratula.inf116_inv_vf_ofi_h = cursor.getString(cursor.getColumnIndex("INF116_INV_VF_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF116_INV_VF_OFI_H"));
//			caratula.inf116_inv_vf_ofi_m = cursor.getString(cursor.getColumnIndex("INF116_INV_VF_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF116_INV_VF_OFI_M"));
//			caratula.inf116_inv_vf_sof_h = cursor.getString(cursor.getColumnIndex("INF116_INV_VF_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF116_INV_VF_SOF_H"));
//			caratula.inf116_inv_vf_sof_m = cursor.getString(cursor.getColumnIndex("INF116_INV_VF_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF116_INV_VF_SOF_M"));
//			caratula.inf116_inv_vf_esp_h = cursor.getString(cursor.getColumnIndex("INF116_INV_VF_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF116_INV_VF_ESP_H"));
//			caratula.inf116_inv_vf_esp_m = cursor.getString(cursor.getColumnIndex("INF116_INV_VF_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF116_INV_VF_ESP_M"));
//			caratula.inf116_inv2_vf = cursor.getString(cursor.getColumnIndex("INF116_INV2_VF"))==null?null:cursor.getInt(cursor.getColumnIndex("INF116_INV2_VF"));
//			caratula.inf116_inv_vf_ser = cursor.getString(cursor.getColumnIndex("INF116_INV_VF_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF116_INV_VF_SER"));
//			caratula.inf116_inv_vf_fra = cursor.getString(cursor.getColumnIndex("INF116_INV_VF_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF116_INV_VF_FRA"));
//			caratula.inf117_opc = cursor.getString(cursor.getColumnIndex("INF117_OPC"))==null?null:cursor.getInt(cursor.getColumnIndex("INF117_OPC"));
//			caratula.inf117_opc_ofi_h = cursor.getString(cursor.getColumnIndex("INF117_OPC_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF117_OPC_OFI_H"));
//			caratula.inf117_opc_ofi_m = cursor.getString(cursor.getColumnIndex("INF117_OPC_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF117_OPC_OFI_M"));
//			caratula.inf117_opc_sof_h = cursor.getString(cursor.getColumnIndex("INF117_OPC_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF117_OPC_SOF_H"));
//			caratula.inf117_opc_sof_m = cursor.getString(cursor.getColumnIndex("INF117_OPC_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF117_OPC_SOF_M"));
//			caratula.inf117_opc_esp_h = cursor.getString(cursor.getColumnIndex("INF117_OPC_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF117_OPC_ESP_H"));
//			caratula.inf117_opc_esp_m = cursor.getString(cursor.getColumnIndex("INF117_OPC_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF117_OPC_ESP_M"));
//			caratula.inf117_opc2 = cursor.getString(cursor.getColumnIndex("INF117_OPC2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF117_OPC2"));
//			caratula.inf117_opc_ser = cursor.getString(cursor.getColumnIndex("INF117_OPC_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF117_OPC_SER"));
//			caratula.inf117_opc_fra = cursor.getString(cursor.getColumnIndex("INF117_OPC_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF117_OPC_FRA"));
//			caratula.inf118_pg = cursor.getString(cursor.getColumnIndex("INF118_PG"))==null?null:cursor.getInt(cursor.getColumnIndex("INF118_PG"));
//			caratula.inf118_pg_ofi_h = cursor.getString(cursor.getColumnIndex("INF118_PG_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF118_PG_OFI_H"));
//			caratula.inf118_pg_ofi_m = cursor.getString(cursor.getColumnIndex("INF118_PG_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF118_PG_OFI_M"));
//			caratula.inf118_pg_sof_h = cursor.getString(cursor.getColumnIndex("INF118_PG_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF118_PG_SOF_H"));
//			caratula.inf118_pg_sof_m = cursor.getString(cursor.getColumnIndex("INF118_PG_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF118_PG_SOF_M"));
//			caratula.inf118_pg_esp_h = cursor.getString(cursor.getColumnIndex("INF118_PG_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF118_PG_ESP_H"));
//			caratula.inf118_pg_esp_m = cursor.getString(cursor.getColumnIndex("INF118_PG_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF118_PG_ESP_M"));
//			caratula.inf118_pg2 = cursor.getString(cursor.getColumnIndex("INF118_PG2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF118_PG2"));
//			caratula.inf118_pg_ser = cursor.getString(cursor.getColumnIndex("INF118_PG_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF118_PG_SER"));
//			caratula.inf118_pg_fra = cursor.getString(cursor.getColumnIndex("INF118_PG_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF118_PG_FRA"));
//			caratula.inf119_sc = cursor.getString(cursor.getColumnIndex("INF119_SC"))==null?null:cursor.getInt(cursor.getColumnIndex("INF119_SC"));
//			caratula.inf119_sc_ofi_h = cursor.getString(cursor.getColumnIndex("INF119_SC_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF119_SC_OFI_H"));
//			caratula.inf119_sc_ofi_m = cursor.getString(cursor.getColumnIndex("INF119_SC_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF119_SC_OFI_M"));
//			caratula.inf119_sc_sof_h = cursor.getString(cursor.getColumnIndex("INF119_SC_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF119_SC_SOF_H"));
//			caratula.inf119_sc_sof_m = cursor.getString(cursor.getColumnIndex("INF119_SC_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF119_SC_SOF_M"));
//			caratula.inf119_sc_esp_h = cursor.getString(cursor.getColumnIndex("INF119_SC_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF119_SC_ESP_H"));
//			caratula.inf119_sc_esp_m = cursor.getString(cursor.getColumnIndex("INF119_SC_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF119_SC_ESP_M"));
//			caratula.inf119_sc2 = cursor.getString(cursor.getColumnIndex("INF119_SC2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF119_SC2"));
//			caratula.inf119_sc_ser = cursor.getString(cursor.getColumnIndex("INF119_SC_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF119_SC_SER"));
//			caratula.inf119_sc_fra = cursor.getString(cursor.getColumnIndex("INF119_SC_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF119_SC_FRA"));
//			caratula.inf120_o = cursor.getString(cursor.getColumnIndex("INF120_O"))==null?null:cursor.getInt(cursor.getColumnIndex("INF120_O"));
//			caratula.inf120_o_o = cursor.getString(cursor.getColumnIndex("INF120_O_O"));
//			caratula.inf120_o_ofi_h = cursor.getString(cursor.getColumnIndex("INF120_O_OFI_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF120_O_OFI_H"));
//			caratula.inf120_o_ofi_m = cursor.getString(cursor.getColumnIndex("INF120_O_OFI_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF120_O_OFI_M"));
//			caratula.inf120_o_sof_h = cursor.getString(cursor.getColumnIndex("INF120_O_SOF_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF120_O_SOF_H"));
//			caratula.inf120_o_sof_m = cursor.getString(cursor.getColumnIndex("INF120_O_SOF_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF120_O_SOF_M"));
//			caratula.inf120_o_esp_h = cursor.getString(cursor.getColumnIndex("INF120_O_ESP_H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF120_O_ESP_H"));
//			caratula.inf120_o_esp_m = cursor.getString(cursor.getColumnIndex("INF120_O_ESP_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF120_O_ESP_M"));
//			caratula.inf120_o2 = cursor.getString(cursor.getColumnIndex("INF120_O2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF120_O2"));
//			caratula.inf120_o_ser = cursor.getString(cursor.getColumnIndex("INF120_O_SER"))==null?null:cursor.getInt(cursor.getColumnIndex("INF120_O_SER"));
//			caratula.inf120_o_fra = cursor.getString(cursor.getColumnIndex("INF120_O_FRA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF120_O_FRA"));
//			caratula.obs_cap_100 = cursor.getString(cursor.getColumnIndex("OBS_CAP_100"));
//
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap200> fillINF_Capitulo200s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap200> caratulas = new ArrayList<INF_Cap200>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_200 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap200 data = new INF_Cap200();
//
//			List<String> allFieldMatches = data.getFieldMatches(201, 255, "ID_N", "OBS_CAP_200_1", "OBS_CAP_200_2",
//					"OBS_CAP_200_3", "OBS_CAP_200_4", "OBS_CAP_200_5", "OBS_CAP_200_6", "OBS_CAP_200_7",
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (INF_Cap200)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			caratulas.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap200> fillINF_Capitulo200s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap200> caratulas = new ArrayList<INF_Cap200>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_200 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap200 caratula = new INF_Cap200();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.inf201 = cursor.getString(cursor.getColumnIndex("INF201"))==null?null:cursor.getInt(cursor.getColumnIndex("INF201"));
//			caratula.inf201_o = cursor.getString(cursor.getColumnIndex("INF201_O"));
//			caratula.inf202_1a = cursor.getString(cursor.getColumnIndex("INF202_1A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_1A"));
//			caratula.inf202_1b = cursor.getString(cursor.getColumnIndex("INF202_1B"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_1B"));
//			caratula.inf202_1c = cursor.getString(cursor.getColumnIndex("INF202_1C"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_1C"));
//			caratula.inf202_1d = cursor.getString(cursor.getColumnIndex("INF202_1D"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_1D"));
//			caratula.inf202_1e = cursor.getString(cursor.getColumnIndex("INF202_1E"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_1E"));
//			caratula.inf202_1f = cursor.getString(cursor.getColumnIndex("INF202_1F"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_1F"));
//			caratula.inf202_1g = cursor.getString(cursor.getColumnIndex("INF202_1G"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_1G"));
//			caratula.inf202_1g_o = cursor.getString(cursor.getColumnIndex("INF202_1G_O"));
//			caratula.inf202_2h = cursor.getString(cursor.getColumnIndex("INF202_2H"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_2H"));
//			caratula.inf202_2i = cursor.getString(cursor.getColumnIndex("INF202_2I"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_2I"));
//			caratula.inf202_2i_o = cursor.getString(cursor.getColumnIndex("INF202_2I_O"));
//			caratula.inf202_3j = cursor.getString(cursor.getColumnIndex("INF202_3J"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_3J"));
//			caratula.inf202_3k = cursor.getString(cursor.getColumnIndex("INF202_3K"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_3K"));
//			caratula.inf202_3l1 = cursor.getString(cursor.getColumnIndex("INF202_3L1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_3L1"));
//			caratula.inf202_3l2 = cursor.getString(cursor.getColumnIndex("INF202_3L2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_3L2"));
//			caratula.inf202_3m = cursor.getString(cursor.getColumnIndex("INF202_3M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_3M"));
//			caratula.inf202_3n = cursor.getString(cursor.getColumnIndex("INF202_3N"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_3N"));
//			caratula.inf202_3n_o = cursor.getString(cursor.getColumnIndex("INF202_3N_O"));
//			caratula.inf202_4_ni = cursor.getString(cursor.getColumnIndex("INF202_4_NI"))==null?null:cursor.getInt(cursor.getColumnIndex("INF202_4_NI"));
//			caratula.inf202_4_ni_o = cursor.getString(cursor.getColumnIndex("INF202_4_NI_O"));
//			caratula.inf203 = cursor.getString(cursor.getColumnIndex("INF203"))==null?null:cursor.getInt(cursor.getColumnIndex("INF203"));
//			caratula.inf204 = cursor.getString(cursor.getColumnIndex("INF204"))==null?null:cursor.getInt(cursor.getColumnIndex("INF204"));
//			caratula.inf205 = cursor.getString(cursor.getColumnIndex("INF205"))==null?null:cursor.getInt(cursor.getColumnIndex("INF205"));
//			caratula.inf205a = cursor.getString(cursor.getColumnIndex("INF205A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF205A"));
//			caratula.inf206a = cursor.getString(cursor.getColumnIndex("INF206A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF206A"));
//			caratula.inf207a = cursor.getString(cursor.getColumnIndex("INF207A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF207A"));
//			caratula.inf206b = cursor.getString(cursor.getColumnIndex("INF206B"))==null?null:cursor.getInt(cursor.getColumnIndex("INF206B"));
//			caratula.inf207b = cursor.getString(cursor.getColumnIndex("INF207B"))==null?null:cursor.getInt(cursor.getColumnIndex("INF207B"));
//			caratula.inf206c = cursor.getString(cursor.getColumnIndex("INF206C"))==null?null:cursor.getInt(cursor.getColumnIndex("INF206C"));
//			caratula.inf207c = cursor.getString(cursor.getColumnIndex("INF207C"))==null?null:cursor.getInt(cursor.getColumnIndex("INF207C"));
//			caratula.inf206d = cursor.getString(cursor.getColumnIndex("INF206D"))==null?null:cursor.getInt(cursor.getColumnIndex("INF206D"));
//			caratula.inf207d = cursor.getString(cursor.getColumnIndex("INF207D"))==null?null:cursor.getInt(cursor.getColumnIndex("INF207D"));
//			caratula.inf206e = cursor.getString(cursor.getColumnIndex("INF206E"))==null?null:cursor.getInt(cursor.getColumnIndex("INF206E"));
//			caratula.inf207e = cursor.getString(cursor.getColumnIndex("INF207E"))==null?null:cursor.getInt(cursor.getColumnIndex("INF207E"));
//			caratula.inf206f = cursor.getString(cursor.getColumnIndex("INF206F"))==null?null:cursor.getInt(cursor.getColumnIndex("INF206F"));
//			caratula.inf207f = cursor.getString(cursor.getColumnIndex("INF207F"))==null?null:cursor.getInt(cursor.getColumnIndex("INF207F"));
//			caratula.inf206g = cursor.getString(cursor.getColumnIndex("INF206G"))==null?null:cursor.getInt(cursor.getColumnIndex("INF206G"));
//			caratula.inf206g_o = cursor.getString(cursor.getColumnIndex("INF206G_O"));
//			caratula.inf207g = cursor.getString(cursor.getColumnIndex("INF207G"))==null?null:cursor.getInt(cursor.getColumnIndex("INF207G"));
//			caratula.inf208 = cursor.getString(cursor.getColumnIndex("INF208"))==null?null:cursor.getInt(cursor.getColumnIndex("INF208"));
//			caratula.inf208_o = cursor.getString(cursor.getColumnIndex("INF208_O"));
//			caratula.obs_cap_200_1 = cursor.getString(cursor.getColumnIndex("OBS_CAP_200_1"));
//			caratula.inf209 = cursor.getString(cursor.getColumnIndex("INF209"))==null?null:cursor.getInt(cursor.getColumnIndex("INF209"));
//			caratula.inf209_o = cursor.getString(cursor.getColumnIndex("INF209_O"));
//			caratula.inf210 = cursor.getString(cursor.getColumnIndex("INF210"))==null?null:cursor.getInt(cursor.getColumnIndex("INF210"));
//			caratula.inf210_o = cursor.getString(cursor.getColumnIndex("INF210_O"));
//			caratula.inf211 = cursor.getString(cursor.getColumnIndex("INF211"))==null?null:cursor.getInt(cursor.getColumnIndex("INF211"));
//			caratula.inf212 = cursor.getString(cursor.getColumnIndex("INF212"))==null?null:cursor.getInt(cursor.getColumnIndex("INF212"));
//			caratula.inf212a = cursor.getString(cursor.getColumnIndex("INF212A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF212A"));
//			caratula.inf212b = cursor.getString(cursor.getColumnIndex("INF212B"))==null?null:cursor.getInt(cursor.getColumnIndex("INF212B"));
//			caratula.inf212c = cursor.getString(cursor.getColumnIndex("INF212C"))==null?null:cursor.getInt(cursor.getColumnIndex("INF212C"));
//			caratula.inf213 = cursor.getString(cursor.getColumnIndex("INF213"))==null?null:cursor.getInt(cursor.getColumnIndex("INF213"));
//			caratula.inf213_o = cursor.getString(cursor.getColumnIndex("INF213_O"));
//			caratula.inf214 = cursor.getString(cursor.getColumnIndex("INF214"))==null?null:cursor.getInt(cursor.getColumnIndex("INF214"));
//			caratula.inf215 = cursor.getString(cursor.getColumnIndex("INF215"))==null?null:cursor.getInt(cursor.getColumnIndex("INF215"));
//			caratula.inf216 = cursor.getString(cursor.getColumnIndex("INF216"))==null?null:cursor.getInt(cursor.getColumnIndex("INF216"));
//			caratula.inf217_1 = cursor.getString(cursor.getColumnIndex("INF217_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF217_1"));
//			caratula.inf217_2 = cursor.getString(cursor.getColumnIndex("INF217_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF217_2"));
//			caratula.inf217_3 = cursor.getString(cursor.getColumnIndex("INF217_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF217_3"));
//			caratula.inf217_4 = cursor.getString(cursor.getColumnIndex("INF217_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF217_4"));
//			caratula.inf217_5 = cursor.getString(cursor.getColumnIndex("INF217_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF217_5"));
//			caratula.inf217_5_o = cursor.getString(cursor.getColumnIndex("INF217_5_O"));
//			caratula.inf218 = cursor.getString(cursor.getColumnIndex("INF218"))==null?null:cursor.getInt(cursor.getColumnIndex("INF218"));
//			caratula.inf218_o = cursor.getString(cursor.getColumnIndex("INF218_O"));
//			caratula.obs_cap_200_2 = cursor.getString(cursor.getColumnIndex("OBS_CAP_200_2"));
//			caratula.inf219 = cursor.getString(cursor.getColumnIndex("INF219"))==null?null:cursor.getInt(cursor.getColumnIndex("INF219"));
//			caratula.inf219a = cursor.getString(cursor.getColumnIndex("INF219A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF219A"));
//			caratula.inf219b = cursor.getString(cursor.getColumnIndex("INF219B"))==null?null:cursor.getInt(cursor.getColumnIndex("INF219B"));
//			caratula.inf219c = cursor.getString(cursor.getColumnIndex("INF219C"))==null?null:cursor.getInt(cursor.getColumnIndex("INF219C"));
//			caratula.inf220 = cursor.getString(cursor.getColumnIndex("INF220"))==null?null:cursor.getInt(cursor.getColumnIndex("INF220"));
//			caratula.inf221 = cursor.getString(cursor.getColumnIndex("INF221"))==null?null:cursor.getInt(cursor.getColumnIndex("INF221"));
//			caratula.inf222 = cursor.getString(cursor.getColumnIndex("INF222"))==null?null:cursor.getInt(cursor.getColumnIndex("INF222"));
//			caratula.inf222_1 = cursor.getString(cursor.getColumnIndex("INF222_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF222_1"));
//			caratula.inf222_o = cursor.getString(cursor.getColumnIndex("INF222_O"));
//			caratula.inf223 = cursor.getString(cursor.getColumnIndex("INF223"))==null?null:cursor.getInt(cursor.getColumnIndex("INF223"));
//			caratula.inf223_o = cursor.getString(cursor.getColumnIndex("INF223_O"));
//			caratula.inf224_1 = cursor.getString(cursor.getColumnIndex("INF224_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF224_1"));
//			caratula.inf224_2 = cursor.getString(cursor.getColumnIndex("INF224_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF224_2"));
//			caratula.inf224_3 = cursor.getString(cursor.getColumnIndex("INF224_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF224_3"));
//			caratula.inf224_4 = cursor.getString(cursor.getColumnIndex("INF224_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF224_4"));
//			caratula.inf224_5 = cursor.getString(cursor.getColumnIndex("INF224_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF224_5"));
//			caratula.inf224_5_o = cursor.getString(cursor.getColumnIndex("INF224_5_O"));
//			caratula.inf225 = cursor.getString(cursor.getColumnIndex("INF225"))==null?null:cursor.getInt(cursor.getColumnIndex("INF225"));
//			caratula.inf226 = cursor.getString(cursor.getColumnIndex("INF226"))==null?null:cursor.getInt(cursor.getColumnIndex("INF226"));
//			caratula.inf227_1 = cursor.getString(cursor.getColumnIndex("INF227_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF227_1"));
//			caratula.inf227_1_1 = cursor.getString(cursor.getColumnIndex("INF227_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF227_1_1"));
//			caratula.inf227_1_2 = cursor.getString(cursor.getColumnIndex("INF227_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF227_1_2"));
//			caratula.inf227_1_3 = cursor.getString(cursor.getColumnIndex("INF227_1_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF227_1_3"));
//			caratula.inf227_2 = cursor.getString(cursor.getColumnIndex("INF227_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF227_2"));
//			caratula.inf227_2_1 = cursor.getString(cursor.getColumnIndex("INF227_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF227_2_1"));
//			caratula.inf227_2_2 = cursor.getString(cursor.getColumnIndex("INF227_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF227_2_2"));
//			caratula.inf227_2_3 = cursor.getString(cursor.getColumnIndex("INF227_2_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF227_2_3"));
//			caratula.inf228_1 = cursor.getString(cursor.getColumnIndex("INF228_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_1"));
//			caratula.inf228_1_1 = cursor.getString(cursor.getColumnIndex("INF228_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_1_1"));
//			caratula.inf228_1_2 = cursor.getString(cursor.getColumnIndex("INF228_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_1_2"));
//			caratula.inf228_1_3 = cursor.getString(cursor.getColumnIndex("INF228_1_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_1_3"));
//			caratula.inf228_2 = cursor.getString(cursor.getColumnIndex("INF228_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_2"));
//			caratula.inf228_2_1 = cursor.getString(cursor.getColumnIndex("INF228_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_2_1"));
//			caratula.inf228_2_2 = cursor.getString(cursor.getColumnIndex("INF228_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_2_2"));
//			caratula.inf228_2_3 = cursor.getString(cursor.getColumnIndex("INF228_2_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_2_3"));
//			caratula.inf228_3 = cursor.getString(cursor.getColumnIndex("INF228_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_3"));
//			caratula.inf228_3_3 = cursor.getString(cursor.getColumnIndex("INF228_3_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_3_3"));
//			caratula.inf228_4 = cursor.getString(cursor.getColumnIndex("INF228_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_4"));
//			caratula.inf228_4_o = cursor.getString(cursor.getColumnIndex("INF228_4_O"));
//			caratula.inf228_4_1 = cursor.getString(cursor.getColumnIndex("INF228_4_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_4_1"));
//			caratula.inf228_4_2 = cursor.getString(cursor.getColumnIndex("INF228_4_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_4_2"));
//			caratula.inf228_4_3 = cursor.getString(cursor.getColumnIndex("INF228_4_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF228_4_3"));
//			caratula.obs_cap_200_3 = cursor.getString(cursor.getColumnIndex("OBS_CAP_200_3"));
//			caratula.inf229_1 = cursor.getString(cursor.getColumnIndex("INF229_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF229_1"));
//			caratula.inf229_1_1 = cursor.getString(cursor.getColumnIndex("INF229_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF229_1_1"));
//			caratula.inf229_1_2 = cursor.getString(cursor.getColumnIndex("INF229_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF229_1_2"));
//			caratula.inf229_1_3 = cursor.getString(cursor.getColumnIndex("INF229_1_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF229_1_3"));
//			caratula.inf229_2 = cursor.getString(cursor.getColumnIndex("INF229_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF229_2"));
//			caratula.inf229_2_1 = cursor.getString(cursor.getColumnIndex("INF229_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF229_2_1"));
//			caratula.inf229_2_2 = cursor.getString(cursor.getColumnIndex("INF229_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF229_2_2"));
//			caratula.inf229_2_3 = cursor.getString(cursor.getColumnIndex("INF229_2_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF229_2_3"));
//			caratula.inf230_1 = cursor.getString(cursor.getColumnIndex("INF230_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_1"));
//			caratula.inf230_1_1 = cursor.getString(cursor.getColumnIndex("INF230_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_1_1"));
//			caratula.inf230_1_2 = cursor.getString(cursor.getColumnIndex("INF230_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_1_2"));
//			caratula.inf230_1_3 = cursor.getString(cursor.getColumnIndex("INF230_1_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_1_3"));
//			caratula.inf230_2 = cursor.getString(cursor.getColumnIndex("INF230_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_2"));
//			caratula.inf230_2_1 = cursor.getString(cursor.getColumnIndex("INF230_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_2_1"));
//			caratula.inf230_2_2 = cursor.getString(cursor.getColumnIndex("INF230_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_2_2"));
//			caratula.inf230_2_3 = cursor.getString(cursor.getColumnIndex("INF230_2_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_2_3"));
//			caratula.inf230_3 = cursor.getString(cursor.getColumnIndex("INF230_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_3"));
//			caratula.inf230_3_3 = cursor.getString(cursor.getColumnIndex("INF230_3_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_3_3"));
//			caratula.inf230_4 = cursor.getString(cursor.getColumnIndex("INF230_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_4"));
//			caratula.inf230_4_o = cursor.getString(cursor.getColumnIndex("INF230_4_O"));
//			caratula.inf230_4_1 = cursor.getString(cursor.getColumnIndex("INF230_4_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_4_1"));
//			caratula.inf230_4_2 = cursor.getString(cursor.getColumnIndex("INF230_4_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_4_2"));
//			caratula.inf230_4_3 = cursor.getString(cursor.getColumnIndex("INF230_4_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF230_4_3"));
//			caratula.inf231_1 = cursor.getString(cursor.getColumnIndex("INF231_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF231_1"));
//			caratula.inf231_1_1 = cursor.getString(cursor.getColumnIndex("INF231_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF231_1_1"));
//			caratula.inf231_1_2 = cursor.getString(cursor.getColumnIndex("INF231_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF231_1_2"));
//			caratula.inf231_1_3 = cursor.getString(cursor.getColumnIndex("INF231_1_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF231_1_3"));
//			caratula.inf231_2 = cursor.getString(cursor.getColumnIndex("INF231_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF231_2"));
//			caratula.inf231_2_1 = cursor.getString(cursor.getColumnIndex("INF231_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF231_2_1"));
//			caratula.inf231_2_2 = cursor.getString(cursor.getColumnIndex("INF231_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF231_2_2"));
//			caratula.inf231_2_3 = cursor.getString(cursor.getColumnIndex("INF231_2_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF231_2_3"));
//			caratula.inf232 = cursor.getString(cursor.getColumnIndex("INF232"))==null?null:cursor.getInt(cursor.getColumnIndex("INF232"));
//			caratula.inf232_o = cursor.getString(cursor.getColumnIndex("INF232_O"));
//			caratula.inf233 = cursor.getString(cursor.getColumnIndex("INF233"))==null?null:cursor.getInt(cursor.getColumnIndex("INF233"));
//			caratula.inf233_o = cursor.getString(cursor.getColumnIndex("INF233_O"));
//			caratula.inf234 = cursor.getString(cursor.getColumnIndex("INF234"))==null?null:cursor.getInt(cursor.getColumnIndex("INF234"));
//			caratula.inf234_o = cursor.getString(cursor.getColumnIndex("INF234_O"));
//			caratula.inf235_p = cursor.getString(cursor.getColumnIndex("INF235_P"))==null?null:cursor.getInt(cursor.getColumnIndex("INF235_P"));
//			caratula.inf235_v = cursor.getString(cursor.getColumnIndex("INF235_V"))==null?null:cursor.getInt(cursor.getColumnIndex("INF235_V"));
//			caratula.inf236_1 = cursor.getString(cursor.getColumnIndex("INF236_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_1"));
//			caratula.inf236_1_cant = cursor.getString(cursor.getColumnIndex("INF236_1_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_1_CANT"));
//			caratula.inf236_2 = cursor.getString(cursor.getColumnIndex("INF236_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_2"));
//			caratula.inf236_2_cant = cursor.getString(cursor.getColumnIndex("INF236_2_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_2_CANT"));
//			caratula.inf236_3 = cursor.getString(cursor.getColumnIndex("INF236_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_3"));
//			caratula.inf236_3_cant = cursor.getString(cursor.getColumnIndex("INF236_3_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_3_CANT"));
//			caratula.inf236_4 = cursor.getString(cursor.getColumnIndex("INF236_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_4"));
//			caratula.inf236_4_cant = cursor.getString(cursor.getColumnIndex("INF236_4_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_4_CANT"));
//			caratula.inf236_5 = cursor.getString(cursor.getColumnIndex("INF236_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_5"));
//			caratula.inf236_5_cant = cursor.getString(cursor.getColumnIndex("INF236_5_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_5_CANT"));
//			caratula.inf236_6 = cursor.getString(cursor.getColumnIndex("INF236_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_6"));
//			caratula.inf236_6_cant = cursor.getString(cursor.getColumnIndex("INF236_6_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_6_CANT"));
//			caratula.inf236_7 = cursor.getString(cursor.getColumnIndex("INF236_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_7"));
//			caratula.inf236_7_o = cursor.getString(cursor.getColumnIndex("INF236_7_O"));
//			caratula.inf236_7_cant = cursor.getString(cursor.getColumnIndex("INF236_7_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_7_CANT"));
//			caratula.inf236_tot_cant = cursor.getString(cursor.getColumnIndex("INF236_TOT_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF236_TOT_CANT"));
//			caratula.obs_cap_200_4 = cursor.getString(cursor.getColumnIndex("OBS_CAP_200_4"));
//			caratula.inf237_1 = cursor.getString(cursor.getColumnIndex("INF237_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_1"));
//			caratula.inf237_1_est1 = cursor.getString(cursor.getColumnIndex("INF237_1_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_1_EST1"));
//			caratula.inf237_1_est2 = cursor.getString(cursor.getColumnIndex("INF237_1_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_1_EST2"));
//			caratula.inf237_1_est3 = cursor.getString(cursor.getColumnIndex("INF237_1_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_1_EST3"));
//			caratula.inf237_1_est4 = cursor.getString(cursor.getColumnIndex("INF237_1_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_1_EST4"));
//			caratula.inf237_2 = cursor.getString(cursor.getColumnIndex("INF237_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_2"));
//			caratula.inf237_2_est1 = cursor.getString(cursor.getColumnIndex("INF237_2_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_2_EST1"));
//			caratula.inf237_2_est2 = cursor.getString(cursor.getColumnIndex("INF237_2_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_2_EST2"));
//			caratula.inf237_2_est3 = cursor.getString(cursor.getColumnIndex("INF237_2_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_2_EST3"));
//			caratula.inf237_2_est4 = cursor.getString(cursor.getColumnIndex("INF237_2_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_2_EST4"));
//			caratula.inf237_3 = cursor.getString(cursor.getColumnIndex("INF237_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_3"));
//			caratula.inf237_3_est1 = cursor.getString(cursor.getColumnIndex("INF237_3_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_3_EST1"));
//			caratula.inf237_3_est2 = cursor.getString(cursor.getColumnIndex("INF237_3_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_3_EST2"));
//			caratula.inf237_3_est3 = cursor.getString(cursor.getColumnIndex("INF237_3_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_3_EST3"));
//			caratula.inf237_3_est4 = cursor.getString(cursor.getColumnIndex("INF237_3_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_3_EST4"));
//			caratula.inf237_4 = cursor.getString(cursor.getColumnIndex("INF237_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_4"));
//			caratula.inf237_4_est1 = cursor.getString(cursor.getColumnIndex("INF237_4_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_4_EST1"));
//			caratula.inf237_4_est3 = cursor.getString(cursor.getColumnIndex("INF237_4_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_4_EST3"));
//			caratula.inf237_4_est4 = cursor.getString(cursor.getColumnIndex("INF237_4_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_4_EST4"));
//			caratula.inf237_5 = cursor.getString(cursor.getColumnIndex("INF237_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_5"));
//			caratula.inf237_5_est1 = cursor.getString(cursor.getColumnIndex("INF237_5_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_5_EST1"));
//			caratula.inf237_5_est2 = cursor.getString(cursor.getColumnIndex("INF237_5_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_5_EST2"));
//			caratula.inf237_5_est3 = cursor.getString(cursor.getColumnIndex("INF237_5_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_5_EST3"));
//			caratula.inf237_5_est4 = cursor.getString(cursor.getColumnIndex("INF237_5_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_5_EST4"));
//			caratula.inf237_6 = cursor.getString(cursor.getColumnIndex("INF237_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_6"));
//			caratula.inf237_6_est1 = cursor.getString(cursor.getColumnIndex("INF237_6_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_6_EST1"));
//			caratula.inf237_6_est2 = cursor.getString(cursor.getColumnIndex("INF237_6_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_6_EST2"));
//			caratula.inf237_6_est3 = cursor.getString(cursor.getColumnIndex("INF237_6_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_6_EST3"));
//			caratula.inf237_6_est4 = cursor.getString(cursor.getColumnIndex("INF237_6_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_6_EST4"));
//			caratula.inf237_7 = cursor.getString(cursor.getColumnIndex("INF237_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_7"));
//			caratula.inf237_7_o = cursor.getString(cursor.getColumnIndex("INF237_7_O"));
//			caratula.inf237_7_est1 = cursor.getString(cursor.getColumnIndex("INF237_7_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_7_EST1"));
//			caratula.inf237_7_est2 = cursor.getString(cursor.getColumnIndex("INF237_7_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_7_EST2"));
//			caratula.inf237_7_est3 = cursor.getString(cursor.getColumnIndex("INF237_7_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_7_EST3"));
//			caratula.inf237_7_est4 = cursor.getString(cursor.getColumnIndex("INF237_7_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_7_EST4"));
//			caratula.inf237_tot_est1 = cursor.getString(cursor.getColumnIndex("INF237_TOT_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_TOT_EST1"));
//			caratula.inf237_tot_est2 = cursor.getString(cursor.getColumnIndex("INF237_TOT_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_TOT_EST2"));
//			caratula.inf237_tot_est3 = cursor.getString(cursor.getColumnIndex("INF237_TOT_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_TOT_EST3"));
//			caratula.inf237_tot_est4 = cursor.getString(cursor.getColumnIndex("INF237_TOT_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF237_TOT_EST4"));
//			caratula.inf238_1 = cursor.getString(cursor.getColumnIndex("INF238_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_1"));
//			caratula.inf238_1_tot = cursor.getString(cursor.getColumnIndex("INF238_1_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_1_TOT"));
//			caratula.inf238_1_rep = cursor.getString(cursor.getColumnIndex("INF238_1_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_1_REP"));
//			caratula.inf238_1_reem = cursor.getString(cursor.getColumnIndex("INF238_1_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_1_REEM"));
//			caratula.inf238_2 = cursor.getString(cursor.getColumnIndex("INF238_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_2"));
//			caratula.inf238_2_tot = cursor.getString(cursor.getColumnIndex("INF238_2_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_2_TOT"));
//			caratula.inf238_2_rep = cursor.getString(cursor.getColumnIndex("INF238_2_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_2_REP"));
//			caratula.inf238_2_reem = cursor.getString(cursor.getColumnIndex("INF238_2_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_2_REEM"));
//			caratula.inf238_3 = cursor.getString(cursor.getColumnIndex("INF238_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_3"));
//			caratula.inf238_3_tot = cursor.getString(cursor.getColumnIndex("INF238_3_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_3_TOT"));
//			caratula.inf238_3_rep = cursor.getString(cursor.getColumnIndex("INF238_3_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_3_REP"));
//			caratula.inf238_3_reem = cursor.getString(cursor.getColumnIndex("INF238_3_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_3_REEM"));
//			caratula.inf238_4 = cursor.getString(cursor.getColumnIndex("INF238_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_4"));
//			caratula.inf238_4_tot = cursor.getString(cursor.getColumnIndex("INF238_4_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_4_TOT"));
//			caratula.inf238_4_rep = cursor.getString(cursor.getColumnIndex("INF238_4_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_4_REP"));
//			caratula.inf238_4_reem = cursor.getString(cursor.getColumnIndex("INF238_4_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_4_REEM"));
//			caratula.inf238_5 = cursor.getString(cursor.getColumnIndex("INF238_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_5"));
//			caratula.inf238_5_tot = cursor.getString(cursor.getColumnIndex("INF238_5_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_5_TOT"));
//			caratula.inf238_5_rep = cursor.getString(cursor.getColumnIndex("INF238_5_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_5_REP"));
//			caratula.inf238_5_reem = cursor.getString(cursor.getColumnIndex("INF238_5_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_5_REEM"));
//			caratula.inf238_6 = cursor.getString(cursor.getColumnIndex("INF238_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_6"));
//			caratula.inf238_6_tot = cursor.getString(cursor.getColumnIndex("INF238_6_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_6_TOT"));
//			caratula.inf238_6_rep = cursor.getString(cursor.getColumnIndex("INF238_6_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_6_REP"));
//			caratula.inf238_6_reem = cursor.getString(cursor.getColumnIndex("INF238_6_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_6_REEM"));
//			caratula.inf238_7 = cursor.getString(cursor.getColumnIndex("INF238_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_7"));
//			caratula.inf238_7_tot = cursor.getString(cursor.getColumnIndex("INF238_7_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_7_TOT"));
//			caratula.inf238_7_rep = cursor.getString(cursor.getColumnIndex("INF238_7_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_7_REP"));
//			caratula.inf238_7_reem = cursor.getString(cursor.getColumnIndex("INF238_7_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_7_REEM"));
//			caratula.inf238_7_o = cursor.getString(cursor.getColumnIndex("INF238_7_O"));
//			caratula.inf238_tot = cursor.getString(cursor.getColumnIndex("INF238_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_TOT"));
//			caratula.inf238_rep = cursor.getString(cursor.getColumnIndex("INF238_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_REP"));
//			caratula.inf238_reem = cursor.getString(cursor.getColumnIndex("INF238_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF238_REEM"));
//			caratula.inf239 = cursor.getString(cursor.getColumnIndex("INF239"))==null?null:cursor.getInt(cursor.getColumnIndex("INF239"));
//			caratula.inf240_1 = cursor.getString(cursor.getColumnIndex("INF240_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_1"));
//			caratula.inf240_1_cant = cursor.getString(cursor.getColumnIndex("INF240_1_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_1_CANT"));
//			caratula.inf240_2 = cursor.getString(cursor.getColumnIndex("INF240_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_2"));
//			caratula.inf240_2_cant = cursor.getString(cursor.getColumnIndex("INF240_2_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_2_CANT"));
//			caratula.inf240_3 = cursor.getString(cursor.getColumnIndex("INF240_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_3"));
//			caratula.inf240_3_cant = cursor.getString(cursor.getColumnIndex("INF240_3_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_3_CANT"));
//			caratula.inf240_4 = cursor.getString(cursor.getColumnIndex("INF240_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_4"));
//			caratula.inf240_4_cant = cursor.getString(cursor.getColumnIndex("INF240_4_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_4_CANT"));
//			caratula.inf240_5 = cursor.getString(cursor.getColumnIndex("INF240_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_5"));
//			caratula.inf240_5_o = cursor.getString(cursor.getColumnIndex("INF240_5_O"));
//			caratula.inf240_5_cant = cursor.getString(cursor.getColumnIndex("INF240_5_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_5_CANT"));
//			caratula.inf240_tot_cant = cursor.getString(cursor.getColumnIndex("INF240_TOT_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF240_TOT_CANT"));
//			caratula.inf241_1 = cursor.getString(cursor.getColumnIndex("INF241_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_1"));
//			caratula.inf241_1_est1 = cursor.getString(cursor.getColumnIndex("INF241_1_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_1_EST1"));
//			caratula.inf241_1_est2 = cursor.getString(cursor.getColumnIndex("INF241_1_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_1_EST2"));
//			caratula.inf241_1_est3 = cursor.getString(cursor.getColumnIndex("INF241_1_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_1_EST3"));
//			caratula.inf241_1_est4 = cursor.getString(cursor.getColumnIndex("INF241_1_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_1_EST4"));
//			caratula.inf241_2 = cursor.getString(cursor.getColumnIndex("INF241_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_2"));
//			caratula.inf241_2_est1 = cursor.getString(cursor.getColumnIndex("INF241_2_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_2_EST1"));
//			caratula.inf241_2_est2 = cursor.getString(cursor.getColumnIndex("INF241_2_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_2_EST2"));
//			caratula.inf241_2_est3 = cursor.getString(cursor.getColumnIndex("INF241_2_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_2_EST3"));
//			caratula.inf241_2_est4 = cursor.getString(cursor.getColumnIndex("INF241_2_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_2_EST4"));
//			caratula.inf241_3 = cursor.getString(cursor.getColumnIndex("INF241_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_3"));
//			caratula.inf241_3_est1 = cursor.getString(cursor.getColumnIndex("INF241_3_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_3_EST1"));
//			caratula.inf241_3_est2 = cursor.getString(cursor.getColumnIndex("INF241_3_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_3_EST2"));
//			caratula.inf241_3_est3 = cursor.getString(cursor.getColumnIndex("INF241_3_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_3_EST3"));
//			caratula.inf241_3_est4 = cursor.getString(cursor.getColumnIndex("INF241_3_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_3_EST4"));
//			caratula.inf241_4 = cursor.getString(cursor.getColumnIndex("INF241_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_4"));
//			caratula.inf241_4_est1 = cursor.getString(cursor.getColumnIndex("INF241_4_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_4_EST1"));
//			caratula.inf241_4_est3 = cursor.getString(cursor.getColumnIndex("INF241_4_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_4_EST3"));
//			caratula.inf241_4_est4 = cursor.getString(cursor.getColumnIndex("INF241_4_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_4_EST4"));
//			caratula.inf241_5 = cursor.getString(cursor.getColumnIndex("INF241_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_5"));
//			caratula.inf241_5_o = cursor.getString(cursor.getColumnIndex("INF241_5_O"));
//			caratula.inf241_5_est1 = cursor.getString(cursor.getColumnIndex("INF241_5_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_5_EST1"));
//			caratula.inf241_5_est2 = cursor.getString(cursor.getColumnIndex("INF241_5_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_5_EST2"));
//			caratula.inf241_5_est3 = cursor.getString(cursor.getColumnIndex("INF241_5_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_5_EST3"));
//			caratula.inf241_5_est4 = cursor.getString(cursor.getColumnIndex("INF241_5_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_5_EST4"));
//			caratula.inf241_tot_est1 = cursor.getString(cursor.getColumnIndex("INF241_TOT_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_TOT_EST1"));
//			caratula.inf241_tot_est2 = cursor.getString(cursor.getColumnIndex("INF241_TOT_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_TOT_EST2"));
//			caratula.inf241_tot_est3 = cursor.getString(cursor.getColumnIndex("INF241_TOT_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_TOT_EST3"));
//			caratula.inf241_tot_est4 = cursor.getString(cursor.getColumnIndex("INF241_TOT_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF241_TOT_EST4"));
//			caratula.inf242_1 = cursor.getString(cursor.getColumnIndex("INF242_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_1"));
//			caratula.inf242_1_tot = cursor.getString(cursor.getColumnIndex("INF242_1_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_1_TOT"));
//			caratula.inf242_1_rep = cursor.getString(cursor.getColumnIndex("INF242_1_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_1_REP"));
//			caratula.inf242_1_reem = cursor.getString(cursor.getColumnIndex("INF242_1_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_1_REEM"));
//			caratula.inf242_2 = cursor.getString(cursor.getColumnIndex("INF242_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_2"));
//			caratula.inf242_2_tot = cursor.getString(cursor.getColumnIndex("INF242_2_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_2_TOT"));
//			caratula.inf242_2_rep = cursor.getString(cursor.getColumnIndex("INF242_2_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_2_REP"));
//			caratula.inf242_2_reem = cursor.getString(cursor.getColumnIndex("INF242_2_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_2_REEM"));
//			caratula.inf242_3 = cursor.getString(cursor.getColumnIndex("INF242_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_3"));
//			caratula.inf242_3_tot = cursor.getString(cursor.getColumnIndex("INF242_3_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_3_TOT"));
//			caratula.inf242_3_rep = cursor.getString(cursor.getColumnIndex("INF242_3_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_3_REP"));
//			caratula.inf242_3_reem = cursor.getString(cursor.getColumnIndex("INF242_3_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_3_REEM"));
//			caratula.inf242_4 = cursor.getString(cursor.getColumnIndex("INF242_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_4"));
//			caratula.inf242_4_tot = cursor.getString(cursor.getColumnIndex("INF242_4_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_4_TOT"));
//			caratula.inf242_4_rep = cursor.getString(cursor.getColumnIndex("INF242_4_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_4_REP"));
//			caratula.inf242_4_reem = cursor.getString(cursor.getColumnIndex("INF242_4_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_4_REEM"));
//			caratula.inf242_5 = cursor.getString(cursor.getColumnIndex("INF242_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_5"));
//			caratula.inf242_5_o = cursor.getString(cursor.getColumnIndex("INF242_5_O"));
//			caratula.inf242_5_tot = cursor.getString(cursor.getColumnIndex("INF242_5_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_5_TOT"));
//			caratula.inf242_5_rep = cursor.getString(cursor.getColumnIndex("INF242_5_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_5_REP"));
//			caratula.inf242_5_reem = cursor.getString(cursor.getColumnIndex("INF242_5_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_5_REEM"));
//			caratula.inf242_tot = cursor.getString(cursor.getColumnIndex("INF242_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_TOT"));
//			caratula.inf242_rep = cursor.getString(cursor.getColumnIndex("INF242_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_REP"));
//			caratula.inf242_reem = cursor.getString(cursor.getColumnIndex("INF242_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF242_REEM"));
//			caratula.inf243_con_ch_c = cursor.getString(cursor.getColumnIndex("INF243_CON_CH_C"))==null?null:cursor.getInt(cursor.getColumnIndex("INF243_CON_CH_C"));
//			caratula.inf243_sin_ch_c = cursor.getString(cursor.getColumnIndex("INF243_SIN_CH_C"))==null?null:cursor.getInt(cursor.getColumnIndex("INF243_SIN_CH_C"));
//			caratula.inf243a_1 = cursor.getString(cursor.getColumnIndex("INF243A_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF243A_1"));
//			caratula.inf243a_1_cant = cursor.getString(cursor.getColumnIndex("INF243A_1_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF243A_1_CANT"));
//			caratula.inf243a_2 = cursor.getString(cursor.getColumnIndex("INF243A_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF243A_2"));
//			caratula.inf243a_2_cant = cursor.getString(cursor.getColumnIndex("INF243A_2_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF243A_2_CANT"));
//			caratula.inf243a_3 = cursor.getString(cursor.getColumnIndex("INF243A_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF243A_3"));
//			caratula.inf243a_3_cant = cursor.getString(cursor.getColumnIndex("INF243A_3_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF243A_3_CANT"));
//			caratula.inf243a_tot_cant = cursor.getString(cursor.getColumnIndex("INF243A_TOT_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF243A_TOT_CANT"));
//			caratula.inf244_1 = cursor.getString(cursor.getColumnIndex("INF244_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF244_1"));
//			caratula.inf244_1_cant = cursor.getString(cursor.getColumnIndex("INF244_1_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF244_1_CANT"));
//			caratula.inf244_2 = cursor.getString(cursor.getColumnIndex("INF244_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF244_2"));
//			caratula.inf244_2_cant = cursor.getString(cursor.getColumnIndex("INF244_2_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF244_2_CANT"));
//			caratula.inf244_tot_cant = cursor.getString(cursor.getColumnIndex("INF244_TOT_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF244_TOT_CANT"));
//			caratula.obs_cap_200_5 = cursor.getString(cursor.getColumnIndex("OBS_CAP_200_5"));
//			caratula.inf244a_1 = cursor.getString(cursor.getColumnIndex("INF244A_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF244A_1"));
//			caratula.inf244a_1_cant = cursor.getString(cursor.getColumnIndex("INF244A_1_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF244A_1_CANT"));
//			caratula.inf244a_2 = cursor.getString(cursor.getColumnIndex("INF244A_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF244A_2"));
//			caratula.inf244a_2_cant = cursor.getString(cursor.getColumnIndex("INF244A_2_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF244A_2_CANT"));
//			caratula.inf244a_tot_cant = cursor.getString(cursor.getColumnIndex("INF244A_TOT_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF244A_TOT_CANT"));
//			caratula.inf245_ve = cursor.getString(cursor.getColumnIndex("INF245_VE"))==null?null:cursor.getInt(cursor.getColumnIndex("INF245_VE"));
//			caratula.inf245_va = cursor.getString(cursor.getColumnIndex("INF245_VA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF245_VA"));
//			caratula.inf246_1 = cursor.getString(cursor.getColumnIndex("INF246_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_1"));
//			caratula.inf246_1_cant = cursor.getString(cursor.getColumnIndex("INF246_1_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_1_CANT"));
//			caratula.inf246_2 = cursor.getString(cursor.getColumnIndex("INF246_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_2"));
//			caratula.inf246_2_cant = cursor.getString(cursor.getColumnIndex("INF246_2_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_2_CANT"));
//			caratula.inf246_3 = cursor.getString(cursor.getColumnIndex("INF246_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_3"));
//			caratula.inf246_3_cant = cursor.getString(cursor.getColumnIndex("INF246_3_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_3_CANT"));
//			caratula.inf246_4 = cursor.getString(cursor.getColumnIndex("INF246_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_4"));
//			caratula.inf246_4_cant = cursor.getString(cursor.getColumnIndex("INF246_4_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_4_CANT"));
//			caratula.inf246_5 = cursor.getString(cursor.getColumnIndex("INF246_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_5"));
//			caratula.inf246_5_o = cursor.getString(cursor.getColumnIndex("INF246_5_O"));
//			caratula.inf246_5_cant = cursor.getString(cursor.getColumnIndex("INF246_5_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_5_CANT"));
//			caratula.inf246_tot_cant = cursor.getString(cursor.getColumnIndex("INF246_TOT_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF246_TOT_CANT"));
//			caratula.inf247_1 = cursor.getString(cursor.getColumnIndex("INF247_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_1"));
//			caratula.inf247_1_est1 = cursor.getString(cursor.getColumnIndex("INF247_1_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_1_EST1"));
//			caratula.inf247_1_est2 = cursor.getString(cursor.getColumnIndex("INF247_1_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_1_EST2"));
//			caratula.inf247_1_est3 = cursor.getString(cursor.getColumnIndex("INF247_1_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_1_EST3"));
//			caratula.inf247_1_est4 = cursor.getString(cursor.getColumnIndex("INF247_1_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_1_EST4"));
//			caratula.inf247_2 = cursor.getString(cursor.getColumnIndex("INF247_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_2"));
//			caratula.inf247_2_est1 = cursor.getString(cursor.getColumnIndex("INF247_2_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_2_EST1"));
//			caratula.inf247_2_est2 = cursor.getString(cursor.getColumnIndex("INF247_2_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_2_EST2"));
//			caratula.inf247_2_est3 = cursor.getString(cursor.getColumnIndex("INF247_2_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_2_EST3"));
//			caratula.inf247_2_est4 = cursor.getString(cursor.getColumnIndex("INF247_2_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_2_EST4"));
//			caratula.inf247_3 = cursor.getString(cursor.getColumnIndex("INF247_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_3"));
//			caratula.inf247_3_est1 = cursor.getString(cursor.getColumnIndex("INF247_3_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_3_EST1"));
//			caratula.inf247_3_est2 = cursor.getString(cursor.getColumnIndex("INF247_3_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_3_EST2"));
//			caratula.inf247_3_est3 = cursor.getString(cursor.getColumnIndex("INF247_3_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_3_EST3"));
//			caratula.inf247_3_est4 = cursor.getString(cursor.getColumnIndex("INF247_3_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_3_EST4"));
//			caratula.inf247_4 = cursor.getString(cursor.getColumnIndex("INF247_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_4"));
//			caratula.inf247_4_est1 = cursor.getString(cursor.getColumnIndex("INF247_4_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_4_EST1"));
//			caratula.inf247_4_est3 = cursor.getString(cursor.getColumnIndex("INF247_4_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_4_EST3"));
//			caratula.inf247_4_est4 = cursor.getString(cursor.getColumnIndex("INF247_4_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_4_EST4"));
//			caratula.inf247_5 = cursor.getString(cursor.getColumnIndex("INF247_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_5"));
//			caratula.inf247_5_o = cursor.getString(cursor.getColumnIndex("INF247_5_O"));
//			caratula.inf247_5_est1 = cursor.getString(cursor.getColumnIndex("INF247_5_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_5_EST1"));
//			caratula.inf247_5_est2 = cursor.getString(cursor.getColumnIndex("INF247_5_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_5_EST2"));
//			caratula.inf247_5_est3 = cursor.getString(cursor.getColumnIndex("INF247_5_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_5_EST3"));
//			caratula.inf247_5_est4 = cursor.getString(cursor.getColumnIndex("INF247_5_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_5_EST4"));
//			caratula.inf247_tot_est1 = cursor.getString(cursor.getColumnIndex("INF247_TOT_EST1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_TOT_EST1"));
//			caratula.inf247_tot_est2 = cursor.getString(cursor.getColumnIndex("INF247_TOT_EST2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_TOT_EST2"));
//			caratula.inf247_tot_est3 = cursor.getString(cursor.getColumnIndex("INF247_TOT_EST3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_TOT_EST3"));
//			caratula.inf247_tot_est4 = cursor.getString(cursor.getColumnIndex("INF247_TOT_EST4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF247_TOT_EST4"));
//			caratula.inf248_1 = cursor.getString(cursor.getColumnIndex("INF248_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_1"));
//			caratula.inf248_1_tot = cursor.getString(cursor.getColumnIndex("INF248_1_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_1_TOT"));
//			caratula.inf248_1_rep = cursor.getString(cursor.getColumnIndex("INF248_1_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_1_REP"));
//			caratula.inf248_1_reem = cursor.getString(cursor.getColumnIndex("INF248_1_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_1_REEM"));
//			caratula.inf248_2 = cursor.getString(cursor.getColumnIndex("INF248_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_2"));
//			caratula.inf248_2_tot = cursor.getString(cursor.getColumnIndex("INF248_2_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_2_TOT"));
//			caratula.inf248_2_rep = cursor.getString(cursor.getColumnIndex("INF248_2_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_2_REP"));
//			caratula.inf248_2_reem = cursor.getString(cursor.getColumnIndex("INF248_2_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_2_REEM"));
//			caratula.inf248_3 = cursor.getString(cursor.getColumnIndex("INF248_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_3"));
//			caratula.inf248_3_tot = cursor.getString(cursor.getColumnIndex("INF248_3_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_3_TOT"));
//			caratula.inf248_3_rep = cursor.getString(cursor.getColumnIndex("INF248_3_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_3_REP"));
//			caratula.inf248_3_reem = cursor.getString(cursor.getColumnIndex("INF248_3_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_3_REEM"));
//			caratula.inf248_4 = cursor.getString(cursor.getColumnIndex("INF248_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_4"));
//			caratula.inf248_4_tot = cursor.getString(cursor.getColumnIndex("INF248_4_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_4_TOT"));
//			caratula.inf248_4_rep = cursor.getString(cursor.getColumnIndex("INF248_4_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_4_REP"));
//			caratula.inf248_4_reem = cursor.getString(cursor.getColumnIndex("INF248_4_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_4_REEM"));
//			caratula.inf248_5 = cursor.getString(cursor.getColumnIndex("INF248_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_5"));
//			caratula.inf248_5_tot = cursor.getString(cursor.getColumnIndex("INF248_5_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_5_TOT"));
//			caratula.inf248_5_rep = cursor.getString(cursor.getColumnIndex("INF248_5_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_5_REP"));
//			caratula.inf248_5_reem = cursor.getString(cursor.getColumnIndex("INF248_5_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_5_REEM"));
//			caratula.inf248_5_o = cursor.getString(cursor.getColumnIndex("INF248_5_O"));
//			caratula.inf248_tot = cursor.getString(cursor.getColumnIndex("INF248_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_TOT"));
//			caratula.inf248_rep = cursor.getString(cursor.getColumnIndex("INF248_REP"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_REP"));
//			caratula.inf248_reem = cursor.getString(cursor.getColumnIndex("INF248_REEM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF248_REEM"));
//			caratula.inf249 = cursor.getString(cursor.getColumnIndex("INF249"))==null?null:cursor.getInt(cursor.getColumnIndex("INF249"));
//			caratula.inf250 = cursor.getString(cursor.getColumnIndex("INF250"))==null?null:cursor.getInt(cursor.getColumnIndex("INF250"));
//			caratula.inf250_o = cursor.getString(cursor.getColumnIndex("INF250_O"));
//			caratula.inf251 = cursor.getString(cursor.getColumnIndex("INF251"))==null?null:cursor.getInt(cursor.getColumnIndex("INF251"));
//			caratula.inf252 = cursor.getString(cursor.getColumnIndex("INF252"))==null?null:cursor.getInt(cursor.getColumnIndex("INF252"));
//			caratula.inf253_1 = cursor.getString(cursor.getColumnIndex("INF253_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_1"));
//			caratula.inf253_1_1 = cursor.getString(cursor.getColumnIndex("INF253_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_1_1"));
//			caratula.inf253_1_2 = cursor.getString(cursor.getColumnIndex("INF253_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_1_2"));
//			caratula.inf253_1_3 = cursor.getString(cursor.getColumnIndex("INF253_1_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_1_3"));
//			caratula.inf253_2 = cursor.getString(cursor.getColumnIndex("INF253_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_2"));
//			caratula.inf253_2_1 = cursor.getString(cursor.getColumnIndex("INF253_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_2_1"));
//			caratula.inf253_2_2 = cursor.getString(cursor.getColumnIndex("INF253_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_2_2"));
//			caratula.inf253_2_3 = cursor.getString(cursor.getColumnIndex("INF253_2_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_2_3"));
//			caratula.inf253_3 = cursor.getString(cursor.getColumnIndex("INF253_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_3"));
//			caratula.inf253_3_1 = cursor.getString(cursor.getColumnIndex("INF253_3_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_3_1"));
//			caratula.inf253_3_2 = cursor.getString(cursor.getColumnIndex("INF253_3_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_3_2"));
//			caratula.inf253_3_3 = cursor.getString(cursor.getColumnIndex("INF253_3_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_3_3"));
//			caratula.inf253_4 = cursor.getString(cursor.getColumnIndex("INF253_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_4"));
//			caratula.inf253_4_1 = cursor.getString(cursor.getColumnIndex("INF253_4_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_4_1"));
//			caratula.inf253_4_2 = cursor.getString(cursor.getColumnIndex("INF253_4_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_4_2"));
//			caratula.inf253_4_3 = cursor.getString(cursor.getColumnIndex("INF253_4_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_4_3"));
//			caratula.inf253_5 = cursor.getString(cursor.getColumnIndex("INF253_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_5"));
//			caratula.inf253_5_1 = cursor.getString(cursor.getColumnIndex("INF253_5_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_5_1"));
//			caratula.inf253_5_2 = cursor.getString(cursor.getColumnIndex("INF253_5_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_5_2"));
//			caratula.inf253_5_3 = cursor.getString(cursor.getColumnIndex("INF253_5_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_5_3"));
//			caratula.inf253_6 = cursor.getString(cursor.getColumnIndex("INF253_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_6"));
//			caratula.inf253_6_1 = cursor.getString(cursor.getColumnIndex("INF253_6_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_6_1"));
//			caratula.inf253_6_2 = cursor.getString(cursor.getColumnIndex("INF253_6_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_6_2"));
//			caratula.inf253_6_3 = cursor.getString(cursor.getColumnIndex("INF253_6_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_6_3"));
//			caratula.inf253_6_o = cursor.getString(cursor.getColumnIndex("INF253_6_O"));
//			caratula.inf253_tot_1 = cursor.getString(cursor.getColumnIndex("INF253_TOT_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_TOT_1"));
//			caratula.inf253_tot_2 = cursor.getString(cursor.getColumnIndex("INF253_TOT_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_TOT_2"));
//			caratula.inf253_tot_3 = cursor.getString(cursor.getColumnIndex("INF253_TOT_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF253_TOT_3"));
//			caratula.obs_cap_200_6 = cursor.getString(cursor.getColumnIndex("OBS_CAP_200_6"));
//			caratula.inf254_1 = cursor.getString(cursor.getColumnIndex("INF254_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_1"));
//			caratula.inf255_1 = cursor.getString(cursor.getColumnIndex("INF255_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_1"));
//			caratula.inf254_2 = cursor.getString(cursor.getColumnIndex("INF254_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_2"));
//			caratula.inf255_2 = cursor.getString(cursor.getColumnIndex("INF255_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_2"));
//			caratula.inf254_3 = cursor.getString(cursor.getColumnIndex("INF254_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_3"));
//			caratula.inf255_3 = cursor.getString(cursor.getColumnIndex("INF255_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_3"));
//			caratula.inf254_4 = cursor.getString(cursor.getColumnIndex("INF254_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_4"));
//			caratula.inf255_4 = cursor.getString(cursor.getColumnIndex("INF255_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_4"));
//			caratula.inf254_5 = cursor.getString(cursor.getColumnIndex("INF254_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_5"));
//			caratula.inf255_5 = cursor.getString(cursor.getColumnIndex("INF255_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_5"));
//			caratula.inf254_6 = cursor.getString(cursor.getColumnIndex("INF254_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_6"));
//			caratula.inf255_6 = cursor.getString(cursor.getColumnIndex("INF255_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_6"));
//			caratula.inf254_7 = cursor.getString(cursor.getColumnIndex("INF254_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_7"));
//			caratula.inf255_7 = cursor.getString(cursor.getColumnIndex("INF255_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_7"));
//			caratula.inf254_8 = cursor.getString(cursor.getColumnIndex("INF254_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_8"));
//			caratula.inf255_8 = cursor.getString(cursor.getColumnIndex("INF255_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_8"));
//			caratula.inf254_9 = cursor.getString(cursor.getColumnIndex("INF254_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_9"));
//			caratula.inf255_9 = cursor.getString(cursor.getColumnIndex("INF255_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_9"));
//			caratula.inf254_10 = cursor.getString(cursor.getColumnIndex("INF254_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_10"));
//			caratula.inf255_10 = cursor.getString(cursor.getColumnIndex("INF255_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_10"));
//			caratula.inf254_11 = cursor.getString(cursor.getColumnIndex("INF254_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_11"));
//			caratula.inf255_11 = cursor.getString(cursor.getColumnIndex("INF255_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_11"));
//			caratula.inf254_12 = cursor.getString(cursor.getColumnIndex("INF254_12"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_12"));
//			caratula.inf255_12 = cursor.getString(cursor.getColumnIndex("INF255_12"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_12"));
//			caratula.inf254_13 = cursor.getString(cursor.getColumnIndex("INF254_13"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_13"));
//			caratula.inf255_13 = cursor.getString(cursor.getColumnIndex("INF255_13"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_13"));
//			caratula.inf254_14 = cursor.getString(cursor.getColumnIndex("INF254_14"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_14"));
//			caratula.inf255_14 = cursor.getString(cursor.getColumnIndex("INF255_14"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_14"));
//			caratula.inf254_15 = cursor.getString(cursor.getColumnIndex("INF254_15"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_15"));
//			caratula.inf255_15 = cursor.getString(cursor.getColumnIndex("INF255_15"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_15"));
//			caratula.inf254_16 = cursor.getString(cursor.getColumnIndex("INF254_16"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_16"));
//			caratula.inf255_16 = cursor.getString(cursor.getColumnIndex("INF255_16"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_16"));
//			caratula.inf254_17 = cursor.getString(cursor.getColumnIndex("INF254_17"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_17"));
//			caratula.inf255_17 = cursor.getString(cursor.getColumnIndex("INF255_17"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_17"));
//			caratula.inf254_18 = cursor.getString(cursor.getColumnIndex("INF254_18"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_18"));
//			caratula.inf255_18 = cursor.getString(cursor.getColumnIndex("INF255_18"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_18"));
//			caratula.inf254_19 = cursor.getString(cursor.getColumnIndex("INF254_19"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_19"));
//			caratula.inf255_19 = cursor.getString(cursor.getColumnIndex("INF255_19"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_19"));
//			caratula.inf254_20 = cursor.getString(cursor.getColumnIndex("INF254_20"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_20"));
//			caratula.inf255_20 = cursor.getString(cursor.getColumnIndex("INF255_20"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_20"));
//			caratula.inf254_21 = cursor.getString(cursor.getColumnIndex("INF254_21"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_21"));
//			caratula.inf255_21 = cursor.getString(cursor.getColumnIndex("INF255_21"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_21"));
//			caratula.inf254_22 = cursor.getString(cursor.getColumnIndex("INF254_22"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_22"));
//			caratula.inf255_22 = cursor.getString(cursor.getColumnIndex("INF255_22"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_22"));
//			caratula.inf254_23 = cursor.getString(cursor.getColumnIndex("INF254_23"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_23"));
//			caratula.inf255_23 = cursor.getString(cursor.getColumnIndex("INF255_23"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_23"));
//			caratula.inf254_24 = cursor.getString(cursor.getColumnIndex("INF254_24"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_24"));
//			caratula.inf255_24 = cursor.getString(cursor.getColumnIndex("INF255_24"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_24"));
//			caratula.inf254_25 = cursor.getString(cursor.getColumnIndex("INF254_25"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_25"));
//			caratula.inf255_25 = cursor.getString(cursor.getColumnIndex("INF255_25"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_25"));
//			caratula.inf254_26 = cursor.getString(cursor.getColumnIndex("INF254_26"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_26"));
//			caratula.inf255_26 = cursor.getString(cursor.getColumnIndex("INF255_26"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_26"));
//			caratula.inf254_27 = cursor.getString(cursor.getColumnIndex("INF254_27"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_27"));
//			caratula.inf255_27 = cursor.getString(cursor.getColumnIndex("INF255_27"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_27"));
//			caratula.inf254_28 = cursor.getString(cursor.getColumnIndex("INF254_28"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_28"));
//			caratula.inf255_28 = cursor.getString(cursor.getColumnIndex("INF255_28"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_28"));
//			caratula.inf254_29 = cursor.getString(cursor.getColumnIndex("INF254_29"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_29"));
//			caratula.inf255_29 = cursor.getString(cursor.getColumnIndex("INF255_29"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_29"));
//			caratula.inf254_30 = cursor.getString(cursor.getColumnIndex("INF254_30"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_30"));
//			caratula.inf255_30 = cursor.getString(cursor.getColumnIndex("INF255_30"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_30"));
//			caratula.inf254_31 = cursor.getString(cursor.getColumnIndex("INF254_31"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_31"));
//			caratula.inf255_31 = cursor.getString(cursor.getColumnIndex("INF255_31"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_31"));
//			caratula.inf254_32 = cursor.getString(cursor.getColumnIndex("INF254_32"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_32"));
//			caratula.inf255_32 = cursor.getString(cursor.getColumnIndex("INF255_32"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_32"));
//			caratula.inf254_33 = cursor.getString(cursor.getColumnIndex("INF254_33"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_33"));
//			caratula.inf255_33 = cursor.getString(cursor.getColumnIndex("INF255_33"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_33"));
//			caratula.inf254_34 = cursor.getString(cursor.getColumnIndex("INF254_34"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_34"));
//			caratula.inf255_34 = cursor.getString(cursor.getColumnIndex("INF255_34"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_34"));
//			caratula.inf254_35 = cursor.getString(cursor.getColumnIndex("INF254_35"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_35"));
//			caratula.inf255_35 = cursor.getString(cursor.getColumnIndex("INF255_35"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_35"));
//			caratula.inf254_36 = cursor.getString(cursor.getColumnIndex("INF254_36"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_36"));
//			caratula.inf255_36 = cursor.getString(cursor.getColumnIndex("INF255_36"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_36"));
//			caratula.inf254_37 = cursor.getString(cursor.getColumnIndex("INF254_37"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_37"));
//			caratula.inf255_37 = cursor.getString(cursor.getColumnIndex("INF255_37"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_37"));
//			caratula.inf254_38 = cursor.getString(cursor.getColumnIndex("INF254_38"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_38"));
//			caratula.inf255_38 = cursor.getString(cursor.getColumnIndex("INF255_38"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_38"));
//			caratula.inf254_39 = cursor.getString(cursor.getColumnIndex("INF254_39"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_39"));
//			caratula.inf255_39 = cursor.getString(cursor.getColumnIndex("INF255_39"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_39"));
//			caratula.inf254_40 = cursor.getString(cursor.getColumnIndex("INF254_40"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_40"));
//			caratula.inf255_40 = cursor.getString(cursor.getColumnIndex("INF255_40"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_40"));
//			caratula.inf254_41 = cursor.getString(cursor.getColumnIndex("INF254_41"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_41"));
//			caratula.inf255_41 = cursor.getString(cursor.getColumnIndex("INF255_41"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_41"));
//			caratula.inf254_42 = cursor.getString(cursor.getColumnIndex("INF254_42"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_42"));
//			caratula.inf255_42 = cursor.getString(cursor.getColumnIndex("INF255_42"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_42"));
//			caratula.inf254_43 = cursor.getString(cursor.getColumnIndex("INF254_43"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_43"));
//			caratula.inf255_43 = cursor.getString(cursor.getColumnIndex("INF255_43"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_43"));
//			caratula.inf254_44 = cursor.getString(cursor.getColumnIndex("INF254_44"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_44"));
//			caratula.inf255_44 = cursor.getString(cursor.getColumnIndex("INF255_44"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_44"));
//			caratula.inf254_45 = cursor.getString(cursor.getColumnIndex("INF254_45"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_45"));
//			caratula.inf255_45 = cursor.getString(cursor.getColumnIndex("INF255_45"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_45"));
//			caratula.inf254_46 = cursor.getString(cursor.getColumnIndex("INF254_46"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_46"));
//			caratula.inf255_46 = cursor.getString(cursor.getColumnIndex("INF255_46"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_46"));
//			caratula.inf254_47 = cursor.getString(cursor.getColumnIndex("INF254_47"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_47"));
//			caratula.inf255_47 = cursor.getString(cursor.getColumnIndex("INF255_47"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_47"));
//			caratula.inf254_48 = cursor.getString(cursor.getColumnIndex("INF254_48"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_48"));
//			caratula.inf255_48 = cursor.getString(cursor.getColumnIndex("INF255_48"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_48"));
//			caratula.inf254_49 = cursor.getString(cursor.getColumnIndex("INF254_49"))==null?null:cursor.getInt(cursor.getColumnIndex("INF254_49"));
//			caratula.inf254_49_o = cursor.getString(cursor.getColumnIndex("INF254_49_O"));
//			caratula.inf255_49 = cursor.getString(cursor.getColumnIndex("INF255_49"))==null?null:cursor.getInt(cursor.getColumnIndex("INF255_49"));
//			caratula.obs_cap_200_7 = cursor.getString(cursor.getColumnIndex("OBS_CAP_200_7"));
//
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap300> fillINF_Capitulo300s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap300> caratulas = new ArrayList<INF_Cap300>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_300 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap300 data = new INF_Cap300();
//
//			List<String> allFieldMatches = data.getFieldMatches(301, 343, "ID_N", "INFOBS_300_SECCION_VII",
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (INF_Cap300)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			caratulas.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap300> fillINF_Capitulo300s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap300> caratulas = new ArrayList<INF_Cap300>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_300 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap300 caratula = new INF_Cap300();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.inf301 = cursor.getString(cursor.getColumnIndex("INF301"));
//			caratula.inf301_total = cursor.getString(cursor.getColumnIndex("INF301_TOTAL"))==null?null:cursor.getInt(cursor.getColumnIndex("INF301_TOTAL"));
//			caratula.inf301a = cursor.getString(cursor.getColumnIndex("INF301A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF301A"));
//			caratula.inf301b = cursor.getString(cursor.getColumnIndex("INF301B"))==null?null:cursor.getInt(cursor.getColumnIndex("INF301B"));
//			caratula.inf301b_o = cursor.getString(cursor.getColumnIndex("INF301B_O"));
//			
//			caratula.inf302_1 = cursor.getString(cursor.getColumnIndex("INF302_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_1"));
//			caratula.inf303_1 = cursor.getString(cursor.getColumnIndex("INF303_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_1"));
//			caratula.inf304a_1 = cursor.getString(cursor.getColumnIndex("INF304A_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_1"));
//			caratula.inf304b_1 = cursor.getString(cursor.getColumnIndex("INF304B_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_1"));
//			caratula.inf305c_1 = cursor.getString(cursor.getColumnIndex("INF305C_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_1"));
//			caratula.inf305d_1 = cursor.getString(cursor.getColumnIndex("INF305D_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_1"));
//			caratula.inf305a_1 = cursor.getString(cursor.getColumnIndex("INF305A_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_1"));
//			
//			caratula.inf302_2 = cursor.getString(cursor.getColumnIndex("INF302_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_2"));
//			caratula.inf303_2 = cursor.getString(cursor.getColumnIndex("INF303_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_2"));
//			caratula.inf304a_2 = cursor.getString(cursor.getColumnIndex("INF304A_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_2"));
//			caratula.inf304b_2 = cursor.getString(cursor.getColumnIndex("INF304B_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_2"));
//			caratula.inf305c_2 = cursor.getString(cursor.getColumnIndex("INF305C_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_2"));
//			caratula.inf305d_2 = cursor.getString(cursor.getColumnIndex("INF305D_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_2"));
//			caratula.inf305a_2 = cursor.getString(cursor.getColumnIndex("INF305A_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_2"));
//			
//			caratula.inf302_3 = cursor.getString(cursor.getColumnIndex("INF302_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_3"));
//			caratula.inf303_3 = cursor.getString(cursor.getColumnIndex("INF303_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_3"));
//			caratula.inf304a_3 = cursor.getString(cursor.getColumnIndex("INF304A_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_3"));
//			caratula.inf304b_3 = cursor.getString(cursor.getColumnIndex("INF304B_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_3"));
//			caratula.inf305c_3 = cursor.getString(cursor.getColumnIndex("INF305C_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_3"));
//			caratula.inf305d_3 = cursor.getString(cursor.getColumnIndex("INF305D_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_3"));
//			caratula.inf305a_3 = cursor.getString(cursor.getColumnIndex("INF305A_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_3"));
//			
//			caratula.inf302_4 = cursor.getString(cursor.getColumnIndex("INF302_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_4"));
//			caratula.inf303_4 = cursor.getString(cursor.getColumnIndex("INF303_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_4"));
//			caratula.inf304a_4 = cursor.getString(cursor.getColumnIndex("INF304A_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_4"));
//			caratula.inf304b_4 = cursor.getString(cursor.getColumnIndex("INF304B_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_4"));
//			caratula.inf305c_4 = cursor.getString(cursor.getColumnIndex("INF305C_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_4"));
//			caratula.inf305d_4 = cursor.getString(cursor.getColumnIndex("INF305D_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_4"));
//			caratula.inf305a_4 = cursor.getString(cursor.getColumnIndex("INF305A_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_4"));
//			
//			caratula.inf302_5 = cursor.getString(cursor.getColumnIndex("INF302_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_5"));
//			caratula.inf303_5 = cursor.getString(cursor.getColumnIndex("INF303_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_5"));
//			caratula.inf304a_5 = cursor.getString(cursor.getColumnIndex("INF304A_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_5"));
//			caratula.inf304b_5 = cursor.getString(cursor.getColumnIndex("INF304B_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_5"));
//			caratula.inf305c_5 = cursor.getString(cursor.getColumnIndex("INF305C_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_5"));
//			caratula.inf305d_5 = cursor.getString(cursor.getColumnIndex("INF305D_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_5"));
//			caratula.inf305a_5 = cursor.getString(cursor.getColumnIndex("INF305A_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_5"));
//			
//			caratula.inf302_6 = cursor.getString(cursor.getColumnIndex("INF302_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_6"));
//			caratula.inf303_6 = cursor.getString(cursor.getColumnIndex("INF303_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_6"));
//			caratula.inf304a_6 = cursor.getString(cursor.getColumnIndex("INF304A_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_6"));
//			caratula.inf304b_6 = cursor.getString(cursor.getColumnIndex("INF304B_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_6"));
//			caratula.inf305c_6 = cursor.getString(cursor.getColumnIndex("INF305C_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_6"));
//			caratula.inf305d_6 = cursor.getString(cursor.getColumnIndex("INF305D_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_6"));
//			caratula.inf305a_6 = cursor.getString(cursor.getColumnIndex("INF305A_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_6"));
//			
//			caratula.inf302_7 = cursor.getString(cursor.getColumnIndex("INF302_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_7"));
//			caratula.inf303_7 = cursor.getString(cursor.getColumnIndex("INF303_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_7"));
//			caratula.inf304a_7 = cursor.getString(cursor.getColumnIndex("INF304A_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_7"));
//			caratula.inf304b_7 = cursor.getString(cursor.getColumnIndex("INF304B_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_7"));
//			caratula.inf305c_7 = cursor.getString(cursor.getColumnIndex("INF305C_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_7"));
//			caratula.inf305d_7 = cursor.getString(cursor.getColumnIndex("INF305D_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_7"));
//			caratula.inf305a_7 = cursor.getString(cursor.getColumnIndex("INF305A_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_7"));
//			
//			caratula.inf302_8 = cursor.getString(cursor.getColumnIndex("INF302_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_8"));
//			caratula.inf302_8_o = cursor.getString(cursor.getColumnIndex("INF302_8_O"));
//			caratula.inf303_8 = cursor.getString(cursor.getColumnIndex("INF303_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_8"));
//			caratula.inf304a_8 = cursor.getString(cursor.getColumnIndex("INF304A_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_8"));
//			caratula.inf304b_8 = cursor.getString(cursor.getColumnIndex("INF304B_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_8"));
//			caratula.inf305c_8 = cursor.getString(cursor.getColumnIndex("INF305C_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_8"));
//			caratula.inf305d_8 = cursor.getString(cursor.getColumnIndex("INF305D_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_8"));
//			caratula.inf305a_8 = cursor.getString(cursor.getColumnIndex("INF305A_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_8"));
//			
//			caratula.inf302_9 = cursor.getString(cursor.getColumnIndex("INF302_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_9"));
//			caratula.inf303_9 = cursor.getString(cursor.getColumnIndex("INF303_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_9"));
//			caratula.inf304a_9 = cursor.getString(cursor.getColumnIndex("INF304A_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_9"));
//			caratula.inf304b_9 = cursor.getString(cursor.getColumnIndex("INF304B_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_9"));
//			caratula.inf305c_9 = cursor.getString(cursor.getColumnIndex("INF305C_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_9"));
//			caratula.inf305d_9 = cursor.getString(cursor.getColumnIndex("INF305D_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_9"));
//			caratula.inf305a_9 = cursor.getString(cursor.getColumnIndex("INF305A_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_9"));
//			
//			caratula.inf302_10 = cursor.getString(cursor.getColumnIndex("INF302_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_10"));
//			caratula.inf303_10 = cursor.getString(cursor.getColumnIndex("INF303_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_10"));
//			caratula.inf304a_10 = cursor.getString(cursor.getColumnIndex("INF304A_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_10"));
//			caratula.inf304b_10 = cursor.getString(cursor.getColumnIndex("INF304B_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_10"));
//			caratula.inf305c_10 = cursor.getString(cursor.getColumnIndex("INF305C_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_10"));
//			caratula.inf305d_10 = cursor.getString(cursor.getColumnIndex("INF305D_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_10"));
//			caratula.inf305a_10 = cursor.getString(cursor.getColumnIndex("INF305A_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_10"));
//			
//			caratula.inf302_11 = cursor.getString(cursor.getColumnIndex("INF302_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF302_11"));
//			caratula.inf303_11 = cursor.getString(cursor.getColumnIndex("INF303_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF303_11"));
//			caratula.inf304a_11 = cursor.getString(cursor.getColumnIndex("INF304A_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304A_11"));
//			caratula.inf304b_11 = cursor.getString(cursor.getColumnIndex("INF304B_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF304B_11"));
//			caratula.inf305c_11 = cursor.getString(cursor.getColumnIndex("INF305C_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305C_11"));
//			caratula.inf305d_11 = cursor.getString(cursor.getColumnIndex("INF305D_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305D_11"));
//			caratula.inf305a_11 = cursor.getString(cursor.getColumnIndex("INF305A_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF305A_11"));
//			
//			caratula.inf306_1 = cursor.getString(cursor.getColumnIndex("INF306_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF306_1"));
//			caratula.inf307_1 = cursor.getString(cursor.getColumnIndex("INF307_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF307_1"));
//			caratula.inf308a_1 = cursor.getString(cursor.getColumnIndex("INF308A_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308A_1"));
//			caratula.inf308b_1 = cursor.getString(cursor.getColumnIndex("INF308B_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308B_1"));
//			caratula.inf309c_1 = cursor.getString(cursor.getColumnIndex("INF309C_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309C_1"));
//			caratula.inf309d_1 = cursor.getString(cursor.getColumnIndex("INF309D_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309D_1"));
//			caratula.inf309a_1 = cursor.getString(cursor.getColumnIndex("INF309A_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309A_1"));
//			caratula.inf310_1 = cursor.getString(cursor.getColumnIndex("INF310_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF310_1"));
//			
//			caratula.inf306_2 = cursor.getString(cursor.getColumnIndex("INF306_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF306_2"));
//			caratula.inf307_2 = cursor.getString(cursor.getColumnIndex("INF307_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF307_2"));
//			caratula.inf308a_2 = cursor.getString(cursor.getColumnIndex("INF308A_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308A_2"));
//			caratula.inf308b_2 = cursor.getString(cursor.getColumnIndex("INF308B_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308B_2"));
//			caratula.inf309c_2 = cursor.getString(cursor.getColumnIndex("INF309C_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309C_2"));
//			caratula.inf309d_2 = cursor.getString(cursor.getColumnIndex("INF309D_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309D_2"));
//			caratula.inf309a_2 = cursor.getString(cursor.getColumnIndex("INF309A_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309A_2"));
//			caratula.inf310_2 = cursor.getString(cursor.getColumnIndex("INF310_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF310_2"));
//			
//			caratula.inf306_3 = cursor.getString(cursor.getColumnIndex("INF306_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF306_3"));
//			caratula.inf307_3 = cursor.getString(cursor.getColumnIndex("INF307_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF307_3"));
//			caratula.inf308a_3 = cursor.getString(cursor.getColumnIndex("INF308A_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308A_3"));
//			caratula.inf308b_3 = cursor.getString(cursor.getColumnIndex("INF308B_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308B_3"));
//			caratula.inf309c_3 = cursor.getString(cursor.getColumnIndex("INF309C_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309C_3"));
//			caratula.inf309d_3 = cursor.getString(cursor.getColumnIndex("INF309D_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309D_3"));
//			caratula.inf309a_3 = cursor.getString(cursor.getColumnIndex("INF309A_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309A_3"));
//			caratula.inf310_3 = cursor.getString(cursor.getColumnIndex("INF310_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF310_3"));
//			
//			caratula.inf306_4 = cursor.getString(cursor.getColumnIndex("INF306_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF306_4"));
//			caratula.inf307_4 = cursor.getString(cursor.getColumnIndex("INF307_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF307_4"));
//			caratula.inf308a_4 = cursor.getString(cursor.getColumnIndex("INF308A_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308A_4"));
//			caratula.inf308b_4 = cursor.getString(cursor.getColumnIndex("INF308B_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308B_4"));
//			caratula.inf309c_4 = cursor.getString(cursor.getColumnIndex("INF309C_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309C_4"));
//			caratula.inf309d_4 = cursor.getString(cursor.getColumnIndex("INF309D_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309D_4"));
//			caratula.inf309a_4 = cursor.getString(cursor.getColumnIndex("INF309A_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309A_4"));
//			caratula.inf310_4 = cursor.getString(cursor.getColumnIndex("INF310_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF310_4"));
//			
//			caratula.inf306_5 = cursor.getString(cursor.getColumnIndex("INF306_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF306_5"));
//			caratula.inf307_5 = cursor.getString(cursor.getColumnIndex("INF307_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF307_5"));
//			caratula.inf308a_5 = cursor.getString(cursor.getColumnIndex("INF308A_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308A_5"));
//			caratula.inf308b_5 = cursor.getString(cursor.getColumnIndex("INF308B_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308B_5"));
//			caratula.inf309c_5 = cursor.getString(cursor.getColumnIndex("INF309C_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309C_5"));
//			caratula.inf309d_5 = cursor.getString(cursor.getColumnIndex("INF309D_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309D_5"));
//			caratula.inf309a_5 = cursor.getString(cursor.getColumnIndex("INF309A_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309A_5"));
//			caratula.inf310_5 = cursor.getString(cursor.getColumnIndex("INF310_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF310_5"));
//			
//			caratula.inf306_6 = cursor.getString(cursor.getColumnIndex("INF306_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF306_6"));
//			caratula.inf307_6 = cursor.getString(cursor.getColumnIndex("INF307_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF307_6"));
//			caratula.inf308a_6 = cursor.getString(cursor.getColumnIndex("INF308A_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308A_6"));
//			caratula.inf308b_6 = cursor.getString(cursor.getColumnIndex("INF308B_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308B_6"));
//			caratula.inf309c_6 = cursor.getString(cursor.getColumnIndex("INF309C_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309C_6"));
//			caratula.inf309d_6 = cursor.getString(cursor.getColumnIndex("INF309D_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309D_6"));
//			caratula.inf309a_6 = cursor.getString(cursor.getColumnIndex("INF309A_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309A_6"));
//			caratula.inf310_6 = cursor.getString(cursor.getColumnIndex("INF310_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF310_6"));
//			
//			caratula.inf306_7 = cursor.getString(cursor.getColumnIndex("INF306_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF306_7"));
//			caratula.inf307_7 = cursor.getString(cursor.getColumnIndex("INF307_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF307_7"));
//			caratula.inf308a_7 = cursor.getString(cursor.getColumnIndex("INF308A_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308A_7"));
//			caratula.inf308b_7 = cursor.getString(cursor.getColumnIndex("INF308B_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308B_7"));
//			caratula.inf309c_7 = cursor.getString(cursor.getColumnIndex("INF309C_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309C_7"));
//			caratula.inf309d_7 = cursor.getString(cursor.getColumnIndex("INF309D_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309D_7"));
//			caratula.inf309a_7 = cursor.getString(cursor.getColumnIndex("INF309A_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309A_7"));
//			caratula.inf310_7 = cursor.getString(cursor.getColumnIndex("INF310_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF310_7"));
//			
//			caratula.inf306_8 = cursor.getString(cursor.getColumnIndex("INF306_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF306_8"));
//			caratula.inf306_8_o = cursor.getString(cursor.getColumnIndex("INF306_8_O"));
//			caratula.inf307_8 = cursor.getString(cursor.getColumnIndex("INF307_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF307_8"));
//			caratula.inf308a_8 = cursor.getString(cursor.getColumnIndex("INF308A_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308A_8"));
//			caratula.inf308b_8 = cursor.getString(cursor.getColumnIndex("INF308B_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF308B_8"));
//			caratula.inf309c_8 = cursor.getString(cursor.getColumnIndex("INF309C_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309C_8"));
//			caratula.inf309d_8 = cursor.getString(cursor.getColumnIndex("INF309D_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309D_8"));
//			caratula.inf309a_8 = cursor.getString(cursor.getColumnIndex("INF309A_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF309A_8"));
//			caratula.inf310_8 = cursor.getString(cursor.getColumnIndex("INF310_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF310_8"));
//			
//			caratula.inf329_1 = cursor.getString(cursor.getColumnIndex("INF329_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF329_1"));
//			caratula.inf329_2 = cursor.getString(cursor.getColumnIndex("INF329_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF329_2"));
//			caratula.inf329_3 = cursor.getString(cursor.getColumnIndex("INF329_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF329_3"));
//			caratula.inf329_4 = cursor.getString(cursor.getColumnIndex("INF329_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF329_4"));
//			caratula.inf329_5 = cursor.getString(cursor.getColumnIndex("INF329_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF329_5"));
//			caratula.inf329_6 = cursor.getString(cursor.getColumnIndex("INF329_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF329_6"));
//			caratula.inf329_7 = cursor.getString(cursor.getColumnIndex("INF329_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF329_7"));
//			caratula.inf329_7_o = cursor.getString(cursor.getColumnIndex("INF329_7_O"));
//			
//			caratula.inf330_1_1 = cursor.getString(cursor.getColumnIndex("INF330_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_1_1"));
//			caratula.inf330_2_1 = cursor.getString(cursor.getColumnIndex("INF330_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_2_1"));
//			caratula.inf330_3_1 = cursor.getString(cursor.getColumnIndex("INF330_3_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_3_1"));
//			caratula.inf330_4_1 = cursor.getString(cursor.getColumnIndex("INF330_4_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_4_1"));
//			caratula.inf330_5_1 = cursor.getString(cursor.getColumnIndex("INF330_5_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_5_1"));
//			caratula.inf330_5_1_o = cursor.getString(cursor.getColumnIndex("INF330_5_1_O"));
//			
//			caratula.inf330_1_2 = cursor.getString(cursor.getColumnIndex("INF330_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_1_2"));
//			caratula.inf330_2_2 = cursor.getString(cursor.getColumnIndex("INF330_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_2_2"));
//			caratula.inf330_3_2 = cursor.getString(cursor.getColumnIndex("INF330_3_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_3_2"));
//			caratula.inf330_4_2 = cursor.getString(cursor.getColumnIndex("INF330_4_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_4_2"));
//			caratula.inf330_5_2 = cursor.getString(cursor.getColumnIndex("INF330_5_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_5_2"));
//			caratula.inf330_5_2_o = cursor.getString(cursor.getColumnIndex("INF330_5_2_O"));
//			
//			caratula.inf330_1_3 = cursor.getString(cursor.getColumnIndex("INF330_1_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_1_3"));
//			caratula.inf330_2_3 = cursor.getString(cursor.getColumnIndex("INF330_2_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_2_3"));
//			caratula.inf330_3_3 = cursor.getString(cursor.getColumnIndex("INF330_3_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_3_3"));
//			caratula.inf330_4_3 = cursor.getString(cursor.getColumnIndex("INF330_4_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_4_3"));
//			caratula.inf330_5_3 = cursor.getString(cursor.getColumnIndex("INF330_5_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_5_3"));
//			caratula.inf330_5_3_o = cursor.getString(cursor.getColumnIndex("INF330_5_3_O"));
//			
//			caratula.inf330_1_4 = cursor.getString(cursor.getColumnIndex("INF330_1_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_1_4"));
//			caratula.inf330_2_4 = cursor.getString(cursor.getColumnIndex("INF330_2_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_2_4"));
//			caratula.inf330_3_4 = cursor.getString(cursor.getColumnIndex("INF330_3_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_3_4"));
//			caratula.inf330_4_4 = cursor.getString(cursor.getColumnIndex("INF330_4_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_4_4"));
//			caratula.inf330_5_4 = cursor.getString(cursor.getColumnIndex("INF330_5_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_5_4"));
//			caratula.inf330_5_4_o = cursor.getString(cursor.getColumnIndex("INF330_5_4_O"));
//			
//			caratula.inf330_1_5 = cursor.getString(cursor.getColumnIndex("INF330_1_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_1_5"));
//			caratula.inf330_2_5 = cursor.getString(cursor.getColumnIndex("INF330_2_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_2_5"));
//			caratula.inf330_3_5 = cursor.getString(cursor.getColumnIndex("INF330_3_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_3_5"));
//			caratula.inf330_4_5 = cursor.getString(cursor.getColumnIndex("INF330_4_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_4_5"));
//			caratula.inf330_5_5 = cursor.getString(cursor.getColumnIndex("INF330_5_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_5_5"));
//			caratula.inf330_5_5_o = cursor.getString(cursor.getColumnIndex("INF330_5_5_O"));
//			
//			caratula.inf330_1_6 = cursor.getString(cursor.getColumnIndex("INF330_1_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_1_6"));
//			caratula.inf330_2_6 = cursor.getString(cursor.getColumnIndex("INF330_2_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_2_6"));
//			caratula.inf330_3_6 = cursor.getString(cursor.getColumnIndex("INF330_3_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_3_6"));
//			caratula.inf330_4_6 = cursor.getString(cursor.getColumnIndex("INF330_4_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_4_6"));
//			caratula.inf330_5_6 = cursor.getString(cursor.getColumnIndex("INF330_5_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_5_6"));
//			caratula.inf330_5_6_o = cursor.getString(cursor.getColumnIndex("INF330_5_6_O"));
//			
//			caratula.inf330_1_7 = cursor.getString(cursor.getColumnIndex("INF330_1_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_1_7"));
//			caratula.inf330_2_7 = cursor.getString(cursor.getColumnIndex("INF330_2_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_2_7"));
//			caratula.inf330_3_7 = cursor.getString(cursor.getColumnIndex("INF330_3_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_3_7"));
//			caratula.inf330_4_7 = cursor.getString(cursor.getColumnIndex("INF330_4_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_4_7"));
//			caratula.inf330_5_7 = cursor.getString(cursor.getColumnIndex("INF330_5_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF330_5_7"));
//			caratula.inf330_5_7_o = cursor.getString(cursor.getColumnIndex("INF330_5_7_O"));
//			
//			caratula.inf331 = cursor.getString(cursor.getColumnIndex("INF331"))==null?null:cursor.getInt(cursor.getColumnIndex("INF331"));
//			caratula.inf331a_1 = cursor.getString(cursor.getColumnIndex("INF331A_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF331A_1"));
//			caratula.inf331a_2 = cursor.getString(cursor.getColumnIndex("INF331A_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF331A_2"));
//			caratula.inf331a_3 = cursor.getString(cursor.getColumnIndex("INF331A_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF331A_3"));
//			caratula.inf331a_4 = cursor.getString(cursor.getColumnIndex("INF331A_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF331A_4"));
//			caratula.inf331a_5 = cursor.getString(cursor.getColumnIndex("INF331A_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF331A_5"));
//			caratula.inf331a_6 = cursor.getString(cursor.getColumnIndex("INF331A_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF331A_6"));
//			caratula.inf331a_6_o = cursor.getString(cursor.getColumnIndex("INF331A_6_O"));
//			
//			caratula.inf332_1 = cursor.getString(cursor.getColumnIndex("INF332_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF332_1"));
//			caratula.inf332_2 = cursor.getString(cursor.getColumnIndex("INF332_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF332_2"));
//			caratula.inf332_3 = cursor.getString(cursor.getColumnIndex("INF332_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF332_3"));
//			caratula.inf332_4 = cursor.getString(cursor.getColumnIndex("INF332_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF332_4"));
//			caratula.inf332_5 = cursor.getString(cursor.getColumnIndex("INF332_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF332_5"));
//			caratula.inf332_6 = cursor.getString(cursor.getColumnIndex("INF332_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF332_6"));
//			
//			caratula.inf333 = cursor.getString(cursor.getColumnIndex("INF333"))==null?null:cursor.getInt(cursor.getColumnIndex("INF333"));
//			
//			caratula.inf334_1 = cursor.getString(cursor.getColumnIndex("INF334_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_1"));
//			caratula.inf334_2 = cursor.getString(cursor.getColumnIndex("INF334_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_2"));
//			caratula.inf334_3 = cursor.getString(cursor.getColumnIndex("INF334_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_3"));
//			caratula.inf334_4 = cursor.getString(cursor.getColumnIndex("INF334_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_4"));
//			caratula.inf334_5 = cursor.getString(cursor.getColumnIndex("INF334_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_5"));
//			caratula.inf334_6 = cursor.getString(cursor.getColumnIndex("INF334_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_6"));
//			caratula.inf334_7 = cursor.getString(cursor.getColumnIndex("INF334_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_7"));
//			caratula.inf334_8 = cursor.getString(cursor.getColumnIndex("INF334_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_8"));
//			caratula.inf334_9 = cursor.getString(cursor.getColumnIndex("INF334_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_9"));
//			caratula.inf334_10 = cursor.getString(cursor.getColumnIndex("INF334_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_10"));
//			caratula.inf334_11 = cursor.getString(cursor.getColumnIndex("INF334_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF334_11"));
//			
//			caratula.inf335a_1 = cursor.getString(cursor.getColumnIndex("INF335A_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_1"));
//			caratula.inf335b_1 = cursor.getString(cursor.getColumnIndex("INF335B_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_1"));
//			caratula.inf335c_1 = cursor.getString(cursor.getColumnIndex("INF335C_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_1"));
//			caratula.inf335total_1 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_1"));
//			
//			caratula.inf335a_2 = cursor.getString(cursor.getColumnIndex("INF335A_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_2"));
//			caratula.inf335b_2 = cursor.getString(cursor.getColumnIndex("INF335B_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_2"));
//			caratula.inf335c_2 = cursor.getString(cursor.getColumnIndex("INF335C_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_2"));
//			caratula.inf335total_2 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_2"));
//			
//			caratula.inf335a_3 = cursor.getString(cursor.getColumnIndex("INF335A_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_3"));
//			caratula.inf335b_3 = cursor.getString(cursor.getColumnIndex("INF335B_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_3"));
//			caratula.inf335c_3 = cursor.getString(cursor.getColumnIndex("INF335C_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_3"));
//			caratula.inf335total_3 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_3"));
//			
//			caratula.inf335a_4 = cursor.getString(cursor.getColumnIndex("INF335A_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_4"));
//			caratula.inf335b_4 = cursor.getString(cursor.getColumnIndex("INF335B_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_4"));
//			caratula.inf335c_4 = cursor.getString(cursor.getColumnIndex("INF335C_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_4"));
//			caratula.inf335total_4 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_4"));
//			
//			caratula.inf335a_5 = cursor.getString(cursor.getColumnIndex("INF335A_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_5"));
//			caratula.inf335b_5 = cursor.getString(cursor.getColumnIndex("INF335B_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_5"));
//			caratula.inf335c_5 = cursor.getString(cursor.getColumnIndex("INF335C_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_5"));
//			caratula.inf335total_5 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_5"));
//			
//			caratula.inf335a_6 = cursor.getString(cursor.getColumnIndex("INF335A_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_6"));
//			caratula.inf335b_6 = cursor.getString(cursor.getColumnIndex("INF335B_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_6"));
//			caratula.inf335c_6 = cursor.getString(cursor.getColumnIndex("INF335C_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_6"));
//			caratula.inf335total_6 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_6"));
//			
//			caratula.inf335a_7 = cursor.getString(cursor.getColumnIndex("INF335A_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_7"));
//			caratula.inf335b_7 = cursor.getString(cursor.getColumnIndex("INF335B_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_7"));
//			caratula.inf335c_7 = cursor.getString(cursor.getColumnIndex("INF335C_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_7"));
//			caratula.inf335total_7 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_7"));
//			
//			caratula.inf335a_8 = cursor.getString(cursor.getColumnIndex("INF335A_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_8"));
//			caratula.inf335b_8 = cursor.getString(cursor.getColumnIndex("INF335B_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_8"));
//			caratula.inf335c_8 = cursor.getString(cursor.getColumnIndex("INF335C_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_8"));
//			caratula.inf335total_8 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_8"));
//			
//			caratula.inf335a_9 = cursor.getString(cursor.getColumnIndex("INF335A_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_9"));
//			caratula.inf335b_9 = cursor.getString(cursor.getColumnIndex("INF335B_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_9"));
//			caratula.inf335c_9 = cursor.getString(cursor.getColumnIndex("INF335C_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_9"));
//			caratula.inf335total_9 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_9"));
//			
//			caratula.inf335a_10 = cursor.getString(cursor.getColumnIndex("INF335A_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_10"));
//			caratula.inf335b_10 = cursor.getString(cursor.getColumnIndex("INF335B_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_10"));
//			caratula.inf335c_10 = cursor.getString(cursor.getColumnIndex("INF335C_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_10"));
//			caratula.inf335total_10 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_10"));
//			
//			caratula.inf335a_11 = cursor.getString(cursor.getColumnIndex("INF335A_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335A_11"));
//			caratula.inf335b_11 = cursor.getString(cursor.getColumnIndex("INF335B_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335B_11"));
//			caratula.inf335c_11 = cursor.getString(cursor.getColumnIndex("INF335C_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335C_11"));
//			caratula.inf335total_11 = cursor.getString(cursor.getColumnIndex("INF335TOTAL_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF335TOTAL_11"));
//			
//			caratula.inf336_1 = cursor.getString(cursor.getColumnIndex("INF336_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF336_1"));
//			caratula.inf336_2 = cursor.getString(cursor.getColumnIndex("INF336_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF336_2"));
//			caratula.inf336_3 = cursor.getString(cursor.getColumnIndex("INF336_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF336_3"));
//			caratula.inf336_4 = cursor.getString(cursor.getColumnIndex("INF336_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF336_4"));
//			caratula.inf336_5 = cursor.getString(cursor.getColumnIndex("INF336_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF336_5"));
//			caratula.inf336_6 = cursor.getString(cursor.getColumnIndex("INF336_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF336_6"));
//			caratula.inf336_6_o = cursor.getString(cursor.getColumnIndex("INF336_6_O"));
//			
//			caratula.inf337_1 = cursor.getString(cursor.getColumnIndex("INF337_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF337_1"));
//			caratula.inf338_1 = cursor.getString(cursor.getColumnIndex("INF338_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF338_1"));
//			caratula.inf339_1 = cursor.getString(cursor.getColumnIndex("INF339_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF339_1"));
//			caratula.inf340_1_1 = cursor.getString(cursor.getColumnIndex("INF340_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_1_1"));
//			caratula.inf340_2_1 = cursor.getString(cursor.getColumnIndex("INF340_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_2_1"));
//			caratula.inf340_3_1 = cursor.getString(cursor.getColumnIndex("INF340_3_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_3_1"));
//			caratula.inf341_1 = cursor.getString(cursor.getColumnIndex("INF341_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF341_1"));
//			caratula.inf342_1_1 = cursor.getString(cursor.getColumnIndex("INF342_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_1_1"));
//			caratula.inf342_2_1 = cursor.getString(cursor.getColumnIndex("INF342_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_2_1"));
//			caratula.inf342_3_1 = cursor.getString(cursor.getColumnIndex("INF342_3_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_3_1"));
//			caratula.inf342_4_1 = cursor.getString(cursor.getColumnIndex("INF342_4_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_4_1"));
//			caratula.inf342_5_1 = cursor.getString(cursor.getColumnIndex("INF342_5_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_5_1"));
//			caratula.inf342_5_1_o = cursor.getString(cursor.getColumnIndex("INF342_5_1_O"));
//			caratula.inf342_6_1 = cursor.getString(cursor.getColumnIndex("INF342_6_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_6_1"));
//			caratula.inf343_1_1 = cursor.getString(cursor.getColumnIndex("INF343_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_1_1"));
//			caratula.inf343_2_1 = cursor.getString(cursor.getColumnIndex("INF343_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_2_1"));
//			caratula.inf343_3_1 = cursor.getString(cursor.getColumnIndex("INF343_3_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_3_1"));
//			caratula.inf343_3_1_o = cursor.getString(cursor.getColumnIndex("INF343_3_1_O"));
//			caratula.inf343_4_1 = cursor.getString(cursor.getColumnIndex("INF343_4_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_4_1"));
//			
//			caratula.inf337_2 = cursor.getString(cursor.getColumnIndex("INF337_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF337_2"));
//			caratula.inf338_2 = cursor.getString(cursor.getColumnIndex("INF338_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF338_2"));
//			caratula.inf339_2 = cursor.getString(cursor.getColumnIndex("INF339_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF339_2"));
//			caratula.inf340_1_2 = cursor.getString(cursor.getColumnIndex("INF340_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_1_2"));
//			caratula.inf340_2_2 = cursor.getString(cursor.getColumnIndex("INF340_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_2_2"));
//			caratula.inf340_3_2 = cursor.getString(cursor.getColumnIndex("INF340_3_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_3_2"));
//			caratula.inf341_2 = cursor.getString(cursor.getColumnIndex("INF341_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF341_2"));
//			caratula.inf342_1_2 = cursor.getString(cursor.getColumnIndex("INF342_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_1_2"));
//			caratula.inf342_2_2 = cursor.getString(cursor.getColumnIndex("INF342_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_2_2"));
//			caratula.inf342_3_2 = cursor.getString(cursor.getColumnIndex("INF342_3_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_3_2"));
//			caratula.inf342_4_2 = cursor.getString(cursor.getColumnIndex("INF342_4_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_4_2"));
//			caratula.inf342_5_2 = cursor.getString(cursor.getColumnIndex("INF342_5_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_5_2"));
//			caratula.inf342_5_2_o = cursor.getString(cursor.getColumnIndex("INF342_5_2_O"));
//			caratula.inf342_6_2 = cursor.getString(cursor.getColumnIndex("INF342_6_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_6_2"));
//			caratula.inf343_1_2 = cursor.getString(cursor.getColumnIndex("INF343_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_1_2"));
//			caratula.inf343_2_2 = cursor.getString(cursor.getColumnIndex("INF343_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_2_2"));
//			caratula.inf343_3_2 = cursor.getString(cursor.getColumnIndex("INF343_3_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_3_2"));
//			caratula.inf343_3_2_o = cursor.getString(cursor.getColumnIndex("INF343_3_2_O"));
//			caratula.inf343_4_2 = cursor.getString(cursor.getColumnIndex("INF343_4_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_4_2"));
//			
//			caratula.inf337_3 = cursor.getString(cursor.getColumnIndex("INF337_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF337_3"));
//			caratula.inf338_3 = cursor.getString(cursor.getColumnIndex("INF338_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF338_3"));
//			caratula.inf339_3 = cursor.getString(cursor.getColumnIndex("INF339_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF339_3"));
//			caratula.inf340_1_3 = cursor.getString(cursor.getColumnIndex("INF340_1_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_1_3"));
//			caratula.inf340_2_3 = cursor.getString(cursor.getColumnIndex("INF340_2_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_2_3"));
//			caratula.inf340_3_3 = cursor.getString(cursor.getColumnIndex("INF340_3_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_3_3"));
//			caratula.inf341_3 = cursor.getString(cursor.getColumnIndex("INF341_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF341_3"));
//			caratula.inf342_1_3 = cursor.getString(cursor.getColumnIndex("INF342_1_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_1_3"));
//			caratula.inf342_2_3 = cursor.getString(cursor.getColumnIndex("INF342_2_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_2_3"));
//			caratula.inf342_3_3 = cursor.getString(cursor.getColumnIndex("INF342_3_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_3_3"));
//			caratula.inf342_4_3 = cursor.getString(cursor.getColumnIndex("INF342_4_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_4_3"));
//			caratula.inf342_5_3 = cursor.getString(cursor.getColumnIndex("INF342_5_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_5_3"));
//			caratula.inf342_5_3_o = cursor.getString(cursor.getColumnIndex("INF342_5_3_O"));
//			caratula.inf342_6_3 = cursor.getString(cursor.getColumnIndex("INF342_6_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_6_3"));
//			caratula.inf343_1_3 = cursor.getString(cursor.getColumnIndex("INF343_1_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_1_3"));
//			caratula.inf343_2_3 = cursor.getString(cursor.getColumnIndex("INF343_2_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_2_3"));
//			caratula.inf343_3_3 = cursor.getString(cursor.getColumnIndex("INF343_3_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_3_3"));
//			caratula.inf343_3_3_o = cursor.getString(cursor.getColumnIndex("INF343_3_3_O"));
//			caratula.inf343_4_3 = cursor.getString(cursor.getColumnIndex("INF343_4_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_4_3"));
//			
//			caratula.inf337_4 = cursor.getString(cursor.getColumnIndex("INF337_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF337_4"));
//			caratula.inf338_4 = cursor.getString(cursor.getColumnIndex("INF338_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF338_4"));
//			caratula.inf339_4 = cursor.getString(cursor.getColumnIndex("INF339_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF339_4"));
//			caratula.inf340_1_4 = cursor.getString(cursor.getColumnIndex("INF340_1_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_1_4"));
//			caratula.inf340_2_4 = cursor.getString(cursor.getColumnIndex("INF340_2_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_2_4"));
//			caratula.inf340_3_4 = cursor.getString(cursor.getColumnIndex("INF340_3_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_3_4"));
//			caratula.inf341_4 = cursor.getString(cursor.getColumnIndex("INF341_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF341_4"));
//			caratula.inf342_1_4 = cursor.getString(cursor.getColumnIndex("INF342_1_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_1_4"));
//			caratula.inf342_2_4 = cursor.getString(cursor.getColumnIndex("INF342_2_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_2_4"));
//			caratula.inf342_3_4 = cursor.getString(cursor.getColumnIndex("INF342_3_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_3_4"));
//			caratula.inf342_4_4 = cursor.getString(cursor.getColumnIndex("INF342_4_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_4_4"));
//			caratula.inf342_5_4 = cursor.getString(cursor.getColumnIndex("INF342_5_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_5_4"));
//			caratula.inf342_5_4_o = cursor.getString(cursor.getColumnIndex("INF342_5_4_O"));
//			caratula.inf342_6_4 = cursor.getString(cursor.getColumnIndex("INF342_6_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_6_4"));
//			caratula.inf343_1_4 = cursor.getString(cursor.getColumnIndex("INF343_1_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_1_4"));
//			caratula.inf343_2_4 = cursor.getString(cursor.getColumnIndex("INF343_2_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_2_4"));
//			caratula.inf343_3_4 = cursor.getString(cursor.getColumnIndex("INF343_3_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_3_4"));
//			caratula.inf343_3_4_o = cursor.getString(cursor.getColumnIndex("INF343_3_4_O"));
//			caratula.inf343_4_4 = cursor.getString(cursor.getColumnIndex("INF343_4_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_4_4"));
//			
//			caratula.inf337_5 = cursor.getString(cursor.getColumnIndex("INF337_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF337_5"));
//			caratula.inf338_5 = cursor.getString(cursor.getColumnIndex("INF338_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF338_5"));
//			caratula.inf339_5 = cursor.getString(cursor.getColumnIndex("INF339_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF339_5"));
//			caratula.inf340_1_5 = cursor.getString(cursor.getColumnIndex("INF340_1_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_1_5"));
//			caratula.inf340_2_5 = cursor.getString(cursor.getColumnIndex("INF340_2_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_2_5"));
//			caratula.inf340_3_5 = cursor.getString(cursor.getColumnIndex("INF340_3_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_3_5"));
//			caratula.inf341_5 = cursor.getString(cursor.getColumnIndex("INF341_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF341_5"));
//			caratula.inf342_1_5 = cursor.getString(cursor.getColumnIndex("INF342_1_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_1_5"));
//			caratula.inf342_2_5 = cursor.getString(cursor.getColumnIndex("INF342_2_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_2_5"));
//			caratula.inf342_3_5 = cursor.getString(cursor.getColumnIndex("INF342_3_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_3_5"));
//			caratula.inf342_4_5 = cursor.getString(cursor.getColumnIndex("INF342_4_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_4_5"));
//			caratula.inf342_5_5 = cursor.getString(cursor.getColumnIndex("INF342_5_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_5_5"));
//			caratula.inf342_5_5_o = cursor.getString(cursor.getColumnIndex("INF342_5_5_O"));
//			caratula.inf342_6_5 = cursor.getString(cursor.getColumnIndex("INF342_6_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_6_5"));
//			caratula.inf343_1_5 = cursor.getString(cursor.getColumnIndex("INF343_1_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_1_5"));
//			caratula.inf343_2_5 = cursor.getString(cursor.getColumnIndex("INF343_2_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_2_5"));
//			caratula.inf343_3_5 = cursor.getString(cursor.getColumnIndex("INF343_3_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_3_5"));
//			caratula.inf343_3_5_o = cursor.getString(cursor.getColumnIndex("INF343_3_5_O"));
//			caratula.inf343_4_5 = cursor.getString(cursor.getColumnIndex("INF343_4_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_4_5"));
//			
//			caratula.inf337_6 = cursor.getString(cursor.getColumnIndex("INF337_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF337_6"));
//			caratula.inf338_6 = cursor.getString(cursor.getColumnIndex("INF338_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF338_6"));
//			caratula.inf339_6 = cursor.getString(cursor.getColumnIndex("INF339_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF339_6"));
//			caratula.inf340_1_6 = cursor.getString(cursor.getColumnIndex("INF340_1_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_1_6"));
//			caratula.inf340_2_6 = cursor.getString(cursor.getColumnIndex("INF340_2_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_2_6"));
//			caratula.inf340_3_6 = cursor.getString(cursor.getColumnIndex("INF340_3_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF340_3_6"));
//			caratula.inf341_6 = cursor.getString(cursor.getColumnIndex("INF341_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF341_6"));
//			caratula.inf342_1_6 = cursor.getString(cursor.getColumnIndex("INF342_1_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_1_6"));
//			caratula.inf342_2_6 = cursor.getString(cursor.getColumnIndex("INF342_2_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_2_6"));
//			caratula.inf342_3_6 = cursor.getString(cursor.getColumnIndex("INF342_3_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_3_6"));
//			caratula.inf342_4_6 = cursor.getString(cursor.getColumnIndex("INF342_4_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_4_6"));
//			caratula.inf342_5_6 = cursor.getString(cursor.getColumnIndex("INF342_5_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_5_6"));
//			caratula.inf342_5_6_o = cursor.getString(cursor.getColumnIndex("INF342_5_6_O"));
//			caratula.inf342_6_6 = cursor.getString(cursor.getColumnIndex("INF342_6_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF342_6_6"));
//			caratula.inf343_1_6 = cursor.getString(cursor.getColumnIndex("INF343_1_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_1_6"));
//			caratula.inf343_2_6 = cursor.getString(cursor.getColumnIndex("INF343_2_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_2_6"));
//			caratula.inf343_3_6 = cursor.getString(cursor.getColumnIndex("INF343_3_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_3_6"));
//			caratula.inf343_3_6_o = cursor.getString(cursor.getColumnIndex("INF343_3_6_O"));
//			caratula.inf343_4_6 = cursor.getString(cursor.getColumnIndex("INF343_4_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF343_4_6"));
//			caratula.infobs_300_seccion_vii = cursor.getString(cursor.getColumnIndex("INFOBS_300_SECCION_VII"));
//
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap300_III> fillINF_Capitulo300_iiis(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap300_III> caratulas = new ArrayList<INF_Cap300_III>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_300_III WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap300_III data = new INF_Cap300_III();
//
//			List<String> allFieldMatches = data.getFieldMatches(311, 328, "ID_N", "ID_COMPUTO", 
//					"INFOBS_300_SECCION_VII", "USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (INF_Cap300_III)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			caratulas.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap300_III> fillINF_Capitulo300_iiis(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap300_III> caratulas = new ArrayList<INF_Cap300_III>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_300_III WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap300_III caratula = new INF_Cap300_III();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.id_computo = cursor.getString(cursor.getColumnIndex("ID_COMPUTO"))==null?null:cursor.getInt(cursor.getColumnIndex("ID_COMPUTO"));
//			caratula.inf311 = cursor.getString(cursor.getColumnIndex("INF311"))==null?null:cursor.getInt(cursor.getColumnIndex("INF311"));
//			caratula.inf312 = cursor.getString(cursor.getColumnIndex("INF312"))==null?null:cursor.getInt(cursor.getColumnIndex("INF312"));
//			caratula.inf313 = cursor.getString(cursor.getColumnIndex("INF313"))==null?null:cursor.getInt(cursor.getColumnIndex("INF313"));
//			caratula.inf314_aa = cursor.getString(cursor.getColumnIndex("INF314_AA"))==null?null:cursor.getInt(cursor.getColumnIndex("INF314_AA"));
//			caratula.inf314_mm = cursor.getString(cursor.getColumnIndex("INF314_MM"))==null?null:cursor.getInt(cursor.getColumnIndex("INF314_MM"));
//			caratula.inf314_ss = cursor.getString(cursor.getColumnIndex("INF314_SS"))==null?null:cursor.getInt(cursor.getColumnIndex("INF314_SS"));
//			caratula.inf315 = cursor.getString(cursor.getColumnIndex("INF315"))==null?null:cursor.getInt(cursor.getColumnIndex("INF315"));
//			caratula.inf315_o = cursor.getString(cursor.getColumnIndex("INF315_O"));
//			caratula.inf316 = cursor.getString(cursor.getColumnIndex("INF316"))==null?null:cursor.getInt(cursor.getColumnIndex("INF316"));
//			caratula.inf317 = cursor.getString(cursor.getColumnIndex("INF317"))==null?null:cursor.getInt(cursor.getColumnIndex("INF317"));
//			caratula.inf318 = cursor.getString(cursor.getColumnIndex("INF318"))==null?null:cursor.getInt(cursor.getColumnIndex("INF318"));
//			caratula.inf319 = cursor.getString(cursor.getColumnIndex("INF319"))==null?null:cursor.getInt(cursor.getColumnIndex("INF319"));
//			caratula.inf320 = cursor.getString(cursor.getColumnIndex("INF320"))==null?null:cursor.getInt(cursor.getColumnIndex("INF320"));
//			caratula.inf321 = cursor.getString(cursor.getColumnIndex("INF321"))==null?null:cursor.getInt(cursor.getColumnIndex("INF321"));
//			caratula.inf321_o = cursor.getString(cursor.getColumnIndex("INF321_O"));
//			caratula.inf322 = cursor.getString(cursor.getColumnIndex("INF322"))==null?null:cursor.getInt(cursor.getColumnIndex("INF322"));
//			caratula.inf323 = cursor.getString(cursor.getColumnIndex("INF323"))==null?null:cursor.getInt(cursor.getColumnIndex("INF323"));
//			caratula.inf324_1 = cursor.getString(cursor.getColumnIndex("INF324_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF324_1"));
//			caratula.inf324_2 = cursor.getString(cursor.getColumnIndex("INF324_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF324_2"));
//			caratula.inf324_3 = cursor.getString(cursor.getColumnIndex("INF324_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF324_3"));
//			caratula.inf324_4 = cursor.getString(cursor.getColumnIndex("INF324_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF324_4"));
//			caratula.inf324_o = cursor.getString(cursor.getColumnIndex("INF324_O"));
//			caratula.inf325 = cursor.getString(cursor.getColumnIndex("INF325"))==null?null:cursor.getInt(cursor.getColumnIndex("INF325"));
//			caratula.inf326 = cursor.getString(cursor.getColumnIndex("INF326"))==null?null:cursor.getInt(cursor.getColumnIndex("INF326"));
//			caratula.inf326_o = cursor.getString(cursor.getColumnIndex("INF326_O"));
//			caratula.inf327 = cursor.getString(cursor.getColumnIndex("INF327"))==null?null:cursor.getInt(cursor.getColumnIndex("INF327"));
//			caratula.inf328 = cursor.getString(cursor.getColumnIndex("INF328"))==null?null:cursor.getInt(cursor.getColumnIndex("INF328"));
//			caratula.inf328_o = cursor.getString(cursor.getColumnIndex("INF328_O"));
//
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap400> fillINF_Capitulo400s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap400> caratulas = new ArrayList<INF_Cap400>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_400 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap400 data = new INF_Cap400();
//
//			List<String> allFieldMatches = data.getFieldMatches(401, 421, "ID_N", "OBS_CAP_400_1","OBS_CAP_400_2",
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (INF_Cap400)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			caratulas.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap400> fillINF_Capitulo400s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap400> caratulas = new ArrayList<INF_Cap400>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_400 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap400 caratula = new INF_Cap400();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.inf401_1 = cursor.getString(cursor.getColumnIndex("INF401_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_1"));
//			caratula.inf401_2 = cursor.getString(cursor.getColumnIndex("INF401_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_2"));
//			caratula.inf401_3 = cursor.getString(cursor.getColumnIndex("INF401_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_3"));
//			caratula.inf401_4 = cursor.getString(cursor.getColumnIndex("INF401_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_4"));
//			caratula.inf401_5 = cursor.getString(cursor.getColumnIndex("INF401_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_5"));
//			caratula.inf401_6 = cursor.getString(cursor.getColumnIndex("INF401_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_6"));
//			caratula.inf401_7 = cursor.getString(cursor.getColumnIndex("INF401_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_7"));
//			caratula.inf401_8 = cursor.getString(cursor.getColumnIndex("INF401_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_8"));
//			caratula.inf401_9 = cursor.getString(cursor.getColumnIndex("INF401_9"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_9"));
//			caratula.inf401_10 = cursor.getString(cursor.getColumnIndex("INF401_10"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_10"));
//			caratula.inf401_11 = cursor.getString(cursor.getColumnIndex("INF401_11"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_11"));
//			caratula.inf401_12 = cursor.getString(cursor.getColumnIndex("INF401_12"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_12"));
//			caratula.inf401_13 = cursor.getString(cursor.getColumnIndex("INF401_13"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_13"));
//			caratula.inf401_14 = cursor.getString(cursor.getColumnIndex("INF401_14"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_14"));
//			caratula.inf401_15 = cursor.getString(cursor.getColumnIndex("INF401_15"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_15"));
//			caratula.inf401_16 = cursor.getString(cursor.getColumnIndex("INF401_16"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_16"));
//			caratula.inf401_17 = cursor.getString(cursor.getColumnIndex("INF401_17"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_17"));
//			caratula.inf401_18 = cursor.getString(cursor.getColumnIndex("INF401_18"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_18"));
//			caratula.inf401_19 = cursor.getString(cursor.getColumnIndex("INF401_19"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_19"));
//			caratula.inf401_20 = cursor.getString(cursor.getColumnIndex("INF401_20"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_20"));
//			caratula.inf401_21 = cursor.getString(cursor.getColumnIndex("INF401_21"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_21"));
//			caratula.inf401_22 = cursor.getString(cursor.getColumnIndex("INF401_22"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_22"));
//			caratula.inf401_23 = cursor.getString(cursor.getColumnIndex("INF401_23"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_23"));
//			caratula.inf401_24 = cursor.getString(cursor.getColumnIndex("INF401_24"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_24"));
//			caratula.inf401_25 = cursor.getString(cursor.getColumnIndex("INF401_25"))==null?null:cursor.getInt(cursor.getColumnIndex("INF401_25"));
//			caratula.obs_cap_400_1 = cursor.getString(cursor.getColumnIndex("OBS_CAP_400_1"));
//			caratula.inf402 = cursor.getString(cursor.getColumnIndex("INF402"))==null?null:cursor.getInt(cursor.getColumnIndex("INF402"));
//			caratula.inf403 = cursor.getString(cursor.getColumnIndex("INF403"))==null?null:cursor.getInt(cursor.getColumnIndex("INF403"));
//			caratula.inf404 = cursor.getString(cursor.getColumnIndex("INF404"))==null?null:cursor.getInt(cursor.getColumnIndex("INF404"));
//			caratula.inf404a = cursor.getString(cursor.getColumnIndex("INF404A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF404A"));
//			caratula.inf404b_1 = cursor.getString(cursor.getColumnIndex("INF404B_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF404B_1"));
//			caratula.inf404b_2 = cursor.getString(cursor.getColumnIndex("INF404B_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF404B_2"));
//			caratula.inf404b_3 = cursor.getString(cursor.getColumnIndex("INF404B_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF404B_3"));
//			caratula.inf404b_4 = cursor.getString(cursor.getColumnIndex("INF404B_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF404B_4"));
//			caratula.inf404b_5 = cursor.getString(cursor.getColumnIndex("INF404B_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF404B_5"));
//			caratula.inf404b_6 = cursor.getString(cursor.getColumnIndex("INF404B_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF404B_6"));
//			caratula.inf404b_7 = cursor.getString(cursor.getColumnIndex("INF404B_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF404B_7"));
//			caratula.inf404b_7_o = cursor.getString(cursor.getColumnIndex("INF404B_7_O"));
//			caratula.inf405 = cursor.getString(cursor.getColumnIndex("INF405"))==null?null:cursor.getInt(cursor.getColumnIndex("INF405"));
//			caratula.inf406 = cursor.getString(cursor.getColumnIndex("INF406"))==null?null:cursor.getInt(cursor.getColumnIndex("INF406"));
//			caratula.inf407 = cursor.getString(cursor.getColumnIndex("INF407"))==null?null:cursor.getInt(cursor.getColumnIndex("INF407"));
//			caratula.inf408 = cursor.getString(cursor.getColumnIndex("INF408"))==null?null:cursor.getInt(cursor.getColumnIndex("INF408"));
//			caratula.inf409 = cursor.getString(cursor.getColumnIndex("INF409"))==null?null:cursor.getInt(cursor.getColumnIndex("INF409"));
//			caratula.inf410 = cursor.getString(cursor.getColumnIndex("INF410"))==null?null:cursor.getInt(cursor.getColumnIndex("INF410"));
//			caratula.inf410_d = cursor.getString(cursor.getColumnIndex("INF410_D"))==null?null:cursor.getInt(cursor.getColumnIndex("INF410_D"));
//			caratula.inf410_pat = cursor.getString(cursor.getColumnIndex("INF410_PAT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF410_PAT"));
//			caratula.inf410_m = cursor.getString(cursor.getColumnIndex("INF410_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF410_M"));
//			caratula.inf411 = cursor.getString(cursor.getColumnIndex("INF411"))==null?null:cursor.getInt(cursor.getColumnIndex("INF411"));
//			caratula.inf411_d = cursor.getString(cursor.getColumnIndex("INF411_D"))==null?null:cursor.getInt(cursor.getColumnIndex("INF411_D"));
//			caratula.inf411_pat = cursor.getString(cursor.getColumnIndex("INF411_PAT"))==null?null:cursor.getInt(cursor.getColumnIndex("INF411_PAT"));
//			caratula.inf411_m = cursor.getString(cursor.getColumnIndex("INF411_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF411_M"));
//			caratula.inf412 = cursor.getString(cursor.getColumnIndex("INF412"))==null?null:cursor.getInt(cursor.getColumnIndex("INF412"));
//			caratula.inf413 = cursor.getString(cursor.getColumnIndex("INF413"))==null?null:cursor.getInt(cursor.getColumnIndex("INF413"));
//			caratula.inf414 = cursor.getString(cursor.getColumnIndex("INF414"))==null?null:cursor.getInt(cursor.getColumnIndex("INF414"));
//			caratula.inf415_d = cursor.getString(cursor.getColumnIndex("INF415_D"))==null?null:cursor.getInt(cursor.getColumnIndex("INF415_D"));
//			caratula.inf415_m = cursor.getString(cursor.getColumnIndex("INF415_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF415_M"));
//			caratula.inf415_a = cursor.getString(cursor.getColumnIndex("INF415_A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF415_A"));
//			caratula.inf416 = cursor.getString(cursor.getColumnIndex("INF416"))==null?null:cursor.getInt(cursor.getColumnIndex("INF416"));
//			caratula.inf416_o = cursor.getString(cursor.getColumnIndex("INF416_O"));
//			caratula.inf417_1 = cursor.getString(cursor.getColumnIndex("INF417_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF417_1"));
//			caratula.inf417_2 = cursor.getString(cursor.getColumnIndex("INF417_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF417_2"));
//			caratula.inf417_3 = cursor.getString(cursor.getColumnIndex("INF417_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF417_3"));
//			caratula.inf417_4 = cursor.getString(cursor.getColumnIndex("INF417_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF417_4"));
//			caratula.inf417_5 = cursor.getString(cursor.getColumnIndex("INF417_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF417_5"));
//			caratula.inf417_5_o = cursor.getString(cursor.getColumnIndex("INF417_5_O"));
//			caratula.inf418 = cursor.getString(cursor.getColumnIndex("INF418"))==null?null:cursor.getInt(cursor.getColumnIndex("INF418"));
//			caratula.inf419_1 = cursor.getString(cursor.getColumnIndex("INF419_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_1"));
//			caratula.inf419_1_1 = cursor.getString(cursor.getColumnIndex("INF419_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_1_1"));
//			caratula.inf419_1_2 = cursor.getString(cursor.getColumnIndex("INF419_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_1_2"));
//			caratula.inf419_2 = cursor.getString(cursor.getColumnIndex("INF419_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_2"));
//			caratula.inf419_2_1 = cursor.getString(cursor.getColumnIndex("INF419_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_2_1"));
//			caratula.inf419_2_2 = cursor.getString(cursor.getColumnIndex("INF419_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_2_2"));
//			caratula.inf419_3 = cursor.getString(cursor.getColumnIndex("INF419_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_3"));
//			caratula.inf419_3_1 = cursor.getString(cursor.getColumnIndex("INF419_3_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_3_1"));
//			caratula.inf419_3_2 = cursor.getString(cursor.getColumnIndex("INF419_3_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_3_2"));
//			caratula.inf419_4 = cursor.getString(cursor.getColumnIndex("INF419_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_4"));
//			caratula.inf419_4_o = cursor.getString(cursor.getColumnIndex("INF419_4_O"));
//			caratula.inf419_4_1 = cursor.getString(cursor.getColumnIndex("INF419_4_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_4_1"));
//			caratula.inf419_4_2 = cursor.getString(cursor.getColumnIndex("INF419_4_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF419_4_2"));
//			caratula.inf420 = cursor.getString(cursor.getColumnIndex("INF420"))==null?null:cursor.getInt(cursor.getColumnIndex("INF420"));
//			caratula.inf421_1 = cursor.getString(cursor.getColumnIndex("INF421_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_1"));
//			caratula.inf421_1_1 = cursor.getString(cursor.getColumnIndex("INF421_1_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_1_1"));
//			caratula.inf421_1_2 = cursor.getString(cursor.getColumnIndex("INF421_1_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_1_2"));
//			caratula.inf421_2 = cursor.getString(cursor.getColumnIndex("INF421_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_2"));
//			caratula.inf421_2_1 = cursor.getString(cursor.getColumnIndex("INF421_2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_2_1"));
//			caratula.inf421_2_2 = cursor.getString(cursor.getColumnIndex("INF421_2_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_2_2"));
//			caratula.inf421_3 = cursor.getString(cursor.getColumnIndex("INF421_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_3"));
//			caratula.inf421_3_1 = cursor.getString(cursor.getColumnIndex("INF421_3_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_3_1"));
//			caratula.inf421_3_2 = cursor.getString(cursor.getColumnIndex("INF421_3_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_3_2"));
//			caratula.inf421_4 = cursor.getString(cursor.getColumnIndex("INF421_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_4"));
//			caratula.inf421_4_1 = cursor.getString(cursor.getColumnIndex("INF421_4_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_4_1"));
//			caratula.inf421_4_2 = cursor.getString(cursor.getColumnIndex("INF421_4_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_4_2"));
//			caratula.inf421_5 = cursor.getString(cursor.getColumnIndex("INF421_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_5"));
//			caratula.inf421_5_o = cursor.getString(cursor.getColumnIndex("INF421_5_O"));
//			caratula.inf421_5_1 = cursor.getString(cursor.getColumnIndex("INF421_5_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_5_1"));
//			caratula.inf421_5_2 = cursor.getString(cursor.getColumnIndex("INF421_5_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF421_5_2"));
//			caratula.obs_cap_400_2 = cursor.getString(cursor.getColumnIndex("OBS_CAP_400_2"));
//
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap500> fillINF_Capitulo500s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap500> caratulas = new ArrayList<INF_Cap500>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_500 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap500 data = new INF_Cap500();
//
//			List<String> allFieldMatches = data.getFieldMatches(501, 507, "ID_N", "OBS_CAP_500_1",
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (INF_Cap500)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			caratulas.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap500> fillINF_Capitulo500s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap500> caratulas = new ArrayList<INF_Cap500>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_500 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap500 caratula = new INF_Cap500();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.inf501 = cursor.getString(cursor.getColumnIndex("INF501"))==null?null:cursor.getInt(cursor.getColumnIndex("INF501"));
//			caratula.inf502 = cursor.getString(cursor.getColumnIndex("INF502"))==null?null:cursor.getInt(cursor.getColumnIndex("INF502"));
//			caratula.inf503_1 = cursor.getString(cursor.getColumnIndex("INF503_1"))==null?null:cursor.getInt(cursor.getColumnIndex("INF503_1"));
//			caratula.inf503_2 = cursor.getString(cursor.getColumnIndex("INF503_2"))==null?null:cursor.getInt(cursor.getColumnIndex("INF503_2"));
//			caratula.inf503_3 = cursor.getString(cursor.getColumnIndex("INF503_3"))==null?null:cursor.getInt(cursor.getColumnIndex("INF503_3"));
//			caratula.inf503_4 = cursor.getString(cursor.getColumnIndex("INF503_4"))==null?null:cursor.getInt(cursor.getColumnIndex("INF503_4"));
//			caratula.inf503_5 = cursor.getString(cursor.getColumnIndex("INF503_5"))==null?null:cursor.getInt(cursor.getColumnIndex("INF503_5"));
//			caratula.inf503_6 = cursor.getString(cursor.getColumnIndex("INF503_6"))==null?null:cursor.getInt(cursor.getColumnIndex("INF503_6"));
//			caratula.inf503_7 = cursor.getString(cursor.getColumnIndex("INF503_7"))==null?null:cursor.getInt(cursor.getColumnIndex("INF503_7"));
//			caratula.inf503_7_o = cursor.getString(cursor.getColumnIndex("INF503_7_O"));
//			caratula.inf503_8 = cursor.getString(cursor.getColumnIndex("INF503_8"))==null?null:cursor.getInt(cursor.getColumnIndex("INF503_8"));
//			caratula.inf504_1_d = cursor.getString(cursor.getColumnIndex("INF504_1_D"))==null?null:cursor.getInt(cursor.getColumnIndex("INF504_1_D"));
//			caratula.inf504_1_m = cursor.getString(cursor.getColumnIndex("INF504_1_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF504_1_M"));
//			caratula.inf504_1_a = cursor.getString(cursor.getColumnIndex("INF504_1_A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF504_1_A"));
//			caratula.inf504_2_d = cursor.getString(cursor.getColumnIndex("INF504_2_D"))==null?null:cursor.getInt(cursor.getColumnIndex("INF504_2_D"));
//			caratula.inf504_2_m = cursor.getString(cursor.getColumnIndex("INF504_2_M"))==null?null:cursor.getInt(cursor.getColumnIndex("INF504_2_M"));
//			caratula.inf504_2_a = cursor.getString(cursor.getColumnIndex("INF504_2_A"))==null?null:cursor.getInt(cursor.getColumnIndex("INF504_2_A"));
//			caratula.inf505 = cursor.getString(cursor.getColumnIndex("INF505"))==null?null:cursor.getInt(cursor.getColumnIndex("INF505"));
//			caratula.inf506 = cursor.getString(cursor.getColumnIndex("INF506"))==null?null:cursor.getInt(cursor.getColumnIndex("INF506"));
//			caratula.inf507 = cursor.getString(cursor.getColumnIndex("INF507"))==null?null:cursor.getInt(cursor.getColumnIndex("INF507"));
//			caratula.obs_cap_500_1 = cursor.getString(cursor.getColumnIndex("OBS_CAP_500_1"));
//
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<INF_Cap600> fillINF_Capitulo600s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap600> data600 = new ArrayList<INF_Cap600>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_600 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap600 data = new INF_Cap600();
//			
//			List<String> allFieldMatches = data.getFieldMatches(601, 609, "ID_N", "INF337_1_A", "INF337_2_A", 
//					"INF337_3_A", "INF337_4_A", "INF337_5_A", "INF337_6",
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (INF_Cap600)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//
//			data600.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return data600;
//	}
	
//	public ArrayList<Anexo_C100> fill_Anexo100s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Anexo_C100> dataanexo100 = new ArrayList<Anexo_C100>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_ANEXO_100 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			Anexo_C100 data = new Anexo_C100();
//			
//			List<String> allFieldMatches = data.getFieldMatches(-1, -1, "ID_N", "CUEST", "ANIO", "TRIMESTRE", "UBIGEO", "SEC_TOT",
//					"NOMB_COMISARIO", "CARGO_COMISARIO", "NOMB_FACILITADOR", "CARGO_FACILIT", "VA_1_DMA", "VA_1_D", "VA_1_M", "VA_1_A",
//					"IVRESFIN_ANEX", "IVRESFIN_ANEX_O", "OBSERVACIONES_ANEXO",
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (Anexo_C100)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			dataanexo100.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return dataanexo100;
//	}
	
//	public ArrayList<Anexo_C100_Det> fill_Anexo100_Dets(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Anexo_C100_Det> dataanexo100_det = new ArrayList<Anexo_C100_Det>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_ANEXO_100_DET WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			Anexo_C100_Det data = new Anexo_C100_Det();
//			
//			List<String> allFieldMatches = data.getFieldMatches(101, 105, "ID_N", "SEC_N", "A_OBS_300", "NRO_VIDEO_300", 
//					"CAP100_OMISION", "USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (Anexo_C100_Det)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			dataanexo100_det.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return dataanexo100_det;
//	}
//	
//	public ArrayList<Anexo_C200_Det> fill_Anexo200_Dets(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Anexo_C200_Det> dataanexo200_det = new ArrayList<Anexo_C200_Det>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_ANEXO_200_DET WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			Anexo_C200_Det data = new Anexo_C200_Det();
//			
//			List<String> allFieldMatches = data.getFieldMatches(200, 208, "ID_N", "SEC_N", "A_OBS_100_200",
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (Anexo_C200_Det)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			dataanexo200_det.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return dataanexo200_det;
//	}
//	
//	public ArrayList<Anexo_C300_Det> fill_Anexo300_Dets(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Anexo_C300_Det> dataanexo300_det = new ArrayList<Anexo_C300_Det>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_ANEXO_300_DET WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			Anexo_C300_Det data = new Anexo_C300_Det();
//			
//			List<String> allFieldMatches = data.getFieldMatches(301, 310, "ID_N", "SEC_N", "NRO_REG_ANEXO", 
//					"A_300_LLAVE", "USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (Anexo_C300_Det)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			dataanexo300_det.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return dataanexo300_det;
//	}
//	
//	public ArrayList<INF_Cap600_I> fillINF_Capitulo600is(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<INF_Cap600_I> data600i = new ArrayList<INF_Cap600_I>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_01_600_I WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			INF_Cap600_I data = new INF_Cap600_I();
//			
//			List<String> allFieldMatches = data.getFieldMatches(610, 635, "ID_N", "OBS_ADICIONAL", 
//					"A_300_LLAVE", "USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (INF_Cap600_I)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//
//			data600i.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return data600i;
//	}
//	
//	public ArrayList<AT_Muestra> fillAT_Muestra(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<AT_Muestra> muestras = new ArrayList<AT_Muestra>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_MUESTRAAT WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			AT_Muestra muestra = new AT_Muestra();
//			muestra.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			muestra.n_at = cursor.getString(cursor.getColumnIndex("N_AT"))==null?null:cursor.getInt(cursor.getColumnIndex("N_AT"));
//			muestra.at_n = cursor.getString(cursor.getColumnIndex("AT_N"))==null?null:cursor.getInt(cursor.getColumnIndex("AT_N"));
//			muestra.estado = cursor.getString(cursor.getColumnIndex("ESTADO"))==null?null:cursor.getInt(cursor.getColumnIndex("ESTADO"));
//
//			muestras.add(muestra);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return muestras;
//	}
//	
//	public ArrayList<ATVisita> fillAT_Visitas(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<ATVisita> caratulas = new ArrayList<ATVisita>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_02_VISITA WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			ATVisita data = new ATVisita();
//
//			List<String> allFieldMatches = data.getFieldMatches(1, 10, "ID_N", "NROVIS", "EFECHA", 
//					"EHORADE","EHORAA","EPVFECHA","EPVHORA","ERESVIS","ERESVIS_O","SFECHA","SHORADE",
//					"SHORAA","SRESVIS","SRESVIS_O","GPSLATITUD","GPSLONGITUD",
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (ATVisita)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			caratulas.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<ATVisita> fillAT_Visitas(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<ATVisita> caratulas = new ArrayList<ATVisita>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_02_VISITA WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			ATVisita caratula = new ATVisita();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.nrovis = cursor.getString(cursor.getColumnIndex("NROVIS"))==null?null:cursor.getInt(cursor.getColumnIndex("NROVIS"));
//			caratula.efecha = cursor.getString(cursor.getColumnIndex("EFECHA"));
//			caratula.ehorade = cursor.getString(cursor.getColumnIndex("EHORADE"));
//			caratula.ehoraa = cursor.getString(cursor.getColumnIndex("EHORAA"));
//			caratula.epvfecha = cursor.getString(cursor.getColumnIndex("EPVFECHA"));
//			caratula.epvhora = cursor.getString(cursor.getColumnIndex("EPVHORA"));
//			caratula.eresvis = cursor.getString(cursor.getColumnIndex("ERESVIS"))==null?null:cursor.getInt(cursor.getColumnIndex("ERESVIS"));
//			caratula.eresvis_o = cursor.getString(cursor.getColumnIndex("ERESVIS_O"));
//			caratula.sfecha = cursor.getString(cursor.getColumnIndex("SFECHA"));
//			caratula.shorade = cursor.getString(cursor.getColumnIndex("SHORADE"));
//			caratula.shoraa = cursor.getString(cursor.getColumnIndex("SHORAA"));
//			caratula.sresvis = cursor.getString(cursor.getColumnIndex("SRESVIS"))==null?null:cursor.getInt(cursor.getColumnIndex("SRESVIS"));
//			caratula.sresvis_o = cursor.getString(cursor.getColumnIndex("SRESVIS_O"));
//			
//			caratula.gpslatitud = cursor.getString(cursor.getColumnIndex("GPSLATITUD"));
//			caratula.gpslongitud = cursor.getString(cursor.getColumnIndex("GPSLONGITUD"));
//			
////			caratula.usucre = cursor.getString(cursor.getColumnIndex("USUCRE"));
////			caratula.feccre = cursor.getString(cursor.getColumnIndex("FECCRE"));
////			caratula.usureg = cursor.getString(cursor.getColumnIndex("USUREG"));
////			caratula.fecreg = cursor.getString(cursor.getColumnIndex("FECREG"));
////			caratula.fecenv = cursor.getString(cursor.getColumnIndex("FECENV"));
//			
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<ATCap100BE> fillATCapitulo100s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<ATCap100BE> caratulas = new ArrayList<ATCap100BE>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_02_100 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			ATCap100BE data = new ATCap100BE();
//
//			List<String> allFieldMatches = data.getFieldMatches(101, 109, "ID_N", "ID_AT", "AT_N", 
//					"AT_NALF","AT_TOT","OBS_100", "AT_CAP200", 
//					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (ATCap100BE)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			caratulas.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<ATCap100BE> fillATCapitulo100s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<ATCap100BE> caratulas = new ArrayList<ATCap100BE>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_02_100 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			ATCap100BE caratula = new ATCap100BE();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.id_at = cursor.getString(cursor.getColumnIndex("ID_AT"))==null?null:cursor.getInt(cursor.getColumnIndex("ID_AT"));
//			caratula.at_n = cursor.getString(cursor.getColumnIndex("AT_N"))==null?null:cursor.getInt(cursor.getColumnIndex("AT_N"));
//			caratula.at_nalf = cursor.getString(cursor.getColumnIndex("AT_NALF"));
//			caratula.at_tot = cursor.getString(cursor.getColumnIndex("AT_TOT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT_TOT"));
//			caratula.at101 = cursor.getString(cursor.getColumnIndex("AT101"))==null?null:cursor.getInt(cursor.getColumnIndex("AT101"));
//			caratula.at101_alf = cursor.getString(cursor.getColumnIndex("AT101_ALF"));
//			caratula.at101_o = cursor.getString(cursor.getColumnIndex("AT101_O"));
//			caratula.at101_nro = cursor.getString(cursor.getColumnIndex("AT101_NRO"));
//			caratula.at102 = cursor.getString(cursor.getColumnIndex("AT102"));
//			caratula.at103_d = cursor.getString(cursor.getColumnIndex("AT103_D"));
//			caratula.at103_m = cursor.getString(cursor.getColumnIndex("AT103_M"));
//			caratula.at103_a = cursor.getString(cursor.getColumnIndex("AT103_A"));
//			caratula.at103_hor = cursor.getString(cursor.getColumnIndex("AT103_HOR"))==null?null:cursor.getInt(cursor.getColumnIndex("AT103_HOR"));
//			caratula.at103_min = cursor.getString(cursor.getColumnIndex("AT103_MIN"))==null?null:cursor.getInt(cursor.getColumnIndex("AT103_MIN"));
//			caratula.at104a = cursor.getString(cursor.getColumnIndex("AT104A"))==null?null:cursor.getInt(cursor.getColumnIndex("AT104A"));
//			caratula.at104a_o = cursor.getString(cursor.getColumnIndex("AT104A_O"));
//			caratula.at104b = cursor.getString(cursor.getColumnIndex("AT104B"))==null?null:cursor.getInt(cursor.getColumnIndex("AT104B"));
//			caratula.at104b_o = cursor.getString(cursor.getColumnIndex("AT104B_O"));
//			caratula.at104c_v1 = cursor.getString(cursor.getColumnIndex("AT104C_V1"));
//			caratula.at104c_r1 = cursor.getString(cursor.getColumnIndex("AT104C_R1"));
//			caratula.at104c_v2 = cursor.getString(cursor.getColumnIndex("AT104C_V2"));
//			caratula.at104c_r2 = cursor.getString(cursor.getColumnIndex("AT104C_R2"));
//			caratula.at105 = cursor.getString(cursor.getColumnIndex("AT105"))==null?null:cursor.getInt(cursor.getColumnIndex("AT105"));
//			caratula.at105_o = cursor.getString(cursor.getColumnIndex("AT105_O"));
//			caratula.at106_1 = cursor.getString(cursor.getColumnIndex("AT106_1"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_1"));
//			caratula.at106_1_cant = cursor.getString(cursor.getColumnIndex("AT106_1_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_1_CANT"));
//			caratula.at106_2 = cursor.getString(cursor.getColumnIndex("AT106_2"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_2"));
//			caratula.at106_2_cant = cursor.getString(cursor.getColumnIndex("AT106_2_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_2_CANT"));
//			caratula.at106_3 = cursor.getString(cursor.getColumnIndex("AT106_3"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_3"));
//			caratula.at106_3_cant = cursor.getString(cursor.getColumnIndex("AT106_3_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_3_CANT"));
//			caratula.at106_4 = cursor.getString(cursor.getColumnIndex("AT106_4"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_4"));
//			caratula.at106_4_cant = cursor.getString(cursor.getColumnIndex("AT106_4_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_4_CANT"));
//			caratula.at106_5 = cursor.getString(cursor.getColumnIndex("AT106_5"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_5"));
//			caratula.at106_5_cant = cursor.getString(cursor.getColumnIndex("AT106_5_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_5_CANT"));
//			caratula.at106_6 = cursor.getString(cursor.getColumnIndex("AT106_6"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_6"));
//			caratula.at106_6_cant = cursor.getString(cursor.getColumnIndex("AT106_6_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_6_CANT"));
//			caratula.at106_7 = cursor.getString(cursor.getColumnIndex("AT106_7"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_7"));
//			caratula.at106_7_cant = cursor.getString(cursor.getColumnIndex("AT106_7_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_7_CANT"));
//			caratula.at106_8 = cursor.getString(cursor.getColumnIndex("AT106_8"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_8"));
//			caratula.at106_8_cant = cursor.getString(cursor.getColumnIndex("AT106_8_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_8_CANT"));
//			caratula.at106_9 = cursor.getString(cursor.getColumnIndex("AT106_9"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_9"));
//			caratula.at106_9_cant = cursor.getString(cursor.getColumnIndex("AT106_9_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_9_CANT"));
//			caratula.at106_10 = cursor.getString(cursor.getColumnIndex("AT106_10"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_10"));
//			caratula.at106_10_cant = cursor.getString(cursor.getColumnIndex("AT106_10_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_10_CANT"));
//			caratula.at106_11 = cursor.getString(cursor.getColumnIndex("AT106_11"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_11"));
//			caratula.at106_11_cant = cursor.getString(cursor.getColumnIndex("AT106_11_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_11_CANT"));
//			caratula.at106_12 = cursor.getString(cursor.getColumnIndex("AT106_12"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_12"));
//			caratula.at106_12_cant = cursor.getString(cursor.getColumnIndex("AT106_12_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_12_CANT"));
//			caratula.at106_13 = cursor.getString(cursor.getColumnIndex("AT106_13"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_13"));
//			caratula.at106_13_cant = cursor.getString(cursor.getColumnIndex("AT106_13_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_13_CANT"));
//			caratula.at106_13_o = cursor.getString(cursor.getColumnIndex("AT106_13_O"));
//			caratula.at106_14 = cursor.getString(cursor.getColumnIndex("AT106_14"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_14"));
//			caratula.at106_14_cant = cursor.getString(cursor.getColumnIndex("AT106_14_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_14_CANT"));
//			caratula.at106_15 = cursor.getString(cursor.getColumnIndex("AT106_15"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_15"));
//			caratula.at106_15_cant = cursor.getString(cursor.getColumnIndex("AT106_15_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_15_CANT"));
//			caratula.at106_16 = cursor.getString(cursor.getColumnIndex("AT106_16"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_16"));
//			caratula.at106_16_cant = cursor.getString(cursor.getColumnIndex("AT106_16_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_16_CANT"));
//			caratula.at106_17 = cursor.getString(cursor.getColumnIndex("AT106_17"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_17"));
//			caratula.at106_17_cant = cursor.getString(cursor.getColumnIndex("AT106_17_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_17_CANT"));
//			caratula.at106_18 = cursor.getString(cursor.getColumnIndex("AT106_18"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_18"));
//			caratula.at106_18_cant = cursor.getString(cursor.getColumnIndex("AT106_18_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_18_CANT"));
//			caratula.at106_19 = cursor.getString(cursor.getColumnIndex("AT106_19"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_19"));
//			caratula.at106_19_cant = cursor.getString(cursor.getColumnIndex("AT106_19_CANT"))==null?null:cursor.getInt(cursor.getColumnIndex("AT106_19_CANT"));
//			caratula.at106_19_o = cursor.getString(cursor.getColumnIndex("AT106_19_O"));
//			caratula.at107_1 = cursor.getString(cursor.getColumnIndex("AT107_1"))==null?null:cursor.getInt(cursor.getColumnIndex("AT107_1"));
//			caratula.at107_2 = cursor.getString(cursor.getColumnIndex("AT107_2"))==null?null:cursor.getInt(cursor.getColumnIndex("AT107_2"));
//			caratula.at107_3 = cursor.getString(cursor.getColumnIndex("AT107_3"))==null?null:cursor.getInt(cursor.getColumnIndex("AT107_3"));
//			caratula.at108 = cursor.getString(cursor.getColumnIndex("AT108"))==null?null:cursor.getInt(cursor.getColumnIndex("AT108"));
//			caratula.at108_1 = cursor.getString(cursor.getColumnIndex("AT108_1"))==null?null:cursor.getInt(cursor.getColumnIndex("AT108_1"));
//			caratula.at108_2 = cursor.getString(cursor.getColumnIndex("AT108_2"))==null?null:cursor.getInt(cursor.getColumnIndex("AT108_2"));
//			caratula.at108_3 = cursor.getString(cursor.getColumnIndex("AT108_3"))==null?null:cursor.getInt(cursor.getColumnIndex("AT108_3"));
//			caratula.at109_1 = cursor.getString(cursor.getColumnIndex("AT109_1"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_1"));
//			caratula.at109_2 = cursor.getString(cursor.getColumnIndex("AT109_2"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_2"));
//			caratula.at109_3 = cursor.getString(cursor.getColumnIndex("AT109_3"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_3"));
//			caratula.at109_4 = cursor.getString(cursor.getColumnIndex("AT109_4"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_4"));
//			caratula.at109_5 = cursor.getString(cursor.getColumnIndex("AT109_5"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_5"));
//			caratula.at109_6 = cursor.getString(cursor.getColumnIndex("AT109_6"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_6"));
//			caratula.at109_7 = cursor.getString(cursor.getColumnIndex("AT109_7"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_7"));
//			caratula.at109_8 = cursor.getString(cursor.getColumnIndex("AT109_8"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_8"));
//			caratula.at109_9 = cursor.getString(cursor.getColumnIndex("AT109_9"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_9"));
//			caratula.at109_10 = cursor.getString(cursor.getColumnIndex("AT109_10"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_10"));
//			caratula.at109_11 = cursor.getString(cursor.getColumnIndex("AT109_11"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_11"));
//			caratula.at109_12 = cursor.getString(cursor.getColumnIndex("AT109_12"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_12"));
//			caratula.at109_13 = cursor.getString(cursor.getColumnIndex("AT109_13"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_13"));
//			caratula.at109_14 = cursor.getString(cursor.getColumnIndex("AT109_14"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_14"));
//			caratula.at109_15 = cursor.getString(cursor.getColumnIndex("AT109_15"))==null?null:cursor.getInt(cursor.getColumnIndex("AT109_15"));
//			caratula.at109_15_o = cursor.getString(cursor.getColumnIndex("AT109_15_O"));
//			caratula.obs_100 = cursor.getString(cursor.getColumnIndex("OBS_100"));
//			caratula.cusu_up = cursor.getString(cursor.getColumnIndex("CUSU_UP"));
//			caratula.fec_up = cursor.getString(cursor.getColumnIndex("FEC_UP"));
//			caratula.hor_up = cursor.getString(cursor.getColumnIndex("HOR_UP"));
//			caratula.cusu_in = cursor.getString(cursor.getColumnIndex("CUSU_IN"));
//			caratula.fec_in = cursor.getString(cursor.getColumnIndex("FEC_IN"));
//			caratula.hor_in = cursor.getString(cursor.getColumnIndex("HOR_IN"));
//			caratula.at_cap200 = cursor.getString(cursor.getColumnIndex("AT_CAP200"))==null?null:cursor.getInt(cursor.getColumnIndex("AT_CAP200"));
//
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<ATCap200BE> fillATCapitulo200s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<ATCap200BE> caratulas = new ArrayList<ATCap200BE>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_02_200 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			ATCap200BE data = new ATCap200BE();
//
//			List<String> allFieldMatches = data.getFieldMatches(201, 207, "ID_N", "ID_AT", "ID_PERSONA", 
//					"IP200N", "USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
//			data = (ATCap200BE)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			
//			caratulas.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<ATCap200BE> fillATCapitulo200s(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<ATCap200BE> caratulas = new ArrayList<ATCap200BE>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_02_200 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			ATCap200BE caratula = new ATCap200BE();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.id_at = cursor.getString(cursor.getColumnIndex("ID_AT"))==null?null:cursor.getInt(cursor.getColumnIndex("ID_AT"));
//			caratula.id_persona = cursor.getString(cursor.getColumnIndex("ID_PERSONA"))==null?null:cursor.getInt(cursor.getColumnIndex("ID_PERSONA"));
//			caratula.ip200n = cursor.getString(cursor.getColumnIndex("IP200N"))==null?null:cursor.getInt(cursor.getColumnIndex("IP200N"));
//			caratula.ip201 = cursor.getString(cursor.getColumnIndex("IP201"))==null?null:cursor.getInt(cursor.getColumnIndex("IP201"));
//			caratula.ip201_o = cursor.getString(cursor.getColumnIndex("IP201_O"));
//			caratula.ip202 = cursor.getString(cursor.getColumnIndex("IP202"))==null?null:cursor.getInt(cursor.getColumnIndex("IP202"));
//			caratula.ip203 = cursor.getString(cursor.getColumnIndex("IP203"));
//			caratula.ip203_ni = cursor.getString(cursor.getColumnIndex("IP203_NI"))==null?null:cursor.getInt(cursor.getColumnIndex("IP203_NI"));
//			caratula.ip204 = cursor.getString(cursor.getColumnIndex("IP204"))==null?null:cursor.getInt(cursor.getColumnIndex("IP204"));
//			caratula.ip205 = cursor.getString(cursor.getColumnIndex("IP205"))==null?null:cursor.getInt(cursor.getColumnIndex("IP205"));
//			caratula.ip205a = cursor.getString(cursor.getColumnIndex("IP205A"))==null?null:cursor.getInt(cursor.getColumnIndex("IP205A"));
//			caratula.ip206 = cursor.getString(cursor.getColumnIndex("IP206"))==null?null:cursor.getInt(cursor.getColumnIndex("IP206"));
//			caratula.ip206_11_o = cursor.getString(cursor.getColumnIndex("IP206_11_O"));
//			caratula.ip206_17_o = cursor.getString(cursor.getColumnIndex("IP206_17_O"));
//			caratula.ip206_o = cursor.getString(cursor.getColumnIndex("IP206_O"));
//			caratula.ip207 = cursor.getString(cursor.getColumnIndex("IP207"))==null?null:cursor.getInt(cursor.getColumnIndex("IP207"));
//			
//			caratula.cusu_up = cursor.getString(cursor.getColumnIndex("CUSU_UP"));
//			caratula.fec_up = cursor.getString(cursor.getColumnIndex("FEC_UP"));
//			caratula.hor_up = cursor.getString(cursor.getColumnIndex("HOR_UP"));
//			caratula.cusu_in = cursor.getString(cursor.getColumnIndex("CUSU_IN"));
//			caratula.fec_in = cursor.getString(cursor.getColumnIndex("FEC_IN"));
//			caratula.hor_in = cursor.getString(cursor.getColumnIndex("HOR_IN"));
//
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<CaratulaDelitos> fill_Caratula_Del(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<CaratulaDelitos> caratula_del = new ArrayList<CaratulaDelitos>();
//
//		Cursor cursor = dbr.rawQuery("SELECT * FROM CARATULA WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			CaratulaDelitos data = new CaratulaDelitos();
//			List<String> allFieldMatches = data.getFieldMatches(-1, -1, "ID_N", "ANIO", "II1", "II2", "II3", "II4", "II5", "II6",
//					"II7", "II8", "II8_O", "II9", "II9_O", "IV2_1_D", "IV2_1_M", "IV_2_1_A", "IVRESFIN_02", "IVRESFIN_02_O",
//					"V3_1", "V3_2", "V3_3", "V3_4","VI1A", "VI2A", "VI2B", "VI1B", "VI3A", "VI3B", "VII1A", "VII1B", "VII1C", "VII1D",
//					"VII3_1D_NT", "VII1E", "VII3_1E_NT", "VII1F", "VII3_1F_NT", "VII2A", "VII2B", "VII2C", "VII2D", "VII3_2D_NT",
//					"VII2E", "VII3_2E_NT", "VII2F", "VII3_2F_NT", "OBS_CAR", "TOTAL_DENUNCIAS", "CONTE_REG200", "CONTE_REG300",
//					"CONTE_REG400", "USR_CREACION", "FEC_CREACION", "USR_EDICION", "FEC_EDICION", "CERRADO");
//			data = (CaratulaDelitos)data.fillEntity(cursor, allFieldMatches);
//			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			data.fecha_recepcion = cursor.getString(cursor.getColumnIndex("fecha_recepcion"));
//			
//			caratula_del.add(data);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return caratula_del;
//	}
	
//	public ArrayList<DelVisita> fill_CaratulaVisit_Del(String id_n){
////		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<DelVisita> visit_del = new ArrayList<DelVisita>();
//
////		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_03_VISITA WHERE ID_N=?", new String[]{id_n});
////		
////		while(cursor.moveToNext()){
////			DelVisita data = new DelVisita();
////
////			List<String> allFieldMatches = data.getFieldMatches(-1, -1, "ID_N", "III2_1", "III2_2_D", "III2_2_M", "III2_2_A",
////					"III2_3A_IH", "III2_3A_IM", "GPSLATITUD_INI", "GPSLONGITUD_INI", "III2_3B_FH", "III2_3B_FM", "GPSLATITUD_FIN",
////					"GPSLONGITUD_FIN", "III2_4A_D", "III2_4A_M", "III2_4A_A", "III2_4B_H","III2_4B_M", "III2_5", "III2_5_O", "III2_6_D", "III2_6_M",
////					"III2_6_A", "III2_7A_H", "III2_7A_M", "III2_7B_H", "III2_7B_M", "III2_8", "III2_8_O", "USUCRE", "FECCRE","USUREG","FECREG","FECENV");
////			data = (DelVisita)data.fillEntity(cursor, allFieldMatches);
////			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//////			data.fecha_recepcion = cursor.getString(cursor.getColumnIndex("fecha_recepcion"));
////			
////			visit_del.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
////		dbr.close();
////		dbr = null;
////		SQLiteDatabase.releaseMemory();
//		return visit_del;
//	}
	
//	public ArrayList<Cap100Delitos> fill_Cap100_Del(String id_n){
////		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Cap100Delitos> cap100_del = new ArrayList<Cap100Delitos>();
//
////		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_03_100 WHERE ID_N=?", new String[]{id_n});
////		
////		while(cursor.moveToNext()){
////			Cap100Delitos data = new Cap100Delitos();
////
////			List<String> allFieldMatches = data.getFieldMatches(101, 121, "ID_N", "SUM_FALLECIDOS", "OBS_03_100",
////					"TOTAL_DENUNCIAS", "CONTE_REG200", "SUM_IH213", "SUM_IH214",
////					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
////			data = (Cap100Delitos)data.fillEntity(cursor, allFieldMatches);
////			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
////			
////			cap100_del.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
////		dbr.close();
////		dbr = null;
////		SQLiteDatabase.releaseMemory();
//		return cap100_del;
//	}
	
//	public ArrayList<Cap100Delitos> fill_Cap100_Del(String id_n, List<String> fields){
////		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Cap100Delitos> cap100_del = new ArrayList<Cap100Delitos>();
//		
////		Utilidades.FieldsEntity fe = Utilidades.getListFieldsEntity(new Cap100Delitos(), fields, true);
////
////		Cursor cursor = dbr.rawQuery("SELECT "+fe.fields+" FROM T_DIG_03_100 WHERE ID_N=?", new String[]{id_n});
////		
////		while(cursor.moveToNext()){
////			Cap100Delitos data = new Cap100Delitos();
////
//////			List<String> allFieldMatches = data.getFieldMatches(101, 121, "ID_N", "SUM_FALLECIDOS", "OBS_03_100",
//////					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
////			data = (Cap100Delitos)data.fillEntity(cursor, /*allFieldMatches*/ fe.lstfields);
////			data.id_n = id_n;
////			
////			cap100_del.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
////		dbr.close();
////		dbr = null;
////		SQLiteDatabase.releaseMemory();
//		return cap100_del;
//	}
	public Cap100Delitos getC100(INF_Caratula01 caratula, SeccionCapitulo... secciones) {
		Cap100Delitos bean = (Cap100Delitos) getBean(
				TABLA_C100,
				"ID_N=?",
				new String[] { caratula.id_n },
				Cap100Delitos.class, secciones);
		return bean;
	}
	
	public String getDuplicidadesC300(int opcion, Integer nro_mreg, String... valores){
		String mreg = nro_mreg == null ? "" : "OR NRO_MREG = "+nro_mreg+" ";
		String where = "";
		switch (opcion) {
			case 1: where = "TRIM(IVH301A)||' '||TRIM(IVH301B)||' '||TRIM(IVH301C)"; break;
			case 2: where = "TRIM(IVH302_N)"; break;
			default:break;
		}
		try {
			if(opcion == 1 && valores[1].equals("NEP NEP NEP")) return null;
		} catch (Exception e) {}
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		boolean _res = existeRegistro("NRO_MREG", selDuplicados(TABLA_C300), "ID_N=? AND "
//				+ "("+where+" = ? AND (IH208 BETWEEN 1 AND 23 "+mreg+") "
//				+ "AND NRO_MREG||' '||NRO_VFREG != ?)", valores);
		
		String _res = existeRegistro(new String[]{"NRO_MREG", "NRO_VFREG"}, selDuplicados(TABLA_C300), "ID_N=? AND "
				+ "("+where+" = ? AND (IFNULL(IH208_COD,IH208) BETWEEN 1 AND 23 "+mreg+") "
				+ "AND NRO_MREG||' '||NRO_VFREG != ?)", valores);
		
		Log.e("_res", "_res: "+_res);
		
//		dbr.close();
//		dbr = null;
		SQLiteDatabase.releaseMemory();
		return _res;
	}
	
	public String getDuplicidadesC400(int opcion, String... valores){
		String where = "";
		switch (opcion) {
			case 1: where = "TRIM(IVM401A)||' '||TRIM(IVM401B)||' '||TRIM(IVM401C)"; break;
			case 2: where = "TRIM(IVM402_N)"; break;
			default:break;
		}
		try {
			if(opcion == 1 && valores[1].equals("NEP NEP NEP")) return null;
		} catch (Exception e) {}
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		boolean _res = existeRegistro("NRO_MREG", TABLA_C400, "ID_N=? AND "
//				+ "("+where+" = ? "
//				+ "AND NRO_MREG = ? AND NRO_PVREG != ?)", valores);
		
		String _res = existeRegistro(new String[]{"NRO_MREG", "NRO_PVREG"}, TABLA_C400, "ID_N=? AND "
				+ "("+where+" = ? "
				+ "AND NRO_MREG = ? AND NRO_PVREG != ?)", valores);
		
		Log.e("_res", "_res: "+_res);
		
//		dbr.close();
//		dbr = null;
		SQLiteDatabase.releaseMemory();
		return _res;
	}
	
	private String selDuplicados(String table){
		String sel = "(SELECT c.*, d.IH208 FROM "+table+" c "
				+ "INNER JOIN (SELECT ID_N, NRO_MREG, IFNULL(IH208_COD,IH208) AS IH208 FROM "+TABLA_C200+") d "
				+ "ON d.ID_N = c.ID_N AND d.NRO_MREG = c.NRO_MREG)";
		return sel;
	}
	
	public boolean getConteoDenunVict(String id_n, Integer nro_mreg, String where300, String op){
		String where = "";
		if(nro_mreg != null){
			if(!nro_mreg.equals(-1))  where = " AND NRO_MREG="+nro_mreg.toString()+" ";
		}
		if(where300 == null) where300 = "";
		
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		
		Cursor cursor = dbr.rawQuery("SELECT CASE WHEN CVICT "+ op +" SUMDEN THEN 0 ELSE 1 END AS RESULT FROM ( "
				+ "SELECT SUM(CVICT) CVICT, SUM(SUMDEN) AS SUMDEN FROM ( "
				+ "SELECT COUNT(ID_N) AS CVICT, 0 AS SUMDEN FROM "+TABLA_C300+" "
				+ "WHERE ID_N = ? "+where+" "+where300+" "
				+ "UNION "
				+ "SELECT 0 AS CVICT,  SUM(IH214) AS SUMDEC FROM "+TABLA_C200+" "
				+ "WHERE ID_N = ? "+where
				+ "))", new String[]{id_n, id_n});
		
		int result = 0;
		while(cursor.moveToNext()){
			result = cursor.getInt(cursor.getColumnIndex("RESULT"));
		}
		
		Log.e("cheakk", "sdsdsds: "+result);
		
		cursor.close();
		cursor = null;
		dbr.close();
		dbr = null;
		SQLiteDatabase.releaseMemory();
		return result == 0;
	}
	
	public Integer getConteoVictimas(String id_n, Integer nro_mreg, String table){
		String where = nro_mreg == null ? "" : " AND NRO_MREG = "+nro_mreg.toString();
		return getConteoVictimas(id_n, nro_mreg, table, where);
	}
	
	public Integer getConteoVictimas(String id_n, Integer nro_mreg, String table, String where){
		return getConteoVictimas(id_n, nro_mreg, "COUNT(ID_N)", table, where);
	}
	
	public Integer getConteoVictimas(String id_n, Integer nro_mreg, String campo, String table, String where){
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		
		Cursor cursor = dbr.rawQuery("SELECT "+campo+" AS CVICT FROM "+table+" "
				+ "WHERE ID_N = ? "+where, new String[]{id_n});
		
		Integer result = 0;
		while(cursor.moveToNext()){
			result = cursor.getInt(cursor.getColumnIndex("CVICT"));
		}
		
		cursor.close();
		cursor = null;
		dbr.close();
		dbr = null;
		SQLiteDatabase.releaseMemory();
		return result;
	}
	
	public Integer getConteoVictimasWhere(String id_n, Integer nro_mreg, String table, String whereIn){
		String where = nro_mreg == null ? "" : " AND D.NRO_MREG = "+nro_mreg.toString();
		whereIn = whereIn == null ? "" : " AND D.IH208 IN ("+whereIn+")";
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		
		Cursor cursor = dbr.rawQuery("SELECT COUNT(D.ID_N) AS CVICT FROM "+table+" C "
				+ "INNER JOIN (SELECT ID_N, NRO_MREG, IFNULL(IH208_COD,IH208) AS IH208 FROM "+TABLA_C200+") D ON (D.[ID_N] = C.[ID_N]"
				+ "AND D.[NRO_MREG] = C.[NRO_MREG]) "
				+ "WHERE D.ID_N = ?"+where+whereIn, new String[]{id_n});
		
		Integer result = 0;
		while(cursor.moveToNext()){
			result = cursor.getInt(cursor.getColumnIndex("CVICT"));
		}
		
		cursor.close();
		cursor = null;
		dbr.close();
		dbr = null;
		SQLiteDatabase.releaseMemory();
		return result;
	}
	
	public boolean getConteoDenunVictimario(String id_n, Integer nro_mreg, String where400, String op){
		String where = "";
		if(nro_mreg != null){
			if(!nro_mreg.equals(-1))  where = " AND NRO_MREG="+nro_mreg.toString()+" ";
		}
		if(where400 == null) where400 = "";
		
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		Cursor cursor = dbr.rawQuery("SELECT CASE WHEN CVICT "+ op +" SUMDEN THEN 0 ELSE 1 END AS RESULT FROM ( "
				+ "SELECT SUM(CVICT) CVICT, SUM(SUMDEN) AS SUMDEN FROM ( "
				+ "SELECT COUNT(ID_N) AS CVICT, 0 AS SUMDEN FROM "+TABLA_C400+" "
				+ "WHERE ID_N = ? "+where+" "+where400+" "
				+ "UNION "
				+ "SELECT 0 AS CVICT,  SUM(IH215) AS SUMDEC FROM "+TABLA_C200+" "
				+ "WHERE ID_N = ? "+where
				+ "))", new String[]{id_n, id_n});
		
		int result = 0;
		while(cursor.moveToNext()){
			result = cursor.getInt(cursor.getColumnIndex("RESULT"));
		}
		
		cursor.close();
		cursor = null;
		dbr.close();
		dbr = null;
		SQLiteDatabase.releaseMemory();
		return result == 0;
	}
	
	public List<C100udt> getCountC200xP208(String id_n) {
		StringBuilder query = new StringBuilder();		
		query.append("SELECT IFNULL(IH208_COD,IH208) AS COD, COUNT(ID_N) AS VALUE").append(" ")
			.append("FROM ").append(TABLA_C200).append(" ")
			.append("WHERE ID_N= '").append(id_n).append("'").append(" ")
			.append("GROUP BY ID_N, IFNULL(IH208_COD,IH208)").append(" ");
		return getBeans(new Query(query.toString()), C100udt.class, "COD", "VALUE");
	}
	
	public boolean updateC100(String id_n, List<C100udt> c200s){
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		for (C100udt c:c200s) {
//			Log.e("graba", "graba: "+c.field);
			dbr.execSQL("UPDATE "+TABLA_C100+" "
					+ "SET "+c.field+" = "+c.value+" "
					+ "WHERE ID_N = '"+id_n+"' ");
		}
		dbr.close();
		dbr = null;
		SQLiteDatabase.releaseMemory();
		return true;
	}
	
	private String getBordeCheckCap300and400(String id_n, boolean borde){
		if(!borde) return "";
		return "LEFT JOIN ("
				+ "SELECT A.ID_N, A.NRO_MREG, 1 AS BORDE FROM "+TABLA_C200+" A "
				+ "LEFT JOIN (SELECT ID_N, NRO_MREG, NRO_VFREG, IVH304, IVH306_ANIO, IVH307, IVH308, IVH309, IVH310 FROM "+TABLA_C300+" "
				+ "GROUP BY ID_N, NRO_MREG) B ON A.ID_N=B.ID_N AND A.NRO_MREG=B.NRO_MREG "
				+ "LEFT JOIN (SELECT ID_N, NRO_MREG, NRO_PVREG, IVM408 FROM "+TABLA_C400+" "
				+ "GROUP BY ID_N, NRO_MREG) C ON A.ID_N=C.ID_N AND A.NRO_MREG=C.NRO_MREG "
				+ "WHERE A.ID_N = '"+id_n+"' AND B.ID_N IS NOT NULL "
				
				+" AND IVH304 IS NOT NULL " 
				+" AND ((IH215 IS NOT NULL " 
				+"   AND ((IVH304 = 1 AND IVH306_ANIO IS NOT NULL AND IVH310 IS NOT NULL) "
				+" 		OR (IVH304 = 2 AND IVH307 IS NOT NULL AND IVH310 IS NOT NULL) "
				+" 		OR (IVH304 IN (3,4) AND IVH307 IS NOT NULL AND IVH310 IS NOT NULL)) "
				+" ) OR (IH215 IS NULL " 
				+" 	 AND ((IVH304 = 1 AND IVH306_ANIO IS NOT NULL) "
				+" 		OR (IVH304 = 2 AND IVH307 IS NOT NULL) "
				+" 		OR (IVH304 IN (3,4) AND IVH308 IS NOT NULL AND IVH309 IS NOT NULL))"
				+" )) "
		        + "AND IFNULL(C.IVM408,-1) = CASE WHEN A.IH215 IS NOT NULL THEN C.IVM408 ELSE -1 END "
				+ "AND IFNULL(A.CONTE_REG300,0) = IFNULL(A.IH214,0) "
				+ "AND IFNULL(A.CONTE_REG400,0) = IFNULL(A.IH215,0) "

				+ ") E ON D.ID_N = E.ID_N AND D.NRO_MREG=E.NRO_MREG ";
	}
	
//	public ArrayList<Cap200Delitos> fill_Cap200_Del(String id_n, boolean borde){
////		String strBorde = getBordeCheckCap300and400(id_n, borde);
////		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Cap200Delitos> cap200_del = new ArrayList<Cap200Delitos>();
//
////		Cursor cursor = dbr.rawQuery("SELECT D.*"+(borde?", E.BORDE":"")+" FROM T_DIG_03_200 D "+strBorde
////				+ " WHERE D.ID_N=?", new String[]{id_n});
////		
////		while(cursor.moveToNext()){
////			Cap200Delitos data = new Cap200Delitos();
////
////			List<String> allFieldMatches = data.getFieldMatches(201, 214, "ID_N", "NRO_MREG", "ORDEN_200",
////					"OBS_03_200", "CONTE_REG300", "CONTE_REG400", 
////					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
////			data = (Cap200Delitos)data.fillEntity(cursor, allFieldMatches);
////			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
////			if(borde) data.borde = cursor.getString(cursor.getColumnIndex("BORDE"));
////			cap200_del.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
////		dbr.close();
////		dbr = null;
////		SQLiteDatabase.releaseMemory();
//		return cap200_del;
//	}
	public List<Cap200Delitos> getC200s(INF_Caratula01 bean, boolean borde, SeccionCapitulo... secciones) {
//		Log.e("entras a la consulta", "entrastes a la consulta");
		String strBorde = getBordeCheckCap300and400(bean.id_n, borde);
		List<Cap200Delitos> cc = getBeans("(SELECT D.*, M.NOMBRE AS P208NOM"+(borde?", E.BORDE":"")+" FROM "+TABLA_C200+" D "
				+ "LEFT JOIN (SELECT * FROM "+TABLA_MODALIDADES+" WHERE VALOR=2) M ON M.CODIGO = D.IH208_COD "+strBorde+")", 
				"ID_N=?",
				new String[] { bean.id_n },
				"NRO_MREG", Cap200Delitos.class, secciones);
//		Log.e("terminastesss a la consulta", "terminastessss a la consulta");
		return cc;
	}
	public Cap200Delitos getC200(INF_Caratula01 caratula, Integer nro_mreg, SeccionCapitulo... secciones) {
		Cap200Delitos bean = (Cap200Delitos) getBean(
				TABLA_C200,
				"ID_N=? AND NRO_MREG=?",
				new String[] { caratula.id_n, nro_mreg.toString() },
				Cap200Delitos.class, secciones);
		return bean;
	}
	
//	public ArrayList<Cap200Delitos> fill_Cap200_Del(String id_n, List<String> fields){
////		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Cap200Delitos> cap200_del = new ArrayList<Cap200Delitos>();
//		
////		Utilidades.FieldsEntity fe = Utilidades.getListFieldsEntity(new Cap200Delitos(), fields, true);
////
////		Cursor cursor = dbr.rawQuery("SELECT "+fe.fields+" FROM T_DIG_03_200 WHERE ID_N=?", new String[]{id_n});
////		
////		while(cursor.moveToNext()){
////			Cap200Delitos data = new Cap200Delitos();
////
//////			List<String> allFieldMatches = data.getFieldMatches(101, 121, "ID_N", "SUM_FALLECIDOS", "OBS_03_100",
//////					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
////			data = (Cap200Delitos)data.fillEntity(cursor, /*allFieldMatches*/ fe.lstfields);
////			data.id_n = id_n;
////			
////			cap200_del.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
////		dbr.close();
////		dbr = null;
////		SQLiteDatabase.releaseMemory();
//		return cap200_del;
//	}
//	
//	public ArrayList<Cap200Delitos> fill_Cap200_Del(String id_n, Integer nro_mreg, List<String> fields){
////		String where = nro_mreg==null?"":"AND NRO_MREG = "+nro_mreg.toString();
////		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Cap200Delitos> cap200_del = new ArrayList<Cap200Delitos>();
//		
////		Utilidades.FieldsEntity fe = Utilidades.getListFieldsEntity(new Cap200Delitos(), fields, true);
////
////		Cursor cursor = dbr.rawQuery("SELECT "+fe.fields+" FROM T_DIG_03_200 WHERE ID_N=? "+where, 
////				new String[]{id_n});
////		
////		while(cursor.moveToNext()){
////			Cap200Delitos data = new Cap200Delitos();
////
//////			List<String> allFieldMatches = data.getFieldMatches(101, 121, "ID_N", "SUM_FALLECIDOS", "OBS_03_100",
//////					"USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
////			data = (Cap200Delitos)data.fillEntity(cursor, /*allFieldMatches*/ fe.lstfields);
////			data.id_n = id_n;
////			
////			cap200_del.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
////		dbr.close();
////		dbr = null;
////		SQLiteDatabase.releaseMemory();
//		return cap200_del;
//	}
	
//	public ArrayList<Cap300Delitos> fill_Cap300_Del(String id_n){
////		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Cap300Delitos> cap300_del = new ArrayList<Cap300Delitos>();
//
////		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_03_300 WHERE ID_N=?", new String[]{id_n});
////		
////		while(cursor.moveToNext()){
////			Cap300Delitos data = new Cap300Delitos();
////
////			List<String> allFieldMatches = data.getFieldMatches(301, 309, "ID_N", "NRO_VFREG", "NRO_MREG",
////					"ORDEN_300", "OBS_03_300", "USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
////			data = (Cap300Delitos)data.fillEntity(cursor, allFieldMatches);
////			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
////			
////			cap300_del.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
////		dbr.close();
////		dbr = null;
////		SQLiteDatabase.releaseMemory();
//		return cap300_del;
//	}
	
//	public ArrayList<Cap300Delitos> fill_Cap300_Del(String id_n, Integer nro_mreg){
////		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Cap300Delitos> cap300_del = new ArrayList<Cap300Delitos>();
//
////		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_03_300 WHERE ID_N=? AND NRO_MREG=?", 
////				new String[]{id_n, nro_mreg.toString()});
////		
////		while(cursor.moveToNext()){
////			Cap300Delitos data = new Cap300Delitos();
////
////			List<String> allFieldMatches = data.getFieldMatches(301, 309, "ID_N", "NRO_VFREG", "NRO_MREG",
////					"ORDEN_300", "OBS_03_300", "USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
////			data = (Cap300Delitos)data.fillEntity(cursor, allFieldMatches);
////			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
////			
////			cap300_del.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
////		dbr.close();
////		dbr = null;
////		SQLiteDatabase.releaseMemory();
//		return cap300_del;
//	}
	public List<Cap300Delitos> getC300s(String id_n, Integer nro_mreg, SeccionCapitulo... secciones) {
		return getBeans(TABLA_C300, 
				"ID_N=? AND NRO_MREG=?",
				new String[] { id_n, nro_mreg.toString() },
				"NRO_VFREG", Cap300Delitos.class, secciones);
	}
	
//	public ArrayList<Cap400Delitos> fill_Cap400_Del(String id_n){
////		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Cap400Delitos> cap400_del = new ArrayList<Cap400Delitos>();
//
////		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_03_400 WHERE ID_N=?", new String[]{id_n});
////		
////		while(cursor.moveToNext()){
////			Cap400Delitos data = new Cap400Delitos();
////
////			List<String> allFieldMatches = data.getFieldMatches(401, 408, "ID_N", "NRO_PVREG", "NRO_MREG",
////					"ORDEN_400", "OBS_03_400", "USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
////			data = (Cap400Delitos)data.fillEntity(cursor, allFieldMatches);
////			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
////			
////			cap400_del.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
////		dbr.close();
////		dbr = null;
////		SQLiteDatabase.releaseMemory();
//		return cap400_del;
//	}
	
//	public ArrayList<Cap400Delitos> fill_Cap400_Del(String id_n, Integer nro_mreg){
////		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Cap400Delitos> cap400_del = new ArrayList<Cap400Delitos>();
//
////		Cursor cursor = dbr.rawQuery("SELECT * FROM T_DIG_03_400 WHERE ID_N=? AND NRO_MREG=?", 
////				new String[]{id_n, nro_mreg.toString()});
////		
////		while(cursor.moveToNext()){
////			Cap400Delitos data = new Cap400Delitos();
////
////			List<String> allFieldMatches = data.getFieldMatches(401, 408, "ID_N", "NRO_MREG", "NRO_PVREG",
////					"ORDEN_400", "OBS_03_400", "USUCRE", "FECCRE", "USUREG", "FECREG", "FECENV");
////			data = (Cap400Delitos)data.fillEntity(cursor, allFieldMatches);
////			data.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
////			
////			cap400_del.add(data);
////		}
////		
////		cursor.close();
////		cursor = null;
////		dbr.close();
////		dbr = null;
////		SQLiteDatabase.releaseMemory();
//		return cap400_del;
//	}
	public List<Cap400Delitos> getC400s(String id_n, Integer nro_mreg, SeccionCapitulo... secciones) {
		return getBeans(TABLA_C400, 
				"ID_N=? AND NRO_MREG=?",
				new String[] { id_n, nro_mreg.toString() },
				"NRO_PVREG", Cap400Delitos.class, secciones);
	}
	
	
	public boolean eliminarCuestionarios(String id_n){
		SQLiteDatabase dbw = dbh.getWritableDatabase();
		int i = 0;
		
		//PARA DELITOS
		i += dbw.delete(TABLA_C400, "ID_N=?", new String[]{id_n});
		i += dbw.delete(TABLA_C300, "ID_N=?", new String[]{id_n});
		i += dbw.delete(TABLA_C200, "ID_N=?", new String[]{id_n});
		i += dbw.delete(TABLA_C100, "ID_N=?", new String[]{id_n});
		i += dbw.delete(TABLA_VISITA, "ID_N=?", new String[]{id_n});

		i += dbw.delete(TABLA_CARATULA, "ID_N=?", new String[]{id_n});
		dbw.close();
		
		return i > 0;
	}
	
//	public boolean eliminarMarco(){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		int i = 0;
//		i += dbw.delete("T_MARCO", null, null);
//		dbw.close();
//		
//		return i > 0;
//	}
	
//	public boolean eliminarUsuarios(){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		int i = 0;
//		i += dbw.delete("T_USUARIO", "ID != ?", new String[]{"0"});
//		dbw.close();
//		
//		return i > 0;
//	}
	
//	public boolean eliminarSegmentaciones(){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		int i = 0;
//		i += dbw.delete("T_SEGMENTACION", "ID != ?", new String[]{"0"});
//		dbw.close();
//		
//		return i > 0;
//	}
	
//	public boolean saveMarco(List<Marco> marco, CounterObservable contadorObserver){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result=false;
//		for( int k=0; k<marco.size(); k++ ){
//			ContentValues content = new ContentValues();
//
//		content.put("ID_N", marco.get(k).id_n);
//		content.put("NOMCOMISARIA", marco.get(k).nomcomisaria);
//		content.put("COD_REGION", marco.get(k).cod_region);
//		content.put("DIRTEPOL", marco.get(k).dirtepol);
//		content.put("CAPIREFERENCIA", marco.get(k).capireferencia);
//		content.put("TIPO", marco.get(k).tipo);
//		content.put("DIRECCION", marco.get(k).direccion);
//		content.put("COMISARIO", marco.get(k).comisario);
//		content.put("TELEFONO1", marco.get(k).telefono1);
//		content.put("TELEFONO2", marco.get(k).telefono2);
//		content.put("TIPOCPNP", marco.get(k).tipocpnp);
//		content.put("CCDD", marco.get(k).ccdd);
//		content.put("CCPP", marco.get(k).ccpp);
//		content.put("CCDI", marco.get(k).ccdi);
//		content.put("CCCP", marco.get(k).cccp);
//		content.put("UBIGEO", marco.get(k).ubigeo);
//		content.put("REGION", marco.get(k).region);
//		content.put("NOMBRECP", marco.get(k).nombrecp);
//		content.put("AREA", marco.get(k).area);
//		content.put("ZONA", marco.get(k).zona);
//		content.put("ZONALF", marco.get(k).zonalf);
//		content.put("MZA", marco.get(k).mza);
//		content.put("MZNALF", marco.get(k).mznalf);
//		content.put("AER_INI", marco.get(k).aer_ini);
//		content.put("AER_FIN", marco.get(k).aer_fin);
////		content.put("FRENTE", marco.get(k).frente);
//		content.put("VDELI", marco.get(k).vdeli);
//		/*content.put("USUREG", marco.usureg);*/
//		content.put("AGREGADO", marco.get(k).agregado);
//		content.put("PERIODO", marco.get(k).periodo);
//		/*content.put("FECREG", marco.fecreg);
//		content.put("PERIODO", marco.periodo);*/
//		
//		result = dbw.insert("T_MARCO", null, content)!=-1;
//		if (contadorObserver != null) {
//			contadorObserver.insertado(1, marco.size());
//		}
//		}
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
	public static class CounterObservable extends Observable {
		private int cantidadInsertado;
		private String name;

		public CounterObservable() {
			this(0);
		}

		public CounterObservable(int cantidadAvanzado) {
			this(cantidadAvanzado, null);
		}
		
		public CounterObservable(int cantidadAvanzado, String name) {
			cantidadInsertado = cantidadAvanzado;
			this.name = name;
		}
		
		private void insertado(int cantidad, int total) {
			cantidadInsertado += 1;
			setChanged();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("NAME", name);
			map.put("INSERTADO", cantidadInsertado);
			map.put("TOTAL", total);
			notifyObservers(map);
		}

		private void insertado(int cantidad) {
			cantidadInsertado += 1;
			setChanged();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("INSERTADO", cantidadInsertado);
			notifyObservers(map);
		}
	}
	
	
//	public boolean saveMarco(Marco marco){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", marco.id_n);
//		content.put("NOMCOMISARIA", marco.nomcomisaria);
//		content.put("COD_REGION", marco.cod_region);
//		content.put("DIRTEPOL", marco.dirtepol);
//		content.put("CAPIREFERENCIA", marco.capireferencia);
//		content.put("TIPO", marco.tipo);
//		content.put("DIRECCION", marco.direccion);
//		content.put("COMISARIO", marco.comisario);
//		content.put("TELEFONO1", marco.telefono1);
//		content.put("TELEFONO2", marco.telefono2);
//		content.put("TIPOCPNP", marco.tipocpnp);
//		content.put("CCDD", marco.ccdd);
//		content.put("CCPP", marco.ccpp);
//		content.put("CCDI", marco.ccdi);
//		content.put("CCCP", marco.cccp);
//		content.put("UBIGEO", marco.ubigeo);
//		content.put("REGION", marco.region);
//		content.put("NOMBRECP", marco.nombrecp);
//		content.put("AREA", marco.area);
//		content.put("ZONA", marco.zona);
//		content.put("ZONALF", marco.zonalf);
//		content.put("MZA", marco.mza);
//		content.put("MZNALF", marco.mznalf);
//		content.put("AER_INI", marco.aer_ini);
//		content.put("AER_FIN", marco.aer_fin);
////		content.put("FRENTE", marco.frente);
//		/*content.put("USUREG", marco.usureg);*/
//		content.put("AGREGADO", marco.agregado);
//		content.put("PERIODO", marco.periodo);
//		/*content.put("FECREG", marco.fecreg);
//		content.put("PERIODO", marco.periodo);*/
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_MARCO", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveUsuario(Usuario usuario){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID", usuario.id);
//		content.put("USUARIO", usuario.usuario);
//		content.put("CLAVE", usuario.clave);
//		content.put("CARGO_ID", usuario.cargo_id);
//		content.put("TELEFONO", usuario.telefono);
//		content.put("EMAIL", usuario.email);
//		content.put("ESTADO", usuario.estado);
//		content.put("NOMBRES", usuario.nombres);
//		content.put("APELLIDOS", usuario.apellidos);
//		content.put("CORREO", usuario.correo);
//		content.put("DNI", usuario.dni);
//		content.put("COD_SEDE_OPERATIVA", usuario.cod_sede_operativa);
//		content.put("CCDD", usuario.ccdd);
//		content.put("CCPP", usuario.ccpp);
//		content.put("CCDI", usuario.ccdi);
//		content.put("EQUIPO", usuario.equipo);
//		content.put("RUTA", usuario.ruta);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_USUARIO", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveUsuario(List<Usuario> usuario, CounterObservable contadorObserver){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result=false;
//		for( int k=0; k<usuario.size(); k++ ){
//			ContentValues content = new ContentValues();
//			
//		content.put("ID", usuario.get(k).id);
//		content.put("USUARIO", usuario.get(k).usuario);
//		content.put("CLAVE", usuario.get(k).clave);
//		content.put("CARGO_ID", usuario.get(k).cargo_id);
//		content.put("TELEFONO", usuario.get(k).telefono);
//		content.put("EMAIL", usuario.get(k).email);
//		content.put("ESTADO", usuario.get(k).estado);
//		content.put("NOMBRES", usuario.get(k).nombres);
//		content.put("APELLIDOS", usuario.get(k).apellidos);
//		content.put("CORREO", usuario.get(k).correo);
//		content.put("DNI", usuario.get(k).dni);
//		content.put("COD_SEDE_OPERATIVA", usuario.get(k).cod_sede_operativa);
//		content.put("CCDD", usuario.get(k).ccdd);
//		content.put("CCPP", usuario.get(k).ccpp);
//		content.put("CCDI", usuario.get(k).ccdi);
//		content.put("EQUIPO", usuario.get(k).equipo);
//		content.put("RUTA", usuario.get(k).ruta);
//		
//		result = dbw.insert("T_USUARIO", null, content)!=-1;
//		if (contadorObserver != null) {
//			contadorObserver.insertado(1, usuario.size());
//		}
//		}
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveSegmentacion(List<Segmentacion> segmentacion, CounterObservable contadorObserver){
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result=false;
//		for( int k=0; k<segmentacion.size(); k++ ){
//			ContentValues content = new ContentValues();
//			
//		content.put("ID", segmentacion.get(k).id);
//		content.put("COD_SEDE_OPERATIVA", segmentacion.get(k).cod_sede_operativa);
////		content.put("COD_DEP_ASIG", segmentacion.get(k).cod_dep_asig);
//		content.put("ID_N", segmentacion.get(k).id_n);
//		content.put("EQUIPO", segmentacion.get(k).equipo);
//		content.put("RUTA", segmentacion.get(k).ruta);
//		content.put("PERIODO", segmentacion.get(k).periodo);
////		content.put("USUARIO_ID", segmentacion.get(k).usuario_id);
//		
//		result = dbw.insert("T_SEGMENTACION", null, content)!=-1;
//		if (contadorObserver != null) {
//			contadorObserver.insertado(1, segmentacion.size());
//		}
//		}
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public static boolean saveUbigeo(SQLiteDatabase db, Ubigeo ubigeo){
//		ContentValues content = new ContentValues();
//			
////		content.put("IDDIST", ubigeo.iddist);
////		content.put("NOMBDIST", ubigeo.nombdist);
////		content.put("IDDPTO", ubigeo.iddpto);
////		content.put("IDPROV", ubigeo.idprov);
////		content.put("NOMBPROV", ubigeo.nombprov);
////		content.put("NOMBDEP", ubigeo.nombdep);
//		
//		boolean result = db.insert("T_UBIGEO", null, content)!=-1;
//		
//		return result;
//	}
	
	public static boolean saveCuest(SQLiteDatabase db, Cuest cuest){
		ContentValues content = new ContentValues();
			
		content.put("ANIO", cuest.anio);
		content.put("CUEST", cuest.cuest);
		content.put("TRIMESTRE", cuest.trimestre);
		
		boolean result = db.insert("T_DIG_CUEST", null, content)!=-1;
		
		return result;
	}
	
//	public boolean saveUbigeo(Ubigeo ubigeo){
//		ContentValues content = new ContentValues();
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		boolean result = false;
////		Log.e("chekealo", "chekealo: ssssssss: "+ubigeo.iddist);
////		boolean accion = existeRegistro("T_UBIGEO", "IDDIST='"+ubigeo.iddist+"'");
////		Log.e("chekealo", "chekealo: ssssssss: "+accion);
////		Log.e("chekealo", "ubigeo.nombprov: "+ubigeo.nombprov);
////
////		if(!accion) {
////			content.put("IDDIST", ubigeo.iddist);
////			content.put("NOMBDIST", ubigeo.nombdist);
////			content.put("IDDPTO", ubigeo.iddpto);
////			content.put("IDPROV", ubigeo.idprov);
////			content.put("NOMBPROV", ubigeo.nombprov);
////			content.put("NOMBDEP", ubigeo.nombdep);
////		
////			result = db.insert("T_UBIGEO", null, content)!=-1;
////		} else {
////			content.put("NOMBDIST", ubigeo.nombdist);
////			content.put("NOMBPROV", ubigeo.nombprov);
////			content.put("NOMBDEP", ubigeo.nombdep);
////			
////			try {
////				result = db.update("T_UBIGEO", content, "IDDIST=?", new String[]{ubigeo.iddist})>0;
////			} catch (Exception e) {
////				Log.e("lanza excp", "lanza excp: "+e.getMessage());
////			}
////			
////		}
//		
//		db.close();
//		db = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean grabarCap100(INF_Cap100 inf_Cap100) {
//		boolean success = false;
////		boolean exist = existeCap100Delitos(cap100.id_n);
//		String oper = existeRegistro(inf_Cap100.id_n) ? "edit":"add";
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			cv.put("ID_N", inf_Cap100.id_n);
//
//	    	cv = inf_Cap100.getContentValues(cv, Utilidades.getListFieldsEntity(inf_Cap100, 
//	    			Utilidades.getListFields(inf_Cap100, new String[]{"OBS_CAP_100"}), false).lstfields);
//
//	    	if(oper.equals("add")){
//	    		success = db.insert("T_DIG_01_100", null, cv)!=-1;
//			}
//			else{
//				success = db.update("T_DIG_01_100", cv, "ID_N=?", new String[]{inf_Cap100.id_n})>0;
//			}
//			
//		} 
//		catch (Exception e) {
//			Log.d("CAP 100", e.getMessage());		
//		} 
//		finally {
//			db.close();
//		}
//		
//		return success;
//	}
//	
//	public boolean saveCaratula(INF_Caratula01 caratula){
//		return save(caratula, caratula.id_n, "T_DIG_01");
//	}
//	public boolean saveINF_Visita(INF_Visita01 caratula){
//		return save(caratula, caratula.id_n, "T_DIG_01_VISITA");
//	}
//	public boolean saveINF_Capitulo100(INF_Cap100 caratula){
//		return save(caratula, caratula.id_n, "T_DIG_01_100");
//	}
//	public boolean saveINF_Capitulo200(INF_Cap200 caratula){
//		return save(caratula, caratula.id_n, "T_DIG_01_200");
//	}
//	public boolean saveINF_Capitulo300(INF_Cap300 caratula){
//		return save(caratula, caratula.id_n, "T_DIG_01_300");
//	}
//	public boolean saveINF_Capitulo300_III(INF_Cap300_III caratula){
//		return save(caratula, caratula.id_n, "T_DIG_01_300_III");
//	}
//	public boolean saveINF_Capitulo400(INF_Cap400 caratula){
//		return save(caratula, caratula.id_n, "T_DIG_01_400");
//	}
//	public boolean saveINF_Capitulo500(INF_Cap500 caratula){
//		return save(caratula, caratula.id_n, "T_DIG_01_500");
//	}
//	public boolean saveINF_Capitulo600(INF_Cap600 caratula){
//		return save(caratula, caratula.id_n, "T_DIG_01_600");
//	}
//	public boolean saveINF_Capitulo600i(INF_Cap600_I caratula){
//		return save(caratula, caratula.id_n, "T_DIG_01_600_I");
//	}
//	public boolean saveAT_Muestra(AT_Muestra muestra){
//		return save(muestra, muestra.id_n, "T_MUESTRAAT");
//	}
//	public boolean saveAT_Visita(ATVisita caratula){
//		return save(caratula, caratula.id_n, "T_DIG_02_VISITA");
//	}
//	public boolean saveATCapitulo100(ATCap100BE caratula){
//		return save(caratula, caratula.id_n, "T_DIG_02_100");
//	}
//	public boolean saveATCapitulo200(ATCap200BE caratula){
//		return save(caratula, caratula.id_n, "T_DIG_02_200");
//	}
//	public boolean saveVisitaDelitos(DelVisita caratula){
////		return save(caratula, caratula.id_n, "T_DIG_03_VISITA");
//		return false;
//	}
//	public boolean saveCap100Delitos(Cap100Delitos caratula){
////		return save(caratula, caratula.id_n, "T_DIG_03_100");
//		return false;
//	}
//	public boolean saveCap200Delitos(Cap200Delitos caratula){
////		return save(caratula, caratula.id_n, "T_DIG_03_200");
//		return false;
//	}
//	public boolean saveCap300Delitos(Cap300Delitos caratula){
////		return save(caratula, caratula.id_n, "T_DIG_03_300");
//		return false;
//	}
//	public boolean saveCap400Delitos(Cap400Delitos caratula){
////		return save(caratula, caratula.id_n, "T_DIG_03_400");
//		return false;
//	}
	
//	public boolean save(CenacomEntity entity, String id_n, String table){
//		boolean success = false;
////		String oper = existeRegistro(id_n) ? "edit":"add";
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			cv.put("ID_N", id_n);
//
//	    	cv = entity.getContentValues(cv, Utilidades.getListFieldsEntity(entity, 
//	    			Utilidades.getListFields(entity), false).lstfields);
//
////	    	if(oper.equals("add")){
//	    		success = db.insert(table, null, cv)!=-1;
////			}
////			else{
////				success = db.update(table, cv, "ID_N=?", new String[]{id_n})>0;
////			}
//			
//		} 
//		catch (Exception e) {
//			Log.e(entity.getClass().getSimpleName(), e.getMessage());		
//		} 
//		finally {
//			db.close();
//		}
//		
//		return success;
//		
//		
////		ContentValues content = new ContentValues();
////		
////		content.put("ID_N", caratula.id_n);
////		content.put("REG", caratula.reg);
////		content.put("NOMREG", caratula.nomreg);
////		content.put("CCDD", caratula.ccdd);
////		content.put("NOMBREDD", caratula.nombredd);
////		content.put("CCPP", caratula.ccpp);
////		content.put("NOMBREPP", caratula.nombrepp);
////		content.put("CCDI", caratula.ccdi);
////		content.put("NOMBREDI", caratula.nombredi);
////		content.put("CCCP", caratula.cccp);
////		content.put("NOMBRECP", caratula.nombrecp);
////		content.put("AREA", caratula.area);
////		content.put("ZONA", caratula.zona);
////		content.put("ZONALF", caratula.zonalf);
////		content.put("MZA", caratula.mza);
////		content.put("MZNALF", caratula.mznalf);
////		content.put("FRENTE", caratula.frente);
////		content.put("AER_INI", caratula.aer_ini);
////		content.put("AER_FIN", caratula.aer_fin);
////		content.put("I11_1", caratula.i11_1);
////		content.put("I11_2", caratula.i11_2);
////		content.put("I11_3", caratula.i11_3);
////		content.put("I11_4", caratula.i11_4);
////		content.put("I11_5", caratula.i11_5);
////		content.put("I11_6", caratula.i11_6);
////		content.put("I11_7", caratula.i11_7);
////		content.put("I11_8", caratula.i11_8);
//////		content.put("I11_9", caratula.i11_9);
////		content.put("I11_10", caratula.i11_10);
////		/*content.put("FORMUL_N", caratula.formul_n);
////		content.put("FORMUL_TOT", caratula.formul_tot);*/
////		content.put("RUTAN", caratula.rutan);
////		
////		content.put("II1", caratula.ii1);
////		content.put("II2", caratula.ii2);
////		content.put("II3", caratula.ii3);
////		content.put("II4", caratula.ii4);
////		content.put("II5", caratula.ii5);
////		content.put("II6", caratula.ii6);
////		content.put("II7", caratula.ii7);
////		content.put("II8", caratula.ii8);
////		content.put("II8_O", caratula.ii8_o);
////		content.put("II9", caratula.ii9);
////		content.put("II9_O", caratula.ii9_o);
////		
////		content.put("IV2_1_D", caratula.iv2_1_d);
////		content.put("IV2_1_M", caratula.iv2_1_m);
////		
////		content.put("IV1_1", caratula.iv1_1);
////		content.put("IVRESFIN_01", caratula.ivresfin_01);
////		content.put("IVRESFIN_01_O", caratula.ivresfin_01_o);
////		content.put("IV2_1", caratula.iv2_1);
////		content.put("IVRESFIN_02", caratula.ivresfin_02);
////		content.put("IVRESFIN_02_O", caratula.ivresfin_02_o);
////		
////		content.put("VI1A", caratula.vi1a);
////		content.put("VI1B", caratula.vi1b);
////		content.put("VI2A", caratula.vi2a);
////		content.put("VI2B", caratula.vi2b);
////		content.put("VI3A", caratula.vi3a);
////		content.put("VI3B", caratula.vi3b);
////		content.put("VI4A", caratula.vi4a);
////		content.put("VI4B", caratula.vi4b);
////		
////		/*content.put("VII1A", caratula.vii1a);
////		content.put("VII1B", caratula.vii1b);
////		content.put("VII2A", caratula.vii2a);
////		content.put("VII2B", caratula.vii2b);*/
////		
////		content.put("OBS_01_CAR", caratula.obs_01_car);
////		content.put("OBS_02_CAR", caratula.obs_02_car);
////		content.put("OBS_CAR", caratula.obs_car);
////		content.put("GPSLATITUD_INF", caratula.gpslatitud_inf);
////		content.put("GPSLONGITUD_INF", caratula.gpslongitud_inf);
////		content.put("GPSLATITUD_AT", caratula.gpslatitud_at);
////		content.put("GPSLONGITUD_AT", caratula.gpslongitud_at);
////		
////		content.put("VHAB", caratula.vhab);
////		content.put("VPCS", caratula.vpcs);
////		content.put("VEFEC", caratula.vefec);
////		
////		content.put("VAT_2013", caratula.vat_2013);
////		content.put("VLO_2013", caratula.vlo_2013);
////		content.put("AT_INI", caratula.at_ini);
////		
////		content.put("VERSION_EXPORTACION", caratula.version_exportacion);
////		content.put("VERSION_IMPORTACION", caratula.version_importacion);
////		content.put("ESTADO_ENVIO", caratula.estado_envio);
////		
////		SQLiteDatabase dbw = dbh.getWritableDatabase();
////		boolean result = dbw.insert("T_DIG_01", null, content)!=-1;
////		
////		dbw.close();
////		dbw = null;
////		SQLiteDatabase.releaseMemory();
////		
////		return result;
//	}
	
//	public boolean saveINF_Visita(INF_Visita01 caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("NROVIS", caratula.nrovis);
//		content.put("EFECHA", caratula.efecha);
//		content.put("EHORADE", caratula.ehorade);
//		content.put("EHORAA", caratula.ehoraa);
//		content.put("EPVFECHA", caratula.epvfecha);
//		content.put("EPVHORA", caratula.epvhora);
//		content.put("ERESVIS", caratula.eresvis);
//		content.put("ERESVIS_O", caratula.eresvis_o);
//		content.put("SFECHA", caratula.sfecha);
//		content.put("SHORADE", caratula.shorade);
//		content.put("SHORAA", caratula.shoraa);
//		content.put("SRESVIS", caratula.sresvis);
//		content.put("SRESVIS_O", caratula.sresvis_o);
//		
//		content.put("GPSLATITUD", caratula.gpslatitud);
//		content.put("GPSLONGITUD", caratula.gpslongitud);
//		
////		content.put("USUCRE", caratula.usucre);
////		content.put("FECCRE", caratula.feccre);
////		content.put("USUREG", caratula.usureg);
////		content.put("FECREG", caratula.fecreg);
////		content.put("FECENV", caratula.fecenv);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_01_VISITA", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveINF_Capitulo100(INF_Cap100 caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("INF101_1", caratula.inf101_1);
//		content.put("INF102_1", caratula.inf102_1);
//		content.put("INF103_1", caratula.inf103_1);
//		content.put("INF104_1", caratula.inf104_1);
//		content.put("INF105_1", caratula.inf105_1);
//		content.put("INF106_1", caratula.inf106_1);
//		content.put("INF107_1_A", caratula.inf107_1_a);
//		content.put("INF107_1_B", caratula.inf107_1_b);
//		content.put("INF107_1_C", caratula.inf107_1_c);
//		content.put("INF108_1_INST", caratula.inf108_1_inst);
//		content.put("INF108_1_PERS", caratula.inf108_1_pers);
//		content.put("INF101_2", caratula.inf101_2);
//		content.put("INF102_2", caratula.inf102_2);
//		content.put("INF103_2", caratula.inf103_2);
//		content.put("INF104_2", caratula.inf104_2);
//		content.put("INF105_2", caratula.inf105_2);
//		content.put("INF106_2", caratula.inf106_2);
//		content.put("INF107_2_A", caratula.inf107_2_a);
//		content.put("INF107_2_B", caratula.inf107_2_b);
//		content.put("INF107_2_C", caratula.inf107_2_c);
//		content.put("INF108_2_INST", caratula.inf108_2_inst);
//		content.put("INF108_2_PERS", caratula.inf108_2_pers);
//		content.put("INF109", caratula.inf109);
//		content.put("INF110_TOT", caratula.inf110_tot);
//		content.put("INF110_TOT_OFI_H", caratula.inf110_tot_ofi_h);
//		content.put("INF110_TOT_OFI_M", caratula.inf110_tot_ofi_m);
//		content.put("INF110_TOT_SOF_H", caratula.inf110_tot_sof_h);
//		content.put("INF110_TOT_SOF_M", caratula.inf110_tot_sof_m);
//		content.put("INF110_TOT_ESP_H", caratula.inf110_tot_esp_h);
//		content.put("INF110_TOT_ESP_M", caratula.inf110_tot_esp_m);
//		content.put("INF110_TOT2", caratula.inf110_tot2);
//		content.put("INF110_TOT_SER", caratula.inf110_tot_ser);
//		content.put("INF110_TOT_FRA", caratula.inf110_tot_fra);
//		content.put("INF111_ADM", caratula.inf111_adm);
//		content.put("INF111_ADM_OFI_H", caratula.inf111_adm_ofi_h);
//		content.put("INF111_ADM_OFI_M", caratula.inf111_adm_ofi_m);
//		content.put("INF111_ADM_SOF_H", caratula.inf111_adm_sof_h);
//		content.put("INF111_ADM_SOF_M", caratula.inf111_adm_sof_m);
//		content.put("INF111_ADM_ESP_H", caratula.inf111_adm_esp_h);
//		content.put("INF111_ADM_ESP_M", caratula.inf111_adm_esp_m);
//		content.put("INF111_ADM2", caratula.inf111_adm2);
//		content.put("INF111_ADM_SER", caratula.inf111_adm_ser);
//		content.put("INF111_ADM_FRA", caratula.inf111_adm_fra);
//		content.put("INF112_MOT", caratula.inf112_mot);
//		content.put("INF112_MOT_OFI_H", caratula.inf112_mot_ofi_h);
//		content.put("INF112_MOT_OFI_M", caratula.inf112_mot_ofi_m);
//		content.put("INF112_MOT_SOF_H", caratula.inf112_mot_sof_h);
//		content.put("INF112_MOT_SOF_M", caratula.inf112_mot_sof_m);
//		content.put("INF112_MOT_ESP_H", caratula.inf112_mot_esp_h);
//		content.put("INF112_MOT_ESP_M", caratula.inf112_mot_esp_m);
//		content.put("INF112_MOT2", caratula.inf112_mot2);
//		content.put("INF112_MOT_SER", caratula.inf112_mot_ser);
//		content.put("INF112_MOT_FRA", caratula.inf112_mot_fra);
//		content.put("INF113_PIE", caratula.inf113_pie);
//		content.put("INF113_PIE_OFI_H", caratula.inf113_pie_ofi_h);
//		content.put("INF113_PIE_OFI_M", caratula.inf113_pie_ofi_m);
//		content.put("INF113_PIE_SOF_H", caratula.inf113_pie_sof_h);
//		content.put("INF113_PIE_SOF_M", caratula.inf113_pie_sof_m);
//		content.put("INF113_PIE_ESP_H", caratula.inf113_pie_esp_h);
//		content.put("INF113_PIE_ESP_M", caratula.inf113_pie_esp_m);
//		content.put("INF113_PIE2", caratula.inf113_pie2);
//		content.put("INF113_PIE_SER", caratula.inf113_pie_ser);
//		content.put("INF113_PIE_FRA", caratula.inf113_pie_fra);
//		content.put("INF114_INV", caratula.inf114_inv);
//		content.put("INF114_INV_OFI_H", caratula.inf114_inv_ofi_h);
//		content.put("INF114_INV_OFI_M", caratula.inf114_inv_ofi_m);
//		content.put("INF114_INV_SOF_H", caratula.inf114_inv_sof_h);
//		content.put("INF114_INV_SOF_M", caratula.inf114_inv_sof_m);
//		content.put("INF114_INV_ESP_H", caratula.inf114_inv_esp_h);
//		content.put("INF114_INV_ESP_M", caratula.inf114_inv_esp_m);
//		content.put("INF114_INV2", caratula.inf114_inv2);
//		content.put("INF114_INV_SER", caratula.inf114_inv_ser);
//		content.put("INF114_INV_FRA", caratula.inf114_inv_fra);
//		content.put("INF115_INV_AT", caratula.inf115_inv_at);
//		content.put("INF115_INV_AT_OFI_H", caratula.inf115_inv_at_ofi_h);
//		content.put("INF115_INV_AT_OFI_M", caratula.inf115_inv_at_ofi_m);
//		content.put("INF115_INV_AT_SOF_H", caratula.inf115_inv_at_sof_h);
//		content.put("INF115_INV_AT_SOF_M", caratula.inf115_inv_at_sof_m);
//		content.put("INF115_INV_AT_ESP_H", caratula.inf115_inv_at_esp_h);
//		content.put("INF115_INV_AT_ESP_M", caratula.inf115_inv_at_esp_m);
//		content.put("INF115_INV2_AT", caratula.inf115_inv2_at);
//		content.put("INF115_INV_AT_SER", caratula.inf115_inv_at_ser);
//		content.put("INF115_INV_AT_FRA", caratula.inf115_inv_at_fra);
//		content.put("INF116_INV_VF", caratula.inf116_inv_vf);
//		content.put("INF116_INV_VF_OFI_H", caratula.inf116_inv_vf_ofi_h);
//		content.put("INF116_INV_VF_OFI_M", caratula.inf116_inv_vf_ofi_m);
//		content.put("INF116_INV_VF_SOF_H", caratula.inf116_inv_vf_sof_h);
//		content.put("INF116_INV_VF_SOF_M", caratula.inf116_inv_vf_sof_m);
//		content.put("INF116_INV_VF_ESP_H", caratula.inf116_inv_vf_esp_h);
//		content.put("INF116_INV_VF_ESP_M", caratula.inf116_inv_vf_esp_m);
//		content.put("INF116_INV2_VF", caratula.inf116_inv2_vf);
//		content.put("INF116_INV_VF_SER", caratula.inf116_inv_vf_ser);
//		content.put("INF116_INV_VF_FRA", caratula.inf116_inv_vf_fra);
//		content.put("INF117_OPC", caratula.inf117_opc);
//		content.put("INF117_OPC_OFI_H", caratula.inf117_opc_ofi_h);
//		content.put("INF117_OPC_OFI_M", caratula.inf117_opc_ofi_m);
//		content.put("INF117_OPC_SOF_H", caratula.inf117_opc_sof_h);
//		content.put("INF117_OPC_SOF_M", caratula.inf117_opc_sof_m);
//		content.put("INF117_OPC_ESP_H", caratula.inf117_opc_esp_h);
//		content.put("INF117_OPC_ESP_M", caratula.inf117_opc_esp_m);
//		content.put("INF117_OPC2", caratula.inf117_opc2);
//		content.put("INF117_OPC_SER", caratula.inf117_opc_ser);
//		content.put("INF117_OPC_FRA", caratula.inf117_opc_fra);
//		content.put("INF118_PG", caratula.inf118_pg);
//		content.put("INF118_PG_OFI_H", caratula.inf118_pg_ofi_h);
//		content.put("INF118_PG_OFI_M", caratula.inf118_pg_ofi_m);
//		content.put("INF118_PG_SOF_H", caratula.inf118_pg_sof_h);
//		content.put("INF118_PG_SOF_M", caratula.inf118_pg_sof_m);
//		content.put("INF118_PG_ESP_H", caratula.inf118_pg_esp_h);
//		content.put("INF118_PG_ESP_M", caratula.inf118_pg_esp_m);
//		content.put("INF118_PG2", caratula.inf118_pg2);
//		content.put("INF118_PG_SER", caratula.inf118_pg_ser);
//		content.put("INF118_PG_FRA", caratula.inf118_pg_fra);
//		content.put("INF119_SC", caratula.inf119_sc);
//		content.put("INF119_SC_OFI_H", caratula.inf119_sc_ofi_h);
//		content.put("INF119_SC_OFI_M", caratula.inf119_sc_ofi_m);
//		content.put("INF119_SC_SOF_H", caratula.inf119_sc_sof_h);
//		content.put("INF119_SC_SOF_M", caratula.inf119_sc_sof_m);
//		content.put("INF119_SC_ESP_H", caratula.inf119_sc_esp_h);
//		content.put("INF119_SC_ESP_M", caratula.inf119_sc_esp_m);
//		content.put("INF119_SC2", caratula.inf119_sc2);
//		content.put("INF119_SC_SER", caratula.inf119_sc_ser);
//		content.put("INF119_SC_FRA", caratula.inf119_sc_fra);
//		content.put("INF120_O", caratula.inf120_o);
//		content.put("INF120_O_O", caratula.inf120_o_o);
//		content.put("INF120_O_OFI_H", caratula.inf120_o_ofi_h);
//		content.put("INF120_O_OFI_M", caratula.inf120_o_ofi_m);
//		content.put("INF120_O_SOF_H", caratula.inf120_o_sof_h);
//		content.put("INF120_O_SOF_M", caratula.inf120_o_sof_m);
//		content.put("INF120_O_ESP_H", caratula.inf120_o_esp_h);
//		content.put("INF120_O_ESP_M", caratula.inf120_o_esp_m);
//		content.put("INF120_O2", caratula.inf120_o2);
//		content.put("INF120_O_SER", caratula.inf120_o_ser);
//		content.put("INF120_O_FRA", caratula.inf120_o_fra);
//		content.put("OBS_CAP_100", caratula.obs_cap_100);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_01_100", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveINF_Capitulo200(INF_Cap200 caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("INF201", caratula.inf201);
//		content.put("INF201_O", caratula.inf201_o);
//		content.put("INF202_1A", caratula.inf202_1a);
//		content.put("INF202_1B", caratula.inf202_1b);
//		content.put("INF202_1C", caratula.inf202_1c);
//		content.put("INF202_1D", caratula.inf202_1d);
//		content.put("INF202_1E", caratula.inf202_1e);
//		content.put("INF202_1F", caratula.inf202_1f);
//		content.put("INF202_1G", caratula.inf202_1g);
//		content.put("INF202_1G_O", caratula.inf202_1g_o);
//		content.put("INF202_2H", caratula.inf202_2h);
//		content.put("INF202_2I", caratula.inf202_2i);
//		content.put("INF202_2I_O", caratula.inf202_2i_o);
//		content.put("INF202_3J", caratula.inf202_3j);
//		content.put("INF202_3K", caratula.inf202_3k);
//		content.put("INF202_3L1", caratula.inf202_3l1);
//		content.put("INF202_3L2", caratula.inf202_3l2);
//		content.put("INF202_3M", caratula.inf202_3m);
//		content.put("INF202_3N", caratula.inf202_3n);
//		content.put("INF202_3N_O", caratula.inf202_3n_o);
//		content.put("INF202_4_NI", caratula.inf202_4_ni);
//		content.put("INF202_4_NI_O", caratula.inf202_4_ni_o);
//		content.put("INF203", caratula.inf203);
//		content.put("INF204", caratula.inf204);
//		content.put("INF205", caratula.inf205);
//		content.put("INF205A", caratula.inf205a);
//		content.put("INF206A", caratula.inf206a);
//		content.put("INF207A", caratula.inf207a);
//		content.put("INF206B", caratula.inf206b);
//		content.put("INF207B", caratula.inf207b);
//		content.put("INF206C", caratula.inf206c);
//		content.put("INF207C", caratula.inf207c);
//		content.put("INF206D", caratula.inf206d);
//		content.put("INF207D", caratula.inf207d);
//		content.put("INF206E", caratula.inf206e);
//		content.put("INF207E", caratula.inf207e);
//		content.put("INF206F", caratula.inf206f);
//		content.put("INF207F", caratula.inf207f);
//		content.put("INF206G", caratula.inf206g);
//		content.put("INF206G_O", caratula.inf206g_o);
//		content.put("INF207G", caratula.inf207g);
//		content.put("INF208", caratula.inf208);
//		content.put("INF208_O", caratula.inf208_o);
//		content.put("OBS_CAP_200_1", caratula.obs_cap_200_1);
//		content.put("INF209", caratula.inf209);
//		content.put("INF209_O", caratula.inf209_o);
//		content.put("INF210", caratula.inf210);
//		content.put("INF210_O", caratula.inf210_o);
//		content.put("INF211", caratula.inf211);
//		content.put("INF212", caratula.inf212);
//		content.put("INF212A", caratula.inf212a);
//		content.put("INF212B", caratula.inf212b);
//		content.put("INF212C", caratula.inf212c);
//		content.put("INF213", caratula.inf213);
//		content.put("INF213_O", caratula.inf213_o);
//		content.put("INF214", caratula.inf214);
//		content.put("INF215", caratula.inf215);
//		content.put("INF216", caratula.inf216);
//		content.put("INF217_1", caratula.inf217_1);
//		content.put("INF217_2", caratula.inf217_2);
//		content.put("INF217_3", caratula.inf217_3);
//		content.put("INF217_4", caratula.inf217_4);
//		content.put("INF217_5", caratula.inf217_5);
//		content.put("INF217_5_O", caratula.inf217_5_o);
//		content.put("INF218", caratula.inf218);
//		content.put("INF218_O", caratula.inf218_o);
//		content.put("OBS_CAP_200_2", caratula.obs_cap_200_2);
//		content.put("INF219", caratula.inf219);
//		content.put("INF219A", caratula.inf219a);
//		content.put("INF219B", caratula.inf219b);
//		content.put("INF219C", caratula.inf219c);
//		content.put("INF220", caratula.inf220);
//		content.put("INF221", caratula.inf221);
//		content.put("INF222", caratula.inf222);
//		content.put("INF222_1", caratula.inf222_1);
//		content.put("INF222_O", caratula.inf222_o);
//		content.put("INF223", caratula.inf223);
//		content.put("INF223_O", caratula.inf223_o);
//		content.put("INF224_1", caratula.inf224_1);
//		content.put("INF224_2", caratula.inf224_2);
//		content.put("INF224_3", caratula.inf224_3);
//		content.put("INF224_4", caratula.inf224_4);
//		content.put("INF224_5", caratula.inf224_5);
//		content.put("INF224_5_O", caratula.inf224_5_o);
//		content.put("INF225", caratula.inf225);
//		content.put("INF226", caratula.inf226);
//		content.put("INF227_1", caratula.inf227_1);
//		content.put("INF227_1_1", caratula.inf227_1_1);
//		content.put("INF227_1_2", caratula.inf227_1_2);
//		content.put("INF227_1_3", caratula.inf227_1_3);
//		content.put("INF227_2", caratula.inf227_2);
//		content.put("INF227_2_1", caratula.inf227_2_1);
//		content.put("INF227_2_2", caratula.inf227_2_2);
//		content.put("INF227_2_3", caratula.inf227_2_3);
//		content.put("INF228_1", caratula.inf228_1);
//		content.put("INF228_1_1", caratula.inf228_1_1);
//		content.put("INF228_1_2", caratula.inf228_1_2);
//		content.put("INF228_1_3", caratula.inf228_1_3);
//		content.put("INF228_2", caratula.inf228_2);
//		content.put("INF228_2_1", caratula.inf228_2_1);
//		content.put("INF228_2_2", caratula.inf228_2_2);
//		content.put("INF228_2_3", caratula.inf228_2_3);
//		content.put("INF228_3", caratula.inf228_3);
//		content.put("INF228_3_3", caratula.inf228_3_3);
//		content.put("INF228_4", caratula.inf228_4);
//		content.put("INF228_4_O", caratula.inf228_4_o);
//		content.put("INF228_4_1", caratula.inf228_4_1);
//		content.put("INF228_4_2", caratula.inf228_4_2);
//		content.put("INF228_4_3", caratula.inf228_4_3);
//		content.put("OBS_CAP_200_3", caratula.obs_cap_200_3);
//		content.put("INF229_1", caratula.inf229_1);
//		content.put("INF229_1_1", caratula.inf229_1_1);
//		content.put("INF229_1_2", caratula.inf229_1_2);
//		content.put("INF229_1_3", caratula.inf229_1_3);
//		content.put("INF229_2", caratula.inf229_2);
//		content.put("INF229_2_1", caratula.inf229_2_1);
//		content.put("INF229_2_2", caratula.inf229_2_2);
//		content.put("INF229_2_3", caratula.inf229_2_3);
//		content.put("INF230_1", caratula.inf230_1);
//		content.put("INF230_1_1", caratula.inf230_1_1);
//		content.put("INF230_1_2", caratula.inf230_1_2);
//		content.put("INF230_1_3", caratula.inf230_1_3);
//		content.put("INF230_2", caratula.inf230_2);
//		content.put("INF230_2_1", caratula.inf230_2_1);
//		content.put("INF230_2_2", caratula.inf230_2_2);
//		content.put("INF230_2_3", caratula.inf230_2_3);
//		content.put("INF230_3", caratula.inf230_3);
//		content.put("INF230_3_3", caratula.inf230_3_3);
//		content.put("INF230_4", caratula.inf230_4);
//		content.put("INF230_4_O", caratula.inf230_4_o);
//		content.put("INF230_4_1", caratula.inf230_4_1);
//		content.put("INF230_4_2", caratula.inf230_4_2);
//		content.put("INF230_4_3", caratula.inf230_4_3);
//		content.put("INF231_1", caratula.inf231_1);
//		content.put("INF231_1_1", caratula.inf231_1_1);
//		content.put("INF231_1_2", caratula.inf231_1_2);
//		content.put("INF231_1_3", caratula.inf231_1_3);
//		content.put("INF231_2", caratula.inf231_2);
//		content.put("INF231_2_1", caratula.inf231_2_1);
//		content.put("INF231_2_2", caratula.inf231_2_2);
//		content.put("INF231_2_3", caratula.inf231_2_3);
//		content.put("INF232", caratula.inf232);
//		content.put("INF232_O", caratula.inf232_o);
//		content.put("INF233", caratula.inf233);
//		content.put("INF233_O", caratula.inf233_o);
//		content.put("INF234", caratula.inf234);
//		content.put("INF234_O", caratula.inf234_o);
//		content.put("INF235_P", caratula.inf235_p);
//		content.put("INF235_V", caratula.inf235_v);
//		content.put("INF236_1", caratula.inf236_1);
//		content.put("INF236_1_CANT", caratula.inf236_1_cant);
//		content.put("INF236_2", caratula.inf236_2);
//		content.put("INF236_2_CANT", caratula.inf236_2_cant);
//		content.put("INF236_3", caratula.inf236_3);
//		content.put("INF236_3_CANT", caratula.inf236_3_cant);
//		content.put("INF236_4", caratula.inf236_4);
//		content.put("INF236_4_CANT", caratula.inf236_4_cant);
//		content.put("INF236_5", caratula.inf236_5);
//		content.put("INF236_5_CANT", caratula.inf236_5_cant);
//		content.put("INF236_6", caratula.inf236_6);
//		content.put("INF236_6_CANT", caratula.inf236_6_cant);
//		content.put("INF236_7", caratula.inf236_7);
//		content.put("INF236_7_O", caratula.inf236_7_o);
//		content.put("INF236_7_CANT", caratula.inf236_7_cant);
//		content.put("INF236_TOT_CANT", caratula.inf236_tot_cant);
//		content.put("OBS_CAP_200_4", caratula.obs_cap_200_4);
//		content.put("INF237_1", caratula.inf237_1);
//		content.put("INF237_1_EST1", caratula.inf237_1_est1);
//		content.put("INF237_1_EST2", caratula.inf237_1_est2);
//		content.put("INF237_1_EST3", caratula.inf237_1_est3);
//		content.put("INF237_1_EST4", caratula.inf237_1_est4);
//		content.put("INF237_2", caratula.inf237_2);
//		content.put("INF237_2_EST1", caratula.inf237_2_est1);
//		content.put("INF237_2_EST2", caratula.inf237_2_est2);
//		content.put("INF237_2_EST3", caratula.inf237_2_est3);
//		content.put("INF237_2_EST4", caratula.inf237_2_est4);
//		content.put("INF237_3", caratula.inf237_3);
//		content.put("INF237_3_EST1", caratula.inf237_3_est1);
//		content.put("INF237_3_EST2", caratula.inf237_3_est2);
//		content.put("INF237_3_EST3", caratula.inf237_3_est3);
//		content.put("INF237_3_EST4", caratula.inf237_3_est4);
//		content.put("INF237_4", caratula.inf237_4);
//		content.put("INF237_4_EST1", caratula.inf237_4_est1);
//		content.put("INF237_4_EST3", caratula.inf237_4_est3);
//		content.put("INF237_4_EST4", caratula.inf237_4_est4);
//		content.put("INF237_5", caratula.inf237_5);
//		content.put("INF237_5_EST1", caratula.inf237_5_est1);
//		content.put("INF237_5_EST2", caratula.inf237_5_est2);
//		content.put("INF237_5_EST3", caratula.inf237_5_est3);
//		content.put("INF237_5_EST4", caratula.inf237_5_est4);
//		content.put("INF237_6", caratula.inf237_6);
//		content.put("INF237_6_EST1", caratula.inf237_6_est1);
//		content.put("INF237_6_EST2", caratula.inf237_6_est2);
//		content.put("INF237_6_EST3", caratula.inf237_6_est3);
//		content.put("INF237_6_EST4", caratula.inf237_6_est4);
//		content.put("INF237_7", caratula.inf237_7);
//		content.put("INF237_7_O", caratula.inf237_7_o);
//		content.put("INF237_7_EST1", caratula.inf237_7_est1);
//		content.put("INF237_7_EST2", caratula.inf237_7_est2);
//		content.put("INF237_7_EST3", caratula.inf237_7_est3);
//		content.put("INF237_7_EST4", caratula.inf237_7_est4);
//		content.put("INF237_TOT_EST1", caratula.inf237_tot_est1);
//		content.put("INF237_TOT_EST2", caratula.inf237_tot_est2);
//		content.put("INF237_TOT_EST3", caratula.inf237_tot_est3);
//		content.put("INF237_TOT_EST4", caratula.inf237_tot_est4);
//		content.put("INF238_1", caratula.inf238_1);
//		content.put("INF238_1_TOT", caratula.inf238_1_tot);
//		content.put("INF238_1_REP", caratula.inf238_1_rep);
//		content.put("INF238_1_REEM", caratula.inf238_1_reem);
//		content.put("INF238_2", caratula.inf238_2);
//		content.put("INF238_2_TOT", caratula.inf238_2_tot);
//		content.put("INF238_2_REP", caratula.inf238_2_rep);
//		content.put("INF238_2_REEM", caratula.inf238_2_reem);
//		content.put("INF238_3", caratula.inf238_3);
//		content.put("INF238_3_TOT", caratula.inf238_3_tot);
//		content.put("INF238_3_REP", caratula.inf238_3_rep);
//		content.put("INF238_3_REEM", caratula.inf238_3_reem);
//		content.put("INF238_4", caratula.inf238_4);
//		content.put("INF238_4_TOT", caratula.inf238_4_tot);
//		content.put("INF238_4_REP", caratula.inf238_4_rep);
//		content.put("INF238_4_REEM", caratula.inf238_4_reem);
//		content.put("INF238_5", caratula.inf238_5);
//		content.put("INF238_5_TOT", caratula.inf238_5_tot);
//		content.put("INF238_5_REP", caratula.inf238_5_rep);
//		content.put("INF238_5_REEM", caratula.inf238_5_reem);
//		content.put("INF238_6", caratula.inf238_6);
//		content.put("INF238_6_TOT", caratula.inf238_6_tot);
//		content.put("INF238_6_REP", caratula.inf238_6_rep);
//		content.put("INF238_6_REEM", caratula.inf238_6_reem);
//		content.put("INF238_7", caratula.inf238_7);
//		content.put("INF238_7_TOT", caratula.inf238_7_tot);
//		content.put("INF238_7_REP", caratula.inf238_7_rep);
//		content.put("INF238_7_REEM", caratula.inf238_7_reem);
//		content.put("INF238_7_O", caratula.inf238_7_o);
//		content.put("INF238_TOT", caratula.inf238_tot);
//		content.put("INF238_REP", caratula.inf238_rep);
//		content.put("INF238_REEM", caratula.inf238_reem);
//		content.put("INF239", caratula.inf239);
//		content.put("INF240_1", caratula.inf240_1);
//		content.put("INF240_1_CANT", caratula.inf240_1_cant);
//		content.put("INF240_2", caratula.inf240_2);
//		content.put("INF240_2_CANT", caratula.inf240_2_cant);
//		content.put("INF240_3", caratula.inf240_3);
//		content.put("INF240_3_CANT", caratula.inf240_3_cant);
//		content.put("INF240_4", caratula.inf240_4);
//		content.put("INF240_4_CANT", caratula.inf240_4_cant);
//		content.put("INF240_5", caratula.inf240_5);
//		content.put("INF240_5_O", caratula.inf240_5_o);
//		content.put("INF240_5_CANT", caratula.inf240_5_cant);
//		content.put("INF240_TOT_CANT", caratula.inf240_tot_cant);
//		content.put("INF241_1", caratula.inf241_1);
//		content.put("INF241_1_EST1", caratula.inf241_1_est1);
//		content.put("INF241_1_EST2", caratula.inf241_1_est2);
//		content.put("INF241_1_EST3", caratula.inf241_1_est3);
//		content.put("INF241_1_EST4", caratula.inf241_1_est4);
//		content.put("INF241_2", caratula.inf241_2);
//		content.put("INF241_2_EST1", caratula.inf241_2_est1);
//		content.put("INF241_2_EST2", caratula.inf241_2_est2);
//		content.put("INF241_2_EST3", caratula.inf241_2_est3);
//		content.put("INF241_2_EST4", caratula.inf241_2_est4);
//		content.put("INF241_3", caratula.inf241_3);
//		content.put("INF241_3_EST1", caratula.inf241_3_est1);
//		content.put("INF241_3_EST2", caratula.inf241_3_est2);
//		content.put("INF241_3_EST3", caratula.inf241_3_est3);
//		content.put("INF241_3_EST4", caratula.inf241_3_est4);
//		content.put("INF241_4", caratula.inf241_4);
//		content.put("INF241_4_EST1", caratula.inf241_4_est1);
//		content.put("INF241_4_EST3", caratula.inf241_4_est3);
//		content.put("INF241_4_EST4", caratula.inf241_4_est4);
//		content.put("INF241_5", caratula.inf241_5);
//		content.put("INF241_5_O", caratula.inf241_5_o);
//		content.put("INF241_5_EST1", caratula.inf241_5_est1);
//		content.put("INF241_5_EST2", caratula.inf241_5_est2);
//		content.put("INF241_5_EST3", caratula.inf241_5_est3);
//		content.put("INF241_5_EST4", caratula.inf241_5_est4);
//		content.put("INF241_TOT_EST1", caratula.inf241_tot_est1);
//		content.put("INF241_TOT_EST2", caratula.inf241_tot_est2);
//		content.put("INF241_TOT_EST3", caratula.inf241_tot_est3);
//		content.put("INF241_TOT_EST4", caratula.inf241_tot_est4);
//		content.put("INF242_1", caratula.inf242_1);
//		content.put("INF242_1_TOT", caratula.inf242_1_tot);
//		content.put("INF242_1_REP", caratula.inf242_1_rep);
//		content.put("INF242_1_REEM", caratula.inf242_1_reem);
//		content.put("INF242_2", caratula.inf242_2);
//		content.put("INF242_2_TOT", caratula.inf242_2_tot);
//		content.put("INF242_2_REP", caratula.inf242_2_rep);
//		content.put("INF242_2_REEM", caratula.inf242_2_reem);
//		content.put("INF242_3", caratula.inf242_3);
//		content.put("INF242_3_TOT", caratula.inf242_3_tot);
//		content.put("INF242_3_REP", caratula.inf242_3_rep);
//		content.put("INF242_3_REEM", caratula.inf242_3_reem);
//		content.put("INF242_4", caratula.inf242_4);
//		content.put("INF242_4_TOT", caratula.inf242_4_tot);
//		content.put("INF242_4_REP", caratula.inf242_4_rep);
//		content.put("INF242_4_REEM", caratula.inf242_4_reem);
//		content.put("INF242_5", caratula.inf242_5);
//		content.put("INF242_5_O", caratula.inf242_5_o);
//		content.put("INF242_5_TOT", caratula.inf242_5_tot);
//		content.put("INF242_5_REP", caratula.inf242_5_rep);
//		content.put("INF242_5_REEM", caratula.inf242_5_reem);
//		content.put("INF242_TOT", caratula.inf242_tot);
//		content.put("INF242_REP", caratula.inf242_rep);
//		content.put("INF242_REEM", caratula.inf242_reem);
//		content.put("INF243_CON_CH_C", caratula.inf243_con_ch_c);
//		content.put("INF243_SIN_CH_C", caratula.inf243_sin_ch_c);
//		content.put("INF243A_1", caratula.inf243a_1);
//		content.put("INF243A_1_CANT", caratula.inf243a_1_cant);
//		content.put("INF243A_2", caratula.inf243a_2);
//		content.put("INF243A_2_CANT", caratula.inf243a_2_cant);
//		content.put("INF243A_3", caratula.inf243a_3);
//		content.put("INF243A_3_CANT", caratula.inf243a_3_cant);
//		content.put("INF243A_TOT_CANT", caratula.inf243a_tot_cant);
//		content.put("INF244_1", caratula.inf244_1);
//		content.put("INF244_1_CANT", caratula.inf244_1_cant);
//		content.put("INF244_2", caratula.inf244_2);
//		content.put("INF244_2_CANT", caratula.inf244_2_cant);
//		content.put("INF244_TOT_CANT", caratula.inf244_tot_cant);
//		content.put("OBS_CAP_200_5", caratula.obs_cap_200_5);
//		content.put("INF244A_1", caratula.inf244a_1);
//		content.put("INF244A_1_CANT", caratula.inf244a_1_cant);
//		content.put("INF244A_2", caratula.inf244a_2);
//		content.put("INF244A_2_CANT", caratula.inf244a_2_cant);
//		content.put("INF244A_TOT_CANT", caratula.inf244a_tot_cant);
//		content.put("INF245_VE", caratula.inf245_ve);
//		content.put("INF245_VA", caratula.inf245_va);
//		content.put("INF246_1", caratula.inf246_1);
//		content.put("INF246_1_CANT", caratula.inf246_1_cant);
//		content.put("INF246_2", caratula.inf246_2);
//		content.put("INF246_2_CANT", caratula.inf246_2_cant);
//		content.put("INF246_3", caratula.inf246_3);
//		content.put("INF246_3_CANT", caratula.inf246_3_cant);
//		content.put("INF246_4", caratula.inf246_4);
//		content.put("INF246_4_CANT", caratula.inf246_4_cant);
//		content.put("INF246_5", caratula.inf246_5);
//		content.put("INF246_5_O", caratula.inf246_5_o);
//		content.put("INF246_5_CANT", caratula.inf246_5_cant);
//		content.put("INF246_TOT_CANT", caratula.inf246_tot_cant);
//		content.put("INF247_1", caratula.inf247_1);
//		content.put("INF247_1_EST1", caratula.inf247_1_est1);
//		content.put("INF247_1_EST2", caratula.inf247_1_est2);
//		content.put("INF247_1_EST3", caratula.inf247_1_est3);
//		content.put("INF247_1_EST4", caratula.inf247_1_est4);
//		content.put("INF247_2", caratula.inf247_2);
//		content.put("INF247_2_EST1", caratula.inf247_2_est1);
//		content.put("INF247_2_EST2", caratula.inf247_2_est2);
//		content.put("INF247_2_EST3", caratula.inf247_2_est3);
//		content.put("INF247_2_EST4", caratula.inf247_2_est4);
//		content.put("INF247_3", caratula.inf247_3);
//		content.put("INF247_3_EST1", caratula.inf247_3_est1);
//		content.put("INF247_3_EST2", caratula.inf247_3_est2);
//		content.put("INF247_3_EST3", caratula.inf247_3_est3);
//		content.put("INF247_3_EST4", caratula.inf247_3_est4);
//		content.put("INF247_4", caratula.inf247_4);
//		content.put("INF247_4_EST1", caratula.inf247_4_est1);
//		content.put("INF247_4_EST3", caratula.inf247_4_est3);
//		content.put("INF247_4_EST4", caratula.inf247_4_est4);
//		content.put("INF247_5", caratula.inf247_5);
//		content.put("INF247_5_O", caratula.inf247_5_o);
//		content.put("INF247_5_EST1", caratula.inf247_5_est1);
//		content.put("INF247_5_EST2", caratula.inf247_5_est2);
//		content.put("INF247_5_EST3", caratula.inf247_5_est3);
//		content.put("INF247_5_EST4", caratula.inf247_5_est4);
//		content.put("INF247_TOT_EST1", caratula.inf247_tot_est1);
//		content.put("INF247_TOT_EST2", caratula.inf247_tot_est2);
//		content.put("INF247_TOT_EST3", caratula.inf247_tot_est3);
//		content.put("INF247_TOT_EST4", caratula.inf247_tot_est4);
//		content.put("INF248_1", caratula.inf248_1);
//		content.put("INF248_1_TOT", caratula.inf248_1_tot);
//		content.put("INF248_1_REP", caratula.inf248_1_rep);
//		content.put("INF248_1_REEM", caratula.inf248_1_reem);
//		content.put("INF248_2", caratula.inf248_2);
//		content.put("INF248_2_TOT", caratula.inf248_2_tot);
//		content.put("INF248_2_REP", caratula.inf248_2_rep);
//		content.put("INF248_2_REEM", caratula.inf248_2_reem);
//		content.put("INF248_3", caratula.inf248_3);
//		content.put("INF248_3_TOT", caratula.inf248_3_tot);
//		content.put("INF248_3_REP", caratula.inf248_3_rep);
//		content.put("INF248_3_REEM", caratula.inf248_3_reem);
//		content.put("INF248_4", caratula.inf248_4);
//		content.put("INF248_4_TOT", caratula.inf248_4_tot);
//		content.put("INF248_4_REP", caratula.inf248_4_rep);
//		content.put("INF248_4_REEM", caratula.inf248_4_reem);
//		content.put("INF248_5", caratula.inf248_5);
//		content.put("INF248_5_TOT", caratula.inf248_5_tot);
//		content.put("INF248_5_REP", caratula.inf248_5_rep);
//		content.put("INF248_5_REEM", caratula.inf248_5_reem);
//		content.put("INF248_5_O", caratula.inf248_5_o);
//		content.put("INF248_TOT", caratula.inf248_tot);
//		content.put("INF248_REP", caratula.inf248_rep);
//		content.put("INF248_REEM", caratula.inf248_reem);
//		content.put("INF249", caratula.inf249);
//		content.put("INF250", caratula.inf250);
//		content.put("INF250_O", caratula.inf250_o);
//		content.put("INF251", caratula.inf251);
//		content.put("INF252", caratula.inf252);
//		content.put("INF253_1", caratula.inf253_1);
//		content.put("INF253_1_1", caratula.inf253_1_1);
//		content.put("INF253_1_2", caratula.inf253_1_2);
//		content.put("INF253_1_3", caratula.inf253_1_3);
//		content.put("INF253_2", caratula.inf253_2);
//		content.put("INF253_2_1", caratula.inf253_2_1);
//		content.put("INF253_2_2", caratula.inf253_2_2);
//		content.put("INF253_2_3", caratula.inf253_2_3);
//		content.put("INF253_3", caratula.inf253_3);
//		content.put("INF253_3_1", caratula.inf253_3_1);
//		content.put("INF253_3_2", caratula.inf253_3_2);
//		content.put("INF253_3_3", caratula.inf253_3_3);
//		content.put("INF253_4", caratula.inf253_4);
//		content.put("INF253_4_1", caratula.inf253_4_1);
//		content.put("INF253_4_2", caratula.inf253_4_2);
//		content.put("INF253_4_3", caratula.inf253_4_3);
//		content.put("INF253_5", caratula.inf253_5);
//		content.put("INF253_5_1", caratula.inf253_5_1);
//		content.put("INF253_5_2", caratula.inf253_5_2);
//		content.put("INF253_5_3", caratula.inf253_5_3);
//		content.put("INF253_6", caratula.inf253_6);
//		content.put("INF253_6_1", caratula.inf253_6_1);
//		content.put("INF253_6_2", caratula.inf253_6_2);
//		content.put("INF253_6_3", caratula.inf253_6_3);
//		content.put("INF253_6_O", caratula.inf253_6_o);
//		content.put("INF253_TOT_1", caratula.inf253_tot_1);
//		content.put("INF253_TOT_2", caratula.inf253_tot_2);
//		content.put("INF253_TOT_3", caratula.inf253_tot_3);
//		content.put("OBS_CAP_200_6", caratula.obs_cap_200_6);
//		content.put("INF254_1", caratula.inf254_1);
//		content.put("INF255_1", caratula.inf255_1);
//		content.put("INF254_2", caratula.inf254_2);
//		content.put("INF255_2", caratula.inf255_2);
//		content.put("INF254_3", caratula.inf254_3);
//		content.put("INF255_3", caratula.inf255_3);
//		content.put("INF254_4", caratula.inf254_4);
//		content.put("INF255_4", caratula.inf255_4);
//		content.put("INF254_5", caratula.inf254_5);
//		content.put("INF255_5", caratula.inf255_5);
//		content.put("INF254_6", caratula.inf254_6);
//		content.put("INF255_6", caratula.inf255_6);
//		content.put("INF254_7", caratula.inf254_7);
//		content.put("INF255_7", caratula.inf255_7);
//		content.put("INF254_8", caratula.inf254_8);
//		content.put("INF255_8", caratula.inf255_8);
//		content.put("INF254_9", caratula.inf254_9);
//		content.put("INF255_9", caratula.inf255_9);
//		content.put("INF254_10", caratula.inf254_10);
//		content.put("INF255_10", caratula.inf255_10);
//		content.put("INF254_11", caratula.inf254_11);
//		content.put("INF255_11", caratula.inf255_11);
//		content.put("INF254_12", caratula.inf254_12);
//		content.put("INF255_12", caratula.inf255_12);
//		content.put("INF254_13", caratula.inf254_13);
//		content.put("INF255_13", caratula.inf255_13);
//		content.put("INF254_14", caratula.inf254_14);
//		content.put("INF255_14", caratula.inf255_14);
//		content.put("INF254_15", caratula.inf254_15);
//		content.put("INF255_15", caratula.inf255_15);
//		content.put("INF254_16", caratula.inf254_16);
//		content.put("INF255_16", caratula.inf255_16);
//		content.put("INF254_17", caratula.inf254_17);
//		content.put("INF255_17", caratula.inf255_17);
//		content.put("INF254_18", caratula.inf254_18);
//		content.put("INF255_18", caratula.inf255_18);
//		content.put("INF254_19", caratula.inf254_19);
//		content.put("INF255_19", caratula.inf255_19);
//		content.put("INF254_20", caratula.inf254_20);
//		content.put("INF255_20", caratula.inf255_20);
//		content.put("INF254_21", caratula.inf254_21);
//		content.put("INF255_21", caratula.inf255_21);
//		content.put("INF254_22", caratula.inf254_22);
//		content.put("INF255_22", caratula.inf255_22);
//		content.put("INF254_23", caratula.inf254_23);
//		content.put("INF255_23", caratula.inf255_23);
//		content.put("INF254_24", caratula.inf254_24);
//		content.put("INF255_24", caratula.inf255_24);
//		content.put("INF254_25", caratula.inf254_25);
//		content.put("INF255_25", caratula.inf255_25);
//		content.put("INF254_26", caratula.inf254_26);
//		content.put("INF255_26", caratula.inf255_26);
//		content.put("INF254_27", caratula.inf254_27);
//		content.put("INF255_27", caratula.inf255_27);
//		content.put("INF254_28", caratula.inf254_28);
//		content.put("INF255_28", caratula.inf255_28);
//		content.put("INF254_29", caratula.inf254_29);
//		content.put("INF255_29", caratula.inf255_29);
//		content.put("INF254_30", caratula.inf254_30);
//		content.put("INF255_30", caratula.inf255_30);
//		content.put("INF254_31", caratula.inf254_31);
//		content.put("INF255_31", caratula.inf255_31);
//		content.put("INF254_32", caratula.inf254_32);
//		content.put("INF255_32", caratula.inf255_32);
//		content.put("INF254_33", caratula.inf254_33);
//		content.put("INF255_33", caratula.inf255_33);
//		content.put("INF254_34", caratula.inf254_34);
//		content.put("INF255_34", caratula.inf255_34);
//		content.put("INF254_35", caratula.inf254_35);
//		content.put("INF255_35", caratula.inf255_35);
//		content.put("INF254_36", caratula.inf254_36);
//		content.put("INF255_36", caratula.inf255_36);
//		content.put("INF254_37", caratula.inf254_37);
//		content.put("INF255_37", caratula.inf255_37);
//		content.put("INF254_38", caratula.inf254_38);
//		content.put("INF255_38", caratula.inf255_38);
//		content.put("INF254_39", caratula.inf254_39);
//		content.put("INF255_39", caratula.inf255_39);
//		content.put("INF254_40", caratula.inf254_40);
//		content.put("INF255_40", caratula.inf255_40);
//		content.put("INF254_41", caratula.inf254_41);
//		content.put("INF255_41", caratula.inf255_41);
//		content.put("INF254_42", caratula.inf254_42);
//		content.put("INF255_42", caratula.inf255_42);
//		content.put("INF254_43", caratula.inf254_43);
//		content.put("INF255_43", caratula.inf255_43);
//		content.put("INF254_44", caratula.inf254_44);
//		content.put("INF255_44", caratula.inf255_44);
//		content.put("INF254_45", caratula.inf254_45);
//		content.put("INF255_45", caratula.inf255_45);
//		content.put("INF254_46", caratula.inf254_46);
//		content.put("INF255_46", caratula.inf255_46);
//		content.put("INF254_47", caratula.inf254_47);
//		content.put("INF255_47", caratula.inf255_47);
//		content.put("INF254_48", caratula.inf254_48);
//		content.put("INF255_48", caratula.inf255_48);
//		content.put("INF254_49", caratula.inf254_49);
//		content.put("INF254_49_O", caratula.inf254_49_o);
//		content.put("INF255_49", caratula.inf255_49);
//		content.put("OBS_CAP_200_7", caratula.obs_cap_200_7);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_01_200", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveINF_Capitulo300(INF_Cap300 caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("INF301", caratula.inf301);
//		content.put("INF301_TOTAL", caratula.inf301_total);
//		content.put("INF301A", caratula.inf301a);
//		content.put("INF301B", caratula.inf301b);
//		content.put("INF301B_O", caratula.inf301b_o);
//		
//		content.put("INF302_1", caratula.inf302_1);
//		content.put("INF303_1", caratula.inf303_1);
//		content.put("INF304A_1", caratula.inf304a_1);
//		content.put("INF304B_1", caratula.inf304b_1);
//		content.put("INF305C_1", caratula.inf305c_1);
//		content.put("INF305D_1", caratula.inf305d_1);
//		content.put("INF305A_1", caratula.inf305a_1);
//		
//		content.put("INF302_2", caratula.inf302_2);
//		content.put("INF303_2", caratula.inf303_2);
//		content.put("INF304A_2", caratula.inf304a_2);
//		content.put("INF304B_2", caratula.inf304b_2);
//		content.put("INF305C_2", caratula.inf305c_2);
//		content.put("INF305D_2", caratula.inf305d_2);
//		content.put("INF305A_2", caratula.inf305a_2);
//		
//		content.put("INF302_3", caratula.inf302_3);
//		content.put("INF303_3", caratula.inf303_3);
//		content.put("INF304A_3", caratula.inf304a_3);
//		content.put("INF304B_3", caratula.inf304b_3);
//		content.put("INF305C_3", caratula.inf305c_3);
//		content.put("INF305D_3", caratula.inf305d_3);
//		content.put("INF305A_3", caratula.inf305a_3);
//		
//		content.put("INF302_4", caratula.inf302_4);
//		content.put("INF303_4", caratula.inf303_4);
//		content.put("INF304A_4", caratula.inf304a_4);
//		content.put("INF304B_4", caratula.inf304b_4);
//		content.put("INF305C_4", caratula.inf305c_4);
//		content.put("INF305D_4", caratula.inf305d_4);
//		content.put("INF305A_4", caratula.inf305a_4);
//		
//		content.put("INF302_5", caratula.inf302_5);
//		content.put("INF303_5", caratula.inf303_5);
//		content.put("INF304A_5", caratula.inf304a_5);
//		content.put("INF304B_5", caratula.inf304b_5);
//		content.put("INF305C_5", caratula.inf305c_5);
//		content.put("INF305D_5", caratula.inf305d_5);
//		content.put("INF305A_5", caratula.inf305a_5);
//		
//		content.put("INF302_6", caratula.inf302_6);
//		content.put("INF303_6", caratula.inf303_6);
//		content.put("INF304A_6", caratula.inf304a_6);
//		content.put("INF304B_6", caratula.inf304b_6);
//		content.put("INF305C_6", caratula.inf305c_6);
//		content.put("INF305D_6", caratula.inf305d_6);
//		content.put("INF305A_6", caratula.inf305a_6);
//		
//		content.put("INF302_7", caratula.inf302_7);
//		content.put("INF303_7", caratula.inf303_7);
//		content.put("INF304A_7", caratula.inf304a_7);
//		content.put("INF304B_7", caratula.inf304b_7);
//		content.put("INF305C_7", caratula.inf305c_7);
//		content.put("INF305D_7", caratula.inf305d_7);
//		content.put("INF305A_7", caratula.inf305a_7);
//		
//		content.put("INF302_8", caratula.inf302_8);
//		content.put("INF302_8_O", caratula.inf302_8_o);
//		content.put("INF303_8", caratula.inf303_8);
//		content.put("INF304A_8", caratula.inf304a_8);
//		content.put("INF304B_8", caratula.inf304b_8);
//		content.put("INF305C_8", caratula.inf305c_8);
//		content.put("INF305D_8", caratula.inf305d_8);
//		content.put("INF305A_8", caratula.inf305a_8);
//		
//		content.put("INF302_9", caratula.inf302_9);
//		content.put("INF303_9", caratula.inf303_9);
//		content.put("INF304A_9", caratula.inf304a_9);
//		content.put("INF304B_9", caratula.inf304b_9);
//		content.put("INF305C_9", caratula.inf305c_9);
//		content.put("INF305D_9", caratula.inf305d_9);
//		content.put("INF305A_9", caratula.inf305a_9);
//		
//		content.put("INF302_10", caratula.inf302_10);
//		content.put("INF303_10", caratula.inf303_10);
//		content.put("INF304A_10", caratula.inf304a_10);
//		content.put("INF304B_10", caratula.inf304b_10);
//		content.put("INF305C_10", caratula.inf305c_10);
//		content.put("INF305D_10", caratula.inf305d_10);
//		content.put("INF305A_10", caratula.inf305a_10);
//		
//		content.put("INF302_11", caratula.inf302_11);
//		content.put("INF303_11", caratula.inf303_11);
//		content.put("INF304A_11", caratula.inf304a_11);
//		content.put("INF304B_11", caratula.inf304b_11);
//		content.put("INF305C_11", caratula.inf305c_11);
//		content.put("INF305D_11", caratula.inf305d_11);
//		content.put("INF305A_11", caratula.inf305a_11);
//		
//		content.put("INF306_1", caratula.inf306_1);
//		content.put("INF307_1", caratula.inf307_1);
//		content.put("INF308A_1", caratula.inf308a_1);
//		content.put("INF308B_1", caratula.inf308b_1);
//		content.put("INF309C_1", caratula.inf309c_1);
//		content.put("INF309D_1", caratula.inf309d_1);
//		content.put("INF309A_1", caratula.inf309a_1);
//		content.put("INF310_1", caratula.inf310_1);
//		
//		content.put("INF306_2", caratula.inf306_2);
//		content.put("INF307_2", caratula.inf307_2);
//		content.put("INF308A_2", caratula.inf308a_2);
//		content.put("INF308B_2", caratula.inf308b_2);
//		content.put("INF309C_2", caratula.inf309c_2);
//		content.put("INF309D_2", caratula.inf309d_2);
//		content.put("INF309A_2", caratula.inf309a_2);
//		content.put("INF310_2", caratula.inf310_2);
//		
//		content.put("INF306_3", caratula.inf306_3);
//		content.put("INF307_3", caratula.inf307_3);
//		content.put("INF308A_3", caratula.inf308a_3);
//		content.put("INF308B_3", caratula.inf308b_3);
//		content.put("INF309C_3", caratula.inf309c_3);
//		content.put("INF309D_3", caratula.inf309d_3);
//		content.put("INF309A_3", caratula.inf309a_3);
//		content.put("INF310_3", caratula.inf310_3);
//		
//		content.put("INF306_4", caratula.inf306_4);
//		content.put("INF307_4", caratula.inf307_4);
//		content.put("INF308A_4", caratula.inf308a_4);
//		content.put("INF308B_4", caratula.inf308b_4);
//		content.put("INF309C_4", caratula.inf309c_4);
//		content.put("INF309D_4", caratula.inf309d_4);
//		content.put("INF309A_4", caratula.inf309a_4);
//		content.put("INF310_4", caratula.inf310_4);
//		
//		content.put("INF306_5", caratula.inf306_5);
//		content.put("INF307_5", caratula.inf307_5);
//		content.put("INF308A_5", caratula.inf308a_5);
//		content.put("INF308B_5", caratula.inf308b_5);
//		content.put("INF309C_5", caratula.inf309c_5);
//		content.put("INF309D_5", caratula.inf309d_5);
//		content.put("INF309A_5", caratula.inf309a_5);
//		content.put("INF310_5", caratula.inf310_5);
//		
//		content.put("INF306_6", caratula.inf306_6);
//		content.put("INF307_6", caratula.inf307_6);
//		content.put("INF308A_6", caratula.inf308a_6);
//		content.put("INF308B_6", caratula.inf308b_6);
//		content.put("INF309C_6", caratula.inf309c_6);
//		content.put("INF309D_6", caratula.inf309d_6);
//		content.put("INF309A_6", caratula.inf309a_6);
//		content.put("INF310_6", caratula.inf310_6);
//		
//		content.put("INF306_7", caratula.inf306_7);
//		content.put("INF307_7", caratula.inf307_7);
//		content.put("INF308A_7", caratula.inf308a_7);
//		content.put("INF308B_7", caratula.inf308b_7);
//		content.put("INF309C_7", caratula.inf309c_7);
//		content.put("INF309D_7", caratula.inf309d_7);
//		content.put("INF309A_7", caratula.inf309a_7);
//		content.put("INF310_7", caratula.inf310_7);
//		
//		content.put("INF306_8", caratula.inf306_8);
//		content.put("INF306_8_O", caratula.inf306_8_o);
//		content.put("INF307_8", caratula.inf307_8);
//		content.put("INF308A_8", caratula.inf308a_8);
//		content.put("INF308B_8", caratula.inf308b_8);
//		content.put("INF309C_8", caratula.inf309c_8);
//		content.put("INF309D_8", caratula.inf309d_8);
//		content.put("INF309A_8", caratula.inf309a_8);
//		content.put("INF310_8", caratula.inf310_8);
//		
//		content.put("INF329_1", caratula.inf329_1);
//		content.put("INF329_2", caratula.inf329_2);
//		content.put("INF329_3", caratula.inf329_3);
//		content.put("INF329_4", caratula.inf329_4);
//		content.put("INF329_5", caratula.inf329_5);
//		content.put("INF329_6", caratula.inf329_6);
//		content.put("INF329_7", caratula.inf329_7);
//		content.put("INF329_7_O", caratula.inf329_7_o);
//		
//		content.put("INF330_1_1", caratula.inf330_1_1);
//		content.put("INF330_2_1", caratula.inf330_2_1);
//		content.put("INF330_3_1", caratula.inf330_3_1);
//		content.put("INF330_4_1", caratula.inf330_4_1);
//		content.put("INF330_5_1", caratula.inf330_5_1);
//		content.put("INF330_5_1_O", caratula.inf330_5_1_o);
//		
//		content.put("INF330_1_2", caratula.inf330_1_2);
//		content.put("INF330_2_2", caratula.inf330_2_2);
//		content.put("INF330_3_2", caratula.inf330_3_2);
//		content.put("INF330_4_2", caratula.inf330_4_2);
//		content.put("INF330_5_2", caratula.inf330_5_2);
//		content.put("INF330_5_2_O", caratula.inf330_5_2_o);
//		
//		content.put("INF330_1_3", caratula.inf330_1_3);
//		content.put("INF330_2_3", caratula.inf330_2_3);
//		content.put("INF330_3_3", caratula.inf330_3_3);
//		content.put("INF330_4_3", caratula.inf330_4_3);
//		content.put("INF330_5_3", caratula.inf330_5_3);
//		content.put("INF330_5_3_O", caratula.inf330_5_3_o);
//		
//		content.put("INF330_1_4", caratula.inf330_1_4);
//		content.put("INF330_2_4", caratula.inf330_2_4);
//		content.put("INF330_3_4", caratula.inf330_3_4);
//		content.put("INF330_4_4", caratula.inf330_4_4);
//		content.put("INF330_5_4", caratula.inf330_5_4);
//		content.put("INF330_5_4_O", caratula.inf330_5_4_o);
//		
//		content.put("INF330_1_5", caratula.inf330_1_5);
//		content.put("INF330_2_5", caratula.inf330_2_5);
//		content.put("INF330_3_5", caratula.inf330_3_5);
//		content.put("INF330_4_5", caratula.inf330_4_5);
//		content.put("INF330_5_5", caratula.inf330_5_5);
//		content.put("INF330_5_5_O", caratula.inf330_5_5_o);
//		
//		content.put("INF330_1_6", caratula.inf330_1_6);
//		content.put("INF330_2_6", caratula.inf330_2_6);
//		content.put("INF330_3_6", caratula.inf330_3_6);
//		content.put("INF330_4_6", caratula.inf330_4_6);
//		content.put("INF330_5_6", caratula.inf330_5_6);
//		content.put("INF330_5_6_O", caratula.inf330_5_6_o);
//		
//		content.put("INF330_1_7", caratula.inf330_1_7);
//		content.put("INF330_2_7", caratula.inf330_2_7);
//		content.put("INF330_3_7", caratula.inf330_3_7);
//		content.put("INF330_4_7", caratula.inf330_4_7);
//		content.put("INF330_5_7", caratula.inf330_5_7);
//		content.put("INF330_5_7_O", caratula.inf330_5_7_o);
//		
//		content.put("INF331", caratula.inf331);
//		content.put("INF331A_1", caratula.inf331a_1);
//		content.put("INF331A_2", caratula.inf331a_2);
//		content.put("INF331A_3", caratula.inf331a_3);
//		content.put("INF331A_4", caratula.inf331a_4);
//		content.put("INF331A_5", caratula.inf331a_5);
//		content.put("INF331A_6", caratula.inf331a_6);
//		content.put("INF331A_6_O", caratula.inf331a_6_o);
//		
//		content.put("INF332_1", caratula.inf332_1);
//		content.put("INF332_2", caratula.inf332_2);
//		content.put("INF332_3", caratula.inf332_3);
//		content.put("INF332_4", caratula.inf332_4);
//		content.put("INF332_5", caratula.inf332_5);
//		content.put("INF332_6", caratula.inf332_6);
//		
//		content.put("INF333", caratula.inf333);
//		
//		content.put("INF334_1", caratula.inf334_1);
//		content.put("INF334_2", caratula.inf334_2);
//		content.put("INF334_3", caratula.inf334_3);
//		content.put("INF334_4", caratula.inf334_4);
//		content.put("INF334_5", caratula.inf334_5);
//		content.put("INF334_6", caratula.inf334_6);
//		content.put("INF334_7", caratula.inf334_7);
//		content.put("INF334_8", caratula.inf334_8);
//		content.put("INF334_9", caratula.inf334_9);
//		content.put("INF334_10", caratula.inf334_10);
//		content.put("INF334_11", caratula.inf334_11);
//		
//		content.put("INF335A_1", caratula.inf335a_1);
//		content.put("INF335B_1", caratula.inf335b_1);
//		content.put("INF335C_1", caratula.inf335c_1);
//		content.put("INF335TOTAL_1", caratula.inf335total_1);
//		
//		content.put("INF335A_2", caratula.inf335a_2);
//		content.put("INF335B_2", caratula.inf335b_2);
//		content.put("INF335C_2", caratula.inf335c_2);
//		content.put("INF335TOTAL_2", caratula.inf335total_2);
//		
//		content.put("INF335A_3", caratula.inf335a_3);
//		content.put("INF335B_3", caratula.inf335b_3);
//		content.put("INF335C_3", caratula.inf335c_3);
//		content.put("INF335TOTAL_3", caratula.inf335total_3);
//		
//		content.put("INF335A_4", caratula.inf335a_4);
//		content.put("INF335B_4", caratula.inf335b_4);
//		content.put("INF335C_4", caratula.inf335c_4);
//		content.put("INF335TOTAL_4", caratula.inf335total_4);
//		
//		content.put("INF335A_5", caratula.inf335a_5);
//		content.put("INF335B_5", caratula.inf335b_5);
//		content.put("INF335C_5", caratula.inf335c_5);
//		content.put("INF335TOTAL_5", caratula.inf335total_5);
//		
//		content.put("INF335A_6", caratula.inf335a_6);
//		content.put("INF335B_6", caratula.inf335b_6);
//		content.put("INF335C_6", caratula.inf335c_6);
//		content.put("INF335TOTAL_6", caratula.inf335total_6);
//		
//		content.put("INF335A_7", caratula.inf335a_7);
//		content.put("INF335B_7", caratula.inf335b_7);
//		content.put("INF335C_7", caratula.inf335c_7);
//		content.put("INF335TOTAL_7", caratula.inf335total_7);
//		
//		content.put("INF335A_8", caratula.inf335a_8);
//		content.put("INF335B_8", caratula.inf335b_8);
//		content.put("INF335C_8", caratula.inf335c_8);
//		content.put("INF335TOTAL_8", caratula.inf335total_8);
//		
//		content.put("INF335A_9", caratula.inf335a_9);
//		content.put("INF335B_9", caratula.inf335b_9);
//		content.put("INF335C_9", caratula.inf335c_9);
//		content.put("INF335TOTAL_9", caratula.inf335total_9);
//		
//		content.put("INF335A_10", caratula.inf335a_10);
//		content.put("INF335B_10", caratula.inf335b_10);
//		content.put("INF335C_10", caratula.inf335c_10);
//		content.put("INF335TOTAL_10", caratula.inf335total_10);
//		
//		content.put("INF335A_11", caratula.inf335a_11);
//		content.put("INF335B_11", caratula.inf335b_11);
//		content.put("INF335C_11", caratula.inf335c_11);
//		content.put("INF335TOTAL_11", caratula.inf335total_11);
//		
//		content.put("INF336_1", caratula.inf336_1);
//		content.put("INF336_2", caratula.inf336_2);
//		content.put("INF336_3", caratula.inf336_3);
//		content.put("INF336_4", caratula.inf336_4);
//		content.put("INF336_5", caratula.inf336_5);
//		content.put("INF336_6", caratula.inf336_6);
//		content.put("INF336_6_O", caratula.inf336_6_o);
//		
//		content.put("INF337_1", caratula.inf337_1);
//		content.put("INF338_1", caratula.inf338_1);
//		content.put("INF339_1", caratula.inf339_1);
//		content.put("INF340_1_1", caratula.inf340_1_1);
//		content.put("INF340_2_1", caratula.inf340_2_1);
//		content.put("INF340_3_1", caratula.inf340_3_1);
//		content.put("INF341_1", caratula.inf341_1);
//		content.put("INF342_1_1", caratula.inf342_1_1);
//		content.put("INF342_2_1", caratula.inf342_2_1);
//		content.put("INF342_3_1", caratula.inf342_3_1);
//		content.put("INF342_4_1", caratula.inf342_4_1);
//		content.put("INF342_5_1", caratula.inf342_5_1);
//		content.put("INF342_5_1_O", caratula.inf342_5_1_o);
//		content.put("INF342_6_1", caratula.inf342_6_1);
//		content.put("INF343_1_1", caratula.inf343_1_1);
//		content.put("INF343_2_1", caratula.inf343_2_1);
//		content.put("INF343_3_1", caratula.inf343_3_1);
//		content.put("INF343_3_1_O", caratula.inf343_3_1_o);
//		content.put("INF343_4_1", caratula.inf343_4_1);
//		
//		content.put("INF337_2", caratula.inf337_2);
//		content.put("INF338_2", caratula.inf338_2);
//		content.put("INF339_2", caratula.inf339_2);
//		content.put("INF340_1_2", caratula.inf340_1_2);
//		content.put("INF340_2_2", caratula.inf340_2_2);
//		content.put("INF340_3_2", caratula.inf340_3_2);
//		content.put("INF341_2", caratula.inf341_2);
//		content.put("INF342_1_2", caratula.inf342_1_2);
//		content.put("INF342_2_2", caratula.inf342_2_2);
//		content.put("INF342_3_2", caratula.inf342_3_2);
//		content.put("INF342_4_2", caratula.inf342_4_2);
//		content.put("INF342_5_2", caratula.inf342_5_2);
//		content.put("INF342_5_2_O", caratula.inf342_5_2_o);
//		content.put("INF342_6_2", caratula.inf342_6_2);
//		content.put("INF343_1_2", caratula.inf343_1_2);
//		content.put("INF343_2_2", caratula.inf343_2_2);
//		content.put("INF343_3_2", caratula.inf343_3_2);
//		content.put("INF343_3_2_O", caratula.inf343_3_2_o);
//		content.put("INF343_4_2", caratula.inf343_4_2);
//		
//		content.put("INF337_3", caratula.inf337_3);
//		content.put("INF338_3", caratula.inf338_3);
//		content.put("INF339_3", caratula.inf339_3);
//		content.put("INF340_1_3", caratula.inf340_1_3);
//		content.put("INF340_2_3", caratula.inf340_2_3);
//		content.put("INF340_3_3", caratula.inf340_3_3);
//		content.put("INF341_3", caratula.inf341_3);
//		content.put("INF342_1_3", caratula.inf342_1_3);
//		content.put("INF342_2_3", caratula.inf342_2_3);
//		content.put("INF342_3_3", caratula.inf342_3_3);
//		content.put("INF342_4_3", caratula.inf342_4_3);
//		content.put("INF342_5_3", caratula.inf342_5_3);
//		content.put("INF342_5_3_O", caratula.inf342_5_3_o);
//		content.put("INF342_6_3", caratula.inf342_6_3);
//		content.put("INF343_1_3", caratula.inf343_1_3);
//		content.put("INF343_2_3", caratula.inf343_2_3);
//		content.put("INF343_3_3", caratula.inf343_3_3);
//		content.put("INF343_3_3_O", caratula.inf343_3_3_o);
//		content.put("INF343_4_3", caratula.inf343_4_3);
//		
//		content.put("INF337_4", caratula.inf337_4);
//		content.put("INF338_4", caratula.inf338_4);
//		content.put("INF339_4", caratula.inf339_4);
//		content.put("INF340_1_4", caratula.inf340_1_4);
//		content.put("INF340_2_4", caratula.inf340_2_4);
//		content.put("INF340_3_4", caratula.inf340_3_4);
//		content.put("INF341_4", caratula.inf341_4);
//		content.put("INF342_1_4", caratula.inf342_1_4);
//		content.put("INF342_2_4", caratula.inf342_2_4);
//		content.put("INF342_3_4", caratula.inf342_3_4);
//		content.put("INF342_4_4", caratula.inf342_4_4);
//		content.put("INF342_5_4", caratula.inf342_5_4);
//		content.put("INF342_5_4_O", caratula.inf342_5_4_o);
//		content.put("INF342_6_4", caratula.inf342_6_4);
//		content.put("INF343_1_4", caratula.inf343_1_4);
//		content.put("INF343_2_4", caratula.inf343_2_4);
//		content.put("INF343_3_4", caratula.inf343_3_4);
//		content.put("INF343_3_4_O", caratula.inf343_3_4_o);
//		content.put("INF343_4_4", caratula.inf343_4_4);
//		
//		content.put("INF337_5", caratula.inf337_5);
//		content.put("INF338_5", caratula.inf338_5);
//		content.put("INF339_5", caratula.inf339_5);
//		content.put("INF340_1_5", caratula.inf340_1_5);
//		content.put("INF340_2_5", caratula.inf340_2_5);
//		content.put("INF340_3_5", caratula.inf340_3_5);
//		content.put("INF341_5", caratula.inf341_5);
//		content.put("INF342_1_5", caratula.inf342_1_5);
//		content.put("INF342_2_5", caratula.inf342_2_5);
//		content.put("INF342_3_5", caratula.inf342_3_5);
//		content.put("INF342_4_5", caratula.inf342_4_5);
//		content.put("INF342_5_5", caratula.inf342_5_5);
//		content.put("INF342_5_5_O", caratula.inf342_5_5_o);
//		content.put("INF342_6_5", caratula.inf342_6_5);
//		content.put("INF343_1_5", caratula.inf343_1_5);
//		content.put("INF343_2_5", caratula.inf343_2_5);
//		content.put("INF343_3_5", caratula.inf343_3_5);
//		content.put("INF343_3_5_O", caratula.inf343_3_5_o);
//		content.put("INF343_4_5", caratula.inf343_4_5);
//		
//		content.put("INF337_6", caratula.inf337_6);
//		content.put("INF338_6", caratula.inf338_6);
//		content.put("INF339_6", caratula.inf339_6);
//		content.put("INF340_1_6", caratula.inf340_1_6);
//		content.put("INF340_2_6", caratula.inf340_2_6);
//		content.put("INF340_3_6", caratula.inf340_3_6);
//		content.put("INF341_6", caratula.inf341_6);
//		content.put("INF342_1_6", caratula.inf342_1_6);
//		content.put("INF342_2_6", caratula.inf342_2_6);
//		content.put("INF342_3_6", caratula.inf342_3_6);
//		content.put("INF342_4_6", caratula.inf342_4_6);
//		content.put("INF342_5_6", caratula.inf342_5_6);
//		content.put("INF342_5_6_O", caratula.inf342_5_6_o);
//		content.put("INF342_6_6", caratula.inf342_6_6);
//		content.put("INF343_1_6", caratula.inf343_1_6);
//		content.put("INF343_2_6", caratula.inf343_2_6);
//		content.put("INF343_3_6", caratula.inf343_3_6);
//		content.put("INF343_3_6_O", caratula.inf343_3_6_o);
//		content.put("INF343_4_6", caratula.inf343_4_6);
//		content.put("INFOBS_300_SECCION_VII", caratula.infobs_300_seccion_vii);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_01_300", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveINF_Capitulo300_III(INF_Cap300_III caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("ID_COMPUTO", caratula.id_computo);
//		content.put("INF311", caratula.inf311);
//		content.put("INF312", caratula.inf312);
//		content.put("INF313", caratula.inf313);
//		content.put("INF314_AA", caratula.inf314_aa);
//		content.put("INF314_MM", caratula.inf314_mm);
//		content.put("INF314_SS", caratula.inf314_ss);
//		content.put("INF315", caratula.inf315);
//		content.put("INF315_O", caratula.inf315_o);
//		content.put("INF316", caratula.inf316);
//		content.put("INF317", caratula.inf317);
//		content.put("INF318", caratula.inf318);
//		content.put("INF319", caratula.inf319);
//		content.put("INF320", caratula.inf320);
//		content.put("INF321", caratula.inf321);
//		content.put("INF321_O", caratula.inf321_o);
//		content.put("INF322", caratula.inf322);
//		content.put("INF323", caratula.inf323);
//		content.put("INF324_1", caratula.inf324_1);
//		content.put("INF324_2", caratula.inf324_2);
//		content.put("INF324_3", caratula.inf324_3);
//		content.put("INF324_4", caratula.inf324_4);
//		content.put("INF324_O", caratula.inf324_o);
//		content.put("INF325", caratula.inf325);
//		content.put("INF326", caratula.inf326);
//		content.put("INF326_O", caratula.inf326_o);
//		content.put("INF327", caratula.inf327);
//		content.put("INF328", caratula.inf328);
//		content.put("INF328_O", caratula.inf328_o);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_01_300_III", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveINF_Capitulo400(INF_Cap400 caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("INF401_1", caratula.inf401_1);
//		content.put("INF401_2", caratula.inf401_2);
//		content.put("INF401_3", caratula.inf401_3);
//		content.put("INF401_4", caratula.inf401_4);
//		content.put("INF401_5", caratula.inf401_5);
//		content.put("INF401_6", caratula.inf401_6);
//		content.put("INF401_7", caratula.inf401_7);
//		content.put("INF401_8", caratula.inf401_8);
//		content.put("INF401_9", caratula.inf401_9);
//		content.put("INF401_10", caratula.inf401_10);
//		content.put("INF401_11", caratula.inf401_11);
//		content.put("INF401_12", caratula.inf401_12);
//		content.put("INF401_13", caratula.inf401_13);
//		content.put("INF401_14", caratula.inf401_14);
//		content.put("INF401_15", caratula.inf401_15);
//		content.put("INF401_16", caratula.inf401_16);
//		content.put("INF401_17", caratula.inf401_17);
//		content.put("INF401_18", caratula.inf401_18);
//		content.put("INF401_19", caratula.inf401_19);
//		content.put("INF401_20", caratula.inf401_20);
//		content.put("INF401_21", caratula.inf401_21);
//		content.put("INF401_22", caratula.inf401_22);
//		content.put("INF401_23", caratula.inf401_23);
//		content.put("INF401_24", caratula.inf401_24);
//		content.put("INF401_25", caratula.inf401_25);
//		content.put("OBS_CAP_400_1", caratula.obs_cap_400_1);
//		content.put("INF402", caratula.inf402);
//		content.put("INF403", caratula.inf403);
//		content.put("INF404", caratula.inf404);
//		content.put("INF404A", caratula.inf404a);
//		content.put("INF404B_1", caratula.inf404b_1);
//		content.put("INF404B_2", caratula.inf404b_2);
//		content.put("INF404B_3", caratula.inf404b_3);
//		content.put("INF404B_4", caratula.inf404b_4);
//		content.put("INF404B_5", caratula.inf404b_5);
//		content.put("INF404B_6", caratula.inf404b_6);
//		content.put("INF404B_7", caratula.inf404b_7);
//		content.put("INF404B_7_O", caratula.inf404b_7_o);
//		content.put("INF405", caratula.inf405);
//		content.put("INF406", caratula.inf406);
//		content.put("INF407", caratula.inf407);
//		content.put("INF408", caratula.inf408);
//		content.put("INF409", caratula.inf409);
//		content.put("INF410", caratula.inf410);
//		content.put("INF410_D", caratula.inf410_d);
//		content.put("INF410_PAT", caratula.inf410_pat);
//		content.put("INF410_M", caratula.inf410_m);
//		content.put("INF411", caratula.inf411);
//		content.put("INF411_D", caratula.inf411_d);
//		content.put("INF411_PAT", caratula.inf411_pat);
//		content.put("INF411_M", caratula.inf411_m);
//		content.put("INF412", caratula.inf412);
//		content.put("INF413", caratula.inf413);
//		content.put("INF414", caratula.inf414);
//		content.put("INF415_D", caratula.inf415_d);
//		content.put("INF415_M", caratula.inf415_m);
//		content.put("INF415_A", caratula.inf415_a);
//		content.put("INF416", caratula.inf416);
//		content.put("INF416_O", caratula.inf416_o);
//		content.put("INF417_1", caratula.inf417_1);
//		content.put("INF417_2", caratula.inf417_2);
//		content.put("INF417_3", caratula.inf417_3);
//		content.put("INF417_4", caratula.inf417_4);
//		content.put("INF417_5", caratula.inf417_5);
//		content.put("INF417_5_O", caratula.inf417_5_o);
//		content.put("INF418", caratula.inf418);
//		content.put("INF419_1", caratula.inf419_1);
//		content.put("INF419_1_1", caratula.inf419_1_1);
//		content.put("INF419_1_2", caratula.inf419_1_2);
//		content.put("INF419_2", caratula.inf419_2);
//		content.put("INF419_2_1", caratula.inf419_2_1);
//		content.put("INF419_2_2", caratula.inf419_2_2);
//		content.put("INF419_3", caratula.inf419_3);
//		content.put("INF419_3_1", caratula.inf419_3_1);
//		content.put("INF419_3_2", caratula.inf419_3_2);
//		content.put("INF419_4", caratula.inf419_4);
//		content.put("INF419_4_O", caratula.inf419_4_o);
//		content.put("INF419_4_1", caratula.inf419_4_1);
//		content.put("INF419_4_2", caratula.inf419_4_2);
//		content.put("INF420", caratula.inf420);
//		content.put("INF421_1", caratula.inf421_1);
//		content.put("INF421_1_1", caratula.inf421_1_1);
//		content.put("INF421_1_2", caratula.inf421_1_2);
//		content.put("INF421_2", caratula.inf421_2);
//		content.put("INF421_2_1", caratula.inf421_2_1);
//		content.put("INF421_2_2", caratula.inf421_2_2);
//		content.put("INF421_3", caratula.inf421_3);
//		content.put("INF421_3_1", caratula.inf421_3_1);
//		content.put("INF421_3_2", caratula.inf421_3_2);
//		content.put("INF421_4", caratula.inf421_4);
//		content.put("INF421_4_1", caratula.inf421_4_1);
//		content.put("INF421_4_2", caratula.inf421_4_2);
//		content.put("INF421_5", caratula.inf421_5);
//		content.put("INF421_5_O", caratula.inf421_5_o);
//		content.put("INF421_5_1", caratula.inf421_5_1);
//		content.put("INF421_5_2", caratula.inf421_5_2);
//		content.put("OBS_CAP_400_2", caratula.obs_cap_400_2);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_01_400", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveINF_Capitulo500(INF_Cap500 caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("INF501", caratula.inf501);
//		content.put("INF502", caratula.inf502);
//		content.put("INF503_1", caratula.inf503_1);
//		content.put("INF503_2", caratula.inf503_2);
//		content.put("INF503_3", caratula.inf503_3);
//		content.put("INF503_4", caratula.inf503_4);
//		content.put("INF503_5", caratula.inf503_5);
//		content.put("INF503_6", caratula.inf503_6);
//		content.put("INF503_7", caratula.inf503_7);
//		content.put("INF503_7_O", caratula.inf503_7_o);
//		content.put("INF503_8", caratula.inf503_8);
//		content.put("INF504_1_D", caratula.inf504_1_d);
//		content.put("INF504_1_M", caratula.inf504_1_m);
//		content.put("INF504_1_A", caratula.inf504_1_a);
//		content.put("INF504_2_D", caratula.inf504_2_d);
//		content.put("INF504_2_M", caratula.inf504_2_m);
//		content.put("INF504_2_A", caratula.inf504_2_a);
//		content.put("INF505", caratula.inf505);
//		content.put("INF506", caratula.inf506);
//		content.put("INF507", caratula.inf507);
//		content.put("OBS_CAP_500_1", caratula.obs_cap_500_1);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_01_500", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
	
//	public boolean saveAT_Visita(ATVisita caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("NROVIS", caratula.nrovis);
//		content.put("EFECHA", caratula.efecha);
//		content.put("EHORADE", caratula.ehorade);
//		content.put("EHORAA", caratula.ehoraa);
//		content.put("EPVFECHA", caratula.epvfecha);
//		content.put("EPVHORA", caratula.epvhora);
//		content.put("ERESVIS", caratula.eresvis);
//		content.put("ERESVIS_O", caratula.eresvis_o);
//		content.put("SFECHA", caratula.sfecha);
//		content.put("SHORADE", caratula.shorade);
//		content.put("SHORAA", caratula.shoraa);
//		content.put("SRESVIS", caratula.sresvis);
//		content.put("SRESVIS_O", caratula.sresvis_o);
//		
//		content.put("GPSLATITUD", caratula.gpslatitud);
//		content.put("GPSLONGITUD", caratula.gpslongitud);
//		
////		content.put("USUCRE", caratula.usucre);
////		content.put("FECCRE", caratula.feccre);
////		content.put("USUREG", caratula.usureg);
////		content.put("FECREG", caratula.fecreg);
////		content.put("FECENV", caratula.fecenv);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_02_VISITA", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveAT_Muestra(AT_Muestra muestra){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", muestra.id_n);
//		content.put("AT_N", muestra.at_n);
//		content.put("ESTADO", muestra.estado);
//
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_MUESTRAAT", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
		
//	public boolean saveATCapitulo100(ATCap100BE caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("ID_AT", caratula.id_at);
//		content.put("AT_N", caratula.at_n);
//		content.put("AT_NALF", caratula.at_nalf);
//		content.put("AT_TOT", caratula.at_tot);
//		content.put("AT101", caratula.at101);
//		content.put("AT101_ALF", caratula.at101_alf);
//		content.put("AT101_O", caratula.at101_o);
//		content.put("AT101_NRO", caratula.at101_nro);
//		content.put("AT102", caratula.at102);
//		content.put("AT103_D", caratula.at103_d);
//		content.put("AT103_M", caratula.at103_m);
//		content.put("AT103_A", caratula.at103_a);
//		content.put("AT103_HOR", caratula.at103_hor);
//		content.put("AT103_MIN", caratula.at103_min);
//		content.put("AT104A", caratula.at104a);
//		content.put("AT104A_O", caratula.at104a_o);
//		content.put("AT104B", caratula.at104b);
//		content.put("AT104B_O", caratula.at104b_o);
//		content.put("AT104C_V1", caratula.at104c_v1);
//		content.put("AT104C_R1", caratula.at104c_r1);
//		content.put("AT104C_V2", caratula.at104c_v2);
//		content.put("AT104C_R2", caratula.at104c_r2);
//		content.put("AT105", caratula.at105);
//		content.put("AT105_O", caratula.at105_o);
//		content.put("AT106_1", caratula.at106_1);
//		content.put("AT106_1_CANT", caratula.at106_1_cant);
//		content.put("AT106_2", caratula.at106_2);
//		content.put("AT106_2_CANT", caratula.at106_2_cant);
//		content.put("AT106_3", caratula.at106_3);
//		content.put("AT106_3_CANT", caratula.at106_3_cant);
//		content.put("AT106_4", caratula.at106_4);
//		content.put("AT106_4_CANT", caratula.at106_4_cant);
//		content.put("AT106_5", caratula.at106_5);
//		content.put("AT106_5_CANT", caratula.at106_5_cant);
//		content.put("AT106_6", caratula.at106_6);
//		content.put("AT106_6_CANT", caratula.at106_6_cant);
//		content.put("AT106_7", caratula.at106_7);
//		content.put("AT106_7_CANT", caratula.at106_7_cant);
//		content.put("AT106_8", caratula.at106_8);
//		content.put("AT106_8_CANT", caratula.at106_8_cant);
//		content.put("AT106_9", caratula.at106_9);
//		content.put("AT106_9_CANT", caratula.at106_9_cant);
//		content.put("AT106_10", caratula.at106_10);
//		content.put("AT106_10_CANT", caratula.at106_10_cant);
//		content.put("AT106_11", caratula.at106_11);
//		content.put("AT106_11_CANT", caratula.at106_11_cant);
//		content.put("AT106_12", caratula.at106_12);
//		content.put("AT106_12_CANT", caratula.at106_12_cant);
//		content.put("AT106_13", caratula.at106_13);
//		content.put("AT106_13_CANT", caratula.at106_13_cant);
//		content.put("AT106_13_O", caratula.at106_13_o);
//		content.put("AT106_14", caratula.at106_14);
//		content.put("AT106_14_CANT", caratula.at106_14_cant);
//		content.put("AT106_15", caratula.at106_15);
//		content.put("AT106_15_CANT", caratula.at106_15_cant);
//		content.put("AT106_16", caratula.at106_16);
//		content.put("AT106_16_CANT", caratula.at106_16_cant);
//		content.put("AT106_17", caratula.at106_17);
//		content.put("AT106_17_CANT", caratula.at106_17_cant);
//		content.put("AT106_18", caratula.at106_18);
//		content.put("AT106_18_CANT", caratula.at106_18_cant);
//		content.put("AT106_19", caratula.at106_19);
//		content.put("AT106_19_CANT", caratula.at106_19_cant);
//		content.put("AT106_19_O", caratula.at106_19_o);
//		content.put("AT107_1", caratula.at107_1);
//		content.put("AT107_2", caratula.at107_2);
//		content.put("AT107_3", caratula.at107_3);
//		content.put("AT108", caratula.at108);
//		content.put("AT108_1", caratula.at108_1);
//		content.put("AT108_2", caratula.at108_2);
//		content.put("AT108_3", caratula.at108_3);
//		content.put("AT109_1", caratula.at109_1);
//		content.put("AT109_2", caratula.at109_2);
//		content.put("AT109_3", caratula.at109_3);
//		content.put("AT109_4", caratula.at109_4);
//		content.put("AT109_5", caratula.at109_5);
//		content.put("AT109_6", caratula.at109_6);
//		content.put("AT109_7", caratula.at109_7);
//		content.put("AT109_8", caratula.at109_8);
//		content.put("AT109_9", caratula.at109_9);
//		content.put("AT109_10", caratula.at109_10);
//		content.put("AT109_11", caratula.at109_11);
//		content.put("AT109_12", caratula.at109_12);
//		content.put("AT109_13", caratula.at109_13);
//		content.put("AT109_14", caratula.at109_14);
//		content.put("AT109_15", caratula.at109_15);
//		content.put("AT109_15_O", caratula.at109_15_o);
//		content.put("OBS_100", caratula.obs_100);
////		content.put("CUSU_UP", caratula.cusu_up);
////		content.put("FEC_UP", caratula.fec_up);
////		content.put("HOR_UP", caratula.hor_up);
////		content.put("CUSU_IN", caratula.cusu_in);
////		content.put("FEC_IN", caratula.fec_in);
////		content.put("HOR_IN", caratula.hor_in);
//		content.put("AT_CAP200", caratula.at_cap200);
//
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_02_100", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveATCapitulo200(ATCap200BE caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("ID_AT", caratula.id_at);
//		content.put("ID_PERSONA", caratula.id_persona);
//		content.put("IP200N", caratula.ip200n);
//		content.put("IP201", caratula.ip201);
//		content.put("IP201_O", caratula.ip201_o);
//		content.put("IP202", caratula.ip202);
//		content.put("IP203", caratula.ip203);
//		content.put("IP203_NI", caratula.ip203_ni);
//		content.put("IP204", caratula.ip204);
//		content.put("IP205", caratula.ip205);
//		content.put("IP205A", caratula.ip205a);
//		content.put("IP206", caratula.ip206);
//		content.put("IP206_11_O", caratula.ip206_11_o);
//		content.put("IP206_17_O", caratula.ip206_17_o);
//		content.put("IP206_O", caratula.ip206_o);
//		content.put("IP207", caratula.ip207);
//		
//		content.put("CUSU_UP", caratula.cusu_up);
//		content.put("FEC_UP", caratula.fec_up);
//		content.put("HOR_UP", caratula.hor_up);
//		content.put("CUSU_IN", caratula.cusu_in);
//		content.put("FEC_IN", caratula.fec_in);
//		content.put("HOR_IN", caratula.hor_in);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_02_200", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveINF_Capitulo600(INF_Cap600 caratula){
//		ContentValues content = new ContentValues();
//		
//		content = caratula.getContentValues(content, 601, 609);
//		content.put("ID_N", caratula.id_n);
//		content.put("INF337_1_A", caratula.inf337_1_a);
//		content.put("INF337_2_A", caratula.inf337_2_a);
//		content.put("INF337_3_A", caratula.inf337_3_a);
//		content.put("INF337_4_A", caratula.inf337_4_a);
//		content.put("INF337_5_A", caratula.inf337_5_a);
//		content.put("INF337_6", caratula.inf337_6);
//		
////		content.put("ID_N", caratula.id_n);
////		content.put("INF501", caratula.inf501);
////		content.put("INF502", caratula.inf502);
////		content.put("INF503_1", caratula.inf503_1);
////		content.put("INF503_2", caratula.inf503_2);
////		content.put("INF503_3", caratula.inf503_3);
////		content.put("INF503_4", caratula.inf503_4);
////		content.put("INF503_5", caratula.inf503_5);
////		content.put("INF503_6", caratula.inf503_6);
////		content.put("INF503_7", caratula.inf503_7);
////		content.put("INF503_7_O", caratula.inf503_7_o);
////		content.put("INF503_8", caratula.inf503_8);
////		content.put("INF504_1_D", caratula.inf504_1_d);
////		content.put("INF504_1_M", caratula.inf504_1_m);
////		content.put("INF504_1_A", caratula.inf504_1_a);
////		content.put("INF504_2_D", caratula.inf504_2_d);
////		content.put("INF504_2_M", caratula.inf504_2_m);
////		content.put("INF504_2_A", caratula.inf504_2_a);
////		content.put("INF505", caratula.inf505);
////		content.put("INF506", caratula.inf506);
////		content.put("INF507", caratula.inf507);
////		content.put("OBS_CAP_500_1", caratula.obs_cap_500_1);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_01_600", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveINF_Capitulo600i(INF_Cap600_I caratula){
//		ContentValues content = new ContentValues();
//		
//		content = caratula.getContentValues(content, 610, 635);
//		content.put("ID_N", caratula.id_n);
//		content.put("OBS_ADICIONAL", caratula.obs_adicional);
//		
////		content.put("ID_N", caratula.id_n);
////		content.put("INF501", caratula.inf501);
////		content.put("INF502", caratula.inf502);
////		content.put("INF503_1", caratula.inf503_1);
////		content.put("INF503_2", caratula.inf503_2);
////		content.put("INF503_3", caratula.inf503_3);
////		content.put("INF503_4", caratula.inf503_4);
////		content.put("INF503_5", caratula.inf503_5);
////		content.put("INF503_6", caratula.inf503_6);
////		content.put("INF503_7", caratula.inf503_7);
////		content.put("INF503_7_O", caratula.inf503_7_o);
////		content.put("INF503_8", caratula.inf503_8);
////		content.put("INF504_1_D", caratula.inf504_1_d);
////		content.put("INF504_1_M", caratula.inf504_1_m);
////		content.put("INF504_1_A", caratula.inf504_1_a);
////		content.put("INF504_2_D", caratula.inf504_2_d);
////		content.put("INF504_2_M", caratula.inf504_2_m);
////		content.put("INF504_2_A", caratula.inf504_2_a);
////		content.put("INF505", caratula.inf505);
////		content.put("INF506", caratula.inf506);
////		content.put("INF507", caratula.inf507);
////		content.put("OBS_CAP_500_1", caratula.obs_cap_500_1);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_DIG_01_600_I", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveAnexo_100(Anexo_C100 anexo){
//		ContentValues content = new ContentValues();
//		
//		content = anexo.getContentValues(content, -1, -1);
//		content.put("ID_N", anexo.id_n);
////		content.put("CUEST", anexo.cuest);
////		content.put("ANIO", anexo.anio);
////		content.put("TRIMESTRE", anexo.trimestre);
//		content.put("UBIGEO", anexo.ubigeo);
//		content.put("SEC_TOT", anexo.sec_tot);
//		content.put("NOMB_COMISARIO", anexo.nomb_comisario);
//		content.put("CARGO_COMISARIO", anexo.cargo_comisario);
//		content.put("NOMB_FACILITADOR", anexo.nomb_facilitador);
//		content.put("CARGO_FACILIT", anexo.cargo_facilit);
//		content.put("VA_1_DMA", anexo.va_1_dma);
//		content.put("VA_1_D", anexo.va_1_d);
//		content.put("VA_1_M", anexo.va_1_m);
//		content.put("VA_1_A", anexo.va_1_a);
//		content.put("IVRESFIN_ANEX", anexo.ivresfin_anex);
//		content.put("IVRESFIN_ANEX_O", anexo.ivresfin_anex_o);
//		content.put("OBSERVACIONES_ANEXO", anexo.observaciones_anexo);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_ANEXO_100", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
//	
//	public boolean saveAnexo_100_Dets(Anexo_C100_Det anexo){
//		ContentValues content = new ContentValues();
//		
//		content = anexo.getContentValues(content, 101, 105);
//		content.put("ID_N", anexo.id_n);
//		content.put("SEC_N", anexo.sec_n);
//		content.put("A_OBS_300", anexo.a_obs_300);
//		content.put("CAP100_OMISION", anexo.cap100_omision);
//		content.put("NRO_VIDEO_300", anexo.nro_video_300);
//
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_ANEXO_100_DET", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
//	
//	public boolean saveAnexo_200_Dets(Anexo_C200_Det anexo){
//		ContentValues content = new ContentValues();
//		
//		content = anexo.getContentValues(content, 200, 208);
//		content.put("ID_N", anexo.id_n);
//		content.put("SEC_N", anexo.sec_n);
//		content.put("CAP200_OMISION", anexo.cap200_omision);
//		content.put("A_OBS_100_200", anexo.a_obs_100_200);
//
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_ANEXO_200_DET", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
//	
//	public boolean saveAnexo_300_Dets(Anexo_C300_Det anexo){
//		ContentValues content = new ContentValues();
//		
//		content = anexo.getContentValues(content, 301, 310);
//		content.put("ID_N", anexo.id_n);
//		content.put("SEC_N", anexo.sec_n);
//		content.put("NRO_REG_ANEXO", anexo.nro_reg_anexo);
//		content.put("A_300_LLAVE", anexo.a_300_llave);
//
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("T_ANEXO_300_DET", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
//
//	
//	
//	public boolean saveCaratulas(List<INF_Caratula01> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveCaratula(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveINF_Visitas(List<INF_Visita01> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveINF_Visita(caratulas.remove(0))) return false;
//		}
//		return true;	
//	}
//	
//	public boolean saveINF_Capitulo100s(List<INF_Cap100> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveINF_Capitulo100(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveINF_Capitulo200s(List<INF_Cap200> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveINF_Capitulo200(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveINF_Capitulo300s(List<INF_Cap300> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveINF_Capitulo300(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveINF_Capitulo300_IIIs(List<INF_Cap300_III> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveINF_Capitulo300_III(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveINF_Capitulo400s(List<INF_Cap400> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveINF_Capitulo400(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveINF_Capitulo500s(List<INF_Cap500> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveINF_Capitulo500(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	
//	public boolean saveAT_Visitas(List<ATVisita> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveAT_Visita(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveAT_Muestras(List<AT_Muestra> muestras){
//		if(muestras==null) return true;
//		if(muestras.isEmpty()) return true;
//		int tot = muestras.size();
//		for(int i=0; i<tot; i++){
//			if(!saveAT_Muestra(muestras.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveATCapitulo100s(List<ATCap100BE> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveATCapitulo100(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveATCapitulo200s(List<ATCap200BE> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveATCapitulo200(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveINF_Capitulo600s(List<INF_Cap600> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveINF_Capitulo600(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveINF_Capitulo600is(List<INF_Cap600_I> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveINF_Capitulo600i(caratulas.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	/*FORMULARIO ADICIONAL TERRITORIAL*/
//	public boolean saveAnexo_100(List<Anexo_C100> anexo){
//		if(anexo==null) return true;
//		if(anexo.isEmpty()) return true;
//		int tot = anexo.size();
//		for(int i=0; i<tot; i++){
//			if(!saveAnexo_100(anexo.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveAnexo_100_Dets(List<Anexo_C100_Det> anexo){
//		if(anexo==null) return true;
//		if(anexo.isEmpty()) return true;
//		int tot = anexo.size();
//		for(int i=0; i<tot; i++){
//			if(!saveAnexo_100_Dets(anexo.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveAnexo_200_Dets(List<Anexo_C200_Det> anexo){
//		if(anexo==null) return true;
//		if(anexo.isEmpty()) return true;
//		int tot = anexo.size();
//		for(int i=0; i<tot; i++){
//			if(!saveAnexo_200_Dets(anexo.remove(0))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveAnexo_300_Dets(List<Anexo_C300_Det> anexo){
//		if(anexo==null) return true;
//		if(anexo.isEmpty()) return true;
//		int tot = anexo.size();
//		for(int i=0; i<tot; i++){
//			if(!saveAnexo_300_Dets(anexo.remove(0))) return false;
//		}
//		return true;
//	}
	
	
	/*DELITOS - OTIN*/
//	public ArrayList<CaratulaDelitos> fillCaratulasDelitos(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<CaratulaDelitos> caratulas = new ArrayList<CaratulaDelitos>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM CARATULA WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			CaratulaDelitos caratula = new CaratulaDelitos();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.anio = cursor.getString(cursor.getColumnIndex("ANIO"))==null?null:cursor.getInt(cursor.getColumnIndex("ANIO"));
//			caratula.ii1 = cursor.getString(cursor.getColumnIndex("II1"));
//			caratula.ii2 = cursor.getString(cursor.getColumnIndex("II2"));
//			caratula.ii3 = cursor.getString(cursor.getColumnIndex("II3"));
//			caratula.ii4 = cursor.getString(cursor.getColumnIndex("II4"))==null?null:cursor.getInt(cursor.getColumnIndex("II4"));
//			caratula.ii5 = cursor.getString(cursor.getColumnIndex("II5"))==null?null:cursor.getInt(cursor.getColumnIndex("II5"));
//			caratula.ii6 = cursor.getString(cursor.getColumnIndex("II6"));
//			caratula.ii7 = cursor.getString(cursor.getColumnIndex("II7"))==null?null:cursor.getInt(cursor.getColumnIndex("II7"));
//			caratula.ii8 = cursor.getString(cursor.getColumnIndex("II8"))==null?null:cursor.getInt(cursor.getColumnIndex("II8"));
//			caratula.ii8_o = cursor.getString(cursor.getColumnIndex("II8_O"));
//			caratula.ii9 = cursor.getString(cursor.getColumnIndex("II9"))==null?null:cursor.getInt(cursor.getColumnIndex("II9"));
//			caratula.ii9_o = cursor.getString(cursor.getColumnIndex("II9_O"));
//			caratula.iv2_1_d = cursor.getString(cursor.getColumnIndex("IV2_1_D"));
//			caratula.iv2_1_m = cursor.getString(cursor.getColumnIndex("IV2_1_M"));
//			caratula.iv_2_1_a = cursor.getString(cursor.getColumnIndex("IV_2_1_A"))==null?null:cursor.getInt(cursor.getColumnIndex("IV_2_1_A"));
//			caratula.ivresfin_02 = cursor.getString(cursor.getColumnIndex("IVRESFIN_02"))==null?null:cursor.getInt(cursor.getColumnIndex("IVRESFIN_02"));
//			caratula.ivresfin_02_o = cursor.getString(cursor.getColumnIndex("IVRESFIN_02_O"));
//			caratula.v3_1 = cursor.getString(cursor.getColumnIndex("V3_1"))==null?null:cursor.getInt(cursor.getColumnIndex("V3_1"));
//			caratula.v3_2 = cursor.getString(cursor.getColumnIndex("V3_2"))==null?null:cursor.getInt(cursor.getColumnIndex("V3_2"));
//			caratula.v3_3 = cursor.getString(cursor.getColumnIndex("V3_3"))==null?null:cursor.getInt(cursor.getColumnIndex("V3_3"));
//			caratula.v3_4 = cursor.getString(cursor.getColumnIndex("V3_4"))==null?null:cursor.getInt(cursor.getColumnIndex("V3_4"));
//			caratula.vi1a = cursor.getString(cursor.getColumnIndex("VI1A"));
//			caratula.vi1b = cursor.getString(cursor.getColumnIndex("VI1B"));
//			caratula.vi3a = cursor.getString(cursor.getColumnIndex("VI3A"));
//			caratula.vi3b = cursor.getString(cursor.getColumnIndex("VI3B"));
//			caratula.vii1a = cursor.getString(cursor.getColumnIndex("VII1A"));
//			caratula.vii1b = cursor.getString(cursor.getColumnIndex("VII1B"));
//			caratula.vii1c = cursor.getString(cursor.getColumnIndex("VII1C"));
//			caratula.vii1d = cursor.getString(cursor.getColumnIndex("VII1D"));
//			caratula.vii3_1d_nt = cursor.getString(cursor.getColumnIndex("VII3_1D_NT"))==null?null:cursor.getInt(cursor.getColumnIndex("VII3_1D_NT"));
//			caratula.vii1e = cursor.getString(cursor.getColumnIndex("VII1E"));
//			caratula.vii3_1e_nt = cursor.getString(cursor.getColumnIndex("VII3_1E_NT"))==null?null:cursor.getInt(cursor.getColumnIndex("VII3_1E_NT"));
//			caratula.vii1f = cursor.getString(cursor.getColumnIndex("VII1F"));
//			caratula.vii3_1f_nt = cursor.getString(cursor.getColumnIndex("VII3_1F_NT"))==null?null:cursor.getInt(cursor.getColumnIndex("VII3_1F_NT"));
//			caratula.vii2a = cursor.getString(cursor.getColumnIndex("VII2A"));
//			caratula.vii2b = cursor.getString(cursor.getColumnIndex("VII2B"));
//			caratula.vii2c = cursor.getString(cursor.getColumnIndex("VII2C"));
//			caratula.vii2d = cursor.getString(cursor.getColumnIndex("VII2D"));
//			caratula.vii3_2d_nt = cursor.getString(cursor.getColumnIndex("VII3_2D_NT"))==null?null:cursor.getInt(cursor.getColumnIndex("VII3_2D_NT"));
//			caratula.vii2e = cursor.getString(cursor.getColumnIndex("VII2E"));
//			caratula.vii3_2e_nt = cursor.getString(cursor.getColumnIndex("VII3_2E_NT"))==null?null:cursor.getInt(cursor.getColumnIndex("VII3_2E_NT"));
//			caratula.vii2f = cursor.getString(cursor.getColumnIndex("VII2F"));
//			caratula.vii3_2f_nt = cursor.getString(cursor.getColumnIndex("VII3_2F_NT"));
//			caratula.obs_car = cursor.getString(cursor.getColumnIndex("OBS_CAR"));
//			caratula.total_denuncias = cursor.getString(cursor.getColumnIndex("TOTAL_DENUNCIAS"))==null?null:cursor.getInt(cursor.getColumnIndex("TOTAL_DENUNCIAS"));
//			caratula.conte_reg200 = cursor.getString(cursor.getColumnIndex("CONTE_REG200"))==null?null:cursor.getInt(cursor.getColumnIndex("CONTE_REG200"));
//			caratula.conte_reg300 = cursor.getString(cursor.getColumnIndex("CONTE_REG300"))==null?null:cursor.getInt(cursor.getColumnIndex("CONTE_REG300"));
//			caratula.conte_reg400 = cursor.getString(cursor.getColumnIndex("CONTE_REG400"))==null?null:cursor.getInt(cursor.getColumnIndex("CONTE_REG400"));
//			caratula.usr_creacion = cursor.getString(cursor.getColumnIndex("USR_CREACION"));
//			caratula.fec_creacion = cursor.getString(cursor.getColumnIndex("FEC_CREACION"));
//			caratula.usr_edicion = cursor.getString(cursor.getColumnIndex("USR_EDICION"));
//			caratula.fec_edicion = cursor.getString(cursor.getColumnIndex("FEC_EDICION"));
//			caratula.fecha_recepcion = cursor.getString(cursor.getColumnIndex("fecha_recepcion"));
//			caratula.cerrado = cursor.getString(cursor.getColumnIndex("CERRADO"))==null?null:cursor.getInt(cursor.getColumnIndex("CERRADO"));
//			
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
//	public ArrayList<VisitasDelitos> fillVisitasDelitos(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<VisitasDelitos> caratulas = new ArrayList<VisitasDelitos>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM CARATULA_ENTREVISTA WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			VisitasDelitos caratula = new VisitasDelitos();
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.anio = cursor.getString(cursor.getColumnIndex("ANIO"))==null?null:cursor.getInt(cursor.getColumnIndex("ANIO"));
//			caratula.iii2_1 = cursor.getString(cursor.getColumnIndex("III2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("III2_1"));
//			caratula.iii2_2_d = cursor.getString(cursor.getColumnIndex("III2_2_D"));
//			caratula.iii2_2_m = cursor.getString(cursor.getColumnIndex("III2_2_M"));
//			caratula.iii2_3a_ih = cursor.getString(cursor.getColumnIndex("III2_3A_IH"));
//			caratula.iii2_3a_im = cursor.getString(cursor.getColumnIndex("III2_3A_IM"));
//			caratula.gpslatitud_ini = cursor.getString(cursor.getColumnIndex("GPSLATITUD_INI"));
//			caratula.gpslongitud_ini = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_INI"));
//			caratula.iii2_3b_fh = cursor.getString(cursor.getColumnIndex("III2_3B_FH"));
//			caratula.iii2_3b_fm = cursor.getString(cursor.getColumnIndex("III2_3B_FM"));
//			caratula.gpslatitud_fin = cursor.getString(cursor.getColumnIndex("GPSLATITUD_FIN"));
//			caratula.gpslongitud_fin = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_FIN"));
//			caratula.iii2_4a_d = cursor.getString(cursor.getColumnIndex("III2_4A_D"));
//			caratula.iii2_4a_m = cursor.getString(cursor.getColumnIndex("III2_4A_M"));
//			caratula.iii2_4b_h = cursor.getString(cursor.getColumnIndex("III2_4B_H"));
//			caratula.iii2_4b_m = cursor.getString(cursor.getColumnIndex("III2_4B_M"));
//			caratula.iii2_5 = cursor.getString(cursor.getColumnIndex("III2_5"))==null?null:cursor.getInt(cursor.getColumnIndex("III2_5"));
//			caratula.iii2_6_d = cursor.getString(cursor.getColumnIndex("III2_6_D"));
//			caratula.iii2_6_m = cursor.getString(cursor.getColumnIndex("III2_6_M"));
//			caratula.iii2_7a_h = cursor.getString(cursor.getColumnIndex("III2_7A_H"));
//			caratula.iii2_7a_m = cursor.getString(cursor.getColumnIndex("III2_7A_M"));
//			caratula.iii2_7b_h = cursor.getString(cursor.getColumnIndex("III2_7B_H"));
//			caratula.iii2_7b_m = cursor.getString(cursor.getColumnIndex("III2_7B_M"));
//			caratula.iii2_8 = cursor.getString(cursor.getColumnIndex("III2_8"))==null?null:cursor.getInt(cursor.getColumnIndex("III2_8"));
//			caratula.usr_creacion = cursor.getString(cursor.getColumnIndex("USR_CREACION"));
//			caratula.fec_creacion = cursor.getString(cursor.getColumnIndex("FEC_CREACION"));
//			caratula.usr_edicion = cursor.getString(cursor.getColumnIndex("USR_EDICION"));
//			caratula.fec_edicion = cursor.getString(cursor.getColumnIndex("FEC_EDICION"));
//			caratula.fecha_recepcion = cursor.getString(cursor.getColumnIndex("FECHA_RECEPCION"));
//			
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
	public ArrayList<Cap100Delitos> fillCap100Delitos(String id_n){
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		ArrayList<Cap100Delitos> caratulas = new ArrayList<Cap100Delitos>();
		
		Cursor cursor = dbr.rawQuery("SELECT * FROM CAP100 WHERE ID_N=?", new String[]{id_n});
		
		while(cursor.moveToNext()){
			Cap100Delitos caratula = new Cap100Delitos();
			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
			caratula.dn101 = cursor.getString(cursor.getColumnIndex("DN101"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101"));
			caratula.dn101_1 = cursor.getString(cursor.getColumnIndex("DN101_1"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_1"));
			caratula.dn101_1_a = cursor.getString(cursor.getColumnIndex("DN101_1_A"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_1_A"));
			caratula.dn101_1_b = cursor.getString(cursor.getColumnIndex("DN101_1_B"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_1_B"));
			caratula.dn101_1_c = cursor.getString(cursor.getColumnIndex("DN101_1_C"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_1_C"));
			caratula.dn101_1_d = cursor.getString(cursor.getColumnIndex("DN101_1_D"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_1_D"));
			caratula.dn101_1_e = cursor.getString(cursor.getColumnIndex("DN101_1_E"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_1_E"));
			caratula.dn101_1_f = cursor.getString(cursor.getColumnIndex("DN101_1_F"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_1_F"));
			caratula.dn101_1_g = cursor.getString(cursor.getColumnIndex("DN101_1_G"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_1_G"));
			caratula.dn101_2 = cursor.getString(cursor.getColumnIndex("DN101_2"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_2"));
			caratula.dn101_3 = cursor.getString(cursor.getColumnIndex("DN101_3"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_3"));
			caratula.dn101_4 = cursor.getString(cursor.getColumnIndex("DN101_4"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_4"));
			caratula.dn101_5 = cursor.getString(cursor.getColumnIndex("DN101_5"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_5"));
			caratula.dn101_6 = cursor.getString(cursor.getColumnIndex("DN101_6"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_6"));
			caratula.sum_fallecidos = cursor.getString(cursor.getColumnIndex("SUM_FALLECIDOS"))==null?null:cursor.getInt(cursor.getColumnIndex("SUM_FALLECIDOS"));
			caratula.dn101_7 = cursor.getString(cursor.getColumnIndex("DN101_7"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_7"));
			caratula.dn101_8 = cursor.getString(cursor.getColumnIndex("DN101_8"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_8"));
			caratula.dn101_9 = cursor.getString(cursor.getColumnIndex("DN101_9"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_9"));
			caratula.dn101_10 = cursor.getString(cursor.getColumnIndex("DN101_10"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_10"));
			caratula.dn101_11 = cursor.getString(cursor.getColumnIndex("DN101_11"))==null?null:cursor.getInt(cursor.getColumnIndex("DN101_11"));
			caratula.dn102 = cursor.getString(cursor.getColumnIndex("DN102"))==null?null:cursor.getInt(cursor.getColumnIndex("DN102"));
			caratula.dn103 = cursor.getString(cursor.getColumnIndex("DN103"))==null?null:cursor.getInt(cursor.getColumnIndex("DN103"));
			caratula.dn104 = cursor.getString(cursor.getColumnIndex("DN104"))==null?null:cursor.getInt(cursor.getColumnIndex("DN104"));
			caratula.dn104_a = cursor.getString(cursor.getColumnIndex("DN104_A"))==null?null:cursor.getInt(cursor.getColumnIndex("DN104_A"));
			caratula.dn104_b = cursor.getString(cursor.getColumnIndex("DN104_B"))==null?null:cursor.getInt(cursor.getColumnIndex("DN104_B"));
			caratula.dn105 = cursor.getString(cursor.getColumnIndex("DN105"))==null?null:cursor.getInt(cursor.getColumnIndex("DN105"));
			caratula.dn105_a = cursor.getString(cursor.getColumnIndex("DN105_A"))==null?null:cursor.getInt(cursor.getColumnIndex("DN105_A"));
			caratula.dn105_b = cursor.getString(cursor.getColumnIndex("DN105_B"))==null?null:cursor.getInt(cursor.getColumnIndex("DN105_B"));
			caratula.dn105_c = cursor.getString(cursor.getColumnIndex("DN105_C"))==null?null:cursor.getInt(cursor.getColumnIndex("DN105_C"));
			caratula.dn106 = cursor.getString(cursor.getColumnIndex("DN106"))==null?null:cursor.getInt(cursor.getColumnIndex("DN106"));
			caratula.dn107 = cursor.getString(cursor.getColumnIndex("DN107"))==null?null:cursor.getInt(cursor.getColumnIndex("DN107"));
			caratula.dn108 = cursor.getString(cursor.getColumnIndex("DN108"))==null?null:cursor.getInt(cursor.getColumnIndex("DN108"));
			caratula.dn109 = cursor.getString(cursor.getColumnIndex("DN109"))==null?null:cursor.getInt(cursor.getColumnIndex("DN109"));
			caratula.dn110 = cursor.getString(cursor.getColumnIndex("DN110"))==null?null:cursor.getInt(cursor.getColumnIndex("DN110"));
			caratula.dn111 = cursor.getString(cursor.getColumnIndex("DN111"))==null?null:cursor.getInt(cursor.getColumnIndex("DN111"));
			caratula.dn112 = cursor.getString(cursor.getColumnIndex("DN112"))==null?null:cursor.getInt(cursor.getColumnIndex("DN112"));
			caratula.dn113 = cursor.getString(cursor.getColumnIndex("DN113"))==null?null:cursor.getInt(cursor.getColumnIndex("DN113"));
			caratula.dn114 = cursor.getString(cursor.getColumnIndex("DN114"))==null?null:cursor.getInt(cursor.getColumnIndex("DN114"));
			caratula.dn115 = cursor.getString(cursor.getColumnIndex("DN115"))==null?null:cursor.getInt(cursor.getColumnIndex("DN115"));
			caratula.dn116 = cursor.getString(cursor.getColumnIndex("DN116"))==null?null:cursor.getInt(cursor.getColumnIndex("DN116"));
			caratula.dn117 = cursor.getString(cursor.getColumnIndex("DN117"))==null?null:cursor.getInt(cursor.getColumnIndex("DN117"));
			caratula.dn118 = cursor.getString(cursor.getColumnIndex("DN118"))==null?null:cursor.getInt(cursor.getColumnIndex("DN118"));
			caratula.dn119 = cursor.getString(cursor.getColumnIndex("DN119"))==null?null:cursor.getInt(cursor.getColumnIndex("DN119"));
			caratula.dn120 = cursor.getString(cursor.getColumnIndex("DN120"))==null?null:cursor.getInt(cursor.getColumnIndex("DN120"));
			caratula.dn121 = cursor.getString(cursor.getColumnIndex("DN121"))==null?null:cursor.getInt(cursor.getColumnIndex("DN121"));
			caratula.obs_03_100 = cursor.getString(cursor.getColumnIndex("OBS_03_100"));
			
			caratulas.add(caratula);
		}
		
		cursor.close();
		cursor = null;
		dbr.close();
		dbr = null;
		SQLiteDatabase.releaseMemory();
		return caratulas;
	}
	
//	public ArrayList<Cap200Delitos> fillCap200Delitos(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
//		ArrayList<Cap200Delitos> caratulas = new ArrayList<Cap200Delitos>();
//		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM CAP200 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			Cap200Delitos caratula = new Cap200Delitos();
//			caratula.nro_mreg = cursor.getString(cursor.getColumnIndex("NRO_MREG"))==null?null:cursor.getInt(cursor.getColumnIndex("NRO_MREG"));
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
////			caratula.anio = cursor.getString(cursor.getColumnIndex("ANIO"))==null?null:cursor.getInt(cursor.getColumnIndex("ANIO"));
//			caratula.orden_200 = cursor.getString(cursor.getColumnIndex("ORDEN_200"))==null?null:cursor.getInt(cursor.getColumnIndex("ORDEN_200"));
//			caratula.ih201 = cursor.getString(cursor.getColumnIndex("IH201"))==null?null:cursor.getInt(cursor.getColumnIndex("IH201"));
//			caratula.ih201_o = cursor.getString(cursor.getColumnIndex("IH201_O"));
//			caratula.ih202 = cursor.getString(cursor.getColumnIndex("IH202"))==null?null:cursor.getInt(cursor.getColumnIndex("IH202"));
//			caratula.ih202_o = cursor.getString(cursor.getColumnIndex("IH202_O"));
//			caratula.ih203 = cursor.getString(cursor.getColumnIndex("IH203"))==null?null:cursor.getInt(cursor.getColumnIndex("IH203"));
//			caratula.ih203_o = cursor.getString(cursor.getColumnIndex("IH203_O"));
//			caratula.ih204 = cursor.getString(cursor.getColumnIndex("IH204"))==null?null:cursor.getInt(cursor.getColumnIndex("IH204"));
//			caratula.ih204_o = cursor.getString(cursor.getColumnIndex("IH204_O"));
//			caratula.ih205_dia = cursor.getString(cursor.getColumnIndex("IH205_DIA"));
//			caratula.ih205_mes = cursor.getString(cursor.getColumnIndex("IH205_MES"));
//			caratula.ih205_anio = cursor.getString(cursor.getColumnIndex("IH205_ANIO"))==null?null:cursor.getInt(cursor.getColumnIndex("IH205_ANIO"));
//			caratula.ih206_hor = cursor.getString(cursor.getColumnIndex("IH206_HOR"));
//			caratula.ih206_min = cursor.getString(cursor.getColumnIndex("IH206_MIN"));
//			caratula.ih207 = cursor.getString(cursor.getColumnIndex("IH207"))==null?null:cursor.getInt(cursor.getColumnIndex("IH207"));
//			caratula.ih207_o = cursor.getString(cursor.getColumnIndex("IH207_O"));
//			caratula.ih208 = cursor.getString(cursor.getColumnIndex("IH208"))==null?null:cursor.getInt(cursor.getColumnIndex("IH208"));
//			caratula.ih208_o = cursor.getString(cursor.getColumnIndex("IH208_O"));
//			caratula.ih209_1 = cursor.getString(cursor.getColumnIndex("IH209_1"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_1"));
//			caratula.ih209_2 = cursor.getString(cursor.getColumnIndex("IH209_2"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_2"));
//			caratula.ih209_3 = cursor.getString(cursor.getColumnIndex("IH209_3"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_3"));
//			caratula.ih209_4 = cursor.getString(cursor.getColumnIndex("IH209_4"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_4"));
//			caratula.ih209_5 = cursor.getString(cursor.getColumnIndex("IH209_5"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_5"));
//			caratula.ih209_6 = cursor.getString(cursor.getColumnIndex("IH209_6"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_6"));
//			caratula.ih209_7 = cursor.getString(cursor.getColumnIndex("IH209_7"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_7"));
//			caratula.ih209_8 = cursor.getString(cursor.getColumnIndex("IH209_8"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_8"));
//			caratula.ih209_9 = cursor.getString(cursor.getColumnIndex("IH209_9"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_9"));
//			caratula.ih209_10 = cursor.getString(cursor.getColumnIndex("IH209_10"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_10"));
//			caratula.ih209_11 = cursor.getString(cursor.getColumnIndex("IH209_11"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_11"));
//			caratula.ih209_12 = cursor.getString(cursor.getColumnIndex("IH209_12"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_12"));
//			caratula.ih209_13 = cursor.getString(cursor.getColumnIndex("IH209_13"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_13"));
//			caratula.ih209_14 = cursor.getString(cursor.getColumnIndex("IH209_14"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_14"));
//			caratula.ih209_15 = cursor.getString(cursor.getColumnIndex("IH209_15"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_15"));
//			caratula.ih209_16 = cursor.getString(cursor.getColumnIndex("IH209_16"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_16"));
//			caratula.ih209_17 = cursor.getString(cursor.getColumnIndex("IH209_17"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_17"));
//			caratula.ih209_18 = cursor.getString(cursor.getColumnIndex("IH209_18"))==null?null:cursor.getInt(cursor.getColumnIndex("IH209_18"));
//			caratula.ih209_18_o = cursor.getString(cursor.getColumnIndex("IH209_18_O"));
//			caratula.ih210 = cursor.getString(cursor.getColumnIndex("IH210"))==null?null:cursor.getInt(cursor.getColumnIndex("IH210"));
//			caratula.ih211 = cursor.getString(cursor.getColumnIndex("IH211"))==null?null:cursor.getInt(cursor.getColumnIndex("IH211"));
//			caratula.ih212_a = cursor.getString(cursor.getColumnIndex("IH212_A"));
//			caratula.ih212_b = cursor.getString(cursor.getColumnIndex("IH212_B"));
//			caratula.ih213 = cursor.getString(cursor.getColumnIndex("IH213"))==null?null:cursor.getInt(cursor.getColumnIndex("IH213"));
//			caratula.ih214 = cursor.getString(cursor.getColumnIndex("IH214"))==null?null:cursor.getInt(cursor.getColumnIndex("IH214"));
////			caratula.sum_ih213 = cursor.getString(cursor.getColumnIndex("SUM_IH213"))==null?null:cursor.getInt(cursor.getColumnIndex("SUM_IH213"));
////			caratula.sum_ih214 = cursor.getString(cursor.getColumnIndex("SUM_IH214"))==null?null:cursor.getInt(cursor.getColumnIndex("SUM_IH214"));
//			caratula.obs_03_200 = cursor.getString(cursor.getColumnIndex("OBS_03_200"));
////			caratula.usr_creacion = cursor.getString(cursor.getColumnIndex("USR_CREACION"));
////			caratula.fec_creacion = cursor.getString(cursor.getColumnIndex("FEC_CREACION"));
////			caratula.usr_edicion = cursor.getString(cursor.getColumnIndex("USR_EDICION"));
////			caratula.fec_edicion = cursor.getString(cursor.getColumnIndex("FEC_EDICION"));
////			caratula.fecha_recepcion = cursor.getString(cursor.getColumnIndex("FECHA_RECEPCION"));
//			caratula.ih203_nro_doc = cursor.getString(cursor.getColumnIndex("IH203_NRO_DOC"));
//			
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
//		return caratulas;
//	}
	
	public ArrayList<Cap300Delitos> fillCap300Delitos(String id_n){
//		SQLiteDatabase dbr = dbh.getReadableDatabase();
		ArrayList<Cap300Delitos> caratulas = new ArrayList<Cap300Delitos>();
		
//		Cursor cursor = dbr.rawQuery("SELECT * FROM CAP300 WHERE ID_N=?", new String[]{id_n});
//		
//		while(cursor.moveToNext()){
//			Cap300Delitos caratula = new Cap300Delitos();
//			caratula.nro_vfreg = cursor.getString(cursor.getColumnIndex("NRO_VFREG"))==null?null:cursor.getInt(cursor.getColumnIndex("NRO_VFREG"));
//			caratula.nro_mreg = cursor.getString(cursor.getColumnIndex("NRO_MREG"))==null?null:cursor.getInt(cursor.getColumnIndex("NRO_MREG"));
//			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//			caratula.orden_300 = cursor.getString(cursor.getColumnIndex("ORDEN_300"))==null?null:cursor.getInt(cursor.getColumnIndex("ORDEN_300"));
//			caratula.ivh301a = cursor.getString(cursor.getColumnIndex("IVH301A"));
//			caratula.ivh301b = cursor.getString(cursor.getColumnIndex("IVH301B"));
//			caratula.ivh301c = cursor.getString(cursor.getColumnIndex("IVH301C"));
//			caratula.ivh302 = cursor.getString(cursor.getColumnIndex("IVH302"))==null?null:cursor.getInt(cursor.getColumnIndex("IVH302"));
//			caratula.ivh302_n = cursor.getString(cursor.getColumnIndex("IVH302_N"));
//			caratula.ivh303 = cursor.getString(cursor.getColumnIndex("IVH303"))==null?null:cursor.getInt(cursor.getColumnIndex("IVH303"));
//			caratula.ivh304a = cursor.getString(cursor.getColumnIndex("IVH304A"))==null?null:cursor.getInt(cursor.getColumnIndex("IVH304A"));
//			caratula.ivh304 = cursor.getString(cursor.getColumnIndex("IVH304"))==null?null:cursor.getInt(cursor.getColumnIndex("IVH304"));
//			caratula.ivh305_dia = cursor.getString(cursor.getColumnIndex("IVH305_DIA"));
//			caratula.ivh305_mes = cursor.getString(cursor.getColumnIndex("IVH305_MES"));
//			caratula.ivh305_anio = cursor.getString(cursor.getColumnIndex("IVH305_ANIO"));
//			caratula.ivh306 = cursor.getString(cursor.getColumnIndex("IVH306"))==null?null:cursor.getInt(cursor.getColumnIndex("IVH306"));
//			caratula.ivh307 = cursor.getString(cursor.getColumnIndex("IVH307"))==null?null:cursor.getInt(cursor.getColumnIndex("IVH307"));
//			caratula.ivh307_o = cursor.getString(cursor.getColumnIndex("IVH307_O"));
//			caratula.ivh308 = cursor.getString(cursor.getColumnIndex("IVH308"))==null?null:cursor.getInt(cursor.getColumnIndex("IVH308"));
//			caratula.ivh309 = cursor.getString(cursor.getColumnIndex("IVH309"))==null?null:cursor.getInt(cursor.getColumnIndex("IVH309"));
//			caratula.ivh309_o = cursor.getString(cursor.getColumnIndex("IVH309_O"));
//			caratula.obs_03_300 = cursor.getString(cursor.getColumnIndex("OBS_03_300"));
//			
//			caratulas.add(caratula);
//		}
//		
//		cursor.close();
//		cursor = null;
//		dbr.close();
//		dbr = null;
//		SQLiteDatabase.releaseMemory();
		return caratulas;
	}
	
	public ArrayList<Cap400Delitos> fillCap400Delitos(String id_n){
		SQLiteDatabase dbr = dbh.getReadableDatabase();
		ArrayList<Cap400Delitos> caratulas = new ArrayList<Cap400Delitos>();
		
		Cursor cursor = dbr.rawQuery("SELECT * FROM CAP400 WHERE ID_N=?", new String[]{id_n});
		
		while(cursor.moveToNext()){
			Cap400Delitos caratula = new Cap400Delitos();
			caratula.nro_pvreg = cursor.getString(cursor.getColumnIndex("NRO_PVREG"))==null?null:cursor.getInt(cursor.getColumnIndex("NRO_PVREG"));
			caratula.nro_mreg = cursor.getString(cursor.getColumnIndex("NRO_MREG"))==null?null:cursor.getInt(cursor.getColumnIndex("NRO_MREG"));
			caratula.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
			caratula.orden_400 = cursor.getString(cursor.getColumnIndex("ORDEN_400"))==null?null:cursor.getInt(cursor.getColumnIndex("ORDEN_400"));
			caratula.ivm401a = cursor.getString(cursor.getColumnIndex("IVM401A"));
			caratula.ivm401b = cursor.getString(cursor.getColumnIndex("IVM401B"));
			caratula.ivm401c = cursor.getString(cursor.getColumnIndex("IVM401C"));
			caratula.ivm402 = cursor.getString(cursor.getColumnIndex("IVM402"))==null?null:cursor.getInt(cursor.getColumnIndex("IVM402"));
			caratula.ivm402_n = cursor.getString(cursor.getColumnIndex("IVM402_N"));
			caratula.ivm403 = cursor.getString(cursor.getColumnIndex("IVM403"))==null?null:cursor.getInt(cursor.getColumnIndex("IVM403"));
			caratula.ivm404 = cursor.getString(cursor.getColumnIndex("IVM404"));
			caratula.ivm405_dia = cursor.getString(cursor.getColumnIndex("IVM405_DIA"));
			caratula.ivm405_mes = cursor.getString(cursor.getColumnIndex("IVM405_MES"));
			caratula.ivm405_anio = cursor.getString(cursor.getColumnIndex("IVM405_ANIO"));
			caratula.ivm406 = cursor.getString(cursor.getColumnIndex("IVM406"))==null?null:cursor.getInt(cursor.getColumnIndex("IVM406"));
			caratula.ivm407 = cursor.getString(cursor.getColumnIndex("IVM407"))==null?null:cursor.getInt(cursor.getColumnIndex("IVM407"));
			caratula.ivm407_o = cursor.getString(cursor.getColumnIndex("IVM407_O"));
			caratula.ivm408 = cursor.getString(cursor.getColumnIndex("IVM408"))==null?null:cursor.getInt(cursor.getColumnIndex("IVM408"));
			caratula.obs_03_400 = cursor.getString(cursor.getColumnIndex("OBS_03_400"));
			
			caratulas.add(caratula);
		}
		
		cursor.close();
		cursor = null;
		dbr.close();
		dbr = null;
		SQLiteDatabase.releaseMemory();
		return caratulas;
	}
	
	
//	public boolean saveCaratulaDelitos(CaratulaDelitos caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("ANIO", caratula.anio);
//		content.put("II1", caratula.ii1);
//		content.put("II2", caratula.ii2);
//		content.put("II3", caratula.ii3);
//		content.put("II4", caratula.ii4);
//		content.put("II5", caratula.ii5);
//		content.put("II6", caratula.ii6);
//		content.put("II7", caratula.ii7);
//		content.put("II8", caratula.ii8);
//		content.put("II8_O", caratula.ii8_o);
//		content.put("II9", caratula.ii9);
//		content.put("II9_O", caratula.ii9_o);
//		content.put("IV2_1_D", caratula.iv2_1_d);
//		content.put("IV2_1_M", caratula.iv2_1_m);
//		content.put("IV_2_1_A", caratula.iv_2_1_a);
//		content.put("IVRESFIN_02", caratula.ivresfin_02);
//		content.put("IVRESFIN_02_O", caratula.ivresfin_02_o);
//		content.put("V3_1", caratula.v3_1);
//		content.put("V3_2", caratula.v3_2);
//		content.put("V3_3", caratula.v3_3);
//		content.put("V3_4", caratula.v3_4);
//		content.put("VI1A", caratula.vi1a);
//		content.put("VI1B", caratula.vi1b);
//		content.put("VI3A", caratula.vi3a);
//		content.put("VI3B", caratula.vi3b);
//		content.put("VII1A", caratula.vii1a);
//		content.put("VII1B", caratula.vii1b);
//		content.put("VII1C", caratula.vii1c);
//		content.put("VII1D", caratula.vii1d);
//		content.put("VII3_1D_NT", caratula.vii3_1d_nt);
//		content.put("VII1E", caratula.vii1e);
//		content.put("VII3_1E_NT", caratula.vii3_1e_nt);
//		content.put("VII1F", caratula.vii1f);
//		content.put("VII3_1F_NT", caratula.vii3_1f_nt);
//		content.put("VII2A", caratula.vii2a);
//		content.put("VII2B", caratula.vii2b);
//		content.put("VII2C", caratula.vii2c);
//		content.put("VII2D", caratula.vii2d);
//		content.put("VII3_2D_NT", caratula.vii3_2d_nt);
//		content.put("VII2E", caratula.vii2e);
//		content.put("VII3_2E_NT", caratula.vii3_2e_nt);
//		content.put("VII2F", caratula.vii2f);
//		content.put("VII3_2F_NT", caratula.vii3_2f_nt);
//		content.put("OBS_CAR", caratula.obs_car);
//		content.put("TOTAL_DENUNCIAS", caratula.total_denuncias);
//		content.put("CONTE_REG200", caratula.conte_reg200);
//		content.put("CONTE_REG300", caratula.conte_reg300);
//		content.put("CONTE_REG400", caratula.conte_reg400);
//		content.put("USR_CREACION", caratula.usr_creacion);
//		content.put("FEC_CREACION", caratula.fec_creacion);
//		content.put("USR_EDICION", caratula.usr_edicion);
//		content.put("FEC_EDICION", caratula.fec_edicion);
//		content.put("fecha_recepcion", caratula.fecha_recepcion);
//		content.put("CERRADO", caratula.cerrado);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("CARATULA", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
//	public boolean saveVisitaDelitos(DelVisita caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
////		content.put("ANIO", caratula.anio);
//		content.put("III2_1", caratula.iii2_1);
//		content.put("III2_2_D", caratula.iii2_2_d);
//		content.put("III2_2_M", caratula.iii2_2_m);
//		content.put("III2_3A_IH", caratula.iii2_3a_ih);
//		content.put("III2_3A_IM", caratula.iii2_3a_im);
//		content.put("GPSLATITUD_INI", caratula.gpslatitud_ini);
//		content.put("GPSLONGITUD_INI", caratula.gpslongitud_ini);
//		content.put("III2_3B_FH", caratula.iii2_3b_fh);
//		content.put("III2_3B_FM", caratula.iii2_3b_fm);
//		content.put("GPSLATITUD_FIN", caratula.gpslatitud_fin);
//		content.put("GPSLONGITUD_FIN", caratula.gpslongitud_fin);
//		content.put("III2_4A_D", caratula.iii2_4a_d);
//		content.put("III2_4A_M", caratula.iii2_4a_m);
//		content.put("III2_4B_H", caratula.iii2_4b_h);
//		content.put("III2_4B_M", caratula.iii2_4b_m);
//		content.put("III2_5", caratula.iii2_5);
//		content.put("III2_6_D", caratula.iii2_6_d);
//		content.put("III2_6_M", caratula.iii2_6_m);
//		content.put("III2_7A_H", caratula.iii2_7a_h);
//		content.put("III2_7A_M", caratula.iii2_7a_m);
//		content.put("III2_7B_H", caratula.iii2_7b_h);
//		content.put("III2_7B_M", caratula.iii2_7b_m);
//		content.put("III2_8", caratula.iii2_8);
////		content.put("USUCRE", caratula.usucre);
////		content.put("FECCRE", caratula.feccre);
////		content.put("USUREG", caratula.usureg);
////		content.put("FECENV", caratula.fecenv);
////		content.put("FECHA_RECEPCION", caratula.fecha_recepcion);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("CARATULA_ENTREVISTA", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
	public boolean saveCap100Delitos(Cap100Delitos cap100, List<String> fields) {
		boolean success = false;
//		boolean exist = existeCap100Delitos(cap100.id_n);
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			cv.put("ID_N", cap100.id_n);
//
//	    	cv = cap100.getContentValues(cv, Utilidades.getListFieldsEntity(cap100, fields, true).lstfields);
//
//			if(exist) {
////				cv.put("USUREG", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
//				success = db.update("T_DIG_03_100", cv, "ID_N=?", new String[]{cap100.id_n})>0;
//			}
//			else {
////				cv.put("FECCRE", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
////				cv.put("USUCRE", Utilidades.getInt(Globales.currentUser.id,-1));
////				cv.put("USUREG", Utilidades.getInt(Globales.currentUser.id,-1));
//				long as = db.insert("T_DIG_03_100", null, cv);
//				success =  as != -1;
//			}
//			updateSumaNroDelitos(db, cap100.id_n);
//			
//		} 
//		catch (Exception e) {
//			Log.d(TAG, e.getMessage());		
//		} 
//		finally {
//			db.close();
//		}
		
		return success;
	}
	public boolean saveOrUpdate(Cap100Delitos bean, SeccionCapitulo... secciones)
			throws java.sql.SQLException {
		return this
				.saveOrUpdate(
						TABLA_C100,
						"ID_N=? ",
						bean, new String[] { "id_n" },
						secciones);
	}
	
//	public boolean saveCap100Delitos(Cap100Delitos caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("ID_N", caratula.id_n);
//		content.put("DN101", caratula.dn101);
//		content.put("DN101_1", caratula.dn101_1);
//		content.put("DN101_1_A", caratula.dn101_1_a);
//		content.put("DN101_1_B", caratula.dn101_1_b);
//		content.put("DN101_1_C", caratula.dn101_1_c);
//		content.put("DN101_1_D", caratula.dn101_1_d);
//		content.put("DN101_1_E", caratula.dn101_1_e);
//		content.put("DN101_1_F", caratula.dn101_1_f);
//		content.put("DN101_1_G", caratula.dn101_1_g);
//		content.put("DN101_2", caratula.dn101_2);
//		content.put("DN101_3", caratula.dn101_3);
//		content.put("DN101_4", caratula.dn101_4);
//		content.put("DN101_5", caratula.dn101_5);
//		content.put("DN101_6", caratula.dn101_6);
//		content.put("SUM_FALLECIDOS", caratula.sum_fallecidos);
//		content.put("DN101_7", caratula.dn101_7);
//		content.put("DN101_8", caratula.dn101_8);
//		content.put("DN101_9", caratula.dn101_9);
//		content.put("DN101_10", caratula.dn101_10);
//		content.put("DN101_11", caratula.dn101_11);
//		content.put("DN102", caratula.dn102);
//		content.put("DN103", caratula.dn103);
//		content.put("DN104", caratula.dn104);
//		content.put("DN104_A", caratula.dn104_a);
//		content.put("DN104_B", caratula.dn104_b);
//		content.put("DN105", caratula.dn105);
//		content.put("DN105_A", caratula.dn105_a);
//		content.put("DN105_B", caratula.dn105_b);
//		content.put("DN105_C", caratula.dn105_c);
//		content.put("DN106", caratula.dn106);
//		content.put("DN107", caratula.dn107);
//		content.put("DN108", caratula.dn108);
//		content.put("DN109", caratula.dn109);
//		content.put("DN110", caratula.dn110);
//		content.put("DN111", caratula.dn111);
//		content.put("DN112", caratula.dn112);
//		content.put("DN113", caratula.dn113);
//		content.put("DN114", caratula.dn114);
//		content.put("DN115", caratula.dn115);
//		content.put("DN116", caratula.dn116);
//		content.put("DN117", caratula.dn117);
//		content.put("DN118", caratula.dn118);
//		content.put("DN119", caratula.dn119);
//		content.put("DN120", caratula.dn120);
//		content.put("DN121", caratula.dn121);
//		content.put("OBS_03_100", caratula.obs_03_100);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("CAP100", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
	private void updateSumaNroDelitos(SQLiteDatabase dbr, String id_n){
		dbr.execSQL("UPDATE "+TABLA_C100+" "
				+ "SET DN120 = (IFNULL(DN101,0) + IFNULL(DN102,0) + IFNULL(DN103,0) + "
				+ "IFNULL(DN104,0) + IFNULL(DN105,0) + IFNULL(DN106,0) + IFNULL(DN107,0) + IFNULL(DN108,0) + "
				+ "IFNULL(DN109,0) + IFNULL(DN110,0) + IFNULL(DN111,0) + IFNULL(DN112,0) + IFNULL(DN113,0) + "
				+ "IFNULL(DN114,0) + IFNULL(DN115,0) + IFNULL(DN116,0) + IFNULL(DN117,0) + IFNULL(DN118,0) + "
				+ "IFNULL(DN119,0)), "
				+ "DN121 = DN101_6 "
				+ "WHERE ID_N = '"+id_n+"' ");
		dbr.execSQL("UPDATE "+TABLA_C100+" "
				+ "SET TOTAL_DENUNCIAS = IFNULL(DN120, 0) + IFNULL(DN121, 0) "
				+ "WHERE ID_N = '"+id_n+"' ");
	}
	
	private void updateResults(SQLiteDatabase dbr, String id_n, Integer nro_mreg){
		dbr.execSQL("UPDATE "+TABLA_CARATULA+" "
				+ "SET V3_3 = (SELECT COUNT(ID_N) FROM "+TABLA_C300+" WHERE ID_N = '"+id_n+"'), "
				+ "V3_4 = (SELECT COUNT(D.ID_N) FROM "+TABLA_C300+" C "
				+ "INNER JOIN (SELECT ID_N, NRO_MREG, IFNULL(IH208_COD,IH208) AS IH208 FROM "+TABLA_C200+") D ON (D.[ID_N] = C.[ID_N] "
				+ "AND D.[NRO_MREG] = C.[NRO_MREG]) "
				+ "WHERE D.ID_N = '"+id_n+"' "
				+ "AND D.IH208 IN (1,2,3,4,5,6,7,8,12,13,14,16)) "
				+ "WHERE ID_N = '"+id_n+"' ");
	}
	
	private void updateConteo200(SQLiteDatabase dbr, String id_n){
		dbr.execSQL("UPDATE "+TABLA_C200+" "
				+ "SET ORDEN_200 = "
				+ "(SELECT COUNT(ID_N) FROM "+TABLA_C200+" C "
				+ "WHERE C.ID_N = '"+id_n+"' "
				+ "AND C.NRO_MREG <= "+TABLA_C200+".NRO_MREG) "
				+ "WHERE ID_N = '"+id_n+"' ");
	}
	
	private void updateConteo300(SQLiteDatabase dbr, String id_n, Integer nro_mreg){
		dbr.execSQL("UPDATE "+TABLA_C300+" "
				+ "SET ORDEN_300 = "
				+ "(SELECT COUNT(ID_N) FROM "+TABLA_C300+" C "
				+ "WHERE C.ID_N = '"+id_n+"' "
				+ "AND C.NRO_MREG = "+nro_mreg+" "
				+ "AND C.NRO_VFREG <= "+TABLA_C300+".NRO_VFREG) "
				+ "WHERE ID_N = '"+id_n+"' "
				+ "AND NRO_MREG = "+nro_mreg);
	}
	
	private void update300_P309(SQLiteDatabase dbr, String id_n, Integer nro_mreg){
		dbr.execSQL("UPDATE "+TABLA_C300+" "
				+ "SET IVH309 = null, "
				+ "IVH309_O = null "
				+ "WHERE ID_N = '"+id_n+"' "
				+ "AND NRO_MREG = "+nro_mreg);
	}
	
	public void updateConteo400(SQLiteDatabase dbr, String id_n, Integer nro_mreg){
		dbr.execSQL("UPDATE "+TABLA_C400+" "
				+ "SET ORDEN_400 = "
				+ "(SELECT COUNT(ID_N) FROM "+TABLA_C400+" C "
				+ "WHERE C.ID_N = '"+id_n+"' "
				+ "AND C.NRO_MREG = "+nro_mreg+" "
				+ "AND C.NRO_PVREG <= "+TABLA_C400+".NRO_PVREG) "
				+ "WHERE ID_N = '"+id_n+"' "
				+ "AND NRO_MREG = "+nro_mreg);
	}
	
	public boolean saveCap200Delitos(Cap200Delitos cap200, List<String> fields) {
		boolean success = false;
//		boolean exist = existeCap200Delitos(cap200.id_n, cap200.nro_mreg);
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			cv.put("ID_N", cap200.id_n);
////			cv.put("NRO_MREG", cap200.nro_mreg);
//
//	    	cv = cap200.getContentValues(cv, Utilidades.getListFieldsEntity(cap200, fields, true).lstfields);
//
//			if(exist) {
////				cv.put("USUREG", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
//				success = db.update("T_DIG_03_200", cv, "ID_N=? AND NRO_MREG=?", new String[]{cap200.id_n, cap200.nro_mreg.toString()})>0;
//				if(!Util.esDiferente(cap200.ih204, 17,18,19,20,21,22,23,24)) update300_P309(db, cap200.id_n, cap200.nro_mreg);
//			}
//			else {
////				cv.put("FECCRE", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
////				cv.put("USUCRE", Utilidades.getInt(Globales.currentUser.id,-1));
////				cv.put("USUREG", Utilidades.getInt(Globales.currentUser.id,-1));
//				long as = db.insert("T_DIG_03_200", null, cv);
//				success =  as != -1;
//				updateConteo200(db, cap200.id_n);
//			}
//			
//		} 
//		catch (Exception e) {
//			Log.d(TAG, e.getMessage());		
//		} 
//		finally {
//			db.close();
//		}
//		
		return success;
	}
	public boolean saveOrUpdate(Cap200Delitos bean, MAINTENCE opcion, SeccionCapitulo... secciones)
			throws java.sql.SQLException {
		return opcion == MAINTENCE.ONEONLY ? this
				.saveOrUpdate(
						TABLA_C200,
						"ID_N=? AND NRO_MREG=?",
						bean, new String[] { "id_n", "nro_mreg" },
						secciones): this
						.saveOrUpdate(
								TABLA_C200,
								"ID_N=?",
								bean, new String[] { "id_n" },
								secciones);
							
	}
	
//	public boolean saveCap200Delitos(Cap200Delitos caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("NRO_MREG", caratula.nro_mreg);
//		content.put("ID_N", caratula.id_n);
////		content.put("ANIO", caratula.anio);
//		content.put("ORDEN_200", caratula.orden_200);
//		content.put("IH201", caratula.ih201);
//		content.put("IH201_O", caratula.ih201_o);
//		content.put("IH202", caratula.ih202);
//		content.put("IH202_O", caratula.ih202_o);
//		content.put("IH203", caratula.ih203);
//		content.put("IH203_O", caratula.ih203_o);
//		content.put("IH204", caratula.ih204);
//		content.put("IH204_O", caratula.ih204_o);
//		content.put("IH205_DIA", caratula.ih205_dia);
//		content.put("IH205_MES", caratula.ih205_mes);
//		content.put("IH205_ANIO", caratula.ih205_anio);
//		content.put("IH206_HOR", caratula.ih206_hor);
//		content.put("IH206_MIN", caratula.ih206_min);
//		content.put("IH207", caratula.ih207);
//		content.put("IH207_O", caratula.ih207_o);
//		content.put("IH208", caratula.ih208);
//		content.put("IH208_O", caratula.ih208_o);
//		content.put("IH209_1", caratula.ih209_1);
//		content.put("IH209_2", caratula.ih209_2);
//		content.put("IH209_3", caratula.ih209_3);
//		content.put("IH209_4", caratula.ih209_4);
//		content.put("IH209_5", caratula.ih209_5);
//		content.put("IH209_6", caratula.ih209_6);
//		content.put("IH209_7", caratula.ih209_7);
//		content.put("IH209_8", caratula.ih209_8);
//		content.put("IH209_9", caratula.ih209_9);
//		content.put("IH209_10", caratula.ih209_10);
//		content.put("IH209_11", caratula.ih209_11);
//		content.put("IH209_12", caratula.ih209_12);
//		content.put("IH209_13", caratula.ih209_13);
//		content.put("IH209_14", caratula.ih209_14);
//		content.put("IH209_15", caratula.ih209_15);
//		content.put("IH209_16", caratula.ih209_16);
//		content.put("IH209_17", caratula.ih209_17);
//		content.put("IH209_18", caratula.ih209_18);
//		content.put("IH209_18_O", caratula.ih209_18_o);
//		content.put("IH210", caratula.ih210);
//		content.put("IH211", caratula.ih211);
//		content.put("IH212_A", caratula.ih212_a);
//		content.put("IH212_B", caratula.ih212_b);
//		content.put("IH213", caratula.ih213);
//		content.put("IH214", caratula.ih214);
//		content.put("SUM_IH213", caratula.sum_ih213);
//		content.put("SUM_IH214", caratula.sum_ih214);
//		content.put("OBS_03_200", caratula.obs_03_200);
////		content.put("USR_CREACION", caratula.usr_creacion);
////		content.put("FEC_CREACION", caratula.fec_creacion);
////		content.put("USR_EDICION", caratula.usr_edicion);
////		content.put("FEC_EDICION", caratula.fec_edicion);
////		content.put("FECHA_RECEPCION", caratula.fecha_recepcion);
//		content.put("IH203_NRO_DOC", caratula.ih203_nro_doc);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("CAP200", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
	public boolean saveCap300Delitos(Cap300Delitos cap300, List<String> fields) {
		boolean success = false;
//		boolean exist = existeCap300Delitos(cap300.id_n, cap300.nro_mreg, cap300.nro_vfreg);
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			cv.put("ID_N", cap300.id_n);
////			cv.put("NRO_MREG", cap300.nro_mreg);
////			cv.put("NRO_VFREG", cap300.nro_vfreg);
//
//	    	cv = cap300.getContentValues(cv, Utilidades.getListFieldsEntity(cap300, fields, true).lstfields);
//
//			if(exist) {
////				cv.put("USUREG", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
//				success = db.update("T_DIG_03_300", cv, "ID_N=? AND NRO_MREG=? AND NRO_VFREG=?", 
//						new String[]{cap300.id_n, cap300.nro_mreg.toString(), cap300.nro_vfreg.toString()})>0;
//			}
//			else {
////				cv.put("FECCRE", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
////				cv.put("USUCRE", Utilidades.getInt(Globales.currentUser.id,-1));
////				cv.put("USUREG", Utilidades.getInt(Globales.currentUser.id,-1));
//				long as = db.insert("T_DIG_03_300", null, cv);
//				success =  as != -1;
//				updateConteo300(db, cap300.id_n,cap300.nro_mreg);
//			}
//			
//		} 
//		catch (Exception e) {
//			Log.d(TAG, e.getMessage());		
//		} 
//		finally {
//			db.close();
//		}
//		
		return success;
	}
	public boolean saveOrUpdate(Cap300Delitos bean, MAINTENCE opcion, SeccionCapitulo... secciones)
			throws java.sql.SQLException {
		return opcion == MAINTENCE.ONEONLY ? this
				.saveOrUpdate(
						TABLA_C300,
						"ID_N=? AND NRO_MREG=? AND NRO_VFREG=?",
						bean, new String[] { "id_n", "nro_mreg", "nro_vfreg" },
						secciones): this
						.saveOrUpdate(
								TABLA_C300,
								"ID_N=? AND NRO_MREG=?",
								bean, new String[] { "id_n", "nro_mreg" },
								secciones);
							
	}
	
//	public boolean saveCap300Delitos(Cap300Delitos caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("NRO_VFREG", caratula.nro_vfreg);
//		content.put("NRO_MREG", caratula.nro_mreg);
//		content.put("ID_N", caratula.id_n);
//		content.put("ORDEN_300", caratula.orden_300);
//		content.put("IVH301A", caratula.ivh301a);
//		content.put("IVH301B", caratula.ivh301b);
//		content.put("IVH301C", caratula.ivh301c);
//		content.put("IVH302", caratula.ivh302);
//		content.put("IVH302_N", caratula.ivh302_n);
//		content.put("IVH303", caratula.ivh303);
//		content.put("IVH304A", caratula.ivh304a);
//		content.put("IVH304", caratula.ivh304);
//		content.put("IVH305_DIA", caratula.ivh305_dia);
//		content.put("IVH305_MES", caratula.ivh305_mes);
//		content.put("IVH305_ANIO", caratula.ivh305_anio);
//		content.put("IVH306", caratula.ivh306);
//		content.put("IVH307", caratula.ivh307);
//		content.put("IVH307_O", caratula.ivh307_o);
//		content.put("IVH308", caratula.ivh308);
//		content.put("IVH309", caratula.ivh309);
//		content.put("IVH309_O", caratula.ivh309_o);
//		content.put("OBS_03_300", caratula.obs_03_300);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("CAP300", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
	public boolean saveCap400Delitos(Cap400Delitos cap400, List<String> fields) {
		boolean success = false;
//		boolean exist = existeCap400Delitos(cap400.id_n, cap400.nro_mreg, cap400.nro_pvreg);
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			cv.put("ID_N", cap400.id_n);
////			cv.put("NRO_MREG", cap400.nro_mreg);
////			cv.put("NRO_PVREG", cap400.nro_pvreg);
//
//	    	cv = cap400.getContentValues(cv, Utilidades.getListFieldsEntity(cap400, fields, true).lstfields);
//
//			if(exist) {
////				cv.put("USUREG", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
//				success = db.update("T_DIG_03_400", cv, "ID_N=? AND NRO_MREG=? AND NRO_PVREG=?", 
//						new String[]{cap400.id_n, cap400.nro_mreg.toString(), cap400.nro_pvreg.toString()})>0;
//			}
//			else {
////				cv.put("FECCRE", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
////				cv.put("USUCRE", Utilidades.getInt(Globales.currentUser.id,-1));
////				cv.put("USUREG", Utilidades.getInt(Globales.currentUser.id,-1));
//				long as = db.insert("T_DIG_03_400", null, cv);
//				success =  as != -1;
//				updateConteo400(db, cap400.id_n, cap400.nro_mreg);
//			}
//			
//		} 
//		catch (Exception e) {
//			Log.d(TAG, e.getMessage());		
//		} 
//		finally {
//			db.close();
//		}
		
		return success;
	}
	public boolean saveOrUpdate(Cap400Delitos bean, MAINTENCE opcion, SeccionCapitulo... secciones)
			throws java.sql.SQLException {
		return opcion == MAINTENCE.ONEONLY ? this
				.saveOrUpdate(
						TABLA_C400,
						"ID_N=? AND NRO_MREG=? AND NRO_PVREG=?",
						bean, new String[] { "id_n", "nro_mreg", "nro_pvreg" },
						secciones): this
						.saveOrUpdate(
								TABLA_C400,
								"ID_N=? AND NRO_MREG=?",
								bean, new String[] { "id_n", "nro_mreg" },
								secciones);
							
	}
	
//	public boolean saveCap400Delitos(Cap400Delitos caratula){
//		ContentValues content = new ContentValues();
//		
//		content.put("NRO_PVREG", caratula.nro_pvreg);
//		content.put("NRO_MREG", caratula.nro_mreg);
//		content.put("ID_N", caratula.id_n);
//		content.put("ORDEN_400", caratula.orden_400);
//		content.put("IVM401A", caratula.ivm401a);
//		content.put("IVM401B", caratula.ivm401b);
//		content.put("IVM401C", caratula.ivm401c);
//		content.put("IVM402", caratula.ivm402);
//		content.put("IVM402_N", caratula.ivm402_n);
//		content.put("IVM403", caratula.ivm403);
//		content.put("IVM404", caratula.ivm404);
//		content.put("IVM405_DIA", caratula.ivm405_dia);
//		content.put("IVM405_MES", caratula.ivm405_mes);
//		content.put("IVM405_ANIO", caratula.ivm405_anio);
//		content.put("IVM406", caratula.ivm406);
//		content.put("IVM407", caratula.ivm407);
//		content.put("IVM407_O", caratula.ivm407_o);
//		content.put("IVM408", caratula.ivm408);
//		content.put("OBS_03_400", caratula.obs_03_400);
//		
//		SQLiteDatabase dbw = dbh.getWritableDatabase();
//		boolean result = dbw.insert("CAP400", null, content)!=-1;
//		
//		dbw.close();
//		dbw = null;
//		SQLiteDatabase.releaseMemory();
//		
//		return result;
//	}
	
	
//	public boolean saveCaratulasDelitos(List<CaratulaDelitos> caratulas){
//		Log.e("saveCaratulasDelitos", "caratulas: "+caratulas);
////		Log.e("saveCaratulasDelitos", "caratulas.isEmpty(): "+caratulas.isEmpty());
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		Log.e("tot", "tot: "+tot);
//		for(int i=0; i<tot; i++){
//			if(!saveCaratulaDelitos(caratulas.get(i))) return false;
//		}
//		return true;
//	}
	
//	public boolean saveVisitasDelitos(List<DelVisita> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveVisitaDelitos(caratulas.get(i))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveCap100Delitos(List<Cap100Delitos> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveCap100Delitos(caratulas.get(i))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveCap200Delitos(List<Cap200Delitos> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveCap200Delitos(caratulas.get(i))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveCap300Delitos(List<Cap300Delitos> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveCap300Delitos(caratulas.get(i))) return false;
//		}
//		return true;
//	}
//	
//	public boolean saveCap400Delitos(List<Cap400Delitos> caratulas){
//		if(caratulas==null) return true;
//		if(caratulas.isEmpty()) return true;
//		int tot = caratulas.size();
//		for(int i=0; i<tot; i++){
//			if(!saveCap400Delitos(caratulas.get(i))) return false;
//		}
//		return true;
//	}
	
	public boolean saveOrUpdate(SQLiteDatabase dbTX, XMLDataObject dataObjects, CounterObservable contadorObserver) throws java.sql.SQLException {
		boolean isTX = true;
		boolean flag = true;
		if (dbTX == null) {
			isTX = false;
			dbTX = this.dbh.getWritableDatabase();
		}
		for (int i = 0; i < dataObjects.getRegistros().size() && flag; i++) {
			if (dataObjects.getRegistros().get(i).get(dataObjects.getPkFields().get(dataObjects.getPkFields().size()-1)) == null) {
				//Log.e(getClass().getSimpleName(), "TableName: " + dataObjects.getTableName() + " pk vacia: " + dataObjects.getPkFields().get(dataObjects.getPkFields().size()-1));
				int nextID = super.nextID(dbTX, dataObjects.getPkFields().get(dataObjects.getPkFields().size()-1), dataObjects.getTableName(), dataObjects.getStringWhereNextID(), dataObjects.getWhereValuesNextID(dataObjects.getRegistros().get(i)));
				dataObjects.getRegistros().get(i).put(dataObjects.getPkFields().get(dataObjects.getPkFields().size()-1),nextID);
			}
			flag = super.saveOrUpdate(dbTX, dataObjects.getTableName(), dataObjects.getRegistros().get(i), dataObjects.getPkFields().toArray(new String[dataObjects.getPkFields().size()]));
			if (contadorObserver != null) {
				contadorObserver.insertado(1);	
			}
		}
		if (!isTX) {
			dbTX.close();
			dbTX = null;
			SQLiteDatabase.releaseMemory();
		}
		return flag;
	}
	
	public Navegation getNavegacion(String id) {
		return (Navegation)getBean(VISTA_NAVEGATION,
				"ID_N=? ",
				Navegation.class, id);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String where, String...whereValues) {
		return super.getMaps(tableName, new String[]{"*"}, where, whereValues);
	}
	
	public List<Map<String, Object>> getRegistros(String tableName, String[] campos, String where, String...whereValues) {
		return super.getMaps(tableName, campos, where, whereValues);
	}	
	
	public Map<String, Object> getDepAsig(String codigo) {
		return getMap(MarcoDAO.TABLA_DEP_ASIG, new String[]{"NOMBRE"}, "CODIGO = ?", codigo);
	}
	
	
//	public List<ListVictimas> getListaVictimas(String codExep) {
//		return getBeans(TABLA_C300, "ID_N NOT NULL", ListVictimas.class);
//	}
//	
//	private String getStringCodLenguas(String codExep){
//		if(codExep == null) return "";
//		String[] ces = codExep.split(",");
//		String resp = "";
//		for(String c : ces){
//			resp = resp + ",'" + c.trim() + "'";
//		}
//		return " AND CODIGO NOT IN ("+resp.replaceFirst(",", "")+")";
//	}
	
	
	
	public List<ListaVictimas> getListaVictimas() {		
		return getBeans(V_LISTAVICTIMAS, "CODIGO NOT NULL", Search.class);
	}
	
	
	public List<Search> getModalidades() {		
		return getBeans(VISTA_MODALIDADES, "CODIGO IS NOT NULL", Search.class);
	}
	public Search getModalidad(Integer codigo) {		
		return (Search)getBean(VISTA_MODALIDADES, "CODIGO = ?", Search.class, String.valueOf(codigo));
	}
	
}