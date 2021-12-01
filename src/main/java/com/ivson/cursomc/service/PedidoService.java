package com.ivson.cursomc.service;

import com.ivson.cursomc.domain.Pedido;
import com.ivson.cursomc.repository.PedidoRepository;
import com.ivson.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto nao encontrado, id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

}
