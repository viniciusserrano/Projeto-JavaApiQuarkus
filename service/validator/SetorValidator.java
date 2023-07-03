package br.com.shift.lis.projetodesenvolvimento.service.validator;

import br.com.shift.common.exception.ViolacaoRegraNegocioException;
import br.com.shift.lis.projetodesenvolvimento.repository.dto.SetorDto;
import br.com.shift.lis.projetodesenvolvimento.repository.enums.StatusEnum;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SetorValidator {
  public void verificaStatusSetor (Boolean setorExiste, SetorDto setorDto) {
    if(setorExiste && setorDto.getStatusEnum().equals(StatusEnum.INATIVO)){
      throw ViolacaoRegraNegocioException.builder()
        .mensagem("Não é possível completar a ação. Existem procedimentos associados a esse setor.")
        .build();
    }
  }
}
