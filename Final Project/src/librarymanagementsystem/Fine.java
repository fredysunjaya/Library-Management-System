package librarymanagementsystem;

public class Fine {
	//	3 Status
	//	None
	//	Paid
	//	Not Paid
	
	private String id;
	private Double total;
	private String status;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Fine(String id, Double total, String status) {
		super();
		this.id = id;
		this.total = total;
		this.status = status;
	}
	
	public Fine() {
		
	}
}
