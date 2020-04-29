package salud.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class PhysiotherapiesTagName extends ElementoCadenaMando {
	private static final String PHYSIOTERAPIES_TAGNAME = "physiotherapies";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String FIELD_SEPARATOR = "; ";

	public PhysiotherapiesTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		StringBuffer readPhysioterapies = new StringBuffer();	
		if (name.equals(PHYSIOTERAPIES_TAGNAME)) {
			readPhysioterapies.append(PHYSIOTERAPIES_TAGNAME+":");
			readPhysioterapies.append("\n");
			readPhysioterapies.append(readPhysio(reader)).append("\n");
		}

		else {
			if (sig != null) {
				return super.puedeLeer(name, reader);

			} else {

				reader.skipValue();

				System.err.println("Category " + name + " not processed.");

			}
		}
		return readPhysioterapies;

	}

	private StringBuffer readPhysio(JsonReader reader) throws IOException {
		StringBuffer physioData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			physioData.append(readPhysioEntry(reader)).append("\n");
			reader.endObject();
		}
		physioData.append("\n");
		reader.endArray();
		return physioData;
	}
	private String readPhysioEntry(JsonReader reader) throws IOException {
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
