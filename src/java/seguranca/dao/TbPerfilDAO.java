/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca.dao;

import java.util.List;
import seguranca.model.TbPerfil;
import org.hibernate.Query;

/**
 *
 * @author RA21077291
 */
public class TbPerfilDAO extends BaseDAO<TbPerfil>{
  
    
    public TbPerfil consultarPorCod(int idt) {
        TbPerfil obj;
        Query qy = hib.createQuery("SELECT obj FROM " + getClasse().getSimpleName() + " obj WHERE cod" + getClasse().getSimpleName().substring(2) + "=? Order By obj.ordPerfil");
        qy.setInteger(0, idt);
        obj = (TbPerfil) qy.uniqueResult();
        return obj;
    }
 
}
