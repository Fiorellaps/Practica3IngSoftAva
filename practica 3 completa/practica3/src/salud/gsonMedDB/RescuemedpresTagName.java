package salud.gsonMedDB;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class RescuemedpresTagName extends ElementoCadenaMando {
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String FIELD_SEPARATOR = "; ";
	public RescuemedpresTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		StringBuffer readRescueMed = new StringBuffer();
		if (name.equals(RESCUEMEDPRES_TAGNAME)) {
			try {
				readRescueMed.append(RESCUEMEDPRES_TAGNAME+":");
				readRescueMed.append("\n");
				readRescueMed.append(readRescueMedicinePresentations(reader)).append("\n");
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
		return readRescueMed;
	}
	private StringBuffer readRescueMedicinePresentations(JsonReader reader) throws IOException {
		StringBuffer rescueMedicinePresentationData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			rescueMedicinePresentationData.append(readRescueMedicinePresentationEntry(reader)).append("\n");
			reader.endObject();
		}
		rescueMedicinePresentationData.append("\n");
		reader.endArray();
		return rescueMedicinePresentationData;
	}
	private String readRescueMedicinePresentationEntry(JsonReader reader) throws IOException {
		String medRef = null;
		String aiRef = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals(MEDREF_FIELD_TAGNAME))
				medRef = reader.nextString();
			else if(name.equals(ACTINGREF_FIELD_TAGNAME))
				aiRef = reader.nextString();
			//Error when reading those entries
			/*else if(name.equals(INHREF_FIELD_TAGNAME))
				inhRef = reader.nextString();

			else if(name.equals(DOSE_FIELD_TAGNAME))	
				dose = reader.nextString();
			 */
			else {
				reader.skipValue();
			}
		}
		return medRef + FIELD_SEPARATOR + aiRef;
	}
}
