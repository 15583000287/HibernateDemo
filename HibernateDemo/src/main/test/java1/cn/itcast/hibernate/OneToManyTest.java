package java1.cn.itcast.hibernate;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class OneToManyTest {
    @Test
    public void oneToManyTest(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
    }

    //演示hibernate一对多级联保存
    @Test
    public void addTest1(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try{
            Configuration cfg = new Configuration();
            cfg.configure();
            sessionFactory = cfg.buildSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //crud操作
            //1.添加一个客户，为这个客户添加一个联系人
            Customer customer = new Customer();
            customer.setCustName("传智播客");
            customer.setCustLevel("vip");
            customer.setCustSource("网络");
            customer.setCustPhone("110");
            customer.setCustMobile("999");

            LinkMan linkMan = new LinkMan();
            linkMan.setLkm_name("lucy");
            linkMan.setLkm_gender("男");
            linkMan.setLkm_phone("911");

            //2.在客户表示所有联系人，在联系人表示客户
            //建立客户对象和联系人对象关系
            //2.1把联系人对象放到客户实体类的set集合里面
            customer.getSetLinkMan().add(linkMan);
            //2.2把客户对象放到联系人里面
            linkMan.setCustomer(customer);

            //3.保存到数据库
            session.save(customer);
            session.save(linkMan);

            //提交事务
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    //演示hibernate一对多级联保存方式二(推荐)      <set name="setLinkMan" cascade="save-update">
    @Test
    public void addTest2(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try{
            Configuration cfg = new Configuration();
            cfg.configure();
            sessionFactory = cfg.buildSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //crud操作
            //1.添加一个客户，为这个客户添加一个联系人
            Customer customer = new Customer();
            customer.setCustName("百度");
            customer.setCustLevel("普通");
            customer.setCustSource("网络");
            customer.setCustPhone("110");
            customer.setCustMobile("999");

            LinkMan linkMan = new LinkMan();
            linkMan.setLkm_name("李彦宏");
            linkMan.setLkm_gender("男");
            linkMan.setLkm_phone("911");

            //2.在客户表示所有联系人，在联系人表示客户
            //建立客户对象和联系人对象关系
            //2.1把联系人对象放到客户实体类的set集合里面
            customer.getSetLinkMan().add(linkMan);

            //3.保存客户
            session.save(customer);

            //提交事务
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    //级联删除  <set name="setLinkMan" cascade="save-update,delete">
    @Test
    public void delteTest1() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            sessionFactory = cfg.buildSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //crud操作
            //1.根据id查询对象
            Customer customer = (Customer) session.get(Customer.class, 2);
            //2.调用删除方法
            session.delete(customer);

            //提交事务
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    //一对多修改 inserse属性  让一的一方放弃维护外键
    @Test
    public void updateTest(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try{
            Configuration cfg = new Configuration();
            cfg.configure();
            sessionFactory = cfg.buildSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //crud操作
            //将李彦宏设置为传智博客的联系人
            //1.将客户和联系人都查询出来
            Customer itcast = (Customer) session.get(Customer.class,1);
            LinkMan liyanhong = (LinkMan) session.get(LinkMan.class,3);
            //设置持久态对象值
            //2.将联系人放到客户里面（持久态对象的跟新会导致数据库跟新）
            itcast.getSetLinkMan().add(liyanhong);
            //把客户放到联系人里面
            liyanhong.setCustomer(itcast);

            //提交事务
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
}
