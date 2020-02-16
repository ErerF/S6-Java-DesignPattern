package pobj.tme4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
	private Map<T,Integer> map;
	private int size;
	
	public HashMultiSet(){
		map=new HashMap<T,Integer>();
		size=0;
	}
	
	public HashMultiSet(Collection<T> c) {
		map=new HashMap<T,Integer>();
		size=0;
		for(T t:c) {
			this.add(t);
		}
	}
	
	public Iterator<T> iterator() {
		return new HashMultiSetIterator();
	}
	
	public int size() {
		return size;
	}
	
	public boolean add(T e) {
		if(map.get(e)==null) 
			map.put(e,new Integer(1));
		else
			map.put(e,new Integer(map.get(e).intValue()+1));	
		
		size++;
		return true;
	}
	
	public boolean add(T e, int count) {
		if(map.get(e)==null) 
			map.put(e,new Integer(count));
		else
			map.put(e,new Integer(map.get(e).intValue()+count));
		
		size+=count;
		return true;
	}
	
	public boolean remove(Object e) {
		T elem=(T)e;

		if(map.get(elem)==null)
			return false;
		else {
			int cpt=map.get(elem);
			map.remove(elem);
			map.put(elem,new Integer(cpt-1));
			size--;
			return true;
		}
	}
	
	public boolean remove(Object e, int count) {
		T elem=(T)e;

		if(map.get(elem)==null || map.get(elem).intValue()<count)
			return false;
		else {
			int cpt=map.get(elem);
			map.remove(elem);
			map.put(elem,new Integer(cpt-count));
			size-=count;
			return true;
		}
	}
	
	public int count(T o) {
		if(map.get(o)==null)
			return 0;
		else
			return map.get(o);
	}
	
	public void clear() {
		map.clear();
		size=0;
	}
	
	public List<T> elements(){
		Set<T> keySet=map.keySet();
		return new ArrayList<T>(keySet);
	}
	
	public int compare(T o1,T o2) {
		if(map.get(o1)==null)
			return -1;
		if(map.get(o2)==null)
			return 1;
		
		int cpt1=map.get(o1).intValue();
		int cpt2=map.get(o2).intValue();
		if(cpt1>cpt2)
			return 1;
		else if(cpt1==cpt2)
			return 0;
		else
			return-1;
	}

	public class HashMultiSetIterator implements Iterator<T>{
		private int cpt=0;
		private T currentKey;
		private Iterator<Map.Entry<T,Integer>> it=map.entrySet().iterator();
		
		public boolean hasNext() {
			return cpt!=0 || it.hasNext();
		}
		
		public T next(){
			if(cpt==0) {
				Entry<T,Integer> entry=it.next();
				currentKey=entry.getKey();
				cpt=entry.getValue().intValue();
			}
			cpt--;
			return currentKey;
		}
		
		public void remove() {
			map.remove(currentKey);
		}
	}
}
