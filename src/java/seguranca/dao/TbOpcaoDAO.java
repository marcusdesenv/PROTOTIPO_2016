/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import seguranca.model.TbOpcao;

/**
 *
 * @author RA21077291
 */
public class TbOpcaoDAO extends BaseDAO<TbOpcao>{
    public List<TbOpcao> consultarPorSgl(String sgl) {
        List<TbOpcao> lista;
        Query qy = hib.createQuery("SELECT obj FROM " + getClasse().getSimpleName() + " obj WHERE sgl" + getClasse().getSimpleName().substring(2) + " LIKE ? Order BY obj.ordOpcao");
        qy.setString(0, "%" + sgl + "%");
        lista = qy.list();
        return lista;
    }
    
    public List<TbOpcao> consultarPorIdtMenu(Integer idt) {
        List<TbOpcao> lista;
        Query qy = hib.createQuery(
                "SELECT obj FROM TbOpcao obj WHERE (0=? OR tbMenu.idtMenu=?) Order BY obj.ordOpcao");
        qy.setInteger(0, idt);
        qy.setInteger(1, idt);
        lista = qy.list();
        return lista;
    }
     public List<TbOpcao> consultarNaoEstaConsulta(Integer idtPerfil) {
        List<TbOpcao> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbOpcao obj WHERE obj.idtOpcao NOT IN "
                + "(SELECT proc.tbOpcao.idtOpcao FROM TaPrivilegio proc WHERE proc.tbPerfil.idtPerfil=?)");
        qy.setInteger(0, idtPerfil);
        lista = (ArrayList<TbOpcao>) qy.list();
        return lista;
    }

    public List<TbOpcao> consultarEstaEmConsulta(Integer idtPerfil) {
        List<TbOpcao> lista;
        Query qy = hib.createQuery("SELECT obj FROM TbOpcao obj WHERE obj.idtOpcao IN "
                + "(SELECT proc.tbOpcao.idtOpcao FROM TaPrivilegio proc WHERE proc.tbPerfil.idtPerfil=?)");
        qy.setInteger(0, idtPerfil);
        lista = (ArrayList<TbOpcao>) qy.list();
        return lista;
    }
    
}
