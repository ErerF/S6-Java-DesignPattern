package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup extends AbstractClip implements Composite{
	private List<Clip> clips;
	
	public ClipGroup() {
		super();
		clips=new ArrayList<>();
	}
	public ClipGroup(List<Clip> c) {
		super();
		clips=c;
		
		double left=this.getLeft(),right=this.getRight(),top=this.getTop(),bottom=this.getBottom();
		for(Clip cl:clips) {
			if(left>cl.getLeft())
				left=cl.getLeft();
			if(right<cl.getRight())
				right=cl.getRight();
			if(top>cl.getTop())
				top=cl.getTop();
			if(bottom<cl.getBottom())
				bottom=cl.getBottom();			
		}
		super.setGeometry(left, top, right, bottom);
	}
	public void setGeometry(double left,double top,double right,double bottom) {
		this.move(left-this.getLeft(),top-this.getTop());
	}
	public void move(double x, double y) {
		for(Clip c:clips) {
			c.move(x, y);
		}
		super.setGeometry(this.getLeft()+x, this.getTop()+y, this.getRight()+x, this.getBottom()+y);
	}
	public boolean isSelected(double x, double y) {
		if(super.isSelected(x, y)) {
			boolean res=false;
			int i=0;
			while(i<clips.size()&&!res) {
				res=res||clips.get(i).isSelected(x, y);
				i++;
			}
			return res;
		}
		else
			return false;
	}
	public Clip copy() {
		List<Clip> c=new ArrayList<>();
		for(Clip cl:clips) {
			c.add(cl.copy());
		}
		return new ClipGroup(c);
	}
	public void draw(GraphicsContext ctx) {
		for(Clip c:clips) {
			c.draw(ctx);
		}
	}

	public List<Clip> getClips(){
		return clips;
	}	
	public void addClip(Clip toAdd) {
		clips.add(toAdd);
		double left=this.getLeft(),right=this.getRight(),top=this.getTop(),bottom=this.getBottom();
		if(left>toAdd.getLeft())
			left=toAdd.getLeft();
		if(right<toAdd.getRight())
			right=toAdd.getRight();
		if(top>toAdd.getTop())
			top=toAdd.getTop();
		if(bottom<toAdd.getBottom())
			bottom=toAdd.getBottom();
		super.setGeometry(left, top, right, bottom);
	}	
	public void removeClip(Clip toRemove) {
		clips.remove(toRemove);		
		double left=Integer.MAX_VALUE,right=Integer.MIN_VALUE,top=Integer.MAX_VALUE,bottom=Integer.MIN_VALUE;

		for(Clip c:clips) {
			if(left>c.getLeft())
				left=c.getLeft();
			if(right<c.getRight())
				right=c.getRight();
			if(top>c.getTop())
				top=c.getTop();
			if(bottom<c.getBottom())
				bottom=c.getBottom();
		}
		super.setGeometry(left, top, right, bottom);
	}
}
