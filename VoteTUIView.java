package ss.week6.voteMachineNew;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import ss.week6.voteMachine.gui.*;

/**View*/
public class VoteTUIView extends JPanel implements Observer, VoteView
{
	private VoteList voteMap;//model
	private PartyList partyList;//model
	private VoteMachine voteMachine; //controller 
	private static final long serialVersionUID = 1L; 

	//---------------constructor-----------------------
	public VoteTUIView (PartyList party_Model,VoteList vote_Model,VoteMachine vM)
	{
		super(); //means constructor: JPanel()
		voteMachine=vM;
	}

	//-----------------get inputs from users------------------------
	public void start ()
	{
		/**P 6.12
		 * You need a loop as it allows you to handle multiple inputs. 
		 * Think of it as a voting machine where first one person can vote, 
		 * then the second and so on. 
		 * It would be irritating if you would have to restart the program each time. 
		 * Therefore you want to be able to handle multiple inputs after each other.
		 */ 
		
		System.out.println("Enter a command: ");
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		while (!command.equals("EXIT"))/**EXIT stops the program.*/
		{
			if (command.equals("HELP"))/**HELP should show the user how to use the program. */
			{
				System.out.printf("you can give the following commands:\n"+
						"VOTE [party]: vote for a certain pary\n"
						+"ADD PARTY [party]: add a party which is not in the party list\n"
						+"VOTES: shows the result up until now \n "
						+"PARTIES: shows all parties that someone can vote for\n"
						+"EXIT: finish voting for all parties\n"
						+"HELP: show the commands\n");
			}
			else if (command.contains("ADD PARTY["+"]"))
			{
				String[] firstSplit =command.split("[");
				String temp=firstSplit[1];
				String[] secondSplit =temp.split("]");
				String party=secondSplit[0];
				voteMachine.addParty(party);//this is a command. controller.method. instead of model.method
			} 
			else if(command.equals("PARTIES"))/**PARTIES shows all parties that someone can vote for. */
			{
				//show something, do not need controller. only need model.method
				this.showParties(this.getParties());
			}
			else if (command.equals("VOTES"))/**VOTES shows the result up until now, so all votes that have been made.*/
			{
				
				this.showVotes(this.getVotes());
			}
			else if (command.contains("VOTE["+"]"))
				/**VOTE [party] means that 
				if a user would type "VOTE VVD" 
				then the user votes for the party VVD.*/
			{
				String[] firstSplit =command.split("[");
				String temp=firstSplit[1];
				String[] secondSplit =temp.split("]");
				String party=secondSplit[0];
				voteMachine.vote(party);//this is a command. controller.method
			}
			System.out.println("Enter a command: ");
			scanner = new Scanner(System.in);
			command = scanner.nextLine();
		}
		scanner.close();
		System.out.println("you have exited the program");

	}
	
	//-------------queries----------------------------
	public VoteMachine getVoteMachine() 
	{
		return voteMachine;//return the controller
	}	
	public List<String> getParties()
	{	
		return voteMachine.getParties();//use controller voteMachine, access to partyList. then getParties()
	}
	public Map <String,Integer> getVotes()
	{
		return voteMachine.getVoteList();//access the model via the controller
	}
	
	//-----------------------commands---------------------
	public void showError(String error)
	{
		System.out.println(error);
	}

	public void showVotes(Map<String,Integer> map)
	{
		for (String key : map.keySet()) {
			System.out.println(key+": "+ map.get(key));
		}
	}

	public void showParties(List<String> party)
	{
		System.out.println(Arrays.toString(party.toArray()));
	}
	
	
	@Override
	public void update(Observable party_model, Object arg) {
		/**inform the user (through the standard output) that the party/vote has been added. 
This gives feedback to the user, telling him/her that their action succeeded.
Note that the observer is notified from methods in the model (PartyList/VoteList) 
thus when the observer gets a notification, 
this means that the party/vote has already been added.*/
		
		// arg can be "party", because in the class PartyList, notifyObservers("party");
		// arg can be "vote " because in the class VoteList, notifyObservers("vote");
		if (arg.equals("party"))
		{
			System.out.println("the party has been added.");
		}
		else if (arg.equals("vote"))
		{
			System.out.println("the vote has been added.");
		}
	}
}
