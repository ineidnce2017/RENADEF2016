package gob.inei.renadef2016.service;

import gob.inei.dnce.dao.SQLiteDAO;
import gob.inei.renadef2016.dao.MyDatabaseHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class Service {

	protected SQLiteDAO sqliteDAO;
	protected MyDatabaseHelper dbh;
	public static final String DATABASE_NAME = "bdRenadef2016_produccion_v01.db";
	public static final int DATABASE_VERSION =  6;
	
	public Service(Context ctx) {
		this.dbh = new MyDatabaseHelper(ctx, DATABASE_NAME, DATABASE_VERSION);
	}
	
	public SQLiteDatabase startTX() {
		return null;
	}
		
	public void commitTX(SQLiteDatabase dbTX) {
		
	}
	
	public void endTX(SQLiteDatabase dbTX) {
		
	}
	
}
