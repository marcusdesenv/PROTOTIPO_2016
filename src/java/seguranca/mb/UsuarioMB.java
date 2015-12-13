package seguranca.mb;

import java.sql.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import seguranca.dao.TbPerfilDAO;
import seguranca.dao.TbUsuarioDAO;
import seguranca.model.TbPerfil;
import seguranca.model.TbUsuario;

@ManagedBean
@ViewScoped
public class UsuarioMB extends Sessao {

    private int idtPerfil;
    private List<TbUsuario> usuarios;
    private TbUsuario tbUsuario;
    private List<TbPerfil> perfis;
    private String nomeUsuario;
    TbUsuarioDAO tbUsuarioDAO;
    TbPerfilDAO tbPerfilDAO;

    public UsuarioMB() {



        idtPerfil = 0;

        tbUsuario = new TbUsuario();
        tbPerfilDAO = new TbPerfilDAO();
        tbUsuarioDAO = new TbUsuarioDAO();
        perfis = tbPerfilDAO.consultarTodos();
        filtrar();
    }

    public void filtrar() {

        usuarios = tbUsuarioDAO.consultarPorIdtPerfilePorNome(idtPerfil, nomeUsuario);
    }

    public void novo() {
        idtPerfil = 0;
        nomeUsuario = "";
        tbUsuario = new TbUsuario();
        tbUsuario.setIdtUsuario(0);

        tbUsuario.setDtaUsuarioCad(new Date(System.currentTimeMillis()));
        tbUsuario.setUsrUsuarioCad((String) sessao.getAttribute("usrUsuario"));

    }

    public void salvarNovaSenha() {
        String psw = tbUsuario.getPwdUsuario();
        tbUsuario.setPwdUsuario(BCrypt.hashpw(psw, BCrypt.gensalt()));
        tbUsuarioDAO.juntar(tbUsuario);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void salvar() {

        tbUsuario.setTbPerfil(tbPerfilDAO.consultarPorIdt(idtPerfil));
        if (tbUsuario.getIdtUsuario() == 0) {
            //Incluir

            String psw = tbUsuario.getPwdUsuario();
            tbUsuario.setPwdUsuario(BCrypt.hashpw(psw, BCrypt.gensalt()));
            tbUsuario.setIdtUsuario(null);
            tbUsuarioDAO.incluir(tbUsuario);
        } else {
            // Alterar
            tbUsuarioDAO.juntar(tbUsuario);
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Gravação", "Atualização efetivada na base de dados.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        idtPerfil = tbUsuario.getTbPerfil().getIdtPerfil();

        filtrar();
    }

    public void excluir() {
        idtPerfil = 0;

        if (tbUsuario.getIdtUsuario() != 0) {
            if (tbUsuarioDAO.excluir(tbUsuario.getIdtUsuario())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado da Exclusão", "Exclusão efetuada com sucesso.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resultado da Exclusão", "Não foi possível excluir.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        filtrar();
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getIdtPerfil() {
        return idtPerfil;
    }

    public void setIdtPerfil(int idtPerfil) {
        this.idtPerfil = idtPerfil;
    }

    public List<TbUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<TbUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public TbUsuario getTbUsuario() {
        return tbUsuario;
    }

    public void setTbUsuario(TbUsuario tbUsuario) {
        this.tbUsuario = tbUsuario;
        idtPerfil = tbUsuario.getTbPerfil().getIdtPerfil();
    }

    public List<TbPerfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<TbPerfil> perfis) {
        this.perfis = perfis;
    }
}
