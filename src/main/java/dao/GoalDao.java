package dao;

import model.Goal;

import java.sql.SQLException;
import java.util.List;

public interface GoalDao {

    void insertGoal(Goal goal) throws SQLException;

    Goal selectGoal(long goalid, int user_id);

    List<Goal> selectAllGoals(int user_id);

    boolean deleteGoal(int id) throws SQLException;

    boolean updateGoal(Goal goal) throws SQLException;

}
