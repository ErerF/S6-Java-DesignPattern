package pobj.motx.tme1;

/**
 * Classe pour representer une case d'une grille
 * 	-vide:' '
 * 	-pleine:'*'
 *	-26 lettres de l'alphabet en minuscule
 */

public class Case {
	/** location de la case */
	private int ligne;
	private int colonne;
	/** le contenu de cette case */
	private char contenu;
	
	//constructeur
	/**
	 * Construit une case en sachant sa position et son contenu
	 * @param lig le numero de ligne de la case
	 * @param col le numero de colonne de la case
	 * @param c le contenu enregistre dans la case
	 * */
	public Case(int lig, int col,char c) {
		ligne=lig;
		colonne=col;
		contenu=c;
	}
	
	//getter
	/**
	 * Accede au numero de ligne de cette case
	 * @return numero de ligne de la case
	 * */
	public int getLig() {
		return ligne;
	}
	
	/**
	 * Accede au numero de colonne de cette case
	 * @return numero de colonne de la case
	 * */
	public int getCol() {
		return colonne;
	}
	
	/**
	 * Accede au contenu de cette case
	 * @return contenu de la case
	 * */
	public char getChar() {
		return contenu;
	}
	
	//setter
	/**
	 * Definit le contenu de cette case
	 * @param le char a enregistrer dans cette case
	 * */
	public void setChar(char c) {
		contenu=c;
	}
	
	//methodes de traitement
	/**
	 * Verifie si la case est vide
	 * @return vrai si la case est vide, cad le contenu est ' '
	 * */
	public boolean isVide() {
		return contenu==' ';
	}
	
	/**
	 * Verifie si la case est pleine
	 * @return vrai si la case est pleine, cad le contenu est '*'
	 * */
	public boolean isPleine() {
		return contenu=='*';
	}
}
