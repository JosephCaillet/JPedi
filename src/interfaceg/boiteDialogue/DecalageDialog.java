package interfaceg.boiteDialogue;

import interfaceg.principale.Apercu;
import traitement.ImageModifier;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class DecalageDialog extends ModifDialog implements ChangeListener
{
	private JSpinner spinnerHorizontalR;
	private JSpinner spinnerVerticalR;
	private JSpinner spinnerHorizontalV;
	private JSpinner spinnerVerticalV;
	private JSpinner spinnerHorizontalB;
	private JSpinner spinnerVerticalB;

	public DecalageDialog(JFrame parent, Apercu apercu)
	{
		super(parent, apercu);
		this.setTitle("Composantes RVB");
	}
	
	protected void setContent()
	{
		spinnerHorizontalR = new JSpinner( new SpinnerNumberModel(0, -apercu.getWidth(), apercu.getWidth(), 1));
		spinnerHorizontalR.addChangeListener(this);
		spinnerVerticalR = new JSpinner( new SpinnerNumberModel(0, -apercu.getHeight(), apercu.getHeight(), 1));
		spinnerVerticalR.addChangeListener(this);
		
		spinnerHorizontalV = new JSpinner( new SpinnerNumberModel(0, -apercu.getWidth(), apercu.getWidth(), 1));
		spinnerHorizontalV.addChangeListener(this);
		spinnerVerticalV = new JSpinner( new SpinnerNumberModel(0, -apercu.getHeight(), apercu.getHeight(), 1));
		spinnerVerticalV.addChangeListener(this);
		
		spinnerHorizontalB = new JSpinner( new SpinnerNumberModel(0, -apercu.getWidth(), apercu.getWidth(), 1));
		spinnerHorizontalB.addChangeListener(this);
		spinnerVerticalB = new JSpinner( new SpinnerNumberModel(0, -apercu.getHeight(), apercu.getHeight(), 1));
		spinnerVerticalB.addChangeListener(this);
		
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.PAGE_AXIS));
		
		JPanel r = new JPanel();
		JPanel v = new JPanel();
		JPanel b = new JPanel();
		
		r.setLayout(new FlowLayout());
		v.setLayout(new FlowLayout());
		b.setLayout(new FlowLayout());
		
		r.add(new JLabel("Horizontal:"));
		r.add(spinnerHorizontalR);
		r.add(new JLabel("Vertical:"));
		r.add(spinnerVerticalR);

		v.add(new JLabel("Horizontal:"));
		v.add(spinnerHorizontalV);
		v.add(new JLabel("Vertical:"));
		v.add(spinnerVerticalV);

		b.add(new JLabel("Horizontal:"));
		b.add(spinnerHorizontalB);
		b.add(new JLabel("Vertical:"));
		b.add(spinnerVerticalB);

		r.setBorder( BorderFactory.createTitledBorder("Décalage composante Rouge"));
		TitledBorder bord = (TitledBorder)r.getBorder();
		bord.setTitleColor(Color.RED);
		v.setBorder( BorderFactory.createTitledBorder("Décalage composante Verte"));
		bord = (TitledBorder)v.getBorder();
		bord.setTitleColor(Color.GREEN);
		b.setBorder( BorderFactory.createTitledBorder("Décalage composante Bleue"));
		bord = (TitledBorder)b.getBorder();
		bord.setTitleColor(Color.BLUE);

		mainContent.add(r);
		mainContent.add(v);
		mainContent.add(b);
		
		content.add(mainContent, BorderLayout.CENTER);
	}
	
	public void stateChanged(ChangeEvent e)
	{
		imgModifie = ImageModifier.decalageRVB(imgNonModifie, (int)spinnerHorizontalR.getValue(), (int)spinnerVerticalR.getValue(), (int)spinnerHorizontalV.getValue(), (int)spinnerVerticalV.getValue(), (int)spinnerHorizontalB.getValue(), (int) spinnerVerticalB.getValue());
		apercu.setImage(imgModifie);
	}
}