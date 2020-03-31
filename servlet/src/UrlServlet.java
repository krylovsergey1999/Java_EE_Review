import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/url")
public class UrlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // http://localhost:8080/servlet/url?one=hello
        String one = req.getParameter("one");
        System.out.println(one);

        // http://localhost:8080/servlet/url?one=hello&two=2&two=%D0%B4%D0%B2%D0%B0
        String[] parameterValues = req.getParameterValues("two");
        for(String v: parameterValues) {
            System.out.println(v);
        }
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Test</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"url\" method=\"post\">\n" +
                "    <input type=\"text\" name=\"one\">\n" +
                "    <input type=\"text\" name=\"two\">\n" +
                "    <input type=\"submit\" class=\"submit\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";

        resp.getWriter().write(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post");
        doGet(req, resp);
    }
}
