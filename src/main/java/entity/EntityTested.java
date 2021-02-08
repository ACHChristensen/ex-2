package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Customer customer1 = new Customer("Michael", "Sørensen");
        Customer customer2 = new Customer("Lars", "Wittrup");
        Customer customer3 = new Customer("Anne", "Høyer");
        Customer customer4 = new Customer("Cathrine", "Christensen");
        
        try {
            em.getTransaction().begin();
            em.persist(customer1);
            em.persist(customer2);
            em.persist(customer3);
            em.persist(customer4);
            em.getTransaction().commit();
            System.out.println(customer1);
            System.out.println(customer2);
            System.out.println(customer3);
            System.out.println(customer4);
       	 
    	}finally{
        	em.close();
    	}
    }
}
