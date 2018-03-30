//
// $Id: Vector2D.java,v 1.2 1997/03/03 22:29:27 min Exp min $
//
// Simple 2D vector class.
// Why is there no operator overloading in Java?  :-(
//

package com.geditor.math;

import java.lang.*;

public class Vector2D
{
  public static final int X = 0;
  public static final int Y = 1;
  public static final int Z = 2;
  private static final int VECTOR_SIZE = 3;
  protected double[] elm;  // has to be visible in Matrix
  
  public Vector2D()
  {
    set(0, 0, 1);
  }
  
  public Vector2D(double a, double b)
  {
    set(a, b, 1);
  }
  
  public Vector2D(double a, double b, double c)
  {
    set(a, b, c);
  }

  public Vector2D(Vector2D B)
  {
    set(B.elm[0], B.elm[1], B.elm[2]);
  }
    
  public void set(double a, double b, double c)
  {
    elm = new double[3];
    elm[X] = a;
    elm[Y] = b;
    elm[Z] = c;
  }  // set
   
  public void set(double a, double b)
  {
    set(a, b, 1);
  }
  
  public double get_x()
  {
    return elm[0];
  }
  
  public double get_y()
  {
    return elm[1];
  }

  public Vector2D add(Vector2D B)
  {
    Vector2D result = 
      new Vector2D(elm[X] + B.elm[X],
		 elm[Y] + B.elm[Y],
		 elm[Z] + B.elm[Z]);
    return result;
  }  // add

  public Vector2D subtract(Vector2D B)
  {
    Vector2D result =
      new Vector2D(elm[X] - B.elm[X],
		 elm[Y] - B.elm[Y],
		 elm[Z] - B.elm[Z]);
    return result;
  }  // subtract

  public void negate()
  {
    for(int i=0; i<VECTOR_SIZE; i++) elm[i] = -elm[i];
  }  // negate

  public boolean equals(Vector2D B)
  {
    return ((elm[X] == B.elm[X]) &&
	    (elm[Y] == B.elm[Y]) &&
	    (elm[Z] == B.elm[Z]));  // for VECTOR_SIZE 2 always equal
  }  // equals

  protected Object clone()
  {
    Vector2D result = new Vector2D(this);
    return result;
  }  // clone

  public double dot_product(Vector2D B)
  {
    double result = 0;
  
    for(int i=0; i<(VECTOR_SIZE-1); i++)
      result += elm[i] * B.elm[i];
    return result;
  }  // dot_product

  public String toString()
  {
    StringBuffer s = new StringBuffer();
  
    s.append("[");
    for(int i=0; i<VECTOR_SIZE; i++)
      {
	s.append(elm[i]);
	if (i < (VECTOR_SIZE - 1)) s.append(", ");
      }
    s.append("]");
    return (s.toString());
  }  // toString

  public void print()
  {
    System.out.print("\n"+this.toString());
  }  // print

  public double length()
  {
    double my_length = 0;
  
    for(int i=0; i<(VECTOR_SIZE-1); i++)
      my_length += elm[i] * elm[i];
    
    return(Math.sqrt(my_length));
  }  // length

  public void normalize()
  {
    double my_length = length();
  
    for(int i=0; i<VECTOR_SIZE; i++)
      elm[i] /= my_length;
  }  // normalize

  public Vector2D multiplied_by(double scalar)
  {
    Vector2D result =
      new Vector2D(elm[X] * scalar, elm[Y] * scalar, elm[Z] * scalar);
    return result;
  }  // multiplied_by

  public Vector2D divided_by(double scalar)
  {
    Vector2D result =
      new Vector2D(elm[X] / scalar, elm[Y] / scalar, elm[Z] / scalar);
    return result;
  }  // divided_by

  public void clamp(double min, double max)
  {
    for(int i=0; i<(VECTOR_SIZE-1); i++)
      {
	if (elm[i] > max) elm[i] = max;
	else if (elm[i] < min) elm[i] = min;
      }
  }  // clamp

  public Vector2D cross_product(Vector2D B)
  {
    Vector2D result =
      new Vector2D(elm[Y] * B.elm[Z] - B.elm[Y] * elm[Z],
		 B.elm[X] * elm[Z] - elm[X] * B.elm[Z],
		 elm[X] * B.elm[Y] - B.elm[X] * elm[Y]);

    return result;
  }  // cross_product



  public int ccw(Vector2D B, Vector2D C)
  {
    if (this.equals(B)) {
      if (this.equals(C)) return 0;
      else return 2;
    }

    if ((this.equals(C)) || (B.equals(C))) return 0;

    double ccw_value = 
      (B.elm[X] * C.elm[Y] - C.elm[X] * B.elm[Y] -
       elm[X] * C.elm[Y] + C.elm[X] * elm[Y] +
       elm[X] * B.elm[Y] - B.elm[X] * elm[Y]);

    if (ccw_value != 0) {
      if (ccw_value > 0) return 1;
      else return -1;
    }

    Vector2D L = new Vector2D (B.subtract(this));
    Vector2D R = new Vector2D (C.subtract(this));
    if ((L.dot_product(R)) < 0) return -2;

    L = this.subtract(B);
    R = C.subtract(B);
    if ((L.dot_product(R)) < 0) return 2;

    return 0;
    
  }  // ccw

}  // Vector2D class

