/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u4.j2ee;

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
@Table(name="testingContacts")
@NamedQueries({
    @NamedQuery(name="findByName", query="select c from Contacts c where c.name like :cname"),
    @NamedQuery(name="findByAddr", query="select c from Contacts c where c.addr like :cAddr")
})
public class Contacts implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(length=100, nullable=false)
    private String name;
    
    private String addr;

    public Contacts() {
    }

    public Contacts(int id, String name, String addr) {
        this.id = id;
        this.name = name;
        this.addr = addr;
    }
    
    

    public Contacts(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Contacts{" + "id=" + id + ", name=" + name + ", addr=" + addr + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
    
    
}
