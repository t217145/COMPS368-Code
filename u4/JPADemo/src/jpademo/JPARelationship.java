package jpademo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPARelationship {
    
    private static final String CUSNAME = "Dummy Customer";
    
    public static void main(String args[]){
        //Prepare the Entity Manager
        EntityManagerFactory _emf = Persistence.createEntityManagerFactory("JPADemoPU");
        EntityManager _em = _emf.createEntityManager();
        
        //Create the Customer object
        Customer _newC = new Customer();
        _newC.setCusname(CUSNAME);
        _newC.setGender('M');
        //omit other field for simply
        
        //Prepare for creating account-customer relationship
        List<Account> _accts = new ArrayList<Account>();
        
        //Create new Account object - 1
        Account _acct1 = new Account();
        _acct1.setBalance(1000f);
        _acct1.setCustid(_newC);//link to new customer
        _accts.add(_acct1);//link to new account
        
        //Create new Account object - 2
        Account _acct2 = new Account();
        _acct2.setBalance(2000f);
        /* Try to uncomment the below line and then run SQL to retrieve 
           both customer and account in the MySQL Workbrench */
        //_acct2.setBalance(-10f); //uncomment this
        _acct2.setCustid(_newC);//link to new customer
        _accts.add(_acct2);//link to new account
        
        //Add the new Account List to the customer
        _newC.setAccountList(_accts);
        
        //Save to DB
        _em.getTransaction().begin();
        _em.persist(_newC);
        _em.getTransaction().commit();
        
        //Retrieve it
        System.out.println("*******  Retrieve after create *******");
        Query _q1 = _em.createNamedQuery("Customer.findByCusname");
        _q1.setParameter("customerName", CUSNAME);
        List<Customer> _rtn = _q1.getResultList();
        for(Customer c : _rtn){
            System.out.println(c.toString() + "***********************");
            for(Account a : c.getAccountList()){
                System.out.println("Account ID : " + a.getAcctid());
                System.out.println("Balance : " + a.getBalance());
                System.out.println(c.toString() + "-----");
            }
        }
        
        /*
        //Clean up delete the account and customer
        _em.getTransaction().begin();
        for(Customer c : _rtn){
            _em.remove(c);//just customer object is ok
        }
        _em.getTransaction().commit();
        
        //Retrieve it again
        System.out.println("\r\n\r\n*******  Retrieve after delete *******");
        Query _q2 = _em.createNamedQuery("Customer.findByCusname");
        _q2.setParameter("cusname", CUSNAME);
        _rtn = _q2.getResultList();
        for(Customer c : _rtn){
            System.out.println(c.toString() + "***********************");
            for(Account a : c.getAccountList()){
                System.out.println("Account ID : " + a.getAcctid());
                System.out.println("Balance : " + a.getBalance());
            }
        }        
        */
        
        //Close the context
        _em.close();
        _emf.close();
    }
    
}