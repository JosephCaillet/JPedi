package interfaceg.boiteDialogue;

import interfaceg.principale.Apercu;
import traitement.ImageModifier;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RetournementDialog extends ModifDialog implements ActionListener
{	
	private JButton retV;
	private JButton retH;
	
	public RetournementDialog(JFrame parent, Apercu apercu)
	{
		super(parent, apercu);
		this.setTitle("Retournement");
	}
	
	protected void setContent()
	{
		retV = new JButton("Retournement Vertical");
		retV.addActionListener(this);
		
		retH = new JButton("Retournement Horizontal");
		retH.addActionListener(this);		
		
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new BorderLayout());
		mainContent.add(retV, BorderLayout.NORTH);
		mainContent.add(retH, BorderLayout.CENTER);
		
		content.add(mainContent, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == retH)
		{
			imgModifie = ImageModifier.retournement(imgModifie, 'h');
			apercu.setImage(imgModifie);
		}
		else if(e.getSource() == retV)
		{
			imgModifie = ImageModifier.retournement(imgModifie,'v');
			apercu.setImage(imgModifie);
		}
	}
}