package lexer;

import java.util.LinkedList;

public class Lexer{

    private Automata automata;

    public Lexer() {
        this.automata = new Automata();
    }

    /**
     * Reads String and converts it to LinkedList<Token>
     * @param str
     * @return
     */
    public LinkedList<Token> tokenize(String str) throws Exception{
        return automata.consumeAll(str);
    }

    /**
     * Lexer's automata back-end 
     */
    private class Automata{
    
        String input;
        int ptr; // Current index
        boolean wasNum;
        String currVal;
        

        public LinkedList<Token> consumeAll(String str) throws Exception{
            this.reset(str);
            
            Token token;
            LinkedList<Token> tokens = new LinkedList<Token>();
            while((token=consumeNext())!=null){
                tokens.addLast(token);
            }
            return tokens;
        }

        private Token consumeNext() throws Exception{
            resetCurrVal();
            int inputLen = input.length();

            // State 0
            while(ptr < inputLen){
                char ch = input.charAt(ptr);
                ptr++;

                // Transition to:
                // State 0
                if (Character.isWhitespace(ch)){
                    // Ignore whitespaces 
                    continue;
                }

                // State 1
                if (Character.isDigit(ch)){
                    if (wasNum || currVal.length()>1){
                        throw new Exception("invalid expression");
                    }
                    currVal += Character.toString(ch);
                    wasNum = true;
                    return state1();
                } 

                // State 2
                if (ch == '.'){
                    // with 1 look ahead (to avoid implementing another state)
                    if (wasNum || currVal.length()>1 || ptr >= inputLen || !Character.isDigit(input.charAt(ptr))){
                        throw new Exception("invalid expression");
                    }
                    currVal += "0.";
                    wasNum = true;
                    return state2();
                }

                // State 3
                switch (ch) {
                    // detect signed numbers and invalid expressions
                    case '+':
                        if (!wasNum){
                            currVal += ch;
                        } else{
                            wasNum = false;
                            return new Token(Type.PLUS);
                        }
                        break;
                    case '-':
                        if (!wasNum){
                            currVal += ch;
                        } else{
                            wasNum = false;
                            return new Token(Type.MINUS);
                        } 
                        break;
                    case '(':
                        wasNum = false;
                        return new Token(Type.LPAR);
                    case ')':
                        wasNum = true;
                        return new Token(Type.RPAR);
                    case '*':
                        if (wasNum){
                            wasNum = false;
                            return new Token(Type.MUL);
                        }
                    case '/':
                        if (wasNum){
                            wasNum = false;
                            return new Token(Type.DIV);
                        }
                    case '^':
                        if (wasNum){
                            wasNum = false;
                            return new Token(Type.POW);
                        }
                    case 'r':
                        if (wasNum){
                            wasNum = false;
                            return new Token(Type.ROOT);
                        }
                    default:
                        throw new Exception("invalid expression");
                }
            }

            return null;
        }

        private Token state1() throws Exception{
            // Terminal state
            int inputLen = input.length();
            char ch;
            while(ptr < inputLen && Character.isDigit(ch=input.charAt(ptr))){
                currVal+=ch;
                ptr++;
            }

            if (ptr < inputLen && (ch=input.charAt(ptr)) == '.'){
                // Transition to State2
                currVal+=ch;
                ptr++;
                return state2();
            }

            try {
                return new Token(Double.parseDouble(currVal));
            } catch (Exception e) {
                throw new Exception("parsing "+currVal+" to double: State1");
            }
        }

        private Token state2() throws Exception{
            // Terminal state
            int inputLen = input.length();
            char ch;
            while(ptr < inputLen && Character.isDigit(ch=input.charAt(ptr))){
                currVal+=ch;
                ptr++;
            }

            try {
                return new Token(Double.parseDouble(currVal));
            } catch (Exception e) {
                throw new Exception("parsing "+currVal+" to double: State2");
            }
        }

        private void reset(String newInput){
            this.ptr = 0;
            this.input = newInput;
            this.wasNum = false;
            resetCurrVal();
        }

        private void resetCurrVal(){
            this.currVal = "";
        }


    }

}