package api.transactions.dsw2.model.service;

import api.transactions.dsw2.model.entity.Transaction;
import api.transactions.dsw2.model.entity.TransactionType;

public class TransactionService {
	
	public void validate(Transaction transaction) {
		transaction.setCategory(transaction.getCategory().trim());
		
		if(transaction.getValue() <= 0 || !transaction.getDate().matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$") || transaction.getType() == null) {
			throw new IllegalArgumentException();
		}
	}
	
}
