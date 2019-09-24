package com.example.demo.order.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.order.models.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Integer>{

}
