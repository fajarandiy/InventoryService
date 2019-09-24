package com.example.demo.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.inventory.models.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Integer>{
	@Query("select a from Inventory a where a.name like concat('%', :name, '%')")
	List<Inventory> findByName(@Param("name") String name);

	@Query("select a from Inventory a where a.address like concat('%', :address, '%')")
	List<Inventory> findByAddress(@Param("address") String address);
}
