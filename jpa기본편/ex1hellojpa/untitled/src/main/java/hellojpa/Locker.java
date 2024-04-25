package hellojpa;

import javax.persistence.*;

@Entity
public class Locker {

    @Column(name="LOCKER_ID")
    @Id @GeneratedValue
    private Long id;

    private String name;


}
