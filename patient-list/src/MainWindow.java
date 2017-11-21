import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;

public class MainWindow {
	private JFrame frmPatientList;
	private JTextField txtName;
	private JTextField txtPhoneNumber;
	private JTextField txtEmail;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmList;
	private JMenuItem mntmNewPatient;
	private JMenuItem mntmAbout;
	private JMenuItem mntmExit;
	private JPanel panel;
	private JLabel lblPatientList;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JComboBox comboBox = new JComboBox();
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnNewAppointment;
	private JButton btnAdmit;
	private JButton btnRelease;
	private JLabel lblPatientName;
	private JLabel lblPatientPhoneNumber;
	private JLabel lblPatientEmail;
	private JLabel lblNumberOfAppointments;
	private JLabel lblNumberOfAdmissions;
	private JLabel lblIsOnAdmission;
	private JPanel panel_1;
	private JLabel lblNewPatient;
	private JLabel lblName;
	private JLabel lblPhoneNumber;
	private JLabel lblEmail;
	private JLabel lblAddress;
	private JButton btnAddPatient;
	private JButton btnCancel;
	private JTextArea txtAddress;
	private JTextArea txaPatientAddress;
	private iPatientArrayList paList;
	private DefaultComboBoxModel model;
	private String[] cbItems;
	final static String PATIENT_LIST = "PATIENT_LIST";
	final static String NEW_PATIENT = "NEW_PATIENT";
	private int patientIndex = 0;
	private int patientCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmPatientList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public MainWindow() throws RemoteException, MalformedURLException, NotBoundException {
		String url = "rmi:///";
		this.paList = (iPatientArrayList) Naming.lookup(url + "patient_array_list");
		initialize();
		if (patientCount > 0) {
			comboBox.setSelectedIndex(0);
			enableListPageButtons();
		} else
			disableListPageButtons()
		;
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws RemoteException 
	 */
	private void initialize() throws RemoteException {
		frmPatientList = new JFrame();
		frmPatientList.setTitle("Patient List");
		frmPatientList.setBounds(100, 100, 460, 500);
		frmPatientList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPatientList.getContentPane().setLayout(new CardLayout(0, 0));
		
		menuBar = new JMenuBar();
		frmPatientList.setJMenuBar(menuBar);
		
		mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		mntmList = new JMenuItem("List");
		
		mntmList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CardLayout cl = (CardLayout) frmPatientList.getContentPane().getLayout();
					
					reloadComboBox();
					if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
					cl.show(frmPatientList.getContentPane(), PATIENT_LIST);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		mnMenu.add(mntmList);
		
		mntmNewPatient = new JMenuItem("New Patient");
		
		mntmNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) frmPatientList.getContentPane().getLayout();
				cl.show(frmPatientList.getContentPane(), NEW_PATIENT);
			}
		});
		
		mnMenu.add(mntmNewPatient);
		
		mntmExit = new JMenuItem("Exit");
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		mntmAbout = new JMenuItem("About");
		
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					frmPatientList, 
					"Patient List App\nCreated by Kingson Chinedu Odogwu\nNovember, 2017", "Information", 
					JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		
		mnMenu.add(mntmAbout);
		
		mnMenu.add(mntmExit);
		
		panel = new JPanel();
		frmPatientList.getContentPane().add(panel, PATIENT_LIST);
		panel.setLayout(null);
		
		lblPatientList = new JLabel("Patient List");
		lblPatientList.setBounds(152, 11, 147, 37);
		lblPatientList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblPatientList);
		
		lbl1 = new JLabel("Patient Number:");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl1.setBounds(33, 102, 122, 14);
		panel.add(lbl1);
		
		lbl2 = new JLabel("Patient Name:");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl2.setBounds(33, 127, 106, 14);
		panel.add(lbl2);
		
		lbl3 = new JLabel("Number of appointments:");
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl3.setBounds(33, 286, 171, 14);
		panel.add(lbl3);
		
		lbl4 = new JLabel("Number of admissions:");
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl4.setBounds(33, 313, 156, 14);
		panel.add(lbl4);
		
		lbl5 = new JLabel("Currently Admitted");
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl5.setBounds(33, 339, 139, 14);
		panel.add(lbl5);

		reloadComboBox();
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JComboBox cb = (JComboBox) e.getSource();
					
					patientIndex = cb.getSelectedIndex();
					if (patientCount > 0) showPatientDetails(patientIndex);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});

		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(205, 99, 106, 20);
		panel.add(comboBox);
		
		btnPrevious = new JButton("<< Previous");
		btnPrevious.setEnabled(false);
		
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (patientIndex - 1 > -1) {
					--patientIndex;
					if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
					btnNext.setEnabled(true);
					if (patientIndex == 0) btnPrevious.setEnabled(false);
				}
			}
		});
		
		btnPrevious.setBounds(66, 54, 106, 23);
		panel.add(btnPrevious);
		
		btnNext = new JButton("Next >>");
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (patientIndex + 1 < patientCount) {
					++patientIndex;
					if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
					btnPrevious.setEnabled(true);
					if (patientIndex == patientCount - 1) btnNext.setEnabled(false);
				}
			}
		});
		
		btnNext.setBounds(260, 54, 89, 23);
		panel.add(btnNext);
		
		btnNewAppointment = new JButton("New appointment");
		
		btnNewAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Patient aPatient;
					
					patientIndex = comboBox.getSelectedIndex();
					aPatient = paList.getPatient(patientIndex);
					aPatient.newAppointment();
					paList.editPatientInformation(patientIndex, aPatient);
					if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewAppointment.setBounds(10, 382, 135, 23);
		panel.add(btnNewAppointment);
		
		btnAdmit = new JButton("Admit");
		
		btnAdmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Patient aPatient;
					
					patientIndex = comboBox.getSelectedIndex();
					aPatient = paList.getPatient(patientIndex);
					aPatient.admit();
					paList.editPatientInformation(patientIndex, aPatient);
					if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnAdmit.setBounds(154, 382, 135, 23);
		panel.add(btnAdmit);
		
		btnRelease = new JButton("Release");
		
		btnRelease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Patient aPatient;
				
					patientIndex = comboBox.getSelectedIndex();
					aPatient = paList.getPatient(patientIndex);
					aPatient.release();
					paList.editPatientInformation(patientIndex, aPatient);
					if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnRelease.setBounds(299, 382, 135, 23);
		panel.add(btnRelease);
		
		lblPatientName = new JLabel("-");
		lblPatientName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatientName.setBounds(205, 127, 211, 20);
		panel.add(lblPatientName);
		
		lblNumberOfAppointments = new JLabel("-");
		lblNumberOfAppointments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfAppointments.setBounds(205, 288, 106, 14);
		panel.add(lblNumberOfAppointments);
		
		lblNumberOfAdmissions = new JLabel("-");
		lblNumberOfAdmissions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfAdmissions.setBounds(205, 315, 106, 14);
		panel.add(lblNumberOfAdmissions);
		
		lblIsOnAdmission = new JLabel("-");
		lblIsOnAdmission.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsOnAdmission.setBounds(205, 339, 106, 14);
		panel.add(lblIsOnAdmission);
		
		JLabel lbl6 = new JLabel("Phone Number:");
		lbl6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl6.setBounds(33, 152, 171, 14);
		panel.add(lbl6);
		
		lblPatientPhoneNumber = new JLabel("-");
		lblPatientPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatientPhoneNumber.setBounds(205, 154, 211, 20);
		panel.add(lblPatientPhoneNumber);
		
		JLabel lbl7 = new JLabel("Email:");
		lbl7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl7.setBounds(33, 177, 171, 14);
		panel.add(lbl7);
		
		lblPatientEmail = new JLabel("-");
		lblPatientEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatientEmail.setBounds(205, 179, 211, 20);
		panel.add(lblPatientEmail);
		
		JLabel lbl8 = new JLabel("Address:");
		lbl8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl8.setBounds(33, 209, 171, 14);
		panel.add(lbl8);
		
		txaPatientAddress = new JTextArea();
		txaPatientAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txaPatientAddress.setText("-");
		txaPatientAddress.setBackground(UIManager.getColor("Button.background"));
		txaPatientAddress.setEditable(false);
		txaPatientAddress.setLineWrap(true);
		txaPatientAddress.setBounds(205, 210, 216, 55);
		panel.add(txaPatientAddress);
		
		panel_1 = new JPanel();
		frmPatientList.getContentPane().add(panel_1, NEW_PATIENT);
		panel_1.setLayout(null);
		
		lblNewPatient = new JLabel("New Patient");
		lblNewPatient.setBounds(137, 11, 160, 37);
		lblNewPatient.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_1.add(lblNewPatient);
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(34, 73, 61, 14);
		panel_1.add(lblName);
		
		lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNumber.setBounds(34, 112, 96, 14);
		panel_1.add(lblPhoneNumber);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(34, 152, 44, 14);
		panel_1.add(lblEmail);
		
		lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(34, 200, 61, 14);
		panel_1.add(lblAddress);
		
		btnAddPatient = new JButton("Add patient");
		
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean flag;
					
					if (txtName.getText().equals("") || txtPhoneNumber.getText().equals("") || txtEmail.getText().equals("") || txtAddress.getText().equals("")) {
						JOptionPane.showMessageDialog(
							frmPatientList, 
							"You must supply a patient's name, \nphone number, email, and address \nbefore you can add \nthe new patient record.", 
							"Error message", 
							JOptionPane.ERROR_MESSAGE
						);
					} else {
						flag = paList.addPatient(
							txtName.getText(), 
							txtPhoneNumber.getText(), 
							txtEmail.getText(), 
							txtAddress.getText()
						);
						
						if (!flag) {
							JOptionPane.showMessageDialog(
								frmPatientList, 
								"A problem occured while creating \nthe patient's record. Please contact \nthe IT administrator.", 
								"Error message", 
								JOptionPane.ERROR_MESSAGE
							);
						}
						
						txtName.setText("");
						txtPhoneNumber.setText("");
						txtEmail.setText("");
						txtAddress.setText("");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnAddPatient.setBounds(61, 368, 123, 23);
		panel_1.add(btnAddPatient);
		
		btnCancel = new JButton("Cancel");
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CardLayout cl = (CardLayout) frmPatientList.getContentPane().getLayout();
					
					reloadComboBox();
					if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
					cl.show(frmPatientList.getContentPane(), PATIENT_LIST);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnCancel.setBounds(244, 368, 123, 23);
		panel_1.add(btnCancel);
		
		txtName = new JTextField();
		txtName.setBounds(166, 72, 216, 20);
		panel_1.add(txtName);
		txtName.setColumns(10);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(166, 111, 216, 20);
		panel_1.add(txtPhoneNumber);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(166, 151, 216, 20);
		panel_1.add(txtEmail);
		
		txtAddress = new JTextArea();
		txtAddress.setLineWrap(true);
		txtAddress.setBounds(166, 197, 216, 143);
		panel_1.add(txtAddress);
		
		patientCount = paList.getLength();
	}

	public void showPatientDetails(int index) throws RemoteException {
		Patient aPatient = paList.getPatient(index);
		String name = aPatient.getName();
		String phoneNumber = aPatient.getPhoneNumber();
		String email = aPatient.getEmail();
		String address = aPatient.getAddress();
		String appointments = aPatient.getAppointments() + "";
		String admissions = aPatient.getAdmissions() + "";
		String admissionStatus = aPatient.getAdmissionStatus();
		
		lblPatientName.setText(name);
		lblPatientPhoneNumber.setText(phoneNumber);
		lblPatientEmail.setText(email);
		txaPatientAddress.setText(address);
		lblNumberOfAppointments.setText(appointments);
		lblNumberOfAdmissions.setText(admissions);
		lblIsOnAdmission.setText(admissionStatus);
		
		if (patientCount > 0)
			enableListPageButtons()
		; else
			disableListPageButtons()
		;
		
		if (patientIndex == 0) 
			btnPrevious.setEnabled(false)
		; else
			btnPrevious.setEnabled(true)
		;
		
		if (patientIndex == patientCount - 1) 
			btnNext.setEnabled(false)
		; else
			btnNext.setEnabled(true)
		;
	}

	public void reloadComboBox() throws RemoteException {
		patientCount = paList.getLength();
		cbItems = new String[patientCount];
		
		for (int i = 0; i < paList.getLength(); i++) {
			cbItems[i] = paList.getPatient(i).getPatientNumber();
		}
		
		model = new DefaultComboBoxModel(cbItems);
		comboBox.setModel(model);
	}
	
	public void disableListPageButtons() {
		btnPrevious.setEnabled(false);
		btnNext.setEnabled(false);
		btnNewAppointment.setEnabled(false);
		btnAdmit.setEnabled(false);
		btnRelease.setEnabled(false);
	}
	
	public void enableListPageButtons() {
		btnNext.setEnabled(true);
		btnNewAppointment.setEnabled(true);
		btnAdmit.setEnabled(true);
		btnRelease.setEnabled(true);
	}
}
