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
import java.util.List;

@Entity
@Table(name = "producto")
@ToString
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @Getter @Setter @Column(name = "descripcion")
    private String description;

    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idproducto", referencedColumnName = "id")
    private List<Imagen> images;

    @Getter @Setter @Column(name = "stock")
    private Integer inStock;

    @Getter @Setter @Column(name = "precio")
    private Integer price;

    @Getter @Setter @Column(name = "idtalla")
    private String sizes;

    @Getter @Setter @Column(name = "idtags")
    private String tags;

    @Getter @Setter @Column(name = "nombre")
    private String title;

    @Getter @Setter @Column(name = "idtipo")
    private String type;

    @Getter @Setter @Column(name = "idgenero")
    private String gender;

    @Getter @Setter @Column(name = "fechacreacion")
    private String date;

    @Getter @Setter @Column(name = "estado")
    private String status;

}
