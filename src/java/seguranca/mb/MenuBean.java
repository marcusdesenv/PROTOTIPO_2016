package seguranca.mb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import seguranca.dao.MenuDAO;
import seguranca.dao.OpcaoDAO;
import seguranca.model.TbMenu;
import seguranca.model.TbOpcao;

@ManagedBean
@ViewScoped
public class MenuBean {

    HttpSession session;
    MenuModel menu;
    /* MenuBean é a Classe Responsável pela Geração do Menu Conforme o Usuário 
     * Logado sendo a mesma atualizada constantemente durante a Execução do Prototipo.*/

    public MenuBean() {
        OpcaoDAO opcaoDAO = new OpcaoDAO();
        ArrayList<String> PermissoesAcesso = new ArrayList<String>();

        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        menu = (MenuModel) session.getAttribute("menu");

        MenuDAO menuDAO = new MenuDAO();

        if (menu == null) {
            String nmeUsuario = (String) session.getAttribute("usrUsuario");
            menu = new DefaultMenuModel();
            List<TbMenu> TbMenu = menuDAO.gerarMenu(nmeUsuario);

            for (Iterator<TbMenu> it = TbMenu.iterator(); it.hasNext();) {
                DefaultSubMenu submenu = new DefaultSubMenu();
                TbMenu tbMenu = it.next();
                submenu.setLabel(tbMenu.getSglMenu());
                List<TbOpcao> TbOpcao = opcaoDAO.gerarOpcoes(tbMenu.getIdtMenu(), nmeUsuario);
                for (Iterator<TbOpcao> it2 = TbOpcao.iterator(); it2.hasNext();) {
                    TbOpcao tbOpcao = it2.next();
                    DefaultMenuItem item = new DefaultMenuItem();
                    item.setValue(tbOpcao.getSglOpcao());
                    item.setUrl(tbOpcao.getUrlOpcao() + ".xhtml");
                    PermissoesAcesso.add("/" + tbOpcao.getUrlOpcao() + ".xhtml");
                    submenu.addElement(item);
                }
                menu.addElement(submenu);
            }
            session.setAttribute("permissoes", PermissoesAcesso);
            session.setAttribute("menu", menu);
        }
    }

    public MenuModel getModel() {
        return menu;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
