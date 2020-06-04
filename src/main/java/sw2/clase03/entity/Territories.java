package sw2.clase03.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "territories")
public class Territories {

    @Id
    @NotBlank
    @Size(max = 20,message = "dale a lo mas 40 caracteres MENSAJE PRUEBA XD")
    private String territoryid;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 50,message = "dale a lo mas 40 caracteres MENSAJE PRUEBA XD")
    private String territorydescription;

    @ManyToOne
    @JoinColumn(name = "regionid")
    private Region region;

    public String getTerritoryid() {
        return territoryid;
    }

    public void setTerritoryid(String territoryid) {
        this.territoryid = territoryid;
    }

    public String getTerritorydescription() {
        return territorydescription;
    }

    public void setTerritorydescription(String territorydescription) {
        this.territorydescription = territorydescription;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
