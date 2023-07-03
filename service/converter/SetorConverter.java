package br.com.shift.lis.projetodesenvolvimento.service.converter;

import br.com.shift.common.interfaces.Converter;
import br.com.shift.lis.projetodesenvolvimento.repository.dto.SetorDto;
import br.com.shift.lis.projetodesenvolvimento.repository.entity.Setor;
import br.com.shift.lis.projetodesenvolvimento.repository.enums.StatusEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SetorConverter implements Converter<Setor, SetorDto> {
//COMO ERA ANTES COM boolean em dto
//  @Override
//  public SetorDto toDto(Setor setor) {
//    if (setor != null) {
//      return SetorDto.builder()
//        .id(setor.getId())
//        .descricao(setor.getDescricao())
//        .statusEnum(StatusEnum.getStatusBoolean(setor.getStatusEnum()))
//        .build();
//    }
//    return SetorDto.builder().build();
//  }

  @Override
  public SetorDto toDto(Setor setor) {
    if (setor != null) {
      return SetorDto.builder()
        .id(setor.getId())
        .descricao(setor.getDescricao())
        .statusEnum(StatusEnum.getStatusEnum(setor.getStatusEnum()))
        .build();
    }
    return SetorDto.builder().build();
  }

  @Override
  public Setor toEntity(SetorDto dto) {
    return Setor.builder()
      .id(dto.getId())
      .descricao(dto.getDescricao())
      .statusEnum(StatusEnum.getStatusEnum(dto.getStatusEnum()))
      .build();
  }

  @Override
  public List<SetorDto> toDtoList(List<Setor> entityList) {
    List<SetorDto> grupoSetorDto = new ArrayList<>();
    entityList.forEach(grupoSetor -> grupoSetorDto.add(toDto(grupoSetor)));
    return grupoSetorDto;
  }

  @Override
  public SetorDto toDtoFromOptionalEntity(Optional<Setor> entityOptional) {
    return entityOptional.map(this::toDto).orElse(null);
  }

  @Override
  public Optional<SetorDto> toOptionalDtoFromOptionalEntity(Optional<Setor> entityOptional) {
    return Optional.ofNullable(toDtoFromOptionalEntity(entityOptional));
  }

}
