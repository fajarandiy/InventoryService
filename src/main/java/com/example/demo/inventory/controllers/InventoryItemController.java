package com.example.demo.inventory.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.inventory.models.Inventory;
import com.example.demo.inventory.models.InventoryItem;
import com.example.demo.inventory.repositories.InventoryItemRepository;
import com.example.demo.inventory.repositories.InventoryRepository;

@RestController
@RequestMapping("api/inventoryItem")
public class InventoryItemController {
	@Autowired
	private InventoryItemRepository repo;
	
	@Autowired
	private InventoryRepository inventoryRepo;
	
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
	public Boolean restock(@Valid @RequestBody Map<String, Object> obj) {
		InventoryItem invItemObj = new InventoryItem();
		String productId =   (String) obj.get("productId");
		String inventoryId =   (String) obj.get("inventoryId");
		Inventory inv = inventoryRepo.findById(Integer.valueOf(inventoryId)).get();
		invItemObj = repo.findItem(productId, inv);
		
		String current_stock = invItemObj.getStock();
		String add_stock = (String) obj.get("addStock");
		int new_stock = Integer.valueOf(current_stock) + Integer.valueOf(add_stock);
		String new_stock_str = String.valueOf(new_stock);
		
		invItemObj.setStock(new_stock_str);
		repo.save(invItemObj);
		return true;
	}
	
	@PostMapping("/cutstock")
	public Boolean cutstock(@Valid @RequestBody Map<String, Object> obj) {
		InventoryItem invItemObj = new InventoryItem();
		String productId =   (String) obj.get("productId");
		String inventoryId =   (String) obj.get("inventoryId");
		Inventory inv = inventoryRepo.findById(Integer.valueOf(inventoryId)).get();
		invItemObj = repo.findItem(productId, inv);
//		invItemObj = repo.findItem(productId, inventoryId);
		
		String current_stock = invItemObj.getStock();
		String add_stock = (String) obj.get("cutStock");
		int new_stock = Integer.valueOf(current_stock) - Integer.valueOf(add_stock);
		String new_stock_str = String.valueOf(new_stock);
		
		invItemObj.setStock(new_stock_str);
		repo.save(invItemObj);
		return true;
	}
}
