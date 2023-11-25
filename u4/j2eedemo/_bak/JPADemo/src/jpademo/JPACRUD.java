package jpademo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

public class JPACRUD {
    
    public static void main(String args[]){
        try{
            JPAHandler _h = new JPAHandler();
            
            //1. prepare EntityManager
            _h.connect();
            
            //2. Get user instruction what he want to do
            String _str;
            while(true){
                _str = _h.getInput("(C)reate\t(R)etrieve\t(U)pdate\t(D)elete\tE(x)it\r\n");
                
                //2a. Handle exceptional input
                if(_str == null || _str.isEmpty()){
                    continue;
                } else if (_str.toLowerCase().equals("x")) {
                    break;
                }
                
                //2b. Handle CRUD according to user input
                _str = _str.toLowerCase();
                switch(_str){
                    case "c":
                        _h.create();
                        break;
                    case "r":
                        _h.retrieve();
                        break;
                    case "u":
                        _h.update();
                        break;
                    case "d":
                        _h.delete();
                        break;
                }
            }
            
            //3. close the connection
            _h.close();
        }catch(Exception e){
        }
    }//end of main thread
    
}//end of class JPACRUD

class JPAHandler{
    
    private EntityManagerFactory _emf;
    private EntityManager _em;
    private final BufferedReader _br;
    
    public JPAHandler(){
        this._br = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public void create(){
        //1. Prepare a new Customer Objects
        Customer _newC = new Customer();
        //2. Get input from user
        String _input = "";
        //2a. Name
        do{
            _input = getInput("Input user name : ");
            if(_input == null || _input.isEmpty()){
                System.out.println("Name cannot be empty");
            } else {
                break;
            }
        }while(true);
        _newC.setCusname(_input);
        //2b. Gender
        _input = getInput("Input user gender : ");
        _newC.setGender(_input.equals("M") ? 'M' : 'F');
        //2c. Address
        _input = getInput("Input user address : ");
        _newC.setAddr((_input == null || _input.isEmpty()) ? "" : _input);
        //2d. Date Of Birth
        _input = getInput("Input user date of birth (yyyy-mm-dd) : ");
        Date _dob = new Date();
        try{
            _dob = new SimpleDateFormat("yyyy-MM-dd").parse(_input);
        }catch(ParseException e){
            System.out.println("Incorrect Date Format");
        }
        _newC.setDob(_dob);
        //3. begin transaction
        _em.getTransaction().begin();        
        //4. Persist and save to DB
        _em.persist(_newC);
        //5. Commit the transaction
        _em.getTransaction().commit();
    }
    
    public void retrieve(){
        //2. Get user instruction what he want to do
        String _str;
        _str = getInput("Retrieve By : G(e)t All\t(I)D\t(N)ame\t(G)ender\t(A)ddress\r\n");

        //2a. Handle exceptional input
        if(_str == null || _str.isEmpty()){
            System.out.println("Incorrect Input");
            return;
        }

        //2b. Handle CRUD according to user input
        _str = _str.toLowerCase();
        switch(_str){
            case "e":
                retrieveAll();
                break;
            case "i":
                retrieveById();
                break;
            case "n":
                retrieveByName();
                break;
            case "g":
                retrieveByGender();
                break;
            case "a":
                retrieveByAddress();
                break;
            default:
                System.out.println("Incorrect Input");
        }
    }
    
    private void retrieveAll(){
        //Prepare the query
        Query _q = _em.createNamedQuery("Customer.findAll");
        //No need to set Parameter
        //Get the result
        List<Customer> _rtn = _q.getResultList();
        //Output to Screen
        for(Customer _c : _rtn){
            System.out.println(_c.toString());
        }
    }
    
    private void retrieveById(){
        //1. Get the customer object by ID
        String _input;
        _input = getInput("Please input customer ID : ");
        int _id = Integer.parseInt(_input);
        Customer _c = _em.find(Customer.class, _id);
        if(_c == null){
            System.out.println("Customer with ID [" + _id + "] not found");
        } else {
            System.out.println(_c.toString());
        }
    }
    
    private void retrieveByName(){
        //1. Get the customer object by ID
        String _input;
        _input = getInput("Please input customer Name : ");
        
        //Prepare the query
        //Query _q = _em.createNamedQuery("Customer.findByCusname");
        Query _q = _em.createQuery("select c from Customer c where c.cusname like :cusname");
        //Set Parameter
        _q.setParameter("cusname", _input);
        //Get the result
        List<Customer> _rtn = _q.getResultList();
        //Output to Screen
        for(Customer _c : _rtn){
            System.out.println(_c.toString());
        }
    }     
    
    private void retrieveByGender(){
        //1. Get the customer object by ID
        String _input;
        _input = getInput("Please input customer Gender : ");
        
        //Prepare the query
        Query _q = _em.createNamedQuery("Customer.findByGender");
        //Set Parameter
        _q.setParameter("gender", _input.charAt(0));
        //Get the result
        List<Customer> _rtn = _q.getResultList();
        //Output to Screen
        for(Customer _c : _rtn){
            System.out.println(_c.toString());
        }
    }
    
     private void retrieveByAddress(){
        //1. Get the customer object by ID
        String _input;
        _input = getInput("Please input customer Address : ");
        
        //Prepare the query
        Query _q = _em.createNamedQuery("Customer.findByAddr");
        //Set Parameter
        _q.setParameter("addr", _input);
        //Get the result
        List<Customer> _rtn = _q.getResultList();
        //Output to Screen
        for(Customer _c : _rtn){
            System.out.println(_c.toString());
        }
    }    
    
    public void update(){
        //1. Get the customer object by ID
        String _input;
        _input = getInput("Please input customer ID : ");
        int _id = Integer.parseInt(_input);
        Customer _c = _em.find(Customer.class, _id);
        if(_c == null){
            System.out.println("Customer with ID [" + _id + "] not found");
            return;
        }
        //2. Start the transaction
        _em.getTransaction().begin();
        //3. Get input from user        
        //3a. Name
        do{
            _input = getInput("Input user name : ");
            if(_input == null || _input.isEmpty()){
                System.out.println("Name cannot be empty");
            } else {
                break;
            }
        }while(true);
        _c.setCusname(_input);
        //3b. Gender
        _input = getInput("Input user gender : ");
        _c.setGender(_input.equals("M") ? 'M' : 'F');
        //3c. Address
        _input = getInput("Input user address : ");
        _c.setAddr((_input == null || _input.isEmpty()) ? "" : _input);
        //3d. Date Of Birth
        _input = getInput("Input user date of birth (yyyy-mm-dd) : ");
        Date _dob = new Date();
        try{
            _dob = new SimpleDateFormat("yyyy-MM-dd").parse(_input);
        }catch(ParseException e){
            System.out.println("Incorrect Date Format");
        }
        _c.setDob(_dob);
        //4. Commit the transaction
        _em.getTransaction().commit();
    }

    public void delete(){
        //1. Get the customer object by ID
        String _input;
        _input = getInput("Please input customer ID : ");
        int _id = Integer.parseInt(_input);
        Customer _c = _em.find(Customer.class, _id);
        if(_c == null){
            System.out.println("Customer with ID [" + _id + "] not found");
            return;
        }
        //2. Start the transaction
        _em.getTransaction().begin();
        _em.remove(_c);
        //4. Commit the transaction
        _em.getTransaction().commit();
    }    
    
    /* Util method */
    public void connect(){
        _emf = Persistence.createEntityManagerFactory("JPADemoPU");
        _em = _emf.createEntityManager();
    }
    
    public void close(){
        try{
            _em.close();
            _emf.close();
            _br.close();
        }catch(IOException _ioe){
            
        }
    }
    
    public String getInput(String msg){
        String _rtn = "";
        System.out.print(msg);
        try{
            _rtn = _br.readLine();
        }catch(IOException _ioe){
        }
        return _rtn;
    }
}