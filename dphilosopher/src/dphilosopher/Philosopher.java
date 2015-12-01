package dphilosopher;
import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;
public class Philosopher implements Runnable {
		public int id;
	Fork leftFork, rightFrok; 
	private JLabel  comments; 
	private Random randomPeriod = new Random(); 
	int maxPeriod = 2000; 
	int numberOfMeals = 0;
	public Philosopher(int id, Fork leftFork, Fork rightFrok, JLabel comments) {
		super();
		this.id = id;
		this.leftFork = leftFork;
		this.rightFrok = rightFrok;
		this.comments = comments;
	}
	public void run() {
		while (true) {
			try {
				this.think();
				this.hungry();
				this.eat();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void think() throws InterruptedException {
	
		comments.setText("Philosopher "+id+" is Thinking");
		comments.setForeground(Color.RED);
		Thread.sleep(randomPeriod.nextInt(maxPeriod));
	}
	public void hungry() throws InterruptedException {
		while (Diningphilosophers.leftNeighbourPhilosopher(id) || Diningphilosophers.rightNeighbourPhilosopher(id)) {
			Thread.sleep(randomPeriod.nextInt(10));
		}
		Diningphilosophers.permissions.acquire();
		leftFork.pickUp();
		rightFrok.pickUp();
		Diningphilosophers.philIsEating[id] = true;
	}

	private void eat() throws InterruptedException {
		
		comments.setText("Philosopher "+id+" is Eating");
		comments.setForeground(Color.MAGENTA);
		
		Thread.sleep(randomPeriod.nextInt(maxPeriod));
		leftFork.putDown();
		rightFrok.putDown();
		Diningphilosophers.permissions.release();
		Diningphilosophers.philIsEating[id] = false;
	}
}
