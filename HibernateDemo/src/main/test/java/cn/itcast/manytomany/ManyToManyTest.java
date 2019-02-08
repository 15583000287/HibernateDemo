package java.cn.itcast.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class ManyToManyTest {
    @Test
    public void manyToManyTest(){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        tx.commit();
        session.close();
        sessionFactory.close();
    }
}
