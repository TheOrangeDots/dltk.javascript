/*******************************************************************************
 * Copyright (c) 2010 xored software, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.javascript.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.javascript.internal.parser.ITypedDeclaration;
import org.eclipse.dltk.javascript.internal.parser.JSLiterals;

public class Argument extends JSNode implements ISourceable, ITypedDeclaration {

	private Identifier identifier;
	private int colonPosition = -1;
	private Type type;
	private int commaPosition = -1;

	public Argument(ASTNode parent) {
		super(parent);
	}

	public String getArgumentName() {
		return identifier != null ? identifier.getName() : null;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public int getColonPosition() {
		return colonPosition;
	}

	public void setColonPosition(int colonPosition) {
		this.colonPosition = colonPosition;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Returns the comma position after this variable or -1 if this is the last
	 * variable in statement.
	 * 
	 * @return
	 */
	public int getCommaPosition() {
		return commaPosition;
	}

	/**
	 * Sets the comma position after this variable.
	 * 
	 * @param commaPosition
	 */
	public void setCommaPosition(int commaPosition) {
		this.commaPosition = commaPosition;
	}

	@Override
	public String toSourceString(String indentationString) {
		final StringBuilder sb = new StringBuilder();
		sb.append(identifier.toSourceString(indentationString));
		if (type != null) {
			sb.append(JSLiterals.COLON);
			sb.append(type.toSourceString(indentationString));
		}
		return sb.toString();
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (identifier != null) {
			identifier.traverse(visitor);
		}
		if (type != null) {
			type.traverse(visitor);
		}
	}

}
