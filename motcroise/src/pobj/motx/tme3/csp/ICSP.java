package pobj.motx.tme3.csp;

import java.util.List;

public interface ICSP {
	/**
	 * @return variables du probleme
	 * */
	public List<IVariable> getVars();
	
	/**
	 * @return un boolean pour dire si le probleme est encore satisfiable
	 * */
	public boolean isConsistent();
	
	/**
	 * Affecter une des variables du probleme
	 * @return le reste du probleme a resoudre apres cet essai
	 * */
	public ICSP assign(IVariable vi,String val);
}
