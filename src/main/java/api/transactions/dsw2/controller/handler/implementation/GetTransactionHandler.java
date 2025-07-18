package api.transactions.dsw2.controller.handler.implementation;

import api.transactions.dsw2.controller.command.implementation.GetTransactionCommand;
import api.transactions.dsw2.controller.handler.AbstractHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetTransactionHandler extends AbstractHandler{

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		String path = request.getPathInfo();
		if (path == null) return false;
		return request.getMethod().equals("GET") && path.matches("^/\\d+$");
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new GetTransactionCommand().execute(request, response);
	}

}
