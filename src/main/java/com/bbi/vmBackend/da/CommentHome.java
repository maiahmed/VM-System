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
import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.Host;
import com.bbi.vmBackend.da.dao.OS;
import com.bbi.vmBackend.da.dao.Request;

public class CommentHome extends DBConnection implements DaoHome {
	private final static String INSERTQUERY = "INSERT INTO `comment` (`content`, `date`, `comment_user_id`, `comment_request_id`) "
			+ "VALUES (?,?,?,?);";
	private final static String SELECTQUERY = "SELECT * FROM request;";
	private final static String UPDATEQUERY = "UPDATE `comment` SET `content`=? WHERE `comment_id`=?";
	private final static String DELETEQUERY = "DELETE FROM `comment` WHERE `comment_id`=? ;";
	private final static String GETONEQUERY = "SELECT * FROM comment WHERE comment_user_id = ?";

	@Override
	public boolean insert(DaoObject obj) {
		Comment comment = (Comment) obj;

		try {
			Connection jdbcConnection = getConnection();
			PreparedStatement statement = jdbcConnection
					.prepareStatement(INSERTQUERY);
			statement.setString(1, comment.getContent());
			statement.setDate(2, (Date) comment.getDate());
			statement.setInt(3, comment.getComment_user_id());
			statement.setInt(4, comment.getComment_request_id());
			System.out.println(statement);
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
	public List<DaoObject> listAll() {

		List<DaoObject> listComments = new ArrayList<DaoObject>();
		try {

			Connection jdbcConnection = getConnection();
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECTQUERY);

			while (resultSet.next()) {
				int comment_id = resultSet.getInt("comment_id");
				String content = resultSet.getString("content");
				Date date = resultSet.getDate("date");
				int comment_user_id = resultSet.getInt("comment_user_id");
				int comment_request_id = resultSet.getInt("comment_request_id");
				Comment comment = new Comment(comment_id, content, date,
						comment_user_id, comment_request_id);
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
	public boolean update(DaoObject obj) {
		Comment comment = (Comment) obj;

		try {
			Connection jdbcConnection = getConnection();
			PreparedStatement statement = jdbcConnection
					.prepareStatement(UPDATEQUERY);
			statement.setString(1, comment.getContent());
			statement.setInt(2, comment.getComment_id());
			System.out.println(statement);
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
	public boolean delete(DaoObject obj) {
		Comment comment = (Comment) obj;
		System.out.println("=-ana f comment delete " + comment.getContent()
				+ " " + comment.getComment_user_id());
		try {
			Connection jdbcConnection = getConnection();
			PreparedStatement statement = jdbcConnection
					.prepareStatement(DELETEQUERY);
			statement.setInt(1, comment.getComment_id());
			System.out.println(statement);
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
	public DaoObject getById(DaoObject obj) {
		Comment comment = (Comment) obj;
		System.out.println("rrrrrrr " + comment.getComment_user_id());

		try {
			Connection jdbcConnection = getConnection();
			PreparedStatement statement = jdbcConnection
					.prepareStatement(GETONEQUERY);
			statement.setInt(1, comment.getComment_user_id());
			System.out.println(statement);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				System.out.println("ana gwa el res next");
				int comment_id = resultSet.getInt("comment_id");
				String content = resultSet.getString("content");
				Date date = resultSet.getDate("date");
				int comment_user_id = resultSet.getInt("comment_user_id");
				int comment_request_id = resultSet.getInt("comment_request_id");

				comment = new Comment(comment_id, content, date,
						comment_user_id, comment_request_id);
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
