package pobj.expr;


public abstract class BinOp {
	private final Expression left;
	private final Expression right;
	
	public BinOp(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public Expression getRight() {
		return right;
	}
}
