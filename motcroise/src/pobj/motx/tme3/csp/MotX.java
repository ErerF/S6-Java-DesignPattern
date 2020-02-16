package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP{
	private GrillePotentiel gp;
	private List<IVariable> vars;
	
	public MotX(GrillePotentiel gp) {
		this.gp=gp;
		
		vars=new ArrayList<IVariable>();
		for(int i=0;i<gp.getMotsPot().size();i++) {
			if(gp.getGrillePlaces().getPlaces().get(i).hasCaseVide()) {
				vars.add(new DicoVariable(i,gp));
			}
		}		
	}
	
	public List<IVariable> getVars(){
		return vars;
	}
	
	public boolean isConsistent() {
		return !gp.isDead();
	}
	
	public ICSP assign(IVariable vi,String val) {
		////Transtypage: instanceof+cast
		//1)instanceof:
		if(!(vi instanceof IVariable))
			throw new IllegalArgumentException();	
		//2)cast:
		DicoVariable dv=(DicoVariable)vi;
		
		GrillePotentiel gpNew=gp.fixer(dv.getIndex(),val);
		//vars.remove(dv.getIndex());
		vars.remove(dv);
		MotX res=new MotX(gpNew);
		
		return res;
	}
}
