package gob.inei.renadef2016.modelo;

import gob.inei.dnce.annotations.FieldEntity;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.interfaces.IDetailEntityComponent;

public class Cobertura extends Entity implements IDetailEntityComponent { 

	private static final long serialVersionUID = 1L;
	public Integer orden = null;
	public String cap = null;
	public String estado = null;
    
    @FieldEntity(ignoreField=true, saveField=false)
	public Integer apertura = null;
    @FieldEntity(saveField=false)
	public String nmedidor = null;
    
    public Cobertura() { } 
    
    public Cobertura(String text) {
    	this.cap = text;
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
