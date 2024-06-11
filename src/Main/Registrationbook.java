package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import Databaseop.DatabaseHelper;
import Databaseop.Databaseop;
import Classes.Hhelper;
import Classes.Item;
import Classes.Category;
import net.proteanit.sql.DbUtils;

public class Registrationbook extends JFrame {
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
	JDateChooser receivedate = new JDateChooser(new Date());
	JDateChooser postingdate = new JDateChooser(new Date());
	JDateChooser qdate1 = new JDateChooser(new Date());
	JDateChooser qdate2 = new JDateChooser(new Date());
	JFrame myframe = this;

	Registrationbook() {
		initcomponents();
		dd.itemcombofill(cmbsendmode, "S");
		dd.itemcombofill(cmbdoccat, "D");
		dd.itemcombofill(cmbqdoccat, "D");
		dd.itemcombofill(cmbadministrator, "A");
		dd.itemcombofill(cmbshiptype, "C");

		dd.regtable_update(reg_table, "");
		hh.iconhere(this);
	}

	private void initcomponents() {
		UIManager.put("ComboBox.selectionBackground", hh.lpiros);
		UIManager.put("ComboBox.selectionForeground", hh.feher);
		UIManager.put("ComboBox.background", new ColorUIResource(hh.homok));
		UIManager.put("ComboBox.foreground", Color.BLACK);
		UIManager.put("ComboBox.border", new LineBorder(Color.green, 1));
		UIManager.put("ComboBox.disabledBackground", Color.magenta);
		UIManager.put("ComboBox.disabledForeground", Color.yellow);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				if (hh.whichpanel(cardPanel) == "tabla") {
					dispose();
				} else if (hh.whichpanel(cardPanel) == "edit") {
					cards.show(cardPanel, "tabla");
				} else if (hh.whichpanel(cardPanel) == "gedit") {
					cards.show(cardPanel, "tabla");
				} else if (hh.whichpanel(cardPanel) == "gedit") {
					cards.show(cardPanel, "tabla");
				}
			}
		});
		setTitle("Registration book");
		cp = getContentPane();
		cp.setBackground(hh.neonzold);
		fejpanel = new JPanel(null);
		fejpanel.setBounds(0, 0, 1230, 50);
		fejpanel.setBackground(hh.zold);

		lbheader = hh.flabel("REGISTRATION BOOK");
		lbheader.setBounds(410, 5, 350, 40);
		fejpanel.add(lbheader);
		add(fejpanel);

		cards = new CardLayout();
		cardPanel = new JPanel(null);
		cardPanel.setLayout(cards);
		cardPanel.setBounds(0, 50, 1230, 580);

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
				dd.regtable_update(reg_table, "");
			}
		});

		cmbsearch = hh.cbcombo();
		cmbsearch.setFocusable(true);
		cmbsearch.setBounds(645, 20, 150, 25);
		cmbsearch.setFont(new java.awt.Font("Tahoma", 1, 16));
		cmbsearch.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.DARK_GRAY));
		cmbsearch.setBackground(Color.ORANGE);
		cmbsearch.addItem("Name");
		cmbsearch.addItem("Reg. number");
		cmbsearch.addItem("Doc. category");
		ttpanel.add(cmbsearch);

		btnsearch = hh.cbutton("Filter");
		btnsearch.setForeground(Color.black);
		btnsearch.setBackground(Color.ORANGE);
		btnsearch.setBounds(805, 20, 90, 25);
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
		btnesearch.setBounds(905, 15, 40, 36);
		btnesearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnesearch.setToolTipText("Extended search");
		ttpanel.add(btnesearch);
		btnesearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(cardPanel, "gedit");
			}
		});

		reg_table = hh.ztable();
		reg_table.setFont(new Font("tahoma", Font.BOLD, 12));

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) reg_table.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.LEFT);
		reg_table.setTableHeader(new JTableHeader(reg_table.getColumnModel()) {
			@Override
			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.height = 25;
				return d;
			}
		});
		hh.madeheader(reg_table);
		reg_table.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				reg_table.scrollRectToVisible(reg_table.getCellRect(reg_table.getRowCount() - 1, 0, true));
			}
		});

		jSPane1 = new JScrollPane(reg_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		reg_table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] { {} }, new String[] { "RID", "PID",
				"Direction", "Regnum", "Date", "Name", "Mailing Address", "City", "Category" }));
		hh.setJTableColumnsWidth(reg_table, 1500, 0, 0, 6, 7, 6, 28, 28, 15, 10);
		reg_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jSPane1.setViewportView(reg_table);
		jSPane1.getViewport().setBackground(hh.vvvzold);
		jSPane1.setBounds(20, 70, 1170, 280);
		jSPane1.setBorder(hh.borderf);
		ttpanel.add(jSPane1);

		reg_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				DefaultTableModel model = (DefaultTableModel) reg_table.getModel();
				DefaultTableModel d1 = (DefaultTableModel) link_table.getModel();
				try {
					int row = reg_table.getSelectedRow();
					if (row > -1) {
						String rid = model.getValueAt(row, 0).toString();
						d1.setRowCount(0);
						ResultSet rs = dd.getlinkrows(rid);
						link_table.setModel(DbUtils.resultSetToTableModel(rs));
						String[] fej = { "", "Attachment" };
						((DefaultTableModel) link_table.getModel()).setColumnIdentifiers(fej);
						hh.setJTableColumnsWidth(link_table, 520, 0, 100);
						dh.CloseConnection();
					}
				} catch (Exception e) {
					System.out.println("sql error!!!");
				}
			}
		});

		btnnew = hh.cbutton("New");
		btnnew.setBounds(100, 430, 130, 30);
		btnnew.setBackground(hh.vpiros1);
		ttpanel.add(btnnew);

		btnnew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				clearFields();
				cards.show(cardPanel, "edit");
				cmbway.setEnabled(true);
				cmbway.requestFocus();
			}
		});

		btnupdate = hh.cbutton("Update");
		btnupdate.setBounds(240, 430, 130, 30);
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
		btndelete.setBounds(380, 430, 130, 30);
		btndelete.setBackground(hh.vkek);
		ttpanel.add(btndelete);
		btndelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				data_delete();
			}
		});
		btnclip = new JButton();
		btnclip.setBounds(1130, 430, 40, 40);
		btnclip.setToolTipText("Attach documents");
		btnclip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnclip.setBorder(hh.myRaisedBorder);
		btnclip.setMargin(new Insets(10, 10, 10, 10));
		try {
			ImageIcon ImageIcon = new ImageIcon(ClassLoader.getSystemResource("Images/clip.png"));
			Image image = ImageIcon.getImage();
			btnclip.setIcon(new ImageIcon(image));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		ttpanel.add(btnclip);
		btnclip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {				
				DefaultTableModel d2 = (DefaultTableModel) reg_table.getModel();
				int row = reg_table.getSelectedRow();			
				if (row < 0) {
					return;
				}			
				String Rid = d2.getValueAt(row, 0).toString();				
				File sourceFile = hh.myfilechooser();
				if (sourceFile.length()==0 || !sourceFile.exists()) {
					return;
				}
				String ques = sourceFile.getAbsolutePath().toString();
				if (!ques.equals("")) {
					String filename = sourceFile.getName();
					String basePath = new File("").getAbsolutePath();
					File destFile = new File(basePath + "\\documents\\" + filename);
					try {
						hh.copyFile(sourceFile, destFile);
						String sql = "insert into documents (rid, dname, dpath) " + "values ('" + Rid + "','" + filename
								+ "','" + basePath + "\\documents\\" + "')";
						int flag = dh.Insupdel(sql);
						if (flag > 0) {
				     		int myid = dd.table_maxid("SELECT MAX(did) AS max_id from documents");
				    		DefaultTableModel d1 = (DefaultTableModel) link_table.getModel();
						    d1.insertRow(d1.getRowCount(), new Object[] { myid, filename });
						    hh.gotolastrow(link_table);
						    }						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		link_table = hh.ltable();
		link_table.setModel(
				new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "", "Attachments" }));
		hh.setJTableColumnsWidth(link_table, 520, 0, 100);
		jSPane4 = new JScrollPane(link_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jSPane4.setViewportView(link_table);
		jSPane4.setBounds(560, 380, 520, 170);
		ttpanel.add(jSPane4);
		link_table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				linktableMouseClicked(evt);
			}
		});
		return ttpanel;
	}

	private void linktableMouseClicked(java.awt.event.MouseEvent evt) {
		int row = link_table.getSelectedRow();
		if (row >= 0) {
			String filename = link_table.getValueAt(row, 1).toString();
			if(!hh.zempty(filename)) {
			String did = link_table.getValueAt(row, 0).toString();
			hh.new linkPopupMenu(filename, link_table, did, row);
			}
		}
	}

	private JPanel makeepanel() {
		JPanel eepanel = new JPanel(null);
		eepanel.setBackground(hh.neonzold);
		eepanel.setBounds(0, 52, 1200, 560);

		lbregnum = hh.clabel("Registration number");
		lbregnum.setBounds(20, 20, 170, 25);
		eepanel.add(lbregnum);

		txregnumber = cTextField(15);
		txregnumber.setBounds(200, 20, 150, 25);
		txregnumber.setEnabled(false);
		txregnumber.setFocusable(false);
		eepanel.add(txregnumber);

		lbway = hh.clabel("Direction");
		lbway.setBounds(360, 20, 80, 25);
		eepanel.add(lbway);

		cmbway = hh.cbcombo();
		cmbway.setModel(new DefaultComboBoxModel(new String[] { "In", "Out" }));
		cmbway.setBounds(450, 20, 60, 25);
		eepanel.add(cmbway);
		cmbway.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String sg = String.valueOf(cmbway.getSelectedItem());
					if (sg == "In") {
						lbrdate.setText("Receive date");
					} else {
						lbrdate.setText("Sending date");
					}
				}
			}
		});

		lbrdate = hh.clabel("Receive date");
		lbrdate.setBounds(520, 20, 110, 25);
		eepanel.add(lbrdate);

		receivedate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		receivedate.setDateFormatString("yyyy-MM-dd");
		receivedate.setFont(new Font("Arial", Font.BOLD, 16));
		receivedate.setBounds(640, 20, 150, 25);
		eepanel.add(receivedate);

		lbposting = hh.clabel("Posting date");
		lbposting.setBounds(800, 20, 110, 25);
		eepanel.add(lbposting);

		postingdate.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		postingdate.setDateFormatString("yyyy-MM-dd");
		postingdate.setFont(new Font("Arial", Font.BOLD, 16));
		postingdate.setBounds(920, 20, 150, 25);
		eepanel.add(postingdate);

		btnpartner = new JButton();
		btnpartner.setBounds(1120, 10, 60, 42);
		btnpartner.setBorder(hh.myRaisedBorder);
		btnpartner.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnpartner.setToolTipText("Partner search");
		try {
			ImageIcon ImageIcon = new ImageIcon(ClassLoader.getSystemResource("Images/partner2.png"));
			Image image = ImageIcon.getImage();
			btnpartner.setIcon(new ImageIcon(image));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		eepanel.add(btnpartner);
		btnpartner.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Partners par = new Partners(myframe);
				par.setSize(1230, 670);
				par.setLayout(null);
				par.setLocationRelativeTo(null);
				par.setVisible(true);
			}
		});

		txpid = new JTextField(); // hidden textfield for partner's pid

		lbname = hh.clabel("Partner's name");
		lbname.setBounds(20, 70, 130, 25);
		eepanel.add(lbname);

		txname = cTextField(150);
		txname.setBounds(160, 70, 910, 25);
		txname.setEnabled(false);
		eepanel.add(txname);

		lbmailaddr = hh.clabel("Mailing address");
		lbmailaddr.setBounds(20, 120, 130, 25);
		eepanel.add(lbmailaddr);

		txaddress = cTextField(150);
		txaddress.setBounds(160, 120, 910, 25);
		txaddress.setEnabled(false);
		eepanel.add(txaddress);

		lbsendmode = hh.clabel("Arrive mode");
		lbsendmode.setBounds(20, 170, 130, 25);
		eepanel.add(lbsendmode);

		cmbsendmode = hh.cbcombo();
		cmbsendmode.setBounds(160, 170, 200, 25);
		cmbsendmode.setName("sendmode");
		eepanel.add(cmbsendmode);
		cmbsendmode.addFocusListener(cFocusListener);

		lbdoccat = hh.clabel("Document category");
		lbdoccat.setBounds(365, 170, 160, 25);
		eepanel.add(lbdoccat);

		cmbdoccat = hh.cbcombo();
		cmbdoccat.setBounds(535, 170, 250, 25);
		cmbdoccat.setName("doccat");
		eepanel.add(cmbdoccat);
		cmbdoccat.addFocusListener(cFocusListener);
		
		btnnewdoc = hh.cbutton("");
		btnnewdoc = new JButton();
		btnnewdoc.setBounds(790, 163, 40, 40);
		btnnewdoc.setBorder(hh.myRaisedBorder);
		btnnewdoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnnewdoc.setToolTipText("New document category");
			try {
				ImageIcon ImageIcon = new ImageIcon(ClassLoader.getSystemResource("Images/doccat_icon.png"));
				Image image = ImageIcon.getImage();
				btnnewdoc.setIcon(new ImageIcon(image));
			} catch (Exception ex) {
				System.out.println(ex);
			}
			 eepanel.add(btnnewdoc);
			 btnnewdoc.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						Codetables cod = new Codetables("D", myframe);  	
						cod.setSize(700, 410);
						cod.setLayout(null);
						cod.setLocationRelativeTo(null);
						cod.setVisible(true);					
					}
				});

		lbshiptype = hh.clabel("Type of shipment");
		lbshiptype.setBounds(835, 170, 145, 25);
		eepanel.add(lbshiptype);

		cmbshiptype = hh.cbcombo();
		cmbshiptype.setBounds(990, 170, 200, 25);
		cmbshiptype.setName("codes");
		eepanel.add(cmbshiptype);

		lbpartnerreg = hh.clabel("Partner's registration number");
		lbpartnerreg.setBounds(20, 220, 250, 25);
		eepanel.add(lbpartnerreg);

		txpartnerregnum = cTextField(150);
		txpartnerregnum.setBounds(280, 220, 200, 25);
		eepanel.add(txpartnerregnum);

		lbpartneradmi = hh.clabel("Partner's administrator");
		lbpartneradmi.setBounds(490, 220, 200, 25);
		eepanel.add(lbpartneradmi);

		txpartneradmi = cTextField(200);
		txpartneradmi.setBounds(700, 220, 367, 25);
		eepanel.add(txpartneradmi);

		lbsubject = hh.clabel("Subject");
		lbsubject.setBounds(20, 270, 130, 25);
		eepanel.add(lbsubject);

		txsubject = cTextField(200);
		txsubject.setBounds(160, 270, 910, 25);
		eepanel.add(txsubject);

		lbnote = hh.clabel("Note");
		lbnote.setBounds(20, 320, 130, 25);
		eepanel.add(lbnote);

		txanote = new JTextArea();
		txanote.setFont(new java.awt.Font("Monospaced", 1, 18));
		txanote.setLineWrap(true);
		txanote.setWrapStyleWord(true);
		txanote.setMargin(new Insets(5, 5, 5, 5));
		txanote.putClientProperty("caretAspectRatio", 0.1);
		txanote.setCaretColor(Color.RED);
		txanote.setBounds(160, 320, 640, 125);
		txanote.setBorder(hh.bb);
		eepanel.add(txanote);

		lbadministrator = hh.clabel("Administrator");
		lbadministrator.setBounds(810, 320, 130, 25);
		eepanel.add(lbadministrator);

		cmbadministrator = hh.cbcombo();
		cmbadministrator.setBounds(950, 320, 240, 25);
		cmbadministrator.setName("codes");
		eepanel.add(cmbadministrator);

		btnsave = hh.cbutton("Save");
		btnsave.setBounds(480, 500, 110, 30);
		btnsave.setBackground(hh.vpiros1);
		eepanel.add(btnsave);

		btnsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				savebuttrun();
			}
		});
		btncancel = hh.cbutton("Cancel");
		btncancel.setBackground(hh.zold);
		btncancel.setBounds(600, 500, 110, 30);
		eepanel.add(btncancel);
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
		DefaultTableModel d1 = (DefaultTableModel) reg_table.getModel();
		String direction = "";
		int adminid = 0;
		int sendid = 0;
		int catid = 0;
		int shipid = 0;

		if (cmbway.getSelectedItem() != null) {
			direction = String.valueOf(cmbway.getSelectedItem());
		}
		if (cmbadministrator.getSelectedItem() != null) {
			Item adm = (Item) cmbadministrator.getSelectedItem();
			adminid = adm.getId();
		}
		if (cmbsendmode.getSelectedItem() != null) {
			Item send = (Item) cmbsendmode.getSelectedItem();
			sendid = send.getId();
		}
		String doccat = "";
		if (cmbdoccat.getSelectedItem() != null) {
			Item cat = (Item) cmbdoccat.getSelectedItem();
			catid = cat.getId();
			doccat = cat.getName();
		}
		if (cmbshiptype.getSelectedItem() != null) {
			Item sh = (Item) cmbshiptype.getSelectedItem();
			shipid = sh.getId();
		}
		String recdate = ((JTextField) receivedate.getDateEditor().getUiComponent()).getText();
		String posdate = ((JTextField) postingdate.getDateEditor().getUiComponent()).getText();
		String pregnumber = txpartnerregnum.getText();
		String partneradmi = txpartneradmi.getText();
		String subject = txsubject.getText();
		String note = txanote.getText();
		String pid = txpid.getText();
		String regnumber = "";

		if (rvalidation(direction, recdate, catid, sendid) == false) {
			return;
		}
		String name = "";
		String postalcodeh = "";
		String addressm = "";
		String citym = "";
		String Countrym = "";
		ResultSet rs;
		try {
			rs = dd.getpartner(pid);
			while (rs.next()) {
				name = rs.getString("name");
				postalcodeh = rs.getString("postalcodem");
				addressm = rs.getString("addressm");
				citym = rs.getString("citym");
				Countrym = rs.getString("countrym");
			}
			dh.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rowid != "") {
			jel = "UP";
			sql = "update  reg_book set pid = '" + pid + "', receive_date = '" + recdate + "'," + "partner_regnum= '"
					+ pregnumber + "', doc_category= '" + catid + "'," + "send_date = '" + posdate + "',"
					+ " arrive_mode='" + sendid + "', subject='" + subject + "', package_type='" + shipid + "', "
					+ " note='" + note + "', partner_admin='" + partneradmi + "', administrator='" + adminid
					+ "' where rid = " + rowid;
		} else {
			regnumber = make_regnumber(direction);
			sql = "insert into reg_book (direction, regnumber, pid, receive_date, partner_regnum, doc_category,"
					+ " send_date, arrive_mode, subject, package_type, note, administrator, partner_admin ) values ('"
					+ direction + "','" + regnumber + "','" + pid + "','" + recdate + "','" + pregnumber + "','" + catid
					+ "','" + posdate + "','" + sendid + "','" + subject + "','" + shipid + "','" + note + "','"
					+ adminid + "','" + partneradmi + "')";

		}
		try {
			int flag = dh.Insupdel(sql);
			if (flag == 1) {
				hh.ztmessage("Success", "Message");
				cmbway.setEnabled(true);
				if (rowid == "") {
					int myid = dd.table_maxid("SELECT MAX(rid) AS max_id FROM reg_book");
					d1.insertRow(d1.getRowCount(),
							new Object[] { myid, pid, direction, regnumber, recdate, name, addressm, citym, doccat });
					hh.gotolastrow(reg_table);
					if (reg_table.getRowCount() > 0) {
						int row = reg_table.getRowCount() - 1;
						reg_table.setRowSelectionInterval(row, row);
					}
				} else {
					d1.setValueAt(pid, myrow, 1);
					d1.setValueAt(direction, myrow, 2);
					d1.setValueAt(regnumber, myrow, 3);
					d1.setValueAt(recdate, myrow, 4);
					d1.setValueAt(name, myrow, 5);
					d1.setValueAt(addressm, myrow, 6);
					d1.setValueAt(citym, myrow, 7);
					d1.setValueAt(doccat, myrow, 8);
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

	private String make_regnumber(String direction) {
		String regnumber = "";
		try {
			regnumber = dd.getregszam(direction);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regnumber;
	}

	private void data_update() throws SQLException {
		DefaultTableModel d1 = (DefaultTableModel) reg_table.getModel();
		int row = reg_table.getSelectedRow();
		myrow = 0;
		if (row < 0) {
			rowid = "";
			row = 0;
		} else {
			myrow = row;
			rowid = d1.getValueAt(row, 0).toString();
			ResultSet rs = dd.getregbook(rowid);
			while (rs.next()) {
				try {
					String dd = rs.getString("receive_date");
					Date date;
					if (!hh.zempty(dd)) {
						date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
						receivedate.setDate(date);
					} else {
						receivedate.setCalendar(null);
					}
					dd = rs.getString("send_date");
					if (!hh.zempty(dd)) {
						date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
						postingdate.setDate(date);
					} else {
						postingdate.setCalendar(null);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				txname.setText(rs.getString("name"));
				String postalcodem = rs.getString("postalcodem");
				String addressm = rs.getString("addressm");
				String citym = rs.getString("citym");
				String countrym = rs.getString("countrym");
				txaddress.setText(postalcodem + " " + addressm + " " + citym + " " + countrym);
				txregnumber.setText(rs.getString("regnumber"));
				txpid.setText(rs.getString("pid"));
				txpartnerregnum.setText(rs.getString("partner_regnum"));
				txpartneradmi.setText(rs.getString("partner_admin"));
				txsubject.setText(rs.getString("subject"));
				txanote.setText(rs.getString("note"));
				cmbway.setSelectedItem(rs.getString("direction"));
				String sg = rs.getString("direction");
				if (sg == "In") {
					lbrdate.setText("Receive date");
				} else {
					lbrdate.setText("Sending date");
				}

				String cnum = rs.getString("doc_category");
				int number = 0;
				if (!hh.zempty(cnum)) {
					number = Integer.parseInt(cnum);
				}
				hh.setSelectedValue(cmbdoccat, number);
				cmbdoccat.updateUI();
				cnum = rs.getString("arrive_mode");
				number = 0;
				if (!hh.zempty(cnum)) {
					number = Integer.parseInt(cnum);
				}
				hh.setSelectedValue(cmbsendmode, number);
				cmbsendmode.updateUI();
				cnum = rs.getString("package_type");
				number = 0;
				if (!hh.zempty(cnum)) {
					number = Integer.parseInt(cnum);
				}
				hh.setSelectedValue(cmbshiptype, number);
				cmbshiptype.updateUI();

				cnum = rs.getString("administrator");
				number = 0;
				if (!hh.zempty(cnum)) {
					number = Integer.parseInt(cnum);
				}
				hh.setSelectedValue(cmbadministrator, number);
				cmbadministrator.updateUI();
			}
			dh.CloseConnection();
		}
		cards.show(cardPanel, "edit");
		cmbway.setEnabled(false);
		receivedate.requestFocus(true);
	}

	private int data_delete() {
		Boolean error = false;
		String sql = "delete from reg_book  where rid =";
		int flag = 0;
		DefaultTableModel d1 = (DefaultTableModel) reg_table.getModel();
		int sIndex = reg_table.getSelectedRow();
		if (sIndex < 0) {
			return flag;
		}
		String iid = d1.getValueAt(sIndex, 0).toString();
		if (iid.equals("")) {
			return flag;
		}
		if (dd.cannotdelete("select rid from documents  where rid =" + iid) == true) {
			JOptionPane.showMessageDialog(null, "You can not delete this  record !");
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

	private Boolean rvalidation(String direction, String recdate, int catid, int sendid) {
		Boolean ret = true;
		ArrayList<String> err = new ArrayList<String>();

		if (!allv.directionvalid(direction)) {
			err.add(allv.mess);
			ret = false;
		}
		if (!allv.datevalid(recdate)) {
			err.add(allv.mess);
			ret = false;
		}
		if (!allv.catidvalid(catid)) {
			err.add(allv.mess);
			ret = false;
		}
		if (!allv.sendidvalid(sendid)) {
			err.add(allv.mess);
			ret = false;
		}
		if (err.size() > 0) {
			JOptionPane.showMessageDialog(null, err.toArray(), "Error message", 1);
		}
		return ret;
	}

	private void clearFields() {
		receivedate.setCalendar(null);
		postingdate.setCalendar(null);
		txregnumber.setText("");
		txname.setText("");
		txaddress.setText("");
		txpartnerregnum.setText("");
		txpartneradmi.setText("");
		txsubject.setText("");
		txanote.setText("");
		txpid.setText("");
		cmbsendmode.setSelectedIndex(-1);
		cmbdoccat.setSelectedIndex(-1);
		cmbshiptype.setSelectedIndex(-1);
		cmbadministrator.setSelectedIndex(-1);
	}

	private JPanel makegepanel() {
		JPanel gepanel = new JPanel(null);
		gepanel.setBackground(hh.neonzold);
		gepanel.setBounds(0, 52, 1200, 560);

		JPanel spanel = new JPanel(null);
		spanel.setBounds(100, 5, 1000, 170);
		spanel.setBackground(hh.neonzold);
		spanel.setBorder(hh.borderf);
		gepanel.add(spanel);

		lbqname = hh.clabel("Partner's name");
		lbqname.setBounds(10, 20, 130, 25);
		spanel.add(lbqname);

		txqname = cTextField(25);
		txqname.setBounds(150, 20, 200, 25);
		spanel.add(txqname);

		lbqdirection = hh.clabel("Direction");
		lbqdirection.setBounds(360, 20, 80, 25);
		spanel.add(lbqdirection);

		cmbqway = hh.cbcombo();
		cmbqway.setModel(new DefaultComboBoxModel(new String[] { " ", "In", "Out" }));
		cmbqway.setBounds(450, 20, 60, 25);
		spanel.add(cmbqway);

		lbqdoccat = hh.clabel("Documentum category");
		lbqdoccat.setBounds(520, 20, 200, 25);
		spanel.add(lbqdoccat);

		cmbqdoccat = hh.cbcombo();
		cmbqdoccat.setBounds(730, 20, 250, 25);
		cmbqdoccat.setName("codes");
		spanel.add(cmbqdoccat);

		lbqpartneradm = hh.clabel("Partner's administrator");
		lbqpartneradm.setBounds(60, 70, 200, 25);
		spanel.add(lbqpartneradm);

		txqpartneradm = cTextField(25);
		txqpartneradm.setBounds(270, 70, 200, 25);
		spanel.add(txqpartneradm);

		lbqpartnerreg = hh.clabel("Partner's registration number");
		lbqpartnerreg.setBounds(480, 70, 250, 25);
		spanel.add(lbqpartnerreg);

		txqpartnerregnum = cTextField(150);
		txqpartnerregnum.setBounds(740, 70, 200, 25);
		spanel.add(txqpartnerregnum);

		lbqsubject = hh.clabel("Subject");
		lbqsubject.setBounds(10, 120, 70, 25);
		spanel.add(lbqsubject);

		txqsubject = cTextField(150);
		txqsubject.setBounds(90, 120, 200, 25);
		spanel.add(txqsubject);

		lbqsenddat = hh.clabel("Send/receive date");
		lbqsenddat.setBounds(300, 120, 150, 25);
		spanel.add(lbqsenddat);

		qdate1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		qdate1.setDateFormatString("yyyy-MM-dd");
		qdate1.setFont(new Font("Arial", Font.BOLD, 16));
		qdate1.setBounds(460, 120, 130, 25);
		spanel.add(qdate1);
		qdate1.setCalendar(null);

		lbmark = hh.clabel(" - ");
		lbmark.setFont(new Font("Tahoma", 1, 18));
		lbmark.setBounds(600, 120, 20, 25);
		spanel.add(lbmark);

		qdate2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY));
		qdate2.setDateFormatString("yyyy-MM-dd");
		qdate2.setFont(new Font("Arial", Font.BOLD, 16));
		qdate2.setBounds(630, 120, 120, 25);
		spanel.add(qdate2);
		qdate2.setCalendar(null);

		btnqsearch = hh.cbutton("Filter");
		btnqsearch.setForeground(Color.black);
		btnqsearch.setBackground(Color.ORANGE);
		btnqsearch.setBounds(780, 120, 90, 25);
		btnqsearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		spanel.add(btnqsearch);
		btnqsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sqlqgyart();
			}
		});

		btnqclear = hh.cbutton("Clear");
		btnqclear.setForeground(Color.black);
		btnqclear.setBackground(Color.ORANGE);
		btnqclear.setBounds(880, 120, 90, 25);
		btnqclear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		spanel.add(btnqclear);
		btnqclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearFieldsq();
			}
		});

		btnsendto = hh.cbutton("Send to Registration");
		btnsendto.setBounds(1000, 190, 190, 30);
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
				sea_table.scrollRectToVisible(sea_table.getCellRect(sea_table.getRowCount() - 1, 0, true));
			}
		});
		sea_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jSPane3 = new JScrollPane(sea_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		sea_table.setModel(
				new javax.swing.table.DefaultTableModel(new Object[][] { {} }, new String[] { "RID", "PID", "Direction",
						"Regnumber", "Date", "Name", "Address", "City", "Partn. reg", "Partner's admin", "Subject" }));
		hh.setJTableColumnsWidth(sea_table, 1500, 0, 0, 6, 9, 8, 20, 16, 10, 6, 12, 13);
		jSPane3.setViewportView(sea_table);
		jSPane3.getViewport().setBackground(hh.vvvzold);
		jSPane3.setBounds(20, 230, 1170, 250);
		jSPane3.setBorder(hh.borderf);
		gepanel.add(jSPane3);
		return gepanel;
	}

	private void clearFieldsq() {
		txqname.setText("");
		txqpartnerregnum.setText("");
		txqpartneradm.setText("");
		txqsubject.setText("");
		txname.requestFocus();
		cmbqway.setSelectedIndex(-1);
		cmbqdoccat.setSelectedIndex(-1);
		qdate1.setCalendar(null);
		qdate2.setCalendar(null);
		DefaultTableModel d1 = (DefaultTableModel) sea_table.getModel();
		d1.setRowCount(0);
	}

	private void sqlqgyart() {
		String name = txqname.getText().trim().toLowerCase();
		String partregnum = txqpartnerregnum.getText().trim().toLowerCase();
		String partneradm = txqpartneradm.getText().trim().toLowerCase();
		String subject = txqsubject.getText().trim().toLowerCase();
		String date1 = ((JTextField) qdate1.getDateEditor().getUiComponent()).getText();
		String date2 = ((JTextField) qdate2.getDateEditor().getUiComponent()).getText();
		String direction = "";
		String doccat = "";
		int catid = 0;
		if (cmbqdoccat.getSelectedItem() != null) {
			Item cat = (Item) cmbqdoccat.getSelectedItem();
			catid = cat.getId();
			doccat = cat.getName();
		}
		if (cmbqway.getSelectedItem() != null) {
			direction = String.valueOf(cmbqway.getSelectedItem());
		}

		String ww = " and ";

		String swhere = "";
		if (!hh.zempty(name)) {
			swhere = " lower(name)  like '%" + name + "%'" + ww;
		}
		if (!hh.zempty(partregnum)) {
			swhere += " lower(partner_regnum)  like '%" + partregnum + "%'" + ww;
		}
		if (!hh.zempty(partneradm)) {
			swhere += " lower(partner_admin)  like '%" + partneradm + "%'" + ww;
		}
		if (!hh.zempty(subject)) {
			swhere += " lower(subject)  like '%" + subject + "%'" + ww;
		}
		if (!hh.zempty(doccat)) {
			swhere += " doc_category =" + catid + ww;
		}
		if (!hh.zempty(direction)) {
			swhere += " direction  like '%" + direction + "%'" + ww;
		}
		if (!hh.zempty(date1) && !hh.zempty(date2) && hh.twodate(date1, date2) == true) {
			swhere += " receive_date >= date('" + date1 + "') and receive_date <=date('" + date2 + "')" + ww;
		}
		if (swhere.length() > 0) {
			swhere = swhere.substring(1, swhere.lastIndexOf(ww));
			dd.qregtable_update(sea_table, swhere);
		} else {
			JOptionPane.showMessageDialog(null, "Empty condition !", "Error", 1);
			return;
		}
	}

	private void sqlgyart() {
		String stext = txsearch.getText().trim().toLowerCase();
		String scmbtxt = String.valueOf(cmbsearch.getSelectedItem());
		String swhere = "";
		if (!hh.zempty(stext)) {
			if (scmbtxt == "Name") {
				swhere = " lower(p.name) like '%" + stext.trim() + "%'";
			} else if (scmbtxt == "Reg. number") {
				swhere = " lower(r.regnumber) like '%" + stext.trim() + "%' ";
			} else if (scmbtxt == "Doc. category") {
				swhere = " lower(c.name) like '%" + stext.trim() + "%' ";
			}
			dd.regtable_update(reg_table, swhere);
		} else {
			JOptionPane.showMessageDialog(null, "Empty condition !", "Error", 1);
			return;
		}
	}

	private final FocusListener cFocusListener = new FocusListener() {
		@Override
		public void focusGained(FocusEvent e) {
			JComponent c = (JComponent) e.getSource();
			c.setBorder(hh.borderz);
		}

		@Override
		public void focusLost(FocusEvent e) {
			boolean ret = true;
			JComponent b = (JComponent) e.getSource();
			JComboBox bb = (JComboBox) e.getSource();

			if (bb.getName() == "sendmode") {
				Item item = (Item) cmbsendmode.getSelectedItem();
				if (item == null) {
					ret = false;
				} else {
					int id = item.getId();
					ret = allv.sendidvalid(id);
				}

			} else if (bb.getName() == "doccat") {
				Item item = (Item) cmbdoccat.getSelectedItem();
				if (item == null) {
					ret = false;
				} else {
					int idd = item.getId();
					ret = allv.catidvalid(idd);
				}
			}
			if (ret == true) {
				b.setBorder(hh.borderf);
			} else {
				b.setBorder(hh.borderp);
			}
		}
	};

	private void sendtorow() {
		int flag = 0;
		DefaultTableModel d1 = (DefaultTableModel) sea_table.getModel();
		DefaultTableModel d2 = (DefaultTableModel) reg_table.getModel();
		int sIndex = sea_table.getSelectedRow();
		if (sIndex < 0) {
			return;
		}
		String rid = d1.getValueAt(sIndex, 0).toString();
		if (rid.equals("")) {
			return;
		}
		ResultSet rs;
		try {
			rs = dd.getregbook(rid);
			while (rs.next()) {
				String pid = rs.getString("pid");
				String direction = rs.getString("direction");
				String regnum = rs.getString("regnumber");
				String date = rs.getString("receive_date");
				String name = rs.getString("name");
				String address = rs.getString("addressm");
				String city = rs.getString("citym");
				String category = rs.getString("category");
				d2.setRowCount(0);
				d2.insertRow(0, new Object[] { rid, pid, direction, regnum, date, name, address, city, category });
			}
			dh.CloseConnection();
			clearFieldsq();
			cards.show(cardPanel, "tabla");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void passtocmbc(int number) {
		dd.itemcombofill(cmbdoccat, "D");	
		hh.setSelectedValue(cmbdoccat, number);
		cmbdoccat.updateUI();
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
		Registrationbook reg = new Registrationbook();
		reg.setSize(1230, 670);
		reg.setLayout(null);
		reg.setLocationRelativeTo(null);
		reg.setVisible(true);
	}

	CardLayout cards;
	JPanel cardPanel, tPanel, ePanel, fejpanel, lPanel, gePanel;
	JScrollPane jSPane1, jSPane2, jSPane3, jSPane4;
	JTable reg_table, sea_table, link_table;
	JComboBox cmbsearch, cmbway, cmbsendmode, cmbdoccat, cmbshiptype, cmbadministrator;
	JComboBox cmbqdoccat, cmbqway;
	JLabel lbheader, lbsearch, lbregnum, lbway, lbrdate, lbposting, lbname, lbmailaddr, lbsendmode, lbdoccat,
			lbshiptype, lbpartnerreg, lbpartneradmi, lbsubject, lbnote, lbadministrator, lbmark;
	JLabel lbqname, lbqdirection, lbqdoccat, lbqsenddat, lbqpartneradm, lbqpartnerreg, lbqsubject;
	JTextField txqname, txqpartnerregnum, txqpartneradm, txqsubject;
	Container cp;
	JButton btnnew, btndelete, btnupdate, btnsave, btncancel, btnsendto, btnclear, btnsearch, btnpartner, btnaddrcopy,
			btnesearch, btnclip, btnqsearch, btnqclear, btnnewdoc;
	JTextField txsearch, txregnumber, txreceive_date, txname, txaddress, txpartnerregnum, txpartneradmi, txsubject,
			txpid;
	JTextArea txanote;

}
