/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cyrus Cheng
 */
@Entity
@Table(name = "items", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i")
    , @NamedQuery(name = "Items.findByItemId", query = "SELECT i FROM Items i WHERE i.itemId = :itemId")
    , @NamedQuery(name = "Items.findByItemCode", query = "SELECT i FROM Items i WHERE i.itemCode = :itemCode")
    , @NamedQuery(name = "Items.findByItemName", query = "SELECT i FROM Items i WHERE i.itemName = :itemName")
    , @NamedQuery(name = "Items.findByItemPrice", query = "SELECT i FROM Items i WHERE i.itemPrice = :itemPrice")
    , @NamedQuery(name = "Items.isExsits", query = "SELECT count(i) as cnt FROM Items i WHERE i.itemCode = :itemCode")
})
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "itemId")
    private Integer itemId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "itemCode")
    private String itemCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "itemName")
    private String itemName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemPrice")
    private double itemPrice;

    public Items() {
    }
    
    public Items(String itemCode, String itemName){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = 0.0;
    }

    public Items(Integer itemId) {
        this.itemId = itemId;
    }

    public Items(Integer itemId, String itemCode, String itemName, double itemPrice) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item Code [" + itemCode + "] inventory : " + itemPrice;
    }
    
}