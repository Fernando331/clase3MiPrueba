package sw2.clase03.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productid;
    
    @Column(nullable = false)
    @NotBlank
    @Size(max = 40,message = "dale a lo mas 40 caracteres MENSAJE PRUEBA XD")
    private String productname;
    
    @ManyToOne
    @JoinColumn(name = "supplierid")
    private Suppliers supplier;
    
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Categories category;

    @Size(max = 20)
    private String quantityperunit;
    
    @Digits(integer = 10, fraction = 4)
    @Positive
    private Double unitprice;

    @Digits(integer = 5, fraction = 0)
    @Min(value = 0)
    @Max(value = 32767)
    private Integer unitsinstock;

    @Digits(integer = 5, fraction = 0)
    @Min(value = 0)
    @Max(value = 32767)
    private Integer unitsonorder;

    @Digits(integer = 5, fraction = 0)
    @Min(value = 0)
    @Max(value = 32767)
    private Integer reorderlevel;

    @Digits(integer = 1, fraction = 0)
    @Min(value = 0)
    @Max(value = 1)
    private Integer discontinued;


    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }


    public String getQuantityperunit() {
        return quantityperunit;
    }

    public void setQuantityperunit(String quantityperunit) {
        this.quantityperunit = quantityperunit;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getUnitsinstock() {
        return unitsinstock;
    }

    public void setUnitsinstock(Integer unitsinstock) {
        this.unitsinstock = unitsinstock;
    }

    public Integer getUnitsonorder() {
        return unitsonorder;
    }

    public void setUnitsonorder(Integer unitsonorder) {
        this.unitsonorder = unitsonorder;
    }

    public Integer getReorderlevel() {
        return reorderlevel;
    }

    public void setReorderlevel(Integer reorderlevel) {
        this.reorderlevel = reorderlevel;
    }

    public Integer getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Integer discontinued) {
        this.discontinued = discontinued;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
