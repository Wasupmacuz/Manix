
import java.util.Arrays;

/**
 * Matrix Manipulator with chaining functions. Basic matrix manipulating functions and some quality of life functions included.
 * @author Wasupmacuz
 */
public class Manix
{
	private Number[][] arr;
	private int m, n;
	int accuracy = 15;

//	double epsilon = 0.001; // used for testing how close a number is to zero.

	/**
	 * Creates a matrix from a pre-existing array.
	 * @param array 2d array to act as a matrix. Assumes rectangular in shape.
	 */
	public Manix(Number[][] array)
	{
		setArr(array);
		m = getArr().length;
		n = getArr()[0].length;
	}

	/**
	 * Creates a matrix with m rows and n columns.
	 * @param m The number of rows
	 * @param n The number of columns
	 */
	public Manix(int m, int n)
	{
		setArr(new Number[m][n]);
		this.m = m;
		this.n = n;
	}

	/**
	 * Provides a new identity matrix.<br>
	 * Static for quality of life.
	 * @param size Width and height of the matrix created.
	 * @return A square matrix with 1's on its diagonal and 0's everywhere else.
	 */
	public static Manix I(int size)
	{
		Integer[][] I = new Integer[size][size];
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(i == j)
					I[i][j] = 1;
				else
					I[i][j] = 0;
			}
		}
		return new Manix(I);
	}

	/**
	 * Provides a new matrix with 0's everywhere.<br>
	 * Calls the fill() method.<br>
	 * Static for quality of life.
	 * @param rows The number of rows this matrix should have.
	 * @param columns The number of columns this matrix should have.
	 * @return fill(new Integer(0), rows, columns)
	 */
	public static Manix Null(int rows, int columns)
	{
		return fill(new Integer(0), rows, columns);
	}

	/**
	 * Creates a new matrix with random values everywhere.<br>
	 * Static for quality of life.<br>
	 * Values can be scaled with mul(Number, Class) and shifted with add(Number, Class) or sub(Number, Class).
	 * @param rows The number of rows this matrix should have.
	 * @param columns The number of columns this matrix should have.
	 * @return A matrix with a random number [0.0, 1.0) as a Double in each element.
	 */
	public static Manix rand(int rows, int columns)
	{
		Number[][] a = new Double[rows][columns];
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < columns; c++)
			{
				a[r][c] = Math.random();
			}
		}
		
		return new Manix(a);
	}
	
	/**
	 * Provides a new matrix with each element specified by {@code value}.<br>
	 * Static for quality of life.
	 * @param value The value to put in each element of the matrix.
	 * @param rows The number of rows this matrix should have.
	 * @param columns The number of columns this matrix should have.
	 * @return A matrix filled with any one number.
	 */
	public static Manix fill(Number value, int rows, int columns)
	{
		Number[][] a = new Number[rows][columns];
		for(int i = 0; i < columns; i++)
			a[0][i] = value;
		for(int i = 1; i < rows; i++)
			a[i] = a[0];
		return new Manix(a);
	}
	
	/**
	 * Sets a specified element to a specified value.
	 * @param row The row of the element to be replaced.
	 * @param col The column of the element to be replaced.
	 * @param val The value to replace with.
	 */
	public void setVal(int row, int col, Number val)
	{
		arr[row][col] = val;
	}
	
	/**
	 * Calculates the arithmetic mean (average) of all elements in this matrix.
	 * @return The sum of all elements, divided by the number of elements.
	 */
	public double mean()
	{
		double mean = 0;
		for(int r = 0; r < m; r++)
		{
			for(int c = 0; c < n; c++)
			{
				mean += getArr()[r][c].doubleValue();
			}
		}
		mean /= m*n;
		return mean;
	}

	/**
	 * Creates a new, transposed version of this matrix and leaves this matrix unaltered.
	 * @return A transposed version of this matrix.
	 */
	public Manix T()
	{
		Number[][] T = new Number[getN()][getM()]; // swap lengths of columns and rows

		//for each element
		for(int i = 0; i < getArr().length; i++)
			for(int j = 0; j < getArr()[0].length; j++)
				//swap columns and rows
				T[j][i] = getArr()[i][j];

		return new Manix(T);
	}

	/**
	 * Adds each element of the addend to the respective element of this matrix.<br>
	 * Creates a new matrix and leaves this matrix unaltered.
	 * @param addend The matrix that we want to add onto this one.
	 * @param numberType A subclass of {@code Number.class}.<br>In essence, {@code Double.class}, {@code Long.class}, {@code Float.class}, {@code Integer.class}, {@code Short.class}, or {@code Byte.class}
	 * @return A new matrix with each element the sum of this one's and the addend's.
	 */
	public <N extends Number> Manix add(Manix addend, Class<N> numberType)
	{
		if(getM() != addend.getM() || getN() != addend.getN())
			throw new IndexOutOfBoundsException("Cannot add matrix of size ["+getM()+", "+getN()+"] with matrix of size ["+addend.getM()+", "+addend.getN()+"].");

		Number[][] C = new Number[getM()][getN()];

		for(int i = 0; i < getM(); i++)
		{
			for(int j = 0; j < getN(); j++)
			{
				if(numberType == Double.class)
					C[i][j] = getArr()[i][j].doubleValue() + addend.getArr()[i][j].doubleValue();
				else if(numberType == Long.class)
					C[i][j] = getArr()[i][j].longValue() + addend.getArr()[i][j].longValue();
				else if(numberType == Float.class)
					C[i][j] = getArr()[i][j].floatValue() + addend.getArr()[i][j].floatValue();
				else if(numberType == Integer.class)
					C[i][j] = getArr()[i][j].intValue() + addend.getArr()[i][j].intValue();
				else if(numberType == Short.class)
					C[i][j] = getArr()[i][j].shortValue() + addend.getArr()[i][j].shortValue();
				else if(numberType == Byte.class)
					C[i][j] = getArr()[i][j].byteValue() + addend.getArr()[i][j].byteValue();
			}
		}

		return new Manix(C);
	}

	/**
	 * Subtracts each element of the minuend from the respective element of this matrix.<br>
	 * Creates a new matrix and leaves this matrix unaltered.
	 * @param minuend The matrix that we want to subtract from this one.
	 * @param numberType A subclass of {@code Number.class}.<br>In essence, {@code Double.class}, {@code Long.class}, {@code Float.class}, {@code Integer.class}, {@code Short.class}, or {@code Byte.class}
	 * @return A new matrix with each element the difference of this one's and the minuend's.
	 */
	public <N extends Number> Manix sub(Manix minuend, Class<N> numberType)
	{
		if(getM() != minuend.getM() || getN() != minuend.getN())
			throw new IndexOutOfBoundsException("Cannot add matrix of size ["+getM()+", "+getN()+"] with matrix of size ["+minuend.getM()+", "+minuend.getN()+"].");

		Number[][] C = new Number[getM()][getN()];

		for(int i = 0; i < getM(); i++)
		{
			for(int j = 0; j < getN(); j++)
			{
				if(numberType == Double.class)
					C[i][j] = getArr()[i][j].doubleValue() - minuend.getArr()[i][j].doubleValue();
				else if(numberType == Long.class)
					C[i][j] = getArr()[i][j].longValue() - minuend.getArr()[i][j].longValue();
				else if(numberType == Float.class)
					C[i][j] = getArr()[i][j].floatValue() - minuend.getArr()[i][j].floatValue();
				else if(numberType == Integer.class)
					C[i][j] = getArr()[i][j].intValue() - minuend.getArr()[i][j].intValue();
				else if(numberType == Short.class)
					C[i][j] = getArr()[i][j].shortValue() - minuend.getArr()[i][j].shortValue();
				else if(numberType == Byte.class)
					C[i][j] = getArr()[i][j].byteValue() - minuend.getArr()[i][j].byteValue();
			}
		}

		return new Manix(C);
	}

	/**
	 * Performs matrix multiplication with this matrix on the left side of the product.<br>
	 * Creates a new matrix and leaves this matrix unaltered.
	 * @param multiplicand The matrix that we want to 'dot' with this one.
	 * @param numberType A subclass of {@code Number.class}.<br>In essence, {@code Double.class}, {@code Long.class}, {@code Float.class}, {@code Integer.class}, {@code Short.class}, or {@code Byte.class}
	 * @return A new matrix which is the product of the matrix multiplication.<br>{@code A.dot(B) != B.dot(A)}
	 */
	public <N extends Number> Manix dot(Manix multiplicand, Class<N> numberType)
	{
		if(getN() != multiplicand.getM())
			throw new IndexOutOfBoundsException("Cannot multiply matrix of width "+getN()+" with matrix of height "+multiplicand.getM() + ".");

		Number[][] C = new Number[getM()][multiplicand.getN()];
		for(int i = 0; i < multiplicand.getN(); i++) // columns of B
		{
			Number[] b = multiplicand.column(i);
			for(int j = 0; j < getM(); j++) // rows of A
			{
				Number[] a = row(j);
				Number sum = new Integer(0);

				for(int x = 0; x < getN(); x++)
				{
					if(numberType == Double.class)
						sum = Double.valueOf(sum.doubleValue() + a[x].doubleValue() * b[x].doubleValue());
					else if(numberType == Long.class)
						sum = Long.valueOf(sum.longValue() + a[x].longValue() * b[x].longValue());
					else if(numberType == Float.class)
						sum = Float.valueOf(sum.floatValue() + a[x].floatValue() * b[x].floatValue());
					else if(numberType == Integer.class)
						sum = Integer.valueOf(sum.intValue() + a[x].intValue() * b[x].intValue());
					else if(numberType == Short.class)
						sum = Short.valueOf((short) (sum.shortValue() + a[x].shortValue() * b[x].shortValue()));
					else if(numberType == Byte.class)
						sum = Byte.valueOf((byte) (sum.byteValue() + a[x].byteValue() * b[x].byteValue()));
				}

				C[j][i] = sum;
			}
		}

		return new Manix(C);
	}

	/**
	 * Multiplies this matrix by {@code b}.<br>
	 * Creates a new matrix and leaves this one unaltered
	 * @param b The scalar to multiply the matrix by.
	 * @param numberType A subclass of {@code Number.class}.<br>In essence, {@code Double.class}, {@code Long.class}, {@code Float.class}, {@code Integer.class}, {@code Short.class}, or {@code Byte.class}
	 * @return A new matrix with each element being {@code b} times the respective element of this matrix
	 */
	public <N extends Number> Manix mul(Number b, Class<N> numberType)
	{

		Number[][] C = new Number[getM()][getN()];
		for(int i = 0; i < getM(); i++)
		{
			for(int j = 0; j < getN(); j++)
			{
				if(numberType == Double.class)
					C[i][j] = getArr()[i][j].doubleValue() * b.doubleValue();
				else if(numberType == Long.class)
					C[i][j] = getArr()[i][j].longValue() * b.longValue();
				else if(numberType == Float.class)
					C[i][j] = getArr()[i][j].floatValue() * b.floatValue();
				else if(numberType == Integer.class)
					C[i][j] = getArr()[i][j].intValue() * b.intValue();
				else if(numberType == Short.class)
					C[i][j] = getArr()[i][j].shortValue() * b.shortValue();
				else if(numberType == Byte.class)
					C[i][j] = getArr()[i][j].byteValue() * b.byteValue();
			}
		}

		return new Manix(C);
	}

	/**
	 * Hadamard multiplication: multiplies each element of the multiplicand with the respective element of this matrix.<br>
	 * Creates a new matrix and leaves this matrix unaltered.
	 * @param multiplicand The matrix that we want to multiply with this one.
	 * @param numberType A subclass of {@code Number.class}.<br>In essence, {@code Double.class}, {@code Long.class}, {@code Float.class}, {@code Integer.class}, {@code Short.class}, or {@code Byte.class}
	 * @return A new matrix with each element the product of this one's and the multiplicand's.<br>{@code A.mul(B) == B.mul(A)}
	 */
	public <N extends Number> Manix mul(Manix multiplicand, Class<N> numberType)
	{
		if(getM() != multiplicand.getM() || getN() != multiplicand.getN())
			throw new IndexOutOfBoundsException("Cannot compute hadamard product on a matrix of size ["+getM()+", "+getN()+"] with matrix of size ["+multiplicand.getM()+", "+multiplicand.getN()+"].");

		Number[][] C = new Number[getM()][getN()];
		for(int i = 0; i < getM(); i++)
		{
			for(int j = 0; j < getN(); j++)
			{
				if(numberType == Double.class)
					C[i][j] = getArr()[i][j].doubleValue() * multiplicand.getArr()[i][j].doubleValue();
				else if(numberType == Long.class)
					C[i][j] = getArr()[i][j].longValue() * multiplicand.getArr()[i][j].longValue();
				else if(numberType == Float.class)
					C[i][j] = getArr()[i][j].floatValue() * multiplicand.getArr()[i][j].floatValue();
				else if(numberType == Integer.class)
					C[i][j] = getArr()[i][j].intValue() * multiplicand.getArr()[i][j].intValue();
				else if(numberType == Short.class)
					C[i][j] = getArr()[i][j].shortValue() * multiplicand.getArr()[i][j].shortValue();
				else if(numberType == Byte.class)
					C[i][j] = getArr()[i][j].byteValue() * multiplicand.getArr()[i][j].byteValue();
			}
		}

		return new Manix(C);
	}

	/**
	 * Finds the determinant of a matrix using LU decomposition with time complexity O(2n^3/3) if it works.<br>
	 * If it fails, use Cofactor expansion with time complexity O(n!). Yikes.
	 * @return The determinant of this matrix as a Double.
	 */
	public Double det()
	{
		if(getM() != getN())
			throw new IndexOutOfBoundsException("Cannot find the determinant of non-square matrices.");

		Double det = Double.valueOf(1);
		try {
			Manix[] lu = LU();
			for(int i = 0; i < getM(); i++)
			{
				det *= lu[1].getArr()[i][i].doubleValue();
			}
		}catch(ArithmeticException e)
		{
			det = det(this);
		}


		return det.doubleValue();
	}
	
	/**
	 * Finds the determinant of a specified matrix using the cofactor expansion method.<br>
	 * O(n!) time complexity.
	 * @param matrix Passes a matrix since the method is recursive.
	 * @return The determinant of {@code matrix} as a Double.
	 */
	private Double det(Manix matrix)
	{
		Number[][] a = matrix.getArr();
		int s = matrix.getM();
		Number[] row = matrix.row(0);

		Double det = Double.valueOf(0);
		Integer sign = Integer.valueOf(1);

		for(int i = 0; i < row.length; i++)
		{
			Number mul = row[i];
			Number[][] subMatrix = new Number[s - 1][s - 1];
			int x = 0;
			for(int j = 0; j < row.length; j++)
			{
				if(j == i)
					continue;

				for(int k = 1; k < s; k++)
				{
					subMatrix[k - 1][x] = a[k][j];
				}
				x++;
			}

			if(s - 1 == 1)
			{
					det = det.doubleValue() + sign.intValue()*mul.doubleValue()*subMatrix[0][0].doubleValue();
			}
			else
			{
					det = det.doubleValue() + sign.intValue()*mul.doubleValue()*det(new Manix(subMatrix)).doubleValue();
			}

			sign = Integer.valueOf(-1*sign.intValue());
		}
		
		return det;
	}

	/**
	 * Performs LU factorization on this matrix.
	 * @return A matrix array populated with Doubles with the first element being the Lower triangular matrix, and the second element being the Upper triangular matrix
	 */
	public Manix[] LU()
	{
		if(getM() != getN())
			throw new IndexOutOfBoundsException("Cannot perform LU decomposition on non-square matrices.");

		int s = getM();
		Number[][] a = getArr();
		Double[][] l = new Double[s][s];
		Double[][] u = new Double[s][s];
		if(a[0][0].doubleValue() == 0)
			throw new ArithmeticException("Unable to find LU factorization for this matrix in its given state. First element of matrix is zero.");

		for(int i = 0; i < s; i++) // populate upper and lower matrices with known information
		{
			u[0][i] = a[0][i].doubleValue();
			l[i][0] = a[i][0].doubleValue()/a[0][0].doubleValue();
			l[i][i] = 1d;
			for(int j = 0; j < i; j++)
			{
				u[i][j] = 0d;
				l[j][i] = 0d;
			}
		}
		for(int r = 1; r < s; r++) // stay in the upper triangle, including the diagonal.
		{
			for(int c = r; c < s; c++)
			{
				if(u[r][c] == null)
				{
					u[r][c] = a[r][c].doubleValue();
					for(int i = 0; i < r; i++)
					{
						if(l[r][i] == null) // u[i][i] is already solved, solve this before moving on
						{
							l[r][i] = a[r][i].doubleValue();
							for(int j = 0; j < i; j++)
							{
								l[r][i] -= u[j][i]*l[r][j];
							}
							l[r][i] /= u[i][i];
							
							if(l[r][i] == Double.NaN || l[r][i] == Double.POSITIVE_INFINITY || l[r][i] == Double.NEGATIVE_INFINITY)
								throw new ArithmeticException("Unable to find LU factorization for this matrix in its given state. Calculated " + l[r][i] + ".");
						}
						u[r][c] -= u[i][c]*l[r][i];
					}
				}
			}
		}

		Manix[] out = new Manix[2];
		out[0] = new Manix(l);
		out[1] = new Manix(u);
		return out;
	}
	
//	public Matrix echelon()
//	{
//		Number[][] a = arr;
//		boolean zeroed = false;
//		while(!zeroed)
//		{
//			zeroed = true;
//			
//		}
//		
//		return new Matrix(a);
//	}

	/**
	 * Reshapes this m by n matrix into a rows by cols matrix.<br>
	 * Each element is placed into the new matrix sequentially. 
	 * @param rows The number of rows in the new matrix.
	 * @param cols The number of columns in the new matrix.
	 * @return A new matrix with all the same elements of this one, but in a different shape.<br>The matrices have the same area and, therefore, assert {@code m*n == rows*cols}
	 */
	public Manix reshape(int rows, int cols)
	{
		if(rows*cols != m*n)
			throw new IndexOutOfBoundsException(m*n + " items do not fit properly into " + rows*cols + " slots.");
		Number[][] newArr = new Number[rows][cols];
		
		for(int r = 0; r < m; r++)
		{
			for(int c = 0; c < n; c++)
			{
				Number e = arr[r][c];
				int rn = (r*n + c)/cols;
				int cn = (r*n + c)%cols;
				newArr[rn][cn] = e;
			}
		}

		arr = newArr;
		m = rows;
		n = cols;
		return this;
	}
	
	/**
	 * Gives an entire specified row of this matrix.
	 * @param m The row to copy.
	 * @return A copy of the {@code m}th row of this matrix as an array of Number
	 */
	public Number[] row(int m)
	{
		return Arrays.copyOf(getArr()[m], getArr()[m].length);
	}

	/**
	 * Gives an entire specified column of this matrix.
	 * @param n The column to copy.
	 * @return A copy of the {@code n}th column of this matrix as an array of Number
	 */
	public Number[] column(int n)
	{
		Number[] column = new Number[getArr().length];
		for(int i = 0; i < column.length; i++)
		{
			column[i] = getArr()[i][n];
		}
		return column;
	}

	/**
	 * Gives an entire specified row of a specified matrix.
	 * @param in The 2d Number array to copy the row from.
	 * @param m The row to copy.
	 * @return A copy of the {@code m}th row of {@code in} as an array of Number
	 */
	public static Number[] row(Number[][] in, int m)
	{
		return in[m];
	}

	/**
	 * Gives an entire specified column of a specified matrix.
	 * @param in The 2d Number array to copy the column from.
	 * @param n The column to copy.
	 * @return A copy of the {@code n}th column of {@code in} as an array of Number
	 */
	public static Number[] column(Number[][] in, int n)
	{
		Number[] column = new Number[in.length];
		for(int i = 0; i < column.length; i++)
		{
			column[i] = in[i][n];
		}
		return column;
	}

	/**
	 * Provides the 2d Number array in which the values of this matrix are stored.
	 * @return arr, the array of Number of this matrix.
	 */
	public Number[][] getArr()
	{
		return arr;
	}

	/**
	 * Sets the 2d Number array that this matrix is based on.<br>
	 * Also updates m and n.
	 * @param arr The new 2d Number array.
	 */
	public void setArr(Number[][] arr)
	{
		this.arr = arr;
		m = arr.length;
		n = arr[0].length;
	}

	/**
	 * Sets the number of decimal places that will be displayed when toString() is called.
	 * @param accuracy A positive integer or zero.
	 */
	public void setDecimalAccuracy(int accuracy)
	{
		this.accuracy = accuracy >= 0 ? accuracy : 0;
	}

	/**
	 * The number of rows in this matrix
	 * @return m, the number of rows.
	 */
	public int getM()
	{
		return m;
	}

	/**
	 * The number of columns in this matrix
	 * @return n, the number of columns.
	 */
	public int getN()
	{
		return n;
	}

	/**
	 * Provides each element of this matrix, equally spaced for readability, and with each row surrounded by vertical bars to simulate the traditional matrix notation.
	 */
	@Override
	public String toString()
	{
		String specifier = "";
		if(arr[0][0] instanceof Double || arr[0][0] instanceof Float)
			specifier = "f";
		if(arr[0][0] instanceof Long || arr[0][0] instanceof Integer || arr[0][0] instanceof Short || arr[0][0] instanceof Byte)
			specifier = "d";
		String s = "";
		int maxLength = 0;
		for(int i = 0; i < getM(); i++)
		{
			for(int j = 0; j < getN(); j++)
			{
				int l;
				if(specifier == "f")
					l = String.format("%.0" + specifier, arr[i][j]).toCharArray().length;
				else
					l = arr[i][j].toString().toCharArray().length;
				if(l > maxLength)
					maxLength = l;
			}
		}
		for(int i = 0; i < getM(); i++)
		{
			s += "|";
			for(int j = 0; j < getN(); j++)
			{
				if(specifier == "f")
					s += String.format("%"+(maxLength + accuracy + 3)+"."+accuracy+specifier,arr[i][j]);
				else
					s += String.format("%"+(maxLength + 3)+specifier, arr[i][j]);
			}

			s += "|\n";
		}
		return s;
	}
}
