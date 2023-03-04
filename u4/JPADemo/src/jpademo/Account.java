/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpademo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cyrus
 */
@Entity
@Table(name = "ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByAcctid", query = "SELECT a FROM Account a WHERE a.acctid = :acctid")
    , @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance >= :balance")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACCTID")
    private Integer acctid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "BALANCE")
    private float balance;
    @JoinColumn(name = "CUSTID", referencedColumnName = "CUSTID")
    @ManyToOne(optional = false)
    private Customer custid;

    public Account() {
    }

    public Account(Integer acctid) {
        this.acctid = acctid;
    }

    public Account(Integer acctid, float balance) {
        this.acctid = acctid;
        this.balance = balance;
    }

    public Integer getAcctid() {
        return acctid;
    }

    public void setAcctid(Integer acctid) {
        this.acctid = acctid;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Customer getCustid() {
        return custid;
    }

    public void setCustid(Customer custid) {
        this.custid = custid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acctid != null ? acctid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.acctid == null && other.acctid != null) || (this.acctid != null && !this.acctid.equals(other.acctid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpademo.Account[ acctid=" + acctid + " ]";
    }
    
}
