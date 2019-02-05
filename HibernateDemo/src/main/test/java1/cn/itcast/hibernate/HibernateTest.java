package java1.cn.itcast.hibernate;

import cn.itcast.entity.User;
import cn.itcast.utils.HibernateUtils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void getTest(){
        //SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //根据id查询
        //User user = (User) session.get(User.class,1);
        //user.setUsername("张三丰");
        User user = new User();
        user.setId(1);
        session.update(user);
        System.out.println(user);
        tx.commit();
        session.close();
        //sessionFactory.close();
    }

    @Test
    public void deleteTest(){  //先查询出对象获取id  根据id删除
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //删除（1:先get,再delete    2:创建要删除的对象，设置id值）
        User user = (User) session.get(User.class,1);
        session.delete(user);
        System.out.println(user);
        tx.commit();
        session.close();
    }


    /**
     * 验证hibernate一级缓存
     */
    @Test
    public void cacheTest(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //根据id=2查询
        User user1 = (User) session.get(User.class,2);
        System.out.println(user1);
        //根据id=2查询  查看输出了输出几次发送的sql语句
        User user2 = (User) session.get(User.class,2);
        System.out.println(user2);
        tx.commit();
        //session.close();
    }

    /**
     * 验证hibernate一级缓存特性
     */
    @Test
        public void catchChracterTest(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //根据id=2查询
        User user = (User) session.get(User.class,2);
        user.setUsername("一级缓存特性");  //会导致数据库更新
        tx.commit();
        //session.close();
    }

    /**
     * Query对象查询(hql语句)
     */
    @Test
    public void queryTest(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        try{
            //获取与本地线程绑定的session(项目中使用)
            //session = HibernateUtils.getLocalSession();
            session = sessionFactory.openSession();
            //开启事务
            tx = session.beginTransaction();
            //Query对象使用
            //1.创建Query对象,方法里写hql语句
            Query query = session.createQuery("from User");
            //2.调用Query方法得到结果
            List<User> users = query.list();
            for(User user:users){
                System.out.println(user);
            }
            //提交事务
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            //回滚事务
            tx.rollback();
        }finally{
            //本地线程绑定的session线程结束自动关闭，不需要手动关闭
            session.close();
            sessionFactory.close();
        }
    }


    /**
     * Criteria对象查询(调用方法，无需写语句)
     */
    @Test
    public void criteriaTest(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //Criteria对象使用
            Criteria criteria = session.createCriteria(User.class);
            List<User> users = criteria.list();
            for(User user: users){
                System.out.println(user);
            }
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.close();
            sessionFactory.close();
        }
    }

    /**
     * SQLQuery对象查询(创建对象传入sql语句)
     */
    @Test
    public void sqlQueryTest(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = null;
        Transaction tx = null;
        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //Criteria对象使用
            SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
            sqlQuery.addEntity(User.class);//设置结果集的元素类型，不设置为数据类型，用Object[]接收
            List<User> users = sqlQuery.list();
            for(User user: users){
                System.out.println(user);
            }
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.close();
            sessionFactory.close();
        }
    }

}
