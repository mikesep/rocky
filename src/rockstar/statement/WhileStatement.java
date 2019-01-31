/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockstar.statement;

import rockstar.expression.Expression;
import rockstar.runtime.BlockContext;

/**
 *
 * @author Gabor
 */
public class WhileStatement extends Block {

    private final Expression condition;
    private boolean negateCondition = false;

    public WhileStatement(Expression condition) {
        this.condition = condition;
    }

    public WhileStatement(Expression condition, boolean negateCondition) {
        this.condition = condition;
        this.negateCondition = negateCondition;
    }
    
    public Expression getCondition() {
        return condition;
    }
    
     @Override
    public String toString() {
        return super.toString() + 
                "\n    COND: " + (negateCondition ? "not " : "") + condition ; 
    }

    @Override
    public void execute(BlockContext ctx) {
        super.execute(ctx); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
