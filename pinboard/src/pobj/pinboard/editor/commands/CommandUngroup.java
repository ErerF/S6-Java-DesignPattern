package pobj.pinboard.editor.commands;


import pobj.pinboard.document.Composite;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command{

	private EditorInterface editor;
	private Composite group;
	
	public CommandUngroup(EditorInterface editor, Composite group) {
		this.editor = editor;
		this.group = group;
	}

	@Override
	public void execute() {
		editor.getBoard().removeClip(group);
		editor.getBoard().addClip(group.getClips());
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(group.getClips());
		editor.getBoard().addClip(group);;
	}
	
}