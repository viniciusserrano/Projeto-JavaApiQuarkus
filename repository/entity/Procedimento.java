package (pathDaAplicacao).repository.entity;

import(PathDaAplicacao).projetodesenvolvimento.repository.enums.StatusEnum;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Schema(name = "Procedimento")
@Entity(name = "s01_vinicius_dado.Procedimento")
@Table(name = "s01_vinicius_dado.Procedimento")

public class Procedimento {

  @Id
  @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "Mnemonico", nullable = false, length = 50)
  private String mnemonico;

  @Column(name = "Descricao", length = 500)
  private String descricao;

  @Column(name = "Preco")
  private BigDecimal preco = BigDecimal.ZERO;

  @Column(name = "StatusEnum", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private StatusEnum statusEnum;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Setor", foreignKey = @ForeignKey(name = "fkSetor"), referencedColumnName = "ID")
  private Setor setor;
}
