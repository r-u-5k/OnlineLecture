package jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            List<Member> resultList = em.createQuery("select m.username, m.age from Member m")
                    .getResultList();

            Object o = resultList.get(0);
            Object[] result = (Object[]) o;

            System.out.println("username: " + result[0]);
            System.out.println("age: " + result[1]);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        emf.close();
    }
}
