
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/**
 *
 * @author panfilov_ms
 */
public class MainClass2_1 {
    
    	public static void main(String[] args) {
            
            Timestamp fDate = new Timestamp(System.currentTimeMillis());
            
            SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            
                System.out.println(sfDate.format(fDate));
		
	}
    
}
