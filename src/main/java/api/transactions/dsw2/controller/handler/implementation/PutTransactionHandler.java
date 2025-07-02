package api.transactions.dsw2.controller.handler.implementation;

import api.transactions.dsw2.controller.command.implementation.PutTransactionCommand;
import api.transactions.dsw2.controller.handler.AbstractHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PutTransactionHandler extends AbstractHandler {

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("PUT") && request.getPathInfo().matches("^/\\d+$");
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new PutTransactionCommand().execute(request, response);
	}

}
