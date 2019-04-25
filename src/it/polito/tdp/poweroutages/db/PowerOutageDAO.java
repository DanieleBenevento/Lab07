package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutage> getPowerOutageList() {

		String sql = "SELECT * FROM poweroutages";
		List<PowerOutage> PowerOutagesList = new ArrayList<PowerOutage>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage p = new PowerOutage(res.getInt("id"), res.getDate("date_event_began").toLocalDate().atTime(res.getTime("date_event_began").toLocalTime()),
				res.getDate("date_event_finished").toLocalDate().atTime(res.getTime("date_event_finished").toLocalTime()),res.getInt("customers_affected"),
				this.getNercList().get(res.getInt("nerc_id")),this.getEventTypeList().get(res.getInt("event_type_id")),
				this.getResponsibleList().get(res.getInt("responsible_id")),this.getAreaList().get(res.getInt("area_id")),
				this.getTagList().get(res.getInt("tag_id")));
				PowerOutagesList.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return PowerOutagesList;
	}
	
	public List<String> getAreaList() {

		String sql = "SELECT * FROM area";
		List<String> areaList = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				areaList.add(res.getString("value"));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return areaList;
	}

	public List<String> getResponsibleList() {

		String sql = "SELECT * FROM responsible";
		List<String> responsibleList = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				responsibleList.add(res.getString("value"));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return responsibleList;
	}
	
	public List<String> getEventTypeList() {

		String sql = "SELECT * FROM eventtype";
		List<String> eventTypeList = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				eventTypeList.add(res.getString("value"));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return eventTypeList;
	}

	public List<String> getTagList() {

		String sql = "SELECT * FROM tag";
		List<String> tagList = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				tagList.add(res.getString("value"));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return tagList;
	}
	
	public List<PowerOutage> getPowerOutageListByNerc(Nerc n) {

		String sql = "SELECT * FROM poweroutages WHERE nerc_id = (SELECT id FROM nerc WHERE value = ? )";
		List<PowerOutage> PowerOutagesListByNerc = new ArrayList<PowerOutage>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, n.getValue());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage p = new PowerOutage(res.getInt("id"), res.getDate("date_event_began").toLocalDate().atTime(res.getTime("date_event_began").toLocalTime()),
				res.getDate("date_event_finished").toLocalDate().atTime(res.getTime("date_event_finished").toLocalTime()),res.getInt("customers_affected"),
				this.getNercList().get(res.getInt("nerc_id")),this.getEventTypeList().get(res.getInt("event_type_id")),
				this.getResponsibleList().get(res.getInt("responsible_id")),this.getAreaList().get(res.getInt("area_id")),
				this.getTagList().get(res.getInt("tag_id")));
				PowerOutagesListByNerc.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return PowerOutagesListByNerc;
	}
}
