import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

public class PatientListServer {
	public static void main(String[] args) {
		try {
			PatientArrayList paList = new PatientArrayList(1000);
			Naming.rebind("patient_array_list", paList);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
