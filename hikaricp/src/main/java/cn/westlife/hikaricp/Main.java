package cn.westlife.hikaricp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhuangchao
 * @date 2019/3/25 10:20
 */
public class Main {

    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/springboot?serverTimezone=GMT%2B8&characterEncoding=utf-8");
        hikariConfig.setUsername("admin");
        hikariConfig.setPassword("123");
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");


        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("id=" + id + " name=" + name + " age=" + age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
