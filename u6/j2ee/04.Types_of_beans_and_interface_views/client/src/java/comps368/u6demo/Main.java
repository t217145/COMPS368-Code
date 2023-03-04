package comps368.u6demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Main {
    
    private static Context ctx;
    
    public static void main(String[] args) throws Exception {
        //Get the bean type
        int bType = -1;
        do{
            bType = BeanType();
        }while(bType < 1 || bType > 3);
        
        //Get the Bean
        MrBean beans = GetBean(bType);
        
        //Get the input and show it at first
        String input = GetInput("Input some text :");
        if(input != null && !input.trim().equals("")){
            if(bType == 3){
                getDtm("Before call bean [WRITE] lock method : ");
            }
            beans.SetStr(input);
            if(bType == 3){
                getDtm("After call bean [WRITE] lock method : ");
            }            
        }

        if(bType == 3){
            GetInput("Press any key to continue....");
            getDtm("Before call bean [READ] lock method : ");
        }
        System.out.println("Your input at first : " + beans.GetStr());
        if(bType == 3){
            getDtm("After call bean [READ] lock method : ");
        }
        
        //hold and wait for the other client to run
        GetInput("Press any key to continue....");
        
        //Show the message after the other client has input
        System.out.println("Your input now : " + beans.GetStr());

        beans.Quit();
    }

    private static int BeanType() throws Exception {
        int rtn = -1;
        
        System.out.println("Select the bean type : ");
        System.out.println("\t1. Stateful");
        System.out.println("\t2. Stateless");
        System.out.println("\t3. Singleton");
        String input = GetInput("Input the number :");
        try{
            rtn = Integer.parseInt(input);
        }catch(Exception e){
            
        }
        return rtn;
    }
    
    private static MrBean GetBean(int bType) throws Exception{
        ctx = new InitialContext();
        System.out.print("Bean type is : ");
        switch (bType) {
            case 1:
                System.out.print("Stateful\r\n");
                return (MrBean)ctx.lookup("java:global/ejb/FulBean");
            case 2:
                System.out.print("Stateless\r\n");
                return (MrBean)ctx.lookup("java:global/ejb/LessBean");
            default:
                System.out.print("Singleton\r\n");
                return (MrBean)ctx.lookup("java:global/ejb/SingBean");
        }
    }
    
    private static String GetInput(String msg, boolean lineBreak) throws Exception{
        Scanner _in = new Scanner(System.in);
        System.out.print(msg + (lineBreak ? "\r\n" : ""));
        return _in.nextLine();
    }
    
    private static String GetInput(String msg) throws Exception{
        return GetInput(msg, false);
    }
    
    private static void getDtm(String msg){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(msg + dtf.format(now));  
    }
}//end of class Main
