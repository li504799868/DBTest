package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate数据库连接管理类
 */
public class HibernateUtils {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() {
        if (session == null) {
            try {
                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure() // configures settings from hibernate.cfg.xml
                        .build();
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                session = sessionFactory.openSession();
            } catch (Exception e) {
                // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
                // so destroy it manually.
//                StandardServiceRegistryBuilder.destroy(registry);
                e.printStackTrace();
            }

        }
        return session;
    }

    public static void destroy() {
        if (session != null) {
            session.close();
        }
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }

}
