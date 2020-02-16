package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;
import pobj.motx.tme1.*;

public class GrillePotentiel {
	private GrillePlaces gp;
	private Dictionnaire dic;
	private List<Dictionnaire> motsPot;
	private List<IContrainte> contraintes;
	
	public GrillePotentiel(GrillePlaces grille,Dictionnaire dicoComplet) {
		gp=grille;
		dic=dicoComplet;
		motsPot=new ArrayList<Dictionnaire>();
		contraintes=new ArrayList<IContrainte>();
		
		/**filtrer le dictionnaire par longueur**/
		for(int i=0;i<gp.getPlaces().size();i++) {
			int longueur=gp.getPlaces().get(i).size();
			Dictionnaire dicAdd=dic.copy();
			if(dicAdd.filtreLongueur(longueur)>0)
				dicAdd.viderCache();
			motsPot.add(dicAdd);
		}
		
		/**filtrer le dictionnaire par des cases remplies**/
		//pour chaque emplacement
		for(int i=0;i<motsPot.size();i++) {
			Emplacement e= gp.getPlaces().get(i);
			//pour chaque case
			for(int j=0;j<e.size();j++){
				//si la case est ni vide ni pleine <=> il contient une lettre
				if(!(e.getCase(j).isVide()) && !(e.getCase(j).isPleine())) {
					if(motsPot.get(i).filtreParLettre(e.getCase(j).getChar(), j)>0)
						motsPot.get(i).viderCache();
				}
			}
		}
		
		/**chercher les croisements**/		
		//diviser les emplacements en 2 parties:
		//	emplacements horizontaux & emplacements verticaux
		List<Emplacement> eHoriz=new ArrayList<Emplacement>();
		List<Emplacement> eVert=new ArrayList<Emplacement>();
		for(Emplacement e:gp.getPlaces()) {
			if(e.isHorizontal())
				eHoriz.add(e);
			else
				eVert.add(e);
		}
		for(Emplacement eh: eHoriz) {
			int l=eh.getCase(0).getLig();
			for(Emplacement ev:eVert) {
				int c=ev.getCase(0).getCol();
				//s'il y a un croisement
				if(l>=ev.getCase(0).getLig() && l<=ev.getCase(ev.size()-1).getLig() 
						&& c>=eh.getCase(0).getCol() && c<=eh.getCase(eh.size()-1).getCol()) {
					//si la case contient pas une lettre
					if(eh.getCase(c-eh.getCase(0).getCol()).isVide()) {
						contraintes.add(new CroixContrainte(gp.getPlaces().indexOf(eh),(c-eh.getCase(0).getCol()),
								gp.getPlaces().indexOf(ev),(l-ev.getCase(0).getLig()),this));
					}					
				}
			}			
		}
		
		this.propage();
	}
	
	//Constructeur-Bonus1
	public GrillePotentiel(GrillePlaces grille,Dictionnaire dicoComplet,List<Dictionnaire> dicActuel) {
		gp=grille;
		dic=dicoComplet;
		motsPot=new ArrayList<Dictionnaire>();
		contraintes=new ArrayList<IContrainte>();
		
		/**filtrer le dictionnaire par longueur**/
		for(int i=0;i<gp.getPlaces().size();i++) {
			int longueur=gp.getPlaces().get(i).size();
			Dictionnaire dicAdd=dicActuel.get(i).copy();
			if(dicAdd.filtreLongueur(longueur)>0)
				dicAdd.viderCache();
			motsPot.add(dicAdd);
		}
		
		/**filtrer le dictionnaire par des cases remplies**/
		//pour chaque emplacement
		for(int i=0;i<motsPot.size();i++) {
			Emplacement e= gp.getPlaces().get(i);
			//pour chaque case
			for(int j=0;j<e.size();j++){
				//si la case est ni vide ni pleine <=> il contient une lettre
				if(!(e.getCase(j).isVide()) && !(e.getCase(j).isPleine())) {
					if(motsPot.get(i).filtreParLettre(e.getCase(j).getChar(), j)>0)
						motsPot.get(i).viderCache();
				}
			}
		}
		
		/**chercher les croisements**/		
		//diviser les emplacements en 2 parties:
		//	emplacements horizontaux & emplacements verticaux
		List<Emplacement> eHoriz=new ArrayList<Emplacement>();
		List<Emplacement> eVert=new ArrayList<Emplacement>();
		for(Emplacement e:gp.getPlaces()) {
			if(e.isHorizontal())
				eHoriz.add(e);
			else
				eVert.add(e);
		}
		for(Emplacement eh: eHoriz) {
			int l=eh.getCase(0).getLig();
			for(Emplacement ev:eVert) {
				int c=ev.getCase(0).getCol();
				//s'il y a un croisement
				if(l>=ev.getCase(0).getLig() && l<=ev.getCase(ev.size()-1).getLig() 
						&& c>=eh.getCase(0).getCol() && c<=eh.getCase(eh.size()-1).getCol()) {
					//si la case contient pas une lettre
					if(eh.getCase(c-eh.getCase(0).getCol()).isVide()) {
						contraintes.add(new CroixContrainte(gp.getPlaces().indexOf(eh),(c-eh.getCase(0).getCol()),
								gp.getPlaces().indexOf(ev),(l-ev.getCase(0).getLig()),this));
					}					
				}
			}			
		}
		
		this.propage();
	}
	
	public GrillePlaces getGrillePlaces() {
		return gp;
	}

	public List<Dictionnaire> getMotsPot(){
		return motsPot;
	}
	
	public List<IContrainte> getContraintes(){
		return contraintes;
	}
	
	public boolean isDead() {
		boolean ok=true;
		int i=0;
		while(ok && i<motsPot.size()) {
			if(motsPot.get(i).size()==0)
				ok=false;
			i++;
		}
		return !ok;
	}

	//version basique
	/*public GrillePotentiel fixer(int m,String soluce) {
		return new GrillePotentiel(gp.fixer(m, soluce),dic);
	}*/
	
	//Bonus 1
	public GrillePotentiel fixer(int m,String soluce) {
		return new GrillePotentiel(gp.fixer(m, soluce),dic,motsPot);
	}

	private boolean propage() {		
		while(true) {
			int cpt=0;
			for(IContrainte c:contraintes) {
				cpt+=((CroixContrainte)c).reduce(this);
			}
			if(isDead())
				return false;
			if(cpt==0)
				return true;
		}		
	}

	public String toString() {
		System.out.println("in GrillePotentiel");
		return gp.getPlaces().toString();
	}
}
