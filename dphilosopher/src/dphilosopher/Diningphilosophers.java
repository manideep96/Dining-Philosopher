package dphilosopher;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import java.awt.Font;
//import javax.swing.JProgressBar;
//import javax.swing.JSlider;
//import javax.swing.JTree;
//import javax.swing.JSpinner;

public class Diningphilosophers {
	private JFrame frame;	
	static final int NUM = 5;
	Philosopher[] philosophers = new Philosopher[NUM];
	static Semaphore permissions = new Semaphore(2);
	static boolean [] philIsEating = {true, false, false, false, false};
	JLabel[] PhilsLables = new JLabel[5];
	JLabel[] comments = new JLabel[5];
	private JLabel lblNewLabel_0;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Diningphilosophers window = new Diningphilosophers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Diningphilosophers() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0,600, 600);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Button start = new Button("Start");
		start.setBounds(253,280 , 77, 22);
	
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
				}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(start);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setForeground(new Color(0, 0, 0));
		toolBar.setBounds(33, 245, 184, 16);
		frame.getContentPane().add(toolBar);
		
		lblNewLabel_3 = new JLabel("Philosopher 4");
		toolBar.add(lblNewLabel_3);
		comments[3] = lblNewLabel_3;
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBounds(33, 340, 184, 16);
		frame.getContentPane().add(toolBar_1);
		
		lblNewLabel_4 = new JLabel("Philosopher 3");
		toolBar_1.add(lblNewLabel_4);
		comments[4] = lblNewLabel_4;
		
		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setBounds(376, 340, 198, 16);
		frame.getContentPane().add(toolBar_2);
		
		lblNewLabel_2 = new JLabel("Philosopher 2");
		toolBar_2.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		comments[2] = lblNewLabel_2;
		
		JToolBar toolBar_3 = new JToolBar();
		toolBar_3.setBounds(381, 245, 193, 16);
		frame.getContentPane().add(toolBar_3);
		
		lblNewLabel_1 = new JLabel("Philosopher 1");
		toolBar_3.add(lblNewLabel_1);
		comments[1] = lblNewLabel_1;
		
		JToolBar toolBar_4 = new JToolBar();
		toolBar_4.setBounds(199, 192, 209, 16);
		frame.getContentPane().add(toolBar_4);
		lblNewLabel_0 = new JLabel("Philosopher 0");
		toolBar_4.add(lblNewLabel_0);
		comments[0] = lblNewLabel_0;
		
		JLabel lblNewLabel = new JLabel("*Pink indicates eating");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(51, 406, 166, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("*RED indicates thinking");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(410, 408, 174, 14);
		frame.getContentPane().add(lblNewLabel_5);
		}
	
	public void start() {		
		
		Fork[] forks = new Fork[NUM];
		for (int i = 0; i < NUM; i++) {
			forks[i] = new Fork(i);
		}
		for (int i = 0; i < NUM; i++) {
			philosophers[i] = new Philosopher(i, forks[(i + 1) % NUM], forks[i], comments[i]);
			new Thread(philosophers[i]).start();
		}
	}
	public synchronized static boolean leftNeighbourPhilosopher(int id) {
		return Diningphilosophers.philIsEating[(id + 1)
				% Diningphilosophers.NUM]; 
	}
	public synchronized static boolean rightNeighbourPhilosopher(int id) {

		if (id == 0)
			return Diningphilosophers.philIsEating[4]; 
		else
			return Diningphilosophers.philIsEating[(id - 1)
					% Diningphilosophers.NUM]; 
	}
}