import bean.mtm.Teacher;
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
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class ManyToManyTest {

    @Test()
    public void create() {
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(registry).buildMetadata();
        SchemaExport export = new SchemaExport();
        export.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

    @Test
    public void insertStudent() {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();

        Class sClass = new Class("5-3");
        session.update(sClass);

        Student student1 = new Student("005", sClass);
        Student student2 = new Student("006", sClass);
        Student student3 = new Student("007", sClass);
        Student student4 = new Student("008", sClass);

        Teacher teacher = new Teacher("001", "老师1");
        Teacher teacher2 = new Teacher("002", "老师2");

        Set<Teacher> studentSet = new HashSet<>();
        studentSet.add(teacher);
        studentSet.add(teacher2);
        student1.setTeachers(studentSet);

        session.update(student1);
        session.update(student2);
        session.update(student3);
        session.update(student4);

        Set<Student> set = new HashSet<>();
        set.add(student1);
        set.add(student2);
        set.add(student3);
        set.add(student4);
        teacher.setStudents(set);
        session.saveOrUpdate(teacher);
        session.getTransaction().commit();
    }

    @Test
    public void queryTeacher() {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Teacher teacher = session.get(Teacher.class, "001");
        System.out.println(teacher.getStudents().size());

        session.clear();
        Teacher teacher1 = session.get(Teacher.class, "001");
        System.out.println(teacher1.getStudents().size());

//        Student student = session.get(Student.class, "005");
//        System.out.println(student.getTeachers().size());

        session.getTransaction().commit();
    }
}
