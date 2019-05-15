package to_db;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Data_Converter {
    
  public static JSONArray SQLtoJSON( ResultSet rs )
    throws SQLException, JSONException
  {
    JSONArray json = new JSONArray();
    ResultSetMetaData rsmd = rs.getMetaData();

    while(rs.next()) {
      int numColumns = rsmd.getColumnCount();
      JSONObject obj = new JSONObject();

      for (int i=1; i<numColumns+1; i++) {
        String column_name = rsmd.getColumnName(i);

	  switch (rsmd.getColumnType(i)) {
	      case java.sql.Types.ARRAY:
		  obj.put(column_name, rs.getArray(column_name));
		  break;
	      case java.sql.Types.BOOLEAN:
		  obj.put(column_name, rs.getBoolean(column_name));
		  break;
	      default:
		  obj.put(column_name, rs.getString(column_name));
		  break;
	  }
      }

      json.put(obj);
    }

    return json;
  }

}