package salud.gsonMedDB;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class LecturaJson extends ElementoCadenaMando  {
	public LecturaJson(ElementoCadenaMando s) {
		super(s);
	}
	//common method for reading the Json File
	public StringBuffer leerTagName(String name, JsonReader reader, String tagName) throws IOException  {
		StringBuffer readTag = new StringBuffer();	
		if (name.equals(tagName)) {
			readTag.append(tagName+":");
			readTag.append("\n");
			readTag.append(readValues(reader)).append("\n");
		}
		else {
			if (sig != null) {
				return super.puedeLeer(name, reader);

			} else {
				reader.skipValue();
				System.err.println("Category " + name + " not processed.");
			}
		}
		return readTag;
	}
	//common method for reading the values of a Tag 
	public StringBuffer readValues(JsonReader reader) throws IOException {
		StringBuffer tagnameData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			tagnameData.append(readEntry(reader)).append("\n");
			reader.endObject();
		}
		tagnameData.append("\n");
		reader.endArray();
		return tagnameData;
	}
	//abstract method for reading the different entries of a Tag, which is different depending on the class.
	public abstract String readEntry(JsonReader reader) throws IOException;
}
