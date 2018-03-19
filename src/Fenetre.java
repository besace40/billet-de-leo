import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Fenetre extends JFrame implements ActionListener{ //Implements ActionListener car nous utiliserons des méthodes ActionPerformed
	
	// La Fenêtre et ce qu'elle contient ! 
	private JPanel pan = new JPanel(); //Un JPanel pour le fond orange
	private JPanel radio = new JPanel(); //Un JPanel pour les boutons radios
	private ButtonGroup bg = new ButtonGroup(); //un ButtonGroup pour rassembler les boutons radios
	
	private JOptionPane erreur = new JOptionPane(); //Une fenêtre d'erreur si l'adresse mail n'est pas valide
	private	JOptionPane confirmation = new JOptionPane();//Une fenêtre de confirmation de réservation
	
	private JButton bouton = new JButton("Valider"); //Création du bouton "valider"
	private JLabel titre = new JLabel("Veuillez entrer vos coordonnées : "); //Tout les JLabel qui permettent d'entrer du texte
	private JLabel tvide = new JLabel("         "); //Espacer les champs
	private JLabel tvide2= new JLabel("         ");
	private JLabel tvide3= new JLabel("         ");
	private JLabel tvide4= new JLabel("         ");
	private JLabel tvide5= new JLabel("         ");
	private JLabel tvide6= new JLabel("         ");
	private JLabel nom = new JLabel("Nom : ");
	private JLabel prenom = new JLabel("Prénom :  ");
	private JLabel mail = new JLabel("Adresse mail :  ");
	private JLabel concert = new JLabel("Choix du concert : ");
	
	private JTextField  cnom,cprenom, cmail; //JTextfield permet les champs de saisie
	private JComboBox combo = new JComboBox();//JCombobox permet la création de liste déroulante
	private JRadioButton jr1 = new JRadioButton("Place assise");//JRadioButton, création des deux boutons avec deux choix
	private JRadioButton jr2 = new JRadioButton("Place debout");
	
	//Constructeur de fenêtre 
	public Fenetre(){
		jr1.setSelected(true);//De base, jr1 sera coché

		this.setTitle("Ma billeterie");//Titre fenêtre
		this.setSize(500, 400);//Taille fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Se ferme avec la croix
		this.setLocationRelativeTo(null);//Centre l'application
		
		//Groupage des boutons radios
		bg.add(jr1);//Bg intègre les deux boutons, pour qu'on puisse en choisir qu'un seul lors de la réservation
		bg.add(jr2);
		radio.add(jr1);//Ajout des boutons au JPannel radio pour configurer leur apparence
		radio.add(jr2);
		
		//Paramètrage des champ de texte utilisateur
		nom.setMaximumSize(new Dimension(100,100));
		prenom.setMaximumSize(new Dimension(100,100));
		mail.setMaximumSize(new Dimension(100,100));
		
		//Texte dans les champs de saisie	
		cnom = new JTextField ("");
		cprenom = new JTextField ("");
		cmail = new JTextField("");
		
		//Paramètrage des champs, adapté à la fenêtre
		
		cnom.setMaximumSize(new Dimension(320,50));
		cprenom.setMaximumSize(new Dimension(320,50));
		cmail.setMaximumSize(new Dimension(320,50));
		
		//Paramètrage du JComboBox(liste déroulante)
		combo.setMaximumSize(new Dimension(320, 50));//longueur puis largeur
		combo.addItem("Bon Entendeur - 20/01/2018 - 55 € - Le Brise Glace");
		combo.addItem("Kenza Farah - 10/04/2018 - 65 € - Le summum");
		combo.addItem("Thibault Gomes - 15/06/2018 - 69€ - Bonlieu");
		combo.addItem("Tom Morello - 19/12/2018 - 109€ - Stade des alpes");
		combo.addItem("Vald - 31/09/2018 - 49€ - Le summum");
		
		//Ajout des élèments dans les BOX 
		pan.setBackground(Color.orange);//Couleur du fond
		radio.setBackground(Color.orange);//Couleur du fond des bouttons radio
		
		
			//Création de box, 7 horizontales, 1 verticales dans laquelle les 7 autres sont regroupés
			Box b1 = Box.createHorizontalBox();
			b1.add(titre);
		
			Box vide = Box.createHorizontalBox();
			vide.add(tvide);
		
			Box b2 = Box.createHorizontalBox();
			b2.add(nom);
			b2.add(cnom);
		
			Box vide2 = Box.createHorizontalBox();
			vide2.add(tvide2);
		
			Box b3 = Box.createHorizontalBox();
			b3.add(prenom);
			b3.add(cprenom);
		
			Box vide3 = Box.createHorizontalBox();
			vide3.add(tvide3);
		
			Box b4 = Box.createHorizontalBox();
			b4.add(mail);
			b4.add(cmail);
		
			Box vide4 = Box.createHorizontalBox();
			vide4.add(tvide4);
		
			Box b5 = Box.createHorizontalBox();
			b5.add(concert);
			b5.add(combo);
		
			Box vide5 = Box.createHorizontalBox();
			vide5.add(tvide5);
		
			Box b6 = Box.createHorizontalBox();
			b6.add(radio);
		
		
			Box vide6 = Box.createHorizontalBox();
			vide6.add(tvide6);
		
			Box b7 = Box.createHorizontalBox();
			b7.add(bouton);
		
		//Parametrage du Listener, méthode lié au clic sur le bouton et validation du formulaire
		bouton.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent arg0) { 
				
				//Variable de verification
				String verif1 = cmail.getText(); 
				String verif2 = cnom.getText();
				String verif3 = cprenom.getText();
				
				if (verif1.isEmpty() || verif2.isEmpty() || verif3.isEmpty()) {//Si au moins un des champs est vide
					System.out.println("Tout les champs ne sont pas remplis");
					erreur.showMessageDialog(null, "Vous n'avez pas saisi tout les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else {
				
				
					if (verif1.contains("@") && verif1.contains(".")) {//Si il y a un @ et un "." au champ de mail
						System.out.println("Résumé de la reservation : ");
						String n1 = cnom.getText();
						System.out.println("Votre nom : " + n1);
						String n2 = cprenom.getText();
						System.out.println("Votre prénom : " + n2);
						String n3 = cmail.getText();
						System.out.println("Votre e-mail : "+n3);
						String n4 = (String) combo.getSelectedItem();
						System.out.println("Votre concert : "+n4);
						jr1.setActionCommand("Place assise");
						jr2.setActionCommand("Place debout");
						String n5 = bg.getSelection().getActionCommand();
						System.out.println("Type de place : "+n5);
						
						confirmation.showMessageDialog(null,"Place reservée ! Vous êtes "+n2+" "+n1 +"\nVotre mail est : "+n3+"\nVous irez au conert suivant : "+n4,"Récapitulatif",JOptionPane.INFORMATION_MESSAGE); //Message de confirmation						
					}else {
						System.out.println("L'adresse e-mail n'est pas valide. "); //Si le mail n'est pas conforme
						erreur.showMessageDialog(null, "Vous n'avez pas saisi d'adresse mail correcte", "Erreur", JOptionPane.ERROR_MESSAGE);
						
					}
				
				if(bg.getSelection().getActionCommand() =="Place assise") {//Si place assise est cochée
					String radio2 = "place assise";
					
					try {
						Class.forName("org.postgresql.Driver");
						System.out.println("Driver O.K.");
						
						String url = "jdbc:postgresql://localhost:5432/billeterie";//lien avec ma BDD
						String user = "postgres";
						String passwd = "jadorelasi";
						
						Connection conn = DriverManager.getConnection(url, user, passwd); //pont entre java et la BDD
						System.out.println("Connexion effective !");         
						
						Statement state = conn.createStatement(); //création objet statement
						state.executeUpdate("INSERT INTO reservations(nom,prenom,mail,concert,type_place) VALUES('"+cnom.getText()+"','"+cprenom.getText()+"','"+cmail.getText()+"','"+combo.getSelectedItem()+"','"+radio2+"')");//Remplir les colonnes de la table réservation
						
						
						
						
						
					} catch (Exception e) {
						e.printStackTrace();//Montre l'erreur de l'exception
						System.out.println("Erreur lors de la connexion avec la BDD");
						
					}  
				} else {//Si place debout est cochée
					String radio2 = "place debout";
					
					try {
						Class.forName("org.postgresql.Driver");
						System.out.println("Driver O.K.");
						
						String url = "jdbc:postgresql://localhost:5432/billeterie";
						String user = "postgres";
						String passwd = "jadorelasi";
						
						Connection conn = DriverManager.getConnection(url, user, passwd); //pont entre java et la BDD
						System.out.println("Connexion effective !");         
						
						Statement state = conn.createStatement(); //création objet statement
						state.executeUpdate("INSERT INTO reservations(nom,prenom,mail,concert,type_place) VALUES('"+cnom.getText()+"','"+cprenom.getText()+"','"+cmail.getText()+"','"+combo.getSelectedItem()+"','"+radio2+"')");
						
						
						
						
						
					} catch (Exception e) {
						e.printStackTrace();//Montre l'erreur de l'exception
						System.out.println("Erreur lors de la connexion avec la BDD");
						
					}  
				}
				}
				
				
				
			}});
		
		
		
		
			//Box verticale qui comprend les 7 horizontales
			Box b8 = Box.createVerticalBox();
			b8.add(b1);
			b8.add(vide);
			b8.add(b2);
			b8.add(vide2);
			b8.add(b3);
			b8.add(vide3);
			b8.add(b4);
			b8.add(vide4);
			b8.add(b5);
			b8.add(vide5);
			b8.add(b6);
			b8.add(vide6);
			b8.add(b7);
		
		
		
		
		
		this.setContentPane(pan); //Pan est le JPanel de JFrame
		
		this.getContentPane().add(b8); //La fenêtre adopte b8 comme Pane également
		
		this.setVisible(true); //La fenêtre est visible
		
	}
	
	
	
	@Override 
	public void actionPerformed(ActionEvent arg0) { // Inutile, mais fait marcher la classe
		// TODO Auto-generated method stub
		
	}
	
	
}
