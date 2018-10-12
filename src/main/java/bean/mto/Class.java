package bean.mto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Class {

    public Class() {

    }

    public Class(String cid) {
        this.cid = cid;
    }

    @Id
    @GeneratedValue(generator = "cid")
    @GenericGenerator(name = "cid", strategy = "assigned")
    @Column(length = 3)
    private String cid;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cid")
    private Set<Student> students;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
