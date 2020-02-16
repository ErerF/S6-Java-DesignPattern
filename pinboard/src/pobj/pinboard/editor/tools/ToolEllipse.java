package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolEllipse implements Tool{
	private boolean painting=false;
	private double xf,yf;
	private double x2,y2;

	public void press(EditorInterface i, MouseEvent e) {
		xf=e.getX();
		yf=e.getY();
		painting=true;
	}
	public void drag(EditorInterface i, MouseEvent e) {
		x2=e.getX();
		y2=e.getY();
		
	}
	public void release(EditorInterface i, MouseEvent e) {
		x2=e.getX();
		y2=e.getY();
		if(xf>x2) {
			double temp=xf;
			xf=x2;
			x2=temp;
		}
		if(yf>y2) {
			double temp=yf;
			yf=y2;
			y2=temp;
		}
		Command cmdAdd = new CommandAdd(i,new ClipEllipse(xf,yf,x2,y2, Color.BLUE));
		cmdAdd.execute();
		i.getUndoStack().addCommand(cmdAdd);
		//i.getBoard().addClip(new ClipEllipse(xf,yf,x2,y2, Color.RED));
		painting=false;
	}
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		if(painting) {
			double x1,y1;
			if(xf>x2) {
				x1=x2;
				x2=xf;
			}
			else {
				x1=xf;
			}
			if(yf>y2) {
				y1=y2;
				y2=yf;
			}
			else {
				y1=yf;
			}
			//gc.setStroke(Color.BLACK);
			i.getBoard().draw(gc);
			gc.clearRect(x1, y1,(x2-x1),(y2-y1));
			gc.strokeOval(x1, y1,(x2-x1),(y2-y1));
		}		
	}
	public String getName(EditorInterface editor) {
		return "Filled ellipse tool";
	}
}