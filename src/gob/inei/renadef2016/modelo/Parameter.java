package gob.inei.renadef2016.modelo;

import gob.inei.dnce.components.Entity;
import gob.inei.dnce.util.Util;

import java.io.Serializable;

public class Parameter extends Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	public String parameter = null;
	public String value1 = null;
	public String value2 = null;
	public String value3 = null;
	
	public Parameter() {
		// TODO Auto-generated constructor stub
	}
	
	public Parameter(Integer id, String parameter) {
		this.id = id;
		this.parameter = parameter;
	}
	
	@Override
	public String toString() {		
		return "Parameter "+id+": [" + Util.getText(value1) + "]"+" [" + Util.getText(value2) + "]"+" [" + Util.getText(value3) + "]";
	}
	
}
