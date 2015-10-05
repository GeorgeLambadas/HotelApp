/**
 * 
 */
package guestClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import hotelServer.HotelCommInterface;
import hotelServer.HotelImpl;
import obj.RoomType;
/**
 * @author George Lambadas 7077076
 * 
 */
public class GuestClientApp {

	public static void main(String[] args) {

		
		/*int RMIPort;
		String hostName = "localhost";

		System.out.println("Enter the RMIregistry port number:");

		RMIPort = 1099;
		String registryURL = "rmi://" + hostName + ":" + RMIPort + "/hotel1";
		// find the remote object and cast it to an interface object
		HotelCommInterface h = null;
		try {
			h = (HotelCommInterface) Naming.lookup(registryURL);

			System.out.println("Lookup completed ");
			// invoke the remote method
			boolean message = h.checkAvailability(0, null, RoomType.DOUBLE, null, null);

			System.out.println("Hotel Response: " + message);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GuestClient();
			}
		});
	}

}
