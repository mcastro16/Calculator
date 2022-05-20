import java.util.HashMap;
import java.util.*;

public abstract class Operator
{
  // The Operator class should contain an instance of a HashMap
  // This map will use keys as the tokens we're interested in,
  // and values will be instances of the Operators.

  // Example:
  // Where does this declaration go? What should its access level be?
  // Class or instance variable? Is this the right declaration?
  static HashMap<String, Operator> operators = new HashMap<String, Operator>();

  public abstract int priority();
  public abstract Operand execute( Operand op1, Operand op2 );

  public static boolean check( String token ) {
    return operators.containsKey( token );
  }
}

class InitOperator extends Operator
{
  public int priority()
  {
    return -1;
  }

  public Operand execute( Operand op1, Operand op2 )
  {
    return op1;
  }
}

class CloseExpressOperator extends Operator
{
  public int priority()
  {
    return 1;
  }

  public Operand execute( Operand op1, Operand op2 )
  {
    return op1;
  }
}

class OpenParentOperator extends Operator
{
  public int priority()
  {
    return 0;
  }

  public Operand execute( Operand op1, Operand op2 )
  {
    return op1;
  }
}

class CloseParentOperator extends Operator
{
  public int priority()
  {
    return 1;
  }

  public Operand execute( Operand op1, Operand op2 )
  {
    return op1;
  }
}

class AdditionOperator extends Operator
{
  public int priority()
  {
    return 2;
  }

  public Operand execute( Operand op1, Operand op2 )
  {
    Operand result = new Operand(op1.getValue() + op2.getValue());
    return result;
  }
}

class SubtractionOperator extends Operator
{
  public int priority()
  {
    return 2;
  }

  public Operand execute( Operand op1, Operand op2 )
  {
    Operand result = new Operand(op1.getValue() - op2.getValue());
    return result;
  }
}

class MultiplicationOperator extends Operator
{
  public int priority()
  {
    return 3;
  }

  public Operand execute( Operand op1, Operand op2 )
  {
    Operand result = new Operand(op1.getValue() * op2.getValue());
    return result;
  }
}

class PowerOperator extends Operator
{
  public int priority()
  {
    return 4;
  }

  public Operand execute( Operand op1, Operand op2 )
  {
    int poweredNum = (int)Math.round(Math.pow(op1.getValue(), op2.getValue()));
    Operand result = new Operand(op1.getValue() + op2.getValue());
    return result;
  }
}

class DivisionOperator extends Operator
{
  public int priority()
  {
    return 3;
  }

  public Operand execute( Operand op1, Operand op2 )
  {
    Operand result = new Operand(op1.getValue() / op2.getValue());
    return result;
  }
}
