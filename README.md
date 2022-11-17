# Manix
A Java based matrix manipulator. Similar functionality to basic numpy functions.

Appropriate functions return a Manix for method chaining:
```
Matrix A;
Matrix B;

// set up matrices

Double determinant = B.T().dot(A, Integer.class).det();
```

Translation from python numpy to Java Manix is not 1:1 because operator overloading is not supported in Java, but it is doable in most cases.
For example:
```
np.rand(inputSize, outputSize) - 0.5
```
can be translated as
```
Manix.rand(inputSize, outputSize).sub(Manix.fill(Double.valueOf(0.5), inputSize, outputSize), Double.class);
```
