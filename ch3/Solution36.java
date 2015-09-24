package chapter3;
/*
 * Implementation Method:
 * Have two queues to save info for cats and dogs separately. If want to get any animal, just compare the date
 * of the cat and the dog in each queue and return the one with most earlier date.
 */
import java.util.*;
import java.text.*;
public class Solution36 {
	public static void main(String[] args) {
		Solution36 sol36 = new Solution36();
		AnimalQueue test = sol36.new AnimalQueue();
		// create queue for cats and dogs
		String[] catsDate = new String[] {"1999-11-23", "2001-01-03", "2005-03-04"};
		String[] dogsDate = new String[] {"1989-11-23", "2001-01-13", "2004-03-04"};
		test.setCatDate(catsDate);
		test.setDogDate(dogsDate);
		// add one more dog
		test.enqueue(sol36.new Dog("Dog", test.strToDate("2011-02-03")));
		// show results after enqueue one dog
		System.out.print("Enqueue of Dogs: ");
		for (Dog each : test.dogs)
			System.out.print(each.getDate() + "     ");
		System.out.println();
		Animal temp = sol36.new Animal();
		// test the function to get one cat from queue
		temp = test.dequeueCats();
		System.out.println("DequeueCats: "+ temp.getName() + " - " + temp.getDate());
		// test the function to get one dog from queue
		temp = test.dequeueDogs();
		System.out.println("DequeueDogs: " + temp.getName() + " - " + temp.getDate());
		// test the function to get one animal, no matter dog or cat, from queue
		temp = test.dequeueAny();
		System.out.println("DequeueAny: " + temp.getName() + " - " + temp.getDate());
	}
	// aminalQueue class, which includes both Dogs queue and Cats queue
	class AnimalQueue
	{
		LinkedList<Dog> dogs = new LinkedList<>();
		LinkedList<Cat> cats = new LinkedList<>();
		// add more animal into queue
		void enqueue (Animal animal)
		{
			if (animal instanceof Dog)
				dogs.add((Dog) animal);
			else if (animal instanceof Cat)
				cats.add((Cat) animal);
		}
		// get any animal from queue
		Animal dequeueAny ()
		{
			if (dogs.size() == 0)
				return cats.poll();
			else if (cats.size() == 0)
				return dogs.poll();
			if (dogs.peek().earlier(cats.peek().getDate()))
				return dogs.poll();
			else
				return cats.poll();
		}
		// get one dog
		Dog dequeueDogs ()
		{
			return dogs.poll();
		}
		// get on cat
		Cat dequeueCats ()
		{
			return cats.poll();
		}
		// function to set dog queue
		void setDogDate(String[] dogsDate)
		{
			for (int i = 0; i < dogsDate.length; i ++)
			{
				Dog newDog = new Dog ("Dog", strToDate(dogsDate[i]));
				dogs.add(newDog);
			}
		}
		// function to set cat queue
		void setCatDate(String[] catsDate)
		{
			for (int i = 0; i < catsDate.length; i ++)
			{
				Cat newCat = new Cat ("Cat", strToDate(catsDate[i]));
				cats.add(newCat);
			}
		}
		// Turn string to Date Object
		Date strToDate (String s)
		{
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateObject = null;
			try {
				dateObject = sdf.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return dateObject;
		}
	}
	// Animal class definition
	class Animal
	{
		private Date date;
		private String category;
		Animal() {};
		Animal(String name) {category = name;}
		Animal(String name, Date d) {category = name; date = d;}
		public Date getDate() {return date;}
		public String getName() {return category;}
		public boolean earlier (Date d) {return date.before(d);}
	}
	// Animal Dog definition extend from Animal class
	class Dog extends Animal
	{
		Dog() {}
		Dog(String name, Date d) {super(name, d);}
	}
	// Animal Cat definition extend from Animal class
	class Cat extends Animal
	{
		Cat() {}
		Cat(String name, Date d) {super(name, d);}
	}
}