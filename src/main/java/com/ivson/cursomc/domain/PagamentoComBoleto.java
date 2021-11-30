package com.ivson.cursomc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoComBoleto extends  Pagamento {

    private Date dataVencimento;

    private Date dataPagamento;

}
