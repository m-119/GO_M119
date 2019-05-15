/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Iterator;
import javax.servlet.annotation.WebServlet;


import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/get")
public class JSON_toDB extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
	
    response.setContentType("application/json; charset=UTF-8;");
    
    try (PrintWriter out = response.getWriter()) {
	
            out.println("Name: ");
	    
        }
    
    
    
    }


}
