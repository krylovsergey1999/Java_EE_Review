import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Многопоточность
 */

@WebServlet("/temp")
public class TempServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName);
        resp.getWriter().write(threadName);
    }

    @Override
    public void init() throws ServletException {
        get();
    }

    public static void get() {
        System.out.println("Работает main");
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @Override
                public void run() {
                    URLConnection urlConnection = null;
                    try {
                        urlConnection = new URL("http://localhost:8080/servlet/temp").openConnection();
                        urlConnection.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
