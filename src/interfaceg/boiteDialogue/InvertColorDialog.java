package interfaceg.boiteDialogue;

import interfaceg.principale.Apercu;
import traitement.ImageModifier;

import javax.swing.*;

public class InvertColorDialog extends ModifDialog
{	
	public InvertColorDialog(JFrame parent, Apercu apercu)
	{
		super(parent, apercu);
		this.setTitle("Inversion des couleurs");
		imgModifie = ImageModifier.invertColor(imgNonModifie);
		apercu.setImage(imgModifie);
	}
	
	protected void setContent()
	{
	}	
}