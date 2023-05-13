/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author WIN 10
 */
import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.*;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;

public class BooksLibrary extends DefaultHandler {
    protected static final String XML_FILE_NAME = "D:/New Folder/WAD_LAB7/web/WebClass.xml";

    public static void main(String argv[]) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            out = new OutputStreamWriter(System.out, "UTF8");
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(XML_FILE_NAME), new BooksLibrary());
        } catch (IOException | ParserConfigurationException | SAXException t) {
            t.printStackTrace();
        }
        System.exit(0);
    }

    static private Writer out;

    @Override
    public void startDocument() throws SAXException {
        showData("<?xml version='1.0' encoding='UTF-8'?>");
        newLine();
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            newLine();
            out.flush();
        } catch (IOException e) {
            throw new SAXException("I/O error", e);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        showData("<" + qName);
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                showData(" ");
                showData(attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"");
            }
        }
        showData(">");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        showData("</" + qName + ">");
    }

    @Override
    public void characters(char buf[], int offset, int len) throws SAXException {
        String s = new String(buf, offset, len);
        showData(s);
    }

    private void showData(String s) throws SAXException {
        try {
            out.write(s);
            out.flush();
        } catch (IOException e) {
            throw new SAXException("I/O error", e);
        }
    }

    // Start a new line
    private void newLine() throws SAXException {
        String lineEnd = System.getProperty("line.separator");
        try {
            out.write(lineEnd);
        } catch (IOException e) {
            throw new SAXException("I/O error", e);
        }
    }
}