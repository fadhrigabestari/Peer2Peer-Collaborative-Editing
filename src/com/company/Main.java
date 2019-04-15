package com.company;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		Random rand = new Random();
	    Controller controller = new Controller("name" + rand.nextInt());
	    while(true){
			Scanner sc = new Scanner(System.in);
			String command = sc.nextLine();
			String[] splitStrings = command.split(" ");
			if(splitStrings[0].equals("insert") ){
				try{
					controller.insertLocal(splitStrings[1].charAt(0),Float.parseFloat(splitStrings[2]));
				}catch (IOException e){
					System.out.println(e);
				}

			}else if(splitStrings[0].equals("delete") ){

				controller.deleteLocal(Float.parseFloat(splitStrings[1]));
			}
			controller.printDocument();

		}
    }
}
