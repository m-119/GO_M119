import to_db.Connector;
 
public class DB_TestReCreate{
    public static void main(String[] args) {
	Connector connector = new Connector();
	connector.reset();
    }
}
