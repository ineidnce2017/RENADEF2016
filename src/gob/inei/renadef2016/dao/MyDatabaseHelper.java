package gob.inei.renadef2016.dao;

import gob.inei.dnce.dao.DatabaseHelper;
import gob.inei.renadef2016.R;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class MyDatabaseHelper extends DatabaseHelper {
	private static String TAG = "MyDatabaseHelper";

	protected static Context context;

	public MyDatabaseHelper(Context ctx, String databaseName,
			int databaseVersion) {
		super(ctx, databaseName, databaseVersion);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		crearVistasCobertura(db);
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH208_COD", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH208_COD INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH208_COD_O", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH208_COD_O VARCHAR(100) NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH209_COD", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH209_COD INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH209_COD_O", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH209_COD_O VARCHAR(100) NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH210_COD", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH210_COD INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH210_COD_O", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH210_COD_O VARCHAR(100) NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH211_21_COD", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH211_21_COD INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH211_22_COD", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH211_22_COD INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH211_23", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH211_23 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH211_24", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH211_24 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH211_25", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH211_25 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH211_26", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH211_26 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C200, "IH211_26_O", "ALTER TABLE "+MarcoDAO.TABLA_C200+" ADD COLUMN IH211_26_O VARCHAR(100) NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_A_1", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_A_1 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_A_2", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_A_2 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_A_3", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_A_3 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_B_1", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_B_1 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_B_2", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_B_2 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_B_3", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_B_3 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_B_4", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_B_4 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_B_5", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_B_5 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_B_6", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_B_6 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_C_1", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_C_1 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_C_2", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_C_2 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_D_1", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_D_1 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "FALTAS_E_1", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN FALTAS_E_1 INT NULL;");
		addColumnIfNotExist(db, MarcoDAO.TABLA_C100, "DNTOTREST", "ALTER TABLE "+MarcoDAO.TABLA_C100+" ADD COLUMN DNTOTREST INT NULL;");
	}
	
	@Override 
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		crearVistasCobertura(db);
	}

	@Override
	public void leerRAW(SQLiteDatabase db) {
		cargarXML(db, R.raw.depasignado, "sqlstatement");
		cargarXML(db, R.raw.schemadatabase, "sqlstatement");
		cargarXML(db, R.raw.sql_sistema, "sql_sistema");
//		cargarXML(db, R.raw.sql_modalidades, "sql_modalidades");
//		cargarXML(db, R.raw.sql_vistas, "view");
//		cargarXML(db, R.raw.vistascobertura, "view");
//		cargarXML(db, R.raw.sql_vistas_report, "view");
		crearVistasCobertura(db);
	}

	@Override
	public void crearVistasCobertura(SQLiteDatabase db) {
		cargarXML(db, R.raw.sql_modalidades, "sql_modalidades");
		cargarXML(db, R.raw.sql_vistas, "view");
		cargarXML(db, R.raw.vistascobertura, "view");
		cargarXML(db, R.raw.sql_vistas_report, "view");
		cargarXML(db, R.raw.sql_upgrade, "sql");
	}
}
