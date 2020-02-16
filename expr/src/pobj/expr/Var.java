package pobj.expr;

public class Var implements Expression{
	private final String nom;
	
	public Var(String nom) {
		this.nom = nom;
	}
	
	public String getName() {
		return nom;
	}
	
	public boolean equals(Object o) {
		if(this==o) {
			return true;
		}
		if(!(o instanceof Var)) {
			return false;
		}
		Var v = (Var) o;
		return v.nom == this.nom;
	}
	
	public String toString() {
		return nom;
	}

	@Override
	public int eval() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T accept(IVisitor<T> iVisitor) {
		return iVisitor.visit(this);
	}
}
