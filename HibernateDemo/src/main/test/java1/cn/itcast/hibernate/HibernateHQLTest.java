package java1.cn.itcast.hibernate;

import cn.itcast.manytomany.Role;
import cn.itcast.manytomany.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

public class HibernateHQLTest {

    //hql查询所有用户
    @Test
    public void hqlSelectAll(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //hql语句查询所有
        Query query = session.createQuery("from User");
        List<User> userList = query.list();
        for(User user:userList){
            System.out.println(user.getUser_name());
        }
        System.out.println(userList.size());
        tx.commit();
        session.close();
        sessionFactory.close();
    }


    //hql条件查询
    @Test
    public void tiaoJianSelect(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //创建query对象
        Query query = session.createQuery("from User u where u.user_id=?");
        //给模板设置参数
        query.setParameter(0,1); //与jdbc模不一样，？的位置是从0开始的
        List<User> userList = query.list();
        for(User user: userList){
            System.out.println(user.getUser_name() +" "+user.getUser_password());
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }


    //hql分页查询
    @Test
    public void pageSelect(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //创建query对象
        Query query = session.createQuery("from User");
        //设置分页参数
        query.setFirstResult(0);//开始位置
        query.setMaxResults(2);//每页记录数
        List<User> userList = query.list();
        for(User user: userList){
            System.out.println(user.getUser_name() +" "+user.getUser_password());
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }

    //hql投影查询
    @Test
    public void mappingSelect(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //创建query对象
        Query query = session.createQuery("select user_name,user_password from User");
        List<Object[]> userNameList = query.list();
        for(Object[] username: userNameList){
            System.out.println(username[0]+" "+username[1]);
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }

    //hql聚合函数的使用
    @Test
    public void countSelect(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //创建query对象
        Query query = session.createQuery("select count(*) from User");
        Long count = (Long) query.uniqueResult();
        int c = count.intValue();
        System.out.println("count: "+c);
        tx.commit();
        session.close();
        sessionFactory.close();
    }


    //QBC 查询所有
    @Test
    public void qbcSelectAll(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //创建Criteria对象
        Criteria criteria = session.createCriteria(User.class);
        List<User> userList = criteria.list();
        for(User user: userList){
            System.out.println(user);
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }


    //QBC 条件查询
    @Test
    public void criteriaSelect(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //创建Criteria对象
        Criteria criteria = session.createCriteria(User.class);
        //添加条件
        //criteria.add(Restrictions.eq("user_id",1));
        criteria.add(Restrictions.like("user_name","%张%"));
        List<User> userList = criteria.list();
        for(User user: userList){
            System.out.println(user);
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }


    //QBC 查询排序
    @Test
    public void criteriaOrderSelect(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //创建Criteria对象
        Criteria criteria = session.createCriteria(User.class);
        //添加排序条件
        criteria.addOrder(Order.desc("user_id"));
        List<User> userList = criteria.list();
        for(User user: userList){
            System.out.println(user);
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }


    //QBC 分页查询
    @Test
    public void criteriaPageSelect(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //创建Criteria对象
        Criteria criteria = session.createCriteria(User.class);
        //添加排序条件
        criteria.setFirstResult(0);
        criteria.setMaxResults(4);
        List<User> userList = criteria.list();
        for(User user: userList){
            System.out.println(user);
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }

    //QBC 统计查询
    @Test
    public void projectionSelect(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //创建Criteria对象
        Criteria criteria = session.createCriteria(User.class);
        //设置统计操作 Projections
        criteria.setProjection(Projections.rowCount());
        Long count = (Long)criteria.uniqueResult();
        int c = count.intValue();
        System.out.println("count :"+c);
        tx.commit();
        session.close();
        sessionFactory.close();
    }


    //QBC 离线查询
    @Test
    public void detacheSelect(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //创建detachedCriteria对象
        //Criteria criteria = session.createCriteria(User.class);
        DetachedCriteria detachedCriteria  = DetachedCriteria.forClass(User.class);
        detachedCriteria.addOrder(Order.desc("user_id"));
        //最终执行的时候才需要用到session
        Criteria criteria = detachedCriteria.getExecutableCriteria(session);
        List<User> userList = criteria.list();
        for(User user: userList){
            System.out.println(user);
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }
}
