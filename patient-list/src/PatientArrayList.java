public class PatientArrayList implements iPatientArrayList {
	private Patient[] head;
	private int limit;
	private int length = 0;
	
	public PatientArrayList(int l) {
		this.limit = l;
		this.head = new Patient[this.limit];
	}
	
	@Override
	public boolean addPatient(String aName, String aPhoneNumber, String anEmail, String anAddress) {
		if (this.length < this.limit) {
			String aPatientNumber = "P" + this.length; 
			int noOfAppointments = 0; 
			int noOfAdmissions = 0; 
			boolean isAdmitted = false;
			Patient temp = new Patient(aName, aPhoneNumber, anEmail, anAddress, aPatientNumber, noOfAppointments, noOfAdmissions, isAdmitted);
			
			this.head[this.length] = temp;
			this.length++;
			return true;
		} else
			return false
		;
	}

	@Override
	public Patient getPatient(int i) {
		if (i < this.head.length)
			return this.head[i]
		; else
			return this.head[this.head.length - 1]
		;
	}

	@Override
	public Patient[] getPatients() {
		return this.head;
	}

	@Override
	public int getLength() {
		return this.length;
	}
}
