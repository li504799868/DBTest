package bean;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "address")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Address {

    @Id
    @GeneratedValue(generator = "addressId")
    @GenericGenerator(name = "addressId", strategy = "assigned")
    @Column(length = 3)
    private String addressId;

    private String location;

    public Address() {
    }

    public Address(String addressId, String location) {
        super();
        this.addressId = addressId;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
