import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import lexer.*;

public class Calculator{
    private Lexer lexer;
    public boolean printTokens;

    private ListIterator<Token> iterator;
    private LinkedList<Token> input;

    public Calculator(){
        this.lexer = new Lexer();
        this.printTokens = false;
    }
    public Calculator(boolean printTokens){
        this.lexer = new Lexer();
        this.printTokens = printTokens;
    }

    /*
    // Grammar
    S -> S + M | S - M | M
    M -> M * D | M \ D | D
    D -> num | ( S ) | D ^ D | D r D
    */

    public double s() throws Exception{
        double result=m();
        while(iterator.hasNext()){
            Token curr = iterator.next();
            if(curr.isSum()){
                result += m();
            } else if(curr.isSub()){
                result -= m();
            } else{
                break;
            }
        }
        return result;
    }

    public double m() throws Exception{
        double result = d();
        // Lookahead for the next Multiplier
        if (!iterator.hasNext())
            return result; //edge case (so it detects the last missing parentheses)

        while(iterator.hasNext()){
            // loakahead
            Token lookahead = iterator.next();
            if (lookahead.isMul()){
                result*=d();
            } else if (lookahead.isDiv()){
                result/=d();
            } else{
                break;
            }
        }
        iterator.previous(); //stop lookahead
        return result;
    }

    private double d() throws Exception{
        Token token = iterator.next();
        double ret = 0;
        if (token.isNum()){
            ret = token.getValue();
        } else if (token.isLPar()){
            double result = s();
            if(iterator.previous().isRPar()){
                iterator.next();
                ret = result;
            }else{
                throw new Exception("invalid expression: missing \')\'.");
            }
        }else{
            return iterator.next().getValue();
        }

        while(iterator.hasNext()){
            // loakahead
            Token lookahead = iterator.next();
            if (lookahead.isPow()){
                ret=Math.pow(ret,d());
            } 
            else if (lookahead.isRoot()){
                double rootDegree = d(), toRoot = ret; 
                ret=Math.pow(ret,1/rootDegree);
                // calc doesn't support complex numbers (negative roots of some degrees are not allowed)
                //if (Double.isNaN(ret)){
                //    throw new Exception("Math ERROR: ("+rootDegree+")_root of NUM("+toRoot+").");
                //}
            }else {
                iterator.previous();
                break;
            }
        }

        return ret;
    }


    private void set(String expr) throws Exception{
        this.input = lexer.tokenize(expr);
        if (this.printTokens){
            Iterator<Token>  itr = input.iterator();
            while (itr.hasNext()) 
                System.out.print(itr.next().toString());
            System.out.println("\n");
        }
        this.iterator = this.input.listIterator();
    }

    public double calculate(String expr) throws Exception{
        // Convert expr String into Token's List
        set(expr);   
        // Computer expression
        return s(); 
    }
    
}