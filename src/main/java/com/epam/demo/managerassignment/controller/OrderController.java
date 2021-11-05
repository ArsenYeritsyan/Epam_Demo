package com.epam.demo.managerassignment.controller;

import com.epam.demo.managerassignment.model.Order;
import com.epam.demo.managerassignment.model.Table;
import com.epam.demo.managerassignment.model.restmodel.UserResponseModel;
import com.epam.demo.managerassignment.repo.OrderRepository;
import com.epam.demo.managerassignment.service.RoleUserService;
import com.epam.demo.managerassignment.service.TableService;
import com.epam.demo.managerassignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderRepository orderRepo;
    private RoleUserService roleService;
    private UserService userRepo;
    private TableService tableRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo, RoleUserService roleService,
                           UserService userRepo, TableService tableRepo) {
        this.orderRepo = orderRepo;
        this.roleService = roleService;
        this.userRepo = userRepo;
        this.tableRepo = tableRepo;
    }

    @PostMapping()
    public ResponseEntity creatOrder(
            @Validated @RequestBody Order order,
            @RequestParam("waiter_id") Long waiterId,
            @RequestParam("table_id") Long tableId) {
        Optional<UserResponseModel> waiterOptional = Optional.ofNullable (userRepo.findById (waiterId));
        roleService.mustBeWaiter (waiterOptional);
        Optional<Table> tableOptional = Optional.ofNullable (tableRepo.findById (tableId));
        Table table;
        table = tableOptional.get ();
       // Set<Order> existingOrders = table.getOrders ();
      // boolean hasOpen = existingOrders.stream ().anyMatch (Order -> Order.getStatus().equals (OrderStatus.OPEN));
        order.setTable ((javax.persistence.Table) table);
        Order savedOrder = orderRepo.save (order);
        return ResponseEntity.status (HttpStatus.CREATED).body (savedOrder);
    }

}
