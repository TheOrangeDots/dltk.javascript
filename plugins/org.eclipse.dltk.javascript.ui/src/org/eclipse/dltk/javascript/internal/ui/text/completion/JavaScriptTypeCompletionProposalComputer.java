/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text.completion;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.javascript.internal.ui.templates.JavaScriptTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ContentAssistInvocationContext;
import org.eclipse.dltk.ui.text.completion.IScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class JavaScriptTypeCompletionProposalComputer extends
		ScriptCompletionProposalComputer {

	@Override
	protected TemplateCompletionProcessor createTemplateProposalComputer(
			ScriptContentAssistInvocationContext context) {
		return new JavaScriptTemplateCompletionProcessor(context);
	}

	@Override
	public List<IContextInformation> computeContextInformation(
			ContentAssistInvocationContext context, IProgressMonitor monitor) {
		if (DLTKCore.DEBUG) {
			System.out.println("Offset: " + context.getInvocationOffset());

			System.out
					.println("TclTypeCompletionProposalComputer.computeContextInformation()");
		}
		// if (context instanceof ScriptContentAssistInvocationContext) {
		// ScriptContentAssistInvocationContext scriptContext=
		// (ScriptContentAssistInvocationContext) context;
		//			
		// int contextInformationPosition=
		// guessContextInformationPosition(scriptContext);
		// List result= addContextInformations(scriptContext,
		// contextInformationPosition, monitor);
		// return result;
		// }
		// return Collections.EMPTY_LIST;

		List<ICompletionProposal> types = computeCompletionProposals(context,
				monitor);
		if (DLTKCore.DEBUG) {
			System.out.println("!!! Proposals: " + types.size());
		}
		List<IContextInformation> list = new ArrayList<IContextInformation>();
		for (ICompletionProposal next : types) {
			if (!(next instanceof IScriptCompletionProposal))
				continue;
			IScriptCompletionProposal proposal = (IScriptCompletionProposal) next;
			IContextInformation contextInformation = proposal
					.getContextInformation();
			if (contextInformation == null) {
				continue;
			}
			if (DLTKCore.DEBUG) {
				System.out.println("Proposal: " + proposal + ", info: "
						+ contextInformation.getInformationDisplayString());
			}
			list.add(contextInformation);
		}
		return list;
	}

	@Override
	protected ScriptCompletionProposalCollector createCollector(
			ScriptContentAssistInvocationContext context) {
		return new JavaScriptCompletionProposalCollector(context
				.getSourceModule());
	}

}
