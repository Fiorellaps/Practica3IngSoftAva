package salud.gsonMedDB;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class PhysiotherapiesTagName extends LecturaJson {
	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String FIELD_SEPARATOR = "; ";
	public PhysiotherapiesTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		return super.leerTagName(name, reader, PHYSIOTHERAPIES_TAGNAME);
	}
	public String readEntry(JsonReader reader) throws IOException {
		String physName = null;
		String physImage = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME))
				physName = reader.nextString();
			else if (name.equals(IMAGE_FIELD_TAGNAME))
				physImage= reader.nextString();
			else {
				reader.skipValue();
			}
		}
		return physName + FIELD_SEPARATOR + physImage;
	}
}
