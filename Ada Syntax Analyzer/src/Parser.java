public class Parser {
    
    private LexicalAnalyzer la;
    private Token nextToken;
    private Error e = new Error();

	public Parser(LexicalAnalyzer L) {
        la = L;
    }
    
    
	public void Start(){
        nextToken = la.getToken();
        if (nextToken.getSym() == Symbol.PROCSYM)
        {
            nextToken = la.getToken();
            if (nextToken.getSym() == Symbol.IDENT)
            {
                nextToken = la.getToken();
                if (nextToken.getSym() == Symbol.ISSYM)
                {
                    nextToken = la.getToken();
                    DecPart();
                    if (nextToken.getSym() == Symbol.BEGINSYM)
                    {
                        nextToken = la.getToken();
                        SeqOfStmt();
                        if (nextToken.getSym() == Symbol.ENDSYM)
                        {
                            nextToken = la.getToken();
                            if (nextToken.getSym() == Symbol.SEMICOLON)
                            {
                                nextToken = la.getToken();
                                if (nextToken.getSym() == Symbol.EOI)
                                    System.out.println("Program syntactically correct.");
                                else
                                    e.error(1, la.getCurrCharPosNum());
                            }
                            else
                                e.error(2, la.getCurrCharPosNum());
                        }
                        else
                            e.error(3, la.getCurrCharPosNum());
                    }
                    else
                        e.error(4, la.getCurrCharPosNum());
                }
                else
                    e.error(5, la.getCurrCharPosNum());
            }
            else
                e.error(6, la.getCurrCharPosNum());
        }
        else
            e.error(7, la.getCurrCharPosNum());

    }

    private void SeqOfStmt (){
        Statement();
        while(nextToken.getSym() == Symbol.NULLSYM || nextToken.getSym() == Symbol.IDENT || nextToken.getSym() == Symbol.IFSYM || nextToken.getSym() == Symbol.WHILESYM || nextToken.getSym() == Symbol.NEWLINE){
            nextToken = la.getToken();
            Statement();
        }
    }

    private void DecPart(){
        while(nextToken.getSym() == Symbol.IDENT){
            nextToken = la.getToken();
            ObjectDec();
        }
    }

    private void ObjectDec(){

        nextToken = la.getToken();
        while (nextToken.getSym() == Symbol.COMMA){
            nextToken = la.getToken();
            if (nextToken.getSym() == Symbol.IDENT)
                nextToken = la.getToken();
            else
                e.error(6, la.getCurrCharPosNum());
        }
        if (nextToken.getSym() == Symbol.COLON){
            nextToken = la.getToken();
            if (nextToken.getSym() == Symbol.BOOLSYM || nextToken.getSym() == Symbol.INTSYM){
                nextToken = la.getToken();
                if (nextToken.getSym() == Symbol.SEMICOLON)
                    nextToken = la.getToken();
                else
                    e.error(2, la.getCurrCharPosNum());
            }
            else
                e.error(8, la.getCurrCharPosNum());
        }
        else
            e.error(9, la.getCurrCharPosNum());
    }

    private void Statement(){
        if (nextToken.getSym() == Symbol.NULLSYM) {
            nextToken = la.getToken();
            if(nextToken.getSym() == Symbol.SEMICOLON)
                nextToken = la.getToken();
            else 
                e.error(2, la.getCurrCharPosNum());
        }
        if(nextToken.getSym() == Symbol.IDENT){
            nextToken = la.getToken();
            if(nextToken.getSym() == Symbol.BECOMES){
                nextToken = la.getToken();
                Expression();
                if(nextToken.getSym() == Symbol.COLON)
                    nextToken = la.getToken();
                else
                    e.error(2, la.getCurrCharPosNum());
            }
            else
                e.error(11, la.getCurrCharPosNum());
        }
        if(nextToken.getSym() == Symbol.IFSYM){
            nextToken = la.getToken();
            Condition();
            if(nextToken.getSym() == Symbol.THENSYM){
                nextToken = la.getToken();
                SeqOfStmt();
                if(nextToken.getSym() == Symbol.ELSESYM){
                    nextToken = la.getToken();
                        SeqOfStmt();
                }
                if(nextToken.getSym() == Symbol.ENDSYM){
                    nextToken = la.getToken();
                    if(nextToken.getSym() == Symbol.IFSYM){
                        nextToken = la.getToken();
                        if(nextToken.getSym() == Symbol.SEMICOLON)
                            nextToken = la.getToken();
                            else
                                e.error(2, la.getCurrCharPosNum());
                    }    
                    else    
                        e.error(12, la.getCurrCharPosNum());
                }
                else
                    e.error(3, la.getCurrCharPosNum());
            }
            else
                e.error(13, la.getCurrCharPosNum());
        }
        if(nextToken.getSym() == Symbol.WHILESYM){
            nextToken = la.getToken();
            Condition();
            if(nextToken.getSym() == Symbol.LOOPSYM){
                nextToken = la.getToken();
                SeqOfStmt();
                if(nextToken.getSym() == Symbol.ENDSYM){
                    nextToken = la.getToken();
                    if(nextToken.getSym() == Symbol.LOOPSYM){
                        nextToken = la.getToken();
                        if(nextToken.getSym() == Symbol.SEMICOLON)
                            nextToken = la.getToken();
                        else
                            e.error(2, la.getCurrCharPosNum());
                    }
                    else    
                        e.error(15, la.getCurrCharPosNum());
                }
                else
                    e.error(3, la.getCurrCharPosNum());
            }
            else
                e.error(15, la.getCurrCharPosNum());
        }
        if(nextToken.getSym() == Symbol.NEWLINE){
            nextToken = la.getToken();
            if(nextToken.getSym() == Symbol.SEMICOLON)
                nextToken = la.getToken();
            else
                e.error(2, la.getCurrCharPosNum());
        }
        if(nextToken.getSym() == Symbol.LPAREN){
            nextToken = la.getToken();
            if(nextToken.getSym() == Symbol.GETSYM || nextToken.getSym() == Symbol.PUTSYM ){
                nextToken = la.getToken();
                if(nextToken.getSym() == Symbol.RPAREN){
                    nextToken = la.getToken();
                    if(nextToken.getSym() == Symbol.LPAREN){
                        nextToken = la.getToken();
                        if(nextToken.getSym() == Symbol.IDENT){
                            nextToken = la.getToken();
                            if(nextToken.getSym() == Symbol.RPAREN){
                                nextToken = la.getToken();
                                if(nextToken.getSym() == Symbol.SEMICOLON)
                                    nextToken = la.getToken();
                                else
                                    e.error(2, la.getCurrCharPosNum());
                            }
                            else
                                e.error(17, la.getCurrCharPosNum());
                        }
                        else
                            e.error(6, la.getCurrCharPosNum());
                    }
                    else 
                        e.error(16, la.getCurrCharPosNum());
                }
                else 
                    e.error(17, la.getCurrCharPosNum());
            }
            else 
                e.error(18, la.getCurrCharPosNum());
        }
    }

    private void Condition(){
        Expression();
    }

    private void Expression(){
        SimpExpr();
        if(nextToken.getSym() == Symbol.EQL || nextToken.getSym() == Symbol.NEQ || nextToken.getSym() == Symbol.LSS || nextToken.getSym() == Symbol.LEQ || nextToken.getSym() == Symbol.GTR || nextToken.getSym() == Symbol.GEQ){
            nextToken = la.getToken();
            SimpExpr();
        }
    }

    private void SimpExpr(){
        Term();
        while(nextToken.getSym() == Symbol.PLUS || nextToken.getSym() == Symbol.MINUS){
            nextToken = la.getToken();
            Term();
            
        }
    }

    private void Term(){
        Primary();
        while(nextToken.getSym() == Symbol.TIMES || nextToken.getSym() == Symbol.SLASH || nextToken.getSym() == Symbol.REMSYM){
            nextToken = la.getToken();
            Primary();     
            
        }
    }   

    private void Primary(){
        if(nextToken.getSym() == Symbol.IDENT || nextToken.getSym() == Symbol.NUMLIT || nextToken.getSym() == Symbol.TRUESYM || nextToken.getSym() == Symbol.FALSESYM){
            nextToken = la.getToken(); 
        }
        else 
            e.error(14, la.getCurrCharPosNum());              
    }

}