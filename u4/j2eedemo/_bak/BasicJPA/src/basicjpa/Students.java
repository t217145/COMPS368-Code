/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicjpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="students")
@NamedQueries({
    @NamedQuery(name="std.myQuery", query="select s from Students s where s.stdName like '%Cheng'"),
    @NamedQuery(name="std.myQuery2", query="select s from Students s where s.stdName like :surname")
})
public class Students implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stdId", nullable=false)
    private int stdId;
    
    @Column(name="stdName", nullable=false, length=100)
    private String stdName;
    
    @Column(name="gender", nullable=false)
    private char gender;
    
    @Column(name="address", length=255)
    private String addr;

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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Students{" + "stdId=" + stdId + ", stdName=" + stdName + ", gender=" + gender + ", addr=" + addr + '}';
    }
}
