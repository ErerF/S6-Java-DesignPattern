package pobj.tme5;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.List;

public class MultiSetDecorator<T> extends AbstractCollection<T> implements MultiSet<T>{
	private MultiSet<T> decorated;//=new HashMultiSet();
	
	public MultiSetDecorator(MultiSet<T> set) {
		decorated=set;
	}
	
	public boolean add(T e, int count) {
		boolean ok=decorated.add(e, count);
		if(!isConsistent())
			throw new InternalError("Add error!");
		return ok;
	}
	public boolean add(T e) {
		boolean ok=decorated.add(e);
		if(!isConsistent())
			throw new InternalError("Add error!");
		return ok;
	}
	public boolean remove(Object e) {
		boolean ok=decorated.remove(e);
		if(!isConsistent())
			throw new InternalError("Add error!");
		return ok;
	}
	public boolean remove(Object e, int count) {
		boolean ok=decorated.remove(e, count);
		if(!isConsistent())
			throw new InternalError("Add error!");
		return ok;
	}
	public int count(T o) {
		return decorated.count(o);
	}
	public void clear() {
		decorated.clear();
	}
	public int size() {
		return decorated.size();
	}	
	public List<T> elements(){
		return decorated.elements();
	}

	@Override
	public int compare(T o1, T o2) {
		return decorated.compare(o1, o2);
	}

	@Override
	public Iterator<T> iterator() {
		return decorated.iterator();
	}
	@Override
	public boolean isConsistent() {
		return decorated.isConsistent();
	}
	
	public String toString() {
		return decorated.toString();
	}
}
