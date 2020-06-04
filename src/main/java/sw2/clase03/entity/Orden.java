package sw2.clase03.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "orders")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderid;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "employeeid")
    private Empleado empleado;


    private String orderdate;
    private String requireddate;
    private String shippeddate;

    @ManyToOne
    @JoinColumn(name = "shipvia")
    private Shipper shipper;

    @Digits(integer = 10, fraction = 4)
    @Positive
    private Double freight;

    @Size(max = 40,message = "dale a lo mas 40 caracteres MENSAJE PRUEBA XD")
    private String shipname;

    @Size(max = 60,message = "dale a lo mas 60 caracteres MENSAJE PRUEBA XD")
    private String shipaddress;

    @Size(max = 15,message = "dale a lo mas 15 caracteres MENSAJE PRUEBA XD")
    private String shipcity;

    @Size(max = 15,message = "dale a lo mas 15 caracteres MENSAJE PRUEBA XD")
    private String shipregion;

    @Size(max = 10,message = "dale a lo mas 10 caracteres MENSAJE PRUEBA XD")
    private String shippostalcode;

    @Size(max = 15,message = "dale a lo mas 15 caracteres MENSAJE PRUEBA XD")
    private String shipcountry;


    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getRequireddate() {
        return requireddate;
    }

    public void setRequireddate(String requireddate) {
        this.requireddate = requireddate;
    }

    public String getShippeddate() {
        return shippeddate;
    }

    public void setShippeddate(String shippeddate) {
        this.shippeddate = shippeddate;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public String getShipname() {
        return shipname;
    }

    public void setShipname(String shipname) {
        this.shipname = shipname;
    }

    public String getShipaddress() {
        return shipaddress;
    }

    public void setShipaddress(String shipaddress) {
        this.shipaddress = shipaddress;
    }

    public String getShipcity() {
        return shipcity;
    }

    public void setShipcity(String shipcity) {
        this.shipcity = shipcity;
    }

    public String getShipregion() {
        return shipregion;
    }

    public void setShipregion(String shipregion) {
        this.shipregion = shipregion;
    }

    public String getShippostalcode() {
        return shippostalcode;
    }

    public void setShippostalcode(String shippostalcode) {
        this.shippostalcode = shippostalcode;
    }

    public String getShipcountry() {
        return shipcountry;
    }

    public void setShipcountry(String shipcountry) {
        this.shipcountry = shipcountry;
    }
}
