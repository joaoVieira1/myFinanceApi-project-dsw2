package api.transactions.dsw2.controller.handler.implementation;

import api.transactions.dsw2.controller.command.implementation.GetBalanceTransactionCommand;
import api.transactions.dsw2.controller.handler.AbstractHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetBalanceTransactionHandler extends AbstractHandler{

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("GET") && request.getPathInfo().equals("/balance");
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new GetBalanceTransactionCommand().execute(request, response);
	}

}
