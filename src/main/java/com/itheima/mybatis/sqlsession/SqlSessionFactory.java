package com.itheima.mybatis.sqlsession;

/**
 * @author 刘晓凯
 * @date 2019-11-04-14:01
 *
 */
public interface SqlSessionFactory {

    /**
     * 用于打开一个新的SqlSession对象
     */
    SqlSession openSession();

}
