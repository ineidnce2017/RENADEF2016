package gob.inei.renadef2016.modelo;

import gob.inei.dnce.annotations.FieldEntity;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.interfaces.IDetailEntityComponent;

public class Search extends Entity implements IDetailEntityComponent { 

	private static final long serialVersionUID = 1L;
	public Integer codigo = null;
	public String nombre = null;
	public Integer valor = null;
	public String titulo = null;
	public String subtitulo = null;
    
    public Search() { }
    
    public Search(Integer codigo, String nombre, Integer valor) { 
    	this.codigo = codigo;
    	this.nombre = nombre;
    	this.valor = valor;
    } 
    
    public String getTitle() {
		return valor == 0 ? nombre : "\t\t"+nombre;
	}
    
    public String getModalidad(){
    	return codigo + ". " + nombre.trim();
    }
   

	@Override
	public void cleanEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTitle() {
		return valor != 2;
	}
	
	@Override
	public String toString() {
		return String.valueOf(codigo) + " - "+nombre;
	}
} 
