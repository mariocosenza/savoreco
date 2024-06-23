package it.savoreco.controller;

import it.savoreco.model.entity.SellerAccount;
import it.savoreco.service.FileUpload;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
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
        value = "/seller/fileUpload"
)
public class FileServlet extends HomeServlet {
    private static final Logger logger = LoggerFactory.getLogger(HomeServlet.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        FileUpload fileUpload = new FileUpload();
        try {
            var filePart = req.getPart("file");
            if (req.getSession(false) != null && req.getSession().getAttribute("seller") instanceof SellerAccount) {
               Base64.getEncoder().encodeToString(filePart.getSubmittedFileName().getBytes());
               fileUpload.saveImage(Base64.getEncoder().encodeToString(filePart.getSubmittedFileName().getBytes()) + ".png", filePart.getInputStream());

            }

        } catch (IOException | ServletException e) {
            logger.warn("Error while uploading file", e);
        }

    }
}
