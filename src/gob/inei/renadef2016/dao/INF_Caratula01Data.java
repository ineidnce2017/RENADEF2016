package gob.inei.renadef2016.dao;

import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.dnce.components.Entity.SeccionCapitulo;
import gob.inei.dnce.dao.SQLiteDAO;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class INF_Caratula01Data extends SQLiteDAO{
	
	private static String TAG = "INF_Caratula01Data";
	
	public static final String TABLA_CARATULA = "T_DIG_01";
	public static final String VISTA_CARATULA_X_MARCO = "V_CARATULA_X_MARCO";
	
	Cursor cursor=null;
//	Globales ineiglobal = Globales.getInstance();	
	
	
	public static INF_Caratula01Data INSTANCE; 

	private INF_Caratula01Data(MyDatabaseHelper dbh) {
		super(dbh); 
	} 
	
	public static INF_Caratula01Data getInstance(MyDatabaseHelper dbh) {
		if (INSTANCE == null) {
			INSTANCE = new INF_Caratula01Data(dbh);
		}
		return INSTANCE;
	}
	
	public boolean existeComisariaEnDigitacion(String pIDN) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		boolean bres=false;
		
		try {                          
			cursor=db.rawQuery("SELECT ID_N FROM T_DIG_01 WHERE ID_N ='" + pIDN + "'",null);       
			bres= cursor.getCount()>0;
			
		} catch (Exception e) {
			Log.d(TAG,e.getMessage());
			
		} finally {
			cursor.close();
			db.close();
		}
		return bres;
	}
	
	public INF_Caratula01 getINFCaratula(String id_n, List<String> campos, boolean check) {
		boolean exist = existeComisariaEnDigitacion(id_n);
		SQLiteDatabase db =dbh.getReadableDatabase();
		INF_Caratula01 atCaratula = new INF_Caratula01();
//		List<String> data = new ArrayList<String>();
//		try {
//
//			Utilidades.FieldsEntity fe = Utilidades.getListFieldsEntity(atCaratula, campos, check);
//
//			String query = "";
//			if(!exist){
//				
//				query = "SELECT m.ID_N, m.COD_REGION AS REG, m.REGION AS NOMREG, "
//					+ "u.CCDD, u.CCPP, u.CCDI, "
//					+ "u.DEPARTAMENTO AS NOMBREDD, u.PROVINCIA AS NOMBREPP, u.DISTRITO AS NOMBREDI, "
//					+ "m.CCCP, m.NOMBRECP, m.AREA, m.ZONA, "
//					+ "m.ZONALF, m.MZA, m.MZNALF, m.FRENTE, m.AER_INI, m.AER_FIN, NULL AS I11_1, NULL AS I11_2, NULL AS I11_3, "
//					+ "NULL AS I11_4, NULL AS I11_5, NULL AS I11_6, NULL AS I11_7, NULL AS I11_8, NULL AS I11_9, NULL AS I11_10, "
//					+ "NULL AS RUTAN, NULL AS II1, NULL AS II2, NULL AS II3, NULL AS II4, NULL AS II5, NULL AS II6, NULL AS II7, "
//					+ "NULL AS II8, NULL AS II8_O, NULL AS II9, NULL AS II9_O, NULL AS IV1_D, NULL AS IV1_M, NULL AS IV1_A, "
//					+ "NULL AS IV2_D, NULL AS IV2_M, NULL AS IV2_A, NULL AS IV3_D, NULL AS IV3_M, NULL AS IV3_A, NULL AS IV1_1, "
//					+ "NULL AS IVRESFIN_01, NULL AS IVRESFIN_01_O, NULL AS IV2_1, NULL AS IVRESFIN_02, NULL AS IVRESFIN_02_O, "
//					+ "NULL AS IV3_1, NULL AS IVRESFIN_03, NULL AS IVRESFIN_03_O, NULL AS VI1A, NULL AS VI1B, NULL AS VI2A, NULL AS VI2B, "
//					+ "NULL AS VII1A, NULL AS VII1B, NULL AS VII1C, "
//					+ "NULL AS VII1D, NULL AS VII3_1D_NT, NULL AS VII1E, NULL AS VII3_1E_NT, NULL AS VII1F, NULL AS VII3_1F_NT, "
//					+ "NULL AS VII2A, NULL AS VII2B, NULL AS VII2C, NULL AS VII2D, NULL AS VII3_2D_NT, NULL AS VII2E, NULL AS VII3_2E_NT, "
//					+ "NULL AS VII2F, NULL AS VII3_2F_NT, "
//					+ "NULL AS VII2B, NULL AS OBS_01_CAR, NULL AS OBS_02_CAR, NULL AS OBS_CAR, NULL AS GPSLATITUD_INF, NULL AS GPSLONGITUD_INF, "
//					+ "NULL AS GPSLATITUD_AT, NULL AS GPSLONGITUD_AT, NULL AS GPSLONGITUD_DEL, NULL AS GPSLATITUD_DEL, NULL AS VHAB, NULL AS VPCS, "
//					+ "NULL AS VEFEC, NULL AS VAT_2013, NULL AS VLO_2013, NULL AS AT_INI, NULL AS VERSION_EXPORTACION, "
//					+ "NULL AS VERSION_IMPORTACION FROM T_Marco m " 
//					+ "INNER JOIN T_UBIGEO u ON m.UBIGEO=u.UBIGEO WHERE m.ID_N=?";
//				
//			}
//			else{
////				query = "SELECT * FROM T_DIG_01 WHERE ID_N=?";
//
//				query = "SELECT "+fe.fields+" FROM T_DIG_01 WHERE ID_N=?";
////				Cursor result = db.rawQuery("SELECT "+atCaratula.getFields(campos, null, 3)+" FROM T_DIG_01 WHERE ID_N='" + id_n, null);
//				
//			}
//			Log.e("mira", "mira: "+id_n);
//			cursor=db.rawQuery(query, new String[]{id_n});
////			cursor.moveToNext();
//			atCaratula.fillEntity(cursor, fe.lstfields);
//			
////        	atCaratula = new INF_Caratula01();
//        	/*atCaratula.id_n= cursor.getString(cursor.getColumnIndex("ID_N"));
//        	atCaratula.reg = cursor.getString(cursor.getColumnIndex("REG"));
//        	atCaratula.nomreg = cursor.getString(cursor.getColumnIndex("NOMREG"));
//        	atCaratula.ccdd = cursor.getString(cursor.getColumnIndex("CCDD"));
//        	atCaratula.nombredd = cursor.getString(cursor.getColumnIndex("NOMBREDD"));
//        	atCaratula.ccpp = cursor.getString(cursor.getColumnIndex("CCPP"));
//        	atCaratula.nombrepp = cursor.getString(cursor.getColumnIndex("NOMBREPP"));
//        	atCaratula.ccdi = cursor.getString(cursor.getColumnIndex("CCDI"));
//        	atCaratula.nombredi = cursor.getString(cursor.getColumnIndex("NOMBREDI"));
//        	atCaratula.cccp = cursor.getString(cursor.getColumnIndex("CCCP"));
//        	atCaratula.nombrecp = cursor.getString(cursor.getColumnIndex("NOMBRECP"));
//        	atCaratula.area = cursor.getInt(cursor.getColumnIndex("AREA"));
//        	atCaratula.zona = cursor.getString(cursor.getColumnIndex("ZONA"));
//        	atCaratula.zonalf = cursor.getString(cursor.getColumnIndex("ZONALF"));
//        	atCaratula.mza = cursor.getString(cursor.getColumnIndex("MZA"));
//        	atCaratula.mznalf = cursor.getString(cursor.getColumnIndex("MZNALF"));
//        	atCaratula.frente = cursor.getString(cursor.getColumnIndex("FRENTE"));
//        	atCaratula.aer_ini = cursor.getString(cursor.getColumnIndex("AER_INI"));
//        	atCaratula.aer_fin = cursor.getString(cursor.getColumnIndex("AER_FIN"));
//        	atCaratula.i11_1 = cursor.getString(cursor.getColumnIndex("I11_1"))==null?null:cursor.getInt(cursor.getColumnIndex("I11_1"));
//        	atCaratula.i11_2 = cursor.getString(cursor.getColumnIndex("I11_2"));
//        	atCaratula.i11_3 = cursor.getString(cursor.getColumnIndex("I11_3"));
//        	atCaratula.i11_4 = cursor.getString(cursor.getColumnIndex("I11_4"));
//        	atCaratula.i11_5 = cursor.getString(cursor.getColumnIndex("I11_5"));
//        	atCaratula.i11_6 = cursor.getString(cursor.getColumnIndex("I11_6"));
//        	atCaratula.i11_7 = cursor.getString(cursor.getColumnIndex("I11_7"));
//        	atCaratula.i11_8 = cursor.getString(cursor.getColumnIndex("I11_8"));
//        	atCaratula.i11_9 = cursor.getString(cursor.getColumnIndex("I11_9"))==null?null:cursor.getFloat(cursor.getColumnIndex("I11_9"));
//        	atCaratula.i11_10 = cursor.getString(cursor.getColumnIndex("I11_10"));
//        	atCaratula.rutan = cursor.getString(cursor.getColumnIndex("RUTAN"));
//        	atCaratula.ii1 = cursor.getString(cursor.getColumnIndex("II1"));
//        	atCaratula.ii2 = cursor.getString(cursor.getColumnIndex("II2"));
//        	atCaratula.ii3 = cursor.getString(cursor.getColumnIndex("II3"));
//        	atCaratula.ii4 = cursor.getString(cursor.getColumnIndex("II4"))==null?null:cursor.getInt(cursor.getColumnIndex("II4"));
//        	atCaratula.ii5 = cursor.getString(cursor.getColumnIndex("II5"))==null?null:cursor.getInt(cursor.getColumnIndex("II5"));
//        	atCaratula.ii6 = cursor.getString(cursor.getColumnIndex("II6"));
//        	atCaratula.ii7 = cursor.getString(cursor.getColumnIndex("II7"))==null?null:cursor.getInt(cursor.getColumnIndex("II7"));
//        	atCaratula.ii8 = cursor.getString(cursor.getColumnIndex("II8"))==null?null:cursor.getInt(cursor.getColumnIndex("II8"));
//        	atCaratula.ii8_o = cursor.getString(cursor.getColumnIndex("II8_O"));
//        	atCaratula.ii9 = cursor.getString(cursor.getColumnIndex("II9"))==null?null:cursor.getInt(cursor.getColumnIndex("II9"));
//        	atCaratula.ii9_o = cursor.getString(cursor.getColumnIndex("II9_O"));
//        	atCaratula.iv2_1_d = cursor.getString(cursor.getColumnIndex("IV2_1_D"));
//        	atCaratula.iv2_1_m = cursor.getString(cursor.getColumnIndex("IV2_1_M"));
//        	atCaratula.iv1_1 = cursor.getString(cursor.getColumnIndex("IV1_1"));
//        	atCaratula.ivresfin_01 = cursor.getString(cursor.getColumnIndex("IVRESFIN_01"))==null?null:cursor.getInt(cursor.getColumnIndex("IVRESFIN_01"));
//        	atCaratula.ivresfin_01_o = cursor.getString(cursor.getColumnIndex("IVRESFIN_01_O"));
//        	atCaratula.iv2_1 = cursor.getString(cursor.getColumnIndex("IV2_1"));
//        	atCaratula.ivresfin_02 = cursor.getString(cursor.getColumnIndex("IVRESFIN_02"))==null?null:cursor.getInt(cursor.getColumnIndex("IVRESFIN_02"));
//        	atCaratula.ivresfin_02_o = cursor.getString(cursor.getColumnIndex("IVRESFIN_02_O"));
//        	atCaratula.iv3_1 = cursor.getString(cursor.getColumnIndex("IV3_1"));
//        	atCaratula.ivresfin_03 = cursor.getString(cursor.getColumnIndex("IVRESFIN_03"))==null?null:cursor.getInt(cursor.getColumnIndex("IVRESFIN_03"));
//        	atCaratula.ivresfin_03_o = cursor.getString(cursor.getColumnIndex("IVRESFIN_03_O"));
//        	atCaratula.vi1a = cursor.getString(cursor.getColumnIndex("VI1A"));
//        	atCaratula.vi1b = cursor.getString(cursor.getColumnIndex("VI1B"));
//        	atCaratula.vi2a = cursor.getString(cursor.getColumnIndex("VI2A"));
//        	atCaratula.vi2b = cursor.getString(cursor.getColumnIndex("VI2B"));
//        	atCaratula.vi3a = cursor.getString(cursor.getColumnIndex("VI3A"));
//        	atCaratula.vi3b = cursor.getString(cursor.getColumnIndex("VI3B"));
//        	atCaratula.vi4a = cursor.getString(cursor.getColumnIndex("VI4A"));
//        	atCaratula.vi4b = cursor.getString(cursor.getColumnIndex("VI4B"));
//        	
//        	atCaratula.vii1a = cursor.getString(cursor.getColumnIndex("VII1A"));
//        	atCaratula.vii1b = cursor.getString(cursor.getColumnIndex("VII1B"))==null?null:cursor.getInt(cursor.getColumnIndex("VII1B"));
//        	atCaratula.vii1c = cursor.getString(cursor.getColumnIndex("VII1C"));
//        	atCaratula.vii1d = cursor.getString(cursor.getColumnIndex("VII1D"));
//        	atCaratula.vii3_1d_nt = cursor.getString(cursor.getColumnIndex("VII3_1D_NT"));
//        	atCaratula.vii1e = cursor.getString(cursor.getColumnIndex("VII1E"));
//        	atCaratula.vii3_1e_nt = cursor.getString(cursor.getColumnIndex("VII3_1E_NT"));
//        	atCaratula.vii1f = cursor.getString(cursor.getColumnIndex("VII1F"));
//        	atCaratula.vii3_1f_nt = cursor.getString(cursor.getColumnIndex("VII3_1F_NT"));
//        	atCaratula.vii2a = cursor.getString(cursor.getColumnIndex("VII2A"));
//        	atCaratula.vii2b = cursor.getString(cursor.getColumnIndex("VII2B"))==null?null:cursor.getInt(cursor.getColumnIndex("VII2B"));
//        	atCaratula.vii2c = cursor.getString(cursor.getColumnIndex("VII2C"));
//        	atCaratula.vii2d = cursor.getString(cursor.getColumnIndex("VII2D"));
//        	atCaratula.vii3_2d_nt = cursor.getString(cursor.getColumnIndex("VII3_2D_NT"));
//        	atCaratula.vii2e = cursor.getString(cursor.getColumnIndex("VII2E"));
//        	atCaratula.vii3_2e_nt = cursor.getString(cursor.getColumnIndex("VII3_2E_NT"));
//        	atCaratula.vii2f = cursor.getString(cursor.getColumnIndex("VII2F"));
//        	atCaratula.vii3_2f_nt = cursor.getString(cursor.getColumnIndex("VII3_2F_NT"));
//        	
//        	atCaratula.obs_01_car = cursor.getString(cursor.getColumnIndex("OBS_01_CAR"));
//        	atCaratula.obs_02_car = cursor.getString(cursor.getColumnIndex("OBS_02_CAR"));
//        	atCaratula.obs_car = cursor.getString(cursor.getColumnIndex("OBS_CAR"));
//        	atCaratula.gpslatitud_inf = cursor.getString(cursor.getColumnIndex("GPSLATITUD_INF"));
//        	atCaratula.gpslongitud_inf = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_INF"));
//        	atCaratula.gpslatitud_at = cursor.getString(cursor.getColumnIndex("GPSLATITUD_AT"));
//        	atCaratula.gpslongitud_at = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_AT"));
//        	atCaratula.gpslatitud_del = cursor.getString(cursor.getColumnIndex("GPSLATITUD_DEL"));
//        	atCaratula.gpslongitud_del = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_DEL"));
//        	atCaratula.vhab = cursor.getString(cursor.getColumnIndex("VHAB"))==null?null:cursor.getInt(cursor.getColumnIndex("VHAB"));
//        	atCaratula.vpcs = cursor.getString(cursor.getColumnIndex("VPCS"))==null?null:cursor.getInt(cursor.getColumnIndex("VPCS"));
//        	atCaratula.vefec = cursor.getString(cursor.getColumnIndex("VEFEC"))==null?null:cursor.getInt(cursor.getColumnIndex("VEFEC"));
//        	
//        	atCaratula.vat_2013 = cursor.getString(cursor.getColumnIndex("VAT_2013"))==null?null:cursor.getInt(cursor.getColumnIndex("VAT_2013"));
//        	atCaratula.vlo_2013 = cursor.getString(cursor.getColumnIndex("VLO_2013"))==null?null:cursor.getInt(cursor.getColumnIndex("VLO_2013"));
//        	atCaratula.at_ini = cursor.getString(cursor.getColumnIndex("AT_INI"))==null?null:cursor.getInt(cursor.getColumnIndex("AT_INI"));
//        	atCaratula.version_exportacion = cursor.getString(cursor.getColumnIndex("VERSION_EXPORTACION"));
//        	atCaratula.version_importacion = cursor.getString(cursor.getColumnIndex("VERSION_IMPORTACION"));
//        	atCaratula.estado_envio = cursor.getString(cursor.getColumnIndex("ESTADO_ENVIO"))==null?1:cursor.getInt(cursor.getColumnIndex("ESTADO_ENVIO"));
//	*/		
//		} catch (Exception e) {
//			Log.e(TAG,e.getMessage());
//			return null;
//		} finally {
//			cursor.close();
//			db.close();
//		}
		
		return atCaratula;
	}
	
	public INF_Caratula01 getCaratulaMarco(String pIDN, SeccionCapitulo... secciones) {
		INF_Caratula01 bean = (INF_Caratula01) getBean(
				VISTA_CARATULA_X_MARCO,
				"ID_N='"+pIDN+"'",
				new String[] {  },
				INF_Caratula01.class, secciones);
		return bean;
	}
	
//	public INF_Caratula01 getINFCaratula(String id_n) {
//		boolean exist = existeComisariaEnDigitacion(id_n);
//		SQLiteDatabase db =dbh.getReadableDatabase();
//		INF_Caratula01 atCaratula = null; //new INF_Caratula01();
//		try {
//			String query = "";
//			if(!exist){
//				
////				List<String> allFieldMatches = caratula.getFieldMatches(200, 208, "ID_N", "SEC_N", "A_OBS_100_200");
//				
//				query = "SELECT m.ID_N, m.COD_REGION AS REG, m.REGION AS NOMREG, "
//					+ "u.IDDPTO AS CCDD, u.IDPROV AS CCPP, u.IDDIST AS CCDI, "
//					+ "u.NOMBDEP AS NOMBREDD, u.NOMBPROV AS NOMBREPP, u.NOMBDIST AS NOMBREDI, "
//					+ "m.CCCP, m.NOMBRECP, m.AREA, m.ZONA, "
//					+ "m.ZONALF, m.MZA, m.MZNALF, m.FRENTE, m.AER_INI, m.AER_FIN, NULL AS I11_1, NULL AS I11_2, NULL AS I11_3, "
//					+ "NULL AS I11_4, NULL AS I11_5, NULL AS I11_6, NULL AS I11_7, NULL AS I11_8, NULL AS I11_9, NULL AS I11_10, "
//					+ "NULL AS RUTAN, NULL AS II1, NULL AS II2, NULL AS II3, NULL AS II4, NULL AS II5, NULL AS II6, NULL AS II7, "
//					+ "NULL AS II8, NULL AS II8_O, NULL AS II9, NULL AS II9_O, NULL AS IV2_1_D, NULL AS IV2_1_M, NULL AS IV1_1, "
//					+ "NULL AS IVRESFIN_01, NULL AS IVRESFIN_01_O, NULL AS IV2_1, NULL AS IVRESFIN_02, NULL AS IVRESFIN_02_O, "
//					+ "NULL AS IV3_1, NULL AS IVRESFIN_03, NULL AS IVRESFIN_03_O, NULL AS VI1A, NULL AS VI1B, NULL AS VI2A, NULL AS VI2B, "
//					+ "NULL AS VI3A, NULL AS VI3B, NULL AS VI4A, NULL AS VI4B, NULL AS VII1A, NULL AS VII1B, NULL AS VII1C, "
//					+ "NULL AS VII1D, NULL AS VII3_1D_NT, NULL AS VII1E, NULL AS VII3_1E_NT, NULL AS VII1F, NULL AS VII3_1F_NT, "
//					+ "NULL AS VII2A, NULL AS VII2B, NULL AS VII2C, NULL AS VII2D, NULL AS VII3_2D_NT, NULL AS VII2E, NULL AS VII3_2E_NT, "
//					+ "NULL AS VII2F, NULL AS VII3_2F_NT, "
//					+ "NULL AS VII2B, NULL AS OBS_01_CAR, NULL AS OBS_02_CAR, NULL AS OBS_CAR, NULL AS GPSLATITUD_INF, NULL AS GPSLONGITUD_INF, "
//					+ "NULL AS GPSLATITUD_AT, NULL AS GPSLONGITUD_AT, NULL AS GPSLONGITUD_DEL, NULL AS GPSLATITUD_DEL, NULL AS VHAB, NULL AS VPCS, "
//					+ "NULL AS VEFEC, NULL AS VAT_2013, NULL AS VLO_2013, NULL AS AT_INI, NULL AS VERSION_EXPORTACION, "
//					+ "NULL AS VERSION_IMPORTACION, NULL AS ESTADO_ENVIO FROM T_Marco m " 
//					+ "INNER JOIN T_UBIGEO u ON m.UBIGEO=u.IDDIST WHERE m.ID_N=?";
//			}
//			else{
//				query = "SELECT * FROM T_DIG_01 WHERE ID_N=?";
//			}
//			
//			cursor=db.rawQuery(query, new String[]{id_n});
//			cursor.moveToNext();
//			
//        	atCaratula = new INF_Caratula01();
//        	atCaratula.id_n= cursor.getString(cursor.getColumnIndex("ID_N"));
//        	atCaratula.reg = cursor.getString(cursor.getColumnIndex("REG"));
//        	atCaratula.nomreg = cursor.getString(cursor.getColumnIndex("NOMREG"));
//        	atCaratula.ccdd = cursor.getString(cursor.getColumnIndex("CCDD"));
//        	atCaratula.nombredd = cursor.getString(cursor.getColumnIndex("NOMBREDD"));
//        	atCaratula.ccpp = cursor.getString(cursor.getColumnIndex("CCPP"));
//        	atCaratula.nombrepp = cursor.getString(cursor.getColumnIndex("NOMBREPP"));
//        	atCaratula.ccdi = cursor.getString(cursor.getColumnIndex("CCDI"));
//        	atCaratula.nombredi = cursor.getString(cursor.getColumnIndex("NOMBREDI"));
//        	atCaratula.cccp = cursor.getString(cursor.getColumnIndex("CCCP"));
//        	atCaratula.nombrecp = cursor.getString(cursor.getColumnIndex("NOMBRECP"));
//        	atCaratula.area = cursor.getInt(cursor.getColumnIndex("AREA"));
//        	atCaratula.zona = cursor.getString(cursor.getColumnIndex("ZONA"));
//        	atCaratula.zonalf = cursor.getString(cursor.getColumnIndex("ZONALF"));
//        	atCaratula.mza = cursor.getString(cursor.getColumnIndex("MZA"));
//        	atCaratula.mznalf = cursor.getString(cursor.getColumnIndex("MZNALF"));
//        	atCaratula.frente = cursor.getString(cursor.getColumnIndex("FRENTE"));
//        	atCaratula.aer_ini = cursor.getString(cursor.getColumnIndex("AER_INI"));
//        	atCaratula.aer_fin = cursor.getString(cursor.getColumnIndex("AER_FIN"));
//        	atCaratula.i11_1 = cursor.getString(cursor.getColumnIndex("I11_1"))==null?null:cursor.getInt(cursor.getColumnIndex("I11_1"));
//        	atCaratula.i11_2 = cursor.getString(cursor.getColumnIndex("I11_2"));
//        	atCaratula.i11_3 = cursor.getString(cursor.getColumnIndex("I11_3"));
//        	atCaratula.i11_4 = cursor.getString(cursor.getColumnIndex("I11_4"));
//        	atCaratula.i11_5 = cursor.getString(cursor.getColumnIndex("I11_5"));
//        	atCaratula.i11_6 = cursor.getString(cursor.getColumnIndex("I11_6"));
//        	atCaratula.i11_7 = cursor.getString(cursor.getColumnIndex("I11_7"));
//        	atCaratula.i11_8 = cursor.getString(cursor.getColumnIndex("I11_8"));
//        	atCaratula.i11_9 = cursor.getString(cursor.getColumnIndex("I11_9"))==null?null:cursor.getFloat(cursor.getColumnIndex("I11_9"));
//        	atCaratula.i11_10 = cursor.getString(cursor.getColumnIndex("I11_10"));
//        	atCaratula.rutan = cursor.getString(cursor.getColumnIndex("RUTAN"));
//        	atCaratula.ii1 = cursor.getString(cursor.getColumnIndex("II1"));
//        	atCaratula.ii2 = cursor.getString(cursor.getColumnIndex("II2"));
//        	atCaratula.ii3 = cursor.getString(cursor.getColumnIndex("II3"));
//        	atCaratula.ii4 = cursor.getString(cursor.getColumnIndex("II4"))==null?null:cursor.getInt(cursor.getColumnIndex("II4"));
//        	atCaratula.ii5 = cursor.getString(cursor.getColumnIndex("II5"))==null?null:cursor.getInt(cursor.getColumnIndex("II5"));
//        	atCaratula.ii6 = cursor.getString(cursor.getColumnIndex("II6"));
//        	atCaratula.ii7 = cursor.getString(cursor.getColumnIndex("II7"))==null?null:cursor.getInt(cursor.getColumnIndex("II7"));
//        	atCaratula.ii8 = cursor.getString(cursor.getColumnIndex("II8"))==null?null:cursor.getInt(cursor.getColumnIndex("II8"));
//        	atCaratula.ii8_o = cursor.getString(cursor.getColumnIndex("II8_O"));
//        	atCaratula.ii9 = cursor.getString(cursor.getColumnIndex("II9"))==null?null:cursor.getInt(cursor.getColumnIndex("II9"));
//        	atCaratula.ii9_o = cursor.getString(cursor.getColumnIndex("II9_O"));
//        	atCaratula.iv2_1_d = cursor.getString(cursor.getColumnIndex("IV2_1_D"));
//        	atCaratula.iv2_1_m = cursor.getString(cursor.getColumnIndex("IV2_1_M"));
//        	atCaratula.iv1_1 = cursor.getString(cursor.getColumnIndex("IV1_1"));
//        	atCaratula.ivresfin_01 = cursor.getString(cursor.getColumnIndex("IVRESFIN_01"))==null?null:cursor.getInt(cursor.getColumnIndex("IVRESFIN_01"));
//        	atCaratula.ivresfin_01_o = cursor.getString(cursor.getColumnIndex("IVRESFIN_01_O"));
//        	atCaratula.iv2_1 = cursor.getString(cursor.getColumnIndex("IV2_1"));
//        	atCaratula.ivresfin_02 = cursor.getString(cursor.getColumnIndex("IVRESFIN_02"))==null?null:cursor.getInt(cursor.getColumnIndex("IVRESFIN_02"));
//        	atCaratula.ivresfin_02_o = cursor.getString(cursor.getColumnIndex("IVRESFIN_02_O"));
//        	atCaratula.iv3_1 = cursor.getString(cursor.getColumnIndex("IV3_1"));
//        	atCaratula.ivresfin_03 = cursor.getString(cursor.getColumnIndex("IVRESFIN_03"))==null?null:cursor.getInt(cursor.getColumnIndex("IVRESFIN_03"));
//        	atCaratula.ivresfin_03_o = cursor.getString(cursor.getColumnIndex("IVRESFIN_03_O"));
//        	atCaratula.vi1a = cursor.getString(cursor.getColumnIndex("VI1A"));
//        	atCaratula.vi1b = cursor.getString(cursor.getColumnIndex("VI1B"));
//        	atCaratula.vi2a = cursor.getString(cursor.getColumnIndex("VI2A"));
//        	atCaratula.vi2b = cursor.getString(cursor.getColumnIndex("VI2B"));
//        	atCaratula.vi3a = cursor.getString(cursor.getColumnIndex("VI3A"));
//        	atCaratula.vi3b = cursor.getString(cursor.getColumnIndex("VI3B"));
//        	atCaratula.vi4a = cursor.getString(cursor.getColumnIndex("VI4A"));
//        	atCaratula.vi4b = cursor.getString(cursor.getColumnIndex("VI4B"));
//        	
//        	atCaratula.vii1a = cursor.getString(cursor.getColumnIndex("VII1A"));
//        	atCaratula.vii1b = cursor.getString(cursor.getColumnIndex("VII1B"))==null?null:cursor.getInt(cursor.getColumnIndex("VII1B"));
//        	atCaratula.vii1c = cursor.getString(cursor.getColumnIndex("VII1C"));
//        	atCaratula.vii1d = cursor.getString(cursor.getColumnIndex("VII1D"));
//        	atCaratula.vii3_1d_nt = cursor.getString(cursor.getColumnIndex("VII3_1D_NT"));
//        	atCaratula.vii1e = cursor.getString(cursor.getColumnIndex("VII1E"));
//        	atCaratula.vii3_1e_nt = cursor.getString(cursor.getColumnIndex("VII3_1E_NT"));
//        	atCaratula.vii1f = cursor.getString(cursor.getColumnIndex("VII1F"));
//        	atCaratula.vii3_1f_nt = cursor.getString(cursor.getColumnIndex("VII3_1F_NT"));
//        	atCaratula.vii2a = cursor.getString(cursor.getColumnIndex("VII2A"));
//        	atCaratula.vii2b = cursor.getString(cursor.getColumnIndex("VII2B"))==null?null:cursor.getInt(cursor.getColumnIndex("VII2B"));
//        	atCaratula.vii2c = cursor.getString(cursor.getColumnIndex("VII2C"));
//        	atCaratula.vii2d = cursor.getString(cursor.getColumnIndex("VII2D"));
//        	atCaratula.vii3_2d_nt = cursor.getString(cursor.getColumnIndex("VII3_2D_NT"));
//        	atCaratula.vii2e = cursor.getString(cursor.getColumnIndex("VII2E"));
//        	atCaratula.vii3_2e_nt = cursor.getString(cursor.getColumnIndex("VII3_2E_NT"));
//        	atCaratula.vii2f = cursor.getString(cursor.getColumnIndex("VII2F"));
//        	atCaratula.vii3_2f_nt = cursor.getString(cursor.getColumnIndex("VII3_2F_NT"));
//        	
//        	atCaratula.obs_01_car = cursor.getString(cursor.getColumnIndex("OBS_01_CAR"));
//        	atCaratula.obs_02_car = cursor.getString(cursor.getColumnIndex("OBS_02_CAR"));
//        	atCaratula.obs_car = cursor.getString(cursor.getColumnIndex("OBS_CAR"));
//        	atCaratula.gpslatitud_inf = cursor.getString(cursor.getColumnIndex("GPSLATITUD_INF"));
//        	atCaratula.gpslongitud_inf = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_INF"));
//        	atCaratula.gpslatitud_at = cursor.getString(cursor.getColumnIndex("GPSLATITUD_AT"));
//        	atCaratula.gpslongitud_at = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_AT"));
//        	atCaratula.gpslatitud_del = cursor.getString(cursor.getColumnIndex("GPSLATITUD_DEL"));
//        	atCaratula.gpslongitud_del = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_DEL"));
//        	atCaratula.vhab = cursor.getString(cursor.getColumnIndex("VHAB"))==null?null:cursor.getInt(cursor.getColumnIndex("VHAB"));
//        	atCaratula.vpcs = cursor.getString(cursor.getColumnIndex("VPCS"))==null?null:cursor.getInt(cursor.getColumnIndex("VPCS"));
//        	atCaratula.vefec = cursor.getString(cursor.getColumnIndex("VEFEC"))==null?null:cursor.getInt(cursor.getColumnIndex("VEFEC"));
//        	
//        	atCaratula.vat_2013 = cursor.getString(cursor.getColumnIndex("VAT_2013"))==null?null:cursor.getInt(cursor.getColumnIndex("VAT_2013"));
//        	atCaratula.vlo_2013 = cursor.getString(cursor.getColumnIndex("VLO_2013"))==null?null:cursor.getInt(cursor.getColumnIndex("VLO_2013"));
//        	atCaratula.at_ini = cursor.getString(cursor.getColumnIndex("AT_INI"))==null?null:cursor.getInt(cursor.getColumnIndex("AT_INI"));
//        	atCaratula.version_exportacion = cursor.getString(cursor.getColumnIndex("VERSION_EXPORTACION"));
//        	atCaratula.version_importacion = cursor.getString(cursor.getColumnIndex("VERSION_IMPORTACION"));
//        	atCaratula.estado_envio = cursor.getString(cursor.getColumnIndex("ESTADO_ENVIO"))==null?1:cursor.getInt(cursor.getColumnIndex("ESTADO_ENVIO"));
//			
//		} catch (Exception e) {
//			Log.e(TAG,e.getMessage());
//			return null;
//		} finally {
//			cursor.close();
//			db.close();
//		}
//		
//		return atCaratula;
//	}
	
	public INF_Caratula01 getGPSDelitos(String id_n) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		INF_Caratula01 atCaratula = new INF_Caratula01();
		
		try {
			String query = "";
			query = "SELECT ID_N, GPSLATITUD_DEL, GPSLONGITUD_DEL FROM T_DIG_01 WHERE ID_N=?";

			cursor=db.rawQuery(query, new String[]{id_n});
			
	        if(cursor.moveToNext()){
	        	atCaratula.id_n= cursor.getString(cursor.getColumnIndex("ID_N"));
	        	atCaratula.gpslatitud_del = cursor.getString(cursor.getColumnIndex("GPSLATITUD_DEL"));
	        	atCaratula.gpslongitud_del = cursor.getString(cursor.getColumnIndex("GPSLONGITUD_DEL"));
	        }
			
		} catch (Exception e) {
			Log.d(TAG,e.getMessage());
			return null;
		} finally {
			cursor.close();
			db.close();
		}
		
		return atCaratula;
	}
	
//	public CaratulaDelitos getCaratulaDel(String id_n) {
//		SQLiteDatabase db =dbh.getReadableDatabase();
//		CaratulaDelitos atCaratula = new CaratulaDelitos();
//		
//		try {
//			String query = "";
//			query = "SELECT * FROM CARATULA WHERE ID_N=?";
//
//			cursor=db.rawQuery(query, new String[]{id_n});
//			
//	        if(cursor.moveToNext()){
//	        	atCaratula.id_n= cursor.getString(cursor.getColumnIndex("ID_N"));
//	        	atCaratula.cerrado = cursor.getInt(cursor.getColumnIndex("CERRADO"));
//	        }
//			
//		} catch (Exception e) {
//			Log.d(TAG,e.getMessage());
//			return null;
//		} finally {
//			cursor.close();
//			db.close();
//		}
//		
//		return atCaratula;
//	}
//	
	public boolean grabarCaratula(INF_Caratula01 atcaratula, int pp, List<String> fields) {
		boolean bres = false;
//		String accion = null;
//		
//		if (existeComisariaEnDigitacion(atcaratula.id_n))
//			accion="update";
//		else 
//			accion="insert";
//		
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		ContentValues cv=new ContentValues();
//    	cv.put("ID_N",atcaratula.id_n);    		
//
//    	cv = atcaratula.getContentValues(cv, Utilidades.getListFieldsEntity(atcaratula, fields, true).lstfields);
//    	
//		try{
//			if(accion.equalsIgnoreCase("insert"))
//				db.insert("T_DIG_01", null, cv);
//			else if(accion.equalsIgnoreCase("update")){
//				db.update("T_DIG_01", cv, "ID_N='" + atcaratula.id_n + "'", null);
//			}
//			cv.clear();
//			
//			bres = true;
//			
//		} 
//		catch (Exception e) {
//			Log.d(TAG, e.getMessage());		
//		} 
//		finally {
//			db.close();
//		}
		
		return bres;
	}
	
	public boolean saveOrUpdate(INF_Caratula01 bean, SeccionCapitulo... secciones)
			throws java.sql.SQLException {
		return this
				.saveOrUpdate(
						TABLA_CARATULA,
						"ID_N='"+bean.id_n+"'",
						bean, new String[] {  },
						secciones);
	}
	
//	public boolean grabarCaratula(INF_Caratula01 atcaratula, List<INF_Visita01> listVisitas) {
//		boolean bres = false;
//		String accion = null;
//		
//		if (existeComisariaEnDigitacion(atcaratula.id_n))
//			accion="update";
//		else 
//			accion="insert";
//		
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		ContentValues cvcaratula=new ContentValues();
//    	cvcaratula.put("ID_N",atcaratula.id_n);    		
//    	  
//    	cvcaratula.put("REG",atcaratula.reg);  
//    	cvcaratula.put("NOMREG",atcaratula.nomreg);
//    	cvcaratula.put("CCDD",atcaratula.ccdd);
//    	cvcaratula.put("NOMBREDD",atcaratula.nombredd);
//    	cvcaratula.put("CCPP",atcaratula.ccpp);
//    	cvcaratula.put("NOMBREPP",atcaratula.nombrepp);
//    	cvcaratula.put("CCDI",atcaratula.ccdi);
//    	cvcaratula.put("NOMBREDI",atcaratula.nombredi);
//    	cvcaratula.put("CCCP",atcaratula.cccp);
//    	cvcaratula.put("NOMBRECP",atcaratula.nombrecp);
//    	cvcaratula.put("AREA",atcaratula.area);
//    	cvcaratula.put("ZONA",atcaratula.zona);
//    	cvcaratula.put("ZONALF",atcaratula.zonalf);
//    	cvcaratula.put("MZA",atcaratula.mza);
//    	cvcaratula.put("MZNALF",atcaratula.mznalf);
//    	cvcaratula.put("FRENTE",atcaratula.frente);
//    	cvcaratula.put("AER_INI",atcaratula.aer_ini);
//    	cvcaratula.put("AER_FIN",atcaratula.aer_fin);
//    	cvcaratula.put("I11_1",atcaratula.i11_1);
//    	cvcaratula.put("I11_2",atcaratula.i11_2);
//    	cvcaratula.put("I11_3",atcaratula.i11_3);
//    	cvcaratula.put("I11_4",atcaratula.i11_4);
//    	cvcaratula.put("I11_5",atcaratula.i11_5);
//    	cvcaratula.put("I11_6",atcaratula.i11_6);
//    	cvcaratula.put("I11_7",atcaratula.i11_7);
//    	cvcaratula.put("I11_8",atcaratula.i11_8);
//    	cvcaratula.put("I11_9",atcaratula.i11_9);
//    	cvcaratula.put("I11_10",atcaratula.i11_10);
//    	cvcaratula.put("RUTAN",atcaratula.rutan);
//    	cvcaratula.put("II1",atcaratula.ii1);
//    	cvcaratula.put("II2",atcaratula.ii2);
//    	cvcaratula.put("II3",atcaratula.ii3);
//    	cvcaratula.put("II4",atcaratula.ii4);
//    	cvcaratula.put("II5",atcaratula.ii5);
//    	cvcaratula.put("II6",atcaratula.ii6);
//    	cvcaratula.put("II7",atcaratula.ii7);
//    	cvcaratula.put("II8",atcaratula.ii8);
//    	cvcaratula.put("II8_O",atcaratula.ii8_o);
//    	cvcaratula.put("II9",atcaratula.ii9);
//    	cvcaratula.put("II9_O",atcaratula.ii9_o);
//    	
//    	cvcaratula.put("IV1_1",atcaratula.iv1_1);
//    	cvcaratula.put("IVRESFIN_01",atcaratula.ivresfin_01);
//    	cvcaratula.put("IVRESFIN_01_O",atcaratula.ivresfin_01_o);
//    	cvcaratula.put("IV2_1",atcaratula.iv2_1);
//    	cvcaratula.put("IVRESFIN_02",atcaratula.ivresfin_02);
//    	cvcaratula.put("IVRESFIN_02_O",atcaratula.ivresfin_02_o);
//    	cvcaratula.put("IV3_1",atcaratula.iv3_1);
//    	cvcaratula.put("IVRESFIN_03",atcaratula.ivresfin_03);
//    	cvcaratula.put("IVRESFIN_03_O",atcaratula.ivresfin_03_o);
//    	cvcaratula.put("VHAB",atcaratula.vhab);
//    	cvcaratula.put("VPCS",atcaratula.vpcs);
//    	cvcaratula.put("VEFEC",atcaratula.vefec);
//    	cvcaratula.put("VI1A",atcaratula.vi1a);
//    	cvcaratula.put("VI1B",atcaratula.vi1b);
//    	cvcaratula.put("VI2A",atcaratula.vi2a);
//    	cvcaratula.put("VI2B",atcaratula.vi2b);
//    	cvcaratula.put("VI3A",atcaratula.vi3a);
//    	cvcaratula.put("VI3B",atcaratula.vi3b);
//    	cvcaratula.put("VI4A",atcaratula.vi4a);
//    	cvcaratula.put("VI4B",atcaratula.vi4b);
//    	
//    	cvcaratula.put("VII1A",atcaratula.vii1a);
//    	cvcaratula.put("VII1B",atcaratula.vii1b);
//    	cvcaratula.put("VII1C",atcaratula.vii1c);
//    	cvcaratula.put("VII1D",atcaratula.vii1d);
//    	cvcaratula.put("VII3_1D_NT",atcaratula.vii3_1d_nt);
//    	cvcaratula.put("VII1E",atcaratula.vii1e);
//    	cvcaratula.put("VII3_1E_NT",atcaratula.vii3_1e_nt);
//    	cvcaratula.put("VII1F",atcaratula.vii1f);
//    	cvcaratula.put("VII3_1F_NT",atcaratula.vii3_1f_nt);
//    	cvcaratula.put("VII2A",atcaratula.vii2a);
//    	cvcaratula.put("VII2B",atcaratula.vii2b);
//    	cvcaratula.put("VII2C",atcaratula.vii2c);
//    	cvcaratula.put("VII2D",atcaratula.vii2d);
//    	cvcaratula.put("VII3_2D_NT",atcaratula.vii3_2d_nt);
//    	cvcaratula.put("VII2E",atcaratula.vii2e);
//    	cvcaratula.put("VII3_2E_NT",atcaratula.vii3_2e_nt);
//    	cvcaratula.put("VII2F",atcaratula.vii2f);
//    	cvcaratula.put("VII3_2F_NT",atcaratula.vii3_2f_nt);
//    	
//    	cvcaratula.put("OBS_CAR",atcaratula.obs_car);
//    	cvcaratula.put("GPSLONGITUD_INF",atcaratula.gpslongitud_inf);
//		cvcaratula.put("GPSLATITUD_INF",atcaratula.gpslatitud_inf);
//		cvcaratula.put("GPSLONGITUD_AT",atcaratula.gpslongitud_at);
//		cvcaratula.put("GPSLATITUD_AT",atcaratula.gpslatitud_at);
//		cvcaratula.put("GPSLONGITUD_DEL",atcaratula.gpslongitud_del);
//		cvcaratula.put("GPSLATITUD_DEL",atcaratula.gpslatitud_del);
//		cvcaratula.put("VAT_2013", atcaratula.vat_2013);
//		cvcaratula.put("VLO_2013", atcaratula.vlo_2013);
//		cvcaratula.put("AT_INI", atcaratula.at_ini);
//		cvcaratula.put("VERSION_IMPORTACION", atcaratula.version_importacion);
//		cvcaratula.put("VERSION_EXPORTACION", atcaratula.version_exportacion);
//		cvcaratula.put("ESTADO_ENVIO", atcaratula.estado_envio);
//		
//		try{
//			if(accion.equalsIgnoreCase("insert"))
//				db.insert("T_DIG_01", null, cvcaratula);
//			else if(accion.equalsIgnoreCase("update")){
//				db.update("T_DIG_01", cvcaratula, "ID_N='" + atcaratula.id_n + "'", null);
//			}
//			cvcaratula.clear();
//			
//			bres = true;
//			
//		} 
//		catch (Exception e) {
//			Log.d(TAG, e.getMessage());		
//		} 
//		finally {
//			db.close();
//		}
//		
//		return bres;
//	}
	
	public boolean existeGPS(String latitud, String longitud) {
		SQLiteDatabase db = this.dbh.getReadableDatabase();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT ID_N")
				.append(" ").append("FROM ").append(MarcoDAO.TABLA_CARATULA)
				.append(" ").append("WHERE GPSLATITUD_DEL=? AND GPSLONGITUD_DEL=?")
				.append(" ");
		cursor = db.rawQuery(queryBuilder.toString(), new String[] { latitud, longitud });
		if (cursor.getCount() == 0) {
			cursor = null;
			db.close();
			return false;
		} else {
			cursor = null;
			db.close();
			return true;
		}
	}
	
	public boolean grabarEstadoEnvio(INF_Caratula01 atcaratula, int cuestionario) {
		boolean bres = false;
//		String accion = null;
//		
//		if(atcaratula == null) return false;
//		if (existeComisariaEnDigitacion(atcaratula.id_n))
//			accion="update";
//		else 
//			return false;
//		
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		ContentValues cvcaratula=new ContentValues();
//		
//    	cvcaratula.put("ID_N",atcaratula.id_n);    		
//		cvcaratula.put("ESTADO_ENVIO", atcaratula.estado_envio);
//		
//		if(cuestionario == Globales.getInstance().INFRAESTRUCTURA){
//			cvcaratula.put("OBS_01_CAR", atcaratula.obs_01_car);
//		} else if(cuestionario == Globales.getInstance().ACCIDENTES_DE_TRANSITO){
//			cvcaratula.put("OBS_02_CAR", atcaratula.obs_02_car);
//		}
//		
//		try{
//			db.update("T_DIG_01", cvcaratula, "ID_N='" + atcaratula.id_n + "'", null);
//			bres = true;
//		} 
//		catch (Exception e) {
//			Log.d(TAG, e.getMessage());		
//		} 
//		finally {
//			db.close();
//		}
//		
		return bres;
	}
	
	public boolean grabarVersionImpExp(String id_n, String version) {
		boolean bres = false;
		
		if(id_n == null) return false;
		if (!existeComisariaEnDigitacion(id_n))	return false;
		
		SQLiteDatabase db = dbh.getWritableDatabase();
		ContentValues cvcaratula=new ContentValues();

    	cvcaratula.put("ID_N", id_n);    		
		cvcaratula.put("VERSION_IMPORTACION", version);
		cvcaratula.put("VERSION_EXPORTACION", version);

		try{
			db.update("T_DIG_01", cvcaratula, "ID_N='" + id_n + "'", null);
			bres = true;
		} 
		catch (Exception e) {
			Log.d(TAG, e.getMessage());		
		} 
		finally {
			db.close();
		}
		
		return bres;
	}
	
	public boolean grabarGPSDelitos(INF_Caratula01 atcaratula) {
		boolean bres = false;
		
		if(atcaratula == null) return false;
		if(atcaratula.id_n == null) return false;
		if (!existeComisariaEnDigitacion(atcaratula.id_n))	return false;
		
		SQLiteDatabase db = dbh.getWritableDatabase();
		ContentValues cvcaratula=new ContentValues();

    	cvcaratula.put("ID_N", atcaratula.id_n);    		
		cvcaratula.put("GPSLATITUD_DEL", atcaratula.gpslatitud_del);
		cvcaratula.put("GPSLONGITUD_DEL", atcaratula.gpslongitud_del);

		try{
			db.update("T_DIG_01", cvcaratula, "ID_N='" + atcaratula.id_n + "'", null);
			bres = true;
		} 
		catch (Exception e) {
			Log.d(TAG, e.getMessage());		
		} 
		finally {
			db.close();
		}
		
		return bres;
	}
	
	public boolean grabarCaratulaExterno(INF_Caratula01 infcaratula) {
		boolean result = true;
		String accion = null;
		
		if (existeComisariaEnDigitacion(infcaratula.id_n))
			accion="update";
		else 
			accion="insert";
		
		SQLiteDatabase db = dbh.getReadableDatabase();
		
		ContentValues cvcaratula=new ContentValues();
		
    	if(accion.equalsIgnoreCase("insert")){
    		cvcaratula.put("ID_N", infcaratula.id_n);  		
    	}
    	
    	cvcaratula.put("REG", infcaratula.reg);  
    	cvcaratula.put("NOMREG", infcaratula.nomreg);
    	cvcaratula.put("CCDD",infcaratula.ccdd);
    	cvcaratula.put("NOMBREDD",infcaratula.nombredd);
    	cvcaratula.put("CCPP",infcaratula.ccpp);
    	cvcaratula.put("NOMBREPP",infcaratula.nombrepp);
    	cvcaratula.put("CCDI",infcaratula.ccdi);
    	cvcaratula.put("NOMBREDI",infcaratula.nombredi);
    	cvcaratula.put("CCCP",infcaratula.cccp);
    	cvcaratula.put("AREA",infcaratula.area);
    	cvcaratula.put("ZONA",infcaratula.zona);
    	cvcaratula.put("ZONALF",infcaratula.zonalf);
    	cvcaratula.put("MZA",infcaratula.mza);
    	cvcaratula.put("MZNALF",infcaratula.mznalf);
//    	cvcaratula.put("FRENTE",infcaratula.frente);
//    	cvcaratula.put("AER_INI",infcaratula.aer_ini);
//    	cvcaratula.put("AER_FIN",infcaratula.aer_fin);
    	cvcaratula.put("I11_1",infcaratula.i11_1);
    	cvcaratula.put("I11_2",infcaratula.i11_2);
    	cvcaratula.put("I11_3",infcaratula.i11_3);
    	cvcaratula.put("I11_4",infcaratula.i11_4);
    	cvcaratula.put("I11_5",infcaratula.i11_5);
    	cvcaratula.put("I11_6",infcaratula.i11_6);
    	cvcaratula.put("I11_7",infcaratula.i11_7);
    	cvcaratula.put("I11_8",infcaratula.i11_8);
//    	cvcaratula.put("I11_9",infcaratula.i11_9);
    	cvcaratula.put("I11_10",infcaratula.i11_10);
    	cvcaratula.put("RUTAN", infcaratula.rutan);
    	cvcaratula.put("II1",infcaratula.ii1);
    	cvcaratula.put("II2",infcaratula.ii2);
    	cvcaratula.put("II3",infcaratula.ii3);
    	cvcaratula.put("II4",infcaratula.ii4);
    	cvcaratula.put("II5",infcaratula.ii5);
    	if(infcaratula.ii6 != null) cvcaratula.put("II6",infcaratula.ii6);
    	if(infcaratula.ii7 != null) cvcaratula.put("II7",infcaratula.ii7);
    	cvcaratula.put("VI1A",infcaratula.vi1a);
    	cvcaratula.put("VI1B",infcaratula.vi1b);
//    	cvcaratula.put("VI2A",infcaratula.vi2a);
//    	cvcaratula.put("VI2B",infcaratula.vi2b);
//    	cvcaratula.put("VI3A",infcaratula.vi3a);
//    	cvcaratula.put("VI3B",infcaratula.vi3b);
//    	cvcaratula.put("VI4A",infcaratula.vi4a);
//    	cvcaratula.put("VI4B",infcaratula.vi4b);

    	if(accion.equalsIgnoreCase("insert")){
			result = db.insert("T_DIG_01", null, cvcaratula)!=-1;
			cvcaratula.clear();
		}
		else if(accion.equalsIgnoreCase("update")){
			result = db.update("T_DIG_01", cvcaratula, "ID_N='" + infcaratula.id_n + "'", null)>0;
			cvcaratula.clear();
		}
		
		db.close();
		db = null;
		return result;
	}
	
	public boolean existGPSPoint(double latitud, double longitud){
		SQLiteDatabase db =dbh.getReadableDatabase();
		boolean bres=false;
		
		try {                          
			cursor=db.rawQuery("SELECT ID_N FROM T_DIG_01 WHERE GPSLATITUD=? AND GPSLONGITUD=?", new String[]{Double.toString(latitud), Double.toString(longitud)});       
			bres= cursor.getCount()>0;
			
		} catch (Exception e) {
			Log.d(TAG,e.getMessage());
		} finally {
			cursor.close();
			db.close();
		}
		return bres;
	}

}
