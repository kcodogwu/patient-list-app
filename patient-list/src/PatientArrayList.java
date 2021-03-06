import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PatientArrayList extends UnicastRemoteObject implements iPatientArrayList {
	private Patient[] store;
	private int limit;
	private int length = 0;
	
	public PatientArrayList() throws RemoteException {
		super();
	}
	
	public PatientArrayList(int l) throws RemoteException {
		this.limit = l;
		this.store = new Patient[this.limit];
	}
	
	@Override
	public boolean addPatient(String aName, String aPhoneNumber, String anEmail, String anAddress) throws RemoteException {
		if (this.length < this.limit) {
			String aPatientNumber = "P"; 
			int noOfAppointments = 0; 
			int noOfAdmissions = 0;
			int pNo = this.length + 1;
			boolean isAdmitted = false;
			Patient temp;
			
			if (("" + pNo).length() == 1)
				aPatientNumber += "000" + pNo
			; else if (("" + pNo).length() == 2)
				aPatientNumber += "00" + pNo
			; else if (("" + pNo).length() == 3)
				aPatientNumber += "0" + pNo
			; else if (("" + pNo).length() == 4)
				aPatientNumber += pNo
			;
			
			temp = new Patient(aName, aPhoneNumber, anEmail, anAddress, aPatientNumber, noOfAppointments, noOfAdmissions, isAdmitted);
			this.store[this.length] = temp;
			this.length++;
			return true;
		} else
			return false
		;
	}

	@Override
	public Patient getPatient(int i) throws RemoteException {
		if (i < this.store.length)
			return this.store[i]
		; else
			return this.store[this.store.length - 1]
		;
	}

	@Override
	public Patient[] getPatients() throws RemoteException {
		return this.store;
	}

	@Override
	public int getLength() throws RemoteException {
		return this.length;
	}

	@Override
	public boolean editPatientInformation(int index, Patient patient) throws RemoteException {
		if (index < this.length) {
			this.store[index] = patient;
			return true;
		} else
			return false
		;
	}
}
