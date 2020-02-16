package pobj.tme5;

public class MultiSetParserTest {

	public static void main(String[] args) {		
		try {
			System.out.println(MultiSetParser.parse("data/parserTest.txt"));
		} catch (InvalidMultiSetFormat e) {
			e.printStackTrace();
		}
	}

}
