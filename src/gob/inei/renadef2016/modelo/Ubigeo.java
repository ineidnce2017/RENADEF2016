package gob.inei.renadef2016.modelo;

import gob.inei.dnce.components.Entity;
import gob.inei.dnce.interfaces.IDetailEntityComponent;

public class Ubigeo extends Entity implements IDetailEntityComponent{
	
	private static final long serialVersionUID = 1L;
	public String ubigeo = null;
	public String ccdd = null;
	public String ccpp = null;
	public String ccdi = null;
	public String departamento = null;
	public String provincia = null;
	public String distrito = null;
	public byte[] foto = null;
	public byte[] foto2 = null;

	public Ubigeo() {
	}

	@Override
	public String toString() {
		if (ccpp == null && ccdi == null) {
			return ccdd != null ? ccdd + " - " + departamento : departamento;
		} else if (ccdi == null) {
			return ccpp != null ? ccpp + " - " + provincia : provincia;
		} else {
			return ccdi != null ? ccdi + " - " + distrito : distrito;
		}
	}

	@Override
	public void cleanEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTitle() {
		// TODO Auto-generated method stub
		return false;
	}
}
