package Utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {

    public static void main(String[] args) throws SQLException {
        /*
        Connection
        Statement
        ResultSet
         */
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@mydatabase.c7tcy8qn0yoc.us-east-2.rds.amazonaws.com:1521/ORCL",
                "techdatabase",
                "Amina12345"
        );
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");
        resultSet.next();
        String first_name = resultSet.getString("FIRST_NAME");
        System.out.println(first_name);
        System.out.println(resultSet.getString("employee_id"));
        resultSet.next();
        System.out.println(resultSet.getString("First_name"));
        resultSet.last();
        System.out.println(resultSet.getString("First_name"));
        resultSet.first();
        System.out.println(resultSet.getString("First_name"));
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getRow());
        System.out.println(resultSet.getObject("employee_id"));
        //**********ResultSetMetaData************************
// result set meta data --> gives us all details about the columns
        ResultSetMetaData rMetaData = resultSet.getMetaData();
        System.out.println(rMetaData.getColumnCount());
        System.out.println(rMetaData.getColumnName(1));
        System.out.println(rMetaData.getColumnName(5));
// I list all data in Java Collection type of object
        List<Map<String, Object>> data = new ArrayList<>();
        //loop througth resultset
        resultSet.beforeFirst();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i < rMetaData.getColumnCount(); i++) {
                map.put(rMetaData.getColumnName(i), resultSet.getObject(rMetaData.getColumnName(i)));
            }
            data.add(map);
        }
        System.out.println(data.get(10).get("FIRST_NAME"));
        System.out.println(data.get(45).get("EMAIL"));
        // Print all salaries of employees useing data object
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).get("SALARY"));
        }
        // get all emails
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).get("EMAIL"));
        }
        //from data object get all first names start with letter A
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get("FIRST_NAME").toString().startsWith("A")) {
                System.out.println(data.get(i).get("FIRST_NAME"));
            }
        }
        // using data object get avarage salary of all employees
        int total = 0;
        for (int i = 0; i < data.size(); i++) {
            total = total + Integer.parseInt(data.get(i).get("SALARY").toString());
        }
        System.out.println(total / data.size());
    }


}
