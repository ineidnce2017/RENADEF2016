package gob.inei.renadef2016.modelo.delitos;

import gob.inei.dnce.annotations.FieldEntity;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.interfaces.IDetailEntityComponent;
import gob.inei.dnce.util.Util;

import java.io.Serializable;

public class Cap200Delitos extends Entity implements IDetailEntityComponent, Serializable{
	private static final long serialVersionUID = 1L;
	
	public String id_n = null;
	public Integer nro_mreg = null;
	public Integer orden_200 = null;
//	public Integer ih201 = null;
//	public String ih201_o = null;
	public String ih201_nro_doc = null;
	public Integer ih202 = null;
	public String ih202_o = null;
//	public Integer ih203 = null;
//	public String ih203_o = null;
	public String ih203_dia = null;
	public String ih203_mes = null;
	public Integer ih203_anio = null;
	public String ih204_hor = null;
	public String ih204_min = null;
//	public Integer ih204 = null;
//	public String ih204_o = null;
	public String ih205 = null;
	public String ih205_dd = null;
	public String ih205_pp = null;
	public String ih205_dis = null;
	public Integer ih206 = null;
	public String ih206_o = null;
//	public String ih206_min = null;
	public String ih207 = null;
	public String ih207_a = null;
	public String ih207_b = null;
	public Integer ih208 = null;
	public String ih208_o = null;
	public Integer ih208_cod = null;
	public String ih208_cod_o = null;
	public Integer ih209 = null;
	public String ih209_o = null;
	public Integer ih209_cod = null;
	public String ih209_cod_o = null;
	public Integer ih210 = null;
	public String ih210_o = null;
	public Integer ih210_cod = null;
	public String ih210_cod_o = null;
	public Integer ih211_1 = null;
	public Integer ih211_2 = null;
	public Integer ih211_3 = null;
	public Integer ih211_4 = null;
	public Integer ih211_5 = null;
	public Integer ih211_6 = null;
	public Integer ih211_7 = null;
	public Integer ih211_8 = null;
	public Integer ih211_9 = null;
	public Integer ih211_10 = null;
	public Integer ih211_11 = null;
	public Integer ih211_12 = null;
	public Integer ih211_13 = null;
	public Integer ih211_14 = null;
	public Integer ih211_15 = null;
	public Integer ih211_16 = null;
	public Integer ih211_17 = null;
	public Integer ih211_18 = null;
	public Integer ih211_19 = null;
	public Integer ih211_20 = null;
	public Integer ih211_21 = null;
	public Integer ih211_22 = null;
	public String ih211_22_o = null;
	public Integer ih211_21_cod = null;
	public Integer ih211_22_cod = null;
	public Integer ih211_23 = null;
	public Integer ih211_24 = null;
	public Integer ih211_25 = null;
	public Integer ih211_26 = null;
	public String ih211_26_o = null;
	public Integer ih212 = null;
	public String ih213 = null;
	public String ih213_oficio = null;
	public Integer ih214 = null;
	public Integer ih215 = null;
	public Integer conte_reg300 = null;
	public Integer conte_reg400 = null;
	public String obs_03_200 = null;
	
//	public String id_n = null;
//	public Integer nro_mreg = null;
//	public Integer orden_200 = null;
//	public Integer ih201 = null;
//	public String ih201_o = null;
//	public Integer ih202 = null;
//	public String ih202_o = null;
//	public Integer ih203 = null;
//	public String ih203_o = null;
//	public String ih203_nro_doc = null;
//	public Integer ih204 = null;
//	public String ih204_o = null;
//	public String ih205_dia = null;
//	public String ih205_mes = null;
//	public Integer ih205_anio = null;
//	public String ih206_hor = null;
//	public String ih206_min = null;
//	public Integer ih207 = null;
//	public String ih207_o = null;
//	public Integer ih208 = null;
//	public String ih208_o = null;
//	public Integer ih209_1 = null;
//	public Integer ih209_2 = null;
//	public Integer ih209_3 = null;
//	public Integer ih209_4 = null;
//	public Integer ih209_5 = null;
//	public Integer ih209_6 = null;
//	public Integer ih209_7 = null;
//	public Integer ih209_8 = null;
//	public Integer ih209_9 = null;
//	public Integer ih209_10 = null;
//	public Integer ih209_11 = null;
//	public Integer ih209_12 = null;
//	public Integer ih209_13 = null;
//	public Integer ih209_14 = null;
//	public Integer ih209_15 = null;
//	public Integer ih209_16 = null;
//	public Integer ih209_17 = null;
//	public Integer ih209_18 = null;
//	public String ih209_18_o = null;
//	public Integer ih210 = null;
//	public Integer ih211 = null;
//	public String ih212_a = null;
//	public String ih212_b = null;
//	public Integer ih213 = null;
//	public Integer ih214 = null;
//	public Integer conte_reg300 = null;
//	public Integer conte_reg400 = null;
//	public String obs_03_200 = null;
	
	@FieldEntity(saveField=false)
	public String p208nom;
	@FieldEntity(saveField=false)
	public String borde;

	public Cap200Delitos(){

	}
	
	public Cap200Delitos(String id_n, Integer nro_mreg, Integer orden_200){
		this.id_n = id_n;
		this.nro_mreg = nro_mreg;
		this.orden_200 = orden_200;
	}
	
	@Override
	public void cleanEntity() {
		// TODO Auto-generated method stub
		
	}
	
	public String getP202(){
		if(ih202 == null) return "";
		switch (ih202) {
			case 1: return "1. Atestados Policiales.";
			case 2: return "2. Informes Policiales.";
			case 3: return "3. Carpetas Fiscales.";
			case 4: return "4. Notas Informativas.";
			case 5: return "5. Oficios.";
			case 6: return "6. Actas.";
			case 7: return "7. Partes Policiales.";
			case 8: return "8. Otro ["+ih202_o+"]";
			default: return ih202.toString();
		}
	}
	
	public Integer getP208(){
		return ih208_cod == null ? ih208 : ih208_cod;
	}
	
	public String getP208Cod(){
		return ih208_cod==null?getClasifMuerte():
				(ih208_cod+". "+Util.getText(p208nom).trim()+(ih208_cod_o==null?"":" - [ "+ih208_cod_o+" ]"));
	}
	public String getP208CodC(){
		return ih208_cod==null?(ih208==null?"":"P208: "+getClasifMuerte()):"P208: "+
			(ih208_cod+". "+Util.getText(p208nom).trim()+(ih208_cod_o==null?"":" - [ "+ih208_cod_o+" ]"));
	}

	public String getP203(){
		return (ih203_dia==null?"__":Util.completarCadena(ih203_dia, "0", 2, Util.COMPLETAR.IZQUIERDA))+"/"+
				(ih203_mes==null?"__":Util.completarCadena(ih203_mes, "0", 2, Util.COMPLETAR.IZQUIERDA))+"/"+
				(ih203_anio==null?"__":ih203_anio);
	}
	
	public String getP204(){
		return (ih204_hor==null?"__":ih204_hor)+":"+
				(ih204_min==null?"__":ih204_min);
	}
	
	public String getP205(){
		return (ih205_dd==null?"":ih205_dd)+""+
				(ih205_pp==null?"":ih205_pp)+""+
				(ih205_dis==null?"":ih205_dis);
	}
	
	public String getP206(){
		if(ih206 == null) return "";
		switch (ih206) {
			case 1: return "1. Avenida.";
			case 2: return "2. Jir\u00f3n.";
			case 3: return "3. Calle.";
			case 4: return "4. Pasaje.";
			case 5: return "5. Carretera.";
			case 6: return "6. Prolong.";
			case 7: return "7. Otro ["+ih206_o+"]";
			default: return ih206.toString();
		}
	}
	
	public String getP207(){
		return (ih207_a==null?"":ih207_a)+
				(ih207_b==null?"":" - "+ih207_b);
	}
	
	public Integer getP209(){
		return ih209_cod == null ? ih209 : ih209_cod;
	}
	
	public String getP209C(){
		if(ih209 == null) return "";
		switch (ih209) {
			case 1: return "1. Campo abierto/lugar desolado/casa abandonada.";
			case 2: return "2. Centro de salud/Instituci\u00f3n educativa.";
			case 3: return "3. Centro de trabajo.";
			case 4: return "4. Dependencia policial/cuartel.";
			case 5: return "5. En el rio, mar, acantilado, acequia, canal, etc.";
			case 6: return "6. Establecimiento penitenciario.";
			case 7: return "7. Instituci\u00f3n residencial (albergue).";
			case 8: return "8. Local comercial bancario o esparcimiento.";
			case 9: return "9. Veh\u00edculo privado.";
			case 10: return "10. Veh\u00edculo p\u00fablico.";
			case 11: return "11. V\u00eda p\u00fablica.";
			case 12: return "12. Vivienda de la v\u00edctima.";
			case 13: return "13. No precisa.";
			case 14: return "14. Otro ["+ih209_o+"]";
			default: return ih209.toString();
		}
	}
	
	public String getP209Cod(){
		if(ih209_cod == null) return getP209C();
		switch (ih209_cod) {
			case 1: return "1. Campo abierto/lugar desolado/casa abandonada.";
			case 2: return "2. Centro de salud/Instituci\u00f3n educativa.";
			case 3: return "3. Centro de trabajo.";
			case 4: return "4. Dependencia policial/cuartel.";
			case 5: return "5. En el rio, mar, acantilado, acequia, canal, etc.";
			case 6: return "6. Establecimiento penitenciario.";
			case 7: return "7. Instituci\u00f3n residencial (albergue).";
			case 8: return "8. Local comercial bancario o esparcimiento.";
			case 9: return "9. Veh\u00edculo.";
			case 10: return "10. Vivienda del victimario.";
			case 11: return "11. V\u00eda p\u00fablica.";
			case 12: return "12. Vivienda de la v\u00edctima.";
			case 13: return "13. Iglesia.";
			case 14: return "14. Losa deportiva.";
			case 15: return "15. Condominio.";
			case 16: return "16. Centro minero.";
			case 17: return "17. Hotel, hostal.";
			case 18: return "18. Cementerio.";
			case 19: return "19. Vivienda de un familiar.";
			case 20: return "20. No precisa.";
			case 21: return "21. Otro ["+ih209_cod_o+"]";
			default: return ih209_cod.toString();
		}
	}
	
	public Integer getP210(){
		return ih210_cod == null ? ih210 : ih210_cod;
	}
	
	public String getP210C(){
		if(ih210 == null) return "";
		switch (ih210) {
			case 1: return "1. Abuso de confianza.";
			case 2: return "2. Accidente de tr\u00e1nsito.";
			case 3: return "3. Acci\u00f3n de fuego directo.";
			case 4: return "4. Agresi\u00f3n f\u00edsica.";
			case 5: return "5. Agresi\u00f3n verbal.";
			case 6: return "6. Amordazamiento.";
			case 7: return "7. Arma blanca.";
			case 8: return "8. Arma de fuego.";
			case 9: return "9. Chantaje.";
			case 10: return "10. Circula de billetes o moneda falsa.";
			case 11: return "11. Cobro de coima.";
			case 12: return "12. Cogoteo.";
			case 13: return "13. En banda o pandilla.";
			case 14: return "14. Estrangulamientol asfixia ahogamiento.";
			case 15: return "15. Falsificaci\u00f3n de documentos.";
			case 16: return "16. Negligencia/m\u00e9dica.";
			case 17: return "17. Objeto contundente.";
			case 18: return "18. Precipitaci\u00f3n/ca\u00edda.";
			case 19: return "19. Secuestro.";
			case 20: return "20. Suministro de sustancias alucin\u00f3genas.";
			case 21: return "21. Suministro de sustancias t\u00f3xicas o envenenamiento.";
			case 22: return "22. Suplantaci\u00f3n de identidad.";
			case 23: return "23. Tima, enga\u00f1o.";
			case 24: return "24. Uso de tecnología inform\u00e1tica.";
			case 25: return "25. Venta al menudeo de droga.";
			case 26: return "26. No precisa.";
			case 27: return "27. Otro ["+ih210_o+"]";
			default: return ih210.toString();
		}
	}
	
	public String getP210Cod(){
		if(ih210_cod == null) return getP210C();
		switch (ih210_cod) {
			case 1: return "1. Abuso de confianza.";
			case 2: return "2. Accidente de tr\u00e1nsito.";
			case 3: return "3. Acci\u00f3n de fuego directo.";
			case 4: return "4. Agresi\u00f3n f\u00edsica.";
			case 5: return "5. Agresi\u00f3n verbal.";
			case 6: return "6. Amordazamiento.";
			case 7: return "7. Arma blanca.";
			case 8: return "8. Arma de fuego.";
			case 9: return "9. Chantaje.";
			case 10: return "10. Circula de billetes o moneda falsa.";
			case 11: return "11. Cobro de coima.";
			case 12: return "12. Cogoteo.";
			case 13: return "13. En banda o pandilla.";
			case 14: return "14. Estrangulamientol asfixia ahogamiento.";
			case 15: return "15. Falsificaci\u00f3n de documentos.";
			case 16: return "16. Negligencia/m\u00e9dica.";
			case 17: return "17. Objeto contundente.";
			case 18: return "18. Precipitaci\u00f3n/ca\u00edda.";
			case 19: return "19. Secuestro.";
			case 20: return "20. Suministro de sustancias alucin\u00f3genas.";
			case 21: return "21. Suministro de sustancias t\u00f3xicas o envenenamiento.";
			case 22: return "22. Suplantaci\u00f3n de identidad.";
			case 23: return "23. Tima, enga\u00f1o.";
			case 24: return "24. Uso de tecnología inform\u00e1tica.";
			case 25: return "25. Venta al menudeo de droga.";
			case 26: return "26. Pirateo.";
			case 27: return "27. Cambiazo.";
			case 28: return "28. Arrebato.";
			case 29: return "29. Tráfico de armas.";
			case 30: return "30. Desfalco.";
			case 31: return "31. Usurpación.";
			case 32: return "32. Vertidos indiscriminados.";
			case 33: return "33. No precisa.";
			case 34: return "34. Otro ["+ih210_cod_o+"]";
			default: return ih210_cod.toString();
		}
	}
	
	public String getP211C(){
		String p211t = ((ih211_1==null||ih211_1.equals(0)?"":"1, ")+
				(ih211_2==null||ih211_2.equals(0)?"":"2, ")+
				(ih211_3==null||ih211_3.equals(0)?"":"3, ")+
				(ih211_4==null||ih211_4.equals(0)?"":"4, ")+
				(ih211_5==null||ih211_5.equals(0)?"":"5, ")+
				(ih211_6==null||ih211_6.equals(0)?"":"6, ")+
				(ih211_7==null||ih211_7.equals(0)?"":"7, ")+
				(ih211_8==null||ih211_8.equals(0)?"":"8, ")+
				(ih211_9==null||ih211_9.equals(0)?"":"9, ")+
				(ih211_10==null||ih211_10.equals(0)?"":"10, ")+
				(ih211_11==null||ih211_11.equals(0)?"":"11, ")+
				(ih211_12==null||ih211_12.equals(0)?"":"12, ")+
				(ih211_13==null||ih211_13.equals(0)?"":"13, ")+
				(ih211_14==null||ih211_14.equals(0)?"":"14, ")+
				(ih211_15==null||ih211_15.equals(0)?"":"15, ")+
				(ih211_16==null||ih211_16.equals(0)?"":"16, ")+
				(ih211_17==null||ih211_17.equals(0)?"":"17, ")+
				(ih211_18==null||ih211_18.equals(0)?"":"18, ")+
				(ih211_19==null||ih211_19.equals(0)?"":"19, ")+
				(ih211_20==null||ih211_20.equals(0)?"":"20, ")+
				(ih211_21==null||ih211_21.equals(0)?"":"21"));
		p211t = p211t.trim();
		if(p211t.endsWith(",")) 
			return p211t.substring(0, p211t.length()-1);
		return p211t;
	}
	
	public String getP211Cod(){
		String p211t = ((ih211_1==null||ih211_1.equals(0)?"":"1, ")+
				(ih211_2==null||ih211_2.equals(0)?"":"2, ")+
				(ih211_3==null||ih211_3.equals(0)?"":"3, ")+
				(ih211_4==null||ih211_4.equals(0)?"":"4, ")+
				(ih211_5==null||ih211_5.equals(0)?"":"5, ")+
				(ih211_6==null||ih211_6.equals(0)?"":"6, ")+
				(ih211_7==null||ih211_7.equals(0)?"":"7, ")+
				(ih211_8==null||ih211_8.equals(0)?"":"8, ")+
				(ih211_9==null||ih211_9.equals(0)?"":"9, ")+
				(ih211_10==null||ih211_10.equals(0)?"":"10, ")+
				(ih211_11==null||ih211_11.equals(0)?"":"11, ")+
				(ih211_12==null||ih211_12.equals(0)?"":"12, ")+
				(ih211_13==null||ih211_13.equals(0)?"":"13, ")+
				(ih211_14==null||ih211_14.equals(0)?"":"14, ")+
				(ih211_15==null||ih211_15.equals(0)?"":"15, ")+
				(ih211_16==null||ih211_16.equals(0)?"":"16, ")+
				(ih211_17==null||ih211_17.equals(0)?"":"17, ")+
				(ih211_18==null||ih211_18.equals(0)?"":"18, ")+
				(ih211_19==null||ih211_19.equals(0)?"":"19, ")+
				(ih211_20==null||ih211_20.equals(0)?"":"20, ")+
				(ih211_21_cod==null||ih211_21_cod.equals(0)?"":"21, ")+
				(ih211_22_cod==null||ih211_22_cod.equals(0)?"":"22, ")+
				(ih211_23==null||ih211_23.equals(0)?"":"23, ")+
				(ih211_24==null||ih211_24.equals(0)?"":"24, ")+
				(ih211_25==null||ih211_25.equals(0)?"":"25, ")+
				(ih211_26==null||ih211_26.equals(0)?"":"26"));
		p211t = p211t.trim();
		if(p211t.endsWith(",")) 
			return p211t.substring(0, p211t.length()-1);
		return p211t;
	}
	
//	public String getP212(){
//		return (ih211==null||ih211.equals(2)?"":ih212_a+". "+ih212_b);
//	}
	
	private String getClasifMuerte() {
		if(ih208 == null) return "";
		switch (ih208) {
			case 1: return "1. Homicidio Simple.";
			case 2: return "2. Parricidio.";
			case 3: return "3. Homicidio calificado (Asesinato).";
			case 4: return "4. Homicidio por emoci\u00f3n violenta.";
			case 5: return "5. Infanticidio.";
			case 6: return "6. Homicido piadoso.";
			case 7: return "7. Feminicidio.";
			case 8: return "8. Sicariato";
			case 9: return "9. Muerte por accidente de tr\u00e1nsito";
			case 10: return "10. Muerte por accidente de trabajo, muerte por negligencia o impericia m\u00e9dica.";
			case 11: return "11. Otro ["+ih208_o+"]";
			case 12: return "12. Instigaci\u00f3n o ayuda al suicidio.";
			case 13: return "13. Otro ["+ih208_o+"]";
			case 14: return "14. Lesiones graves seguida de muerte.";
			case 15: return "15. Aborto provocado con subsecuente muerte de la madre.";
			case 16: return "16. Robo agravado con subsecuente muerte.";
			case 17: return "17. Otro ["+ih208_o+"]";
			case 18: return "18. Muerte natural.";
			case 19: return "19. Muerte por suicidio.";
			case 20: return "20. Muerte fortuita.";
			case 21: return "21. Muerte accidental (caida, asfixia/ahogo, etc.).";
			case 22: return "22. No precisa.";
			case 23: return "23. Otro ["+ih208_o+"]";
			case 24: return "24. Tentativa de Homicidio.";
			case 25: return "25. Aborto (autoaborto, aborto consentido, etc).";
			case 26: return "26. Lesiones graves (desfiguraci\u00f3n del rostro, mutilaci\u00f3n o extracci\u00f3n de \u00f3rgano, fractura de brazo).";
			case 27: return "27. Lesiones leves (golpe o traumatismo, heridas, quemaduras de 1er grado)";
			case 28: return "28. Lesiones con resultado fortuito";
			case 29: return "29. Lesiones culposas";
			case 30: return "30. Otro ["+ih208_o+"]";
			case 31: return "31. Exposici\u00f3n o abandono a personas incapaces (abandono a un menor de edad, abandono de persona incapaz de valerse por s\u00ed misma)";
			case 32: return "32. Omisi\u00f3n de socorro y exposici\u00f3n al peligro";
			case 33: return "33. Omisi\u00f3n de auxilio a persona en peligro o aviso a la autoridad";
			case 34: return "34. Exposici\u00f3n a peligro de persona dependiente (privaci\u00f3n de alimentos o cuidados indispensables, abuso de medios de correcci\u00f3n o disciplina, trabajos excesivos inadecuados)";
			case 35: return "35. Otro ["+ih208_o+"]";
			case 36: return "36. Injuria, Calumnia y Difamaci\u00f3n";
			case 37: return "37. Matrimonios ilegales (bigamia, matrimonio con persona casada, autorizaci\u00f3n ilegal de matrimonio, etc.)";
			case 38: return "38. Delitos contra el estado civil (alteraci\u00f3n o supresi\u00f3n del estado civil, fingimiento de embarazo o parto, etc.)";
			case 39: return "39. Atentados contra la patria potestad (sustracci\u00f3n de menor, inducci\u00f3n a la fuga de menor, instigaci\u00f3n o participaci\u00f3n en pandillaje pernicioso)";
			case 40: return "40. Omisi\u00f3n de asistencia familiar (omisi\u00f3n de prestaci\u00f3n de alimentos, abandono de mujer gestante en situaci\u00f3n cr\u00edtica)";
			case 41: return "41. Otro ["+ih208_o+"]";
			case 42: return "42. Violaci\u00f3n de la libertad personal (coacción, secuestro, etc.)";
			case 43: return "43. Violaci\u00f3n de la intimidad (escuchar observar o registrar palabras, escritos o im\u00e1genes sin consentimiento del interesado).";
			case 44: return "44. Violaci\u00f3n de domicilio";
			case 45: return "45. Violaci\u00f3n del Secreto de las comunicaciones (violaci\u00f3n de correspondencia, interferencia telef\u00f3nica, entre otros).";
			case 46: return "46. Violaci\u00f3n del secreto profesional";
			case 47: return "47. Violaci\u00f3n de la libertad de reuni\u00f3n (perturbaci\u00f3n de reuni\u00f3n p\u00fablica, prohibici\u00f3n de reuni\u00f3n p\u00fablica)";
			case 48: return "48. Violaci\u00f3n de la libertad de trabajo (atentado contra la libertad de trabajo y asociaci\u00f3n, atentado contra las condiciones de seguridad, salud e higiene industriales, etc.)";
			case 49: return "49. Violaci\u00f3n de la libertad de expresi\u00f3n";
			case 50: return "50. Violaci\u00f3n de la libertad sexual (violaci\u00f3n sexual, violaci\u00f3n de menor de edad, seducci\u00f3n, actos contra el pudor)";
			case 51: return "51. Proxenetismo (favorecimiento a la prostituci\u00f3n, turismo sexual infantil, rufianismo)";
			case 52: return "52. Ofensas al pudor p\u00fablico (exhibiciones y publicaciones obscenas, pornografía infantil)";
			case 53: return "53. Otro ["+ih208_o+"]";
			case 54: return "54. Hurto (hurto simple, hurto agravado, hurto de uso)";
			case 55: return "55. Robo (robo agravado)";
			case 56: return "56. Abigeato (hurto de ganado, hurto de uso de ganado)";
			case 57: return "57. Apropiaci\u00f3n il\u00edcita (sustracci\u00f3n de bien propio, apropiaci\u00f3n irregular, apropiaci\u00f3n de prenda)";
			case 58: return "58. Receptaci\u00f3n";
			case 59: return "59. Estafa y otras defraudaciones (estafa agravada).";
			case 60: return "60. Fraude en la administraci\u00f3n de personas jur\u00eddicas (Administraci\u00f3n fraudulenta)";
			case 61: return "61. Extorsi\u00f3n (chantaje)";
			case 62: return "62. Usurpaci\u00f3n";
			case 63: return "63. Da\u00f1os (da\u00f1o simple, daño agravado)";
			case 64: return "64. Delitos inform\u00e1ticos, alteraci\u00f3n, daño y destrucci\u00f3n de base de datos, sistema, red o programa de computadoras, etc.)";
			case 65: return "65. Otro ["+ih208_o+"]";
			case 66: return "66. Atentados contra el sistema crediticio";
			case 67: return "67. Usura";
			case 68: return "68. Libramientos y cobros indebidos";
			case 69: return "69. Otro ["+ih208_o+"]";
			case 70: return "70. Delitos contra los derechos de autor y conexos (copia o reproducci\u00f3n no autorizada, reproducci\u00f3n difusi\u00f3n, distribuci\u00f3n y circulaci\u00f3n de una obra sin autorizaci\u00f3n de autor, etc.)";
			case 71: return "71. Delitos contra la propiedad industrial (fabricaci\u00f3n o uso no autorizado de patente, uso indebido de marca)";
			case 72: return "72. Otro ["+ih208_o+"]";
			case 73: return "73. Delitos contra los bienes culturales (atentados contra yacimientos arqueol\u00f3gicos, extracci\u00f3n ilegal de bienes culturales)";
			case 74: return "74. Abuso del poder econ\u00f3mico";
			case 75: return "75. Acaparamiento, especulaci\u00f3n, adulteraci\u00f3n";
			case 76: return "76. Venta il\u00edcita de mercader\u00edas";
			case 77: return "77. Otros delitos econ\u00f3micos (informaciones falsas sobre calidad de productos, fraude en remates, licitaciones y concursos p\u00fablicos)";
			case 78: return "78. Desempe\u00f1o de actividades no autorizadas (funcionamiento ilegal de juegos de casino y máquinas tragamonedas)";
			case 79: return "79. Delitos financieros (concentraci\u00f3n crediticia, ocultamiento, omisión o falsedad de informaci\u00f3n, etc.)";
			case 80: return "80. Delitos monetarios (fabricaci\u00f3n y falsificaci\u00f3n de moneda de curso legal, fabricaci\u00f3n o inducci\u00f3n de instrumentos para falsificación de billetes)";
			case 81: return "81. Otro ["+ih208_o+"]";
			case 82: return "82. Contrabando";
			case 83: return "83. Defraudaci\u00f3n Fiscal (defraudaci\u00f3n de rentas de aduanas, defraudaci\u00f3n tributaria)";
			case 84: return "84. Elaboraci\u00f3n y comercio clandestino de producto";
			case 85: return "85. Otro ["+ih208_o+"]";
			case 86: return "86. Delitos de peligro com\u00fan (peligro por medio de incendio o explosi\u00f3n, conducci\u00f3n en estado de ebriedad o drogadicci\u00f3n, fabricaci\u00f3n, suministro o tenencia de materiales peligrosos, etc.)";
			case 87: return "87. Delitos contra los medios de transporte, comunicaci\u00f3n y otros servicios, p\u00fablicos";
			case 88: return "88. Delitos contra la salud p\u00fablica (contaminaci\u00f3n y propagaci\u00f3n de enfermedades peligrosas o contagiosas, tráfico Il\u00edcito de drogas, etc.)";
			case 89: return "89. Delitos contra el orden migratorio(tr\u00e1fico il\u00edcito de personas)";
			case 90: return "90. Otro ["+ih208_o+"]";
			case 91: return "91. Delitos de contaminaci\u00f3n (contaminacion del ambiente, tr\u00e1fico ilegal de res\u00edduos s\u00f3lidos, mineria ilegal, etc)";
			case 92: return "92. Delitos contra los recursos naturales (tr\u00e1fico ilegal, depredaci\u00f3n de especies flora y fauna, utilizaci\u00f3n indebida de tierras agr\u00edcolas)";
			case 93: return "93. Responsabilidad funcional e informaci\u00f3n falsa";
			case 94: return "94. Medidas cautelares y exclusi\u00f3n o reducci\u00f3n de pena";
			case 95: return "95. Otro ["+ih208_o+"]";
			case 96: return "96. Delitos contra la paz p\u00fablica (disturbios, apolog\u00eda al delito, asociaci\u00f3n il\u00edcita, marcaje o reglaje, ofensas a la memoria a los muertos, etc.)";
			case 97: return "97. Terrorismo";
			case 98: return "98. Otro ["+ih208_o+"]";
			case 99: return "99. Genosidio";
			case 100: return "100. Desaparici\u00f3n forzada";
			case 101: return "101. Tortura";
			case 102: return "102. Discriminaci\u00f3n (discriminaci\u00f3n de personas, incitaci\u00f3n a la discriminaci\u00f3n)";
			case 103: return "103. Manipulaci\u00f3n gen\u00e9tica y clonaci\u00f3n";
			case 104: return "104. Otro ["+ih208_o+"]";
			case 105: return "105. Atentados contra la seguridad nacional y traici\u00f3n a la patria(traici\u00f3n a la patria)";
			case 106: return "106. Delitos que comprometen las relaciones exteriores del estado (violaci\u00f3n de inmunidad del jefe de estado, atentado contra la persona que goza de protecci\u00f3n internacional, etc.)";
			case 107: return "107. Delitos contra los s\u00edmbolos y valores de la patria (ultraje a los s\u00edmbolos, próceres o héroes de la patria, etc.)";
			case 108: return "108. Otro ["+ih208_o+"]";
			case 109: return "109. Rebeli\u00f3n, sedici\u00f3n y mot\u00edn";
			case 110: return "110. Otro ["+ih208_o+"]";
			case 111: return "111. Delitos contra el derecho de sufragio (perturbaci\u00f3n o impedimento de proceso electoral, inducci\u00f3n a no votar, etc.)";
			case 112: return "112. Otro ["+ih208_o+"]";
			case 113: return "113. Delitos cometidos por particulares (usurpaci\u00f3n de autoridad, t\u00edtulos y honores, violencia a la autoridad y desacato)";
			case 114: return "114. Delitos cometidos por funcionarios p\u00fablicos (abuso de autoridad, concusi\u00f3n y peculado)";
			case 115: return "115. Delitos contra la administraci\u00f3n de justicia (contra la acci\u00f3n jurisdiccional, prevaricato, denegaci\u00f3n, retardo de justicia.)";
			case 116: return "116. Falsificaci\u00f3n de documentos en general (falsificaci\u00f3n de documentos en general, falsedad ideol\u00f3gica, omisi\u00f3n de declaraciones en documento, expedici\u00f3n de certificado m\u00e9dico falso, simulaci\u00f3n de accidente de tr\u00e1nsito)";
			case 117: return "117. Falsificaci\u00f3n de sellos, timbres y marcas oficiales (fabricaci\u00f3n o falsificaci\u00f3n de timbres, sellos y marcas oficiales, fabricaci\u00f3n fraudulenta de marcas o contrase\u00f1as oficiales)";
			case 118: return "118. Otro ["+ih208_o+"]";
			default: return ih208.toString();
		}
	}

	@Override
	public boolean isTitle() {
		// TODO Auto-generated method stub
		return false;
	}
}
