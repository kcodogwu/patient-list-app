public interface iPatientArrayList {
	public boolean addPatient(String aName, String aPhoneNumber, String anEmail, String anAddress);
	public Patient getPatient(int i);
	public Patient[] getPatients();
	public int getLength();
}