package seguranca.model;
// Generated 13/12/2015 09:40:17 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TaPrivilegio generated by hbm2java
 */
@Entity
@Table(name="ta_privilegio"
    ,catalog="bd_prototipo"
)
public class TaPrivilegio  implements java.io.Serializable {


     private Integer idtPrivilegio;
     private TbPerfil tbPerfil;
     private TbOpcao tbOpcao;
     private String usrPrivilegioCad;
     private Date dtaPrivilegioCad;
     private String usrPrivilegioMod;
     private Date dtaPrivilegioMod;

    public TaPrivilegio() {
    }

	
    public TaPrivilegio(TbPerfil tbPerfil, TbOpcao tbOpcao, String usrPrivilegioCad, Date dtaPrivilegioCad) {
        this.tbPerfil = tbPerfil;
        this.tbOpcao = tbOpcao;
        this.usrPrivilegioCad = usrPrivilegioCad;
        this.dtaPrivilegioCad = dtaPrivilegioCad;
    }
    public TaPrivilegio(TbPerfil tbPerfil, TbOpcao tbOpcao, String usrPrivilegioCad, Date dtaPrivilegioCad, String usrPrivilegioMod, Date dtaPrivilegioMod) {
       this.tbPerfil = tbPerfil;
       this.tbOpcao = tbOpcao;
       this.usrPrivilegioCad = usrPrivilegioCad;
       this.dtaPrivilegioCad = dtaPrivilegioCad;
       this.usrPrivilegioMod = usrPrivilegioMod;
       this.dtaPrivilegioMod = dtaPrivilegioMod;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idt_privilegio", unique=true, nullable=false)
    public Integer getIdtPrivilegio() {
        return this.idtPrivilegio;
    }
    
    public void setIdtPrivilegio(Integer idtPrivilegio) {
        this.idtPrivilegio = idtPrivilegio;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_perfil", nullable=false)
    public TbPerfil getTbPerfil() {
        return this.tbPerfil;
    }
    
    public void setTbPerfil(TbPerfil tbPerfil) {
        this.tbPerfil = tbPerfil;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cod_opcao", nullable=false)
    public TbOpcao getTbOpcao() {
        return this.tbOpcao;
    }
    
    public void setTbOpcao(TbOpcao tbOpcao) {
        this.tbOpcao = tbOpcao;
    }
    
    @Column(name="usr_privilegio_cad", nullable=false, length=20)
    public String getUsrPrivilegioCad() {
        return this.usrPrivilegioCad;
    }
    
    public void setUsrPrivilegioCad(String usrPrivilegioCad) {
        this.usrPrivilegioCad = usrPrivilegioCad;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="dta_privilegio_cad", nullable=false, length=10)
    public Date getDtaPrivilegioCad() {
        return this.dtaPrivilegioCad;
    }
    
    public void setDtaPrivilegioCad(Date dtaPrivilegioCad) {
        this.dtaPrivilegioCad = dtaPrivilegioCad;
    }
    
    @Column(name="usr_privilegio_mod", length=20)
    public String getUsrPrivilegioMod() {
        return this.usrPrivilegioMod;
    }
    
    public void setUsrPrivilegioMod(String usrPrivilegioMod) {
        this.usrPrivilegioMod = usrPrivilegioMod;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="dta_privilegio_mod", length=10)
    public Date getDtaPrivilegioMod() {
        return this.dtaPrivilegioMod;
    }
    
    public void setDtaPrivilegioMod(Date dtaPrivilegioMod) {
        this.dtaPrivilegioMod = dtaPrivilegioMod;
    }




}


