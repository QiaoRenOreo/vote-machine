package ss.week6.voteMachineNew;

import java.util.*;

/**The VoteList class should have methods for making a vote 
 * and retrieving all votes in a Map<String,Integer>. 
 * Use ss.week6.test.VoteMachineModelTest to test your implementation.*/

/** the class VoteList contains a Map<String,Integer> which contains all votes. 
 * Thus the class VoteList has a Map 
 * 			which maps a String (party name) to an Integer (number of votes they have received).
the key is then the party name
the value would be the number of votes they have received.
As for the question why it is 1, they first make sure that a specific party exists and then they vote for that party. 
If you vote for a party then this party will get 1 vote.*/
public class VoteList extends Observable
{
	
	private Map <String,Integer>map;
	public VoteList() 
	{
		map=new HashMap<String, Integer>();  
	}
	public void addVote (String party)
	{
		if (map.get(party)==null)
		{ 
			map.put(party, 0); 
		}
		map.put(party, map.get(party) + 1);//null+1 check value==null, change null to 0 
		setChanged();
		notifyObservers("vote");
	}

	public Map <String,Integer> getVotes ()
	{
		return map; 
	}
	public int get(String party) //get the accumulated votes for a certain party
	{
		return map.get(party);
	}

}
