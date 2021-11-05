package com.epam.demo.managerassignment.service;

import com.epam.demo.managerassignment.model.Table;
import com.epam.demo.managerassignment.repo.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {
    private TableRepository tableRepository;


    public TableService(TableRepository tableRepository ) {
        this.tableRepository = tableRepository;
    }

    public List<Table> findAll() {
        List<Table> tables = tableRepository.findAll();
        List<Table> sortedOrders = tables.stream().sorted().collect(Collectors.toList());
        return sortedOrders;
    }

    public Table findById(Long id) {
        return tableRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public void save(Table table) {
        tableRepository.save(table);
    }

    public void deleteById(Long id) {
        tableRepository.deleteById(id);
    }

    public List<Table> findByWaiter_Id(Long waiterId) {
        List<Table> tables = tableRepository.findByWaiter_Id(waiterId);

        return tables;
    }

}
