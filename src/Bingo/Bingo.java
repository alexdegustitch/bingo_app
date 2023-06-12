package Bingo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Bingo extends Frame {

	private Tocak tocak;
	private Tabela tabela;
	private Listici listici;
	private Igraj igra;
	public Button kreni, stani, napravi, igraj, pauza, dodaj;
	private TextField napravi_t, obavestenje;
	public TextField[] tekstovi=new TextField[3];
	public TextArea obv, brojevi;
	private Label broj_komb;
	private Label[] tekst=new Label[3]; 
	private int brojac=0;
	private int[] pomoc=new int[3];
	private boolean flag_pauza=false;
	public String izvuceni_brojevi="";
	private Listic listic;
	
	
	{
		kreni=new Button("kreni");
		stani=new Button("stani");
		stani.setEnabled(false);
		napravi=new Button("napravi");
		igraj=new Button("igraj");
		pauza=new Button("pauza");
		dodaj=new Button("dodaj");
	}
	
	public Bingo() {
		super("BINGO");
		setSize(1000, 600);
		
		tabela=new Tabela();
		tocak=new Tocak(tabela);
		listici=new Listici(tocak);
		igra=new Igraj(tocak, tabela, listici, this);
		
		popuniProzore();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				tocak.stani();
				dispose();
			}
		});
		
		setVisible(true);
		
	}
	
	private void popuniProzore() {
		Panel panel=new Panel(new BorderLayout());
		Panel plo=new Panel(new BorderLayout());
		
		plo.add(tocak, "North");
		plo.add(tabela, "Center");
		panel.add(plo, "Center");
		
		napravi_t=new TextField("73410", 10);
		napravi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listici.napravi(Integer.parseInt(napravi_t.getText()));
				
			}
		});
		
		plo=new Panel();
		
		/*obavestenje=new TextField(30);
		obavestenje.setEnabled(false);
		plo.add(obavestenje);*/
		//plo.add(napravi);
		broj_komb=new Label("Broj kombinacija:");
		plo.add(broj_komb);
		plo.add(napravi_t);
		
		panel.add(plo, "North");
		
		Panel ploca=new Panel(new BorderLayout());
		plo=new Panel(new GridLayout(0, 2));
		
		tekst[0]=new Label("Bingo listica sa 13 pogodaka ima: ");
		plo.add(tekst[0]);
		tekstovi[0]=new TextField("");
		tekstovi[0].setFont(new Font(null, Font.BOLD, 40));
		tekstovi[0].setEnabled(false);
		plo.add(tekstovi[0]);
		tekst[1]=new Label("Bingo listica sa 14 pogodaka ima: ");
		plo.add(tekst[1]);
		tekstovi[1]=new TextField("");
		tekstovi[1].setFont(new Font(null, Font.BOLD, 40));
		tekstovi[1].setEnabled(false);
		plo.add(tekstovi[1]);
		tekst[2]=new Label("Bingo listica sa 15 pogodaka ima: ");
		plo.add(tekst[2]);
		tekstovi[2]=new TextField("");
		tekstovi[2].setFont(new Font(null, Font.BOLD, 40));
		tekstovi[2].setEnabled(false);
		plo.add(tekstovi[2]);
		
		ploca.add(plo, "Center");
		obv=new TextArea( 5, 45);
		
		
		obv.setEnabled(false);
		obv.setFont(new Font(null, Font.ITALIC, 21));
	
		
		ploca.add(obv, "North");
		
		brojevi=new TextArea("", 7, 45, TextArea.SCROLLBARS_NONE);
		brojevi.setEditable(false);
		brojevi.setFont(new Font(null, Font.ITALIC, 21));
		
		ploca.add(brojevi, "South");
		
		panel.add(ploca, "West");
		
	
		plo=new Panel();
		
		kreni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listici.napravi(Integer.parseInt(napravi_t.getText()));
				tabela.restart();
				tocak.kreni();
				String s="";
				obv.setText(s);
				kreni.setEnabled(false);
				stani.setEnabled(true);
				
			}
		});
		plo.add(kreni);
		
		stani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				tocak.stani();
				brojac++;
				String s=tocak.broj();
				izvuceni_brojevi+=s + "    ";
				brojevi.setText(izvuceni_brojevi);
				int broj=Integer.parseInt(s);
				
				listici.pretrazi(broj);
				pomoc=listici.prebroj();
				for(int i=0;i<pomoc.length;i++)
					tekstovi[i].setText(Integer.toString(pomoc[i]));
				String ss= brojac + "." + " izvuceni broj je " + tocak.broj();
				obv.setText(ss);
				
				tocak.kreni();
				if(Integer.parseInt(tekstovi[2].getText())>0) {
					izvuceni_brojevi="";
					
					tocak.restart(s);
					tocak.stani();
					stani.setEnabled(false);
					kreni.setEnabled(true);
					brojac=0;
				}
				
			}
		});
		
		plo.add(stani);

		igraj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listici.napravi(Integer.parseInt(napravi_t.getText()));
				kreni.setEnabled(false);
				stani.setEnabled(false);
				igra.kreni();
				
		
				
			}
		});
		
		plo.add(igraj);

		pauza.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(flag_pauza==false) {
					igra.pauza();
					flag_pauza=true;
				}
				else {
					igra.odpauziaraj();
					flag_pauza=false;	
				}
				
			}
		});
		
		plo.add(pauza);
		
		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listic=new Listic();
				listic.setVisible(true);
				int[] komb_brojevi=listic.vrati();
				listici.dodaj(komb_brojevi);
				
			}
		});
		
		plo.add(dodaj);
		panel.add(plo, "South");
			
		add(panel);
		
	}
	
	public class Listic extends Frame{
		Label labela=new Label("Uneti zeljenje brojeve: ");
		TextField[] tekst=new TextField[15];
		
		
		public Listic() {
			super("Bingo listic");
			setSize(1000, 100);
			Panel plo=new Panel();
			plo.setLayout(new GridLayout(1, 16));
			plo.add(labela);
			for(int i=0;i<tekst.length;i++) {
				tekst[i]=new TextField("0", 3);
				plo.add(tekst[i]);
			}
			add(plo);
			setVisible(false);
			
			this.addWindowListener(new WindowAdapter() {
				
				@Override
				public void windowClosing(WindowEvent e) {
					setVisible(false);
				}
			
			});
		}
		
		public int[] vrati() {
			int[] pom=new int[15];
			for(int i=0;i<tekst.length;i++) 
				pom[i]=Integer.parseInt(tekst[i].getText());
			return pom;
		}
	}
	
	public static void main(String[] args) {
		new Bingo();
	}
}
