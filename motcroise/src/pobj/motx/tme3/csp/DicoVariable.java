package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable{
	//index:l'emplacement du mot correspondant
	private int index;
	private GrillePotentiel gp;
	
	public DicoVariable(int index,GrillePotentiel gp) {
		this.index=index;
		this.gp=gp;
	}
	
	public int getIndex() {
		return index;
	}
	
	public List<String> getDomain(){
		return gp.getMotsPot().get(index).getMots();
	}
	
	public String toString() {
		return "DicoVariable: index="+index;
	}
}
