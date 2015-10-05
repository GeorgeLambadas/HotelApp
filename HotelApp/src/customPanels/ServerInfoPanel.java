/**
 * 
 */
package customPanels;

import guestClient.GuestClient;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author George Lambadas 7077076
 *
 */
public class ServerInfoPanel extends JPanel {
	
	private JTextField hostTextField;
	private JTextField portTextField;
	private JTextField hotelNameTextField;
	private JButton btnSubmit;
	private GuestClient manager;

	
	public ServerInfoPanel(GuestClient containerFrame) {
		manager = containerFrame;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel hostLabel = new JLabel("RMI Host Name");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		add(hostLabel, gbc_label);
		
		hostTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		add(hostTextField, gbc_textField);
		hostTextField.setColumns(10);
		
		JLabel lblRmiPortNumber = new JLabel("RMI Port Number");
		GridBagConstraints gbc_lblRmiPortNumber = new GridBagConstraints();
		gbc_lblRmiPortNumber.anchor = GridBagConstraints.EAST;
		gbc_lblRmiPortNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblRmiPortNumber.gridx = 1;
		gbc_lblRmiPortNumber.gridy = 2;
		add(lblRmiPortNumber, gbc_lblRmiPortNumber);
		
		portTextField = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		add(portTextField, gbc_textField_1);
		portTextField.setColumns(10);
		
		JLabel lblHotelName = new JLabel("Hotel Name");
		GridBagConstraints gbc_lblHotelName = new GridBagConstraints();
		gbc_lblHotelName.anchor = GridBagConstraints.EAST;
		gbc_lblHotelName.insets = new Insets(0, 0, 5, 5);
		gbc_lblHotelName.gridx = 1;
		gbc_lblHotelName.gridy = 3;
		add(lblHotelName, gbc_lblHotelName);
		
		hotelNameTextField = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		add(hotelNameTextField, gbc_textField_2);
		hotelNameTextField.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ButtonClickListener());
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.gridx = 2;
		gbc_btnSubmit.gridy = 5;
		add(btnSubmit, gbc_btnSubmit);
	}
	
	/**
	 * Listener class for button clicking
	 * 
	 * @author George Lambadas 7077076
	 * 
	 */
	private class ButtonClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();

			if (source == btnSubmit) {
				String hostName, hotelName;
				int portNumber;
				hostName = ServerInfoPanel.this.hostTextField.getText();
				hotelName = ServerInfoPanel.this.hotelNameTextField.getText();
				portNumber = Integer.parseInt(ServerInfoPanel.this.portTextField.getText());
				if (ServerInfoPanel.this.manager.connectToServer(hostName, portNumber, hotelName)) {
					manager.setActivePanel(new GuestPanel(manager));
				}
			}

		}
	}

}
