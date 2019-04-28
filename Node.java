package my_package;
import java.util.*;

public class Node {
	//declare vars
	//consider which of these should be static/protected/etc.
	//!!!might still have leftover mess from changing imput from String data to int[] digits
	private int[] digits;
	private Node parent;
	private ArrayList<Node> children;
	//private ArrayList<Node> siblings;
	private int depth;
	//index (0,1,2) of last digit changed 
	//for 1st node (root) avoid this bit of code or set to -1 and catch exc
	private int last_changed; 
	private boolean expanded;
	
	
	//could try-catch nullpointer for root but seems clunky
	//!!!make input arg be the int[], not string, and translate into int[] in main program 
	public Node(Node parent, int[] digits, int last_changed) {
		
		this.digits = digits;
		this.last_changed = last_changed;
		expanded = false;
		//see if this works
		if (parent == null) {
			depth = 0;
			this.last_changed = -1;
		}
		else {
		this.parent = parent;
		}
		
		//the idea is to not "arbitrarily" set depth when we create node, so not input arg
		//but still having it as var so we don't have to traverse whole tree every time
		//!this.depth = parent.getDepth() = 1;
		
		//other?		
	}
	

	//methods
	
	//!decide if want to return Str of sth else 
	public int[] getDigits() {
		return digits;		
	}	
	
	public Node getParent() {
		return parent;		
	}
	
	public ArrayList<Node> getChildren() {
		return children;		
	}
	
	public int getLastChanged() {
		return last_changed;
	}
	
//	public ArrayList<Node> getSiblings() {
//		//assumes the null parent if statement works
//		if (depth != 0) {
//			return siblings;//fix: return children of parent except current child
//		}
//		else {
//			return null;
//		}
//				
//	}

	//static? -> not but maybe when we set it yes
	public int getDepth() {
		if (depth != 0) {
			depth = parent.getDepth() + 1;
		}
		return depth;
	}

	//!consider making appendNewChildren but prob not nec
	//problem: not very transparent from outside cos uses private vars
	//needs to be ordered (for loops should ensure that)
	//generate all poss children now, filter which we expand later (not .equal prev exp nodes)
	public ArrayList<Node> generateChildren() {
		//assumes parent null thing works		
		//i.e. if root (depth=0), then last_changed == -1
		ArrayList<Node> children = new ArrayList<Node>();
		int[] dig_temp = new int[3];
		for (int x = 0; x < 3; x++) {
			if (x != last_changed) {
				dig_temp = digits;
				if (dig_temp[x] != 0) {
					dig_temp[x]--;
					Node child = new Node(this, dig_temp, x);
					children.add(child);
				}
				dig_temp = digits;
				if (dig_temp[x] != 9) {
					dig_temp[x]++;
					Node child = new Node(this, dig_temp, x);
					children.add(child);
				} 			
			}
		}
		return children;		
	}
	
	public boolean equals(Node otherNode) {
//		assumptions: if data is same, digits should be same 
//		if  children are the same, last_changed should be same 
		int[] other_digits = otherNode.getDigits();
		int other_changed = otherNode.getLastChanged();

		//can use Arrays.equals(), compare indiv contents for more transparency/safer
		if (Arrays.equals(this.digits, other_digits) && this.last_changed == other_changed) {
			return true;
		}
		return false;
	}

	//getPath()
	//This way don't create new one every time, but can get when nec.
	//iterate through parents until root reached (e.g. while parent != null)
	//
		

	
	//will probs be relevant for cycles
	//but we could ignore instead of deleting
	//public void deleteNode() {
		//sth sth
	//}

	
	
}
