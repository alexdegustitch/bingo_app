package Bingo;

import java.util.Random;

public class Listici {

	public int[][] listici;
	private int[] listic = new int[15];
	private int vrsta;
	private int[] pomoc=new int[3];
	private int[] help;
	private Tocak tocak;
	Random rand =new Random();
	 { rand.setSeed(System.currentTimeMillis()); }
	
	
	public Listici(Tocak t) {
		tocak=t;
		listici=new int[1][1];
	}
	
	public int[] prebroj() {
		for(int i=0;i<3;i++)
			pomoc[i]=0;
		for(int i=0;i<vrsta;i++)	
			if(listici[i][15]>12) {
				switch(listici[i][15]) {
				case 13: pomoc[0]++; break;
				case 14: pomoc[1]++; break;
				case 15: pomoc[2]++; break;
				}
				
			}
		
		return pomoc;
	}
	
	public void pretrazi(int br) {
		
		for(int i=0;i<vrsta;i++) 
			for(int j=0;j<15;j++)
				if(listici[i][j]==br) {
					listici[i][j]=0;
					listici[i][15]++;
					break;
				}
		
	}
	

	public void napravi(int vrsta) {
		listici=new int[vrsta][16];
		int[] pom=new int[15];
		this.vrsta=vrsta;
		for(int i=0; i<vrsta; i++) {
			pom=napraviListic();
			for(int j=0;j<pom.length;j++)
				listici[i][j]=pom[j];
			listici[i][15]=0;
			}
	}
	
	private int[] napraviListic() {
		boolean flag=false;
		
		for(int i=0;i<listic.length;i++) {
			flag=false;
	
			int broj = rand.nextInt(90)+1;
			for(int j=0;j<i;j++)
				if(listic[j]==broj) {
					flag=true;
					break;
				}
			
			if(flag==true)
				i--;
			else
				listic[i]=broj;
			
		}
		

		sortiraj();
		
		return listic;
	}
	
	public void dodaj(int[] pom) {
		napravi(vrsta);
		int pomoc=vrsta+1;
		int[][] pomocni_niz=new int[pomoc][16];
		for(int i=0;i<listici.length;i++)
			for(int j=0;j<16;j++) {
				pomocni_niz[i][j]=listici[i][j];
				listici[i]=null;
			}
		
		for(int i=0;i<15;i++)
			pomocni_niz[vrsta][i]=pom[i];
		pomocni_niz[vrsta][15]=0;
		
		listici=null;
		listici=new int[pomoc][16];
		listici=pomocni_niz;
		pomocni_niz=null;
		vrsta++;
		
	}
	
	private void sortiraj() {
		for(int i=0;i<listic.length-1;i++)
			for(int j=i+1;j<listic.length;j++)
				if(listic[i]>listic[j]) {
					int temp=listic[i];
					listic[i]=listic[j];
					listic[j]=temp;
				}
	}
	
	public int[] pobeda() {
		help=new int[vratiBroj()];
		int pop=0;
		for(int i=0;i<vrsta;i++)
			if(listici[i][15]==14) {
				int p=0;
				while(listici[i][p]==0)
					p++;
				help[pop++]=listici[i][p];
			}
		return help;
	}
	
	public int vratiBroj() {
		int pop=0;
		for(int i=0;i<vrsta;i++)
			if(listici[i][15]==14) 
				pop++;
		return pop;
	}
}
