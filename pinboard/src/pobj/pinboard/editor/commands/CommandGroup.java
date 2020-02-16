package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.document.Composite;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command{
	
	private EditorInterface editor;
	private Composite groupe;
	
	public CommandGroup(EditorInterface editor, List<Clip> toGroup) {
		this.editor = editor;
		groupe = new ClipGroup();
		for(Clip clip:toGroup) {
			groupe.addClip(clip);
		}
	}

	@Override
	public void execute() {
		editor.getBoard().removeClip(groupe.getClips());
		editor.getBoard().addClip(groupe);
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(groupe);
		editor.getBoard().addClip(groupe.getClips());;
	}
	
}