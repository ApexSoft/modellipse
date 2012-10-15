/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.gmf.glue.partialEditing;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.gmf.glue.edit.part.PopupXtextEditorHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class SyntheticResourceProvider implements ISyntheticResourceProvider {

	/**
	 *
	 */
	public static final String SYNTHETIC_SCHEME = "synthetic";
	
	//@Inject 
	//private IResourceSetProvider resourceSetProvider;
	@Inject
	private Provider<XtextResourceSet> resourceSetProvider;
	
	@Inject
	private IGrammarAccess grammarAccess;
	
	
	
	public XtextResource createResource() {
		ResourceSet resourceSet = resourceSetProvider.get();
//		EObject context = PopupXtextEditorHelper.context ;
//		ResourceSet resourceSet = context.eResource().getResourceSet() ;
		Resource grammarResource = resourceSet.createResource(
				URI.createURI(SYNTHETIC_SCHEME + ":/" + grammarAccess.getGrammar().getName() + ".xtext"));
		grammarResource.getContents().add(EcoreUtil.copy(grammarAccess.getGrammar()));
		XtextResource result = (XtextResource) resourceSet.createResource(
				URI.createURI(SYNTHETIC_SCHEME + ":/" + grammarAccess.getGrammar().getName() + "." + PopupXtextEditorHelper.fileExtension));
		resourceSet.getResources().add(result);
		return result ;		

	}
	
}
