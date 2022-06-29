package HappyPet.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "ordencompra")
@ToString
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Integer id;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idusuario")
    private Usuario user;

    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idordencompra", referencedColumnName = "id")
    private List<Detalle> orderItems;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iddireccion")
    private Direccion shippingAddress;


    @Getter @Setter @Column(name = "cantidad")
    private Integer numberOfItems;

    @Getter @Setter @Column(name = "total")
    private Double total;

    @Getter @Setter @Column(name = "subtotal")
    private Double subTotal;

    @Getter @Setter @Column(name = "impuesto")
    private Double tax;

    @Getter @Setter @Column(name = "fechapago")
    private String paidAt;

    @Getter @Setter @Column(name = "idtransaccion")
    private String idPaypal;

    @Getter @Setter @Column(name = "idestadopago")
    private String isPaid;

}
