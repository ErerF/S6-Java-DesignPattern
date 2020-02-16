package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MultiSetParser {

	public static MultiSet<String> parse(String filename) throws InvalidMultiSetFormat{
		try {
			BufferedReader br=new BufferedReader(new FileReader(filename));
			MultiSet<String> res=new HashMultiSet<String>();
			String line;
			while((line=br.readLine())!=null) {
				String[] splited=line.split(":");
				if(splited.length<2)
					throw new InvalidMultiSetFormat("Error:Expected <chaine>:<number> !");
				if(splited.length>2)
					throw new InvalidMultiSetFormat("Error:Cannot have ':' in <chaine> !");
				
				res.add(splited[0],Integer.decode(splited[1]));
			}			
			
			br.close();
			return res;
		}
		catch(IOException e) {
			e.printStackTrace();			
			throw new InvalidMultiSetFormat("Error:IOException !",e);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			throw new InvalidMultiSetFormat("Error:Expected a number after ':' !",e);
		}
	}
}
