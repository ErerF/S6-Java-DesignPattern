package pobj.motx.tme2;

public class CroixContrainte implements IContrainte{
	private int m1;
	private int c1;
	private int m2;
	private int c2;
	private GrillePotentiel gp;
	
	//??????????vraiment besoin de 2 constructeurs?
	public CroixContrainte(int m1,int c1,int m2,int c2) {
		this.m1=m1;
		this.c1=c1;
		this.m2=m2;
		this.c2=c2;
		//reduce();
	}
	
	public CroixContrainte(int m1,int c1,int m2,int c2,GrillePotentiel gp) {
		this.m1=m1;
		this.c1=c1;
		this.m2=m2;
		this.c2=c2;
		this.gp=gp;
		//reduce(gp);
	}

	public int getM1() {return m1;}
	public int getC1() {return c1;}
	public int getM2() {return m2;}
	public int getC2() {return c2;}

	//version basique
	/*public int reduce(GrillePotentiel grille) {
		//calculer el1
		EnsembleLettre el1=new EnsembleLettre();
		Dictionnaire d1=grille.getMotsPot().get(m1);		
		for(int i=0;i<d1.size();i++) {			
			el1.add(d1.get(i).charAt(c1));
		}
		
		//calculer el2
		EnsembleLettre el2=new EnsembleLettre();
		Dictionnaire d2=grille.getMotsPot().get(m2);
		for(int i=0;i<d2.size();i++) {			
			el2.add(d2.get(i).charAt(c2));
		}
		
		EnsembleLettre s=el1.intersection(el2);
		
		//filtrer d1
		int cpt1=0;		
		if(el1.size()>s.size()) {
			cpt1=d1.filtreParEnsLettre(c1, s);
		}
		
		//filtrer d2
		int cpt2=0;		
		if(el2.size()>s.size()) {
			cpt2=d2.filtreParEnsLettre(c2,s);
		}
	
		return cpt1+cpt2;
	}*/

	//version-Bonus1
	public int reduce(GrillePotentiel grille) {		
		//recuperer el1		
		Dictionnaire d1=grille.getMotsPot().get(m1);	
		EnsembleLettre el1=d1.charAt(c1);
		
		
		//recuperer el2		
		Dictionnaire d2=grille.getMotsPot().get(m2);
		EnsembleLettre el2=d2.charAt(c2);
		
		EnsembleLettre s=el1.intersection(el2);
		
		//filtrer d1
		int cpt1=0;		
		if(el1.size()>s.size()) {
			cpt1=d1.filtreParEnsLettre(c1, s);
			if(cpt1>0)
				d1.viderCache();
		}
		
		//filtrer d2
		int cpt2=0;		
		if(el2.size()>s.size()) {
			cpt2=d2.filtreParEnsLettre(c2,s);
			if(cpt2>0)
				d2.viderCache();
		}
	
		return cpt1+cpt2;
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof CroixContrainte)) {
			return false;
		}
		
		CroixContrainte c=(CroixContrainte)other;
		
		return 		(m1==c.getM1() && c1==c.getC1() && m2==c.getM2() && c2==c.getC2())
				||	(m1==c.getM2() && c1==c.getC2() && m2==c.getM1() && c2==c.getC1());
	}
}