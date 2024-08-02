package com.xiw.test;

import com.itextpdf.text.DocumentException;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.commons.lang.CharEncoding;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Html2PdfTest {

    public static void convertHtmlToPdfUsingFlying0(String inputFile, String outputFile) throws IOException, DocumentException {
        File htmlFile = new File(inputFile);

        org.jsoup.nodes.Document document = Jsoup.parse(htmlFile, "UTF-8");
        document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            ITextRenderer renderer = new ITextRenderer();
            org.xhtmlrenderer.layout.SharedContext sharedContext = renderer.getSharedContext();

            String folderPath = "src/main/resources/font";
            List<String> fontList = getAllTtfFiles(folderPath);

            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontList.forEach(font -> {
                try {

                    fontResolver.addFont(font, CharEncoding.UTF_8, true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            renderer.setDocumentFromString(document.html());
            renderer.layout();
            renderer.createPDF(outputStream);
        }
    }

    public static List<String> getAllTtfFiles(String folderPath) {
        List<String> ttfFiles = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            findTtfFiles(folder, ttfFiles);
        }

        return ttfFiles;
    }

    private static void findTtfFiles(File folder, List<String> ttfFiles) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findTtfFiles(file, ttfFiles);
                } else if (file.isFile() && (file.getName().toLowerCase().endsWith(".ttf") || file.getName()
                        .toLowerCase()
                        .endsWith(".ttc"))) {
                    ttfFiles.add(file.getAbsolutePath());
                }
            }
        }
    }


    /**
     * 中文字体不显示+偏移
     */
    @Test
    public void convertHtmlToPdfUsingFlying() {
        String inputFile = "input.html";
        String outputFile = "output.pdf";

        try {
            convertHtmlToPdfUsingFlying0(inputFile, outputFile);
            System.out.println("PDF generated successfully.");
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 偏移
     * 自动加载指定文件夹下的所有字体文件
     */
    @Test
    public void testOpenPDF() {
        String inputFile = "input.html";
        String outputFile = "output.pdf";
        File htmlFile = new File(inputFile);
        try (OutputStream os = new FileOutputStream(outputFile)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.toStream(os);
            builder.withW3cDocument(new W3CDom().fromJsoup(Jsoup.parse(htmlFile, "UTF-8")), "/");
            addFonts(builder);
            builder.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addFonts(PdfRendererBuilder builder) throws IOException {
        Path fontDirectory = Paths.get("src/main/resources/font");

        // PERF: Should only be called once, as each font must be parsed for font family name.
        List<AutoFont.CSSFont> fonts = AutoFont.findFontsInDirectory(fontDirectory, Arrays.asList("ttf", "ttc", "TTF", "TTC"), true, true);
        // Use this in your template for the font-family property.
        AutoFont.toCSSEscapedFontFamily(fonts);
        // Add fonts to builder.
        AutoFont.toBuilder(builder, fonts);
    }


}
