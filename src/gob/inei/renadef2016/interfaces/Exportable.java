package gob.inei.renadef2016.interfaces;

import gob.inei.dnce.interfaces.IDetailEntityComponent;

public interface Exportable extends IDetailEntityComponent {

	boolean getResFin();
	String getUbigeo();
	String getCodigoExportacion();
	String getDescripcionExportacion();
}
