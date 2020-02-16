package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command{
	
	private EditorInterface editor;
	private List<Clip> clips;
	
	public CommandAdd(EditorInterface editor, Clip toAdd) {
		this.editor = editor;
		clips = new ArrayList<Clip>();
		clips.add(toAdd);
	}
	public CommandAdd(EditorInterface editor, List<Clip> toAdd) {
		this.editor = editor;
		clips = new ArrayList<Clip>();
		clips.addAll(toAdd);
	}
	@Override
	public void execute() {
		editor.getBoard().addClip(clips);
	}
	@Override
	public void undo() {
		editor.getBoard().removeClip(clips);
	}
}
