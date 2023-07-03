package br.com.shift.lis.projetodesenvolvimento.service.converter;

import br.com.shift.common.interfaces.Converter;
import br.com.shift.lis.projetodesenvolvimento.repository.dto.ProcedimentoDto;
import br.com.shift.lis.projetodesenvolvimento.repository.entity.Procedimento;
import br.com.shift.lis.projetodesenvolvimento.repository.enums.StatusEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@ApplicationScoped
public class ProcedimentoConverter implements Converter<Procedimento, ProcedimentoDto> {

  @Inject
  SetorConverter setorConverter;


  @Override
  public ProcedimentoDto toDto(Procedimento procedimento) {
      return ProcedimentoDto.builder()
        .id(procedimento.getId())
        .mnemonico(procedimento.getMnemonico())
        .descricao(procedimento.getDescricao())
        .preco(procedimento.getPreco())
        .statusEnum(StatusEnum.getStatusEnum(procedimento.getStatusEnum()))
        .setor(setorConverter.toDto(procedimento.getSetor()))
        .build();
  }

  @Override
  public Procedimento toEntity(ProcedimentoDto dto) {
    return Procedimento.builder()
      .id(dto.getId())
      .mnemonico(dto.getMnemonico())
      .descricao(dto.getDescricao())
      .preco(dto.getPreco())
      .setor(setorConverter.toEntity(dto.getSetor()))
      .statusEnum(StatusEnum.getStatusEnum(dto.getStatusEnum()))
      .build();
  }

  @Override
  public List<ProcedimentoDto> toDtoList(List<Procedimento> entityList) {
    List<ProcedimentoDto> grupoProcedimentoDto = new ArrayList<>();
    entityList.forEach(grupoProcedimento -> grupoProcedimentoDto.add(toDto(grupoProcedimento)));
    return grupoProcedimentoDto;
  }

  @Override
  public ProcedimentoDto toDtoFromOptionalEntity(Optional<Procedimento> entityOptional) {
    return entityOptional.map(this::toDto).orElse(null);
  }

  @Override
  public Optional<ProcedimentoDto> toOptionalDtoFromOptionalEntity(
    Optional<Procedimento> entityOptional) {
    return Optional.ofNullable(toDtoFromOptionalEntity(entityOptional));
  }
}