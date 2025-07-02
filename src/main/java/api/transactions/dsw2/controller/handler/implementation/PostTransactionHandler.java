package api.transactions.dsw2.controller.handler.implementation;

import api.transactions.dsw2.controller.command.implementation.PostTransactionCommand;
import api.transactions.dsw2.controller.handler.AbstractHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PostTransactionHandler extends AbstractHandler{

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("POST") && (request.getPathInfo().equals("/") || request.getPathInfo() == null);
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new PostTransactionCommand().execute(request, response);
	}

}
