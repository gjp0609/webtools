package top.gjp0609.webtools.utils;//package me.rainbow.utils;
//
//import com.artofsolving.jodconverter.BasicDocumentFormatRegistry;
//import com.artofsolving.jodconverter.DocumentConverter;
//import com.artofsolving.jodconverter.DocumentFamily;
//import com.artofsolving.jodconverter.DocumentFormat;
//import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
//import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang3.StringUtils;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.net.ConnectException;
//
///**
// * @author guojinpeng
// * @date 17.12.21 14:24
// */
//@SuppressWarnings({"unused"})
//public class OfficeUtil {
//    /**
//     * 使用本地openoffice转换目标office文件为pdf文件
//     *
//     * @param inputFilePath  待转换的文件路径
//     * @param outputFilePath 输出文件路径
//     * @date 2017年6月9日 下午4:12:29
//     */
//    public static void convert(String inputFilePath, String outputFilePath)
//            throws ConnectException, FileNotFoundException {
//        String ip = "localhost";
//        int port = 8100;
//        convert(inputFilePath, outputFilePath, ip, port);
//    }
//
//    /**
//     * 使用远程openoffice转换目标office文件为pdf文件
//     *
//     * @param inputFilePath  待转换的文件路径
//     * @param outputFilePath 输出文件路径
//     * @param connectIp      远程调用ip
//     * @param connectPort    远程调用端口
//     * @date 2017年6月9日 下午4:12:29
//     */
//    public static void convert(String inputFilePath, String outputFilePath, String connectIp, int connectPort)
//            throws ConnectException, FileNotFoundException {
//        if (StringUtils.isEmpty(inputFilePath) || StringUtils.isEmpty(outputFilePath) || StringUtils.isEmpty(connectIp))
//            throw new IllegalArgumentException("参数异常！！");
//        File inputFile = new File(inputFilePath);
//        File outputFile = new File(outputFilePath);
//        if (!inputFile.exists()) throw new FileNotFoundException("原文件不存在或路径错误。");
//        OpenOfficeConnection connection = new SocketOpenOfficeConnection(connectIp, connectPort);
//        connection.connect();
//        DocumentConverter converter = getConverter(connectIp, connection);
//        DocumentFormat format = guessDocumentFormat(inputFile, converter);
//        converter.convert(inputFile, format, outputFile, null);
//        connection.disconnect();
//    }
//
//    private static DocumentFormat guessDocumentFormat(File file, DocumentConverter converter) {
//        String extension = FilenameUtils.getExtension(file.getName());
//        DocumentFormat format = new DefaultDocumentFormatRegistry().getFormatByFileExtension(extension);
//        if (format == null) throw new IllegalArgumentException("unknown document format for file: " + file);
//        else return format;
//    }
//
//    private static DocumentConverter getConverter(String connectIp, OpenOfficeConnection connection) {
//        return "localhost".equals(connectIp) || "127.0.0.1".equals(connectIp) || "0:0:0:0:0:0:0:1".equals(connectIp)
//                ? new OpenOfficeDocumentConverter(connection)
//                : new StreamOpenOfficeDocumentConverter(connection);
//    }
//
//    /**
//     * 支持的格式
//     */
//    static class DefaultDocumentFormatRegistry extends BasicDocumentFormatRegistry {
//        DefaultDocumentFormatRegistry() {
//            DocumentFormat pdf = new DocumentFormat("Portable Document Format", "application/pdf", "pdf");
//            pdf.setExportFilter(DocumentFamily.DRAWING, "draw_pdf_Export");
//            pdf.setExportFilter(DocumentFamily.PRESENTATION, "impress_pdf_Export");
//            pdf.setExportFilter(DocumentFamily.SPREADSHEET, "calc_pdf_Export");
//            pdf.setExportFilter(DocumentFamily.TEXT, "writer_pdf_Export");
//            this.addDocumentFormat(pdf);
//            DocumentFormat swf = new DocumentFormat("Macromedia Flash", "application/x-shockwave-flash", "swf");
//            swf.setExportFilter(DocumentFamily.DRAWING, "draw_flash_Export");
//            swf.setExportFilter(DocumentFamily.PRESENTATION, "impress_flash_Export");
//            this.addDocumentFormat(swf);
//            DocumentFormat xhtml = new DocumentFormat("XHTML", "application/xhtml+xml", "xhtml");
//            xhtml.setExportFilter(DocumentFamily.PRESENTATION, "XHTML Impress File");
//            xhtml.setExportFilter(DocumentFamily.SPREADSHEET, "XHTML Calc File");
//            xhtml.setExportFilter(DocumentFamily.TEXT, "XHTML Writer File");
//            this.addDocumentFormat(xhtml);
//            DocumentFormat html = new DocumentFormat("HTML", DocumentFamily.TEXT, "text/html", "html");
//            html.setExportFilter(DocumentFamily.PRESENTATION, "impress_html_Export");
//            html.setExportFilter(DocumentFamily.SPREADSHEET, "HTML (StarCalc)");
//            html.setExportFilter(DocumentFamily.TEXT, "HTML (StarWriter)");
//            this.addDocumentFormat(html);
//            DocumentFormat odt = new DocumentFormat("OpenDocument Text", DocumentFamily.TEXT, "application/vnd.oasis.opendocument.text", "odt");
//            odt.setExportFilter(DocumentFamily.TEXT, "writer8");
//            this.addDocumentFormat(odt);
//            DocumentFormat sxw = new DocumentFormat("OpenOffice.org 1.0 Text Document", DocumentFamily.TEXT, "application/vnd.sun.xml.writer", "sxw");
//            sxw.setExportFilter(DocumentFamily.TEXT, "StarOffice XML (Writer)");
//            this.addDocumentFormat(sxw);
//            DocumentFormat doc = new DocumentFormat("Microsoft Word", DocumentFamily.TEXT, "application/msword", "doc");
//            doc.setExportFilter(DocumentFamily.TEXT, "MS Word 97");
//            this.addDocumentFormat(doc);
//            DocumentFormat docx = new DocumentFormat("Microsoft Word 2007 XML", DocumentFamily.TEXT, "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
//            this.addDocumentFormat(docx);
//            DocumentFormat rtf = new DocumentFormat("Rich Text Format", DocumentFamily.TEXT, "text/rtf", "rtf");
//            rtf.setExportFilter(DocumentFamily.TEXT, "Rich Text Format");
//            this.addDocumentFormat(rtf);
//            DocumentFormat wpd = new DocumentFormat("WordPerfect", DocumentFamily.TEXT, "application/wordperfect", "wpd");
//            this.addDocumentFormat(wpd);
//            DocumentFormat txt = new DocumentFormat("Plain Text", DocumentFamily.TEXT, "text/plain", "txt");
//            txt.setImportOption("FilterName", "Text (encoded)");
//            txt.setImportOption("FilterOptions", "UTF8,CRLF");
//            txt.setExportFilter(DocumentFamily.TEXT, "Text (encoded)");
//            txt.setExportOption(DocumentFamily.TEXT, "FilterOptions", "UTF8,CRLF");
//            this.addDocumentFormat(txt);
//            DocumentFormat wikitext = new DocumentFormat("MediaWiki wikitext", "text/x-wiki", "wiki");
//            wikitext.setExportFilter(DocumentFamily.TEXT, "MediaWiki");
//            this.addDocumentFormat(wikitext);
//            DocumentFormat ods = new DocumentFormat("OpenDocument Spreadsheet", DocumentFamily.SPREADSHEET, "application/vnd.oasis.opendocument.spreadsheet", "ods");
//            ods.setExportFilter(DocumentFamily.SPREADSHEET, "calc8");
//            this.addDocumentFormat(ods);
//            DocumentFormat sxc = new DocumentFormat("OpenOffice.org 1.0 Spreadsheet", DocumentFamily.SPREADSHEET, "application/vnd.sun.xml.calc", "sxc");
//            sxc.setExportFilter(DocumentFamily.SPREADSHEET, "StarOffice XML (Calc)");
//            this.addDocumentFormat(sxc);
//            DocumentFormat xls = new DocumentFormat("Microsoft Excel", DocumentFamily.SPREADSHEET, "application/vnd.ms-excel", "xls");
//            xls.setExportFilter(DocumentFamily.SPREADSHEET, "MS Excel 97");
//            this.addDocumentFormat(xls);
//            DocumentFormat xlsx = new DocumentFormat("Microsoft Excel 2007 XML", DocumentFamily.SPREADSHEET, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
//            this.addDocumentFormat(xlsx);
//            DocumentFormat csv = new DocumentFormat("CSV", DocumentFamily.SPREADSHEET, "text/csv", "csv");
//            csv.setImportOption("FilterName", "Text - txt - csv (StarCalc)");
//            csv.setImportOption("FilterOptions", "44,34,0");
//            csv.setExportFilter(DocumentFamily.SPREADSHEET, "Text - txt - csv (StarCalc)");
//            csv.setExportOption(DocumentFamily.SPREADSHEET, "FilterOptions", "44,34,0");
//            this.addDocumentFormat(csv);
//            DocumentFormat tsv = new DocumentFormat("Tab-separated Values", DocumentFamily.SPREADSHEET, "text/tab-separated-values", "tsv");
//            tsv.setImportOption("FilterName", "Text - txt - csv (StarCalc)");
//            tsv.setImportOption("FilterOptions", "9,34,0");
//            tsv.setExportFilter(DocumentFamily.SPREADSHEET, "Text - txt - csv (StarCalc)");
//            tsv.setExportOption(DocumentFamily.SPREADSHEET, "FilterOptions", "9,34,0");
//            this.addDocumentFormat(tsv);
//            DocumentFormat odp = new DocumentFormat("OpenDocument Presentation", DocumentFamily.PRESENTATION, "application/vnd.oasis.opendocument.presentation", "odp");
//            odp.setExportFilter(DocumentFamily.PRESENTATION, "impress8");
//            this.addDocumentFormat(odp);
//            DocumentFormat sxi = new DocumentFormat("OpenOffice.org 1.0 Presentation", DocumentFamily.PRESENTATION, "application/vnd.sun.xml.impress", "sxi");
//            sxi.setExportFilter(DocumentFamily.PRESENTATION, "StarOffice XML (Impress)");
//            this.addDocumentFormat(sxi);
//            DocumentFormat ppt = new DocumentFormat("Microsoft PowerPoint", DocumentFamily.PRESENTATION, "application/vnd.ms-powerpoint", "ppt");
//            ppt.setExportFilter(DocumentFamily.PRESENTATION, "MS PowerPoint 97");
//            this.addDocumentFormat(ppt);
//            DocumentFormat pptx = new DocumentFormat("Microsoft PowerPoint 2007 XML", DocumentFamily.PRESENTATION, "application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx");
//            this.addDocumentFormat(pptx);
//            DocumentFormat odg = new DocumentFormat("OpenDocument Drawing", DocumentFamily.DRAWING, "application/vnd.oasis.opendocument.graphics", "odg");
//            odg.setExportFilter(DocumentFamily.DRAWING, "draw8");
//            this.addDocumentFormat(odg);
//            DocumentFormat svg = new DocumentFormat("Scalable Vector Graphics", "image/svg+xml", "svg");
//            svg.setExportFilter(DocumentFamily.DRAWING, "draw_svg_Export");
//            this.addDocumentFormat(svg);
//        }
//    }
//}
