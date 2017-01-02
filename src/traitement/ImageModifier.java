package traitement;

import java.awt.image.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class ImageModifier
{
	public static BufferedImage noirEtBlanc(BufferedImage inImage)//inspir� de http://lakjeewa.blogspot.fr/2012/05/brightness-changing-algorithm-image.html
	{
		//size of input image
        int w = inImage.getWidth();
        int h = inImage.getHeight();
 
        BufferedImage outImage = new BufferedImage(w, h, inImage.getType());
 
        //Pixel by pixel navigation loop
        for (int i = 0; i < w; i++)
		{
            for (int j = 0; j < h; j++)
			{
                //get the RGB component of input imge pixel
                Color color = new Color(inImage.getRGB(i, j),true);
                int r, v, b, a;
				a = color.getAlpha();
    			r = color.getRed();
                v = color.getGreen();
                b = color.getBlue();
				//change the value of each component
				r = v = b = (r+v+b)/3; 
                //set output image pixel component
                outImage.setRGB(i, j, new Color(r, v, b, a).getRGB());
            }
        }
		return outImage;
	}
	
	public static BufferedImage noirEtBlancPondere(BufferedImage inImage, double coeffR, double coeffV, double coeffB)//inspir� de http://lakjeewa.blogspot.fr/2012/05/brightness-changing-algorithm-image.html
	{
		//size of input image
        int w = inImage.getWidth();
        int h = inImage.getHeight();
 
        BufferedImage outImage = new BufferedImage(w, h, inImage.getType());
 
        //Pixel by pixel navigation loop
        for (int i = 0; i < w; i++)
		{
            for (int j = 0; j < h; j++)
			{
                //get the RGB component of input imge pixel
                Color color = new Color(inImage.getRGB(i, j),true);
                int r, v, b, a;
				a = color.getAlpha();
    			r = color.getRed();
                v = color.getGreen();
                b = color.getBlue();
				//change the value of each component
				//gris = int(round(0.299�rouge + 0.587�vert + 0.114�bleu))
				r = v = b = (int)(Math.round(coeffR * r + coeffV * v + coeffB * b)/(coeffR+coeffV+coeffB));
                //set output image pixel component
                outImage.setRGB(i, j, new Color(r, v, b, a).getRGB());
            }
        }
		return outImage;
	}
	
	public static BufferedImage sepia(BufferedImage inImage)//inspir� de http://stackoverflow.com/questions/1061093/how-is-a-sepia-tone-created
	{
		//size of input image
        int w = inImage.getWidth();
        int h = inImage.getHeight();
 
        BufferedImage outImage = new BufferedImage(w, h, inImage.getType());
 
        //Pixel by pixel navigation loop
        for (int i = 0; i < w; i++)
		{
            for (int j = 0; j < h; j++)
			{
                //get the RGB component of input imge pixel
                Color color = new Color(inImage.getRGB(i, j),true);
                int r, v, b, a, nr, nv, nb;//nx: nouvelle valeur de la composante x.
				a = color.getAlpha();
    			r = color.getRed();
                v = color.getGreen();
                b = color.getBlue();
				//change the value of each component
				nr = (int)((r * .393) + (v *.769) + (b * .189));//*(.393+.769+.189)));//System.out.println(nr);
				if(nr>255)nr=255;
				nv = (int)((r * .349) + (v *.686) + (b * .168));//*(.349+.686+.168)));//System.out.println(nv);
				if(nv>255)nv=255;
				nb = (int)((r * .272) + (v *.534) + (b * .131));//*(.272+.534+.131)));//System.out.println(nb);
				if(nb>255)nb=255;
                //set output image pixel component
                outImage.setRGB(i, j, new Color(nr, nv, nb, a).getRGB());
            }
        }
		return outImage;
	}
	
	public static BufferedImage luminositeRVB(BufferedImage inImage, int offsetR, int offsetV, int offsetB)//inspir� de http://lakjeewa.blogspot.fr/2012/05/brightness-changing-algorithm-image.html
	{
		//size of input image
        int w = inImage.getWidth();
        int h = inImage.getHeight();
 
        BufferedImage outImage = new BufferedImage(w, h, inImage.getType());
 
        //Pixel by pixel navigation loop
        for (int i = 0; i < w; i++)
		{
            for (int j = 0; j < h; j++)
			{
                //get the RGB component of input imge pixel
                Color color = new Color(inImage.getRGB(i, j),true);
                int r, v, b, a;
				a = color.getAlpha();
    			r = color.getRed() + offsetR;
                v = color.getGreen() + offsetV;
                b = color.getBlue() + offsetB;
				//test si debordement
				if(r < 0)
				{
					r = 0;
				}
				else if(r > 255)
				{
					r = 255;
				}
				if(v < 0)
				{
					v = 0;
				}
				else if( v >255)
				{
					v = 255;
				}
				if(b < 0)
				{
					b = 0;
				}
				else if(b > 255)
				{
					b = 255;
				}
                //set output image pixel component
                outImage.setRGB(i, j, new Color(r, v, b, a).getRGB());
            }
        }
		return outImage;
	}
	
	public static BufferedImage invertColor(BufferedImage inImage)
	{
        int w = inImage.getWidth();
        int h = inImage.getHeight();
 
        BufferedImage outImage = new BufferedImage(w, h, inImage.getType());
 
        //Pixel by pixel navigation loop
        for (int i = 0; i < w; i++)
		{
            for (int j = 0; j < h; j++)
			{
                //get the RGB component of input imge pixel
                Color color = new Color(inImage.getRGB(i, j),true);
                int r, v, b, a;
				a = color.getAlpha();
    			r = color.getRed();
                v = color.getGreen();
                b = color.getBlue();
				//change the value of each component
				r = 255 - r;
				v = 255 - v;
				b = 255 - b;
                //set output image pixel component
                outImage.setRGB(i, j, new Color(r, v, b, a).getRGB());
            }
        }
		return outImage;
	}
	
	public static BufferedImage seuillageNoirEtBlanc(BufferedImage inImage, int seuil)
	{
		//size of input image
        int w = inImage.getWidth();
        int h = inImage.getHeight();
 
        BufferedImage outImage = new BufferedImage(w, h, inImage.getType());
 
        //Pixel by pixel navigation loop
        for (int i = 0; i < w; i++)
		{
            for (int j = 0; j < h; j++)
			{
                //get the RGB component of input imge pixel
                Color color = new Color(inImage.getRGB(i, j),true);
                int r, v, b;
    			r = color.getRed();
                v = color.getGreen();
                b = color.getBlue();
				//change the value of each component
				if( (r+v+b)/3 > seuil)
				{
					outImage.setRGB(i, j, Color.BLACK.getRGB());
				}
				else
				{
					outImage.setRGB(i, j, Color.WHITE.getRGB());
				}
            }
        }
		return outImage;
	}
	
	public static BufferedImage seuillageRVB(BufferedImage inImage, int seuilR, int seuilV, int seuilB)
	{
		//size of input image
        int w = inImage.getWidth();
        int h = inImage.getHeight();
 
        BufferedImage outImage = new BufferedImage(w, h, inImage.getType());
 
        //Pixel by pixel navigation loop
        for (int i = 0; i < w; i++)
		{
            for (int j = 0; j < h; j++)
			{
                //get the RGB component of input imge pixel
                Color color = new Color(inImage.getRGB(i, j),true);
                int r, v, b;
    			r = color.getRed();
                v = color.getGreen();
                b = color.getBlue();
				//change the value of each component
				if(r > seuilR)
				{
					r = 255;
				}
				else
				{
					r = 0;
				}
				if(v > seuilV)
				{
					v = 255;
				}
				else
				{
					v = 0;
				}
				if(b > seuilB)
				{
					b = 255;
				}
				else
				{
					b = 0;
				}
				
				outImage.setRGB(i, j, new Color(r, v, b).getRGB());
            }
        }
		return outImage;
	}
	
	public static BufferedImage retournement(BufferedImage inImage, char type)//code from(age de chevre): http://stackoverflow.com/questions/9558981/flip-image-with-graphics2d
	{
		int w = inImage.getWidth();
        int h = inImage.getHeight();
		BufferedImage outImage = new BufferedImage(w, h, inImage.getType());
		AffineTransform tx;
		
		if(type == 'h')
		{
			tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-inImage.getWidth(null), 0);
		}
		else//sous entendu: else if(type == 'v')
		{
			tx = AffineTransform.getScaleInstance(1, -1);
			tx.translate(0, -inImage.getHeight(null));
		}
		
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		outImage = op.filter(inImage, null);
		
		return outImage;
	}
	
	public static BufferedImage nuancesRVB(BufferedImage inImage, int nbNuancesR, int nbNuancesV, int nbNuancesB)
	{
		//size of input image
        int w = inImage.getWidth();
        int h = inImage.getHeight();
 
        BufferedImage outImage = new BufferedImage(w, h, inImage.getType());
		
		int nuancesR[] = new int[nbNuancesR+1];//+1 pour rajouter la valeur 255 ds le tableau
		int nuancesV[] = new int[nbNuancesV+1];
		int nuancesB[] = new int[nbNuancesB+1];
		int l;
		
		for(l = 0; l < nbNuancesR; l++)
		{
			nuancesR[l] = (255/nbNuancesR)*l;
			//System.out.println("R: "+nuancesR[l]);
		}
		nuancesR[l]=255;
		
		for(l = 0; l < nbNuancesV; l++)
		{
			nuancesV[l] = (255/nbNuancesV)*l;
			//System.out.println("V: "+nuancesV[l]);
		}
		nuancesV[l]=255;
		
		for(l = 0; l < nbNuancesB; l++)
		{
			nuancesB[l] = (255/nbNuancesB)*l;
			//System.out.println("B: "+nuancesB[l]);
		}
		nuancesB[l]=255;

        //Pixel by pixel navigation loop
        for (int i = 0; i < w; i++)
		{
            for (int j = 0; j < h; j++)
			{
                //get the RGB component of input imge pixel
                Color color = new Color(inImage.getRGB(i, j),true);
                int r, v, b, a;
				a = color.getAlpha();
    			r = color.getRed();
                v = color.getGreen();
                b = color.getBlue();
				
				for(int k=0; k < nuancesR.length; k++)
				{
					if( r <= nuancesR[k])
					{
						r = nuancesR[k];
						break;
					}
				}
				for(int k=0; k < nuancesV.length; k++)
				{
					if( v <= nuancesV[k])
					{
						v = nuancesV[k];
						break;
					}
				}
				for(int k=0; k < nuancesB.length; k++)
				{
					if( b <= nuancesB[k])
					{
						b = nuancesB[k];
						break;
					}
				}
								
				outImage.setRGB(i, j, new Color(r, v, b, a).getRGB());
            }
        }
		return outImage;
	}
	
	public static BufferedImage nuancesNB(BufferedImage inImage, int nbNuances, boolean pondere)
	{
		//size of input image
        int w = inImage.getWidth();
        int h = inImage.getHeight();
 
        BufferedImage outImage = new BufferedImage(w, h, inImage.getType());
		
		int nuances[] = new int[nbNuances+1];//+1 pour rajouter la valeur 255 ds le tableau
		int l;
		
		for(l = 0; l < nbNuances; l++)
		{
			nuances[l] = (255/nbNuances)*l;
		}
		nuances[l] = 255;

        //Pixel by pixel navigation loop
        for (int i = 0; i < w; i++)
		{
            for (int j = 0; j < h; j++)
			{
                //get the RGB component of input imge pixel
                Color color = new Color(inImage.getRGB(i, j),true);
                int r, v, b, a, m;
				a = color.getAlpha();
    			r = color.getRed();
                v = color.getGreen();
                b = color.getBlue();
				
				if(pondere)
				{
					m = (int)(Math.round(0.299 * r + 0.587 * v + 0.114 * b));
				}
				else
				{
					m = (r+v+b)/3;
				}
				
				for(int k=0; k < nuances.length; k++)
				{
					if( m <= nuances[k])
					{
						r = v = b = nuances[k];
						break;
					}
				}								
				outImage.setRGB(i, j, new Color(r, v, b, a).getRGB());
            }
        }
		return outImage;
	}

	public static BufferedImage decalageRVB(BufferedImage inImage, int decalageHorizontalR, int decalageVertitalR, int decalageHorizontalV, int decalageVertitalV, int decalageHorizontalB, int decalageVertitalB)
	{
		//size of input image
		int w = inImage.getWidth();
		int h = inImage.getHeight();

		BufferedImage outImage = new BufferedImage(w, h, inImage.getType());

		//Pixel by pixel navigation loop
		for (int i = 0; i < w; i++)
		{
			for (int j = 0; j < h; j++)
			{
				//Décalage du rouge
				if( 0 < i+decalageHorizontalR && i+decalageHorizontalR < w && 0 < j+decalageVertitalR && j+decalageVertitalR < h)
				{
					Color colorSource = new Color(inImage.getRGB(i, j), true);
					int rS, vS, bS, aS;
					aS = colorSource.getAlpha();
					rS = colorSource.getRed();
					vS = colorSource.getGreen();
					bS = colorSource.getBlue();

					Color colorDest = new Color(inImage.getRGB(i+decalageHorizontalR, j+decalageVertitalR), true);
					int rD, vD, bD, aD;
					aD = colorDest.getAlpha();
					rD = colorDest.getRed();
					vD = colorDest.getGreen();
					bD = colorDest.getBlue();
					outImage.setRGB(i+decalageHorizontalR, j+decalageVertitalR, new Color(rS,vD,bD).getRGB());
					outImage.setRGB(i, j, new Color(0,vS,bS).getRGB());
				}
				//Décalage du vert
				if( 0 < i+decalageHorizontalV && i+decalageHorizontalV < w && 0 < j+decalageVertitalV && j+decalageVertitalV < h)
				{
					Color colorSource = new Color(inImage.getRGB(i, j), true);
					int rS, vS, bS, aS;
					aS = colorSource.getAlpha();
					rS = colorSource.getRed();
					vS = colorSource.getGreen();
					bS = colorSource.getBlue();

					Color colorDest = new Color(inImage.getRGB(i+decalageHorizontalV, j+decalageVertitalV), true);
					int rD, vD, bD, aD;
					aD = colorDest.getAlpha();
					rD = colorDest.getRed();
					vD = colorDest.getGreen();
					bD = colorDest.getBlue();
					outImage.setRGB(i+decalageHorizontalV, j+decalageVertitalV, new Color(rD,vS,bD).getRGB());
					outImage.setRGB(i, j, new Color(rD,0,bD).getRGB());
				}
				//Décalage du bleu
				if( 0 < i+decalageHorizontalB && i+decalageHorizontalB < w && 0 < j+decalageVertitalB && j+decalageVertitalB < h)
				{
					Color colorSource = new Color(inImage.getRGB(i, j), true);
					int rS, vS, bS, aS;
					aS = colorSource.getAlpha();
					rS = colorSource.getRed();
					vS = colorSource.getGreen();
					bS = colorSource.getBlue();

					Color colorDest = new Color(inImage.getRGB(i+decalageHorizontalB, j+decalageVertitalB), true);
					int rD, vD, bD, aD;
					aD = colorDest.getAlpha();
					rD = colorDest.getRed();
					vD = colorDest.getGreen();
					bD = colorDest.getBlue();
					outImage.setRGB(i+decalageHorizontalB, j+decalageVertitalB, new Color(rD,vD,bS).getRGB());
					outImage.setRGB(i, j, new Color(rD,vD,0).getRGB());
				}
			}
		}
		return outImage;
	}
}