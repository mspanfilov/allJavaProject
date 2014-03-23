import java.util.List;


public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
                
                System.out.println(args[0]);
                System.out.println(args[1]);
                System.out.println(args[2]);
                
		
		CuttingStringEncoderImpl csei = new CuttingStringEncoderImpl();
		
		List<byte[]> res = csei.cutAndEncode(args[0], java.nio.charset.Charset.forName(args[1]), Integer.parseInt(args[2]));
          
		System.out.println("res: ");
		
                for (int i=0; i<res.size();i++){
                    System.out.println(new String(res.get(i),java.nio.charset.Charset.forName(args[1])));
                }
		
	}

}
