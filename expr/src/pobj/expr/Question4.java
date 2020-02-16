package pobj.expr;

public class Question4 {
	
	public static Expression e1() {
		Add a = new Add(new Constant(2), new Constant(3));
		return new Mult(a, new Constant(4));
	}
	
	public static Expression e2() {
		Add left = new Add(new Var("x"), new Constant(3));
		Add right = new Add(new Var("x"), new Constant(4));
		return new Mult(left,right);
	}
	
	public static Expression e3() {
		Add left = new Add(new Var("x"), new Constant(10));
		Add right = new Add(new Var("y"), new Constant(-8));
		return new Mult(left,right);
	}
}
