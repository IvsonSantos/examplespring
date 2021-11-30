package com.ivson.cursomc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoComCartao extends  Pagamento {

    private Integer numeroDeParcelas;

}
