package com.bbi.rest.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbi.rest.DataAccess.Doa.Notifications;
import com.bbi.rest.DataAccess.Doa.OS;

public class NotificationsHome {

	String sql;
	Statement stmt = null;
	Connection conn = null;
	PreparedStatement PreparedStmt;

	public static Connection GetConnection() {
		Connection conn = null;
		String URL = "jdbc:mysql://localhost/vm";
		String USER = "root";
		String PASS = "admin";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error in connection");
		}
		System.out.println("Successfully connected");
		return conn;
	}

	public void InsertNewNotification(Notifications notification) {
		sql = "INSERT INTO notification content ,from , date ,notif_request_id , notif_user_id, type "
				+ " , hidden , seen , reminder , expired , extra_notif" + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {

			conn = GetConnection();
			PreparedStmt = conn.prepareStatement(sql);
			PreparedStmt.setString(1, notification.getContent());
			PreparedStmt.setString(2, notification.getFrom());
			PreparedStmt.setDate(3, notification.getDate());
			PreparedStmt.setInt(4, notification.getNoti_RequestId());
			PreparedStmt.setInt(5, notification.getNoti_UserId());
			PreparedStmt.setString(6, notification.getType());
			PreparedStmt.setString(7, notification.getHidden());
			PreparedStmt.setString(8, notification.getSeen());
			PreparedStmt.setString(9, notification.getReminder());
			PreparedStmt.setString(10, notification.getExpired());
			PreparedStmt.setString(11, notification.getExtra_Notif());

			PreparedStmt.executeUpdate();
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in inserting function ");
		}
	}

	public void Deleting(int NotificationId) {
		sql = "DELETE FROM notification " + "WHERE notification_id = ?";
		conn = GetConnection();
		Notifications notification = new Notifications();
		try {
			PreparedStmt = conn.prepareStatement(sql);
			if (NotificationId == notification.getId()) {
				PreparedStmt.setInt(1, NotificationId);
				PreparedStmt.executeUpdate();
				conn.close();
			} else {
				System.out.println("The selected id doesn't exist or deleted before !");
			}
		} catch (SQLException sq) {
			System.out.println("Error in deleting the notification !");
		}
	}

	public void Updating(Notifications notification) {
		sql = "UPDATE notification set content =? , from=? , date=? , notif_request_id=? , notif_user_id=? , "
				+ "type=? , hidden=? , seen=? , reminder=? , expired=? , extra_notif =? " + " WHERE notification_id=?";
		conn = GetConnection();
		try {
			PreparedStmt = conn.prepareStatement(sql);
			PreparedStmt.setString(1, notification.getContent());
			PreparedStmt.setString(2, notification.getFrom());
			PreparedStmt.setDate(3, notification.getDate());
			PreparedStmt.setInt(4, notification.getNoti_RequestId());
			PreparedStmt.setInt(5, notification.getNoti_UserId());
			PreparedStmt.setString(6, notification.getType());
			PreparedStmt.setString(7, notification.getHidden());
			PreparedStmt.setString(8, notification.getSeen());
			PreparedStmt.setString(9, notification.getReminder());
			PreparedStmt.setString(10, notification.getExpired());
			PreparedStmt.setString(11, notification.getExtra_Notif());
			PreparedStmt.setInt(12, notification.getId());

			PreparedStmt.executeUpdate();
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in updating the notification !");
		}
	}

	public List<Notifications> EmployeeNotification(int user_id) {
		sql = "SELECT * FROM notification " + "WHERE notif_user_id=? ";
		conn = GetConnection();
		Notifications EmployeeNotification = new Notifications();
		List<Notifications> EmplNotificationsList = new ArrayList<>();
		ResultSet rs = null;
		try {
			PreparedStmt = conn.prepareStatement(sql);
			if (user_id == EmployeeNotification.getNoti_UserId()) {
				PreparedStmt.setInt(1, user_id);
				rs = PreparedStmt.executeQuery();

				while (rs.next()) {
					EmployeeNotification.setId(rs.getInt("notification_id"));
					EmployeeNotification.setContent(rs.getString("content"));
					EmployeeNotification.setFrom(rs.getString("from"));
					EmployeeNotification.setDate(rs.getDate("date"));
					EmployeeNotification.setNoti_RequestId(rs.getInt("notif_request_id"));
					EmployeeNotification.setNoti_UserId(rs.getInt("notif_user_id"));
					EmployeeNotification.setType(rs.getString("type"));
					EmployeeNotification.setHidden(rs.getString("hidden"));
					EmployeeNotification.setSeen(rs.getString("seen"));
					EmployeeNotification.setReminder(rs.getString("reminder"));
					EmployeeNotification.setExpired(rs.getString("expired"));
					EmployeeNotification.setExtra_Notif(rs.getString("extra_notif"));

					EmplNotificationsList.add(EmployeeNotification);
				}
			} else {
				System.out.println("user_id doesn't exist !");
			}

			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selection");
		}
		return EmplNotificationsList;
	}

	public Notifications SelectOne(int NotificationID) {
		sql = "SELECT * FROM notification " + "WHERE notif_user_id=?";
		conn = GetConnection();
		Notifications notification = new Notifications();
		ResultSet rs = null;
		try {
			PreparedStmt = conn.prepareStatement(sql);
			if (NotificationID == notification.getNoti_UserId()) {
				PreparedStmt.setInt(1, NotificationID);
				rs = PreparedStmt.executeQuery();

				while (rs.next()) {
					notification.setId(rs.getInt("notification_id"));
					notification.setContent(rs.getString("content"));
					notification.setFrom(rs.getString("from"));
					notification.setDate(rs.getDate("date"));
					notification.setNoti_RequestId(rs.getInt("notif_request_id"));
					notification.setNoti_UserId(rs.getInt("notif_user_id"));
					notification.setType(rs.getString("type"));
					notification.setHidden(rs.getString("hidden"));
					notification.setSeen(rs.getString("seen"));
					notification.setReminder(rs.getString("reminder"));
					notification.setExpired(rs.getString("expired"));
					notification.setExtra_Notif(rs.getString("extra_notif"));

				}
			} else {
				System.out.println("Id doesn't exist !");
			}

			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selecting the required notification !");
		}
		return notification;
	}

	public List<Notifications> SelectAll() {
		sql = "SELECT * FROM notification ";
		conn = GetConnection();
		Notifications notification = new Notifications();
		List<Notifications> NotificationsList = new ArrayList<>();
		ResultSet rs;
		try {
			PreparedStmt = conn.prepareStatement(sql);
			rs = PreparedStmt.executeQuery();

			while (rs.next()) {
				notification.setId(rs.getInt("notification_id"));
				notification.setContent(rs.getString("content"));
				notification.setFrom(rs.getString("from"));
				notification.setDate(rs.getDate("date"));
				notification.setNoti_RequestId(rs.getInt("notif_request_id"));
				notification.setNoti_UserId(rs.getInt("notif_user_id"));
				notification.setType(rs.getString("type"));
				notification.setHidden(rs.getString("hidden"));
				notification.setSeen(rs.getString("seen"));
				notification.setReminder(rs.getString("reminder"));
				notification.setExpired(rs.getString("expired"));
				notification.setExtra_Notif(rs.getString("extra_notif"));

				NotificationsList.add(notification);

			}
			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selection");
		}
		return NotificationsList;
	}

}
