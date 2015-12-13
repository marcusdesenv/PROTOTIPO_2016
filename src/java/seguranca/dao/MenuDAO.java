/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca.dao;

import java.util.List;
import seguranca.model.TbMenu;
import org.hibernate.Query;

/**
 *
 * @author Avell B153
 */
public class MenuDAO extends BaseDAO<TbMenu> {

    public List<TbMenu> gerarMenu(String username) {

        List<TbMenu> lista;
        Query qy = hib.createQuery("SELECT DISTINCT menu  FROM TbMenu menu,TbOpcao opcao,TaPrivilegio privilegio, TbPerfil perfil, TbUsuario usuario "
                + "WHERE opcao.tbMenu.idtMenu = menu.idtMenu AND privilegio.tbOpcao.idtOpcao = opcao.idtOpcao "
                + "AND perfil.idtPerfil = privilegio.tbPerfil.idtPerfil AND usuario.tbPerfil.idtPerfil = perfil.idtPerfil "
                + "AND usuario.usrUsuario LIKE ? ORDER BY menu.ordMenu ");
        qy.setString(0, username);
       
        lista = qy.list();
        return lista;
    }
      
}
