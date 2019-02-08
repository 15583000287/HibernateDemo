package java1.cn.itcast.hibernate;

import cn.itcast.manytomany.Role;
import cn.itcast.manytomany.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class ManyToManyTest {
        //多对多级联添加
        @Test
        public void manyToManyTest(){
            Configuration cfg = new Configuration();
            cfg.configure();
            SessionFactory sessionFactory = cfg.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            //级联添加
            User user1 = new User();
            user1.setUser_name("张三");
            user1.setUser_password("123456");

        User user2 = new User();
        user2.setUser_name("李四");
        user2.setUser_password("123456");

        Role role1 = new Role();
        role1.setRole_name("经理");
        Role role2 = new Role();
        role2.setRole_name("CEO");
        Role role3 = new Role();
        role3.setRole_name("董事长");

        //将角色添加用户set集合
        user1.getSetRole().add(role1);
        user1.getSetRole().add(role2);
        user2.getSetRole().add(role2);
        user2.getSetRole().add(role3);

        //保存user对象
        session.save(user1);
        session.save(user2);

        tx.commit();
        session.close();
        sessionFactory.close();
    }


    //多对多级联添加      让张三有司机角色
    @Test
    public void manyToManyAdd(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //1.先将张三和司机查询出来
        User zhangsan = (User) session.get(User.class,1);
        Role driver = (Role) session.get(Role.class,4);
        //2.将司机添加到用户的set集合(删除也是一样，使用remove方法，将对象从set集合移除)
        zhangsan.getSetRole().add(driver);
        //3.保存
        session.save(zhangsan);

        tx.commit();
        session.close();
        sessionFactory.close();
    }


    //多对多级联删除      删除张三的司机角色
    @Test
    public void manyToManyDelete(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //1.先将张三和司机查询出来
        User zhangsan = (User) session.get(User.class,1);
        Role driver = (Role) session.get(Role.class,4);
        //2.将司机角色从set集合移除)
        zhangsan.getSetRole().remove(driver);
        //3.保存
        session.save(zhangsan);

        tx.commit();
        session.close();
        sessionFactory.close();
    }
}
