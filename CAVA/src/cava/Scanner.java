// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: /home/jim/NetBeansProjects/COMPILADORS/Scanner.flex

package cava;

import java.io.*;
import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1, 1
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\37\u0100\1\u0200\267\u0100\10\u0300\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\2\3\1\2\22\0\1\1\1\4"+
    "\1\5\5\0\1\6\1\7\1\0\1\10\1\11\1\12"+
    "\2\0\1\13\11\14\1\0\1\15\1\16\1\17\1\20"+
    "\2\0\32\21\1\22\1\23\1\24\1\0\1\21\1\0"+
    "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\2\21\1\36\1\21\1\37\1\40\2\21\1\41"+
    "\1\42\1\43\1\44\1\21\1\45\3\21\1\46\1\0"+
    "\1\47\7\0\1\3\u01a2\0\2\3\326\0\u0100\3";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1024];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\1\1\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\2\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\13\17\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\5\17\1\33\1\17"+
    "\1\34\4\17\1\35\1\36\1\37\1\40\1\41\4\17"+
    "\1\42\6\17\1\43\3\17\1\44\2\17\1\45\1\46"+
    "\2\17\1\47\1\17\1\50\1\51\1\52";

  private static int [] zzUnpackAction() {
    int [] result = new int[85];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\50\0\120\0\170\0\240\0\310\0\120\0\120"+
    "\0\120\0\120\0\120\0\120\0\120\0\360\0\120\0\u0118"+
    "\0\u0140\0\u0168\0\u0190\0\120\0\120\0\u01b8\0\u01e0\0\u0208"+
    "\0\u0230\0\u0258\0\u0280\0\u02a8\0\u02d0\0\u02f8\0\u0320\0\u0348"+
    "\0\120\0\120\0\u0370\0\120\0\u0398\0\120\0\120\0\120"+
    "\0\120\0\u03c0\0\u03e8\0\u0410\0\u0438\0\u0460\0\u0190\0\u0488"+
    "\0\u0190\0\u04b0\0\u04d8\0\u0500\0\u0528\0\120\0\120\0\120"+
    "\0\120\0\u0190\0\u0550\0\u0578\0\u05a0\0\u05c8\0\u0190\0\u05f0"+
    "\0\u0618\0\u0640\0\u0668\0\u0690\0\u06b8\0\u0190\0\u06e0\0\u0708"+
    "\0\u0730\0\u0190\0\u0758\0\u0780\0\u0190\0\u0190\0\u07a8\0\u07d0"+
    "\0\u0190\0\u07f8\0\u0190\0\u0190\0\u0190";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[85];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\0\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\3\1\25\1\26\1\27\1\30"+
    "\1\23\1\31\1\32\2\23\1\33\2\23\1\34\1\35"+
    "\1\36\1\37\1\23\1\40\1\41\1\42\2\43\1\5"+
    "\2\43\1\44\15\43\1\45\24\43\51\0\1\4\50\0"+
    "\1\5\64\0\1\46\43\0\2\16\52\0\1\47\47\0"+
    "\1\50\47\0\1\51\43\0\2\23\4\0\1\23\3\0"+
    "\21\23\15\0\2\23\4\0\1\23\3\0\12\23\1\52"+
    "\6\23\15\0\2\23\4\0\1\23\3\0\13\23\1\53"+
    "\5\23\15\0\2\23\4\0\1\23\3\0\13\23\1\54"+
    "\5\23\15\0\2\23\4\0\1\23\3\0\11\23\1\55"+
    "\7\23\15\0\2\23\4\0\1\23\3\0\1\56\20\23"+
    "\15\0\2\23\4\0\1\23\3\0\5\23\1\57\4\23"+
    "\1\60\6\23\15\0\2\23\4\0\1\23\3\0\14\23"+
    "\1\61\4\23\15\0\2\23\4\0\1\23\3\0\4\23"+
    "\1\62\14\23\15\0\2\23\4\0\1\23\3\0\16\23"+
    "\1\63\2\23\15\0\2\23\4\0\1\23\3\0\14\23"+
    "\1\64\4\23\15\0\2\23\4\0\1\23\3\0\7\23"+
    "\1\65\11\23\2\0\2\43\1\0\2\43\1\0\15\43"+
    "\1\0\24\43\5\0\1\66\31\0\1\67\1\0\1\70"+
    "\1\0\1\71\17\0\2\23\4\0\1\23\3\0\3\23"+
    "\1\72\15\23\15\0\2\23\4\0\1\23\3\0\13\23"+
    "\1\73\5\23\15\0\2\23\4\0\1\23\3\0\12\23"+
    "\1\74\6\23\15\0\2\23\4\0\1\23\3\0\15\23"+
    "\1\75\3\23\15\0\2\23\4\0\1\23\3\0\11\23"+
    "\1\76\7\23\15\0\2\23\4\0\1\23\3\0\16\23"+
    "\1\77\2\23\15\0\2\23\4\0\1\23\3\0\16\23"+
    "\1\100\2\23\15\0\2\23\4\0\1\23\3\0\14\23"+
    "\1\101\4\23\15\0\2\23\4\0\1\23\3\0\17\23"+
    "\1\102\1\23\15\0\2\23\4\0\1\23\3\0\10\23"+
    "\1\103\10\23\15\0\2\23\4\0\1\23\3\0\11\23"+
    "\1\104\7\23\15\0\2\23\4\0\1\23\3\0\15\23"+
    "\1\105\3\23\15\0\2\23\4\0\1\23\3\0\4\23"+
    "\1\106\14\23\15\0\2\23\4\0\1\23\3\0\15\23"+
    "\1\107\3\23\15\0\2\23\4\0\1\23\3\0\17\23"+
    "\1\110\1\23\15\0\2\23\4\0\1\23\3\0\10\23"+
    "\1\111\10\23\15\0\2\23\4\0\1\23\3\0\4\23"+
    "\1\112\14\23\15\0\2\23\4\0\1\23\3\0\11\23"+
    "\1\113\7\23\15\0\2\23\4\0\1\23\3\0\4\23"+
    "\1\114\14\23\15\0\2\23\4\0\1\23\3\0\16\23"+
    "\1\115\2\23\15\0\2\23\4\0\1\23\3\0\4\23"+
    "\1\116\14\23\15\0\2\23\4\0\1\23\3\0\14\23"+
    "\1\117\4\23\15\0\2\23\4\0\1\23\3\0\12\23"+
    "\1\120\6\23\15\0\2\23\4\0\1\23\3\0\4\23"+
    "\1\121\14\23\15\0\2\23\4\0\1\23\3\0\1\122"+
    "\20\23\15\0\2\23\4\0\1\23\3\0\12\23\1\123"+
    "\6\23\15\0\2\23\4\0\1\23\3\0\6\23\1\124"+
    "\12\23\15\0\2\23\4\0\1\23\3\0\12\23\1\125"+
    "\6\23\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2080];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\3\1\7\11\1\1\1\11\4\1\2\11"+
    "\13\1\2\11\1\1\1\11\1\1\4\11\14\1\4\11"+
    "\34\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[85];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
    StringBuffer string = new StringBuffer();

    public class entradaInv extends IOException{
       public entradaInv(String tk, int linia){
           super("ERROR: Error l??xic: "+ tk +" en linea: " + linia);
       }
    }

    /***
       Mecanismes de gesti?? de s??mbols basat en ComplexSymbol. Tot i que en
       aquest cas potser no ??s del tot necessari.
     ***/
    /**
     Construcci?? d'un symbol sense atribut associat.
     **/
    private ComplexSymbol symbol(int type) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type);
    }

    /**
     Construcci?? d'un symbol amb un atribut associat.
     **/
    private Symbol symbol(int type, Object value) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type, value);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          {   return symbol(ParserSym.EOF);
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { throw new entradaInv(this.yytext(),(this.yyline+1));
            }
            // fall through
          case 43: break;
          case 2:
            { /* no fer res */
            }
            // fall through
          case 44: break;
          case 3:
            { /*return symbol(ParserSym.EOF);       */
            }
            // fall through
          case 45: break;
          case 4:
            { string.setLength(0); yybegin(STRING);
            }
            // fall through
          case 46: break;
          case 5:
            { return symbol(ParserSym.LPAREN);
            }
            // fall through
          case 47: break;
          case 6:
            { return symbol(ParserSym.RPAREN);
            }
            // fall through
          case 48: break;
          case 7:
            { return symbol(ParserSym.SUMA);
            }
            // fall through
          case 49: break;
          case 8:
            { return symbol(ParserSym.COMA);
            }
            // fall through
          case 50: break;
          case 9:
            { return symbol(ParserSym.RESTA);
            }
            // fall through
          case 51: break;
          case 10:
            { return symbol(ParserSym.NUM, Integer.parseInt(this.yytext()));
            }
            // fall through
          case 52: break;
          case 11:
            { return symbol(ParserSym.PCOMA);
            }
            // fall through
          case 53: break;
          case 12:
            { return symbol(ParserSym.MENOR);
            }
            // fall through
          case 54: break;
          case 13:
            { return symbol(ParserSym.IGUAL);
            }
            // fall through
          case 55: break;
          case 14:
            { return symbol(ParserSym.MAJOR);
            }
            // fall through
          case 56: break;
          case 15:
            { return symbol(ParserSym.ID, this.yytext());
            }
            // fall through
          case 57: break;
          case 16:
            { return symbol(ParserSym.LBRACKET);
            }
            // fall through
          case 58: break;
          case 17:
            { return symbol(ParserSym.RBRACKET);
            }
            // fall through
          case 59: break;
          case 18:
            { return symbol(ParserSym.LCOR);
            }
            // fall through
          case 60: break;
          case 19:
            { return symbol(ParserSym.RCOR);
            }
            // fall through
          case 61: break;
          case 20:
            { string.append( yytext() );
            }
            // fall through
          case 62: break;
          case 21:
            { yybegin(YYINITIAL);
                                        return symbol(ParserSym.STRING_LITERAL,
                                        string.toString());
            }
            // fall through
          case 63: break;
          case 22:
            { string.append('\\');
            }
            // fall through
          case 64: break;
          case 23:
            { return symbol(ParserSym.NOTEQ);
            }
            // fall through
          case 65: break;
          case 24:
            { return symbol(ParserSym.MENORIG);
            }
            // fall through
          case 66: break;
          case 25:
            { return symbol(ParserSym.IGUALIGUAL);
            }
            // fall through
          case 67: break;
          case 26:
            { return symbol(ParserSym.MAJORIG);
            }
            // fall through
          case 68: break;
          case 27:
            { return symbol(ParserSym.IF);
            }
            // fall through
          case 69: break;
          case 28:
            { return symbol(ParserSym.OR);
            }
            // fall through
          case 70: break;
          case 29:
            { string.append('\"');
            }
            // fall through
          case 71: break;
          case 30:
            { string.append('\n');
            }
            // fall through
          case 72: break;
          case 31:
            { string.append('\r');
            }
            // fall through
          case 73: break;
          case 32:
            { string.append('\t');
            }
            // fall through
          case 74: break;
          case 33:
            { return symbol(ParserSym.AND);
            }
            // fall through
          case 75: break;
          case 34:
            { return symbol(ParserSym.INT);
            }
            // fall through
          case 76: break;
          case 35:
            { return symbol(ParserSym.ELSE);
            }
            // fall through
          case 77: break;
          case 36:
            { return symbol(ParserSym.TRUE);
            }
            // fall through
          case 78: break;
          case 37:
            { return symbol(ParserSym.CONST);
            }
            // fall through
          case 79: break;
          case 38:
            { return symbol(ParserSym.FALSE);
            }
            // fall through
          case 80: break;
          case 39:
            { return symbol(ParserSym.WHILE);
            }
            // fall through
          case 81: break;
          case 40:
            { return symbol(ParserSym.RETURN);
            }
            // fall through
          case 82: break;
          case 41:
            { return symbol(ParserSym.STRING);
            }
            // fall through
          case 83: break;
          case 42:
            { return symbol(ParserSym.BOOLEAN);
            }
            // fall through
          case 84: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
