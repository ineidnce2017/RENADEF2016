package gob.inei.renadef2016.modelo;

import java.util.List;

import gob.inei.dnce.annotations.FieldEntity;
import gob.inei.dnce.components.Entity;

public class Usuario extends Entity {

	public String usuario = null;
	public String clave = null;
	public Integer cargo_id = null;
	public String telefono = null;
	public String email = null;
	public String estado = null;
	public String nombres = null;
	public String apellidos = null;
	public String correo = null;
	public String dni = null;
	public String cod_sede_operativa = null;
	public String ccdd = null;
	public String ccpp = null;
	public String ccdi = null;
	public String equipo = null;
	public String ruta = null;

	@FieldEntity(ignoreField = true)
	public List<Permiso> permisos;

	@FieldEntity(ignoreField = true, saveField = false)
	public static final int REGISTRADOR = 3;

	@Override
	public String toString() {
		return usuario;
	}

}
