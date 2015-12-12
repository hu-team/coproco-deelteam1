package nl.hu.coproco.service;

import nl.hu.coproco.service.export.Export;
import nl.hu.coproco.service.export.JsonExport;
import nl.hu.coproco.service.export.ObjectExport;

import java.util.ArrayList;

public class ExportService {
    public final static String EXPORT_JSON = "JSON";
    public final static String EXPORT_OBJECT = "Object File";

    public static JsonExport exportJson() {
        return new JsonExport(PatternService.getAllPatterns());
    }

    public static ArrayList<String> getAvailableTypes() {
        ArrayList<String> returning = new ArrayList<>();

        returning.add(EXPORT_JSON);
        returning.add(EXPORT_OBJECT);

        return returning;
    }

    public static Export getExportForType(String type) {
        if (type.equals(EXPORT_JSON)) {
            return new JsonExport(PatternService.getAllPatterns());
        }
        if (type.equals(EXPORT_OBJECT)) {
            return new ObjectExport();
        }

        return null;
    }

}
