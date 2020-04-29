package salud.gsonMedDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

public class ClienteCadenaMando {
	ElementoCadenaMando cadenademando;
	public ClienteCadenaMando(ElementoCadenaMando cm) {
		cadenademando = cm;
	}
	public void setCadenaDeMando(ElementoCadenaMando ncm) {
		cadenademando = ncm;
	}
	public String parse(String jsonFileName) throws IOException {
		InputStream usersIS = new FileInputStream (new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));
		reader.beginObject();
		StringBuffer readData = new StringBuffer();
		while (reader.hasNext()) {
			String name = reader.nextName();
			readData.append(cadenademando.puedeLeer(name, reader));
		}
		reader.endObject();
		reader.close();
		usersIS.close();
		return new String(readData);
	}
}


