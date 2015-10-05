/**
 * 
 */
package customPanels;

import guestClient.GuestClient;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SpinnerListModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JSpinner;

import com.toedter.calendar.JCalendar;

import obj.RoomType;

/**
 * @author George Lambadas 7077076
 * 
 */
public class GuestPanel extends JPanel {

	private GuestClient manager;
	private JCalendar calendarStartDate, calendarEndDate;
	private JComboBox roomTypeCombobox;
	private String[] hotelNames;
	private JLabel lblHotel;
	private JComboBox hotelCombobox;
	private JButton btnSubmit;
	private JLabel lblAction;
	private JComboBox actionCombobox;

	/**
	 * @param manager
	 */
	public GuestPanel(GuestClient manager) {
		this.manager = manager;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblStartDate = new JLabel("Start Date");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 0;
		gbc_lblStartDate.gridy = 0;
		add(lblStartDate, gbc_lblStartDate);

		JLabel lblEndDate = new JLabel("End Date");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblEndDate.gridx = 1;
		gbc_lblEndDate.gridy = 0;
		add(lblEndDate, gbc_lblEndDate);

		calendarStartDate = new JCalendar();
		GridBagConstraints gbc_calendarStartDate = new GridBagConstraints();
		gbc_calendarStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_calendarStartDate.gridx = 0;
		gbc_calendarStartDate.gridy = 1;
		add(calendarStartDate, gbc_calendarStartDate);

		calendarEndDate = new JCalendar();
		GridBagConstraints gbc_calendarEndDate = new GridBagConstraints();
		gbc_calendarEndDate.insets = new Insets(0, 0, 5, 0);
		gbc_calendarEndDate.gridx = 1;
		gbc_calendarEndDate.gridy = 1;
		add(calendarEndDate, gbc_calendarEndDate);

		JLabel lblRoomType = new JLabel("Room Type");
		GridBagConstraints gbc_lblRoomType = new GridBagConstraints();
		gbc_lblRoomType.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoomType.gridx = 0;
		gbc_lblRoomType.gridy = 2;
		add(lblRoomType, gbc_lblRoomType);

		roomTypeCombobox = new JComboBox<RoomType>(
				new DefaultComboBoxModel<RoomType>(RoomType.values()));
		roomTypeCombobox.setEditable(false);
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 2;
		add(roomTypeCombobox, gbc_spinner);

		lblHotel = new JLabel("Hotel");
		GridBagConstraints gbc_lblHotel = new GridBagConstraints();
		gbc_lblHotel.insets = new Insets(0, 0, 5, 5);
		gbc_lblHotel.gridx = 0;
		gbc_lblHotel.gridy = 3;
		add(lblHotel, gbc_lblHotel);

		hotelCombobox = new JComboBox<String>(new DefaultComboBoxModel<String>(
				manager.getHotelNames()));
		hotelCombobox.setEditable(false);
		GridBagConstraints gbc_hotelSpinner = new GridBagConstraints();
		gbc_hotelSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_hotelSpinner.gridx = 1;
		gbc_hotelSpinner.gridy = 3;
		add(hotelCombobox, gbc_hotelSpinner);

		lblAction = new JLabel("Action");
		GridBagConstraints gbc_lblAction = new GridBagConstraints();
		gbc_lblAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblAction.gridx = 0;
		gbc_lblAction.gridy = 4;
		add(lblAction, gbc_lblAction);

		actionCombobox = new JComboBox<String>(new DefaultComboBoxModel<String>(
				new String[] { "Check Availability", "Make Reservation",
						"Cancel Reservation" }));
		actionCombobox.setEditable(false);
		GridBagConstraints gbc_actionSpinner = new GridBagConstraints();
		gbc_actionSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_actionSpinner.gridx = 1;
		gbc_actionSpinner.gridy = 4;
		add(actionCombobox, gbc_actionSpinner);

		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 1;
		gbc_btnSubmit.gridy = 5;
		btnSubmit.addActionListener(new ButtonClickListener());
		add(btnSubmit, gbc_btnSubmit);
	}

	private class ButtonClickListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();

			if (source == btnSubmit) {
				// get all data
				String hotelName, action;
				RoomType r;
				Calendar start, end;

				hotelName = (String) GuestPanel.this.hotelCombobox.getSelectedItem();
				action = (String) GuestPanel.this.actionCombobox.getSelectedItem();

				r = (RoomType) GuestPanel.this.roomTypeCombobox.getSelectedItem();

				start = GuestPanel.this.calendarStartDate.getCalendar();
				end = GuestPanel.this.calendarEndDate.getCalendar();

				// TODO test getting data correctly and make this handler work

				// validate dates
				if (start.compareTo(end) < 0) {
					// handle different actions
					if (action == "Check Availability") {
						GuestPanel.this.manager.checkAvailability(
								manager.guestID, hotelName, r, start, end);
					} else if (action == "Make Reservation") {
						GuestPanel.this.manager.reserveRoom(manager.guestID,
								hotelName, r, start, end);
					} else if (action == "Cancel Reservation") {
						GuestPanel.this.manager.cancelReservation(
								manager.guestID, hotelName, r, start, end);
					}
				}
			}
		}
	}

}
