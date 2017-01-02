package interfaceg.boiteDialogue;

import interfaceg.principale.Apercu;
import traitement.ImageModifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class NuancesDialog extends ModifDialog implements ChangeListener, ActionListener
{
	private JSpinner spinnerR;
	private JSpinner spinnerV;
	private JSpinner spinnerB;
	private JSpinner spinnerNB;
	private JCheckBox nbPondere;
	private int offsetR = 255;
	private int offsetV = 255;
	private int offsetB = 255;
	private int offsetNB = 255;
	
	public NuancesDialog(JFrame parent, Apercu apercu)
	{
		super(parent, apercu);
		this.setTitle("Nuances");
	}
	
	protected void setContent()
	{
		spinnerR = new JSpinner( new SpinnerNumberModel(255, 1, 255, 1));
		spinnerR.addChangeListener(this);
		
		spinnerV = new JSpinner( new SpinnerNumberModel(255, 1, 255, 1));
		spinnerV.addChangeListener(this);
		
		spinnerB = new JSpinner( new SpinnerNumberModel(255, 1, 255, 1));
		spinnerB.addChangeListener(this);
		
		spinnerNB = new JSpinner( new SpinnerNumberModel(255, 1, 255, 1));
		spinnerNB.addChangeListener(this);
		
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.PAGE_AXIS));
		
		nbPondere = new JCheckBox("Moyene pondérée");
		nbPondere.addActionListener(this);
		
		JPanel rvb = new JPanel();
		JPanel nb = new JPanel();
		
		rvb.setLayout(new FlowLayout());
		nb.setLayout(new FlowLayout());
		
		rvb.add(new JLabel("Rouge:"));
		rvb.add(spinnerR);
		rvb.add(new JLabel("Vert:"));
		rvb.add(spinnerV);
		rvb.add(new JLabel("Bleu:"));
		rvb.add(spinnerB);
		nb.add(new JLabel("Gris:"));
		nb.add(spinnerNB);
		nb.add(nbPondere);
		
		rvb.setBorder( BorderFactory.createTitledBorder("Nombres de nuances par couleurs:"));
		//TitledBorder bord = (TitledBorder)rvb.getBorder();
		//bord.setTitleColor(Color.RED);
		nb.setBorder( BorderFactory.createTitledBorder("Nb de nuances de gris:"));
		//bord = (TitledBorder)b.getBorder();
		//bord.setTitleColor(Color.BLUE);

		mainContent.add(rvb);
		mainContent.add(nb);
		
		content.add(mainContent, BorderLayout.CENTER);
	}
	
	public void stateChanged(ChangeEvent e)
	{
		if(e.getSource() == spinnerR)
		{
			offsetR = (int)spinnerR.getValue();
			imgModifie = ImageModifier.nuancesRVB(imgNonModifie, offsetR, offsetV, offsetB);
			apercu.setImage(imgModifie);
		}
		else if(e.getSource() == spinnerV)
		{
			offsetV = (int)spinnerV.getValue();
			imgModifie = ImageModifier.nuancesRVB(imgNonModifie, offsetR, offsetV, offsetB);
			apercu.setImage(imgModifie);
		}
		else if(e.getSource() == spinnerB)
		{
			offsetB = (int)spinnerB.getValue();
			imgModifie = imgModifie = ImageModifier.nuancesRVB(imgNonModifie, offsetR, offsetV, offsetB);
			apercu.setImage(imgModifie);
		}
		else if(e.getSource() == spinnerNB)
		{
			offsetNB = (int)spinnerNB.getValue();
			imgModifie = ImageModifier.nuancesNB(imgNonModifie, offsetNB, nbPondere.isSelected());
			apercu.setImage(imgModifie);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == nbPondere)
		{
			offsetNB = (int)spinnerNB.getValue();
			imgModifie = ImageModifier.nuancesNB(imgNonModifie, offsetNB, nbPondere.isSelected());
			apercu.setImage(imgModifie);
		}
	}
}