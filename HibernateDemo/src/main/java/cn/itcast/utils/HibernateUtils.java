package cn.itcast.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static Configuration cfg = null;
    private static  SessionFactory sessionFactory;
    //创建SessionFacotry对象
    {
        //加载核心配置文件
        cfg = new Configuration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    /**
     * 获取sessionFactory对象，此对象创建非常耗时，一个项目建议创建一个SessionFactory对象
     * web项目中创建好了就不要关闭，因为此方静态代码块之执行一次，关闭了人家就没法用了
     * @return
     */
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    /**
     * 获取与本地线程绑定的sesison对象(保证是单线程的）
     * 注意：前提是需要先在核心配置文件中配置
     * @return
     */
    public static Session getLocalSession(){
        return sessionFactory.getCurrentSession();
    }

    public static void main(String[] args ){

    }
}
