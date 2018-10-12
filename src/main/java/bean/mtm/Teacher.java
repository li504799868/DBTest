package bean.mtm;

import bean.mto.Student;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Teacher {

    @Id
    @GeneratedValue(generator = "tid")
    @GenericGenerator(name = "tid", strategy = "assigned")
    @Column(length = 10)
    private String tid;

    private String tName;

    @ManyToMany
    @JoinTable(
            name = "teacher_student",
            joinColumns = {@JoinColumn(name = "tid")},
            inverseJoinColumns = {@JoinColumn(name = "sid")}
    )
    private Set<Student> students;

    public Teacher() {
    }

    public Teacher(String tid, String tName) {
        this.tid = tid;
        this.tName = tName;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
