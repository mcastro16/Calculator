import java.util.*;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;

  private StringTokenizer tokenizer;
  private static final String DELIMITERS = ".,+-*^/() ";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
    Operator.operators.put("+", new AdditionOperator());
    Operator.operators.put("-", new SubtractionOperator());
    Operator.operators.put("*", new MultiplicationOperator());
    Operator.operators.put("^", new PowerOperator());
    Operator.operators.put("/", new DivisionOperator());
    Operator.operators.put("(", new OpenParentOperator());
    Operator.operators.put(")", new CloseParentOperator());
    Operator.operators.put(".", new InitOperator()); //priority is -1
    Operator.operators.put(",", new CloseExpressOperator());
    /*
    method that takes an argument, said argument is a "."(i call CloseExpressOperator with priority = 1) that i will put at the end
    of the expression
    */
  }

  public int eval( String expression ) {
    String token;
    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    expression += ",";
    /*
    places "." at the top of the stack and skips over it because it has less priority than
    the specified precedence of the operators +,-,*,^,/
    */
    operatorStack.push(Operator.operators.get(".")); //using . as init operator for operator stack
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

    while ( this.tokenizer.hasMoreTokens() ) {
      // filter out spaces
      if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if ( Operand.check( token )) {
          operandStack.push( new Operand( token ));
        } else {
          if ( ! Operator.check( token )) {
            System.out.println( "*****invalid token******" );
            System.exit( 1 );
          }

          // TODO Operator is abstract - this line will need to be fixed:
          // ( The Operator class should contain an instance of a HashMap,
          // and values will be instances of the Operators.  See Operator class
          // skeleton for an example. )

          Operator newOperator = Operator.operators.get(token);

            //runs while loop until it hits the init operator "," placed at the end of expression
          while ( operatorStack.peek().priority() >= newOperator.priority() && !newOperator.equals(Operator.operators.get(("(")))) {
  				    // note that when we eval the expression 1 - 2 we will
  				    // push the 1 then the 2 and then do the subtraction operation
  				    // This means that the first number to be popped is the
  				    // second operand, not the first operand - see the following code
  				  if(token.equals(")"))
            {
  						while(!operatorStack.peek().equals(Operator.operators.get("(")) && !operatorStack.peek().equals(Operator.operators.get(".")))
              {
                Operator oldOpr = operatorStack.pop();
    						Operand op2 = operandStack.pop();
    						Operand op1 = operandStack.pop();
    						operandStack.push(oldOpr.execute(op1,op2));
  						}
  						if(!operatorStack.peek().equals(Operator.operators.get(".")))
              {
  							operatorStack.pop();
  						}
  						newOperator = Operator.operators.get(token);
  					}
            else
            {
  					  Operator oldOpr = operatorStack.pop();
  						Operand op2 = operandStack.pop();
  						Operand op1 = operandStack.pop();
  						operandStack.push(oldOpr.execute(op1,op2));
  					}
  				}
          if(!newOperator.equals(Operator.operators.get(")")))
          {
            operatorStack.push(newOperator);
          }
        }
      }
      // Control gets here when we've picked up all of the tokens; you must add
      // code to complete the evaluation - consider how the code given here
      // will evaluate the expression 1+2*3
      // When we have no more tokens to scan, the operand stack will contain 1 2
      // and the operator stack will have + * with 2 and * on the top;
      // In order to complete the evaluation we must empty the stacks (except
      // the init operator on the operator stack); that is, we should keep
      // evaluating the operator stack until empty
      // Suggestion: create a method that takes an operator as argument and
      // then executes the while loop; also, move the stacks out of the main
      // method
    }
    operatorStack.clear(); //clears init and closeExpress operator
    int result = operandStack.pop().getValue();
    return result;
  }
}
