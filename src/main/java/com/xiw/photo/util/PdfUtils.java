package com.xiw.photo.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;


public class PdfUtils {
    public static void mergePdfFiles(List<String> fileList, String result) {
        Document document = new Document();
        try {
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(result));
            document.open();
            for (String file : fileList) {
                PdfReader reader = new PdfReader(file);
                int n = reader.getNumberOfPages();
                for (int i = 1; i <= n; i++) {
                    PdfImportedPage page = copy.getImportedPage(reader, i);
                    copy.addPage(page);
                }
                reader.close();
            }
            copy.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static void main(String[] args) {
        String basePath = "/Users/xiwang/Downloads/book/";
        List<String> filePathList = new ArrayList<>();

        filePathList = Arrays.stream(Objects.requireNonNull(new File(basePath).listFiles())).map(File::getAbsolutePath)
                             .filter(e -> e.contains(".pdf") && e.contains("tmp"))
                             .sorted(Comparator.comparing(PdfUtils::getNumberInt)).collect(Collectors.toList());
        mergePdfFiles(filePathList, "/Users/xiwang/Downloads/book/Merged.pdf");
    }

    private static int getNumberInt(String path) {
        if (path == null) {
            return 999;
        }
        String[] split = path.split(File.separator);
        int length = split.length;
        String s = split[length - 1];
        String s1 = s.split("\\.pdf")[0].split("tmp_")[1];
        return Integer.parseInt(s1);
    }
}