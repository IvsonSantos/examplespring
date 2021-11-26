package com.ivson.cursomc.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Categoria implements Serializable {

    private Integer id;

    private String nome;

}
