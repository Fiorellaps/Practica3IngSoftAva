package salud.gsonMedDB;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualStepsTagName extends ElementoCadenaMando {
	private static final String MANUALSTEPS_TAGNAME = "userManualSteps";
	private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
	private static final String INHALERREF_FIELD_TAGNAME = "inhalerRef";
	private static final String FIELD_SEPARATOR = "; ";
	public UserManualStepsTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		StringBuffer readManualMed = new StringBuffer();
		if (name.equals(MANUALSTEPS_TAGNAME)) {
			try {
				readManualMed.append(MANUALSTEPS_TAGNAME+":");
				readManualMed.append("\n");
				readManualMed.append(readManualPhys(reader)).append("\n");
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
		return readManualMed;
	}
	private StringBuffer readManualPhys(JsonReader reader) throws IOException {
		StringBuffer rescueManualData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			rescueManualData.append(readManualEntry(reader)).append("\n");
			reader.endObject();
		}
		rescueManualData.append("\n");
		reader.endArray();
		return rescueManualData;
	}
	private String readManualEntry(JsonReader reader) throws IOException {
		String stepTitle = null;
		String stepImage = null;
		String stepText = null;
		String inhalersRef = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals(STEPTITLE_FIELD_TAGNAME))
				stepTitle = reader.nextString();
			else if(name.equals(STEPIMAGE_FIELD_TAGNAME))
				stepImage = reader.nextString();
			else if(name.equals(STEPTEXT_FIELD_TAGNAME))
				stepText = reader.nextString();
			else if(name.equals(INHALERREF_FIELD_TAGNAME))	
				inhalersRef = reader.nextString();
			else {
				reader.skipValue();
			}
		}
		return stepTitle + FIELD_SEPARATOR + stepImage + FIELD_SEPARATOR +
				stepText + FIELD_SEPARATOR + inhalersRef;
	}
}

