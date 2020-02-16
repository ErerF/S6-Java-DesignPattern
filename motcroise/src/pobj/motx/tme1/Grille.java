package pobj.motx.tme1;
/**
 * Classe pour representer une grille de Case
 */
public class Grille {
	/** les cases de la grille */
	private Case[][] m;
	
	//constructeur
	/**
	 * Construit une grille en sachant sa taille
	 * @param hauteur nombre de ligne de la grille
	 * @param largeur nombre de colonne de la grille
	 */
	public Grille(int hauteur, int largeur) {
		m=new Case[hauteur][largeur];
		
		for(int i=0;i<hauteur;i++) {
			for(int j=0;j<largeur;j++) {
				m[i][j]=new Case(i,j,' ');
			}
		}
	}
	
	/**
	 * Construit une grille en sachant sa taille
	 * @param hauteur nombre de ligne de la grille
	 * @param largeur nombre de colonne de la grille
	 * @param mOri le tableau 2D de Case a recopier
	 */
	public Grille(int hauteur, int largeur,Case[][] mOri) {
		m=new Case[hauteur][largeur];
		for(int i=0;i<hauteur;i++) {
			for(int j=0;j<largeur;j++) {
				m[i][j]=new Case(i,j,mOri[i][j].getChar());
			}
		}
	}
	
	//getter
	/**
	 * Accede a certaine case de la grille
	 * @param lig numero de la ligne de la case a obtenir
	 * @param col numero du colonne de la case a obtenir
	 * @return la case a la position [lig][col]
	 * */
	public Case getCase(int lig,int col) {
		return m[lig][col];
	}
	
	/**
	 * Accede au nombre de ligne de la grille
	 * @return le nombre de ligne de la grille
	 * */
	public int nbLig() {
		return m.length;
	}
	
	/**
	 * Accede au nombre de colonne de la grille
	 * @return le nombre de colonne de la grille
	 * */
	public int nbCol() {
		return m[0].length;
	}
	
	/**
	 * Fait une copie de cette grille
	 * @return la copie de cette grille
	 * */
	public Grille copy() {
		return new Grille(m.length, m[0].length,this.m);
	}
	
	//methode standard
	/**
	 * Retourne une description de la grille
	 * @return la description de la grille
	 * */
	public String toString() {
		System.out.println("in Grille");
		StringBuilder res=new StringBuilder();
		res.append("La grille:\n"+ GrilleLoader.serialize(this, false));
		return res.toString();
	}
}
