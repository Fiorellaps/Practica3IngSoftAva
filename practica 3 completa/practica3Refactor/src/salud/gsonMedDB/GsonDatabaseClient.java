package salud.gsonMedDB;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try{
			RescuemedpresTagName rescMedPres=new RescuemedpresTagName(null);
			ActiveIngredientsTag ingredients= new ActiveIngredientsTag(rescMedPres);
			MedicinesTagName medicines= new MedicinesTagName(ingredients);
			PhysiotherapiesTagName physio= new PhysiotherapiesTagName(medicines);
			InhalersTagName inhalers= new InhalersTagName (physio);
			PosologiesTagName posologies = new PosologiesTagName(inhalers);
			PresentationsTagName presentations = new PresentationsTagName(posologies);
			UserManualPhysTagName manualPhys= new UserManualPhysTagName(presentations);
			UserManualStepsTagName manualSteps= new UserManualStepsTagName(manualPhys);
			ClienteCadenaMando ccm = new ClienteCadenaMando(manualSteps);
			try {
				System.out.println(ccm.parse("./datos.json"));
			} finally {
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
