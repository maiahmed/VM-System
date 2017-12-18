package com.bbi.vmBackend.da;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.dao.Comment;
import com.bbi.vmBackend.da.dao.Employee;
import com.bbi.vmBackend.da.dao.OS;
import com.bbi.vmBackend.da.dao.Request;

public class RequestHome extends DBConnection implements RequestDaoHome {

	@Override
	public List<Request> listAll() {
		List<Request> listRequest = new ArrayList<Request>();

		String sql = "SELECT * FROM request;";
		try {
			Connection jdbcConnection = connect();
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int CPU = resultSet.getInt("CPU");
				int RAM = resultSet.getInt("RAM");
				int HD = resultSet.getInt("HD");
				Date creation_date = resultSet.getDate("creation_date");
				Date expiring_date = resultSet.getDate("expiring_date");
				Date handeled_date = resultSet.getDate("handeled_date");
				int request_user_id = resultSet.getInt("request_user_id");
				String internetFacing = resultSet.getString("internetFacing");
				Date submited_date = resultSet.getDate("submited_date");
				Date approved_date = resultSet.getDate("approved_date");
				int period = resultSet.getInt("period");
				int os_id = resultSet.getInt("os_id");

				Employee employee = new Employee();
				employee.setUserId(request_user_id);
				OS os = new OS();
				os.setOsId(os_id);
				Request request = new Request(id, CPU, RAM, HD, creation_date,
						expiring_date, handeled_date, employee, internetFacing,
						submited_date, approved_date, period, os);
				listRequest.add(request);
			}

			resultSet.close();
			statement.close();
			jdbcConnection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return listRequest;
	}

	@Override
	public Request getById(int id) {
		Request request = null;
		String sql = "SELECT * FROM host WHERE host_id = ?";

		try {
			Connection jdbcConnection = DBConnection.connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int CPU = resultSet.getInt("CPU");
				int RAM = resultSet.getInt("RAM");
				int HD = resultSet.getInt("HD");
				Date creation_date = resultSet.getDate("creation_date");
				Date expiring_date = resultSet.getDate("expiring_date");
				Date handeled_date = resultSet.getDate("handeled_date");
				int request_user_id = resultSet.getInt("request_user_id");
				String internetFacing = resultSet.getString("internetFacing");
				Date submited_date = resultSet.getDate("submited_date");
				Date approved_date = resultSet.getDate("approved_date");
				int period = resultSet.getInt("period");
				int os_id = resultSet.getInt("os_id");

				Employee employee = new Employee();
				employee.setUserId(request_user_id);
				OS os = new OS();
				os.setOsId(os_id);
				request = new Request(id, CPU, RAM, HD, creation_date,
						expiring_date, handeled_date, employee, internetFacing,
						submited_date, approved_date, period, os);
			}

			resultSet.close();
			statement.close();
			return request;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insert(Request request) {
		String sql = "INSERT INTO request (`CPU`, `RAM`, `HD`, `creation_date`, `expiring_date`,"
				+ " `internetFacing`,`request_user_id`, `submited_date`, `approved_date`, `handeled_date`, "
				+ "`period`, `os_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,?);";
		try {
			Connection jdbcConnection = DBConnection.connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, request.getCPU());
			statement.setInt(2, request.getRAM());
			statement.setDate(3, request.getCreation_date());
			statement.setDate(4, request.getExpiring_date());
			statement.setString(5, request.isInternetFacing());
			statement.setInt(6, request.getRequest_user_id().getUserId());
			statement.setDate(7, request.getSubmited_date());
			statement.setDate(8, request.getApproved_date());
			statement.setDate(9, request.getHandeled_date());
			statement.setInt(1, request.getPeriod());
			statement.setInt(1, request.getOs_id().getOsId());
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
	public boolean update(Request request) {
		String sql = "UPDATE `request` SET `HD`=?, `internetFacing`=?, `period`=? WHERE `request_id`=?";

		try {
			Connection jdbcConnection = DBConnection.connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, request.getHD());
			statement.setString(2, request.isInternetFacing());
			statement.setInt(3, request.getPeriod());
			statement.setInt(4, request.getId());

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
	public boolean delete(Request request) {
		String sql = "DELETE FROM `request` WHERE `request_id`=? ;";

		try {
			Connection jdbcConnection = DBConnection.connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, request.getId());

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

}
