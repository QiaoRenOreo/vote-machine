package ss.week6.voteMachineNew;
import java.util.*;

/**P 6.10 form the model for the voting machine. 
 * The PartyList class should store all the parties in the system 
 * and have a method for adding a party and retrieving all parties in a List. 
 * */
public class PartyList extends Observable
{
	private List<String>partyList;
	
	public PartyList()  
	{
		partyList = new ArrayList<String>();
	}
	public void addParty(String party) 
	{
		
		partyList.add(party);
		setChanged();
		notifyObservers("party");
	}
	public List<String> getParties()
	{
		return partyList;  
	}
	public boolean hasParty(String party)
	{
		return (partyList.contains(party)) ;
	}
	
}
