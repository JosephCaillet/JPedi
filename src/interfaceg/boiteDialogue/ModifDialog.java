package interfaceg.boiteDialogue;

import interfaceg.principale.Apercu;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.border.*;

public abstract class ModifDialog extends JDialog
{
	protected Apercu apercu;
	protected BufferedImage imgNonModifie;
	protected BufferedImage imgModifie;
	private JButton ok = new JButton("Valider");
	private JButton cancel = new JButton("Annuler");
	protected JPanel content;
	private JLabel voirOriginal = new JLabel("Voir l'original");
	private boolean returnValue;
	
	private static int posX = -9999;
	private static int posY = -9999;
	
	public ModifDialog(JFrame parent, Apercu apercu)
	{
		super(parent, true);
		this.apercu = apercu;
		this.returnValue = false;
		this.imgNonModifie = this.imgModifie = apercu.getImage();
		
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				apercu.setImage(imgNonModifie);
				close();
			}
		});
		this.setBasicContent();
		this.setContent();
		this.pack();
		
		if(posX == -9999)
		{
			this.setLocationRelativeTo(parent);
		}
		else
		{
			this.setLocation(posX, posY);
		}
	}
	
	public boolean showDialog()
	{
		this.setVisible(true);
		return returnValue;
	}
	
	private void close()
	{
		posX = getX();
		posY = getY();
		setVisible(false);
	}
	
	private void setBasicContent()
	{
		ok.setToolTipText("Valider les modifications");
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				returnValue = true;
				close();
			}
		});
		
		cancel.setToolTipText("Annuler les modifications");
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				apercu.setImage(imgNonModifie);
				close();
			}
		});
		
		voirOriginal.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e){}
			public void mouseEntered(MouseEvent e)
			{
				apercu.setImage(imgNonModifie);
			}
			public void mouseExited(MouseEvent e)
			{
				apercu.setImage(imgModifie);
			}
			public void mousePressed(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}
		});

		
		content = new JPanel();
		content.setBorder(new EmptyBorder(2, 2, 2, 2) );
		content.setLayout(new BorderLayout(0,1));
		
		JPanel bas = new JPanel();
		bas.add(ok);
		bas.add(cancel);
		//voirOriginal.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		JPanel haut = new JPanel();//Cela permet d'eviter que le jlabel voirOriginal prenne toute la largeur.
		haut.setLayout(new FlowLayout());
		haut.add(voirOriginal);
		
		content.add(bas, BorderLayout.SOUTH);
		content.add(haut, BorderLayout.NORTH);
		this.setContentPane(content);
	}
	
	abstract protected void setContent();
}