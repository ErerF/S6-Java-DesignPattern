package pobj.expr;


public class VisitorSimplify implements IVisitor<Expression>{

	@Override
	public Expression visit(Constant c) {
		return c;
	}

	@Override
	public Expression visit(Add e) {
		if(e.getLeft().equals(new Constant(0))) {
			return e.getRight().accept(this);
		}
		else if(e.getRight().equals(new Constant(0))) {
			return e.getLeft().accept(this);
		}
		else {
			if(Question10.isConstant(e)) {
				return new Constant(Question10.evalConstantExpression(e));
			}
			else {
				return new Add(e.getLeft().accept(this), e.getRight().accept(this));
			}
		}
	}

	@Override
	public Expression visit(Mult e) {
		if(e.getLeft().equals(new Constant(0)) ||e.getRight().equals(new Constant(0))) {
			return new Constant(0);
		}
		if(e.getLeft().equals(new Constant(1))) {
			return e.getRight().accept(this);
		}
		else if(e.getRight().equals(new Constant(1))) {
			return e.getLeft().accept(this);
		}
		else {
			if(Question10.isConstant(e)) {
				return new Constant(Question10.evalConstantExpression(e));
			}
			else {
				return new Mult(e.getLeft().accept(this), e.getRight().accept(this));
			}
		}
	}

	@Override
	public Expression visit(Var v) {
		return v;
	}
	
}
