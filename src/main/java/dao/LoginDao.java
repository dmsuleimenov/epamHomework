package dao;

import model.LoginBean;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

	public int validate(LoginBean loginBean) throws ClassNotFoundException {
		int id = 0;
		Class.forName("com.mysql.jdbc.Driver");
		try {Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement("select * from users where username = ? and password = ? ");
				preparedStatement.setString(1, loginBean.getUsername());
				preparedStatement.setString(2, loginBean.getPassword());

				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					id = rs.getInt("id");
				}

			} catch (SQLException e) {
			JDBCUtils.printSQLException(e);

		}
		return id;
	}

}
