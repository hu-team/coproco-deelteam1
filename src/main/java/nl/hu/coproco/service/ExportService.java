package nl.hu.coproco.service;

import nl.hu.coproco.service.export.Export;
import nl.hu.coproco.service.export.JsonExport;
import nl.hu.coproco.service.export.XmlExport;

import java.util.ArrayList;

public class ExportService {
    public final static String EXPORT_JSON = "JSON";
    public final static String EXPORT_XML = "XML";

    public static JsonExport exportJson() {
        return new JsonExport(PatternService.getAllPatterns());
    }

    public static ArrayList<String> getAvailableTypes() {
        ArrayList<String> returning = new ArrayList<>();

        returning.add(EXPORT_JSON);
        returning.add(EXPORT_XML);

        return returning;
    }

    public static Export getExportForType(String type) {
        if (type.equals(EXPORT_JSON)) {
            return new JsonExport(PatternService.getAllPatterns());
        }
        if (type.equals(EXPORT_XML)) {
            return new XmlExport();
        }

        return null;
    }

}
