package pobj.motx.tme2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Un ensemble de mots.
 */
public class Dictionnaire {
	// stockage des mots
	private List<String> mots = new ArrayList<String>();	
	private EnsembleLettre[] cache=null;
	
	public Dictionnaire() {}
	
	public Dictionnaire(List<String> mots, EnsembleLettre[] cache) {
		this.mots=mots;
		//this.cache=new EnsembleLettre[cache.length];
		this.cache=cache;
	}

	public void viderCache() {
		cache=null;
	}
	
	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	public List<String> getMots(){
		return mots;
	}
	
	public EnsembleLettre charAt(int index) {
		if(mots.isEmpty())
			return new EnsembleLettre();
		if(cache==null) {
			cache=new EnsembleLettre[mots.get(0).length()];
		}
		if(cache[index]==null) {
			EnsembleLettre el=new EnsembleLettre();
			for(int i=0;i<mots.size();i++) {
				el.add(mots.get(i).charAt(index));
			}
			cache[index]=el;
			return cache[index];
		}
		else
			return cache[index];
	}
	
	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		//Dictionnaire copy = new Dictionnaire();
		//copy.mots.addAll(mots);
		Dictionnaire copy=new Dictionnaire(mots,cache);
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}
	
	public int filtreParLettre(char c,int i) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.charAt(i) == c) {
				cible.add(mot);
			}
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}
	
	public static Dictionnaire loadDictionnaire(String path) {
		Dictionnaire dic=new Dictionnaire();
		try(BufferedReader br=new BufferedReader(new FileReader(path))){
			for(String line=br.readLine();line!=null;line=br.readLine()) {
				dic.add(line);
			}			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return dic;
	}

	public EnsembleLettre calculEnsLettre(int i) {
		EnsembleLettre el=new EnsembleLettre();
		for(String m: mots) {
			el.add(m.charAt(i));
		}
		return el;
	}
	
	public int filtreParEnsLettre(int i,EnsembleLettre el) {
		List<String> cible = new ArrayList<String>();
		int cpt=0;
		for (String mot : mots) {
			if (el.contains(mot.charAt(i))) {
				cible.add(mot);
			}
			else
				cpt++;
		}
		this.mots = cible;
		return cpt;
	}
	
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}
}
