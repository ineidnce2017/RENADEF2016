package gob.inei.renadef2016.modelo.delitos;

import gob.inei.dnce.components.Entity;
import gob.inei.dnce.interfaces.IDetailEntityComponent;
import gob.inei.dnce.util.Util;

import java.io.Serializable;

public class Cap400Delitos extends Entity implements IDetailEntityComponent, Serializable{
	private static final long serialVersionUID = 1L;
	public String id_n = null;
	public Integer nro_mreg = null;
	public Integer nro_pvreg = null;
	public Integer orden_400 = null;
	public String ivm401a = null;
	public String ivm401b = null;
	public String ivm401c = null;
	public Integer ivm402 = null;
	public String ivm402_n = null;
	public Integer ivm403 = null;
	public String ivm404 = null;
	public String ivm405_dia = null;
	public String ivm405_mes = null;
	public String ivm405_anio = null;
	public Integer ivm406 = null;
	public Integer ivm407 = null;
	public String ivm407_o = null;
	public Integer ivm408 = null;
	public String obs_03_400 = null;
	
	public Cap400Delitos(){

	}
	
	public Cap400Delitos(String id_n, Integer nro_mreg, Integer nro_pvreg, Integer orden_400){
		this.id_n = id_n;
		this.nro_mreg = nro_mreg;
		this.nro_pvreg = nro_pvreg;
		this.orden_400 = orden_400;
	}
	
	public String getP401(){
		return (ivm401a==null?"":ivm401a)+" "+(ivm401b==null?"":ivm401b)+
				" "+(ivm401c==null?"":ivm401c);
	}
	
	public String getP402(){
		return (ivm402==null?"":ivm402+(ivm402_n==null?"":" - "+ivm402_n));
	}
	
	public String getP403(){
		if(ivm403 == null) return "";
		switch (ivm403) {
			case 1: return "1 - Hombre.";
			case 2: return "2 - Mujer.";
			case 3: return "3 - No precisa.";
			default: return ivm403.toString();
		}
	}
	
	public String getP405(){
		return (ivm405_dia==null?"__":Util.completarCadena(ivm405_dia, "0", 2, Util.COMPLETAR.IZQUIERDA))+"/"+
				(ivm405_mes==null?"__":Util.completarCadena(ivm405_mes, "0", 2, Util.COMPLETAR.IZQUIERDA))+"/"+
				(ivm405_anio==null?"__":ivm405_anio);
	}
	
	public String getP406() {
		if(ivm406 == null) return "";
		switch (ivm406) {
			case 1: return "1 - Sin nivel.";
			case 2: return "2 - Educacion inicial.";
			case 3: return "3 - Primaria.";
			case 4: return "4 - Secundaria.";
			case 5: return "5 - Sup. no Universitario.";
			case 6: return "6 - Sup. Universitario.";
			case 7: return "7 - Postgrado.";
			case 8: return "8 - No precisa.";
			default: return ivm406.toString();
		}
	}
	
	public String getP407() {
		if(ivm407 == null) return "";
		switch (ivm407) {
			case 1: return "1 - Agricultor/a.";
			case 2: return "2 - Ama de casa.";
			case 3: return "3 - Cambista.";
			case 4: return "4 - Agente de seguridad.";
			case 5: return "5 - Taxista.";
			case 6: return "6 - Conductor/a de bus/combi/motocicleta, etc.";
			case 7: return "7 - Comerciante informal.";
			case 8: return "8 - Comerciante formal.";
			case 9: return "9 - Empleado/a en oficina.";
			case 10: return "10 - Empresario/a negocio independiente.";
			case 11: return "11 - Estudiante.";
			case 12: return "12 - Empleado/a del hogar.";
			case 13: return "13 - Mienbro de las FF.AA/PNP.";
			case 14: return "14 - Obrero/a (en general).";
			case 15: return "15 - Sin empleo.";
			case 16: return "16 - Jubilado/Pensionista.";
			case 17: return "17 - No precisa.";
			case 18: return "18 - Otro ["+ivm407_o+"]";
			default: return ivm407.toString();
		}
	}
	
	public String getP408() {
		if(ivm408 == null) return "";
		switch (ivm408) {
			case 1: return "1 - Conviviente.";
			case 2: return "2 - Casado/a.";
			case 3: return "3 - Viudo/a.";
			case 4: return "4 - Divorciado/a.";
			case 5: return "5 - Separado/a.";
			case 6: return "6 - Soltero/a.";
			case 7: return "7 - No precisa.";
			default: return ivm408.toString();
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
