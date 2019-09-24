package com.example.demo.inventory.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.inventory.models.InventoryItem;
import com.example.demo.inventory.repositories.InventoryItemRepository;

@RestController
@RequestMapping("api/inventoryItem")
public class InventoryItemController {
	@Autowired
	private InventoryItemRepository repo;
	
	@PostMapping("/create")
	public String createInventoryItem(@Valid @RequestBody InventoryItem obj) {
		repo.save(obj);
		return "success create";
	}
	
	@GetMapping("/getall")
	public Iterable<InventoryItem> getAll() {
		return repo.findAll();
	}
	
	@PostMapping("/restock")
	public Boolean restock(@Valid @RequestBody InventoryItem obj) {
		repo.findById(obj.getId());
		
		return true;
	}
}
