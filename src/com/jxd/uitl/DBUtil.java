package com.jxd.uitl;

import java.sql.*;

/**
 * @author Liang Yue
 * @description TODO 数据库连接和关闭工具类
 * @date 2020/8/3 16:07
 */
public class DBUtil {
    //连接数据库的四大参数
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/ly?characterEncoding=utf-8&serverTimezone=GMT";
    private static String user = "root";
    private static String password = "root";

    /**
     * 获得数据库连接
     * @return 数据库连接
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭资源
     * @param resultSet 结果集
     * @param statement 执行sql的对象
     * @param connection 数据库连接对象
     */
    public static void closeAll(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 关闭资源
     * @param resultSet 结果集
     * @param preparedStatement 执行sql的对象
     * @param connection 数据库连接对象
     */
    /*public static void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }*/
}
