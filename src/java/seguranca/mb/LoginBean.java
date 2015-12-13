package seguranca.mb;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import seguranca.dao.TbUsuarioDAO;
import seguranca.model.TbUsuario;

@ManagedBean
@ViewScoped
/*Classe Responsável pela Verificação do Login e Acesso a paginas.*/
public class LoginBean extends Sessao {

    public String username;
    public String password;
    private TbUsuario usuario;

    /*O metodo verificarLogin confere se o Usuário está logado e se tem acesso a pagina.*/
    public void logout() throws IOException {

        sessao.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
    }

    public void login() throws IOException {


        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        TbUsuarioDAO tbUsuarioDAO = new TbUsuarioDAO();
        FacesMessage msg;


        usuario = tbUsuarioDAO.fazerLogin(username);
        boolean testarSenha = BCrypt.checkpw(password, usuario.getPwdUsuario());

        if (usuario != null && testarSenha) {
            sessao.setAttribute("idtUsuario", usuario.getIdtUsuario());
            sessao.setAttribute("usrUsuario", usuario.getUsrUsuario());
            sessao.setAttribute("nmeUsuario", usuario.getNmeUsuario());

            FacesContext.getCurrentInstance().getExternalContext().redirect("template.xhtml");

        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario ou senha Inválidos");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomeUsuario() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        return (String) sessao.getAttribute("nmeUsuario");
    }
}
