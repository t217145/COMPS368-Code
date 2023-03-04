/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpademo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.text.SimpleDateFormat;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

/**
 *
 * @author cyrus
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    , @NamedQuery(name = "Customer.findByCustid", query = "SELECT c FROM Customer c WHERE c.custid = :custid")
    , @NamedQuery(name = "Customer.findByCusname", query = "SELECT c FROM Customer c WHERE c.cusname like :customerName")
    , @NamedQuery(name = "Customer.findByGender", query = "SELECT c FROM Customer c WHERE c.gender = :gender")
    , @NamedQuery(name = "Customer.findByAddr", query = "SELECT c FROM Customer c WHERE c.addr like :addr")
    , @NamedQuery(name = "Customer.findByDob", query = "SELECT c FROM Customer c WHERE c.dob = :dob")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CUSTID")
    private Integer custid;
    @Basic(optional = false)
    @Column(name = "CUSNAME")
    private String cusname;
    @Basic(optional = false)
    @Column(name = "GENDER")
    private Character gender;
    @Basic(optional = false)
    @Column(name = "ADDR")
    private String addr = "";
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custid")
    private List<Account> accountList;
    
    @Transient
    private double ttlAmt;

    public Customer() {
    }

    public Customer(Integer custid) {
        this.custid = custid;
    }
    
    @PostLoad
    private void SetTtlAmt(){
        //add all account amount
    }

    public Customer(Integer custid, String cusname, Character gender, String addr) {
        this.custid = custid;
        this.cusname = cusname;
        this.gender = gender;
        this.addr = addr;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Date getDob() {
        return dob == null ? new Date() : dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custid != null ? custid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.custid == null && other.custid != null) || (this.custid != null && !this.custid.equals(other.custid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID : " + custid + "\r\n"
                + "Name : " + cusname + "\r\n"
                + "Gender : " + gender + "\r\n"
                + "Address : " + addr + "\r\n"
                + "Date of Birth : " + new SimpleDateFormat("yyyy-MM-dd").format(getDob()) + "\r\n";
    }
    
}
