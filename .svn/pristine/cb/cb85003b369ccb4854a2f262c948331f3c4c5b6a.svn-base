package gob.inei.renadef2016.modelo;

import gob.inei.dnce.annotations.FieldEntity;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.util.Util;

import java.util.List;

public class Permiso extends Entity {
	
	@FieldEntity(ignoreField=true)
	public static final int MODIFICAR_CENTROS_POBLADOS = 1;
	@FieldEntity(ignoreField=true)
	public static final int MODIFICAR_ZONAS = 2;
	@FieldEntity(ignoreField=true)
	public static final int MODIFICAR_MANZANAS = 3;
	@FieldEntity(ignoreField=true)
	public static final int MODIFICAR_FRENTES = 4;
	@FieldEntity(ignoreField=true)
	public static final int REGISTRAR_CPV0301 = 5;
	@FieldEntity(ignoreField=true)
	public static final int REGISTRAR_CPV0302 = 6;
	@FieldEntity(ignoreField=true)
	public static final int CALIFICAR = 7;
	@FieldEntity(ignoreField=true)
	public static final int ASIGNAR_TRABAJO = 8;
	@FieldEntity(ignoreField=true)
	public static final int CAMBIAR_DE_DNI = 9;
	
	private static final long serialVersionUID = 1L;
	public String descripcion;

	public Permiso() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	public static boolean tieneAcceso(List<Permiso> permisos, int permiso) {
		if (permisos == null || permisos.size() == 0) {
			return false;
		}
		for (Permiso p : permisos) {
			if (!Util.esDiferente(p.id, permiso)) {
				return true;
			}
		}
		return false;
	}
}
