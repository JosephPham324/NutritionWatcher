package Control.Exercise;

import DAO.ExerciseDAO;
import DAO.ExerciseTypeDAO;
import Entity.ExerciseType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Semester: FALL 2022
 * Subject : FRJ301
 * Class   : SE1606
 * Project : Nutrition 
 * @author : Group 4
 * CE161130  Nguyen Le Quang Thinh (Leader)
 * CE170036  Pham Nhat Quang
 * CE160464  Nguyen The Lu
 * CE161096  Nguyen Ngoc My Quyen
 * CE161025  Tran Thi Ngoc Hieu
 */
public class AddExerciseControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddExerciseControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddExerciseControl at " + request.getContextPath() + "</h1>");
            out.println(new Date() + "<br>");
            out.println(request.getParameter("exerciseName") + "<br>");
            out.println(request.getParameter("description") + "<br>");
            out.println(request.getParameter("kcalph") + "<br>");
            out.println(request.getParameter("kcal") + "<br>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
//        processRequest(request, response);
        String name = request.getParameter("exerciseName");
//        String description = request.getParameter("description");
        double kcalph = Double.parseDouble(request.getParameter("kcalph"));
        double kcal = Double.parseDouble(request.getParameter("kcal"));

        ExerciseTypeDAO etDAO = new ExerciseTypeDAO();
        ExerciseDAO eDAO = new ExerciseDAO();
        ExerciseType et = null;
        Date now = new Date();
        int userID = -1;
        if (request.getSession().getAttribute("userID") != null) {
            userID = Integer.parseInt(request.getSession().getAttribute("userID").toString());
        }
        try {
            et = etDAO.getExerciseByName(name);
            eDAO.insertExercise(now, userID, et, (double) kcal / kcalph * 60, kcal);
//            response.getWriter().write((double)kcal/kcalph+"");
            response.sendRedirect("search-exercise");
        } catch (SQLException ex) {
            try (PrintWriter out = response.getWriter()) {
                out.println(ex);
            }
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
