package nl.hu.coproco.service.export;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import nl.hu.coproco.domain.PatternStorage;
import nl.hu.coproco.domain.Pattern;

import java.util.ArrayList;



import java.util.Arrays;


public class JsonExport implements Export {
    private PatternStorage patternStorage;
    private ArrayList<Pattern> allPatterns = new ArrayList<Pattern>();
    private int i = 0;

    //Initializing new JsonArray
    private JsonArray jArry = new JsonArray();

    @Override
    public boolean saveExport(String filename) {
       if(fillJsonArray(filename)){
           return true;
       }else{return false;}
    }

    public boolean fillJsonArray(String fileName){
        //getting all patterns, should use the controller instead of speaking directly to patternStorage
        allPatterns = patternStorage.getPatterns();

        //What do we use fileName for?

        //try catch to fill up the Json-array
        try
        {
            for (i=0;   i<=allPatterns.size();  i++)
            {
                JsonObject jObjd = new JsonObject();
                //One JsonObject per Object in the ArrayList

                jObjd.addProperty("Name", allPatterns.get(i).getName());
                jObjd.addProperty("Problem", allPatterns.get(i).getProblem());
                jObjd.addProperty("Solution", allPatterns.get(i).getSolution());
                jObjd.addProperty("Consequences", allPatterns.get(i).getConsequences());
                jObjd.addProperty("ScopeName", allPatterns.get(i).getScope().getName());
                jObjd.addProperty("PurposeName", allPatterns.get(i).getPurpose().getName());
                jObjd.addProperty("Diagram", allPatterns.get(i).getDiagram().getFilePath());

                //This is optional, but could be useful
                jObjd.addProperty("ClassName", allPatterns.get(i).getClass().getName());

                //Adding to the array
                jArry.add(jObjd);
            }
        }

        catch(JsonIOException ex)
        {
            System.out.println("Error bij " + i + "- fillJsonArray() Catch(){}");
            jArry = null;
            return false;
        }
        //If everything in the for loop has been written succesfully to the jArry it will return true,
        // so that it can be exported. You want to empty the jArry once you get an error to prevent duplicates.
        return true;
    }
}
