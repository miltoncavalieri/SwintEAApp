package br.com.zaiac.swinteaapp.facade;

import br.com.zaiac.swinteaapp.entities.Clicasolote;
import br.com.zaiac.swinteaapp.entities.Clicasotipolote;
import br.com.zaiac.swinteaapp.entities.Cliente;
import br.com.zaiac.swinteaapp.session.AbstractFacade;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ClicasoloteFacade extends AbstractFacade<Clicasolote>{
    public ClicasoloteFacade() {
        super(Clicasolote.class);
    }
    
    public Clicasolote findLoteAberto(Cliente cliId, Clicasotipolote a01Id) {
        Query query = getEm().createNamedQuery("Clicasolote.findLoteAberto");
        query.setParameter("cliId", cliId);
        query.setParameter("a01Id", a01Id);
        
        List<Clicasolote> lotes = query.getResultList();
        if (lotes.isEmpty()) {
            throw new NoResultException("Lote aberto não encontrado");
        } else {
            return (Clicasolote) lotes.get(0);
        }
        
        
    }
    
    public Clicasolote findByA03Id(Long a03Id) {
        Query query = getEm().createNamedQuery("Clicasolote.findByA03Id");
        query.setParameter("a03Id", a03Id);
        return (Clicasolote) query.getSingleResult();
    }
    
    
    public int updateA03ConcluidoAllLotes() {
        String sql = "update clicasolote l set a03_concluido =" +
                        " (" + 
                        " select if(count(1) > 0, 'true', 'false') as result" +
                        " from clicaso c " +
                        " inner join clicasocheckpoint k on (k.a04_id_caso = c.a04_id_caso and k.a05_seq = c.a05_seq_ultimo_status) " +
                        " where ((k.a01_id = 4 and k.a02_id = 7) or (k.a01_id = 3 and k.a02_id = 5) or (k.a01_id = 2 and k.a02_id = 4) or (k.a01_id = 1 and k.a02_id = 4))" +
                        " and c.a03_id = l.a03_id" +
                        " )" +
                        " where a03_concluido = 'false'";

        Query query = getEm().createNativeQuery(sql);
        return query.executeUpdate();
    }
    
    public int updateA03ConcluidoByLotes(Long a03Id) {
        String sql = "update clicasolote l set a03_concluido =" +
                        " (" + 
                        " select if(count(1) > 0, 'true', 'false') as result" +
                        " from clicaso c " +
                        " inner join clicasocheckpoint k on (k.a04_id_caso = c.a04_id_caso and k.a05_seq = c.a05_seq_ultimo_status) " +
                        " where ((k.a01_id = 4 and k.a02_id = 7) or (k.a01_id = 3 and k.a02_id = 5) or (k.a01_id = 2 and k.a02_id = 4) or (k.a01_id = 1 and k.a02_id = 4))" +
                        " and c.a03_id = l.a03_id" +
                        " )" +
                        " where a03_concluido = 'false' and l.a03_id = :a03_id";
        Query query = getEm().createNativeQuery(sql);
        query.setParameter("a03_id", a03Id);
        return query.executeUpdate();
    }
    
    
}
