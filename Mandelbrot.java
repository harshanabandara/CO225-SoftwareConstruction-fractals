import java.awt.*; /* java abstract window toolkit */

class Mandelbrot{
	/*
	method to check a complex number c is in the mandelbrot set 
	*/
	public static void isMandelbrot(double cReal,double cImag,Point p,Graphics g,int iter){
		Complex c = new Complex(cReal,cImag);
		Complex zN= new Complex(0d,0d);
		Complex zN1;
		int i= 0;
		boolean flag = true; 
		while(i<iter){
			 zN1 = Complex.addComplex(zN.comSquared(),c);
			if (zN1.absSquared()>4) {
				flag = false;
				break;
			}
			zN = zN1;
			i++;			 
		}
		if (flag == true) {
			Fractal.printPoint((Graphics2D)g,Color.BLACK,p);//if its in mandelbrot set,colour with black
		}else{
			Fractal.printPoint((Graphics2D)g,Color.WHITE,p);//else,white
		}		

	}
}