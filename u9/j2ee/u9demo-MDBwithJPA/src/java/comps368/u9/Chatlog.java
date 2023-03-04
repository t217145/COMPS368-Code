/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u9;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cyrus
 */
@Entity
@Table(name = "chatlog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chatlog.findAll", query = "SELECT c FROM Chatlog c")
    , @NamedQuery(name = "Chatlog.findByLogId", query = "SELECT c FROM Chatlog c WHERE c.logId = :logId")
    , @NamedQuery(name = "Chatlog.findByMessage", query = "SELECT c FROM Chatlog c WHERE c.message = :message")
    , @NamedQuery(name = "Chatlog.findByCreateDtm", query = "SELECT c FROM Chatlog c WHERE c.createDtm = :createDtm")})
public class Chatlog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "log_id")
    private Integer logId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_dtm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDtm;

    public Chatlog() {
    }

    public Chatlog(Integer logId) {
        this.logId = logId;
    }

    public Chatlog(Integer logId, String message, Date createDtm) {
        this.logId = logId;
        this.message = message;
        this.createDtm = createDtm;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateDtm() {
        return createDtm;
    }

    public void setCreateDtm(Date createDtm) {
        this.createDtm = createDtm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chatlog)) {
            return false;
        }
        Chatlog other = (Chatlog) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "comps368.u9.Chatlog[ logId=" + logId + " ]";
    }
    
}
