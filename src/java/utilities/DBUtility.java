/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {

    private static Connection connection = null;

    public static Connection getConnection() throws Exception {
        
        if (connection != null) {
            return connection;
        } else {
            // Store the database URL in a string
            Class.forName("com.mysql.jdbc.Driver").newInstance();//Class is java generic class
            // the forName method searches the class "com.mysql.jdbc.Driver" and newInstance() creates
            //a new instance of the class "com.mysql.jdbc.Driver" if found usinf forName()as Drivermanager type/name
            
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "aryancmsa221");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmdb", "root", "");
            /*getConnection is a function of DriverManager class and it takes input the url in the
            above format*/
            
            return connection;
        }
    }
}
