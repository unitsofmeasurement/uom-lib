package tec.uom.lib.common.function;

/**
 * Represents an operation on a single {@link Quantity} that produces a
 * result of type {@link Quantity}.
 * <p>
 * Examples might be an operator that rounds the amount to the nearest 1000, or
 * one that performs other quantity operations.
 * <p>
 * 
 * <pre>
 * // Example
 * result = thisOperator.apply(quantity);
 * </pre>
 *  
 * <h4>Implementation specification</h4>
 * The implementation must take the input object and apply it. The
 * implementation defines the logic of the operator and is responsible for
 * documenting that logic. It may use any method on {@code Quantity} to
 * determine the result.
 * <p>
 * The input object must not be altered. Instead, an altered copy of the
 * original must be returned. This provides equivalent, safe behavior for
 * immutable and mutable quantities.
 * <p>
 * This method may be called from multiple threads in parallel. It must be
 * thread-safe when invoked.
 * 
 * <p>
 * This interface is modeled after {@code java.util.function.UnaryOperator} in Java SE 8, 
 * but intended to be Java ME compatible as well.
 * 
 * @author Werner Keil
 * 
 * @version 0.1
 */
public interface QuantityOperator {

    /**
     * Applies the operator on the given quantity.
     * @param quantity the quantity to be operated on.
     * @return the applied quantity.
     */
    Quantity apply(Quantity quantity);
}
