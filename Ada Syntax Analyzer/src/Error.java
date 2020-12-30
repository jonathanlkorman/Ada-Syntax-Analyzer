public class Error{

    public void error(int messageNumber, int spaces){
        
        System.out.format("%" + spaces + "s", "" + "^\n");

        switch (messageNumber)
        {
            case 1: System.out.println("ERROR: Did not reach end of file");
                break;
            case 2: System.out.println("ERROR:Missing Semicolon");
                break;
            case 3: System.out.println("ERROR:Missing End Symbol");
                break;
            case 4: System.out.println("ERROR:Missing Begin Symbol");
                break;
            case 5: System.out.println("ERROR:Missing IS Symbol");
                break;
            case 6: System.out.println("ERROR:Missing Ident");
                break;
            case 7: System.out.println("ERROR:Missing Procedure Symbol");
                break;
            case 8: System.out.println("ERROR:Missing Boolean or Integer Symbol");
                break;
            case 9: System.out.println("ERROR:Missing Colon");
                break;
            case 10: System.out.println("ERROR:Missing Expression");
                break;
            case 11: System.out.println("ERROR:Missing Becomes Symbol");
                break;
            case 12: System.out.println("ERROR:Missing If Symbol");
                break;
            case 13: System.out.println("ERROR:Missing Then Symbol");
                break;
            case 14: System.out.println("ERROR:Missing Ident or Numlit or true or false");
                break;
            case 15: System.out.println("Missing Loop Symbol");
                break;
            case 16: System.out.println("Missing LPAREN Symbol");
                break;
            case 17: System.out.println("Missing RPAREN Symbol");
                break;
            case 18: System.out.println("Missing Get or Put Symbol");
                break;
        }
    
        System.exit(0);
    }
}