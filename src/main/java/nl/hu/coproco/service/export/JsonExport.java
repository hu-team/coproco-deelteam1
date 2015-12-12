package nl.hu.coproco.service.export;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import nl.hu.coproco.Main;
import nl.hu.coproco.domain.PatternStorage;
import nl.hu.coproco.domain.Pattern;

import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;



public class JsonExport implements Export {
    private ArrayList<Pattern> allPatterns;
    private int i = 0;

    // Initializing new JsonArray
    private JsonArray jArry = new JsonArray();

    // test function
    public JsonExport(ArrayList<Pattern> patterns){
        this.allPatterns = patterns;
    }

    // Main method used to call the other methods and use this class
    public boolean saveExport(File file) {
        if(fillJsonArray()){
            // Create the JSon output file
            createJsonOutputFile(file);
            return true;
        }
        return false;
    }

    private boolean fillJsonArray(){
        // try catch to fill up the Json-array
        try {
            for (i = 0; i < allPatterns.size(); i++) {
                JsonObject jObj = new JsonObject();
                // One JsonObject per Object in the ArrayList

                jObj.addProperty("Name", allPatterns.get(i).getName());
                jObj.addProperty("Problem", allPatterns.get(i).getProblem());
                jObj.addProperty("Solution", allPatterns.get(i).getSolution());
                jObj.addProperty("Consequences", allPatterns.get(i).getConsequences());
                jObj.addProperty("ScopeName", allPatterns.get(i).getScope().getName());
                jObj.addProperty("PurposeName", allPatterns.get(i).getPurpose().getName());
                jObj.addProperty("Diagram", allPatterns.get(i).getDiagram().getEncodedImage());

                // This is optional, but could be useful when validating the class it was on the moment of exporting
                jObj.addProperty("ClassName", allPatterns.get(i).getClass().getName());

                // Adding to the array
                jArry.add(jObj);
            }
        }

        catch(JsonIOException ex)
        {
            System.out.println("Error at " + i + "- fillJsonArray() Catch(){}");
            jArry = null;
            return false;
        }
        // If everything in the for loop has been written succesfully to the jArry it will return true,
        // so that it can be exported. You want to empty the jArry once you get an error to prevent duplicates.
        return true;
    }

    private void createJsonOutputFile(File file) {
        // Creating the output JSon file
        JsonObject outputFileJSon = new JsonObject();

        outputFileJSon.addProperty("name", file.getName());
        outputFileJSon.addProperty("build", Main.BUILD);
        outputFileJSon.addProperty("version", Main.VERSION);

        outputFileJSon.add("Patterns", jArry);

        // Actually writing the data to a file
        writeFile(file, outputFileJSon);
    }

    private void writeFile(File file, JsonObject outputFileJSon) {
        try{
            // Creating the actual file for the client. Path could contain variables to decide the path yourself, but we should have a String for it then
            PrintWriter exportFile = new PrintWriter(file, "UTF-8");
            // Writing the JSon to text in this file

            exportFile.write(outputFileJSon.toString());
            exportFile.close();
            System.out.println("Succesfully copied JSON Object to File...");
        }
        catch(IOException ex){
            System.out.println("Error at writeFile() IOException");
        }
    }
}
