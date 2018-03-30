//
// $Id: Matrix2D.java,v 1.2 1997/03/03 22:29:27 min Exp min $
//
// Simple 3 by 3 Matrix2D class
//

package com.geditor.math;

import java.lang.*;

public class Matrix2D
{
  private double elm[];
  
  public Matrix2D()
  {
    elm = new double[9];
    identity();
  }  // constructor, creates identity Matrix2D
  
  public Matrix2D(double[] src)
  {
    elm = new double[9];
    for (int i=0; i<9; i++)
       elm[i]=src[i];
  }  // constructor, creates copy of matrix

  public Matrix2D(Matrix2D src)
  {
    elm = new double[9];
    set(src);
  }  // copy constructor 

  public void set(Matrix2D src)
  {
    for(int i=0; i<9; i++)
      elm[i] = src.elm[i];
  }  // set, Matrix2D source

  public void set(Vector2D a, Vector2D b)
  {
    elm[0] = a.elm[0];
    elm[1] = a.elm[1];
    elm[2] = 0;
    elm[3] = b.elm[0];
    elm[4] = b.elm[1];
    elm[5] = 0;
  }  // set, with three Vector2Ds
  
  public void set(int index, double value)
  {
    elm[index] = value;
  }  // set an element
  
  public double get(int index)
  {
    return elm[index];
  }  // get an element

  public void print()
  {
    for(int i=0; i<3; i++)
    {
      System.out.print("(");
      for(int j=0; j<3; j++)
      {
        System.out.print(elm[i*3+j]);
        if (j<2) System.out.print(", ");
      }
      System.out.println(")");
    }  // for, each row
  }  // print 
      
  public void identity()
  {
    for(int i=0; i<9; i++) elm[i] = 0;
    elm[0] = 1;
    elm[4] = 1;
    elm[8] = 1;
  }  // identity
  
  public Vector2D vec_premultiply(Vector2D V)
  {
    Vector2D result =
      new Vector2D(V.elm[0] * this.elm[0] +
                   V.elm[1] * this.elm[3] +
                   V.elm[2] * this.elm[6],

                   V.elm[0] * this.elm[1] +
                   V.elm[1] * this.elm[4] +
                   V.elm[2] * this.elm[7],

                   V.elm[0] * this.elm[2] +
                   V.elm[1] * this.elm[5] +
                   V.elm[2] * this.elm[8]);
    return result;
  }  // vec_premultiply
      
  public Vector2D vec_postmultiply(Vector2D V)
  {
    Vector2D result =
      new Vector2D(V.elm[0] * this.elm[0] +
                   V.elm[1] * this.elm[1] +
                   V.elm[2] * this.elm[2],
                 
                   V.elm[0] * this.elm[3] +
                   V.elm[1] * this.elm[4] +
                   V.elm[2] * this.elm[5],

                   V.elm[0] * this.elm[6] +
                   V.elm[1] * this.elm[7] +
                   V.elm[2] * this.elm[8]);                 
    return result;
  }  // vec_postmultiply
  
  public Matrix2D Matrix2D_multiply(Matrix2D M)
  {
    Matrix2D result = new Matrix2D();
    
    result.elm[0] = this.elm[0] * M.elm[0] +
                    this.elm[1] * M.elm[3] +
                    this.elm[2] * M.elm[6];

    result.elm[1] = this.elm[0] * M.elm[1] +
                    this.elm[1] * M.elm[4] +
                    this.elm[2] * M.elm[7];

    result.elm[2] = this.elm[0] * M.elm[2] +
                    this.elm[1] * M.elm[5] +
                    this.elm[2] * M.elm[8];

    result.elm[3] = this.elm[3] * M.elm[0] +
                    this.elm[4] * M.elm[3] +
                    this.elm[5] * M.elm[6];

    result.elm[4] = this.elm[3] * M.elm[1] +
                    this.elm[4] * M.elm[4] +
                    this.elm[5] * M.elm[7];

    result.elm[5] = this.elm[3] * M.elm[2] +
                    this.elm[4] * M.elm[5] +
                    this.elm[5] * M.elm[8];

    result.elm[6] = this.elm[6] * M.elm[0] +
                    this.elm[7] * M.elm[3] +
                    this.elm[8] * M.elm[6];

    result.elm[7] = this.elm[6] * M.elm[1] +
                    this.elm[7] * M.elm[4] +
                    this.elm[8] * M.elm[7];

    result.elm[8] = this.elm[6] * M.elm[2] +
                    this.elm[7] * M.elm[5] +
                    this.elm[8] * M.elm[8];
    return result;
  }  // Matrix2D_multiply

  public Matrix2D add(Matrix2D M)
  {
    Matrix2D result = new Matrix2D();
    
    for(int i=0; i<9; i++)
      result.elm[i] = this.elm[i] + M.elm[i];
      
    return result;
  }  // add

  public Matrix2D scalar_multiply(double scalar)
  {
    Matrix2D result = new Matrix2D();
    
    for(int i=0; i<9; i++)
      result.elm[i] = scalar * this.elm[i];
      
    return result;
  }  // scalar_multiply
    
  public Matrix2D transpose()
  {
    Matrix2D result = new Matrix2D();
    
    result.elm[0] = elm[0];
    result.elm[1] = elm[3];
    result.elm[2] = elm[6];    
    result.elm[3] = elm[1];
    result.elm[4] = elm[4];
    result.elm[5] = elm[7];
    result.elm[6] = elm[2];
    result.elm[7] = elm[5];
    result.elm[8] = elm[8];

    return result;
  }  // transpose

  public void translate(double dx, double dy)
  {
    identity();
    elm[2] = dx;
    elm[5] = dy;
  }  // translate
  
  public void translate(Vector2D V)
  {
    identity();
    elm[2] = V.elm[0];
    elm[5] = V.elm[1];
  }  // translate with Vector2D par.
  
  public void rotate(double rad)
  {
    identity();
    
    elm[0] =  Math.cos(rad);
    elm[1] = -Math.sin(rad);
    elm[3] = -elm[1];
    elm[4] =  elm[0];
  }  // rotate
    
  public void scale(double sx, double sy)
  {
    identity();
    
    elm[0] = sx;
    elm[4] = sy;
  }  // scale  
    
}  // class Matrix2D

