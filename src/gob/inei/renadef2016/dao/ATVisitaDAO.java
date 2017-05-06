package gob.inei.renadef2016.dao;

import gob.inei.renadef2016.common.App.MAINTENCE;
import gob.inei.renadef2016.modelo.DelVisita;
import gob.inei.renadef2016.modelo.INF_Caratula01;
import gob.inei.dnce.components.Entity.SeccionCapitulo;
import gob.inei.dnce.dao.DatabaseHelper;
import gob.inei.dnce.dao.SQLiteDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.SparseArray;

public class ATVisitaDAO extends SQLiteDAO {
	
	public ATVisitaDAO(DatabaseHelper dbh) {
		super(dbh);
		// TODO Auto-generated constructor stub
	}

	private String TAG = "ATVisitaData";
	/*Cursor cursor=null;*/
	
	public static ATVisitaDAO INSTANCE; 
	public static final String TABLA_VISITA = "T_DIG_03_VISITA";

	private ATVisitaDAO(MyDatabaseHelper dbh) {
		super(dbh);
	}
	
	public static ATVisitaDAO getInstance(MyDatabaseHelper dbh) {
		if (INSTANCE == null) {
			INSTANCE = new ATVisitaDAO(dbh);
		}
		return INSTANCE;
	}
	
	public Boolean existeATVisita(String id_n, Integer nrovis) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		Cursor cursor=null;
		boolean bres=false;

		try {
			cursor=db.rawQuery("SELECT NROVIS FROM T_DIG_02_VISITA WHERE ID_N=? AND NROVIS=?", new String[]{id_n, nrovis.toString()});       
			bres= cursor.getCount()>0;
		} catch (Exception e) {
			Log.d("Cynthia",e.getMessage());
		} finally {
			cursor.close();
			db.close();
		}
		return bres;
	}
	
	public Boolean existeDelVisita(String id_n, Integer nrovis) {
		SQLiteDatabase db =dbh.getReadableDatabase();
		Cursor cursor=null;
		boolean bres=false;

		try {
			cursor=db.rawQuery("SELECT III_1 FROM T_DIG_03_VISITA WHERE ID_N=? AND III_1=?", new String[]{id_n, nrovis.toString()});       
			bres= cursor.getCount()>0;
		} catch (Exception e) {
			Log.d("Cynthia",e.getMessage());
		} finally {
			cursor.close();
			db.close();
		}
		return bres;
	}
	
//	public List<ATVisita> getATVisitas(String id_n, List<String> fields) {
//		SQLiteDatabase db =dbh.getReadableDatabase();
//		List<ATVisita> lstATVisitas = new ArrayList<ATVisita>();
//		ATVisita data = new ATVisita();
//		Cursor cursor=null;
//		
//		Utilidades.FieldsEntity fe = Utilidades.getListFieldsEntity(data, fields, false);
//		
//		try {
//			cursor=db.rawQuery("SELECT "+ fe.fields +" FROM T_DIG_02_VISITA WHERE ID_N='"+id_n+"' ORDER BY NROVIS ASC", null);
//			
//			if(cursor.getCount() == 0) {
////				lstATVisitas.add(data);
//				return lstATVisitas;
//			}
//			while(cursor.moveToNext()){
//				ATVisita datarow = new ATVisita();
//				datarow = (ATVisita)datarow.fillEntity(cursor, fe.lstfields);
//				lstATVisitas.add(datarow);
//			}
//			
//
//		} catch (Exception e) {
//			Log.d("Cynthia",e.getMessage());
//		} finally { 
//			cursor.close();
//			db.close();
//		}
//		
//		return lstATVisitas;
//	}
	

//	public List<ATVisita> getATVisitas(String id_n) {
//		SQLiteDatabase db =dbh.getReadableDatabase();
//		List<ATVisita> lstATVisitas = new ArrayList<ATVisita>();
//		Cursor cursor=null;
//		
//		try {
//			cursor=db.rawQuery("SELECT * FROM T_DIG_02_VISITA WHERE ID_N='"+id_n+"' ORDER BY NROVIS ASC", null);
//			
//			ATVisita visita;
//	        while(cursor.moveToNext()){
//	        	visita = new ATVisita();
//	        	/*visita.id_n= cursor.getString(cursor.getColumnIndex("ID_N"));
//	        	visita.iii2_1 = cursor.getInt(cursor.getColumnIndex("III2_1"));
//	        	visita.iii2_2_d = cursor.getString(cursor.getColumnIndex("III2_2_D"));
//	        	visita.iii2_2_m = cursor.getString(cursor.getColumnIndex("III2_2_M"));
//	        	visita.iii2_3a_ih = cursor.getString(cursor.getColumnIndex("III2_3A_IH"));
//	        	visita.iii2_3a_im = cursor.getString(cursor.getColumnIndex("III2_3A_IM"));
//	        	visita.iii2_3b_fh = cursor.getString(cursor.getColumnIndex("III2_3B_FH"));
//	        	visita.iii2_3b_fm = cursor.getString(cursor.getColumnIndex("III2_3B_FM"));
//	        	visita.iii2_4a_d = cursor.getString(cursor.getColumnIndex("III2_4A_D"));
//	        	visita.iii2_4a_m = cursor.getString(cursor.getColumnIndex("III2_4A_M"));
//	        	visita.iii2_4b_h = cursor.getString(cursor.getColumnIndex("III2_4B_H"));
//	        	visita.iii2_4b_m = cursor.getString(cursor.getColumnIndex("III2_4B_M"));
//	        	visita.iii2_5 = cursor.getInt(cursor.getColumnIndex("III2_5"));
//	        	visita.iii2_6_d = cursor.getString(cursor.getColumnIndex("III2_6_D"));
//	        	visita.iii2_6_m = cursor.getString(cursor.getColumnIndex("III2_6_M"));
//	        	visita.iii2_7a_h = cursor.getString(cursor.getColumnIndex("III2_7A_H"));
//	        	visita.iii2_7a_m = cursor.getString(cursor.getColumnIndex("III2_7A_M"));
//	        	visita.iii2_7b_h = cursor.getString(cursor.getColumnIndex("III2_7B_H"));
//	        	visita.iii2_7b_m = cursor.getString(cursor.getColumnIndex("III2_7B_M"));
//	        	visita.iii2_8 = cursor.getInt(cursor.getColumnIndex("III2_8"));*/
//	        	visita.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//	        	visita.nrovis = cursor.getString(cursor.getColumnIndex("NROVIS"))==null?null:cursor.getInt(cursor.getColumnIndex("NROVIS"));
//	        	visita.efecha = cursor.getString(cursor.getColumnIndex("EFECHA"));
//	        	visita.ehorade = cursor.getString(cursor.getColumnIndex("EHORADE"));
//	        	visita.ehoraa = cursor.getString(cursor.getColumnIndex("EHORAA"));
//	        	visita.epvfecha = cursor.getString(cursor.getColumnIndex("EPVFECHA"));
//	        	visita.epvhora = cursor.getString(cursor.getColumnIndex("EPVHORA"));
//	        	visita.eresvis = cursor.getString(cursor.getColumnIndex("ERESVIS"))==null?null:cursor.getInt(cursor.getColumnIndex("ERESVIS"));
//	        	visita.eresvis_o = cursor.getString(cursor.getColumnIndex("ERESVIS_O"));
//	        	visita.sfecha = cursor.getString(cursor.getColumnIndex("SFECHA"));
//	        	visita.shorade = cursor.getString(cursor.getColumnIndex("SHORADE"));
//	        	visita.shoraa = cursor.getString(cursor.getColumnIndex("SHORAA"));
//	        	visita.sresvis = cursor.getString(cursor.getColumnIndex("SRESVIS"))==null?null:cursor.getInt(cursor.getColumnIndex("SRESVIS"));
//	        	visita.sresvis_o = cursor.getString(cursor.getColumnIndex("SRESVIS_O"));
//	        	visita.gpslatitud = cursor.getString(cursor.getColumnIndex("GPSLATITUD"));
//	        	visita.gpslongitud = cursor.getString(cursor.getColumnIndex("GPSLONGITUD"));
//	        	visita.usucre = cursor.getString(cursor.getColumnIndex("USUCRE"));
//	        	visita.feccre = cursor.getString(cursor.getColumnIndex("FECCRE"));
//	        	visita.usureg = cursor.getString(cursor.getColumnIndex("USUREG"));
//	        	visita.fecreg = cursor.getString(cursor.getColumnIndex("FECREG"));
//	        	visita.fecenv = cursor.getString(cursor.getColumnIndex("FECENV"));
//	        	
//	        	lstATVisitas.add(visita);
//	        }
//		} catch (Exception e) {
//			Log.d("Cynthia",e.getMessage());
//		} finally {
//			cursor.close();
//			db.close();
//		}
//		
//		return lstATVisitas;
//	}
	
//	public boolean guardarVisita(ATVisita visita, List<String> fields) {
//		boolean success = false;
//		boolean exist = existeATVisita(visita.id_n, visita.nrovis);
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			cv.put("ID_N", visita.id_n);
//
//	    	cv = visita.getContentValues(cv, Utilidades.getListFieldsEntity(visita, fields, true).lstfields);
//
//			if(exist) {
////				cv.put("USUREG", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
//				success = db.update("T_DIG_02_VISITA", cv, "ID_N=? AND NROVIS=?", new String[]{visita.id_n, visita.nrovis.toString()})>0;
//			}
//			else {
////				cv.put("FECCRE", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
////				cv.put("USUCRE", Utilidades.getInt(Globales.currentUser.id,-1));
////				cv.put("USUREG", Utilidades.getInt(Globales.currentUser.id,-1));
//				long as = db.insert("T_DIG_02_VISITA", null, cv);
//				success =  as != -1;
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
//		return success;
//	}
	
//	public boolean guardarVisita(ATVisita visita) {
//		boolean success = false;
//		boolean exist = existeATVisita(visita.id_n, visita.nrovis);
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			/*cv.put("ID_N", visita.id_n);
//			cv.put("III2_1", visita.iii2_1);
//			cv.put("III2_2_D", visita.iii2_2_d);
//			cv.put("III2_2_M", visita.iii2_2_m);
//			cv.put("III2_3A_IH", visita.iii2_3a_ih);
//			cv.put("III2_3A_IM", visita.iii2_3a_im);
//			cv.put("III2_3B_FH", visita.iii2_3b_fh);
//			cv.put("III2_3B_FM", visita.iii2_3b_fm);
//			cv.put("III2_4A_D", visita.iii2_4a_d);
//			cv.put("III2_4A_M", visita.iii2_4a_m);
//			cv.put("III2_4B_H", visita.iii2_4b_h);
//			cv.put("III2_4B_M", visita.iii2_4b_m);
//			cv.put("III2_5", visita.iii2_5);
//			cv.put("III2_6_D", visita.iii2_6_d);
//			cv.put("III2_6_M", visita.iii2_6_m);
//			cv.put("III2_7A_H", visita.iii2_7a_h);
//			cv.put("III2_7A_M", visita.iii2_7a_m);
//			cv.put("III2_7B_H", visita.iii2_7b_h);
//			cv.put("III2_7B_M", visita.iii2_7b_m);
//			cv.put("III2_8", visita.iii2_8);
//			
//			Cursor cur = db.rawQuery("SELECT ID_N FROM T_DIG_02_VISITA WHERE ID_N=? AND III2_1=?", new String[]{visita.id_n, visita.iii2_1.toString()});
//			boolean exist = cur.getCount()>0;
//			
//			if(exist) {
//				success = db.update("T_DIG_02_VISITA", cv, "ID_N=? AND III2_1=?", new String[]{visita.id_n, visita.iii2_1.toString()})>0;
//			}
//			else {
//				success = db.insert("T_DIG_02_VISITA", null, cv) != -1;
//			}*/
//			
//			cv.put("ID_N", visita.id_n);
//			cv.put("NROVIS", visita.nrovis);
//			cv.put("EFECHA", visita.efecha);
//			cv.put("EHORADE", visita.ehorade);
//			cv.put("EHORAA", visita.ehoraa);
//			cv.put("EPVFECHA", visita.epvfecha);
//			cv.put("EPVHORA", visita.epvhora);
//			cv.put("ERESVIS", visita.eresvis);
//			cv.put("ERESVIS_O", visita.eresvis_o);
//			cv.put("SFECHA", visita.sfecha);
//			cv.put("SHORADE", visita.shorade);
//			cv.put("SHORAA", visita.shoraa);
//			cv.put("SRESVIS", visita.sresvis);
//			cv.put("SRESVIS_O", visita.sresvis_o);
//			cv.put("GPSLATITUD", visita.gpslatitud);
//			cv.put("GPSLONGITUD", visita.gpslongitud);
////			cv.put("USUCRE", visita.usucre);
////			cv.put("FECCRE", visita.feccre);
////			cv.put("USUREG", visita.usureg);
////			cv.put("FECREG", visita.fecreg);
////			cv.put("FECENV", visita.fecenv);
//			
//			//Cursor cur = db.rawQuery("SELECT ID_N FROM T_DIG_02_VISITA WHERE ID_N=? AND NROVIS=?", new String[]{visita.id_n, visita.nrovis.toString()});
//			//boolean exist = cur.getCount()>0;
//			
//			if(exist) {
//				cv.put("USUREG", dbh.getFechaActualToString());
//				cv.put("FECREG", dbh.getFechaActualToString());
//				success = db.update("T_DIG_02_VISITA", cv, "ID_N=? AND NROVIS=?", new String[]{visita.id_n, visita.nrovis.toString()})>0;
//			}
//			else {
//				cv.put("FECCRE", dbh.getFechaActualToString());
//				cv.put("FECREG", dbh.getFechaActualToString());
//				cv.put("USUCRE", Utilidades.getInt(Globales.currentUser.id,-1));
//				cv.put("USUREG", Utilidades.getInt(Globales.currentUser.id,-1));
//				long as = db.insert("T_DIG_02_VISITA", null, cv);
//				success =  as != -1;
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
//		return success;
//	}
	
//	public boolean guardarVisitaSup(ATVisita visita) {
//		boolean success = false;
//		boolean exist = existeATVisita(visita.id_n, visita.nrovis);
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			
//			cv.put("ID_N", visita.id_n);
//			cv.put("NROVIS", visita.nrovis);
//			cv.put("SFECHA", visita.sfecha);
//			cv.put("SHORADE", visita.shorade);
//			cv.put("SHORAA", visita.shoraa);
//			cv.put("SRESVIS", visita.sresvis);
//			cv.put("SRESVIS_O", visita.sresvis_o);
////			cv.put("USUCRE", visita.usucre);
////			cv.put("FECCRE", visita.feccre);
////			cv.put("USUREG", visita.usureg);
////			cv.put("FECREG", visita.fecreg);
////			cv.put("FECENV", visita.fecenv);
//			
//			if(exist) {
//				success = db.update("T_DIG_02_VISITA", cv, "ID_N=? AND NROVIS=?", new String[]{visita.id_n, visita.nrovis.toString()})>0;
//			}
//			else {
//				long as = db.insert("T_DIG_02_VISITA", null, cv);
//				success =  as != -1;
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
//		return success;
//	}
	
	public Boolean deleteATVisita(String id_n, Integer nrovis) {
		SQLiteDatabase db =dbh.getWritableDatabase();
		boolean bres=false;

		try {
			bres = db.delete("T_DIG_02_VISITA", "ID_N=? AND NROVIS=?", new String[]{id_n, nrovis.toString()})>0;
		} catch (Exception e) {
			Log.d(TAG,e.getMessage());
		} finally {
			db.close();
		}
		return bres;
	}
	
//	public List<Anexo_C200_Det> getListAnexo_C200(String idn, Integer sec_n){
//		ArrayList<Anexo_C200_Det> lstData = new ArrayList<Anexo_C200_Det>();
//		Anexo_C200_Det data = new Anexo_C200_Det();
//		SQLiteDatabase db = dbh.getReadableDatabase();
//		
//		List<String> allFieldMatches = data.getFieldMatches(200, 208, "ID_N", "SEC_N", "A_OBS_100_200");
//		
////		Log.e("valores1", "obs: "+data.getFields(allFieldMatches));
//		
//		Cursor result = db.rawQuery("SELECT "+data.getFields(allFieldMatches)+" FROM T_ANEXO_200_DET WHERE ID_N='" + idn + "' AND SEC_N = " + sec_n, null);
//
//		if(result.getCount() == 0) {
//			lstData.add(data);
//			return lstData;
//		}
//		while(result.moveToNext()){
//			Anexo_C200_Det datarow = new Anexo_C200_Det();
//			datarow = (Anexo_C200_Det)datarow.fillEntity(result, allFieldMatches);
//			lstData.add(datarow);
//		}
//		
//		result.close();
//		db.close();
//		
//		return lstData;
//	}
	
	public List<DelVisita> getDelVisitas(String id_n, Integer nro_vis, List<String> fields) {
//		String where = "";
//		if(nro_vis != null) where = " AND III2_1 = "+nro_vis.toString()+" ";
//		SQLiteDatabase db =dbh.getReadableDatabase();
		List<DelVisita> lstDelVisitas = new ArrayList<DelVisita>();
//		DelVisita data = new DelVisita();
//		Cursor cursor=null;
//		
//		Utilidades.FieldsEntity fe = Utilidades.getListFieldsEntity(data, fields, false);
//		
//		try {
//			cursor=db.rawQuery("SELECT "+ fe.fields +" FROM T_DIG_03_VISITA WHERE ID_N='"+id_n+"' "
//					+ where+"ORDER BY III2_1 ASC", null);
//			
//			if(cursor.getCount() == 0) {
////				lstDelVisitas.add(data);
//				return lstDelVisitas;
//			}
//			while(cursor.moveToNext()){
//				DelVisita datarow = new DelVisita();
//				datarow = (DelVisita)datarow.fillEntity(cursor, fe.lstfields);
//				lstDelVisitas.add(datarow);
//			}
//			
////			DelVisita visita;
////	        while(cursor.moveToNext()){
////	        	visita = new DelVisita();
////	        	visita.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
////	        	visita.iii2_1 = cursor.getString(cursor.getColumnIndex("III2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("III2_1"));
////	        	visita.iii2_2_d = cursor.getString(cursor.getColumnIndex("III2_2_D"));
////	        	visita.iii2_3a_ih = cursor.getString(cursor.getColumnIndex("III2_3A_IH"));
////	        	visita.iii2_3b_fh = cursor.getString(cursor.getColumnIndex("III2_3B_FH"));
////	        	visita.iii2_4a_d = cursor.getString(cursor.getColumnIndex("III2_4A_D"));
////	        	visita.iii2_4b_h = cursor.getString(cursor.getColumnIndex("III2_4B_H"));
////	        	visita.iii2_5 = cursor.getString(cursor.getColumnIndex("III2_5"))==null?null:cursor.getInt(cursor.getColumnIndex("III2_5"));
////	        	visita.iii2_5_o = cursor.getString(cursor.getColumnIndex("III2_5_O"));
////	        	visita.iii2_6_d = cursor.getString(cursor.getColumnIndex("III2_6_D"));
////	        	visita.iii2_7a_h = cursor.getString(cursor.getColumnIndex("III2_7A_H"));
////	        	visita.iii2_7b_h = cursor.getString(cursor.getColumnIndex("III2_7B_H"));
////	        	visita.iii2_8 = cursor.getString(cursor.getColumnIndex("III2_8"))==null?null:cursor.getInt(cursor.getColumnIndex("III2_8"));
////	        	visita.iii2_8_o = cursor.getString(cursor.getColumnIndex("III2_8_O"));
////
////	        	
////	        	lstDelVisitas.add(visita);
////	        }
//		} catch (Exception e) {
//			Log.d("Cynthia",e.getMessage());
//		} finally {
//			cursor.close();
//			db.close();
//		}
		
		return lstDelVisitas;
	}
	
	public List<DelVisita> getVisitas(INF_Caratula01 caratula) {
		return getBeans(TABLA_VISITA, 
				"ID_N=?",
				"III_1", DelVisita.class, 
				caratula.id_n.toString());
	}
	
	public List<DelVisita> getDelVisitas(INF_Caratula01 bean, SeccionCapitulo... secciones) {
		return getBeans(TABLA_VISITA, 
				"ID_N='"+bean.id_n+"'",
				new String[] {},
				"III_1", DelVisita.class, secciones);
	}
	
	public DelVisita getDelVisita(String id_n, Integer nro_vis, SeccionCapitulo... secciones) {
		DelVisita bean = (DelVisita) getBean(
				TABLA_VISITA,
				"ID_N=? AND III_1=?",
				new String[] { id_n.toString(), nro_vis.toString()},
				DelVisita.class, secciones);
		return bean;
	}
	
//	public List<DelVisita> getDelVisitas(String id_n) {
//		SQLiteDatabase db =dbh.getReadableDatabase();
//		List<DelVisita> lstDelVisitas = new ArrayList<DelVisita>();
//		Cursor cursor=null;
//		
//		try {
//			cursor=db.rawQuery("SELECT * FROM T_DIG_03_VISITA WHERE ID_N='"+id_n+"' ORDER BY III2_1 ASC", null);
//			
//			DelVisita visita;
//	        while(cursor.moveToNext()){
//	        	visita = new DelVisita();
//	        	visita.id_n = cursor.getString(cursor.getColumnIndex("ID_N"));
//	        	visita.iii2_1 = cursor.getString(cursor.getColumnIndex("III2_1"))==null?null:cursor.getInt(cursor.getColumnIndex("III2_1"));
//	        	visita.iii2_2_d = cursor.getString(cursor.getColumnIndex("III2_2_D"));
//	        	visita.iii2_3a_ih = cursor.getString(cursor.getColumnIndex("III2_3A_IH"));
//	        	visita.iii2_3b_fh = cursor.getString(cursor.getColumnIndex("III2_3B_FH"));
//	        	visita.iii2_4a_d = cursor.getString(cursor.getColumnIndex("III2_4A_D"));
//	        	visita.iii2_4b_h = cursor.getString(cursor.getColumnIndex("III2_4B_H"));
//	        	visita.iii2_5 = cursor.getString(cursor.getColumnIndex("III2_5"))==null?null:cursor.getInt(cursor.getColumnIndex("III2_5"));
//	        	visita.iii2_5_o = cursor.getString(cursor.getColumnIndex("III2_5_O"));
//	        	visita.iii2_6_d = cursor.getString(cursor.getColumnIndex("III2_6_D"));
//	        	visita.iii2_7a_h = cursor.getString(cursor.getColumnIndex("III2_7A_H"));
//	        	visita.iii2_7b_h = cursor.getString(cursor.getColumnIndex("III2_7B_H"));
//	        	visita.iii2_8 = cursor.getString(cursor.getColumnIndex("III2_8"))==null?null:cursor.getInt(cursor.getColumnIndex("III2_8"));
//	        	visita.iii2_8_o = cursor.getString(cursor.getColumnIndex("III2_8_O"));
////	        	visita.gpslatitud = cursor.getString(cursor.getColumnIndex("GPSLATITUD"));
////	        	visita.gpslongitud = cursor.getString(cursor.getColumnIndex("GPSLONGITUD"));
////	        	visita.usucre = cursor.getString(cursor.getColumnIndex("USUCRE"));
////	        	visita.feccre = cursor.getString(cursor.getColumnIndex("FECCRE"));
////	        	visita.usureg = cursor.getString(cursor.getColumnIndex("USUREG"));
////	        	visita.fecreg = cursor.getString(cursor.getColumnIndex("FECREG"));
////	        	visita.fecenv = cursor.getString(cursor.getColumnIndex("FECENV"));
//	        	
//	        	lstDelVisitas.add(visita);
//	        }
//		} catch (Exception e) {
//			Log.d("Cynthia",e.getMessage());
//		} finally {
//			cursor.close();
//			db.close();
//		}
//		
//		return lstDelVisitas;
//	}
	
//	public boolean guardarVisita(DelVisita visita) {
//		boolean success = false;
//		boolean exist = existeDelVisita(visita.id_n, visita.iii2_1);
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			
//			cv.put("ID_N", visita.id_n);
//			cv.put("III2_1", visita.iii2_1);
//			cv.put("III2_2_D", visita.iii2_2_d);
//			cv.put("III2_3A_IH", visita.iii2_3a_ih);
//			cv.put("III2_3B_FH", visita.iii2_3b_fh);
//			cv.put("III2_4A_D", visita.iii2_4a_d);
//			cv.put("III2_4B_H", visita.iii2_4b_h);
//			cv.put("III2_5", visita.iii2_5);
//			cv.put("III2_5_O", visita.iii2_5_o);
//			cv.put("III2_6_D", visita.iii2_6_d);
//			cv.put("III2_7A_H", visita.iii2_7a_h);
//			cv.put("III2_7B_H", visita.iii2_7b_h);
//			cv.put("III2_8", visita.iii2_8);
//			cv.put("III2_8_O", visita.iii2_8_o);
////			cv.put("GPSLATITUD", visita.gpslatitud);
////			cv.put("GPSLONGITUD", visita.gpslongitud);
//			
//			if(exist) {
//				cv.put("USUREG", dbh.getFechaActualToString());
//				cv.put("FECREG", dbh.getFechaActualToString());
//				success = db.update("T_DIG_03_VISITA", cv, "ID_N=? AND III2_1=?", new String[]{visita.id_n, visita.iii2_1.toString()})>0;
//			}
//			else {
//				cv.put("FECCRE", dbh.getFechaActualToString());
//				cv.put("FECREG", dbh.getFechaActualToString());
//				cv.put("USUCRE", Utilidades.getInt(Globales.currentUser.id,-1));
//				cv.put("USUREG", Utilidades.getInt(Globales.currentUser.id,-1));
//				long as = db.insert("T_DIG_03_VISITA", null, cv);
//				success =  as != -1;
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
//		return success;
//	}
	
	public boolean guardarVisita(DelVisita visita, List<String> fields) {
		boolean success = false;
//		boolean exist = existeDelVisita(visita.id_n, visita.iii2_1);
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//			cv.put("ID_N", visita.id_n);
//
//	    	cv = visita.getContentValues(cv, Utilidades.getListFieldsEntity(visita, fields, true).lstfields);
//
//			if(exist) {
////				cv.put("USUREG", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
//				success = db.update("T_DIG_03_VISITA", cv, "ID_N=? AND III2_1=?", new String[]{visita.id_n, visita.iii2_1.toString()})>0;
//			}
//			else {
////				cv.put("FECCRE", dbh.getFechaActualToString());
////				cv.put("FECREG", dbh.getFechaActualToString());
////				cv.put("USUCRE", Utilidades.getInt(Globales.currentUser.id,-1));
////				cv.put("USUREG", Utilidades.getInt(Globales.currentUser.id,-1));
//				long as = db.insert("T_DIG_03_VISITA", null, cv);
//				success =  as != -1;
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
	
	public boolean saveOrUpdate(DelVisita bean, SeccionCapitulo... secciones)
			throws java.sql.SQLException {
		return this
				.saveOrUpdate(
						TABLA_VISITA,
						"ID_N='"+bean.id_n+"' AND III_1=?",
						bean, new String[] { "iii_1" },
						secciones);
	}
	
//	public boolean guardarVisitaSup(DelVisita visita) {
//		boolean success = false;
//		boolean exist = existeDelVisita(visita.id_n, visita.iii2_1);
//		SQLiteDatabase db = dbh.getWritableDatabase();
//		
//		try{
//			ContentValues cv = new ContentValues();
//
//			cv.put("ID_N", visita.id_n);
//			cv.put("III2_1", visita.iii2_1);
//			cv.put("III2_6_D", visita.iii2_6_d);
//			cv.put("III2_7A_H", visita.iii2_7a_h);
//			cv.put("III2_7B_H", visita.iii2_7b_h);
//			cv.put("III2_8", visita.iii2_8);
//			cv.put("III2_8_O", visita.iii2_8_o);
//			
//			if(exist) {
//				success = db.update("T_DIG_03_VISITA", cv, "ID_N=? AND III2_1=?", new String[]{visita.id_n, visita.iii2_1.toString()})>0;
//			}
//			else {
//				long as = db.insert("T_DIG_03_VISITA", null, cv);
//				success =  as != -1;
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
//		return success;
//	}
	
	public Boolean deleteDelVisita(String id_n, Integer nrovis) {
		SQLiteDatabase db =dbh.getWritableDatabase();
		boolean bres=false;

		try {
			bres = db.delete("T_DIG_03_VISITA", "ID_N=? AND III_1=?", new String[]{id_n, nrovis.toString()})>0;
		} catch (Exception e) {
			Log.d(TAG,e.getMessage());
		} finally {
			db.close();
		}
		return bres;
	}
	public boolean borrarVisita(DelVisita bean, MAINTENCE opcion)
			throws java.sql.SQLException {
		
		return opcion == MAINTENCE.ONEONLY ?
				borrar(
				TABLA_VISITA,
				"ID_N=? AND III_1=?",
				bean.id_n, bean.iii_1.toString()
				):
					borrar(
							TABLA_VISITA,
							"ID_N=?",
							bean.id_n
							);
	}

}