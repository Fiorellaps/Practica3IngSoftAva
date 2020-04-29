package salud.gsonMedDB;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualPhysTagName extends ElementoCadenaMando {
	private static final String MANUALPHYSIOSTEPS_TAGNAME = "userManualPhysioSteps";
	private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
	private static final String PHYSIOREF_FIELD_TAGNAME = "physioRef";
	private static final String FIELD_SEPARATOR = "; ";
	public UserManualPhysTagName(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		StringBuffer readManualPhysMed = new StringBuffer();
		if (name.equals(MANUALPHYSIOSTEPS_TAGNAME)) {
			try {
				readManualPhysMed.append(MANUALPHYSIOSTEPS_TAGNAME+":");
				readManualPhysMed.append("\n");
				readManualPhysMed.append(readManualPhys(reader)).append("\n");
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
		return readManualPhysMed;
	}
	private StringBuffer readManualPhys(JsonReader reader) throws IOException {
		StringBuffer rescueManualPhysData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			rescueManualPhysData.append(readManualPhysEntry(reader)).append("\n");
			reader.endObject();
		}
		rescueManualPhysData.append("\n");
		reader.endArray();
		return rescueManualPhysData;
	}
	private String readManualPhysEntry(JsonReader reader) throws IOException {
		String stepTitle = null;
		String stepImage = null;
		String stepText = null;
		String physioRef = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals(STEPTITLE_FIELD_TAGNAME))
				stepTitle = reader.nextString();
			else if(name.equals(STEPIMAGE_FIELD_TAGNAME))
				stepImage = reader.nextString();
			else if(name.equals(STEPTEXT_FIELD_TAGNAME))
				stepText = reader.nextString();
			else if(name.equals(PHYSIOREF_FIELD_TAGNAME))	
				physioRef = reader.nextString();
			else {
				reader.skipValue();
			}
		}
		return stepTitle + FIELD_SEPARATOR + stepImage + FIELD_SEPARATOR +
				stepText + FIELD_SEPARATOR + physioRef;
	}
}

