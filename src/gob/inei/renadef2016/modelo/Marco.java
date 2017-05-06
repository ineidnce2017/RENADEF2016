package gob.inei.renadef2016.modelo;

import gob.inei.renadef2016.interfaces.Exportable;
import gob.inei.dnce.components.Entity;
import gob.inei.dnce.components.FragmentForm;
import gob.inei.dnce.interfaces.IDetailEntityComponent;

import java.io.Serializable;

public class Marco extends Entity implements IDetailEntityComponent, Serializable, Exportable{
	private static final long serialVersionUID = 1L;
	public String id_n;
	public String nomcomisaria;
	public String cod_region;
	public String region;
	public String ccdd;
	public String nombredd;
	public String ccpp;
	public String nombrepp;
	public String ccdi;
	public String nombredi;
	public Integer area;
	public String zona;
	public String zonalf;
	public String mza;
	public String mznalf;
//	public String frente;
	public String aer_ini;
	public String aer_fin;
	public Integer vdeli;
	public String dirtepol;
	public String capireferencia;
	public String tipo;
	public String direccion;
	public String comisario;
	public String telefono1;
	public String telefono2;
	public Integer tipocpnp;
	public String cccp;
	public String ubigeo;
	public String nombrecp;
	public String cod_dep_asig;
	public Integer agregado;
	public Integer periodo ;
	public String fechai;
	public String fechaf;
	public Integer resfin;
	
	public Marco(){}
	
//	public Marco(String coddepasig){
//		this.cod_dep_asig = coddepasig;
//	}
	
//	public Marco(String id_n, String nombcomisaria, String region, String nombredd, String nombrepp, String nombredi, Integer vdeli) {
//     this.id_n = id_n;
//     this.nomcomisaria = nombcomisaria;
//     this.region = region;
//     this.nombredd = nombredd;
//     this.nombrepp = nombrepp;
//     this.nombredi = nombredi;
//     this.vdeli=vdeli;
//}
	
//	public Marco(String id_n, String nomcomisaria, String region, String nombredd, String nombrepp, String nombredi, 
//			Integer vdeli, String dirtepol, String capireferencia, String tipo, String direccion, String comisario,
//			String telefono1, String telefono2, Integer tipocpnp, String cccp, String ubigeo, String nombrecp, 
//			int area, String zona, String zonalf, String mza, String mznalf, String aer_ini, String aer_fin) {
//		this.id_n = id_n;
//		this.nomcomisaria = nomcomisaria;
//		this.region = region;
//		this.nombredd = nombredd;
//		this.nombrepp = nombrepp;
//		this.nombredi = nombredi;
//		this.vdeli=vdeli;
//     
//		this.dirtepol = dirtepol;
//		this.capireferencia = capireferencia;
//		this.tipo = tipo;
//		this.direccion = direccion;
//		this.comisario = comisario;
//		this.telefono1 = telefono1;
//		this.telefono2 = telefono2;
//		this.tipocpnp = tipocpnp;
//		this.cccp = cccp;
//		this.ubigeo = ubigeo;
//		this.nombrecp = nombrecp;
//		this.area = area;
//		this.mza = mza;
//		this.mznalf = mznalf;
//		this.zona = zona;
//		this.zonalf = zonalf;
////		this.frente = frente;
//		this.aer_ini = aer_ini;
//		this.aer_fin = aer_fin;
//	}

	public String getId_n() {
		return id_n;
	}
	public void setId_n(String id_n) {
		this.id_n = id_n;
	}
	public String getNomcomisaria() {
		return nomcomisaria;
	}
	public void setNomcomisaria(String nomcomisaria) {
		this.nomcomisaria = nomcomisaria;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getNombredd() {
		return nombredd;
	}
	public void setNombredd(String nombredd) {
		this.nombredd = nombredd;
	}
	public String getNombrepp() {
		return nombrepp;
	}
	public void setNombrepp(String nombrepp) {
		this.nombrepp = nombrepp;
	}
	public String getNombredi() {
		return nombredi;
	}
	public void setNombredi(String nombredi) {
		this.nombredi = nombredi;
	}

	public Integer getVdeli() {
		return vdeli;
	}

	public void setVdeli(Integer vdeli) {
		this.vdeli = vdeli;
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
	public String getUbigeo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCodigoExportacion() {
		return this.id_n;
	}

	@Override
	public String getDescripcionExportacion() {
		return this.nomcomisaria;
	}
	
	public static String getFieldsExport(){
		return getFieldsExport(null);
	}
	public static String getFieldsExport(String alias){
		return getFieldsExport(alias, FragmentForm.OPCION.ONE);	
	}
	public static String getFieldsExport(String alias, FragmentForm.OPCION opcion){
		return getFieldsExport(alias, opcion, null);	
	}
	public static String getFieldsExport(String alias, String other){
		return getFieldsExport(alias, FragmentForm.OPCION.ONE, other);	
	}
	public static String getFieldsExport(String alias, FragmentForm.OPCION opcion, String other){
		alias = alias == null?"":alias+".";
		other = other == null?"":","+other;
		switch (opcion) {
			case ONE:
				return alias+"ID_N,"+alias+"CCDD,"+alias+"CCPP,"+alias+"CCDI,"+alias+"NOMCOMISARIA,"+
						alias+"AGREGADO"+other;
			case TWO:
				return alias+"ID_N,"+alias+"COD_REGION,"+alias+"REGION,"+alias+"VDELI,"+alias+"NOMCOMISARIA,"+
						alias+"AGREGADO,"+alias+"UBIGEO,"+alias+"DIRTEPOL,"+alias+"TIPO,"+alias+"COMISARIO,"+
						alias+"TIPOCPNP,"+alias+"NOMBRECP,"+alias+"COD_DEP_ASIG"+other;
			default:
				return alias+"ID_N";
		}
	}

	@Override
	public boolean getResFin() {
		return this.resfin != null;
	}

}
