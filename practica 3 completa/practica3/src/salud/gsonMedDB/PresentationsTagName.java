package salud.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class PresentationsTagName extends ElementoCadenaMando {
	private static final String MEDICINEPRES_TAGNAME = "medicinePresentations";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String FIELD_SEPARATOR = "; ";

	public PresentationsTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		StringBuffer readPresentationsMed = new StringBuffer();
		if (name.equals(MEDICINEPRES_TAGNAME)) {
			try {
				readPresentationsMed.append(MEDICINEPRES_TAGNAME+":");
				readPresentationsMed.append("\n");
				readPresentationsMed.append(readPresentations(reader)).append("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			if (sig != null) {
				return super.puedeLeer(name, reader);
			} else {
				reader.skipValue();
				System.err.println("Category " + name + " not processed.");
			}
		}
		return readPresentationsMed;
	}
	private StringBuffer readPresentations(JsonReader reader) throws IOException {
		StringBuffer rescuPresentationData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			rescuPresentationData.append(readPresentationEntry(reader)).append("\n");
			reader.endObject();
		}
		rescuPresentationData.append("\n");
		reader.endArray();
		return rescuPresentationData;
	}
	private String readPresentationEntry(JsonReader reader) throws IOException {
		String medRef = null;
		String aiRef = null;
		while(reader.hasNext()&& reader.peek()!=JsonToken.NULL){
			String name = reader.nextName();
			if(name.equals(MEDREF_FIELD_TAGNAME))
				medRef = reader.nextString();
			else if(name.equals(ACTINGREF_FIELD_TAGNAME)) 	
				aiRef = reader.nextString();
			// Error when reading those entries
			/*else if(name.equals(INHALER_FIELD_TAGNAME)) 	
				inRef = reader.nextString();
			else if(name.equals(DOSE_FIELD_TAGNAME)) 	
				dose = reader.nextString();
			else if(name.equals(POSOLOGY_FIELD_TAGNAME)) 	
				posRef = reader.nextString();
			 */
			else {
				reader.skipValue();
			}
		}
		return medRef + FIELD_SEPARATOR + aiRef;
	}


}
