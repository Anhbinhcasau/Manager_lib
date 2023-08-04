package com.example.quanlithuvien;

import java.sql.Connection;
import java.sql.DriverManager;




public class ConnectDatabase {
    public Connection connection;
    public Connection getConnection(){
        String databaseName="thuvien";
        String dataUser="root" ;
        String dataPassword="cangimatkhau1";
        String url="jdbc:mysql://127.0.0.1:3306/" + databaseName;

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
