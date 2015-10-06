/**
 * 
 */
package hotelServer;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author George Lambadas 7077076
 * 
 */
public class HotelServer {
	

	private static int hotelNumber = 1;
	public static void main(String[] args) {
		System.out.println("hotel server starting");
		int RMIPortNum = 1099;

		try {
			Registry registry = LocateRegistry.getRegistry();
			if (registry == null)
				registry = LocateRegistry.createRegistry(RMIPortNum);
			HotelGuestInterface hotel = new HotelImpl("hotel" + 3);

			hotelNumber++;
			
			String registryURL = "rmi://localhost:" + RMIPortNum + "/"
					+ ((HotelImpl) hotel).getName();
			Naming.rebind(registryURL, hotel);
			System.out.println("Hello Server ready.");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
