package (PathDaAplicacao).projetodesenvolvimento.repository.dto;

import (PathDaAplicacao).projetodesenvolvimento.repository.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetorDto {
  @JsonProperty("id")
  @JsonIgnore
  private Long id;

  @JsonProperty("description")
  private String descricao;

  @JsonProperty("status")
  private StatusEnum statusEnum;

  public SetorDto(Long id) {
    this.id = id;
  }
}
