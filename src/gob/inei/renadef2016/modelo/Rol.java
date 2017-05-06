package gob.inei.renadef2016.modelo;

import gob.inei.dnce.annotations.FieldEntity;
import gob.inei.dnce.components.Entity;

public class Rol extends Entity {
	
	@FieldEntity(ignoreField=true)
	public static final int ACTUALIZADOR = 1;
	@FieldEntity(ignoreField=true)
	public static final int REGISTRADOR = 2;
	@FieldEntity(ignoreField=true)
	public static final int JEFE_BRIGADA = 3;
	@FieldEntity(ignoreField=true)
	public static final int ACTUALIZADOR_REGISTRADOR = 5;
	@FieldEntity(ignoreField=true)
	public static final int CALIFICADOR = 6;
	@FieldEntity(ignoreField=true)
	public static final int RUTERO = 7;
	@FieldEntity(ignoreField=true)
	public static final int ANALISTA_CALIDAD = 8;
	
	private static final long serialVersionUID = 1L;
	public String descripcion;

	public Rol() {
	}

	public static class RolPermiso extends Entity {
		
		private static final long serialVersionUID = 1L;
		public Integer rol_id = null;
		public Integer permiso_id = null;
		
		public RolPermiso() {
		}	
	}
}
