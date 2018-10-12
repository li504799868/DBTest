import bean.mto.Class;
import bean.mto.Student;
import bean.oto_bpk.Me;
import bean.oto_bpk.You;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.Action;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

import java.security.acl.Acl;
import java.util.EnumSet;

public class OneToOneBindTest {

    @Test()
    public void create() {
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(registry).buildMetadata();
        SchemaExport export = new SchemaExport();
        export.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

    @Test
    public void insert() {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();

        Me me = new Me(new You());
        session.save(me);
        session.getTransaction().commit();
    }

    @Test
    public void query() {
        Session session = HibernateUtils.getSession();
        Me me = session.get(Me.class, 1);
        System.out.println(me.getYou().getyId());
        You you = session.get(You.class, 2);
        System.out.println(you.getMe().getmId());
    }

    @Test
    public void insertStudent() {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();

        Class sClass = new Class("5-2");
        session.save(sClass);

        Student student1 = new Student("001", sClass);
        Student student2 = new Student("002", sClass);
        Student student3 = new Student("003", sClass);
        Student student4 = new Student("004", sClass);

        session.save(student1);
        session.save(student2);
        session.save(student3);
        session.save(student4);

        session.getTransaction().commit();
    }

    @Test
    public void queryClass() {
        Session session = HibernateUtils.getSession();
        Class sClass = session.get(Class.class, "5-2");
        System.out.println(sClass.getStudents().size());
    }
}
