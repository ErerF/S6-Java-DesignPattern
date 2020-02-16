package pobj.expr;


public class Question10 {
	public static boolean isConstant(Expression e) { 
		if (e instanceof Constant) {
			return true;
		}
		else {
			if(e instanceof Add) {
				return isConstant(((Add)e).getLeft()) && isConstant(((Add)e).getRight());
			}
			else if(e instanceof Mult) {
				return isConstant(((Mult)e).getLeft()) && isConstant(((Mult)e).getRight());
			}
			else {
				return false;
			}
		}
	} 
	
	public static int evalConstantExpression (Expression e) { 
		if (e instanceof Constant) {
			return e.eval();
		}
		else {
			if(e instanceof Add) {
				return evalConstantExpression(((Add)e).getLeft()) + evalConstantExpression(((Add)e).getRight());
			}else {
				return evalConstantExpression(((Mult)e).getLeft()) * evalConstantExpression(((Mult)e).getRight());
			}
		}
	}
}
