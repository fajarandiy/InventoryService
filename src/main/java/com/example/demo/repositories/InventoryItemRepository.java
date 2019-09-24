package com.example.demo.order.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.order.models.Inventory;

public interface InventoryItemRepository extends CrudRepository<Inventory, Integer>{

}
