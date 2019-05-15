
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import to_db.Connector;

public class clear_year_test {
    
    public static void main(String[] args) throws SQLException, ParseException {
	Connector connector = new Connector ();
	
	//формат дат SQL
        DateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd");
        
        //год, на который заполнение
        String year = "2019";
        
        //начальная дата
        Calendar date_from = Calendar.getInstance();
        date_from.setTime(sqlformat.parse( Integer.toString(Integer.parseInt(year)-1)+"-12-31") );
        
        //конечная дата
        Calendar date_to = Calendar.getInstance();
        date_to.setTime(sqlformat.parse("2019"+"-12-31"));
        
        //длина года в днях
        int days = (int) ChronoUnit.DAYS.between(date_from.toInstant(), date_to.toInstant());
        
        //сколько рабочих часов
        String sun = "00:00"+":00";
        String mon = "08:15"+":00";
        String tue = "08:15"+":00";
        String wed = "08:15"+":00";
        String thu = "08:15"+":00";
        String fri = "07:00"+":00";
        String sat = "00:00"+":00";
        
        //сбор рабочих часов
        ArrayList<String> strDays = new ArrayList();
        strDays.add(sun);
        strDays.add(mon);
        strDays.add(tue);
        strDays.add(wed);
        strDays.add(thu);
        strDays.add(fri);
        strDays.add(sat);

        int i = 0;
        do{
            date_from.add(Calendar.DAY_OF_MONTH, 1);
            System.out.println("INSERT INTO CALENDAR (DAY, HOURS) VALUES ('"+sqlformat.format(date_from.getTime())+"','"+strDays.get(date_from.get(Calendar.DAY_OF_WEEK)-1)+"') ON DUPLICATE KEY UPDATE HOURS=VALUES(HOURS)");
            //System.out.println(i);
        }
        while (++i<days);

        
      }
    }












 
