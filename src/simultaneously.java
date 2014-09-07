/*
* Simultaneous functions adding and removing from Queue
* @Author: Jamie Yiw
* @Date: 07/09/2014
*/

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class simultaneously {

	public static void main(String[] args) {
		
		final Deque<Person> myQueue = new LinkedList<Person>();
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		//Thread to add person to Queue
		Thread addPerson = new Thread() {
			@Override
			public void run() {
				for (int y = 0; y < 100000; ++y) {
					try {
						sleep(15);
						Date birthday = null;
						
						try{
							birthday = sdf.parse("03/08/1984");
					    } catch (ParseException e) {
					        System.out.println(e.toString());
					        e.printStackTrace();
					    }
						Person personObj = new Person("Jamie", 31, "M", birthday);
						myQueue.addLast(personObj);

					} catch (InterruptedException ex) {
					}
				}
			}
		};
		//Thread to remove person from Queue
		Thread RemovePerson = new Thread() {
			@Override
			public void run() {
				for (int x = 0; x < 100000; ++x) {
					try {
						sleep(10);
						if (!myQueue.isEmpty()) {
							myQueue.removeFirst();
						}
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		
		addPerson.start(); 	
		RemovePerson.start(); 
	}
}

//Person Object
class Person{

	String name;
	Integer age;
	String gender;
	Date birthday;
	
	//Constructor
	Person(String _name, Integer _age, String _gender, Date _birthday){
		name = _name;
		age = _age;
		gender = _gender;
		birthday = _birthday;
	}	
}
