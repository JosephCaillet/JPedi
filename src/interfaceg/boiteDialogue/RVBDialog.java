package interfaceg.boiteDialogue;

import interfaceg.principale.Apercu;
import traitement.ImageModifier;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class RVBDialog extends ModifDialog implements ChangeListener
{
	private JSlider sliderR;
	private JSlider sliderV;
	private JSlider sliderB;
	private JSpinner spinnerR;
	private JSpinner spinnerV;
	private JSpinner spinnerB;
	private int offsetR;
	private int offsetV;
	private int offsetB;
	
	public RVBDialog(JFrame parent, Apercu apercu)
	{
		super(parent, apercu);
		this.setTitle("Composantes RVB");
	}
	
	protected void setContent()
	{
		sliderR = new JSlider(-255, 255, 0);
		sliderR.setMajorTickSpacing(85);
		sliderR.setMinorTickSpacing(17);
		sliderR.setPaintTicks(true);
		sliderR.setPaintTrack(true);
		sliderR.setPaintLabels(true);
		sliderR.addChangeListener(this);
		
		sliderV = new JSlider(-255, 255, 0);
		sliderV.setMajorTickSpacing(85);
		sliderV.setMinorTickSpacing(17);
		sliderV.setPaintTicks(true);
		sliderV.setPaintTrack(true);
		sliderV.setPaintLabels(true);
		sliderV.addChangeListener(this);
		
		sliderB = new JSlider(-255, 255, 0);
		sliderB.setMajorTickSpacing(85);
		sliderB.setMinorTickSpacing(17);
		sliderB.setPaintTicks(true);
		sliderB.setPaintTrack(true);
		sliderB.setPaintLabels(true);
		sliderB.addChangeListener(this);
		
		spinnerR = new JSpinner( new SpinnerNumberModel(0, -255, 255, 1));
		spinnerR.addChangeListener(this);
		
		spinnerV = new JSpinner( new SpinnerNumberModel(0, -255, 255, 1));
		spinnerV.addChangeListener(this);
		
		spinnerB = new JSpinner( new SpinnerNumberModel(0, -255, 255, 1));
		spinnerB.addChangeListener(this);
		
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.PAGE_AXIS));
		
		JPanel r = new JPanel();//Box.createHorizontalBox();
		JPanel v = new JPanel();//Box.createHorizontalBox();
		JPanel b = new JPanel();//Box.createHorizontalBox();
		
		r.setLayout(new FlowLayout());
		v.setLayout(new FlowLayout());
		b.setLayout(new FlowLayout());
		
		r.add(sliderR);
		v.add(sliderV);
		b.add(sliderB);
		
		r.add(spinnerR);
		v.add(spinnerV);
		b.add(spinnerB);
		
		r.setBorder( BorderFactory.createTitledBorder("Composante Rouge"));
		TitledBorder bord = (TitledBorder)r.getBorder();
		bord.setTitleColor(Color.RED);
		v.setBorder( BorderFactory.createTitledBorder("Composante Verte"));
		bord = (TitledBorder)v.getBorder();
		bord.setTitleColor(Color.GREEN);
		b.setBorder( BorderFactory.createTitledBorder("Composante Bleue"));
		bord = (TitledBorder)b.getBorder();
		bord.setTitleColor(Color.BLUE);

		mainContent.add(r);
		mainContent.add(v);
		mainContent.add(b);
		
		content.add(mainContent, BorderLayout.CENTER);
	}
	
	public void stateChanged(ChangeEvent e)
	{
		if(e.getSource() == sliderR)
		{
			offsetR = sliderR.getValue();
			spinnerR.setValue(offsetR);
			//imgModifie = ImageModifier.luminositeRVB(imgNonModifie, offsetR, offsetV, offsetB);
			//apercu.setImage(imgModifie);
		}
		else if(e.getSource() == spinnerR)
		{
			offsetR = (int)spinnerR.getValue();
			sliderR.setValue(offsetR);
			imgModifie = ImageModifier.luminositeRVB(imgNonModifie, offsetR, offsetV, offsetB);
			apercu.setImage(imgModifie);
		}
		else if(e.getSource() == sliderV)
		{
			offsetV = sliderV.getValue();
			spinnerV.setValue(offsetV);
			//imgModifie = ImageModifier.luminositeRVB(imgNonModifie, offsetR, offsetV, offsetB);
			//apercu.setImage(imgModifie);
		}
		else if(e.getSource() == spinnerV)
		{
			offsetV = (int)spinnerV.getValue();
			sliderV.setValue(offsetV);
			imgModifie = ImageModifier.luminositeRVB(imgNonModifie, offsetR, offsetV, offsetB);
			apercu.setImage(imgModifie);
		}
		else if(e.getSource() == sliderB)
		{
			offsetB = sliderB.getValue();
			spinnerB.setValue(offsetB);
			//imgModifie = ImageModifier.luminositeRVB(imgNonModifie, offsetR, offsetV, offsetB);
			//apercu.setImage(imgModifie);
		}
		else if(e.getSource() == spinnerB)
		{
			offsetB = (int)spinnerB.getValue();
			sliderB.setValue(offsetB);
			imgModifie = ImageModifier.luminositeRVB(imgNonModifie, offsetR, offsetV, offsetB);
			apercu.setImage(imgModifie);
		}
	}
}