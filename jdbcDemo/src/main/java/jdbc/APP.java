package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


/**
 * Created by Chris on 2019/1/8.
 */
public class APP {
    public static void main (String [] args ){
        Long start = System.currentTimeMillis();
        try {
             String driverClass = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/big4";
            String username ="root";
            String password ="123456";
            Class.forName(driverClass);
            Connection  conn = DriverManager.getConnection(url,username,password);
            conn.setAutoCommit(false);
            String sql = "insert into users(name,age) values (?, ?)";
            PreparedStatement ppst = conn.prepareStatement(sql);
            //Statement statement =  conn.createStatement(sql);

            for(int i=0;i<10000;i++){
                ppst.setString(1,"tom"+i);
                ppst.setInt(2,i%100);
                ppst.executeUpdate();
            }
            conn.commit();
            ppst.close();
            conn.close();
            System.out.println(System.currentTimeMillis()-start);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
