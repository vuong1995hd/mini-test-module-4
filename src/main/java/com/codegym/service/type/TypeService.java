package com.codegym.service.type;

import com.practicecrud.model.Type;
import com.practicecrud.repository.ITypeRepository;
import com.practicecrud.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService implements ITypeService {

    @Autowired
    private ITypeRepository typeRepository;

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public void save(Type type) {
        typeRepository.save(type);

    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        typeRepository.deleteTypeById(id);
    }
}
