package com.example.demo.inventory.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.inventory.models.Inventory;
import com.example.demo.inventory.models.InventoryItem;
import com.example.demo.inventory.repositories.InventoryRepository;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
		@Autowired
		private InventoryRepository repo;
		
		@PostMapping("/create")
		public String create(@Valid @RequestBody Inventory obj) {
			repo.save(obj);
			
			return "success create";
		}
		
		@PostMapping("/update/{action}") //add or remove
		public String update(@Valid @RequestBody Inventory obj, @Valid @PathVariable(value="action") String action) {
			Inventory newInventory = repo.findById(obj.getId()).get();

			if(newInventory != null) {
				List<InventoryItem> newList = newInventory.getStocks();
				if("add".equals(action)) {
					newList.addAll(obj.getStocks());
				} else if("remove".equals(action)){
					newList.remove(obj.getStocks());
				}
				newInventory.setStocks(newList);
				repo.save(newInventory);
				
				return "success update";
			} else {
				return "failed update";
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
		
		@GetMapping("/getId/{id}/getStocks")
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
