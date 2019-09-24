package com.example.demo.inventory.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.inventory.models.Inventory;

public interface InventoryItemRepository extends CrudRepository<Inventory, Integer>{

}
