package api.transactions.dsw2.controller.handler;

import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class HandlerFactory {
	
	public static InterfaceHandler createChain() throws Exception{
		
		InputStreamReader reader = new InputStreamReader(HandlerFactory.class.getClassLoader().getResourceAsStream("chain-config.json"));
		JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
		JsonArray array = json.getAsJsonArray("chain");
		List<InterfaceHandler> handlers = new ArrayList<>();
		
		for (JsonElement element : array) {
			String className = element.getAsString();
			Class<?> classObject = Class.forName(className);
			Constructor<?> constructor = classObject.getDeclaredConstructor();
			InterfaceHandler handler = (InterfaceHandler) constructor.newInstance();
			handlers.add(handler);
		}
		
		for (int i = 0; i < handlers.size() - 1; i++) {
			handlers.get(i).setNext(handlers.get(i + 1));
		}
		
		return handlers.get(0);
		
	}
	
}
