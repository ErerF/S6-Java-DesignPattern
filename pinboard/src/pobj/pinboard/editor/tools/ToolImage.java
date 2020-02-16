package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;

public class ToolImage implements Tool{
	public void press(EditorInterface i, MouseEvent e) {
		
	}
	public void drag(EditorInterface i,MouseEvent e) {
		
	}
	public void release(EditorInterface i, MouseEvent e) {
		
	}
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		
	}
	public String getName(EditorInterface editor) {
		return "Image tool";
	}
}
