package br.com.shift.lis.projetodesenvolvimento.repository.entity;

import br.com.shift.lis.projetodesenvolvimento.repository.enums.StatusEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Schema(name = "Setor")
@Entity(name = "s01_vinicius_dado.Setor")
@Table(name = "s01_vinicius_dado.Setor")
public class Setor {

  @Id
  @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "Descricao", length = 500)
  private String descricao;

  @Column(name = "StatusEnum", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private StatusEnum statusEnum;

}
