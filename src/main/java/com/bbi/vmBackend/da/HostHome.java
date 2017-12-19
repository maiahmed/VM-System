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
import com.bbi.vmBackend.da.dao.Request;

public class HostHome extends DBConnection  implements DaoHome {
	private final static String insertQuery = "INSERT INTO `host` (`name`, `ip`) VALUES (?, ?);";
	private final static String selectQuery = "SELECT * FROM host;";
	private final static String updateQuery = "UPDATE `host` SET `name`=? And `ip`= ? WHERE `host_id`=?";
	private final static String deleteQuery = "DELETE FROM `host` WHERE `host_id`=? ;";
	private final static String getOneQuery = "SELECT * FROM host WHERE host_id = ?";
	@Override
	public boolean insert(DaoObject obj) {
		Host host = (Host) obj;
	
		try {
			Connection jdbcConnection = getInstance().getConnection();
			PreparedStatement statement = jdbcConnection.prepareStatement(insertQuery);
			statement.setString(1, host.getName());
			statement.setString(2, host.getIp());

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
		List<DaoObject> listHosts = new ArrayList<DaoObject>();

		
		try {
			Connection jdbcConnection = getInstance().getConnection();
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectQuery);

			while (resultSet.next()) {
				int host_id = resultSet.getInt("host_id");
				String name = resultSet.getString("name");
				String ip = resultSet.getString("ip");

				Host host = new Host(host_id, name, ip);
				listHosts.add(host);
			}

			resultSet.close();
			statement.close();
			jdbcConnection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return listHosts;

	}

	@Override
	public boolean update(DaoObject obj) {
		Host host = (Host) obj;
		try {
			Connection jdbcConnection = getInstance().getConnection();
			PreparedStatement statement = jdbcConnection.prepareStatement(updateQuery);
			statement.setString(1, host.getName());
			statement.setString(2, host.getIp());

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
		Host host = (Host) obj;
		try {
			Connection jdbcConnection = getInstance().getConnection();
			PreparedStatement statement = jdbcConnection.prepareStatement(deleteQuery);
			statement.setInt(1, host.getHost_id());
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
		Host host = (Host) obj;
//		Host host = null;
		try {
			Connection jdbcConnection = getInstance().getConnection();
			PreparedStatement statement = jdbcConnection.prepareStatement(getOneQuery);
			statement.setInt(1, host.getHost_id());

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int host_id = resultSet.getInt("host_id");
				String name = resultSet.getString("name");
				String ip = resultSet.getString("ip");

				host = new Host(host_id, name, ip);
			}

			resultSet.close();
			statement.close();
			return host;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		

		
	}
}
