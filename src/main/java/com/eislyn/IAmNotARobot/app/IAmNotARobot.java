package com.eislyn.IAmNotARobot.app;

import java.util.Scanner;

import net.dv8tion.jda.api.JDA;

/**
 * The main class of IAmNotARobot discord bot that starts the applciation.
 * @author Eislyn
 * 15/10/2022
 */
public class IAmNotARobot {

	/**
	 * Starts the application by setting up, shuts down jda with q, do not shut down using stop button in console.
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		JDASetup jdaSetup = JDASetup.getInstance();
		jdaSetup.setupJDA();
		
		JDA jda = jdaSetup.getJda();
		
		System.out.print("Type q to stop appplication: ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
	
		if(input.equals("q")) {
			System.out.println("Application is quitting...");
			jda.shutdownNow();
		}
		scanner.close();
		return;
	}

}
