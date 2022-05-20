public class Operand {
  int value;
  public Operand( String token ) {
    this.value = Integer.parseInt( token );
  }

  public Operand( int value ) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static boolean check( String token ) {
    try {
      int i = Integer.parseInt(token);
    } catch(Exception exp) {
      return false;
    }
    return true;
  }
}
