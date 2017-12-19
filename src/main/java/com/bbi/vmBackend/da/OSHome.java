package com.bbi.vmBackend.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbi.vmBackend.da.dao.DaoObject;
import com.bbi.vmBackend.da.dao.OS;

public class OSHome extends DBConnection implements DaoHome {

	String sql;

	@Override
	public List<DaoObject> listAll() {
		sql = "SELECT * FROM os ";
		Connection conn = GetConnection();
		ResultSet rs;
		OS os = new OS();
		List<DaoObject> OSList = new ArrayList<>();
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
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
		sql = "SELECT * FROM os " + "WHERE id=?";
		Connection conn = GetConnection();
		ResultSet rs;
		OS os = (OS) obj;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
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
		sql = "INSERT INTO os (name, extra_os, manager) VALUES (?,?,?)";
		OS os = (OS) obj;
		boolean entered = false;
		try {

			Connection conn = GetConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, os.getOsName());
			preparedStmt.setString(2, os.getOsExtra_OS());
			preparedStmt.setInt(3, os.getOsManager());

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
		sql = "UPDATE os set name=? , extra_os=? , manager=?  WHERE id=?";
		Connection conn = GetConnection();
		OS os = (OS) obj;
		boolean entered = false;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, os.getOsName());
			preparedStmt.setString(2, os.getOsExtra_OS());
			preparedStmt.setInt(3, os.getOsManager());
			preparedStmt.setInt(4, os.getOsId());
			preparedStmt.executeUpdate();
			conn.close();
			entered = true;
		} catch (SQLException sq) {
			System.out.println("Error in updating the OS !");
			entered = false;
		}
		return entered;
	}

	@Override
	public boolean delete(DaoObject obj) {
		sql = "DELETE FROM os " + "WHERE id = ?";
		Connection conn = GetConnection();
		OS os = (OS) obj;
		boolean entered = false;
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, os.getOsId());
			preparedStmt.executeUpdate();
			conn.close();
			entered = true;
		} catch (SQLException sq) {
			System.out.println("Error in deleting the selected id !");
			entered = false;
		}
		return entered;
	}

}