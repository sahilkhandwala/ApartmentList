package Challenge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SpinningCage {
	
	private List<String> members;
	private List<String> absentMembers = new ArrayList<>();
	private final String FILENAME = "./Memberlist.txt";
	private BufferedReader br;
	
	public SpinningCage() {
		initializeMemberList();
	}
	
	public void addNewMembers(String name){
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {
			bw.write(name);
			bw.newLine();
			initializeMemberList();
		} catch (IOException e) {
			System.out.println("Exception while adding new member!");
		}
	}
	
	public boolean addAbsentMember(String name) {
		boolean present = members.contains(name);
		if(present) {
			absentMembers.add(name);	
		}
		return present;
	}
	
	public void fridayLunch() {
		members.removeAll(absentMembers);
		
		int totalMembers = members.size();
		int groupNo = 1;
		int groupSize = 0;
		shuffle(members, totalMembers);
		int start = 0;
		int remaining = 0;
				
		// We cannot have a group according to the given constraints 
		if(totalMembers <3) {
			System.out.println("******* Add or hire more team members ********");
			return;
		}
		
		// keep iterating till true
		while(remaining >= 0) {
			remaining = totalMembers - start;
			// if there are <= 5 total members we can directly form a group
			if (remaining <=5) {
				groupSize = remaining;
			}
			
			// if there are just 6 members remaining, only possible combination is 2 groups of 3 each
			else if(remaining == 6) {
				groupSize = 3;
			}
			// If there are only 7 members left, we cannot form a group of 5
			else if(remaining ==7) {
				groupSize = generateRandom(3, 4);	
			}
			// we choose at random to form a group between 3 to 5 members
			else {
				groupSize = generateRandom(3, 5);				
			}
			printGroupMembers(members, start, groupSize, "GroupNo " + groupNo);		
			start += groupSize;
			if(start >= totalMembers) {
				return;
			}
			groupNo++;
		}
	}
	
	private void initializeMemberList() {
		members = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(FILENAME));
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				members.add(currentLine);
			}
		} catch (IOException e) {
			System.out.println("Exception while reading member list");
		}
	}
	
	// Generate Random numbers within range of low and high inclusive
	
	private int generateRandom(int low, int high) {
		return ThreadLocalRandom.current().nextInt(low, high+1);
	}
	
	/*
	 *  Aim:- Shuffles the list in random order
	 *  Algo:- takes last element, chooses random index from 0 to i, replaces the last element with the chosen
	 *  random index. Decrements index and continues.
	 */
	
	private void shuffle(List<String> members, int totalMembers) {
		for(int i=totalMembers-1; i>0; i--) {
			int random = ThreadLocalRandom.current().nextInt(0, i);
			String temp = members.get(i);
			members.set(i, members.get(random));
			members.set(random, temp);
		}
	}
	
	// prints out the group no and following members
	
	private void printGroupMembers(List<String> members, int start, int end, String groupNo) {
		System.out.println(groupNo + ":");
		for(int i=start; i<=(start+ end)-1; i++) {
			System.out.print(members.get(i) + " ");
		}
		System.out.println();
	}
}
