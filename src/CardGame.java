/*
 * Teena Xiong
 * 11/8/2016
 * 
 * post lab 07
 * 
 * This program will consist of two methods. User are asked to pick between option 1 or 2, which will 
 * divert them to the respective method. The program will have three arrays: a rank, a suit, and a deck.
 * 
 * method 1: this is where user and the computer will pick a card from their own deck of cards. Whoever
 * has the highest number is the winner. 
 * 
 * method 2: this is where the computer will play against another automatic computer. Whoever has the
 * highest number is the winner. They will automatically compete for 100 times. At the end, the returned
 * string will be the winner that have the most winnings. 
 */


import java.util.Scanner; 
public class CardGame {
	public static void main (String [] args)
	{
		Scanner input = new Scanner (System.in);
		
		String [] suit = {"Club", "Heart", "Diamond", "Spade"}; //contains the suit from smallest to largest. 
		
		//contains the ranks, from smallest to largest. 
		String [] ranks ={"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		
		int [] deck = new int[52]; //an array that consist of 52 index position. 
		int [] computerDeck = new int [52];  //another array with 52 index position. 
		
		//initializing the deck cards. 
		for(int num=0; num<deck.length; num++)
		{
			deck[num]=num; 
		}
		
		//shuffling the deck of cards. 
		for(int num = 0; num<deck.length; num++)
		{
			int random = (int)(Math.random () * deck.length); //generate a number. 
			int temp = deck[num]; //storing the value in deck[num] position here. 
			deck[num] = deck[random]; //storing a value from the deck[random] into deck[num. 
			deck[random] = temp; //now deck[random] has the value we previously stored in temp. 
		}
		
		//initializing the computerDeck array. 
		for(int num=0; num<computerDeck.length; num++)
		{
			computerDeck[num]=num; 
		}
		
		//shuffling the computerDeck. same as the code for deck array. 
		for(int num = 0; num<computerDeck.length; num++)
		{
			int temp = computerDeck[num];
			int random = (int)(Math.random () * computerDeck.length); 
			computerDeck[num] = computerDeck[random];
			computerDeck[random] = temp; 
		}
		
		
		//asking user to pick an option: 1 or 2
		System.out.println("Do you want to play part 1 or part 2? (enter 1 or 2)");
		int userInput = input.nextInt();
		
		//if user did not input 1 or 2, this while loop will continue to run. 
		while(userInput != 1 && userInput !=2)
		{
			System.out.println("Please enter a 1 or 2");
			userInput = input.nextInt(); 
		}
		
		//if user picks 1, this if statement is ran. 
		if(userInput==1)
		{
			String winner = getPart1(suit, ranks, deck, computerDeck); //calling getPart1 method with parameters
			System.out.println("The winner is: " + winner); //printing out the winner. 
		}
		
		//if user picks 2, this if statement is ran. 
		if(userInput==2)
		{
			String winner = getPart2(suit, ranks, deck, computerDeck); //calling getPart2 method with parameters
			System.out.println("The winner is the " + winner); //printing out the winner. 
		}
	}

	
	
//the method for the getPart 1. Returns a string. 
public static String getPart1(String [] suit, String [] ranks, int [] deck, int [] computerDeck)
{
	Scanner input = new Scanner (System.in); 
	System.out.println("Pick a number from 1 to 52");
	int userPick = input.nextInt(); //user picks a number. 
	String winner = null;  //initialzing the variable winner. 
	
	//if user did not pick a number 1-52, this while loop will run. 
	while(!(userPick>=1 && userPick <=52))
	{
		System.out.println("Sorry. Please pick a number from 1 to 52");
		 userPick = input.nextInt();
	}
	

	//prints out what card user got. will consist of the rank and the suit. 
	System.out.println("Your card is " + ranks [deck[(userPick-1)] % 13] + " of "
					+ suit [deck[(userPick-1)] / 13]); 
	
	
	int randomComputer = (int)(Math.random() * 51); //generating a number
	//prints out what card the computer got. will consist of the rank and the suit. 
	System.out.println("The computer's card is " + ranks [computerDeck[(randomComputer)] % 13] + " of " 
			+ suit [computerDeck[(randomComputer)] / 13]); 
	
	
	int playerRank = 0; //Initializing the playerRank to 0. 
	int playerSuit = 0; //initializing the playerSuit to 0. 
	
	//this for loop is checking to see what index in the array does the player card is located at. 
	for(int num = 0; num<ranks.length; num++)
	{
		for(int i = 0; i<suit.length; i++)
		{
			if(ranks [deck[(userPick-1)] % 13] == ranks[num] && suit [deck[(userPick-1)] / 13]==suit[i])
			{
				playerRank = num; //the index position of the player's rank is stored here. 
				playerSuit = i; //the index position of the player's suit is stored here. 
			}
		}//end of inner for loop. 
	}//end of for loop. 
	
	
	int computerRank = 0; 
	int computerSuit = 0; 
	//this for loop is checking to see what index in the array does the computer card is located at. 
	for(int num = 0; num<ranks.length; num++)
	{
		for(int i = 0; i<suit.length; i++)
		{
			if(ranks [computerDeck[(randomComputer)] % 13]==ranks[num] && suit [computerDeck[(randomComputer)] / 13]==suit[i])
			{
				 computerRank = num; //the index position of the computer's rank is stored here. 
				 computerSuit = i; //the index position of the computer's suit is stored here. 
			}
		}
	}
	
	
	//this is comparing to see which card is the highest. 
	if(playerRank > computerRank)
	{
		winner = "Player"; 
	}
	else if (playerRank < computerRank)
	{
		winner = "Computer";
	}
	else if(playerRank == computerRank)
	{
		if(playerSuit > computerSuit)
			winner = "Player";
		else if(playerSuit < computerSuit)
			winner = "Computer"; 
		else if (playerSuit == computerSuit)
			winner = "Tie";
	}
	
	
	return winner; //return a string back to the main method. 
}//ends the getPart1 method. 




//getPart2 method with arguments. Returns a string. 
public static String getPart2 (String [] suit, String [] ranks, int [] deck, int [] computerDeck)
{
	//initializing all the variables
	int computer1Win = 0; //stores how many times computer 1 wins. 
	int computer2Win = 0; //stores how many times computer 2 wins. 
	int tie = 0; //stores the number of ties. 
	String winner = null; //stores who is the winner. 
	int num = 1; //a counter for the while loop. 
	
	//this while loop will run until num is 100
	while( num <=100)
	{
		//shuffling the deck card. 
		for(int index = 0; index<deck.length; index++)
		{
			int random = (int)(Math.random () * deck.length); 
			int temp = deck[index];
			deck[index] = deck[random];
			deck[random] = temp; 
		}
		
		//shuffling the computer deck card. 
		for(int numIndex = 0; numIndex<computerDeck.length; numIndex++)
		{
			int temp = computerDeck[numIndex];
			int random = (int)(Math.random () * computerDeck.length); 
			computerDeck[numIndex] = computerDeck[random];
			computerDeck[random] = temp; 
		}
		
		//generating a random number for computer 1. 
		int randomComputer1 = (int)(Math.random() * 51); 
		
		//prints out what computer 1 card is. 
		System.out.println("The computer01 card is " + ranks [deck[(randomComputer1)] % 13] + " of "
				+ suit [deck[randomComputer1] / 13]); 
		
		//generating a random number for computer 2. 
		int randomComputer2 = (int)(Math.random() * 51); 
		
		//prints out what the computer 2 card is. 
		System.out.println("The computer02 card is " + ranks [computerDeck[(randomComputer2)] % 13] + " of " 
				+ suit [computerDeck[(randomComputer2)] / 13]);
		
		
		
		int computer01Rank = 0; 
		int computer01Suit = 0; 
		
		//this for loop is finding the index position where the computer 1 card is stored at in 
		//the array ranks and suit. 
		for(int count = 0; count<ranks.length; count++)
		{
			for(int i = 0; i<suit.length; i++)
			{
				if(ranks [deck[(randomComputer1)] % 13] == ranks[count] && suit [deck[(randomComputer1)] / 13]==suit[i])
				{
					computer01Rank = count;
					computer01Suit = i; 
					
				}
			}
		}
		
		
		
		int computer02Rank = 0; 
		int computer02Suit = 0; 
		
		//this for loop is finding the index position where the computer 2 card is stored at in the
		//rank and suit array. 
		for(int num1 = 0; num1<ranks.length; num1++)
		{
			for(int count01 = 0; count01<suit.length; count01++)
			{
				if(ranks [computerDeck[(randomComputer2)] % 13]==ranks[num1] && suit [computerDeck[(randomComputer2)] / 13]==suit[count01])
				{
					 computer02Rank = num1;
					 computer02Suit = count01; 
					
				}
			}
		}
		
		
		//these if statements determines who is the winner for this round. 
		if(computer01Rank > computer02Rank)
		{
			System.out.println("Computer1 won this round."); 
			computer1Win++; //keeps track how many times computer 1 win. 
		}
		else if (computer01Rank < computer02Rank)
		{
			System.out.println("Computer2 won this round."); 
			computer2Win++; //keeps track how many times computer 2 win. 
		}
		else if(computer01Rank == computer02Rank)
		{
			if(computer01Suit > computer02Suit)
				{
					System.out.println("Computer1 won this round."); 
					computer1Win++;
				}
			else if (computer01Suit < computer02Suit)
				{
					System.out.println("Computer1 won this round."); 
					computer2Win++; 	
				}
			else if (computer01Suit == computer02Suit)
			{
				System.out.println("It is a tie this round."); 
				tie++;
			}
				 
		}
		
		num++; //incrementing the counter. 
		System.out.println("\n*********************************"); 	
	} //end of while loop
	
	//prints out the number of winnings and ties. 
	System.out.println("Computer01 won " + computer1Win + " times"); 
	System.out.println("Computer02 won " + computer2Win + " times");
	System.out.println("There were " + tie + " ties");
	
	//determining who is the winner. 
	if(computer1Win > computer2Win)
		winner = "Computer01";
	else winner = "Computer02"; 
	
	return winner; //return the winner back to the main method. 
	
	
}//end of getPart2 method. 

	
}//ending of class
