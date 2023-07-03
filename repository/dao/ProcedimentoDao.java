package br.com.shift.lis.projetodesenvolvimento.repository.dao;

import br.com.shift.lis.projetodesenvolvimento.repository.entity.Procedimento;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import java.util.Objects;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@ApplicationScoped
public class ProcedimentoDao implements PanacheRepositoryBase<Procedimento, Long> {

  @Inject
  EntityManager entityManager;

  public Procedimento getParametroId(Long parametroId) {
    return findById(parametroId);
  }

  public Optional<Procedimento> findByUserMnemonico(String mnemonico) {
    return find("mnemonico", mnemonico).singleResultOptional();
  }
//todo Usar o "order By ID desc" pois assim busca por ordem decrescente - se não usa-lo vai buscar em ordem crescente
  public Boolean existeSetor (Long setorId) {
   var buscaSetor = entityManager.createNativeQuery("SELECT ID FROM s01_vinicius_dado.Procedimento WHERE Setor=:setorId order By ID desc")
     .setParameter("setorId", setorId)
     .setMaxResults(1)
     .getResultList();
    if (Objects.isNull(buscaSetor) && buscaSetor.isEmpty()) {
      return false;
    }
   return true;
  }
  public Procedimento salvarProcedimento(Procedimento procedimento) {
    persist(procedimento);
    return procedimento;
  }

}
