package javaapplication2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class database {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public database(){
        try{
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DELL\\Desktop\\SOOAD\\logindb");
            st=con.createStatement();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void close(){
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException e){
                
            }
        }
        if(st!=null){
            try{
                st.close();
            }catch(SQLException e){
                
            }
        }        
        if(con!=null){
            try{
                con.close();
            }catch(SQLException e){
                
            }
        }
    }
    
    public String[][] getData(String query){
        String s[][]=new String[100][9];
        int i=0;
        try{
            rs=st.executeQuery(query);
            while(rs.next()){
                for(int j=0;j<9;j++){
                    s[i][j]=rs.getString(j+1);
                }
                i++;
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return s;
    }
    public void addData(String s){
        try {
            st.executeUpdate(s);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean login(String uname,String passwd){
        try{
            String query="select * from login";
            rs=st.executeQuery(query);
            while(rs.next()){
                String n=rs.getString("username");
                String p=rs.getString("password");
                if(uname.equals(n)&&passwd.equals(p))
                    return true;
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }
    
    public boolean regis(String name,String no,String uname,String passwd){
        try{
 String query="insert into login(username,password,name,number) values('"+uname+"','"+passwd+"','"+name+"','"+no+"')";
            st.executeUpdate(query);
            return true;
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }
    public int getVal(String typ)
    {
            try{
            String query="select price from grade where type='"+typ+"'";
            rs=st.executeQuery(query);
            String n=rs.getString("price");
            int x=Integer.parseInt(n);
            return x;
           }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
      public void setOrder(String cname,int id,String type,double total,String date)
    {
            try{
            String query="insert into customer values('"+cname+"','"+id+"','"+type+"','"+total+"','"+date+"')";
            st.executeUpdate(query);
               }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
       public String[][] pCust(String query){
        String s[][]=new String[100][5];
        int i=0;
        try{
            rs=st.executeQuery(query);
            while(rs.next()){
                for(int j=0;j<5;j++){
                    s[i][j]=rs.getString(j+1);
                }
                i++;
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return s;
    }
       public int getDateDiff(String sdate,String nam)
       {
            try{
            String query="select date from customer where name='"+nam+"'";
            rs=st.executeQuery(query);
            String n=rs.getString("date");
            n=n.substring(0,2);
            int d=Integer.parseInt(n);
            int sd=Integer.parseInt(sdate);
            int x=0;
            for(int i=d;i!=sd;i++)
            {
                if(i==30)
                    i=0;
                x++;
            }
            return x;
           }
        catch(Exception ex){
            System.out.println(ex);
        }
       return -1;
       }

}