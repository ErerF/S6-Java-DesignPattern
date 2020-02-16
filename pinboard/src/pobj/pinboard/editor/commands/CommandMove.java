package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command{
	private List<Clip> clips;
	//private Clip clip;
	private double x;
	private double y;
	
	public CommandMove(EditorInterface editor, Clip toMove, double x, double y) {
		//this.clip = toMove;
		this.x = x;
		this.y = y;
		clips = new ArrayList<Clip>();
		clips.add(toMove);
	}

	@Override
	public void execute() {
		//clip.move(x, y);
		for(Clip clip :clips) {
			clip.move(x, y);
			System.out.println(clip.getTop()+ " " +clip.getLeft()+ " " +clip.getBottom()+ " " +clip.getRight());
		}
	}

	@Override
	public void undo() {
		//clip.move(-x, -y);
		for(Clip clip :clips) {
			System.out.println(clip.getTop()+ " " +clip.getLeft()+ " " +clip.getBottom()+ " " +clip.getRight());
			clip.move(-x, -y);
			System.out.println(clip.getTop()+ " " +clip.getLeft()+ " " +clip.getBottom()+ " " +clip.getRight());
		}
	}
	
	
}
