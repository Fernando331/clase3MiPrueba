package sw2.clase03.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 45,message = "dale a lo mas 45 caracteres MENSAJE PRUEBA XD")
    private String nombre;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 45,message = "dale a lo mas 45 caracteres MENSAJE PRUEBA XD")
    private String apellido;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 8,message = "dale a lo mas 8 caracteres MENSAJE PRUEBA XD")
    private String dni;

    @Digits(integer = 3, fraction = 0)
    @Min(value = 0)
    @Max(value = 32767)
    private Integer edad;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 45,message = "dale a lo mas 45 caracteres MENSAJE PRUEBA XD")
    private String email;

    private String pwd;


    private Integer activo;


    @ManyToOne
    @JoinColumn(name = "idrol")
    private Rol rol;

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}