/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
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

package org.eclipse.papyrus.uml.textedit.stereotypeproperty.xtext.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.papyrus.uml.alf.services.AlfGrammarAccess;

@Singleton
public class AppliedStereotypePropertyGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class AppliedStereotypePropertyRuleElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "AppliedStereotypePropertyRule");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cPropertyAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final CrossReference cPropertyPropertyCrossReference_0_0 = (CrossReference)cPropertyAssignment_0.eContents().get(0);
		private final RuleCall cPropertyPropertyIDTerminalRuleCall_0_0_1 = (RuleCall)cPropertyPropertyCrossReference_0_0.eContents().get(1);
		private final Assignment cValueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cValueExpressionValueRuleParserRuleCall_1_0 = (RuleCall)cValueAssignment_1.eContents().get(0);
		
		//AppliedStereotypePropertyRule:
		//	property=[uml::Property] value=ExpressionValueRule;
		public ParserRule getRule() { return rule; }

		//property=[uml::Property] value=ExpressionValueRule
		public Group getGroup() { return cGroup; }

		//property=[uml::Property]
		public Assignment getPropertyAssignment_0() { return cPropertyAssignment_0; }

		//[uml::Property]
		public CrossReference getPropertyPropertyCrossReference_0_0() { return cPropertyPropertyCrossReference_0_0; }

		//ID
		public RuleCall getPropertyPropertyIDTerminalRuleCall_0_0_1() { return cPropertyPropertyIDTerminalRuleCall_0_0_1; }

		//value=ExpressionValueRule
		public Assignment getValueAssignment_1() { return cValueAssignment_1; }

		//ExpressionValueRule
		public RuleCall getValueExpressionValueRuleParserRuleCall_1_0() { return cValueExpressionValueRuleParserRuleCall_1_0; }
	}

	public class ExpressionValueRuleElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "ExpressionValueRule");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cEqualsSignKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cExpressionAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cExpressionSequenceElementParserRuleCall_1_0 = (RuleCall)cExpressionAssignment_1.eContents().get(0);
		
		//ExpressionValueRule:
		//	"=" expression=SequenceElement;
		public ParserRule getRule() { return rule; }

		//"=" expression=SequenceElement
		public Group getGroup() { return cGroup; }

		//"="
		public Keyword getEqualsSignKeyword_0() { return cEqualsSignKeyword_0; }

		//expression=SequenceElement
		public Assignment getExpressionAssignment_1() { return cExpressionAssignment_1; }

		//SequenceElement
		public RuleCall getExpressionSequenceElementParserRuleCall_1_0() { return cExpressionSequenceElementParserRuleCall_1_0; }
	}
	
	
	private AppliedStereotypePropertyRuleElements pAppliedStereotypePropertyRule;
	private ExpressionValueRuleElements pExpressionValueRule;
	
	private final GrammarProvider grammarProvider;

	private AlfGrammarAccess gaAlf;

	@Inject
	public AppliedStereotypePropertyGrammarAccess(GrammarProvider grammarProvider,
		AlfGrammarAccess gaAlf) {
		this.grammarProvider = grammarProvider;
		this.gaAlf = gaAlf;
	}
	
	public Grammar getGrammar() {	
		return grammarProvider.getGrammar(this);
	}
	

	public AlfGrammarAccess getAlfGrammarAccess() {
		return gaAlf;
	}

	
	//AppliedStereotypePropertyRule:
	//	property=[uml::Property] value=ExpressionValueRule;
	public AppliedStereotypePropertyRuleElements getAppliedStereotypePropertyRuleAccess() {
		return (pAppliedStereotypePropertyRule != null) ? pAppliedStereotypePropertyRule : (pAppliedStereotypePropertyRule = new AppliedStereotypePropertyRuleElements());
	}
	
	public ParserRule getAppliedStereotypePropertyRuleRule() {
		return getAppliedStereotypePropertyRuleAccess().getRule();
	}

	//ExpressionValueRule:
	//	"=" expression=SequenceElement;
	public ExpressionValueRuleElements getExpressionValueRuleAccess() {
		return (pExpressionValueRule != null) ? pExpressionValueRule : (pExpressionValueRule = new ExpressionValueRuleElements());
	}
	
	public ParserRule getExpressionValueRuleRule() {
		return getExpressionValueRuleAccess().getRule();
	}

	/// *
	//  Test rule
	// * / Test:
	//	("testExpression" expression+=Expression)* ("testAssignmentExpression" assignExpression+=AssignmentCompletion)*
	//	("testStatement" statements+=Statement)* ("testBlock" block=Block);
	public AlfGrammarAccess.TestElements getTestAccess() {
		return gaAlf.getTestAccess();
	}
	
	public ParserRule getTestRule() {
		return getTestAccess().getRule();
	}

	////('testStatementSequence' statement += StatementSequence)* ;
	/// *********************************
	// * PrimitiveLiterals
	// ********************************** / LITERAL:
	//	BOOLEAN_LITERAL | NUMBER_LITERAL | STRING_LITERAL;
	public AlfGrammarAccess.LITERALElements getLITERALAccess() {
		return gaAlf.getLITERALAccess();
	}
	
	public ParserRule getLITERALRule() {
		return getLITERALAccess().getRule();
	}

	//// (suffix = SuffixExpression) ? ;
	//BOOLEAN_LITERAL:
	//	value=BooleanValue;
	public AlfGrammarAccess.BOOLEAN_LITERALElements getBOOLEAN_LITERALAccess() {
		return gaAlf.getBOOLEAN_LITERALAccess();
	}
	
	public ParserRule getBOOLEAN_LITERALRule() {
		return getBOOLEAN_LITERALAccess().getRule();
	}

	//enum BooleanValue:
	//	TRUE="true" | FALSE="false";
	public AlfGrammarAccess.BooleanValueElements getBooleanValueAccess() {
		return gaAlf.getBooleanValueAccess();
	}
	
	public EnumRule getBooleanValueRule() {
		return getBooleanValueAccess().getRule();
	}

	//NUMBER_LITERAL:
	//	INTEGER_LITERAL | UNLIMITED_LITERAL;
	public AlfGrammarAccess.NUMBER_LITERALElements getNUMBER_LITERALAccess() {
		return gaAlf.getNUMBER_LITERALAccess();
	}
	
	public ParserRule getNUMBER_LITERALRule() {
		return getNUMBER_LITERALAccess().getRule();
	}

	//// (suffix = SuffixExpression) ? ;
	//INTEGER_LITERAL:
	//	value=IntegerValue;
	public AlfGrammarAccess.INTEGER_LITERALElements getINTEGER_LITERALAccess() {
		return gaAlf.getINTEGER_LITERALAccess();
	}
	
	public ParserRule getINTEGER_LITERALRule() {
		return getINTEGER_LITERALAccess().getRule();
	}

	//// (suffix = SuffixExpression) ? ;
	//UNLIMITED_LITERAL:
	//	value="*";
	public AlfGrammarAccess.UNLIMITED_LITERALElements getUNLIMITED_LITERALAccess() {
		return gaAlf.getUNLIMITED_LITERALAccess();
	}
	
	public ParserRule getUNLIMITED_LITERALRule() {
		return getUNLIMITED_LITERALAccess().getRule();
	}

	//terminal IntegerValue:
	//	("0" | "1".."9" ("_"? "0".."9")*) //DECIMAL 
	//	// BINARY
	//	// HEX
	//	// OCT
	//	| ("0b" | "0B") "0".."1" ("_"? "0".."1")* | ("0x" | "0X") ("0".."9" | "a".."f" | "A".."F") ("_"? ("0".."9" | "a".."f" |
	//	"A".."F"))* | "0" "_"? "0".."7" ("_"? "0".."7")*;
	public TerminalRule getIntegerValueRule() {
		return gaAlf.getIntegerValueRule();
	} 

	//// (suffix = SuffixExpression) ?;
	//STRING_LITERAL:
	//	value=STRING;
	public AlfGrammarAccess.STRING_LITERALElements getSTRING_LITERALAccess() {
		return gaAlf.getSTRING_LITERALAccess();
	}
	
	public ParserRule getSTRING_LITERALRule() {
		return getSTRING_LITERALAccess().getRule();
	}

	//NameExpression:
	//	(prefixOp=("++" | "--") path=QualifiedNamePath? id=ID | path=QualifiedNamePath? id=ID (invocationCompletion=Tuple |
	//	sequenceConstructionCompletion=SequenceConstructionOrAccessCompletion | postfixOp=("++" | "--"))?)
	//	suffix=SuffixExpression?;
	public AlfGrammarAccess.NameExpressionElements getNameExpressionAccess() {
		return gaAlf.getNameExpressionAccess();
	}
	
	public ParserRule getNameExpressionRule() {
		return getNameExpressionAccess().getRule();
	}

	//QualifiedNamePath:
	//	(namespace+=UnqualifiedName "::")+;
	public AlfGrammarAccess.QualifiedNamePathElements getQualifiedNamePathAccess() {
		return gaAlf.getQualifiedNamePathAccess();
	}
	
	public ParserRule getQualifiedNamePathRule() {
		return getQualifiedNamePathAccess().getRule();
	}

	//UnqualifiedName:
	//	name=ID templateBinding=TemplateBinding?;
	public AlfGrammarAccess.UnqualifiedNameElements getUnqualifiedNameAccess() {
		return gaAlf.getUnqualifiedNameAccess();
	}
	
	public ParserRule getUnqualifiedNameRule() {
		return getUnqualifiedNameAccess().getRule();
	}

	//TemplateBinding:
	//	"<" bindings+=NamedTemplateBinding ("," bindings+=NamedTemplateBinding)* ">";
	public AlfGrammarAccess.TemplateBindingElements getTemplateBindingAccess() {
		return gaAlf.getTemplateBindingAccess();
	}
	
	public ParserRule getTemplateBindingRule() {
		return getTemplateBindingAccess().getRule();
	}

	//NamedTemplateBinding:
	//	formal=ID "=>" actual=QualifiedNameWithBinding;
	public AlfGrammarAccess.NamedTemplateBindingElements getNamedTemplateBindingAccess() {
		return gaAlf.getNamedTemplateBindingAccess();
	}
	
	public ParserRule getNamedTemplateBindingRule() {
		return getNamedTemplateBindingAccess().getRule();
	}

	//QualifiedNameWithBinding:
	//	id=ID binding=TemplateBinding? ("::" remaining=QualifiedNameWithBinding)?;
	public AlfGrammarAccess.QualifiedNameWithBindingElements getQualifiedNameWithBindingAccess() {
		return gaAlf.getQualifiedNameWithBindingAccess();
	}
	
	public ParserRule getQualifiedNameWithBindingRule() {
		return getQualifiedNameWithBindingAccess().getRule();
	}

	//Tuple:
	//	{Tuple} "(" (tupleElements+=TupleElement ("," tupleElements+=TupleElement)*)? ")";
	public AlfGrammarAccess.TupleElements getTupleAccess() {
		return gaAlf.getTupleAccess();
	}
	
	public ParserRule getTupleRule() {
		return getTupleAccess().getRule();
	}

	//TupleElement:
	//	argument=Expression;
	public AlfGrammarAccess.TupleElementElements getTupleElementAccess() {
		return gaAlf.getTupleElementAccess();
	}
	
	public ParserRule getTupleElementRule() {
		return getTupleElementAccess().getRule();
	}

	/// **************
	// * Expressions
	// ************** / Expression:
	//	ConditionalTestExpression;
	public AlfGrammarAccess.ExpressionElements getExpressionAccess() {
		return gaAlf.getExpressionAccess();
	}
	
	public ParserRule getExpressionRule() {
		return getExpressionAccess().getRule();
	}

	//ConditionalTestExpression:
	//	exp=ConditionalOrExpression ("?" whenTrue=ConditionalTestExpression ":" whenFalse=ConditionalTestExpression)?;
	public AlfGrammarAccess.ConditionalTestExpressionElements getConditionalTestExpressionAccess() {
		return gaAlf.getConditionalTestExpressionAccess();
	}
	
	public ParserRule getConditionalTestExpressionRule() {
		return getConditionalTestExpressionAccess().getRule();
	}

	//ConditionalOrExpression:
	//	exp+=ConditionalAndExpression ("||" exp+=ConditionalAndExpression)*;
	public AlfGrammarAccess.ConditionalOrExpressionElements getConditionalOrExpressionAccess() {
		return gaAlf.getConditionalOrExpressionAccess();
	}
	
	public ParserRule getConditionalOrExpressionRule() {
		return getConditionalOrExpressionAccess().getRule();
	}

	//ConditionalAndExpression:
	//	exp+=InclusiveOrExpression ("&&" exp+=InclusiveOrExpression)*;
	public AlfGrammarAccess.ConditionalAndExpressionElements getConditionalAndExpressionAccess() {
		return gaAlf.getConditionalAndExpressionAccess();
	}
	
	public ParserRule getConditionalAndExpressionRule() {
		return getConditionalAndExpressionAccess().getRule();
	}

	//InclusiveOrExpression:
	//	exp+=ExclusiveOrExpression ("|" exp+=ExclusiveOrExpression)*;
	public AlfGrammarAccess.InclusiveOrExpressionElements getInclusiveOrExpressionAccess() {
		return gaAlf.getInclusiveOrExpressionAccess();
	}
	
	public ParserRule getInclusiveOrExpressionRule() {
		return getInclusiveOrExpressionAccess().getRule();
	}

	//ExclusiveOrExpression:
	//	exp+=AndExpression ("^" exp+=AndExpression)*;
	public AlfGrammarAccess.ExclusiveOrExpressionElements getExclusiveOrExpressionAccess() {
		return gaAlf.getExclusiveOrExpressionAccess();
	}
	
	public ParserRule getExclusiveOrExpressionRule() {
		return getExclusiveOrExpressionAccess().getRule();
	}

	//AndExpression:
	//	exp+=EqualityExpression ("&" exp+=EqualityExpression)*;
	public AlfGrammarAccess.AndExpressionElements getAndExpressionAccess() {
		return gaAlf.getAndExpressionAccess();
	}
	
	public ParserRule getAndExpressionRule() {
		return getAndExpressionAccess().getRule();
	}

	//EqualityExpression:
	//	exp+=ClassificationExpression (op+=("==" | "!=") exp+=ClassificationExpression)*;
	public AlfGrammarAccess.EqualityExpressionElements getEqualityExpressionAccess() {
		return gaAlf.getEqualityExpressionAccess();
	}
	
	public ParserRule getEqualityExpressionRule() {
		return getEqualityExpressionAccess().getRule();
	}

	////enum EqualityOperator :
	////	EQUALS = '==' |
	////	NOT_EQUALS = '!='
	////;
	//ClassificationExpression:
	//	exp=RelationalExpression (op=("instanceof" | "hastype") typeName=NameExpression)?;
	public AlfGrammarAccess.ClassificationExpressionElements getClassificationExpressionAccess() {
		return gaAlf.getClassificationExpressionAccess();
	}
	
	public ParserRule getClassificationExpressionRule() {
		return getClassificationExpressionAccess().getRule();
	}

	////enum ClassificationOperator :
	////	INSTANCEOF = 'instanceof' |
	////	HASTYPE = 'hastype'
	////;
	//RelationalExpression:
	//	left=ShiftExpression (op=("<" | ">" | "<=" | ">=") right=ShiftExpression)?;
	public AlfGrammarAccess.RelationalExpressionElements getRelationalExpressionAccess() {
		return gaAlf.getRelationalExpressionAccess();
	}
	
	public ParserRule getRelationalExpressionRule() {
		return getRelationalExpressionAccess().getRule();
	}

	////RelationalOperator :
	////	LOWER = '<' |
	////	UPPER = '>' |
	////	LOWER_EQUALS = '<=' |
	////	UPPER_EQUALS = '>='
	////;
	//ShiftExpression:
	//	exp+=AdditiveExpression (op=("<<" | ">>" | ">>>") exp+=AdditiveExpression)?;
	public AlfGrammarAccess.ShiftExpressionElements getShiftExpressionAccess() {
		return gaAlf.getShiftExpressionAccess();
	}
	
	public ParserRule getShiftExpressionRule() {
		return getShiftExpressionAccess().getRule();
	}

	////enum ShiftOperator :
	////	LSHIFT = '<<' |
	////	RSHIFT = '>>' |
	////	URSHIFT = '>>>'
	////;
	//AdditiveExpression:
	//	exp+=MultiplicativeExpression (op+=("+" | "-") exp+=MultiplicativeExpression)*;
	public AlfGrammarAccess.AdditiveExpressionElements getAdditiveExpressionAccess() {
		return gaAlf.getAdditiveExpressionAccess();
	}
	
	public ParserRule getAdditiveExpressionRule() {
		return getAdditiveExpressionAccess().getRule();
	}

	////enum AdditiveOp :
	////	PLUS = '+' |
	////	MINUS = '-'
	////;
	//MultiplicativeExpression:
	//	exp+=UnaryExpression (op+=("*" | "/" | "%") exp+=UnaryExpression)*;
	public AlfGrammarAccess.MultiplicativeExpressionElements getMultiplicativeExpressionAccess() {
		return gaAlf.getMultiplicativeExpressionAccess();
	}
	
	public ParserRule getMultiplicativeExpressionRule() {
		return getMultiplicativeExpressionAccess().getRule();
	}

	////enum MultiplicativeOp :
	////	MULT = '*' |
	////	DIV = '/' |
	////	MOD = '%'
	////;
	//UnaryExpression:
	//	op=("!" | "-" | "+" | "$" | "~")? exp=PrimaryExpression;
	public AlfGrammarAccess.UnaryExpressionElements getUnaryExpressionAccess() {
		return gaAlf.getUnaryExpressionAccess();
	}
	
	public ParserRule getUnaryExpressionRule() {
		return getUnaryExpressionAccess().getRule();
	}

	////PrimaryExpression :
	////	prefix = ValueSpecification (suffix = SuffixExpression)? 
	////;
	//PrimaryExpression:
	//	prefix=ValueSpecification;
	public AlfGrammarAccess.PrimaryExpressionElements getPrimaryExpressionAccess() {
		return gaAlf.getPrimaryExpressionAccess();
	}
	
	public ParserRule getPrimaryExpressionRule() {
		return getPrimaryExpressionAccess().getRule();
	}

	//SuffixExpression:
	//	OperationCallExpression | PropertyCallExpression | LinkOperationExpression | SequenceOperationExpression |
	//	SequenceReductionExpression | SequenceExpansionExpression | ClassExtentExpression;
	public AlfGrammarAccess.SuffixExpressionElements getSuffixExpressionAccess() {
		return gaAlf.getSuffixExpressionAccess();
	}
	
	public ParserRule getSuffixExpressionRule() {
		return getSuffixExpressionAccess().getRule();
	}

	//OperationCallExpression:
	//	"." operationName=ID tuple=Tuple suffix=SuffixExpression?;
	public AlfGrammarAccess.OperationCallExpressionElements getOperationCallExpressionAccess() {
		return gaAlf.getOperationCallExpressionAccess();
	}
	
	public ParserRule getOperationCallExpressionRule() {
		return getOperationCallExpressionAccess().getRule();
	}

	//// OperationCallExpressionWithoutDot :
	//// 	operationName = ID tuple = Tuple (suffix = SuffixExpression)?
	//// ;
	//PropertyCallExpression:
	//	"." propertyName=ID ("[" index=Expression "]")? suffix=SuffixExpression?;
	public AlfGrammarAccess.PropertyCallExpressionElements getPropertyCallExpressionAccess() {
		return gaAlf.getPropertyCallExpressionAccess();
	}
	
	public ParserRule getPropertyCallExpressionRule() {
		return getPropertyCallExpressionAccess().getRule();
	}

	//LinkOperationExpression:
	//	"." kind=LinkOperationKind tuple=LinkOperationTuple;
	public AlfGrammarAccess.LinkOperationExpressionElements getLinkOperationExpressionAccess() {
		return gaAlf.getLinkOperationExpressionAccess();
	}
	
	public ParserRule getLinkOperationExpressionRule() {
		return getLinkOperationExpressionAccess().getRule();
	}

	//LinkOperationTuple:
	//	"(" linkOperationTupleElement+=LinkOperationTupleElement ("," linkOperationTupleElement+=LinkOperationTupleElement)*
	//	")";
	public AlfGrammarAccess.LinkOperationTupleElements getLinkOperationTupleAccess() {
		return gaAlf.getLinkOperationTupleAccess();
	}
	
	public ParserRule getLinkOperationTupleRule() {
		return getLinkOperationTupleAccess().getRule();
	}

	////LinkOperationTupleElement :
	////	objectOrRole = ID (('['roleIndex = Expression ']')? '=>' object = ID)?
	////;
	//LinkOperationTupleElement:
	//	role=ID ("[" roleIndex=Expression "]")? "=>" object=Expression;
	public AlfGrammarAccess.LinkOperationTupleElementElements getLinkOperationTupleElementAccess() {
		return gaAlf.getLinkOperationTupleElementAccess();
	}
	
	public ParserRule getLinkOperationTupleElementRule() {
		return getLinkOperationTupleElementAccess().getRule();
	}

	//enum LinkOperationKind:
	//	CREATE="createLink" | DESTROY="destroyLink" | CLEAR="clearAssoc";
	public AlfGrammarAccess.LinkOperationKindElements getLinkOperationKindAccess() {
		return gaAlf.getLinkOperationKindAccess();
	}
	
	public EnumRule getLinkOperationKindRule() {
		return getLinkOperationKindAccess().getRule();
	}

	//SequenceOperationExpression: //'->' operationName = ID tuple = Tuple (suffix = SuffixExpression) ?
	//	"->" operationName=QualifiedNameWithBinding tuple=Tuple suffix=SuffixExpression?;
	public AlfGrammarAccess.SequenceOperationExpressionElements getSequenceOperationExpressionAccess() {
		return gaAlf.getSequenceOperationExpressionAccess();
	}
	
	public ParserRule getSequenceOperationExpressionRule() {
		return getSequenceOperationExpressionAccess().getRule();
	}

	//SequenceReductionExpression:
	//	"->" "reduce" isOrdered?="ordered"? behavior=QualifiedNameWithBinding suffix=SuffixExpression?;
	public AlfGrammarAccess.SequenceReductionExpressionElements getSequenceReductionExpressionAccess() {
		return gaAlf.getSequenceReductionExpressionAccess();
	}
	
	public ParserRule getSequenceReductionExpressionRule() {
		return getSequenceReductionExpressionAccess().getRule();
	}

	//SequenceExpansionExpression:
	//	SelectOrRejectOperation | CollectOrIterateOperation | ForAllOrExistsOrOneOperation | IsUniqueOperation;
	public AlfGrammarAccess.SequenceExpansionExpressionElements getSequenceExpansionExpressionAccess() {
		return gaAlf.getSequenceExpansionExpressionAccess();
	}
	
	public ParserRule getSequenceExpansionExpressionRule() {
		return getSequenceExpansionExpressionAccess().getRule();
	}

	//SelectOrRejectOperation:
	//	"->" op=SelectOrRejectOperator name=ID "(" expr=Expression ")" suffix=SuffixExpression?;
	public AlfGrammarAccess.SelectOrRejectOperationElements getSelectOrRejectOperationAccess() {
		return gaAlf.getSelectOrRejectOperationAccess();
	}
	
	public ParserRule getSelectOrRejectOperationRule() {
		return getSelectOrRejectOperationAccess().getRule();
	}

	//enum SelectOrRejectOperator:
	//	SELECT="select" | REJECT="reject";
	public AlfGrammarAccess.SelectOrRejectOperatorElements getSelectOrRejectOperatorAccess() {
		return gaAlf.getSelectOrRejectOperatorAccess();
	}
	
	public EnumRule getSelectOrRejectOperatorRule() {
		return getSelectOrRejectOperatorAccess().getRule();
	}

	//CollectOrIterateOperation:
	//	"->" op=CollectOrIterateOperator name=ID "(" expr=Expression ")" suffix=SuffixExpression?;
	public AlfGrammarAccess.CollectOrIterateOperationElements getCollectOrIterateOperationAccess() {
		return gaAlf.getCollectOrIterateOperationAccess();
	}
	
	public ParserRule getCollectOrIterateOperationRule() {
		return getCollectOrIterateOperationAccess().getRule();
	}

	//enum CollectOrIterateOperator:
	//	COLLECT="collect" | ITERATE="iterate";
	public AlfGrammarAccess.CollectOrIterateOperatorElements getCollectOrIterateOperatorAccess() {
		return gaAlf.getCollectOrIterateOperatorAccess();
	}
	
	public EnumRule getCollectOrIterateOperatorRule() {
		return getCollectOrIterateOperatorAccess().getRule();
	}

	//ForAllOrExistsOrOneOperation:
	//	"->" op=ForAllOrExistsOrOneOperator name=ID "(" expr=Expression ")" suffix=SuffixExpression?;
	public AlfGrammarAccess.ForAllOrExistsOrOneOperationElements getForAllOrExistsOrOneOperationAccess() {
		return gaAlf.getForAllOrExistsOrOneOperationAccess();
	}
	
	public ParserRule getForAllOrExistsOrOneOperationRule() {
		return getForAllOrExistsOrOneOperationAccess().getRule();
	}

	//enum ForAllOrExistsOrOneOperator:
	//	FORALL="forAll" | EXISTS="exists" | ONE="one";
	public AlfGrammarAccess.ForAllOrExistsOrOneOperatorElements getForAllOrExistsOrOneOperatorAccess() {
		return gaAlf.getForAllOrExistsOrOneOperatorAccess();
	}
	
	public EnumRule getForAllOrExistsOrOneOperatorRule() {
		return getForAllOrExistsOrOneOperatorAccess().getRule();
	}

	//IsUniqueOperation:
	//	"->" "isUnique" name=ID "(" expr=Expression ")" suffix=SuffixExpression?;
	public AlfGrammarAccess.IsUniqueOperationElements getIsUniqueOperationAccess() {
		return gaAlf.getIsUniqueOperationAccess();
	}
	
	public ParserRule getIsUniqueOperationRule() {
		return getIsUniqueOperationAccess().getRule();
	}

	//ValueSpecification:
	//	NameExpression | LITERAL | ThisExpression | SuperInvocationExpression | InstanceCreationExpression |
	//	ParenthesizedExpression | NullExpression;
	public AlfGrammarAccess.ValueSpecificationElements getValueSpecificationAccess() {
		return gaAlf.getValueSpecificationAccess();
	}
	
	public ParserRule getValueSpecificationRule() {
		return getValueSpecificationAccess().getRule();
	}

	//NonLiteralValueSpecification:
	//	NameExpression | ParenthesizedExpression | InstanceCreationExpression | ThisExpression | SuperInvocationExpression;
	public AlfGrammarAccess.NonLiteralValueSpecificationElements getNonLiteralValueSpecificationAccess() {
		return gaAlf.getNonLiteralValueSpecificationAccess();
	}
	
	public ParserRule getNonLiteralValueSpecificationRule() {
		return getNonLiteralValueSpecificationAccess().getRule();
	}

	//ParenthesizedExpression:
	//	"(" expOrTypeCast=Expression ")" (casted=NonLiteralValueSpecification | suffix=SuffixExpression)?;
	public AlfGrammarAccess.ParenthesizedExpressionElements getParenthesizedExpressionAccess() {
		return gaAlf.getParenthesizedExpressionAccess();
	}
	
	public ParserRule getParenthesizedExpressionRule() {
		return getParenthesizedExpressionAccess().getRule();
	}

	//NullExpression:
	//	{NullExpression} "null";
	public AlfGrammarAccess.NullExpressionElements getNullExpressionAccess() {
		return gaAlf.getNullExpressionAccess();
	}
	
	public ParserRule getNullExpressionRule() {
		return getNullExpressionAccess().getRule();
	}

	//ThisExpression:
	//	{ThisExpression} "this" suffix=SuffixExpression?;
	public AlfGrammarAccess.ThisExpressionElements getThisExpressionAccess() {
		return gaAlf.getThisExpressionAccess();
	}
	
	public ParserRule getThisExpressionRule() {
		return getThisExpressionAccess().getRule();
	}

	//// SuperInvocationExpression :
	////	//{SuperInvocationExpression} 'super' ('.' qualifiedNameRoot = ID '::' qualifiedNameRemaining = NameExpression)? //(suffix = SuffixExpression) ?
	////	'super' ('.' className = ID '::' operationCallWithoutDot = OperationCallExpressionWithoutDot | operationCall = OperationCallExpression) 
	////;
	//SuperInvocationExpression: //{SuperInvocationExpression} 'super' ('.' qualifiedNameRoot = ID '::' qualifiedNameRemaining = NameExpression)? //(suffix = SuffixExpression) ?
	////'super' ('.' className = ID '::' operationCallWithoutDot = OperationCallExpressionWithoutDot | operationCall = OperationCallExpression)
	////'super' ((tuple = Tuple) |
	////		 ('.' (path = QualifiedNamePath) operation = ID tuple = Tuple))
	//	"super" (tuple=Tuple | "." operationName=QualifiedNameWithBinding tuple=Tuple);
	public AlfGrammarAccess.SuperInvocationExpressionElements getSuperInvocationExpressionAccess() {
		return gaAlf.getSuperInvocationExpressionAccess();
	}
	
	public ParserRule getSuperInvocationExpressionRule() {
		return getSuperInvocationExpressionAccess().getRule();
	}

	////InstanceCreationExpression :
	////'new' constructor=QualifiedNameWithBinding 
	////	(tuple = Tuple | sequenceConstuctionCompletion = SequenceConstructionCompletion) (suffix = SuffixExpression) ?
	////'new' constructor=QualifiedNameWithBinding 
	////	tuple = Tuple (suffix = SuffixExpression) ?
	////;
	//InstanceCreationExpression: //'new' constructor=QualifiedNameWithBinding 
	////	(tuple = Tuple | sequenceConstuctionCompletion = SequenceConstructionCompletion) (suffix = SuffixExpression) ?
	//	"new" constructor=QualifiedNameWithBinding tuple=InstanceCreationTuple suffix=SuffixExpression?;
	public AlfGrammarAccess.InstanceCreationExpressionElements getInstanceCreationExpressionAccess() {
		return gaAlf.getInstanceCreationExpressionAccess();
	}
	
	public ParserRule getInstanceCreationExpressionRule() {
		return getInstanceCreationExpressionAccess().getRule();
	}

	//InstanceCreationTuple:
	//	{InstanceCreationTuple} "(" (instanceCreationTupleElement+=InstanceCreationTupleElement (","
	//	instanceCreationTupleElement+=InstanceCreationTupleElement)*)? ")";
	public AlfGrammarAccess.InstanceCreationTupleElements getInstanceCreationTupleAccess() {
		return gaAlf.getInstanceCreationTupleAccess();
	}
	
	public ParserRule getInstanceCreationTupleRule() {
		return getInstanceCreationTupleAccess().getRule();
	}

	////LinkOperationTupleElement :
	////	objectOrRole = ID (('['roleIndex = Expression ']')? '=>' object = ID)?
	////;
	//InstanceCreationTupleElement:
	//	role=ID "=>" object=Expression;
	public AlfGrammarAccess.InstanceCreationTupleElementElements getInstanceCreationTupleElementAccess() {
		return gaAlf.getInstanceCreationTupleElementAccess();
	}
	
	public ParserRule getInstanceCreationTupleElementRule() {
		return getInstanceCreationTupleElementAccess().getRule();
	}

	//SequenceConstructionOrAccessCompletion:
	//	multiplicityIndicator?="[" (accessCompletion=AccessCompletion |
	//	sequenceCompletion=PartialSequenceConstructionCompletion) | expression=SequenceConstructionExpression;
	public AlfGrammarAccess.SequenceConstructionOrAccessCompletionElements getSequenceConstructionOrAccessCompletionAccess() {
		return gaAlf.getSequenceConstructionOrAccessCompletionAccess();
	}
	
	public ParserRule getSequenceConstructionOrAccessCompletionRule() {
		return getSequenceConstructionOrAccessCompletionAccess().getRule();
	}

	//AccessCompletion:
	//	accessIndex=Expression "]";
	public AlfGrammarAccess.AccessCompletionElements getAccessCompletionAccess() {
		return gaAlf.getAccessCompletionAccess();
	}
	
	public ParserRule getAccessCompletionRule() {
		return getAccessCompletionAccess().getRule();
	}

	//PartialSequenceConstructionCompletion:
	//	"]" expression=SequenceConstructionExpression;
	public AlfGrammarAccess.PartialSequenceConstructionCompletionElements getPartialSequenceConstructionCompletionAccess() {
		return gaAlf.getPartialSequenceConstructionCompletionAccess();
	}
	
	public ParserRule getPartialSequenceConstructionCompletionRule() {
		return getPartialSequenceConstructionCompletionAccess().getRule();
	}

	////SequenceConstructionCompletion :
	////	(multiplicityIndicator ?= '['']')? expression = SequenceConstructionExpression
	////;
	//SequenceConstructionExpression:
	//	"{" sequenceElement+=SequenceElement (("," sequenceElement+=SequenceElement)* | ".." rangeUpper=Expression) "}";
	public AlfGrammarAccess.SequenceConstructionExpressionElements getSequenceConstructionExpressionAccess() {
		return gaAlf.getSequenceConstructionExpressionAccess();
	}
	
	public ParserRule getSequenceConstructionExpressionRule() {
		return getSequenceConstructionExpressionAccess().getRule();
	}

	//SequenceElement:
	//	Expression | SequenceConstructionExpression;
	public AlfGrammarAccess.SequenceElementElements getSequenceElementAccess() {
		return gaAlf.getSequenceElementAccess();
	}
	
	public ParserRule getSequenceElementRule() {
		return getSequenceElementAccess().getRule();
	}

	//ClassExtentExpression:
	//	{ClassExtentExpression} "." "allInstances" "(" ")";
	public AlfGrammarAccess.ClassExtentExpressionElements getClassExtentExpressionAccess() {
		return gaAlf.getClassExtentExpressionAccess();
	}
	
	public ParserRule getClassExtentExpressionRule() {
		return getClassExtentExpressionAccess().getRule();
	}

	/// *****************
	// * Statements
	// **************** / Block:
	//	"{" {Block} sequence=StatementSequence? "}";
	public AlfGrammarAccess.BlockElements getBlockAccess() {
		return gaAlf.getBlockAccess();
	}
	
	public ParserRule getBlockRule() {
		return getBlockAccess().getRule();
	}

	//StatementSequence:
	//	statements+=DocumentedStatement+;
	public AlfGrammarAccess.StatementSequenceElements getStatementSequenceAccess() {
		return gaAlf.getStatementSequenceAccess();
	}
	
	public ParserRule getStatementSequenceRule() {
		return getStatementSequenceAccess().getRule();
	}

	//DocumentedStatement:
	//	comment=(ML_COMMENT | SL_COMMENT)? statement=Statement;
	public AlfGrammarAccess.DocumentedStatementElements getDocumentedStatementAccess() {
		return gaAlf.getDocumentedStatementAccess();
	}
	
	public ParserRule getDocumentedStatementRule() {
		return getDocumentedStatementAccess().getRule();
	}

	//InlineStatement:
	//	"/ *@" "inline" "(" langageName=ID ")" body=STRING "* /";
	public AlfGrammarAccess.InlineStatementElements getInlineStatementAccess() {
		return gaAlf.getInlineStatementAccess();
	}
	
	public ParserRule getInlineStatementRule() {
		return getInlineStatementAccess().getRule();
	}

	//AnnotatedStatement:
	//	"//@" annotation= //block = Block
	//	Annotation statement=Statement;
	public AlfGrammarAccess.AnnotatedStatementElements getAnnotatedStatementAccess() {
		return gaAlf.getAnnotatedStatementAccess();
	}
	
	public ParserRule getAnnotatedStatementRule() {
		return getAnnotatedStatementAccess().getRule();
	}

	//Statement:
	//	AnnotatedStatement | InlineStatement | BlockStatement | EmptyStatement | LocalNameDeclarationStatement | IfStatement |
	//	SwitchStatement | WhileStatement | DoStatement | ForStatement | BreakStatement | ReturnStatement | AcceptStatement |
	//	ClassifyStatement | InvocationOrAssignementOrDeclarationStatement | SuperInvocationStatement | ThisInvocationStatement
	//	| InstanceCreationInvocationStatement;
	public AlfGrammarAccess.StatementElements getStatementAccess() {
		return gaAlf.getStatementAccess();
	}
	
	public ParserRule getStatementRule() {
		return getStatementAccess().getRule();
	}

	//Annotation:
	//	kind=AnnotationKind ("(" args+=ID ("," args+=ID)* ")")?;
	public AlfGrammarAccess.AnnotationElements getAnnotationAccess() {
		return gaAlf.getAnnotationAccess();
	}
	
	public ParserRule getAnnotationRule() {
		return getAnnotationAccess().getRule();
	}

	//enum AnnotationKind:
	//	ISOLATED="isolated" | DETERMINED="determined" | ASSURED="assured" | PARALLEL="parallel";
	public AlfGrammarAccess.AnnotationKindElements getAnnotationKindAccess() {
		return gaAlf.getAnnotationKindAccess();
	}
	
	public EnumRule getAnnotationKindRule() {
		return getAnnotationKindAccess().getRule();
	}

	//BlockStatement:
	//	block=Block;
	public AlfGrammarAccess.BlockStatementElements getBlockStatementAccess() {
		return gaAlf.getBlockStatementAccess();
	}
	
	public ParserRule getBlockStatementRule() {
		return getBlockStatementAccess().getRule();
	}

	//EmptyStatement:
	//	{EmptyStatement} ";";
	public AlfGrammarAccess.EmptyStatementElements getEmptyStatementAccess() {
		return gaAlf.getEmptyStatementAccess();
	}
	
	public ParserRule getEmptyStatementRule() {
		return getEmptyStatementAccess().getRule();
	}

	//LocalNameDeclarationStatement:
	//	"let" varName=ID ":" type=QualifiedNameWithBinding (multiplicityIndicator?="[" "]")? //'=' init = Expression ';'
	//	"=" init=SequenceElement ";";
	public AlfGrammarAccess.LocalNameDeclarationStatementElements getLocalNameDeclarationStatementAccess() {
		return gaAlf.getLocalNameDeclarationStatementAccess();
	}
	
	public ParserRule getLocalNameDeclarationStatementRule() {
		return getLocalNameDeclarationStatementAccess().getRule();
	}

	//IfStatement:
	//	"if" sequentialClausses=SequentialClauses finalClause=FinalClause?;
	public AlfGrammarAccess.IfStatementElements getIfStatementAccess() {
		return gaAlf.getIfStatementAccess();
	}
	
	public ParserRule getIfStatementRule() {
		return getIfStatementAccess().getRule();
	}

	//SequentialClauses:
	//	conccurentClauses+=ConcurrentClauses ("else" "if" conccurentClauses+=ConcurrentClauses)*;
	public AlfGrammarAccess.SequentialClausesElements getSequentialClausesAccess() {
		return gaAlf.getSequentialClausesAccess();
	}
	
	public ParserRule getSequentialClausesRule() {
		return getSequentialClausesAccess().getRule();
	}

	//ConcurrentClauses:
	//	nonFinalClause+=NonFinalClause ("or" "if" nonFinalClause+=NonFinalClause)*;
	public AlfGrammarAccess.ConcurrentClausesElements getConcurrentClausesAccess() {
		return gaAlf.getConcurrentClausesAccess();
	}
	
	public ParserRule getConcurrentClausesRule() {
		return getConcurrentClausesAccess().getRule();
	}

	//NonFinalClause:
	//	"(" condition=Expression ")" block=Block;
	public AlfGrammarAccess.NonFinalClauseElements getNonFinalClauseAccess() {
		return gaAlf.getNonFinalClauseAccess();
	}
	
	public ParserRule getNonFinalClauseRule() {
		return getNonFinalClauseAccess().getRule();
	}

	//FinalClause:
	//	"else" block=Block;
	public AlfGrammarAccess.FinalClauseElements getFinalClauseAccess() {
		return gaAlf.getFinalClauseAccess();
	}
	
	public ParserRule getFinalClauseRule() {
		return getFinalClauseAccess().getRule();
	}

	//SwitchStatement:
	//	"switch" "(" expression=Expression ")" "{" switchClause+=SwitchClause* defaultClause=SwitchDefaultClause? "}";
	public AlfGrammarAccess.SwitchStatementElements getSwitchStatementAccess() {
		return gaAlf.getSwitchStatementAccess();
	}
	
	public ParserRule getSwitchStatementRule() {
		return getSwitchStatementAccess().getRule();
	}

	//SwitchClause:
	//	switchCase+=SwitchCase switchCase+=SwitchCase* statementSequence=NonEmptyStatementSequence;
	public AlfGrammarAccess.SwitchClauseElements getSwitchClauseAccess() {
		return gaAlf.getSwitchClauseAccess();
	}
	
	public ParserRule getSwitchClauseRule() {
		return getSwitchClauseAccess().getRule();
	}

	//SwitchCase:
	//	"case" expression=Expression ":";
	public AlfGrammarAccess.SwitchCaseElements getSwitchCaseAccess() {
		return gaAlf.getSwitchCaseAccess();
	}
	
	public ParserRule getSwitchCaseRule() {
		return getSwitchCaseAccess().getRule();
	}

	//SwitchDefaultClause:
	//	"default" ":" statementSequence=NonEmptyStatementSequence;
	public AlfGrammarAccess.SwitchDefaultClauseElements getSwitchDefaultClauseAccess() {
		return gaAlf.getSwitchDefaultClauseAccess();
	}
	
	public ParserRule getSwitchDefaultClauseRule() {
		return getSwitchDefaultClauseAccess().getRule();
	}

	//NonEmptyStatementSequence:
	//	statement+=DocumentedStatement+;
	public AlfGrammarAccess.NonEmptyStatementSequenceElements getNonEmptyStatementSequenceAccess() {
		return gaAlf.getNonEmptyStatementSequenceAccess();
	}
	
	public ParserRule getNonEmptyStatementSequenceRule() {
		return getNonEmptyStatementSequenceAccess().getRule();
	}

	/// * WHILE STATEMENTS * / WhileStatement:
	//	"while" "(" condition=Expression ")" block=Block;
	public AlfGrammarAccess.WhileStatementElements getWhileStatementAccess() {
		return gaAlf.getWhileStatementAccess();
	}
	
	public ParserRule getWhileStatementRule() {
		return getWhileStatementAccess().getRule();
	}

	/// * DO STATEMENTS * / DoStatement:
	//	"do" block=Block "while" "(" condition=Expression ")" ";";
	public AlfGrammarAccess.DoStatementElements getDoStatementAccess() {
		return gaAlf.getDoStatementAccess();
	}
	
	public ParserRule getDoStatementRule() {
		return getDoStatementAccess().getRule();
	}

	/// * FOR STATEMENTS * / ForStatement:
	//	"for" "(" control=ForControl ")" block=Block;
	public AlfGrammarAccess.ForStatementElements getForStatementAccess() {
		return gaAlf.getForStatementAccess();
	}
	
	public ParserRule getForStatementRule() {
		return getForStatementAccess().getRule();
	}

	//ForControl:
	//	loopVariableDefinition+=LoopVariableDefinition ("," loopVariableDefinition+=LoopVariableDefinition)*;
	public AlfGrammarAccess.ForControlElements getForControlAccess() {
		return gaAlf.getForControlAccess();
	}
	
	public ParserRule getForControlRule() {
		return getForControlAccess().getRule();
	}

	//LoopVariableDefinition:
	//	name=ID "in" expression1=Expression (".." expression2=Expression)? | type=QualifiedNameWithBinding name=ID ":"
	//	expression=Expression;
	public AlfGrammarAccess.LoopVariableDefinitionElements getLoopVariableDefinitionAccess() {
		return gaAlf.getLoopVariableDefinitionAccess();
	}
	
	public ParserRule getLoopVariableDefinitionRule() {
		return getLoopVariableDefinitionAccess().getRule();
	}

	/// * BREAK STATEMENTS * / BreakStatement:
	//	{BreakStatement} "break" ";";
	public AlfGrammarAccess.BreakStatementElements getBreakStatementAccess() {
		return gaAlf.getBreakStatementAccess();
	}
	
	public ParserRule getBreakStatementRule() {
		return getBreakStatementAccess().getRule();
	}

	/// * RETURN STATEMENTS * / ReturnStatement:
	//	"return" expression=Expression ";";
	public AlfGrammarAccess.ReturnStatementElements getReturnStatementAccess() {
		return gaAlf.getReturnStatementAccess();
	}
	
	public ParserRule getReturnStatementRule() {
		return getReturnStatementAccess().getRule();
	}

	/// * ACCEPT STATEMENTS * / AcceptStatement:
	//	clause=AcceptClause (simpleAccept=SimpleAcceptStatementCompletion | compoundAccept=CompoundAcceptStatementCompletion);
	public AlfGrammarAccess.AcceptStatementElements getAcceptStatementAccess() {
		return gaAlf.getAcceptStatementAccess();
	}
	
	public ParserRule getAcceptStatementRule() {
		return getAcceptStatementAccess().getRule();
	}

	//SimpleAcceptStatementCompletion:
	//	{SimpleAcceptStatementCompletion} ";";
	public AlfGrammarAccess.SimpleAcceptStatementCompletionElements getSimpleAcceptStatementCompletionAccess() {
		return gaAlf.getSimpleAcceptStatementCompletionAccess();
	}
	
	public ParserRule getSimpleAcceptStatementCompletionRule() {
		return getSimpleAcceptStatementCompletionAccess().getRule();
	}

	//CompoundAcceptStatementCompletion:
	//	block=Block ("or" acceptBlock+=AcceptBlock)*;
	public AlfGrammarAccess.CompoundAcceptStatementCompletionElements getCompoundAcceptStatementCompletionAccess() {
		return gaAlf.getCompoundAcceptStatementCompletionAccess();
	}
	
	public ParserRule getCompoundAcceptStatementCompletionRule() {
		return getCompoundAcceptStatementCompletionAccess().getRule();
	}

	//AcceptBlock:
	//	clause=AcceptClause block=Block;
	public AlfGrammarAccess.AcceptBlockElements getAcceptBlockAccess() {
		return gaAlf.getAcceptBlockAccess();
	}
	
	public ParserRule getAcceptBlockRule() {
		return getAcceptBlockAccess().getRule();
	}

	//AcceptClause:
	//	"accept" "(" (name=ID ":")? qualifiedNameList=QualifiedNameList ")";
	public AlfGrammarAccess.AcceptClauseElements getAcceptClauseAccess() {
		return gaAlf.getAcceptClauseAccess();
	}
	
	public ParserRule getAcceptClauseRule() {
		return getAcceptClauseAccess().getRule();
	}

	/// * CLASSIFY STATEMENTS * / ClassifyStatement:
	//	"classify" expression=Expression clause=ClassificationClause ";";
	public AlfGrammarAccess.ClassifyStatementElements getClassifyStatementAccess() {
		return gaAlf.getClassifyStatementAccess();
	}
	
	public ParserRule getClassifyStatementRule() {
		return getClassifyStatementAccess().getRule();
	}

	//ClassificationClause:
	//	classifyFromClause=ClassificationFromClause classifyToClause=ClassificationToClause? |
	//	reclassyAllClause=ReclassifyAllClause? classifyToClause=ClassificationToClause;
	public AlfGrammarAccess.ClassificationClauseElements getClassificationClauseAccess() {
		return gaAlf.getClassificationClauseAccess();
	}
	
	public ParserRule getClassificationClauseRule() {
		return getClassificationClauseAccess().getRule();
	}

	//ClassificationFromClause:
	//	"from" qualifiedNameList=QualifiedNameList;
	public AlfGrammarAccess.ClassificationFromClauseElements getClassificationFromClauseAccess() {
		return gaAlf.getClassificationFromClauseAccess();
	}
	
	public ParserRule getClassificationFromClauseRule() {
		return getClassificationFromClauseAccess().getRule();
	}

	//ClassificationToClause:
	//	"to" qualifiedNameList=QualifiedNameList;
	public AlfGrammarAccess.ClassificationToClauseElements getClassificationToClauseAccess() {
		return gaAlf.getClassificationToClauseAccess();
	}
	
	public ParserRule getClassificationToClauseRule() {
		return getClassificationToClauseAccess().getRule();
	}

	//ReclassifyAllClause:
	//	{ReclassifyAllClause} "from" "*";
	public AlfGrammarAccess.ReclassifyAllClauseElements getReclassifyAllClauseAccess() {
		return gaAlf.getReclassifyAllClauseAccess();
	}
	
	public ParserRule getReclassifyAllClauseRule() {
		return getReclassifyAllClauseAccess().getRule();
	}

	//QualifiedNameList:
	//	qualifiedName+=QualifiedNameWithBinding ("," qualifiedName+=QualifiedNameWithBinding)*;
	public AlfGrammarAccess.QualifiedNameListElements getQualifiedNameListAccess() {
		return gaAlf.getQualifiedNameListAccess();
	}
	
	public ParserRule getQualifiedNameListRule() {
		return getQualifiedNameListAccess().getRule();
	}

	//InvocationOrAssignementOrDeclarationStatement:
	//	typePart_OR_assignedPart_OR_invocationPart=NameExpression (variableDeclarationCompletion=VariableDeclarationCompletion //(suffixCompletion = SuffixCompletion)?
	//	| assignmentCompletion=AssignmentCompletion)? ";";
	public AlfGrammarAccess.InvocationOrAssignementOrDeclarationStatementElements getInvocationOrAssignementOrDeclarationStatementAccess() {
		return gaAlf.getInvocationOrAssignementOrDeclarationStatementAccess();
	}
	
	public ParserRule getInvocationOrAssignementOrDeclarationStatementRule() {
		return getInvocationOrAssignementOrDeclarationStatementAccess().getRule();
	}

	//SuperInvocationStatement:
	//	_super=SuperInvocationExpression //(suffix = SuffixCompletion)? ';'
	//	";";
	public AlfGrammarAccess.SuperInvocationStatementElements getSuperInvocationStatementAccess() {
		return gaAlf.getSuperInvocationStatementAccess();
	}
	
	public ParserRule getSuperInvocationStatementRule() {
		return getSuperInvocationStatementAccess().getRule();
	}

	//ThisInvocationStatement: //_this = ThisExpression suffix = SuffixCompletion (assignmentCompletion = AssignmentCompletion)? ';'
	//	_this=ThisExpression assignmentCompletion=AssignmentCompletion? ";";
	public AlfGrammarAccess.ThisInvocationStatementElements getThisInvocationStatementAccess() {
		return gaAlf.getThisInvocationStatementAccess();
	}
	
	public ParserRule getThisInvocationStatementRule() {
		return getThisInvocationStatementAccess().getRule();
	}

	//InstanceCreationInvocationStatement:
	//	_new=InstanceCreationExpression //(suffix = SuffixCompletion)? ';'
	//	";";
	public AlfGrammarAccess.InstanceCreationInvocationStatementElements getInstanceCreationInvocationStatementAccess() {
		return gaAlf.getInstanceCreationInvocationStatementAccess();
	}
	
	public ParserRule getInstanceCreationInvocationStatementRule() {
		return getInstanceCreationInvocationStatementAccess().getRule();
	}

	////SuffixCompletion :
	////	suffix = SuffixExpression
	////;
	//VariableDeclarationCompletion:
	//	(multiplicityIndicator?="[" "]")? variableName=ID initValue=AssignmentCompletion;
	public AlfGrammarAccess.VariableDeclarationCompletionElements getVariableDeclarationCompletionAccess() {
		return gaAlf.getVariableDeclarationCompletionAccess();
	}
	
	public ParserRule getVariableDeclarationCompletionRule() {
		return getVariableDeclarationCompletionAccess().getRule();
	}

	////op=('=' | '+=' | '-=' | '*=' | '%=' | '/=' | '&=' |
	////	'|=' | '^=' | '<<=' | '>>=' | '>>>=') rightHandSide = Expression
	//AssignmentCompletion:
	//	op=AssignmentOperator rightHandSide=SequenceElement;
	public AlfGrammarAccess.AssignmentCompletionElements getAssignmentCompletionAccess() {
		return gaAlf.getAssignmentCompletionAccess();
	}
	
	public ParserRule getAssignmentCompletionRule() {
		return getAssignmentCompletionAccess().getRule();
	}

	//enum AssignmentOperator:
	//	ASSIGN="=" | PLUSASSIGN="+=" | MINUSASSIGN="-=" | MULTASSIGN="*=" | MODASSIGN="%=" | DIVASSIGN="/=" | ANDASSIGN="&=" |
	//	ORASSIGN="|=" | XORASSIGN="^=" | LSHIFTASSIGN="<<=" | RSHIFTASSIGN=">>=" | URSHIFTASSIGN=">>>=";
	public AlfGrammarAccess.AssignmentOperatorElements getAssignmentOperatorAccess() {
		return gaAlf.getAssignmentOperatorAccess();
	}
	
	public EnumRule getAssignmentOperatorRule() {
		return getAssignmentOperatorAccess().getRule();
	}

	/// ****************
	// * Terminals
	// ***************** / //terminal DOUBLE_COLON : '::' ;
	//terminal ID:
	//	("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")* | "\'"->"\'";
	public TerminalRule getIDRule() {
		return gaAlf.getIDRule();
	} 

	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"";
	public TerminalRule getSTRINGRule() {
		return gaAlf.getSTRINGRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *" !"@"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaAlf.getML_COMMENTRule();
	} 

	////terminal IDENTIFIER : ID  ;
	////terminal IDENTIFIER : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*  | ('\'' -> '\'')  ;
	////terminal DOCUMENTATION_COMMENT : '/ *' -> '* /' ;
	////terminal ML_COMMENT	: '/�' -> '�/';
	////terminal SL_COMMENT 	: '��' !('\n'|'\r')* ('\r'? '\n')?;
	////terminal WS			: (' '|'\t'|'\r'|'\n')+; terminal SL_COMMENT:
	//	"//" !("\n" | "\r" | "@")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaAlf.getSL_COMMENTRule();
	} 

	//terminal INT returns ecore::EInt:
	//	"0".."9"+;
	public TerminalRule getINTRule() {
		return gaAlf.getINTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaAlf.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaAlf.getANY_OTHERRule();
	} 
}
