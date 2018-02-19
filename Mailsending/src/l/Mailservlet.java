package l;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/mailserv")
public class Mailservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "c:/ups";
    public Mailservlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		go(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		go(request,response);
	}
    protected void go(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String to=request.getParameter("to");
		String Subject=request.getParameter("Subject");
		String message=request.getParameter("message");
		String cc=request.getParameter("cc");
		String bcc=request.getParameter("bcc");
		Messanger.sendMail(to,Subject,message,cc,bcc);
		PrintWriter webpage = response.getWriter();

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator
								+ name));
					}
				}
				webpage.write("File Uploaded Successfully");
			} catch (Exception e) {
				webpage.write("File Upload Failed due to " + e);
			}
		} else {
			webpage.write("Sorry this Servlet only handles file upload request");
		}
	}

	}

