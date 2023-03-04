package comps368;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cyrus Cheng
 */
public class Students {
   private int stdId;
   private String stdName;

    public Students(int stdId, String stdName) {
        this.stdId = stdId;
        this.stdName = stdName;
    }

    public Students() {
    }
   
   

    public int getStdId() {
        return stdId;
    }

    public void setStdId(int stdId) {
        this.stdId = stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }
   
}
