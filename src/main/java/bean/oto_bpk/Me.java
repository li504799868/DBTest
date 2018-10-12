package bean.oto_bpk;

import javax.persistence.*;

@Entity
public class Me {

    @Id
    @GeneratedValue
    private int mId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "yId", unique = true)
    private You you;

    public Me() {
    }

    public Me(You you) {
        this.you = you;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public You getYou() {
        return you;
    }

    public void setYou(You you) {
        this.you = you;
    }
}
