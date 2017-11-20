import java.io.Serializable;

public interface iPatient extends Serializable {
	public void setPatientNumber(String aPatientNumber);
	public String getPatientNumber();
	public void setName(String aName);
	public String getName();
	public void setPhoneNumber(String aPhoneNumber);
	public String getPhoneNumber();
	public void setEmail(String anEmail);
	public String getEmail();
	public void setAddress(String anAddress);
	public String getAddress();
	public void newAppointment();
	public int getAppointments();
	public void admit();
	public void release();
	public int getAdmissions();
	public String getAdmissionStatus();
}
