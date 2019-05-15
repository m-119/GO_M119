/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import to_db.Connector;
import to_db.Data_Converter;

/**
 *
 * @author JAG
 */
@WebServlet(urlPatterns = {"/DB_Connector"})
public class DB_Connector extends HttpServlet {
    Connector connector = new Connector();
    //Map <String, String> kv = new HashMap<String, String>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	response.setContentType("text/html;charset=UTF-8");
	try (PrintWriter out = response.getWriter()) {
	    /* TODO output your page here. You may use following sample code. */
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Servlet DB_Connector</title>");	    
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>Servlet DB_Connector at " + request.getContextPath() + "</h1>");
	    out.println("</body>");
	    out.println("</html>");
	}
	
	
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	//processRequest(request, response);
	response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding ("UTF-8");
        
	PrintWriter writer = response.getWriter();
	
	if (("Rfull".equals(request.getParameter("reset")))
	  &&
	  (connector.reset()))
	    {
		JSONObject json = new JSONObject();
                
                json.put("action","done");
                json.put("SQLdata","");
                
		response.getWriter().write(json.toString());
		response.getWriter().close();
	    }
	
    }

    @Override
public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    
  response.setContentType("text/html;charset=UTF-8");
    
  StringBuffer jb = new StringBuffer();
  JSONObject jsonIN;
  String line = null;
  
    try {
    BufferedReader reader = request.getReader();
    while ((line = reader.readLine()) != null)
      jb.append(line);
  } catch (Exception e) { /*report an error*/ }

  try {
    jsonIN =  new JSONObject(jb.toString());
  } catch (JSONException e) {
    // crash and burn
    throw new IOException("Error parsing JSON request string");
  }
    
  String actionType = jsonIN.getString("action");
   
  Date id = new Date();
  
  System.out.println(id.getHours()+":"+id.getMinutes()+":"+id.getSeconds()+"--------->");
  
  System.out.println("--------->"+jsonIN.getString("action"));
  if (("reset DB".equals(actionType))
	  &&
	  (connector.reset()))
	    {

                JSONObject json = new JSONObject();
                
                json.put("action","done");
                json.put("SQLdata","");
                
		response.getWriter().write(json.toString());
		response.getWriter().close();
                
	    }
  else if ("none".equals(actionType))
	    {

                JSONObject json = new JSONObject(); 
                
                json.put("action","no actions");
                json.put("SQLdata","");
                
		response.getWriter().write(json.toString());
		response.getWriter().close();
                                
	    }
  else if ("DEPARTMENT_ID-ID-TITLE".equals(actionType))
	    {

		try{
		ResultSet rs = connector.get("SELECT DEPARTMENT_ID, ID, TITLE TEXT FROM TITLE T INNER JOIN DEPARTMENT_TITLE DT ON T.ID = DT.TITLE_ID");
		
                JSONObject json = new JSONObject();
                
		json.put("action","DEPARTMENT_ID-ID-TITLE");
		json.put("SQLdata",Data_Converter.SQLtoJSON(rs));
                
		response.getWriter().write(json.toString());
		response.getWriter().close();
                
		} catch (Exception ex) {
		Logger.getLogger(DB_Connector.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	    }
  else if ("ID-DEPARTMENT".equals(actionType))
	    {
		try{
		ResultSet rs = connector.get("SELECT ID, DEPARTMENT TEXT FROM DEPARTMENT");
		
                JSONObject json = new JSONObject();
                
		json.put("action","ID-DEPARTMENT");
		json.put("SQLdata",Data_Converter.SQLtoJSON(rs));
                
		response.getWriter().write(json.toString());
		response.getWriter().close();
                System.out.println("ID-DEPARTMENT");
		} catch (Exception ex) {
		Logger.getLogger(DB_Connector.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
  else if ("ID-GRADE".equals(actionType))
	    {
                System.out.println("ID-GRADE");
		try{
		ResultSet rs = connector.get("SELECT ID, GRADE TEXT FROM GRADE");
                
                JSONObject json = new JSONObject();
                
		json.put("action","ID-GRADE");
		json.put("SQLdata",Data_Converter.SQLtoJSON(rs));
                
		response.getWriter().write(json.toString());
		response.getWriter().close();
		} catch (Exception ex) {
		Logger.getLogger(DB_Connector.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
    else if ("ID-REGION-TIME_ZONE".equals(actionType))
	    {
		try{
		ResultSet rs = connector.get("SELECT ID, REGION TEXT, TIME_ZONE FROM M119.REGION");
                
                JSONObject json = new JSONObject();
                
		json.put("action","ID-REGION-TIME_ZONE");
		json.put("SQLdata",Data_Converter.SQLtoJSON(rs));
                
		response.getWriter().write(json.toString());
		response.getWriter().close();
                //System.out.println("ID-REGION-TIME_ZONE");
		} catch (Exception ex) {
		Logger.getLogger(DB_Connector.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
      else if ("ID-REGION".equals(actionType))
	    {
		try{
		ResultSet rs = connector.get("SELECT ID, REGION TEXT FROM M119.REGION");
                
		JSONObject json = new JSONObject();
                
		json.put("action","ID-REGION");
		json.put("SQLdata",Data_Converter.SQLtoJSON(rs));
                
		response.getWriter().write(json.toString());
		response.getWriter().close();
                
		} catch (Exception ex) {
		Logger.getLogger(DB_Connector.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
      else if ("ID-TIME_ZONE".equals(actionType))
	    {
		try{
		ResultSet rs = connector.get("SELECT ID, TIME_ZONE TEXT FROM M119.REGION");
                
                JSONObject json = new JSONObject();
                
		json.put("action","ID-TIME_ZONE");
		json.put("SQLdata",Data_Converter.SQLtoJSON(rs));
                
		response.getWriter().write(json.toString());
		response.getWriter().close();
                
		} catch (Exception ex) {
		Logger.getLogger(DB_Connector.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
      else if (("clear Year".equals(actionType))
                  &&
                  (connector.ClearYear(jsonIN.getString("year"),jsonIN.getString("sun"),jsonIN.getString("mon"),jsonIN.getString("tue"),jsonIN.getString("wed"),jsonIN.getString("thu"),jsonIN.getString("fri"),jsonIN.getString("sat"))))
          {
              JSONObject json = new JSONObject();
              json.put("action","Year recreated");

              response.getWriter().write(json.toString());
              response.getWriter().close();
          }
      else if ("load Calendar".equals(actionType))
	    {
		try{
		ResultSet rs = connector.get("SELECT DAY, HOURS FROM M119.CALENDAR WHERE DAY BETWEEN '"+jsonIN.getString("date_from")+"' AND '"+jsonIN.getString("date_to")+"'");
		JSONObject json = new JSONObject();
                
		json.put("action","load Calendar");
		json.put("SQLdata",Data_Converter.SQLtoJSON(rs));
                
		response.getWriter().write(json.toString());
		
		response.getWriter().close();
		} catch (Exception ex) {
		Logger.getLogger(DB_Connector.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
      else if ("update Calendar".equals(actionType))
	    {
		try{
		ResultSet rs;
		JSONObject json = new JSONObject();
                
		json.put("action","update Calendar");
                
                JSONArray ja = jsonIN.getJSONArray("SQLdata[]");
                
                for(int i = 0 ; i < ja.length() ; i++)
                {
                    connector.set("UPDATE M119.CALENDAR SET HOURS = '" + ja.getJSONObject(i).get("HOURS") + "' WHERE DAY = '" + ja.getJSONObject(i).get("DAY") + "'");
                    System.out.println("SQL 'update Calendar':"+"UPDATE M119.CALENDAR SET HOURS = '" + ja.getJSONObject(i).get("HOURS") + "' WHERE DAY = '" + ja.getJSONObject(i).get("DAY") + "'");
                }
                
		response.getWriter().write(json.toString());
		
		response.getWriter().close();
		} catch (Exception ex) {
		Logger.getLogger(DB_Connector.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
     else if ("upload User".equals(actionType))
	    {
		try{
		ResultSet rs;
		JSONObject json = new JSONObject();
                
		json.put("action","update Calendar");
                
                //connector.set("UPDATE M119.USR SET HOURS = '" + jsonIN ja.getJSONObject(i).get("HOURS") + "' WHERE DAY = '" + ja.getJSONObject(i).get("DAY") + "'");
                //System.out.println("SQL 'update Calendar':"+"UPDATE M119.CALENDAR SET HOURS = '" + ja.getJSONObject(i).get("HOURS") + "' WHERE DAY = '" + ja.getJSONObject(i).get("DAY") + "'");
                
		response.getWriter().write(json.toString());
		
		response.getWriter().close();
		} catch (Exception ex) {
		Logger.getLogger(DB_Connector.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }


}

    @Override
    public String getServletInfo() {
	return "Short description";
    }

}
