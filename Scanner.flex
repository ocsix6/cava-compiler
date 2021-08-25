package cava;

import java.io.*;
import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

%%
%cup //Indicació de quin tipus d'analitzador sintàctic s'utilitzarà
%line
%public              // Per indicar que la classe és pública
%class Scanner       // El nom de la classe

%eofval{
  return symbol(ParserSym.EOF);
%eofval}

// Declaracions

NUM     = 0 | [1-9][0-9]*
ID      = [A-Za-z_][A-Za-z0-9_]*

WS        = [ \t]+
ENDLINE   = [\r\n]+



// El següent codi es copiarà també, dins de la classe. És a dir, si es posa res
// ha de ser en el format adient: mètodes, atributs, etc.

%{
    StringBuffer string = new StringBuffer();

    public class entradaInv extends IOException{
       public entradaInv(String tk, int linia){
           super("ERROR: Error lèxic: "+ tk +" en linea: " + linia);
       }
    }

    /***
       Mecanismes de gestió de símbols basat en ComplexSymbol. Tot i que en
       aquest cas potser no és del tot necessari.
     ***/
    /**
     Construcció d'un symbol sense atribut associat.
     **/
    private ComplexSymbol symbol(int type) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type);
    }

    /**
     Construcció d'un symbol amb un atribut associat.
     **/
    private Symbol symbol(int type, Object value) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type, value);
    }
%}

%state STRING

%%

// Regles/accions

<YYINITIAL> "int"     { return symbol(ParserSym.INT);                  }
<YYINITIAL> "boolean" { return symbol(ParserSym.BOOLEAN);              }
<YYINITIAL> "string"  { return symbol(ParserSym.STRING);               }
<YYINITIAL> "const"   { return symbol(ParserSym.CONST);                }
<YYINITIAL> "if"      { return symbol(ParserSym.IF);                   }
<YYINITIAL> "while"   { return symbol(ParserSym.WHILE);                }
<YYINITIAL> "else"    { return symbol(ParserSym.ELSE);                 }
<YYINITIAL> "return"  { return symbol(ParserSym.RETURN);               }
<YYINITIAL> "("       { return symbol(ParserSym.LPAREN);               }
<YYINITIAL> ")"       { return symbol(ParserSym.RPAREN);               }
<YYINITIAL> "{"       { return symbol(ParserSym.LCOR);                 }
<YYINITIAL> "}"       { return symbol(ParserSym.RCOR);                 }
<YYINITIAL> "["       { return symbol(ParserSym.LBRACKET);             }
<YYINITIAL> "]"       { return symbol(ParserSym.RBRACKET);             }
<YYINITIAL> ","       { return symbol(ParserSym.COMA);                 }
<YYINITIAL> ";"       { return symbol(ParserSym.PCOMA);                }
<YYINITIAL> "="       { return symbol(ParserSym.IGUAL);                }
<YYINITIAL> "or"      { return symbol(ParserSym.OR);                   }
<YYINITIAL> "and"     { return symbol(ParserSym.AND);                  }
<YYINITIAL> "<"       { return symbol(ParserSym.MENOR);                }
<YYINITIAL> ">"       { return symbol(ParserSym.MAJOR);                }
<YYINITIAL> "!="      { return symbol(ParserSym.NOTEQ);                }
<YYINITIAL> "=="      { return symbol(ParserSym.IGUALIGUAL);           }
<YYINITIAL> ">="      { return symbol(ParserSym.MAJORIG);              }
<YYINITIAL> "<="      { return symbol(ParserSym.MENORIG);              }
<YYINITIAL> "+"       { return symbol(ParserSym.SUMA);                 }
<YYINITIAL> "-"       { return symbol(ParserSym.RESTA);                }
<YYINITIAL> "true"    { return symbol(ParserSym.TRUE);                 }
<YYINITIAL> "false"   { return symbol(ParserSym.FALSE);                }
<YYINITIAL> {ID}      { return symbol(ParserSym.ID, this.yytext());    }
<YYINITIAL> {NUM}     { return symbol(ParserSym.NUM, Integer.parseInt(this.yytext()));}
<YYINITIAL> \"      { string.setLength(0); yybegin(STRING); }
<STRING> {
      \"                             { yybegin(YYINITIAL);
                                        return symbol(ParserSym.STRING_LITERAL,
                                        string.toString()); }
      [^\n\r\"\\]+                   { string.append( yytext() ); }
      \\t                            { string.append('\t'); }
      \\n                            { string.append('\n'); }

      \\r                            { string.append('\r'); }
      \\\"                           { string.append('\"'); }
      \\                             { string.append('\\'); }
}
{WS}      { /* no fer res */                              }
{ENDLINE} { /*return symbol(ParserSym.EOF);       */          }
.         {throw new entradaInv(this.yytext(),(this.yyline+1));                       }
