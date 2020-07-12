import dao.GoalDao;
import dao.GoalDaoImpl;
import dao.TaskDao;
import dao.TaskDaoImpl;
import model.Goal;
import model.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/")
public class TaskController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDao taskDAO;
    private GoalDao goalDAO;

    public void init() {
        taskDAO = new TaskDaoImpl();
        goalDAO = new GoalDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String action = request.getServletPath();

        try {
            switch (action) {
                case "/new_task":
                    showTaskNewForm(request, response);
                    break;
                case "/insert_task":
                    insertTask(request, response);
                    break;
                case "/delete_task":
                    deleteTask(request, response);
                    break;
                case "/edit_task":
                    showTaskEditForm(request, response);
                    break;
                case "/update_task":
                    updateTask(request, response);
                    break;
                case "/list":
                    list(request, response);
                    break;
                case "/new_goal":
                    showGoalNewForm(request, response);
                    break;
                case "/insert_goal":
                    insertGoal(request, response);
                    break;
                case "/delete_goal":
                    deleteGoal(request, response);
                    break;
                case "/edit_goal":
                    showGoalEditForm(request, response);
                    break;
                case "/update_goal":
                    updateGoal(request, response);
                    break;
                case "/bind":
                    addGoalToTask(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session=request.getSession(false);
        int user_id =(Integer) session.getAttribute("user_id");
        List <Task> listTask = taskDAO.selectAllTasks(user_id);
        request.setAttribute("listTask", listTask);
        List<Goal> listGoal = goalDAO.selectAllGoals(user_id);
        request.setAttribute("listGoal", listGoal);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task/main-page.jsp");
        dispatcher.forward(request, response);
    }

    private void showTaskNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        int user_id =(Integer) session.getAttribute("user_id");
        List<Goal> listGoal = goalDAO.selectAllGoals(user_id);
        request.setAttribute("listGoal", listGoal);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task/task-form.jsp");
        dispatcher.forward(request, response);

    }

    private void showTaskEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session=request.getSession(false);
        int user_id =(Integer) session.getAttribute("user_id");
        Task existingTask = taskDAO.selectTask(id,user_id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task/task-form.jsp");
        request.setAttribute("task", existingTask);
        dispatcher.forward(request, response);

    }

    private void insertTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String title = request.getParameter("title");
        HttpSession session=request.getSession(false);
        int user_id =(Integer) session.getAttribute("user_id");
        String description = request.getParameter("description");
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        String goal_Title = "";
        try {
            goal_Title = (String) session.getAttribute("goal_title");
        } catch (NullPointerException e) {

        }
        Task newTask = new Task(title, user_id, description, LocalDate.now(), isDone, goal_Title);
        taskDAO.insertTask(newTask);
        response.sendRedirect("list");
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        HttpSession session=request.getSession(false);
        String goal_Title = "";
        try {
            goal_Title = (String) session.getAttribute("goal_title");
        } catch (NullPointerException e) {

        }
        int user_id =(Integer) session.getAttribute("user_id");
        String description = request.getParameter("description");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Task updateTask = new Task(id, title, user_id, description, targetDate, isDone, goal_Title);
        taskDAO.updateTask(updateTask);
        response.sendRedirect("list");
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        taskDAO.deleteTask(id);
        response.sendRedirect("list");
    }

    private void showGoalNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("goal/goal-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showGoalEditForm(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session=request.getSession(false);
        int user_id =(Integer) session.getAttribute("user_id");
        Goal existingGoal = goalDAO.selectGoal(id,user_id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("goal/goal-form.jsp");
        request.setAttribute("goal", existingGoal);
        dispatcher.forward(request, response);

    }

    private void insertGoal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String title = request.getParameter("title");
        HttpSession session=request.getSession(false);
        int user_id =(Integer) session.getAttribute("user_id");
        Goal newGoal = new Goal(title, user_id);
        goalDAO.insertGoal(newGoal);
        response.sendRedirect("list");
    }

    private void updateGoal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        HttpSession session=request.getSession(false);
        int user_id =(Integer) session.getAttribute("user_id");
        Goal updateGoal = new Goal(id, title, user_id);
        goalDAO.updateGoal(updateGoal);

        response.sendRedirect("list");
    }

    private void deleteGoal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        goalDAO.deleteGoal(id);
        response.sendRedirect("list");
    }

    private void addGoalToTask(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {
        String goal_title = request.getParameter("goal_title");
        HttpSession session=request.getSession(false);
        session.setAttribute("goal_title", goal_title);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task/task-form.jsp");
        dispatcher.forward(request, response);
    }


}