package com.example.quanlithuvien;

import java.sql.Connection;
import java.sql.DriverManager;




public class ConnectDatabase {
    public Connection connection;
    public Connection getConnection(){
        String databaseName="quanlithuvien";
        String dataUser="root" ;
        String dataPassword="ha!";
        String url="jdbc:mysql://localhost/" + databaseName;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(url,dataUser,dataPassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;

    }

}
