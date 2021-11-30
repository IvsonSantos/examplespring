package com.ivson.cursomc.service;

import com.ivson.cursomc.domain.Cliente;
import com.ivson.cursomc.repository.ClienteRepository;
import com.ivson.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente buscar(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto nao encontrado, id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

}
