public class Patient implements iPatient {
	private String patientNumber = "";
	private String name = "";
	private String phoneNumber = "";
	private String email = "";
	private String address = "";
	private int appointments = 0;
	private int admissions = 0;
	private boolean isOnAdmission = false;
	
	public Patient(String aName) {
		this.name = aName;
	}
	
	public Patient(
			String aName, String aPhoneNumber, String anEmail, 
			String anAddress, String aPatientNumber, int noOfAppointments, 
			int noOfAdmissions, boolean isAdmitted
	) {
		this.name = aName;
		this.phoneNumber = aPhoneNumber;
		this.email = anEmail;
		this.address = anAddress;
		this.patientNumber = aPatientNumber;
		this.appointments = noOfAppointments;
		this.admissions = noOfAdmissions;
		this.isOnAdmission = isAdmitted;
	}
	
	@Override
	public void setPatientNumber(String aPatientNumber) {
		this.patientNumber = aPatientNumber;
	}
	
	@Override
	public String getPatientNumber() {
		return this.patientNumber;
	}
	
	@Override
	public void setName(String aName) {
		this.name = aName;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setPhoneNumber(String aPhoneNumber) {
		this.phoneNumber = aPhoneNumber;
	}

	@Override
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	@Override
	public void setEmail(String anEmail) {
		this.email = anEmail;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setAddress(String anAddress) {
		this.address = anAddress;
	}

	@Override
	public String getAddress() {
		return this.address;
	}
	
	@Override
	public void newAppointment() {
		this.appointments++;
	}
	
	@Override
	public int getAppointments() {
		return this.appointments;
	}
	
	@Override
	public void admit() {
		this.isOnAdmission = true;
		this.admissions++;
	}
	
	@Override
	public void release() {
		this.isOnAdmission = false;
	}
	
	@Override
	public int getAdmissions() {
		return this.admissions;
	}

	@Override
	public String getAdmissionStatus() {
		if (this.isOnAdmission)
			return "Yes"
		; else
			return "No"
		;
	}
}
