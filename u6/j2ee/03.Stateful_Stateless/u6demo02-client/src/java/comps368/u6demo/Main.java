/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6demo;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author Cyrus Cheng
 */
public class Main {     
    
    public static void main(String[] args) throws Exception{
        System.out.println("Testing Stateless................");
        TestStateLess();
        System.out.println("Testing Stateful................");
        TestStateFul();
        
        Context ctx = new InitialContext();
        
        Test2Remote b = (Test2Remote)ctx.lookup("java:global/u6demo02a-ejb/Test2");
        b.Read();
    }
    
    private static void TestStateLess() throws Exception{
        Context ctx = new InitialContext();
        LessBeanRemote[] bs = new LessBeanRemote[10];
        for(int i=0;i<10;i++){
            bs[i] = (LessBeanRemote)ctx.lookup("java:global/u6demo02-ejb/LessBean");
            bs[i].SetStr("String " + (i+1));
        }

        for(int i=0;i<10;i++){
            System.out.println(bs[i].GetStr());
        }
    }
    
    private static void TestStateFul() throws Exception{
        Context ctx = new InitialContext();
        FulBeanRemote[] bs = new FulBeanRemote[10];
        for(int i=0;i<10;i++){
            bs[i] = (FulBeanRemote)ctx.lookup("java:global/u6demo02-ejb/FulBean");
            bs[i].SetStr("String " + (i+1));
        }

        for(int i=0;i<10;i++){
            System.out.println(bs[i].GetStr());
        }
    }    
    
}
