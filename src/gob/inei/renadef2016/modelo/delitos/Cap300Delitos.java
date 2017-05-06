package gob.inei.renadef2016.modelo.delitos;

import gob.inei.dnce.components.Entity;
import gob.inei.dnce.interfaces.IDetailEntityComponent;
import gob.inei.dnce.util.Util;

import java.io.Serializable;

public class Cap300Delitos extends Entity implements IDetailEntityComponent, Serializable{
	private static final long serialVersionUID = 1L;
//	public String id_n = null;
//	public Integer nro_mreg = null;
//	public Integer nro_vfreg = null;
//	public Integer orden_300 = null;
//	public String ivh301a = null;
//	public String ivh301b = null;
//	public String ivh301c = null;
//	public Integer ivh302 = null;
//	public String ivh302_n = null;
//	public Integer ivh303 = null;
//	public Integer ivh304 = null;
//	public Integer ivh304a = null;
//	public String ivh305_dia = null;
//	public String ivh305_mes = null;
//	public String ivh305_anio = null;
//	public Integer ivh306 = null;
//	public Integer ivh307 = null;
//	public String ivh307_o = null;
//	public Integer ivh308 = null;
//	public Integer ivh309 = null;
//	public String ivh309_o = null;
//	public String obs_03_300 = null;
	
	public String id_n = null;
	public Integer nro_mreg = null;
	public Integer nro_vfreg = null;
	public Integer orden_300 = null;
	public String ivh301a = null;
	public String ivh301b = null;
	public String ivh301c = null;
	public Integer ivh302 = null;
	public String ivh302_n = null;
	public Integer ivh303 = null;
	public Integer ivh304 = null;
	public Integer ivh305 = null;
	public String ivh306_dia = null;
	public String ivh306_mes = null;
	public String ivh306_anio = null;
	public Integer ivh307 = null;
	public Integer ivh308 = null;
	public String ivh308_o = null;
	public Integer ivh309 = null;
	public Integer ivh310 = null;
	public String ivh310_o = null;
	public String obs_03_300 = null;

	public Cap300Delitos(){

	}
	
	public Cap300Delitos(String id_n, Integer nro_mreg, Integer nro_vfreg, Integer orden_300){
		this.id_n = id_n;
		this.nro_mreg = nro_mreg;
		this.nro_vfreg = nro_vfreg;
		this.orden_300 = orden_300;
	}
	
	public String getP301(){
		return (ivh301a==null?"":ivh301a)+" "+(ivh301b==null?"":ivh301b)+
				" "+(ivh301c==null?"":ivh301c);
	}
	
	public String getP302(){
		return (ivh302==null?"":ivh302+(ivh302_n==null?"":" - "+ivh302_n));
	}
	
	public String getP303(){
		if(ivh303 == null) return "";
		switch (ivh303) {
			case 1: return "1 - Hombre.";
			case 2: return "2 - Mujer.";
			case 3: return "3 - No precisa.";
			default: return ivh303.toString();
		}
	}
	
	public String getP304(){
		return getCaractEdad();
	}
	
	public String getP306(){
		return (ivh306_dia==null?"__":Util.completarCadena(ivh306_dia, "0", 2, Util.COMPLETAR.IZQUIERDA))+"/"+
				(ivh306_mes==null?"__":Util.completarCadena(ivh306_mes, "0", 2, Util.COMPLETAR.IZQUIERDA))+"/"+
				(ivh306_anio==null?"__":ivh306_anio);
	}
	
	public String getP307() {
		if(ivh307 == null) return "";
		switch (ivh307) {
			case 1: return "1 - Sin nivel.";
			case 2: return "2 - Educacion inicial.";
			case 3: return "3 - Primaria.";
			case 4: return "4 - Secundaria.";
			case 5: return "5 - Sup. no Universitario.";
			case 6: return "6 - Sup. Universitario.";
			case 7: return "7 - Postgrado.";
			case 8: return "8 - No precisa.";
			default: return ivh307.toString();
		}
	}
	
	public String getP308() {
		if(ivh308 == null) return "";
		switch (ivh308) {
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
			case 18: return "18 - Otro ["+ivh308_o+"]";
			default: return ivh308.toString();
		}
	}
	
	public String getP309() {
		if(ivh309 == null) return "";
		switch (ivh309) {
			case 1: return "1 - Conviviente.";
			case 2: return "2 - Casado/a.";
			case 3: return "3 - Viudo/a.";
			case 4: return "4 - Divorciado/a.";
			case 5: return "5 - Separado/a.";
			case 6: return "6 - Soltero/a.";
			case 7: return "7 - No precisa.";
			default: return ivh309.toString();
		}
	}
	
	public String getP310(){
		return getRelParentesco();
	}
	
	private String getCaractEdad() {
		if(ivh304 == null) return "";
		switch (ivh304) {
			case 1: return "1 - Menor de tres a\u00f1os.";
			case 2: return "2 - Menor de doce a\u00f1os.";
			case 3: return "3 - De doce a m\u00e1s a\u00f1os.";
			case 4: return "4 - No precisa.";
			default: return ivh304.toString();
		}
	}
	
	private String getRelParentesco() {
		if(ivh310 == null) return "";
		switch (ivh310) {
			case 1: return "1 - Esposo/a.";
			case 2: return "2 - Ex-esposo/a.";
			case 3: return "3 - Pareja/conviviente.";
			case 4: return "4 - Ex-pareja/Ex-conviviente.";
			case 5: return "5 - Padre/Madre Suegro/a.";
			case 6: return "6 - Hijo/a.";
			case 7: return "7 - Otros parientes.";
			case 8: return "8 - Conocido/a.";
			case 9: return "9 - Desconocido/a.";
			case 10: return "10 - No precisa.";
			case 11: return "11 - Otro ["+ivh310_o+"]";
			default: return ivh310.toString();
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
