package com.ivson.cursomc.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Fºisica"),
    PESSOAJURIDICA(1, "Pessoa Jurídica");

    private int cod;

    private String descricao;

    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (TipoCliente x : TipoCliente.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: " + cod);
    }

}
