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

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmList = new JMenuItem("List");
		mnMenu.add(mntmList);
		
		JMenuItem mntmNewPatient = new JMenuItem("New Patient");
		mnMenu.add(mntmNewPatient);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnMenu.add(mntmExit);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_262852550863755");
		panel.setLayout(null);
		
		JLabel lblPatientList = new JLabel("Patient List");
		lblPatientList.setBounds(152, 11, 147, 37);
		lblPatientList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblPatientList);
		
		JLabel lbl1 = new JLabel("Patient Number:");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl1.setBounds(33, 102, 122, 14);
		panel.add(lbl1);
		
		JLabel lbl2 = new JLabel("Patient Name:");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl2.setBounds(33, 145, 106, 14);
		panel.add(lbl2);
		
		JLabel lbl3 = new JLabel("Number of appointments:");
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl3.setBounds(33, 184, 171, 14);
		panel.add(lbl3);
		
		JLabel lbl4 = new JLabel("Number of admissions:");
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl4.setBounds(33, 223, 156, 14);
		panel.add(lbl4);
		
		JLabel lbl5 = new JLabel("Is on admission?");
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl5.setBounds(33, 261, 111, 14);
		panel.add(lbl5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(205, 99, 106, 20);
		panel.add(comboBox);
		
		JButton button = new JButton("<< Previous");
		button.setBounds(66, 54, 106, 23);
		panel.add(button);
		
		JButton btnNext = new JButton("Next >>");
		btnNext.setBounds(260, 54, 89, 23);
		panel.add(btnNext);
		
		JButton btnNew = new JButton("New appointment");
		btnNew.setBounds(26, 338, 123, 23);
		panel.add(btnNew);
		
		JButton btnAdmit = new JButton("Admit");
		btnAdmit.setBounds(157, 338, 123, 23);
		panel.add(btnAdmit);
		
		JButton btnRelease = new JButton("Release");
		btnRelease.setBounds(290, 338, 123, 23);
		panel.add(btnRelease);
		
		JLabel lblPatientName = new JLabel("-");
		lblPatientName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatientName.setBounds(205, 145, 106, 14);
		panel.add(lblPatientName);
		
		JLabel lblNumberOfAppointments = new JLabel("-");
		lblNumberOfAppointments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfAppointments.setBounds(205, 186, 106, 14);
		panel.add(lblNumberOfAppointments);
		
		JLabel lblNumberOfAdmissions = new JLabel("-");
		lblNumberOfAdmissions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfAdmissions.setBounds(205, 225, 106, 14);
		panel.add(lblNumberOfAdmissions);
		
		JLabel lblIsOnAdmission = new JLabel("-");
		lblIsOnAdmission.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsOnAdmission.setBounds(205, 261, 106, 14);
		panel.add(lblIsOnAdmission);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "name_262855384487829");
		panel_1.setLayout(null);
		
		JLabel lblNewPatient = new JLabel("New Patient");
		lblNewPatient.setBounds(137, 11, 160, 37);
		lblNewPatient.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_1.add(lblNewPatient);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(34, 73, 61, 14);
		panel_1.add(lblName);
		
		JLabel lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNumber.setBounds(34, 112, 96, 14);
		panel_1.add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(34, 152, 44, 14);
		panel_1.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(34, 200, 61, 14);
		panel_1.add(lblAddress);
		
		JButton button_1 = new JButton("New appointment");
		button_1.setBounds(61, 368, 123, 23);
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("New appointment");
		button_2.setBounds(244, 368, 123, 23);
		panel_1.add(button_2);
		
		textField = new JTextField();
		textField.setBounds(166, 72, 216, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(166, 111, 216, 20);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(166, 151, 216, 20);
		panel_1.add(textField_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(166, 197, 216, 143);
		panel_1.add(textArea);
	}
}
