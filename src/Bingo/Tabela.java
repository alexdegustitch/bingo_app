package Bingo;

import java.awt.*;

public class Tabela extends Panel {
	
	private Label[] labele=new Label[90];
	private int[] niz=new int[90];
	
	public Tabela() {
		
		setLayout(new GridLayout(9, 10));
		for(int i=0;i<90;i++) {
			labele[i]=new Label(Integer.toString(i+1), Label.CENTER);
			labele[i].setBackground(Color.GRAY);
			add(labele[i]);
			niz[i]=0;
		}
	}
	
	public void postavi(int num) {
		labele[num].setBackground(Color.RED);
		niz[num]=1;
	}
	
	public void restart() {
		for(int i=0;i<labele.length;i++) {
			labele[i].setBackground(Color.GRAY);
			niz[i]=0;
		}
	}
	

	
}
