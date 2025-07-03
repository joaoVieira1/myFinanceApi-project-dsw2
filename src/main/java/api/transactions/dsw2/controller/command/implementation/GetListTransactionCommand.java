package api.transactions.dsw2.controller.command.implementation;

import java.util.List;

import com.google.gson.Gson;

import api.transactions.dsw2.controller.command.Command;
import api.transactions.dsw2.model.dao.TransactionDao;
import api.transactions.dsw2.model.entity.Transaction;
import api.transactions.dsw2.model.entity.TransactionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetListTransactionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TransactionDao dao = new TransactionDao();

        // Parâmetros opcionais
        String monthParam = request.getParameter("month");
        String yearParam = request.getParameter("year");
        String typeParam = request.getParameter("type");
        String categoryParam = request.getParameter("category");
        String pageParam = request.getParameter("page");
        String sizeParam = request.getParameter("size");

        Integer month = monthParam != null ? Integer.parseInt(monthParam) : null;
        Integer year = yearParam != null ? Integer.parseInt(yearParam) : null;
        TransactionType type = null;
        if (typeParam != null) {
            try {
                type = TransactionType.valueOf(typeParam.toUpperCase());
            } catch (IllegalArgumentException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Tipo inválido. Use 'RECEITA' ou 'DESPESA'.");
                return;
            }
        }

        int page = pageParam != null ? Integer.parseInt(pageParam) : 1;
        int size = sizeParam != null ? Integer.parseInt(sizeParam) : 10;
        int offset = (page - 1) * size;

        List<Transaction> transactions = dao.findAllWithFilters(month, year, type, categoryParam, offset, size);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(transactions));
		
		
	}

}
