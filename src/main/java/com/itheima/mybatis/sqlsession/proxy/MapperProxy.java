package com.itheima.mybatis.sqlsession.proxy;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.cfg.Mapper;
import com.itheima.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @author 刘晓凯
 * @date 2019-11-04-15:07
 */
public class MapperProxy implements InvocationHandler {

    //map的key是全限定名+方法名
    private Map<String, Mapper> mappers;
    private Connection conn;
    public MapperProxy(Map<String, Mapper> mappers, Connection connection){
         this.mappers = mappers;
         this.conn = connection;

     }



    /**
     * 用于对方法进行增强的，我们的增强其实就是调用selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.获取方法名
        String methodName = method.getName();
        //2.获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        //3.组合key
        String key = className+"."+methodName;
        //4.获取mappers中的Mapper对象
        Mapper mapper = mappers.get(key);
        //5.判断是否有mapper
        if(mapper == null){
            throw new IllegalAccessException("传入的参数有误");
        }
        //6.使用工具类执行查询所有
        return new Executor().selectList(mapper, conn);
    }
}
