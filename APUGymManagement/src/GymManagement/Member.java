package GymManagement;

/**
 *
 * @author Admin
 */
public class Member {
	
	String name, plan, trainer, from, to;
	int week, fess, paid, due;
	

	public Member(String name, String plan, int week, int fees, int paid, int due) {
		
		this.name = name;
		this.plan = plan;
		this.week = week;
		this.fess = fees;
		this.paid = paid;
		this.due  = due;
		this.trainer = "Not assigned";
		this.from = "00:00";
		this.to = "00:00";
		
	}
	
	public Member(String name, String plan, int week, int fees, int paid, int due, String trainer, String from, String to) {
		
		this.name = name;
		this.plan = plan;
		this.week = week;
		this.fess = fees;
		this.paid = paid;
		this.due  = due;
		this.trainer = trainer;
		this.from = from;
		this.to = to;
		
	}

}

