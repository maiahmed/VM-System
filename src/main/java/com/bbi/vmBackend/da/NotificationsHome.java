package com.bbi.vmBackend.da;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.Notifications;

public class NotificationsHome extends DBConnection implements DaoHome {
	String sql;

	@Override
	public List<DaoObject> listAll() {
		sql = "SELECT * FROM notification ";
		Connection conn =  getConnection();
		Notifications notification = new Notifications();
		List<DaoObject> NotificationsList = new ArrayList<>();
		ResultSet rs;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			rs = preparedStmt.executeQuery();

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

	@Override
	public DaoObject getById(DaoObject obj) {
		sql = "SELECT * FROM notification " + "WHERE notif_user_id=?";
		Connection conn = getInstance().getConnection();
		Notifications notification = (Notifications) obj;
		ResultSet rs = null;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, notification.getId());
			rs = preparedStmt.executeQuery();

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

			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selecting the required notification !");
		}
		return notification;
	}

	@Override
	public boolean insert(DaoObject obj) {
		boolean entered = false;
		Notifications notification = (Notifications) obj;
		sql = "INSERT INTO notification content ,from , date ,notif_request_id , notif_user_id, type "
				+ " , hidden , seen , reminder , expired , extra_notif" + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try {

			Connection conn = getInstance().getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, notification.getContent());
			preparedStmt.setString(2, notification.getFrom());
			preparedStmt.setDate(3, (Date) notification.getDate());
			preparedStmt.setInt(4, notification.getNoti_RequestId());
			preparedStmt.setInt(5, notification.getNoti_UserId());
			preparedStmt.setString(6, notification.getType());
			preparedStmt.setString(7, notification.getHidden());
			preparedStmt.setString(8, notification.getSeen());
			preparedStmt.setString(9, notification.getReminder());
			preparedStmt.setString(10, notification.getExpired());
			preparedStmt.setString(11, notification.getExtra_Notif());

			preparedStmt.executeUpdate();
			conn.close();
			entered = true;
		} catch (SQLException sq) {
			System.out.println("Error in inserting function ");
			entered = false;
		}
		return entered;
	}

	@Override
	public boolean update(DaoObject obj) {
		sql = "UPDATE notification set content =? , from=? , date=? , notif_request_id=? , notif_user_id=? , "
				+ "type=? , hidden=? , seen=? , reminder=? , expired=? , extra_notif =? " + " WHERE notification_id=?";
		Connection conn = getInstance().getConnection();
		boolean entered = false;
		Notifications notification = (Notifications) obj;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, notification.getContent());
			preparedStmt.setString(2, notification.getFrom());
			preparedStmt.setDate(3, (Date) notification.getDate());
			preparedStmt.setInt(4, notification.getNoti_RequestId());
			preparedStmt.setInt(5, notification.getNoti_UserId());
			preparedStmt.setString(6, notification.getType());
			preparedStmt.setString(7, notification.getHidden());
			preparedStmt.setString(8, notification.getSeen());
			preparedStmt.setString(9, notification.getReminder());
			preparedStmt.setString(10, notification.getExpired());
			preparedStmt.setString(11, notification.getExtra_Notif());
			preparedStmt.setInt(12, notification.getId());

			preparedStmt.executeUpdate();
			conn.close();
			entered = true;

		} catch (SQLException sq) {
			System.out.println("Error in updating the notification !");
			entered = false;
		}
		return entered;
	}

	@Override
	public boolean delete(DaoObject obj) {
		Notifications notification = (Notifications) obj;
		boolean entered = false;
		sql = "DELETE FROM notification " + "WHERE notification_id = ?";
		Connection conn = getInstance().getConnection();
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, notification.getId());
			preparedStmt.executeUpdate();
			conn.close();
			entered = true;

		} catch (SQLException sq) {
			System.out.println("Error in deleting the notification !");
			entered = false;
		}
		return entered;
	}

	public List<DaoObject> EmployeeNotification(DaoObject obj) {
		sql = "SELECT * FROM notification " + "WHERE notif_user_id=? ";
		Connection conn = getInstance().getConnection();

		Notifications EmployeeNotification = (Notifications)obj;
		List<DaoObject> EmplNotificationsList = new ArrayList<>();
		ResultSet rs = null;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, EmployeeNotification.getNoti_UserId());
			rs = preparedStmt.executeQuery();

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

			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selection");
		}
		return EmplNotificationsList;
	}

}