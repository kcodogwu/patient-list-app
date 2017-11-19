import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
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
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class MainWindow {
	private JFrame frame;
	private JTextField txtName;
	private JTextField txtPhoneNumber;
	private JTextField txtEmail;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmList;
	private JMenuItem mntmNewPatient;
	private JMenuItem mntmExit;
	private JPanel panel;
	private JLabel lblPatientList;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lbl5;
	private JComboBox comboBox = new JComboBox();
	private JButton button;
	private JButton btnNext;
	private JButton btnNew;
	private JButton btnAdmit;
	private JButton btnRelease;
	private JLabel lblPatientName;
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
	private PatientArrayList paList = new PatientArrayList(1000);
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
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		mntmList = new JMenuItem("List");
		
		mntmList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
				
				reloadComboBox();
				if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
				cl.show(frame.getContentPane(), PATIENT_LIST);
			}
		});
		
		mnMenu.add(mntmList);
		
		mntmNewPatient = new JMenuItem("New Patient");
		
		mntmNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
				cl.show(frame.getContentPane(), NEW_PATIENT);
			}
		});
		
		mnMenu.add(mntmNewPatient);
		
		mntmExit = new JMenuItem("Exit");
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		mnMenu.add(mntmExit);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, PATIENT_LIST);
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
		lbl2.setBounds(33, 145, 106, 14);
		panel.add(lbl2);
		
		lbl3 = new JLabel("Number of appointments:");
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl3.setBounds(33, 184, 171, 14);
		panel.add(lbl3);
		
		lbl4 = new JLabel("Number of admissions:");
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl4.setBounds(33, 223, 156, 14);
		panel.add(lbl4);
		
		lbl5 = new JLabel("Is on admission?");
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl5.setBounds(33, 261, 111, 14);
		panel.add(lbl5);

		reloadComboBox();
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				
				patientIndex = cb.getSelectedIndex();
				if (patientCount > 0) showPatientDetails(patientIndex);
			}
		});

		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(205, 99, 106, 20);
		panel.add(comboBox);
		
		button = new JButton("<< Previous");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (patientIndex - 1 > -1) {
					--patientIndex;
					if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
				}
			}
		});
		
		button.setBounds(66, 54, 106, 23);
		panel.add(button);
		
		btnNext = new JButton("Next >>");
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (patientIndex + 1 < patientCount) {
					++patientIndex;
					if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
				}
			}
		});
		
		btnNext.setBounds(260, 54, 89, 23);
		panel.add(btnNext);
		
		btnNew = new JButton("New appointment");
		
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientIndex = comboBox.getSelectedIndex();
				Patient aPatient = paList.getPatient(patientIndex);
				
				aPatient.newAppointment();
				if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
			}
		});
		
		btnNew.setBounds(10, 338, 135, 23);
		panel.add(btnNew);
		
		btnAdmit = new JButton("Admit");
		
		btnAdmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientIndex = comboBox.getSelectedIndex();
				Patient aPatient = paList.getPatient(patientIndex);
				
				aPatient.admit();
				if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
			}
		});
		
		btnAdmit.setBounds(154, 338, 135, 23);
		panel.add(btnAdmit);
		
		btnRelease = new JButton("Release");
		
		btnRelease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientIndex = comboBox.getSelectedIndex();
				Patient aPatient = paList.getPatient(patientIndex);
				
				aPatient.release();
				if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
			}
		});
		
		btnRelease.setBounds(299, 338, 135, 23);
		panel.add(btnRelease);
		
		lblPatientName = new JLabel("-");
		lblPatientName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatientName.setBounds(205, 145, 106, 14);
		panel.add(lblPatientName);
		
		lblNumberOfAppointments = new JLabel("-");
		lblNumberOfAppointments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfAppointments.setBounds(205, 186, 106, 14);
		panel.add(lblNumberOfAppointments);
		
		lblNumberOfAdmissions = new JLabel("-");
		lblNumberOfAdmissions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfAdmissions.setBounds(205, 225, 106, 14);
		panel.add(lblNumberOfAdmissions);
		
		lblIsOnAdmission = new JLabel("-");
		lblIsOnAdmission.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsOnAdmission.setBounds(205, 261, 106, 14);
		panel.add(lblIsOnAdmission);
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, NEW_PATIENT);
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
				boolean flag = paList.addPatient(
					txtName.getText(), 
					txtPhoneNumber.getText(), 
					txtEmail.getText(), 
					txtAddress.getText()
				);
				
				if (flag) {
					// clear error message
				} else {
					// show error message
				}
				
				txtName.setText("");
				txtPhoneNumber.setText("");
				txtEmail.setText("");
				txtAddress.setText("");
			}
		});
		
		btnAddPatient.setBounds(61, 368, 123, 23);
		panel_1.add(btnAddPatient);
		
		btnCancel = new JButton("Cancel");
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
				
				reloadComboBox();
				if (patientCount > 0) comboBox.setSelectedIndex(patientIndex);
				cl.show(frame.getContentPane(), PATIENT_LIST);
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
		txtAddress.setBounds(166, 197, 216, 143);
		panel_1.add(txtAddress);
		
		patientCount = paList.getLength();
	}

	public void showPatientDetails(int index) {
		Patient aPatient = paList.getPatient(index);
		
		lblPatientName.setText(aPatient.getName());
		lblNumberOfAppointments.setText(aPatient.getAppointments() + "");
		lblNumberOfAdmissions.setText(aPatient.getAdmissions() + "");
		lblIsOnAdmission.setText(aPatient.getAdmissionStatus());
	}

	public void reloadComboBox() {
		patientCount = paList.getLength();
		cbItems = new String[patientCount];
		
		for (int i = 0; i < paList.getLength(); i++) {
			cbItems[i] = paList.getPatient(i).getName();
		}
		
		model = new DefaultComboBoxModel(cbItems);
		comboBox.setModel(model);
	}
}
