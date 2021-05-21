package Backend;

public class Query {
	
	public int id;
	public int company_id;
	public String query;
	public String name;
	public String email;
	
	public Query(int id,  String name, String email, String query,int company_id) {
		this.company_id=company_id;
		this.id=id;
		this.query=query;
		this.name=name;
		this.email=email;
	}
	
	
	
}
