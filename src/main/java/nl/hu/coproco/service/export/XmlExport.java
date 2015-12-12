package nl.hu.coproco.service.export;

import nl.hu.coproco.Main;
import nl.hu.coproco.domain.Pattern;
import nl.hu.coproco.service.PatternService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlExport implements Export {
    private Document document = null;
    private Element rootElement = null;

    public XmlExport() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            this.document = documentBuilder.newDocument();

            this.rootElement = this.document.createElement("export");
            this.document.appendChild(rootElement);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveExport(File file) {
        // We need to convert to xml first.
        Element application = this.document.createElement("application");
        this.rootElement.appendChild(application);

        application.setAttribute("build", "" + Main.BUILD);
        application.setAttribute("version", Main.VERSION);

        // The list will go here
        Element patterns = this.document.createElement("patterns");

        // Add to root
        this.rootElement.appendChild(patterns);

        // Will loop
        this.exportToElement(patterns);

        // Will export to file
        return this.exportXmlToFile(file);
    }

    private boolean exportXmlToFile(File originalFile) {
        // Add extension
        File file = new File(originalFile.getAbsoluteFile() + ".xml");

        try {
            // Transformers
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(this.document);

            StreamResult stream = new StreamResult(file);

            // Transform now.
            transformer.transform(source, stream);
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private void exportToElement(Element root) {
        // Loop patterns
        for(Pattern pattern: PatternService.getAllPatterns()) {
            Element p = this.document.createElement("pattern");

            Element pName = this.document.createElement("name");
            pName.appendChild(this.document.createCDATASection(pattern.getName()));

            Element pProblem = this.document.createElement("problem");
            pProblem.appendChild(this.document.createCDATASection(pattern.getProblem()));

            Element pSolution = this.document.createElement("solution");
            pSolution.appendChild(this.document.createCDATASection(pattern.getSolution()));

            Element pConsequences = this.document.createElement("consequences");
            pConsequences.appendChild(this.document.createCDATASection(pattern.getConsequences()));

            Element pScope = this.document.createElement("scope");
            pScope.appendChild(this.document.createTextNode(pattern.getScope().getName()));

            Element pPurpose = this.document.createElement("purpose");
            pPurpose.appendChild(this.document.createTextNode(pattern.getPurpose().getName()));

            Element pDiagram = this.document.createElement("diagram");
            pDiagram.appendChild(this.document.createCDATASection(pattern.getDiagram().getEncodedImage()));

            p.appendChild(pName);
            p.appendChild(pProblem);
            p.appendChild(pSolution);
            p.appendChild(pConsequences);
            p.appendChild(pScope);
            p.appendChild(pPurpose);
            p.appendChild(pDiagram);

            root.appendChild(p);
        }
    }
}
