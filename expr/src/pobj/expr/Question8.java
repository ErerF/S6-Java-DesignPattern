package pobj.expr;

import java.util.HashMap;
import java.util.Map;

public class Question8 {
	
	public static Map<String, Integer> env1() {
		Map<String, Integer> res = new HashMap<String, Integer>();
		return res;
	}
	
	public static Map<String, Integer> env2() {
		Map<String, Integer> res = new HashMap<String, Integer>();
		res.put("x", 10);
		res.put("y", 20);
		return res;
	}
	
	public static Map<String, Integer> env3() {
		Map<String, Integer> res = new HashMap<String, Integer>();
		res.put("z", 9);
		return res;
	}

}
