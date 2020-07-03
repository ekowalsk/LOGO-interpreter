package Parser;
import Dictionary.ErrorMessage;
import Dictionary.LexemeType;
import Lexer.*;
import Nodes.*;
import Nodes.ExpressionCommands.Backward;
import Nodes.ExpressionCommands.Forward;
import Nodes.ExpressionCommands.Left;
import Nodes.Number;
import Nodes.SimpleCommands.Clean;
import Nodes.ExpressionCommands.Down;
import Nodes.SimpleCommands.Hide;
import Nodes.SimpleCommands.Show;
import Nodes.SimpleCommands.Up;

public class Parser {
    private final Lexer lexer;
    public Parser (Lexer lexer) {
        this.lexer = lexer;
    }
    private Node statement() throws Exception {
        switch (currentTokenType()) {
            case HIDE:
                return hide();
            case SHOW:
                return show();
            case UP:
                return up();
            case DOWN:
                return down();
            case CLEAN:
                return clean();
            case FORWARD:
                return forward();
            case BACKWARD:
                return backward();
            case LEFT:
                return left();
            default:
                return null;
        }
    }
    private LexemeType currentTokenType() {
        return lexer.getCurrentToken().getType();
    }
    private Hide hide() {
        lexer.consume();
        return new Hide();
    }
    private Show show() {
        lexer.consume();
        return new Show();
    }
    private Up up() {
        lexer.consume();
        return new Up();
    }
    private Down down() {
        lexer.consume();
        return new Down();
    }
    private Clean clean() {
        lexer.consume();
        return new Clean();
    }
    private Forward forward() throws Exception {
        lexer.consume();
        return new Forward(expression());
    }
    private Backward backward() throws Exception {
        lexer.consume();
        return new Backward(expression());
    }
    private Left left() throws Exception {
        lexer.consume();
        return new Left(expression());
    }
    private Node factor() throws Exception {
        switch(currentTokenType()) {
            case SUB:
            case ADD:
                Token token = lexer.getCurrentToken();
                lexer.consume();
                UnaryOperation unaryOperation = new UnaryOperation(token.getValue(), factor());
                return unaryOperation;
            case NUMBER:
                Number number = new Number(lexer.getCurrentToken().getValue());
                lexer.consume();
                return number;
            case IDENT:
                Variable variable = new Variable(lexer.getCurrentToken().getValue());
                lexer.consume();
                return variable;
            case LBRACKET:
                lexer.consume();
                Node expression = expression();
                consumeToken(LexemeType.RBRACKET, ErrorMessage.RBRACKET_EXPECTED);
                return expression;
            default:
                throw new Exception(ErrorMessage.FACTOR_EXPECTED);
        }
    }
    private Node term() throws Exception {
        Node node = factor();
        while (currentTokenType() == LexemeType.MUL || currentTokenType() == LexemeType.DIV) {
            Token token = lexer.getCurrentToken();
            lexer.consume();
            node = new BinaryOperation(node, token.getValue(), factor());
        }
        return node;
    }
    private Node expression() throws Exception {
        Node node = simpleExpression();
        if (currentTokenType() == LexemeType.RELOP) {
            Token token = lexer.getCurrentToken();
            lexer.consume();
            node = new BinaryOperation(node, token.getValue(), simpleExpression());
        }
        return node;
    }
    private Node simpleExpression() throws Exception {
        Node node = term();
        while (currentTokenType() == LexemeType.ADD || currentTokenType() == LexemeType.SUB) {
            Token token = lexer.getCurrentToken();
            lexer.consume();
            node = new BinaryOperation(node, token.getValue(), term());
        }
        return node;
    }
    private void consumeToken(LexemeType type, String exceptionMessage) throws Exception {
        if (currentTokenType() == type)
            lexer.consume();
        else
            throw new Exception(exceptionMessage + lexer.getCurrentToken().getPosition());
    }
}
