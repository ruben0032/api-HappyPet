package HappyPet.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "detallecompra")
@ToString
public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @Getter @Setter @Column(name = "idordencompra")
    private Integer idOrden;

    @Getter @Setter @Column(name = "idproducto")
    private Integer idproducto;

    @Getter @Setter @Column(name = "nombre")
    private String title;

    @Getter @Setter @Column(name = "talla")
    private String size;

    @Getter @Setter @Column(name = "genero")
    private String gender;

    @Getter @Setter @Column(name = "cantidad")
    private Integer quantity;

    @Getter @Setter @Column(name = "imagen")
    private String image;

    @Getter @Setter @Column(name = "precio")
    private Integer price;

}
