package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.editor.commands.Command;

public class CommandStack {
	
	private List<Command> undo;
	private List<Command> redo;
	
	public CommandStack() {
		undo = new ArrayList<Command>();
		redo = new ArrayList<Command>();
	}
	
	public void addCommand(Command cmd) {
		undo.add(cmd);
		redo.clear();
	}
	
	public void undo() {
		Command cmd = undo.remove(undo.size()-1);
		cmd.undo();
		redo.add(cmd);
	}
	
	public void redo() {
		Command cmd = redo.remove(redo.size()-1);
		cmd.execute();
		undo.add(cmd);
	}
	
	public boolean isUndoEmpty() {
		return undo.isEmpty();
	}
	
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}
}