package api.transactions.dsw2.controller.command.implementation;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

import api.transactions.dsw2.controller.command.Command;
import api.transactions.dsw2.model.dao.TransactionDao;
import api.transactions.dsw2.model.entity.TransactionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetBalanceTransactionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TransactionDao dao = new TransactionDao();

        Map<String, Double> revenues = dao.sumByCategory(TransactionType.RECEITA);
        Map<String, Double> expenses = dao.sumByCategory(TransactionType.DESPESA);

        double totalRevenues = revenues.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalExpenses = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
        double balance = totalRevenues - totalExpenses;

        Map<String, Object> resume = new LinkedHashMap<>();
        resume.put("receitasPorCategoria", revenues);
        resume.put("despesasPorCategoria", expenses);
        resume.put("saldoAtual", balance);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(resume));
	}

}
