package br.com.shift.lis.projetodesenvolvimento.service.converter;

import br.com.shift.common.interfaces.Converter;
import br.com.shift.lis.projetodesenvolvimento.repository.dto.CampoProcedimentoDto;
import br.com.shift.lis.projetodesenvolvimento.repository.entity.Procedimento;
import br.com.shift.lis.projetodesenvolvimento.repository.enums.StatusEnum;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@ApplicationScoped
public class CampoProcedimentoConverter implements Converter<Procedimento, CampoProcedimentoDto> {

  @Inject
  SetorConverter setorConverter;

  @Override
  public CampoProcedimentoDto toDto(Procedimento procedimento) {
    return CampoProcedimentoDto.builder()
      .id(procedimento.getId())
      .mnemonico(procedimento.getMnemonico())
      .descricao(procedimento.getDescricao())
      .preco(procedimento.getPreco())
      .statusEnum(StatusEnum.getStatusEnum(procedimento.getStatusEnum()))
      .setorId(procedimento.getSetor().getId())
      .build();
  }

  @Override
  public Procedimento toEntity(CampoProcedimentoDto dto) {
    return Procedimento.builder()
      .id(dto.getId())
      .mnemonico(dto.getMnemonico())
      .descricao(dto.getDescricao())
      .preco(dto.getPreco())
//      .setor(setorConverter.toEntity(new SetorDto(dto.getSetor())))
      .statusEnum(StatusEnum.getStatusEnum(dto.getStatusEnum()))
      .build();
  }

  @Override
  public List<CampoProcedimentoDto> toDtoList(List<Procedimento> entityList) {
    return null;
  }

  @Override
  public CampoProcedimentoDto toDtoFromOptionalEntity(Optional<Procedimento> entityOptional) {
    return null;
  }

  @Override
  public Optional<CampoProcedimentoDto> toOptionalDtoFromOptionalEntity(
    Optional<Procedimento> entityOptional) {
    return Optional.empty();
  }


}
