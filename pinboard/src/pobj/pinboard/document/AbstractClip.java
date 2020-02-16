package pobj.pinboard.document;

import javafx.scene.paint.Color;

//class abstract:interdit d'instancier
//				pas forcement besoin de methode abstract
//par contre, une methode abstract doit dans une classe abstract
public abstract class AbstractClip implements Clip{
	private double left,right,top,bottom;
	private Color color;
	
	public AbstractClip() {
		left=Integer.MAX_VALUE;
		right=Integer.MIN_VALUE;
		top=Integer.MAX_VALUE;
		bottom=Integer.MIN_VALUE;
	}
	
	public AbstractClip(double left,double top,double right,double bottom,Color color) {
		//if(left==right || top==bottom)
			//throw new IllegalArgumentException();
		this.left=left;
		this.right=right;
		this.top=top;
		this.bottom=bottom;
		this.color=color;
	}
	
	// Geometry	
	public double getTop() {
		return top;
	}
	public double getLeft() {
		return left;
	}
	public double getBottom() {
		return bottom;
	}
	public double getRight() {
		return right;
	}
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left=left;
		this.right=right;
		this.top=top;
		this.bottom=bottom;
	}
	public void move(double x, double y) {
		left+=x;
		right+=x;
		top+=y;
		bottom+=y;
	}
	
	// Colors
	public void setColor(Color c) {
		color=c;
	}
	public Color getColor() {
		return color;
	}
	
	public boolean isSelected(double x, double y) {
		return x>=getLeft() && x<=getRight() && y>=getTop() && y<=getBottom();
	}
}
