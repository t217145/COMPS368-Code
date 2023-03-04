package jdbcdemo;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JDBCDemo {

    public static void main(String args[]){
        try{
            //Prepare the connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comps368demo", "comps368", "comps368");

            //Get user input
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the students name that you want to query with : ");
            String usrName = br.readLine();

            //Prepare the query, use PreparedStatement to prevent SQL injection
            PreparedStatement ppt = conn.prepareStatement("select * from students where stdName like ? LIMIT 10");

            //set the parameter
            ppt.setString(1, usrName);

            //Query the result
            ResultSet rs = ppt.executeQuery();

            //Traverse the result set
            String msg = "";
            while(rs.next()){
                msg += "Student ID : " + Integer.toString(rs.getInt("stdId")) + "\r\n";
                msg += "Name : " + rs.getString("stdName") + "\r\n";
                msg += "Gender : " + rs.getString("gender") + "\r\n";
                msg += "Address : " + rs.getString("addr") + "\r\n";
                msg += "Date Of Birth : " + (rs.getDate("dateOfBirth") == null ? "null" : rs.getDate("dateOfBirth").toString()) + "\r\n";
                System.out.println(msg);
            }

            //Clean up
            rs.close();
            ppt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end of main thread
	
}//end of class