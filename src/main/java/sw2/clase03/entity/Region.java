package sw2.clase03.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "region")
public class Region {

    @Id
    @Digits(integer = 10, fraction = 0)
    @Min(value = 0)
    @Max(value = 2147483647)
    private Integer regionid;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 40)
    private String regiondescription;

    public Integer getRegionid() {
        return regionid;
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    public String getRegiondescription() {
        return regiondescription;
    }

    public void setRegiondescription(String regiondescription) {
        this.regiondescription = regiondescription;
    }
}
