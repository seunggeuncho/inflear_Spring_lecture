package hellojpa;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Order extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    private Date orderDate;

    private OrderStatus status;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

}
