package pobj.tme4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
	private List<T> list;
	private int size;
	
	public NaiveMultiSet(){
		list=new ArrayList<T>();
		size=0;
	}
	
	public NaiveMultiSet(Collection<T> c) {
		list=new ArrayList<T>();
		size=0;
		for(T t:c) {
			this.add(t);
		}
	}
	
	public boolean add(T e, int count) {
		for(int i=0;i<count;i++) {
			list.add(e);
		}
		size+=count;
		return true;
	}
	
	public boolean add(T e) {
		list.add(e);
		size++;
		return true;
	}
	
	public boolean remove(Object e) {
		if(list.remove((T)e)) {
			size--;
			return true;
		}
		else
			return false;
	}
	
	public boolean remove(Object e, int count) {
		T t=(T)e;
		if(this.count((T)e)>=count){
			for(int i=0;i<count;i++) {
				list.remove(t);
			}
			size-=count;
			return true;
		}
		else 
			return false;
	}
	
	public int count(T o) {
		int cpt=0;
		for(T t:list) {
			if(t.equals(o))
				cpt++;
		}
		return cpt;
	}
	
	public void clear() {
		list.clear();
		size=0;
	}
	
	public int size() {
		return size;
	}
	
	public Iterator<T> iterator() {
		return list.iterator();
	}
	
	public List<T> elements(){
		return list;
	}
	
	public int compare(T o1,T o2) {
		int cpt1=this.count(o1);
		int cpt2=this.count(o2);
		if(cpt1>cpt2)
			return 1;
		else if(cpt1==cpt2)
			return 0;
		else
			return-1;
	}
}
