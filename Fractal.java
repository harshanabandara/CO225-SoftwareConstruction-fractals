import java.awt.*; /* java abstract window toolkit */ 
import javax.swing.*;
import java.awt.geom.Line2D;
import java.lang.String;

class Fractal extends JPanel{
	//size of the image(pixels)
	private static int width = 800;
	private static int height= 800;
	//bounds and spans of region
	private double reMax,reMin,imMax,imMin,reSpan,imSpan;
	//no of iteration
	private int iter;
	//name of the set
	private String setName;
	//c value for julia set
	private Complex cJulia;
	public Fractal(double reMin,double reMax,double imMin,double imMax, //Constructor
					int iter,String setName,Complex c){
		this.reMin = reMin;
		this.reMax = reMax;
		this.imMin = imMin;
		this.imMax = imMax;
		this.iter  = iter;
		this.setName = setName;
		this.cJulia     = c; //we dont need a c value to calculate mandelbrot set
		this.reSpan = reMax-reMin;
		this.imSpan = imMax - imMin;
		setPreferredSize(new Dimension(width,height));
	}
	public static void printPoint(Graphics2D frame,Color c,Point p){ 
	//printing the given point,usualiy prints a line,Since given the same coordinates,it ptints a point
		frame.setColor(c);
		frame.draw(new Line2D.Double(p.getX(),p.getY(),p.getX(),p.getY()));
	}
	public void paintComponent(Graphics g){
		//checks whether its in the set; 
		super.paintComponent(g);
		int i,j;
		for (i=0 ;i<width ;i++ ) {
			for (j=0;j<height ;j++ ) {
				Point p = new Point(i,j);
				double cReal = this.reMin + this.reSpan * (double)i /width;//real component according to x axis
				double cImag = this.imMin + this.imSpan * (double)j /height;//imaginary component according to y axis
				//calling julia and mandelbrot to see the values are in the set;
				if (setName.equalsIgnoreCase("julia")) {
					Julia.isJulia(cReal,cImag,cJulia,p,g,iter);
				}else if(setName.equalsIgnoreCase("mandelbrot")){
					Mandelbrot.isMandelbrot(cReal,cImag,p,g,iter);
				}
			}
		}
	}
	public static void main(String[] args) {
		/*
		About arguments of julia set,given pdf wasnt clear enough.implemented both
		*/
		int argcount = args.length;
		if (argcount==0) {
			System.out.println("No input arguments");
		}
		else if (args[0].equalsIgnoreCase("julia")) {//drawing julia graph
			JFrame frame = new JFrame("Julia set");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			if (argcount == 1) {//default
				Complex juliaD = new Complex(-0.4,0.6);//default c value for julia
				frame.setContentPane(new Fractal(-1.0,1.0,-1.0,1.0,1000,"julia",juliaD));//default image
				
			}
			else if (argcount == 3 ) {
				Complex juliaC;
				double juliaCreal;
				double juliaImag;
				try{
					juliaCreal = Double.parseDouble(args[1]);
					juliaImag = Double.parseDouble(args[2]);
					

				}catch(Exception e){
					System.out.println("Invalid input.Input format:<julia> <Real part of c> <Imaginary part of c> ");
				}
				//Cannot use the above values inside the try bloc :(
				juliaCreal = Double.parseDouble(args[1]);
				juliaImag = Double.parseDouble(args[2]);
				juliaC = new Complex(juliaCreal,juliaImag);
				frame.setContentPane(new Fractal(-1.0,1.0,-1.0,1.0,1000,"julia",juliaC));
			}
			else if (argcount == 4) {
				Complex juliaC;
				double juliaCreal;
				double juliaImag;
				int itr;
				try{
					juliaCreal = Double.parseDouble(args[1]);
					juliaImag = Double.parseDouble(args[2]);
					itr = Integer.parseInt(args[3]);

				}catch(Exception e){
					System.out.println("Invalid input.Input format:<julia> <Real part of c> <Imaginary part of c> <Number of iterations> ");
				}
				juliaCreal = Double.parseDouble(args[1]);
				juliaImag = Double.parseDouble(args[2]);
				juliaC = new Complex(juliaCreal,juliaImag);
				itr = Integer.parseInt(args[3]);
				frame.setContentPane(new Fractal(-1.0,1.0,-1.0,1.0,itr,"julia",juliaC));

				
			}
			frame.pack(); 
			frame.setLocationRelativeTo(null); 
			frame.setVisible(true);

		}
		else if (args[0].equalsIgnoreCase("mandelbrot")) {	//drawing mandelbrot
			JFrame frame = new JFrame("Mandelbrot set");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Complex mandelC =new Complex(0d,0d);//Since we dont need c value
			if (argcount == 1) {//default
				
				frame.setContentPane(new Fractal(-1.0,1.0,-1.0,1.0,1000,"mandelbrot",mandelC));
				
			}
			else if (argcount == 5) {
				double reMin,reMax,imMin,imMax;
				try{
					reMin = Double.parseDouble(args[1]);
					reMax = Double.parseDouble(args[2]);
					imMin = Double.parseDouble(args[3]);
					imMax = Double.parseDouble(args[4]);

				}catch(Exception e){
					System.out.println("Invalid input.Input format:<mandelbrot> <Real region> <Imaginary region> ");
				}
					reMin = Double.parseDouble(args[1]);
					reMax = Double.parseDouble(args[2]);
					imMin = Double.parseDouble(args[3]);
					imMax = Double.parseDouble(args[4]);
				frame.setContentPane(new Fractal(reMin,reMax,imMin,imMax,1000,"mandelbrot",mandelC));
				
			}
			else if (argcount == 6) {
				double reMin,reMax,imMin,imMax;
				int itr;
				try{
					reMin = Double.parseDouble(args[1]);
					reMax = Double.parseDouble(args[2]);
					imMin = Double.parseDouble(args[3]);
					imMax = Double.parseDouble(args[4]);
					itr   = Integer.parseInt(args[5]);
				}catch(Exception e){
					System.out.println("Invalid input.Input format:<mandelbrot> <Real region> <Imaginary region> <Iterations>");
				}

				reMin = Double.parseDouble(args[1]);
				reMax = Double.parseDouble(args[2]);
				imMin = Double.parseDouble(args[3]);
				imMax = Double.parseDouble(args[4]);
				itr   = Integer.parseInt(args[5]);
				frame.setContentPane(new Fractal(reMin,reMax,imMin,imMax,itr,"mandelbrot",mandelC));
								
			}
			frame.pack(); 
			frame.setLocationRelativeTo(null); 
			frame.setVisible(true);

			
		}else{
			System.out.println("Give valid input arguments");
			return;
		}
	}

}