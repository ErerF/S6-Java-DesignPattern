package pobj.tme4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import pobj.util.Chrono;

public class WordCount {

	public static void wordcount(MultiSet<String> ms) {
		try {
			String file="data/WarAndPeace.txt";
			BufferedReader br=new BufferedReader(new FileReader(file));
			String line;
			while((line=br.readLine())!=null) {
				for(String word:line.split("\\P{L}+")) {
					if(word.equals("")) continue;//ignore les mots vides
					ms.add(word);
				}
			}
			br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> list=ms.elements();
		list.sort(ms);
		Collections.reverse(list);
		if(list.size()>=10) {
			System.out.println("Voici les 10 elements plus frequents:");
			for(int i=0;i<10;i++) {
				System.out.println("	"+list.get(i));
			}
		}		
		else {
			System.out.println("Attention: La liste contient moins de 10 elements!");
			System.out.println("Voici tous les elements:");
			for(String s:list) {
				System.out.println("	"+s);
			}
		}
	}

	public static void main(String[] args) {
		MultiSet<String> hms=new HashMultiSet<String>();
		Chrono chrono1=new Chrono();
		WordCount.wordcount(hms);
		chrono1.stop();
		System.out.println("	--Version HashMultiSet<String>");
		
		/*
		MultiSet<String> nms=new NaiveMultiSet<String>();
		Chrono chrono2=new Chrono();
		WordCount.wordcount(nms);
		chrono2.stop();
		System.out.println("	--Version NaiveMultiSet<String>");*/
	}
}
