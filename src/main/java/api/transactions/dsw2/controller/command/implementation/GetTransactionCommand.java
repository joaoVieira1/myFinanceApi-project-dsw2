package api.transactions.dsw2.controller.command.implementation;

import com.google.gson.Gson;

import api.transactions.dsw2.controller.command.Command;
import api.transactions.dsw2.model.dao.TransactionDao;
import api.transactions.dsw2.model.entity.Transaction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetTransactionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getPathInfo().substring(1));
		TransactionDao dao = new TransactionDao();
		Transaction transaction = dao.findById(id);
		
		if(transaction == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().write("Transação não encontrada.");
		}else {
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write(new Gson().toJson(transaction));
		}
		
	}

}
