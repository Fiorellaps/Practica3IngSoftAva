package salud.gsonMedDB;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualStepsTagName extends LecturaJson {
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
		return super.leerTagName(name, reader, MANUALSTEPS_TAGNAME);
	}
	public String readEntry(JsonReader reader) throws IOException {
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