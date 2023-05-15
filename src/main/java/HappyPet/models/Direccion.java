package HappyPet.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "direccionenvio")
@ToString
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @Getter @Setter @Column(name = "nombre")
    private String firstName;

    @Getter @Setter @Column(name = "apellido")
    private String lastName;

    @Getter @Setter @Column(name = "direccion")
    private String address;

    @Getter @Setter @Column(name = "direccionopcional")
    private String address2;

    @Getter @Setter @Column(name = "ciudad")
    private String city;

    @Getter @Setter @Column(name = "telefono")
    private String phone;
}
