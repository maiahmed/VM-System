package com.bbi.vmBackend.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.OS;

public class OSHome extends SingletonDBConnection implements DaoHome {

	private final static String insertQuery =  "INSERT INTO os (name, extra_os, manager) VALUES (?,?,?)";
	private final static String selectQuery = "SELECT * FROM os ";
	private final static String updateQuery =  "UPDATE os set name=? , extra_os=? , manager=?  WHERE id=?";
	private final static String deleteQuery = "DELETE FROM os " + "WHERE id = ?";
	private final static String getOneQuery = "SELECT * FROM os " + "WHERE id=?";

	@Override
	public List<DaoObject> listAll() {
		Connection conn = getConnection();
		ResultSet rs;
		OS os = new OS();
		List<DaoObject> OSList = new ArrayList<>();
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(selectQuery);
			rs = preparedStmt.executeQuery();

			while (rs.next()) {
				os.setOsId(rs.getInt("id"));
				os.setOsName(rs.getString("name"));
				os.setOsExtra_OS(rs.getString("extra_os"));
				os.setOsManager(rs.getInt("manager"));
				OSList.add(os);

			}
			rs.close();
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in selection");
		}
		return OSList;
	}

	@Override
	public DaoObject getById(DaoObject obj) {
		Connection conn = getConnection();
		ResultSet rs;
		OS os = (OS) obj;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(getOneQuery);
			preparedStmt.setInt(1, os.getOsId());
			rs = preparedStmt.executeQuery();

			while (rs.next()) {

				os.setOsId(rs.getInt("id"));
				os.setOsName(rs.getString("name"));
				os.setOsExtra_OS(rs.getString("extra_os"));
				os.setOsManager(rs.getInt("manager"));

				rs.close();
				conn.close();

			}
		} catch (SQLException sq) {
			System.out.println("Error in selecting the required os");
		}
		return os;
	}

	@Override
	public boolean insert(DaoObject obj) {
		OS os = (OS) obj;
		boolean entered = false;
		try {

			Connection conn = getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(insertQuery);
			preparedStmt.setString(1, os.getOsName());
			preparedStmt.setString(2, os.getOsExtra_OS());
			preparedStmt.setInt(3, os.getOsManager());

			entered = preparedStmt.executeUpdate() > 0;
			conn.close();

		} catch (SQLException sq) {
			System.out.println("Error in inserting function ");
		}
		return entered;
	}

	@Override
	public boolean update(DaoObject obj) {
		Connection conn = getConnection();
		OS os = (OS) obj;
		boolean entered = false;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(updateQuery);
			preparedStmt.setString(1, os.getOsName());
			preparedStmt.setString(2, os.getOsExtra_OS());
			preparedStmt.setInt(3, os.getOsManager());
			preparedStmt.setInt(4, os.getOsId());
			entered = preparedStmt.executeUpdate() > 0;
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in updating the OS !");
		}
		return entered;
	}

	@Override
	public boolean delete(DaoObject obj) {
	
		Connection conn = getConnection();
		OS os = (OS) obj;
		boolean entered = false;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(deleteQuery);
			preparedStmt.setInt(1, os.getOsId());
			entered = preparedStmt.executeUpdate() > 0;
			conn.close();
		} catch (SQLException sq) {
			System.out.println("Error in deleting the selected id !");
		}
		return entered;
	}

}