package jpql;


import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1" );
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2" );
            member2.setTeam(teamA);
            em.persist(member2);


            Member member3 = new Member();
            member3.setUsername("member3" );
            member3.setTeam(teamB);
            em.persist(member3);


            em.flush();
            em.clear();

            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            System.out.println("resultCount = " + resultCount);
            /*List<MemberDto> result = em.createQuery("select new jpql.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                    .getResultList();
            MemberDto memberDto = result.get(0);
            System.out.println("memberDto.getUsername() = " + memberDto.getUsername());
            System.out.println("memberDto.getAge() = " + memberDto.getAge());*/

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }

}
