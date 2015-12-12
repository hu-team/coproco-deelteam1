package nl.hu.coproco.service.loader;

import nl.hu.coproco.domain.*;
import nl.hu.coproco.service.PatternService;
import nl.hu.coproco.service.PurposeService;
import nl.hu.coproco.service.ScopeService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XmlImport implements Import {

    @Override
    public boolean loadExport(File file) {
        if (!file.exists() || !file.canRead()) {
            return false;
        }

        // Parse document from file
        Document document = this.parseDocument(file);

        if (document == null) { return false; }

        Element root = document.getDocumentElement();

        // Elements
        Element application = null;
        Element patterns = null;

        // Parse application vars
        String version = null;
        int build = 0;

        try {
            application = (Element)root.getElementsByTagName("application").item(0);
            patterns = (Element)root.getElementsByTagName("patterns").item(0);

            version = application.getAttribute("version");
            build = Integer.parseInt(application.getAttribute("build"));
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        // Parse the patterns
        ArrayList<Pattern> importedPatterns = this.parsePatterns(patterns);

        if (importedPatterns == null) {
            return false;
        }

        // Set patterns
        PatternService.setAllPatterns(importedPatterns);

        return true;
    }

    private Document parseDocument(File input) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            return documentBuilder.parse(input);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<Pattern> parsePatterns(Element patternRoot) {
        try {
            NodeList patternNodes = patternRoot.getElementsByTagName("pattern");

            ArrayList<Pattern> patterns = new ArrayList<>();

            for (int i = 0; i < patternNodes.getLength(); i++) {
                Element patternElement = (Element) patternNodes.item(i);

                String name = ((Element) patternElement.getElementsByTagName("name").item(0)).getTextContent();
                String problem = ((Element) patternElement.getElementsByTagName("problem").item(0)).getTextContent();
                String solution = ((Element) patternElement.getElementsByTagName("solution").item(0)).getTextContent();
                String consequences = ((Element) patternElement.getElementsByTagName("consequences").item(0)).getTextContent();

                Scope scope = ScopeService.getScopeByName(((Element) patternElement.getElementsByTagName("scope").item(0)).getTextContent());
                Purpose purpose = PurposeService.getPurposeByName(((Element) patternElement.getElementsByTagName("purpose").item(0)).getTextContent());

                CachableImage diagram = new ProxyImage(((Element) patternElement.getElementsByTagName("diagram").item(0)).getTextContent());

                Pattern pattern = new Pattern(name);
                pattern.setProblem(problem);
                pattern.setSolution(solution);
                pattern.setConsequences(consequences);

                pattern.setScope(scope);
                pattern.setPurpose(purpose);

                pattern.setDiagram(diagram);

                patterns.add(pattern);
            }

            return patterns;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
