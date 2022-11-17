public class Test
{
	public static void main(String[] args)
	{
		Integer[][] a = { {1, 4}, 
											{3, 2},
											{9, 5} };
		Double[][] b = { {1.6, 3.1, 7.8},
										 {2.5, 1.0, 2.4} };
		Integer[][] c = { {5, 6, 6, 8},
											{2, 2, 2, 8},
											{6, 6, 2, 8},
											{2, 3, 6, 7} };
		Integer[][] d = { { 3,  0,  0,  3,  0},
											{-3,  0, -2,  0,  0},
											{ 0, -1,  0,  0, -3},
											{ 0,  0,  0,  3,  3},
											{ 0, -1,  2,  0,  1} };
		Manix A = new Manix(a);
		Manix B = new Manix(b);
		Manix C = new Manix(c);
		Manix D = new Manix(d);
		System.out.println(D);
		System.out.println(String.format("Determinant: %.2f", D.det()));
//		System.out.println(D.triangulateUpper());
		System.out.println(C);
		System.out.println(C.det());
		System.out.println(C.reshape(1, 16));
		System.out.println(A.dot(B, Double.class));
	}
}
