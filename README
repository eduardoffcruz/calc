
============================ calc ============================
______________________________________________________________
calc is a command-line calculator implemented in java. 

The input stream is divided into Tokens (automata based
lexical anaysis). Then, the following grammar is applied,
following an iterative based approach:
    S -> S + M | S - M | M
    M -> M * D | M \ D | D
    D -> num | ( S ) | D ^ D | D r D    

Finally, the expression result is outputed to the stdout.
Note: Some error detection mechanisms are in place to alert
    the user for possible input mistakes. 
______________________________________________________________

HOW TO:
	+Compile:
		\_Open Terminal in calc folder
		  > javac Main.java

	+Run:
		\_Open Terminal in calc folder
		 > java Main.java "<expression>"

            example: 
             > java Main.java ((1.5+2*3/2)^2)r2

Requirements: Java SDK
----------------------------------------------------------------
Supported operators :
              +   ADDITION:   2+3 <==> [2 plus 3]
              -   SUBTRACTION:    5-6 <==> [5 minus 6]
              *   MULTIPLICATION: 1*3 <==> [1 multiplied by 3]
              /   DIVISION:   3/2 <==> [3 divided by 2]
              ^   POWER:  2^3 <==> [2 to_the_power of 3]
              r   ROOT:   25r2 <==> [square_root of 25]
             +,-  SIGNED: -10 <==> [negative 10]
             ( )  PARENTHESES:    ((2+(1))*(4))

    ... check testing suite, for examples [at Main.java].
----------------------------------------------------------------
Developed by 							 
	Eduardo F. F. Cruz	-	eduardoffcruz@gmail.com
----------------------------------------------------------------

============================= @2022 ============================