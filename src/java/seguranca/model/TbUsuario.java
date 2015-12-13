package seguranca.model;
// Generated 13/12/2015 09:40:17 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * TbUsuario generated by hbm2java
 */
@Entity
@Table(name="tb_usuario"
    ,catalog="bd_prototipo"
    , uniqueConstraints = @UniqueConstraint(columnNames="usr_usuario") 
)
public class TbUsuario  implements java.io.Serializable {


     private Integer idtUsuario;
     private TbPerfil tbPerfil;
     private String usrUsuarioCad;
     private Date dtaUsuarioCad;
     private String nmeUsuario;
     private String usrUsuario;
     private String pwdUsuario;
     private boolean stsUsuario;
     private String cpfUsuario;
     private String endUsuario;
     private String telUsuario;
     private Date dtaNascUsuario;
     private Set<TbLog> tbLogs = new HashSet<TbLog>(0);

    public TbUsuario() {
    }

	
    public TbUsuario(TbPerfil tbPerfil, String usrUsuarioCad, Date dtaUsuarioCad, String nmeUsuario, String usrUsuario, String pwdUsuario, boolean stsUsuario, String cpfUsuario) {
        this.tbPerfil = tbPerfil;
        this.usrUsuarioCad = usrUsuarioCad;
        this.dtaUsuarioCad = dtaUsuarioCad;
        this.nmeUsuario = nmeUsuario;
        this.usrUsuario = usrUsuario;
        this.pwdUsuario = pwdUsuario;
        this.stsUsuario = stsUsuario;
        this.cpfUsuario = cpfUsuario;
    }
    public TbUsuario(TbPerfil tbPerfil, String usrUsuarioCad, Date dtaUsuarioCad, String nmeUsuario, String usrUsuario, String pwdUsuario, boolean stsUsuario, String cpfUsuario, String endUsuario, String telUsuario, Date dtaNascUsuario, Set<TbLog> tbLogs) {
       this.tbPerfil = tbPerfil;
       this.usrUsuarioCad = usrUsuarioCad;
       this.dtaUsuarioCad = dtaUsuarioCad;
       this.nmeUsuario = nmeUsuario;
       this.usrUsuario = usrUsuario;
       this.pwdUsuario = pwdUsuario;
       this.stsUsuario = stsUsuario;
       this.cpfUsuario = cpfUsuario;
       this.endUsuario = endUsuario;
       this.telUsuario = telUsuario;
       this.dtaNascUsuario = dtaNascUsuario;
       this.tbLogs = tbLogs;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idt_usuario", unique=true, nullable=false)
    public Integer getIdtUsuario() {
        return this.idtUsuario;
    }
    
    public void setIdtUsuario(Integer idtUsuario) {
        this.idtUsuario = idtUsuario;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_perfil", nullable=false)
    public TbPerfil getTbPerfil() {
        return this.tbPerfil;
    }
    
    public void setTbPerfil(TbPerfil tbPerfil) {
        this.tbPerfil = tbPerfil;
    }
    
    @Column(name="usr_usuario_cad", nullable=false, length=20)
    public String getUsrUsuarioCad() {
        return this.usrUsuarioCad;
    }
    
    public void setUsrUsuarioCad(String usrUsuarioCad) {
        this.usrUsuarioCad = usrUsuarioCad;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="dta_usuario_cad", nullable=false, length=10)
    public Date getDtaUsuarioCad() {
        return this.dtaUsuarioCad;
    }
    
    public void setDtaUsuarioCad(Date dtaUsuarioCad) {
        this.dtaUsuarioCad = dtaUsuarioCad;
    }
    
    @Column(name="nme_usuario", nullable=false, length=80)
    public String getNmeUsuario() {
        return this.nmeUsuario;
    }
    
    public void setNmeUsuario(String nmeUsuario) {
        this.nmeUsuario = nmeUsuario;
    }
    
    @Column(name="usr_usuario", unique=true, nullable=false, length=20)
    public String getUsrUsuario() {
        return this.usrUsuario;
    }
    
    public void setUsrUsuario(String usrUsuario) {
        this.usrUsuario = usrUsuario;
    }
    
    @Column(name="pwd_usuario", nullable=false, length=128)
    public String getPwdUsuario() {
        return this.pwdUsuario;
    }
    
    public void setPwdUsuario(String pwdUsuario) {
        this.pwdUsuario = pwdUsuario;
    }
    
    @Column(name="sts_usuario", nullable=false)
    public boolean isStsUsuario() {
        return this.stsUsuario;
    }
    
    public void setStsUsuario(boolean stsUsuario) {
        this.stsUsuario = stsUsuario;
    }
    
    @Column(name="cpf_usuario", nullable=false, length=14)
    public String getCpfUsuario() {
        return this.cpfUsuario;
    }
    
    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }
    
    @Column(name="end_usuario", length=100)
    public String getEndUsuario() {
        return this.endUsuario;
    }
    
    public void setEndUsuario(String endUsuario) {
        this.endUsuario = endUsuario;
    }
    
    @Column(name="tel_usuario", length=20)
    public String getTelUsuario() {
        return this.telUsuario;
    }
    
    public void setTelUsuario(String telUsuario) {
        this.telUsuario = telUsuario;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="dta_nasc_usuario", length=10)
    public Date getDtaNascUsuario() {
        return this.dtaNascUsuario;
    }
    
    public void setDtaNascUsuario(Date dtaNascUsuario) {
        this.dtaNascUsuario = dtaNascUsuario;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tbUsuario")
    public Set<TbLog> getTbLogs() {
        return this.tbLogs;
    }
    
    public void setTbLogs(Set<TbLog> tbLogs) {
        this.tbLogs = tbLogs;
    }




}


