package interfaceg.principale;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Apercu extends JScrollPane
{
	private BufferedImage img;
	private float scale = 1;
	private PanneauImage panneauImg;
	private JPanel autoCenterPanel;

	public Apercu()
	{
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getVerticalScrollBar().setUnitIncrement(20);
		getHorizontalScrollBar().setUnitIncrement(20);
		panneauImg = new PanneauImage();
		autoCenterPanel = new JPanel(new GridBagLayout());
		autoCenterPanel.add(panneauImg);
		HandScrollListener scrollListener = new HandScrollListener(panneauImg);//http://stackoverflow.com/questions/10243257/java-scroll-image-by-mouse-dragging
		this.getViewport().addMouseMotionListener(scrollListener);
		this.getViewport().addMouseListener(scrollListener);
		setBordure(false);
	}
	
	public void setImage(BufferedImage img)
	{
		this.img = img;
		this.repaint();
		//Runtime r = Runtime.getRuntime();
		//System.out.println("TOTAL:  " + r.totalMemory());
		//System.out.println("FREE:   " + r.freeMemory());
	}
	
	public void setZoom(int zoom)
	{
		scale = (float)zoom/(float)100;
		//System.out.println(zoom +"\n"+ scale);
		panneauImg.setPreferredSize(new Dimension((int)(img.getWidth()*scale), (int)(img.getHeight()*scale)));
		this.setViewportView(autoCenterPanel);
		this.repaint();
	}
	
	public BufferedImage getImage()
	{
		return img;
	}
	
	public void nouvelleImage(BufferedImage img)
	{
		this.img = img;
		panneauImg.setPreferredSize(new Dimension((int)(img.getWidth()*scale), (int)(img.getHeight()*scale)));
		//REMETRE ZOOM 100 OUVRIR
		this.setViewportView(autoCenterPanel);
		this.repaint();
		//Runtime r = Runtime.getRuntime();
		//System.out.println("TOTAL:  " + r.totalMemory());
		//System.out.println("FREE:   " + r.freeMemory());
	}
	
	public void effImage()
	{
		this.setViewportView(null);
		this.repaint();
	}

	public void setBordure(boolean bool)
	{
		if(bool == true)
		{
			setViewportBorder(BorderFactory.createLineBorder(Color.WHITE,10,false));
		}
		else
		{
			this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
	}
	
	//CLASSES INTERNES
	public class PanneauImage extends JPanel
	{
		public PanneauImage()
		{
			super();
			//this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
		}
		
		public void paintComponent(Graphics g)
		{
			//g.setColor(Color.GRAY);
			//g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.drawImage(img, 0, 0,(int)(img.getWidth()*scale), (int)(img.getHeight()*scale), Color.BLACK, this);
		}
	}
	
	public class HandScrollListener extends MouseAdapter//http://stackoverflow.com/questions/10243257/java-scroll-image-by-mouse-dragging
	{
	    private final Cursor defCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	    private final Cursor hndCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	    private final Point pp = new Point();
	    private PanneauImage image;
	
	    public HandScrollListener(PanneauImage image)
	    {
	        this.image = image;
	    }
	
	    public void mouseDragged(final MouseEvent e)
	    {
	        JViewport vport = (JViewport)e.getSource();
	        Point cp = e.getPoint();
	        Point vp = vport.getViewPosition();
	        vp.translate(pp.x-cp.x, pp.y-cp.y);
	        image.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
	        pp.setLocation(cp);
	    }
	
	    public void mousePressed(MouseEvent e)
	    {
	        image.setCursor(hndCursor);
	        pp.setLocation(e.getPoint());
	    }
	
	    public void mouseReleased(MouseEvent e)
	    {
	        image.setCursor(defCursor);
	        image.repaint();
	    }
	}
}