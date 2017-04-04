package br.ufba.dcc.wiser.fot.manager.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

	public JSONObject getInformation(String ip, String domain, String path)
			throws JSONException, Exception, MalformedURLException, IOException {

		URL url = new URL("http://" + ip + ":8181/" + domain + "/" + path);
		// URL url = new URL("http://" + ip +
		// ":8181/cxf/gtw/gatewayservice/gateway/gt");
		System.out.println(">>>>>>>>>>>>>>>>> Entrou em JSON Input Stream");

		InputStream in = url.openStream();
		System.out.println(">>>>>>>>>>>>>>>>> Passou pelo JSON Input Stream");
		JSONObject json = new JSONObject(getStringFromInputStream(in));

		return json;
	}

	private static String getStringFromInputStream(InputStream in) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int c = 0;
		while ((c = in.read()) != -1) {
			bos.write(c);
		}
		in.close();
		bos.close();
		return bos.toString();
	}

}
