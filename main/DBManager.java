import java.sql.*;
import java.io.*;
import java.sql.Driver;
import java.sql.Connection;
class DBManager{
  private static Connection conn;//the instance of this singleton design pattern
  private DBManager(){};
  public static Connection getConnection() throws Exception{ 
    if(!(conn==null)){
      return conn;
    }
    else{
      Class.forName("oracle.jdbc.driver.OracleDriver");
      conn=DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241","rdj217cse216","LiangCheng");
      return conn;
    }
  }
  public static void closeConnection(){
    try{
      conn.close(); 
    }catch(Exception e){
    }
  }
}