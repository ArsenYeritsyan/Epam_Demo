package com.epam.demo.managerassignment.repo;

import com.epam.demo.managerassignment.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
    List<ProductInOrder> findByProduct_Id(Long product_id);
    public  ProductInOrder save(ProductInOrder entity);
    public  List<ProductInOrder> findAll();
    public Optional<ProductInOrder> findById(ProductInOrder entityId);
    public  ProductInOrder update(ProductInOrder entity);
    public  ProductInOrder updateById(ProductInOrder entity,Long entityId);
    public  void delete(ProductInOrder entity);
    public  void deleteById(Long entityId);
    List<ProductInOrder> findByOrder_id(Long order_id);
}
