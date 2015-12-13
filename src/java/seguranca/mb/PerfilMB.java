package seguranca.mb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;
import seguranca.dao.TaPrivilegioDAO;
import seguranca.dao.TbOpcaoDAO;
import seguranca.dao.TbPerfilDAO;
import seguranca.model.TaPrivilegio;
import seguranca.model.TbOpcao;
import seguranca.model.TbPerfil;

@ManagedBean
@ViewScoped
public class PerfilMB extends Sessao {

    private boolean incluirNovo;
    private int idtPessoa;
    private List<TbPerfil> perfis;
    private TbPerfil tbPerfil;
    private DualListModel<TbOpcao> opcoes;
    private List<TaPrivilegio> privilegios;
    TbPerfilDAO tbPerfilDAO = new TbPerfilDAO();

    public PerfilMB() {
        incluirNovo = false;


        idtPessoa = 0;
        filtrar();
    }

    public void filtrar() {
        perfis = tbPerfilDAO.consultarTodos();
    }

    public void novo() {
        idtPessoa = 0;
        tbPerfil = new TbPerfil();
        tbPerfil.setIdtPerfil(0);
        tbPerfil.setDtaPerfilCad(new Date(System.currentTimeMillis()));
        tbPerfil.setUsrPerfilCad((String) sessao.getAttribute("usrUsuario"));
        incluirNovo = true;
    }

    public void salvar() {
        if (incluirNovo == false) {
            tbPerfil.setDtaPerfilMod(new Date(System.currentTimeMillis()));
            tbPerfil.setUsrPerfilMod((String) sessao.getAttribute("usrUsuario"));
        }

        if (tbPerfil.getIdtPerfil() == 0) {
            //Incluir
            tbPerfil.setIdtPerfil(null);
            tbPerfilDAO.incluir(tbPerfil);
        } else {
            // Alterar
            tbPerfilDAO.alterar(tbPerfil);
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        incluirNovo = false;
        filtrar();
    }

    public void excluir() {
        idtPessoa = 0;
        if (tbPerfil.getIdtPerfil() != 0) {
            if (tbPerfilDAO.excluir(tbPerfil.getIdtPerfil())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Exclusão", "Exclusão efetuada com sucesso.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resultado da Exclusão", "Não foi possível excluir.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        filtrar();
    }

    public void salvarProc() {
        TaPrivilegioDAO taPrivilegioDAO = new TaPrivilegioDAO();
        taPrivilegioDAO.excluirTodosPorConsulta(tbPerfil.getIdtPerfil());
        for (Object item : opcoes.getTarget()) {
            TaPrivilegio taPrivilegio = new TaPrivilegio();
            taPrivilegio.setTbPerfil(tbPerfil);
            taPrivilegio.setTbOpcao((TbOpcao) item);
            taPrivilegio.setDtaPrivilegioCad(new Date(System.currentTimeMillis()));
            taPrivilegio.setUsrPrivilegioCad((String) sessao.getAttribute("usrUsuario"));
            taPrivilegioDAO.incluir(taPrivilegio);
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sucesso", "Opções incluídas com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public int getIdtPessoa() {
        return idtPessoa;
    }

    public void setIdtPessoa(int idtPessoa) {
        this.idtPessoa = idtPessoa;
    }

    public List<TbPerfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<TbPerfil> perfils) {
        this.perfis = perfils;
    }

    public TbPerfil getTbPerfil() {
        return tbPerfil;
    }

    public void setTbPerfil(TbPerfil tbPerfil) {
        this.tbPerfil = tbPerfil;
    }

    public boolean isIncluirNovo() {
        return incluirNovo;
    }

    public void setIncluirNovo(boolean incluirNovo) {
        this.incluirNovo = incluirNovo;
    }

    public List<TaPrivilegio> getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(List<TaPrivilegio> privilegios) {
        this.privilegios = privilegios;
    }

    public DualListModel<TbOpcao> getOpcoes() {
        List<TbOpcao> disp, escolhidos;
        if (tbPerfil != null) {
            TbOpcaoDAO tbOpcaoDAO = new TbOpcaoDAO();
            disp = tbOpcaoDAO.consultarNaoEstaConsulta(tbPerfil.getIdtPerfil());
            escolhidos = tbOpcaoDAO.consultarEstaEmConsulta(tbPerfil.getIdtPerfil());
        } else {
            disp = new ArrayList<TbOpcao>();
            escolhidos = new ArrayList<TbOpcao>();
        }
        opcoes = new DualListModel<TbOpcao>(disp, escolhidos);

        return opcoes;
    }

    public void setOpcoes(DualListModel<TbOpcao> opcoes) {
        this.opcoes = opcoes;
    }
}
