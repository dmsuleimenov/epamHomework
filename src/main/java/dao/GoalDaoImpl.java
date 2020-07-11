package dao;

import model.Goal;
import utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoalDaoImpl implements GoalDao{

    private static final String INSERT_GOAL_SQL = "INSERT INTO goals" +
            "  (title, user_id) VALUES " + " (?, ?);";
    private static final String SELECT_GOAL_BY_ID =
            "select id,title,user_id from goals where id =? and user_id =?";
    private static final String SELECT_ALL_GOALS = "select * from goals where user_id = ?";
    private static final String DELETE_GOAL_BY_ID = "delete from goals where id = ?;";
    private static final String UPDATE_GOAL =
            "update goals set title = ?, user_id= ? where id = ?;";

    public GoalDaoImpl() {

    }

    @Override
    public void insertGoal(Goal goal) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GOAL_SQL)) {
            preparedStatement.setString(1, goal.getTitle());
            preparedStatement.setInt(2, goal.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }

    @Override
    public Goal selectGoal(long goalid, int user_id) {
        Goal goal = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GOAL_BY_ID)) {
            preparedStatement.setLong(1, goalid);
            preparedStatement.setInt(2, user_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                user_id = rs.getInt("user_id");
                goal = new Goal(id, title, user_id);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return goal;
    }

    @Override
    public List <Goal> selectAllGoals(int user_id) {
        List <Goal> goals = new ArrayList <> ();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GOALS)) {
            preparedStatement.setInt(1,user_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                user_id = rs.getInt("user_id");
                goals.add(new Goal(id, title, user_id));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return goals;
    }

    @Override
    public boolean deleteGoal(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_GOAL_BY_ID)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateGoal(Goal goal) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_GOAL)) {
            statement.setString(1, goal.getTitle());
            statement.setInt(2, goal.getUser_id());
            statement.setLong(3, goal.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
