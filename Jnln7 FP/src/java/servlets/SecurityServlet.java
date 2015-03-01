/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import ejb.CourseBean;
import ejb.StudentBean;
import ejb.TeacherBean;
import ejb.TeacherCourseBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;
import model.StudentCourse;
import model.Teacher;
import model.TeacherCourse;

/**
 *
 * @author Justin
 */
@WebServlet(name = "SecurityServlet", urlPatterns = {"/app/SecurityServlet"})
public class SecurityServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SecurityServlet.class.getName());
    
    @EJB
    private TeacherCourseBean teacherCourseBean;
    @EJB
    private CourseBean courseBean;

    @EJB
    private TeacherBean teacherBean;
    @EJB
    private StudentBean studentBean;
    
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
            out.println("<title>BLACKBOARD V2</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\" "+ request.getContextPath()+"/css/mycss.css\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\" "+ request.getContextPath()+"/css/bootstrap.min.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"headerClass\"><h1>BLACKBOARD V2</h1>");
            out.println("<a href=\"" + request.getContextPath()+"/logout\" class=\"linkHome btn btn-primary\">Logout</a>");
            out.println("</div>");
            //out.println("<h1>Servlet SecurityServlet at " + request.getContextPath() + "</h1>");
            LOG.info("Inside Security Servlet!");
            if (request.isUserInRole("teacher")) {
                Teacher t = teacherBean.findByUser(request.getRemoteUser());
                LOG.info("teacher list :" + t );
                out.println("<h2> Hello Teacher : " + t.getName() + "</h2>");
                
                
                if (!t.getTeacherCourses().isEmpty()) {
                    out.println("<h3> The courses where you teach are : </h3>");
                    out.println("<table class=\"bordered\">");
                    out.println("<tr><th>Course Name</th><th>Course Description</th></tr>");
                    
                    for (TeacherCourse teacherCourse : t.getTeacherCourses()) {
                        out.println("<tr>");
                        out.println("<td>" + teacherCourse.getCourse().getCourseName() + "</td>");
                        out.println("<td>" + teacherCourse.getCourse().getCourseDescription() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    //out.println(t.getTeacherCourses().iterator().next().getCourse().getCourseName());
                }
            }
            if (request.isUserInRole("student")) {
                Student s = studentBean.findByUser(request.getRemoteUser());
                out.println("<h2> Hello Student : " + s.getFirstName() + "</h2>");
                
                if (!s.getStudentCourses().isEmpty()) {
                    out.println("<p> Your courses are : </p><table class=\"bordered\">");
                    out.println("<tr><th>Course Name</th><th>Course Description</th></tr>");
                    for (StudentCourse c : s.getStudentCourses()) {
                         out.println("<tr>");
                        out.println("<td>" + c.getCourse().getCourseName() + "</td>");
                        out.println("<td>" + c.getCourse().getCourseDescription() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                }
            }
           out.println("<a href=\"../javadoc/index.html\">View Javadocs</a>");
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
        processRequest(request, response);
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
