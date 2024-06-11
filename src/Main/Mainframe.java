package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

import Classes.Hhelper;

public class Mainframe extends JFrame {
	Hhelper hh = new Hhelper();
	Color sblue = new Color(71, 80, 111);
	Color dblue = new Color(52, 72, 95);
	Color vblue = new Color(128, 156, 183);
	Color vorange = new Color(251, 125, 15);
	Color sorange = new Color(234, 93, 3);
	Color korange = new Color(236, 161, 0);
	Color bourdon = new Color(188, 14, 33);
	Color vbourdon = new Color(207, 25, 9);
	Color yellow = new Color(254, 196, 1);
	Color pink = new Color(215, 168, 142);
	Color black = new Color(3, 18, 38);

	Mainframe() {
		init();	
		hh.iconhere(this);
		 ifdirectory();
	}

	private void init() {
		lbheader = new JLabel();
		lbheader.setFont(new Font("Footlight MT Light", 1, 36));
		lbheader.setForeground(yellow);
		lbheader.setText("DOCUMENT REGISTRATION BOOK");
		lbheader.setBounds(380, 40, 600, 50);
		add(lbheader);

		cp = getContentPane();
		cp.setBackground(dblue);

		lbregist = new JLabel();
		lbregist.setBounds(440, 120, 200, 200);
		lbregist.setBorder(hh.borderf2);
		lbregist.setIcon(new ImageIcon(ClassLoader.getSystemResource("Images/registration.png")));
		add(lbregist);

		btnregist = xbutton("Registration");
		btnregist.setBounds(440, 340, 200, 35);
		add(btnregist);
		btnregist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrationbook ob = new Registrationbook();
				ob.setSize(1230, 670);
				ob.setLayout(null);
				ob.setLocationRelativeTo(null);
				ob.setVisible(true);
				// dispose();
			}
		});

		lbpartner = new JLabel();
		lbpartner.setBounds(660, 120, 200, 200);
		lbpartner.setBorder(hh.borderf2);
		lbpartner.setIcon(new ImageIcon(ClassLoader.getSystemResource("Images/partner4.png")));
		add(lbpartner);

		btnpartner = xbutton("Partners");
		btnpartner.setBounds(660, 340, 200, 35);
		add(btnpartner);
		btnpartner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Partners ob = new Partners();
				ob.setSize(1230, 670);
				ob.setLayout(null);
				ob.setLocationRelativeTo(null);
				ob.setVisible(true);
				// dispose();
			}
		});

		lbadmin = new JLabel();
		lbadmin.setBounds(120, 410, 200, 200);
		lbadmin.setBorder(hh.borderf2);
		lbadmin.setIcon(new ImageIcon(ClassLoader.getSystemResource("Images/admin.png")));
		add(lbadmin);

		btnadmin = xbutton("Administrators");
		btnadmin.setBounds(120, 630, 200, 35);
		add(btnadmin);
		btnadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Codetables ob = new Codetables("A");
				ob.setSize(700, 410);
				ob.setLayout(null);
				ob.setLocationRelativeTo(null);
				ob.setVisible(true);
				// dispose();
			}
		});

		lbarrivemode = new JLabel();
		lbarrivemode.setBounds(340, 410, 200, 200);
		lbarrivemode.setBorder(hh.borderf2);
		lbarrivemode.setIcon(new ImageIcon(ClassLoader.getSystemResource("Images/arrivemode.png")));
		add(lbarrivemode);

		btnarrivemode = xbutton("Send modes");
		btnarrivemode.setBounds(340, 630, 200, 35);
		add(btnarrivemode);

		btnarrivemode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Codetables ob = new Codetables("S");
				ob.setSize(700, 410);
				ob.setLayout(null);
				ob.setLocationRelativeTo(null);
				ob.setVisible(true);
				// dispose();
			}
		});

		lbdoccat = new JLabel();
		lbdoccat.setBounds(550, 410, 200, 200);
		lbdoccat.setBorder(hh.borderf2);
		lbdoccat.setIcon(new ImageIcon(ClassLoader.getSystemResource("Images/doccat.png")));
		add(lbdoccat);

		btndoccat = xbutton("Document category");
		btndoccat.setBounds(550, 630, 200, 35);
		add(btndoccat);

		btndoccat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Codetables ob = new Codetables("D");
				ob.setSize(700, 410);
				ob.setLayout(null);
				ob.setLocationRelativeTo(null);
				ob.setVisible(true);
				// dispose();
			}
		});

		lbshipment = new JLabel();
		lbshipment.setBounds(760, 410, 200, 200);
		lbshipment.setBorder(hh.borderf2);
		lbshipment.setIcon(new ImageIcon(ClassLoader.getSystemResource("Images/shipment.png")));
		add(lbshipment);

		btnshipment = xbutton("Types of shipment");
		btnshipment.setBounds(760, 630, 200, 35);
		add(btnshipment);

		btnshipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Codetables ob = new Codetables("C");
				ob.setSize(700, 410);
				ob.setLayout(null);
				ob.setLocationRelativeTo(null);
				ob.setVisible(true);
				// dispose();
			}
		});

		lbsettings = new JLabel();
		lbsettings.setBounds(970, 410, 200, 200);
		lbsettings.setBorder(hh.borderf2);
		lbsettings.setIcon(new ImageIcon(ClassLoader.getSystemResource("Images/settings.png")));
		add(lbsettings);

		btnsettings = xbutton("Settings");
		btnsettings.setBounds(970, 630, 200, 35);
		add(btnsettings);
		btnsettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings ob = new Settings();
				ob.setSize(950, 500);
				ob.setLayout(null);
				ob.setLocationRelativeTo(null);
				ob.setVisible(true);
				// dispose();
			}
		});

		btnexit = xbutton("");
		// btnexit.setBounds(1240, 3, 50, 50);
		btnexit.setBounds(1180, 20, 50, 50);
		btnexit.setIcon(new ImageIcon(ClassLoader.getSystemResource("Images/exit.png")));
		add(btnexit);

		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x, y, d;
				x = 1000;
				y = 600;
				d = 10;
				while (x > 0 && y > 0) {
					setSize(x, y);
					x = x - 2 * d;
					y = y - d;
					setVisible(true);
					try {
						Thread.sleep(10);
					} catch (Exception e1) {
						System.out.println("Error:" + e1);
					}
				}
				dispose();
			}
		});
	}

	private void ifdirectory() {
		String basePath = new File("").getAbsolutePath();
		String dirName = basePath + "\\documents\\";
		File theDir = new File(dirName);
		if (!theDir.exists())
			theDir.mkdirs();
	}

	public JButton xbutton(String string) {
		JButton bbutton = new JButton(string);
		bbutton.setBorder(hh.myRaisedBorder);
		bbutton.setForeground(sblue);
		bbutton.setBackground(yellow);
		bbutton.setFont(new Font("Tahoma", Font.BOLD, 18));
		bbutton.setPreferredSize(new Dimension(100, 30));
		bbutton.setMargin(new Insets(10, 10, 10, 10));
		bbutton.setFocusable(false);
		bbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return bbutton;
	}

	public static void main(String args[]) {
		Mainframe Main = new Mainframe();
		Main.setUndecorated(true);
		Main.setSize(1290, 720);
		Main.setLayout(null);
		Main.setLocationRelativeTo(null);
		Main.setShape(new RoundRectangle2D.Double(10, 10, 1270, 700, 50, 50));
		Main.setVisible(true);
	}

	Container cp;
	JLabel lbadmin, lbarrivemode, lbdoccat, lbshipment, lbpartner, lbregist, lbsettings, lbheader;
	JButton btnadmin, btnarrivemode, btndoccat, btnshipment, btnpartner, btnregist, btnsettings, btnexit;

}
