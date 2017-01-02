package interfaceg.boiteDialogue;

import interfaceg.principale.Apercu;
import traitement.ImageModifier;

import javax.swing.*;

public class SepiaDialog extends ModifDialog
{	
	public SepiaDialog(JFrame parent, Apercu apercu)
	{
		super(parent, apercu);
		this.setTitle("Sepia");
		imgModifie = ImageModifier.sepia(imgNonModifie);
		apercu.setImage(imgModifie);
	}
	
	protected void setContent()
	{
	}	
}