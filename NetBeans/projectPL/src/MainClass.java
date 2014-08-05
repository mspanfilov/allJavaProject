import java.util.List;


public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Начало!");
                
                System.out.println(args[0]);
                System.out.println(args[1]);
                System.out.println(args[2]);
                
		
		CuttingStringEncoderImpl csei = new CuttingStringEncoderImpl();
		
		List<String> res = csei.cutAndEncode(args[0], java.nio.charset.Charset.forName(args[1]), Integer.parseInt(args[2]));
                
                for (int i=0; i<res.size();i++){
                    System.out.println(res.get(i));
                }
                
		System.out.println("Конец!");
		
	}

}
