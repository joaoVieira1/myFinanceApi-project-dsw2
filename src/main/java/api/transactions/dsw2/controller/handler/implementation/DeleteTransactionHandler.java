package api.transactions.dsw2.controller.handler.implementation;

import api.transactions.dsw2.controller.command.implementation.DeleteTransactionCommand;
import api.transactions.dsw2.controller.handler.AbstractHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteTransactionHandler extends AbstractHandler{

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("DELETE") && request.getPathInfo().matches("^/\\d+$");
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new DeleteTransactionCommand().execute(request, response);
	}

}
