package ss.week6.voteMachineNew;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ss.week6.voteMachine.gui.*;


public class VoteMachine /*controller*/ implements ActionListener 
{
	private PartyList partyList;
	private VoteList voteMap;
	VoteView voteView;
	 
	//---------------------constructor-----------------------
	public VoteMachine (PartyList party_Model,VoteList vote_Model)
	{
		this.partyList=party_Model; 
		this.voteMap=vote_Model; 
		//P 6.17 
		//this.voteView=new VoteTUIView (party_Model,vote_Model,this);
		this.voteView = new VoteGUIView (this);//P 6.17
		partyList.addObserver(voteView); // The view observes the model.
		voteMap.addObserver(voteView); 
	
	} 
	
	//-------------------queires------------------
	public List<String> getParties()
	{
		return this.partyList.getParties();//model.method
	}
	public Map <String,Integer> getVoteList()
	{
		return this.voteMap.getVotes();//model.method
	}
	
	//-----------------commands------------------------
	public void addParty(String party) {
		partyList.addParty(party);//model.method
	}
	
	public void vote(String party) {
		voteMap.addVote(party);//model.method
	}
	
	public void actionPerformed(ActionEvent e) {
		JTextField tf=(JTextField)e.getSource();
		String str=tf.getText();
		String which=e.getActionCommand();
		//System.out.println("getActionCommand"+which);
		if (which.equals("Party"))
		{ 
			partyList.addParty(str);
		}
		else //which.equals("Vote")
		{
			voteMap.addVote(str);
		}
	} 
	public void start ()
	{
		voteView.start();// //P 6.15 call start method of the view
	}
	

	//--------------main method------------------------
	public static void main (String[] args)
	{
		//P 6.11 dot 1 
		PartyList myPartyList= new PartyList();
		VoteList myVoteMap=new VoteList();
		VoteMachine voteMachine=new VoteMachine (myPartyList,myVoteMap);//give arguments
		voteMachine.start ();
	}
}
