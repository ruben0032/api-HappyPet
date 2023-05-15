package HappyPet.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "imagen")
@ToString
public class Imagen {

    @Id
    @Getter @Setter @Column(name = "id")
    private String public_id;

    @Getter @Setter @Column(name = "idproducto")
    private Integer idProducto;

    @Getter @Setter @Column(name = "src")
    private String src;

}
