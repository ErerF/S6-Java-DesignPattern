package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour representer un emplacement
 */
public class Emplacement {
	/** liste des cases de cet emplacement */
	private List<Case> lettres;
	/** taille de cet emplacement */
	private int size;
	
	//constructeur
	/**
	 * Construit un emplacement en sachant sa taille
	 * @param size taille de cet emplacement
	 */
	public Emplacement(int size) {
		this.size = size;
		this.lettres = new ArrayList<Case>();
	}
	
	/**
	 * Construit un emplacement en sachant la liste des cases
	 * @param l la liste des cases vont etre enregistrees dans cet emplacement
	 */
	public Emplacement(List<Case> l) {		
		lettres = new ArrayList<Case>(l);
		size=lettres.size();
	}
	
	public Case getCase(int i) {
		return lettres.get(i);
	}
	
	/*public void setMot(String mot) {
		if(size!=mot.length())
			throw new IllegalArgumentException();
		for(int i=0;i<size;i++) {
			lettres.get(i).setChar(mot.charAt(i));
		}
	}*/
	
	/**
	 * Accede a la taille de l'emplacement
	 * @return la taille de l'emplacement
	 * */
	public int size() {
		return size;
	}
	
	/**
	 * Verifie si cet emplacement est horizontal
	 * @return vrai si cet emplacement est horizontal
	 * */
	public boolean isHorizontal() {
		return lettres.get(0).getLig()==lettres.get(1).getLig();
	}
	
	public boolean hasCaseVide() {
		for(Case c:lettres) {
			if(c.isVide())
				return true;
		}
		return false;
	}
	
	/**
	 * Retourne le contenu d'un emplacement
	 * @return le contenu d'un emplacement
	 * */
	public String toString() {
		StringBuilder res=new StringBuilder();
		for(Case c:lettres) {
			res.append(c.getChar());
		}
		return res.toString();
	}
}
