package interfaceg.boiteDialogue;

import interfaceg.principale.Apercu;
import traitement.ImageModifier;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class LuminositeDialog extends ModifDialog implements ChangeListener
{
	private JSlider sliderLum;
	private JSpinner spinnerLum;
	//private static int offsetLum = 0;
	
	public LuminositeDialog(JFrame parent, Apercu apercu)
	{
		super(parent, apercu);
		this.setTitle("Luminosité");
	}
	
	protected void setContent()
	{
		sliderLum = new JSlider(-255, 255, 0);
		sliderLum.setMajorTickSpacing(85);
		sliderLum.setMinorTickSpacing(17);
		sliderLum.setPaintTicks(true);
		sliderLum.setPaintTrack(true);
		sliderLum.setPaintLabels(true);
		sliderLum.addChangeListener(this);
		
		spinnerLum = new JSpinner( new SpinnerNumberModel(0, -255, 255, 1));
		spinnerLum.addChangeListener(this);
		
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new FlowLayout());
		mainContent.add(sliderLum);
		mainContent.add(spinnerLum);
		
		content.add(mainContent, BorderLayout.CENTER);
	}
	
	public void stateChanged(ChangeEvent e)
	{
		if(e.getSource() == sliderLum)
		{
			int offsetLum = sliderLum.getValue();
			spinnerLum.setValue(offsetLum);
			//imgModifie = ImageModifier.luminositeRVB(imgNonModifie, offsetLum, offsetLum, offsetLum);
			//System.out.println("Slider");
			//apercu.setImage(imgModifie);
		}
		else if(e.getSource() == spinnerLum)
		{
			int offsetLum = (int)spinnerLum.getValue();
			sliderLum.setValue(offsetLum);
			imgModifie = ImageModifier.luminositeRVB(imgNonModifie, offsetLum, offsetLum, offsetLum);
			//System.out.println("Spinner");
			apercu.setImage(imgModifie);
		}
	}
}