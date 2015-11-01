import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Catalog{
  public HashMap<Integer, Product> Dictionary = new HashMap<Integer, Product>();
  public Catalog(Connection con){
    fill(con);
  }
  
  public static void main(String[] args){
    Boolean notDone = true;
    ResultSet result = null;
    Statement s = null;
    Connection con = null;
    Scanner krb = new Scanner(System.in);
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      con=DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241","rdj217cse216","LiangCheng");
      s=con.createStatement();
      notDone = false;
      Catalog c = new Catalog(con);
     // System.out.println("working");
      s.close();
      con.close();
    } catch(Exception e){
      System.out.println ("Login Error: ");
      System.out.println (e.getMessage());
      Catalog c = new Catalog(con);
      //  s.close();
      //con.close();
    }    
  }
  
  public void fill(Connection con){
    //throws SQLException, IOException, java.lang.ClassNotFoundException {
    
    try{
      ResultSet result = null;
      Statement s = null;
      s=con.createStatement();
      ArrayList<Integer> ProdID = new ArrayList<Integer>();
      ArrayList<Integer> prs = new ArrayList<Integer>();
      ArrayList<Integer> rr = new ArrayList<Integer>();
      ArrayList<String>  desc = new ArrayList<String>();
      
      String q = "select PRODUCTID from PRODUCTS";
      result = s.executeQuery(q);
      ResultSetMetaData rsmd = result.getMetaData();
      //System.out.println("TEST");
      if (!result.next()) System.out.println ("Empty result.");
      else{
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        }
        do {
          for (int i = 1; i <= rsmd.getColumnCount(); i++)
          {
            //System.out.println(result.getInt(i));
            ProdID.add(result.getInt(i));
          }
        }while (result.next());
      }
//System.out.println("TEST");
      q = "select PRICE from PRODUCTS";
      result = s.executeQuery(q);
      rsmd = result.getMetaData();
      if (!result.next()) System.out.println ("Empty result.");
      else{
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        }
        do {
          for (int i = 1; i <= rsmd.getColumnCount(); i++)
          {
            prs.add(result.getInt(i));
            //System.out.println(result.getInt(i));
          }
        }while (result.next());
      }
      
      q = "select RENTALRATE from PRODUCTS";
      result = s.executeQuery(q);
      rsmd = result.getMetaData();
      if (!result.next()) System.out.println ("Empty result.");
      else{
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        }
        do {
          for (int i = 1; i <= rsmd.getColumnCount(); i++)
          {
            rr.add(result.getInt(i));
            //System.out.println(result.getInt(i));
          }
        }while (result.next());
      }
      
      q = "select DESCRIPTION from PRODUCTS";
      result = s.executeQuery(q);
      rsmd = result.getMetaData();
      if (!result.next()) System.out.println ("Empty result.");
      else{
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        }
        do {
          for (int i = 1; i <= rsmd.getColumnCount(); i++)
          {
            desc.add(result.getString(i));
            //System.out.println(result.getInt(i));
          }
        }while (result.next());
      }
      
      for(int k = 0; k < prs.size(); k++){
        Product A = new Product(ProdID.get(k), prs.get(k), rr.get(k), desc.get(k));
        Dictionary.put(ProdID.get(k), A);
        System.out.println(ProdID.get(k)+ "  " + prs.get(k)+ "  " + rr.get(k)+ "  " + desc.get(k));
      }
    } catch(Exception e){
      System.out.println (e);
    }
    
  }
  
}