package salud.gsonMedDB;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class PosologiesTagName extends LecturaJson {
	private static final String POSOLOGIES_TAGNAME = "posologies";
	private static final String DESCRIPTION_FIELD_TAGNAME = "description";

	public PosologiesTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		return super.leerTagName(name, reader, POSOLOGIES_TAGNAME);
	}
	public String readEntry(JsonReader reader) throws IOException {
		String posDesc = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if (name.equals(DESCRIPTION_FIELD_TAGNAME))
				posDesc = reader.nextString();
			else {
				reader.skipValue();
			}
		}
		return posDesc;
	}
}
