//package relatorios.mb;
//
//import java.util.Date;
//import java.util.HashMap;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//import util.Report;
//
//@ManagedBean
//@RequestScoped
//public class Relatorio04MB {
//
//    private Date dtaIni;
//    private Date dtaFim;
//
//    public Relatorio04MB() {
//    }
//
//    public void impRelatorio() {
//        if (dtaIni.before(dtaFim) || dtaIni.equals(dtaFim)) {
//            Report rep = new Report();
//            HashMap par = new HashMap();
//            par.put("dtaIni", dtaIni);
//            par.put("dtaFim", dtaFim);
//            rep.impRelPDFJasper("relatorio04.jasper", par, FacesContext.getCurrentInstance());
//        } else {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Período de datas incorreto.");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//    }
//
//    public Date getDtaIni() {
//        return dtaIni;
//    }
//
//    public void setDtaIni(Date dtaIni) {
//        this.dtaIni = dtaIni;
//    }
//
//    public Date getDtaFim() {
//        return dtaFim;
//    }
//
//    public void setDtaFim(Date dtaFim) {
//        this.dtaFim = dtaFim;
//    }
//}
