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
		
		test.enqueue(sol36.new Dog("Dog", test.strToDate("2011-02-03")));
		System.out.print("Enqueue: ");
		for (Dog each : test.dogs)
			System.out.print(each.getDate() + "     ");
		System.out.println();
		Animal temp = sol36.new Animal();
		temp = test.dequeueCats();
		System.out.println("DequeueCats: "+ temp.getName() + " - " + temp.getDate());
		temp = test.dequeueDogs();
		System.out.println("DequeueDogs: " + temp.getName() + " - " + temp.getDate());
		temp = test.dequeueAny();
		System.out.println("DequeueAny: " + temp.getName() + " - " + temp.getDate());
	}
	
	class AnimalQueue
	{
		LinkedList<Dog> dogs = new LinkedList<>();
		LinkedList<Cat> cats = new LinkedList<>();
		void enqueue (Animal animal)
		{
			if (animal instanceof Dog)
				dogs.add((Dog) animal);
			else if (animal instanceof Cat)
				cats.add((Cat) animal);
		}
		// Constructor
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
		Dog dequeueDogs ()
		{
			return dogs.poll();
		}
		Cat dequeueCats ()
		{
			return cats.poll();
		}
		void setDogDate(String[] dogsDate)
		{
			for (int i = 0; i < dogsDate.length; i ++)
			{
				Dog newDog = new Dog ("Dog", strToDate(dogsDate[i]));
				dogs.add(newDog);
			}
		}
		void setCatDate(String[] catsDate)
		{
			for (int i = 0; i < catsDate.length; i ++)
			{
				Cat newCat = new Cat ("Cat", strToDate(catsDate[i]));
				cats.add(newCat);
			}
		}
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
	class Dog extends Animal
	{
		Dog() {}
		Dog(String name, Date d) {super(name, d);}
	}
	class Cat extends Animal
	{
		Cat() {}
		Cat(String name, Date d) {super(name, d);}
	}
}