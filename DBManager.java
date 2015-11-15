import java.sql.*;
import java.io.*;
import java.sql.Driver;
import java.sql.Connection;
class DBManager{
  PreparedStatement pst;
  ResultSet rs;
  private static Connection conn;//the instance of this singleton design pattern
  public DBManager(){};
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
  
   public static boolean checkLogin(String username, String password){
    
    String sql = "{?=call RECORDSYSTEMLOGONATTEMPT(?,?)}";
    int value=-1;
    try{
      java.sql.CallableStatement statement = DBManager.getConnection().prepareCall(sql);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);      
      statement.setString(2, username);
      statement.setString(3, password);
      statement.execute();
      //System.out.println("here.");
      value = statement.getInt(1);
    }catch(Exception e){
      System.out.println("Something happened:"+e);
      DBManager.closeConnection();
    }
    
    if(value > 1){
      return true;
    }
    else{ 
      return false;
    }
  }
}
