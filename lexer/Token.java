package lexer;

enum Type{
    NUM,
    PLUS,
    MINUS,
    MUL,
    DIV,
    LPAR,
    RPAR,
    POW,
    ROOT,
}

public class Token {
    private Type type;
    private double value;

    public Token(Type type){
        this.type = type;
    }

    public Token(double value){
        this.type = Type.NUM;
        this.value = value;
    }

    public String toString(){
        switch (this.type) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case MUL:
                return "*";
            case DIV:
                return "/";
            case LPAR:
                return "(";
            case RPAR:
                return ")";
            case POW:
                return "^";
            case ROOT:
                return "_ROOT_";
            case NUM:
                return "NUM("+this.value+")";
            default:
                return "INVALID";
        }
    }

    public double getValue() throws Exception{
        if (this.type!=Type.NUM){
            throw new Exception("not a NUM");
        }
        return this.value;
    }

    public Type getType(){
        return this.type;
    }

    public boolean isSum(){
        return this.type == Type.PLUS;
    }
    public boolean isSub(){
        return this.type == Type.MINUS;
    }
    public boolean isMul(){
        return this.type == Type.MUL;
    }
    public boolean isDiv(){
        return this.type == Type.DIV;
    }
    public boolean isNum(){
        return this.type == Type.NUM;
    }
    public boolean isLPar(){
        return this.type == Type.LPAR;
    }
    public boolean isRPar(){
        return this.type == Type.RPAR;
    }
    public boolean isPow(){
        return this.type == Type.POW;
    }
    public boolean isRoot(){
        return this.type == Type.ROOT;
    }
}

