package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip{
	//private double left,right,top,bottom;
	//private Color color;
	
	public ClipRect(double left,double top,double right,double bottom,Color color) {
		super(left,top,right,bottom,color);
	}
	
	// Drawing
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillRect(getLeft(), getTop(), (getRight()-getLeft()),(getBottom()-getTop()));
	}
	
	// Geometry	
	/*public double getTop() {
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
	}*/
	public boolean isSelected(double x, double y) {
		//return x>=getLeft() && x<=getRight() && y>=getTop() && y<=getBottom();
		return super.isSelected(x, y);
	}

	// Colors
	/*public void setColor(Color c) {
		color=c;
	}
	public Color getColor() {
		return color;
	}*/

	// Cloning
	public Clip copy() {
		return new ClipRect(getLeft(),getTop(),getRight(),getBottom(),getColor());
	}
}
