/**
 * 
 */
package hotelServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

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
			if(registry == null)
				LocateRegistry.createRegistry(RMIPortNum);

			HotelCommInterface hotel = new HotelImpl("hotel" + hotelNumber);

			hotelNumber++;
			
			String registryURL = "rmi://localhost:" + RMIPortNum + "/"
					+ ((HotelImpl) hotel).getName();
			Naming.rebind(registryURL, hotel);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hello Server ready.");
	}
}
