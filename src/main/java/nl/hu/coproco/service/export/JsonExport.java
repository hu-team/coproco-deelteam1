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

    @Override // functie die alles van de ophaal functie omzet naar Json export
    public boolean saveExport(String filename) {
        allPatterns = patternStorage.getPatterns();
        //  JsonArray JsonArray = new JsonArray.fromObject(allPatterns);



        return true;

        try
        {
            int i = 0;
            JsonArray jArry = new JsonArray();
            for (i=0;i<=allPatterns.size();i++) //
            {
                JsonObject jObjd = new JsonObject();
                jObjd.addProperty("key", allPatterns.get(i).getConsequences());
                jObjd.addProperty("key", allPatterns.get(i).getName());
                jObjd.addProperty("key", allPatterns.get(i).getProblem());
                jObjd.addProperty("key", allPatterns.get(i).getSolution());
                jObjd.addProperty("key", allPatterns.get(i).getDiagram().getFilePath());
                jObjd.addProperty("key", allPatterns.get(i).getPurpose().getName());
                jObjd.addProperty("key", allPatterns.get(i).getScope().getName());
                jObjd.addProperty("key", allPatterns.get(i).getClass().getName());

                jObjd.put("key", value);
                jArry.put(jObjd);

            }
        }
        catch(JsonIOException ex)
        {}
    }




    //export waarheen? Gewoon textfile?
}
