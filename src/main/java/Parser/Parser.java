package Parser;
import Dictionary.ErrorMessage;
import Dictionary.LexemeType;
import Lexer.*;
import Nodes.*;
import Nodes.ChildrenNodes.Compound;
import Nodes.ChildrenNodes.Program;
import Nodes.ColorCommands.SetPaintColor;
import Nodes.ColorCommands.SetPenColor;
import Nodes.ConditionalCommands.Repeat;
import Nodes.ConditionalCommands.While;
import Nodes.ExpressionCommands.*;
import Nodes.Number;
import Nodes.SimpleCommands.*;

import java.util.LinkedList;

public class Parser {
    private final Lexer lexer;
    public Parser (Lexer lexer) {
        this.lexer = lexer;
    }
    public Node program() throws Exception {
        Program program = new Program();
        while (currentTokenType() != LexemeType.ETX)
            program.add(statement());
        return program;
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
            case RIGHT:
                return right();
            case SETPOS:
                return setPosition();
            case REPEAT:
                return repeat();
            case WHILE:
                return whiles();
            case SETPAINTCOLOR:
                return setPaintColor();
            case SETPENCOLOR:
                return setPenColor();
            case MAKE:
                return assignment();
            case IFELSE:
                return ifelse();
            case BEGIN:
                return procedure();
            case IDENT:
                return procedureCall();
            default:
                throw new Exception(ErrorMessage.INSTRUCTION_EXCECTED + lexer.getCurrentToken().getPosition());
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
    private Right right() throws Exception {
        lexer.consume();
        return new Right(expression());
    }
    private SetPosition setPosition() throws Exception {
        lexer.consume();
        return new SetPosition(expression(), expression());
    }
    private Repeat repeat() throws Exception {
        lexer.consume();
        return new Repeat(expression(), squarelBlock());
    }
    private While whiles() throws Exception {
        lexer.consume();
        return new While(expression(), squarelBlock());
    }
    private SetPaintColor setPaintColor() {
        lexer.consume();
        return new SetPaintColor(color());
    }
    private SetPenColor setPenColor () {
        lexer.consume();
        return new SetPenColor(color());
    }
    private Assignment assignment() throws Exception {
        lexer.consume();
        Token variableType = lexer.getCurrentToken();
        consumeToken(LexemeType.TYPE, ErrorMessage.TYPE_EXPECTED);
        Token variableIdentifier = lexer.getCurrentToken();
        consumeToken(LexemeType.IDENT, ErrorMessage.IDENTIFIER_EXPECTED);
        consumeToken(LexemeType.ASSIGNOP, ErrorMessage.ASSIGNOP_EXPECTED);
        return new Assignment(new Variable(variableIdentifier.getValue()), new VariableType(variableType.getValue()), expression());
    }
    private IfElse ifelse() throws Exception {
        lexer.consume();
        Node condition = expression();
        Node trueBlock = squarelBlock();
        Node falseBlock = null;
        if (currentTokenType() == LexemeType.SQUARELBRACKET)
            falseBlock = squarelBlock();
        return new IfElse(condition, trueBlock, falseBlock);
    }
    private Node procedure() throws Exception {
        lexer.consume();
        Token identifier = lexer.getCurrentToken();
        LinkedList<Parameter> parameters = new LinkedList<>();
        consumeToken(LexemeType.IDENT, ErrorMessage.IDENTIFIER_EXPECTED);
        consumeToken(LexemeType.LBRACKET, ErrorMessage.LBRACKET_EXPECTED);
        if(currentTokenType() == LexemeType.IDENT)
            readParameters(parameters);
        consumeToken(LexemeType.RBRACKET, ErrorMessage.RBRACKET_EXPECTED);
        return new ProcedureDeclaration(identifier.getValue(), procedureBlock(), parameters);
    }
    private ProcedureCall procedureCall() throws Exception {
        String name = lexer.getCurrentToken().getValue();
        lexer.consume();
        LinkedList<Node> arguments = new LinkedList<>();
        readArguments(arguments);
        return new ProcedureCall(name, arguments);
    }
    private void readArguments(LinkedList<Node> arguments) throws Exception {
        consumeToken(LexemeType.LBRACKET, ErrorMessage.LBRACKET_EXPECTED);
        if (currentTokenType() == LexemeType.RBRACKET) {
            lexer.consume();
            return;
        }
        while (currentTokenType() != LexemeType.RBRACKET)
            arguments.add(expression());
        consumeToken(LexemeType.RBRACKET, ErrorMessage.RBRACKET_EXPECTED);
    }
    private void readParameters(LinkedList<Parameter> parameters) throws Exception {
        Token identifier = lexer.getCurrentToken();
        consumeToken(LexemeType.IDENT, ErrorMessage.IDENTIFIER_EXPECTED);
        Token type = lexer.getCurrentToken();
        consumeToken(LexemeType.TYPE, ErrorMessage.TYPE_EXPECTED);
        parameters.add(new Parameter(new Variable(identifier.getValue()), new VariableType(type.getValue())));
        if (currentTokenType() == LexemeType.SEPARATOR) {
            lexer.consume();
            readParameters(parameters);
        }
    }
    private LinkedList<Node> procedureBlock() throws Exception {
        LinkedList<Node> block = new LinkedList<>();
        while (currentTokenType() != LexemeType.END)
            block.add(statement());
        consumeToken(LexemeType.END, ErrorMessage.END_EXPECTED);
        return block;
    }
    private Color color() {
        Token color = lexer.getCurrentToken();
        lexer.consume();
        return new Color(color.getValue());
    }
    private Compound squarelBlock() throws Exception {
        consumeToken(LexemeType.SQUARELBRACKET, ErrorMessage.SQUAREL_EXPECTED);
        Compound compound = block();
        consumeToken(LexemeType.SQUARERBRACKET, ErrorMessage.SQUARER_EXPECTED);
        return compound;
    }
    private Compound block() throws Exception {
        Compound compound = new Compound();
        while(currentTokenType() != LexemeType.SQUARERBRACKET)
            compound.add(statement());
        return compound;
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
