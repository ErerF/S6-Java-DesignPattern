package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour representer des emplacements d'une grille
 */
public class GrillePlaces {
	/** liste des emplacements de la grille */
	private Grille grille;
	private List<Emplacement> places;
	
	//constructeur
	/**
	 * Construit une liste des emplacements de la grille
	 * @param grille la grille a explorer
	 */
	public GrillePlaces(Grille grille) {
		this.grille=grille;
		this.places = new ArrayList<Emplacement>();
		
		//parcourir les lignes
		for(int i=0;i<grille.nbLig();i++) {
			this.cherchePlaces(this.getLig(i, grille));
		}
		
		//parcourir les colonnes
		for(int i=0;i<grille.nbCol();i++) {
			this.cherchePlaces(this.getCol(i, grille));
		}
	}
	
	/**********les methodes aident le constructeur**********/
	
	/**
	 * Accede aux cases qui constituent une ligne donnee
	 * @return la liste des cases de la ligne donnee
	 * */
	private List<Case> getLig(int lig,Grille grille){
		if(lig>=grille.nbLig()) {
			throw new IllegalArgumentException();
		}
		List<Case> res=new ArrayList<Case>();
		for(int i=0;i<grille.nbCol();i++) {
			res.add(grille.getCase(lig,i));
		}
		return res;
	}
	
	/**
	 * Accede aux cases qui constituent un colonne donne
	 * @return la liste des cases du colonne donne
	 * */
	private List<Case> getCol(int col,Grille grille){
		if(col>=grille.nbCol()) {
			throw new IllegalArgumentException();
		}
		List<Case> res=new ArrayList<Case>();
		for(int i=0;i<grille.nbLig();i++) {
			res.add(grille.getCase(i,col));
		}
		return res;
	}	
	
	/**
	 * Completer la liste des emplacements de la grille
	 * @param cases la liste des cases a detecter
	 */
	private void cherchePlaces(List<Case> cases) {
		List<Case> l=new ArrayList<Case>();
		for(Case c : cases) {
			if(!c.isPleine()) {
				l.add(c);
			}
			else {
				if(l.size()>1) {
					places.add(new Emplacement(l));
				}
				l.clear();
			}
		}
		if(l.size()>1) {
			places.add(new Emplacement(l));
		}
	}
	
	/******************************************/	
	/**
	 * Accede a la liste des emplacements de la grille
	 * @return la liste des emplacements
	 * */
	public List<Emplacement> getPlaces(){
		return places;
	}
	
	/**
	 * Accede au nombre des emplacements horizontaux
	 * @return le nombre des emplacements horizontaux
	 * */
	public int getNbHorizontal() {
		int i=0;
		while(i<places.size()&&places.get(i).isHorizontal())
			i++;
		return i;
	}
	
	public GrillePlaces fixer(int m, String soluce){
		//Grille g=grille.copy();	
		Grille g=grille;
		for(int i=0;i<places.get(m).size();i++) {
			Case c=places.get(m).getCase(i);
			g.getCase(c.getLig(),c.getCol()).setChar(soluce.charAt(i));;
		}
		
		return new GrillePlaces(g);
	}
	
	/**
	 * Retourne une description des emplacements de la grille
	 * @return la description des emplacements de la grille
	 * */
	public String toString() {
		StringBuilder res=new StringBuilder();
		for(Emplacement e : places) {
			res.append(e.toString()+'\n');
		}		
		return res.toString();
	}
}
