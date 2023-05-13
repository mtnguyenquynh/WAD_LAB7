/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author WIN 10
 */
public class BookServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ParserConfigurationException, SAXException {
    response.setContentType("text/html;charset=UTF-8");
        // TODO output your page here
        try (PrintWriter out = response.getWriter()) {
            // TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Book List</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><center>Book List</center></h1>");
            out.println("<center><table border=1 cellpadding=0 bgcolor=#FFFFFF></center>");
            out.println("<tr><td><b>ISBN</b></td> <td><b>Title</b></td> <td><b>Authors</b></td> <td><b>Publisher</b></td> <td><b>Publication Date</b></td> <td><b>Price</b></td></tr>");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Turn on namespace support
            factory.setNamespaceAware(true);
            // Create a JAXP document builder
            DocumentBuilder parser = factory.newDocumentBuilder();
            // Read the entire document into memory
Document document = (Document) parser.parse("D:/New Folder/WAD_LAB7/web/book.xml");            // Obtain the root node of the tree
            Node booklist = document.getDocumentElement();
            NodeList books = booklist.getChildNodes();
            int nBooks = books.getLength();
            for (int i = 0; i < nBooks; i++) {
                
                Node book = books.item(i);
                if (book.getNodeType() != Node.TEXT_NODE) {
                    out.println("<tr>");
                    printBook(book, out);
                    out.println("</tr>");
                }
                
            }
            out.println("</body>");
            out.println("</html>");
        }
}

private void printBook(Node book, PrintWriter out) {
    NodeList childNodes = book.getChildNodes();
    String isbn = "";
    String title = "";
    String authors = "";
    String publisher = "";
    String publicationDate = "";
    String price = "";
    for (int i = 0; i < childNodes.getLength(); i++) {
        Node child = childNodes.item(i);
        String nodeName = child.getNodeName();
        if (nodeName != null) {
            if (nodeName.equals("isbn")) {
                isbn = child.getTextContent();
            } else if (nodeName.equals("title")) {
                title = child.getTextContent();
            } else if (nodeName.equals("authors")) {
                authors = child.getTextContent();
            } else if (nodeName.equals("publisher")) {
                publisher = child.getTextContent();
            } else if (nodeName.equals("publication_date")) {
                publicationDate = child.getTextContent();
            } else if (nodeName.equals("price")) {
                price = child.getTextContent();
            }
        }
    }
    out.print("<td>" + isbn + "</td>" + "<td>" + title + "</td>" + "<td>" + authors + "</td>" + "<td>" + publisher + "</td>" + "<td>" + publicationDate + "</td>" + "<td>" + price + "</td>");
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
