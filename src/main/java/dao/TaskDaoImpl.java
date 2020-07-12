package dao;
import model.Task;
import utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class TaskDaoImpl implements TaskDao {

    private static final String INSERT_TASKS_SQL = "INSERT INTO tasks" +
            "  (title, user_id, description, target_date,  is_done, goal_title) VALUES " + " (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_TASK_BY_ID =
            "select id,title,user_id,description,target_date,is_done, goal_title from tasks where id =? and user_id =?";
    private static final String SELECT_ALL_TASKS = "select * from tasks where user_id = ?";
    private static final String DELETE_TASK_BY_ID = "delete from tasks where id = ?;";
    private static final String UPDATE_TASK =
        "update tasks set title = ?, user_id= ?, description =?, target_date =?, is_done = ?, goal_title = ? where id = ?;";

    public TaskDaoImpl() {}

    @Override
    public void insertTask(Task task) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASKS_SQL)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setInt(2, task.getUser_id());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(task.getTargetDate()));
            preparedStatement.setBoolean(5, task.getStatus());
            preparedStatement.setString(6,task.getGoal_title());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }

    @Override
    public Task selectTask(long taskid, int user_id) {
        Task task = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID);) {
            preparedStatement.setLong(1, taskid);
            preparedStatement.setInt(2, user_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                user_id = rs.getInt("user_id");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                String goal_title = rs.getString("goal_title");
                task = new Task(id, title, user_id, description, targetDate, isDone, goal_title);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return task;
    }

    @Override
    public List <Task> selectAllTasks(int user_id) {

        List <Task> tasks = new ArrayList < > ();

        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS);) {
            preparedStatement.setInt(1,user_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                user_id = rs.getInt("user_id");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                String goal_title = rs.getString("goal_title");
                tasks.add(new Task(id, title, user_id, description, targetDate, isDone, goal_title));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return tasks;
    }

    @Override
    public boolean deleteTask(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_TASK_BY_ID);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateTask(Task task) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_TASK);) {
            statement.setString(1, task.getTitle());
            statement.setInt(2, task.getUser_id());
            statement.setString(3, task.getDescription());
            statement.setDate(4, JDBCUtils.getSQLDate(task.getTargetDate()));
            statement.setBoolean(5, task.getStatus());
            statement.setLong(6, task.getId());
            statement.setString(7, task.getGoal_title());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}