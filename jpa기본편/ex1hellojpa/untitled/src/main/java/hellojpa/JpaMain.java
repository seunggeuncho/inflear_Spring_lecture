package hellojpa;

import javax.persistence.*;
import javax.swing.plaf.metal.MetalComboBoxEditor;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Address address = new Address("city", "street", "1000");

            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("member2");
            member.setHomeAddress(address);
            em.persist(member2);



            tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
