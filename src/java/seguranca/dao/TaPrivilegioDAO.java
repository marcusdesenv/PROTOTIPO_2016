/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;
import seguranca.model.TaPrivilegio;

/**
 *
 * @author RA21077291
 */
public class TaPrivilegioDAO extends BaseDAO<TaPrivilegio>{
    public List<TaPrivilegio> consultarPorIdtOpcao(Integer idt) {
        List<TaPrivilegio> lista;
        Query qy = hib.createQuery(
                "SELECT obj FROM TaPrivilegio obj WHERE (0=? OR tbOpcao.idtOpcao=?) ");
        qy.setInteger(0, idt);
        qy.setInteger(1, idt);
        lista = qy.list();
        return lista;
    }
    
    public List<TaPrivilegio> consultarPorIdtPerfil(Integer idt) {
        List<TaPrivilegio> lista;
        Query qy = hib.createQuery(
                "SELECT obj FROM TaPrivilegio obj WHERE (0=? OR tbPerfil.idtPerfil=?)");
        qy.setInteger(0, idt);
        qy.setInteger(1, idt);
        lista = qy.list();
        return lista;
    }
      public boolean excluirTodosPorConsulta(Integer idt) {
        List<TaPrivilegio> lista;
        Query qy = hib.createQuery("SELECT obj FROM TaPrivilegio obj WHERE obj.tbPerfil.idtPerfil=?");
        qy.setInteger(0, idt);
        lista = qy.list();
        for (Iterator<TaPrivilegio> it = lista.iterator(); it.hasNext();) {
            TaPrivilegio taPrivilegio = it.next();
            Transaction ts = hib.beginTransaction();
            ts.begin();
            hib.delete(taPrivilegio);
            hib.flush();
            ts.commit();
        }
        return true;
    }
}
