package api.transactions.dsw2.model.service;

import api.transactions.dsw2.model.entity.TransactionType;

public class TransactionService {
	
	public void validate(String date, double value, TransactionType type) {
		if(value <= 0 || !date.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$") || type == null) {
			throw new IllegalArgumentException("Argumentos inválidos para realizar a transação, consulte a documentação da API.");
		}
	}
	
}
