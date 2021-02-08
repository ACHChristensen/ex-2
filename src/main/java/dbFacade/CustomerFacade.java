package dbFacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    public CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }

    private List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("Select name from CUSTOMER c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    private int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT Count(id) AS from CUSTOMER c", Customer.class);
            return query.getFirstResult();
        } finally {
            em.close();
        }
    }

    private List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT Customer AS from CUSTOMER c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    private Customer addCustomer(String fName, String lName) {
        EntityManager em = emf.createEntityManager();
        Customer customer = new Customer(fName, lName);
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }

    }
}
