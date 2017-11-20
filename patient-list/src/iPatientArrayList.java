import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iPatientArrayList extends Remote {
	public boolean addPatient(String aName, String aPhoneNumber, String anEmail, String anAddress) throws RemoteException;
	public Patient getPatient(int i) throws RemoteException;
	public Patient[] getPatients() throws RemoteException;
	public int getLength() throws RemoteException;
	public boolean editPatientInformation(int index, Patient patient) throws RemoteException;
}