package seguranca.mb;

import java.sql.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import seguranca.dao.TbMenuDAO;
import seguranca.dao.TbOpcaoDAO;
import seguranca.model.TbMenu;
import seguranca.model.TbOpcao;

@ManagedBean
@ViewScoped
public class OpcaoMB extends Sessao {

    private boolean incluirNovo;
    private int idtMenu;
    private List<TbOpcao> opcaos;
    private TbOpcao tbOpcao;
    private List<TbMenu> menus;
    TbOpcaoDAO tbOpcaoDAO = new TbOpcaoDAO();

    public OpcaoMB() {
        incluirNovo = false;


        idtMenu = 0;
        tbOpcao = new TbOpcao();
        TbMenuDAO tbMenuDAO = new TbMenuDAO();
        menus = tbMenuDAO.consultarTodos();
        filtrar();
    }

    public void filtrar() {
        opcaos = tbOpcaoDAO.consultarPorIdtMenu(idtMenu);
    }

    public void novo() {
        incluirNovo = true;
        idtMenu = 0;
        tbOpcao = new TbOpcao();
        tbOpcao.setIdtOpcao(0);
        tbOpcao.setDtaOpcaoCad(new Date(System.currentTimeMillis()));
        tbOpcao.setUsrOpcaoCad((String) sessao.getAttribute("usrUsuario"));
    }

    public void salvar() {
        if (incluirNovo == false) {
            tbOpcao.setDtaOpcaoMod(new Date(System.currentTimeMillis()));
            tbOpcao.setUsrOpcaoMod((String) sessao.getAttribute("usrUsuario"));
        }
        TbMenuDAO tbMenuDAO = new TbMenuDAO();
        tbOpcao.setTbMenu(tbMenuDAO.consultarPorIdt(idtMenu));
        if (tbOpcao.getIdtOpcao() == 0) {
            //Incluir
            tbOpcao.setIdtOpcao(null);
            tbOpcaoDAO.incluir(tbOpcao);
        } else {
            // Alterar
            tbOpcaoDAO.juntar(tbOpcao);
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        idtMenu = tbOpcao.getTbMenu().getIdtMenu();
        incluirNovo = false;
        filtrar();
    }

    public void excluir() {
        idtMenu = 0;
        if (tbOpcao.getIdtOpcao() != 0) {
            if (tbOpcaoDAO.excluir(tbOpcao.getIdtOpcao())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Exclusão", "Exclusão efetuada com sucesso.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resultado da Exclusão", "Não foi possível excluir.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        filtrar();
    }

    public int getIdtMenu() {
        return idtMenu;
    }

    public void setIdtMenu(int idtMenu) {
        this.idtMenu = idtMenu;
    }

    public List<TbOpcao> getOpcaos() {
        return opcaos;
    }

    public void setOpcaos(List<TbOpcao> opcaos) {
        this.opcaos = opcaos;
    }

    public TbOpcao getTbOpcao() {
        return tbOpcao;
    }

    public void setTbOpcao(TbOpcao tbOpcao) {
        this.tbOpcao = tbOpcao;
        idtMenu = tbOpcao.getTbMenu().getIdtMenu();
    }

    public List<TbMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<TbMenu> menus) {
        this.menus = menus;
    }

    public boolean isIncluirNovo() {
        return incluirNovo;
    }

    public void setIncluirNovo(boolean incluirNovo) {
        this.incluirNovo = incluirNovo;
    }
}
