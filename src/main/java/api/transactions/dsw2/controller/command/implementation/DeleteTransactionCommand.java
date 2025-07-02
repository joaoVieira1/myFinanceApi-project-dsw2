package api.transactions.dsw2.controller.command.implementation;

import api.transactions.dsw2.controller.command.Command;
import api.transactions.dsw2.model.dao.TransactionDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteTransactionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getPathInfo().substring(1));
		TransactionDao dao = new TransactionDao();
		
		if(dao.delete(id)) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
