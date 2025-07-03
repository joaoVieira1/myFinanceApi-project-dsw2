package api.transactions.dsw2.controller.handler.implementation;

import api.transactions.dsw2.controller.command.implementation.GetListTransactionCommand;
import api.transactions.dsw2.controller.handler.AbstractHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetListTransactionHandler extends AbstractHandler{

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("GET") && (request.getPathInfo() == null || request.getPathInfo().equals("/"));
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new GetListTransactionCommand().execute(request, response);
	}

}
