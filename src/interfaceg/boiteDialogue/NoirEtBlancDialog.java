package interfaceg.boiteDialogue;

import interfaceg.principale.Apercu;
import traitement.ImageModifier;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class NoirEtBlancDialog extends ModifDialog implements ActionListener
{	
	private JButton nb;
	private JButton nbp;
	
	public NoirEtBlancDialog(JFrame parent, Apercu apercu)
	{
		super(parent, apercu);
		this.setTitle("Noir et Blanc");
	}
	
	protected void setContent()
	{
		nb = new JButton("Noir et Blanc");
		nb.setToolTipText("Passe l'image en noir et blanc en effectuant une moyenne normale des composantes RVB.");
		nb.addActionListener(this);
		
		nbp = new JButton("Noir et Blanc (pondéré)");
		nbp.setToolTipText("Passe l'image en noir et blanc en effectuant une moyenne pondérée des composantes RVB.");
		nbp.addActionListener(this);		
		
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new BorderLayout());
		mainContent.add(nb, BorderLayout.NORTH);
		mainContent.add(nbp, BorderLayout.CENTER);
		
		content.add(mainContent, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == nb)
		{
			imgModifie = ImageModifier.noirEtBlanc(imgNonModifie);
			apercu.setImage(imgModifie);
		}
		else if(e.getSource() == nbp)
		{
			imgModifie = ImageModifier.noirEtBlancPondere(imgNonModifie, 0.299, 0.587, 0.114);
			apercu.setImage(imgModifie);
		}
	}
}