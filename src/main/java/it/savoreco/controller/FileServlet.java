package it.savoreco.controller;

import com.google.common.html.HtmlEscapers;
import it.savoreco.model.entity.SellerAccount;
import it.savoreco.service.FileUpload;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Base64;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@WebServlet(
        name = "fileServlet",
        displayName = "Upload",
        description = "Upload",
        value = "/fileUpload"
)
public class FileServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(FileServlet.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        FileUpload fileUpload = new FileUpload();

        try {
            if (req.getSession(false) != null && req.getSession().getAttribute("seller") instanceof SellerAccount) {

                String mode = req.getParameter("mode");
                if (mode.equals("restaurant")) {
                    var filePart = req.getPart("image");
                    Base64.getEncoder().encodeToString(filePart.getSubmittedFileName().getBytes());
                    String imageUrl = fileUpload.saveImage(HtmlEscapers.htmlEscaper().escape(Base64.getEncoder().encodeToString(filePart.getSubmittedFileName().getBytes()) + ".png"), filePart.getInputStream());

                    resp.setContentType("text/plain");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write(imageUrl);

                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

        } catch (IOException | ServletException e) {
            logger.warn("Error while uploading file", e);
        }
    }

}
