/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockstar.runtime;

/**
 *
 * @author Gabor
 */
public class RockstarReturnException extends RuntimeException {
    
    private final Value returnValue;

    public RockstarReturnException(Value returnValue) {
        this.returnValue = returnValue;
    }

    public Value getReturnValue() {
        return returnValue;
    }
    
}
