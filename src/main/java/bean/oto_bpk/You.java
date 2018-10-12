package bean.oto_bpk;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class You {

    @Id
    @GeneratedValue
    private int yId;

    @OneToOne(mappedBy = "you")
    private Me me;

    public You() {
    }

    public int getyId() {
        return yId;
    }

    public void setyId(int yId) {
        this.yId = yId;
    }

    public Me getMe() {
        return me;
    }

    public void setMe(Me me) {
        this.me = me;
    }
}
