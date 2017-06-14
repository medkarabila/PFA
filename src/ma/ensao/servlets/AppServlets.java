package ma.ensao.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.ensao.entity.User;

public class AppServlets extends HttpServlet {
	User user = new User();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			user = (User) req.getSession(true).getAttribute("user");

			if (user == null) {
				req.getRequestDispatcher("app.jsp").forward(req, resp);

			} else {
				if (user.getStatus().equals("admin")) {
					req.getRequestDispatcher("Admin").forward(req, resp);

				}
				if (user.getStatus().equals("etudiant")) {
					req.getRequestDispatcher("Front").forward(req, resp);

				}
				if (user.getStatus().equals("prof")) {
					req.getRequestDispatcher("prof.jsp").forward(req, resp);

				}
			}
		} catch (Exception e) {
			System.out.println("Error");

		}

	}

}
