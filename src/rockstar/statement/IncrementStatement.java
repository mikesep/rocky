/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockstar.statement;

import rockstar.expression.ConstantValue;
import rockstar.expression.PlusExpression;
import rockstar.expression.VariableReference;
import rockstar.runtime.BlockContext;
import rockstar.runtime.NumericValue;
import rockstar.runtime.RockstarRuntimeException;

/**
 *
 * @author Gabor
 */
public class IncrementStatement extends Statement {

    private final VariableReference variable;
    private final int count;
    private PlusExpression plus;

    public IncrementStatement(VariableReference variable, int count) {
        this.variable = variable;
        this.count = count;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\n    " + variable + " ++".repeat(count);
    }

    private PlusExpression getPlus() {
        if (plus == null) {
            plus = new PlusExpression();
            plus.addParameter(variable);
            plus.addParameter(new ConstantValue(NumericValue.getValueFor(count)));
        }
        return plus;
    }

    @Override
    public void execute(BlockContext ctx) {
        super.execute(ctx);
        ConstantValue v = ctx.getVariable(variable.getName());
        if (v.isNumeric()) {
            // increment by count
            ConstantValue value = getPlus().evaluate(ctx);
            ctx.setVariable(variable.getName(), value);
        } else if (v.isBoolean()) {
            if (count % 2 == 1) {
                // negate boolean
                ctx.setVariable(variable.getName(), new ConstantValue(!v.getBoolValue()));
            }
        }
        throw new RockstarRuntimeException(v.getType() + " ++");
    }

}
