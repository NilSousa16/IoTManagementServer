package br.ufba.dcc.wiser.fot.manager.util;

import java.io.FileNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;

/**
 * Class with utility methods
 * 
 * @author Nilson Rodrigues Sousa
 */
public class ConverterInfoJsonClass<T> {

	/**
	 * Method used to return as information from a class that is in json
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return T - returns JSON captured T class information
	 */
	public T getInfo(JSONObject jsonObject, Class<T> classe)
			throws JsonSyntaxException, JSONException, ClassNotFoundException, FileNotFoundException {
		System.out.println(">>>>>>>>>>>>>>>>Entrou no método getInfo()");
		Gson gson = new Gson();
		String typeInfo = classe.getSimpleName();
		// preciso que o T retorne o gateway.class por exemplo.
		// solução provisória seria utilizar um condicional
		@SuppressWarnings("unchecked")
		T t = (T) gson.fromJson(jsonObject.getJSONObject(typeInfo).toString(), Gateway.class);
		return t;
	}
}
