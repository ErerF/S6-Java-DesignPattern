package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ClipImage {
	private Image image;
	private double left,top;

	public ClipImage(double left,double top,File filename) {
		this.left=left;
		this.top=top;
		image=new Image("file://"+filename.getAbsolutePath());
	}
	
	public void drawImage(GraphicsContext ctx) {
		ctx.drawImage(image,left,top);
	}
	
	public double getWidth() {
		return image.getWidth();
	}
	public double getHeight() {
		return image.getHeight();
	}
	
	public void setLeft(double l) {
		left=l;
	}
	public void setTop(double t) {
		top=t;
	}
	
	public boolean isSelected(double x,double y) {
		return x>=left && x<left+image.getWidth() && y>=top && y<top+image.getHeight();
	}
}
