package Main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import Classes.Allvalid;
import Classes.Category;
import Classes.Hhelper;
import Databaseop.DatabaseHelper;
import Databaseop.Databaseop;

public class Settings extends JFrame{
	Connection con;
	Statement stmt;
	PreparedStatement pst;
	ResultSet rs;
	Hhelper hh = new Hhelper();
	DatabaseHelper dh = new DatabaseHelper();
	Databaseop dd = new Databaseop();
	Allvalid allv = new Allvalid();
	
	Settings(){
		initcomponents();
		reading();
		hh.iconhere(this);
		txname.requestFocus();
		
	}
	private void initcomponents() {
		UIManager.put("ComboBox.selectionBackground", hh.lpiros);
		UIManager.put("ComboBox.selectionForeground", hh.feher);
		UIManager.put("ComboBox.background", new ColorUIResource(hh.homok));
		UIManager.put("ComboBox.foreground", Color.BLACK);
		UIManager.put("ComboBox.border", new LineBorder(Color.green, 1));

		setTitle("Settings");
		cp = getContentPane();
		cp.setBackground(hh.neonzold);
		fejpanel = new JPanel(null);
		fejpanel.setBounds(0, 0, 1230, 50);
		fejpanel.setBackground(hh.zold);

		lbheader = hh.flabel("SETTINGS");
		lbheader.setBounds(350, 5, 300, 40);
		fejpanel.add(lbheader);
		add(fejpanel);
		
		lpanel = new JPanel(null);
		
		lpanel.setBounds(10,60, 530,290);
	    lpanel.setBackground(hh.neonzold);
//	    lpanel.setBorder(hh.borderf);
		add(lpanel);
		
		lbname = hh.clabel("Name of firm:");
		lbname.setBounds(10, 10, 120, 25);
		lpanel.add(lbname);

		txname = cTextField(25);
		txname.setBounds(170, 10, 340, 25);
		lpanel.add(txname);
		txname.addKeyListener(hh.MUpper());
		
		lbaddress = hh.clabel("Address:");
		lbaddress.setBounds(10, 60, 120, 25);
		lpanel.add(lbaddress);

		txaddress = cTextField(25);
		txaddress.setBounds(170, 60, 340, 25);
		lpanel.add(txaddress);
		txaddress.addKeyListener(hh.MUpper());
		
		lbcity = hh.clabel("City:");
		lbcity.setBounds(10, 110, 120, 25);
		lpanel.add(lbcity);

		txcity = cTextField(25);
		txcity.setBounds(170, 110, 340, 25);
		lpanel.add(txcity);
		txcity.addKeyListener(hh.MUpper());
		
		lbpostalcode = hh.clabel("Postalcode:");
		lbpostalcode.setBounds(10, 160, 120, 25);
		lpanel.add(lbpostalcode);

		txpostalcode = cTextField(25);
		txpostalcode.setBounds(170, 160, 340, 25);
		lpanel.add(txpostalcode);
		
		lbphone = hh.clabel("Phone:");
		lbphone.setBounds(10, 210, 120, 25);
		lpanel.add(lbphone);

		txphone = cTextField(25);
		txphone.setBounds(170, 210, 340, 25);
		lpanel.add(txphone);
		
		lbemail = hh.clabel("Email:");
		lbemail.setBounds(10, 260, 120, 25);
		lpanel.add(lbemail);

		txemail = cTextField(25);
		txemail.setBounds(170, 260, 340, 25);
		lpanel.add(txemail);		
		
		rpanel = new JPanel(null);
		rpanel.setBounds(550,60, 370,250);
	    rpanel.setBackground(hh.neonzold);
	//	rpanel.setBorder(hh.borderf);
		add(rpanel);
		
		lbprefix = hh.clabel("Prefix in registration number:");
		lbprefix.setBounds(20, 10, 250, 25);
		rpanel.add(lbprefix);

		txprefix = cTextField(2);
		txprefix.setBounds(300, 10, 50, 25);
		rpanel.add(txprefix);	
		txprefix.addKeyListener(hh.Onlyalphabet(txprefix));
		
		lbcurrentyear = hh.clabel("Current year........................:");
		lbcurrentyear.setBounds(20, 60, 240, 25);
		rpanel.add(lbcurrentyear);

		txcurrentyear= cTextField(25);
		txcurrentyear.setBounds(280, 60, 70, 25);
		rpanel.add(txcurrentyear);
		txcurrentyear.addKeyListener(hh.Onlynum());
		
		lbin_number = hh.clabel("Sequential number (in) ......:");
		lbin_number.setBounds(20, 110, 240, 25);
		rpanel.add(lbin_number);

		txin_number= cTextField(25);
		txin_number.setBounds(280, 110, 70, 25);
		txin_number.setHorizontalAlignment(JTextField.RIGHT);
		rpanel.add(txin_number);
		txin_number.addKeyListener(hh.Onlynum());
		
		lbout_number = hh.clabel("Sequential number (out) ...:");
		lbout_number.setBounds(20, 160, 240, 25);
		rpanel.add(lbout_number);

		txout_number= cTextField(25);
		txout_number.setBounds(280, 160, 70, 25);
		txout_number.setHorizontalAlignment(JTextField.RIGHT);
		rpanel.add(txout_number);
		txout_number.addKeyListener(hh.Onlynum());
		
		btnsave = hh.cbutton("Save");
		btnsave.setBounds(330, 380, 100, 25);
		btnsave.setBackground(hh.vpiros1);
		add(btnsave);

		btnsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				 savebuttrun();
			}
		});
		btncancel = hh.cbutton("Cancel");
		btncancel.setBackground(hh.zold);
		btncancel.setBounds(440, 380, 100, 25);
		add(btncancel);
		btncancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
			dispose();
			}
		});	
	}
	private void reading() {
		try {
			 String sql = " select * from params where parid = '1'";  
			con = dh.getConnection();
			rs = dh.GetData(sql);
			if (rs.next()) {
			      txname.setText(rs.getString("name"));
			      txaddress.setText(rs.getString("address"));
			      txcity.setText(rs.getString("city"));
			      txpostalcode.setText(rs.getString("postal_code"));
			      txphone.setText(rs.getString("phone"));
			     txemail.setText(rs.getString("email"));
			      txprefix.setText(rs.getString("prefix"));
			      txcurrentyear.setText(rs.getString("currentyear"));
			      txin_number.setText(rs.getString("in_regnumber"));
			      txout_number.setText(rs.getString("out_regnumber"));			      
			} 
			dh.CloseConnection();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}	  	
	   }
	private void  savebuttrun() {
		String sql = "";
		String jel = "";		
		String name = txname.getText();
		String phone = txphone.getText();
		String email = txemail.getText();
		String address = txaddress.getText();
		String city = txcity.getText();	
		String postalcode = txpostalcode.getText();
		String currentyear = txcurrentyear.getText();
		String prefix = txprefix.getText();
		String in_number= txin_number.getText();		
		String out_number = txout_number.getText();

		if (ppvalidation(name, address, city, currentyear, prefix) == false) {
			return;
		}		
		sql = "update  params set name= '" + name + "', phone= '" + phone + "'," + "email = '" + email
				+ "', address = '" + address + "'," + "city= '" + city + "', currentyear= '" + currentyear + "',"
				+ "postal_code = '" + postalcode + "', prefix='" + prefix + "', in_regnumber='" + in_number
				+ "', out_regnumber='" + out_number + "' where parid = '1'";
		int flag = dh.Insupdel(sql);
		if (flag == 1) {
			hh.ztmessage("Success", "Message");
		}		
	}
		private Boolean ppvalidation(String name, String address, String city, String currentyear, String prefix) {
			Boolean ret = true;
			ArrayList<String> err = new ArrayList<String>();

			if (!allv.namevalid(name)) {
				err.add(allv.mess);
				ret = false;
		}
			if (!allv.addressvalid(address)) {
				err.add(allv.mess);
				ret = false;
		}
			if (!allv.cityvalid(city)) {
				err.add(allv.mess);
				ret = false;
		}
			if (!allv.currentyearvalid(currentyear)) {
				err.add(allv.mess);
				ret = false;
		}
			if (!allv.prefixvalid(currentyear)) {
				err.add(allv.mess);
				ret = false;
		}
			if (err.size() > 0) {
				JOptionPane.showMessageDialog(null, err.toArray(), "Error message", 1);
			}
			return ret;
		}

	public JTextField cTextField(int hossz) {
		JTextField textField = new JTextField(hossz);
		textField.setFont(hh.textf);
		textField.setBorder(hh.borderf);
		textField.setBackground(hh.feher);
		textField.setPreferredSize(new Dimension(250, 30));
		textField.setCaretColor(Color.RED);
		textField.putClientProperty("caretAspectRatio", 0.1);
		// textField.addFocusListener(dFocusListener);
		textField.setText("");
		textField.setDisabledTextColor(Color.magenta);
		return textField;
	}
	public static void main(String args[]) {
		Settings st = new Settings();	
		st.setSize(960, 500);
		st.setLayout(null);
		st.setLocationRelativeTo(null);	 
		st.setVisible(true);
	}
JLabel lbheader;
JPanel fejpanel,lpanel, rpanel;;
Container cp;
JComboBox cmbcountries;
JTextField txname, txpostalcode, txaddress, txcity, txphone, txemail, txin_number, txout_number, txprefix, txcurrentyear;
JLabel lbname, lbpostalcode, lbaddress, lbcity, lbphone, lbemail, lbin_number, lbout_number, lbprefix, lbcurrentyear;
JButton btnsave, btncancel;
}
