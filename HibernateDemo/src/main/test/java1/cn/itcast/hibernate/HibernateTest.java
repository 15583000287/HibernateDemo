package java1.cn.itcast.hibernate;

import cn.itcast.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {
    @Test
    public void testAdd(){
        //1.加载hibernate核心配置文件
        //到src下面找到名称是hibernate.cfg.xml文件
        //在hibernate里面封装对象
        Configuration cfg = new Configuration();
        cfg.configure();
        //2.创建SessionFactory对象
        //读取hibernate核心配置文件内容，创建sessionFactory
        //此过程，根据映射关系，在配置数据库里面创建好表
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //3.使用SessionFactory创建session对象,类似于jdbc的 Connection
        Session session = sessionFactory.openSession();  //save update saveOrUpdate 增加和修改   get load 根据主键查询
                                                        // createQuery() 和createSQLQuery（）用于数据库操作对象
                                                        //creatCriteria() 条件查询
                                                        // 这一步，帮我们创建表(前提加了配置)
                                                        //创建SessionFactory对象特别耗时，一个项目建议创建一个，利用工具类创建(static代码块)
        //4.开启事务 （Transaction事务对象）  提交：commit()  rollback()
        Transaction tx = session.beginTransaction();
        //5.写具体逻辑crud操作
        User user = new User();
        user.setUsername("王旭");
        user.setPassword("747471");
        //调用session里面的方法
        session.saveOrUpdate(user);  //此处异常应该执行回滚
        //Object obj = session.get(User.class,1);
        //session.persist(obj); //jpa提供的
        //ystem.out.println(obj);
        //6.提交事务
        tx.commit();
        //7.关闭资源
        session.close();
        sessionFactory.close();
    }
}
