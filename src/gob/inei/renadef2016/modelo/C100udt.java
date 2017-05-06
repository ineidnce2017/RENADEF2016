package gob.inei.renadef2016.modelo;

import gob.inei.dnce.components.Entity;
import gob.inei.dnce.interfaces.IDetailEntityComponent;

public class C100udt extends Entity implements IDetailEntityComponent{
	private static final long serialVersionUID = 1L;
	public Integer cod;
	public String field;
	public Integer value;
	
	@Override
	public boolean isTitle() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void cleanEntity() {
		// TODO Auto-generated method stub
		
	}

}
