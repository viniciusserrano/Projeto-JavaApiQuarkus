package br.com.shift.lis.projetodesenvolvimento.repository.dto;

import br.com.shift.lis.projetodesenvolvimento.repository.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
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
public class ProcedimentoDto {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("mnemonic")
  private String mnemonico;

  @JsonProperty("description")
  private String descricao;

  @JsonProperty("price")
  private BigDecimal preco = BigDecimal.ZERO;

  @JsonProperty("section")
  private SetorDto setor;

  @JsonProperty("status")
  private StatusEnum statusEnum;




}
