/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca.dao;

import java.util.List;
import seguranca.model.TbOpcao;
import org.hibernate.Query;

/**
 *
 * @author Avell B153
 */
public class OpcaoDAO extends BaseDAO<TbOpcao> {

    public List<TbOpcao> gerarOpcoes(int menu, String username) {
        List<TbOpcao> lista;
        Query qy = hib.createQuery("SELECT opcao  FROM TbOpcao opcao,TaPrivilegio privilegio, TbPerfil perfil, TbUsuario usuario "
                + "WHERE  privilegio.tbOpcao.idtOpcao = opcao.idtOpcao "
                + "AND perfil.idtPerfil = privilegio.tbPerfil.idtPerfil AND usuario.tbPerfil.idtPerfil = perfil.idtPerfil "
                + "AND usuario.usrUsuario LIKE ?   and opcao.tbMenu LIKE ? ORDER BY opcao.ordOpcao");
        qy.setString(0, username);

        qy.setInteger(1, menu);
        lista = qy.list();
        return lista;
    }

    public List<TbOpcao> verificarOpcao(String username, String password) {
        List<TbOpcao> lista;
        Query qy = hib.createQuery("SELECT opcao  FROM TbOpcao opcao,TaPrivilegio privilegio, TbPerfil perfil, TbUsuario usuario "
                + "WHERE  privilegio.tbOpcao.idtOpcao = opcao.idtOpcao "
                + "AND perfil.idtPerfil = privilegio.tbPerfil.idtPerfil AND usuario.tbPerfil.idtPerfil = perfil.idtPerfil "
                + "AND usuario.usrUsuario LIKE ? AND usuario.pwdUsuario LIKE ?   ORDER BY opcao.ordOpcao");
        qy.setString(0, username);
        qy.setString(1, password);

        lista = qy.list();
        return lista;
    }
}
