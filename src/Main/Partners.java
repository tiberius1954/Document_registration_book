package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import Classes.Allvalid;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import Databaseop.DatabaseHelper;
import Databaseop.Databaseop;
import Classes.Hhelper;
import Classes.Category;
import net.proteanit.sql.DbUtils;

public class Partners extends JFrame {
	Connection con;
	Statement stmt;
	PreparedStatement pst;
	ResultSet rs;
	Hhelper hh = new Hhelper();
	DatabaseHelper dh = new DatabaseHelper();
	Databaseop dd = new Databaseop();
	Allvalid allv = new Allvalid();
	private String rowid = "";
	private int myrow = 0;
	private String sfrom = "";
	private JFrame pframe;


	Partners() {
		initcomponents();
		dd.countrycombofill(cmbcountriesh);
		dd.countrycombofill(cmbcountriesm);
		dd.categoriescombofill(cmbcategory);
		dd.table_update(par_table, "");
		hh.iconhere(this);
	}
	Partners(JFrame parent) {
		sfrom = "registrationbook";
		pframe = parent;
		initcomponents();
		dd.countrycombofill(cmbcountriesh);
		dd.countrycombofill(cmbcountriesm);
		dd.categoriescombofill(cmbcategory);
		dd.table_update(par_table, "");
		hh.iconhere(this);
	}

	private void initcomponents() {
		UIManager.put("ComboBox.selectionBackground", hh.lpiros);
		UIManager.put("ComboBox.selectionForeground", hh.feher);
		UIManager.put("ComboBox.background", new ColorUIResource(hh.homok));
		UIManager.put("ComboBox.foreground", Color.BLACK);
		UIManager.put("ComboBox.border", new LineBorder(Color.green, 1));
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				if (hh.whichpanel(cardPanel) == "tabla") {
					dispose();
				} else if (hh.whichpanel(cardPanel) == "edit") {
					cards.show(cardPanel, "tabla");
				} else if (hh.whichpanel(cardPanel) == "gedit") {
					cards.show(cardPanel, "tabla");
				}
			}
		});
		setTitle("Partners");
		cp = getContentPane();
		cp.setBackground(hh.neonzold);
		fejpanel = new JPanel(null);
		fejpanel.setBounds(0, 0, 1230, 50);
		fejpanel.setBackground(hh.zold);

		lbheader = hh.flabel("P A R T N E R S");
		lbheader.setBounds(500, 5, 300, 40);
		fejpanel.add(lbheader);
		add(fejpanel);

		cards = new CardLayout();
		cardPanel = new JPanel(null);
		cardPanel.setLayout(cards);
		cardPanel.setBounds(10, 50, 1200, 570);
		tPanel = maketpanel();
		tPanel.setName("tabla");
		ePanel = makeepanel();
		ePanel.setName("edit");
		gePanel = makegepanel();
		gePanel.setName("gedit");

		cardPanel.add(tPanel, "tabla");
		cardPanel.add(ePanel, "edit");
		cardPanel.add(gePanel, "gedit");

		add(cardPanel);
	cards.show(cardPanel, "tabla");
		// cards.show(cardPanel, "edit");
		// cards.show(cardPanel, "gedit");
	}

	private JPanel maketpanel() {
		JPanel ttpanel = new JPanel(null);
		ttpanel.setSize(1200, 540);
		ttpanel.setBackground(hh.neonzold);
		lbsearch = hh.clabel("Search:");
		lbsearch.setBounds(280, 20, 70, 25);
		ttpanel.add(lbsearch);

		txsearch = cTextField(25);
		txsearch.setBounds(360, 20, 250, 25);
		ttpanel.add(txsearch);

		btnclear = new JButton();
		btnclear.setFont(new java.awt.Font("Tahoma", 1, 16));
		btnclear.setMargin(new Insets(0, 0, 0, 0));
		btnclear.setBounds(610, 20, 25, 25);
		btnclear.setBorder(hh.borderf);
		btnclear.setText("x");
		ttpanel.add(btnclear);
		btnclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txsearch.setText("");
				txsearch.requestFocus();
				dd.table_update(par_table, "");
			}
		});
		cmbsearch = hh.cbcombo();
		cmbsearch.setFocusable(true);
		cmbsearch.setBounds(645, 20, 150, 25);
		cmbsearch.setFont(new java.awt.Font("Tahoma", 1, 16));
		cmbsearch.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.DARK_GRAY));
		cmbsearch.setBackground(Color.ORANGE);
		cmbsearch.addItem("Name");
		cmbsearch.addItem("City");
		cmbsearch.addItem("Address");
		ttpanel.add(cmbsearch);

		btnsearch = hh.cbutton("Filter");
		btnsearch.setForeground(Color.black);
		btnsearch.setBackground(Color.ORANGE);
		btnsearch.setBounds(800, 20, 90, 25);
		btnsearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		ttpanel.add(btnsearch);
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sqlgyart();
			}
		});

		btnesearch = new JButton();
		try {
			ImageIcon ImageIcon = new ImageIcon(ClassLoader.getSystemResource("Images/search3.png"));
			Image image = ImageIcon.getImage();
			btnesearch.setIcon(new ImageIcon(image));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnesearch.setBorder(hh.myRaisedBorder);
		btnesearch.setBounds(895, 15, 40, 36);
		btnesearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnesearch.setToolTipText("Extended search");
		ttpanel.add(btnesearch);
		btnesearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(cardPanel, "gedit");
			}
		});

		par_table = hh.ztable();
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) par_table.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.LEFT);
		par_table.setTableHeader(new JTableHeader(par_table.getColumnModel()) {
			@Override
			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.height = 25;
				return d;
			}
		});
		hh.madeheader(par_table);
		par_table.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				par_table.scrollRectToVisible(par_table.getCellRect(par_table.getRowCount() - 1, 0, true));
			}
		});

		jSPane1 = new JScrollPane(par_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		par_table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {} },
				new String[] { "PID", "Name", "Postal code", "Mailing address", "City", "Country" }));
		hh.setJTableColumnsWidth(par_table, 1150, 0, 30, 10, 30, 20, 20);
		jSPane1.setViewportView(par_table);
		jSPane1.getViewport().setBackground(hh.vvvzold);
		jSPane1.setBounds(20, 70, 1150, 380);
		jSPane1.setBorder(hh.borderf);
		ttpanel.add(jSPane1);

		btnnew = hh.cbutton("New");
		btnnew.setBounds(400, 490, 130, 30);
		btnnew.setBackground(hh.vpiros1);
		ttpanel.add(btnnew);

		btnnew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				data_new();
			}
		});

		btnupdate = hh.cbutton("Update");
		btnupdate.setBounds(540, 490, 130, 30);
		btnupdate.setBackground(hh.zold);
		ttpanel.add(btnupdate);
		btnupdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					data_update();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		btndelete = hh.cbutton("Delete");
		btndelete.setBounds(680, 490, 130, 30);
		btndelete.setBackground(hh.vkek);
		ttpanel.add(btndelete);
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				data_delete();
			}
		});
	
		btnsendtoregbook = hh.cbutton("Send to Registration");
		btnsendtoregbook.setBounds(980, 490, 190, 30);
		btnsendtoregbook.setBackground(hh.narancs);
		if (sfrom.equals("registrationbook")){ 
			ttpanel.add(btnsendtoregbook);
	      }
		
		btnsendtoregbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			sendtorowreg();
			}
		});				
		return ttpanel;
	}

	private void data_new() {
		rowid = "";
		cards.show(cardPanel, "edit");
		clearFields();
		txname.requestFocus();
	}

	private void data_update() throws SQLException {
		DefaultTableModel d1 = (DefaultTableModel) par_table.getModel();
		int row = par_table.getSelectedRow();
		myrow = 0;
		if (row < 0) {
			rowid = "";
			row = 0;
		} else {
			myrow = row;
			rowid = d1.getValueAt(row, 0).toString();
			ResultSet rs = dd.getpartner(rowid);			
				txname.setText(rs.getString("name"));
				txpostalcodeh.setText(rs.getString("postalcodeh"));
				txaddressh.setText(rs.getString("addressh"));
				txcityh.setText(rs.getString("cityh"));
				cmbcountriesh.setSelectedItem(rs.getString("countryh"));
				cmbcountriesm.setSelectedItem(rs.getString("countrym"));
				txpostalcodem.setText(rs.getString("postalcodem"));
				txaddressm.setText(rs.getString("addressm"));
				txcitym.setText(rs.getString("citym"));
				txemail.setText(rs.getString("email"));
				txphone.setText(rs.getString("phone"));
				txhomepage.setText(rs.getString("homepage"));
				txanote.setText(rs.getString("note"));
				String cnum = rs.getString("category");
				int number = 0;
				if (!hh.zempty(cnum)) {
					number = Integer.parseInt(cnum);
				}
				hh.setSelectedValue(cmbcategory, number);
				cmbcategory.updateUI();			
				dh.CloseConnection();
			}		
			cards.show(cardPanel, "edit");	
			txname.requestFocus();
		}		
	
	private int data_delete() {
		String sql = "delete from partners  where pid =";
		Boolean error = false;
		int flag = 0;
		DefaultTableModel d1 = (DefaultTableModel) par_table.getModel();
		int sIndex = par_table.getSelectedRow();
		if (sIndex < 0) {
			return flag;
		}
		String iid = d1.getValueAt(sIndex, 0).toString();
		if (iid.equals("")) {
			return flag;
		}
		if (dd.cannotdelete("select pid from reg_book  where pid =" + iid) == true) {
			JOptionPane.showMessageDialog(null, "You can not delete this  partner !");
			return flag;
		}
		int a = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?");
		if (a == JOptionPane.YES_OPTION) {
			String vsql = sql + iid;
			flag = dh.Insupdel(vsql);
			if (flag == 1)
				d1.removeRow(sIndex);
			clearFields();
		}
		return flag;
	}
	private void sqlgyart() {
		String stext = txsearch.getText().trim().toLowerCase();
		String scmbtxt = String.valueOf(cmbsearch.getSelectedItem());
		String swhere = "";
		if (!hh.zempty(stext)) {
			if (scmbtxt == "Name") {
				swhere = " lower(name) like '%" + stext.trim() + "%'";
			} else if (scmbtxt == "City") {
				swhere = " lower(citym) like '%" + stext.trim() + "%' ";
			} else if (scmbtxt == "Address") {
				swhere = " lower(addressm) like '%" + stext.trim() + "%' ";
			}
			dd.table_update(par_table,swhere);
		} else {
			JOptionPane.showMessageDialog(null, "Empty condition !", "Error", 1);
			return;
		}
	}

	private JPanel makeepanel() {
		JPanel eepanel = new JPanel(null);
		eepanel.setBounds(0, 52, 1200, 560);
		// eepanel.setBorder(hh.borderf);
		eepanel.setBackground(hh.neonzold);
		lPanel = new JPanel(null);
		lPanel.setBounds(10, 0, 1180, 565);
		lPanel.setBackground(hh.neonzold);
//		lPanel.setBorder(hh.line);
		eepanel.add(lPanel);

		lbname = hh.clabel("Name");
		lbname.setBounds(70, 20, 120, 20);
		lPanel.add(lbname);

		txname = cTextField(25);
		txname.setBounds(200, 20, 660, 25);
		lPanel.add(txname);
		txname.addKeyListener(hh.MUpper());

		add1Panel = new JPanel(null);
		add1Panel.setBounds(5, 55, 1170, 120);
		add1Panel.setBackground(hh.neonzold);
		TitledBorder b1 = new TitledBorder(hh.rb, "Headquarters address");
		b1.setTitleFont(new Font("Tahoma", 1, 16));
		b1.setTitleColor(hh.narancs2);
		add1Panel.setBorder(b1);
		lPanel.add(add1Panel);

		lbcountryh = hh.clabel("Country");
		lbcountryh.setBounds(0, 30, 90, 20);
		add1Panel.add(lbcountryh);

		cmbcountriesh = hh.cbcombo();
		cmbcountriesh.setBounds(100, 30, 250, 25);
		add1Panel.add(cmbcountriesh);

		lbpostalch = hh.clabel("Postalcode");
		lbpostalch.setBounds(360, 30, 90, 20);
		add1Panel.add(lbpostalch);

		txpostalcodeh = cTextField(25);
		txpostalcodeh.setBounds(460, 30, 150, 25);
		add1Panel.add(txpostalcodeh);

		lbcityh = hh.clabel("City");
		lbcityh.setBounds(620, 30, 70, 20);
		add1Panel.add(lbcityh);

		txcityh = cTextField(25);
		txcityh.setBounds(700, 30, 400, 25);
		add1Panel.add(txcityh);
		txcityh.addKeyListener(hh.MUpper());

		lbaddrh = hh.clabel("Address");
		lbaddrh.setBounds(0, 70, 90, 20);
		add1Panel.add(lbaddrh);

		txaddressh = cTextField(700);
		txaddressh.setBounds(100, 70, 1000, 25);
		add1Panel.add(txaddressh);
		txaddressh.addKeyListener(hh.MUpper());

		btnaddrcopy = hh.cbutton("Address copy");
		btnaddrcopy.setBounds(1030, 185, 140, 25);
		btnaddrcopy.setForeground(Color.black);
		btnaddrcopy.setBackground(Color.ORANGE);
		lPanel.add(btnaddrcopy);
		btnaddrcopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				copyFields();
			}
		});

		add2Panel = new JPanel(null);
		add2Panel.setBounds(5, 210, 1170, 120);
		add2Panel.setBackground(hh.neonzold);
		TitledBorder b2 = new TitledBorder(hh.rb, "Mailing address");
		b2.setTitleFont(new Font("Tahoma", 1, 16));
		b2.setTitleColor(hh.narancs2);
		add2Panel.setBorder(b2);
		lPanel.add(add2Panel);

		lbcountrym = hh.clabel("Country");
		lbcountrym.setBounds(0, 30, 90, 20);
		add2Panel.add(lbcountrym);

		cmbcountriesm = hh.cbcombo();
		cmbcountriesm.setBounds(100, 30, 250, 25);
		add2Panel.add(cmbcountriesm);

		lbpostalcm = hh.clabel("Postalcode");
		lbpostalcm.setBounds(360, 30, 90, 20);
		add2Panel.add(lbpostalcm);

		txpostalcodem = cTextField(25);
		txpostalcodem.setBounds(460, 30, 150, 25);
		add2Panel.add(txpostalcodem);

		lbcitym = hh.clabel("City");
		lbcitym.setBounds(620, 30, 70, 20);
		add2Panel.add(lbcitym);

		txcitym = cTextField(25);
		txcitym.setBounds(700, 30, 400, 25);
		add2Panel.add(txcitym);
		txcitym.addKeyListener(hh.MUpper());

		lbaddrm = hh.clabel("Address");
		lbaddrm.setBounds(0, 70, 90, 20);
		add2Panel.add(lbaddrm);

		txaddressm = cTextField(700);
		txaddressm.setBounds(100, 70, 1000, 25);
		add2Panel.add(txaddressm);
		txaddressm.addKeyListener(hh.MUpper());

		lbphone = hh.clabel("Phone");
		lbphone.setBounds(5, 350, 90, 20);
		lPanel.add(lbphone);

		txphone = cTextField(700);
		txphone.setBounds(110, 350, 550, 25);
		lPanel.add(txphone);

		lbemail = hh.clabel("Email");
		lbemail.setBounds(660, 350, 60, 20);
		lPanel.add(lbemail);

		txemail = cTextField(700);
		txemail.setBounds(730, 350, 440, 25);
		lPanel.add(txemail);

		lbhomepage = hh.clabel("Homepage");
		lbhomepage.setBounds(5, 390, 90, 20);
		lPanel.add(lbhomepage);

		txhomepage = cTextField(700);
		txhomepage.setBounds(110, 390, 700, 25);
		lPanel.add(txhomepage);

		lbcategory = hh.clabel("Category");
		lbcategory.setBounds(810, 390, 90, 20);
		lPanel.add(lbcategory);

		cmbcategory = hh.cbcombo();
		cmbcategory.setBounds(910, 390, 260, 25);
		cmbcategory.setName("pcategory");
		lPanel.add(cmbcategory);

		lbnote = hh.clabel("Notes");
		lbnote.setBounds(230, 440, 90, 25);
		lPanel.add(lbnote);

		txanote = new JTextArea();
		// txanote.setBounds(910, 390, 260, 25);
		txanote.setFont(new java.awt.Font("Monospaced", 1, 18));
		txanote.setLineWrap(true);
		txanote.setWrapStyleWord(true);
		txanote.setMargin(new Insets(5, 5, 5, 5));
		txanote.putClientProperty("caretAspectRatio", 0.1);
		// txanote.setEditable(false);
		txanote.setCaretColor(Color.RED);
		jSPane2 = new JScrollPane();
		jSPane2.setViewportView(txanote);
		jSPane2.setBounds(330, 430, 600, 130);
		jSPane2.setBorder(hh.bb);
		jSPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jSPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		lPanel.add(jSPane2);

		btnsave = hh.cbutton("Save");
		btnsave.setBounds(870, 20, 100, 25);
		btnsave.setBackground(hh.vpiros1);
		lPanel.add(btnsave);

		btnsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				 savebuttrun();
			}
		});
		btncancel = hh.cbutton("Cancel");
		btncancel.setBackground(hh.zold);
		btncancel.setBounds(980, 20, 100, 25);
		lPanel.add(btncancel);
		btncancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				clearFields();
				cards.show(cardPanel, "tabla");
			}
		});
		return eepanel;
	}
	
	private void savebuttrun() {
		String sql = "";
		String jel = "";
		String countryh = "";
		String countrym = "";
		String category = "";
		int catid = 0;
		DefaultTableModel d1 = (DefaultTableModel) par_table.getModel();
		if (cmbcountriesh.getSelectedItem() != null) {
			countryh = String.valueOf(cmbcountriesh.getSelectedItem());
		}
		if (cmbcountriesm.getSelectedItem() != null) {
			countrym = String.valueOf(cmbcountriesm.getSelectedItem());
		}
		
		if (cmbcategory.getSelectedItem() != null) {
			category= String.valueOf(cmbcategory.getSelectedItem());
			Category cat = (Category) cmbcategory.getSelectedItem();
			catid = cat.getCid();
		}
		String name = txname.getText();
		String phone = txphone.getText();
		String email = txemail.getText();
		String addressh = txaddressh.getText();
		String cityh = txcityh.getText();
		String addressm = txaddressm.getText();
		String citym = txcitym.getText();		
		String postalcodeh = txpostalcodeh.getText();
		String postalcodem = txpostalcodem.getText();
		String homepage = txhomepage.getText();
		String note = txanote.getText();		

		if (pvalidation(name, addressm, citym) == false) {
			return;
		}

		if (rowid != "") {
			jel = "UP";
			sql = "update  partners set name= '" + name + "', phone= '" + phone + "'," + "email = '" + email
					+ "', addressh = '" + addressh + "'," + "cityh= '" + cityh + "', countryh= '" + countryh + "',"
					+ "postalcodeh = '" + postalcodeh + "', addressm='" + addressm + "', citym='" + citym
					+ "', postalcodem='" + postalcodem + "', " + " countrym='" + countrym +
					"', category='" + catid + "', homepage='" +homepage +"', note='" + note +
						"' where pid = " + rowid;
		} else {
			sql = "insert into partners (name, phone, email, addressh, cityh, countryh, countryh, postalcodeh, "
					+ "addressm, citym, postalcodem, countrym, category, homepage, note) " + "values ('" + name + 
					"','" + phone + "','" + email + "','"
					+ addressh + "','" + cityh + "','" + countryh + "','" + countryh + "','" +postalcodeh + "','" + addressm + "','"
					+ citym + "','" + postalcodem + "','" + countrym + "','" + catid +"','" +homepage +"','" + note + "')";
		}
		try {
			int flag = dh.Insupdel(sql);
			if (flag == 1) {
				hh.ztmessage("Success", "Message");
				if (rowid == "") {
					int myid = dd.table_maxid("SELECT MAX(pid) AS max_id FROM partners");
					d1.insertRow(d1.getRowCount(),
							new Object[] { myid, name, postalcodem, addressm, citym, countrym });
					hh.gotolastrow(par_table);
					if (par_table.getRowCount() > 0) {
						int row = par_table.getRowCount() - 1;
						par_table.setRowSelectionInterval(row, row);
					}
				} else {
					d1.setValueAt(name, myrow, 1);				
					d1.setValueAt(postalcodem, myrow, 2);
					d1.setValueAt(addressm, myrow, 3);
					d1.setValueAt(citym, myrow, 4);
					d1.setValueAt(countrym, myrow, 5);					
				}
			} else {
				JOptionPane.showMessageDialog(null, "sql error !");
			}
		} catch (Exception e) {
			System.err.println("SQLException: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "sql insert hiba");
		}
		clearFields();
		cards.show(cardPanel, "tabla");
	}
	private Boolean pvalidation( String name, String addressm, String citym) {
		Boolean ret = true;
		ArrayList<String> err = new ArrayList<String>();

		if (!allv.namevalid(name)) {
			err.add(allv.mess);
			ret = false;
	}
		if (!allv.addressvalid(addressm)) {
			err.add(allv.mess);
			ret = false;
	}
		if (!allv.cityvalid(citym)) {
			err.add(allv.mess);
			ret = false;
	}
		if (err.size() > 0) {
			JOptionPane.showMessageDialog(null, err.toArray(), "Error message", 1);
		}
		return ret;
	}
	
	private void clearFields() {
		txname.setText("");
		txpostalcodeh.setText("");
		txaddressh.setText("");
		txcityh.setText("");
		cmbcountriesh.setSelectedIndex(-1);
		cmbcountriesm.setSelectedIndex(-1);
		txpostalcodem.setText("");
		txaddressm.setText("");
		txcitym.setText("");
		txemail.setText("");
		txphone.setText("");
		txhomepage.setText("");
		txanote.setText("");
		cmbcategory.setSelectedIndex(-1);
	}
	private void copyFields() {
		txpostalcodem.setText(txpostalcodeh.getText());
		txcitym.setText(txcityh.getText());
		txaddressm.setText(txaddressh.getText());
		
		if (cmbcountriesh.getSelectedItem() != null) {		
			String country = String.valueOf(cmbcountriesh.getSelectedItem());
			cmbcountriesm.setSelectedItem(country);			
		}
	}

	private JPanel makegepanel() {
		JPanel gepanel = new JPanel(null);
		// gepanel.setBorder(hh.line);
		gepanel.setBounds(0, 52, 1200, 560);
		// eepanel.setBorder(hh.borderf);
		gepanel.setBackground(hh.neonzold);
		gPanel = new JPanel(null);
		gPanel.setBounds(100, 5, 1000, 220);
		gPanel.setBackground(hh.neonzold);
		gPanel.setBorder(hh.rb);
		gepanel.add(gPanel);

		lbquater = hh.clabel("Headquarters address");
		lbquater.setBounds(10, 10, 190, 25);
		lbquater.setBackground(hh.sotetkek);
		lbquater.setForeground(Color.white);
		lbquater.setHorizontalAlignment(JLabel.CENTER);
		lbquater.setOpaque(true);
		gPanel.add(lbquater);

		lbpostalcodes = hh.clabel("Postalcode");
		lbpostalcodes.setBounds(10, 50, 100, 25);
		gPanel.add(lbpostalcodes);

		txpostalcodes = cTextField(100);
		txpostalcodes.setBounds(120, 50, 100, 25);
		gPanel.add(txpostalcodes);

		lbcitys = hh.clabel("City");
		lbcitys.setBounds(230, 50, 40, 25);
		gPanel.add(lbcitys);

		txcitys = cTextField(100);
		txcitys.setBounds(280, 50, 300, 25);
		gPanel.add(txcitys);

		lbaddresss = hh.clabel("Address");
		lbaddresss.setBounds(590, 50, 80, 25);
		gPanel.add(lbaddresss);

		txaddresss = cTextField(100);
		txaddresss.setBounds(680, 50, 300, 25);
		gPanel.add(txaddresss);

		lbmmail = hh.clabel("Mailing address");
		lbmmail.setBounds(10, 90, 190, 25);
		lbmmail.setBackground(hh.sotetkek);
		lbmmail.setForeground(Color.white);
		lbmmail.setHorizontalAlignment(JLabel.CENTER);
		lbmmail.setOpaque(true);
		gPanel.add(lbmmail);

		lbpostalcodel = hh.clabel("Postalcode");
		lbpostalcodel.setBounds(10, 130, 100, 25);
		gPanel.add(lbpostalcodel);

		txpostalcodel = cTextField(100);
		txpostalcodel.setBounds(120, 130, 100, 25);
		gPanel.add(txpostalcodel);

		lbcityl = hh.clabel("City");
		lbcityl.setBounds(230, 130, 40, 25);
		gPanel.add(lbcityl);

		txcityl = cTextField(100);
		txcityl.setBounds(280, 130, 300, 25);
		gPanel.add(txcityl);

		lbaddressl = hh.clabel("Address");
		lbaddressl.setBounds(590, 130, 80, 25);
		gPanel.add(lbaddressl);

		txaddressl = cTextField(100);
		txaddressl.setBounds(680, 130, 300, 25);
		gPanel.add(txaddressl);

		lbemails = hh.clabel("Email");
		lbemails.setBounds(10, 175, 60, 25);
		gPanel.add(lbemails);

		txemails = cTextField(100);
		txemails.setBounds(120, 175, 300, 25);
		gPanel.add(txemails);

		lbphones = hh.clabel("Phone");
		lbphones.setBounds(430, 175, 60, 25);
		gPanel.add(lbphones);

		txphones = cTextField(100);
		txphones.setBounds(500, 175, 270, 25);
		gPanel.add(txphones);

		btnfilters = hh.cbutton("Filter");
		btnfilters.setForeground(Color.black);
		btnfilters.setBackground(Color.ORANGE);
		btnfilters.setBounds(790, 175, 90, 25);
		btnfilters.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		gPanel.add(btnfilters);
		btnfilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sqlgyarts();
			}
		});

		btnclears = hh.cbutton("Clear");
		btnclears.setForeground(Color.black);
		btnclears.setBackground(Color.ORANGE);
		btnclears.setBounds(890, 175, 90, 25);
		btnclears.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		gPanel.add(btnclears);
		btnclears.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearSFields();
			}
		});		

		btnsendto = hh.cbutton("Send to Partners");
		btnsendto.setBounds(910, 240, 190, 30);
		btnsendto.setBackground(hh.narancs);
		gepanel.add(btnsendto);
		btnsendto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendtorow();
			}
		});		

		sea_table = hh.ztable();
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) sea_table.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.LEFT);
		sea_table.setTableHeader(new JTableHeader(sea_table.getColumnModel()) {
			@Override
			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.height = 25;
				return d;
			}
		});

		hh.madeheader(sea_table);
		sea_table.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				sea_table.scrollRectToVisible(sea_table.getCellRect(par_table.getRowCount() - 1, 0, true));
			}
		});

		jSPane3 = new JScrollPane(sea_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		sea_table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {} },
				new String[] { "PID", "Name", "Postal code", "Address", "City", "Phone","Country" }));		
	   hh.setJTableColumnsWidth(sea_table, 1150, 0, 30, 10, 30, 20, 20,0);	  
      jSPane3.setViewportView(sea_table);		
		jSPane3.getViewport().setBackground(hh.vvvzold);
       jSPane3.setBounds(20, 285, 1150, 250);	
		jSPane3.setBorder(hh.borderf);
		gepanel.add(jSPane3);
		return gepanel;
	}	
	
	private void sendtorow() {
		int flag = 0;
		DefaultTableModel d1 = (DefaultTableModel) sea_table.getModel();
		DefaultTableModel d2 = (DefaultTableModel) par_table.getModel();
		int sIndex = sea_table.getSelectedRow();
		if (sIndex < 0) {
			return;
		}
		String pid = d1.getValueAt(sIndex, 0).toString();
		if (pid.equals("")) {
			return;
		}
		d2.setRowCount(0);
		String name  = d1.getValueAt(sIndex, 1).toString();
		String postalcodem = d1.getValueAt(sIndex, 2).toString();
		String addressm = d1.getValueAt(sIndex, 3).toString();
		String citym= d1.getValueAt(sIndex, 4).toString();
		String countrym = d1.getValueAt(sIndex, 6).toString();
	
		d2.insertRow(0,
				new Object[] { pid, name, postalcodem, addressm, citym, countrym });	
		    cards.show(cardPanel, "tabla");
		    clearSFields();
	}
	private void sendtorowreg() {
		int flag = 0;	
		DefaultTableModel d1 = (DefaultTableModel) par_table.getModel();
		int sIndex = par_table.getSelectedRow();
		if (sIndex < 0) {
			return;
		}
		String pid = d1.getValueAt(sIndex, 0).toString();
		if (pid.equals("")) {
			return;
		}	
		String name  = d1.getValueAt(sIndex, 1).toString();
		String postalcodem = d1.getValueAt(sIndex, 2).toString().trim();
		String addressm = d1.getValueAt(sIndex, 3).toString().trim();
		String citym = d1.getValueAt(sIndex, 4).toString().trim();
	    String countrym = d1.getValueAt(sIndex, 5).toString().trim();
		pid =  d1.getValueAt(sIndex, 0).toString();
		((Registrationbook) pframe).txname.setText(name);
		((Registrationbook) pframe).txaddress.setText(postalcodem+ " " +addressm + " " + citym+ " "+ countrym);
		((Registrationbook) pframe).txpid.setText(pid);		
		pframe.setVisible(true);		
		dispose();
	}	
		
	private void sqlgyarts() {
		String postalcodeh = txpostalcodes.getText().trim().toLowerCase();
		String postalcodem = txpostalcodel.getText().trim().toLowerCase();
		String cityh = txcitys.getText().trim().toLowerCase();
		String citym = txcityl.getText().trim().toLowerCase();
		String addressh = txaddresss.getText().trim().toLowerCase();
		String addressm = txaddressl.getText().trim().toLowerCase();
		String email = txemails.getText().trim().toLowerCase();
		String phone = txphones .getText().trim().toLowerCase();
		String ww = " and ";
		
		String swhere = "";
		if (!hh.zempty(postalcodeh)) {		
				swhere =  " lower(postalcodeh)  like '%" + postalcodeh + "%'"+ ww ;
			} 
		if (!hh.zempty(addressh)) {		
			swhere +=  " lower(addressh)  like '%" + addressh + "%'"+ ww ;
		} 
		if (!hh.zempty(cityh)) {		
			swhere +=  " lower(cityh)  like '%" + cityh + "%'"+ ww ;
		} 
		if (!hh.zempty(postalcodem)) {		
			swhere += " lower(postalcodeh)  like '%" + postalcodem + "%'"+ ww ;
		} 
		if (!hh.zempty(addressm)) {		
			swhere += " lower(addressm)  like '%" + addressm + "%'"+ ww ;
		} 
		if (!hh.zempty(citym)) {		
			swhere +=  " lower(citym)  like '%" + citym + "%'"+ ww ;
		} 
		if (!hh.zempty(email)) {		
			swhere +=  " lower(email)  like '%" + email + "%'"+ ww ;
		} 
		if (!hh.zempty(phone)) {		
			swhere +=  " phone  like '%" + phone + "%'"+ ww ;
		} 
		if (swhere.length() >0) {
			 swhere = swhere.substring(1, swhere.lastIndexOf(ww));
				
			dd.stable_update(sea_table, swhere);
		} else {
			JOptionPane.showMessageDialog(null, "Empty condition !", "Error", 1);
			return;
		}
	}
	private void clearSFields() {
		DefaultTableModel d1 = (DefaultTableModel) sea_table.getModel();
		txpostalcodes.setText("");
		txpostalcodel.setText("");
		txcitys.setText("");
		txcityl.setText("");
		txaddresss.setText("");
		txaddressl.setText("");
		txemails.setText("");
		txphones .setText("");	
		d1.setRowCount(0);		
	}
	private final FocusListener dFocusListener = new FocusListener() {
		@Override
		public void focusGained(FocusEvent e) {
			JComponent c = (JComponent) e.getSource();
			c.setBorder(hh.borderz);
		}
		@Override	
		public void focusLost(FocusEvent e) {
			String content = "";
			boolean ret = true;
			JTextField Txt = (JTextField) e.getSource();
			content = Txt.getText();		
			if (Txt == txname) {
				Txt.setText(content);
				 ret = allv.namevalid(content);
			}	else if (Txt == txaddressm) {
				Txt.setText(content);
				 ret = allv.addressvalid(content);
			}	else if (Txt == txcitym) {
				Txt.setText(content);
				 ret = allv.cityvalid(content);
			}				
			if (ret == true) {
				Txt.setBorder(hh.borderf);
			} else {
				Txt.setBorder(hh.borderp);
			}
		}
	};



	public JTextField cTextField(int hossz) {
		JTextField textField = new JTextField(hossz);
		textField.setFont(hh.textf);
		textField.setBorder(hh.borderf);
		textField.setBackground(hh.feher);
		textField.setPreferredSize(new Dimension(250, 30));
		textField.setCaretColor(Color.RED);
		textField.putClientProperty("caretAspectRatio", 0.1);
		textField.addFocusListener(dFocusListener);
		textField.setText("");
		textField.setDisabledTextColor(Color.magenta);
		return textField;
	}

	public static void main(String args[]) {
		Partners par = new Partners();
		par.setSize(1230, 670);
		par.setLayout(null);
		par.setLocationRelativeTo(null);
		par.setVisible(true);
	}

	CardLayout cards;
	JPanel cardPanel, tPanel, ePanel, fejpanel, lPanel, rPanel, add1Panel, add2Panel, gePanel, gPanel;
	JScrollPane jSPane1, jSPane2, jSPane3;
	JTable par_table, sea_table;
	JComboBox cmbcountriesh, cmbcountriesm, cmbsearch, cmbcategory;
	JLabel lbname, lbaddrh, lbcityh, lbcountryh, lbphone, lbemail, lbheader, lbpostalch;
	JLabel lbaddrm, lbcitym, lbcountrym, lbpostalcm, lbhomepage, lbcategory;
	JLabel lbpicture, lbsearch, lbnote, lbquater, lbmmail;
	JLabel lbpostalcodes, lbcitys, lbaddresss, lbpostalcodel, lbcityl, lbaddressl, lbemails, lbphones;
	JTextArea txanote;
	JTextField txname, txaddressh, txcityh, txpostalcodeh, txphone, txemail, txsearch;
	JTextField txaddressm, txcitym, txpostalcodem, txhomepage;
	JTextField txpostalcodes, txcitys, txaddresss, txpostalcodel, txcityl, txaddressl, txemails, txphones;
	JButton btnnew, btndelete, btnupdate, btnsave, btncancel, btnsendto, btnclear, btnsearch, btnaddrcopy, btnesearch;
	JButton btnfilters, btnclears, btnsendtoregbook;
	Container cp;
}
