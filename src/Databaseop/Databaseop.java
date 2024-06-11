package Databaseop;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Classes.Hhelper;
import Classes.Item;
import Classes.Category;
import net.proteanit.sql.DbUtils;

public class Databaseop {
	Connection con;
	Statement stmt;
	PreparedStatement pst;
	ResultSet rs;
	DatabaseHelper dh = new DatabaseHelper();
	Hhelper hh = new Hhelper();

	public int data_delete(JTable dtable, String sql) {
		int flag = 0;
		DefaultTableModel d1 = (DefaultTableModel) dtable.getModel();
		int sIndex = dtable.getSelectedRow();
		if (sIndex < 0) {
			return flag;
		}
		String iid = d1.getValueAt(sIndex, 0).toString();
		if (iid.equals("")) {
			return flag;
		}

		int a = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?");
		if (a == JOptionPane.YES_OPTION) {
			String vsql = sql + iid;
			flag = dh.Insupdel(vsql);
			if (flag == 1) {
				d1.removeRow(sIndex);
			}
		}
		return flag;
	}

	public int tdata_delete(JTable dtable, String sql, int row) {
		int flag = 0;
		DefaultTableModel d1 = (DefaultTableModel) dtable.getModel();	
		flag = dh.Insupdel(sql);
		if (flag == 1) {
			d1.removeRow(row);
		}
		return flag;
	}

	public int table_maxid(String sql) {
		int myid = 0;
		try {
			con = dh.getConnection();
			rs = dh.GetData(sql);
			if (!rs.next()) {
				System.out.println("Error.");
			} else {
				myid = rs.getInt("max_id");
			}
			dh.CloseConnection();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}
		return myid;
	}

	public void countrycombofill(JComboBox ccombo) {
		String Sql = " select countryname from country order by  countryname";
		try {
			rs = dh.GetData(Sql);
			while (rs.next()) {
				ccombo.addItem(rs.getString("countryname"));
			}
			dh.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void categoriescombofill(JComboBox ccombo) {
		ccombo.removeAllItems();
		Category A = new Category(0, " ");
		ccombo.addItem(A);
		String sql = "select cid, name  from codes where type='P'  order by name";
		try {
			ResultSet rs = dh.GetData(sql);
			while (rs.next()) {
				A = new Category(rs.getInt("cid"), rs.getString("name"));
				ccombo.addItem(A);
			}
			dh.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void itemcombofill(JComboBox ccombo, String type) {
		ccombo.removeAllItems();
		Item A = new Item(0, " ");
		ccombo.addItem(A);
		String sql = "select cid, name  from codes where type='" + type + "'  order by name";
		try {
			ResultSet rs = dh.GetData(sql);
			while (rs.next()) {
				A = new Item(rs.getInt("cid"), rs.getString("name"));
				ccombo.addItem(A);
			}
			dh.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Boolean cannotdelete(String sql) {
		Boolean found = false;
		rs = dh.GetData(sql);
		try {
			if (rs.next()) {
				found = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dh.CloseConnection();
		return found;
	}

	public void table_update(JTable dtable, String what) {
		String Sql;
		if (what == "") {
			Sql = "select pid, name, postalcodem, addressm, citym, countrym  from partners order by name";
		} else {
			Sql = "select pid, name, postalcodem, addressm, citym, countrym  from partners  where " + what
					+ " order by name";
		}
		ResultSet res = dh.GetData(Sql);
		dtable.setModel(DbUtils.resultSetToTableModel(res));
		dh.CloseConnection();
		String[] fej = { "PID", "Name", "Postal code", "Mailing Address", "City", "Country" };
		((DefaultTableModel) dtable.getModel()).setColumnIdentifiers(fej);
		hh.setJTableColumnsWidth(dtable, 1150, 0, 30, 10, 30, 20, 20);
		hh.table_show(dtable);
		if (dtable.getRowCount() > 0) {
			int row = dtable.getRowCount() - 1;
			dtable.setRowSelectionInterval(row, row);
		}
	}

	public void stable_update(JTable dtable, String what) {
		String Sql;
		if (what == "") {
			Sql = "select pid, name, postalcodem, addressm, citym, phone, countrym " + "  from partners order by name";
		} else {
			Sql = "select pid, name, postalcodem, addressm, citym, phone, countrym " + " from partners  where " + what
					+ " order by name";
		}
		ResultSet res = dh.GetData(Sql);
		dtable.setModel(DbUtils.resultSetToTableModel(res));
		dh.CloseConnection();
		String[] fej = { "PID", "Name", "Pcode", "Mailing Address", "City", "Phone", "Country" };
		((DefaultTableModel) dtable.getModel()).setColumnIdentifiers(fej);
		hh.setJTableColumnsWidth(dtable, 1150, 0, 30, 10, 30, 20, 20, 0);

		hh.table_show(dtable);
		if (dtable.getRowCount() > 0) {
			int row = dtable.getRowCount() - 1;
			dtable.setRowSelectionInterval(row, row);
		}
	}

	public void regtable_update(JTable dtable, String what) {
		String Sql;
		if (what == "") {
			Sql = "select r.rid, r.pid, r.direction, r.regnumber, r.receive_date,  p.name, p.addressm, p.citym, c.name "
					+ "from reg_book r join partners p on r.pid = p.pid  join codes c  on r.doc_category = c.cid";

		} else {
			Sql = "select r.rid, r.pid, r.direction, r.regnumber, r.receive_date,  p.name, p.addressm, p.citym, c.name "
					+ "from reg_book r join partners p on r.pid = p.pid join codes c on r.doc_category = c.cid where "
					+ what;
		}
		ResultSet res = dh.GetData(Sql);
		dtable.setModel(DbUtils.resultSetToTableModel(res));
		dh.CloseConnection();
		String[] fej = { "RID", "PID", "Direction", "Regnum", "Date", "Name", "Mailing Address", "City", "Category" };
		((DefaultTableModel) dtable.getModel()).setColumnIdentifiers(fej);
		hh.setJTableColumnsWidth(dtable, 1500, 0, 0, 6, 7, 6, 28, 28, 15, 10);
		hh.table_show(dtable);
		if (dtable.getRowCount() > 0) {
			int row = dtable.getRowCount() - 1;
			dtable.setRowSelectionInterval(row, row);
		}
	}

	public void qregtable_update(JTable dtable, String what) {
		String Sql;
		if (what == "") {
			Sql = "select r.rid, r.pid, r.direction, r.regnumber, r.receive_date,  p.name, p.addressm, p.citym,"
					+ " r.partner_regnum, r.partner_admin, subject from reg_book r join partners p on r.pid = p.pid";

		} else {
			Sql = "select r.rid, r.pid, r.direction, r.regnumber, r.receive_date,  p.name, p.addressm, p.citym, "
					+ "r.partner_regnum, r.partner_admin, subject from reg_book r join partners p on r.pid = p.pid  where "
					+ what;
		}
		ResultSet res = dh.GetData(Sql);
		dtable.setModel(DbUtils.resultSetToTableModel(res));
		dh.CloseConnection();
		String[] fej = { "RID","PID", "Direction", "Regnumber", "Date", "Name", "Address", "City", "Partn. reg",
				"Partner's admin", "Subject" };
		((DefaultTableModel) dtable.getModel()).setColumnIdentifiers(fej);
		//hh.setJTableColumnsWidth(dtable, 1500, 0,0, 7, 7, 10, 20, 16, 10, 6, 11, 13);
		hh.setJTableColumnsWidth(dtable, 1500, 0, 0, 6, 9, 8, 20, 16, 10, 6, 12, 13);
		hh.table_show(dtable);
		if (dtable.getRowCount() > 0) {
			int row = dtable.getRowCount() - 1;
			dtable.setRowSelectionInterval(row, row);
		}
	}

	public ResultSet getpartner(String pid) throws SQLException {
		String sql = " select * from partners where pid ='" + pid + "'";
		ResultSet rs = dh.GetData(sql);
		return rs;
	}

	public ResultSet getregbook(String rid) throws SQLException {
		String sql = "select r.rid, r.pid, r.direction, r.regnumber, r.receive_date, r.send_date,  p.name, p.addressm,"
				+ " p.citym, p.postalcodem, p.countrym, r.doc_category, r.arrive_mode, r.package_type, r.administrator,"
				+ "r.partner_admin, r.note, r.partner_regnum, r.subject, c.name as category "
				+ "from reg_book r join partners p on r.pid = p.pid   join codes c on r.doc_category = c.cid "
				+ "where rid ='" + rid + "'";
		ResultSet rs = dh.GetData(sql);
		return rs;
	}

	public ResultSet getlinkrows(String rid) throws SQLException {
		String sql = "select did, dname from documents  where rid ='" + rid + "'";
		ResultSet rs = dh.GetData(sql);
		return rs;
	}

	public String getregszam(String direction) throws SQLException {
		String snumber = "";
		int number = 0;
		String sql = "";
		String prefix = "";
		String year = "";
		int ng = 0;
		if (direction.equals("In")) {
			sql = "select in_regnumber, prefix, currentyear from params  where parid ='1'";
			ng = 1;
		} else {
			ng = 2;
			sql = "select out_regnumber, prefix, currentyear from params  where parid ='1'";
		}
		con = dh.getConnection();
		rs = dh.GetData(sql);
		if (rs.next()) {
			year = rs.getString("currentyear");
			prefix = rs.getString("prefix");
			if (ng == 1) {
				number = rs.getInt("in_regnumber") + 1;
			} else {
				number = rs.getInt("out_regnumber") + 1;
			}
		}
		dh.CloseConnection();

		if (ng == 1) {
			sql = " update  params set in_regnumber ='" + number + "'";
		} else {
			sql = " update  params set out_regnumber = '" + number + "'";
		}
		int flag = dh.Insupdel(sql);
		if (flag == 1) {
			snumber = prefix + direction + year + "/" + number;
		}
		return snumber;
	}
}
