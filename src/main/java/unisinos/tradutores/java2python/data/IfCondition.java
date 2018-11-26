package unisinos.tradutores.java2python.data;

import lombok.Data;

@Data
public class IfCondition extends Expression {

    public final String condition;
    //public final Body body
    public final IfCondition elseCondition;

}
