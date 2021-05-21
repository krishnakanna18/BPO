package Backend;

public class Item{
	int id;
	int quantity;
	String name;
	double price;
	int description;
	public double total;
	
	public Item(int quantity, String name,double price) {
		this.quantity=quantity;
		this.name=name;
		this.price=price;
		this.total=price*quantity;
	}
	
	
}
