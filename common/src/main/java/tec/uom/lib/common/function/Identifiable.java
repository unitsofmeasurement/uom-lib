/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright 2003-2014, Werner Keil and individual
 *  contributors by the @author tag.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package tec.uom.lib.common.function;

/**
 * Interface for objects with an Id.
 * the Id is a <code>T</code>.
 * 
 * <p>There is no requirement that a distinct result be returned each
 * time the supplier is invoked, unless implementing classes enforce it.
 * 
 * <p>This is a <a href="http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html#package.description">functional interface</a>
 * whose functional method is {@link #getId()}.
 * 
 * @author Werner KEIL
 * @version 1.1 $Date: 2014/09/07 $
 */
public interface Identifiable<T> {
    /**
     * @return an Id
     */
    T getId();
}