class Complex{
	/*
	Complex numnber object
	*/
	private  double real,imag;
	//constructor
	public Complex(double real,double imag){
		this.real = real;
		this.imag = imag;
	}
	//get the real component
	public double getReal(){
		return this.real;
	}
	//get imaginary component
	public double getImag(){
		return this.imag;
	}
	//add two complex numbers:static method
	public static Complex addComplex(Complex c1,Complex c2){
		return new Complex(c1.getReal()+c2.getReal(),c1.getImag()+c2.getImag());
	}
	//calculate the squared power of complex number
	public Complex comSquared(){
		double realS,imagS;
		realS = this.real*this.real -this.imag*this.imag;
		imagS = 2*this.real*this.imag;
		return new Complex(realS,imagS);
	}
	//calculate the absolute value of a complex number
	public double absSquared(){
		return this.real*this.real + this.imag*this.imag;
	}
	public String toString(){//to debugging purposes only.not included in class diagram
		return this.real+" "+this.imag+"i";
	}
}