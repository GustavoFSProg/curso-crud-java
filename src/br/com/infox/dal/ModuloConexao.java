package br.com.infox.dal;

/*
 <#if licenseFirst??>
${licenseFirst}
</#if>
${licensePrefix}The MIT License
${licensePrefix?replace(" +$", "", "r")}
${licensePrefix}Copyright ${date?date?string("yyyy")} ${project.organization!user}.
${licensePrefix?replace(" +$", "", "r")}
${licensePrefix}Permission is hereby granted, free of charge, to any person obtaining a copy
${licensePrefix}of this software and associated documentation files (the "Software"), to deal
${licensePrefix}in the Software without restriction, including without limitation the rights
${licensePrefix}to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
${licensePrefix}copies of the Software, and to permit persons to whom the Software is
${licensePrefix}furnished to do so, subject to the following conditions:
${licensePrefix?replace(" +$", "", "r")}
${licensePrefix}The above copyright notice and this permission notice shall be included in
${licensePrefix}all copies or substantial portions of the Software.
${licensePrefix?replace(" +$", "", "r")}
${licensePrefix}THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
${licensePrefix}IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
${licensePrefix}FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
${licensePrefix}AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
${licensePrefix}LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
${licensePrefix}OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
${licensePrefix}THE SOFTWARE.
<#if licenseLast??>
${licenseLast}
</#if>
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
