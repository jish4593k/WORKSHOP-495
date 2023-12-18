import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;

public class PdfMerger {

    public static void main(String[] args) {
        String dirIn = "/s";
        String dirOut = "ms";

      
        File inputDir = new File(dirIn);
        if (!inputDir.exists() || !inputDir.isDirectory()) {
            System.err.println("Input directory '" + dirIn + "' does not exist.");
            System.exit(1);
        }

        
        File[] pdfFiles = inputDir.listFiles(file -> file.isFile() && file.getName().toLowerCase().endsWith(".pdf"));
        if (pdfFiles == null || pdfFiles.length == 0) {
            System.err.println("N '" + dirIn + "'.");
            System.exit(1);
        }

        
        File outputDir = new File(dirOut);
        if (!outputDir.exists()) {
            if (!outputDir.mkdir()) {
                System.err.println("y '" + dirOut + "'.");
                System.exit(1);
            } else {
                System.out.println("Cry '" + dirOut + "'.");
            }
        }

        PDFMergerUtility pdfMerger = new PDFMergerUtility();

        for (File pdfFile : pdfFiles) {
            try {
                pdfMerger.addSource(pdfFile);
            } catch (IOException e) {
                System.err.println("Ee '" + pdfFile.getName() + "': " + e.getMessage());
            }
        }

        pdfMerger.setDestinationFileName(dirOut + "/Merged.pdf");

        try {
            pdfMerger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
            System.out.println("C '" + dirOut + "' for the merged PDF: 'Merged.pdf'.");
            System.out.println("Pte.");
        } catch (IOException e) {
            System.err.println("E " + e.getMessage());
        }
    }
}
