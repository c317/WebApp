package com.gasinfo.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class JdbcUtils {

	private static DataSource dataSource = null;
    
    static{
        dataSource = new ComboPooledDataSource("dbPool");//与定义的named-config的name属性值相同
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void releaseConnection(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    /**
     * 获取数据源的一个Connection对象
     * @return
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}