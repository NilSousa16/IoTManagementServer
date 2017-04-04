package br.ufba.dcc.wiser.fot.manager.util;

import java.io.FileNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;

public class ConverterInfoJsonClass<T> {

	// method used to return as information from a class that is in json
	public T getInfo(JSONObject jsonObject, Class<T> classe)
			throws JsonSyntaxException, JSONException, ClassNotFoundException, FileNotFoundException {
		System.out.println(">>>>>>>>>>>>>>>>Entrou no método getInfo()");
		Gson gson = new Gson();
		String typeInfo = classe.getSimpleName();
		// preciso que o T retorne o gateway.class por exemplo.
		@SuppressWarnings("unchecked")
		T t = (T) gson.fromJson(jsonObject.getJSONObject(typeInfo).toString(), Gateway.class);
		return t;
	}
}
