package api.transactions.dsw2.controller.command.implementation;

import java.io.BufferedReader;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import api.transactions.dsw2.controller.command.Command;
import api.transactions.dsw2.model.dao.TransactionDao;
import api.transactions.dsw2.model.entity.Transaction;
import api.transactions.dsw2.model.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PutTransactionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getPathInfo().substring(1));
		BufferedReader reader = request.getReader();
		TransactionDao dao = new TransactionDao();
		TransactionService service = new TransactionService();
		
		try {
			Transaction updateTransaction = new Gson().fromJson(reader, Transaction.class);
			service.validate(updateTransaction);
			
			if(dao.update(id, updateTransaction)) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write("Atualização realizada.");
			}else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().write("Transação não encontrada.");
			}
			
		}catch(IllegalArgumentException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Argumentos inválidos para realizar a transação, consulte a documentação da API.");
		}catch(JsonSyntaxException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("JSON mal formado ou argumento ausente, consulte a documentação da API.");
		}catch(Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Erro interno: " + e.getMessage());
		}
		
		
		
	}

}
