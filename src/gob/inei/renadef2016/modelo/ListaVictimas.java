package gob.inei.renadef2016.modelo;

import gob.inei.dnce.annotations.FieldEntity;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.interfaces.IDetailEntityComponent;

import java.io.Serializable;

public class ListaVictimas extends Entity implements Serializable, IDetailEntityComponent {
	private static final long serialVersionUID = 1L;

	public String codigo = null;
	public String nombre = null;
	public Integer dni = null;


	public ListaVictimas() {
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
	
	@Override
	public String toString() {
		return codigo + " - " + nombre;
	}
}
