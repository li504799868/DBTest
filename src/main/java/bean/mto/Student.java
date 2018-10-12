package bean.mto;

import bean.mtm.Teacher;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(generator = "sid")
    @GenericGenerator(name = "sid", strategy = "assigned")
    @Column(length = 10)
    private String sid;

    /**
     * 学生与教室是多对一的关系
     * */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
    private Class sClass;

    /**
     * 学生与老师是多对多的关系
     * */
    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers;

    public Student(){}

    public Student(String sid, Class sClass) {
        this.sid = sid;
        this.sClass = sClass;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Class getsClass() {
        return sClass;
    }

    public void setsClass(Class sClass) {
        this.sClass = sClass;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
