package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {
	private List<Clip> selection=new ArrayList<>();
	
	public void select(Board board,double x,double y) {
		selection.clear();
		for(int i=board.getContents().size()-1;i>=0;i--) {
			Clip c=board.getContents().get(i);
			if(c.isSelected(x, y)) {
				selection.add(c);
				break;
			}
		}
	}
	
	public void toogleSelect(Board board,double x,double y) {
		for(int i=board.getContents().size()-1;i>=0;i--) {
			Clip c=board.getContents().get(i);
			if(c.isSelected(x, y)) {
				if(selection.contains(c))
					selection.remove(c);
				else
					selection.add(c);
				
				break;
			}
		}
	}
	
	public void clear() {
		selection.clear();
	}
	
	public List<Clip> getContents(){
		return selection;
	}
	
	public void drawFeedback(GraphicsContext gc) {
		double left=Integer.MAX_VALUE,right=Integer.MIN_VALUE,top=Integer.MAX_VALUE,bottom=Integer.MIN_VALUE;
		for(Clip c: selection) {
			if(c.getLeft()<left)
				left=c.getLeft();
			if(c.getRight()>right)
				right=c.getRight();
			if(c.getBottom()>bottom)
				bottom=c.getBottom();
			if(c.getTop()<top)
				top=c.getTop();
		}
		gc.clearRect(left, top,(right-left),(bottom-top));
		gc.strokeRect(left, top,(right-left),(bottom-top));
	}
}
