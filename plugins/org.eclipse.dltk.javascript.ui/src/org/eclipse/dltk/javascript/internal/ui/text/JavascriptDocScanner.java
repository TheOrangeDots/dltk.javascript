/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text;

import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.TodoTaskPreferencesOnPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;

public class JavascriptDocScanner extends JavaScriptScriptCommentScanner {

	public JavascriptDocScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store, JavascriptColorConstants.JS_DOC,
				JavascriptColorConstants.JS_TODO_TAG,
				new TodoTaskPreferencesOnPreferenceStore(store));
	}

	@Override
	protected int skipCommentChars() {
		int count = 0;
		int c = read();
		while (Character.isWhitespace(c)) {
			++count;
			c = read();
		}
		while (c == '/') {
			++count;
			c = read();
		}
		while (c == '*') {
			++count;
			c = read();
		}
		unread();
		return count;
	}

}
