import java.sql.*;
import java.util.Scanner;

public class Home {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        while(true){
            System.out.println("Enter the option below");
            System.out.println("1 .Insert the values");
            System.out.println("2 .View all values");
            System.out.println("3 .Search the values using date");
            System.out.println("4 .Exit");

            choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Enter the values");
                    System.out.println("Enter the temperature");
                    int temp = sc.nextInt();
                    System.out.println("Enter the humidity");
                    int hum = sc.nextInt();
                    System.out.println("Enter the moisture");
                    String moist = sc.next();

                    System.out.println("Enter the sensor value");
                    int sensor = sc.nextInt();
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/home_db","root","");
                        String sql = "INSERT INTO `home_automation`(`temp`, `humidity`, `moisture`, `date`, `sensor_values`) VALUES (?,?,?,now(),?)";
                        PreparedStatement stmt = con.prepareStatement(sql);
                        stmt.setInt(1,temp);
                        stmt.setInt(2,hum);
                        stmt.setString(3,moist);
                        //stmt.setString(4,d);
                        stmt.setInt(4,sensor);
                        stmt.executeUpdate();
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 2:
                    System.out.println("View all the values in the database");
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/home_db","root","");
                        String sql = "SELECT `temp`, `humidity`, `moisture`, `date`, `sensor_values` FROM `home_automation` ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                            temp = rs.getInt("temp");
                            hum = rs.getInt("humidity");
                            moist = rs.getString("moisture");
                            String date = rs.getString("date");
                            sensor = rs.getInt("sensor_values");
                            System.out.println("Temperature = "+temp);
                            System.out.println("Humidity ="+hum);
                            System.out.println("Moisture = "+moist);
                            System.out.println("Date ="+date);
                            System.out.println("Sensor values = "+sensor+'\n');

                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 3:
                    System.out.println("Search the details of the particular date ");
                    System.out.println("Enter the date");
                    String date = sc.nextLine();
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/home_db","root","");
                        String sql = "SELECT `temp`, `humidity`, `moisture`, `date`, `sensor_values` FROM `home_automation` WHERE `date`='"+date+"' ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                            temp = rs.getInt("temp");
                            hum = rs.getInt("humidity");
                            moist = rs.getString("moisture");
                            date = rs.getString("date");
                            sensor = rs.getInt("sensor_values");
                            System.out.println("Temperature = "+temp);
                            System.out.println("Humidity ="+hum);
                            System.out.println("Moisture = "+moist);
                            System.out.println("Date ="+date);
                            System.out.println("Sensor values = "+sensor+'\n');

                        }

                    }
                    catch ( Exception e){
                        System.out.println(e);
                    }
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}
