package Bingo;

import java.awt.*;
import java.util.Random;

public class Tocak extends Panel implements Runnable {
	
	private static int[] niz=new int[90];
	private boolean radi=false;
	private Thread nit=new Thread(this);
	private Label label=new Label("90", Label.CENTER);
	private Tabela tabela;
	Random rand =new Random();
	{ rand.setSeed(System.currentTimeMillis()); }
	
	public Tocak(Tabela tab) {
		setSize(150000, 70000);
		setBackground(Color.YELLOW);

		
		label.setFont(new Font(null, Font.BOLD, 30));
		add(label, "Center");
		for(int i=0;i<90;i++)
			niz[i]=0;
		tabela=tab;
		setVisible(true);
		
		nit.start();
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					if(!radi) wait();
				}
				
				radnja();
				Thread.sleep(10);
			}
			
		}catch(InterruptedException ie) {}
		
	}
	
	public void radnja() {
		int number=rand.nextInt(90)+1;
		
		while(niz[number-1]==1)
			number=rand.nextInt(90)+1;
		
		label.setText(Integer.toString(number));	
	}
	
	public synchronized void kreni() {
		setBackground(Color.WHITE);
		radi=true;
		notifyAll();
	}
	
	public synchronized void stani() {
		setBackground(Color.GREEN);
		radi=false;
		int broj=Integer.parseInt(label.getText());
		niz[broj-1]=1;
		
		tabela.postavi(broj-1);
	}
	
	public void zavrsi() {
		nit.interrupt();
		
	}
	
	public String broj() {
		return label.getText();
	}
	
	public void restart(String s) {
		label.setText(s);
		for(int i=0; i<niz.length;i++)
			niz[i]=0;
	}

}
