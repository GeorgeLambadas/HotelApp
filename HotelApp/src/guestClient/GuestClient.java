/**
 * 
 */
package guestClient;

import hotelServer.HotelCommInterface;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import obj.Hotel;
import obj.RoomType;

import customPanels.GuestPanel;
import customPanels.ServerInfoPanel;

/**
 * @author George Lambadas 7077076
 * 
 */
public class GuestClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3858689424995751875L;

	private HotelCommInterface hotel;
	private String registryURL;
	private JPanel activePanel;

	public int guestID;

	public GuestClient() {
		guestID = -1;
		hotel = null;
		registryURL = null;
		initialize();
		setVisible(true);
		centerScreen();
	}

	/**
	 * 
	 */
	private void initialize() {
		JPanel serverInfoPanel = new ServerInfoPanel(this);
		setActivePanel(serverInfoPanel);
	}

	private void centerScreen() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2,
				(dim.height - abounds.height) / 2);
	}

	/**
	 * @param hostName
	 * @param portNumber
	 * @param hotelName
	 */
	public boolean connectToServer(String hostName, int portNumber,
			String hotelName) {
		boolean success = true;
		registryURL = "rmi://" + hostName + ":" + portNumber + "/hotel";
		// get list of all things registered with this Naming.list(registryURL);

		// find the remote object and cast it to an interface object
		// TODO uncomment for comm
		/*
		 * try { hotel = (HotelCommInterface) Naming.lookup(registryURL); }
		 * catch (MalformedURLException | RemoteException | NotBoundException e)
		 * { success = false; e.printStackTrace(); }
		 */
		return success;

	}

	/**
	 * @param guestPanel
	 */
	public void setActivePanel(JPanel activePanel) {
		if (this.activePanel != null) {
			this.remove((Component) this.activePanel);
		}
		this.activePanel = activePanel;
		this.add(activePanel);
		this.setBounds(activePanel.getBounds());
		this.centerScreen();
		pack();
	}

	/**
	 * @return
	 */
	public String[] getHotelNames() {
		String[] untrimmedHotelNames = { "rmi://localhost:1099/hotel1",
				"rmi://localhost:1099/hotel2", "rmi://localhost:1099/hotel3",
				"rmi://localhost:1099/hotel4" };

		// TODO
		/*
		 * try { untrimmedHotelNames = Naming.list(registryURL); } catch
		 * (RemoteException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (MalformedURLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		String[] trimmedNames = new String[untrimmedHotelNames.length];

		for (int i = 0; i < trimmedNames.length; i++) {
			trimmedNames[i] = untrimmedHotelNames[i]
					.substring(untrimmedHotelNames[i].lastIndexOf("/") + 1);
		}

		return trimmedNames;
	}
	
	public Object checkAvailability(int guestID, String hotelName, RoomType r, Calendar checkInDate, Calendar checkOutDate) {
		try {
			hotel.checkAvailability(guestID, hotelName, r, checkInDate, checkOutDate);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean cancelReservation(int guestID, String h, RoomType r, Calendar checkInDate, Calendar checkOutDate) {
		boolean success = true;
		
		try {
			success = hotel.cancelReservation(guestID, h, r, checkInDate, checkOutDate);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		}
		
		return success;		
	}
	
	public boolean reserveRoom(int guestID, String h, RoomType r, Calendar checkInDate, Calendar checkOutDate) {
		boolean success = true;
		
		try {
			success = hotel.checkAvailability(guestID, h, r, checkInDate, checkOutDate);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
}
