package Backend;
import java.util.*;
public class testing {

	public static void main(String[] args) {
		
		Company cp=new Company();
		Admin ad=new Admin();
		Reciept r=new Reciept();
		Documents d=new Documents();
		List<Employee> employees;
		Transactions t=new Transactions();
		try {
		ad.login("Mrudul", "rugged");
			
		Ecom e=new Ecom();
		e.Reply(1, "Thank You for your response");
//			t.addTransactions(123123, "Credit", 134534.00, "Assasinate John Wick", "2021-10-3");
//			List<Transactions> trs=t.getTransactions("2021-01-5","2021-12-5");
//			for(Transactions temp:trs) System.out.println(temp);
//		
//			Map<Integer,Double> mp=t.report("Credit", 2021, 3);
//			for(Map.Entry<Integer,Double> obj:mp.entrySet())
//				System.out.println(obj.getKey()+":"+obj.getValue());
		
//		List<Employee> emps=t.getSalaryList();
//				for(Employee emp:emps) System.out.println(emp);
//				int[] arr= {1,2,4,5
//				t.distributeSalaries();
			
//			Map<String,Double> mp=t.report("Credit", 2021);
//			for(Map.Entry<String,Double> obj:mp.entrySet())
//				System.out.println(obj.getKey()+":"+obj.getValue());
//			System.out.println();
//			mp=t.report("Debit", 2021);
//			for(Map.Entry<String,Double> obj:mp.entrySet())
//				System.out.println(obj.getKey()+":"+obj.getValue());
		
			
//			Reciept toAdd=new Reciept();
//			toAdd.client_company="Amazon";
//			toAdd.client_name="Jeff Bezoz";
//			toAdd.company_id=7;
//			List<Item> items=new ArrayList<Item>();
//			items.add(new Item(2,"Box",50.0));
//			items.add(new Item(2,"Nails",50.0));
//			toAdd.items=items;
//			r.addReciept(toAdd);

//			int res=d.addFile("/home/krishna/db.sql");
//			System.out.println(res);
//			
//			List<String> ls=d.getFilePaths();
//			for(String s:ls)
//				System.out.println(s);
//			
			
//			
			
//			cp.addEmployee( "Mrudul", "Mohammed",	 "SDE-2",	 "Backend", "2015-3-4", "2015-3-4", 1100000.00, 12345678);
//			ad.login("Mrudul", "rugged");
//			System.out.println("Working");
//			employees=cp.getEmployees("shn");
//			for(Employee e:employees) {
//				System.out.println(e.toString());
//			}
			
//			cp.deleteEmployee(3);
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
	
	}

}
