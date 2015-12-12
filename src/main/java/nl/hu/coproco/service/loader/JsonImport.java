package nl.hu.coproco.service.loader;

import com.google.gson.*;
import nl.hu.coproco.domain.Pattern;
import nl.hu.coproco.domain.ProxyImage;
import nl.hu.coproco.service.PatternService;
import nl.hu.coproco.service.PurposeService;
import nl.hu.coproco.service.ScopeService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class JsonImport implements Import {

    @Override
    public boolean loadExport(File file) {
        if (!file.exists() || !file.canRead()) {
            return false;
        }

        // Load file into string
        String jsonImport = null;
        try {
            jsonImport = this.readFile(file);
        }catch(IOException ioe) {
            ioe.printStackTrace();
            return false;
        }

        // Parse Json
        JsonObject container = null;
        try {
            JsonParser parser = new JsonParser();
            container = parser.parse(jsonImport).getAsJsonObject();
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            this.parseJson(container);
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String readFile(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private void parseJson(JsonObject container) throws Exception {
        // Get the export version of application
        int build = container.get("build").getAsInt();
        String version = container.get("version").getAsString();

        System.out.println("Loading import with version " + version + " and build: " + build);

        // Get array
        JsonArray array = container.get("Patterns").getAsJsonArray();

        ArrayList<Pattern> patterns = new ArrayList<Pattern>();
        // Loop and make the pattern objects
        for(JsonElement e: array) {
            JsonObject patternObject = e.getAsJsonObject();

            if (patternObject.get("ClassName").getAsString().equals("nl.hu.coproco.domain.Pattern")) {
                Pattern pattern = new Pattern(patternObject.get("Name").getAsString());
                pattern.setProblem(patternObject.get("Problem").getAsString());
                pattern.setSolution(patternObject.get("Solution").getAsString());
                pattern.setConsequences(patternObject.get("Consequences").getAsString());

                pattern.setScope(ScopeService.getScopeByName(patternObject.get("ScopeName").getAsString()));
                pattern.setPurpose(PurposeService.getPurposeByName(patternObject.get("PurposeName").getAsString()));

                pattern.setDiagram(new ProxyImage(patternObject.get("Diagram").getAsString()));

                patterns.add(pattern);
            }
        }

        // Set the array in the service
        PatternService.setAllPatterns(patterns);
    }
}
