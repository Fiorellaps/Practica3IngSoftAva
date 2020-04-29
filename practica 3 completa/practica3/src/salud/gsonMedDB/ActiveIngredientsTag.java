package salud.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ActiveIngredientsTag extends ElementoCadenaMando {
	private static final String INGREDIENTS_TAGNAME = "activeIngredients";
	private static final String NAME_FIELD_TAGNAME = "name";
	public ActiveIngredientsTag(ElementoCadenaMando s) {
		super(s);
	}
	public StringBuffer puedeLeer(String name, JsonReader reader) throws IOException  {
		StringBuffer readIngredients = new StringBuffer();	
		if (name.equals(INGREDIENTS_TAGNAME)) {
			readIngredients.append(INGREDIENTS_TAGNAME+":");
			readIngredients.append("\n");
			readIngredients.append(readIngredients(reader)).append("\n");
		}
		else {
			if (sig != null) {
				return super.puedeLeer(name, reader);
			} else {
				reader.skipValue();
				System.err.println("Category " + name + " not processed.");
			}
		}
		return readIngredients;
	}
	private StringBuffer readIngredients(JsonReader reader) throws IOException {
		StringBuffer ingredientsData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			ingredientsData.append(readIngredientsEntry(reader)).append("\n");
			reader.endObject();
		}
		ingredientsData.append("\n");
		reader.endArray();
		return ingredientsData;
	}
	private String readIngredientsEntry(JsonReader reader) throws IOException {
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
