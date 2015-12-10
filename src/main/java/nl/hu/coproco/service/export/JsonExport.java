package nl.hu.coproco.service.export;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import nl.hu.coproco.domain.PatternStorage;
import nl.hu.coproco.domain.Pattern;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;



public class JsonExport implements Export {
    private PatternStorage patternStorage;
    private ArrayList<Pattern> allPatterns = new ArrayList<Pattern>();
    private int i = 0;

    //Initializing new JsonArray
    private JsonArray jArry = new JsonArray();


    //Main method used to call the other methods and use this class
    public boolean saveExport(String filename) {
       if(fillJsonArray()){
           //Create the JSon output file
           createJsonOutputFile(filename);
           return true;
       }else{return false;}
    }

    private boolean fillJsonArray(){
        //getting all patterns, should use the controller instead of speaking directly to patternStorage
        allPatterns = patternStorage.getPatterns();

        //try catch to fill up the Json-array
        try
        {
            for (i=0;   i<=allPatterns.size();  i++)
            {
                JsonObject jObj = new JsonObject();
                //One JsonObject per Object in the ArrayList

                jObj.addProperty("Name", allPatterns.get(i).getName());
                jObj.addProperty("Problem", allPatterns.get(i).getProblem());
                jObj.addProperty("Solution", allPatterns.get(i).getSolution());
                jObj.addProperty("Consequences", allPatterns.get(i).getConsequences());
                jObj.addProperty("ScopeName", allPatterns.get(i).getScope().getName());
                jObj.addProperty("PurposeName", allPatterns.get(i).getPurpose().getName());
                jObj.addProperty("Diagram", allPatterns.get(i).getDiagram().getEncodedImage());

                //This is optional, but could be useful
                jObj.addProperty("ClassName", allPatterns.get(i).getClass().getName());

                //Adding to the array
                jArry.add(jObj);
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

    private void createJsonOutputFile(String filename) {
        //Creating the output JSon file
        JsonObject outputFileJSon = new JsonObject();
        outputFileJSon.addProperty("name", filename);
        outputFileJSon.add("Patterns", jArry);

        //Actually writing the data to a file
        writeFile(outputFileJSon);
    }

    private void writeFile(JsonObject outputFileJSon) {
        try{
            //Creating the actual file for the client. Path could contain variables to decide the path yourself, but we should have a String for it then
            FileWriter exportFile = new FileWriter("/Users/Patterns.txt");
            //Writing the JSon to text in this file
            exportFile.write(outputFileJSon.toString());
            System.out.println("Succesfully copied JSON Object to File...");
        }
        catch(IOException ex){}
    }
}
