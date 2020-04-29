package salud.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class MedicinesTagName extends LecturaJson {
	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";

	public MedicinesTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		return super.leerTagName(name, reader, MEDICINES_TAGNAME);
	}
	public String readEntry(JsonReader reader) throws IOException {
		String medName = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME))
				medName = reader.nextString();

			else {
				reader.skipValue();
			}
		}
		return medName;
	}
}
