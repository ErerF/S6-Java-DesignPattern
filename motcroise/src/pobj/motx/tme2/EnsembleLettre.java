package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

public class EnsembleLettre {
	//sans doublons:hashtable?hashset?
	private List<Character> lettres;
	//private Dictionnaire  dic;
	
	public EnsembleLettre() {
		lettres=new ArrayList<Character>();
	}
	
	public EnsembleLettre(List<Character> l) {
		lettres=new ArrayList<Character>();
		lettres=l;
	}
	
	/*public EnsembleLettre(Dictionnaire d) {
		dic=d;
	}*/
	
	public void add(char c) {
		if(!lettres.contains(c))
			lettres.add(c);
	}
	
	public List<Character> getLettres(){
		return lettres;
	}
	
	public int size() {
		return lettres.size();
	}
	
	public boolean contains(char c) {
		return lettres.contains(c);
	}
	
	public EnsembleLettre intersection(EnsembleLettre el2) {
		List<Character> res=new ArrayList<Character>(lettres);
		//res=lettres;
		res.retainAll(el2.getLettres());
		return new EnsembleLettre(res);
	}
}
