package salud.gsonMedDB;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class RescuemedpresTagName extends LecturaJson {
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String FIELD_SEPARATOR = "; ";
	public RescuemedpresTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		return super.leerTagName(name, reader, RESCUEMEDPRES_TAGNAME);
	}
	public String readEntry(JsonReader reader) throws IOException {
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
