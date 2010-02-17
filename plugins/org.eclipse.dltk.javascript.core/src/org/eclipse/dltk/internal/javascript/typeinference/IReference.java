/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.javascript.typeinference;

import java.util.Collection;
import java.util.Set;

import org.eclipse.dltk.core.IModelElement;

public interface IReference {

	/**
	 * this method returns array of elements that can be identified by child
	 * with specified key if key is not null; or array of possible child
	 * elements of this element (methods and properties if key is null)
	 * 
	 * @param resolveLocals
	 *            TODO
	 * @param key
	 * 
	 * @return
	 */
	public Set<IReference> getChilds(boolean resolveLocals);

	public IReference getChild(String key, boolean resolveLocals);

	public void setChild(String key, IReference ref);

	/**
	 * returns name of this reference
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Returns possible types of this reference or <code>null</code> if unknown.
	 * 
	 * @return
	 */
	public Set<String> getTypes();

	public boolean isChildishReference();

	public void recordDelete(String fieldId);

	public IReference getPrototype(boolean resolveLocals);

	public void setPrototype(IReference ref);

	public void addModelElements(Collection<IModelElement> toAdd);

	public void setLocationInformation(IReferenceLocation location);

	public boolean isFunctionRef();

	public boolean isLocal();

	public void setLocal(boolean local);
}
