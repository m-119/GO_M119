import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import to_db.Connector;
import to_db.Data_Converter;
 
public class DB_TestReCreate{
    public static void main(String[] args) {
	Connector connector = new Connector();
	//connector.reset();
        
        try{
            
		ResultSet rs = connector.get("INSERT INTO table (a,b) VALUES (1,5) ON DUPLICATE KEY UPDATE b=VALUES(b)");
		} catch (Exception ex) {
		Logger.getLogger(DB_Connector.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        
    }
}
