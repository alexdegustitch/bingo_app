package Bingo;

import java.awt.Color;

public class Igraj extends Thread {

	
	private boolean radi=false;
	private Tocak tocak;
	private Tabela tabela;
	private Listici listici;
	private Bingo bingo;
	private int[] pomoc=new int[3];
	private int brojac=0;
	private boolean pocetak=true;
	private boolean pauza=false;
	
	public Igraj(Tocak t, Tabela tab, Listici l, Bingo b) {
		tocak=t;
		tabela=tab;
		listici=l;
		bingo=b;
		start();
	}
	
	@Override
	public void run() {
		try {
			while(!interrupted()) {
				synchronized (this) {
					if(!radi) wait();
				}
				
				if(pocetak==true) {
					tabela.restart();
					pocetak=false;
				}
				
				tocak.kreni();
				
				sleep(7000);
				
				tocak.stani();
			
				
				String s=tocak.broj();
	
				bingo.izvuceni_brojevi+=s + "    ";
				bingo.brojevi.setText(bingo.izvuceni_brojevi);
				brojac++;
				int pom=Integer.parseInt(s);
				String ss= brojac + "." + " izvuceni broj je " + tocak.broj();
				bingo.obv.setText(ss);
				listici.pretrazi(pom);
				pomoc=listici.prebroj();
		
				for(int i=0;i<pomoc.length;i++) {
					bingo.tekstovi[i].setText(Integer.toString(pomoc[i]));
				
				}
				
				if(Integer.parseInt(bingo.tekstovi[2].getText())>0) {
					bingo.kreni.setEnabled(true);
					bingo.stani.setEnabled(true);
					tocak.restart(s);
					bingo.izvuceni_brojevi="";
					
					brojac=0;
					pocetak=true;
					stani();
				}
				
			}
			
		}catch(InterruptedException ie) {}
	}
	
	public synchronized void kreni() {
		
		radi=true;
		notifyAll();
	}
	
	public synchronized void stani() {
		
		radi=false;
	}
	
	public void unisti() {
		interrupt();
	}
	public void pauza() {
		suspend();
	}
	public void odpauziaraj() {
		resume();
	}
}
