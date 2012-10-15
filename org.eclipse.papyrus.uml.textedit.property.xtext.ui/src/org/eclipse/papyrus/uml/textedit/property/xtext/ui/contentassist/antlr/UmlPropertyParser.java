/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.textedit.property.xtext.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.eclipse.papyrus.uml.textedit.property.xtext.services.UmlPropertyGrammarAccess;

public class UmlPropertyParser extends AbstractContentAssistParser {
	
	@Inject
	private UmlPropertyGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.eclipse.papyrus.uml.textedit.property.xtext.ui.contentassist.antlr.internal.InternalUmlPropertyParser createParser() {
		org.eclipse.papyrus.uml.textedit.property.xtext.ui.contentassist.antlr.internal.InternalUmlPropertyParser result = new org.eclipse.papyrus.uml.textedit.property.xtext.ui.contentassist.antlr.internal.InternalUmlPropertyParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getPropertyRuleAccess().getAlternatives_4(), "rule__PropertyRule__Alternatives_4");
					put(grammarAccess.getUnlimitedLiteralAccess().getAlternatives(), "rule__UnlimitedLiteral__Alternatives");
					put(grammarAccess.getModifierSpecificationAccess().getAlternatives(), "rule__ModifierSpecification__Alternatives");
					put(grammarAccess.getVisibilityKindAccess().getAlternatives(), "rule__VisibilityKind__Alternatives");
					put(grammarAccess.getModifierKindAccess().getAlternatives(), "rule__ModifierKind__Alternatives");
					put(grammarAccess.getPropertyRuleAccess().getGroup(), "rule__PropertyRule__Group__0");
					put(grammarAccess.getTypeRuleAccess().getGroup(), "rule__TypeRule__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup(), "rule__QualifiedName__Group__0");
					put(grammarAccess.getMultiplicityRuleAccess().getGroup(), "rule__MultiplicityRule__Group__0");
					put(grammarAccess.getMultiplicityRuleAccess().getGroup_2(), "rule__MultiplicityRule__Group_2__0");
					put(grammarAccess.getModifiersRuleAccess().getGroup(), "rule__ModifiersRule__Group__0");
					put(grammarAccess.getModifiersRuleAccess().getGroup_2(), "rule__ModifiersRule__Group_2__0");
					put(grammarAccess.getRedefinesRuleAccess().getGroup(), "rule__RedefinesRule__Group__0");
					put(grammarAccess.getSubsetsRuleAccess().getGroup(), "rule__SubsetsRule__Group__0");
					put(grammarAccess.getDefaultValueRuleAccess().getGroup(), "rule__DefaultValueRule__Group__0");
					put(grammarAccess.getPropertyRuleAccess().getVisibilityAssignment_0(), "rule__PropertyRule__VisibilityAssignment_0");
					put(grammarAccess.getPropertyRuleAccess().getIsDerivedAssignment_1(), "rule__PropertyRule__IsDerivedAssignment_1");
					put(grammarAccess.getPropertyRuleAccess().getNameAssignment_2(), "rule__PropertyRule__NameAssignment_2");
					put(grammarAccess.getPropertyRuleAccess().getTypeAssignment_4_0(), "rule__PropertyRule__TypeAssignment_4_0");
					put(grammarAccess.getPropertyRuleAccess().getMultiplicityAssignment_5(), "rule__PropertyRule__MultiplicityAssignment_5");
					put(grammarAccess.getPropertyRuleAccess().getModifiersAssignment_6(), "rule__PropertyRule__ModifiersAssignment_6");
					put(grammarAccess.getPropertyRuleAccess().getDefaultAssignment_7(), "rule__PropertyRule__DefaultAssignment_7");
					put(grammarAccess.getTypeRuleAccess().getPathAssignment_0(), "rule__TypeRule__PathAssignment_0");
					put(grammarAccess.getTypeRuleAccess().getTypeAssignment_1(), "rule__TypeRule__TypeAssignment_1");
					put(grammarAccess.getQualifiedNameAccess().getPathAssignment_0(), "rule__QualifiedName__PathAssignment_0");
					put(grammarAccess.getQualifiedNameAccess().getRemainingAssignment_2(), "rule__QualifiedName__RemainingAssignment_2");
					put(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_1(), "rule__MultiplicityRule__BoundsAssignment_1");
					put(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_2_1(), "rule__MultiplicityRule__BoundsAssignment_2_1");
					put(grammarAccess.getBoundSpecificationAccess().getValueAssignment(), "rule__BoundSpecification__ValueAssignment");
					put(grammarAccess.getModifiersRuleAccess().getValuesAssignment_1(), "rule__ModifiersRule__ValuesAssignment_1");
					put(grammarAccess.getModifiersRuleAccess().getValuesAssignment_2_1(), "rule__ModifiersRule__ValuesAssignment_2_1");
					put(grammarAccess.getModifierSpecificationAccess().getValueAssignment_0(), "rule__ModifierSpecification__ValueAssignment_0");
					put(grammarAccess.getModifierSpecificationAccess().getRedefinesAssignment_1(), "rule__ModifierSpecification__RedefinesAssignment_1");
					put(grammarAccess.getModifierSpecificationAccess().getSubsetsAssignment_2(), "rule__ModifierSpecification__SubsetsAssignment_2");
					put(grammarAccess.getRedefinesRuleAccess().getPropertyAssignment_1(), "rule__RedefinesRule__PropertyAssignment_1");
					put(grammarAccess.getSubsetsRuleAccess().getPropertyAssignment_1(), "rule__SubsetsRule__PropertyAssignment_1");
					put(grammarAccess.getDefaultValueRuleAccess().getDefaultAssignment_1(), "rule__DefaultValueRule__DefaultAssignment_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.eclipse.papyrus.uml.textedit.property.xtext.ui.contentassist.antlr.internal.InternalUmlPropertyParser typedParser = (org.eclipse.papyrus.uml.textedit.property.xtext.ui.contentassist.antlr.internal.InternalUmlPropertyParser) parser;
			typedParser.entryRulePropertyRule();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public UmlPropertyGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(UmlPropertyGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
