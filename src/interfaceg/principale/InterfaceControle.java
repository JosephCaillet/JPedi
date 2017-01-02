package interfaceg.principale;

import interfaceg.boiteDialogue.*;

import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;

public class InterfaceControle extends JFrame implements ActionListener, ChangeListener
{
	//fichier image
	private File fichierImg;
	//img actuelle
	private BufferedImage imgEnCour;
	private BufferedImage imgOriginale;
	//Panneau d'apercu
	private Apercu apercu;

	//COMPOSANT INTERFACES
	//Composants barre de menu
	private JMenuBar barreMenu = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenu edition = new JMenu("Edition");
	private JMenu affichage = new JMenu("Affichage");
	private JMenu retouche = new JMenu("Retouches");
	private JMenu effet = new JMenu("Effets");
	private JMenu aide = new JMenu("?");
	//Composant menu fichiers
	private JMenuItem f_ouvrir = new JMenuItem("Ouvrir");
	private JMenuItem f_enregistrer = new JMenuItem("Enregistrer");
	private JMenuItem f_enregistrerSous = new JMenuItem("Enregistrer Sous");
	private JMenuItem f_fermer = new JMenuItem("Fermer");
	private JMenuItem f_quitter = new JMenuItem("Quitter");
	//Composant menu edition
	private JMenuItem ed_restaurer = new JMenuItem("Restaurer la dernière sauvegarde");
	//Composant menu affichage
	private JMenuItem a_pack = new JMenuItem("Taille automatique");
	private JMenuItem a_centrer = new JMenuItem("Centrer la fenêtre");
	private JLabel a_zoomLabel = new JLabel("Zoom:");
	private JLabel a_zoomLabel2 = new JLabel("%");
	private JSpinner a_zoomSpinner = new JSpinner( new SpinnerNumberModel(100, 1, 3000, 1));
	//Composant menu retouches
	private JMenuItem r_luminosite = new JMenuItem("Luminosité");
	private JMenuItem r_RVB = new JMenuItem("Composantes RVB");
	private JMenuItem r_ret = new JMenuItem("Retournement");
	//Composant menu effets
	private JMenuItem ef_nb = new JMenuItem("Noir et Blanc");
	private JMenuItem ef_sepia = new JMenuItem("Sepia");
	private JMenuItem ef_invertColor = new JMenuItem("Inversion des couleurs");
	private JMenuItem ef_seuillageNB = new JMenuItem("Seuillage Noir et Blanc");
	private JMenuItem ef_seuillageRVB = new JMenuItem("Seuillage RVB");
	private JMenuItem ef_nuances = new JMenuItem("Nuances");
	private JMenuItem ef_decalage_RVB = new JMenuItem("Décalage RVB");
	
	//CONSTRUCTEUR
	public InterfaceControle()
	{
		super("JPedi");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.creerInterface();
		enableMenu(false);
		this.pack();
		this.setMinimumSize(new Dimension(this.getWidth(),150));
		this.setVisible(true);
		this.fichierImg = null;
	}
	
	private void creerInterface()
	{
		//Pour chaque menu, on ajoute les listener à chaque elements du menu,on les ajoutes au menu, puis on met les racourcis claviers.
		
		//menu fichier
		f_ouvrir.addActionListener(this);
		f_enregistrer.addActionListener(this);
		f_enregistrerSous.addActionListener(this);
	 	f_fermer.addActionListener(this);
		f_quitter.addActionListener(this);
		
		fichier.add(f_ouvrir);
		fichier.add(f_enregistrer);
		fichier.add(f_enregistrerSous);
		fichier.add(f_fermer);
		fichier.addSeparator();
		fichier.add(f_quitter);
		
		fichier.setMnemonic('F');
		f_ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		f_enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		f_enregistrerSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
	 	f_fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
		f_quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		
		//menu edition	
		ed_restaurer.addActionListener(this);
		
		edition.add(ed_restaurer);
		
		edition.setMnemonic('D');
		ed_restaurer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		
		//menu affichage
		a_pack.addActionListener(this);
		a_centrer.addActionListener(this);
		a_zoomSpinner.addChangeListener(this);
		
		affichage.add(a_pack);
		affichage.add(a_centrer);

		
		affichage.setMnemonic('A');
		
		//menu retouches
		r_luminosite.addActionListener(this);
		r_RVB.addActionListener(this);
		r_ret.addActionListener(this);
		
		retouche.add(r_luminosite);
		retouche.add(r_RVB);
		retouche.add(r_ret);
		
		retouche.setMnemonic('R');
		
		//menu effets
		ef_nb.addActionListener(this);
		ef_sepia.addActionListener(this);
		ef_invertColor.addActionListener(this);
		ef_seuillageNB.addActionListener(this);
		ef_seuillageRVB.addActionListener(this);
		ef_nuances.addActionListener(this);
		ef_decalage_RVB.addActionListener(this);
		
		effet.add(ef_nb);
		effet.add(ef_sepia);
		effet.add(ef_invertColor);
		effet.add(ef_seuillageNB);
		effet.add(ef_seuillageRVB);
		effet.add(ef_nuances);
		effet.add(ef_decalage_RVB);
		
		effet.setMnemonic('E');
		
		//barre menu
		barreMenu.add(fichier);
		barreMenu.add(edition);
		barreMenu.add(affichage);
		barreMenu.add(retouche);
		barreMenu.add(effet);
		barreMenu.add(aide);
		//barreMenu.add(a_zoomLabel);
		//barreMenu.add(a_zoomSpinner);
		//barreMenu.add(a_zoomLabel2);
		JPanel a = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 0));
		a.setOpaque(false);
		a.add(a_zoomLabel);
		a.add(a_zoomSpinner);
		a.add(a_zoomLabel2);
		barreMenu.add(a);
		this.setJMenuBar(barreMenu);
		
		//Panneau apercu image
		this.apercu = new Apercu();
		this.setLayout(new BorderLayout());
		this.getContentPane().add(apercu, BorderLayout.CENTER);
		//this.getContentPane().add(apercu);
	}
	
	public static void setSystemLookAndFeel()//Vient du SDZ
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (InstantiationException e) {}
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		catch (IllegalAccessException e) {}
	}
	
	private void enableMenu(boolean bool)//true si une image est en cour, et...false sinon.
	{
		//Composant menu fichier
		f_enregistrer.setEnabled(bool);
		f_enregistrerSous.setEnabled(bool);
		f_fermer.setEnabled(bool);
		//Composant menu edition
		ed_restaurer.setEnabled(bool);
		//Comosant menu retouche
		r_luminosite.setEnabled(bool);
		r_RVB.setEnabled(bool);
		r_ret.setEnabled(bool);
		//Composant menu effets
		ef_nb.setEnabled(bool);
		ef_sepia.setEnabled(bool);
		ef_invertColor.setEnabled(bool);
		ef_seuillageNB.setEnabled(bool);
		ef_seuillageRVB.setEnabled(bool);
		ef_nuances.setEnabled(bool);
		ef_decalage_RVB.setEnabled(bool);
		//Composants menu affichage
		a_zoomSpinner.setEnabled(bool);
	}
	
	private void setTitleIsModified(boolean modifie)
	{
		if(modifie)
		{
			setTitle("[Modifié] " + fichierImg.getName() + "  -  JPedi");
		}
		else
		{
			setTitle(fichierImg.getName() + "  -  JPedi");
		}
	}
	
	public void stateChanged(ChangeEvent e)
	{
		if(e.getSource() == a_zoomSpinner)
		{
			apercu.setZoom((int)a_zoomSpinner.getValue());
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//MENU FICHIER
		if(e.getSource() == f_ouvrir)
		{
			//System.out.println("Ouvrir");
			
			//init fenetre de choix de fichier
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Ouvrir");
			chooser.setAcceptAllFileFilterUsed(true);
			String[] ext = ImageIO.getReaderFileSuffixes();
			for( String s : ext)//Listing extension ouvrables
			{
				//System.out.println(s);
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("."+s,s));
			}
		
			chooser.setCurrentDirectory(fichierImg);
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				fichierImg = chooser.getSelectedFile();
				try
				{
					imgEnCour = ImageIO.read(fichierImg);
					if (imgEnCour != null)
					{
						a_zoomSpinner.setValue(100);
						apercu.nouvelleImage(imgEnCour);
						setTitleIsModified(false);
						imgOriginale = imgEnCour;
						enableMenu(true);
						pack();
					}
					else
					{
						System.out.println("Erreur chargement image");
					}
				}
				catch(IOException ex)
				{
					System.out.println("Erreur de chargement image");
				}
			}
		}
		else if(e.getSource() == f_enregistrer)
		{
			//System.out.println("Enregistrer");
			try
			{
				String extension = fichierImg.getAbsolutePath();
				int index = extension.lastIndexOf('.');
				extension = extension.substring(index+1);
				
				//System.out.println(extension);
				
				if(ImageIO.write(imgEnCour,extension,fichierImg) == false)
				{
					System.out.println("Erreur enregistrement de l'image");
					return;
				}
				imgOriginale = imgEnCour;
				setTitleIsModified(false);
			}
			catch(IOException ex)
			{
				System.out.println("Erreur enregistrement image");
			}
		}
		else if(e.getSource() == f_enregistrerSous)
		{
			//System.out.println("Enregistrer sous");
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Enregistrer sous");
			
			String[] ext = ImageIO.getWriterFileSuffixes();
			for( String s : ext)
			{
				//System.out.println(s);
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("."+s,s));
			}
			
			chooser.setAcceptAllFileFilterUsed(true);
			chooser.setCurrentDirectory(fichierImg);
			int returnVal = chooser.showSaveDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				File tempFichierImg = chooser.getSelectedFile();
				try
				{
					File fictemp = new File(tempFichierImg.getAbsolutePath() + chooser.getFileFilter().getDescription());
					BufferedImage imgAEcrire;
					//System.out.println(fictemp.getAbsolutePath());
					//System.out.println(fichierImg.getAbsolutePath()+chooser.getFileFilter().getDescription());
					//System.out.println(chooser.getFileFilter().getDescription());
					if(fictemp.getAbsolutePath().equals(fichierImg.getAbsolutePath()+chooser.getFileFilter().getDescription()) == true)
					{
						System.out.println("Votre fichier présente une double extension. Veuillez effacer celle présente dans le champ nom lors de l'enregistrement.");
						return;
					}
					
					//Si le fichier de sortie ne suporte pas la transparence
					if(chooser.getFileFilter().getDescription().equals(".png") == false && chooser.getFileFilter().getDescription().equals(".gif") == false)
					{
						//System.out.println("Risque de Rose!");
						//On creer une nouvelle img avec fond uni de couleur noire.
						//Code trouvé ici: http://www.mkyong.com/java/convert-png-to-jpeg-image-file-in-java/
						imgAEcrire = new BufferedImage(imgEnCour.getWidth(), imgEnCour.getHeight(), BufferedImage.TYPE_INT_RGB);
						imgAEcrire.createGraphics().drawImage(imgEnCour, 0, 0, Color.BLACK, null);
					}
					else
					{
						imgAEcrire = imgEnCour;
					}
					
					//System.out.println(tempFichierImg.getAbsolutePath() + chooser.getFileFilter().getDescription());
					if(ImageIO.write(imgAEcrire,chooser.getFileFilter().getDescription().substring(1), fictemp) == false)
					{
						System.out.println("Erreur enregistrement sous de l'image");
						return;
					}
					fichierImg = fictemp;
					setTitleIsModified(false);
					imgOriginale = imgEnCour;
				}
				catch(IOException ex)
				{
					System.out.println("Erreur enregistrement sous image");
				}
			}
		}
		else if(e.getSource() == f_fermer)
		{
			//System.out.println("Fermer");
			a_zoomSpinner.setValue(100);
			apercu.effImage();
			enableMenu(false);
			pack();
			setTitle("JPedi");
		}
		else if(e.getSource() == f_quitter)
		{
			//System.out.println("Quitter");
			System.exit(0);
		}
		//MENU EDITION
		else if(e.getSource() == ed_restaurer)
		{
			imgEnCour = imgOriginale;
			apercu.setImage(imgOriginale);
			setTitleIsModified(false);
		}
		//MENU AFFICHAGE
		else if(e.getSource() == a_pack)
		{
			pack();
		}
		else if(e.getSource() == a_centrer)
		{
			setLocationRelativeTo(null);
		}
		//MENU RETOUCHE
		else if(e.getSource() == r_luminosite)
		{
			LuminositeDialog lum = new LuminositeDialog(this, apercu);
			if(lum.showDialog() == true)
			{
				imgEnCour = apercu.getImage();
				setTitleIsModified(true);
			}
		}
		else if(e.getSource() == r_RVB)
		{
			RVBDialog rvb = new RVBDialog(this, apercu);
			if(rvb.showDialog() == true)
			{
				imgEnCour = apercu.getImage();
				setTitleIsModified(true);
			}
		}
		else if(e.getSource() == r_ret)
		{
			RetournementDialog ret = new RetournementDialog(this, apercu);
			if(ret.showDialog() == true)
			{
				imgEnCour = apercu.getImage();
				setTitleIsModified(true);
			}
		}
		//MENU EFFETS
		else if(e.getSource() == ef_nb)
		{
			NoirEtBlancDialog nb = new NoirEtBlancDialog(this, apercu);
			if(nb.showDialog() == true)
			{
				imgEnCour = apercu.getImage();
				setTitleIsModified(true);
			}
		}
		else if(e.getSource() == ef_sepia)
		{
			SepiaDialog sepia = new SepiaDialog(this, apercu);
			if(sepia.showDialog() == true)
			{
				imgEnCour = apercu.getImage();
				setTitleIsModified(true);
			}
		}
		else if(e.getSource() == ef_invertColor)
		{
			InvertColorDialog inv = new InvertColorDialog(this, apercu);
			if(inv.showDialog() == true)
			{
				imgEnCour = apercu.getImage();
				setTitleIsModified(true);
			}
		}
		else if(e.getSource() == ef_seuillageNB)
		{
			SeuillageNoirEtBlancDialog snb = new SeuillageNoirEtBlancDialog(this, apercu);
			if(snb.showDialog() == true)
			{
				imgEnCour = apercu.getImage();
				setTitleIsModified(true);
			}
		}
		else if(e.getSource() == ef_seuillageRVB)
		{
			SeuillageRVBDialog srvb = new SeuillageRVBDialog(this, apercu);
			if(srvb.showDialog() == true)
			{
				imgEnCour = apercu.getImage();
				setTitleIsModified(true);
			}

		}
		else if(e.getSource() == ef_nuances)
		{
			NuancesDialog nd = new NuancesDialog(this, apercu);
			if(nd.showDialog() == true)
			{
				imgEnCour = apercu.getImage();
				setTitleIsModified(true);
			}
		}
		else if(e.getSource() == ef_decalage_RVB)
		{
			DecalageDialog dd = new DecalageDialog(this, apercu);
			if(dd.showDialog() == true)
			{
				imgEnCour = apercu.getImage();
				setTitleIsModified(true);
			}
		}
	}
}
