import bean.Address;
import bean.Car;
import hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.EnumSet;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SchemaCreateTest {

    @Test()
    public void create() {
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(registry).buildMetadata();
        SchemaExport export = new SchemaExport();
        export.create(EnumSet.of(TargetType.DATABASE), metadata);

    }

    @Test
    public void insertCar() {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();

        Address address = new Address("001", "北京");
        session.save(address);
        Car car = new Car("c1", "奔驰", address);
        session.save(car);
        session.getTransaction().commit();
    }

    @Test
    public void queryCar() {
        Session session = HibernateUtils.getSession();
        Address address = session.get(Address.class, "001");
        System.out.println(address.toString());

        session.clear();
        Address address1 = session.get(Address.class, "001");
        System.out.println(address1.toString());
    }

    @AfterClass
    public static void destroy() {
        HibernateUtils.destroy();
    }
}
