public class Main {
    
    private static void assertEquals(double expected, double actual) throws Exception{
        if (Double.compare(expected, actual) != 0){
            throw new Exception("Assertion error:\n\t- expected: "+expected+", actual: "+actual);
        }
    }

    public static void test() throws Exception{
        Calculator calc = new Calculator(true);
        assertEquals(2*(2+3)+(-1)+2, calc.calculate("2*(2+3)+(-1)+2"));
        assertEquals(1.4+2.12*5+3.0/3.0/2, calc.calculate("1.4+2.12*5+3.0/3.0/2"));
        assertEquals(-1-2*(-3)-2.0/(-1.5)*3+7*2-4*1+1-2*5, calc.calculate("-1-2*-3-2.0/-1.5*3+7*2-4*1+1-2*5"));
        assertEquals(2*3+2*1+2*5*3+7*2*4*1+1*4+9+2*5, calc.calculate("2*3+2*1+2*5*3+7*2*4*1+1*4+9+2*5"));
        assertEquals(2*3+2*1+2*5*3+7*2+2*5*3+7*2*4*1+1+2*5*4*1+1*4+9+2*5*1+2*5*3+7*2*4*1+1+2*5, calc.calculate("2*3+2*1+2*5*3+7*2+2*5*3+7*2*4*1+1+2*5*4*1+1*4+9+2*5*1+2*5*3+7*2*4*1+1+2*5"));
        assertEquals(2*(3)+(2)*(1+2*5*3+7)*2+2*(5*(3+7))*2*4*(1+1+2*5)*4*((1+1*4+9+2*5*1+2*5*3+7*2*4*1+1)+(2*5-(-1-2*-3-2.0/-1.5*3+7*2-4*1+1-2*5))),calc.calculate("2*(3)+(2)*(1+2*5*3+7)*2+2*(5*(3+7))*2*4*(1+1+2*5)*4*((1+1*4+9+2*5*1+2*5*3+7*2*4*1+1)+(2*5-(-1-2*-3-2.0/-1.5*3+7*2-4*1+1-2*5)))"));
        assertEquals(2*(3)+(2)*(1+2*5*3+7)*2+2*(5*(3+7))-1-2*(-3)-2.0/(-1.5)*3+7*2-4*1+1-2*5*2*4*(1+1+2*5)*4*((1+1*4+9+2*5*1+2*5*3+7*2*4*1+1)+(2*5-(-1-2*-3-2.0/-1.5*3+7*2-4*1+1-2*5))),calc.calculate("2*(3)+(2)*(1+2*5*3+7)*2+2*(5*(3+7))-1-2*(-3)-2.0/(-1.5)*3+7*2-4*1+1-2*5*2*4*(1+1+2*5)*4*((1+1*4+9+2*5*1+2*5*3+7*2*4*1+1)+(2*5-(-1-2*-3-2.0/-1.5*3+7*2-4*1+1-2*5)))"));
        assertEquals(1.315686955709436E8,calc.calculate("(-2*(3))^(-2-1-2*(-3)-2.0/(-1.5)*3+7*2-4*1+1-2*5)*(1+2*5*3+7)*2+2*(5*(3+7))*2*4*(1+1+2*5)*4*((1+1*4+9+2*5*1+2*5*3+7*2*4*1+1)+(2*5-(-1-2*-3-2.0/-1.5*3+7*2-4*1+1-2^5^-2)))"));
        assertEquals(-1-2*(-3)-2.0/(-1.5)*3+7*2-4*1+1-2*5*(3)+(2)*(1+2*5*3+7)*2+2*(5*(3+7))*2*4*(1+1+2*5)*4*((1+1*4+9+2*5*1+2*5*3+7*2*4*1+1)+(2*5-(-1-2*-3-2.0/-1.5*3+7*2-4*1+1-2*5))),calc.calculate("-1-2*(-3)-2.0/(-1.5)*3+7*2-4*1+1-2*5*(3)+(2)*(1+2*5*3+7)*2+2*(5*(3+7))*2*4*(1+1+2*5)*4*((1+1*4+9+2*5*1+2*5*3+7*2*4*1+1)+(2*5-(-1-2*-3-2.0/-1.5*3+7*2-4*1+1-2*5)))"));
        assertEquals(148450.75848197556,calc.calculate("(2*(3))r(-2-1-2*(-3)-2.0/(-1.5)*3+7*2-4*1+1-2*5)*(1+2*5*3+7)*2+2*(5*(3+7))*2*4*(1+1+2*5)r4*((1+1*4+9+2*5*1+2*5*3+7*2*4*1+1)+(2*5-(-1-2*-3-2.0/-1.5*3+7*2-4*1+1+2^5r-2)))"));
    }

    public static void main(String[] args) throws Exception{
        /**
         * Calculator supports the following OPERATORS :
         *      +   ADDITION:   2+3 <==> [2 plus 3]
         *      -   SUBTRACTION:    5-6 <==> [5 minus 6]
         *      *   MULTIPLICATION: 1*3 <==> [1 multiplied by 3]
         *      /   DIVISION:   3/2 <==> [3 divided by 2]
         *      ^   POWER:  2^3 <==> [2 to_the_power of 3]
         *      r   ROOT:   25r2 <==> [square_root of 25]
         *     +,-  SIGNED: -10 <==> [negative 10]
         *     ( )  PARENTHESES:    ((2+(1))*(4))
         * 
         * ... see testing suite, for examples.
         */
        //test();

        Calculator calc = new Calculator();
        if (args.length>0)
            try {
                System.out.println(" = "+calc.calculate(args[0]));
            } catch (Exception e) {
                System.out.println(e);
            }
        else
            System.out.println("Missing expression.\nTry:\n>  java Main.java \"<expression>\"");
    }
}
