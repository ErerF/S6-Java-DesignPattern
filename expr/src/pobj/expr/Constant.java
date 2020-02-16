package pobj.expr;


public class Constant implements Expression{
	
	private int constant;
	
	public Constant(int constant) {
		this.constant = constant;
	}
	public Constant() {
		constant = 0;
	}
	
	public int getValue() {
		return constant;
	}
	
	public boolean equals(Object o) {
		if(this==o) {
			return true;
		}
		if(!(o instanceof Constant)) {
			return false;
		}
		Constant c = (Constant) o;
		return c.constant == this.constant;
	}
	
	public String toString() {
		return ""+constant;
	}
	
	@Override
	public int eval() {
		return getValue();
	}
	@Override
	public <T> T accept(IVisitor<T> iVisitor) {
		return iVisitor.visit(this);
	}
}
