package salud.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class PosologiesTagName extends ElementoCadenaMando {
	private static final String POSOLOGIES_TAGNAME = "posologies";
	private static final String DESCRIPTION_FIELD_TAGNAME = "description";

	public PosologiesTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		StringBuffer readPosologies = new StringBuffer();	
		if (name.equals(POSOLOGIES_TAGNAME)) {
			readPosologies.append(POSOLOGIES_TAGNAME+":");
			readPosologies.append("\n");
			readPosologies.append(readPosologies(reader)).append("\n");
		}
		else {
			if (sig != null) {
				return super.puedeLeer(name, reader);
			} else {
				reader.skipValue();
				System.err.println("Category " + name + " not processed.");
			}
		}
		return readPosologies;
	}
	private StringBuffer readPosologies(JsonReader reader) throws IOException {
		StringBuffer posologiesData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			posologiesData.append(readPosologiesEntry(reader)).append("\n");
			reader.endObject();
		}
		posologiesData.append("\n");
		reader.endArray();
		return posologiesData;
	}
	private String readPosologiesEntry(JsonReader reader) throws IOException {
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
