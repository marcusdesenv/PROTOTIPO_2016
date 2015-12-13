/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca.dao;

import java.util.List;
import org.hibernate.Query;
import seguranca.model.TbMenu;

/**
 *
 * @author RA21077291
 */
public class TbMenuDAO extends BaseDAO<TbMenu>{
    public List<TbMenu> consultarPorSgl(String sgl) {
        List<TbMenu> lista;
        Query qy = hib.createQuery("SELECT obj FROM " + getClasse().getSimpleName() + " obj WHERE sgl" + getClasse().getSimpleName().substring(2) + " LIKE ? ORDER BY obj.ordMenu");
        qy.setString(0, "%" + sgl + "%");
        lista = qy.list();
        return lista;
    }
    

    
    
}
