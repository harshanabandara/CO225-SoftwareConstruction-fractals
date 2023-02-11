import java.awt.*; /* java abstract window toolkit */

class Julia{
	/*
	method to check the complex number z is in julia,
	*/
	public static void isJulia(double zReal,double zImag,Complex cJulia,Point p,Graphics g,int iter){
		Complex zN = new Complex(zReal,zImag);//this is the value we need to check
		Complex zN1;
		int i = 0;
		boolean flag = true;
		while(i<iter){
			zN1 = Complex.addComplex(zN.comSquared(),cJulia);
			if (zN1.absSquared()>4) {
				flag = false;
				break;
			}
			zN = zN1;
			i++;
		}
		if (flag == true) {
			Fractal.printPoint((Graphics2D)g,Color.BLACK,p);//if its in julia set,colour with black
		}else{
			Fractal.printPoint((Graphics2D)g,Color.WHITE,p);//else :white
		}
	}
}