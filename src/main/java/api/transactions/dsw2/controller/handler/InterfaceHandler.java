package api.transactions.dsw2.controller.handler;

import java.net.http.HttpRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface InterfaceHandler {
	
	void setNext(InterfaceHandler next);
	
	void handle(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
