/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockstar.statement;

import java.util.List;
import rockstar.runtime.ASTAware;
import rockstar.runtime.BlockContext;
import rockstar.runtime.RockstarContinueException;

/**
 *
 * @author Gabor
 */
public class ContinueStatement extends Statement {

    @Override
    boolean applyTo(Block block) {
        Block b = block;
        while (b != null) {
            if (b instanceof WhileStatement) {
                return true;
            }
            b = b.getParent();

        }
        return false;
    }

    @Override
    public void execute(BlockContext ctx) {
        throw new RockstarContinueException();
    }

    @Override
    public List<ASTAware> getASTChildren() {
        return null;
    }

}
