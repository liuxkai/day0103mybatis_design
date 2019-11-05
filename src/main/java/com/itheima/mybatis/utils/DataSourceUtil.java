package com.itheima.mybatis.utils;

import com.itheima.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 刘晓凯
 * @date 2019-11-04-15:35
 * 用于创建数据源的工具类
 */
public class DataSourceUtil {
    /**
     * 用于获取一个连接
     */
    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
