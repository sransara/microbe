import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.*;


public class CustomErrorStrategy extends DefaultErrorStrategy {
  @Override
  public void reportError(Parser recognizer, RecognitionException e)
  {
    throw new ParseCancellationException(e);
  }

  /** Make sure we don't attempt to recover inline; if the parser
   *  successfully recovers, it won't throw an exception.
   */
  @Override
  public Token recoverInline(Parser recognizer) throws RecognitionException
  {
    InputMismatchException e = new InputMismatchException(recognizer);
    throw new ParseCancellationException(e);
  }

  /** Make sure we don't attempt to recover from problems in subrules. */
  @Override
  public void sync(Parser recognizer) { }
}
