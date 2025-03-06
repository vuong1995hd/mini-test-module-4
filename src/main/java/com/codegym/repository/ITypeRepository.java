package com.codegym.repository;


import com.codegym.model.DTO.TypeDTO;
import com.codegym.model.Type;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ITypeRepository extends CrudRepository<Type, Long> {

    @Query(nativeQuery = true,
            value = "select p.id as id, p.name as name, COUNT(c.name) as count from computertype p left join computer c on p.id = c.computerType_id group by p.id;")
    Iterable<TypeDTO> getType();

    @Query(nativeQuery = true,
            value = "call deleteTypeById(:id)")
    @Transactional
    @Modifying
    void deleteTypeById(@Param("id") long id);

}
