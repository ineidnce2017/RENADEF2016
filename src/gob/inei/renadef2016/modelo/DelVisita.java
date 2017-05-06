package gob.inei.renadef2016.modelo;

import java.io.Serializable;

import gob.inei.dnce.components.Entity;
import gob.inei.dnce.interfaces.IDetailEntityComponent;

public class DelVisita extends Entity implements IDetailEntityComponent, Serializable{
	private static final long serialVersionUID = 1L;
	public String id_n = null;	
	public Integer iii_1 = null;
	public String iii_2_d = null;	
	public String iii_2_m = null;	
	public Integer iii_2_a = null;
	public String iii_3a_ih = null;
	public String iii_3a_im = null;
//	public String gpslatitud_ini = null;
//	public String gpslongitud_ini = null;	
	public String iii_3b_fh = null;
	public String iii_3b_fm = null;
//	public String gpslatitud_fin = null;
//	public String gpslongitud_fin = null;
	public String iii_4a_d = null;
	public String iii_4a_m = null;
	public Integer iii_4a_a = null;
	public String iii_4b_h = null;	
	public String iii_4b_m = null;
	public Integer iii_5 = null;
	public String iii_5_o = null;
	public String iii_6_d = null;
	public String iii_6_m = null;
	public String iii_6_a = null;
	public String iii_7a_h = null;
	public String iii_7a_m = null;
	public String iii_7b_h = null;
	public String iii_7b_m = null;
	public Integer iii_8 = null;
	public String iii_8_o = null;
	
	@Override
	public void cleanEntity() {
		// TODO Auto-generated method stub
		
	}
	
	public String getFechaE(){
		return (iii_2_d==null?"__":iii_2_d)+"/"+
				(iii_2_m==null?"__":iii_2_m)+"/"+
				(iii_2_a==null?"__":iii_2_a);
	}
	
	public String getHoraE_from(){
		return (iii_3a_ih==null?"__":iii_3a_ih)+":"+
				(iii_3a_im==null?"__":iii_3a_im);
	}
	
	public String getHoraE_to(){
		return (iii_3b_fh==null?"__":iii_3b_fh)+":"+
				(iii_3b_fm==null?"__":iii_3b_fm);
	}
	
	public String getFechaEPV(){
		return (iii_4a_d==null?"__":iii_4a_d)+"/"+
				(iii_4a_m==null?"__":iii_4a_m)+"/"+
				(iii_4a_a==null?"__":iii_4a_a);
	}
	
	public String getHoraE_pv(){
		return (iii_4b_h==null?"__":iii_4b_h)+":"+
				(iii_4b_m==null?"__":iii_4b_m);
	}
	
	public String getFechaS(){
		return (iii_6_d==null?"__":iii_6_d)+"/"+
				(iii_6_m==null?"__":iii_6_m)+"/"+
				(iii_6_a==null?"__":iii_6_a);
	}
	
	public String getHoraS_from(){
		return (iii_7a_h==null?"__":iii_7a_h)+":"+
				(iii_7a_m==null?"__":iii_7a_m);
	}
	
	public String getHoraS_to(){
		return (iii_7b_h==null?"__":iii_7b_h)+":"+
				(iii_7b_m==null?"__":iii_7b_m);
	}

	@Override
	public boolean isTitle() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getPks() {
		return "ID_N, III_1";
	}
}
