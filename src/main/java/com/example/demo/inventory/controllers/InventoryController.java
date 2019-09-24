package com.example.demo.inventory.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.inventory.models.Inventory;
import com.example.demo.inventory.models.InventoryItem;
import com.example.demo.inventory.repositories.InventoryItemRepository;
import com.example.demo.inventory.repositories.InventoryRepository;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
		@Autowired
		private InventoryRepository repo;
		
		@Autowired
		private InventoryItemRepository itemRepo;
		
		@PostMapping("/create")
		public String create(@Valid @RequestBody Inventory obj) {
			repo.save(obj);
			
			return "success create";
		}
		
		@PostMapping("/update/{id}") //add or remove
		public String update(@Valid @RequestBody List<InventoryItem> obj, @Valid @PathVariable(value="id") int id, @Valid @RequestParam(value="action") String action) {
			Optional<Inventory> opt = repo.findById(id);

			if(opt.isPresent()) {
				Inventory inv = opt.get();
				if("add".equals(action)) {
					for(InventoryItem inventoryItem : obj) {
						inventoryItem.setInventory(inv);
						itemRepo.save(inventoryItem);
					}
				} else if("remove".equals(action)){
					for(InventoryItem inventoryItem : obj) {
						inventoryItem.setInventory(null);
						itemRepo.save(inventoryItem);
					}
				}
				
				repo.save(inv);
				
				return "success update";
			} else {
				return "update failed, data not found";
			}
			
		}
		
		@GetMapping("/getAll")
		public Iterable<Inventory> getAll() {
			return repo.findAll();
		}
		
		@GetMapping("/getId/{id}")
		public Inventory getId(@Valid @PathVariable(value="id") int id) {
			return repo.findById(id).get();
		}
		
		@GetMapping("/getId/{id}/stocks")
		public List<InventoryItem> getStocks(@Valid @PathVariable(value="id") int id) {
			return repo.findById(id).get().getStocks();
		}
		
		@GetMapping("/getName/{name}")
		public List<Inventory> getName(@Valid @PathVariable(value="name") String name) {
			return repo.findByName(name);
		}
		
		@GetMapping("/getAddress/{address}")
		public List<Inventory> getAddress(@Valid @PathVariable(value="address") String address) {
			return repo.findByAddress(address);
		}
		
}
