package pobj.pinboard.editor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;

public class Clipboard {
	private static Clipboard clipboard;
	private List<Clip> instance;
	private List<ClipboardListener> listeners;
	
	private Clipboard() {
		instance=new ArrayList<>();
		listeners=new ArrayList<>();
	}
	
	public static Clipboard getInstance() {
		if(clipboard==null)
			clipboard=new Clipboard();
		return clipboard;
	}
	
	public void copyToClipboard(List<Clip> clips) {
		clear();
		for(Clip c:clips) {
			instance.add(c.copy());
		}
		for(ClipboardListener cl:listeners) {
			cl.clipboardChanged();
		}
	}
	
	public List<Clip> copyFromClipboard(){
		List<Clip> res=new ArrayList<>();
		for(Clip c: instance) {
			res.add(c.copy());
		}
		return res;
	}
	
	public void clear() {
		instance.clear();
		for(ClipboardListener cl:listeners) {
			cl.clipboardChanged();
		}
	}
	
	public boolean isEmpty() {
		return instance.isEmpty();
	}
	
	public void addListener(ClipboardListener listener) {
		listeners.add(listener);
		listener.clipboardChanged();
	}
	
	public void removeListener(ClipboardListener listener) {
		listeners.remove(listener);
	}
}
