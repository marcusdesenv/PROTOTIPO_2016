/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca.mb;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Avell
 */
public class Sessao {

    HttpSession sessao;

    public Sessao() {
        this.sessao =   (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
}
