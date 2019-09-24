package com.example.demo.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.inventory.models.Inventory;
import com.example.demo.inventory.models.InventoryItem;

public interface InventoryItemRepository extends CrudRepository<InventoryItem, Integer>{

	@Query("select a from InventoryItem a where a.productId = :productId and a.inventory = :inventoryId")
	InventoryItem findItem(@Param("productId") String productId, @Param("inventoryId") Inventory inv);
	
}
