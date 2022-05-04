package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;
import it.polito.tdp.poweroutages.model.Rilevamento;

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

	public List<PowerOutage> getPowerOutagesNercGap(int yearMin, int yearMax, Nerc nerc) {
		// TODO Auto-generated method stub
		String sql = "SELECT * "
				+ "FROM poweroutages "
				+ "WHERE YEAR(date_event_began) >= ? "
				+ "AND YEAR(date_event_finished) <= ? "
				+ "AND nerc_id = ? ";
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, yearMin);
			st.setInt(2, yearMax);
			st.setInt(3, nerc.getId());
			ResultSet rs = st.executeQuery();
			List<PowerOutage> output = new ArrayList<PowerOutage>();
			while(rs.next()) {
				PowerOutage po = new PowerOutage(rs.getInt("id"), rs.getInt("event_type_id"), rs.getInt("tag_id"), rs.getInt("area_id"), nerc, 
						rs.getInt("responsible_id"), rs.getInt("customers_affected"), rs.getTimestamp("date_event_began").toLocalDateTime(), 
						rs.getTimestamp("date_event_finished").toLocalDateTime(), rs.getInt("demand_loss"));
				output.add(po);
				
			}
			st.close();
			rs.close();
			conn.close();
			return output;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		
	}
	
	public List<Rilevamento> getRilevamentiNerc(Nerc nerc) {
		String sql ="SELECT customers_affected, date_event_began, date_event_finished, HOUR(TIMEDIFF(date_event_began, date_event_finished)) AS diff "
				+ "FROM poweroutages "
				+ "WHERE nerc_id=?";
		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				Rilevamento r = new Rilevamento(rs.getInt("customers_affected"), rs.getDate("date_event_began"), 
						rs.getDate("date_event_finished"), rs.getInt("diff"));
				rilevamenti.add(r);
			}
			conn.close();
			return rilevamenti;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	
	}
}
