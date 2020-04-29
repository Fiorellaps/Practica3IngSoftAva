package salud.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ActiveIngredientsTag extends LecturaJson {
	private static final String INGREDIENTS_TAGNAME = "activeIngredients";
	private static final String NAME_FIELD_TAGNAME = "name";
	public ActiveIngredientsTag(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		//Calls to mother's method leetTagName
		return super.leerTagName(name, reader, INGREDIENTS_TAGNAME);
	}
	//We don't need anymore readValues because it is the same for all element so it is in the super class
	//readEntry remains as before, because it is different for all classes
	public String readEntry(JsonReader reader) throws IOException {
		String actName = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME))
				actName = reader.nextString();
			else {
				reader.skipValue();
			}
		}
		return actName;
	}
}

