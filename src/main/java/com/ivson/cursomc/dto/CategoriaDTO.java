package com.ivson.cursomc.dto;

import com.ivson.cursomc.domain.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CategoriaDTO implements Serializable {

    private Integer id;

    @NotNull(message = "Nao pode nulo")
    @Length(min = 5, max = 10, message = "tamnho 5 a 10")
    private String nome;

    public CategoriaDTO(Categoria obj) {
        id = obj.getId();
        nome = obj.getNome();
    }

}
