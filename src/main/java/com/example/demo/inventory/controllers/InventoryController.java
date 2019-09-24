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
		
		@GetMapping("/getall")
		public Iterable<Inventory> getAll() {
			return repo.findAll();
		}
		
		@GetMapping("/getname/{name}")
		public List<Inventory> getName(@Valid @PathVariable(value="name") String name) {
			return repo.findByName(name);
		}
		
		@GetMapping("/getaddress/{address}")
		public List<Inventory> getAddress(@Valid @PathVariable(value="address") String address) {
			return repo.findByAddress(address);
		}
		
}
