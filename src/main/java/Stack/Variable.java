package Stack;

public class Variable {
    public enum Type {STRING, NUMBER}
    private final String name;
    private final Type type;
    private final Object value;

    public Variable (String name, String type, Object value) {
        this.name = name;
        this.type = (type.equals("num"))? Type.NUMBER : Type.STRING;
        this.value = value;
    }
     public String getName() { return name; }
     public Type getType() { return type; }
     public Object getValue() { return value; }
}
