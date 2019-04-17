import java.io.IOException;
import java.io.PrintWriter; 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  
@WebServlet("/hello")
public class Test extends HttpServlet {
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
	//doGet: обрабатывает запросы GET (получение данных)
	response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
	
	//calendar_day - день
	//calendar_hours - часы
	//company_id - id компании
	//company - название коммпании
	//departament_id - id отдела
	//departament - название отдела
	//title_id - id должности
	//title - название должности
	//service_id - id услуги 
	//service - название услуги
	//region_id - id региона
	//region - название региона
	//time_zone - часовой пояс региона
	//place_id - id площадки
	//place - название/адрес площадки
	//grade_id - id уровня
	//grade - уровень
	//usr_id - id сотрудника
	//usr_lastName - фамилия сотрудника
	//usr_firstName - имя сотрудника
	//usr_middleName - отчество сотрудника
	//usr_birth - дата рождения сотрудника
	//rtc_hours - время на учлугу
	//family_child
	//family_parent
	//family_name
	//family_birth
	
	
	//
	String string = "January 2, 2010";
	DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
	
	//System.out.println(date);
	
	
        try {
	    Date date = format.parse(string);
            writer.println("<h2>Hello from HelloServlet</h2>"+date);
        } catch (ParseException ex) {
	    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            writer.close();  
        }
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
	//doPost: обрабатывает запросы POST (отправка данных)
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
	//doPut: обрабатывает запросы PUT (отправка данных для изменения)
    }
 
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
         //doDelete: обрабатывает запросы DELETE (удаление данных)
    }
 
    protected void doHead(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
         //doHead: обрабатывает запросы HEAD
    }
}