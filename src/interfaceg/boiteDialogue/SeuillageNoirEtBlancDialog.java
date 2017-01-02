package interfaceg.boiteDialogue;

import interfaceg.principale.Apercu;
import traitement.ImageModifier;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class SeuillageNoirEtBlancDialog extends ModifDialog implements ChangeListener
{
	private JSlider sliderSeuil;
	private JSpinner spinnerSeuil;
	
	public SeuillageNoirEtBlancDialog(JFrame parent, Apercu apercu)
	{
		super(parent, apercu);
		this.setTitle("Seuillage NB");
		imgModifie = ImageModifier.seuillageNoirEtBlanc(imgNonModifie, 128);
		apercu.setImage(imgModifie);
	}
	
	protected void setContent()
	{
		sliderSeuil = new JSlider(0, 255, 128);
		sliderSeuil.setMajorTickSpacing(51);
		sliderSeuil.setMinorTickSpacing(17);
		sliderSeuil.setPaintTicks(true);
		sliderSeuil.setPaintTrack(true);
		sliderSeuil.setPaintLabels(true);
		sliderSeuil.addChangeListener(this);
		
		spinnerSeuil = new JSpinner( new SpinnerNumberModel(128, 0, 255, 1));
		spinnerSeuil.addChangeListener(this);
		
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new FlowLayout());
		mainContent.add(sliderSeuil);
		mainContent.add(spinnerSeuil);
		
		content.add(mainContent, BorderLayout.CENTER);
	}
	
	public void stateChanged(ChangeEvent e)
	{
		if(e.getSource() == sliderSeuil)
		{
			int seuil = sliderSeuil.getValue();
			spinnerSeuil.setValue(seuil);
		}
		else if(e.getSource() == spinnerSeuil)
		{
			int seuil = (int)spinnerSeuil.getValue();
			sliderSeuil.setValue(seuil);
			imgModifie = ImageModifier.seuillageNoirEtBlanc(imgNonModifie, seuil);
			apercu.setImage(imgModifie);
		}
	}
}