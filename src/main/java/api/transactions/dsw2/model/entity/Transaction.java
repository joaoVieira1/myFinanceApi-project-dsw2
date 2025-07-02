package api.transactions.dsw2.model.entity;

import java.time.LocalDateTime;

public class Transaction {
	
	private String description;
	private double value;
	private TransactionType type;
	private String category;
	private LocalDateTime date;
	
	public Transaction() {
		
	}
	
	

	public Transaction(String description, double value, TransactionType type, String category, LocalDateTime date) {
		super();
		this.description = description;
		this.value = value;
		this.type = type;
		this.category = category;
		this.date = date;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
	
}
