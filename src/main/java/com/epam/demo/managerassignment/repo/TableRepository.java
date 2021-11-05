package com.epam.demo.managerassignment.repo;

import com.epam.demo.managerassignment.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<Table,Long> {
    Table save(Table entity);
    Optional<Table> findById(Long entityId);
    Table update(Table entity);
    Table updateById(Table entity, Long entityId);
    void delete(Table entity);
    void deleteById(Long entityId);
    List<Table> findByWaiter_Id(Long id);
}
