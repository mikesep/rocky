/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockstar.runtime;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import rockstar.statement.FunctionBlock;

/**
 *
 * @author Gabor
 */
public class BlockContext {

    private final BlockContext parent;
    private final BlockContext root;
    private final Map<String, Value> vars = new HashMap<>();
    private final Map<String, FunctionBlock> funcs = new HashMap<>();

    private final InputStream input;
    private final PrintStream output;
    private final PrintStream error;
    private final Map<String, Object> env;

    public BlockContext(InputStream input, PrintStream output, PrintStream error, Map<String, Object> env) {
        this.parent = null;
        this.root = this;
        this.input = input;
        this.output = output;
        this.error = error;
        this.env = env;
    }

    /**
     * Context initialization
     *
     * @param parent
     */
    public BlockContext(BlockContext parent) {
        this.parent = parent;
        this.root = parent.root;
        this.input = parent.input;
        this.output = parent.output;
        this.error = parent.error;
        this.env = parent.env;
    }

    public BlockContext getParent() {
        return parent;
    }

    public InputStream getInput() {
        return input;
    }

    public PrintStream getOutput() {
        return output;
    }

    public PrintStream getError() {
        return error;
    }

    public Map<String, Object> getEnv() {
        return env;
    }
    
    

    public void setVariable(String name, Value value) {
        BlockContext ctx = findVariableContext(name);
        if (ctx == null) {
            ctx = this;
        }
        ctx.vars.put(name, value);
    }

    public void setLocalVariable(String name, Value value) {
        vars.put(name, value);
    }
    
    public Value getVariableValue(String name) {
        BlockContext ctx = findVariableContext(name);
        if (ctx != null) {
            return ctx.vars.get(name);
        }
        return Value.MYSTERIOUS;
    }
    
    private BlockContext findVariableContext(String name) {
        if (vars.containsKey(name)) {
            return this;
        }
        return (parent == null) ? null : parent.findVariableContext(name);
    }

    public FunctionBlock retrieveFunction(String name) {
        return root.funcs.get(name);
    }

    public void defineFunction(String name, FunctionBlock function) {
        root.funcs.put(name, function);
    }

}
