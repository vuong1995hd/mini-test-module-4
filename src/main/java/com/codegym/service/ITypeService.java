package com.codegym.service;


import com.codegym.model.DTO.TypeDTO;
import com.codegym.model.Type;

public interface ITypeService extends IGenerateService<Type>{
    Iterable<TypeDTO> getType();
}
