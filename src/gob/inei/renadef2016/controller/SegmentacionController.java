package gob.inei.renadef2016.controller;

import gob.inei.renadef2016.common.App;
import gob.inei.renadef2016.dao.MarcoDAO;
import gob.inei.renadef2016.modelo.Usuario;
import gob.inei.renadef2016.service.SegmentacionService;
import gob.inei.dnce.components.Entity;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SegmentacionController {

	public static final int CARTOGRAFIA = 1;
	public static final int REGISTRO = 2;
	private Context context;
	private SegmentacionService segmentacionService;
	
	public SegmentacionController(Context context) {
		this.context = context;
	}
	
	private SegmentacionService getSegmentacion() {
		if (segmentacionService == null) {
			segmentacionService = SegmentacionService.getInstance(context);
		}
		return segmentacionService;
	}

}
