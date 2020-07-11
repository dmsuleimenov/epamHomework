package dao;

import model.Task;

import java.sql.SQLException;
import java.util.List;


public interface TaskDao {

    void insertTask(Task task) throws SQLException;

    Task selectTask(long taskid, int user_id);

    List<Task> selectAllTasks(int user_id);

    boolean deleteTask(int id) throws SQLException;

    boolean updateTask(Task task) throws SQLException;

}