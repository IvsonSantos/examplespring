package com.ivson.cursomc.service;

import com.ivson.cursomc.domain.Categoria;
import com.ivson.cursomc.dto.CategoriaDTO;
import com.ivson.cursomc.repository.CategoriaRepository;
import com.ivson.cursomc.service.exceptions.DataIntegritException;
import com.ivson.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto nao encontrado, id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegritException("Nao pode excluir uma categoria");
        }
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Categoria fromDto(CategoriaDTO dto) {
        return Categoria.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .build();
    }

}
