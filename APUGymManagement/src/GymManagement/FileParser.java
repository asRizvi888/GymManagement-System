/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class FileParser {
	public ArrayList<Member> FileToArrayList () {
		ArrayList<Member> arr = new ArrayList<>();
		
		try {
			Scanner io = new Scanner(new File("member.txt"));

			while (io.hasNext()) {

				String _name = io.nextLine();
				String _plan = io.nextLine();
				String _week = io.nextLine();
				String _fees = io.nextLine();
				String _paid = io.nextLine();
				String _due  = io.nextLine();
				String _trainer = io.nextLine();
				String _from = io.nextLine();
				String _to = io.nextLine();

				int NoOfWeek = Integer.parseInt(_week);
				int Fees = Integer.parseInt(_fees);
				int Paid = Integer.parseInt(_paid);
				int Due = Integer.parseInt(_due);

				io.nextLine();

				Member member = new Member(_name, _plan, NoOfWeek, Fees, Paid, Due, _trainer, _from, _to);
				arr.add(member);
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(AddMember.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return arr;
	}	
	
	public void ArrayListToFile (ArrayList<Member> arr) {
		
		try (PrintWriter p1 = new PrintWriter("member.txt")) {

			try{

				for(int i=0; i<arr.size(); i++) {

					Member a = arr.get(i);

					p1.println(a.name);
					p1.println(a.plan);
					p1.println(a.week);
					p1.println(a.fess);
					p1.println(a.paid);
					p1.println(a.due);
					p1.println(a.trainer);
					p1.println(a.from);
					p1.println(a.to);
					p1.println();
				}

			} catch(NumberFormatException ex){}

		} catch(FileNotFoundException ex){
			Logger.getLogger(AddMember.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
