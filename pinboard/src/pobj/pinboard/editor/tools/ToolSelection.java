package pobj.pinboard.editor.tools;

import java.awt.print.Printable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool{
	private double x,y;
	private double xI,yI;
	public void press(EditorInterface i, MouseEvent e) {
		xI=e.getX();
		yI=e.getY();
		x=e.getX();
		y=e.getY();
		if(e.isShiftDown()) {
			i.getSelection().toogleSelect(i.getBoard(),x,y);
		}
		else {
			i.getSelection().select(i.getBoard(), x, y);
		}
	}
	public void drag(EditorInterface i, MouseEvent e) {
		double a=e.getX(),b=e.getY();
		for(Clip c:i.getSelection().getContents()) {
			c.move(a-x, b-y);
		}
		x=a;
		y=b;
	}
	public void release(EditorInterface i, MouseEvent e) {
		double a=e.getX(),b=e.getY();
		for(Clip c:i.getSelection().getContents()) {
			c.move(-(a-xI), -(b-yI));
			Command cmdMove = new CommandMove(i,c,a-xI,b-yI);
			System.out.println((a-xI)+" "+(b-yI));
			System.out.println(c.getTop()+ " " +c.getLeft()+ " " +c.getBottom()+ " " +c.getRight());
			cmdMove.execute();
			System.out.println(c.getTop()+ " " +c.getLeft()+ " " +c.getBottom()+ " " +c.getRight());
			i.getUndoStack().addCommand(cmdMove);
			//c.move(a-x, b-y);
		}
		//i.getBoard().removeClip(i.getSelection().getContents());
		//i.getBoard().addClip(i.getSelection().getContents());
	}
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getBoard().draw(gc);
		i.getSelection().drawFeedback(gc);
		for(Clip c:i.getSelection().getContents()) {
			if(c instanceof ClipRect) {
				gc.clearRect(c.getLeft(), c.getTop(),(c.getRight()-c.getLeft()),(c.getBottom()-c.getTop()));
				gc.strokeRect(c.getLeft(), c.getTop(),(c.getRight()-c.getLeft()),(c.getBottom()-c.getTop()));
			}
			else if(c instanceof ClipEllipse) {
				gc.clearRect(c.getLeft(), c.getTop(),(c.getRight()-c.getLeft()),(c.getBottom()-c.getTop()));
				gc.strokeOval(c.getLeft(), c.getTop(),(c.getRight()-c.getLeft()),(c.getBottom()-c.getTop()));
			}
		}
	}
	public String getName(EditorInterface editor) {
		return "Selection tool";
	}

}
