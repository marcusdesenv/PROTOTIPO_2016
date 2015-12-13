/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca.mb;

import java.util.ArrayList;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Avell
 */
public class VerificarAcesso implements PhaseListener {

    HttpSession session;
    ArrayList<String> permissoes;

    @Override
    public void afterPhase(PhaseEvent event) {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        permissoes = (ArrayList<String>) session.getAttribute("permissoes");
        FacesContext facesContext = event.getFacesContext();
        String currentPage = null;
        try {
            currentPage = facesContext.getViewRoot().getViewId();
        } catch (Exception e) {
        }

        boolean isLoginPage = (currentPage.lastIndexOf("Login.xhtml") > -1);
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String currentUser = (String) session.getAttribute("usrUsuario");


        if (!isLoginPage && currentUser == null) {
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "Paginalogin");
        } else {

            if (permissoes != null) {
                if (!permissoes.contains(currentPage) && !"/acessoNegado.xhtml".equals(currentPage) && !"/Login.xhtml".equals(currentPage) && !"/template.xhtml".equals(currentPage)) {
                    NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                    nh.handleNavigation(facesContext, null, "paginaAcessoNegado");
                }
                if ("/Login.xhtml".equals(currentPage)) {
                    session.invalidate();
                }
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}