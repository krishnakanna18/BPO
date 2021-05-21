package Backend;

public class Feedback {
	

	public int id;
	public int company_id;
	public String feedback;
	public String name;
	
	public Feedback(int id,  String name, String feedback,int company_id) {
		this.company_id=company_id;
		this.id=id;
		this.name=name;
		this.feedback=feedback;
	}


}
