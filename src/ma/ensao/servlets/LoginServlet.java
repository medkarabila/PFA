
package ma.ensao.servlets;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ma.ensao.entity.User;
import ma.ensao.hibernateDAO.LoginService;


public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        String userId = request.getParameter("userId");        
        String password = request.getParameter("password");
        User usr = new User();
        LoginService loginService = new LoginService();
        usr=loginService.getUserByLoginPassword(userId, password);
        boolean result = loginService.authenticate(userId, password);
        User user = loginService.getUserByUserId(userId);
        String status=usr.getStatus();
        if(result == true && status.equals("admin")){
            request.getSession().setAttribute("user", user);            
            response.sendRedirect("/e_learning_pfa_2/Admin");
        }
        else if(result == true && status.equals("etudiant")){
            request.getSession().setAttribute("user", user);            
            response.sendRedirect("/e_learning_pfa_2/Front");
        }
        else if(result == true && status.equals("prof")){
            request.getSession().setAttribute("user", user);            
            response.sendRedirect("prof.jsp");
        }
        else{
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
