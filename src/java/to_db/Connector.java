package to_db;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {

    String url = "jdbc:derby://localhost:1527/M119";
    String username = "M119";
    String password = "M119";
    String driver = "org.apache.derby.jdbc.ClientDriver";
    Statement statement;
    Connection conn;

    public Connector(String url, String username, String password, String driver) {

        try {
            this.url = url;
            this.username = username;
            this.password = password;
            this.driver = driver;
            Class.forName(this.driver).getDeclaredConstructor().newInstance();

            conn = DriverManager.getConnection(this.url, this.username, this.password);
            statement = conn.createStatement();

        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

    }

    public Connector() {
        try {
            Class.forName(this.driver).getDeclaredConstructor().newInstance();

            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();

        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public boolean set(String sql) throws SQLException {
        try {
            statement = conn.createStatement();
            statement.executeUpdate(sql);
            return true;

        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }

    public ResultSet get(String sql) throws SQLException {
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
            ResultSet rs = statement.executeQuery("SELECT 'Connection failed...' AS 1");
            return rs;
        }
    }

    public void get(String sql, ResultSet rs) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public boolean reset() {
        Default def = new Default();
        for (String s : def.DROP().split(";")) {
            try {
                statement.execute(s);
            } catch (SQLException ex) {
                Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (String s : def.CREATE().split(";")) {
            try {
                statement.execute(s);
            } catch (SQLException ex) {
                Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (String s : def.ALTER().split(";")) {
            try {
                statement.execute(s);
            } catch (SQLException ex) {
                Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (String s : def.INSERT().split(";")) {
            try {
                statement.executeUpdate(s);
            } catch (SQLException ex) {
                Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public boolean ClearYear(String y, String sun, String mon, String tue, String wed, String thu, String fri, String sat) {
        /*
        Данный метод приводит год в соотсветствии с шаблоном
         */
        //формат дат SQL
        DateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd");

        //год, на который заполнение
        String year = y;

        //начальная дата
        Calendar date_from = Calendar.getInstance();
        try {
            date_from.setTime(sqlformat.parse(Integer.toString(Integer.parseInt(year) - 1) + "-12-31"));
        } catch (ParseException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

        //конечная дата
        Calendar date_to = Calendar.getInstance();
        try {
            date_to.setTime(sqlformat.parse(year + "-12-31"));
        } catch (ParseException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

        //длина года в днях
        int days = (int) ChronoUnit.DAYS.between(date_from.toInstant(), date_to.toInstant());

        //сбор рабочих часов
        ArrayList<String> strDays = new ArrayList();
        strDays.add(sun + ":00");
        strDays.add(mon + ":00");
        strDays.add(tue + ":00");
        strDays.add(wed + ":00");
        strDays.add(thu + ":00");
        strDays.add(fri + ":00");
        strDays.add(sat + ":00");

        String iDay;
        String iHour;

        int i = 0;
        do {
            date_from.add(Calendar.DAY_OF_MONTH, 1);
            iDay = sqlformat.format(date_from.getTime());
            iHour = strDays.get(date_from.get(Calendar.DAY_OF_WEEK) - 1);
            try {
                statement.executeUpdate("INSERT INTO CALENDAR (DAY, HOURS) VALUES ('" + iDay + "','" + iHour + "')");
            } catch (SQLException ex) {
                try {
                    statement.executeUpdate("UPDATE CALENDAR SET HOURS = '" + iHour + "' WHERE DAY = '" + iDay + "'");
                } catch (SQLException ex1) {
                    Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex1);
                }

                System.out.print(ex);
            }

            System.out.println(i);
        } while (++i < days);

        return true;
    }

    public void fillCalendar(String date, String sun, String mon, String tue, String wed, String thu, String fri, String sat) {

    }
}
