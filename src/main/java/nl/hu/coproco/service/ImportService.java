package nl.hu.coproco.service;

import nl.hu.coproco.service.loader.Import;
import nl.hu.coproco.service.loader.JsonImport;
import nl.hu.coproco.service.loader.ObjectImport;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class ImportService {
    public static boolean executeImport(File file) {
        // Check if exists and readable
        if (!file.exists() && !file.canRead()) {
            return false;
        }

        Import importer = null;

        // Determinate the importer to use
        String ext = FilenameUtils.getExtension(file.getAbsolutePath());

        if (ext.equals("json")) {
            importer = new JsonImport();
        }
        if (ext.equals("obj")) {
            importer = new ObjectImport();
        }

        if (importer == null) {
            return false;
        }

        // Try to import the file
        try {
            return importer.loadExport(file);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
