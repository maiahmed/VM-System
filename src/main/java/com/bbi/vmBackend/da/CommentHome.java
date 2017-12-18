package com.bbi.vmBackend.da;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.dao.Comment;
import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Host;
import com.bbi.vmBackend.da.dao.OS;
import com.bbi.vmBackend.da.dao.Request;

public abstract class CommentHome extends DBConnection  implements CommentDaoHome {
	@Override
	public boolean insert(Comment comment) {

		String sql = "INSERT INTO `comment` (`content`, `date`, `comment_user_id`, `comment_request_id`) "
				+ "VALUES (?,?,?,?);";
		try {
			Connection jdbcConnection = DBConnection.connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, comment.getContent());
			statement.setDate(2, comment.getDate());
			statement.setInt(3, comment.getComment_user_id().getUserId());
			statement.setInt(4, comment.getComment_request_id().getId());

			boolean rowInserted = statement.executeUpdate() > 0;
			statement.close();
			jdbcConnection.close();
			return rowInserted;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Comment> listAll() {
		List<Comment> listComments = new ArrayList<Comment>();
		// String sql =
		// "SELECT comment_id,content,date   `CPU`, `RAM`, `HD`, `creation_date`,"
		// +
		// " `expiring_date`,`internetFacing`,`request_user_id`, `submited_date`,"
		// +
		// " `approved_date`,`handeled_date`, `period`, `os_id`from request, comment "
		// + "where comment_id = 1 and request_id = comment_request_id;";
		String sql = "SELECT * FROM request;";
		try {
			
			Connection jdbcConnection = DBConnection.connect();
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int comment_id = resultSet.getInt("comment_id");
				String content = resultSet.getString("content");
				Date date = resultSet.getDate("date");
				int comment_user_id = resultSet.getInt("comment_user_id");
				int comment_request_id = resultSet.getInt("comment_request_id");
				

				Employee employee = new Employee();
				employee.setUserId(comment_user_id);
				Request request = new Request();
				request.setId(comment_request_id);
				Comment comment = new Comment(comment_id, content, date, employee, request);
				listComments.add(comment);
			}

			resultSet.close();
			statement.close();
			jdbcConnection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return listComments;

	}

	@Override
	public boolean update(Comment comment) {
		String sql = "UPDATE `comment` SET `content`=? WHERE `comment_id`=?";

		try {
			Connection jdbcConnection = DBConnection.connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, comment.getContent());
			statement.setInt(2, comment.getComment_id());

			boolean rowUpdated = statement.executeUpdate() > 0;
			statement.close();
			jdbcConnection.close();
			return rowUpdated;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(Comment comment) {
		String sql = "DELETE FROM `comment` WHERE `comment_id`=? ;";

		try {
			Connection jdbcConnection = DBConnection.connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, comment.getComment_id());

			boolean rowUpdated = statement.executeUpdate() > 0;
			statement.close();
			jdbcConnection.close();
			return rowUpdated;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Comment getById(int id) {
		Comment comment = null;
		String sql = "SELECT * FROM host WHERE host_id = ?";

		try {
			Connection jdbcConnection = DBConnection.connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int comment_id = resultSet.getInt("comment_id");
				String content = resultSet.getString("content");
				Date date = resultSet.getDate("date");
				int comment_user_id = resultSet.getInt("comment_user_id");
				int comment_request_id = resultSet.getInt("comment_request_id");
				

				Employee employee = new Employee();
				employee.setUserId(comment_user_id);
				Request request = new Request();
				request.setId(comment_request_id);
				comment = new Comment(comment_id, content, date, employee, request);
			}

			resultSet.close();
			statement.close();
			return comment;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		

	}
}
