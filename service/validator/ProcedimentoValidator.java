package br.com.shift.lis.projetodesenvolvimento.service.validator;

import br.com.shift.common.exception.ViolacaoRegraNegocioException;
import br.com.shift.lis.projetodesenvolvimento.repository.dto.CampoProcedimentoDto;
import br.com.shift.lis.projetodesenvolvimento.service.SetorService;
import java.math.BigDecimal;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProcedimentoValidator {

  @Inject
  SetorService setorService;

  public void verificaStatusSetor(Boolean setorExiste, CampoProcedimentoDto campoProcedimentoDto) {
    var buscaStatusSetor = setorService.findByIdSetor(campoProcedimentoDto.getSetorId());
    if (setorExiste && buscaStatusSetor.getStatusEnum().equals(Boolean.FALSE)) {
      throw ViolacaoRegraNegocioException.builder()
        .mensagem("Não é possível completar a ação. O setor está inativo")
        .build();
    }
  }
  public Boolean verificaPreco(CampoProcedimentoDto campoProcedimentoDto) {
    if (campoProcedimentoDto.getPreco().compareTo(new BigDecimal(0)) >= 0) {
      return true;
    }
    throw ViolacaoRegraNegocioException.builder()
      .mensagem("Não é possível completar a ação. O preço deve ser um valor maior ou igual a zero.")
      .build();
  }
}
