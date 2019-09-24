package com.example.demo.inventory.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InventoryItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String inventoryId;
	private String productId;
	private String stock;
	private String basePrice;
	private String updateStockDate; //yyyymmdd
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}
	public String getUpdateStockDate() {
		return updateStockDate;
	}
	public void setUpdateStockDate(String updateStockDate) {
		this.updateStockDate = updateStockDate;
	}
}