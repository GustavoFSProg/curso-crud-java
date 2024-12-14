package br.com.infox.dal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.*;

/**
 *
 * @author oem
 */
public class ModuloConexao {
    
    public static Connection conector(){
    java.sql.Connection conexao = null;
    
    String driver = "com.mysql.jdbc.Driver";
    
    String url = "jdbc:mysql://localhost:3306/crud_java?characterEncoding=utf-8";
    
    String user = "root";
    
    String password = "";
    
    
    try{
    
    Class.forName(driver);
    
    conexao = DriverManager.getConnection(url,"root","");
    
               System.out.println("DEU CERTO");

            
            return conexao;

       }catch(Exception e){
           System.out.println("error::");
           System.out.println(e);

            return null;
    }
    
}
    
    
    
}
