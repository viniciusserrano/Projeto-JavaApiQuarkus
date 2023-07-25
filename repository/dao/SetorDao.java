package (PathDaAplicacao).projetodesenvolvimento.repository.dao;

import (PathDaAplicacao).projetodesenvolvimento.repository.entity.Setor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class SetorDao implements PanacheRepositoryBase<Setor, Long> {

  public Optional<Setor> findByDescricao(String descricao) {
    return find("descricao", descricao).singleResultOptional();
  }

  public Setor getSetorId(Long setorId) {
    return findById(setorId);
  }

  public Setor salvarSetor (Setor setor) {
    persist(setor);
    return setor;
  }

}
