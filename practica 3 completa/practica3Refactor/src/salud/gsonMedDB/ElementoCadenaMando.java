package salud.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ElementoCadenaMando {
protected ElementoCadenaMando sig;
	
	public ElementoCadenaMando(ElementoCadenaMando s) {
		sig = s;
	}
	
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException {
		return sig.puedeLeer( name, reader);
	}
}
