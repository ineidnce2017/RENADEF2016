package gob.inei.renadef2016.dao;

import gob.inei.dnce.dao.SQLiteDAO;
import gob.inei.renadef2016.modelo.Ubigeo;
import gob.inei.renadef2016.modelo.Usuario;

import java.util.List;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class UbigeoDAO extends SQLiteDAO {
	
	public static UbigeoDAO INSTANCE; 
	public static final String TABLA = "T_UBIGEO";
	public static final int PERIODO_TODOS = 99;

	private UbigeoDAO(MyDatabaseHelper dbh) {
		super(dbh);
	}
	
	public static UbigeoDAO getInstance(MyDatabaseHelper dbh) {
		if (INSTANCE == null) {
			INSTANCE = new UbigeoDAO(dbh);
		}
		return INSTANCE;
	}
	
	public boolean saveOrUpdate(Ubigeo bean) {
		return this.saveOrUpdate(bean, null); 
	}
	 
	public boolean saveOrUpdate(Ubigeo bean, SQLiteDatabase dbTX) {
		if (dbTX == null) {
			dbTX = dbh.getWritableDatabase();
		}
		String oper = existeRegistro(dbTX, "UBIGEO", TABLA, "UBIGEO = ?", bean.ubigeo) ? "edit":"add";
		ContentValues content = new ContentValues();	
		String[] campos = bean.getFieldsSaveNames();	
		content = bean.getContentValues(content, -1, -1, campos);		
		boolean result;
		if (oper.equals("add")) {
			result = dbTX.insertOrThrow(TABLA, null, content)!=-1;
		} else {
			result = dbTX.update(TABLA, content, "UBIGEO = ?", new String[]{bean.ubigeo})>0;
		}		
		SQLiteDatabase.releaseMemory();		
		return result;
	}
	
	public List<Ubigeo> getDepartamentos(String cod_sede) {
		StringBuilder sb = new StringBuilder();
		Query query = new Query();
		sb.append("SELECT DISTINCT u.CCDD AS UBIGEO, u.CCDD, u.DEPARTAMENTO").append(" ")
			.append("FROM ").append(TABLA).append(" u ");
		if (!"99".equals(cod_sede)) {
//			sb.append("JOIN ").append(SegmentacionDAO.TABLA_SEDES_OPERATIVA_UBIGEO).append(" so ON so.UBIGEO=u.ubigeo ").append(" ")
//				.append("WHERE so.COD_SEDE=?").append(" ");	
			
//			sb.append("JOIN ").append(MarcoDAO.TABLA_DEP_ASIG).append(" so ON so.CODDEP=u.CCDD ").append(" ")
//			.append("WHERE so.CODIGO=?").append(" ");	
			sb.append("JOIN (Select distinct COD_DEP_ASIG, CCDD from ").append(MarcoDAO.TABLA_MARCO).append(") so ON so.CCDD=u.CCDD ").append(" ")
			.append("WHERE so.COD_DEP_ASIG=?").append(" ");	
		}			
		sb.append("ORDER BY u.CCDD").append(" ");
		query.setStatement(sb.toString());
		if (!"99".equals(cod_sede)) {
			query.setWhereValues(new String[]{cod_sede});
		}
//		Log.e(getClass().getSimpleName(), query.toString());
		return getBeans(query, Ubigeo.class, "UBIGEO", "CCDD","DEPARTAMENTO");
	}
	
	public Ubigeo getDepartamento(String ccdd) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT CCDD AS UBIGEO, CCDD, DEPARTAMENTO").append(" ")
			.append("FROM ").append(TABLA).append(" ")
			.append("WHERE CCDD =?").append(" ")
			.append("ORDER BY CCDD").append(" ");
		Query query = new Query(sb.toString(), ccdd);
		return (Ubigeo) getBean(query, Ubigeo.class, "UBIGEO", "CCDD","DEPARTAMENTO");
	}
	
	public List<Ubigeo> getProvincias(String cod_sede, String ccdd) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT u.CCDD||u.CCPP AS UBIGEO, u.CCDD, u.DEPARTAMENTO, u.CCPP, u.PROVINCIA").append(" ")
			.append("FROM ").append(TABLA).append(" u ");
		Query query = new Query();
//		if (!"99".equals(cod_sede)) {
////			sb.append("JOIN ").append(SegmentacionDAO.TABLA_SEDES_OPERATIVA_UBIGEO).append(" so ON so.UBIGEO=u.ubigeo ");
//			sb.append("JOIN ").append(MarcoDAO.TABLA_DEP_ASIG).append(" so ON so.CODDEP=u.CCDD ").append(" ");
//		}
		sb.append("WHERE u.CCDD = ?").append(" ");
//		if (!"99".equals(cod_sede)) {
//			sb.append("AND so.CODIGO=?").append(" ");	
//		}
		sb.append("ORDER BY u.CCDD, u.CCPP").append(" ");
		query.setStatement(sb.toString());
//		if (!"99".equals(cod_sede)) {
//			query.setWhereValues(new String[]{ccdd, cod_sede});
//		} else {
			query.setWhereValues(new String[]{ccdd});
//		}
//		Log.e(getClass().getSimpleName(), query.toString());
		return getBeans(query, Ubigeo.class, "UBIGEO", "CCDD","DEPARTAMENTO", "CCPP", "PROVINCIA");
	}
	
	public Ubigeo getProvincia(String ccdd, String ccpp) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT CCDD||CCPP AS UBIGEO, CCDD, DEPARTAMENTO, CCPP, PROVINCIA").append(" ")
			.append("FROM ").append(TABLA).append(" ")
			.append("WHERE CCDD=? AND CCPP=?").append(" ")
			.append("ORDER BY CCDD").append(" ");
		Query query = new Query(sb.toString(), ccdd, ccpp);
		return (Ubigeo) getBean(query, Ubigeo.class, "UBIGEO", "CCDD", "DEPARTAMENTO", "CCPP", "PROVINCIA");
	}
	
	public List<Ubigeo> getDistritos(String cod_sede, String ccdd, String ccpp) {
		StringBuilder sb = new StringBuilder();
		Query query = new Query();
		sb.append("SELECT DISTINCT u.UBIGEO, u.CCDD, u.DEPARTAMENTO, u.CCPP, u.PROVINCIA, u.CCDI, u.DISTRITO").append(" ")
			.append("FROM ").append(TABLA).append(" u ");
//		if (!"99".equals(cod_sede)) {
////			sb.append("JOIN ").append(SegmentacionDAO.TABLA_SEDES_OPERATIVA_UBIGEO).append(" so ON so.UBIGEO=u.ubigeo ");
//			sb.append("JOIN ").append(MarcoDAO.TABLA_DEP_ASIG).append(" so ON so.CODDEP=u.CCDD ").append(" ");
//		}
		sb.append("WHERE u.CCDD = ? AND u.CCPP = ?").append(" ");
//		if (!"99".equals(cod_sede)) {
//			sb.append("AND so.CODIGO=?").append(" ");	
//		}
		sb.append("ORDER BY u.CCDD, u.CCPP, u.CCDI").append(" ");
		query.setStatement(sb.toString());
//		if (!"99".equals(cod_sede)) {
//			query.setWhereValues(new String[]{ccdd, ccpp, cod_sede});
//		} else {
			query.setWhereValues(new String[]{ccdd, ccpp});
//		}
//		Log.e(getClass().getSimpleName(), query.toString());
		return getBeans(query, Ubigeo.class, "UBIGEO", "CCDD", "DEPARTAMENTO", "CCPP", "PROVINCIA", "CCDI", "DISTRITO");
	}
	
//	public List<Ubigeo> getDistritos(Integer periodo, String cod_sede, String ccdd, String ccpp, Integer usuarioId) {
//		StringBuilder sb = new StringBuilder();
//		Query query = new Query();
//		sb.append("SELECT DISTINCT u.UBIGEO, u.CCDD, u.DEPARTAMENTO, u.CCPP, u.PROVINCIA, u.CCDI, u.DISTRITO, u.FOTO, u.FOTO2").append(" ")
//			.append("FROM ").append(TABLA).append(" u ")
////			.append("JOIN ").append(SegmentacionDAO.VISTA_SEGMENTACION).append(" Seg ON Seg.CCDD||Seg.CCPP||Seg.CCDI=u.ubigeo ");
//			.append("JOIN (").append(MarcoDAO.querySelect(null,null,null,null,null,usuarioId,periodo)).append(") Seg ON Seg.UBIGEO = u.UBIGEO").append(" ");
//		if (!"99".equals(cod_sede)) {
//			sb.append("JOIN ").append(SegmentacionDAO.TABLA_SEDES_OPERATIVA_UBIGEO).append(" so ON so.UBIGEO=u.ubigeo ");					
//		}
//		sb.append("WHERE u.CCDD = ? AND u.CCPP = ?").append(" ");
//		if (periodo != PERIODO_TODOS) {
//			sb.append("AND seg.PERIODO=").append(periodo).append(" ");	
//		}
//		if (!"99".equals(cod_sede)) {
//			sb.append("AND so.COD_SEDE=?").append(" ");	
//		}
//		if (usuarioId!=null) {
//			sb.append("AND seg.USUARIO_ID=").append(usuarioId).append(" ");	
//		}
//		sb.append("ORDER BY u.CCDD, u.CCPP, u.CCDI").append(" ");
//		query.setStatement(sb.toString());
//		if (!"99".equals(cod_sede)) {
//			query.setWhereValues(new String[]{ccdd, ccpp, cod_sede});
//		} else {
//			query.setWhereValues(new String[]{ccdd, ccpp});
//		}
////		Log.e(getClass().getSimpleName(), query.toString());
//		return getBeans(query, Ubigeo.class, "UBIGEO", "CCDD", "DEPARTAMENTO", "CCPP", "PROVINCIA", "CCDI", "DISTRITO","FOTO","FOTO2");
//	}
	
	public Ubigeo getDistrito(String ccdd, String ccpp, String ccdi) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT UBIGEO, CCDD, DEPARTAMENTO, CCPP, PROVINCIA, CCDI, DISTRITO,FOTO,FOTO2").append(" ")
			.append("FROM ").append(TABLA).append(" ")
			.append("WHERE CCDD=? AND CCPP=? AND CCDI=?").append(" ")
			.append("ORDER BY CCDD").append(" ");
		Query query = new Query(sb.toString(), ccdd, ccpp, ccdi);
		return (Ubigeo) getBean(query, Ubigeo.class, "UBIGEO", "CCDD", "DEPARTAMENTO", "CCPP", "PROVINCIA", "CCDI", "DISTRITO","FOTO","FOTO2");
	}
	
	public List<Usuario> getUser(String ccdd, String ccpp, String ccdi, String codccpp, String zonaid) {
//		Log.e("ccdd : ", ccdd.toString());
//		Log.e("ccpp : ", ccpp.toString());
//		Log.e("ccdi : ", ccdi.toString());
//		Log.e("codccpp : ", codccpp.toString());
//		Log.e("zonaid : ", zonaid.toString());
	    			
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct z.USUARIO_ID as ID,z.USUARIO   ").append(" ")
			.append("FROM V_ZONAS_CARTO_I z").append(" ")					
			.append("WHERE z.UBIGEO = ? AND z.CODCCPP = ? AND z.ZONA_ID = ? ").append(" ")
			.append("ORDER BY z.UBIGEO ").append(" ");
		Query query = new Query(sb.toString(), ccdd+ccpp+ccdi,codccpp,zonaid);
		//Log.e("mi query oki : ", query.toString());
		return getBeans(query, Usuario.class, "ID","USUARIO");
	}
	
}
