/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca.dao;

import java.util.List;
import seguranca.model.TbUsuario;
import org.hibernate.Query;


public class TbUsuarioDAO extends BaseDAO<TbUsuario> {

    public TbUsuario fazerLogin(String usr) {
        TbUsuario usuario;
      Query qy = hib.createQuery("SELECT obj FROM TbUsuario obj WHERE obj.usrUsuario LIKE ? AND obj.stsUsuario = true");
        qy.setString(0, "" + usr + "");
        
        usuario = (TbUsuario) qy.uniqueResult(); 
        return usuario;
    }

    public List<TbUsuario> consultarPorUsr(String usr) {
        List<TbUsuario> lista;
        Query qy = hib.createQuery("SELECT obj FROM " + getClasse().getSimpleName() + " obj WHERE usr" + getClasse().getSimpleName().substring(2) + " LIKE ?");
        qy.setString(0, "%" + usr + "%");
        lista = qy.list();
        return lista;
    }

    public List<TbUsuario> consultarPorPwd(String pwd) {
        List<TbUsuario> lista;
        Query qy = hib.createQuery("SELECT obj FROM " + getClasse().getSimpleName() + " obj WHERE pwd" + getClasse().getSimpleName().substring(2) + " LIKE ?");
        qy.setString(0, "%" + pwd + "%");
        lista = qy.list();
        return lista;
    }

    public List<TbUsuario> consultarPorCpf(String cpf) {
        List<TbUsuario> lista;
        Query qy = hib.createQuery("SELECT obj FROM " + getClasse().getSimpleName() + " obj WHERE cpf" + getClasse().getSimpleName().substring(2) + " LIKE ?");
        qy.setString(0, "%" + cpf + "%");
        lista = qy.list();
        return lista;
    }

    public List<TbUsuario> consultarPorIdtPerfil(Integer idt) {
        List<TbUsuario> lista;
        Query qy = hib.createQuery(
                "SELECT obj FROM TbUsuario obj WHERE (0=? OR tbPerfil.idtPerfil=?) ");
        qy.setInteger(0, idt);
        qy.setInteger(1, idt);
        lista = qy.list();
        return lista;
    }
    public List<TbUsuario> consultarPorIdtPerfilePorNome(Integer idt ,String nome) {
        List<TbUsuario> lista;
        Query qy = hib.createQuery(
                "SELECT obj FROM TbUsuario obj WHERE (0=? OR tbPerfil.idtPerfil=?) and obj.nmeUsuario LIKE Concat('%',?,'%')  ");
        qy.setInteger(0, idt);
        qy.setInteger(1, idt);
        qy.setString(2, nome);
        
        lista = qy.list();
        return lista;
    }
   
}