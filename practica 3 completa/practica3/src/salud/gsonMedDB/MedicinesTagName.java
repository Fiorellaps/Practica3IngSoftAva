package salud.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class MedicinesTagName extends ElementoCadenaMando {
	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";

	public MedicinesTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		StringBuffer readMedicines = new StringBuffer();	
		if (name.equals(MEDICINES_TAGNAME)) {
			readMedicines.append(MEDICINES_TAGNAME+":");
			readMedicines.append("\n");
			readMedicines.append(readMedicines(reader)).append("\n");

		}

		else {
			if (sig != null) {
				return super.puedeLeer(name, reader);

			} else {

				reader.skipValue();

				System.err.println("Category " + name + " not processed.");

			}
		}
		return readMedicines;

	}
	private StringBuffer readMedicines(JsonReader reader) throws IOException {
		StringBuffer medicineData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			medicineData.append(readMedicineEntry(reader)).append("\n");
			reader.endObject();
		}
		medicineData.append("\n");
		reader.endArray();
		return medicineData;
	}
	private String readMedicineEntry(JsonReader reader) throws IOException {
		//	        reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
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
