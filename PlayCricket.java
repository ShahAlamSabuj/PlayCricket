//import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class Team{
	int wicket;
	int run;
	double over;
	int ball = 1;
	void setWicket(){
		wicket++;
	}
	void setRun(int r){
		run += r;
	}
	void setOver(){
		ball++;
		if(ball <= 6)
		{	over += 0.1;
		}
		else{
			over += 0.5;
			ball = 1;
		}
	}
	int getWicket(){
		return wicket;
	}
	double getOver(){
		double fOver;
		DecimalFormat decim = new DecimalFormat("0.0");
		fOver = Double.parseDouble(decim.format(over));
		return fOver;
	}
	int getRun(){
		return run;
	}
}

class Game {
	static int wicket;
	static double over;
	int runLimit = 999;				

//runLimit for 2nd Team
	Team [] t = new Team[2];
	static int i =0;
	public Game(){
		t[0] = new Team();
		t[1] = new Team();
	}
	String x = "*Team 1 batting first then team 2 ";
	void guessing(){
		int guessResult;
			if(i<2 && t[i].getWicket()<wicket && t[i].getOver()<over && t[i].getRun()<=runLimit){
				guessResult = (int) (Math.random() * 6);
				
				switch(guessResult){
					case 0:{
						wicket(t[i]);
						break;
					}
					case 1:{		
						//run: 1
						System.out.print("run:1 \t");
						x="run:1";
						t[i].setOver();
						t[i].setRun(1);
						break;
					}
					case 2:{		
						//for no ball
						System.out.print("No ball\t");
						x="No Ball";
						t[i].setRun(1);
						freehit(t[i]);
						break;
					}
					case 3:{		
						//run: 2
						System.out.print("run 2\t");
						x="run 2";
						t[i].setOver();
						t[i].setRun(2);
						break;
					}
					case 4:{		
						//run:6
						System.out.print("run:6\t");
						x="Run 6";
						t[i].setOver();
						t[i].setRun(6);
						break;
					}
					case 5:{		
						//white
						System.out.print("White\t");
						x="White";
						t[i].setRun(1);
						break;
					}
					case 6:{		
						//run: 4
						System.out.print("run:4\t");
						x="Run 4";
						t[i].setOver();
						t[i].setRun(4);
						break;
					}
				}
				System.out.print("Over: "+t[i].getOver());
				System.out.print("\tRun: "+t[i].getRun()+"\t");
				System.out.println("wicket: "+t[i].getWicket());
				
			}
			else if(i == 2){
				try{
					Thread.sleep(300);
				}catch (Exception ex){}
					System.exit(0);
			}
			else{
					runLimit = t[i].getRun();
					System.out.println("team:"+(i+1));
					System.out.println("Run: "+t[i].getRun());
					System.out.println("Over: "+t[i].getOver());
					System.out.println("wicket: "+t[i].getWicket());
					System.out.println("---------***-----------");
					if(i==0)
					x="Team 1's turnn is over now Turn for team 2";
					if(i==1)
					result();
					i++;
					}
			}
	
	void wicket(Team t){
		int i = (int) (Math.random()*2);
		switch(i){
		case 0: {			//For Bold
			System.out.print("Bold\t");
				x="Bold";
				t.setOver();
				t.setWicket();
				break;
			}	
		case 1:{			//lbw
				System.out.print("LBW\t");
				x="Lbw";
				t.setOver();
				t.setWicket();
				break;
			}
		case 2:{			//catch
				System.out.print("Catch\t");
				x="Catch";
				t.setOver();
				t.setWicket();
				break;
			}
		}
	}
	void result(){
		if(t[0].getRun() >t[1].getRun() ){
			System.out.println("Team 1 Win!!");
			x="Team 1 Win!!"; 
			}
		else if(t[0].getRun() <t[1].getRun() ){
			System.out.println("Team 2 Win!!");
			x="Team 2 Win!!";
		}
		else{
			System.out.println("Match draw!!");
			x="Match draw!!";
		}
		
	}
	void freehit(Team t){
		int guessNum;
		guessNum = (int) (Math.random()*3);
		switch(guessNum){
			case 0:{			
				//run: 1
				System.out.print("No ball\tFREEHIT run:1\t");
				x="No ball FREEHIT run:1";
				t.setRun(1);
				break;
			}
			case 1:{			
				//run: 2
				System.out.print("No ball\tFREEHIT run:2\t");
				x="No ball FREEHIT run:2";
				t.setRun(2);
				break;
			}
			case 2:{			
				//run:6
				System.out.print("No ball\tFREEHIT run:6\t");
				x="No ball FREEHIT run:6";
				t.setRun(6);
				break;
			}
			case 3:{			
				//run: 4
				System.out.print("No ball\tFREEHIT run:4\t");
				x="No ball FREEHIT run:4";
				t.setRun(4);
				break;
			}
		}
	}
}

public class PlayCricket{
	JFrame frame,begin,overwicket;
	Game g;
	JPanel wpanel= new JPanel();
	JPanel epanel= new JPanel();
	JLabel label1run,label1wicket,label1over;
	JLabel label2run,label2wicket,label2over;
	JLabel event;
	JTextField wicket,over;
	public static void main(String [] args){
		PlayCricket c= new PlayCricket();
		c.go();
	}
	
	void go(){
		g= new Game();

		//-----------begin initialization----------------------------------
		begin = new JFrame("Cricket game version-1.0");
		JMenuBar mnuMain = new JMenuBar();		

				//mnubar initialization
		JMenuItem mnuNewGame = new JMenuItem("New Game"),
		mnuExit = new JMenuItem("Exit");		

				       //mnu item initialization
		mnuMain.add(mnuNewGame);
		mnuMain.add(mnuExit);
		mnuNewGame.addActionListener(new NewGame());
		mnuExit.addActionListener(new Exit());
		begin.getContentPane().add

(BorderLayout.NORTH, mnuMain);
		Welcome w = new Welcome();
		begin.getContentPane().add

(BorderLayout.CENTER, w);
		
		begin.setSize(400,250);
		begin.setVisible(true);
		begin.setDefaultCloseOperation

(JFrame.EXIT_ON_CLOSE);
		
		//---------------initialization---------------------
		overwicket = new JFrame("Over & Wicket setup");
		JPanel wc = new JPanel();
		JPanel mc = new JPanel();
		JPanel dc = new JPanel();
		JLabel askOver = new JLabel("Set total Over Num: ");
		JLabel askWicket = new JLabel("Set total wicket Num: ");
		wicket = new JTextField(2);
		//g.wicket = (int) Integer.parseInt(wicket.getText());	//problem
		
		over = new JTextField(2);
		//g.over = (double) Double.parseDouble(over.getText());	//problem
		
		
		JButton play = new JButton("Play");
		play.addActionListener(new PlayListener());

		wc.add(askOver);
		wc.add(over);
		mc.add(askWicket);
		mc.add(wicket);
		dc.add(play);
	
		overwicket.getContentPane().add

(BorderLayout.NORTH, wc);
		overwicket.getContentPane().add

(BorderLayout.CENTER, mc);
		overwicket.getContentPane().add

(BorderLayout.SOUTH, dc);
		
		
		
		overwicket.setSize(400,250);
		//overwicket.setVisible(true);
		overwicket.setDefaultCloseOperation

(JFrame.EXIT_ON_CLOSE);
		
		// test of getting getText()------------------
		String wi ="wicket no";
		wi = wicket.getText();
		System.out.println(wi);
		String o ="no over";
		o = over.getText();
		System.out.print(o);
		
		
		//-------------frame initialization-----------------------------
		frame = new JFrame("Playground");
		JButton button = new JButton("Ball");
		button.addActionListener(new Listener());
		Pollo p = new Pollo();
		frame.getContentPane().add

(BorderLayout.CENTER, p);
		frame.getContentPane().add

(BorderLayout.SOUTH, button);
		
		event = new JLabel(g.x);
		frame.getContentPane().add
		
		(BorderLayout.NORTH, event);
		
		//wpanel = new JPanel();
		label1run = new JLabel("Run: "+g.t[0].getRun()+"\n");
		label1wicket = new JLabel("wicket: "+g.t[0].getWicket()+"\n");
		label1over = new JLabel("over: "+g.t[0].getOver()+"\n");
		JLabel labelname = new JLabel("Team 1");
		wpanel.add(labelname);
		wpanel.add(label1over);
		wpanel.add(label1run);
		wpanel.add(label1wicket);
		wpanel.setSize(50,500);
		frame.getContentPane().add(BorderLayout.WEST, wpanel);
		
		
		label2run = new JLabel("Run: "+g.t[1].getRun()+"\n");
		label2wicket = new JLabel("wicket: "+g.t[1].getWicket()+"\n");
		label2over = new JLabel("over: "+g.t[1].getOver()+"\n");
		JLabel label2name = new JLabel("Team 2");
		epanel.add(label2name);
		epanel.add(label2over);
		epanel.add(label2run);
		epanel.add(label2wicket);
		epanel.setSize(30,500);
		frame.getContentPane().add(BorderLayout.EAST, epanel);
		
		frame.setSize(1200,700);
		//frame.setVisible(true);
		frame.setDefaultCloseOperation

(JFrame.EXIT_ON_CLOSE);
		
	 }
	 
	 void label(){
		 
		 //wpanel = new JPanel();
		label1run.setText("Run: "+g.t[0].getRun()+"\n");
		label1wicket.setText("wicket: "+g.t[0].getWicket()+"\n");
		label1over .setText("over: "+g.t[0].getOver()+"\n");
		 
		 event.setText(g.x);
		
		label2run.setText("Run: "+g.t[1].getRun()+"\n");
		label2wicket.setText("wicket: "+g.t[1].getWicket()+"\n");
		label2over .setText("over: "+g.t[1].getOver()+"\n");
		
	}
	
	 class PlayListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			g.wicket = Integer.parseInt(wicket.getText());
			g.over = Double.parseDouble(over.getText());
			frame.setVisible(true);
			overwicket.setVisible(false);
		}
	}
	class NewGame implements ActionListener{
		public void actionPerformed(ActionEvent event){
			overwicket.setVisible(true);
			begin.setVisible(false);
		}
	}
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			g.guessing();
			label();
			frame.repaint();
		}
	}
	class Exit implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.exit(0);
		}
	}
}
class Pollo extends JPanel{	
	public void paintComponent(Graphics g){
		Image i = new ImageIcon("cricketfield.JPG").getImage();
		g.drawImage(i,3,4,this);
	}
}

class Welcome extends JPanel{	
	public void paintComponent(Graphics g){
		Image i = new ImageIcon("images.JPG").getImage();
		g.drawImage(i,10,12,this);
	}
}