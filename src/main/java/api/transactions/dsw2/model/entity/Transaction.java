package api.transactions.dsw2.model.entity;

public class Transaction {
	
	private int id;
	private String description;
	private double value;
	private TransactionType type;
	private String category;
	private String date;
	
	public Transaction() {
		
	}

	public Transaction(int id, String description, double value, TransactionType type, String category, String date) {
		super();
		setId(id);
		setDescription(description);
		setValue(value);
		setType(type);
		setCategory(category);
		setDate(date);
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
