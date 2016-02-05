package com.sist.client;

public class HSL2RGB {
	
	public int[] HsltoRGB(int h, double s, double l)
	{
		int[] result=new int[3];
		double R;
		double G;
		double B;
		double var_1;
		double var_2;
		
	if (s==0)                       //Hsl from 0 to 1
	{
	   R=l*255;                      //RGB results from 0 to 255
	   G=l*255;
	   B=l*255;
	}
	else
	{
	   if (l<0.5) 
	      var_2=l*(1+s);
	   else
	   {
		  var_2=(l+s)-(s*l);  
	   }
	   	var_1=2*l-var_2;
		R = 255*Hue_2_RGB(var_1,var_2, h+(1/3)); 
		G = 255*Hue_2_RGB(var_1,var_2, h);
		B = 255*Hue_2_RGB(var_1,var_2, h-(1/3));
	} 
	  result[0]=(int)R;
	  result[1]=(int)G;
	  result[2]=(int)B;
	  return result;
	}
	
	public double Hue_2_RGB(double v1, double v2, double vH)             //Function Hue_2_RGB
	{
	    double result=0;
		if(vH<0)
			vH+=1;
	    if(vH>1)
	    	vH-=1;
	    if((6*vH)<1)
	    {
	    	result=(v1+(v2-v1)*6*vH);
	    }
	    if((2*vH)<1)
	    {
	    	result=v2;
	    }
	    if((3*vH)<2)
	    {
	    	result=(v1+(v2-v1)*((2/3)-vH)*6);
	    }
	    result=(v1);
	    return result;
	}
}
