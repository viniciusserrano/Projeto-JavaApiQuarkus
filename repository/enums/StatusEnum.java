package br.com.shift.lis.projetodesenvolvimento.repository.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {

  INATIVO(0, "INATIVO"),
  ATIVO(1, "ATIVO");

  private Integer indice;
  private String status;

  public static StatusEnum fromIndice(Integer s) throws IllegalArgumentException {
    return Arrays.stream(values())
      .filter(v -> v.indice.equals(s))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Valor Desconhecido: " + s));
  }

//  public static boolean getStatusBoolean(StatusEnum statusEnum) {
//    if(statusEnum.equals(ATIVO)){
//     return true;
//    }
//    return false;
//  }

  public static StatusEnum getStatusEnum(StatusEnum getStatus) {
    if (getStatus.equals(ATIVO)) {
      return ATIVO;
    }
    return INATIVO;
  }
//COMO ERA ANTES
//  public static StatusEnum getStatusEnum(boolean getStatus) {
//
//    if (getStatus.equals(ATIVO)) {
//      return ATIVO;
//    }
//    return INATIVO;
//  }

}
