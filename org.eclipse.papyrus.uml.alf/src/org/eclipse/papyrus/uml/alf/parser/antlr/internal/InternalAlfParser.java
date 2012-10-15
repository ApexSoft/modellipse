/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
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
package org.eclipse.papyrus.uml.alf.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.papyrus.uml.alf.services.AlfGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalAlfParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INTEGERVALUE", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_INT", "RULE_WS", "RULE_ANY_OTHER", "'testExpression'", "'testAssignmentExpression'", "'testStatement'", "'testBlock'", "'*'", "'++'", "'--'", "'::'", "'<'", "','", "'>'", "'=>'", "'('", "')'", "'?'", "':'", "'||'", "'&&'", "'|'", "'^'", "'&'", "'=='", "'!='", "'instanceof'", "'hastype'", "'<='", "'>='", "'<<'", "'>>'", "'>>>'", "'+'", "'-'", "'/'", "'%'", "'!'", "'$'", "'~'", "'.'", "'['", "']'", "'->'", "'reduce'", "'ordered'", "'isUnique'", "'null'", "'this'", "'super'", "'new'", "'{'", "'..'", "'}'", "'allInstances'", "'/*@'", "'inline'", "'*/'", "'//@'", "';'", "'let'", "'='", "'if'", "'else'", "'or'", "'switch'", "'case'", "'default'", "'while'", "'do'", "'for'", "'in'", "'break'", "'return'", "'accept'", "'classify'", "'from'", "'to'", "'true'", "'false'", "'createLink'", "'destroyLink'", "'clearAssoc'", "'select'", "'reject'", "'collect'", "'iterate'", "'forAll'", "'exists'", "'one'", "'isolated'", "'determined'", "'assured'", "'parallel'", "'+='", "'-='", "'*='", "'%='", "'/='", "'&='", "'|='", "'^='", "'<<='", "'>>='", "'>>>='"
    };
    public static final int RULE_ID=6;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int EOF=-1;
    public static final int T__93=93;
    public static final int T__19=19;
    public static final int T__94=94;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__90=90;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__99=99;
    public static final int RULE_INTEGERVALUE=4;
    public static final int T__98=98;
    public static final int T__97=97;
    public static final int T__96=96;
    public static final int T__95=95;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int RULE_ML_COMMENT=7;
    public static final int RULE_STRING=5;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int T__103=103;
    public static final int T__59=59;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int RULE_INT=9;
    public static final int T__113=113;
    public static final int T__112=112;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__102=102;
    public static final int T__101=101;
    public static final int T__100=100;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_WS=10;

    // delegates
    // delegators


        public InternalAlfParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalAlfParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalAlfParser.tokenNames; }
    public String getGrammarFileName() { return "../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g"; }



     	private AlfGrammarAccess grammarAccess;
     	
        public InternalAlfParser(TokenStream input, AlfGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Test";	
       	}
       	
       	@Override
       	protected AlfGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleTest"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:68:1: entryRuleTest returns [EObject current=null] : iv_ruleTest= ruleTest EOF ;
    public final EObject entryRuleTest() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTest = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:69:2: (iv_ruleTest= ruleTest EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:70:2: iv_ruleTest= ruleTest EOF
            {
             newCompositeNode(grammarAccess.getTestRule()); 
            pushFollow(FOLLOW_ruleTest_in_entryRuleTest75);
            iv_ruleTest=ruleTest();

            state._fsp--;

             current =iv_ruleTest; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTest85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTest"


    // $ANTLR start "ruleTest"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:77:1: ruleTest returns [EObject current=null] : ( (otherlv_0= 'testExpression' ( (lv_expression_1_0= ruleExpression ) ) )* (otherlv_2= 'testAssignmentExpression' ( (lv_assignExpression_3_0= ruleAssignmentCompletion ) ) )* (otherlv_4= 'testStatement' ( (lv_statements_5_0= ruleStatement ) ) )* (otherlv_6= 'testBlock' ( (lv_block_7_0= ruleBlock ) ) ) ) ;
    public final EObject ruleTest() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_expression_1_0 = null;

        EObject lv_assignExpression_3_0 = null;

        EObject lv_statements_5_0 = null;

        EObject lv_block_7_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:80:28: ( ( (otherlv_0= 'testExpression' ( (lv_expression_1_0= ruleExpression ) ) )* (otherlv_2= 'testAssignmentExpression' ( (lv_assignExpression_3_0= ruleAssignmentCompletion ) ) )* (otherlv_4= 'testStatement' ( (lv_statements_5_0= ruleStatement ) ) )* (otherlv_6= 'testBlock' ( (lv_block_7_0= ruleBlock ) ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:81:1: ( (otherlv_0= 'testExpression' ( (lv_expression_1_0= ruleExpression ) ) )* (otherlv_2= 'testAssignmentExpression' ( (lv_assignExpression_3_0= ruleAssignmentCompletion ) ) )* (otherlv_4= 'testStatement' ( (lv_statements_5_0= ruleStatement ) ) )* (otherlv_6= 'testBlock' ( (lv_block_7_0= ruleBlock ) ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:81:1: ( (otherlv_0= 'testExpression' ( (lv_expression_1_0= ruleExpression ) ) )* (otherlv_2= 'testAssignmentExpression' ( (lv_assignExpression_3_0= ruleAssignmentCompletion ) ) )* (otherlv_4= 'testStatement' ( (lv_statements_5_0= ruleStatement ) ) )* (otherlv_6= 'testBlock' ( (lv_block_7_0= ruleBlock ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:81:2: (otherlv_0= 'testExpression' ( (lv_expression_1_0= ruleExpression ) ) )* (otherlv_2= 'testAssignmentExpression' ( (lv_assignExpression_3_0= ruleAssignmentCompletion ) ) )* (otherlv_4= 'testStatement' ( (lv_statements_5_0= ruleStatement ) ) )* (otherlv_6= 'testBlock' ( (lv_block_7_0= ruleBlock ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:81:2: (otherlv_0= 'testExpression' ( (lv_expression_1_0= ruleExpression ) ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==12) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:81:4: otherlv_0= 'testExpression' ( (lv_expression_1_0= ruleExpression ) )
            	    {
            	    otherlv_0=(Token)match(input,12,FOLLOW_12_in_ruleTest123); 

            	        	newLeafNode(otherlv_0, grammarAccess.getTestAccess().getTestExpressionKeyword_0_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:85:1: ( (lv_expression_1_0= ruleExpression ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:86:1: (lv_expression_1_0= ruleExpression )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:86:1: (lv_expression_1_0= ruleExpression )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:87:3: lv_expression_1_0= ruleExpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTestAccess().getExpressionExpressionParserRuleCall_0_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleExpression_in_ruleTest144);
            	    lv_expression_1_0=ruleExpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTestRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"expression",
            	            		lv_expression_1_0, 
            	            		"Expression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:103:4: (otherlv_2= 'testAssignmentExpression' ( (lv_assignExpression_3_0= ruleAssignmentCompletion ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==13) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:103:6: otherlv_2= 'testAssignmentExpression' ( (lv_assignExpression_3_0= ruleAssignmentCompletion ) )
            	    {
            	    otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleTest159); 

            	        	newLeafNode(otherlv_2, grammarAccess.getTestAccess().getTestAssignmentExpressionKeyword_1_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:107:1: ( (lv_assignExpression_3_0= ruleAssignmentCompletion ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:108:1: (lv_assignExpression_3_0= ruleAssignmentCompletion )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:108:1: (lv_assignExpression_3_0= ruleAssignmentCompletion )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:109:3: lv_assignExpression_3_0= ruleAssignmentCompletion
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTestAccess().getAssignExpressionAssignmentCompletionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAssignmentCompletion_in_ruleTest180);
            	    lv_assignExpression_3_0=ruleAssignmentCompletion();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTestRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"assignExpression",
            	            		lv_assignExpression_3_0, 
            	            		"AssignmentCompletion");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:125:4: (otherlv_4= 'testStatement' ( (lv_statements_5_0= ruleStatement ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==14) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:125:6: otherlv_4= 'testStatement' ( (lv_statements_5_0= ruleStatement ) )
            	    {
            	    otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleTest195); 

            	        	newLeafNode(otherlv_4, grammarAccess.getTestAccess().getTestStatementKeyword_2_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:129:1: ( (lv_statements_5_0= ruleStatement ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:130:1: (lv_statements_5_0= ruleStatement )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:130:1: (lv_statements_5_0= ruleStatement )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:131:3: lv_statements_5_0= ruleStatement
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTestAccess().getStatementsStatementParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleStatement_in_ruleTest216);
            	    lv_statements_5_0=ruleStatement();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTestRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"statements",
            	            		lv_statements_5_0, 
            	            		"Statement");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:147:4: (otherlv_6= 'testBlock' ( (lv_block_7_0= ruleBlock ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:147:6: otherlv_6= 'testBlock' ( (lv_block_7_0= ruleBlock ) )
            {
            otherlv_6=(Token)match(input,15,FOLLOW_15_in_ruleTest231); 

                	newLeafNode(otherlv_6, grammarAccess.getTestAccess().getTestBlockKeyword_3_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:151:1: ( (lv_block_7_0= ruleBlock ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:152:1: (lv_block_7_0= ruleBlock )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:152:1: (lv_block_7_0= ruleBlock )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:153:3: lv_block_7_0= ruleBlock
            {
             
            	        newCompositeNode(grammarAccess.getTestAccess().getBlockBlockParserRuleCall_3_1_0()); 
            	    
            pushFollow(FOLLOW_ruleBlock_in_ruleTest252);
            lv_block_7_0=ruleBlock();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTestRule());
            	        }
                   		set(
                   			current, 
                   			"block",
                    		lv_block_7_0, 
                    		"Block");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTest"


    // $ANTLR start "entryRuleLITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:177:1: entryRuleLITERAL returns [EObject current=null] : iv_ruleLITERAL= ruleLITERAL EOF ;
    public final EObject entryRuleLITERAL() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLITERAL = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:178:2: (iv_ruleLITERAL= ruleLITERAL EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:179:2: iv_ruleLITERAL= ruleLITERAL EOF
            {
             newCompositeNode(grammarAccess.getLITERALRule()); 
            pushFollow(FOLLOW_ruleLITERAL_in_entryRuleLITERAL289);
            iv_ruleLITERAL=ruleLITERAL();

            state._fsp--;

             current =iv_ruleLITERAL; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLITERAL299); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLITERAL"


    // $ANTLR start "ruleLITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:186:1: ruleLITERAL returns [EObject current=null] : (this_BOOLEAN_LITERAL_0= ruleBOOLEAN_LITERAL | this_NUMBER_LITERAL_1= ruleNUMBER_LITERAL | this_STRING_LITERAL_2= ruleSTRING_LITERAL ) ;
    public final EObject ruleLITERAL() throws RecognitionException {
        EObject current = null;

        EObject this_BOOLEAN_LITERAL_0 = null;

        EObject this_NUMBER_LITERAL_1 = null;

        EObject this_STRING_LITERAL_2 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:189:28: ( (this_BOOLEAN_LITERAL_0= ruleBOOLEAN_LITERAL | this_NUMBER_LITERAL_1= ruleNUMBER_LITERAL | this_STRING_LITERAL_2= ruleSTRING_LITERAL ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:190:1: (this_BOOLEAN_LITERAL_0= ruleBOOLEAN_LITERAL | this_NUMBER_LITERAL_1= ruleNUMBER_LITERAL | this_STRING_LITERAL_2= ruleSTRING_LITERAL )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:190:1: (this_BOOLEAN_LITERAL_0= ruleBOOLEAN_LITERAL | this_NUMBER_LITERAL_1= ruleNUMBER_LITERAL | this_STRING_LITERAL_2= ruleSTRING_LITERAL )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 87:
            case 88:
                {
                alt4=1;
                }
                break;
            case RULE_INTEGERVALUE:
            case 16:
                {
                alt4=2;
                }
                break;
            case RULE_STRING:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:191:5: this_BOOLEAN_LITERAL_0= ruleBOOLEAN_LITERAL
                    {
                     
                            newCompositeNode(grammarAccess.getLITERALAccess().getBOOLEAN_LITERALParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleBOOLEAN_LITERAL_in_ruleLITERAL346);
                    this_BOOLEAN_LITERAL_0=ruleBOOLEAN_LITERAL();

                    state._fsp--;

                     
                            current = this_BOOLEAN_LITERAL_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:201:5: this_NUMBER_LITERAL_1= ruleNUMBER_LITERAL
                    {
                     
                            newCompositeNode(grammarAccess.getLITERALAccess().getNUMBER_LITERALParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleNUMBER_LITERAL_in_ruleLITERAL373);
                    this_NUMBER_LITERAL_1=ruleNUMBER_LITERAL();

                    state._fsp--;

                     
                            current = this_NUMBER_LITERAL_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:211:5: this_STRING_LITERAL_2= ruleSTRING_LITERAL
                    {
                     
                            newCompositeNode(grammarAccess.getLITERALAccess().getSTRING_LITERALParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleSTRING_LITERAL_in_ruleLITERAL400);
                    this_STRING_LITERAL_2=ruleSTRING_LITERAL();

                    state._fsp--;

                     
                            current = this_STRING_LITERAL_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLITERAL"


    // $ANTLR start "entryRuleBOOLEAN_LITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:227:1: entryRuleBOOLEAN_LITERAL returns [EObject current=null] : iv_ruleBOOLEAN_LITERAL= ruleBOOLEAN_LITERAL EOF ;
    public final EObject entryRuleBOOLEAN_LITERAL() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBOOLEAN_LITERAL = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:228:2: (iv_ruleBOOLEAN_LITERAL= ruleBOOLEAN_LITERAL EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:229:2: iv_ruleBOOLEAN_LITERAL= ruleBOOLEAN_LITERAL EOF
            {
             newCompositeNode(grammarAccess.getBOOLEAN_LITERALRule()); 
            pushFollow(FOLLOW_ruleBOOLEAN_LITERAL_in_entryRuleBOOLEAN_LITERAL435);
            iv_ruleBOOLEAN_LITERAL=ruleBOOLEAN_LITERAL();

            state._fsp--;

             current =iv_ruleBOOLEAN_LITERAL; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBOOLEAN_LITERAL445); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBOOLEAN_LITERAL"


    // $ANTLR start "ruleBOOLEAN_LITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:236:1: ruleBOOLEAN_LITERAL returns [EObject current=null] : ( (lv_value_0_0= ruleBooleanValue ) ) ;
    public final EObject ruleBOOLEAN_LITERAL() throws RecognitionException {
        EObject current = null;

        Enumerator lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:239:28: ( ( (lv_value_0_0= ruleBooleanValue ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:240:1: ( (lv_value_0_0= ruleBooleanValue ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:240:1: ( (lv_value_0_0= ruleBooleanValue ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:241:1: (lv_value_0_0= ruleBooleanValue )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:241:1: (lv_value_0_0= ruleBooleanValue )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:242:3: lv_value_0_0= ruleBooleanValue
            {
             
            	        newCompositeNode(grammarAccess.getBOOLEAN_LITERALAccess().getValueBooleanValueEnumRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleBooleanValue_in_ruleBOOLEAN_LITERAL490);
            lv_value_0_0=ruleBooleanValue();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getBOOLEAN_LITERALRule());
            	        }
                   		set(
                   			current, 
                   			"value",
                    		lv_value_0_0, 
                    		"BooleanValue");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBOOLEAN_LITERAL"


    // $ANTLR start "entryRuleNUMBER_LITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:266:1: entryRuleNUMBER_LITERAL returns [EObject current=null] : iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF ;
    public final EObject entryRuleNUMBER_LITERAL() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNUMBER_LITERAL = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:267:2: (iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:268:2: iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF
            {
             newCompositeNode(grammarAccess.getNUMBER_LITERALRule()); 
            pushFollow(FOLLOW_ruleNUMBER_LITERAL_in_entryRuleNUMBER_LITERAL525);
            iv_ruleNUMBER_LITERAL=ruleNUMBER_LITERAL();

            state._fsp--;

             current =iv_ruleNUMBER_LITERAL; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNUMBER_LITERAL535); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNUMBER_LITERAL"


    // $ANTLR start "ruleNUMBER_LITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:275:1: ruleNUMBER_LITERAL returns [EObject current=null] : (this_INTEGER_LITERAL_0= ruleINTEGER_LITERAL | this_UNLIMITED_LITERAL_1= ruleUNLIMITED_LITERAL ) ;
    public final EObject ruleNUMBER_LITERAL() throws RecognitionException {
        EObject current = null;

        EObject this_INTEGER_LITERAL_0 = null;

        EObject this_UNLIMITED_LITERAL_1 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:278:28: ( (this_INTEGER_LITERAL_0= ruleINTEGER_LITERAL | this_UNLIMITED_LITERAL_1= ruleUNLIMITED_LITERAL ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:279:1: (this_INTEGER_LITERAL_0= ruleINTEGER_LITERAL | this_UNLIMITED_LITERAL_1= ruleUNLIMITED_LITERAL )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:279:1: (this_INTEGER_LITERAL_0= ruleINTEGER_LITERAL | this_UNLIMITED_LITERAL_1= ruleUNLIMITED_LITERAL )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_INTEGERVALUE) ) {
                alt5=1;
            }
            else if ( (LA5_0==16) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:280:5: this_INTEGER_LITERAL_0= ruleINTEGER_LITERAL
                    {
                     
                            newCompositeNode(grammarAccess.getNUMBER_LITERALAccess().getINTEGER_LITERALParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleINTEGER_LITERAL_in_ruleNUMBER_LITERAL582);
                    this_INTEGER_LITERAL_0=ruleINTEGER_LITERAL();

                    state._fsp--;

                     
                            current = this_INTEGER_LITERAL_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:290:5: this_UNLIMITED_LITERAL_1= ruleUNLIMITED_LITERAL
                    {
                     
                            newCompositeNode(grammarAccess.getNUMBER_LITERALAccess().getUNLIMITED_LITERALParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleUNLIMITED_LITERAL_in_ruleNUMBER_LITERAL609);
                    this_UNLIMITED_LITERAL_1=ruleUNLIMITED_LITERAL();

                    state._fsp--;

                     
                            current = this_UNLIMITED_LITERAL_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNUMBER_LITERAL"


    // $ANTLR start "entryRuleINTEGER_LITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:306:1: entryRuleINTEGER_LITERAL returns [EObject current=null] : iv_ruleINTEGER_LITERAL= ruleINTEGER_LITERAL EOF ;
    public final EObject entryRuleINTEGER_LITERAL() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleINTEGER_LITERAL = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:307:2: (iv_ruleINTEGER_LITERAL= ruleINTEGER_LITERAL EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:308:2: iv_ruleINTEGER_LITERAL= ruleINTEGER_LITERAL EOF
            {
             newCompositeNode(grammarAccess.getINTEGER_LITERALRule()); 
            pushFollow(FOLLOW_ruleINTEGER_LITERAL_in_entryRuleINTEGER_LITERAL644);
            iv_ruleINTEGER_LITERAL=ruleINTEGER_LITERAL();

            state._fsp--;

             current =iv_ruleINTEGER_LITERAL; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleINTEGER_LITERAL654); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleINTEGER_LITERAL"


    // $ANTLR start "ruleINTEGER_LITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:315:1: ruleINTEGER_LITERAL returns [EObject current=null] : ( (lv_value_0_0= RULE_INTEGERVALUE ) ) ;
    public final EObject ruleINTEGER_LITERAL() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:318:28: ( ( (lv_value_0_0= RULE_INTEGERVALUE ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:319:1: ( (lv_value_0_0= RULE_INTEGERVALUE ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:319:1: ( (lv_value_0_0= RULE_INTEGERVALUE ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:320:1: (lv_value_0_0= RULE_INTEGERVALUE )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:320:1: (lv_value_0_0= RULE_INTEGERVALUE )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:321:3: lv_value_0_0= RULE_INTEGERVALUE
            {
            lv_value_0_0=(Token)match(input,RULE_INTEGERVALUE,FOLLOW_RULE_INTEGERVALUE_in_ruleINTEGER_LITERAL695); 

            			newLeafNode(lv_value_0_0, grammarAccess.getINTEGER_LITERALAccess().getValueIntegerValueTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getINTEGER_LITERALRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_0_0, 
                    		"IntegerValue");
            	    

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleINTEGER_LITERAL"


    // $ANTLR start "entryRuleUNLIMITED_LITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:345:1: entryRuleUNLIMITED_LITERAL returns [EObject current=null] : iv_ruleUNLIMITED_LITERAL= ruleUNLIMITED_LITERAL EOF ;
    public final EObject entryRuleUNLIMITED_LITERAL() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUNLIMITED_LITERAL = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:346:2: (iv_ruleUNLIMITED_LITERAL= ruleUNLIMITED_LITERAL EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:347:2: iv_ruleUNLIMITED_LITERAL= ruleUNLIMITED_LITERAL EOF
            {
             newCompositeNode(grammarAccess.getUNLIMITED_LITERALRule()); 
            pushFollow(FOLLOW_ruleUNLIMITED_LITERAL_in_entryRuleUNLIMITED_LITERAL735);
            iv_ruleUNLIMITED_LITERAL=ruleUNLIMITED_LITERAL();

            state._fsp--;

             current =iv_ruleUNLIMITED_LITERAL; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUNLIMITED_LITERAL745); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUNLIMITED_LITERAL"


    // $ANTLR start "ruleUNLIMITED_LITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:354:1: ruleUNLIMITED_LITERAL returns [EObject current=null] : ( (lv_value_0_0= '*' ) ) ;
    public final EObject ruleUNLIMITED_LITERAL() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:357:28: ( ( (lv_value_0_0= '*' ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:358:1: ( (lv_value_0_0= '*' ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:358:1: ( (lv_value_0_0= '*' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:359:1: (lv_value_0_0= '*' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:359:1: (lv_value_0_0= '*' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:360:3: lv_value_0_0= '*'
            {
            lv_value_0_0=(Token)match(input,16,FOLLOW_16_in_ruleUNLIMITED_LITERAL787); 

                    newLeafNode(lv_value_0_0, grammarAccess.getUNLIMITED_LITERALAccess().getValueAsteriskKeyword_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUNLIMITED_LITERALRule());
            	        }
                   		setWithLastConsumed(current, "value", lv_value_0_0, "*");
            	    

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUNLIMITED_LITERAL"


    // $ANTLR start "entryRuleSTRING_LITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:381:1: entryRuleSTRING_LITERAL returns [EObject current=null] : iv_ruleSTRING_LITERAL= ruleSTRING_LITERAL EOF ;
    public final EObject entryRuleSTRING_LITERAL() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSTRING_LITERAL = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:382:2: (iv_ruleSTRING_LITERAL= ruleSTRING_LITERAL EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:383:2: iv_ruleSTRING_LITERAL= ruleSTRING_LITERAL EOF
            {
             newCompositeNode(grammarAccess.getSTRING_LITERALRule()); 
            pushFollow(FOLLOW_ruleSTRING_LITERAL_in_entryRuleSTRING_LITERAL835);
            iv_ruleSTRING_LITERAL=ruleSTRING_LITERAL();

            state._fsp--;

             current =iv_ruleSTRING_LITERAL; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSTRING_LITERAL845); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSTRING_LITERAL"


    // $ANTLR start "ruleSTRING_LITERAL"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:390:1: ruleSTRING_LITERAL returns [EObject current=null] : ( (lv_value_0_0= RULE_STRING ) ) ;
    public final EObject ruleSTRING_LITERAL() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:393:28: ( ( (lv_value_0_0= RULE_STRING ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:394:1: ( (lv_value_0_0= RULE_STRING ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:394:1: ( (lv_value_0_0= RULE_STRING ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:395:1: (lv_value_0_0= RULE_STRING )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:395:1: (lv_value_0_0= RULE_STRING )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:396:3: lv_value_0_0= RULE_STRING
            {
            lv_value_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSTRING_LITERAL886); 

            			newLeafNode(lv_value_0_0, grammarAccess.getSTRING_LITERALAccess().getValueSTRINGTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSTRING_LITERALRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_0_0, 
                    		"STRING");
            	    

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSTRING_LITERAL"


    // $ANTLR start "entryRuleNameExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:420:1: entryRuleNameExpression returns [EObject current=null] : iv_ruleNameExpression= ruleNameExpression EOF ;
    public final EObject entryRuleNameExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNameExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:421:2: (iv_ruleNameExpression= ruleNameExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:422:2: iv_ruleNameExpression= ruleNameExpression EOF
            {
             newCompositeNode(grammarAccess.getNameExpressionRule()); 
            pushFollow(FOLLOW_ruleNameExpression_in_entryRuleNameExpression926);
            iv_ruleNameExpression=ruleNameExpression();

            state._fsp--;

             current =iv_ruleNameExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNameExpression936); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNameExpression"


    // $ANTLR start "ruleNameExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:429:1: ruleNameExpression returns [EObject current=null] : ( ( ( ( ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) ) ) ( (lv_path_1_0= ruleQualifiedNamePath ) )? ( (lv_id_2_0= RULE_ID ) ) ) | ( ( (lv_path_3_0= ruleQualifiedNamePath ) )? ( (lv_id_4_0= RULE_ID ) ) ( ( (lv_invocationCompletion_5_0= ruleTuple ) ) | ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) ) | ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) ) )? ) ) ( (lv_suffix_8_0= ruleSuffixExpression ) )? ) ;
    public final EObject ruleNameExpression() throws RecognitionException {
        EObject current = null;

        Token lv_prefixOp_0_1=null;
        Token lv_prefixOp_0_2=null;
        Token lv_id_2_0=null;
        Token lv_id_4_0=null;
        Token lv_postfixOp_7_1=null;
        Token lv_postfixOp_7_2=null;
        EObject lv_path_1_0 = null;

        EObject lv_path_3_0 = null;

        EObject lv_invocationCompletion_5_0 = null;

        EObject lv_sequenceConstructionCompletion_6_0 = null;

        EObject lv_suffix_8_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:432:28: ( ( ( ( ( ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) ) ) ( (lv_path_1_0= ruleQualifiedNamePath ) )? ( (lv_id_2_0= RULE_ID ) ) ) | ( ( (lv_path_3_0= ruleQualifiedNamePath ) )? ( (lv_id_4_0= RULE_ID ) ) ( ( (lv_invocationCompletion_5_0= ruleTuple ) ) | ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) ) | ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) ) )? ) ) ( (lv_suffix_8_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:433:1: ( ( ( ( ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) ) ) ( (lv_path_1_0= ruleQualifiedNamePath ) )? ( (lv_id_2_0= RULE_ID ) ) ) | ( ( (lv_path_3_0= ruleQualifiedNamePath ) )? ( (lv_id_4_0= RULE_ID ) ) ( ( (lv_invocationCompletion_5_0= ruleTuple ) ) | ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) ) | ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) ) )? ) ) ( (lv_suffix_8_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:433:1: ( ( ( ( ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) ) ) ( (lv_path_1_0= ruleQualifiedNamePath ) )? ( (lv_id_2_0= RULE_ID ) ) ) | ( ( (lv_path_3_0= ruleQualifiedNamePath ) )? ( (lv_id_4_0= RULE_ID ) ) ( ( (lv_invocationCompletion_5_0= ruleTuple ) ) | ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) ) | ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) ) )? ) ) ( (lv_suffix_8_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:433:2: ( ( ( ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) ) ) ( (lv_path_1_0= ruleQualifiedNamePath ) )? ( (lv_id_2_0= RULE_ID ) ) ) | ( ( (lv_path_3_0= ruleQualifiedNamePath ) )? ( (lv_id_4_0= RULE_ID ) ) ( ( (lv_invocationCompletion_5_0= ruleTuple ) ) | ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) ) | ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) ) )? ) ) ( (lv_suffix_8_0= ruleSuffixExpression ) )?
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:433:2: ( ( ( ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) ) ) ( (lv_path_1_0= ruleQualifiedNamePath ) )? ( (lv_id_2_0= RULE_ID ) ) ) | ( ( (lv_path_3_0= ruleQualifiedNamePath ) )? ( (lv_id_4_0= RULE_ID ) ) ( ( (lv_invocationCompletion_5_0= ruleTuple ) ) | ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) ) | ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) ) )? ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=17 && LA11_0<=18)) ) {
                alt11=1;
            }
            else if ( (LA11_0==RULE_ID) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:433:3: ( ( ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) ) ) ( (lv_path_1_0= ruleQualifiedNamePath ) )? ( (lv_id_2_0= RULE_ID ) ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:433:3: ( ( ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) ) ) ( (lv_path_1_0= ruleQualifiedNamePath ) )? ( (lv_id_2_0= RULE_ID ) ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:433:4: ( ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) ) ) ( (lv_path_1_0= ruleQualifiedNamePath ) )? ( (lv_id_2_0= RULE_ID ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:433:4: ( ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:434:1: ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:434:1: ( (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:435:1: (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:435:1: (lv_prefixOp_0_1= '++' | lv_prefixOp_0_2= '--' )
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==17) ) {
                        alt6=1;
                    }
                    else if ( (LA6_0==18) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 0, input);

                        throw nvae;
                    }
                    switch (alt6) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:436:3: lv_prefixOp_0_1= '++'
                            {
                            lv_prefixOp_0_1=(Token)match(input,17,FOLLOW_17_in_ruleNameExpression983); 

                                    newLeafNode(lv_prefixOp_0_1, grammarAccess.getNameExpressionAccess().getPrefixOpPlusSignPlusSignKeyword_0_0_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getNameExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "prefixOp", lv_prefixOp_0_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:448:8: lv_prefixOp_0_2= '--'
                            {
                            lv_prefixOp_0_2=(Token)match(input,18,FOLLOW_18_in_ruleNameExpression1012); 

                                    newLeafNode(lv_prefixOp_0_2, grammarAccess.getNameExpressionAccess().getPrefixOpHyphenMinusHyphenMinusKeyword_0_0_0_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getNameExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "prefixOp", lv_prefixOp_0_2, null);
                            	    

                            }
                            break;

                    }


                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:463:2: ( (lv_path_1_0= ruleQualifiedNamePath ) )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==RULE_ID) ) {
                        int LA7_1 = input.LA(2);

                        if ( (LA7_1==20) ) {
                            int LA7_3 = input.LA(3);

                            if ( (LA7_3==RULE_ID) ) {
                                int LA7_5 = input.LA(4);

                                if ( (LA7_5==23) ) {
                                    alt7=1;
                                }
                            }
                        }
                        else if ( (LA7_1==19) ) {
                            alt7=1;
                        }
                    }
                    switch (alt7) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:464:1: (lv_path_1_0= ruleQualifiedNamePath )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:464:1: (lv_path_1_0= ruleQualifiedNamePath )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:465:3: lv_path_1_0= ruleQualifiedNamePath
                            {
                             
                            	        newCompositeNode(grammarAccess.getNameExpressionAccess().getPathQualifiedNamePathParserRuleCall_0_0_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleQualifiedNamePath_in_ruleNameExpression1049);
                            lv_path_1_0=ruleQualifiedNamePath();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getNameExpressionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"path",
                                    		lv_path_1_0, 
                                    		"QualifiedNamePath");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }
                            break;

                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:481:3: ( (lv_id_2_0= RULE_ID ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:482:1: (lv_id_2_0= RULE_ID )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:482:1: (lv_id_2_0= RULE_ID )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:483:3: lv_id_2_0= RULE_ID
                    {
                    lv_id_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNameExpression1067); 

                    			newLeafNode(lv_id_2_0, grammarAccess.getNameExpressionAccess().getIdIDTerminalRuleCall_0_0_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getNameExpressionRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"id",
                            		lv_id_2_0, 
                            		"ID");
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:500:6: ( ( (lv_path_3_0= ruleQualifiedNamePath ) )? ( (lv_id_4_0= RULE_ID ) ) ( ( (lv_invocationCompletion_5_0= ruleTuple ) ) | ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) ) | ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) ) )? )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:500:6: ( ( (lv_path_3_0= ruleQualifiedNamePath ) )? ( (lv_id_4_0= RULE_ID ) ) ( ( (lv_invocationCompletion_5_0= ruleTuple ) ) | ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) ) | ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) ) )? )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:500:7: ( (lv_path_3_0= ruleQualifiedNamePath ) )? ( (lv_id_4_0= RULE_ID ) ) ( ( (lv_invocationCompletion_5_0= ruleTuple ) ) | ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) ) | ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) ) )?
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:500:7: ( (lv_path_3_0= ruleQualifiedNamePath ) )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==RULE_ID) ) {
                        int LA8_1 = input.LA(2);

                        if ( (LA8_1==20) ) {
                            int LA8_3 = input.LA(3);

                            if ( (LA8_3==RULE_ID) ) {
                                int LA8_5 = input.LA(4);

                                if ( (LA8_5==23) ) {
                                    alt8=1;
                                }
                            }
                        }
                        else if ( (LA8_1==19) ) {
                            alt8=1;
                        }
                    }
                    switch (alt8) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:501:1: (lv_path_3_0= ruleQualifiedNamePath )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:501:1: (lv_path_3_0= ruleQualifiedNamePath )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:502:3: lv_path_3_0= ruleQualifiedNamePath
                            {
                             
                            	        newCompositeNode(grammarAccess.getNameExpressionAccess().getPathQualifiedNamePathParserRuleCall_0_1_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleQualifiedNamePath_in_ruleNameExpression1101);
                            lv_path_3_0=ruleQualifiedNamePath();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getNameExpressionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"path",
                                    		lv_path_3_0, 
                                    		"QualifiedNamePath");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }
                            break;

                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:518:3: ( (lv_id_4_0= RULE_ID ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:519:1: (lv_id_4_0= RULE_ID )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:519:1: (lv_id_4_0= RULE_ID )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:520:3: lv_id_4_0= RULE_ID
                    {
                    lv_id_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNameExpression1119); 

                    			newLeafNode(lv_id_4_0, grammarAccess.getNameExpressionAccess().getIdIDTerminalRuleCall_0_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getNameExpressionRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"id",
                            		lv_id_4_0, 
                            		"ID");
                    	    

                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:536:2: ( ( (lv_invocationCompletion_5_0= ruleTuple ) ) | ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) ) | ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) ) )?
                    int alt10=4;
                    switch ( input.LA(1) ) {
                        case 24:
                            {
                            alt10=1;
                            }
                            break;
                        case 50:
                            {
                            int LA10_2 = input.LA(2);

                            if ( ((LA10_2>=RULE_INTEGERVALUE && LA10_2<=RULE_ID)||(LA10_2>=16 && LA10_2<=18)||LA10_2==24||(LA10_2>=42 && LA10_2<=43)||(LA10_2>=46 && LA10_2<=48)||(LA10_2>=56 && LA10_2<=59)||(LA10_2>=87 && LA10_2<=88)) ) {
                                alt10=2;
                            }
                            else if ( (LA10_2==51) ) {
                                int LA10_6 = input.LA(3);

                                if ( (LA10_6==60) ) {
                                    alt10=2;
                                }
                            }
                            }
                            break;
                        case 60:
                            {
                            alt10=2;
                            }
                            break;
                        case 17:
                        case 18:
                            {
                            alt10=3;
                            }
                            break;
                    }

                    switch (alt10) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:536:3: ( (lv_invocationCompletion_5_0= ruleTuple ) )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:536:3: ( (lv_invocationCompletion_5_0= ruleTuple ) )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:537:1: (lv_invocationCompletion_5_0= ruleTuple )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:537:1: (lv_invocationCompletion_5_0= ruleTuple )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:538:3: lv_invocationCompletion_5_0= ruleTuple
                            {
                             
                            	        newCompositeNode(grammarAccess.getNameExpressionAccess().getInvocationCompletionTupleParserRuleCall_0_1_2_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleTuple_in_ruleNameExpression1146);
                            lv_invocationCompletion_5_0=ruleTuple();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getNameExpressionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"invocationCompletion",
                                    		lv_invocationCompletion_5_0, 
                                    		"Tuple");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:555:6: ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:555:6: ( (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion ) )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:556:1: (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:556:1: (lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:557:3: lv_sequenceConstructionCompletion_6_0= ruleSequenceConstructionOrAccessCompletion
                            {
                             
                            	        newCompositeNode(grammarAccess.getNameExpressionAccess().getSequenceConstructionCompletionSequenceConstructionOrAccessCompletionParserRuleCall_0_1_2_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleSequenceConstructionOrAccessCompletion_in_ruleNameExpression1173);
                            lv_sequenceConstructionCompletion_6_0=ruleSequenceConstructionOrAccessCompletion();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getNameExpressionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"sequenceConstructionCompletion",
                                    		lv_sequenceConstructionCompletion_6_0, 
                                    		"SequenceConstructionOrAccessCompletion");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;
                        case 3 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:574:6: ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:574:6: ( ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) ) )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:575:1: ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:575:1: ( (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' ) )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:576:1: (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:576:1: (lv_postfixOp_7_1= '++' | lv_postfixOp_7_2= '--' )
                            int alt9=2;
                            int LA9_0 = input.LA(1);

                            if ( (LA9_0==17) ) {
                                alt9=1;
                            }
                            else if ( (LA9_0==18) ) {
                                alt9=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 9, 0, input);

                                throw nvae;
                            }
                            switch (alt9) {
                                case 1 :
                                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:577:3: lv_postfixOp_7_1= '++'
                                    {
                                    lv_postfixOp_7_1=(Token)match(input,17,FOLLOW_17_in_ruleNameExpression1199); 

                                            newLeafNode(lv_postfixOp_7_1, grammarAccess.getNameExpressionAccess().getPostfixOpPlusSignPlusSignKeyword_0_1_2_2_0_0());
                                        

                                    	        if (current==null) {
                                    	            current = createModelElement(grammarAccess.getNameExpressionRule());
                                    	        }
                                           		setWithLastConsumed(current, "postfixOp", lv_postfixOp_7_1, null);
                                    	    

                                    }
                                    break;
                                case 2 :
                                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:589:8: lv_postfixOp_7_2= '--'
                                    {
                                    lv_postfixOp_7_2=(Token)match(input,18,FOLLOW_18_in_ruleNameExpression1228); 

                                            newLeafNode(lv_postfixOp_7_2, grammarAccess.getNameExpressionAccess().getPostfixOpHyphenMinusHyphenMinusKeyword_0_1_2_2_0_1());
                                        

                                    	        if (current==null) {
                                    	            current = createModelElement(grammarAccess.getNameExpressionRule());
                                    	        }
                                           		setWithLastConsumed(current, "postfixOp", lv_postfixOp_7_2, null);
                                    	    

                                    }
                                    break;

                            }


                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:604:6: ( (lv_suffix_8_0= ruleSuffixExpression ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==49||LA12_0==52) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:605:1: (lv_suffix_8_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:605:1: (lv_suffix_8_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:606:3: lv_suffix_8_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getNameExpressionAccess().getSuffixSuffixExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleNameExpression1269);
                    lv_suffix_8_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getNameExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_8_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNameExpression"


    // $ANTLR start "entryRuleQualifiedNamePath"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:630:1: entryRuleQualifiedNamePath returns [EObject current=null] : iv_ruleQualifiedNamePath= ruleQualifiedNamePath EOF ;
    public final EObject entryRuleQualifiedNamePath() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQualifiedNamePath = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:631:2: (iv_ruleQualifiedNamePath= ruleQualifiedNamePath EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:632:2: iv_ruleQualifiedNamePath= ruleQualifiedNamePath EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNamePathRule()); 
            pushFollow(FOLLOW_ruleQualifiedNamePath_in_entryRuleQualifiedNamePath1306);
            iv_ruleQualifiedNamePath=ruleQualifiedNamePath();

            state._fsp--;

             current =iv_ruleQualifiedNamePath; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedNamePath1316); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedNamePath"


    // $ANTLR start "ruleQualifiedNamePath"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:639:1: ruleQualifiedNamePath returns [EObject current=null] : ( ( (lv_namespace_0_0= ruleUnqualifiedName ) ) otherlv_1= '::' )+ ;
    public final EObject ruleQualifiedNamePath() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_namespace_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:642:28: ( ( ( (lv_namespace_0_0= ruleUnqualifiedName ) ) otherlv_1= '::' )+ )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:643:1: ( ( (lv_namespace_0_0= ruleUnqualifiedName ) ) otherlv_1= '::' )+
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:643:1: ( ( (lv_namespace_0_0= ruleUnqualifiedName ) ) otherlv_1= '::' )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_ID) ) {
                    int LA13_1 = input.LA(2);

                    if ( (LA13_1==20) ) {
                        int LA13_3 = input.LA(3);

                        if ( (LA13_3==RULE_ID) ) {
                            int LA13_5 = input.LA(4);

                            if ( (LA13_5==23) ) {
                                alt13=1;
                            }


                        }


                    }
                    else if ( (LA13_1==19) ) {
                        alt13=1;
                    }


                }


                switch (alt13) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:643:2: ( (lv_namespace_0_0= ruleUnqualifiedName ) ) otherlv_1= '::'
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:643:2: ( (lv_namespace_0_0= ruleUnqualifiedName ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:644:1: (lv_namespace_0_0= ruleUnqualifiedName )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:644:1: (lv_namespace_0_0= ruleUnqualifiedName )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:645:3: lv_namespace_0_0= ruleUnqualifiedName
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getQualifiedNamePathAccess().getNamespaceUnqualifiedNameParserRuleCall_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleUnqualifiedName_in_ruleQualifiedNamePath1362);
            	    lv_namespace_0_0=ruleUnqualifiedName();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getQualifiedNamePathRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"namespace",
            	            		lv_namespace_0_0, 
            	            		"UnqualifiedName");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_1=(Token)match(input,19,FOLLOW_19_in_ruleQualifiedNamePath1374); 

            	        	newLeafNode(otherlv_1, grammarAccess.getQualifiedNamePathAccess().getColonColonKeyword_1());
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedNamePath"


    // $ANTLR start "entryRuleUnqualifiedName"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:673:1: entryRuleUnqualifiedName returns [EObject current=null] : iv_ruleUnqualifiedName= ruleUnqualifiedName EOF ;
    public final EObject entryRuleUnqualifiedName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnqualifiedName = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:674:2: (iv_ruleUnqualifiedName= ruleUnqualifiedName EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:675:2: iv_ruleUnqualifiedName= ruleUnqualifiedName EOF
            {
             newCompositeNode(grammarAccess.getUnqualifiedNameRule()); 
            pushFollow(FOLLOW_ruleUnqualifiedName_in_entryRuleUnqualifiedName1411);
            iv_ruleUnqualifiedName=ruleUnqualifiedName();

            state._fsp--;

             current =iv_ruleUnqualifiedName; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnqualifiedName1421); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnqualifiedName"


    // $ANTLR start "ruleUnqualifiedName"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:682:1: ruleUnqualifiedName returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_templateBinding_1_0= ruleTemplateBinding ) )? ) ;
    public final EObject ruleUnqualifiedName() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_templateBinding_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:685:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_templateBinding_1_0= ruleTemplateBinding ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:686:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_templateBinding_1_0= ruleTemplateBinding ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:686:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_templateBinding_1_0= ruleTemplateBinding ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:686:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_templateBinding_1_0= ruleTemplateBinding ) )?
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:686:2: ( (lv_name_0_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:687:1: (lv_name_0_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:687:1: (lv_name_0_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:688:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleUnqualifiedName1463); 

            			newLeafNode(lv_name_0_0, grammarAccess.getUnqualifiedNameAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUnqualifiedNameRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"ID");
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:704:2: ( (lv_templateBinding_1_0= ruleTemplateBinding ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==20) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:705:1: (lv_templateBinding_1_0= ruleTemplateBinding )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:705:1: (lv_templateBinding_1_0= ruleTemplateBinding )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:706:3: lv_templateBinding_1_0= ruleTemplateBinding
                    {
                     
                    	        newCompositeNode(grammarAccess.getUnqualifiedNameAccess().getTemplateBindingTemplateBindingParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTemplateBinding_in_ruleUnqualifiedName1489);
                    lv_templateBinding_1_0=ruleTemplateBinding();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getUnqualifiedNameRule());
                    	        }
                           		set(
                           			current, 
                           			"templateBinding",
                            		lv_templateBinding_1_0, 
                            		"TemplateBinding");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnqualifiedName"


    // $ANTLR start "entryRuleTemplateBinding"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:730:1: entryRuleTemplateBinding returns [EObject current=null] : iv_ruleTemplateBinding= ruleTemplateBinding EOF ;
    public final EObject entryRuleTemplateBinding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateBinding = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:731:2: (iv_ruleTemplateBinding= ruleTemplateBinding EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:732:2: iv_ruleTemplateBinding= ruleTemplateBinding EOF
            {
             newCompositeNode(grammarAccess.getTemplateBindingRule()); 
            pushFollow(FOLLOW_ruleTemplateBinding_in_entryRuleTemplateBinding1526);
            iv_ruleTemplateBinding=ruleTemplateBinding();

            state._fsp--;

             current =iv_ruleTemplateBinding; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTemplateBinding1536); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTemplateBinding"


    // $ANTLR start "ruleTemplateBinding"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:739:1: ruleTemplateBinding returns [EObject current=null] : (otherlv_0= '<' ( (lv_bindings_1_0= ruleNamedTemplateBinding ) ) (otherlv_2= ',' ( (lv_bindings_3_0= ruleNamedTemplateBinding ) ) )* otherlv_4= '>' ) ;
    public final EObject ruleTemplateBinding() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_bindings_1_0 = null;

        EObject lv_bindings_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:742:28: ( (otherlv_0= '<' ( (lv_bindings_1_0= ruleNamedTemplateBinding ) ) (otherlv_2= ',' ( (lv_bindings_3_0= ruleNamedTemplateBinding ) ) )* otherlv_4= '>' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:743:1: (otherlv_0= '<' ( (lv_bindings_1_0= ruleNamedTemplateBinding ) ) (otherlv_2= ',' ( (lv_bindings_3_0= ruleNamedTemplateBinding ) ) )* otherlv_4= '>' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:743:1: (otherlv_0= '<' ( (lv_bindings_1_0= ruleNamedTemplateBinding ) ) (otherlv_2= ',' ( (lv_bindings_3_0= ruleNamedTemplateBinding ) ) )* otherlv_4= '>' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:743:3: otherlv_0= '<' ( (lv_bindings_1_0= ruleNamedTemplateBinding ) ) (otherlv_2= ',' ( (lv_bindings_3_0= ruleNamedTemplateBinding ) ) )* otherlv_4= '>'
            {
            otherlv_0=(Token)match(input,20,FOLLOW_20_in_ruleTemplateBinding1573); 

                	newLeafNode(otherlv_0, grammarAccess.getTemplateBindingAccess().getLessThanSignKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:747:1: ( (lv_bindings_1_0= ruleNamedTemplateBinding ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:748:1: (lv_bindings_1_0= ruleNamedTemplateBinding )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:748:1: (lv_bindings_1_0= ruleNamedTemplateBinding )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:749:3: lv_bindings_1_0= ruleNamedTemplateBinding
            {
             
            	        newCompositeNode(grammarAccess.getTemplateBindingAccess().getBindingsNamedTemplateBindingParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleNamedTemplateBinding_in_ruleTemplateBinding1594);
            lv_bindings_1_0=ruleNamedTemplateBinding();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTemplateBindingRule());
            	        }
                   		add(
                   			current, 
                   			"bindings",
                    		lv_bindings_1_0, 
                    		"NamedTemplateBinding");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:765:2: (otherlv_2= ',' ( (lv_bindings_3_0= ruleNamedTemplateBinding ) ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==21) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:765:4: otherlv_2= ',' ( (lv_bindings_3_0= ruleNamedTemplateBinding ) )
            	    {
            	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleTemplateBinding1607); 

            	        	newLeafNode(otherlv_2, grammarAccess.getTemplateBindingAccess().getCommaKeyword_2_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:769:1: ( (lv_bindings_3_0= ruleNamedTemplateBinding ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:770:1: (lv_bindings_3_0= ruleNamedTemplateBinding )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:770:1: (lv_bindings_3_0= ruleNamedTemplateBinding )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:771:3: lv_bindings_3_0= ruleNamedTemplateBinding
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTemplateBindingAccess().getBindingsNamedTemplateBindingParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleNamedTemplateBinding_in_ruleTemplateBinding1628);
            	    lv_bindings_3_0=ruleNamedTemplateBinding();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTemplateBindingRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"bindings",
            	            		lv_bindings_3_0, 
            	            		"NamedTemplateBinding");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            otherlv_4=(Token)match(input,22,FOLLOW_22_in_ruleTemplateBinding1642); 

                	newLeafNode(otherlv_4, grammarAccess.getTemplateBindingAccess().getGreaterThanSignKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTemplateBinding"


    // $ANTLR start "entryRuleNamedTemplateBinding"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:799:1: entryRuleNamedTemplateBinding returns [EObject current=null] : iv_ruleNamedTemplateBinding= ruleNamedTemplateBinding EOF ;
    public final EObject entryRuleNamedTemplateBinding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamedTemplateBinding = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:800:2: (iv_ruleNamedTemplateBinding= ruleNamedTemplateBinding EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:801:2: iv_ruleNamedTemplateBinding= ruleNamedTemplateBinding EOF
            {
             newCompositeNode(grammarAccess.getNamedTemplateBindingRule()); 
            pushFollow(FOLLOW_ruleNamedTemplateBinding_in_entryRuleNamedTemplateBinding1678);
            iv_ruleNamedTemplateBinding=ruleNamedTemplateBinding();

            state._fsp--;

             current =iv_ruleNamedTemplateBinding; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamedTemplateBinding1688); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNamedTemplateBinding"


    // $ANTLR start "ruleNamedTemplateBinding"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:808:1: ruleNamedTemplateBinding returns [EObject current=null] : ( ( (lv_formal_0_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_actual_2_0= ruleQualifiedNameWithBinding ) ) ) ;
    public final EObject ruleNamedTemplateBinding() throws RecognitionException {
        EObject current = null;

        Token lv_formal_0_0=null;
        Token otherlv_1=null;
        EObject lv_actual_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:811:28: ( ( ( (lv_formal_0_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_actual_2_0= ruleQualifiedNameWithBinding ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:812:1: ( ( (lv_formal_0_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_actual_2_0= ruleQualifiedNameWithBinding ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:812:1: ( ( (lv_formal_0_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_actual_2_0= ruleQualifiedNameWithBinding ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:812:2: ( (lv_formal_0_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_actual_2_0= ruleQualifiedNameWithBinding ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:812:2: ( (lv_formal_0_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:813:1: (lv_formal_0_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:813:1: (lv_formal_0_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:814:3: lv_formal_0_0= RULE_ID
            {
            lv_formal_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNamedTemplateBinding1730); 

            			newLeafNode(lv_formal_0_0, grammarAccess.getNamedTemplateBindingAccess().getFormalIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNamedTemplateBindingRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"formal",
                    		lv_formal_0_0, 
                    		"ID");
            	    

            }


            }

            otherlv_1=(Token)match(input,23,FOLLOW_23_in_ruleNamedTemplateBinding1747); 

                	newLeafNode(otherlv_1, grammarAccess.getNamedTemplateBindingAccess().getEqualsSignGreaterThanSignKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:834:1: ( (lv_actual_2_0= ruleQualifiedNameWithBinding ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:835:1: (lv_actual_2_0= ruleQualifiedNameWithBinding )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:835:1: (lv_actual_2_0= ruleQualifiedNameWithBinding )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:836:3: lv_actual_2_0= ruleQualifiedNameWithBinding
            {
             
            	        newCompositeNode(grammarAccess.getNamedTemplateBindingAccess().getActualQualifiedNameWithBindingParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_ruleNamedTemplateBinding1768);
            lv_actual_2_0=ruleQualifiedNameWithBinding();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getNamedTemplateBindingRule());
            	        }
                   		set(
                   			current, 
                   			"actual",
                    		lv_actual_2_0, 
                    		"QualifiedNameWithBinding");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNamedTemplateBinding"


    // $ANTLR start "entryRuleQualifiedNameWithBinding"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:860:1: entryRuleQualifiedNameWithBinding returns [EObject current=null] : iv_ruleQualifiedNameWithBinding= ruleQualifiedNameWithBinding EOF ;
    public final EObject entryRuleQualifiedNameWithBinding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQualifiedNameWithBinding = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:861:2: (iv_ruleQualifiedNameWithBinding= ruleQualifiedNameWithBinding EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:862:2: iv_ruleQualifiedNameWithBinding= ruleQualifiedNameWithBinding EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameWithBindingRule()); 
            pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_entryRuleQualifiedNameWithBinding1804);
            iv_ruleQualifiedNameWithBinding=ruleQualifiedNameWithBinding();

            state._fsp--;

             current =iv_ruleQualifiedNameWithBinding; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedNameWithBinding1814); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedNameWithBinding"


    // $ANTLR start "ruleQualifiedNameWithBinding"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:869:1: ruleQualifiedNameWithBinding returns [EObject current=null] : ( ( (lv_id_0_0= RULE_ID ) ) ( (lv_binding_1_0= ruleTemplateBinding ) )? (otherlv_2= '::' ( (lv_remaining_3_0= ruleQualifiedNameWithBinding ) ) )? ) ;
    public final EObject ruleQualifiedNameWithBinding() throws RecognitionException {
        EObject current = null;

        Token lv_id_0_0=null;
        Token otherlv_2=null;
        EObject lv_binding_1_0 = null;

        EObject lv_remaining_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:872:28: ( ( ( (lv_id_0_0= RULE_ID ) ) ( (lv_binding_1_0= ruleTemplateBinding ) )? (otherlv_2= '::' ( (lv_remaining_3_0= ruleQualifiedNameWithBinding ) ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:873:1: ( ( (lv_id_0_0= RULE_ID ) ) ( (lv_binding_1_0= ruleTemplateBinding ) )? (otherlv_2= '::' ( (lv_remaining_3_0= ruleQualifiedNameWithBinding ) ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:873:1: ( ( (lv_id_0_0= RULE_ID ) ) ( (lv_binding_1_0= ruleTemplateBinding ) )? (otherlv_2= '::' ( (lv_remaining_3_0= ruleQualifiedNameWithBinding ) ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:873:2: ( (lv_id_0_0= RULE_ID ) ) ( (lv_binding_1_0= ruleTemplateBinding ) )? (otherlv_2= '::' ( (lv_remaining_3_0= ruleQualifiedNameWithBinding ) ) )?
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:873:2: ( (lv_id_0_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:874:1: (lv_id_0_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:874:1: (lv_id_0_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:875:3: lv_id_0_0= RULE_ID
            {
            lv_id_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedNameWithBinding1856); 

            			newLeafNode(lv_id_0_0, grammarAccess.getQualifiedNameWithBindingAccess().getIdIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getQualifiedNameWithBindingRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"id",
                    		lv_id_0_0, 
                    		"ID");
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:891:2: ( (lv_binding_1_0= ruleTemplateBinding ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==20) ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1==RULE_ID) ) {
                    int LA16_3 = input.LA(3);

                    if ( (LA16_3==23) ) {
                        alt16=1;
                    }
                }
            }
            switch (alt16) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:892:1: (lv_binding_1_0= ruleTemplateBinding )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:892:1: (lv_binding_1_0= ruleTemplateBinding )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:893:3: lv_binding_1_0= ruleTemplateBinding
                    {
                     
                    	        newCompositeNode(grammarAccess.getQualifiedNameWithBindingAccess().getBindingTemplateBindingParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTemplateBinding_in_ruleQualifiedNameWithBinding1882);
                    lv_binding_1_0=ruleTemplateBinding();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getQualifiedNameWithBindingRule());
                    	        }
                           		set(
                           			current, 
                           			"binding",
                            		lv_binding_1_0, 
                            		"TemplateBinding");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:909:3: (otherlv_2= '::' ( (lv_remaining_3_0= ruleQualifiedNameWithBinding ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==19) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:909:5: otherlv_2= '::' ( (lv_remaining_3_0= ruleQualifiedNameWithBinding ) )
                    {
                    otherlv_2=(Token)match(input,19,FOLLOW_19_in_ruleQualifiedNameWithBinding1896); 

                        	newLeafNode(otherlv_2, grammarAccess.getQualifiedNameWithBindingAccess().getColonColonKeyword_2_0());
                        
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:913:1: ( (lv_remaining_3_0= ruleQualifiedNameWithBinding ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:914:1: (lv_remaining_3_0= ruleQualifiedNameWithBinding )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:914:1: (lv_remaining_3_0= ruleQualifiedNameWithBinding )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:915:3: lv_remaining_3_0= ruleQualifiedNameWithBinding
                    {
                     
                    	        newCompositeNode(grammarAccess.getQualifiedNameWithBindingAccess().getRemainingQualifiedNameWithBindingParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_ruleQualifiedNameWithBinding1917);
                    lv_remaining_3_0=ruleQualifiedNameWithBinding();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getQualifiedNameWithBindingRule());
                    	        }
                           		set(
                           			current, 
                           			"remaining",
                            		lv_remaining_3_0, 
                            		"QualifiedNameWithBinding");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedNameWithBinding"


    // $ANTLR start "entryRuleTuple"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:939:1: entryRuleTuple returns [EObject current=null] : iv_ruleTuple= ruleTuple EOF ;
    public final EObject entryRuleTuple() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTuple = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:940:2: (iv_ruleTuple= ruleTuple EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:941:2: iv_ruleTuple= ruleTuple EOF
            {
             newCompositeNode(grammarAccess.getTupleRule()); 
            pushFollow(FOLLOW_ruleTuple_in_entryRuleTuple1955);
            iv_ruleTuple=ruleTuple();

            state._fsp--;

             current =iv_ruleTuple; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTuple1965); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTuple"


    // $ANTLR start "ruleTuple"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:948:1: ruleTuple returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_tupleElements_2_0= ruleTupleElement ) ) (otherlv_3= ',' ( (lv_tupleElements_4_0= ruleTupleElement ) ) )* )? otherlv_5= ')' ) ;
    public final EObject ruleTuple() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_tupleElements_2_0 = null;

        EObject lv_tupleElements_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:951:28: ( ( () otherlv_1= '(' ( ( (lv_tupleElements_2_0= ruleTupleElement ) ) (otherlv_3= ',' ( (lv_tupleElements_4_0= ruleTupleElement ) ) )* )? otherlv_5= ')' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:952:1: ( () otherlv_1= '(' ( ( (lv_tupleElements_2_0= ruleTupleElement ) ) (otherlv_3= ',' ( (lv_tupleElements_4_0= ruleTupleElement ) ) )* )? otherlv_5= ')' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:952:1: ( () otherlv_1= '(' ( ( (lv_tupleElements_2_0= ruleTupleElement ) ) (otherlv_3= ',' ( (lv_tupleElements_4_0= ruleTupleElement ) ) )* )? otherlv_5= ')' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:952:2: () otherlv_1= '(' ( ( (lv_tupleElements_2_0= ruleTupleElement ) ) (otherlv_3= ',' ( (lv_tupleElements_4_0= ruleTupleElement ) ) )* )? otherlv_5= ')'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:952:2: ()
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:953:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getTupleAccess().getTupleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleTuple2011); 

                	newLeafNode(otherlv_1, grammarAccess.getTupleAccess().getLeftParenthesisKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:962:1: ( ( (lv_tupleElements_2_0= ruleTupleElement ) ) (otherlv_3= ',' ( (lv_tupleElements_4_0= ruleTupleElement ) ) )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=RULE_INTEGERVALUE && LA19_0<=RULE_ID)||(LA19_0>=16 && LA19_0<=18)||LA19_0==24||(LA19_0>=42 && LA19_0<=43)||(LA19_0>=46 && LA19_0<=48)||(LA19_0>=56 && LA19_0<=59)||(LA19_0>=87 && LA19_0<=88)) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:962:2: ( (lv_tupleElements_2_0= ruleTupleElement ) ) (otherlv_3= ',' ( (lv_tupleElements_4_0= ruleTupleElement ) ) )*
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:962:2: ( (lv_tupleElements_2_0= ruleTupleElement ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:963:1: (lv_tupleElements_2_0= ruleTupleElement )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:963:1: (lv_tupleElements_2_0= ruleTupleElement )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:964:3: lv_tupleElements_2_0= ruleTupleElement
                    {
                     
                    	        newCompositeNode(grammarAccess.getTupleAccess().getTupleElementsTupleElementParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTupleElement_in_ruleTuple2033);
                    lv_tupleElements_2_0=ruleTupleElement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTupleRule());
                    	        }
                           		add(
                           			current, 
                           			"tupleElements",
                            		lv_tupleElements_2_0, 
                            		"TupleElement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:980:2: (otherlv_3= ',' ( (lv_tupleElements_4_0= ruleTupleElement ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==21) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:980:4: otherlv_3= ',' ( (lv_tupleElements_4_0= ruleTupleElement ) )
                    	    {
                    	    otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleTuple2046); 

                    	        	newLeafNode(otherlv_3, grammarAccess.getTupleAccess().getCommaKeyword_2_1_0());
                    	        
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:984:1: ( (lv_tupleElements_4_0= ruleTupleElement ) )
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:985:1: (lv_tupleElements_4_0= ruleTupleElement )
                    	    {
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:985:1: (lv_tupleElements_4_0= ruleTupleElement )
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:986:3: lv_tupleElements_4_0= ruleTupleElement
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getTupleAccess().getTupleElementsTupleElementParserRuleCall_2_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleTupleElement_in_ruleTuple2067);
                    	    lv_tupleElements_4_0=ruleTupleElement();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getTupleRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"tupleElements",
                    	            		lv_tupleElements_4_0, 
                    	            		"TupleElement");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleTuple2083); 

                	newLeafNode(otherlv_5, grammarAccess.getTupleAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTuple"


    // $ANTLR start "entryRuleTupleElement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1014:1: entryRuleTupleElement returns [EObject current=null] : iv_ruleTupleElement= ruleTupleElement EOF ;
    public final EObject entryRuleTupleElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleElement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1015:2: (iv_ruleTupleElement= ruleTupleElement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1016:2: iv_ruleTupleElement= ruleTupleElement EOF
            {
             newCompositeNode(grammarAccess.getTupleElementRule()); 
            pushFollow(FOLLOW_ruleTupleElement_in_entryRuleTupleElement2119);
            iv_ruleTupleElement=ruleTupleElement();

            state._fsp--;

             current =iv_ruleTupleElement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTupleElement2129); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTupleElement"


    // $ANTLR start "ruleTupleElement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1023:1: ruleTupleElement returns [EObject current=null] : ( (lv_argument_0_0= ruleExpression ) ) ;
    public final EObject ruleTupleElement() throws RecognitionException {
        EObject current = null;

        EObject lv_argument_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1026:28: ( ( (lv_argument_0_0= ruleExpression ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1027:1: ( (lv_argument_0_0= ruleExpression ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1027:1: ( (lv_argument_0_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1028:1: (lv_argument_0_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1028:1: (lv_argument_0_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1029:3: lv_argument_0_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getTupleElementAccess().getArgumentExpressionParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleTupleElement2174);
            lv_argument_0_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTupleElementRule());
            	        }
                   		set(
                   			current, 
                   			"argument",
                    		lv_argument_0_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTupleElement"


    // $ANTLR start "entryRuleExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1053:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1054:2: (iv_ruleExpression= ruleExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1055:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression2209);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression2219); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1062:1: ruleExpression returns [EObject current=null] : this_ConditionalTestExpression_0= ruleConditionalTestExpression ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ConditionalTestExpression_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1065:28: (this_ConditionalTestExpression_0= ruleConditionalTestExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1067:5: this_ConditionalTestExpression_0= ruleConditionalTestExpression
            {
             
                    newCompositeNode(grammarAccess.getExpressionAccess().getConditionalTestExpressionParserRuleCall()); 
                
            pushFollow(FOLLOW_ruleConditionalTestExpression_in_ruleExpression2265);
            this_ConditionalTestExpression_0=ruleConditionalTestExpression();

            state._fsp--;

             
                    current = this_ConditionalTestExpression_0; 
                    afterParserOrEnumRuleCall();
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleConditionalTestExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1083:1: entryRuleConditionalTestExpression returns [EObject current=null] : iv_ruleConditionalTestExpression= ruleConditionalTestExpression EOF ;
    public final EObject entryRuleConditionalTestExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalTestExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1084:2: (iv_ruleConditionalTestExpression= ruleConditionalTestExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1085:2: iv_ruleConditionalTestExpression= ruleConditionalTestExpression EOF
            {
             newCompositeNode(grammarAccess.getConditionalTestExpressionRule()); 
            pushFollow(FOLLOW_ruleConditionalTestExpression_in_entryRuleConditionalTestExpression2299);
            iv_ruleConditionalTestExpression=ruleConditionalTestExpression();

            state._fsp--;

             current =iv_ruleConditionalTestExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConditionalTestExpression2309); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConditionalTestExpression"


    // $ANTLR start "ruleConditionalTestExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1092:1: ruleConditionalTestExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleConditionalOrExpression ) ) (otherlv_1= '?' ( (lv_whenTrue_2_0= ruleConditionalTestExpression ) ) otherlv_3= ':' ( (lv_whenFalse_4_0= ruleConditionalTestExpression ) ) )? ) ;
    public final EObject ruleConditionalTestExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_exp_0_0 = null;

        EObject lv_whenTrue_2_0 = null;

        EObject lv_whenFalse_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1095:28: ( ( ( (lv_exp_0_0= ruleConditionalOrExpression ) ) (otherlv_1= '?' ( (lv_whenTrue_2_0= ruleConditionalTestExpression ) ) otherlv_3= ':' ( (lv_whenFalse_4_0= ruleConditionalTestExpression ) ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1096:1: ( ( (lv_exp_0_0= ruleConditionalOrExpression ) ) (otherlv_1= '?' ( (lv_whenTrue_2_0= ruleConditionalTestExpression ) ) otherlv_3= ':' ( (lv_whenFalse_4_0= ruleConditionalTestExpression ) ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1096:1: ( ( (lv_exp_0_0= ruleConditionalOrExpression ) ) (otherlv_1= '?' ( (lv_whenTrue_2_0= ruleConditionalTestExpression ) ) otherlv_3= ':' ( (lv_whenFalse_4_0= ruleConditionalTestExpression ) ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1096:2: ( (lv_exp_0_0= ruleConditionalOrExpression ) ) (otherlv_1= '?' ( (lv_whenTrue_2_0= ruleConditionalTestExpression ) ) otherlv_3= ':' ( (lv_whenFalse_4_0= ruleConditionalTestExpression ) ) )?
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1096:2: ( (lv_exp_0_0= ruleConditionalOrExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1097:1: (lv_exp_0_0= ruleConditionalOrExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1097:1: (lv_exp_0_0= ruleConditionalOrExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1098:3: lv_exp_0_0= ruleConditionalOrExpression
            {
             
            	        newCompositeNode(grammarAccess.getConditionalTestExpressionAccess().getExpConditionalOrExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleConditionalOrExpression_in_ruleConditionalTestExpression2355);
            lv_exp_0_0=ruleConditionalOrExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConditionalTestExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"ConditionalOrExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1114:2: (otherlv_1= '?' ( (lv_whenTrue_2_0= ruleConditionalTestExpression ) ) otherlv_3= ':' ( (lv_whenFalse_4_0= ruleConditionalTestExpression ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==26) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1114:4: otherlv_1= '?' ( (lv_whenTrue_2_0= ruleConditionalTestExpression ) ) otherlv_3= ':' ( (lv_whenFalse_4_0= ruleConditionalTestExpression ) )
                    {
                    otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleConditionalTestExpression2368); 

                        	newLeafNode(otherlv_1, grammarAccess.getConditionalTestExpressionAccess().getQuestionMarkKeyword_1_0());
                        
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1118:1: ( (lv_whenTrue_2_0= ruleConditionalTestExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1119:1: (lv_whenTrue_2_0= ruleConditionalTestExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1119:1: (lv_whenTrue_2_0= ruleConditionalTestExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1120:3: lv_whenTrue_2_0= ruleConditionalTestExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getConditionalTestExpressionAccess().getWhenTrueConditionalTestExpressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleConditionalTestExpression_in_ruleConditionalTestExpression2389);
                    lv_whenTrue_2_0=ruleConditionalTestExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getConditionalTestExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"whenTrue",
                            		lv_whenTrue_2_0, 
                            		"ConditionalTestExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_3=(Token)match(input,27,FOLLOW_27_in_ruleConditionalTestExpression2401); 

                        	newLeafNode(otherlv_3, grammarAccess.getConditionalTestExpressionAccess().getColonKeyword_1_2());
                        
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1140:1: ( (lv_whenFalse_4_0= ruleConditionalTestExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1141:1: (lv_whenFalse_4_0= ruleConditionalTestExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1141:1: (lv_whenFalse_4_0= ruleConditionalTestExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1142:3: lv_whenFalse_4_0= ruleConditionalTestExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getConditionalTestExpressionAccess().getWhenFalseConditionalTestExpressionParserRuleCall_1_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleConditionalTestExpression_in_ruleConditionalTestExpression2422);
                    lv_whenFalse_4_0=ruleConditionalTestExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getConditionalTestExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"whenFalse",
                            		lv_whenFalse_4_0, 
                            		"ConditionalTestExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConditionalTestExpression"


    // $ANTLR start "entryRuleConditionalOrExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1166:1: entryRuleConditionalOrExpression returns [EObject current=null] : iv_ruleConditionalOrExpression= ruleConditionalOrExpression EOF ;
    public final EObject entryRuleConditionalOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalOrExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1167:2: (iv_ruleConditionalOrExpression= ruleConditionalOrExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1168:2: iv_ruleConditionalOrExpression= ruleConditionalOrExpression EOF
            {
             newCompositeNode(grammarAccess.getConditionalOrExpressionRule()); 
            pushFollow(FOLLOW_ruleConditionalOrExpression_in_entryRuleConditionalOrExpression2460);
            iv_ruleConditionalOrExpression=ruleConditionalOrExpression();

            state._fsp--;

             current =iv_ruleConditionalOrExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConditionalOrExpression2470); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConditionalOrExpression"


    // $ANTLR start "ruleConditionalOrExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1175:1: ruleConditionalOrExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleConditionalAndExpression ) ) (otherlv_1= '||' ( (lv_exp_2_0= ruleConditionalAndExpression ) ) )* ) ;
    public final EObject ruleConditionalOrExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_exp_0_0 = null;

        EObject lv_exp_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1178:28: ( ( ( (lv_exp_0_0= ruleConditionalAndExpression ) ) (otherlv_1= '||' ( (lv_exp_2_0= ruleConditionalAndExpression ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1179:1: ( ( (lv_exp_0_0= ruleConditionalAndExpression ) ) (otherlv_1= '||' ( (lv_exp_2_0= ruleConditionalAndExpression ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1179:1: ( ( (lv_exp_0_0= ruleConditionalAndExpression ) ) (otherlv_1= '||' ( (lv_exp_2_0= ruleConditionalAndExpression ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1179:2: ( (lv_exp_0_0= ruleConditionalAndExpression ) ) (otherlv_1= '||' ( (lv_exp_2_0= ruleConditionalAndExpression ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1179:2: ( (lv_exp_0_0= ruleConditionalAndExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1180:1: (lv_exp_0_0= ruleConditionalAndExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1180:1: (lv_exp_0_0= ruleConditionalAndExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1181:3: lv_exp_0_0= ruleConditionalAndExpression
            {
             
            	        newCompositeNode(grammarAccess.getConditionalOrExpressionAccess().getExpConditionalAndExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleConditionalAndExpression_in_ruleConditionalOrExpression2516);
            lv_exp_0_0=ruleConditionalAndExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConditionalOrExpressionRule());
            	        }
                   		add(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"ConditionalAndExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1197:2: (otherlv_1= '||' ( (lv_exp_2_0= ruleConditionalAndExpression ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==28) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1197:4: otherlv_1= '||' ( (lv_exp_2_0= ruleConditionalAndExpression ) )
            	    {
            	    otherlv_1=(Token)match(input,28,FOLLOW_28_in_ruleConditionalOrExpression2529); 

            	        	newLeafNode(otherlv_1, grammarAccess.getConditionalOrExpressionAccess().getVerticalLineVerticalLineKeyword_1_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1201:1: ( (lv_exp_2_0= ruleConditionalAndExpression ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1202:1: (lv_exp_2_0= ruleConditionalAndExpression )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1202:1: (lv_exp_2_0= ruleConditionalAndExpression )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1203:3: lv_exp_2_0= ruleConditionalAndExpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getConditionalOrExpressionAccess().getExpConditionalAndExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleConditionalAndExpression_in_ruleConditionalOrExpression2550);
            	    lv_exp_2_0=ruleConditionalAndExpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getConditionalOrExpressionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exp",
            	            		lv_exp_2_0, 
            	            		"ConditionalAndExpression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConditionalOrExpression"


    // $ANTLR start "entryRuleConditionalAndExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1227:1: entryRuleConditionalAndExpression returns [EObject current=null] : iv_ruleConditionalAndExpression= ruleConditionalAndExpression EOF ;
    public final EObject entryRuleConditionalAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalAndExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1228:2: (iv_ruleConditionalAndExpression= ruleConditionalAndExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1229:2: iv_ruleConditionalAndExpression= ruleConditionalAndExpression EOF
            {
             newCompositeNode(grammarAccess.getConditionalAndExpressionRule()); 
            pushFollow(FOLLOW_ruleConditionalAndExpression_in_entryRuleConditionalAndExpression2588);
            iv_ruleConditionalAndExpression=ruleConditionalAndExpression();

            state._fsp--;

             current =iv_ruleConditionalAndExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConditionalAndExpression2598); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConditionalAndExpression"


    // $ANTLR start "ruleConditionalAndExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1236:1: ruleConditionalAndExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleInclusiveOrExpression ) ) (otherlv_1= '&&' ( (lv_exp_2_0= ruleInclusiveOrExpression ) ) )* ) ;
    public final EObject ruleConditionalAndExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_exp_0_0 = null;

        EObject lv_exp_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1239:28: ( ( ( (lv_exp_0_0= ruleInclusiveOrExpression ) ) (otherlv_1= '&&' ( (lv_exp_2_0= ruleInclusiveOrExpression ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1240:1: ( ( (lv_exp_0_0= ruleInclusiveOrExpression ) ) (otherlv_1= '&&' ( (lv_exp_2_0= ruleInclusiveOrExpression ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1240:1: ( ( (lv_exp_0_0= ruleInclusiveOrExpression ) ) (otherlv_1= '&&' ( (lv_exp_2_0= ruleInclusiveOrExpression ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1240:2: ( (lv_exp_0_0= ruleInclusiveOrExpression ) ) (otherlv_1= '&&' ( (lv_exp_2_0= ruleInclusiveOrExpression ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1240:2: ( (lv_exp_0_0= ruleInclusiveOrExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1241:1: (lv_exp_0_0= ruleInclusiveOrExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1241:1: (lv_exp_0_0= ruleInclusiveOrExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1242:3: lv_exp_0_0= ruleInclusiveOrExpression
            {
             
            	        newCompositeNode(grammarAccess.getConditionalAndExpressionAccess().getExpInclusiveOrExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleInclusiveOrExpression_in_ruleConditionalAndExpression2644);
            lv_exp_0_0=ruleInclusiveOrExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConditionalAndExpressionRule());
            	        }
                   		add(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"InclusiveOrExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1258:2: (otherlv_1= '&&' ( (lv_exp_2_0= ruleInclusiveOrExpression ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==29) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1258:4: otherlv_1= '&&' ( (lv_exp_2_0= ruleInclusiveOrExpression ) )
            	    {
            	    otherlv_1=(Token)match(input,29,FOLLOW_29_in_ruleConditionalAndExpression2657); 

            	        	newLeafNode(otherlv_1, grammarAccess.getConditionalAndExpressionAccess().getAmpersandAmpersandKeyword_1_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1262:1: ( (lv_exp_2_0= ruleInclusiveOrExpression ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1263:1: (lv_exp_2_0= ruleInclusiveOrExpression )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1263:1: (lv_exp_2_0= ruleInclusiveOrExpression )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1264:3: lv_exp_2_0= ruleInclusiveOrExpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getConditionalAndExpressionAccess().getExpInclusiveOrExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleInclusiveOrExpression_in_ruleConditionalAndExpression2678);
            	    lv_exp_2_0=ruleInclusiveOrExpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getConditionalAndExpressionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exp",
            	            		lv_exp_2_0, 
            	            		"InclusiveOrExpression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConditionalAndExpression"


    // $ANTLR start "entryRuleInclusiveOrExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1288:1: entryRuleInclusiveOrExpression returns [EObject current=null] : iv_ruleInclusiveOrExpression= ruleInclusiveOrExpression EOF ;
    public final EObject entryRuleInclusiveOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInclusiveOrExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1289:2: (iv_ruleInclusiveOrExpression= ruleInclusiveOrExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1290:2: iv_ruleInclusiveOrExpression= ruleInclusiveOrExpression EOF
            {
             newCompositeNode(grammarAccess.getInclusiveOrExpressionRule()); 
            pushFollow(FOLLOW_ruleInclusiveOrExpression_in_entryRuleInclusiveOrExpression2716);
            iv_ruleInclusiveOrExpression=ruleInclusiveOrExpression();

            state._fsp--;

             current =iv_ruleInclusiveOrExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInclusiveOrExpression2726); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInclusiveOrExpression"


    // $ANTLR start "ruleInclusiveOrExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1297:1: ruleInclusiveOrExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleExclusiveOrExpression ) ) (otherlv_1= '|' ( (lv_exp_2_0= ruleExclusiveOrExpression ) ) )* ) ;
    public final EObject ruleInclusiveOrExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_exp_0_0 = null;

        EObject lv_exp_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1300:28: ( ( ( (lv_exp_0_0= ruleExclusiveOrExpression ) ) (otherlv_1= '|' ( (lv_exp_2_0= ruleExclusiveOrExpression ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1301:1: ( ( (lv_exp_0_0= ruleExclusiveOrExpression ) ) (otherlv_1= '|' ( (lv_exp_2_0= ruleExclusiveOrExpression ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1301:1: ( ( (lv_exp_0_0= ruleExclusiveOrExpression ) ) (otherlv_1= '|' ( (lv_exp_2_0= ruleExclusiveOrExpression ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1301:2: ( (lv_exp_0_0= ruleExclusiveOrExpression ) ) (otherlv_1= '|' ( (lv_exp_2_0= ruleExclusiveOrExpression ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1301:2: ( (lv_exp_0_0= ruleExclusiveOrExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1302:1: (lv_exp_0_0= ruleExclusiveOrExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1302:1: (lv_exp_0_0= ruleExclusiveOrExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1303:3: lv_exp_0_0= ruleExclusiveOrExpression
            {
             
            	        newCompositeNode(grammarAccess.getInclusiveOrExpressionAccess().getExpExclusiveOrExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleExclusiveOrExpression_in_ruleInclusiveOrExpression2772);
            lv_exp_0_0=ruleExclusiveOrExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getInclusiveOrExpressionRule());
            	        }
                   		add(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"ExclusiveOrExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1319:2: (otherlv_1= '|' ( (lv_exp_2_0= ruleExclusiveOrExpression ) ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==30) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1319:4: otherlv_1= '|' ( (lv_exp_2_0= ruleExclusiveOrExpression ) )
            	    {
            	    otherlv_1=(Token)match(input,30,FOLLOW_30_in_ruleInclusiveOrExpression2785); 

            	        	newLeafNode(otherlv_1, grammarAccess.getInclusiveOrExpressionAccess().getVerticalLineKeyword_1_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1323:1: ( (lv_exp_2_0= ruleExclusiveOrExpression ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1324:1: (lv_exp_2_0= ruleExclusiveOrExpression )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1324:1: (lv_exp_2_0= ruleExclusiveOrExpression )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1325:3: lv_exp_2_0= ruleExclusiveOrExpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getInclusiveOrExpressionAccess().getExpExclusiveOrExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleExclusiveOrExpression_in_ruleInclusiveOrExpression2806);
            	    lv_exp_2_0=ruleExclusiveOrExpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getInclusiveOrExpressionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exp",
            	            		lv_exp_2_0, 
            	            		"ExclusiveOrExpression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInclusiveOrExpression"


    // $ANTLR start "entryRuleExclusiveOrExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1349:1: entryRuleExclusiveOrExpression returns [EObject current=null] : iv_ruleExclusiveOrExpression= ruleExclusiveOrExpression EOF ;
    public final EObject entryRuleExclusiveOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExclusiveOrExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1350:2: (iv_ruleExclusiveOrExpression= ruleExclusiveOrExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1351:2: iv_ruleExclusiveOrExpression= ruleExclusiveOrExpression EOF
            {
             newCompositeNode(grammarAccess.getExclusiveOrExpressionRule()); 
            pushFollow(FOLLOW_ruleExclusiveOrExpression_in_entryRuleExclusiveOrExpression2844);
            iv_ruleExclusiveOrExpression=ruleExclusiveOrExpression();

            state._fsp--;

             current =iv_ruleExclusiveOrExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExclusiveOrExpression2854); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExclusiveOrExpression"


    // $ANTLR start "ruleExclusiveOrExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1358:1: ruleExclusiveOrExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleAndExpression ) ) (otherlv_1= '^' ( (lv_exp_2_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleExclusiveOrExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_exp_0_0 = null;

        EObject lv_exp_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1361:28: ( ( ( (lv_exp_0_0= ruleAndExpression ) ) (otherlv_1= '^' ( (lv_exp_2_0= ruleAndExpression ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1362:1: ( ( (lv_exp_0_0= ruleAndExpression ) ) (otherlv_1= '^' ( (lv_exp_2_0= ruleAndExpression ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1362:1: ( ( (lv_exp_0_0= ruleAndExpression ) ) (otherlv_1= '^' ( (lv_exp_2_0= ruleAndExpression ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1362:2: ( (lv_exp_0_0= ruleAndExpression ) ) (otherlv_1= '^' ( (lv_exp_2_0= ruleAndExpression ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1362:2: ( (lv_exp_0_0= ruleAndExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1363:1: (lv_exp_0_0= ruleAndExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1363:1: (lv_exp_0_0= ruleAndExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1364:3: lv_exp_0_0= ruleAndExpression
            {
             
            	        newCompositeNode(grammarAccess.getExclusiveOrExpressionAccess().getExpAndExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleAndExpression_in_ruleExclusiveOrExpression2900);
            lv_exp_0_0=ruleAndExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExclusiveOrExpressionRule());
            	        }
                   		add(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"AndExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1380:2: (otherlv_1= '^' ( (lv_exp_2_0= ruleAndExpression ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==31) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1380:4: otherlv_1= '^' ( (lv_exp_2_0= ruleAndExpression ) )
            	    {
            	    otherlv_1=(Token)match(input,31,FOLLOW_31_in_ruleExclusiveOrExpression2913); 

            	        	newLeafNode(otherlv_1, grammarAccess.getExclusiveOrExpressionAccess().getCircumflexAccentKeyword_1_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1384:1: ( (lv_exp_2_0= ruleAndExpression ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1385:1: (lv_exp_2_0= ruleAndExpression )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1385:1: (lv_exp_2_0= ruleAndExpression )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1386:3: lv_exp_2_0= ruleAndExpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getExclusiveOrExpressionAccess().getExpAndExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAndExpression_in_ruleExclusiveOrExpression2934);
            	    lv_exp_2_0=ruleAndExpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getExclusiveOrExpressionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exp",
            	            		lv_exp_2_0, 
            	            		"AndExpression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExclusiveOrExpression"


    // $ANTLR start "entryRuleAndExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1410:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1411:2: (iv_ruleAndExpression= ruleAndExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1412:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
             newCompositeNode(grammarAccess.getAndExpressionRule()); 
            pushFollow(FOLLOW_ruleAndExpression_in_entryRuleAndExpression2972);
            iv_ruleAndExpression=ruleAndExpression();

            state._fsp--;

             current =iv_ruleAndExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpression2982); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAndExpression"


    // $ANTLR start "ruleAndExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1419:1: ruleAndExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleEqualityExpression ) ) (otherlv_1= '&' ( (lv_exp_2_0= ruleEqualityExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_exp_0_0 = null;

        EObject lv_exp_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1422:28: ( ( ( (lv_exp_0_0= ruleEqualityExpression ) ) (otherlv_1= '&' ( (lv_exp_2_0= ruleEqualityExpression ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1423:1: ( ( (lv_exp_0_0= ruleEqualityExpression ) ) (otherlv_1= '&' ( (lv_exp_2_0= ruleEqualityExpression ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1423:1: ( ( (lv_exp_0_0= ruleEqualityExpression ) ) (otherlv_1= '&' ( (lv_exp_2_0= ruleEqualityExpression ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1423:2: ( (lv_exp_0_0= ruleEqualityExpression ) ) (otherlv_1= '&' ( (lv_exp_2_0= ruleEqualityExpression ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1423:2: ( (lv_exp_0_0= ruleEqualityExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1424:1: (lv_exp_0_0= ruleEqualityExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1424:1: (lv_exp_0_0= ruleEqualityExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1425:3: lv_exp_0_0= ruleEqualityExpression
            {
             
            	        newCompositeNode(grammarAccess.getAndExpressionAccess().getExpEqualityExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleEqualityExpression_in_ruleAndExpression3028);
            lv_exp_0_0=ruleEqualityExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAndExpressionRule());
            	        }
                   		add(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"EqualityExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1441:2: (otherlv_1= '&' ( (lv_exp_2_0= ruleEqualityExpression ) ) )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==32) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1441:4: otherlv_1= '&' ( (lv_exp_2_0= ruleEqualityExpression ) )
            	    {
            	    otherlv_1=(Token)match(input,32,FOLLOW_32_in_ruleAndExpression3041); 

            	        	newLeafNode(otherlv_1, grammarAccess.getAndExpressionAccess().getAmpersandKeyword_1_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1445:1: ( (lv_exp_2_0= ruleEqualityExpression ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1446:1: (lv_exp_2_0= ruleEqualityExpression )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1446:1: (lv_exp_2_0= ruleEqualityExpression )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1447:3: lv_exp_2_0= ruleEqualityExpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAndExpressionAccess().getExpEqualityExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleEqualityExpression_in_ruleAndExpression3062);
            	    lv_exp_2_0=ruleEqualityExpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getAndExpressionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exp",
            	            		lv_exp_2_0, 
            	            		"EqualityExpression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAndExpression"


    // $ANTLR start "entryRuleEqualityExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1471:1: entryRuleEqualityExpression returns [EObject current=null] : iv_ruleEqualityExpression= ruleEqualityExpression EOF ;
    public final EObject entryRuleEqualityExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEqualityExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1472:2: (iv_ruleEqualityExpression= ruleEqualityExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1473:2: iv_ruleEqualityExpression= ruleEqualityExpression EOF
            {
             newCompositeNode(grammarAccess.getEqualityExpressionRule()); 
            pushFollow(FOLLOW_ruleEqualityExpression_in_entryRuleEqualityExpression3100);
            iv_ruleEqualityExpression=ruleEqualityExpression();

            state._fsp--;

             current =iv_ruleEqualityExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEqualityExpression3110); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEqualityExpression"


    // $ANTLR start "ruleEqualityExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1480:1: ruleEqualityExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleClassificationExpression ) ) ( ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' ) ) ) ( (lv_exp_2_0= ruleClassificationExpression ) ) )* ) ;
    public final EObject ruleEqualityExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_exp_0_0 = null;

        EObject lv_exp_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1483:28: ( ( ( (lv_exp_0_0= ruleClassificationExpression ) ) ( ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' ) ) ) ( (lv_exp_2_0= ruleClassificationExpression ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1484:1: ( ( (lv_exp_0_0= ruleClassificationExpression ) ) ( ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' ) ) ) ( (lv_exp_2_0= ruleClassificationExpression ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1484:1: ( ( (lv_exp_0_0= ruleClassificationExpression ) ) ( ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' ) ) ) ( (lv_exp_2_0= ruleClassificationExpression ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1484:2: ( (lv_exp_0_0= ruleClassificationExpression ) ) ( ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' ) ) ) ( (lv_exp_2_0= ruleClassificationExpression ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1484:2: ( (lv_exp_0_0= ruleClassificationExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1485:1: (lv_exp_0_0= ruleClassificationExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1485:1: (lv_exp_0_0= ruleClassificationExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1486:3: lv_exp_0_0= ruleClassificationExpression
            {
             
            	        newCompositeNode(grammarAccess.getEqualityExpressionAccess().getExpClassificationExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleClassificationExpression_in_ruleEqualityExpression3156);
            lv_exp_0_0=ruleClassificationExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getEqualityExpressionRule());
            	        }
                   		add(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"ClassificationExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1502:2: ( ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' ) ) ) ( (lv_exp_2_0= ruleClassificationExpression ) ) )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=33 && LA27_0<=34)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1502:3: ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' ) ) ) ( (lv_exp_2_0= ruleClassificationExpression ) )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1502:3: ( ( (lv_op_1_1= '==' | lv_op_1_2= '!=' ) ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1503:1: ( (lv_op_1_1= '==' | lv_op_1_2= '!=' ) )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1503:1: ( (lv_op_1_1= '==' | lv_op_1_2= '!=' ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1504:1: (lv_op_1_1= '==' | lv_op_1_2= '!=' )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1504:1: (lv_op_1_1= '==' | lv_op_1_2= '!=' )
            	    int alt26=2;
            	    int LA26_0 = input.LA(1);

            	    if ( (LA26_0==33) ) {
            	        alt26=1;
            	    }
            	    else if ( (LA26_0==34) ) {
            	        alt26=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 26, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt26) {
            	        case 1 :
            	            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1505:3: lv_op_1_1= '=='
            	            {
            	            lv_op_1_1=(Token)match(input,33,FOLLOW_33_in_ruleEqualityExpression3177); 

            	                    newLeafNode(lv_op_1_1, grammarAccess.getEqualityExpressionAccess().getOpEqualsSignEqualsSignKeyword_1_0_0_0());
            	                

            	            	        if (current==null) {
            	            	            current = createModelElement(grammarAccess.getEqualityExpressionRule());
            	            	        }
            	                   		addWithLastConsumed(current, "op", lv_op_1_1, null);
            	            	    

            	            }
            	            break;
            	        case 2 :
            	            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1517:8: lv_op_1_2= '!='
            	            {
            	            lv_op_1_2=(Token)match(input,34,FOLLOW_34_in_ruleEqualityExpression3206); 

            	                    newLeafNode(lv_op_1_2, grammarAccess.getEqualityExpressionAccess().getOpExclamationMarkEqualsSignKeyword_1_0_0_1());
            	                

            	            	        if (current==null) {
            	            	            current = createModelElement(grammarAccess.getEqualityExpressionRule());
            	            	        }
            	                   		addWithLastConsumed(current, "op", lv_op_1_2, null);
            	            	    

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1532:2: ( (lv_exp_2_0= ruleClassificationExpression ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1533:1: (lv_exp_2_0= ruleClassificationExpression )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1533:1: (lv_exp_2_0= ruleClassificationExpression )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1534:3: lv_exp_2_0= ruleClassificationExpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getEqualityExpressionAccess().getExpClassificationExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleClassificationExpression_in_ruleEqualityExpression3243);
            	    lv_exp_2_0=ruleClassificationExpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getEqualityExpressionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exp",
            	            		lv_exp_2_0, 
            	            		"ClassificationExpression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEqualityExpression"


    // $ANTLR start "entryRuleClassificationExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1558:1: entryRuleClassificationExpression returns [EObject current=null] : iv_ruleClassificationExpression= ruleClassificationExpression EOF ;
    public final EObject entryRuleClassificationExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassificationExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1559:2: (iv_ruleClassificationExpression= ruleClassificationExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1560:2: iv_ruleClassificationExpression= ruleClassificationExpression EOF
            {
             newCompositeNode(grammarAccess.getClassificationExpressionRule()); 
            pushFollow(FOLLOW_ruleClassificationExpression_in_entryRuleClassificationExpression3281);
            iv_ruleClassificationExpression=ruleClassificationExpression();

            state._fsp--;

             current =iv_ruleClassificationExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClassificationExpression3291); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClassificationExpression"


    // $ANTLR start "ruleClassificationExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1567:1: ruleClassificationExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleRelationalExpression ) ) ( ( ( (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' ) ) ) ( (lv_typeName_2_0= ruleNameExpression ) ) )? ) ;
    public final EObject ruleClassificationExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_exp_0_0 = null;

        EObject lv_typeName_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1570:28: ( ( ( (lv_exp_0_0= ruleRelationalExpression ) ) ( ( ( (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' ) ) ) ( (lv_typeName_2_0= ruleNameExpression ) ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1571:1: ( ( (lv_exp_0_0= ruleRelationalExpression ) ) ( ( ( (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' ) ) ) ( (lv_typeName_2_0= ruleNameExpression ) ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1571:1: ( ( (lv_exp_0_0= ruleRelationalExpression ) ) ( ( ( (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' ) ) ) ( (lv_typeName_2_0= ruleNameExpression ) ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1571:2: ( (lv_exp_0_0= ruleRelationalExpression ) ) ( ( ( (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' ) ) ) ( (lv_typeName_2_0= ruleNameExpression ) ) )?
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1571:2: ( (lv_exp_0_0= ruleRelationalExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1572:1: (lv_exp_0_0= ruleRelationalExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1572:1: (lv_exp_0_0= ruleRelationalExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1573:3: lv_exp_0_0= ruleRelationalExpression
            {
             
            	        newCompositeNode(grammarAccess.getClassificationExpressionAccess().getExpRelationalExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleRelationalExpression_in_ruleClassificationExpression3337);
            lv_exp_0_0=ruleRelationalExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getClassificationExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"RelationalExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1589:2: ( ( ( (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' ) ) ) ( (lv_typeName_2_0= ruleNameExpression ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=35 && LA29_0<=36)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1589:3: ( ( (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' ) ) ) ( (lv_typeName_2_0= ruleNameExpression ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1589:3: ( ( (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' ) ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1590:1: ( (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1590:1: ( (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1591:1: (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1591:1: (lv_op_1_1= 'instanceof' | lv_op_1_2= 'hastype' )
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==35) ) {
                        alt28=1;
                    }
                    else if ( (LA28_0==36) ) {
                        alt28=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;
                    }
                    switch (alt28) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1592:3: lv_op_1_1= 'instanceof'
                            {
                            lv_op_1_1=(Token)match(input,35,FOLLOW_35_in_ruleClassificationExpression3358); 

                                    newLeafNode(lv_op_1_1, grammarAccess.getClassificationExpressionAccess().getOpInstanceofKeyword_1_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getClassificationExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1604:8: lv_op_1_2= 'hastype'
                            {
                            lv_op_1_2=(Token)match(input,36,FOLLOW_36_in_ruleClassificationExpression3387); 

                                    newLeafNode(lv_op_1_2, grammarAccess.getClassificationExpressionAccess().getOpHastypeKeyword_1_0_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getClassificationExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_1_2, null);
                            	    

                            }
                            break;

                    }


                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1619:2: ( (lv_typeName_2_0= ruleNameExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1620:1: (lv_typeName_2_0= ruleNameExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1620:1: (lv_typeName_2_0= ruleNameExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1621:3: lv_typeName_2_0= ruleNameExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getClassificationExpressionAccess().getTypeNameNameExpressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleNameExpression_in_ruleClassificationExpression3424);
                    lv_typeName_2_0=ruleNameExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getClassificationExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"typeName",
                            		lv_typeName_2_0, 
                            		"NameExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClassificationExpression"


    // $ANTLR start "entryRuleRelationalExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1645:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1646:2: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1647:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
             newCompositeNode(grammarAccess.getRelationalExpressionRule()); 
            pushFollow(FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression3462);
            iv_ruleRelationalExpression=ruleRelationalExpression();

            state._fsp--;

             current =iv_ruleRelationalExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpression3472); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelationalExpression"


    // $ANTLR start "ruleRelationalExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1654:1: ruleRelationalExpression returns [EObject current=null] : ( ( (lv_left_0_0= ruleShiftExpression ) ) ( ( ( (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' ) ) ) ( (lv_right_2_0= ruleShiftExpression ) ) )? ) ;
    public final EObject ruleRelationalExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        Token lv_op_1_3=null;
        Token lv_op_1_4=null;
        EObject lv_left_0_0 = null;

        EObject lv_right_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1657:28: ( ( ( (lv_left_0_0= ruleShiftExpression ) ) ( ( ( (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' ) ) ) ( (lv_right_2_0= ruleShiftExpression ) ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1658:1: ( ( (lv_left_0_0= ruleShiftExpression ) ) ( ( ( (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' ) ) ) ( (lv_right_2_0= ruleShiftExpression ) ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1658:1: ( ( (lv_left_0_0= ruleShiftExpression ) ) ( ( ( (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' ) ) ) ( (lv_right_2_0= ruleShiftExpression ) ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1658:2: ( (lv_left_0_0= ruleShiftExpression ) ) ( ( ( (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' ) ) ) ( (lv_right_2_0= ruleShiftExpression ) ) )?
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1658:2: ( (lv_left_0_0= ruleShiftExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1659:1: (lv_left_0_0= ruleShiftExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1659:1: (lv_left_0_0= ruleShiftExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1660:3: lv_left_0_0= ruleShiftExpression
            {
             
            	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getLeftShiftExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleShiftExpression_in_ruleRelationalExpression3518);
            lv_left_0_0=ruleShiftExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRelationalExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"left",
                    		lv_left_0_0, 
                    		"ShiftExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1676:2: ( ( ( (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' ) ) ) ( (lv_right_2_0= ruleShiftExpression ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==20||LA31_0==22||(LA31_0>=37 && LA31_0<=38)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1676:3: ( ( (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' ) ) ) ( (lv_right_2_0= ruleShiftExpression ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1676:3: ( ( (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' ) ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1677:1: ( (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1677:1: ( (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1678:1: (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1678:1: (lv_op_1_1= '<' | lv_op_1_2= '>' | lv_op_1_3= '<=' | lv_op_1_4= '>=' )
                    int alt30=4;
                    switch ( input.LA(1) ) {
                    case 20:
                        {
                        alt30=1;
                        }
                        break;
                    case 22:
                        {
                        alt30=2;
                        }
                        break;
                    case 37:
                        {
                        alt30=3;
                        }
                        break;
                    case 38:
                        {
                        alt30=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 30, 0, input);

                        throw nvae;
                    }

                    switch (alt30) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1679:3: lv_op_1_1= '<'
                            {
                            lv_op_1_1=(Token)match(input,20,FOLLOW_20_in_ruleRelationalExpression3539); 

                                    newLeafNode(lv_op_1_1, grammarAccess.getRelationalExpressionAccess().getOpLessThanSignKeyword_1_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getRelationalExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1691:8: lv_op_1_2= '>'
                            {
                            lv_op_1_2=(Token)match(input,22,FOLLOW_22_in_ruleRelationalExpression3568); 

                                    newLeafNode(lv_op_1_2, grammarAccess.getRelationalExpressionAccess().getOpGreaterThanSignKeyword_1_0_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getRelationalExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_1_2, null);
                            	    

                            }
                            break;
                        case 3 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1703:8: lv_op_1_3= '<='
                            {
                            lv_op_1_3=(Token)match(input,37,FOLLOW_37_in_ruleRelationalExpression3597); 

                                    newLeafNode(lv_op_1_3, grammarAccess.getRelationalExpressionAccess().getOpLessThanSignEqualsSignKeyword_1_0_0_2());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getRelationalExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_1_3, null);
                            	    

                            }
                            break;
                        case 4 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1715:8: lv_op_1_4= '>='
                            {
                            lv_op_1_4=(Token)match(input,38,FOLLOW_38_in_ruleRelationalExpression3626); 

                                    newLeafNode(lv_op_1_4, grammarAccess.getRelationalExpressionAccess().getOpGreaterThanSignEqualsSignKeyword_1_0_0_3());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getRelationalExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_1_4, null);
                            	    

                            }
                            break;

                    }


                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1730:2: ( (lv_right_2_0= ruleShiftExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1731:1: (lv_right_2_0= ruleShiftExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1731:1: (lv_right_2_0= ruleShiftExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1732:3: lv_right_2_0= ruleShiftExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightShiftExpressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleShiftExpression_in_ruleRelationalExpression3663);
                    lv_right_2_0=ruleShiftExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getRelationalExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"right",
                            		lv_right_2_0, 
                            		"ShiftExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelationalExpression"


    // $ANTLR start "entryRuleShiftExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1756:1: entryRuleShiftExpression returns [EObject current=null] : iv_ruleShiftExpression= ruleShiftExpression EOF ;
    public final EObject entryRuleShiftExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleShiftExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1757:2: (iv_ruleShiftExpression= ruleShiftExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1758:2: iv_ruleShiftExpression= ruleShiftExpression EOF
            {
             newCompositeNode(grammarAccess.getShiftExpressionRule()); 
            pushFollow(FOLLOW_ruleShiftExpression_in_entryRuleShiftExpression3701);
            iv_ruleShiftExpression=ruleShiftExpression();

            state._fsp--;

             current =iv_ruleShiftExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleShiftExpression3711); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleShiftExpression"


    // $ANTLR start "ruleShiftExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1765:1: ruleShiftExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleAdditiveExpression ) ) ( ( ( (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' ) ) ) ( (lv_exp_2_0= ruleAdditiveExpression ) ) )? ) ;
    public final EObject ruleShiftExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        Token lv_op_1_3=null;
        EObject lv_exp_0_0 = null;

        EObject lv_exp_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1768:28: ( ( ( (lv_exp_0_0= ruleAdditiveExpression ) ) ( ( ( (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' ) ) ) ( (lv_exp_2_0= ruleAdditiveExpression ) ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1769:1: ( ( (lv_exp_0_0= ruleAdditiveExpression ) ) ( ( ( (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' ) ) ) ( (lv_exp_2_0= ruleAdditiveExpression ) ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1769:1: ( ( (lv_exp_0_0= ruleAdditiveExpression ) ) ( ( ( (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' ) ) ) ( (lv_exp_2_0= ruleAdditiveExpression ) ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1769:2: ( (lv_exp_0_0= ruleAdditiveExpression ) ) ( ( ( (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' ) ) ) ( (lv_exp_2_0= ruleAdditiveExpression ) ) )?
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1769:2: ( (lv_exp_0_0= ruleAdditiveExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1770:1: (lv_exp_0_0= ruleAdditiveExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1770:1: (lv_exp_0_0= ruleAdditiveExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1771:3: lv_exp_0_0= ruleAdditiveExpression
            {
             
            	        newCompositeNode(grammarAccess.getShiftExpressionAccess().getExpAdditiveExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleShiftExpression3757);
            lv_exp_0_0=ruleAdditiveExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getShiftExpressionRule());
            	        }
                   		add(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"AdditiveExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1787:2: ( ( ( (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' ) ) ) ( (lv_exp_2_0= ruleAdditiveExpression ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=39 && LA33_0<=41)) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1787:3: ( ( (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' ) ) ) ( (lv_exp_2_0= ruleAdditiveExpression ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1787:3: ( ( (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' ) ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1788:1: ( (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1788:1: ( (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1789:1: (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1789:1: (lv_op_1_1= '<<' | lv_op_1_2= '>>' | lv_op_1_3= '>>>' )
                    int alt32=3;
                    switch ( input.LA(1) ) {
                    case 39:
                        {
                        alt32=1;
                        }
                        break;
                    case 40:
                        {
                        alt32=2;
                        }
                        break;
                    case 41:
                        {
                        alt32=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 32, 0, input);

                        throw nvae;
                    }

                    switch (alt32) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1790:3: lv_op_1_1= '<<'
                            {
                            lv_op_1_1=(Token)match(input,39,FOLLOW_39_in_ruleShiftExpression3778); 

                                    newLeafNode(lv_op_1_1, grammarAccess.getShiftExpressionAccess().getOpLessThanSignLessThanSignKeyword_1_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getShiftExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_1_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1802:8: lv_op_1_2= '>>'
                            {
                            lv_op_1_2=(Token)match(input,40,FOLLOW_40_in_ruleShiftExpression3807); 

                                    newLeafNode(lv_op_1_2, grammarAccess.getShiftExpressionAccess().getOpGreaterThanSignGreaterThanSignKeyword_1_0_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getShiftExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_1_2, null);
                            	    

                            }
                            break;
                        case 3 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1814:8: lv_op_1_3= '>>>'
                            {
                            lv_op_1_3=(Token)match(input,41,FOLLOW_41_in_ruleShiftExpression3836); 

                                    newLeafNode(lv_op_1_3, grammarAccess.getShiftExpressionAccess().getOpGreaterThanSignGreaterThanSignGreaterThanSignKeyword_1_0_0_2());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getShiftExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_1_3, null);
                            	    

                            }
                            break;

                    }


                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1829:2: ( (lv_exp_2_0= ruleAdditiveExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1830:1: (lv_exp_2_0= ruleAdditiveExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1830:1: (lv_exp_2_0= ruleAdditiveExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1831:3: lv_exp_2_0= ruleAdditiveExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getShiftExpressionAccess().getExpAdditiveExpressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleShiftExpression3873);
                    lv_exp_2_0=ruleAdditiveExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getShiftExpressionRule());
                    	        }
                           		add(
                           			current, 
                           			"exp",
                            		lv_exp_2_0, 
                            		"AdditiveExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleShiftExpression"


    // $ANTLR start "entryRuleAdditiveExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1855:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1856:2: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1857:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
             newCompositeNode(grammarAccess.getAdditiveExpressionRule()); 
            pushFollow(FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression3911);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();

            state._fsp--;

             current =iv_ruleAdditiveExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdditiveExpression3921); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAdditiveExpression"


    // $ANTLR start "ruleAdditiveExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1864:1: ruleAdditiveExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleMultiplicativeExpression ) ) ( ( ( (lv_op_1_1= '+' | lv_op_1_2= '-' ) ) ) ( (lv_exp_2_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_exp_0_0 = null;

        EObject lv_exp_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1867:28: ( ( ( (lv_exp_0_0= ruleMultiplicativeExpression ) ) ( ( ( (lv_op_1_1= '+' | lv_op_1_2= '-' ) ) ) ( (lv_exp_2_0= ruleMultiplicativeExpression ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1868:1: ( ( (lv_exp_0_0= ruleMultiplicativeExpression ) ) ( ( ( (lv_op_1_1= '+' | lv_op_1_2= '-' ) ) ) ( (lv_exp_2_0= ruleMultiplicativeExpression ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1868:1: ( ( (lv_exp_0_0= ruleMultiplicativeExpression ) ) ( ( ( (lv_op_1_1= '+' | lv_op_1_2= '-' ) ) ) ( (lv_exp_2_0= ruleMultiplicativeExpression ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1868:2: ( (lv_exp_0_0= ruleMultiplicativeExpression ) ) ( ( ( (lv_op_1_1= '+' | lv_op_1_2= '-' ) ) ) ( (lv_exp_2_0= ruleMultiplicativeExpression ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1868:2: ( (lv_exp_0_0= ruleMultiplicativeExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1869:1: (lv_exp_0_0= ruleMultiplicativeExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1869:1: (lv_exp_0_0= ruleMultiplicativeExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1870:3: lv_exp_0_0= ruleMultiplicativeExpression
            {
             
            	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getExpMultiplicativeExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression3967);
            lv_exp_0_0=ruleMultiplicativeExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAdditiveExpressionRule());
            	        }
                   		add(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"MultiplicativeExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1886:2: ( ( ( (lv_op_1_1= '+' | lv_op_1_2= '-' ) ) ) ( (lv_exp_2_0= ruleMultiplicativeExpression ) ) )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( ((LA35_0>=42 && LA35_0<=43)) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1886:3: ( ( (lv_op_1_1= '+' | lv_op_1_2= '-' ) ) ) ( (lv_exp_2_0= ruleMultiplicativeExpression ) )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1886:3: ( ( (lv_op_1_1= '+' | lv_op_1_2= '-' ) ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1887:1: ( (lv_op_1_1= '+' | lv_op_1_2= '-' ) )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1887:1: ( (lv_op_1_1= '+' | lv_op_1_2= '-' ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1888:1: (lv_op_1_1= '+' | lv_op_1_2= '-' )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1888:1: (lv_op_1_1= '+' | lv_op_1_2= '-' )
            	    int alt34=2;
            	    int LA34_0 = input.LA(1);

            	    if ( (LA34_0==42) ) {
            	        alt34=1;
            	    }
            	    else if ( (LA34_0==43) ) {
            	        alt34=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 34, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt34) {
            	        case 1 :
            	            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1889:3: lv_op_1_1= '+'
            	            {
            	            lv_op_1_1=(Token)match(input,42,FOLLOW_42_in_ruleAdditiveExpression3988); 

            	                    newLeafNode(lv_op_1_1, grammarAccess.getAdditiveExpressionAccess().getOpPlusSignKeyword_1_0_0_0());
            	                

            	            	        if (current==null) {
            	            	            current = createModelElement(grammarAccess.getAdditiveExpressionRule());
            	            	        }
            	                   		addWithLastConsumed(current, "op", lv_op_1_1, null);
            	            	    

            	            }
            	            break;
            	        case 2 :
            	            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1901:8: lv_op_1_2= '-'
            	            {
            	            lv_op_1_2=(Token)match(input,43,FOLLOW_43_in_ruleAdditiveExpression4017); 

            	                    newLeafNode(lv_op_1_2, grammarAccess.getAdditiveExpressionAccess().getOpHyphenMinusKeyword_1_0_0_1());
            	                

            	            	        if (current==null) {
            	            	            current = createModelElement(grammarAccess.getAdditiveExpressionRule());
            	            	        }
            	                   		addWithLastConsumed(current, "op", lv_op_1_2, null);
            	            	    

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1916:2: ( (lv_exp_2_0= ruleMultiplicativeExpression ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1917:1: (lv_exp_2_0= ruleMultiplicativeExpression )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1917:1: (lv_exp_2_0= ruleMultiplicativeExpression )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1918:3: lv_exp_2_0= ruleMultiplicativeExpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getExpMultiplicativeExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4054);
            	    lv_exp_2_0=ruleMultiplicativeExpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getAdditiveExpressionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exp",
            	            		lv_exp_2_0, 
            	            		"MultiplicativeExpression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAdditiveExpression"


    // $ANTLR start "entryRuleMultiplicativeExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1942:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1943:2: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1944:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
             newCompositeNode(grammarAccess.getMultiplicativeExpressionRule()); 
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression4092);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();

            state._fsp--;

             current =iv_ruleMultiplicativeExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiplicativeExpression4102); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMultiplicativeExpression"


    // $ANTLR start "ruleMultiplicativeExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1951:1: ruleMultiplicativeExpression returns [EObject current=null] : ( ( (lv_exp_0_0= ruleUnaryExpression ) ) ( ( ( (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' ) ) ) ( (lv_exp_2_0= ruleUnaryExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        Token lv_op_1_3=null;
        EObject lv_exp_0_0 = null;

        EObject lv_exp_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1954:28: ( ( ( (lv_exp_0_0= ruleUnaryExpression ) ) ( ( ( (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' ) ) ) ( (lv_exp_2_0= ruleUnaryExpression ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1955:1: ( ( (lv_exp_0_0= ruleUnaryExpression ) ) ( ( ( (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' ) ) ) ( (lv_exp_2_0= ruleUnaryExpression ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1955:1: ( ( (lv_exp_0_0= ruleUnaryExpression ) ) ( ( ( (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' ) ) ) ( (lv_exp_2_0= ruleUnaryExpression ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1955:2: ( (lv_exp_0_0= ruleUnaryExpression ) ) ( ( ( (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' ) ) ) ( (lv_exp_2_0= ruleUnaryExpression ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1955:2: ( (lv_exp_0_0= ruleUnaryExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1956:1: (lv_exp_0_0= ruleUnaryExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1956:1: (lv_exp_0_0= ruleUnaryExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1957:3: lv_exp_0_0= ruleUnaryExpression
            {
             
            	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getExpUnaryExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleUnaryExpression_in_ruleMultiplicativeExpression4148);
            lv_exp_0_0=ruleUnaryExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getMultiplicativeExpressionRule());
            	        }
                   		add(
                   			current, 
                   			"exp",
                    		lv_exp_0_0, 
                    		"UnaryExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1973:2: ( ( ( (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' ) ) ) ( (lv_exp_2_0= ruleUnaryExpression ) ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==16||(LA37_0>=44 && LA37_0<=45)) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1973:3: ( ( (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' ) ) ) ( (lv_exp_2_0= ruleUnaryExpression ) )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1973:3: ( ( (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' ) ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1974:1: ( (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' ) )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1974:1: ( (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1975:1: (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1975:1: (lv_op_1_1= '*' | lv_op_1_2= '/' | lv_op_1_3= '%' )
            	    int alt36=3;
            	    switch ( input.LA(1) ) {
            	    case 16:
            	        {
            	        alt36=1;
            	        }
            	        break;
            	    case 44:
            	        {
            	        alt36=2;
            	        }
            	        break;
            	    case 45:
            	        {
            	        alt36=3;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 36, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt36) {
            	        case 1 :
            	            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1976:3: lv_op_1_1= '*'
            	            {
            	            lv_op_1_1=(Token)match(input,16,FOLLOW_16_in_ruleMultiplicativeExpression4169); 

            	                    newLeafNode(lv_op_1_1, grammarAccess.getMultiplicativeExpressionAccess().getOpAsteriskKeyword_1_0_0_0());
            	                

            	            	        if (current==null) {
            	            	            current = createModelElement(grammarAccess.getMultiplicativeExpressionRule());
            	            	        }
            	                   		addWithLastConsumed(current, "op", lv_op_1_1, null);
            	            	    

            	            }
            	            break;
            	        case 2 :
            	            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:1988:8: lv_op_1_2= '/'
            	            {
            	            lv_op_1_2=(Token)match(input,44,FOLLOW_44_in_ruleMultiplicativeExpression4198); 

            	                    newLeafNode(lv_op_1_2, grammarAccess.getMultiplicativeExpressionAccess().getOpSolidusKeyword_1_0_0_1());
            	                

            	            	        if (current==null) {
            	            	            current = createModelElement(grammarAccess.getMultiplicativeExpressionRule());
            	            	        }
            	                   		addWithLastConsumed(current, "op", lv_op_1_2, null);
            	            	    

            	            }
            	            break;
            	        case 3 :
            	            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2000:8: lv_op_1_3= '%'
            	            {
            	            lv_op_1_3=(Token)match(input,45,FOLLOW_45_in_ruleMultiplicativeExpression4227); 

            	                    newLeafNode(lv_op_1_3, grammarAccess.getMultiplicativeExpressionAccess().getOpPercentSignKeyword_1_0_0_2());
            	                

            	            	        if (current==null) {
            	            	            current = createModelElement(grammarAccess.getMultiplicativeExpressionRule());
            	            	        }
            	                   		addWithLastConsumed(current, "op", lv_op_1_3, null);
            	            	    

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2015:2: ( (lv_exp_2_0= ruleUnaryExpression ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2016:1: (lv_exp_2_0= ruleUnaryExpression )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2016:1: (lv_exp_2_0= ruleUnaryExpression )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2017:3: lv_exp_2_0= ruleUnaryExpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getExpUnaryExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleMultiplicativeExpression4264);
            	    lv_exp_2_0=ruleUnaryExpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getMultiplicativeExpressionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exp",
            	            		lv_exp_2_0, 
            	            		"UnaryExpression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMultiplicativeExpression"


    // $ANTLR start "entryRuleUnaryExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2041:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2042:2: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2043:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
             newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            pushFollow(FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression4302);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;

             current =iv_ruleUnaryExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpression4312); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnaryExpression"


    // $ANTLR start "ruleUnaryExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2050:1: ruleUnaryExpression returns [EObject current=null] : ( ( ( (lv_op_0_1= '!' | lv_op_0_2= '-' | lv_op_0_3= '+' | lv_op_0_4= '$' | lv_op_0_5= '~' ) ) )? ( (lv_exp_1_0= rulePrimaryExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_1=null;
        Token lv_op_0_2=null;
        Token lv_op_0_3=null;
        Token lv_op_0_4=null;
        Token lv_op_0_5=null;
        EObject lv_exp_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2053:28: ( ( ( ( (lv_op_0_1= '!' | lv_op_0_2= '-' | lv_op_0_3= '+' | lv_op_0_4= '$' | lv_op_0_5= '~' ) ) )? ( (lv_exp_1_0= rulePrimaryExpression ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2054:1: ( ( ( (lv_op_0_1= '!' | lv_op_0_2= '-' | lv_op_0_3= '+' | lv_op_0_4= '$' | lv_op_0_5= '~' ) ) )? ( (lv_exp_1_0= rulePrimaryExpression ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2054:1: ( ( ( (lv_op_0_1= '!' | lv_op_0_2= '-' | lv_op_0_3= '+' | lv_op_0_4= '$' | lv_op_0_5= '~' ) ) )? ( (lv_exp_1_0= rulePrimaryExpression ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2054:2: ( ( (lv_op_0_1= '!' | lv_op_0_2= '-' | lv_op_0_3= '+' | lv_op_0_4= '$' | lv_op_0_5= '~' ) ) )? ( (lv_exp_1_0= rulePrimaryExpression ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2054:2: ( ( (lv_op_0_1= '!' | lv_op_0_2= '-' | lv_op_0_3= '+' | lv_op_0_4= '$' | lv_op_0_5= '~' ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( ((LA39_0>=42 && LA39_0<=43)||(LA39_0>=46 && LA39_0<=48)) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2055:1: ( (lv_op_0_1= '!' | lv_op_0_2= '-' | lv_op_0_3= '+' | lv_op_0_4= '$' | lv_op_0_5= '~' ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2055:1: ( (lv_op_0_1= '!' | lv_op_0_2= '-' | lv_op_0_3= '+' | lv_op_0_4= '$' | lv_op_0_5= '~' ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2056:1: (lv_op_0_1= '!' | lv_op_0_2= '-' | lv_op_0_3= '+' | lv_op_0_4= '$' | lv_op_0_5= '~' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2056:1: (lv_op_0_1= '!' | lv_op_0_2= '-' | lv_op_0_3= '+' | lv_op_0_4= '$' | lv_op_0_5= '~' )
                    int alt38=5;
                    switch ( input.LA(1) ) {
                    case 46:
                        {
                        alt38=1;
                        }
                        break;
                    case 43:
                        {
                        alt38=2;
                        }
                        break;
                    case 42:
                        {
                        alt38=3;
                        }
                        break;
                    case 47:
                        {
                        alt38=4;
                        }
                        break;
                    case 48:
                        {
                        alt38=5;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 38, 0, input);

                        throw nvae;
                    }

                    switch (alt38) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2057:3: lv_op_0_1= '!'
                            {
                            lv_op_0_1=(Token)match(input,46,FOLLOW_46_in_ruleUnaryExpression4357); 

                                    newLeafNode(lv_op_0_1, grammarAccess.getUnaryExpressionAccess().getOpExclamationMarkKeyword_0_0_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getUnaryExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_0_1, null);
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2069:8: lv_op_0_2= '-'
                            {
                            lv_op_0_2=(Token)match(input,43,FOLLOW_43_in_ruleUnaryExpression4386); 

                                    newLeafNode(lv_op_0_2, grammarAccess.getUnaryExpressionAccess().getOpHyphenMinusKeyword_0_0_1());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getUnaryExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_0_2, null);
                            	    

                            }
                            break;
                        case 3 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2081:8: lv_op_0_3= '+'
                            {
                            lv_op_0_3=(Token)match(input,42,FOLLOW_42_in_ruleUnaryExpression4415); 

                                    newLeafNode(lv_op_0_3, grammarAccess.getUnaryExpressionAccess().getOpPlusSignKeyword_0_0_2());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getUnaryExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_0_3, null);
                            	    

                            }
                            break;
                        case 4 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2093:8: lv_op_0_4= '$'
                            {
                            lv_op_0_4=(Token)match(input,47,FOLLOW_47_in_ruleUnaryExpression4444); 

                                    newLeafNode(lv_op_0_4, grammarAccess.getUnaryExpressionAccess().getOpDollarSignKeyword_0_0_3());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getUnaryExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_0_4, null);
                            	    

                            }
                            break;
                        case 5 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2105:8: lv_op_0_5= '~'
                            {
                            lv_op_0_5=(Token)match(input,48,FOLLOW_48_in_ruleUnaryExpression4473); 

                                    newLeafNode(lv_op_0_5, grammarAccess.getUnaryExpressionAccess().getOpTildeKeyword_0_0_4());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getUnaryExpressionRule());
                            	        }
                                   		setWithLastConsumed(current, "op", lv_op_0_5, null);
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2120:3: ( (lv_exp_1_0= rulePrimaryExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2121:1: (lv_exp_1_0= rulePrimaryExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2121:1: (lv_exp_1_0= rulePrimaryExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2122:3: lv_exp_1_0= rulePrimaryExpression
            {
             
            	        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getExpPrimaryExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulePrimaryExpression_in_ruleUnaryExpression4511);
            lv_exp_1_0=rulePrimaryExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getUnaryExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"exp",
                    		lv_exp_1_0, 
                    		"PrimaryExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnaryExpression"


    // $ANTLR start "entryRulePrimaryExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2146:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2147:2: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2148:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
             newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            pushFollow(FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression4547);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;

             current =iv_rulePrimaryExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimaryExpression4557); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimaryExpression"


    // $ANTLR start "rulePrimaryExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2155:1: rulePrimaryExpression returns [EObject current=null] : ( (lv_prefix_0_0= ruleValueSpecification ) ) ;
    public final EObject rulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject lv_prefix_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2158:28: ( ( (lv_prefix_0_0= ruleValueSpecification ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2159:1: ( (lv_prefix_0_0= ruleValueSpecification ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2159:1: ( (lv_prefix_0_0= ruleValueSpecification ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2160:1: (lv_prefix_0_0= ruleValueSpecification )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2160:1: (lv_prefix_0_0= ruleValueSpecification )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2161:3: lv_prefix_0_0= ruleValueSpecification
            {
             
            	        newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getPrefixValueSpecificationParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleValueSpecification_in_rulePrimaryExpression4602);
            lv_prefix_0_0=ruleValueSpecification();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPrimaryExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"prefix",
                    		lv_prefix_0_0, 
                    		"ValueSpecification");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimaryExpression"


    // $ANTLR start "entryRuleSuffixExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2185:1: entryRuleSuffixExpression returns [EObject current=null] : iv_ruleSuffixExpression= ruleSuffixExpression EOF ;
    public final EObject entryRuleSuffixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuffixExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2186:2: (iv_ruleSuffixExpression= ruleSuffixExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2187:2: iv_ruleSuffixExpression= ruleSuffixExpression EOF
            {
             newCompositeNode(grammarAccess.getSuffixExpressionRule()); 
            pushFollow(FOLLOW_ruleSuffixExpression_in_entryRuleSuffixExpression4637);
            iv_ruleSuffixExpression=ruleSuffixExpression();

            state._fsp--;

             current =iv_ruleSuffixExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSuffixExpression4647); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSuffixExpression"


    // $ANTLR start "ruleSuffixExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2194:1: ruleSuffixExpression returns [EObject current=null] : (this_OperationCallExpression_0= ruleOperationCallExpression | this_PropertyCallExpression_1= rulePropertyCallExpression | this_LinkOperationExpression_2= ruleLinkOperationExpression | this_SequenceOperationExpression_3= ruleSequenceOperationExpression | this_SequenceReductionExpression_4= ruleSequenceReductionExpression | this_SequenceExpansionExpression_5= ruleSequenceExpansionExpression | this_ClassExtentExpression_6= ruleClassExtentExpression ) ;
    public final EObject ruleSuffixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCallExpression_0 = null;

        EObject this_PropertyCallExpression_1 = null;

        EObject this_LinkOperationExpression_2 = null;

        EObject this_SequenceOperationExpression_3 = null;

        EObject this_SequenceReductionExpression_4 = null;

        EObject this_SequenceExpansionExpression_5 = null;

        EObject this_ClassExtentExpression_6 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2197:28: ( (this_OperationCallExpression_0= ruleOperationCallExpression | this_PropertyCallExpression_1= rulePropertyCallExpression | this_LinkOperationExpression_2= ruleLinkOperationExpression | this_SequenceOperationExpression_3= ruleSequenceOperationExpression | this_SequenceReductionExpression_4= ruleSequenceReductionExpression | this_SequenceExpansionExpression_5= ruleSequenceExpansionExpression | this_ClassExtentExpression_6= ruleClassExtentExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2198:1: (this_OperationCallExpression_0= ruleOperationCallExpression | this_PropertyCallExpression_1= rulePropertyCallExpression | this_LinkOperationExpression_2= ruleLinkOperationExpression | this_SequenceOperationExpression_3= ruleSequenceOperationExpression | this_SequenceReductionExpression_4= ruleSequenceReductionExpression | this_SequenceExpansionExpression_5= ruleSequenceExpansionExpression | this_ClassExtentExpression_6= ruleClassExtentExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2198:1: (this_OperationCallExpression_0= ruleOperationCallExpression | this_PropertyCallExpression_1= rulePropertyCallExpression | this_LinkOperationExpression_2= ruleLinkOperationExpression | this_SequenceOperationExpression_3= ruleSequenceOperationExpression | this_SequenceReductionExpression_4= ruleSequenceReductionExpression | this_SequenceExpansionExpression_5= ruleSequenceExpansionExpression | this_ClassExtentExpression_6= ruleClassExtentExpression )
            int alt40=7;
            alt40 = dfa40.predict(input);
            switch (alt40) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2199:5: this_OperationCallExpression_0= ruleOperationCallExpression
                    {
                     
                            newCompositeNode(grammarAccess.getSuffixExpressionAccess().getOperationCallExpressionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleOperationCallExpression_in_ruleSuffixExpression4694);
                    this_OperationCallExpression_0=ruleOperationCallExpression();

                    state._fsp--;

                     
                            current = this_OperationCallExpression_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2209:5: this_PropertyCallExpression_1= rulePropertyCallExpression
                    {
                     
                            newCompositeNode(grammarAccess.getSuffixExpressionAccess().getPropertyCallExpressionParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_rulePropertyCallExpression_in_ruleSuffixExpression4721);
                    this_PropertyCallExpression_1=rulePropertyCallExpression();

                    state._fsp--;

                     
                            current = this_PropertyCallExpression_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2219:5: this_LinkOperationExpression_2= ruleLinkOperationExpression
                    {
                     
                            newCompositeNode(grammarAccess.getSuffixExpressionAccess().getLinkOperationExpressionParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleLinkOperationExpression_in_ruleSuffixExpression4748);
                    this_LinkOperationExpression_2=ruleLinkOperationExpression();

                    state._fsp--;

                     
                            current = this_LinkOperationExpression_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2229:5: this_SequenceOperationExpression_3= ruleSequenceOperationExpression
                    {
                     
                            newCompositeNode(grammarAccess.getSuffixExpressionAccess().getSequenceOperationExpressionParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleSequenceOperationExpression_in_ruleSuffixExpression4775);
                    this_SequenceOperationExpression_3=ruleSequenceOperationExpression();

                    state._fsp--;

                     
                            current = this_SequenceOperationExpression_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2239:5: this_SequenceReductionExpression_4= ruleSequenceReductionExpression
                    {
                     
                            newCompositeNode(grammarAccess.getSuffixExpressionAccess().getSequenceReductionExpressionParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleSequenceReductionExpression_in_ruleSuffixExpression4802);
                    this_SequenceReductionExpression_4=ruleSequenceReductionExpression();

                    state._fsp--;

                     
                            current = this_SequenceReductionExpression_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2249:5: this_SequenceExpansionExpression_5= ruleSequenceExpansionExpression
                    {
                     
                            newCompositeNode(grammarAccess.getSuffixExpressionAccess().getSequenceExpansionExpressionParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleSequenceExpansionExpression_in_ruleSuffixExpression4829);
                    this_SequenceExpansionExpression_5=ruleSequenceExpansionExpression();

                    state._fsp--;

                     
                            current = this_SequenceExpansionExpression_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2259:5: this_ClassExtentExpression_6= ruleClassExtentExpression
                    {
                     
                            newCompositeNode(grammarAccess.getSuffixExpressionAccess().getClassExtentExpressionParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleClassExtentExpression_in_ruleSuffixExpression4856);
                    this_ClassExtentExpression_6=ruleClassExtentExpression();

                    state._fsp--;

                     
                            current = this_ClassExtentExpression_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSuffixExpression"


    // $ANTLR start "entryRuleOperationCallExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2275:1: entryRuleOperationCallExpression returns [EObject current=null] : iv_ruleOperationCallExpression= ruleOperationCallExpression EOF ;
    public final EObject entryRuleOperationCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCallExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2276:2: (iv_ruleOperationCallExpression= ruleOperationCallExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2277:2: iv_ruleOperationCallExpression= ruleOperationCallExpression EOF
            {
             newCompositeNode(grammarAccess.getOperationCallExpressionRule()); 
            pushFollow(FOLLOW_ruleOperationCallExpression_in_entryRuleOperationCallExpression4891);
            iv_ruleOperationCallExpression=ruleOperationCallExpression();

            state._fsp--;

             current =iv_ruleOperationCallExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperationCallExpression4901); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperationCallExpression"


    // $ANTLR start "ruleOperationCallExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2284:1: ruleOperationCallExpression returns [EObject current=null] : (otherlv_0= '.' ( (lv_operationName_1_0= RULE_ID ) ) ( (lv_tuple_2_0= ruleTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? ) ;
    public final EObject ruleOperationCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_operationName_1_0=null;
        EObject lv_tuple_2_0 = null;

        EObject lv_suffix_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2287:28: ( (otherlv_0= '.' ( (lv_operationName_1_0= RULE_ID ) ) ( (lv_tuple_2_0= ruleTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2288:1: (otherlv_0= '.' ( (lv_operationName_1_0= RULE_ID ) ) ( (lv_tuple_2_0= ruleTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2288:1: (otherlv_0= '.' ( (lv_operationName_1_0= RULE_ID ) ) ( (lv_tuple_2_0= ruleTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2288:3: otherlv_0= '.' ( (lv_operationName_1_0= RULE_ID ) ) ( (lv_tuple_2_0= ruleTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )?
            {
            otherlv_0=(Token)match(input,49,FOLLOW_49_in_ruleOperationCallExpression4938); 

                	newLeafNode(otherlv_0, grammarAccess.getOperationCallExpressionAccess().getFullStopKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2292:1: ( (lv_operationName_1_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2293:1: (lv_operationName_1_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2293:1: (lv_operationName_1_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2294:3: lv_operationName_1_0= RULE_ID
            {
            lv_operationName_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOperationCallExpression4955); 

            			newLeafNode(lv_operationName_1_0, grammarAccess.getOperationCallExpressionAccess().getOperationNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getOperationCallExpressionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"operationName",
                    		lv_operationName_1_0, 
                    		"ID");
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2310:2: ( (lv_tuple_2_0= ruleTuple ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2311:1: (lv_tuple_2_0= ruleTuple )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2311:1: (lv_tuple_2_0= ruleTuple )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2312:3: lv_tuple_2_0= ruleTuple
            {
             
            	        newCompositeNode(grammarAccess.getOperationCallExpressionAccess().getTupleTupleParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleTuple_in_ruleOperationCallExpression4981);
            lv_tuple_2_0=ruleTuple();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperationCallExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"tuple",
                    		lv_tuple_2_0, 
                    		"Tuple");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2328:2: ( (lv_suffix_3_0= ruleSuffixExpression ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==49||LA41_0==52) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2329:1: (lv_suffix_3_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2329:1: (lv_suffix_3_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2330:3: lv_suffix_3_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperationCallExpressionAccess().getSuffixSuffixExpressionParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleOperationCallExpression5002);
                    lv_suffix_3_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperationCallExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_3_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperationCallExpression"


    // $ANTLR start "entryRulePropertyCallExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2354:1: entryRulePropertyCallExpression returns [EObject current=null] : iv_rulePropertyCallExpression= rulePropertyCallExpression EOF ;
    public final EObject entryRulePropertyCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyCallExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2355:2: (iv_rulePropertyCallExpression= rulePropertyCallExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2356:2: iv_rulePropertyCallExpression= rulePropertyCallExpression EOF
            {
             newCompositeNode(grammarAccess.getPropertyCallExpressionRule()); 
            pushFollow(FOLLOW_rulePropertyCallExpression_in_entryRulePropertyCallExpression5039);
            iv_rulePropertyCallExpression=rulePropertyCallExpression();

            state._fsp--;

             current =iv_rulePropertyCallExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyCallExpression5049); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePropertyCallExpression"


    // $ANTLR start "rulePropertyCallExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2363:1: rulePropertyCallExpression returns [EObject current=null] : (otherlv_0= '.' ( (lv_propertyName_1_0= RULE_ID ) ) (otherlv_2= '[' ( (lv_index_3_0= ruleExpression ) ) otherlv_4= ']' )? ( (lv_suffix_5_0= ruleSuffixExpression ) )? ) ;
    public final EObject rulePropertyCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propertyName_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_index_3_0 = null;

        EObject lv_suffix_5_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2366:28: ( (otherlv_0= '.' ( (lv_propertyName_1_0= RULE_ID ) ) (otherlv_2= '[' ( (lv_index_3_0= ruleExpression ) ) otherlv_4= ']' )? ( (lv_suffix_5_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2367:1: (otherlv_0= '.' ( (lv_propertyName_1_0= RULE_ID ) ) (otherlv_2= '[' ( (lv_index_3_0= ruleExpression ) ) otherlv_4= ']' )? ( (lv_suffix_5_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2367:1: (otherlv_0= '.' ( (lv_propertyName_1_0= RULE_ID ) ) (otherlv_2= '[' ( (lv_index_3_0= ruleExpression ) ) otherlv_4= ']' )? ( (lv_suffix_5_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2367:3: otherlv_0= '.' ( (lv_propertyName_1_0= RULE_ID ) ) (otherlv_2= '[' ( (lv_index_3_0= ruleExpression ) ) otherlv_4= ']' )? ( (lv_suffix_5_0= ruleSuffixExpression ) )?
            {
            otherlv_0=(Token)match(input,49,FOLLOW_49_in_rulePropertyCallExpression5086); 

                	newLeafNode(otherlv_0, grammarAccess.getPropertyCallExpressionAccess().getFullStopKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2371:1: ( (lv_propertyName_1_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2372:1: (lv_propertyName_1_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2372:1: (lv_propertyName_1_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2373:3: lv_propertyName_1_0= RULE_ID
            {
            lv_propertyName_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_rulePropertyCallExpression5103); 

            			newLeafNode(lv_propertyName_1_0, grammarAccess.getPropertyCallExpressionAccess().getPropertyNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getPropertyCallExpressionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"propertyName",
                    		lv_propertyName_1_0, 
                    		"ID");
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2389:2: (otherlv_2= '[' ( (lv_index_3_0= ruleExpression ) ) otherlv_4= ']' )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==50) ) {
                int LA42_1 = input.LA(2);

                if ( ((LA42_1>=RULE_INTEGERVALUE && LA42_1<=RULE_ID)||(LA42_1>=16 && LA42_1<=18)||LA42_1==24||(LA42_1>=42 && LA42_1<=43)||(LA42_1>=46 && LA42_1<=48)||(LA42_1>=56 && LA42_1<=59)||(LA42_1>=87 && LA42_1<=88)) ) {
                    alt42=1;
                }
            }
            switch (alt42) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2389:4: otherlv_2= '[' ( (lv_index_3_0= ruleExpression ) ) otherlv_4= ']'
                    {
                    otherlv_2=(Token)match(input,50,FOLLOW_50_in_rulePropertyCallExpression5121); 

                        	newLeafNode(otherlv_2, grammarAccess.getPropertyCallExpressionAccess().getLeftSquareBracketKeyword_2_0());
                        
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2393:1: ( (lv_index_3_0= ruleExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2394:1: (lv_index_3_0= ruleExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2394:1: (lv_index_3_0= ruleExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2395:3: lv_index_3_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getPropertyCallExpressionAccess().getIndexExpressionParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_rulePropertyCallExpression5142);
                    lv_index_3_0=ruleExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPropertyCallExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"index",
                            		lv_index_3_0, 
                            		"Expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_4=(Token)match(input,51,FOLLOW_51_in_rulePropertyCallExpression5154); 

                        	newLeafNode(otherlv_4, grammarAccess.getPropertyCallExpressionAccess().getRightSquareBracketKeyword_2_2());
                        

                    }
                    break;

            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2415:3: ( (lv_suffix_5_0= ruleSuffixExpression ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==49||LA43_0==52) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2416:1: (lv_suffix_5_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2416:1: (lv_suffix_5_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2417:3: lv_suffix_5_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getPropertyCallExpressionAccess().getSuffixSuffixExpressionParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_rulePropertyCallExpression5177);
                    lv_suffix_5_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPropertyCallExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_5_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePropertyCallExpression"


    // $ANTLR start "entryRuleLinkOperationExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2441:1: entryRuleLinkOperationExpression returns [EObject current=null] : iv_ruleLinkOperationExpression= ruleLinkOperationExpression EOF ;
    public final EObject entryRuleLinkOperationExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLinkOperationExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2442:2: (iv_ruleLinkOperationExpression= ruleLinkOperationExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2443:2: iv_ruleLinkOperationExpression= ruleLinkOperationExpression EOF
            {
             newCompositeNode(grammarAccess.getLinkOperationExpressionRule()); 
            pushFollow(FOLLOW_ruleLinkOperationExpression_in_entryRuleLinkOperationExpression5214);
            iv_ruleLinkOperationExpression=ruleLinkOperationExpression();

            state._fsp--;

             current =iv_ruleLinkOperationExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLinkOperationExpression5224); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLinkOperationExpression"


    // $ANTLR start "ruleLinkOperationExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2450:1: ruleLinkOperationExpression returns [EObject current=null] : (otherlv_0= '.' ( (lv_kind_1_0= ruleLinkOperationKind ) ) ( (lv_tuple_2_0= ruleLinkOperationTuple ) ) ) ;
    public final EObject ruleLinkOperationExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Enumerator lv_kind_1_0 = null;

        EObject lv_tuple_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2453:28: ( (otherlv_0= '.' ( (lv_kind_1_0= ruleLinkOperationKind ) ) ( (lv_tuple_2_0= ruleLinkOperationTuple ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2454:1: (otherlv_0= '.' ( (lv_kind_1_0= ruleLinkOperationKind ) ) ( (lv_tuple_2_0= ruleLinkOperationTuple ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2454:1: (otherlv_0= '.' ( (lv_kind_1_0= ruleLinkOperationKind ) ) ( (lv_tuple_2_0= ruleLinkOperationTuple ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2454:3: otherlv_0= '.' ( (lv_kind_1_0= ruleLinkOperationKind ) ) ( (lv_tuple_2_0= ruleLinkOperationTuple ) )
            {
            otherlv_0=(Token)match(input,49,FOLLOW_49_in_ruleLinkOperationExpression5261); 

                	newLeafNode(otherlv_0, grammarAccess.getLinkOperationExpressionAccess().getFullStopKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2458:1: ( (lv_kind_1_0= ruleLinkOperationKind ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2459:1: (lv_kind_1_0= ruleLinkOperationKind )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2459:1: (lv_kind_1_0= ruleLinkOperationKind )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2460:3: lv_kind_1_0= ruleLinkOperationKind
            {
             
            	        newCompositeNode(grammarAccess.getLinkOperationExpressionAccess().getKindLinkOperationKindEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLinkOperationKind_in_ruleLinkOperationExpression5282);
            lv_kind_1_0=ruleLinkOperationKind();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLinkOperationExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"kind",
                    		lv_kind_1_0, 
                    		"LinkOperationKind");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2476:2: ( (lv_tuple_2_0= ruleLinkOperationTuple ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2477:1: (lv_tuple_2_0= ruleLinkOperationTuple )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2477:1: (lv_tuple_2_0= ruleLinkOperationTuple )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2478:3: lv_tuple_2_0= ruleLinkOperationTuple
            {
             
            	        newCompositeNode(grammarAccess.getLinkOperationExpressionAccess().getTupleLinkOperationTupleParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleLinkOperationTuple_in_ruleLinkOperationExpression5303);
            lv_tuple_2_0=ruleLinkOperationTuple();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLinkOperationExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"tuple",
                    		lv_tuple_2_0, 
                    		"LinkOperationTuple");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLinkOperationExpression"


    // $ANTLR start "entryRuleLinkOperationTuple"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2502:1: entryRuleLinkOperationTuple returns [EObject current=null] : iv_ruleLinkOperationTuple= ruleLinkOperationTuple EOF ;
    public final EObject entryRuleLinkOperationTuple() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLinkOperationTuple = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2503:2: (iv_ruleLinkOperationTuple= ruleLinkOperationTuple EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2504:2: iv_ruleLinkOperationTuple= ruleLinkOperationTuple EOF
            {
             newCompositeNode(grammarAccess.getLinkOperationTupleRule()); 
            pushFollow(FOLLOW_ruleLinkOperationTuple_in_entryRuleLinkOperationTuple5339);
            iv_ruleLinkOperationTuple=ruleLinkOperationTuple();

            state._fsp--;

             current =iv_ruleLinkOperationTuple; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLinkOperationTuple5349); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLinkOperationTuple"


    // $ANTLR start "ruleLinkOperationTuple"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2511:1: ruleLinkOperationTuple returns [EObject current=null] : (otherlv_0= '(' ( (lv_linkOperationTupleElement_1_0= ruleLinkOperationTupleElement ) ) (otherlv_2= ',' ( (lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement ) ) )* otherlv_4= ')' ) ;
    public final EObject ruleLinkOperationTuple() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_linkOperationTupleElement_1_0 = null;

        EObject lv_linkOperationTupleElement_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2514:28: ( (otherlv_0= '(' ( (lv_linkOperationTupleElement_1_0= ruleLinkOperationTupleElement ) ) (otherlv_2= ',' ( (lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement ) ) )* otherlv_4= ')' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2515:1: (otherlv_0= '(' ( (lv_linkOperationTupleElement_1_0= ruleLinkOperationTupleElement ) ) (otherlv_2= ',' ( (lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement ) ) )* otherlv_4= ')' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2515:1: (otherlv_0= '(' ( (lv_linkOperationTupleElement_1_0= ruleLinkOperationTupleElement ) ) (otherlv_2= ',' ( (lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement ) ) )* otherlv_4= ')' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2515:3: otherlv_0= '(' ( (lv_linkOperationTupleElement_1_0= ruleLinkOperationTupleElement ) ) (otherlv_2= ',' ( (lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement ) ) )* otherlv_4= ')'
            {
            otherlv_0=(Token)match(input,24,FOLLOW_24_in_ruleLinkOperationTuple5386); 

                	newLeafNode(otherlv_0, grammarAccess.getLinkOperationTupleAccess().getLeftParenthesisKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2519:1: ( (lv_linkOperationTupleElement_1_0= ruleLinkOperationTupleElement ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2520:1: (lv_linkOperationTupleElement_1_0= ruleLinkOperationTupleElement )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2520:1: (lv_linkOperationTupleElement_1_0= ruleLinkOperationTupleElement )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2521:3: lv_linkOperationTupleElement_1_0= ruleLinkOperationTupleElement
            {
             
            	        newCompositeNode(grammarAccess.getLinkOperationTupleAccess().getLinkOperationTupleElementLinkOperationTupleElementParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLinkOperationTupleElement_in_ruleLinkOperationTuple5407);
            lv_linkOperationTupleElement_1_0=ruleLinkOperationTupleElement();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLinkOperationTupleRule());
            	        }
                   		add(
                   			current, 
                   			"linkOperationTupleElement",
                    		lv_linkOperationTupleElement_1_0, 
                    		"LinkOperationTupleElement");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2537:2: (otherlv_2= ',' ( (lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==21) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2537:4: otherlv_2= ',' ( (lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement ) )
            	    {
            	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleLinkOperationTuple5420); 

            	        	newLeafNode(otherlv_2, grammarAccess.getLinkOperationTupleAccess().getCommaKeyword_2_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2541:1: ( (lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2542:1: (lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2542:1: (lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2543:3: lv_linkOperationTupleElement_3_0= ruleLinkOperationTupleElement
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getLinkOperationTupleAccess().getLinkOperationTupleElementLinkOperationTupleElementParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleLinkOperationTupleElement_in_ruleLinkOperationTuple5441);
            	    lv_linkOperationTupleElement_3_0=ruleLinkOperationTupleElement();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getLinkOperationTupleRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"linkOperationTupleElement",
            	            		lv_linkOperationTupleElement_3_0, 
            	            		"LinkOperationTupleElement");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);

            otherlv_4=(Token)match(input,25,FOLLOW_25_in_ruleLinkOperationTuple5455); 

                	newLeafNode(otherlv_4, grammarAccess.getLinkOperationTupleAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLinkOperationTuple"


    // $ANTLR start "entryRuleLinkOperationTupleElement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2571:1: entryRuleLinkOperationTupleElement returns [EObject current=null] : iv_ruleLinkOperationTupleElement= ruleLinkOperationTupleElement EOF ;
    public final EObject entryRuleLinkOperationTupleElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLinkOperationTupleElement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2572:2: (iv_ruleLinkOperationTupleElement= ruleLinkOperationTupleElement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2573:2: iv_ruleLinkOperationTupleElement= ruleLinkOperationTupleElement EOF
            {
             newCompositeNode(grammarAccess.getLinkOperationTupleElementRule()); 
            pushFollow(FOLLOW_ruleLinkOperationTupleElement_in_entryRuleLinkOperationTupleElement5491);
            iv_ruleLinkOperationTupleElement=ruleLinkOperationTupleElement();

            state._fsp--;

             current =iv_ruleLinkOperationTupleElement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLinkOperationTupleElement5501); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLinkOperationTupleElement"


    // $ANTLR start "ruleLinkOperationTupleElement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2580:1: ruleLinkOperationTupleElement returns [EObject current=null] : ( ( (lv_role_0_0= RULE_ID ) ) (otherlv_1= '[' ( (lv_roleIndex_2_0= ruleExpression ) ) otherlv_3= ']' )? otherlv_4= '=>' ( (lv_object_5_0= ruleExpression ) ) ) ;
    public final EObject ruleLinkOperationTupleElement() throws RecognitionException {
        EObject current = null;

        Token lv_role_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_roleIndex_2_0 = null;

        EObject lv_object_5_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2583:28: ( ( ( (lv_role_0_0= RULE_ID ) ) (otherlv_1= '[' ( (lv_roleIndex_2_0= ruleExpression ) ) otherlv_3= ']' )? otherlv_4= '=>' ( (lv_object_5_0= ruleExpression ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2584:1: ( ( (lv_role_0_0= RULE_ID ) ) (otherlv_1= '[' ( (lv_roleIndex_2_0= ruleExpression ) ) otherlv_3= ']' )? otherlv_4= '=>' ( (lv_object_5_0= ruleExpression ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2584:1: ( ( (lv_role_0_0= RULE_ID ) ) (otherlv_1= '[' ( (lv_roleIndex_2_0= ruleExpression ) ) otherlv_3= ']' )? otherlv_4= '=>' ( (lv_object_5_0= ruleExpression ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2584:2: ( (lv_role_0_0= RULE_ID ) ) (otherlv_1= '[' ( (lv_roleIndex_2_0= ruleExpression ) ) otherlv_3= ']' )? otherlv_4= '=>' ( (lv_object_5_0= ruleExpression ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2584:2: ( (lv_role_0_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2585:1: (lv_role_0_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2585:1: (lv_role_0_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2586:3: lv_role_0_0= RULE_ID
            {
            lv_role_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLinkOperationTupleElement5543); 

            			newLeafNode(lv_role_0_0, grammarAccess.getLinkOperationTupleElementAccess().getRoleIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLinkOperationTupleElementRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"role",
                    		lv_role_0_0, 
                    		"ID");
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2602:2: (otherlv_1= '[' ( (lv_roleIndex_2_0= ruleExpression ) ) otherlv_3= ']' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==50) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2602:4: otherlv_1= '[' ( (lv_roleIndex_2_0= ruleExpression ) ) otherlv_3= ']'
                    {
                    otherlv_1=(Token)match(input,50,FOLLOW_50_in_ruleLinkOperationTupleElement5561); 

                        	newLeafNode(otherlv_1, grammarAccess.getLinkOperationTupleElementAccess().getLeftSquareBracketKeyword_1_0());
                        
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2606:1: ( (lv_roleIndex_2_0= ruleExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2607:1: (lv_roleIndex_2_0= ruleExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2607:1: (lv_roleIndex_2_0= ruleExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2608:3: lv_roleIndex_2_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getLinkOperationTupleElementAccess().getRoleIndexExpressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleLinkOperationTupleElement5582);
                    lv_roleIndex_2_0=ruleExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getLinkOperationTupleElementRule());
                    	        }
                           		set(
                           			current, 
                           			"roleIndex",
                            		lv_roleIndex_2_0, 
                            		"Expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_3=(Token)match(input,51,FOLLOW_51_in_ruleLinkOperationTupleElement5594); 

                        	newLeafNode(otherlv_3, grammarAccess.getLinkOperationTupleElementAccess().getRightSquareBracketKeyword_1_2());
                        

                    }
                    break;

            }

            otherlv_4=(Token)match(input,23,FOLLOW_23_in_ruleLinkOperationTupleElement5608); 

                	newLeafNode(otherlv_4, grammarAccess.getLinkOperationTupleElementAccess().getEqualsSignGreaterThanSignKeyword_2());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2632:1: ( (lv_object_5_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2633:1: (lv_object_5_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2633:1: (lv_object_5_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2634:3: lv_object_5_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getLinkOperationTupleElementAccess().getObjectExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleLinkOperationTupleElement5629);
            lv_object_5_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLinkOperationTupleElementRule());
            	        }
                   		set(
                   			current, 
                   			"object",
                    		lv_object_5_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLinkOperationTupleElement"


    // $ANTLR start "entryRuleSequenceOperationExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2658:1: entryRuleSequenceOperationExpression returns [EObject current=null] : iv_ruleSequenceOperationExpression= ruleSequenceOperationExpression EOF ;
    public final EObject entryRuleSequenceOperationExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSequenceOperationExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2659:2: (iv_ruleSequenceOperationExpression= ruleSequenceOperationExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2660:2: iv_ruleSequenceOperationExpression= ruleSequenceOperationExpression EOF
            {
             newCompositeNode(grammarAccess.getSequenceOperationExpressionRule()); 
            pushFollow(FOLLOW_ruleSequenceOperationExpression_in_entryRuleSequenceOperationExpression5665);
            iv_ruleSequenceOperationExpression=ruleSequenceOperationExpression();

            state._fsp--;

             current =iv_ruleSequenceOperationExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSequenceOperationExpression5675); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSequenceOperationExpression"


    // $ANTLR start "ruleSequenceOperationExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2667:1: ruleSequenceOperationExpression returns [EObject current=null] : (otherlv_0= '->' ( (lv_operationName_1_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_2_0= ruleTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? ) ;
    public final EObject ruleSequenceOperationExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_operationName_1_0 = null;

        EObject lv_tuple_2_0 = null;

        EObject lv_suffix_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2670:28: ( (otherlv_0= '->' ( (lv_operationName_1_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_2_0= ruleTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2671:1: (otherlv_0= '->' ( (lv_operationName_1_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_2_0= ruleTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2671:1: (otherlv_0= '->' ( (lv_operationName_1_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_2_0= ruleTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2671:3: otherlv_0= '->' ( (lv_operationName_1_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_2_0= ruleTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )?
            {
            otherlv_0=(Token)match(input,52,FOLLOW_52_in_ruleSequenceOperationExpression5712); 

                	newLeafNode(otherlv_0, grammarAccess.getSequenceOperationExpressionAccess().getHyphenMinusGreaterThanSignKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2675:1: ( (lv_operationName_1_0= ruleQualifiedNameWithBinding ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2676:1: (lv_operationName_1_0= ruleQualifiedNameWithBinding )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2676:1: (lv_operationName_1_0= ruleQualifiedNameWithBinding )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2677:3: lv_operationName_1_0= ruleQualifiedNameWithBinding
            {
             
            	        newCompositeNode(grammarAccess.getSequenceOperationExpressionAccess().getOperationNameQualifiedNameWithBindingParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_ruleSequenceOperationExpression5733);
            lv_operationName_1_0=ruleQualifiedNameWithBinding();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSequenceOperationExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"operationName",
                    		lv_operationName_1_0, 
                    		"QualifiedNameWithBinding");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2693:2: ( (lv_tuple_2_0= ruleTuple ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2694:1: (lv_tuple_2_0= ruleTuple )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2694:1: (lv_tuple_2_0= ruleTuple )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2695:3: lv_tuple_2_0= ruleTuple
            {
             
            	        newCompositeNode(grammarAccess.getSequenceOperationExpressionAccess().getTupleTupleParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleTuple_in_ruleSequenceOperationExpression5754);
            lv_tuple_2_0=ruleTuple();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSequenceOperationExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"tuple",
                    		lv_tuple_2_0, 
                    		"Tuple");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2711:2: ( (lv_suffix_3_0= ruleSuffixExpression ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==49||LA46_0==52) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2712:1: (lv_suffix_3_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2712:1: (lv_suffix_3_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2713:3: lv_suffix_3_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSequenceOperationExpressionAccess().getSuffixSuffixExpressionParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleSequenceOperationExpression5775);
                    lv_suffix_3_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSequenceOperationExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_3_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSequenceOperationExpression"


    // $ANTLR start "entryRuleSequenceReductionExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2737:1: entryRuleSequenceReductionExpression returns [EObject current=null] : iv_ruleSequenceReductionExpression= ruleSequenceReductionExpression EOF ;
    public final EObject entryRuleSequenceReductionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSequenceReductionExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2738:2: (iv_ruleSequenceReductionExpression= ruleSequenceReductionExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2739:2: iv_ruleSequenceReductionExpression= ruleSequenceReductionExpression EOF
            {
             newCompositeNode(grammarAccess.getSequenceReductionExpressionRule()); 
            pushFollow(FOLLOW_ruleSequenceReductionExpression_in_entryRuleSequenceReductionExpression5812);
            iv_ruleSequenceReductionExpression=ruleSequenceReductionExpression();

            state._fsp--;

             current =iv_ruleSequenceReductionExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSequenceReductionExpression5822); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSequenceReductionExpression"


    // $ANTLR start "ruleSequenceReductionExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2746:1: ruleSequenceReductionExpression returns [EObject current=null] : (otherlv_0= '->' otherlv_1= 'reduce' ( (lv_isOrdered_2_0= 'ordered' ) )? ( (lv_behavior_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_suffix_4_0= ruleSuffixExpression ) )? ) ;
    public final EObject ruleSequenceReductionExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_isOrdered_2_0=null;
        EObject lv_behavior_3_0 = null;

        EObject lv_suffix_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2749:28: ( (otherlv_0= '->' otherlv_1= 'reduce' ( (lv_isOrdered_2_0= 'ordered' ) )? ( (lv_behavior_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_suffix_4_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2750:1: (otherlv_0= '->' otherlv_1= 'reduce' ( (lv_isOrdered_2_0= 'ordered' ) )? ( (lv_behavior_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_suffix_4_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2750:1: (otherlv_0= '->' otherlv_1= 'reduce' ( (lv_isOrdered_2_0= 'ordered' ) )? ( (lv_behavior_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_suffix_4_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2750:3: otherlv_0= '->' otherlv_1= 'reduce' ( (lv_isOrdered_2_0= 'ordered' ) )? ( (lv_behavior_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_suffix_4_0= ruleSuffixExpression ) )?
            {
            otherlv_0=(Token)match(input,52,FOLLOW_52_in_ruleSequenceReductionExpression5859); 

                	newLeafNode(otherlv_0, grammarAccess.getSequenceReductionExpressionAccess().getHyphenMinusGreaterThanSignKeyword_0());
                
            otherlv_1=(Token)match(input,53,FOLLOW_53_in_ruleSequenceReductionExpression5871); 

                	newLeafNode(otherlv_1, grammarAccess.getSequenceReductionExpressionAccess().getReduceKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2758:1: ( (lv_isOrdered_2_0= 'ordered' ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==54) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2759:1: (lv_isOrdered_2_0= 'ordered' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2759:1: (lv_isOrdered_2_0= 'ordered' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2760:3: lv_isOrdered_2_0= 'ordered'
                    {
                    lv_isOrdered_2_0=(Token)match(input,54,FOLLOW_54_in_ruleSequenceReductionExpression5889); 

                            newLeafNode(lv_isOrdered_2_0, grammarAccess.getSequenceReductionExpressionAccess().getIsOrderedOrderedKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSequenceReductionExpressionRule());
                    	        }
                           		setWithLastConsumed(current, "isOrdered", true, "ordered");
                    	    

                    }


                    }
                    break;

            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2773:3: ( (lv_behavior_3_0= ruleQualifiedNameWithBinding ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2774:1: (lv_behavior_3_0= ruleQualifiedNameWithBinding )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2774:1: (lv_behavior_3_0= ruleQualifiedNameWithBinding )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2775:3: lv_behavior_3_0= ruleQualifiedNameWithBinding
            {
             
            	        newCompositeNode(grammarAccess.getSequenceReductionExpressionAccess().getBehaviorQualifiedNameWithBindingParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_ruleSequenceReductionExpression5924);
            lv_behavior_3_0=ruleQualifiedNameWithBinding();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSequenceReductionExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"behavior",
                    		lv_behavior_3_0, 
                    		"QualifiedNameWithBinding");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2791:2: ( (lv_suffix_4_0= ruleSuffixExpression ) )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==49||LA48_0==52) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2792:1: (lv_suffix_4_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2792:1: (lv_suffix_4_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2793:3: lv_suffix_4_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSequenceReductionExpressionAccess().getSuffixSuffixExpressionParserRuleCall_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleSequenceReductionExpression5945);
                    lv_suffix_4_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSequenceReductionExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_4_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSequenceReductionExpression"


    // $ANTLR start "entryRuleSequenceExpansionExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2817:1: entryRuleSequenceExpansionExpression returns [EObject current=null] : iv_ruleSequenceExpansionExpression= ruleSequenceExpansionExpression EOF ;
    public final EObject entryRuleSequenceExpansionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSequenceExpansionExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2818:2: (iv_ruleSequenceExpansionExpression= ruleSequenceExpansionExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2819:2: iv_ruleSequenceExpansionExpression= ruleSequenceExpansionExpression EOF
            {
             newCompositeNode(grammarAccess.getSequenceExpansionExpressionRule()); 
            pushFollow(FOLLOW_ruleSequenceExpansionExpression_in_entryRuleSequenceExpansionExpression5982);
            iv_ruleSequenceExpansionExpression=ruleSequenceExpansionExpression();

            state._fsp--;

             current =iv_ruleSequenceExpansionExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSequenceExpansionExpression5992); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSequenceExpansionExpression"


    // $ANTLR start "ruleSequenceExpansionExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2826:1: ruleSequenceExpansionExpression returns [EObject current=null] : (this_SelectOrRejectOperation_0= ruleSelectOrRejectOperation | this_CollectOrIterateOperation_1= ruleCollectOrIterateOperation | this_ForAllOrExistsOrOneOperation_2= ruleForAllOrExistsOrOneOperation | this_IsUniqueOperation_3= ruleIsUniqueOperation ) ;
    public final EObject ruleSequenceExpansionExpression() throws RecognitionException {
        EObject current = null;

        EObject this_SelectOrRejectOperation_0 = null;

        EObject this_CollectOrIterateOperation_1 = null;

        EObject this_ForAllOrExistsOrOneOperation_2 = null;

        EObject this_IsUniqueOperation_3 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2829:28: ( (this_SelectOrRejectOperation_0= ruleSelectOrRejectOperation | this_CollectOrIterateOperation_1= ruleCollectOrIterateOperation | this_ForAllOrExistsOrOneOperation_2= ruleForAllOrExistsOrOneOperation | this_IsUniqueOperation_3= ruleIsUniqueOperation ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2830:1: (this_SelectOrRejectOperation_0= ruleSelectOrRejectOperation | this_CollectOrIterateOperation_1= ruleCollectOrIterateOperation | this_ForAllOrExistsOrOneOperation_2= ruleForAllOrExistsOrOneOperation | this_IsUniqueOperation_3= ruleIsUniqueOperation )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2830:1: (this_SelectOrRejectOperation_0= ruleSelectOrRejectOperation | this_CollectOrIterateOperation_1= ruleCollectOrIterateOperation | this_ForAllOrExistsOrOneOperation_2= ruleForAllOrExistsOrOneOperation | this_IsUniqueOperation_3= ruleIsUniqueOperation )
            int alt49=4;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==52) ) {
                switch ( input.LA(2) ) {
                case 96:
                case 97:
                case 98:
                    {
                    alt49=3;
                    }
                    break;
                case 92:
                case 93:
                    {
                    alt49=1;
                    }
                    break;
                case 94:
                case 95:
                    {
                    alt49=2;
                    }
                    break;
                case 55:
                    {
                    alt49=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 49, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2831:5: this_SelectOrRejectOperation_0= ruleSelectOrRejectOperation
                    {
                     
                            newCompositeNode(grammarAccess.getSequenceExpansionExpressionAccess().getSelectOrRejectOperationParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleSelectOrRejectOperation_in_ruleSequenceExpansionExpression6039);
                    this_SelectOrRejectOperation_0=ruleSelectOrRejectOperation();

                    state._fsp--;

                     
                            current = this_SelectOrRejectOperation_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2841:5: this_CollectOrIterateOperation_1= ruleCollectOrIterateOperation
                    {
                     
                            newCompositeNode(grammarAccess.getSequenceExpansionExpressionAccess().getCollectOrIterateOperationParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleCollectOrIterateOperation_in_ruleSequenceExpansionExpression6066);
                    this_CollectOrIterateOperation_1=ruleCollectOrIterateOperation();

                    state._fsp--;

                     
                            current = this_CollectOrIterateOperation_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2851:5: this_ForAllOrExistsOrOneOperation_2= ruleForAllOrExistsOrOneOperation
                    {
                     
                            newCompositeNode(grammarAccess.getSequenceExpansionExpressionAccess().getForAllOrExistsOrOneOperationParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleForAllOrExistsOrOneOperation_in_ruleSequenceExpansionExpression6093);
                    this_ForAllOrExistsOrOneOperation_2=ruleForAllOrExistsOrOneOperation();

                    state._fsp--;

                     
                            current = this_ForAllOrExistsOrOneOperation_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2861:5: this_IsUniqueOperation_3= ruleIsUniqueOperation
                    {
                     
                            newCompositeNode(grammarAccess.getSequenceExpansionExpressionAccess().getIsUniqueOperationParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleIsUniqueOperation_in_ruleSequenceExpansionExpression6120);
                    this_IsUniqueOperation_3=ruleIsUniqueOperation();

                    state._fsp--;

                     
                            current = this_IsUniqueOperation_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSequenceExpansionExpression"


    // $ANTLR start "entryRuleSelectOrRejectOperation"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2877:1: entryRuleSelectOrRejectOperation returns [EObject current=null] : iv_ruleSelectOrRejectOperation= ruleSelectOrRejectOperation EOF ;
    public final EObject entryRuleSelectOrRejectOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectOrRejectOperation = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2878:2: (iv_ruleSelectOrRejectOperation= ruleSelectOrRejectOperation EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2879:2: iv_ruleSelectOrRejectOperation= ruleSelectOrRejectOperation EOF
            {
             newCompositeNode(grammarAccess.getSelectOrRejectOperationRule()); 
            pushFollow(FOLLOW_ruleSelectOrRejectOperation_in_entryRuleSelectOrRejectOperation6155);
            iv_ruleSelectOrRejectOperation=ruleSelectOrRejectOperation();

            state._fsp--;

             current =iv_ruleSelectOrRejectOperation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSelectOrRejectOperation6165); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSelectOrRejectOperation"


    // $ANTLR start "ruleSelectOrRejectOperation"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2886:1: ruleSelectOrRejectOperation returns [EObject current=null] : (otherlv_0= '->' ( (lv_op_1_0= ruleSelectOrRejectOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? ) ;
    public final EObject ruleSelectOrRejectOperation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_op_1_0 = null;

        EObject lv_expr_4_0 = null;

        EObject lv_suffix_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2889:28: ( (otherlv_0= '->' ( (lv_op_1_0= ruleSelectOrRejectOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2890:1: (otherlv_0= '->' ( (lv_op_1_0= ruleSelectOrRejectOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2890:1: (otherlv_0= '->' ( (lv_op_1_0= ruleSelectOrRejectOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2890:3: otherlv_0= '->' ( (lv_op_1_0= ruleSelectOrRejectOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )?
            {
            otherlv_0=(Token)match(input,52,FOLLOW_52_in_ruleSelectOrRejectOperation6202); 

                	newLeafNode(otherlv_0, grammarAccess.getSelectOrRejectOperationAccess().getHyphenMinusGreaterThanSignKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2894:1: ( (lv_op_1_0= ruleSelectOrRejectOperator ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2895:1: (lv_op_1_0= ruleSelectOrRejectOperator )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2895:1: (lv_op_1_0= ruleSelectOrRejectOperator )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2896:3: lv_op_1_0= ruleSelectOrRejectOperator
            {
             
            	        newCompositeNode(grammarAccess.getSelectOrRejectOperationAccess().getOpSelectOrRejectOperatorEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleSelectOrRejectOperator_in_ruleSelectOrRejectOperation6223);
            lv_op_1_0=ruleSelectOrRejectOperator();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectOrRejectOperationRule());
            	        }
                   		set(
                   			current, 
                   			"op",
                    		lv_op_1_0, 
                    		"SelectOrRejectOperator");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2912:2: ( (lv_name_2_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2913:1: (lv_name_2_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2913:1: (lv_name_2_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2914:3: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSelectOrRejectOperation6240); 

            			newLeafNode(lv_name_2_0, grammarAccess.getSelectOrRejectOperationAccess().getNameIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSelectOrRejectOperationRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"ID");
            	    

            }


            }

            otherlv_3=(Token)match(input,24,FOLLOW_24_in_ruleSelectOrRejectOperation6257); 

                	newLeafNode(otherlv_3, grammarAccess.getSelectOrRejectOperationAccess().getLeftParenthesisKeyword_3());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2934:1: ( (lv_expr_4_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2935:1: (lv_expr_4_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2935:1: (lv_expr_4_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2936:3: lv_expr_4_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getSelectOrRejectOperationAccess().getExprExpressionParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleSelectOrRejectOperation6278);
            lv_expr_4_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSelectOrRejectOperationRule());
            	        }
                   		set(
                   			current, 
                   			"expr",
                    		lv_expr_4_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleSelectOrRejectOperation6290); 

                	newLeafNode(otherlv_5, grammarAccess.getSelectOrRejectOperationAccess().getRightParenthesisKeyword_5());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2956:1: ( (lv_suffix_6_0= ruleSuffixExpression ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==49||LA50_0==52) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2957:1: (lv_suffix_6_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2957:1: (lv_suffix_6_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2958:3: lv_suffix_6_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSelectOrRejectOperationAccess().getSuffixSuffixExpressionParserRuleCall_6_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleSelectOrRejectOperation6311);
                    lv_suffix_6_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSelectOrRejectOperationRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_6_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSelectOrRejectOperation"


    // $ANTLR start "entryRuleCollectOrIterateOperation"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2982:1: entryRuleCollectOrIterateOperation returns [EObject current=null] : iv_ruleCollectOrIterateOperation= ruleCollectOrIterateOperation EOF ;
    public final EObject entryRuleCollectOrIterateOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectOrIterateOperation = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2983:2: (iv_ruleCollectOrIterateOperation= ruleCollectOrIterateOperation EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2984:2: iv_ruleCollectOrIterateOperation= ruleCollectOrIterateOperation EOF
            {
             newCompositeNode(grammarAccess.getCollectOrIterateOperationRule()); 
            pushFollow(FOLLOW_ruleCollectOrIterateOperation_in_entryRuleCollectOrIterateOperation6348);
            iv_ruleCollectOrIterateOperation=ruleCollectOrIterateOperation();

            state._fsp--;

             current =iv_ruleCollectOrIterateOperation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectOrIterateOperation6358); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCollectOrIterateOperation"


    // $ANTLR start "ruleCollectOrIterateOperation"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2991:1: ruleCollectOrIterateOperation returns [EObject current=null] : (otherlv_0= '->' ( (lv_op_1_0= ruleCollectOrIterateOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? ) ;
    public final EObject ruleCollectOrIterateOperation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_op_1_0 = null;

        EObject lv_expr_4_0 = null;

        EObject lv_suffix_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2994:28: ( (otherlv_0= '->' ( (lv_op_1_0= ruleCollectOrIterateOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2995:1: (otherlv_0= '->' ( (lv_op_1_0= ruleCollectOrIterateOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2995:1: (otherlv_0= '->' ( (lv_op_1_0= ruleCollectOrIterateOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2995:3: otherlv_0= '->' ( (lv_op_1_0= ruleCollectOrIterateOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )?
            {
            otherlv_0=(Token)match(input,52,FOLLOW_52_in_ruleCollectOrIterateOperation6395); 

                	newLeafNode(otherlv_0, grammarAccess.getCollectOrIterateOperationAccess().getHyphenMinusGreaterThanSignKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:2999:1: ( (lv_op_1_0= ruleCollectOrIterateOperator ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3000:1: (lv_op_1_0= ruleCollectOrIterateOperator )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3000:1: (lv_op_1_0= ruleCollectOrIterateOperator )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3001:3: lv_op_1_0= ruleCollectOrIterateOperator
            {
             
            	        newCompositeNode(grammarAccess.getCollectOrIterateOperationAccess().getOpCollectOrIterateOperatorEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleCollectOrIterateOperator_in_ruleCollectOrIterateOperation6416);
            lv_op_1_0=ruleCollectOrIterateOperator();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCollectOrIterateOperationRule());
            	        }
                   		set(
                   			current, 
                   			"op",
                    		lv_op_1_0, 
                    		"CollectOrIterateOperator");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3017:2: ( (lv_name_2_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3018:1: (lv_name_2_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3018:1: (lv_name_2_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3019:3: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleCollectOrIterateOperation6433); 

            			newLeafNode(lv_name_2_0, grammarAccess.getCollectOrIterateOperationAccess().getNameIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getCollectOrIterateOperationRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"ID");
            	    

            }


            }

            otherlv_3=(Token)match(input,24,FOLLOW_24_in_ruleCollectOrIterateOperation6450); 

                	newLeafNode(otherlv_3, grammarAccess.getCollectOrIterateOperationAccess().getLeftParenthesisKeyword_3());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3039:1: ( (lv_expr_4_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3040:1: (lv_expr_4_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3040:1: (lv_expr_4_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3041:3: lv_expr_4_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getCollectOrIterateOperationAccess().getExprExpressionParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleCollectOrIterateOperation6471);
            lv_expr_4_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCollectOrIterateOperationRule());
            	        }
                   		set(
                   			current, 
                   			"expr",
                    		lv_expr_4_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleCollectOrIterateOperation6483); 

                	newLeafNode(otherlv_5, grammarAccess.getCollectOrIterateOperationAccess().getRightParenthesisKeyword_5());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3061:1: ( (lv_suffix_6_0= ruleSuffixExpression ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==49||LA51_0==52) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3062:1: (lv_suffix_6_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3062:1: (lv_suffix_6_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3063:3: lv_suffix_6_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getCollectOrIterateOperationAccess().getSuffixSuffixExpressionParserRuleCall_6_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleCollectOrIterateOperation6504);
                    lv_suffix_6_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getCollectOrIterateOperationRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_6_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCollectOrIterateOperation"


    // $ANTLR start "entryRuleForAllOrExistsOrOneOperation"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3087:1: entryRuleForAllOrExistsOrOneOperation returns [EObject current=null] : iv_ruleForAllOrExistsOrOneOperation= ruleForAllOrExistsOrOneOperation EOF ;
    public final EObject entryRuleForAllOrExistsOrOneOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForAllOrExistsOrOneOperation = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3088:2: (iv_ruleForAllOrExistsOrOneOperation= ruleForAllOrExistsOrOneOperation EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3089:2: iv_ruleForAllOrExistsOrOneOperation= ruleForAllOrExistsOrOneOperation EOF
            {
             newCompositeNode(grammarAccess.getForAllOrExistsOrOneOperationRule()); 
            pushFollow(FOLLOW_ruleForAllOrExistsOrOneOperation_in_entryRuleForAllOrExistsOrOneOperation6541);
            iv_ruleForAllOrExistsOrOneOperation=ruleForAllOrExistsOrOneOperation();

            state._fsp--;

             current =iv_ruleForAllOrExistsOrOneOperation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleForAllOrExistsOrOneOperation6551); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForAllOrExistsOrOneOperation"


    // $ANTLR start "ruleForAllOrExistsOrOneOperation"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3096:1: ruleForAllOrExistsOrOneOperation returns [EObject current=null] : (otherlv_0= '->' ( (lv_op_1_0= ruleForAllOrExistsOrOneOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? ) ;
    public final EObject ruleForAllOrExistsOrOneOperation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_op_1_0 = null;

        EObject lv_expr_4_0 = null;

        EObject lv_suffix_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3099:28: ( (otherlv_0= '->' ( (lv_op_1_0= ruleForAllOrExistsOrOneOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3100:1: (otherlv_0= '->' ( (lv_op_1_0= ruleForAllOrExistsOrOneOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3100:1: (otherlv_0= '->' ( (lv_op_1_0= ruleForAllOrExistsOrOneOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3100:3: otherlv_0= '->' ( (lv_op_1_0= ruleForAllOrExistsOrOneOperator ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )?
            {
            otherlv_0=(Token)match(input,52,FOLLOW_52_in_ruleForAllOrExistsOrOneOperation6588); 

                	newLeafNode(otherlv_0, grammarAccess.getForAllOrExistsOrOneOperationAccess().getHyphenMinusGreaterThanSignKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3104:1: ( (lv_op_1_0= ruleForAllOrExistsOrOneOperator ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3105:1: (lv_op_1_0= ruleForAllOrExistsOrOneOperator )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3105:1: (lv_op_1_0= ruleForAllOrExistsOrOneOperator )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3106:3: lv_op_1_0= ruleForAllOrExistsOrOneOperator
            {
             
            	        newCompositeNode(grammarAccess.getForAllOrExistsOrOneOperationAccess().getOpForAllOrExistsOrOneOperatorEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleForAllOrExistsOrOneOperator_in_ruleForAllOrExistsOrOneOperation6609);
            lv_op_1_0=ruleForAllOrExistsOrOneOperator();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getForAllOrExistsOrOneOperationRule());
            	        }
                   		set(
                   			current, 
                   			"op",
                    		lv_op_1_0, 
                    		"ForAllOrExistsOrOneOperator");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3122:2: ( (lv_name_2_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3123:1: (lv_name_2_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3123:1: (lv_name_2_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3124:3: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleForAllOrExistsOrOneOperation6626); 

            			newLeafNode(lv_name_2_0, grammarAccess.getForAllOrExistsOrOneOperationAccess().getNameIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getForAllOrExistsOrOneOperationRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"ID");
            	    

            }


            }

            otherlv_3=(Token)match(input,24,FOLLOW_24_in_ruleForAllOrExistsOrOneOperation6643); 

                	newLeafNode(otherlv_3, grammarAccess.getForAllOrExistsOrOneOperationAccess().getLeftParenthesisKeyword_3());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3144:1: ( (lv_expr_4_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3145:1: (lv_expr_4_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3145:1: (lv_expr_4_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3146:3: lv_expr_4_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getForAllOrExistsOrOneOperationAccess().getExprExpressionParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleForAllOrExistsOrOneOperation6664);
            lv_expr_4_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getForAllOrExistsOrOneOperationRule());
            	        }
                   		set(
                   			current, 
                   			"expr",
                    		lv_expr_4_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleForAllOrExistsOrOneOperation6676); 

                	newLeafNode(otherlv_5, grammarAccess.getForAllOrExistsOrOneOperationAccess().getRightParenthesisKeyword_5());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3166:1: ( (lv_suffix_6_0= ruleSuffixExpression ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==49||LA52_0==52) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3167:1: (lv_suffix_6_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3167:1: (lv_suffix_6_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3168:3: lv_suffix_6_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getForAllOrExistsOrOneOperationAccess().getSuffixSuffixExpressionParserRuleCall_6_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleForAllOrExistsOrOneOperation6697);
                    lv_suffix_6_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForAllOrExistsOrOneOperationRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_6_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForAllOrExistsOrOneOperation"


    // $ANTLR start "entryRuleIsUniqueOperation"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3192:1: entryRuleIsUniqueOperation returns [EObject current=null] : iv_ruleIsUniqueOperation= ruleIsUniqueOperation EOF ;
    public final EObject entryRuleIsUniqueOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIsUniqueOperation = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3193:2: (iv_ruleIsUniqueOperation= ruleIsUniqueOperation EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3194:2: iv_ruleIsUniqueOperation= ruleIsUniqueOperation EOF
            {
             newCompositeNode(grammarAccess.getIsUniqueOperationRule()); 
            pushFollow(FOLLOW_ruleIsUniqueOperation_in_entryRuleIsUniqueOperation6734);
            iv_ruleIsUniqueOperation=ruleIsUniqueOperation();

            state._fsp--;

             current =iv_ruleIsUniqueOperation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIsUniqueOperation6744); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIsUniqueOperation"


    // $ANTLR start "ruleIsUniqueOperation"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3201:1: ruleIsUniqueOperation returns [EObject current=null] : (otherlv_0= '->' otherlv_1= 'isUnique' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? ) ;
    public final EObject ruleIsUniqueOperation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_expr_4_0 = null;

        EObject lv_suffix_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3204:28: ( (otherlv_0= '->' otherlv_1= 'isUnique' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3205:1: (otherlv_0= '->' otherlv_1= 'isUnique' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3205:1: (otherlv_0= '->' otherlv_1= 'isUnique' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3205:3: otherlv_0= '->' otherlv_1= 'isUnique' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ( (lv_suffix_6_0= ruleSuffixExpression ) )?
            {
            otherlv_0=(Token)match(input,52,FOLLOW_52_in_ruleIsUniqueOperation6781); 

                	newLeafNode(otherlv_0, grammarAccess.getIsUniqueOperationAccess().getHyphenMinusGreaterThanSignKeyword_0());
                
            otherlv_1=(Token)match(input,55,FOLLOW_55_in_ruleIsUniqueOperation6793); 

                	newLeafNode(otherlv_1, grammarAccess.getIsUniqueOperationAccess().getIsUniqueKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3213:1: ( (lv_name_2_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3214:1: (lv_name_2_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3214:1: (lv_name_2_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3215:3: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIsUniqueOperation6810); 

            			newLeafNode(lv_name_2_0, grammarAccess.getIsUniqueOperationAccess().getNameIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIsUniqueOperationRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"ID");
            	    

            }


            }

            otherlv_3=(Token)match(input,24,FOLLOW_24_in_ruleIsUniqueOperation6827); 

                	newLeafNode(otherlv_3, grammarAccess.getIsUniqueOperationAccess().getLeftParenthesisKeyword_3());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3235:1: ( (lv_expr_4_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3236:1: (lv_expr_4_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3236:1: (lv_expr_4_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3237:3: lv_expr_4_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getIsUniqueOperationAccess().getExprExpressionParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleIsUniqueOperation6848);
            lv_expr_4_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getIsUniqueOperationRule());
            	        }
                   		set(
                   			current, 
                   			"expr",
                    		lv_expr_4_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleIsUniqueOperation6860); 

                	newLeafNode(otherlv_5, grammarAccess.getIsUniqueOperationAccess().getRightParenthesisKeyword_5());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3257:1: ( (lv_suffix_6_0= ruleSuffixExpression ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==49||LA53_0==52) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3258:1: (lv_suffix_6_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3258:1: (lv_suffix_6_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3259:3: lv_suffix_6_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getIsUniqueOperationAccess().getSuffixSuffixExpressionParserRuleCall_6_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleIsUniqueOperation6881);
                    lv_suffix_6_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getIsUniqueOperationRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_6_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIsUniqueOperation"


    // $ANTLR start "entryRuleValueSpecification"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3283:1: entryRuleValueSpecification returns [EObject current=null] : iv_ruleValueSpecification= ruleValueSpecification EOF ;
    public final EObject entryRuleValueSpecification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueSpecification = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3284:2: (iv_ruleValueSpecification= ruleValueSpecification EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3285:2: iv_ruleValueSpecification= ruleValueSpecification EOF
            {
             newCompositeNode(grammarAccess.getValueSpecificationRule()); 
            pushFollow(FOLLOW_ruleValueSpecification_in_entryRuleValueSpecification6918);
            iv_ruleValueSpecification=ruleValueSpecification();

            state._fsp--;

             current =iv_ruleValueSpecification; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleValueSpecification6928); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValueSpecification"


    // $ANTLR start "ruleValueSpecification"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3292:1: ruleValueSpecification returns [EObject current=null] : (this_NameExpression_0= ruleNameExpression | this_LITERAL_1= ruleLITERAL | this_ThisExpression_2= ruleThisExpression | this_SuperInvocationExpression_3= ruleSuperInvocationExpression | this_InstanceCreationExpression_4= ruleInstanceCreationExpression | this_ParenthesizedExpression_5= ruleParenthesizedExpression | this_NullExpression_6= ruleNullExpression ) ;
    public final EObject ruleValueSpecification() throws RecognitionException {
        EObject current = null;

        EObject this_NameExpression_0 = null;

        EObject this_LITERAL_1 = null;

        EObject this_ThisExpression_2 = null;

        EObject this_SuperInvocationExpression_3 = null;

        EObject this_InstanceCreationExpression_4 = null;

        EObject this_ParenthesizedExpression_5 = null;

        EObject this_NullExpression_6 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3295:28: ( (this_NameExpression_0= ruleNameExpression | this_LITERAL_1= ruleLITERAL | this_ThisExpression_2= ruleThisExpression | this_SuperInvocationExpression_3= ruleSuperInvocationExpression | this_InstanceCreationExpression_4= ruleInstanceCreationExpression | this_ParenthesizedExpression_5= ruleParenthesizedExpression | this_NullExpression_6= ruleNullExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3296:1: (this_NameExpression_0= ruleNameExpression | this_LITERAL_1= ruleLITERAL | this_ThisExpression_2= ruleThisExpression | this_SuperInvocationExpression_3= ruleSuperInvocationExpression | this_InstanceCreationExpression_4= ruleInstanceCreationExpression | this_ParenthesizedExpression_5= ruleParenthesizedExpression | this_NullExpression_6= ruleNullExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3296:1: (this_NameExpression_0= ruleNameExpression | this_LITERAL_1= ruleLITERAL | this_ThisExpression_2= ruleThisExpression | this_SuperInvocationExpression_3= ruleSuperInvocationExpression | this_InstanceCreationExpression_4= ruleInstanceCreationExpression | this_ParenthesizedExpression_5= ruleParenthesizedExpression | this_NullExpression_6= ruleNullExpression )
            int alt54=7;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 17:
            case 18:
                {
                alt54=1;
                }
                break;
            case RULE_INTEGERVALUE:
            case RULE_STRING:
            case 16:
            case 87:
            case 88:
                {
                alt54=2;
                }
                break;
            case 57:
                {
                alt54=3;
                }
                break;
            case 58:
                {
                alt54=4;
                }
                break;
            case 59:
                {
                alt54=5;
                }
                break;
            case 24:
                {
                alt54=6;
                }
                break;
            case 56:
                {
                alt54=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }

            switch (alt54) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3297:5: this_NameExpression_0= ruleNameExpression
                    {
                     
                            newCompositeNode(grammarAccess.getValueSpecificationAccess().getNameExpressionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleNameExpression_in_ruleValueSpecification6975);
                    this_NameExpression_0=ruleNameExpression();

                    state._fsp--;

                     
                            current = this_NameExpression_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3307:5: this_LITERAL_1= ruleLITERAL
                    {
                     
                            newCompositeNode(grammarAccess.getValueSpecificationAccess().getLITERALParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLITERAL_in_ruleValueSpecification7002);
                    this_LITERAL_1=ruleLITERAL();

                    state._fsp--;

                     
                            current = this_LITERAL_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3317:5: this_ThisExpression_2= ruleThisExpression
                    {
                     
                            newCompositeNode(grammarAccess.getValueSpecificationAccess().getThisExpressionParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleThisExpression_in_ruleValueSpecification7029);
                    this_ThisExpression_2=ruleThisExpression();

                    state._fsp--;

                     
                            current = this_ThisExpression_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3327:5: this_SuperInvocationExpression_3= ruleSuperInvocationExpression
                    {
                     
                            newCompositeNode(grammarAccess.getValueSpecificationAccess().getSuperInvocationExpressionParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleSuperInvocationExpression_in_ruleValueSpecification7056);
                    this_SuperInvocationExpression_3=ruleSuperInvocationExpression();

                    state._fsp--;

                     
                            current = this_SuperInvocationExpression_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3337:5: this_InstanceCreationExpression_4= ruleInstanceCreationExpression
                    {
                     
                            newCompositeNode(grammarAccess.getValueSpecificationAccess().getInstanceCreationExpressionParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleInstanceCreationExpression_in_ruleValueSpecification7083);
                    this_InstanceCreationExpression_4=ruleInstanceCreationExpression();

                    state._fsp--;

                     
                            current = this_InstanceCreationExpression_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3347:5: this_ParenthesizedExpression_5= ruleParenthesizedExpression
                    {
                     
                            newCompositeNode(grammarAccess.getValueSpecificationAccess().getParenthesizedExpressionParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleParenthesizedExpression_in_ruleValueSpecification7110);
                    this_ParenthesizedExpression_5=ruleParenthesizedExpression();

                    state._fsp--;

                     
                            current = this_ParenthesizedExpression_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3357:5: this_NullExpression_6= ruleNullExpression
                    {
                     
                            newCompositeNode(grammarAccess.getValueSpecificationAccess().getNullExpressionParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleNullExpression_in_ruleValueSpecification7137);
                    this_NullExpression_6=ruleNullExpression();

                    state._fsp--;

                     
                            current = this_NullExpression_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValueSpecification"


    // $ANTLR start "entryRuleNonLiteralValueSpecification"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3373:1: entryRuleNonLiteralValueSpecification returns [EObject current=null] : iv_ruleNonLiteralValueSpecification= ruleNonLiteralValueSpecification EOF ;
    public final EObject entryRuleNonLiteralValueSpecification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNonLiteralValueSpecification = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3374:2: (iv_ruleNonLiteralValueSpecification= ruleNonLiteralValueSpecification EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3375:2: iv_ruleNonLiteralValueSpecification= ruleNonLiteralValueSpecification EOF
            {
             newCompositeNode(grammarAccess.getNonLiteralValueSpecificationRule()); 
            pushFollow(FOLLOW_ruleNonLiteralValueSpecification_in_entryRuleNonLiteralValueSpecification7172);
            iv_ruleNonLiteralValueSpecification=ruleNonLiteralValueSpecification();

            state._fsp--;

             current =iv_ruleNonLiteralValueSpecification; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNonLiteralValueSpecification7182); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNonLiteralValueSpecification"


    // $ANTLR start "ruleNonLiteralValueSpecification"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3382:1: ruleNonLiteralValueSpecification returns [EObject current=null] : (this_NameExpression_0= ruleNameExpression | this_ParenthesizedExpression_1= ruleParenthesizedExpression | this_InstanceCreationExpression_2= ruleInstanceCreationExpression | this_ThisExpression_3= ruleThisExpression | this_SuperInvocationExpression_4= ruleSuperInvocationExpression ) ;
    public final EObject ruleNonLiteralValueSpecification() throws RecognitionException {
        EObject current = null;

        EObject this_NameExpression_0 = null;

        EObject this_ParenthesizedExpression_1 = null;

        EObject this_InstanceCreationExpression_2 = null;

        EObject this_ThisExpression_3 = null;

        EObject this_SuperInvocationExpression_4 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3385:28: ( (this_NameExpression_0= ruleNameExpression | this_ParenthesizedExpression_1= ruleParenthesizedExpression | this_InstanceCreationExpression_2= ruleInstanceCreationExpression | this_ThisExpression_3= ruleThisExpression | this_SuperInvocationExpression_4= ruleSuperInvocationExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3386:1: (this_NameExpression_0= ruleNameExpression | this_ParenthesizedExpression_1= ruleParenthesizedExpression | this_InstanceCreationExpression_2= ruleInstanceCreationExpression | this_ThisExpression_3= ruleThisExpression | this_SuperInvocationExpression_4= ruleSuperInvocationExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3386:1: (this_NameExpression_0= ruleNameExpression | this_ParenthesizedExpression_1= ruleParenthesizedExpression | this_InstanceCreationExpression_2= ruleInstanceCreationExpression | this_ThisExpression_3= ruleThisExpression | this_SuperInvocationExpression_4= ruleSuperInvocationExpression )
            int alt55=5;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 17:
            case 18:
                {
                alt55=1;
                }
                break;
            case 24:
                {
                alt55=2;
                }
                break;
            case 59:
                {
                alt55=3;
                }
                break;
            case 57:
                {
                alt55=4;
                }
                break;
            case 58:
                {
                alt55=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }

            switch (alt55) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3387:5: this_NameExpression_0= ruleNameExpression
                    {
                     
                            newCompositeNode(grammarAccess.getNonLiteralValueSpecificationAccess().getNameExpressionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleNameExpression_in_ruleNonLiteralValueSpecification7229);
                    this_NameExpression_0=ruleNameExpression();

                    state._fsp--;

                     
                            current = this_NameExpression_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3397:5: this_ParenthesizedExpression_1= ruleParenthesizedExpression
                    {
                     
                            newCompositeNode(grammarAccess.getNonLiteralValueSpecificationAccess().getParenthesizedExpressionParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleParenthesizedExpression_in_ruleNonLiteralValueSpecification7256);
                    this_ParenthesizedExpression_1=ruleParenthesizedExpression();

                    state._fsp--;

                     
                            current = this_ParenthesizedExpression_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3407:5: this_InstanceCreationExpression_2= ruleInstanceCreationExpression
                    {
                     
                            newCompositeNode(grammarAccess.getNonLiteralValueSpecificationAccess().getInstanceCreationExpressionParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleInstanceCreationExpression_in_ruleNonLiteralValueSpecification7283);
                    this_InstanceCreationExpression_2=ruleInstanceCreationExpression();

                    state._fsp--;

                     
                            current = this_InstanceCreationExpression_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3417:5: this_ThisExpression_3= ruleThisExpression
                    {
                     
                            newCompositeNode(grammarAccess.getNonLiteralValueSpecificationAccess().getThisExpressionParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleThisExpression_in_ruleNonLiteralValueSpecification7310);
                    this_ThisExpression_3=ruleThisExpression();

                    state._fsp--;

                     
                            current = this_ThisExpression_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3427:5: this_SuperInvocationExpression_4= ruleSuperInvocationExpression
                    {
                     
                            newCompositeNode(grammarAccess.getNonLiteralValueSpecificationAccess().getSuperInvocationExpressionParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleSuperInvocationExpression_in_ruleNonLiteralValueSpecification7337);
                    this_SuperInvocationExpression_4=ruleSuperInvocationExpression();

                    state._fsp--;

                     
                            current = this_SuperInvocationExpression_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNonLiteralValueSpecification"


    // $ANTLR start "entryRuleParenthesizedExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3443:1: entryRuleParenthesizedExpression returns [EObject current=null] : iv_ruleParenthesizedExpression= ruleParenthesizedExpression EOF ;
    public final EObject entryRuleParenthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParenthesizedExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3444:2: (iv_ruleParenthesizedExpression= ruleParenthesizedExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3445:2: iv_ruleParenthesizedExpression= ruleParenthesizedExpression EOF
            {
             newCompositeNode(grammarAccess.getParenthesizedExpressionRule()); 
            pushFollow(FOLLOW_ruleParenthesizedExpression_in_entryRuleParenthesizedExpression7372);
            iv_ruleParenthesizedExpression=ruleParenthesizedExpression();

            state._fsp--;

             current =iv_ruleParenthesizedExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParenthesizedExpression7382); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParenthesizedExpression"


    // $ANTLR start "ruleParenthesizedExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3452:1: ruleParenthesizedExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_expOrTypeCast_1_0= ruleExpression ) ) otherlv_2= ')' ( ( (lv_casted_3_0= ruleNonLiteralValueSpecification ) ) | ( (lv_suffix_4_0= ruleSuffixExpression ) ) )? ) ;
    public final EObject ruleParenthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_expOrTypeCast_1_0 = null;

        EObject lv_casted_3_0 = null;

        EObject lv_suffix_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3455:28: ( (otherlv_0= '(' ( (lv_expOrTypeCast_1_0= ruleExpression ) ) otherlv_2= ')' ( ( (lv_casted_3_0= ruleNonLiteralValueSpecification ) ) | ( (lv_suffix_4_0= ruleSuffixExpression ) ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3456:1: (otherlv_0= '(' ( (lv_expOrTypeCast_1_0= ruleExpression ) ) otherlv_2= ')' ( ( (lv_casted_3_0= ruleNonLiteralValueSpecification ) ) | ( (lv_suffix_4_0= ruleSuffixExpression ) ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3456:1: (otherlv_0= '(' ( (lv_expOrTypeCast_1_0= ruleExpression ) ) otherlv_2= ')' ( ( (lv_casted_3_0= ruleNonLiteralValueSpecification ) ) | ( (lv_suffix_4_0= ruleSuffixExpression ) ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3456:3: otherlv_0= '(' ( (lv_expOrTypeCast_1_0= ruleExpression ) ) otherlv_2= ')' ( ( (lv_casted_3_0= ruleNonLiteralValueSpecification ) ) | ( (lv_suffix_4_0= ruleSuffixExpression ) ) )?
            {
            otherlv_0=(Token)match(input,24,FOLLOW_24_in_ruleParenthesizedExpression7419); 

                	newLeafNode(otherlv_0, grammarAccess.getParenthesizedExpressionAccess().getLeftParenthesisKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3460:1: ( (lv_expOrTypeCast_1_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3461:1: (lv_expOrTypeCast_1_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3461:1: (lv_expOrTypeCast_1_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3462:3: lv_expOrTypeCast_1_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getParenthesizedExpressionAccess().getExpOrTypeCastExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleParenthesizedExpression7440);
            lv_expOrTypeCast_1_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getParenthesizedExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"expOrTypeCast",
                    		lv_expOrTypeCast_1_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,25,FOLLOW_25_in_ruleParenthesizedExpression7452); 

                	newLeafNode(otherlv_2, grammarAccess.getParenthesizedExpressionAccess().getRightParenthesisKeyword_2());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3482:1: ( ( (lv_casted_3_0= ruleNonLiteralValueSpecification ) ) | ( (lv_suffix_4_0= ruleSuffixExpression ) ) )?
            int alt56=3;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==RULE_ID||(LA56_0>=17 && LA56_0<=18)||LA56_0==24||(LA56_0>=57 && LA56_0<=59)) ) {
                alt56=1;
            }
            else if ( (LA56_0==49||LA56_0==52) ) {
                alt56=2;
            }
            switch (alt56) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3482:2: ( (lv_casted_3_0= ruleNonLiteralValueSpecification ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3482:2: ( (lv_casted_3_0= ruleNonLiteralValueSpecification ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3483:1: (lv_casted_3_0= ruleNonLiteralValueSpecification )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3483:1: (lv_casted_3_0= ruleNonLiteralValueSpecification )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3484:3: lv_casted_3_0= ruleNonLiteralValueSpecification
                    {
                     
                    	        newCompositeNode(grammarAccess.getParenthesizedExpressionAccess().getCastedNonLiteralValueSpecificationParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleNonLiteralValueSpecification_in_ruleParenthesizedExpression7474);
                    lv_casted_3_0=ruleNonLiteralValueSpecification();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getParenthesizedExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"casted",
                            		lv_casted_3_0, 
                            		"NonLiteralValueSpecification");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3501:6: ( (lv_suffix_4_0= ruleSuffixExpression ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3501:6: ( (lv_suffix_4_0= ruleSuffixExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3502:1: (lv_suffix_4_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3502:1: (lv_suffix_4_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3503:3: lv_suffix_4_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getParenthesizedExpressionAccess().getSuffixSuffixExpressionParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleParenthesizedExpression7501);
                    lv_suffix_4_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getParenthesizedExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_4_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParenthesizedExpression"


    // $ANTLR start "entryRuleNullExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3527:1: entryRuleNullExpression returns [EObject current=null] : iv_ruleNullExpression= ruleNullExpression EOF ;
    public final EObject entryRuleNullExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3528:2: (iv_ruleNullExpression= ruleNullExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3529:2: iv_ruleNullExpression= ruleNullExpression EOF
            {
             newCompositeNode(grammarAccess.getNullExpressionRule()); 
            pushFollow(FOLLOW_ruleNullExpression_in_entryRuleNullExpression7539);
            iv_ruleNullExpression=ruleNullExpression();

            state._fsp--;

             current =iv_ruleNullExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullExpression7549); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNullExpression"


    // $ANTLR start "ruleNullExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3536:1: ruleNullExpression returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleNullExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3539:28: ( ( () otherlv_1= 'null' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3540:1: ( () otherlv_1= 'null' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3540:1: ( () otherlv_1= 'null' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3540:2: () otherlv_1= 'null'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3540:2: ()
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3541:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getNullExpressionAccess().getNullExpressionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,56,FOLLOW_56_in_ruleNullExpression7595); 

                	newLeafNode(otherlv_1, grammarAccess.getNullExpressionAccess().getNullKeyword_1());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNullExpression"


    // $ANTLR start "entryRuleThisExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3558:1: entryRuleThisExpression returns [EObject current=null] : iv_ruleThisExpression= ruleThisExpression EOF ;
    public final EObject entryRuleThisExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3559:2: (iv_ruleThisExpression= ruleThisExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3560:2: iv_ruleThisExpression= ruleThisExpression EOF
            {
             newCompositeNode(grammarAccess.getThisExpressionRule()); 
            pushFollow(FOLLOW_ruleThisExpression_in_entryRuleThisExpression7631);
            iv_ruleThisExpression=ruleThisExpression();

            state._fsp--;

             current =iv_ruleThisExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleThisExpression7641); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleThisExpression"


    // $ANTLR start "ruleThisExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3567:1: ruleThisExpression returns [EObject current=null] : ( () otherlv_1= 'this' ( (lv_suffix_2_0= ruleSuffixExpression ) )? ) ;
    public final EObject ruleThisExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_suffix_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3570:28: ( ( () otherlv_1= 'this' ( (lv_suffix_2_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3571:1: ( () otherlv_1= 'this' ( (lv_suffix_2_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3571:1: ( () otherlv_1= 'this' ( (lv_suffix_2_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3571:2: () otherlv_1= 'this' ( (lv_suffix_2_0= ruleSuffixExpression ) )?
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3571:2: ()
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3572:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getThisExpressionAccess().getThisExpressionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,57,FOLLOW_57_in_ruleThisExpression7687); 

                	newLeafNode(otherlv_1, grammarAccess.getThisExpressionAccess().getThisKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3581:1: ( (lv_suffix_2_0= ruleSuffixExpression ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==49||LA57_0==52) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3582:1: (lv_suffix_2_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3582:1: (lv_suffix_2_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3583:3: lv_suffix_2_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getThisExpressionAccess().getSuffixSuffixExpressionParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleThisExpression7708);
                    lv_suffix_2_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getThisExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_2_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleThisExpression"


    // $ANTLR start "entryRuleSuperInvocationExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3607:1: entryRuleSuperInvocationExpression returns [EObject current=null] : iv_ruleSuperInvocationExpression= ruleSuperInvocationExpression EOF ;
    public final EObject entryRuleSuperInvocationExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuperInvocationExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3608:2: (iv_ruleSuperInvocationExpression= ruleSuperInvocationExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3609:2: iv_ruleSuperInvocationExpression= ruleSuperInvocationExpression EOF
            {
             newCompositeNode(grammarAccess.getSuperInvocationExpressionRule()); 
            pushFollow(FOLLOW_ruleSuperInvocationExpression_in_entryRuleSuperInvocationExpression7745);
            iv_ruleSuperInvocationExpression=ruleSuperInvocationExpression();

            state._fsp--;

             current =iv_ruleSuperInvocationExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSuperInvocationExpression7755); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSuperInvocationExpression"


    // $ANTLR start "ruleSuperInvocationExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3616:1: ruleSuperInvocationExpression returns [EObject current=null] : (otherlv_0= 'super' ( ( (lv_tuple_1_0= ruleTuple ) ) | (otherlv_2= '.' ( (lv_operationName_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_4_0= ruleTuple ) ) ) ) ) ;
    public final EObject ruleSuperInvocationExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_tuple_1_0 = null;

        EObject lv_operationName_3_0 = null;

        EObject lv_tuple_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3619:28: ( (otherlv_0= 'super' ( ( (lv_tuple_1_0= ruleTuple ) ) | (otherlv_2= '.' ( (lv_operationName_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_4_0= ruleTuple ) ) ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3620:1: (otherlv_0= 'super' ( ( (lv_tuple_1_0= ruleTuple ) ) | (otherlv_2= '.' ( (lv_operationName_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_4_0= ruleTuple ) ) ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3620:1: (otherlv_0= 'super' ( ( (lv_tuple_1_0= ruleTuple ) ) | (otherlv_2= '.' ( (lv_operationName_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_4_0= ruleTuple ) ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3620:3: otherlv_0= 'super' ( ( (lv_tuple_1_0= ruleTuple ) ) | (otherlv_2= '.' ( (lv_operationName_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_4_0= ruleTuple ) ) ) )
            {
            otherlv_0=(Token)match(input,58,FOLLOW_58_in_ruleSuperInvocationExpression7792); 

                	newLeafNode(otherlv_0, grammarAccess.getSuperInvocationExpressionAccess().getSuperKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3624:1: ( ( (lv_tuple_1_0= ruleTuple ) ) | (otherlv_2= '.' ( (lv_operationName_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_4_0= ruleTuple ) ) ) )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==24) ) {
                alt58=1;
            }
            else if ( (LA58_0==49) ) {
                alt58=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3624:2: ( (lv_tuple_1_0= ruleTuple ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3624:2: ( (lv_tuple_1_0= ruleTuple ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3625:1: (lv_tuple_1_0= ruleTuple )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3625:1: (lv_tuple_1_0= ruleTuple )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3626:3: lv_tuple_1_0= ruleTuple
                    {
                     
                    	        newCompositeNode(grammarAccess.getSuperInvocationExpressionAccess().getTupleTupleParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTuple_in_ruleSuperInvocationExpression7814);
                    lv_tuple_1_0=ruleTuple();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSuperInvocationExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"tuple",
                            		lv_tuple_1_0, 
                            		"Tuple");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3643:6: (otherlv_2= '.' ( (lv_operationName_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_4_0= ruleTuple ) ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3643:6: (otherlv_2= '.' ( (lv_operationName_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_4_0= ruleTuple ) ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3643:8: otherlv_2= '.' ( (lv_operationName_3_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_4_0= ruleTuple ) )
                    {
                    otherlv_2=(Token)match(input,49,FOLLOW_49_in_ruleSuperInvocationExpression7833); 

                        	newLeafNode(otherlv_2, grammarAccess.getSuperInvocationExpressionAccess().getFullStopKeyword_1_1_0());
                        
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3647:1: ( (lv_operationName_3_0= ruleQualifiedNameWithBinding ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3648:1: (lv_operationName_3_0= ruleQualifiedNameWithBinding )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3648:1: (lv_operationName_3_0= ruleQualifiedNameWithBinding )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3649:3: lv_operationName_3_0= ruleQualifiedNameWithBinding
                    {
                     
                    	        newCompositeNode(grammarAccess.getSuperInvocationExpressionAccess().getOperationNameQualifiedNameWithBindingParserRuleCall_1_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_ruleSuperInvocationExpression7854);
                    lv_operationName_3_0=ruleQualifiedNameWithBinding();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSuperInvocationExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"operationName",
                            		lv_operationName_3_0, 
                            		"QualifiedNameWithBinding");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3665:2: ( (lv_tuple_4_0= ruleTuple ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3666:1: (lv_tuple_4_0= ruleTuple )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3666:1: (lv_tuple_4_0= ruleTuple )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3667:3: lv_tuple_4_0= ruleTuple
                    {
                     
                    	        newCompositeNode(grammarAccess.getSuperInvocationExpressionAccess().getTupleTupleParserRuleCall_1_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTuple_in_ruleSuperInvocationExpression7875);
                    lv_tuple_4_0=ruleTuple();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSuperInvocationExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"tuple",
                            		lv_tuple_4_0, 
                            		"Tuple");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSuperInvocationExpression"


    // $ANTLR start "entryRuleInstanceCreationExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3691:1: entryRuleInstanceCreationExpression returns [EObject current=null] : iv_ruleInstanceCreationExpression= ruleInstanceCreationExpression EOF ;
    public final EObject entryRuleInstanceCreationExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstanceCreationExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3692:2: (iv_ruleInstanceCreationExpression= ruleInstanceCreationExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3693:2: iv_ruleInstanceCreationExpression= ruleInstanceCreationExpression EOF
            {
             newCompositeNode(grammarAccess.getInstanceCreationExpressionRule()); 
            pushFollow(FOLLOW_ruleInstanceCreationExpression_in_entryRuleInstanceCreationExpression7913);
            iv_ruleInstanceCreationExpression=ruleInstanceCreationExpression();

            state._fsp--;

             current =iv_ruleInstanceCreationExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInstanceCreationExpression7923); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInstanceCreationExpression"


    // $ANTLR start "ruleInstanceCreationExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3700:1: ruleInstanceCreationExpression returns [EObject current=null] : (otherlv_0= 'new' ( (lv_constructor_1_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_2_0= ruleInstanceCreationTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? ) ;
    public final EObject ruleInstanceCreationExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_constructor_1_0 = null;

        EObject lv_tuple_2_0 = null;

        EObject lv_suffix_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3703:28: ( (otherlv_0= 'new' ( (lv_constructor_1_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_2_0= ruleInstanceCreationTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3704:1: (otherlv_0= 'new' ( (lv_constructor_1_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_2_0= ruleInstanceCreationTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3704:1: (otherlv_0= 'new' ( (lv_constructor_1_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_2_0= ruleInstanceCreationTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3704:3: otherlv_0= 'new' ( (lv_constructor_1_0= ruleQualifiedNameWithBinding ) ) ( (lv_tuple_2_0= ruleInstanceCreationTuple ) ) ( (lv_suffix_3_0= ruleSuffixExpression ) )?
            {
            otherlv_0=(Token)match(input,59,FOLLOW_59_in_ruleInstanceCreationExpression7960); 

                	newLeafNode(otherlv_0, grammarAccess.getInstanceCreationExpressionAccess().getNewKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3708:1: ( (lv_constructor_1_0= ruleQualifiedNameWithBinding ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3709:1: (lv_constructor_1_0= ruleQualifiedNameWithBinding )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3709:1: (lv_constructor_1_0= ruleQualifiedNameWithBinding )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3710:3: lv_constructor_1_0= ruleQualifiedNameWithBinding
            {
             
            	        newCompositeNode(grammarAccess.getInstanceCreationExpressionAccess().getConstructorQualifiedNameWithBindingParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_ruleInstanceCreationExpression7981);
            lv_constructor_1_0=ruleQualifiedNameWithBinding();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getInstanceCreationExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"constructor",
                    		lv_constructor_1_0, 
                    		"QualifiedNameWithBinding");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3726:2: ( (lv_tuple_2_0= ruleInstanceCreationTuple ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3727:1: (lv_tuple_2_0= ruleInstanceCreationTuple )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3727:1: (lv_tuple_2_0= ruleInstanceCreationTuple )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3728:3: lv_tuple_2_0= ruleInstanceCreationTuple
            {
             
            	        newCompositeNode(grammarAccess.getInstanceCreationExpressionAccess().getTupleInstanceCreationTupleParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleInstanceCreationTuple_in_ruleInstanceCreationExpression8002);
            lv_tuple_2_0=ruleInstanceCreationTuple();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getInstanceCreationExpressionRule());
            	        }
                   		set(
                   			current, 
                   			"tuple",
                    		lv_tuple_2_0, 
                    		"InstanceCreationTuple");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3744:2: ( (lv_suffix_3_0= ruleSuffixExpression ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==49||LA59_0==52) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3745:1: (lv_suffix_3_0= ruleSuffixExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3745:1: (lv_suffix_3_0= ruleSuffixExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3746:3: lv_suffix_3_0= ruleSuffixExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getInstanceCreationExpressionAccess().getSuffixSuffixExpressionParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuffixExpression_in_ruleInstanceCreationExpression8023);
                    lv_suffix_3_0=ruleSuffixExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInstanceCreationExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"suffix",
                            		lv_suffix_3_0, 
                            		"SuffixExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInstanceCreationExpression"


    // $ANTLR start "entryRuleInstanceCreationTuple"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3770:1: entryRuleInstanceCreationTuple returns [EObject current=null] : iv_ruleInstanceCreationTuple= ruleInstanceCreationTuple EOF ;
    public final EObject entryRuleInstanceCreationTuple() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstanceCreationTuple = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3771:2: (iv_ruleInstanceCreationTuple= ruleInstanceCreationTuple EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3772:2: iv_ruleInstanceCreationTuple= ruleInstanceCreationTuple EOF
            {
             newCompositeNode(grammarAccess.getInstanceCreationTupleRule()); 
            pushFollow(FOLLOW_ruleInstanceCreationTuple_in_entryRuleInstanceCreationTuple8060);
            iv_ruleInstanceCreationTuple=ruleInstanceCreationTuple();

            state._fsp--;

             current =iv_ruleInstanceCreationTuple; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInstanceCreationTuple8070); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInstanceCreationTuple"


    // $ANTLR start "ruleInstanceCreationTuple"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3779:1: ruleInstanceCreationTuple returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement ) ) (otherlv_3= ',' ( (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement ) ) )* )? otherlv_5= ')' ) ;
    public final EObject ruleInstanceCreationTuple() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_instanceCreationTupleElement_2_0 = null;

        EObject lv_instanceCreationTupleElement_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3782:28: ( ( () otherlv_1= '(' ( ( (lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement ) ) (otherlv_3= ',' ( (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement ) ) )* )? otherlv_5= ')' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3783:1: ( () otherlv_1= '(' ( ( (lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement ) ) (otherlv_3= ',' ( (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement ) ) )* )? otherlv_5= ')' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3783:1: ( () otherlv_1= '(' ( ( (lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement ) ) (otherlv_3= ',' ( (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement ) ) )* )? otherlv_5= ')' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3783:2: () otherlv_1= '(' ( ( (lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement ) ) (otherlv_3= ',' ( (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement ) ) )* )? otherlv_5= ')'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3783:2: ()
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3784:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInstanceCreationTupleAccess().getInstanceCreationTupleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleInstanceCreationTuple8116); 

                	newLeafNode(otherlv_1, grammarAccess.getInstanceCreationTupleAccess().getLeftParenthesisKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3793:1: ( ( (lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement ) ) (otherlv_3= ',' ( (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement ) ) )* )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==RULE_ID) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3793:2: ( (lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement ) ) (otherlv_3= ',' ( (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement ) ) )*
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3793:2: ( (lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3794:1: (lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3794:1: (lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3795:3: lv_instanceCreationTupleElement_2_0= ruleInstanceCreationTupleElement
                    {
                     
                    	        newCompositeNode(grammarAccess.getInstanceCreationTupleAccess().getInstanceCreationTupleElementInstanceCreationTupleElementParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleInstanceCreationTupleElement_in_ruleInstanceCreationTuple8138);
                    lv_instanceCreationTupleElement_2_0=ruleInstanceCreationTupleElement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInstanceCreationTupleRule());
                    	        }
                           		add(
                           			current, 
                           			"instanceCreationTupleElement",
                            		lv_instanceCreationTupleElement_2_0, 
                            		"InstanceCreationTupleElement");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3811:2: (otherlv_3= ',' ( (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement ) ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==21) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3811:4: otherlv_3= ',' ( (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement ) )
                    	    {
                    	    otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleInstanceCreationTuple8151); 

                    	        	newLeafNode(otherlv_3, grammarAccess.getInstanceCreationTupleAccess().getCommaKeyword_2_1_0());
                    	        
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3815:1: ( (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement ) )
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3816:1: (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement )
                    	    {
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3816:1: (lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement )
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3817:3: lv_instanceCreationTupleElement_4_0= ruleInstanceCreationTupleElement
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getInstanceCreationTupleAccess().getInstanceCreationTupleElementInstanceCreationTupleElementParserRuleCall_2_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleInstanceCreationTupleElement_in_ruleInstanceCreationTuple8172);
                    	    lv_instanceCreationTupleElement_4_0=ruleInstanceCreationTupleElement();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getInstanceCreationTupleRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"instanceCreationTupleElement",
                    	            		lv_instanceCreationTupleElement_4_0, 
                    	            		"InstanceCreationTupleElement");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleInstanceCreationTuple8188); 

                	newLeafNode(otherlv_5, grammarAccess.getInstanceCreationTupleAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInstanceCreationTuple"


    // $ANTLR start "entryRuleInstanceCreationTupleElement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3845:1: entryRuleInstanceCreationTupleElement returns [EObject current=null] : iv_ruleInstanceCreationTupleElement= ruleInstanceCreationTupleElement EOF ;
    public final EObject entryRuleInstanceCreationTupleElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstanceCreationTupleElement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3846:2: (iv_ruleInstanceCreationTupleElement= ruleInstanceCreationTupleElement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3847:2: iv_ruleInstanceCreationTupleElement= ruleInstanceCreationTupleElement EOF
            {
             newCompositeNode(grammarAccess.getInstanceCreationTupleElementRule()); 
            pushFollow(FOLLOW_ruleInstanceCreationTupleElement_in_entryRuleInstanceCreationTupleElement8224);
            iv_ruleInstanceCreationTupleElement=ruleInstanceCreationTupleElement();

            state._fsp--;

             current =iv_ruleInstanceCreationTupleElement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInstanceCreationTupleElement8234); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInstanceCreationTupleElement"


    // $ANTLR start "ruleInstanceCreationTupleElement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3854:1: ruleInstanceCreationTupleElement returns [EObject current=null] : ( ( (lv_role_0_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_object_2_0= ruleExpression ) ) ) ;
    public final EObject ruleInstanceCreationTupleElement() throws RecognitionException {
        EObject current = null;

        Token lv_role_0_0=null;
        Token otherlv_1=null;
        EObject lv_object_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3857:28: ( ( ( (lv_role_0_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_object_2_0= ruleExpression ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3858:1: ( ( (lv_role_0_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_object_2_0= ruleExpression ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3858:1: ( ( (lv_role_0_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_object_2_0= ruleExpression ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3858:2: ( (lv_role_0_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_object_2_0= ruleExpression ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3858:2: ( (lv_role_0_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3859:1: (lv_role_0_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3859:1: (lv_role_0_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3860:3: lv_role_0_0= RULE_ID
            {
            lv_role_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInstanceCreationTupleElement8276); 

            			newLeafNode(lv_role_0_0, grammarAccess.getInstanceCreationTupleElementAccess().getRoleIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getInstanceCreationTupleElementRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"role",
                    		lv_role_0_0, 
                    		"ID");
            	    

            }


            }

            otherlv_1=(Token)match(input,23,FOLLOW_23_in_ruleInstanceCreationTupleElement8293); 

                	newLeafNode(otherlv_1, grammarAccess.getInstanceCreationTupleElementAccess().getEqualsSignGreaterThanSignKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3880:1: ( (lv_object_2_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3881:1: (lv_object_2_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3881:1: (lv_object_2_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3882:3: lv_object_2_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getInstanceCreationTupleElementAccess().getObjectExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleInstanceCreationTupleElement8314);
            lv_object_2_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getInstanceCreationTupleElementRule());
            	        }
                   		set(
                   			current, 
                   			"object",
                    		lv_object_2_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInstanceCreationTupleElement"


    // $ANTLR start "entryRuleSequenceConstructionOrAccessCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3906:1: entryRuleSequenceConstructionOrAccessCompletion returns [EObject current=null] : iv_ruleSequenceConstructionOrAccessCompletion= ruleSequenceConstructionOrAccessCompletion EOF ;
    public final EObject entryRuleSequenceConstructionOrAccessCompletion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSequenceConstructionOrAccessCompletion = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3907:2: (iv_ruleSequenceConstructionOrAccessCompletion= ruleSequenceConstructionOrAccessCompletion EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3908:2: iv_ruleSequenceConstructionOrAccessCompletion= ruleSequenceConstructionOrAccessCompletion EOF
            {
             newCompositeNode(grammarAccess.getSequenceConstructionOrAccessCompletionRule()); 
            pushFollow(FOLLOW_ruleSequenceConstructionOrAccessCompletion_in_entryRuleSequenceConstructionOrAccessCompletion8350);
            iv_ruleSequenceConstructionOrAccessCompletion=ruleSequenceConstructionOrAccessCompletion();

            state._fsp--;

             current =iv_ruleSequenceConstructionOrAccessCompletion; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSequenceConstructionOrAccessCompletion8360); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSequenceConstructionOrAccessCompletion"


    // $ANTLR start "ruleSequenceConstructionOrAccessCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3915:1: ruleSequenceConstructionOrAccessCompletion returns [EObject current=null] : ( ( ( (lv_multiplicityIndicator_0_0= '[' ) ) ( ( (lv_accessCompletion_1_0= ruleAccessCompletion ) ) | ( (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion ) ) ) ) | ( (lv_expression_3_0= ruleSequenceConstructionExpression ) ) ) ;
    public final EObject ruleSequenceConstructionOrAccessCompletion() throws RecognitionException {
        EObject current = null;

        Token lv_multiplicityIndicator_0_0=null;
        EObject lv_accessCompletion_1_0 = null;

        EObject lv_sequenceCompletion_2_0 = null;

        EObject lv_expression_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3918:28: ( ( ( ( (lv_multiplicityIndicator_0_0= '[' ) ) ( ( (lv_accessCompletion_1_0= ruleAccessCompletion ) ) | ( (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion ) ) ) ) | ( (lv_expression_3_0= ruleSequenceConstructionExpression ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3919:1: ( ( ( (lv_multiplicityIndicator_0_0= '[' ) ) ( ( (lv_accessCompletion_1_0= ruleAccessCompletion ) ) | ( (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion ) ) ) ) | ( (lv_expression_3_0= ruleSequenceConstructionExpression ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3919:1: ( ( ( (lv_multiplicityIndicator_0_0= '[' ) ) ( ( (lv_accessCompletion_1_0= ruleAccessCompletion ) ) | ( (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion ) ) ) ) | ( (lv_expression_3_0= ruleSequenceConstructionExpression ) ) )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==50) ) {
                alt63=1;
            }
            else if ( (LA63_0==60) ) {
                alt63=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3919:2: ( ( (lv_multiplicityIndicator_0_0= '[' ) ) ( ( (lv_accessCompletion_1_0= ruleAccessCompletion ) ) | ( (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion ) ) ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3919:2: ( ( (lv_multiplicityIndicator_0_0= '[' ) ) ( ( (lv_accessCompletion_1_0= ruleAccessCompletion ) ) | ( (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion ) ) ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3919:3: ( (lv_multiplicityIndicator_0_0= '[' ) ) ( ( (lv_accessCompletion_1_0= ruleAccessCompletion ) ) | ( (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion ) ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3919:3: ( (lv_multiplicityIndicator_0_0= '[' ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3920:1: (lv_multiplicityIndicator_0_0= '[' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3920:1: (lv_multiplicityIndicator_0_0= '[' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3921:3: lv_multiplicityIndicator_0_0= '['
                    {
                    lv_multiplicityIndicator_0_0=(Token)match(input,50,FOLLOW_50_in_ruleSequenceConstructionOrAccessCompletion8404); 

                            newLeafNode(lv_multiplicityIndicator_0_0, grammarAccess.getSequenceConstructionOrAccessCompletionAccess().getMultiplicityIndicatorLeftSquareBracketKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSequenceConstructionOrAccessCompletionRule());
                    	        }
                           		setWithLastConsumed(current, "multiplicityIndicator", true, "[");
                    	    

                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3934:2: ( ( (lv_accessCompletion_1_0= ruleAccessCompletion ) ) | ( (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion ) ) )
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( ((LA62_0>=RULE_INTEGERVALUE && LA62_0<=RULE_ID)||(LA62_0>=16 && LA62_0<=18)||LA62_0==24||(LA62_0>=42 && LA62_0<=43)||(LA62_0>=46 && LA62_0<=48)||(LA62_0>=56 && LA62_0<=59)||(LA62_0>=87 && LA62_0<=88)) ) {
                        alt62=1;
                    }
                    else if ( (LA62_0==51) ) {
                        alt62=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 62, 0, input);

                        throw nvae;
                    }
                    switch (alt62) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3934:3: ( (lv_accessCompletion_1_0= ruleAccessCompletion ) )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3934:3: ( (lv_accessCompletion_1_0= ruleAccessCompletion ) )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3935:1: (lv_accessCompletion_1_0= ruleAccessCompletion )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3935:1: (lv_accessCompletion_1_0= ruleAccessCompletion )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3936:3: lv_accessCompletion_1_0= ruleAccessCompletion
                            {
                             
                            	        newCompositeNode(grammarAccess.getSequenceConstructionOrAccessCompletionAccess().getAccessCompletionAccessCompletionParserRuleCall_0_1_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleAccessCompletion_in_ruleSequenceConstructionOrAccessCompletion8439);
                            lv_accessCompletion_1_0=ruleAccessCompletion();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getSequenceConstructionOrAccessCompletionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"accessCompletion",
                                    		lv_accessCompletion_1_0, 
                                    		"AccessCompletion");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3953:6: ( (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion ) )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3953:6: ( (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion ) )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3954:1: (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3954:1: (lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3955:3: lv_sequenceCompletion_2_0= rulePartialSequenceConstructionCompletion
                            {
                             
                            	        newCompositeNode(grammarAccess.getSequenceConstructionOrAccessCompletionAccess().getSequenceCompletionPartialSequenceConstructionCompletionParserRuleCall_0_1_1_0()); 
                            	    
                            pushFollow(FOLLOW_rulePartialSequenceConstructionCompletion_in_ruleSequenceConstructionOrAccessCompletion8466);
                            lv_sequenceCompletion_2_0=rulePartialSequenceConstructionCompletion();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getSequenceConstructionOrAccessCompletionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"sequenceCompletion",
                                    		lv_sequenceCompletion_2_0, 
                                    		"PartialSequenceConstructionCompletion");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3972:6: ( (lv_expression_3_0= ruleSequenceConstructionExpression ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3972:6: ( (lv_expression_3_0= ruleSequenceConstructionExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3973:1: (lv_expression_3_0= ruleSequenceConstructionExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3973:1: (lv_expression_3_0= ruleSequenceConstructionExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3974:3: lv_expression_3_0= ruleSequenceConstructionExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSequenceConstructionOrAccessCompletionAccess().getExpressionSequenceConstructionExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSequenceConstructionExpression_in_ruleSequenceConstructionOrAccessCompletion8495);
                    lv_expression_3_0=ruleSequenceConstructionExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSequenceConstructionOrAccessCompletionRule());
                    	        }
                           		set(
                           			current, 
                           			"expression",
                            		lv_expression_3_0, 
                            		"SequenceConstructionExpression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSequenceConstructionOrAccessCompletion"


    // $ANTLR start "entryRuleAccessCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3998:1: entryRuleAccessCompletion returns [EObject current=null] : iv_ruleAccessCompletion= ruleAccessCompletion EOF ;
    public final EObject entryRuleAccessCompletion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAccessCompletion = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:3999:2: (iv_ruleAccessCompletion= ruleAccessCompletion EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4000:2: iv_ruleAccessCompletion= ruleAccessCompletion EOF
            {
             newCompositeNode(grammarAccess.getAccessCompletionRule()); 
            pushFollow(FOLLOW_ruleAccessCompletion_in_entryRuleAccessCompletion8531);
            iv_ruleAccessCompletion=ruleAccessCompletion();

            state._fsp--;

             current =iv_ruleAccessCompletion; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAccessCompletion8541); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAccessCompletion"


    // $ANTLR start "ruleAccessCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4007:1: ruleAccessCompletion returns [EObject current=null] : ( ( (lv_accessIndex_0_0= ruleExpression ) ) otherlv_1= ']' ) ;
    public final EObject ruleAccessCompletion() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_accessIndex_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4010:28: ( ( ( (lv_accessIndex_0_0= ruleExpression ) ) otherlv_1= ']' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4011:1: ( ( (lv_accessIndex_0_0= ruleExpression ) ) otherlv_1= ']' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4011:1: ( ( (lv_accessIndex_0_0= ruleExpression ) ) otherlv_1= ']' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4011:2: ( (lv_accessIndex_0_0= ruleExpression ) ) otherlv_1= ']'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4011:2: ( (lv_accessIndex_0_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4012:1: (lv_accessIndex_0_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4012:1: (lv_accessIndex_0_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4013:3: lv_accessIndex_0_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getAccessCompletionAccess().getAccessIndexExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleAccessCompletion8587);
            lv_accessIndex_0_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAccessCompletionRule());
            	        }
                   		set(
                   			current, 
                   			"accessIndex",
                    		lv_accessIndex_0_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,51,FOLLOW_51_in_ruleAccessCompletion8599); 

                	newLeafNode(otherlv_1, grammarAccess.getAccessCompletionAccess().getRightSquareBracketKeyword_1());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAccessCompletion"


    // $ANTLR start "entryRulePartialSequenceConstructionCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4041:1: entryRulePartialSequenceConstructionCompletion returns [EObject current=null] : iv_rulePartialSequenceConstructionCompletion= rulePartialSequenceConstructionCompletion EOF ;
    public final EObject entryRulePartialSequenceConstructionCompletion() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePartialSequenceConstructionCompletion = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4042:2: (iv_rulePartialSequenceConstructionCompletion= rulePartialSequenceConstructionCompletion EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4043:2: iv_rulePartialSequenceConstructionCompletion= rulePartialSequenceConstructionCompletion EOF
            {
             newCompositeNode(grammarAccess.getPartialSequenceConstructionCompletionRule()); 
            pushFollow(FOLLOW_rulePartialSequenceConstructionCompletion_in_entryRulePartialSequenceConstructionCompletion8635);
            iv_rulePartialSequenceConstructionCompletion=rulePartialSequenceConstructionCompletion();

            state._fsp--;

             current =iv_rulePartialSequenceConstructionCompletion; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePartialSequenceConstructionCompletion8645); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePartialSequenceConstructionCompletion"


    // $ANTLR start "rulePartialSequenceConstructionCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4050:1: rulePartialSequenceConstructionCompletion returns [EObject current=null] : (otherlv_0= ']' ( (lv_expression_1_0= ruleSequenceConstructionExpression ) ) ) ;
    public final EObject rulePartialSequenceConstructionCompletion() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_expression_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4053:28: ( (otherlv_0= ']' ( (lv_expression_1_0= ruleSequenceConstructionExpression ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4054:1: (otherlv_0= ']' ( (lv_expression_1_0= ruleSequenceConstructionExpression ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4054:1: (otherlv_0= ']' ( (lv_expression_1_0= ruleSequenceConstructionExpression ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4054:3: otherlv_0= ']' ( (lv_expression_1_0= ruleSequenceConstructionExpression ) )
            {
            otherlv_0=(Token)match(input,51,FOLLOW_51_in_rulePartialSequenceConstructionCompletion8682); 

                	newLeafNode(otherlv_0, grammarAccess.getPartialSequenceConstructionCompletionAccess().getRightSquareBracketKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4058:1: ( (lv_expression_1_0= ruleSequenceConstructionExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4059:1: (lv_expression_1_0= ruleSequenceConstructionExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4059:1: (lv_expression_1_0= ruleSequenceConstructionExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4060:3: lv_expression_1_0= ruleSequenceConstructionExpression
            {
             
            	        newCompositeNode(grammarAccess.getPartialSequenceConstructionCompletionAccess().getExpressionSequenceConstructionExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleSequenceConstructionExpression_in_rulePartialSequenceConstructionCompletion8703);
            lv_expression_1_0=ruleSequenceConstructionExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPartialSequenceConstructionCompletionRule());
            	        }
                   		set(
                   			current, 
                   			"expression",
                    		lv_expression_1_0, 
                    		"SequenceConstructionExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePartialSequenceConstructionCompletion"


    // $ANTLR start "entryRuleSequenceConstructionExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4084:1: entryRuleSequenceConstructionExpression returns [EObject current=null] : iv_ruleSequenceConstructionExpression= ruleSequenceConstructionExpression EOF ;
    public final EObject entryRuleSequenceConstructionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSequenceConstructionExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4085:2: (iv_ruleSequenceConstructionExpression= ruleSequenceConstructionExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4086:2: iv_ruleSequenceConstructionExpression= ruleSequenceConstructionExpression EOF
            {
             newCompositeNode(grammarAccess.getSequenceConstructionExpressionRule()); 
            pushFollow(FOLLOW_ruleSequenceConstructionExpression_in_entryRuleSequenceConstructionExpression8739);
            iv_ruleSequenceConstructionExpression=ruleSequenceConstructionExpression();

            state._fsp--;

             current =iv_ruleSequenceConstructionExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSequenceConstructionExpression8749); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSequenceConstructionExpression"


    // $ANTLR start "ruleSequenceConstructionExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4093:1: ruleSequenceConstructionExpression returns [EObject current=null] : (otherlv_0= '{' ( (lv_sequenceElement_1_0= ruleSequenceElement ) ) ( (otherlv_2= ',' ( (lv_sequenceElement_3_0= ruleSequenceElement ) ) )* | (otherlv_4= '..' ( (lv_rangeUpper_5_0= ruleExpression ) ) ) ) otherlv_6= '}' ) ;
    public final EObject ruleSequenceConstructionExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_sequenceElement_1_0 = null;

        EObject lv_sequenceElement_3_0 = null;

        EObject lv_rangeUpper_5_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4096:28: ( (otherlv_0= '{' ( (lv_sequenceElement_1_0= ruleSequenceElement ) ) ( (otherlv_2= ',' ( (lv_sequenceElement_3_0= ruleSequenceElement ) ) )* | (otherlv_4= '..' ( (lv_rangeUpper_5_0= ruleExpression ) ) ) ) otherlv_6= '}' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4097:1: (otherlv_0= '{' ( (lv_sequenceElement_1_0= ruleSequenceElement ) ) ( (otherlv_2= ',' ( (lv_sequenceElement_3_0= ruleSequenceElement ) ) )* | (otherlv_4= '..' ( (lv_rangeUpper_5_0= ruleExpression ) ) ) ) otherlv_6= '}' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4097:1: (otherlv_0= '{' ( (lv_sequenceElement_1_0= ruleSequenceElement ) ) ( (otherlv_2= ',' ( (lv_sequenceElement_3_0= ruleSequenceElement ) ) )* | (otherlv_4= '..' ( (lv_rangeUpper_5_0= ruleExpression ) ) ) ) otherlv_6= '}' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4097:3: otherlv_0= '{' ( (lv_sequenceElement_1_0= ruleSequenceElement ) ) ( (otherlv_2= ',' ( (lv_sequenceElement_3_0= ruleSequenceElement ) ) )* | (otherlv_4= '..' ( (lv_rangeUpper_5_0= ruleExpression ) ) ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,60,FOLLOW_60_in_ruleSequenceConstructionExpression8786); 

                	newLeafNode(otherlv_0, grammarAccess.getSequenceConstructionExpressionAccess().getLeftCurlyBracketKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4101:1: ( (lv_sequenceElement_1_0= ruleSequenceElement ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4102:1: (lv_sequenceElement_1_0= ruleSequenceElement )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4102:1: (lv_sequenceElement_1_0= ruleSequenceElement )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4103:3: lv_sequenceElement_1_0= ruleSequenceElement
            {
             
            	        newCompositeNode(grammarAccess.getSequenceConstructionExpressionAccess().getSequenceElementSequenceElementParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleSequenceElement_in_ruleSequenceConstructionExpression8807);
            lv_sequenceElement_1_0=ruleSequenceElement();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSequenceConstructionExpressionRule());
            	        }
                   		add(
                   			current, 
                   			"sequenceElement",
                    		lv_sequenceElement_1_0, 
                    		"SequenceElement");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4119:2: ( (otherlv_2= ',' ( (lv_sequenceElement_3_0= ruleSequenceElement ) ) )* | (otherlv_4= '..' ( (lv_rangeUpper_5_0= ruleExpression ) ) ) )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==21||LA65_0==62) ) {
                alt65=1;
            }
            else if ( (LA65_0==61) ) {
                alt65=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }
            switch (alt65) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4119:3: (otherlv_2= ',' ( (lv_sequenceElement_3_0= ruleSequenceElement ) ) )*
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4119:3: (otherlv_2= ',' ( (lv_sequenceElement_3_0= ruleSequenceElement ) ) )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==21) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4119:5: otherlv_2= ',' ( (lv_sequenceElement_3_0= ruleSequenceElement ) )
                    	    {
                    	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleSequenceConstructionExpression8821); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getSequenceConstructionExpressionAccess().getCommaKeyword_2_0_0());
                    	        
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4123:1: ( (lv_sequenceElement_3_0= ruleSequenceElement ) )
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4124:1: (lv_sequenceElement_3_0= ruleSequenceElement )
                    	    {
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4124:1: (lv_sequenceElement_3_0= ruleSequenceElement )
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4125:3: lv_sequenceElement_3_0= ruleSequenceElement
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSequenceConstructionExpressionAccess().getSequenceElementSequenceElementParserRuleCall_2_0_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleSequenceElement_in_ruleSequenceConstructionExpression8842);
                    	    lv_sequenceElement_3_0=ruleSequenceElement();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getSequenceConstructionExpressionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"sequenceElement",
                    	            		lv_sequenceElement_3_0, 
                    	            		"SequenceElement");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop64;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4142:6: (otherlv_4= '..' ( (lv_rangeUpper_5_0= ruleExpression ) ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4142:6: (otherlv_4= '..' ( (lv_rangeUpper_5_0= ruleExpression ) ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4142:8: otherlv_4= '..' ( (lv_rangeUpper_5_0= ruleExpression ) )
                    {
                    otherlv_4=(Token)match(input,61,FOLLOW_61_in_ruleSequenceConstructionExpression8863); 

                        	newLeafNode(otherlv_4, grammarAccess.getSequenceConstructionExpressionAccess().getFullStopFullStopKeyword_2_1_0());
                        
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4146:1: ( (lv_rangeUpper_5_0= ruleExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4147:1: (lv_rangeUpper_5_0= ruleExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4147:1: (lv_rangeUpper_5_0= ruleExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4148:3: lv_rangeUpper_5_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSequenceConstructionExpressionAccess().getRangeUpperExpressionParserRuleCall_2_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleSequenceConstructionExpression8884);
                    lv_rangeUpper_5_0=ruleExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSequenceConstructionExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"rangeUpper",
                            		lv_rangeUpper_5_0, 
                            		"Expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,62,FOLLOW_62_in_ruleSequenceConstructionExpression8898); 

                	newLeafNode(otherlv_6, grammarAccess.getSequenceConstructionExpressionAccess().getRightCurlyBracketKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSequenceConstructionExpression"


    // $ANTLR start "entryRuleSequenceElement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4176:1: entryRuleSequenceElement returns [EObject current=null] : iv_ruleSequenceElement= ruleSequenceElement EOF ;
    public final EObject entryRuleSequenceElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSequenceElement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4177:2: (iv_ruleSequenceElement= ruleSequenceElement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4178:2: iv_ruleSequenceElement= ruleSequenceElement EOF
            {
             newCompositeNode(grammarAccess.getSequenceElementRule()); 
            pushFollow(FOLLOW_ruleSequenceElement_in_entryRuleSequenceElement8934);
            iv_ruleSequenceElement=ruleSequenceElement();

            state._fsp--;

             current =iv_ruleSequenceElement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSequenceElement8944); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSequenceElement"


    // $ANTLR start "ruleSequenceElement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4185:1: ruleSequenceElement returns [EObject current=null] : (this_Expression_0= ruleExpression | this_SequenceConstructionExpression_1= ruleSequenceConstructionExpression ) ;
    public final EObject ruleSequenceElement() throws RecognitionException {
        EObject current = null;

        EObject this_Expression_0 = null;

        EObject this_SequenceConstructionExpression_1 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4188:28: ( (this_Expression_0= ruleExpression | this_SequenceConstructionExpression_1= ruleSequenceConstructionExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4189:1: (this_Expression_0= ruleExpression | this_SequenceConstructionExpression_1= ruleSequenceConstructionExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4189:1: (this_Expression_0= ruleExpression | this_SequenceConstructionExpression_1= ruleSequenceConstructionExpression )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( ((LA66_0>=RULE_INTEGERVALUE && LA66_0<=RULE_ID)||(LA66_0>=16 && LA66_0<=18)||LA66_0==24||(LA66_0>=42 && LA66_0<=43)||(LA66_0>=46 && LA66_0<=48)||(LA66_0>=56 && LA66_0<=59)||(LA66_0>=87 && LA66_0<=88)) ) {
                alt66=1;
            }
            else if ( (LA66_0==60) ) {
                alt66=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }
            switch (alt66) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4190:5: this_Expression_0= ruleExpression
                    {
                     
                            newCompositeNode(grammarAccess.getSequenceElementAccess().getExpressionParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleExpression_in_ruleSequenceElement8991);
                    this_Expression_0=ruleExpression();

                    state._fsp--;

                     
                            current = this_Expression_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4200:5: this_SequenceConstructionExpression_1= ruleSequenceConstructionExpression
                    {
                     
                            newCompositeNode(grammarAccess.getSequenceElementAccess().getSequenceConstructionExpressionParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleSequenceConstructionExpression_in_ruleSequenceElement9018);
                    this_SequenceConstructionExpression_1=ruleSequenceConstructionExpression();

                    state._fsp--;

                     
                            current = this_SequenceConstructionExpression_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSequenceElement"


    // $ANTLR start "entryRuleClassExtentExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4216:1: entryRuleClassExtentExpression returns [EObject current=null] : iv_ruleClassExtentExpression= ruleClassExtentExpression EOF ;
    public final EObject entryRuleClassExtentExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassExtentExpression = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4217:2: (iv_ruleClassExtentExpression= ruleClassExtentExpression EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4218:2: iv_ruleClassExtentExpression= ruleClassExtentExpression EOF
            {
             newCompositeNode(grammarAccess.getClassExtentExpressionRule()); 
            pushFollow(FOLLOW_ruleClassExtentExpression_in_entryRuleClassExtentExpression9053);
            iv_ruleClassExtentExpression=ruleClassExtentExpression();

            state._fsp--;

             current =iv_ruleClassExtentExpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClassExtentExpression9063); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClassExtentExpression"


    // $ANTLR start "ruleClassExtentExpression"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4225:1: ruleClassExtentExpression returns [EObject current=null] : ( () otherlv_1= '.' otherlv_2= 'allInstances' otherlv_3= '(' otherlv_4= ')' ) ;
    public final EObject ruleClassExtentExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;

         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4228:28: ( ( () otherlv_1= '.' otherlv_2= 'allInstances' otherlv_3= '(' otherlv_4= ')' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4229:1: ( () otherlv_1= '.' otherlv_2= 'allInstances' otherlv_3= '(' otherlv_4= ')' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4229:1: ( () otherlv_1= '.' otherlv_2= 'allInstances' otherlv_3= '(' otherlv_4= ')' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4229:2: () otherlv_1= '.' otherlv_2= 'allInstances' otherlv_3= '(' otherlv_4= ')'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4229:2: ()
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4230:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getClassExtentExpressionAccess().getClassExtentExpressionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,49,FOLLOW_49_in_ruleClassExtentExpression9109); 

                	newLeafNode(otherlv_1, grammarAccess.getClassExtentExpressionAccess().getFullStopKeyword_1());
                
            otherlv_2=(Token)match(input,63,FOLLOW_63_in_ruleClassExtentExpression9121); 

                	newLeafNode(otherlv_2, grammarAccess.getClassExtentExpressionAccess().getAllInstancesKeyword_2());
                
            otherlv_3=(Token)match(input,24,FOLLOW_24_in_ruleClassExtentExpression9133); 

                	newLeafNode(otherlv_3, grammarAccess.getClassExtentExpressionAccess().getLeftParenthesisKeyword_3());
                
            otherlv_4=(Token)match(input,25,FOLLOW_25_in_ruleClassExtentExpression9145); 

                	newLeafNode(otherlv_4, grammarAccess.getClassExtentExpressionAccess().getRightParenthesisKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClassExtentExpression"


    // $ANTLR start "entryRuleBlock"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4259:1: entryRuleBlock returns [EObject current=null] : iv_ruleBlock= ruleBlock EOF ;
    public final EObject entryRuleBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBlock = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4260:2: (iv_ruleBlock= ruleBlock EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4261:2: iv_ruleBlock= ruleBlock EOF
            {
             newCompositeNode(grammarAccess.getBlockRule()); 
            pushFollow(FOLLOW_ruleBlock_in_entryRuleBlock9181);
            iv_ruleBlock=ruleBlock();

            state._fsp--;

             current =iv_ruleBlock; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBlock9191); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBlock"


    // $ANTLR start "ruleBlock"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4268:1: ruleBlock returns [EObject current=null] : (otherlv_0= '{' () ( (lv_sequence_2_0= ruleStatementSequence ) )? otherlv_3= '}' ) ;
    public final EObject ruleBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_sequence_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4271:28: ( (otherlv_0= '{' () ( (lv_sequence_2_0= ruleStatementSequence ) )? otherlv_3= '}' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4272:1: (otherlv_0= '{' () ( (lv_sequence_2_0= ruleStatementSequence ) )? otherlv_3= '}' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4272:1: (otherlv_0= '{' () ( (lv_sequence_2_0= ruleStatementSequence ) )? otherlv_3= '}' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4272:3: otherlv_0= '{' () ( (lv_sequence_2_0= ruleStatementSequence ) )? otherlv_3= '}'
            {
            otherlv_0=(Token)match(input,60,FOLLOW_60_in_ruleBlock9228); 

                	newLeafNode(otherlv_0, grammarAccess.getBlockAccess().getLeftCurlyBracketKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4276:1: ()
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4277:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getBlockAccess().getBlockAction_1(),
                        current);
                

            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4282:2: ( (lv_sequence_2_0= ruleStatementSequence ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( ((LA67_0>=RULE_ID && LA67_0<=RULE_SL_COMMENT)||(LA67_0>=17 && LA67_0<=18)||(LA67_0>=57 && LA67_0<=60)||LA67_0==64||(LA67_0>=67 && LA67_0<=69)||LA67_0==71||LA67_0==74||(LA67_0>=77 && LA67_0<=79)||(LA67_0>=81 && LA67_0<=84)) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4283:1: (lv_sequence_2_0= ruleStatementSequence )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4283:1: (lv_sequence_2_0= ruleStatementSequence )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4284:3: lv_sequence_2_0= ruleStatementSequence
                    {
                     
                    	        newCompositeNode(grammarAccess.getBlockAccess().getSequenceStatementSequenceParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleStatementSequence_in_ruleBlock9258);
                    lv_sequence_2_0=ruleStatementSequence();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	        }
                           		set(
                           			current, 
                           			"sequence",
                            		lv_sequence_2_0, 
                            		"StatementSequence");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,62,FOLLOW_62_in_ruleBlock9271); 

                	newLeafNode(otherlv_3, grammarAccess.getBlockAccess().getRightCurlyBracketKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBlock"


    // $ANTLR start "entryRuleStatementSequence"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4312:1: entryRuleStatementSequence returns [EObject current=null] : iv_ruleStatementSequence= ruleStatementSequence EOF ;
    public final EObject entryRuleStatementSequence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatementSequence = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4313:2: (iv_ruleStatementSequence= ruleStatementSequence EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4314:2: iv_ruleStatementSequence= ruleStatementSequence EOF
            {
             newCompositeNode(grammarAccess.getStatementSequenceRule()); 
            pushFollow(FOLLOW_ruleStatementSequence_in_entryRuleStatementSequence9307);
            iv_ruleStatementSequence=ruleStatementSequence();

            state._fsp--;

             current =iv_ruleStatementSequence; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStatementSequence9317); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStatementSequence"


    // $ANTLR start "ruleStatementSequence"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4321:1: ruleStatementSequence returns [EObject current=null] : ( (lv_statements_0_0= ruleDocumentedStatement ) )+ ;
    public final EObject ruleStatementSequence() throws RecognitionException {
        EObject current = null;

        EObject lv_statements_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4324:28: ( ( (lv_statements_0_0= ruleDocumentedStatement ) )+ )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4325:1: ( (lv_statements_0_0= ruleDocumentedStatement ) )+
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4325:1: ( (lv_statements_0_0= ruleDocumentedStatement ) )+
            int cnt68=0;
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( ((LA68_0>=RULE_ID && LA68_0<=RULE_SL_COMMENT)||(LA68_0>=17 && LA68_0<=18)||(LA68_0>=57 && LA68_0<=60)||LA68_0==64||(LA68_0>=67 && LA68_0<=69)||LA68_0==71||LA68_0==74||(LA68_0>=77 && LA68_0<=79)||(LA68_0>=81 && LA68_0<=84)) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4326:1: (lv_statements_0_0= ruleDocumentedStatement )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4326:1: (lv_statements_0_0= ruleDocumentedStatement )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4327:3: lv_statements_0_0= ruleDocumentedStatement
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getStatementSequenceAccess().getStatementsDocumentedStatementParserRuleCall_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleDocumentedStatement_in_ruleStatementSequence9362);
            	    lv_statements_0_0=ruleDocumentedStatement();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getStatementSequenceRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"statements",
            	            		lv_statements_0_0, 
            	            		"DocumentedStatement");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt68 >= 1 ) break loop68;
                        EarlyExitException eee =
                            new EarlyExitException(68, input);
                        throw eee;
                }
                cnt68++;
            } while (true);


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStatementSequence"


    // $ANTLR start "entryRuleDocumentedStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4351:1: entryRuleDocumentedStatement returns [EObject current=null] : iv_ruleDocumentedStatement= ruleDocumentedStatement EOF ;
    public final EObject entryRuleDocumentedStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDocumentedStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4352:2: (iv_ruleDocumentedStatement= ruleDocumentedStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4353:2: iv_ruleDocumentedStatement= ruleDocumentedStatement EOF
            {
             newCompositeNode(grammarAccess.getDocumentedStatementRule()); 
            pushFollow(FOLLOW_ruleDocumentedStatement_in_entryRuleDocumentedStatement9398);
            iv_ruleDocumentedStatement=ruleDocumentedStatement();

            state._fsp--;

             current =iv_ruleDocumentedStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDocumentedStatement9408); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDocumentedStatement"


    // $ANTLR start "ruleDocumentedStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4360:1: ruleDocumentedStatement returns [EObject current=null] : ( ( ( (lv_comment_0_1= RULE_ML_COMMENT | lv_comment_0_2= RULE_SL_COMMENT ) ) )? ( (lv_statement_1_0= ruleStatement ) ) ) ;
    public final EObject ruleDocumentedStatement() throws RecognitionException {
        EObject current = null;

        Token lv_comment_0_1=null;
        Token lv_comment_0_2=null;
        EObject lv_statement_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4363:28: ( ( ( ( (lv_comment_0_1= RULE_ML_COMMENT | lv_comment_0_2= RULE_SL_COMMENT ) ) )? ( (lv_statement_1_0= ruleStatement ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4364:1: ( ( ( (lv_comment_0_1= RULE_ML_COMMENT | lv_comment_0_2= RULE_SL_COMMENT ) ) )? ( (lv_statement_1_0= ruleStatement ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4364:1: ( ( ( (lv_comment_0_1= RULE_ML_COMMENT | lv_comment_0_2= RULE_SL_COMMENT ) ) )? ( (lv_statement_1_0= ruleStatement ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4364:2: ( ( (lv_comment_0_1= RULE_ML_COMMENT | lv_comment_0_2= RULE_SL_COMMENT ) ) )? ( (lv_statement_1_0= ruleStatement ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4364:2: ( ( (lv_comment_0_1= RULE_ML_COMMENT | lv_comment_0_2= RULE_SL_COMMENT ) ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( ((LA70_0>=RULE_ML_COMMENT && LA70_0<=RULE_SL_COMMENT)) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4365:1: ( (lv_comment_0_1= RULE_ML_COMMENT | lv_comment_0_2= RULE_SL_COMMENT ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4365:1: ( (lv_comment_0_1= RULE_ML_COMMENT | lv_comment_0_2= RULE_SL_COMMENT ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4366:1: (lv_comment_0_1= RULE_ML_COMMENT | lv_comment_0_2= RULE_SL_COMMENT )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4366:1: (lv_comment_0_1= RULE_ML_COMMENT | lv_comment_0_2= RULE_SL_COMMENT )
                    int alt69=2;
                    int LA69_0 = input.LA(1);

                    if ( (LA69_0==RULE_ML_COMMENT) ) {
                        alt69=1;
                    }
                    else if ( (LA69_0==RULE_SL_COMMENT) ) {
                        alt69=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 69, 0, input);

                        throw nvae;
                    }
                    switch (alt69) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4367:3: lv_comment_0_1= RULE_ML_COMMENT
                            {
                            lv_comment_0_1=(Token)match(input,RULE_ML_COMMENT,FOLLOW_RULE_ML_COMMENT_in_ruleDocumentedStatement9452); 

                            			newLeafNode(lv_comment_0_1, grammarAccess.getDocumentedStatementAccess().getCommentML_COMMENTTerminalRuleCall_0_0_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getDocumentedStatementRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"comment",
                                    		lv_comment_0_1, 
                                    		"ML_COMMENT");
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4382:8: lv_comment_0_2= RULE_SL_COMMENT
                            {
                            lv_comment_0_2=(Token)match(input,RULE_SL_COMMENT,FOLLOW_RULE_SL_COMMENT_in_ruleDocumentedStatement9472); 

                            			newLeafNode(lv_comment_0_2, grammarAccess.getDocumentedStatementAccess().getCommentSL_COMMENTTerminalRuleCall_0_0_1()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getDocumentedStatementRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"comment",
                                    		lv_comment_0_2, 
                                    		"SL_COMMENT");
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4400:3: ( (lv_statement_1_0= ruleStatement ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4401:1: (lv_statement_1_0= ruleStatement )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4401:1: (lv_statement_1_0= ruleStatement )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4402:3: lv_statement_1_0= ruleStatement
            {
             
            	        newCompositeNode(grammarAccess.getDocumentedStatementAccess().getStatementStatementParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleStatement_in_ruleDocumentedStatement9502);
            lv_statement_1_0=ruleStatement();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDocumentedStatementRule());
            	        }
                   		set(
                   			current, 
                   			"statement",
                    		lv_statement_1_0, 
                    		"Statement");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDocumentedStatement"


    // $ANTLR start "entryRuleInlineStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4426:1: entryRuleInlineStatement returns [EObject current=null] : iv_ruleInlineStatement= ruleInlineStatement EOF ;
    public final EObject entryRuleInlineStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInlineStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4427:2: (iv_ruleInlineStatement= ruleInlineStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4428:2: iv_ruleInlineStatement= ruleInlineStatement EOF
            {
             newCompositeNode(grammarAccess.getInlineStatementRule()); 
            pushFollow(FOLLOW_ruleInlineStatement_in_entryRuleInlineStatement9538);
            iv_ruleInlineStatement=ruleInlineStatement();

            state._fsp--;

             current =iv_ruleInlineStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInlineStatement9548); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInlineStatement"


    // $ANTLR start "ruleInlineStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4435:1: ruleInlineStatement returns [EObject current=null] : (otherlv_0= '/*@' otherlv_1= 'inline' otherlv_2= '(' ( (lv_langageName_3_0= RULE_ID ) ) otherlv_4= ')' ( (lv_body_5_0= RULE_STRING ) ) otherlv_6= '*/' ) ;
    public final EObject ruleInlineStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_langageName_3_0=null;
        Token otherlv_4=null;
        Token lv_body_5_0=null;
        Token otherlv_6=null;

         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4438:28: ( (otherlv_0= '/*@' otherlv_1= 'inline' otherlv_2= '(' ( (lv_langageName_3_0= RULE_ID ) ) otherlv_4= ')' ( (lv_body_5_0= RULE_STRING ) ) otherlv_6= '*/' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4439:1: (otherlv_0= '/*@' otherlv_1= 'inline' otherlv_2= '(' ( (lv_langageName_3_0= RULE_ID ) ) otherlv_4= ')' ( (lv_body_5_0= RULE_STRING ) ) otherlv_6= '*/' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4439:1: (otherlv_0= '/*@' otherlv_1= 'inline' otherlv_2= '(' ( (lv_langageName_3_0= RULE_ID ) ) otherlv_4= ')' ( (lv_body_5_0= RULE_STRING ) ) otherlv_6= '*/' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4439:3: otherlv_0= '/*@' otherlv_1= 'inline' otherlv_2= '(' ( (lv_langageName_3_0= RULE_ID ) ) otherlv_4= ')' ( (lv_body_5_0= RULE_STRING ) ) otherlv_6= '*/'
            {
            otherlv_0=(Token)match(input,64,FOLLOW_64_in_ruleInlineStatement9585); 

                	newLeafNode(otherlv_0, grammarAccess.getInlineStatementAccess().getSolidusAsteriskCommercialAtKeyword_0());
                
            otherlv_1=(Token)match(input,65,FOLLOW_65_in_ruleInlineStatement9597); 

                	newLeafNode(otherlv_1, grammarAccess.getInlineStatementAccess().getInlineKeyword_1());
                
            otherlv_2=(Token)match(input,24,FOLLOW_24_in_ruleInlineStatement9609); 

                	newLeafNode(otherlv_2, grammarAccess.getInlineStatementAccess().getLeftParenthesisKeyword_2());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4451:1: ( (lv_langageName_3_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4452:1: (lv_langageName_3_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4452:1: (lv_langageName_3_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4453:3: lv_langageName_3_0= RULE_ID
            {
            lv_langageName_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInlineStatement9626); 

            			newLeafNode(lv_langageName_3_0, grammarAccess.getInlineStatementAccess().getLangageNameIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getInlineStatementRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"langageName",
                    		lv_langageName_3_0, 
                    		"ID");
            	    

            }


            }

            otherlv_4=(Token)match(input,25,FOLLOW_25_in_ruleInlineStatement9643); 

                	newLeafNode(otherlv_4, grammarAccess.getInlineStatementAccess().getRightParenthesisKeyword_4());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4473:1: ( (lv_body_5_0= RULE_STRING ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4474:1: (lv_body_5_0= RULE_STRING )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4474:1: (lv_body_5_0= RULE_STRING )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4475:3: lv_body_5_0= RULE_STRING
            {
            lv_body_5_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleInlineStatement9660); 

            			newLeafNode(lv_body_5_0, grammarAccess.getInlineStatementAccess().getBodySTRINGTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getInlineStatementRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"body",
                    		lv_body_5_0, 
                    		"STRING");
            	    

            }


            }

            otherlv_6=(Token)match(input,66,FOLLOW_66_in_ruleInlineStatement9677); 

                	newLeafNode(otherlv_6, grammarAccess.getInlineStatementAccess().getAsteriskSolidusKeyword_6());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInlineStatement"


    // $ANTLR start "entryRuleAnnotatedStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4503:1: entryRuleAnnotatedStatement returns [EObject current=null] : iv_ruleAnnotatedStatement= ruleAnnotatedStatement EOF ;
    public final EObject entryRuleAnnotatedStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnotatedStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4504:2: (iv_ruleAnnotatedStatement= ruleAnnotatedStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4505:2: iv_ruleAnnotatedStatement= ruleAnnotatedStatement EOF
            {
             newCompositeNode(grammarAccess.getAnnotatedStatementRule()); 
            pushFollow(FOLLOW_ruleAnnotatedStatement_in_entryRuleAnnotatedStatement9713);
            iv_ruleAnnotatedStatement=ruleAnnotatedStatement();

            state._fsp--;

             current =iv_ruleAnnotatedStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnnotatedStatement9723); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnnotatedStatement"


    // $ANTLR start "ruleAnnotatedStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4512:1: ruleAnnotatedStatement returns [EObject current=null] : (otherlv_0= '//@' ( (lv_annotation_1_0= ruleAnnotation ) ) ( (lv_statement_2_0= ruleStatement ) ) ) ;
    public final EObject ruleAnnotatedStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_annotation_1_0 = null;

        EObject lv_statement_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4515:28: ( (otherlv_0= '//@' ( (lv_annotation_1_0= ruleAnnotation ) ) ( (lv_statement_2_0= ruleStatement ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4516:1: (otherlv_0= '//@' ( (lv_annotation_1_0= ruleAnnotation ) ) ( (lv_statement_2_0= ruleStatement ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4516:1: (otherlv_0= '//@' ( (lv_annotation_1_0= ruleAnnotation ) ) ( (lv_statement_2_0= ruleStatement ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4516:3: otherlv_0= '//@' ( (lv_annotation_1_0= ruleAnnotation ) ) ( (lv_statement_2_0= ruleStatement ) )
            {
            otherlv_0=(Token)match(input,67,FOLLOW_67_in_ruleAnnotatedStatement9760); 

                	newLeafNode(otherlv_0, grammarAccess.getAnnotatedStatementAccess().getSolidusSolidusCommercialAtKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4520:1: ( (lv_annotation_1_0= ruleAnnotation ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4521:1: (lv_annotation_1_0= ruleAnnotation )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4521:1: (lv_annotation_1_0= ruleAnnotation )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4522:3: lv_annotation_1_0= ruleAnnotation
            {
             
            	        newCompositeNode(grammarAccess.getAnnotatedStatementAccess().getAnnotationAnnotationParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleAnnotation_in_ruleAnnotatedStatement9781);
            lv_annotation_1_0=ruleAnnotation();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAnnotatedStatementRule());
            	        }
                   		set(
                   			current, 
                   			"annotation",
                    		lv_annotation_1_0, 
                    		"Annotation");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4538:2: ( (lv_statement_2_0= ruleStatement ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4539:1: (lv_statement_2_0= ruleStatement )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4539:1: (lv_statement_2_0= ruleStatement )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4540:3: lv_statement_2_0= ruleStatement
            {
             
            	        newCompositeNode(grammarAccess.getAnnotatedStatementAccess().getStatementStatementParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleStatement_in_ruleAnnotatedStatement9802);
            lv_statement_2_0=ruleStatement();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAnnotatedStatementRule());
            	        }
                   		set(
                   			current, 
                   			"statement",
                    		lv_statement_2_0, 
                    		"Statement");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnnotatedStatement"


    // $ANTLR start "entryRuleStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4564:1: entryRuleStatement returns [EObject current=null] : iv_ruleStatement= ruleStatement EOF ;
    public final EObject entryRuleStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4565:2: (iv_ruleStatement= ruleStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4566:2: iv_ruleStatement= ruleStatement EOF
            {
             newCompositeNode(grammarAccess.getStatementRule()); 
            pushFollow(FOLLOW_ruleStatement_in_entryRuleStatement9838);
            iv_ruleStatement=ruleStatement();

            state._fsp--;

             current =iv_ruleStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStatement9848); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStatement"


    // $ANTLR start "ruleStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4573:1: ruleStatement returns [EObject current=null] : (this_AnnotatedStatement_0= ruleAnnotatedStatement | this_InlineStatement_1= ruleInlineStatement | this_BlockStatement_2= ruleBlockStatement | this_EmptyStatement_3= ruleEmptyStatement | this_LocalNameDeclarationStatement_4= ruleLocalNameDeclarationStatement | this_IfStatement_5= ruleIfStatement | this_SwitchStatement_6= ruleSwitchStatement | this_WhileStatement_7= ruleWhileStatement | this_DoStatement_8= ruleDoStatement | this_ForStatement_9= ruleForStatement | this_BreakStatement_10= ruleBreakStatement | this_ReturnStatement_11= ruleReturnStatement | this_AcceptStatement_12= ruleAcceptStatement | this_ClassifyStatement_13= ruleClassifyStatement | this_InvocationOrAssignementOrDeclarationStatement_14= ruleInvocationOrAssignementOrDeclarationStatement | this_SuperInvocationStatement_15= ruleSuperInvocationStatement | this_ThisInvocationStatement_16= ruleThisInvocationStatement | this_InstanceCreationInvocationStatement_17= ruleInstanceCreationInvocationStatement ) ;
    public final EObject ruleStatement() throws RecognitionException {
        EObject current = null;

        EObject this_AnnotatedStatement_0 = null;

        EObject this_InlineStatement_1 = null;

        EObject this_BlockStatement_2 = null;

        EObject this_EmptyStatement_3 = null;

        EObject this_LocalNameDeclarationStatement_4 = null;

        EObject this_IfStatement_5 = null;

        EObject this_SwitchStatement_6 = null;

        EObject this_WhileStatement_7 = null;

        EObject this_DoStatement_8 = null;

        EObject this_ForStatement_9 = null;

        EObject this_BreakStatement_10 = null;

        EObject this_ReturnStatement_11 = null;

        EObject this_AcceptStatement_12 = null;

        EObject this_ClassifyStatement_13 = null;

        EObject this_InvocationOrAssignementOrDeclarationStatement_14 = null;

        EObject this_SuperInvocationStatement_15 = null;

        EObject this_ThisInvocationStatement_16 = null;

        EObject this_InstanceCreationInvocationStatement_17 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4576:28: ( (this_AnnotatedStatement_0= ruleAnnotatedStatement | this_InlineStatement_1= ruleInlineStatement | this_BlockStatement_2= ruleBlockStatement | this_EmptyStatement_3= ruleEmptyStatement | this_LocalNameDeclarationStatement_4= ruleLocalNameDeclarationStatement | this_IfStatement_5= ruleIfStatement | this_SwitchStatement_6= ruleSwitchStatement | this_WhileStatement_7= ruleWhileStatement | this_DoStatement_8= ruleDoStatement | this_ForStatement_9= ruleForStatement | this_BreakStatement_10= ruleBreakStatement | this_ReturnStatement_11= ruleReturnStatement | this_AcceptStatement_12= ruleAcceptStatement | this_ClassifyStatement_13= ruleClassifyStatement | this_InvocationOrAssignementOrDeclarationStatement_14= ruleInvocationOrAssignementOrDeclarationStatement | this_SuperInvocationStatement_15= ruleSuperInvocationStatement | this_ThisInvocationStatement_16= ruleThisInvocationStatement | this_InstanceCreationInvocationStatement_17= ruleInstanceCreationInvocationStatement ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4577:1: (this_AnnotatedStatement_0= ruleAnnotatedStatement | this_InlineStatement_1= ruleInlineStatement | this_BlockStatement_2= ruleBlockStatement | this_EmptyStatement_3= ruleEmptyStatement | this_LocalNameDeclarationStatement_4= ruleLocalNameDeclarationStatement | this_IfStatement_5= ruleIfStatement | this_SwitchStatement_6= ruleSwitchStatement | this_WhileStatement_7= ruleWhileStatement | this_DoStatement_8= ruleDoStatement | this_ForStatement_9= ruleForStatement | this_BreakStatement_10= ruleBreakStatement | this_ReturnStatement_11= ruleReturnStatement | this_AcceptStatement_12= ruleAcceptStatement | this_ClassifyStatement_13= ruleClassifyStatement | this_InvocationOrAssignementOrDeclarationStatement_14= ruleInvocationOrAssignementOrDeclarationStatement | this_SuperInvocationStatement_15= ruleSuperInvocationStatement | this_ThisInvocationStatement_16= ruleThisInvocationStatement | this_InstanceCreationInvocationStatement_17= ruleInstanceCreationInvocationStatement )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4577:1: (this_AnnotatedStatement_0= ruleAnnotatedStatement | this_InlineStatement_1= ruleInlineStatement | this_BlockStatement_2= ruleBlockStatement | this_EmptyStatement_3= ruleEmptyStatement | this_LocalNameDeclarationStatement_4= ruleLocalNameDeclarationStatement | this_IfStatement_5= ruleIfStatement | this_SwitchStatement_6= ruleSwitchStatement | this_WhileStatement_7= ruleWhileStatement | this_DoStatement_8= ruleDoStatement | this_ForStatement_9= ruleForStatement | this_BreakStatement_10= ruleBreakStatement | this_ReturnStatement_11= ruleReturnStatement | this_AcceptStatement_12= ruleAcceptStatement | this_ClassifyStatement_13= ruleClassifyStatement | this_InvocationOrAssignementOrDeclarationStatement_14= ruleInvocationOrAssignementOrDeclarationStatement | this_SuperInvocationStatement_15= ruleSuperInvocationStatement | this_ThisInvocationStatement_16= ruleThisInvocationStatement | this_InstanceCreationInvocationStatement_17= ruleInstanceCreationInvocationStatement )
            int alt71=18;
            switch ( input.LA(1) ) {
            case 67:
                {
                alt71=1;
                }
                break;
            case 64:
                {
                alt71=2;
                }
                break;
            case 60:
                {
                alt71=3;
                }
                break;
            case 68:
                {
                alt71=4;
                }
                break;
            case 69:
                {
                alt71=5;
                }
                break;
            case 71:
                {
                alt71=6;
                }
                break;
            case 74:
                {
                alt71=7;
                }
                break;
            case 77:
                {
                alt71=8;
                }
                break;
            case 78:
                {
                alt71=9;
                }
                break;
            case 79:
                {
                alt71=10;
                }
                break;
            case 81:
                {
                alt71=11;
                }
                break;
            case 82:
                {
                alt71=12;
                }
                break;
            case 83:
                {
                alt71=13;
                }
                break;
            case 84:
                {
                alt71=14;
                }
                break;
            case RULE_ID:
            case 17:
            case 18:
                {
                alt71=15;
                }
                break;
            case 58:
                {
                alt71=16;
                }
                break;
            case 57:
                {
                alt71=17;
                }
                break;
            case 59:
                {
                alt71=18;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }

            switch (alt71) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4578:5: this_AnnotatedStatement_0= ruleAnnotatedStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getAnnotatedStatementParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleAnnotatedStatement_in_ruleStatement9895);
                    this_AnnotatedStatement_0=ruleAnnotatedStatement();

                    state._fsp--;

                     
                            current = this_AnnotatedStatement_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4588:5: this_InlineStatement_1= ruleInlineStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getInlineStatementParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleInlineStatement_in_ruleStatement9922);
                    this_InlineStatement_1=ruleInlineStatement();

                    state._fsp--;

                     
                            current = this_InlineStatement_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4598:5: this_BlockStatement_2= ruleBlockStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getBlockStatementParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleBlockStatement_in_ruleStatement9949);
                    this_BlockStatement_2=ruleBlockStatement();

                    state._fsp--;

                     
                            current = this_BlockStatement_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4608:5: this_EmptyStatement_3= ruleEmptyStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getEmptyStatementParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleEmptyStatement_in_ruleStatement9976);
                    this_EmptyStatement_3=ruleEmptyStatement();

                    state._fsp--;

                     
                            current = this_EmptyStatement_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4618:5: this_LocalNameDeclarationStatement_4= ruleLocalNameDeclarationStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getLocalNameDeclarationStatementParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleLocalNameDeclarationStatement_in_ruleStatement10003);
                    this_LocalNameDeclarationStatement_4=ruleLocalNameDeclarationStatement();

                    state._fsp--;

                     
                            current = this_LocalNameDeclarationStatement_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4628:5: this_IfStatement_5= ruleIfStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getIfStatementParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleIfStatement_in_ruleStatement10030);
                    this_IfStatement_5=ruleIfStatement();

                    state._fsp--;

                     
                            current = this_IfStatement_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4638:5: this_SwitchStatement_6= ruleSwitchStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getSwitchStatementParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleSwitchStatement_in_ruleStatement10057);
                    this_SwitchStatement_6=ruleSwitchStatement();

                    state._fsp--;

                     
                            current = this_SwitchStatement_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4648:5: this_WhileStatement_7= ruleWhileStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getWhileStatementParserRuleCall_7()); 
                        
                    pushFollow(FOLLOW_ruleWhileStatement_in_ruleStatement10084);
                    this_WhileStatement_7=ruleWhileStatement();

                    state._fsp--;

                     
                            current = this_WhileStatement_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4658:5: this_DoStatement_8= ruleDoStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getDoStatementParserRuleCall_8()); 
                        
                    pushFollow(FOLLOW_ruleDoStatement_in_ruleStatement10111);
                    this_DoStatement_8=ruleDoStatement();

                    state._fsp--;

                     
                            current = this_DoStatement_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4668:5: this_ForStatement_9= ruleForStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getForStatementParserRuleCall_9()); 
                        
                    pushFollow(FOLLOW_ruleForStatement_in_ruleStatement10138);
                    this_ForStatement_9=ruleForStatement();

                    state._fsp--;

                     
                            current = this_ForStatement_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4678:5: this_BreakStatement_10= ruleBreakStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getBreakStatementParserRuleCall_10()); 
                        
                    pushFollow(FOLLOW_ruleBreakStatement_in_ruleStatement10165);
                    this_BreakStatement_10=ruleBreakStatement();

                    state._fsp--;

                     
                            current = this_BreakStatement_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4688:5: this_ReturnStatement_11= ruleReturnStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getReturnStatementParserRuleCall_11()); 
                        
                    pushFollow(FOLLOW_ruleReturnStatement_in_ruleStatement10192);
                    this_ReturnStatement_11=ruleReturnStatement();

                    state._fsp--;

                     
                            current = this_ReturnStatement_11; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 13 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4698:5: this_AcceptStatement_12= ruleAcceptStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getAcceptStatementParserRuleCall_12()); 
                        
                    pushFollow(FOLLOW_ruleAcceptStatement_in_ruleStatement10219);
                    this_AcceptStatement_12=ruleAcceptStatement();

                    state._fsp--;

                     
                            current = this_AcceptStatement_12; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 14 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4708:5: this_ClassifyStatement_13= ruleClassifyStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getClassifyStatementParserRuleCall_13()); 
                        
                    pushFollow(FOLLOW_ruleClassifyStatement_in_ruleStatement10246);
                    this_ClassifyStatement_13=ruleClassifyStatement();

                    state._fsp--;

                     
                            current = this_ClassifyStatement_13; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 15 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4718:5: this_InvocationOrAssignementOrDeclarationStatement_14= ruleInvocationOrAssignementOrDeclarationStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getInvocationOrAssignementOrDeclarationStatementParserRuleCall_14()); 
                        
                    pushFollow(FOLLOW_ruleInvocationOrAssignementOrDeclarationStatement_in_ruleStatement10273);
                    this_InvocationOrAssignementOrDeclarationStatement_14=ruleInvocationOrAssignementOrDeclarationStatement();

                    state._fsp--;

                     
                            current = this_InvocationOrAssignementOrDeclarationStatement_14; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 16 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4728:5: this_SuperInvocationStatement_15= ruleSuperInvocationStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getSuperInvocationStatementParserRuleCall_15()); 
                        
                    pushFollow(FOLLOW_ruleSuperInvocationStatement_in_ruleStatement10300);
                    this_SuperInvocationStatement_15=ruleSuperInvocationStatement();

                    state._fsp--;

                     
                            current = this_SuperInvocationStatement_15; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 17 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4738:5: this_ThisInvocationStatement_16= ruleThisInvocationStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getThisInvocationStatementParserRuleCall_16()); 
                        
                    pushFollow(FOLLOW_ruleThisInvocationStatement_in_ruleStatement10327);
                    this_ThisInvocationStatement_16=ruleThisInvocationStatement();

                    state._fsp--;

                     
                            current = this_ThisInvocationStatement_16; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 18 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4748:5: this_InstanceCreationInvocationStatement_17= ruleInstanceCreationInvocationStatement
                    {
                     
                            newCompositeNode(grammarAccess.getStatementAccess().getInstanceCreationInvocationStatementParserRuleCall_17()); 
                        
                    pushFollow(FOLLOW_ruleInstanceCreationInvocationStatement_in_ruleStatement10354);
                    this_InstanceCreationInvocationStatement_17=ruleInstanceCreationInvocationStatement();

                    state._fsp--;

                     
                            current = this_InstanceCreationInvocationStatement_17; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStatement"


    // $ANTLR start "entryRuleAnnotation"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4764:1: entryRuleAnnotation returns [EObject current=null] : iv_ruleAnnotation= ruleAnnotation EOF ;
    public final EObject entryRuleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnotation = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4765:2: (iv_ruleAnnotation= ruleAnnotation EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4766:2: iv_ruleAnnotation= ruleAnnotation EOF
            {
             newCompositeNode(grammarAccess.getAnnotationRule()); 
            pushFollow(FOLLOW_ruleAnnotation_in_entryRuleAnnotation10389);
            iv_ruleAnnotation=ruleAnnotation();

            state._fsp--;

             current =iv_ruleAnnotation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnnotation10399); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnnotation"


    // $ANTLR start "ruleAnnotation"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4773:1: ruleAnnotation returns [EObject current=null] : ( ( (lv_kind_0_0= ruleAnnotationKind ) ) (otherlv_1= '(' ( (lv_args_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_args_4_0= RULE_ID ) ) )* otherlv_5= ')' )? ) ;
    public final EObject ruleAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_args_2_0=null;
        Token otherlv_3=null;
        Token lv_args_4_0=null;
        Token otherlv_5=null;
        Enumerator lv_kind_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4776:28: ( ( ( (lv_kind_0_0= ruleAnnotationKind ) ) (otherlv_1= '(' ( (lv_args_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_args_4_0= RULE_ID ) ) )* otherlv_5= ')' )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4777:1: ( ( (lv_kind_0_0= ruleAnnotationKind ) ) (otherlv_1= '(' ( (lv_args_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_args_4_0= RULE_ID ) ) )* otherlv_5= ')' )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4777:1: ( ( (lv_kind_0_0= ruleAnnotationKind ) ) (otherlv_1= '(' ( (lv_args_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_args_4_0= RULE_ID ) ) )* otherlv_5= ')' )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4777:2: ( (lv_kind_0_0= ruleAnnotationKind ) ) (otherlv_1= '(' ( (lv_args_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_args_4_0= RULE_ID ) ) )* otherlv_5= ')' )?
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4777:2: ( (lv_kind_0_0= ruleAnnotationKind ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4778:1: (lv_kind_0_0= ruleAnnotationKind )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4778:1: (lv_kind_0_0= ruleAnnotationKind )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4779:3: lv_kind_0_0= ruleAnnotationKind
            {
             
            	        newCompositeNode(grammarAccess.getAnnotationAccess().getKindAnnotationKindEnumRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleAnnotationKind_in_ruleAnnotation10445);
            lv_kind_0_0=ruleAnnotationKind();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAnnotationRule());
            	        }
                   		set(
                   			current, 
                   			"kind",
                    		lv_kind_0_0, 
                    		"AnnotationKind");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4795:2: (otherlv_1= '(' ( (lv_args_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_args_4_0= RULE_ID ) ) )* otherlv_5= ')' )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==24) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4795:4: otherlv_1= '(' ( (lv_args_2_0= RULE_ID ) ) (otherlv_3= ',' ( (lv_args_4_0= RULE_ID ) ) )* otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleAnnotation10458); 

                        	newLeafNode(otherlv_1, grammarAccess.getAnnotationAccess().getLeftParenthesisKeyword_1_0());
                        
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4799:1: ( (lv_args_2_0= RULE_ID ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4800:1: (lv_args_2_0= RULE_ID )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4800:1: (lv_args_2_0= RULE_ID )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4801:3: lv_args_2_0= RULE_ID
                    {
                    lv_args_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAnnotation10475); 

                    			newLeafNode(lv_args_2_0, grammarAccess.getAnnotationAccess().getArgsIDTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAnnotationRule());
                    	        }
                           		addWithLastConsumed(
                           			current, 
                           			"args",
                            		lv_args_2_0, 
                            		"ID");
                    	    

                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4817:2: (otherlv_3= ',' ( (lv_args_4_0= RULE_ID ) ) )*
                    loop72:
                    do {
                        int alt72=2;
                        int LA72_0 = input.LA(1);

                        if ( (LA72_0==21) ) {
                            alt72=1;
                        }


                        switch (alt72) {
                    	case 1 :
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4817:4: otherlv_3= ',' ( (lv_args_4_0= RULE_ID ) )
                    	    {
                    	    otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleAnnotation10493); 

                    	        	newLeafNode(otherlv_3, grammarAccess.getAnnotationAccess().getCommaKeyword_1_2_0());
                    	        
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4821:1: ( (lv_args_4_0= RULE_ID ) )
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4822:1: (lv_args_4_0= RULE_ID )
                    	    {
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4822:1: (lv_args_4_0= RULE_ID )
                    	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4823:3: lv_args_4_0= RULE_ID
                    	    {
                    	    lv_args_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAnnotation10510); 

                    	    			newLeafNode(lv_args_4_0, grammarAccess.getAnnotationAccess().getArgsIDTerminalRuleCall_1_2_1_0()); 
                    	    		

                    	    	        if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getAnnotationRule());
                    	    	        }
                    	           		addWithLastConsumed(
                    	           			current, 
                    	           			"args",
                    	            		lv_args_4_0, 
                    	            		"ID");
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop72;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleAnnotation10529); 

                        	newLeafNode(otherlv_5, grammarAccess.getAnnotationAccess().getRightParenthesisKeyword_1_3());
                        

                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnnotation"


    // $ANTLR start "entryRuleBlockStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4851:1: entryRuleBlockStatement returns [EObject current=null] : iv_ruleBlockStatement= ruleBlockStatement EOF ;
    public final EObject entryRuleBlockStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBlockStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4852:2: (iv_ruleBlockStatement= ruleBlockStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4853:2: iv_ruleBlockStatement= ruleBlockStatement EOF
            {
             newCompositeNode(grammarAccess.getBlockStatementRule()); 
            pushFollow(FOLLOW_ruleBlockStatement_in_entryRuleBlockStatement10567);
            iv_ruleBlockStatement=ruleBlockStatement();

            state._fsp--;

             current =iv_ruleBlockStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBlockStatement10577); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBlockStatement"


    // $ANTLR start "ruleBlockStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4860:1: ruleBlockStatement returns [EObject current=null] : ( (lv_block_0_0= ruleBlock ) ) ;
    public final EObject ruleBlockStatement() throws RecognitionException {
        EObject current = null;

        EObject lv_block_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4863:28: ( ( (lv_block_0_0= ruleBlock ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4864:1: ( (lv_block_0_0= ruleBlock ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4864:1: ( (lv_block_0_0= ruleBlock ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4865:1: (lv_block_0_0= ruleBlock )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4865:1: (lv_block_0_0= ruleBlock )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4866:3: lv_block_0_0= ruleBlock
            {
             
            	        newCompositeNode(grammarAccess.getBlockStatementAccess().getBlockBlockParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleBlock_in_ruleBlockStatement10622);
            lv_block_0_0=ruleBlock();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getBlockStatementRule());
            	        }
                   		set(
                   			current, 
                   			"block",
                    		lv_block_0_0, 
                    		"Block");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBlockStatement"


    // $ANTLR start "entryRuleEmptyStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4890:1: entryRuleEmptyStatement returns [EObject current=null] : iv_ruleEmptyStatement= ruleEmptyStatement EOF ;
    public final EObject entryRuleEmptyStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEmptyStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4891:2: (iv_ruleEmptyStatement= ruleEmptyStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4892:2: iv_ruleEmptyStatement= ruleEmptyStatement EOF
            {
             newCompositeNode(grammarAccess.getEmptyStatementRule()); 
            pushFollow(FOLLOW_ruleEmptyStatement_in_entryRuleEmptyStatement10657);
            iv_ruleEmptyStatement=ruleEmptyStatement();

            state._fsp--;

             current =iv_ruleEmptyStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEmptyStatement10667); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEmptyStatement"


    // $ANTLR start "ruleEmptyStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4899:1: ruleEmptyStatement returns [EObject current=null] : ( () otherlv_1= ';' ) ;
    public final EObject ruleEmptyStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4902:28: ( ( () otherlv_1= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4903:1: ( () otherlv_1= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4903:1: ( () otherlv_1= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4903:2: () otherlv_1= ';'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4903:2: ()
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4904:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getEmptyStatementAccess().getEmptyStatementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,68,FOLLOW_68_in_ruleEmptyStatement10713); 

                	newLeafNode(otherlv_1, grammarAccess.getEmptyStatementAccess().getSemicolonKeyword_1());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEmptyStatement"


    // $ANTLR start "entryRuleLocalNameDeclarationStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4921:1: entryRuleLocalNameDeclarationStatement returns [EObject current=null] : iv_ruleLocalNameDeclarationStatement= ruleLocalNameDeclarationStatement EOF ;
    public final EObject entryRuleLocalNameDeclarationStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalNameDeclarationStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4922:2: (iv_ruleLocalNameDeclarationStatement= ruleLocalNameDeclarationStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4923:2: iv_ruleLocalNameDeclarationStatement= ruleLocalNameDeclarationStatement EOF
            {
             newCompositeNode(grammarAccess.getLocalNameDeclarationStatementRule()); 
            pushFollow(FOLLOW_ruleLocalNameDeclarationStatement_in_entryRuleLocalNameDeclarationStatement10749);
            iv_ruleLocalNameDeclarationStatement=ruleLocalNameDeclarationStatement();

            state._fsp--;

             current =iv_ruleLocalNameDeclarationStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalNameDeclarationStatement10759); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLocalNameDeclarationStatement"


    // $ANTLR start "ruleLocalNameDeclarationStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4930:1: ruleLocalNameDeclarationStatement returns [EObject current=null] : (otherlv_0= 'let' ( (lv_varName_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleQualifiedNameWithBinding ) ) ( ( (lv_multiplicityIndicator_4_0= '[' ) ) otherlv_5= ']' )? otherlv_6= '=' ( (lv_init_7_0= ruleSequenceElement ) ) otherlv_8= ';' ) ;
    public final EObject ruleLocalNameDeclarationStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_varName_1_0=null;
        Token otherlv_2=null;
        Token lv_multiplicityIndicator_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_type_3_0 = null;

        EObject lv_init_7_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4933:28: ( (otherlv_0= 'let' ( (lv_varName_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleQualifiedNameWithBinding ) ) ( ( (lv_multiplicityIndicator_4_0= '[' ) ) otherlv_5= ']' )? otherlv_6= '=' ( (lv_init_7_0= ruleSequenceElement ) ) otherlv_8= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4934:1: (otherlv_0= 'let' ( (lv_varName_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleQualifiedNameWithBinding ) ) ( ( (lv_multiplicityIndicator_4_0= '[' ) ) otherlv_5= ']' )? otherlv_6= '=' ( (lv_init_7_0= ruleSequenceElement ) ) otherlv_8= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4934:1: (otherlv_0= 'let' ( (lv_varName_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleQualifiedNameWithBinding ) ) ( ( (lv_multiplicityIndicator_4_0= '[' ) ) otherlv_5= ']' )? otherlv_6= '=' ( (lv_init_7_0= ruleSequenceElement ) ) otherlv_8= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4934:3: otherlv_0= 'let' ( (lv_varName_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleQualifiedNameWithBinding ) ) ( ( (lv_multiplicityIndicator_4_0= '[' ) ) otherlv_5= ']' )? otherlv_6= '=' ( (lv_init_7_0= ruleSequenceElement ) ) otherlv_8= ';'
            {
            otherlv_0=(Token)match(input,69,FOLLOW_69_in_ruleLocalNameDeclarationStatement10796); 

                	newLeafNode(otherlv_0, grammarAccess.getLocalNameDeclarationStatementAccess().getLetKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4938:1: ( (lv_varName_1_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4939:1: (lv_varName_1_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4939:1: (lv_varName_1_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4940:3: lv_varName_1_0= RULE_ID
            {
            lv_varName_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLocalNameDeclarationStatement10813); 

            			newLeafNode(lv_varName_1_0, grammarAccess.getLocalNameDeclarationStatementAccess().getVarNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLocalNameDeclarationStatementRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"varName",
                    		lv_varName_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleLocalNameDeclarationStatement10830); 

                	newLeafNode(otherlv_2, grammarAccess.getLocalNameDeclarationStatementAccess().getColonKeyword_2());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4960:1: ( (lv_type_3_0= ruleQualifiedNameWithBinding ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4961:1: (lv_type_3_0= ruleQualifiedNameWithBinding )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4961:1: (lv_type_3_0= ruleQualifiedNameWithBinding )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4962:3: lv_type_3_0= ruleQualifiedNameWithBinding
            {
             
            	        newCompositeNode(grammarAccess.getLocalNameDeclarationStatementAccess().getTypeQualifiedNameWithBindingParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_ruleLocalNameDeclarationStatement10851);
            lv_type_3_0=ruleQualifiedNameWithBinding();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLocalNameDeclarationStatementRule());
            	        }
                   		set(
                   			current, 
                   			"type",
                    		lv_type_3_0, 
                    		"QualifiedNameWithBinding");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4978:2: ( ( (lv_multiplicityIndicator_4_0= '[' ) ) otherlv_5= ']' )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==50) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4978:3: ( (lv_multiplicityIndicator_4_0= '[' ) ) otherlv_5= ']'
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4978:3: ( (lv_multiplicityIndicator_4_0= '[' ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4979:1: (lv_multiplicityIndicator_4_0= '[' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4979:1: (lv_multiplicityIndicator_4_0= '[' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:4980:3: lv_multiplicityIndicator_4_0= '['
                    {
                    lv_multiplicityIndicator_4_0=(Token)match(input,50,FOLLOW_50_in_ruleLocalNameDeclarationStatement10870); 

                            newLeafNode(lv_multiplicityIndicator_4_0, grammarAccess.getLocalNameDeclarationStatementAccess().getMultiplicityIndicatorLeftSquareBracketKeyword_4_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLocalNameDeclarationStatementRule());
                    	        }
                           		setWithLastConsumed(current, "multiplicityIndicator", true, "[");
                    	    

                    }


                    }

                    otherlv_5=(Token)match(input,51,FOLLOW_51_in_ruleLocalNameDeclarationStatement10895); 

                        	newLeafNode(otherlv_5, grammarAccess.getLocalNameDeclarationStatementAccess().getRightSquareBracketKeyword_4_1());
                        

                    }
                    break;

            }

            otherlv_6=(Token)match(input,70,FOLLOW_70_in_ruleLocalNameDeclarationStatement10909); 

                	newLeafNode(otherlv_6, grammarAccess.getLocalNameDeclarationStatementAccess().getEqualsSignKeyword_5());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5001:1: ( (lv_init_7_0= ruleSequenceElement ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5002:1: (lv_init_7_0= ruleSequenceElement )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5002:1: (lv_init_7_0= ruleSequenceElement )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5003:3: lv_init_7_0= ruleSequenceElement
            {
             
            	        newCompositeNode(grammarAccess.getLocalNameDeclarationStatementAccess().getInitSequenceElementParserRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleSequenceElement_in_ruleLocalNameDeclarationStatement10930);
            lv_init_7_0=ruleSequenceElement();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLocalNameDeclarationStatementRule());
            	        }
                   		set(
                   			current, 
                   			"init",
                    		lv_init_7_0, 
                    		"SequenceElement");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_8=(Token)match(input,68,FOLLOW_68_in_ruleLocalNameDeclarationStatement10942); 

                	newLeafNode(otherlv_8, grammarAccess.getLocalNameDeclarationStatementAccess().getSemicolonKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLocalNameDeclarationStatement"


    // $ANTLR start "entryRuleIfStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5031:1: entryRuleIfStatement returns [EObject current=null] : iv_ruleIfStatement= ruleIfStatement EOF ;
    public final EObject entryRuleIfStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5032:2: (iv_ruleIfStatement= ruleIfStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5033:2: iv_ruleIfStatement= ruleIfStatement EOF
            {
             newCompositeNode(grammarAccess.getIfStatementRule()); 
            pushFollow(FOLLOW_ruleIfStatement_in_entryRuleIfStatement10978);
            iv_ruleIfStatement=ruleIfStatement();

            state._fsp--;

             current =iv_ruleIfStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfStatement10988); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIfStatement"


    // $ANTLR start "ruleIfStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5040:1: ruleIfStatement returns [EObject current=null] : (otherlv_0= 'if' ( (lv_sequentialClausses_1_0= ruleSequentialClauses ) ) ( (lv_finalClause_2_0= ruleFinalClause ) )? ) ;
    public final EObject ruleIfStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_sequentialClausses_1_0 = null;

        EObject lv_finalClause_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5043:28: ( (otherlv_0= 'if' ( (lv_sequentialClausses_1_0= ruleSequentialClauses ) ) ( (lv_finalClause_2_0= ruleFinalClause ) )? ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5044:1: (otherlv_0= 'if' ( (lv_sequentialClausses_1_0= ruleSequentialClauses ) ) ( (lv_finalClause_2_0= ruleFinalClause ) )? )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5044:1: (otherlv_0= 'if' ( (lv_sequentialClausses_1_0= ruleSequentialClauses ) ) ( (lv_finalClause_2_0= ruleFinalClause ) )? )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5044:3: otherlv_0= 'if' ( (lv_sequentialClausses_1_0= ruleSequentialClauses ) ) ( (lv_finalClause_2_0= ruleFinalClause ) )?
            {
            otherlv_0=(Token)match(input,71,FOLLOW_71_in_ruleIfStatement11025); 

                	newLeafNode(otherlv_0, grammarAccess.getIfStatementAccess().getIfKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5048:1: ( (lv_sequentialClausses_1_0= ruleSequentialClauses ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5049:1: (lv_sequentialClausses_1_0= ruleSequentialClauses )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5049:1: (lv_sequentialClausses_1_0= ruleSequentialClauses )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5050:3: lv_sequentialClausses_1_0= ruleSequentialClauses
            {
             
            	        newCompositeNode(grammarAccess.getIfStatementAccess().getSequentialClaussesSequentialClausesParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleSequentialClauses_in_ruleIfStatement11046);
            lv_sequentialClausses_1_0=ruleSequentialClauses();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getIfStatementRule());
            	        }
                   		set(
                   			current, 
                   			"sequentialClausses",
                    		lv_sequentialClausses_1_0, 
                    		"SequentialClauses");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5066:2: ( (lv_finalClause_2_0= ruleFinalClause ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==72) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5067:1: (lv_finalClause_2_0= ruleFinalClause )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5067:1: (lv_finalClause_2_0= ruleFinalClause )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5068:3: lv_finalClause_2_0= ruleFinalClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getIfStatementAccess().getFinalClauseFinalClauseParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleFinalClause_in_ruleIfStatement11067);
                    lv_finalClause_2_0=ruleFinalClause();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getIfStatementRule());
                    	        }
                           		set(
                           			current, 
                           			"finalClause",
                            		lv_finalClause_2_0, 
                            		"FinalClause");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIfStatement"


    // $ANTLR start "entryRuleSequentialClauses"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5092:1: entryRuleSequentialClauses returns [EObject current=null] : iv_ruleSequentialClauses= ruleSequentialClauses EOF ;
    public final EObject entryRuleSequentialClauses() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSequentialClauses = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5093:2: (iv_ruleSequentialClauses= ruleSequentialClauses EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5094:2: iv_ruleSequentialClauses= ruleSequentialClauses EOF
            {
             newCompositeNode(grammarAccess.getSequentialClausesRule()); 
            pushFollow(FOLLOW_ruleSequentialClauses_in_entryRuleSequentialClauses11104);
            iv_ruleSequentialClauses=ruleSequentialClauses();

            state._fsp--;

             current =iv_ruleSequentialClauses; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSequentialClauses11114); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSequentialClauses"


    // $ANTLR start "ruleSequentialClauses"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5101:1: ruleSequentialClauses returns [EObject current=null] : ( ( (lv_conccurentClauses_0_0= ruleConcurrentClauses ) ) (otherlv_1= 'else' otherlv_2= 'if' ( (lv_conccurentClauses_3_0= ruleConcurrentClauses ) ) )* ) ;
    public final EObject ruleSequentialClauses() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_conccurentClauses_0_0 = null;

        EObject lv_conccurentClauses_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5104:28: ( ( ( (lv_conccurentClauses_0_0= ruleConcurrentClauses ) ) (otherlv_1= 'else' otherlv_2= 'if' ( (lv_conccurentClauses_3_0= ruleConcurrentClauses ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5105:1: ( ( (lv_conccurentClauses_0_0= ruleConcurrentClauses ) ) (otherlv_1= 'else' otherlv_2= 'if' ( (lv_conccurentClauses_3_0= ruleConcurrentClauses ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5105:1: ( ( (lv_conccurentClauses_0_0= ruleConcurrentClauses ) ) (otherlv_1= 'else' otherlv_2= 'if' ( (lv_conccurentClauses_3_0= ruleConcurrentClauses ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5105:2: ( (lv_conccurentClauses_0_0= ruleConcurrentClauses ) ) (otherlv_1= 'else' otherlv_2= 'if' ( (lv_conccurentClauses_3_0= ruleConcurrentClauses ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5105:2: ( (lv_conccurentClauses_0_0= ruleConcurrentClauses ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5106:1: (lv_conccurentClauses_0_0= ruleConcurrentClauses )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5106:1: (lv_conccurentClauses_0_0= ruleConcurrentClauses )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5107:3: lv_conccurentClauses_0_0= ruleConcurrentClauses
            {
             
            	        newCompositeNode(grammarAccess.getSequentialClausesAccess().getConccurentClausesConcurrentClausesParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleConcurrentClauses_in_ruleSequentialClauses11160);
            lv_conccurentClauses_0_0=ruleConcurrentClauses();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSequentialClausesRule());
            	        }
                   		add(
                   			current, 
                   			"conccurentClauses",
                    		lv_conccurentClauses_0_0, 
                    		"ConcurrentClauses");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5123:2: (otherlv_1= 'else' otherlv_2= 'if' ( (lv_conccurentClauses_3_0= ruleConcurrentClauses ) ) )*
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( (LA76_0==72) ) {
                    int LA76_1 = input.LA(2);

                    if ( (LA76_1==71) ) {
                        alt76=1;
                    }


                }


                switch (alt76) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5123:4: otherlv_1= 'else' otherlv_2= 'if' ( (lv_conccurentClauses_3_0= ruleConcurrentClauses ) )
            	    {
            	    otherlv_1=(Token)match(input,72,FOLLOW_72_in_ruleSequentialClauses11173); 

            	        	newLeafNode(otherlv_1, grammarAccess.getSequentialClausesAccess().getElseKeyword_1_0());
            	        
            	    otherlv_2=(Token)match(input,71,FOLLOW_71_in_ruleSequentialClauses11185); 

            	        	newLeafNode(otherlv_2, grammarAccess.getSequentialClausesAccess().getIfKeyword_1_1());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5131:1: ( (lv_conccurentClauses_3_0= ruleConcurrentClauses ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5132:1: (lv_conccurentClauses_3_0= ruleConcurrentClauses )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5132:1: (lv_conccurentClauses_3_0= ruleConcurrentClauses )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5133:3: lv_conccurentClauses_3_0= ruleConcurrentClauses
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSequentialClausesAccess().getConccurentClausesConcurrentClausesParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleConcurrentClauses_in_ruleSequentialClauses11206);
            	    lv_conccurentClauses_3_0=ruleConcurrentClauses();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSequentialClausesRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"conccurentClauses",
            	            		lv_conccurentClauses_3_0, 
            	            		"ConcurrentClauses");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop76;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSequentialClauses"


    // $ANTLR start "entryRuleConcurrentClauses"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5157:1: entryRuleConcurrentClauses returns [EObject current=null] : iv_ruleConcurrentClauses= ruleConcurrentClauses EOF ;
    public final EObject entryRuleConcurrentClauses() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConcurrentClauses = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5158:2: (iv_ruleConcurrentClauses= ruleConcurrentClauses EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5159:2: iv_ruleConcurrentClauses= ruleConcurrentClauses EOF
            {
             newCompositeNode(grammarAccess.getConcurrentClausesRule()); 
            pushFollow(FOLLOW_ruleConcurrentClauses_in_entryRuleConcurrentClauses11244);
            iv_ruleConcurrentClauses=ruleConcurrentClauses();

            state._fsp--;

             current =iv_ruleConcurrentClauses; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConcurrentClauses11254); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConcurrentClauses"


    // $ANTLR start "ruleConcurrentClauses"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5166:1: ruleConcurrentClauses returns [EObject current=null] : ( ( (lv_nonFinalClause_0_0= ruleNonFinalClause ) ) (otherlv_1= 'or' otherlv_2= 'if' ( (lv_nonFinalClause_3_0= ruleNonFinalClause ) ) )* ) ;
    public final EObject ruleConcurrentClauses() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_nonFinalClause_0_0 = null;

        EObject lv_nonFinalClause_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5169:28: ( ( ( (lv_nonFinalClause_0_0= ruleNonFinalClause ) ) (otherlv_1= 'or' otherlv_2= 'if' ( (lv_nonFinalClause_3_0= ruleNonFinalClause ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5170:1: ( ( (lv_nonFinalClause_0_0= ruleNonFinalClause ) ) (otherlv_1= 'or' otherlv_2= 'if' ( (lv_nonFinalClause_3_0= ruleNonFinalClause ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5170:1: ( ( (lv_nonFinalClause_0_0= ruleNonFinalClause ) ) (otherlv_1= 'or' otherlv_2= 'if' ( (lv_nonFinalClause_3_0= ruleNonFinalClause ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5170:2: ( (lv_nonFinalClause_0_0= ruleNonFinalClause ) ) (otherlv_1= 'or' otherlv_2= 'if' ( (lv_nonFinalClause_3_0= ruleNonFinalClause ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5170:2: ( (lv_nonFinalClause_0_0= ruleNonFinalClause ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5171:1: (lv_nonFinalClause_0_0= ruleNonFinalClause )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5171:1: (lv_nonFinalClause_0_0= ruleNonFinalClause )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5172:3: lv_nonFinalClause_0_0= ruleNonFinalClause
            {
             
            	        newCompositeNode(grammarAccess.getConcurrentClausesAccess().getNonFinalClauseNonFinalClauseParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleNonFinalClause_in_ruleConcurrentClauses11300);
            lv_nonFinalClause_0_0=ruleNonFinalClause();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConcurrentClausesRule());
            	        }
                   		add(
                   			current, 
                   			"nonFinalClause",
                    		lv_nonFinalClause_0_0, 
                    		"NonFinalClause");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5188:2: (otherlv_1= 'or' otherlv_2= 'if' ( (lv_nonFinalClause_3_0= ruleNonFinalClause ) ) )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==73) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5188:4: otherlv_1= 'or' otherlv_2= 'if' ( (lv_nonFinalClause_3_0= ruleNonFinalClause ) )
            	    {
            	    otherlv_1=(Token)match(input,73,FOLLOW_73_in_ruleConcurrentClauses11313); 

            	        	newLeafNode(otherlv_1, grammarAccess.getConcurrentClausesAccess().getOrKeyword_1_0());
            	        
            	    otherlv_2=(Token)match(input,71,FOLLOW_71_in_ruleConcurrentClauses11325); 

            	        	newLeafNode(otherlv_2, grammarAccess.getConcurrentClausesAccess().getIfKeyword_1_1());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5196:1: ( (lv_nonFinalClause_3_0= ruleNonFinalClause ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5197:1: (lv_nonFinalClause_3_0= ruleNonFinalClause )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5197:1: (lv_nonFinalClause_3_0= ruleNonFinalClause )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5198:3: lv_nonFinalClause_3_0= ruleNonFinalClause
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getConcurrentClausesAccess().getNonFinalClauseNonFinalClauseParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleNonFinalClause_in_ruleConcurrentClauses11346);
            	    lv_nonFinalClause_3_0=ruleNonFinalClause();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getConcurrentClausesRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"nonFinalClause",
            	            		lv_nonFinalClause_3_0, 
            	            		"NonFinalClause");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop77;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConcurrentClauses"


    // $ANTLR start "entryRuleNonFinalClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5222:1: entryRuleNonFinalClause returns [EObject current=null] : iv_ruleNonFinalClause= ruleNonFinalClause EOF ;
    public final EObject entryRuleNonFinalClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNonFinalClause = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5223:2: (iv_ruleNonFinalClause= ruleNonFinalClause EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5224:2: iv_ruleNonFinalClause= ruleNonFinalClause EOF
            {
             newCompositeNode(grammarAccess.getNonFinalClauseRule()); 
            pushFollow(FOLLOW_ruleNonFinalClause_in_entryRuleNonFinalClause11384);
            iv_ruleNonFinalClause=ruleNonFinalClause();

            state._fsp--;

             current =iv_ruleNonFinalClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNonFinalClause11394); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNonFinalClause"


    // $ANTLR start "ruleNonFinalClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5231:1: ruleNonFinalClause returns [EObject current=null] : (otherlv_0= '(' ( (lv_condition_1_0= ruleExpression ) ) otherlv_2= ')' ( (lv_block_3_0= ruleBlock ) ) ) ;
    public final EObject ruleNonFinalClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_condition_1_0 = null;

        EObject lv_block_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5234:28: ( (otherlv_0= '(' ( (lv_condition_1_0= ruleExpression ) ) otherlv_2= ')' ( (lv_block_3_0= ruleBlock ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5235:1: (otherlv_0= '(' ( (lv_condition_1_0= ruleExpression ) ) otherlv_2= ')' ( (lv_block_3_0= ruleBlock ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5235:1: (otherlv_0= '(' ( (lv_condition_1_0= ruleExpression ) ) otherlv_2= ')' ( (lv_block_3_0= ruleBlock ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5235:3: otherlv_0= '(' ( (lv_condition_1_0= ruleExpression ) ) otherlv_2= ')' ( (lv_block_3_0= ruleBlock ) )
            {
            otherlv_0=(Token)match(input,24,FOLLOW_24_in_ruleNonFinalClause11431); 

                	newLeafNode(otherlv_0, grammarAccess.getNonFinalClauseAccess().getLeftParenthesisKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5239:1: ( (lv_condition_1_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5240:1: (lv_condition_1_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5240:1: (lv_condition_1_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5241:3: lv_condition_1_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getNonFinalClauseAccess().getConditionExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleNonFinalClause11452);
            lv_condition_1_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getNonFinalClauseRule());
            	        }
                   		set(
                   			current, 
                   			"condition",
                    		lv_condition_1_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,25,FOLLOW_25_in_ruleNonFinalClause11464); 

                	newLeafNode(otherlv_2, grammarAccess.getNonFinalClauseAccess().getRightParenthesisKeyword_2());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5261:1: ( (lv_block_3_0= ruleBlock ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5262:1: (lv_block_3_0= ruleBlock )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5262:1: (lv_block_3_0= ruleBlock )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5263:3: lv_block_3_0= ruleBlock
            {
             
            	        newCompositeNode(grammarAccess.getNonFinalClauseAccess().getBlockBlockParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleBlock_in_ruleNonFinalClause11485);
            lv_block_3_0=ruleBlock();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getNonFinalClauseRule());
            	        }
                   		set(
                   			current, 
                   			"block",
                    		lv_block_3_0, 
                    		"Block");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNonFinalClause"


    // $ANTLR start "entryRuleFinalClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5287:1: entryRuleFinalClause returns [EObject current=null] : iv_ruleFinalClause= ruleFinalClause EOF ;
    public final EObject entryRuleFinalClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFinalClause = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5288:2: (iv_ruleFinalClause= ruleFinalClause EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5289:2: iv_ruleFinalClause= ruleFinalClause EOF
            {
             newCompositeNode(grammarAccess.getFinalClauseRule()); 
            pushFollow(FOLLOW_ruleFinalClause_in_entryRuleFinalClause11521);
            iv_ruleFinalClause=ruleFinalClause();

            state._fsp--;

             current =iv_ruleFinalClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFinalClause11531); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFinalClause"


    // $ANTLR start "ruleFinalClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5296:1: ruleFinalClause returns [EObject current=null] : (otherlv_0= 'else' ( (lv_block_1_0= ruleBlock ) ) ) ;
    public final EObject ruleFinalClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_block_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5299:28: ( (otherlv_0= 'else' ( (lv_block_1_0= ruleBlock ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5300:1: (otherlv_0= 'else' ( (lv_block_1_0= ruleBlock ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5300:1: (otherlv_0= 'else' ( (lv_block_1_0= ruleBlock ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5300:3: otherlv_0= 'else' ( (lv_block_1_0= ruleBlock ) )
            {
            otherlv_0=(Token)match(input,72,FOLLOW_72_in_ruleFinalClause11568); 

                	newLeafNode(otherlv_0, grammarAccess.getFinalClauseAccess().getElseKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5304:1: ( (lv_block_1_0= ruleBlock ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5305:1: (lv_block_1_0= ruleBlock )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5305:1: (lv_block_1_0= ruleBlock )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5306:3: lv_block_1_0= ruleBlock
            {
             
            	        newCompositeNode(grammarAccess.getFinalClauseAccess().getBlockBlockParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleBlock_in_ruleFinalClause11589);
            lv_block_1_0=ruleBlock();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFinalClauseRule());
            	        }
                   		set(
                   			current, 
                   			"block",
                    		lv_block_1_0, 
                    		"Block");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFinalClause"


    // $ANTLR start "entryRuleSwitchStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5330:1: entryRuleSwitchStatement returns [EObject current=null] : iv_ruleSwitchStatement= ruleSwitchStatement EOF ;
    public final EObject entryRuleSwitchStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5331:2: (iv_ruleSwitchStatement= ruleSwitchStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5332:2: iv_ruleSwitchStatement= ruleSwitchStatement EOF
            {
             newCompositeNode(grammarAccess.getSwitchStatementRule()); 
            pushFollow(FOLLOW_ruleSwitchStatement_in_entryRuleSwitchStatement11625);
            iv_ruleSwitchStatement=ruleSwitchStatement();

            state._fsp--;

             current =iv_ruleSwitchStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchStatement11635); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSwitchStatement"


    // $ANTLR start "ruleSwitchStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5339:1: ruleSwitchStatement returns [EObject current=null] : (otherlv_0= 'switch' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_switchClause_5_0= ruleSwitchClause ) )* ( (lv_defaultClause_6_0= ruleSwitchDefaultClause ) )? otherlv_7= '}' ) ;
    public final EObject ruleSwitchStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        EObject lv_expression_2_0 = null;

        EObject lv_switchClause_5_0 = null;

        EObject lv_defaultClause_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5342:28: ( (otherlv_0= 'switch' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_switchClause_5_0= ruleSwitchClause ) )* ( (lv_defaultClause_6_0= ruleSwitchDefaultClause ) )? otherlv_7= '}' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5343:1: (otherlv_0= 'switch' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_switchClause_5_0= ruleSwitchClause ) )* ( (lv_defaultClause_6_0= ruleSwitchDefaultClause ) )? otherlv_7= '}' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5343:1: (otherlv_0= 'switch' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_switchClause_5_0= ruleSwitchClause ) )* ( (lv_defaultClause_6_0= ruleSwitchDefaultClause ) )? otherlv_7= '}' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5343:3: otherlv_0= 'switch' otherlv_1= '(' ( (lv_expression_2_0= ruleExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_switchClause_5_0= ruleSwitchClause ) )* ( (lv_defaultClause_6_0= ruleSwitchDefaultClause ) )? otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,74,FOLLOW_74_in_ruleSwitchStatement11672); 

                	newLeafNode(otherlv_0, grammarAccess.getSwitchStatementAccess().getSwitchKeyword_0());
                
            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleSwitchStatement11684); 

                	newLeafNode(otherlv_1, grammarAccess.getSwitchStatementAccess().getLeftParenthesisKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5351:1: ( (lv_expression_2_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5352:1: (lv_expression_2_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5352:1: (lv_expression_2_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5353:3: lv_expression_2_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getSwitchStatementAccess().getExpressionExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleSwitchStatement11705);
            lv_expression_2_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSwitchStatementRule());
            	        }
                   		set(
                   			current, 
                   			"expression",
                    		lv_expression_2_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,25,FOLLOW_25_in_ruleSwitchStatement11717); 

                	newLeafNode(otherlv_3, grammarAccess.getSwitchStatementAccess().getRightParenthesisKeyword_3());
                
            otherlv_4=(Token)match(input,60,FOLLOW_60_in_ruleSwitchStatement11729); 

                	newLeafNode(otherlv_4, grammarAccess.getSwitchStatementAccess().getLeftCurlyBracketKeyword_4());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5377:1: ( (lv_switchClause_5_0= ruleSwitchClause ) )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==75) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5378:1: (lv_switchClause_5_0= ruleSwitchClause )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5378:1: (lv_switchClause_5_0= ruleSwitchClause )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5379:3: lv_switchClause_5_0= ruleSwitchClause
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSwitchStatementAccess().getSwitchClauseSwitchClauseParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSwitchClause_in_ruleSwitchStatement11750);
            	    lv_switchClause_5_0=ruleSwitchClause();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSwitchStatementRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"switchClause",
            	            		lv_switchClause_5_0, 
            	            		"SwitchClause");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop78;
                }
            } while (true);

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5395:3: ( (lv_defaultClause_6_0= ruleSwitchDefaultClause ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==76) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5396:1: (lv_defaultClause_6_0= ruleSwitchDefaultClause )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5396:1: (lv_defaultClause_6_0= ruleSwitchDefaultClause )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5397:3: lv_defaultClause_6_0= ruleSwitchDefaultClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getSwitchStatementAccess().getDefaultClauseSwitchDefaultClauseParserRuleCall_6_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSwitchDefaultClause_in_ruleSwitchStatement11772);
                    lv_defaultClause_6_0=ruleSwitchDefaultClause();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSwitchStatementRule());
                    	        }
                           		set(
                           			current, 
                           			"defaultClause",
                            		lv_defaultClause_6_0, 
                            		"SwitchDefaultClause");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,62,FOLLOW_62_in_ruleSwitchStatement11785); 

                	newLeafNode(otherlv_7, grammarAccess.getSwitchStatementAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSwitchStatement"


    // $ANTLR start "entryRuleSwitchClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5425:1: entryRuleSwitchClause returns [EObject current=null] : iv_ruleSwitchClause= ruleSwitchClause EOF ;
    public final EObject entryRuleSwitchClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchClause = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5426:2: (iv_ruleSwitchClause= ruleSwitchClause EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5427:2: iv_ruleSwitchClause= ruleSwitchClause EOF
            {
             newCompositeNode(grammarAccess.getSwitchClauseRule()); 
            pushFollow(FOLLOW_ruleSwitchClause_in_entryRuleSwitchClause11821);
            iv_ruleSwitchClause=ruleSwitchClause();

            state._fsp--;

             current =iv_ruleSwitchClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchClause11831); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSwitchClause"


    // $ANTLR start "ruleSwitchClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5434:1: ruleSwitchClause returns [EObject current=null] : ( ( (lv_switchCase_0_0= ruleSwitchCase ) ) ( (lv_switchCase_1_0= ruleSwitchCase ) )* ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) ) ) ;
    public final EObject ruleSwitchClause() throws RecognitionException {
        EObject current = null;

        EObject lv_switchCase_0_0 = null;

        EObject lv_switchCase_1_0 = null;

        EObject lv_statementSequence_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5437:28: ( ( ( (lv_switchCase_0_0= ruleSwitchCase ) ) ( (lv_switchCase_1_0= ruleSwitchCase ) )* ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5438:1: ( ( (lv_switchCase_0_0= ruleSwitchCase ) ) ( (lv_switchCase_1_0= ruleSwitchCase ) )* ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5438:1: ( ( (lv_switchCase_0_0= ruleSwitchCase ) ) ( (lv_switchCase_1_0= ruleSwitchCase ) )* ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5438:2: ( (lv_switchCase_0_0= ruleSwitchCase ) ) ( (lv_switchCase_1_0= ruleSwitchCase ) )* ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5438:2: ( (lv_switchCase_0_0= ruleSwitchCase ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5439:1: (lv_switchCase_0_0= ruleSwitchCase )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5439:1: (lv_switchCase_0_0= ruleSwitchCase )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5440:3: lv_switchCase_0_0= ruleSwitchCase
            {
             
            	        newCompositeNode(grammarAccess.getSwitchClauseAccess().getSwitchCaseSwitchCaseParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleSwitchCase_in_ruleSwitchClause11877);
            lv_switchCase_0_0=ruleSwitchCase();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSwitchClauseRule());
            	        }
                   		add(
                   			current, 
                   			"switchCase",
                    		lv_switchCase_0_0, 
                    		"SwitchCase");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5456:2: ( (lv_switchCase_1_0= ruleSwitchCase ) )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==75) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5457:1: (lv_switchCase_1_0= ruleSwitchCase )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5457:1: (lv_switchCase_1_0= ruleSwitchCase )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5458:3: lv_switchCase_1_0= ruleSwitchCase
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSwitchClauseAccess().getSwitchCaseSwitchCaseParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSwitchCase_in_ruleSwitchClause11898);
            	    lv_switchCase_1_0=ruleSwitchCase();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSwitchClauseRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"switchCase",
            	            		lv_switchCase_1_0, 
            	            		"SwitchCase");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop80;
                }
            } while (true);

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5474:3: ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5475:1: (lv_statementSequence_2_0= ruleNonEmptyStatementSequence )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5475:1: (lv_statementSequence_2_0= ruleNonEmptyStatementSequence )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5476:3: lv_statementSequence_2_0= ruleNonEmptyStatementSequence
            {
             
            	        newCompositeNode(grammarAccess.getSwitchClauseAccess().getStatementSequenceNonEmptyStatementSequenceParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleNonEmptyStatementSequence_in_ruleSwitchClause11920);
            lv_statementSequence_2_0=ruleNonEmptyStatementSequence();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSwitchClauseRule());
            	        }
                   		set(
                   			current, 
                   			"statementSequence",
                    		lv_statementSequence_2_0, 
                    		"NonEmptyStatementSequence");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSwitchClause"


    // $ANTLR start "entryRuleSwitchCase"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5500:1: entryRuleSwitchCase returns [EObject current=null] : iv_ruleSwitchCase= ruleSwitchCase EOF ;
    public final EObject entryRuleSwitchCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchCase = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5501:2: (iv_ruleSwitchCase= ruleSwitchCase EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5502:2: iv_ruleSwitchCase= ruleSwitchCase EOF
            {
             newCompositeNode(grammarAccess.getSwitchCaseRule()); 
            pushFollow(FOLLOW_ruleSwitchCase_in_entryRuleSwitchCase11956);
            iv_ruleSwitchCase=ruleSwitchCase();

            state._fsp--;

             current =iv_ruleSwitchCase; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchCase11966); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSwitchCase"


    // $ANTLR start "ruleSwitchCase"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5509:1: ruleSwitchCase returns [EObject current=null] : (otherlv_0= 'case' ( (lv_expression_1_0= ruleExpression ) ) otherlv_2= ':' ) ;
    public final EObject ruleSwitchCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_expression_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5512:28: ( (otherlv_0= 'case' ( (lv_expression_1_0= ruleExpression ) ) otherlv_2= ':' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5513:1: (otherlv_0= 'case' ( (lv_expression_1_0= ruleExpression ) ) otherlv_2= ':' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5513:1: (otherlv_0= 'case' ( (lv_expression_1_0= ruleExpression ) ) otherlv_2= ':' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5513:3: otherlv_0= 'case' ( (lv_expression_1_0= ruleExpression ) ) otherlv_2= ':'
            {
            otherlv_0=(Token)match(input,75,FOLLOW_75_in_ruleSwitchCase12003); 

                	newLeafNode(otherlv_0, grammarAccess.getSwitchCaseAccess().getCaseKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5517:1: ( (lv_expression_1_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5518:1: (lv_expression_1_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5518:1: (lv_expression_1_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5519:3: lv_expression_1_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getSwitchCaseAccess().getExpressionExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleSwitchCase12024);
            lv_expression_1_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSwitchCaseRule());
            	        }
                   		set(
                   			current, 
                   			"expression",
                    		lv_expression_1_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleSwitchCase12036); 

                	newLeafNode(otherlv_2, grammarAccess.getSwitchCaseAccess().getColonKeyword_2());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSwitchCase"


    // $ANTLR start "entryRuleSwitchDefaultClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5547:1: entryRuleSwitchDefaultClause returns [EObject current=null] : iv_ruleSwitchDefaultClause= ruleSwitchDefaultClause EOF ;
    public final EObject entryRuleSwitchDefaultClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchDefaultClause = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5548:2: (iv_ruleSwitchDefaultClause= ruleSwitchDefaultClause EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5549:2: iv_ruleSwitchDefaultClause= ruleSwitchDefaultClause EOF
            {
             newCompositeNode(grammarAccess.getSwitchDefaultClauseRule()); 
            pushFollow(FOLLOW_ruleSwitchDefaultClause_in_entryRuleSwitchDefaultClause12072);
            iv_ruleSwitchDefaultClause=ruleSwitchDefaultClause();

            state._fsp--;

             current =iv_ruleSwitchDefaultClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchDefaultClause12082); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSwitchDefaultClause"


    // $ANTLR start "ruleSwitchDefaultClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5556:1: ruleSwitchDefaultClause returns [EObject current=null] : (otherlv_0= 'default' otherlv_1= ':' ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) ) ) ;
    public final EObject ruleSwitchDefaultClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_statementSequence_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5559:28: ( (otherlv_0= 'default' otherlv_1= ':' ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5560:1: (otherlv_0= 'default' otherlv_1= ':' ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5560:1: (otherlv_0= 'default' otherlv_1= ':' ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5560:3: otherlv_0= 'default' otherlv_1= ':' ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) )
            {
            otherlv_0=(Token)match(input,76,FOLLOW_76_in_ruleSwitchDefaultClause12119); 

                	newLeafNode(otherlv_0, grammarAccess.getSwitchDefaultClauseAccess().getDefaultKeyword_0());
                
            otherlv_1=(Token)match(input,27,FOLLOW_27_in_ruleSwitchDefaultClause12131); 

                	newLeafNode(otherlv_1, grammarAccess.getSwitchDefaultClauseAccess().getColonKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5568:1: ( (lv_statementSequence_2_0= ruleNonEmptyStatementSequence ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5569:1: (lv_statementSequence_2_0= ruleNonEmptyStatementSequence )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5569:1: (lv_statementSequence_2_0= ruleNonEmptyStatementSequence )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5570:3: lv_statementSequence_2_0= ruleNonEmptyStatementSequence
            {
             
            	        newCompositeNode(grammarAccess.getSwitchDefaultClauseAccess().getStatementSequenceNonEmptyStatementSequenceParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleNonEmptyStatementSequence_in_ruleSwitchDefaultClause12152);
            lv_statementSequence_2_0=ruleNonEmptyStatementSequence();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSwitchDefaultClauseRule());
            	        }
                   		set(
                   			current, 
                   			"statementSequence",
                    		lv_statementSequence_2_0, 
                    		"NonEmptyStatementSequence");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSwitchDefaultClause"


    // $ANTLR start "entryRuleNonEmptyStatementSequence"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5594:1: entryRuleNonEmptyStatementSequence returns [EObject current=null] : iv_ruleNonEmptyStatementSequence= ruleNonEmptyStatementSequence EOF ;
    public final EObject entryRuleNonEmptyStatementSequence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNonEmptyStatementSequence = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5595:2: (iv_ruleNonEmptyStatementSequence= ruleNonEmptyStatementSequence EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5596:2: iv_ruleNonEmptyStatementSequence= ruleNonEmptyStatementSequence EOF
            {
             newCompositeNode(grammarAccess.getNonEmptyStatementSequenceRule()); 
            pushFollow(FOLLOW_ruleNonEmptyStatementSequence_in_entryRuleNonEmptyStatementSequence12188);
            iv_ruleNonEmptyStatementSequence=ruleNonEmptyStatementSequence();

            state._fsp--;

             current =iv_ruleNonEmptyStatementSequence; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNonEmptyStatementSequence12198); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNonEmptyStatementSequence"


    // $ANTLR start "ruleNonEmptyStatementSequence"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5603:1: ruleNonEmptyStatementSequence returns [EObject current=null] : ( (lv_statement_0_0= ruleDocumentedStatement ) )+ ;
    public final EObject ruleNonEmptyStatementSequence() throws RecognitionException {
        EObject current = null;

        EObject lv_statement_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5606:28: ( ( (lv_statement_0_0= ruleDocumentedStatement ) )+ )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5607:1: ( (lv_statement_0_0= ruleDocumentedStatement ) )+
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5607:1: ( (lv_statement_0_0= ruleDocumentedStatement ) )+
            int cnt81=0;
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( ((LA81_0>=RULE_ID && LA81_0<=RULE_SL_COMMENT)||(LA81_0>=17 && LA81_0<=18)||(LA81_0>=57 && LA81_0<=60)||LA81_0==64||(LA81_0>=67 && LA81_0<=69)||LA81_0==71||LA81_0==74||(LA81_0>=77 && LA81_0<=79)||(LA81_0>=81 && LA81_0<=84)) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5608:1: (lv_statement_0_0= ruleDocumentedStatement )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5608:1: (lv_statement_0_0= ruleDocumentedStatement )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5609:3: lv_statement_0_0= ruleDocumentedStatement
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getNonEmptyStatementSequenceAccess().getStatementDocumentedStatementParserRuleCall_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleDocumentedStatement_in_ruleNonEmptyStatementSequence12243);
            	    lv_statement_0_0=ruleDocumentedStatement();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getNonEmptyStatementSequenceRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"statement",
            	            		lv_statement_0_0, 
            	            		"DocumentedStatement");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt81 >= 1 ) break loop81;
                        EarlyExitException eee =
                            new EarlyExitException(81, input);
                        throw eee;
                }
                cnt81++;
            } while (true);


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNonEmptyStatementSequence"


    // $ANTLR start "entryRuleWhileStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5633:1: entryRuleWhileStatement returns [EObject current=null] : iv_ruleWhileStatement= ruleWhileStatement EOF ;
    public final EObject entryRuleWhileStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhileStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5634:2: (iv_ruleWhileStatement= ruleWhileStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5635:2: iv_ruleWhileStatement= ruleWhileStatement EOF
            {
             newCompositeNode(grammarAccess.getWhileStatementRule()); 
            pushFollow(FOLLOW_ruleWhileStatement_in_entryRuleWhileStatement12279);
            iv_ruleWhileStatement=ruleWhileStatement();

            state._fsp--;

             current =iv_ruleWhileStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWhileStatement12289); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWhileStatement"


    // $ANTLR start "ruleWhileStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5642:1: ruleWhileStatement returns [EObject current=null] : (otherlv_0= 'while' otherlv_1= '(' ( (lv_condition_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) ) ;
    public final EObject ruleWhileStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_condition_2_0 = null;

        EObject lv_block_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5645:28: ( (otherlv_0= 'while' otherlv_1= '(' ( (lv_condition_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5646:1: (otherlv_0= 'while' otherlv_1= '(' ( (lv_condition_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5646:1: (otherlv_0= 'while' otherlv_1= '(' ( (lv_condition_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5646:3: otherlv_0= 'while' otherlv_1= '(' ( (lv_condition_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) )
            {
            otherlv_0=(Token)match(input,77,FOLLOW_77_in_ruleWhileStatement12326); 

                	newLeafNode(otherlv_0, grammarAccess.getWhileStatementAccess().getWhileKeyword_0());
                
            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleWhileStatement12338); 

                	newLeafNode(otherlv_1, grammarAccess.getWhileStatementAccess().getLeftParenthesisKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5654:1: ( (lv_condition_2_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5655:1: (lv_condition_2_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5655:1: (lv_condition_2_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5656:3: lv_condition_2_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getWhileStatementAccess().getConditionExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleWhileStatement12359);
            lv_condition_2_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getWhileStatementRule());
            	        }
                   		set(
                   			current, 
                   			"condition",
                    		lv_condition_2_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,25,FOLLOW_25_in_ruleWhileStatement12371); 

                	newLeafNode(otherlv_3, grammarAccess.getWhileStatementAccess().getRightParenthesisKeyword_3());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5676:1: ( (lv_block_4_0= ruleBlock ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5677:1: (lv_block_4_0= ruleBlock )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5677:1: (lv_block_4_0= ruleBlock )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5678:3: lv_block_4_0= ruleBlock
            {
             
            	        newCompositeNode(grammarAccess.getWhileStatementAccess().getBlockBlockParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleBlock_in_ruleWhileStatement12392);
            lv_block_4_0=ruleBlock();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getWhileStatementRule());
            	        }
                   		set(
                   			current, 
                   			"block",
                    		lv_block_4_0, 
                    		"Block");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWhileStatement"


    // $ANTLR start "entryRuleDoStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5702:1: entryRuleDoStatement returns [EObject current=null] : iv_ruleDoStatement= ruleDoStatement EOF ;
    public final EObject entryRuleDoStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5703:2: (iv_ruleDoStatement= ruleDoStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5704:2: iv_ruleDoStatement= ruleDoStatement EOF
            {
             newCompositeNode(grammarAccess.getDoStatementRule()); 
            pushFollow(FOLLOW_ruleDoStatement_in_entryRuleDoStatement12428);
            iv_ruleDoStatement=ruleDoStatement();

            state._fsp--;

             current =iv_ruleDoStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDoStatement12438); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDoStatement"


    // $ANTLR start "ruleDoStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5711:1: ruleDoStatement returns [EObject current=null] : (otherlv_0= 'do' ( (lv_block_1_0= ruleBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_condition_4_0= ruleExpression ) ) otherlv_5= ')' otherlv_6= ';' ) ;
    public final EObject ruleDoStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_block_1_0 = null;

        EObject lv_condition_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5714:28: ( (otherlv_0= 'do' ( (lv_block_1_0= ruleBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_condition_4_0= ruleExpression ) ) otherlv_5= ')' otherlv_6= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5715:1: (otherlv_0= 'do' ( (lv_block_1_0= ruleBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_condition_4_0= ruleExpression ) ) otherlv_5= ')' otherlv_6= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5715:1: (otherlv_0= 'do' ( (lv_block_1_0= ruleBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_condition_4_0= ruleExpression ) ) otherlv_5= ')' otherlv_6= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5715:3: otherlv_0= 'do' ( (lv_block_1_0= ruleBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_condition_4_0= ruleExpression ) ) otherlv_5= ')' otherlv_6= ';'
            {
            otherlv_0=(Token)match(input,78,FOLLOW_78_in_ruleDoStatement12475); 

                	newLeafNode(otherlv_0, grammarAccess.getDoStatementAccess().getDoKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5719:1: ( (lv_block_1_0= ruleBlock ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5720:1: (lv_block_1_0= ruleBlock )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5720:1: (lv_block_1_0= ruleBlock )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5721:3: lv_block_1_0= ruleBlock
            {
             
            	        newCompositeNode(grammarAccess.getDoStatementAccess().getBlockBlockParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleBlock_in_ruleDoStatement12496);
            lv_block_1_0=ruleBlock();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDoStatementRule());
            	        }
                   		set(
                   			current, 
                   			"block",
                    		lv_block_1_0, 
                    		"Block");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,77,FOLLOW_77_in_ruleDoStatement12508); 

                	newLeafNode(otherlv_2, grammarAccess.getDoStatementAccess().getWhileKeyword_2());
                
            otherlv_3=(Token)match(input,24,FOLLOW_24_in_ruleDoStatement12520); 

                	newLeafNode(otherlv_3, grammarAccess.getDoStatementAccess().getLeftParenthesisKeyword_3());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5745:1: ( (lv_condition_4_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5746:1: (lv_condition_4_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5746:1: (lv_condition_4_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5747:3: lv_condition_4_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getDoStatementAccess().getConditionExpressionParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleDoStatement12541);
            lv_condition_4_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDoStatementRule());
            	        }
                   		set(
                   			current, 
                   			"condition",
                    		lv_condition_4_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleDoStatement12553); 

                	newLeafNode(otherlv_5, grammarAccess.getDoStatementAccess().getRightParenthesisKeyword_5());
                
            otherlv_6=(Token)match(input,68,FOLLOW_68_in_ruleDoStatement12565); 

                	newLeafNode(otherlv_6, grammarAccess.getDoStatementAccess().getSemicolonKeyword_6());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDoStatement"


    // $ANTLR start "entryRuleForStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5779:1: entryRuleForStatement returns [EObject current=null] : iv_ruleForStatement= ruleForStatement EOF ;
    public final EObject entryRuleForStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5780:2: (iv_ruleForStatement= ruleForStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5781:2: iv_ruleForStatement= ruleForStatement EOF
            {
             newCompositeNode(grammarAccess.getForStatementRule()); 
            pushFollow(FOLLOW_ruleForStatement_in_entryRuleForStatement12601);
            iv_ruleForStatement=ruleForStatement();

            state._fsp--;

             current =iv_ruleForStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleForStatement12611); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForStatement"


    // $ANTLR start "ruleForStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5788:1: ruleForStatement returns [EObject current=null] : (otherlv_0= 'for' otherlv_1= '(' ( (lv_control_2_0= ruleForControl ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) ) ;
    public final EObject ruleForStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_control_2_0 = null;

        EObject lv_block_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5791:28: ( (otherlv_0= 'for' otherlv_1= '(' ( (lv_control_2_0= ruleForControl ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5792:1: (otherlv_0= 'for' otherlv_1= '(' ( (lv_control_2_0= ruleForControl ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5792:1: (otherlv_0= 'for' otherlv_1= '(' ( (lv_control_2_0= ruleForControl ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5792:3: otherlv_0= 'for' otherlv_1= '(' ( (lv_control_2_0= ruleForControl ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) )
            {
            otherlv_0=(Token)match(input,79,FOLLOW_79_in_ruleForStatement12648); 

                	newLeafNode(otherlv_0, grammarAccess.getForStatementAccess().getForKeyword_0());
                
            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleForStatement12660); 

                	newLeafNode(otherlv_1, grammarAccess.getForStatementAccess().getLeftParenthesisKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5800:1: ( (lv_control_2_0= ruleForControl ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5801:1: (lv_control_2_0= ruleForControl )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5801:1: (lv_control_2_0= ruleForControl )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5802:3: lv_control_2_0= ruleForControl
            {
             
            	        newCompositeNode(grammarAccess.getForStatementAccess().getControlForControlParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleForControl_in_ruleForStatement12681);
            lv_control_2_0=ruleForControl();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getForStatementRule());
            	        }
                   		set(
                   			current, 
                   			"control",
                    		lv_control_2_0, 
                    		"ForControl");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,25,FOLLOW_25_in_ruleForStatement12693); 

                	newLeafNode(otherlv_3, grammarAccess.getForStatementAccess().getRightParenthesisKeyword_3());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5822:1: ( (lv_block_4_0= ruleBlock ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5823:1: (lv_block_4_0= ruleBlock )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5823:1: (lv_block_4_0= ruleBlock )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5824:3: lv_block_4_0= ruleBlock
            {
             
            	        newCompositeNode(grammarAccess.getForStatementAccess().getBlockBlockParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleBlock_in_ruleForStatement12714);
            lv_block_4_0=ruleBlock();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getForStatementRule());
            	        }
                   		set(
                   			current, 
                   			"block",
                    		lv_block_4_0, 
                    		"Block");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForStatement"


    // $ANTLR start "entryRuleForControl"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5848:1: entryRuleForControl returns [EObject current=null] : iv_ruleForControl= ruleForControl EOF ;
    public final EObject entryRuleForControl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForControl = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5849:2: (iv_ruleForControl= ruleForControl EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5850:2: iv_ruleForControl= ruleForControl EOF
            {
             newCompositeNode(grammarAccess.getForControlRule()); 
            pushFollow(FOLLOW_ruleForControl_in_entryRuleForControl12750);
            iv_ruleForControl=ruleForControl();

            state._fsp--;

             current =iv_ruleForControl; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleForControl12760); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForControl"


    // $ANTLR start "ruleForControl"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5857:1: ruleForControl returns [EObject current=null] : ( ( (lv_loopVariableDefinition_0_0= ruleLoopVariableDefinition ) ) (otherlv_1= ',' ( (lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition ) ) )* ) ;
    public final EObject ruleForControl() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_loopVariableDefinition_0_0 = null;

        EObject lv_loopVariableDefinition_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5860:28: ( ( ( (lv_loopVariableDefinition_0_0= ruleLoopVariableDefinition ) ) (otherlv_1= ',' ( (lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5861:1: ( ( (lv_loopVariableDefinition_0_0= ruleLoopVariableDefinition ) ) (otherlv_1= ',' ( (lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5861:1: ( ( (lv_loopVariableDefinition_0_0= ruleLoopVariableDefinition ) ) (otherlv_1= ',' ( (lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5861:2: ( (lv_loopVariableDefinition_0_0= ruleLoopVariableDefinition ) ) (otherlv_1= ',' ( (lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5861:2: ( (lv_loopVariableDefinition_0_0= ruleLoopVariableDefinition ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5862:1: (lv_loopVariableDefinition_0_0= ruleLoopVariableDefinition )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5862:1: (lv_loopVariableDefinition_0_0= ruleLoopVariableDefinition )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5863:3: lv_loopVariableDefinition_0_0= ruleLoopVariableDefinition
            {
             
            	        newCompositeNode(grammarAccess.getForControlAccess().getLoopVariableDefinitionLoopVariableDefinitionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleLoopVariableDefinition_in_ruleForControl12806);
            lv_loopVariableDefinition_0_0=ruleLoopVariableDefinition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getForControlRule());
            	        }
                   		add(
                   			current, 
                   			"loopVariableDefinition",
                    		lv_loopVariableDefinition_0_0, 
                    		"LoopVariableDefinition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5879:2: (otherlv_1= ',' ( (lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition ) ) )*
            loop82:
            do {
                int alt82=2;
                int LA82_0 = input.LA(1);

                if ( (LA82_0==21) ) {
                    alt82=1;
                }


                switch (alt82) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5879:4: otherlv_1= ',' ( (lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition ) )
            	    {
            	    otherlv_1=(Token)match(input,21,FOLLOW_21_in_ruleForControl12819); 

            	        	newLeafNode(otherlv_1, grammarAccess.getForControlAccess().getCommaKeyword_1_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5883:1: ( (lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5884:1: (lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5884:1: (lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5885:3: lv_loopVariableDefinition_2_0= ruleLoopVariableDefinition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getForControlAccess().getLoopVariableDefinitionLoopVariableDefinitionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleLoopVariableDefinition_in_ruleForControl12840);
            	    lv_loopVariableDefinition_2_0=ruleLoopVariableDefinition();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getForControlRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"loopVariableDefinition",
            	            		lv_loopVariableDefinition_2_0, 
            	            		"LoopVariableDefinition");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop82;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForControl"


    // $ANTLR start "entryRuleLoopVariableDefinition"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5909:1: entryRuleLoopVariableDefinition returns [EObject current=null] : iv_ruleLoopVariableDefinition= ruleLoopVariableDefinition EOF ;
    public final EObject entryRuleLoopVariableDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoopVariableDefinition = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5910:2: (iv_ruleLoopVariableDefinition= ruleLoopVariableDefinition EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5911:2: iv_ruleLoopVariableDefinition= ruleLoopVariableDefinition EOF
            {
             newCompositeNode(grammarAccess.getLoopVariableDefinitionRule()); 
            pushFollow(FOLLOW_ruleLoopVariableDefinition_in_entryRuleLoopVariableDefinition12878);
            iv_ruleLoopVariableDefinition=ruleLoopVariableDefinition();

            state._fsp--;

             current =iv_ruleLoopVariableDefinition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLoopVariableDefinition12888); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLoopVariableDefinition"


    // $ANTLR start "ruleLoopVariableDefinition"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5918:1: ruleLoopVariableDefinition returns [EObject current=null] : ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'in' ( (lv_expression1_2_0= ruleExpression ) ) (otherlv_3= '..' ( (lv_expression2_4_0= ruleExpression ) ) )? ) | ( ( (lv_type_5_0= ruleQualifiedNameWithBinding ) ) ( (lv_name_6_0= RULE_ID ) ) otherlv_7= ':' ( (lv_expression_8_0= ruleExpression ) ) ) ) ;
    public final EObject ruleLoopVariableDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_name_6_0=null;
        Token otherlv_7=null;
        EObject lv_expression1_2_0 = null;

        EObject lv_expression2_4_0 = null;

        EObject lv_type_5_0 = null;

        EObject lv_expression_8_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5921:28: ( ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'in' ( (lv_expression1_2_0= ruleExpression ) ) (otherlv_3= '..' ( (lv_expression2_4_0= ruleExpression ) ) )? ) | ( ( (lv_type_5_0= ruleQualifiedNameWithBinding ) ) ( (lv_name_6_0= RULE_ID ) ) otherlv_7= ':' ( (lv_expression_8_0= ruleExpression ) ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5922:1: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'in' ( (lv_expression1_2_0= ruleExpression ) ) (otherlv_3= '..' ( (lv_expression2_4_0= ruleExpression ) ) )? ) | ( ( (lv_type_5_0= ruleQualifiedNameWithBinding ) ) ( (lv_name_6_0= RULE_ID ) ) otherlv_7= ':' ( (lv_expression_8_0= ruleExpression ) ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5922:1: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'in' ( (lv_expression1_2_0= ruleExpression ) ) (otherlv_3= '..' ( (lv_expression2_4_0= ruleExpression ) ) )? ) | ( ( (lv_type_5_0= ruleQualifiedNameWithBinding ) ) ( (lv_name_6_0= RULE_ID ) ) otherlv_7= ':' ( (lv_expression_8_0= ruleExpression ) ) ) )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==RULE_ID) ) {
                int LA84_1 = input.LA(2);

                if ( (LA84_1==80) ) {
                    alt84=1;
                }
                else if ( (LA84_1==RULE_ID||(LA84_1>=19 && LA84_1<=20)) ) {
                    alt84=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5922:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'in' ( (lv_expression1_2_0= ruleExpression ) ) (otherlv_3= '..' ( (lv_expression2_4_0= ruleExpression ) ) )? )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5922:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'in' ( (lv_expression1_2_0= ruleExpression ) ) (otherlv_3= '..' ( (lv_expression2_4_0= ruleExpression ) ) )? )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5922:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'in' ( (lv_expression1_2_0= ruleExpression ) ) (otherlv_3= '..' ( (lv_expression2_4_0= ruleExpression ) ) )?
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5922:3: ( (lv_name_0_0= RULE_ID ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5923:1: (lv_name_0_0= RULE_ID )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5923:1: (lv_name_0_0= RULE_ID )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5924:3: lv_name_0_0= RULE_ID
                    {
                    lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLoopVariableDefinition12931); 

                    			newLeafNode(lv_name_0_0, grammarAccess.getLoopVariableDefinitionAccess().getNameIDTerminalRuleCall_0_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLoopVariableDefinitionRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_0_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_1=(Token)match(input,80,FOLLOW_80_in_ruleLoopVariableDefinition12948); 

                        	newLeafNode(otherlv_1, grammarAccess.getLoopVariableDefinitionAccess().getInKeyword_0_1());
                        
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5944:1: ( (lv_expression1_2_0= ruleExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5945:1: (lv_expression1_2_0= ruleExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5945:1: (lv_expression1_2_0= ruleExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5946:3: lv_expression1_2_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getLoopVariableDefinitionAccess().getExpression1ExpressionParserRuleCall_0_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleLoopVariableDefinition12969);
                    lv_expression1_2_0=ruleExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getLoopVariableDefinitionRule());
                    	        }
                           		set(
                           			current, 
                           			"expression1",
                            		lv_expression1_2_0, 
                            		"Expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5962:2: (otherlv_3= '..' ( (lv_expression2_4_0= ruleExpression ) ) )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==61) ) {
                        alt83=1;
                    }
                    switch (alt83) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5962:4: otherlv_3= '..' ( (lv_expression2_4_0= ruleExpression ) )
                            {
                            otherlv_3=(Token)match(input,61,FOLLOW_61_in_ruleLoopVariableDefinition12982); 

                                	newLeafNode(otherlv_3, grammarAccess.getLoopVariableDefinitionAccess().getFullStopFullStopKeyword_0_3_0());
                                
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5966:1: ( (lv_expression2_4_0= ruleExpression ) )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5967:1: (lv_expression2_4_0= ruleExpression )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5967:1: (lv_expression2_4_0= ruleExpression )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5968:3: lv_expression2_4_0= ruleExpression
                            {
                             
                            	        newCompositeNode(grammarAccess.getLoopVariableDefinitionAccess().getExpression2ExpressionParserRuleCall_0_3_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleExpression_in_ruleLoopVariableDefinition13003);
                            lv_expression2_4_0=ruleExpression();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getLoopVariableDefinitionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"expression2",
                                    		lv_expression2_4_0, 
                                    		"Expression");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5985:6: ( ( (lv_type_5_0= ruleQualifiedNameWithBinding ) ) ( (lv_name_6_0= RULE_ID ) ) otherlv_7= ':' ( (lv_expression_8_0= ruleExpression ) ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5985:6: ( ( (lv_type_5_0= ruleQualifiedNameWithBinding ) ) ( (lv_name_6_0= RULE_ID ) ) otherlv_7= ':' ( (lv_expression_8_0= ruleExpression ) ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5985:7: ( (lv_type_5_0= ruleQualifiedNameWithBinding ) ) ( (lv_name_6_0= RULE_ID ) ) otherlv_7= ':' ( (lv_expression_8_0= ruleExpression ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5985:7: ( (lv_type_5_0= ruleQualifiedNameWithBinding ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5986:1: (lv_type_5_0= ruleQualifiedNameWithBinding )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5986:1: (lv_type_5_0= ruleQualifiedNameWithBinding )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:5987:3: lv_type_5_0= ruleQualifiedNameWithBinding
                    {
                     
                    	        newCompositeNode(grammarAccess.getLoopVariableDefinitionAccess().getTypeQualifiedNameWithBindingParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_ruleLoopVariableDefinition13034);
                    lv_type_5_0=ruleQualifiedNameWithBinding();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getLoopVariableDefinitionRule());
                    	        }
                           		set(
                           			current, 
                           			"type",
                            		lv_type_5_0, 
                            		"QualifiedNameWithBinding");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6003:2: ( (lv_name_6_0= RULE_ID ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6004:1: (lv_name_6_0= RULE_ID )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6004:1: (lv_name_6_0= RULE_ID )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6005:3: lv_name_6_0= RULE_ID
                    {
                    lv_name_6_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLoopVariableDefinition13051); 

                    			newLeafNode(lv_name_6_0, grammarAccess.getLoopVariableDefinitionAccess().getNameIDTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLoopVariableDefinitionRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_6_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_7=(Token)match(input,27,FOLLOW_27_in_ruleLoopVariableDefinition13068); 

                        	newLeafNode(otherlv_7, grammarAccess.getLoopVariableDefinitionAccess().getColonKeyword_1_2());
                        
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6025:1: ( (lv_expression_8_0= ruleExpression ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6026:1: (lv_expression_8_0= ruleExpression )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6026:1: (lv_expression_8_0= ruleExpression )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6027:3: lv_expression_8_0= ruleExpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getLoopVariableDefinitionAccess().getExpressionExpressionParserRuleCall_1_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression_in_ruleLoopVariableDefinition13089);
                    lv_expression_8_0=ruleExpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getLoopVariableDefinitionRule());
                    	        }
                           		set(
                           			current, 
                           			"expression",
                            		lv_expression_8_0, 
                            		"Expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLoopVariableDefinition"


    // $ANTLR start "entryRuleBreakStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6051:1: entryRuleBreakStatement returns [EObject current=null] : iv_ruleBreakStatement= ruleBreakStatement EOF ;
    public final EObject entryRuleBreakStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBreakStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6052:2: (iv_ruleBreakStatement= ruleBreakStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6053:2: iv_ruleBreakStatement= ruleBreakStatement EOF
            {
             newCompositeNode(grammarAccess.getBreakStatementRule()); 
            pushFollow(FOLLOW_ruleBreakStatement_in_entryRuleBreakStatement13126);
            iv_ruleBreakStatement=ruleBreakStatement();

            state._fsp--;

             current =iv_ruleBreakStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBreakStatement13136); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBreakStatement"


    // $ANTLR start "ruleBreakStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6060:1: ruleBreakStatement returns [EObject current=null] : ( () otherlv_1= 'break' otherlv_2= ';' ) ;
    public final EObject ruleBreakStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6063:28: ( ( () otherlv_1= 'break' otherlv_2= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6064:1: ( () otherlv_1= 'break' otherlv_2= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6064:1: ( () otherlv_1= 'break' otherlv_2= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6064:2: () otherlv_1= 'break' otherlv_2= ';'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6064:2: ()
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6065:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getBreakStatementAccess().getBreakStatementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,81,FOLLOW_81_in_ruleBreakStatement13182); 

                	newLeafNode(otherlv_1, grammarAccess.getBreakStatementAccess().getBreakKeyword_1());
                
            otherlv_2=(Token)match(input,68,FOLLOW_68_in_ruleBreakStatement13194); 

                	newLeafNode(otherlv_2, grammarAccess.getBreakStatementAccess().getSemicolonKeyword_2());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBreakStatement"


    // $ANTLR start "entryRuleReturnStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6086:1: entryRuleReturnStatement returns [EObject current=null] : iv_ruleReturnStatement= ruleReturnStatement EOF ;
    public final EObject entryRuleReturnStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReturnStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6087:2: (iv_ruleReturnStatement= ruleReturnStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6088:2: iv_ruleReturnStatement= ruleReturnStatement EOF
            {
             newCompositeNode(grammarAccess.getReturnStatementRule()); 
            pushFollow(FOLLOW_ruleReturnStatement_in_entryRuleReturnStatement13230);
            iv_ruleReturnStatement=ruleReturnStatement();

            state._fsp--;

             current =iv_ruleReturnStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleReturnStatement13240); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReturnStatement"


    // $ANTLR start "ruleReturnStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6095:1: ruleReturnStatement returns [EObject current=null] : (otherlv_0= 'return' ( (lv_expression_1_0= ruleExpression ) ) otherlv_2= ';' ) ;
    public final EObject ruleReturnStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_expression_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6098:28: ( (otherlv_0= 'return' ( (lv_expression_1_0= ruleExpression ) ) otherlv_2= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6099:1: (otherlv_0= 'return' ( (lv_expression_1_0= ruleExpression ) ) otherlv_2= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6099:1: (otherlv_0= 'return' ( (lv_expression_1_0= ruleExpression ) ) otherlv_2= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6099:3: otherlv_0= 'return' ( (lv_expression_1_0= ruleExpression ) ) otherlv_2= ';'
            {
            otherlv_0=(Token)match(input,82,FOLLOW_82_in_ruleReturnStatement13277); 

                	newLeafNode(otherlv_0, grammarAccess.getReturnStatementAccess().getReturnKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6103:1: ( (lv_expression_1_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6104:1: (lv_expression_1_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6104:1: (lv_expression_1_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6105:3: lv_expression_1_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getReturnStatementAccess().getExpressionExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleReturnStatement13298);
            lv_expression_1_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getReturnStatementRule());
            	        }
                   		set(
                   			current, 
                   			"expression",
                    		lv_expression_1_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,68,FOLLOW_68_in_ruleReturnStatement13310); 

                	newLeafNode(otherlv_2, grammarAccess.getReturnStatementAccess().getSemicolonKeyword_2());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReturnStatement"


    // $ANTLR start "entryRuleAcceptStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6133:1: entryRuleAcceptStatement returns [EObject current=null] : iv_ruleAcceptStatement= ruleAcceptStatement EOF ;
    public final EObject entryRuleAcceptStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAcceptStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6134:2: (iv_ruleAcceptStatement= ruleAcceptStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6135:2: iv_ruleAcceptStatement= ruleAcceptStatement EOF
            {
             newCompositeNode(grammarAccess.getAcceptStatementRule()); 
            pushFollow(FOLLOW_ruleAcceptStatement_in_entryRuleAcceptStatement13346);
            iv_ruleAcceptStatement=ruleAcceptStatement();

            state._fsp--;

             current =iv_ruleAcceptStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAcceptStatement13356); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAcceptStatement"


    // $ANTLR start "ruleAcceptStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6142:1: ruleAcceptStatement returns [EObject current=null] : ( ( (lv_clause_0_0= ruleAcceptClause ) ) ( ( (lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion ) ) | ( (lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion ) ) ) ) ;
    public final EObject ruleAcceptStatement() throws RecognitionException {
        EObject current = null;

        EObject lv_clause_0_0 = null;

        EObject lv_simpleAccept_1_0 = null;

        EObject lv_compoundAccept_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6145:28: ( ( ( (lv_clause_0_0= ruleAcceptClause ) ) ( ( (lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion ) ) | ( (lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion ) ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6146:1: ( ( (lv_clause_0_0= ruleAcceptClause ) ) ( ( (lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion ) ) | ( (lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion ) ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6146:1: ( ( (lv_clause_0_0= ruleAcceptClause ) ) ( ( (lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion ) ) | ( (lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6146:2: ( (lv_clause_0_0= ruleAcceptClause ) ) ( ( (lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion ) ) | ( (lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6146:2: ( (lv_clause_0_0= ruleAcceptClause ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6147:1: (lv_clause_0_0= ruleAcceptClause )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6147:1: (lv_clause_0_0= ruleAcceptClause )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6148:3: lv_clause_0_0= ruleAcceptClause
            {
             
            	        newCompositeNode(grammarAccess.getAcceptStatementAccess().getClauseAcceptClauseParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleAcceptClause_in_ruleAcceptStatement13402);
            lv_clause_0_0=ruleAcceptClause();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAcceptStatementRule());
            	        }
                   		set(
                   			current, 
                   			"clause",
                    		lv_clause_0_0, 
                    		"AcceptClause");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6164:2: ( ( (lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion ) ) | ( (lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion ) ) )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==68) ) {
                alt85=1;
            }
            else if ( (LA85_0==60) ) {
                alt85=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }
            switch (alt85) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6164:3: ( (lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6164:3: ( (lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6165:1: (lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6165:1: (lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6166:3: lv_simpleAccept_1_0= ruleSimpleAcceptStatementCompletion
                    {
                     
                    	        newCompositeNode(grammarAccess.getAcceptStatementAccess().getSimpleAcceptSimpleAcceptStatementCompletionParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSimpleAcceptStatementCompletion_in_ruleAcceptStatement13424);
                    lv_simpleAccept_1_0=ruleSimpleAcceptStatementCompletion();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAcceptStatementRule());
                    	        }
                           		set(
                           			current, 
                           			"simpleAccept",
                            		lv_simpleAccept_1_0, 
                            		"SimpleAcceptStatementCompletion");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6183:6: ( (lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6183:6: ( (lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6184:1: (lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6184:1: (lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6185:3: lv_compoundAccept_2_0= ruleCompoundAcceptStatementCompletion
                    {
                     
                    	        newCompositeNode(grammarAccess.getAcceptStatementAccess().getCompoundAcceptCompoundAcceptStatementCompletionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleCompoundAcceptStatementCompletion_in_ruleAcceptStatement13451);
                    lv_compoundAccept_2_0=ruleCompoundAcceptStatementCompletion();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getAcceptStatementRule());
                    	        }
                           		set(
                           			current, 
                           			"compoundAccept",
                            		lv_compoundAccept_2_0, 
                            		"CompoundAcceptStatementCompletion");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAcceptStatement"


    // $ANTLR start "entryRuleSimpleAcceptStatementCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6209:1: entryRuleSimpleAcceptStatementCompletion returns [EObject current=null] : iv_ruleSimpleAcceptStatementCompletion= ruleSimpleAcceptStatementCompletion EOF ;
    public final EObject entryRuleSimpleAcceptStatementCompletion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleAcceptStatementCompletion = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6210:2: (iv_ruleSimpleAcceptStatementCompletion= ruleSimpleAcceptStatementCompletion EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6211:2: iv_ruleSimpleAcceptStatementCompletion= ruleSimpleAcceptStatementCompletion EOF
            {
             newCompositeNode(grammarAccess.getSimpleAcceptStatementCompletionRule()); 
            pushFollow(FOLLOW_ruleSimpleAcceptStatementCompletion_in_entryRuleSimpleAcceptStatementCompletion13488);
            iv_ruleSimpleAcceptStatementCompletion=ruleSimpleAcceptStatementCompletion();

            state._fsp--;

             current =iv_ruleSimpleAcceptStatementCompletion; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleAcceptStatementCompletion13498); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleAcceptStatementCompletion"


    // $ANTLR start "ruleSimpleAcceptStatementCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6218:1: ruleSimpleAcceptStatementCompletion returns [EObject current=null] : ( () otherlv_1= ';' ) ;
    public final EObject ruleSimpleAcceptStatementCompletion() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6221:28: ( ( () otherlv_1= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6222:1: ( () otherlv_1= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6222:1: ( () otherlv_1= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6222:2: () otherlv_1= ';'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6222:2: ()
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6223:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSimpleAcceptStatementCompletionAccess().getSimpleAcceptStatementCompletionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,68,FOLLOW_68_in_ruleSimpleAcceptStatementCompletion13544); 

                	newLeafNode(otherlv_1, grammarAccess.getSimpleAcceptStatementCompletionAccess().getSemicolonKeyword_1());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleAcceptStatementCompletion"


    // $ANTLR start "entryRuleCompoundAcceptStatementCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6240:1: entryRuleCompoundAcceptStatementCompletion returns [EObject current=null] : iv_ruleCompoundAcceptStatementCompletion= ruleCompoundAcceptStatementCompletion EOF ;
    public final EObject entryRuleCompoundAcceptStatementCompletion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompoundAcceptStatementCompletion = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6241:2: (iv_ruleCompoundAcceptStatementCompletion= ruleCompoundAcceptStatementCompletion EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6242:2: iv_ruleCompoundAcceptStatementCompletion= ruleCompoundAcceptStatementCompletion EOF
            {
             newCompositeNode(grammarAccess.getCompoundAcceptStatementCompletionRule()); 
            pushFollow(FOLLOW_ruleCompoundAcceptStatementCompletion_in_entryRuleCompoundAcceptStatementCompletion13580);
            iv_ruleCompoundAcceptStatementCompletion=ruleCompoundAcceptStatementCompletion();

            state._fsp--;

             current =iv_ruleCompoundAcceptStatementCompletion; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCompoundAcceptStatementCompletion13590); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCompoundAcceptStatementCompletion"


    // $ANTLR start "ruleCompoundAcceptStatementCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6249:1: ruleCompoundAcceptStatementCompletion returns [EObject current=null] : ( ( (lv_block_0_0= ruleBlock ) ) (otherlv_1= 'or' ( (lv_acceptBlock_2_0= ruleAcceptBlock ) ) )* ) ;
    public final EObject ruleCompoundAcceptStatementCompletion() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_block_0_0 = null;

        EObject lv_acceptBlock_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6252:28: ( ( ( (lv_block_0_0= ruleBlock ) ) (otherlv_1= 'or' ( (lv_acceptBlock_2_0= ruleAcceptBlock ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6253:1: ( ( (lv_block_0_0= ruleBlock ) ) (otherlv_1= 'or' ( (lv_acceptBlock_2_0= ruleAcceptBlock ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6253:1: ( ( (lv_block_0_0= ruleBlock ) ) (otherlv_1= 'or' ( (lv_acceptBlock_2_0= ruleAcceptBlock ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6253:2: ( (lv_block_0_0= ruleBlock ) ) (otherlv_1= 'or' ( (lv_acceptBlock_2_0= ruleAcceptBlock ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6253:2: ( (lv_block_0_0= ruleBlock ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6254:1: (lv_block_0_0= ruleBlock )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6254:1: (lv_block_0_0= ruleBlock )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6255:3: lv_block_0_0= ruleBlock
            {
             
            	        newCompositeNode(grammarAccess.getCompoundAcceptStatementCompletionAccess().getBlockBlockParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleBlock_in_ruleCompoundAcceptStatementCompletion13636);
            lv_block_0_0=ruleBlock();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCompoundAcceptStatementCompletionRule());
            	        }
                   		set(
                   			current, 
                   			"block",
                    		lv_block_0_0, 
                    		"Block");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6271:2: (otherlv_1= 'or' ( (lv_acceptBlock_2_0= ruleAcceptBlock ) ) )*
            loop86:
            do {
                int alt86=2;
                int LA86_0 = input.LA(1);

                if ( (LA86_0==73) ) {
                    alt86=1;
                }


                switch (alt86) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6271:4: otherlv_1= 'or' ( (lv_acceptBlock_2_0= ruleAcceptBlock ) )
            	    {
            	    otherlv_1=(Token)match(input,73,FOLLOW_73_in_ruleCompoundAcceptStatementCompletion13649); 

            	        	newLeafNode(otherlv_1, grammarAccess.getCompoundAcceptStatementCompletionAccess().getOrKeyword_1_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6275:1: ( (lv_acceptBlock_2_0= ruleAcceptBlock ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6276:1: (lv_acceptBlock_2_0= ruleAcceptBlock )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6276:1: (lv_acceptBlock_2_0= ruleAcceptBlock )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6277:3: lv_acceptBlock_2_0= ruleAcceptBlock
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getCompoundAcceptStatementCompletionAccess().getAcceptBlockAcceptBlockParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAcceptBlock_in_ruleCompoundAcceptStatementCompletion13670);
            	    lv_acceptBlock_2_0=ruleAcceptBlock();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getCompoundAcceptStatementCompletionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"acceptBlock",
            	            		lv_acceptBlock_2_0, 
            	            		"AcceptBlock");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop86;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCompoundAcceptStatementCompletion"


    // $ANTLR start "entryRuleAcceptBlock"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6301:1: entryRuleAcceptBlock returns [EObject current=null] : iv_ruleAcceptBlock= ruleAcceptBlock EOF ;
    public final EObject entryRuleAcceptBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAcceptBlock = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6302:2: (iv_ruleAcceptBlock= ruleAcceptBlock EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6303:2: iv_ruleAcceptBlock= ruleAcceptBlock EOF
            {
             newCompositeNode(grammarAccess.getAcceptBlockRule()); 
            pushFollow(FOLLOW_ruleAcceptBlock_in_entryRuleAcceptBlock13708);
            iv_ruleAcceptBlock=ruleAcceptBlock();

            state._fsp--;

             current =iv_ruleAcceptBlock; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAcceptBlock13718); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAcceptBlock"


    // $ANTLR start "ruleAcceptBlock"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6310:1: ruleAcceptBlock returns [EObject current=null] : ( ( (lv_clause_0_0= ruleAcceptClause ) ) ( (lv_block_1_0= ruleBlock ) ) ) ;
    public final EObject ruleAcceptBlock() throws RecognitionException {
        EObject current = null;

        EObject lv_clause_0_0 = null;

        EObject lv_block_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6313:28: ( ( ( (lv_clause_0_0= ruleAcceptClause ) ) ( (lv_block_1_0= ruleBlock ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6314:1: ( ( (lv_clause_0_0= ruleAcceptClause ) ) ( (lv_block_1_0= ruleBlock ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6314:1: ( ( (lv_clause_0_0= ruleAcceptClause ) ) ( (lv_block_1_0= ruleBlock ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6314:2: ( (lv_clause_0_0= ruleAcceptClause ) ) ( (lv_block_1_0= ruleBlock ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6314:2: ( (lv_clause_0_0= ruleAcceptClause ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6315:1: (lv_clause_0_0= ruleAcceptClause )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6315:1: (lv_clause_0_0= ruleAcceptClause )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6316:3: lv_clause_0_0= ruleAcceptClause
            {
             
            	        newCompositeNode(grammarAccess.getAcceptBlockAccess().getClauseAcceptClauseParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleAcceptClause_in_ruleAcceptBlock13764);
            lv_clause_0_0=ruleAcceptClause();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAcceptBlockRule());
            	        }
                   		set(
                   			current, 
                   			"clause",
                    		lv_clause_0_0, 
                    		"AcceptClause");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6332:2: ( (lv_block_1_0= ruleBlock ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6333:1: (lv_block_1_0= ruleBlock )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6333:1: (lv_block_1_0= ruleBlock )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6334:3: lv_block_1_0= ruleBlock
            {
             
            	        newCompositeNode(grammarAccess.getAcceptBlockAccess().getBlockBlockParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleBlock_in_ruleAcceptBlock13785);
            lv_block_1_0=ruleBlock();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAcceptBlockRule());
            	        }
                   		set(
                   			current, 
                   			"block",
                    		lv_block_1_0, 
                    		"Block");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAcceptBlock"


    // $ANTLR start "entryRuleAcceptClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6358:1: entryRuleAcceptClause returns [EObject current=null] : iv_ruleAcceptClause= ruleAcceptClause EOF ;
    public final EObject entryRuleAcceptClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAcceptClause = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6359:2: (iv_ruleAcceptClause= ruleAcceptClause EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6360:2: iv_ruleAcceptClause= ruleAcceptClause EOF
            {
             newCompositeNode(grammarAccess.getAcceptClauseRule()); 
            pushFollow(FOLLOW_ruleAcceptClause_in_entryRuleAcceptClause13821);
            iv_ruleAcceptClause=ruleAcceptClause();

            state._fsp--;

             current =iv_ruleAcceptClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAcceptClause13831); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAcceptClause"


    // $ANTLR start "ruleAcceptClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6367:1: ruleAcceptClause returns [EObject current=null] : (otherlv_0= 'accept' otherlv_1= '(' ( ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' )? ( (lv_qualifiedNameList_4_0= ruleQualifiedNameList ) ) otherlv_5= ')' ) ;
    public final EObject ruleAcceptClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_qualifiedNameList_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6370:28: ( (otherlv_0= 'accept' otherlv_1= '(' ( ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' )? ( (lv_qualifiedNameList_4_0= ruleQualifiedNameList ) ) otherlv_5= ')' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6371:1: (otherlv_0= 'accept' otherlv_1= '(' ( ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' )? ( (lv_qualifiedNameList_4_0= ruleQualifiedNameList ) ) otherlv_5= ')' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6371:1: (otherlv_0= 'accept' otherlv_1= '(' ( ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' )? ( (lv_qualifiedNameList_4_0= ruleQualifiedNameList ) ) otherlv_5= ')' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6371:3: otherlv_0= 'accept' otherlv_1= '(' ( ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' )? ( (lv_qualifiedNameList_4_0= ruleQualifiedNameList ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,83,FOLLOW_83_in_ruleAcceptClause13868); 

                	newLeafNode(otherlv_0, grammarAccess.getAcceptClauseAccess().getAcceptKeyword_0());
                
            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleAcceptClause13880); 

                	newLeafNode(otherlv_1, grammarAccess.getAcceptClauseAccess().getLeftParenthesisKeyword_1());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6379:1: ( ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==RULE_ID) ) {
                int LA87_1 = input.LA(2);

                if ( (LA87_1==27) ) {
                    alt87=1;
                }
            }
            switch (alt87) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6379:2: ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':'
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6379:2: ( (lv_name_2_0= RULE_ID ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6380:1: (lv_name_2_0= RULE_ID )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6380:1: (lv_name_2_0= RULE_ID )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6381:3: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAcceptClause13898); 

                    			newLeafNode(lv_name_2_0, grammarAccess.getAcceptClauseAccess().getNameIDTerminalRuleCall_2_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAcceptClauseRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_2_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_3=(Token)match(input,27,FOLLOW_27_in_ruleAcceptClause13915); 

                        	newLeafNode(otherlv_3, grammarAccess.getAcceptClauseAccess().getColonKeyword_2_1());
                        

                    }
                    break;

            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6401:3: ( (lv_qualifiedNameList_4_0= ruleQualifiedNameList ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6402:1: (lv_qualifiedNameList_4_0= ruleQualifiedNameList )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6402:1: (lv_qualifiedNameList_4_0= ruleQualifiedNameList )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6403:3: lv_qualifiedNameList_4_0= ruleQualifiedNameList
            {
             
            	        newCompositeNode(grammarAccess.getAcceptClauseAccess().getQualifiedNameListQualifiedNameListParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedNameList_in_ruleAcceptClause13938);
            lv_qualifiedNameList_4_0=ruleQualifiedNameList();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAcceptClauseRule());
            	        }
                   		set(
                   			current, 
                   			"qualifiedNameList",
                    		lv_qualifiedNameList_4_0, 
                    		"QualifiedNameList");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleAcceptClause13950); 

                	newLeafNode(otherlv_5, grammarAccess.getAcceptClauseAccess().getRightParenthesisKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAcceptClause"


    // $ANTLR start "entryRuleClassifyStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6431:1: entryRuleClassifyStatement returns [EObject current=null] : iv_ruleClassifyStatement= ruleClassifyStatement EOF ;
    public final EObject entryRuleClassifyStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassifyStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6432:2: (iv_ruleClassifyStatement= ruleClassifyStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6433:2: iv_ruleClassifyStatement= ruleClassifyStatement EOF
            {
             newCompositeNode(grammarAccess.getClassifyStatementRule()); 
            pushFollow(FOLLOW_ruleClassifyStatement_in_entryRuleClassifyStatement13986);
            iv_ruleClassifyStatement=ruleClassifyStatement();

            state._fsp--;

             current =iv_ruleClassifyStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClassifyStatement13996); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClassifyStatement"


    // $ANTLR start "ruleClassifyStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6440:1: ruleClassifyStatement returns [EObject current=null] : (otherlv_0= 'classify' ( (lv_expression_1_0= ruleExpression ) ) ( (lv_clause_2_0= ruleClassificationClause ) ) otherlv_3= ';' ) ;
    public final EObject ruleClassifyStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_expression_1_0 = null;

        EObject lv_clause_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6443:28: ( (otherlv_0= 'classify' ( (lv_expression_1_0= ruleExpression ) ) ( (lv_clause_2_0= ruleClassificationClause ) ) otherlv_3= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6444:1: (otherlv_0= 'classify' ( (lv_expression_1_0= ruleExpression ) ) ( (lv_clause_2_0= ruleClassificationClause ) ) otherlv_3= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6444:1: (otherlv_0= 'classify' ( (lv_expression_1_0= ruleExpression ) ) ( (lv_clause_2_0= ruleClassificationClause ) ) otherlv_3= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6444:3: otherlv_0= 'classify' ( (lv_expression_1_0= ruleExpression ) ) ( (lv_clause_2_0= ruleClassificationClause ) ) otherlv_3= ';'
            {
            otherlv_0=(Token)match(input,84,FOLLOW_84_in_ruleClassifyStatement14033); 

                	newLeafNode(otherlv_0, grammarAccess.getClassifyStatementAccess().getClassifyKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6448:1: ( (lv_expression_1_0= ruleExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6449:1: (lv_expression_1_0= ruleExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6449:1: (lv_expression_1_0= ruleExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6450:3: lv_expression_1_0= ruleExpression
            {
             
            	        newCompositeNode(grammarAccess.getClassifyStatementAccess().getExpressionExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_in_ruleClassifyStatement14054);
            lv_expression_1_0=ruleExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getClassifyStatementRule());
            	        }
                   		set(
                   			current, 
                   			"expression",
                    		lv_expression_1_0, 
                    		"Expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6466:2: ( (lv_clause_2_0= ruleClassificationClause ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6467:1: (lv_clause_2_0= ruleClassificationClause )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6467:1: (lv_clause_2_0= ruleClassificationClause )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6468:3: lv_clause_2_0= ruleClassificationClause
            {
             
            	        newCompositeNode(grammarAccess.getClassifyStatementAccess().getClauseClassificationClauseParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleClassificationClause_in_ruleClassifyStatement14075);
            lv_clause_2_0=ruleClassificationClause();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getClassifyStatementRule());
            	        }
                   		set(
                   			current, 
                   			"clause",
                    		lv_clause_2_0, 
                    		"ClassificationClause");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,68,FOLLOW_68_in_ruleClassifyStatement14087); 

                	newLeafNode(otherlv_3, grammarAccess.getClassifyStatementAccess().getSemicolonKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClassifyStatement"


    // $ANTLR start "entryRuleClassificationClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6496:1: entryRuleClassificationClause returns [EObject current=null] : iv_ruleClassificationClause= ruleClassificationClause EOF ;
    public final EObject entryRuleClassificationClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassificationClause = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6497:2: (iv_ruleClassificationClause= ruleClassificationClause EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6498:2: iv_ruleClassificationClause= ruleClassificationClause EOF
            {
             newCompositeNode(grammarAccess.getClassificationClauseRule()); 
            pushFollow(FOLLOW_ruleClassificationClause_in_entryRuleClassificationClause14123);
            iv_ruleClassificationClause=ruleClassificationClause();

            state._fsp--;

             current =iv_ruleClassificationClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClassificationClause14133); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClassificationClause"


    // $ANTLR start "ruleClassificationClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6505:1: ruleClassificationClause returns [EObject current=null] : ( ( ( (lv_classifyFromClause_0_0= ruleClassificationFromClause ) ) ( (lv_classifyToClause_1_0= ruleClassificationToClause ) )? ) | ( ( (lv_reclassyAllClause_2_0= ruleReclassifyAllClause ) )? ( (lv_classifyToClause_3_0= ruleClassificationToClause ) ) ) ) ;
    public final EObject ruleClassificationClause() throws RecognitionException {
        EObject current = null;

        EObject lv_classifyFromClause_0_0 = null;

        EObject lv_classifyToClause_1_0 = null;

        EObject lv_reclassyAllClause_2_0 = null;

        EObject lv_classifyToClause_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6508:28: ( ( ( ( (lv_classifyFromClause_0_0= ruleClassificationFromClause ) ) ( (lv_classifyToClause_1_0= ruleClassificationToClause ) )? ) | ( ( (lv_reclassyAllClause_2_0= ruleReclassifyAllClause ) )? ( (lv_classifyToClause_3_0= ruleClassificationToClause ) ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6509:1: ( ( ( (lv_classifyFromClause_0_0= ruleClassificationFromClause ) ) ( (lv_classifyToClause_1_0= ruleClassificationToClause ) )? ) | ( ( (lv_reclassyAllClause_2_0= ruleReclassifyAllClause ) )? ( (lv_classifyToClause_3_0= ruleClassificationToClause ) ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6509:1: ( ( ( (lv_classifyFromClause_0_0= ruleClassificationFromClause ) ) ( (lv_classifyToClause_1_0= ruleClassificationToClause ) )? ) | ( ( (lv_reclassyAllClause_2_0= ruleReclassifyAllClause ) )? ( (lv_classifyToClause_3_0= ruleClassificationToClause ) ) ) )
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==85) ) {
                int LA90_1 = input.LA(2);

                if ( (LA90_1==RULE_ID) ) {
                    alt90=1;
                }
                else if ( (LA90_1==16) ) {
                    alt90=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 90, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA90_0==86) ) {
                alt90=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 90, 0, input);

                throw nvae;
            }
            switch (alt90) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6509:2: ( ( (lv_classifyFromClause_0_0= ruleClassificationFromClause ) ) ( (lv_classifyToClause_1_0= ruleClassificationToClause ) )? )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6509:2: ( ( (lv_classifyFromClause_0_0= ruleClassificationFromClause ) ) ( (lv_classifyToClause_1_0= ruleClassificationToClause ) )? )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6509:3: ( (lv_classifyFromClause_0_0= ruleClassificationFromClause ) ) ( (lv_classifyToClause_1_0= ruleClassificationToClause ) )?
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6509:3: ( (lv_classifyFromClause_0_0= ruleClassificationFromClause ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6510:1: (lv_classifyFromClause_0_0= ruleClassificationFromClause )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6510:1: (lv_classifyFromClause_0_0= ruleClassificationFromClause )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6511:3: lv_classifyFromClause_0_0= ruleClassificationFromClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getClassificationClauseAccess().getClassifyFromClauseClassificationFromClauseParserRuleCall_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleClassificationFromClause_in_ruleClassificationClause14180);
                    lv_classifyFromClause_0_0=ruleClassificationFromClause();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getClassificationClauseRule());
                    	        }
                           		set(
                           			current, 
                           			"classifyFromClause",
                            		lv_classifyFromClause_0_0, 
                            		"ClassificationFromClause");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6527:2: ( (lv_classifyToClause_1_0= ruleClassificationToClause ) )?
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==86) ) {
                        alt88=1;
                    }
                    switch (alt88) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6528:1: (lv_classifyToClause_1_0= ruleClassificationToClause )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6528:1: (lv_classifyToClause_1_0= ruleClassificationToClause )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6529:3: lv_classifyToClause_1_0= ruleClassificationToClause
                            {
                             
                            	        newCompositeNode(grammarAccess.getClassificationClauseAccess().getClassifyToClauseClassificationToClauseParserRuleCall_0_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleClassificationToClause_in_ruleClassificationClause14201);
                            lv_classifyToClause_1_0=ruleClassificationToClause();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getClassificationClauseRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"classifyToClause",
                                    		lv_classifyToClause_1_0, 
                                    		"ClassificationToClause");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6546:6: ( ( (lv_reclassyAllClause_2_0= ruleReclassifyAllClause ) )? ( (lv_classifyToClause_3_0= ruleClassificationToClause ) ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6546:6: ( ( (lv_reclassyAllClause_2_0= ruleReclassifyAllClause ) )? ( (lv_classifyToClause_3_0= ruleClassificationToClause ) ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6546:7: ( (lv_reclassyAllClause_2_0= ruleReclassifyAllClause ) )? ( (lv_classifyToClause_3_0= ruleClassificationToClause ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6546:7: ( (lv_reclassyAllClause_2_0= ruleReclassifyAllClause ) )?
                    int alt89=2;
                    int LA89_0 = input.LA(1);

                    if ( (LA89_0==85) ) {
                        alt89=1;
                    }
                    switch (alt89) {
                        case 1 :
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6547:1: (lv_reclassyAllClause_2_0= ruleReclassifyAllClause )
                            {
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6547:1: (lv_reclassyAllClause_2_0= ruleReclassifyAllClause )
                            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6548:3: lv_reclassyAllClause_2_0= ruleReclassifyAllClause
                            {
                             
                            	        newCompositeNode(grammarAccess.getClassificationClauseAccess().getReclassyAllClauseReclassifyAllClauseParserRuleCall_1_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleReclassifyAllClause_in_ruleClassificationClause14231);
                            lv_reclassyAllClause_2_0=ruleReclassifyAllClause();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getClassificationClauseRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"reclassyAllClause",
                                    		lv_reclassyAllClause_2_0, 
                                    		"ReclassifyAllClause");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }
                            break;

                    }

                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6564:3: ( (lv_classifyToClause_3_0= ruleClassificationToClause ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6565:1: (lv_classifyToClause_3_0= ruleClassificationToClause )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6565:1: (lv_classifyToClause_3_0= ruleClassificationToClause )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6566:3: lv_classifyToClause_3_0= ruleClassificationToClause
                    {
                     
                    	        newCompositeNode(grammarAccess.getClassificationClauseAccess().getClassifyToClauseClassificationToClauseParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleClassificationToClause_in_ruleClassificationClause14253);
                    lv_classifyToClause_3_0=ruleClassificationToClause();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getClassificationClauseRule());
                    	        }
                           		set(
                           			current, 
                           			"classifyToClause",
                            		lv_classifyToClause_3_0, 
                            		"ClassificationToClause");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClassificationClause"


    // $ANTLR start "entryRuleClassificationFromClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6590:1: entryRuleClassificationFromClause returns [EObject current=null] : iv_ruleClassificationFromClause= ruleClassificationFromClause EOF ;
    public final EObject entryRuleClassificationFromClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassificationFromClause = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6591:2: (iv_ruleClassificationFromClause= ruleClassificationFromClause EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6592:2: iv_ruleClassificationFromClause= ruleClassificationFromClause EOF
            {
             newCompositeNode(grammarAccess.getClassificationFromClauseRule()); 
            pushFollow(FOLLOW_ruleClassificationFromClause_in_entryRuleClassificationFromClause14290);
            iv_ruleClassificationFromClause=ruleClassificationFromClause();

            state._fsp--;

             current =iv_ruleClassificationFromClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClassificationFromClause14300); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClassificationFromClause"


    // $ANTLR start "ruleClassificationFromClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6599:1: ruleClassificationFromClause returns [EObject current=null] : (otherlv_0= 'from' ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) ) ) ;
    public final EObject ruleClassificationFromClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_qualifiedNameList_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6602:28: ( (otherlv_0= 'from' ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6603:1: (otherlv_0= 'from' ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6603:1: (otherlv_0= 'from' ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6603:3: otherlv_0= 'from' ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) )
            {
            otherlv_0=(Token)match(input,85,FOLLOW_85_in_ruleClassificationFromClause14337); 

                	newLeafNode(otherlv_0, grammarAccess.getClassificationFromClauseAccess().getFromKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6607:1: ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6608:1: (lv_qualifiedNameList_1_0= ruleQualifiedNameList )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6608:1: (lv_qualifiedNameList_1_0= ruleQualifiedNameList )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6609:3: lv_qualifiedNameList_1_0= ruleQualifiedNameList
            {
             
            	        newCompositeNode(grammarAccess.getClassificationFromClauseAccess().getQualifiedNameListQualifiedNameListParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedNameList_in_ruleClassificationFromClause14358);
            lv_qualifiedNameList_1_0=ruleQualifiedNameList();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getClassificationFromClauseRule());
            	        }
                   		set(
                   			current, 
                   			"qualifiedNameList",
                    		lv_qualifiedNameList_1_0, 
                    		"QualifiedNameList");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClassificationFromClause"


    // $ANTLR start "entryRuleClassificationToClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6633:1: entryRuleClassificationToClause returns [EObject current=null] : iv_ruleClassificationToClause= ruleClassificationToClause EOF ;
    public final EObject entryRuleClassificationToClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassificationToClause = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6634:2: (iv_ruleClassificationToClause= ruleClassificationToClause EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6635:2: iv_ruleClassificationToClause= ruleClassificationToClause EOF
            {
             newCompositeNode(grammarAccess.getClassificationToClauseRule()); 
            pushFollow(FOLLOW_ruleClassificationToClause_in_entryRuleClassificationToClause14394);
            iv_ruleClassificationToClause=ruleClassificationToClause();

            state._fsp--;

             current =iv_ruleClassificationToClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClassificationToClause14404); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClassificationToClause"


    // $ANTLR start "ruleClassificationToClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6642:1: ruleClassificationToClause returns [EObject current=null] : (otherlv_0= 'to' ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) ) ) ;
    public final EObject ruleClassificationToClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_qualifiedNameList_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6645:28: ( (otherlv_0= 'to' ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6646:1: (otherlv_0= 'to' ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6646:1: (otherlv_0= 'to' ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6646:3: otherlv_0= 'to' ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) )
            {
            otherlv_0=(Token)match(input,86,FOLLOW_86_in_ruleClassificationToClause14441); 

                	newLeafNode(otherlv_0, grammarAccess.getClassificationToClauseAccess().getToKeyword_0());
                
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6650:1: ( (lv_qualifiedNameList_1_0= ruleQualifiedNameList ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6651:1: (lv_qualifiedNameList_1_0= ruleQualifiedNameList )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6651:1: (lv_qualifiedNameList_1_0= ruleQualifiedNameList )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6652:3: lv_qualifiedNameList_1_0= ruleQualifiedNameList
            {
             
            	        newCompositeNode(grammarAccess.getClassificationToClauseAccess().getQualifiedNameListQualifiedNameListParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedNameList_in_ruleClassificationToClause14462);
            lv_qualifiedNameList_1_0=ruleQualifiedNameList();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getClassificationToClauseRule());
            	        }
                   		set(
                   			current, 
                   			"qualifiedNameList",
                    		lv_qualifiedNameList_1_0, 
                    		"QualifiedNameList");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClassificationToClause"


    // $ANTLR start "entryRuleReclassifyAllClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6676:1: entryRuleReclassifyAllClause returns [EObject current=null] : iv_ruleReclassifyAllClause= ruleReclassifyAllClause EOF ;
    public final EObject entryRuleReclassifyAllClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReclassifyAllClause = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6677:2: (iv_ruleReclassifyAllClause= ruleReclassifyAllClause EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6678:2: iv_ruleReclassifyAllClause= ruleReclassifyAllClause EOF
            {
             newCompositeNode(grammarAccess.getReclassifyAllClauseRule()); 
            pushFollow(FOLLOW_ruleReclassifyAllClause_in_entryRuleReclassifyAllClause14498);
            iv_ruleReclassifyAllClause=ruleReclassifyAllClause();

            state._fsp--;

             current =iv_ruleReclassifyAllClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleReclassifyAllClause14508); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReclassifyAllClause"


    // $ANTLR start "ruleReclassifyAllClause"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6685:1: ruleReclassifyAllClause returns [EObject current=null] : ( () otherlv_1= 'from' otherlv_2= '*' ) ;
    public final EObject ruleReclassifyAllClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6688:28: ( ( () otherlv_1= 'from' otherlv_2= '*' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6689:1: ( () otherlv_1= 'from' otherlv_2= '*' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6689:1: ( () otherlv_1= 'from' otherlv_2= '*' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6689:2: () otherlv_1= 'from' otherlv_2= '*'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6689:2: ()
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6690:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getReclassifyAllClauseAccess().getReclassifyAllClauseAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,85,FOLLOW_85_in_ruleReclassifyAllClause14554); 

                	newLeafNode(otherlv_1, grammarAccess.getReclassifyAllClauseAccess().getFromKeyword_1());
                
            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleReclassifyAllClause14566); 

                	newLeafNode(otherlv_2, grammarAccess.getReclassifyAllClauseAccess().getAsteriskKeyword_2());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReclassifyAllClause"


    // $ANTLR start "entryRuleQualifiedNameList"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6711:1: entryRuleQualifiedNameList returns [EObject current=null] : iv_ruleQualifiedNameList= ruleQualifiedNameList EOF ;
    public final EObject entryRuleQualifiedNameList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQualifiedNameList = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6712:2: (iv_ruleQualifiedNameList= ruleQualifiedNameList EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6713:2: iv_ruleQualifiedNameList= ruleQualifiedNameList EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameListRule()); 
            pushFollow(FOLLOW_ruleQualifiedNameList_in_entryRuleQualifiedNameList14602);
            iv_ruleQualifiedNameList=ruleQualifiedNameList();

            state._fsp--;

             current =iv_ruleQualifiedNameList; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedNameList14612); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedNameList"


    // $ANTLR start "ruleQualifiedNameList"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6720:1: ruleQualifiedNameList returns [EObject current=null] : ( ( (lv_qualifiedName_0_0= ruleQualifiedNameWithBinding ) ) (otherlv_1= ',' ( (lv_qualifiedName_2_0= ruleQualifiedNameWithBinding ) ) )* ) ;
    public final EObject ruleQualifiedNameList() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_qualifiedName_0_0 = null;

        EObject lv_qualifiedName_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6723:28: ( ( ( (lv_qualifiedName_0_0= ruleQualifiedNameWithBinding ) ) (otherlv_1= ',' ( (lv_qualifiedName_2_0= ruleQualifiedNameWithBinding ) ) )* ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6724:1: ( ( (lv_qualifiedName_0_0= ruleQualifiedNameWithBinding ) ) (otherlv_1= ',' ( (lv_qualifiedName_2_0= ruleQualifiedNameWithBinding ) ) )* )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6724:1: ( ( (lv_qualifiedName_0_0= ruleQualifiedNameWithBinding ) ) (otherlv_1= ',' ( (lv_qualifiedName_2_0= ruleQualifiedNameWithBinding ) ) )* )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6724:2: ( (lv_qualifiedName_0_0= ruleQualifiedNameWithBinding ) ) (otherlv_1= ',' ( (lv_qualifiedName_2_0= ruleQualifiedNameWithBinding ) ) )*
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6724:2: ( (lv_qualifiedName_0_0= ruleQualifiedNameWithBinding ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6725:1: (lv_qualifiedName_0_0= ruleQualifiedNameWithBinding )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6725:1: (lv_qualifiedName_0_0= ruleQualifiedNameWithBinding )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6726:3: lv_qualifiedName_0_0= ruleQualifiedNameWithBinding
            {
             
            	        newCompositeNode(grammarAccess.getQualifiedNameListAccess().getQualifiedNameQualifiedNameWithBindingParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_ruleQualifiedNameList14658);
            lv_qualifiedName_0_0=ruleQualifiedNameWithBinding();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getQualifiedNameListRule());
            	        }
                   		add(
                   			current, 
                   			"qualifiedName",
                    		lv_qualifiedName_0_0, 
                    		"QualifiedNameWithBinding");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6742:2: (otherlv_1= ',' ( (lv_qualifiedName_2_0= ruleQualifiedNameWithBinding ) ) )*
            loop91:
            do {
                int alt91=2;
                int LA91_0 = input.LA(1);

                if ( (LA91_0==21) ) {
                    alt91=1;
                }


                switch (alt91) {
            	case 1 :
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6742:4: otherlv_1= ',' ( (lv_qualifiedName_2_0= ruleQualifiedNameWithBinding ) )
            	    {
            	    otherlv_1=(Token)match(input,21,FOLLOW_21_in_ruleQualifiedNameList14671); 

            	        	newLeafNode(otherlv_1, grammarAccess.getQualifiedNameListAccess().getCommaKeyword_1_0());
            	        
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6746:1: ( (lv_qualifiedName_2_0= ruleQualifiedNameWithBinding ) )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6747:1: (lv_qualifiedName_2_0= ruleQualifiedNameWithBinding )
            	    {
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6747:1: (lv_qualifiedName_2_0= ruleQualifiedNameWithBinding )
            	    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6748:3: lv_qualifiedName_2_0= ruleQualifiedNameWithBinding
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getQualifiedNameListAccess().getQualifiedNameQualifiedNameWithBindingParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleQualifiedNameWithBinding_in_ruleQualifiedNameList14692);
            	    lv_qualifiedName_2_0=ruleQualifiedNameWithBinding();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getQualifiedNameListRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"qualifiedName",
            	            		lv_qualifiedName_2_0, 
            	            		"QualifiedNameWithBinding");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop91;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedNameList"


    // $ANTLR start "entryRuleInvocationOrAssignementOrDeclarationStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6772:1: entryRuleInvocationOrAssignementOrDeclarationStatement returns [EObject current=null] : iv_ruleInvocationOrAssignementOrDeclarationStatement= ruleInvocationOrAssignementOrDeclarationStatement EOF ;
    public final EObject entryRuleInvocationOrAssignementOrDeclarationStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInvocationOrAssignementOrDeclarationStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6773:2: (iv_ruleInvocationOrAssignementOrDeclarationStatement= ruleInvocationOrAssignementOrDeclarationStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6774:2: iv_ruleInvocationOrAssignementOrDeclarationStatement= ruleInvocationOrAssignementOrDeclarationStatement EOF
            {
             newCompositeNode(grammarAccess.getInvocationOrAssignementOrDeclarationStatementRule()); 
            pushFollow(FOLLOW_ruleInvocationOrAssignementOrDeclarationStatement_in_entryRuleInvocationOrAssignementOrDeclarationStatement14730);
            iv_ruleInvocationOrAssignementOrDeclarationStatement=ruleInvocationOrAssignementOrDeclarationStatement();

            state._fsp--;

             current =iv_ruleInvocationOrAssignementOrDeclarationStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInvocationOrAssignementOrDeclarationStatement14740); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInvocationOrAssignementOrDeclarationStatement"


    // $ANTLR start "ruleInvocationOrAssignementOrDeclarationStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6781:1: ruleInvocationOrAssignementOrDeclarationStatement returns [EObject current=null] : ( ( (lv_typePart_OR_assignedPart_OR_invocationPart_0_0= ruleNameExpression ) ) ( ( (lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion ) ) | ( (lv_assignmentCompletion_2_0= ruleAssignmentCompletion ) ) )? otherlv_3= ';' ) ;
    public final EObject ruleInvocationOrAssignementOrDeclarationStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        EObject lv_typePart_OR_assignedPart_OR_invocationPart_0_0 = null;

        EObject lv_variableDeclarationCompletion_1_0 = null;

        EObject lv_assignmentCompletion_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6784:28: ( ( ( (lv_typePart_OR_assignedPart_OR_invocationPart_0_0= ruleNameExpression ) ) ( ( (lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion ) ) | ( (lv_assignmentCompletion_2_0= ruleAssignmentCompletion ) ) )? otherlv_3= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6785:1: ( ( (lv_typePart_OR_assignedPart_OR_invocationPart_0_0= ruleNameExpression ) ) ( ( (lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion ) ) | ( (lv_assignmentCompletion_2_0= ruleAssignmentCompletion ) ) )? otherlv_3= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6785:1: ( ( (lv_typePart_OR_assignedPart_OR_invocationPart_0_0= ruleNameExpression ) ) ( ( (lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion ) ) | ( (lv_assignmentCompletion_2_0= ruleAssignmentCompletion ) ) )? otherlv_3= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6785:2: ( (lv_typePart_OR_assignedPart_OR_invocationPart_0_0= ruleNameExpression ) ) ( ( (lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion ) ) | ( (lv_assignmentCompletion_2_0= ruleAssignmentCompletion ) ) )? otherlv_3= ';'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6785:2: ( (lv_typePart_OR_assignedPart_OR_invocationPart_0_0= ruleNameExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6786:1: (lv_typePart_OR_assignedPart_OR_invocationPart_0_0= ruleNameExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6786:1: (lv_typePart_OR_assignedPart_OR_invocationPart_0_0= ruleNameExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6787:3: lv_typePart_OR_assignedPart_OR_invocationPart_0_0= ruleNameExpression
            {
             
            	        newCompositeNode(grammarAccess.getInvocationOrAssignementOrDeclarationStatementAccess().getTypePart_OR_assignedPart_OR_invocationPartNameExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleNameExpression_in_ruleInvocationOrAssignementOrDeclarationStatement14786);
            lv_typePart_OR_assignedPart_OR_invocationPart_0_0=ruleNameExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getInvocationOrAssignementOrDeclarationStatementRule());
            	        }
                   		set(
                   			current, 
                   			"typePart_OR_assignedPart_OR_invocationPart",
                    		lv_typePart_OR_assignedPart_OR_invocationPart_0_0, 
                    		"NameExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6803:2: ( ( (lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion ) ) | ( (lv_assignmentCompletion_2_0= ruleAssignmentCompletion ) ) )?
            int alt92=3;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==RULE_ID||LA92_0==50) ) {
                alt92=1;
            }
            else if ( (LA92_0==70||(LA92_0>=103 && LA92_0<=113)) ) {
                alt92=2;
            }
            switch (alt92) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6803:3: ( (lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6803:3: ( (lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6804:1: (lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6804:1: (lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6805:3: lv_variableDeclarationCompletion_1_0= ruleVariableDeclarationCompletion
                    {
                     
                    	        newCompositeNode(grammarAccess.getInvocationOrAssignementOrDeclarationStatementAccess().getVariableDeclarationCompletionVariableDeclarationCompletionParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleVariableDeclarationCompletion_in_ruleInvocationOrAssignementOrDeclarationStatement14808);
                    lv_variableDeclarationCompletion_1_0=ruleVariableDeclarationCompletion();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInvocationOrAssignementOrDeclarationStatementRule());
                    	        }
                           		set(
                           			current, 
                           			"variableDeclarationCompletion",
                            		lv_variableDeclarationCompletion_1_0, 
                            		"VariableDeclarationCompletion");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6822:6: ( (lv_assignmentCompletion_2_0= ruleAssignmentCompletion ) )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6822:6: ( (lv_assignmentCompletion_2_0= ruleAssignmentCompletion ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6823:1: (lv_assignmentCompletion_2_0= ruleAssignmentCompletion )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6823:1: (lv_assignmentCompletion_2_0= ruleAssignmentCompletion )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6824:3: lv_assignmentCompletion_2_0= ruleAssignmentCompletion
                    {
                     
                    	        newCompositeNode(grammarAccess.getInvocationOrAssignementOrDeclarationStatementAccess().getAssignmentCompletionAssignmentCompletionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAssignmentCompletion_in_ruleInvocationOrAssignementOrDeclarationStatement14835);
                    lv_assignmentCompletion_2_0=ruleAssignmentCompletion();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInvocationOrAssignementOrDeclarationStatementRule());
                    	        }
                           		set(
                           			current, 
                           			"assignmentCompletion",
                            		lv_assignmentCompletion_2_0, 
                            		"AssignmentCompletion");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,68,FOLLOW_68_in_ruleInvocationOrAssignementOrDeclarationStatement14849); 

                	newLeafNode(otherlv_3, grammarAccess.getInvocationOrAssignementOrDeclarationStatementAccess().getSemicolonKeyword_2());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInvocationOrAssignementOrDeclarationStatement"


    // $ANTLR start "entryRuleSuperInvocationStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6852:1: entryRuleSuperInvocationStatement returns [EObject current=null] : iv_ruleSuperInvocationStatement= ruleSuperInvocationStatement EOF ;
    public final EObject entryRuleSuperInvocationStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuperInvocationStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6853:2: (iv_ruleSuperInvocationStatement= ruleSuperInvocationStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6854:2: iv_ruleSuperInvocationStatement= ruleSuperInvocationStatement EOF
            {
             newCompositeNode(grammarAccess.getSuperInvocationStatementRule()); 
            pushFollow(FOLLOW_ruleSuperInvocationStatement_in_entryRuleSuperInvocationStatement14885);
            iv_ruleSuperInvocationStatement=ruleSuperInvocationStatement();

            state._fsp--;

             current =iv_ruleSuperInvocationStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSuperInvocationStatement14895); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSuperInvocationStatement"


    // $ANTLR start "ruleSuperInvocationStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6861:1: ruleSuperInvocationStatement returns [EObject current=null] : ( ( (lv__super_0_0= ruleSuperInvocationExpression ) ) otherlv_1= ';' ) ;
    public final EObject ruleSuperInvocationStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv__super_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6864:28: ( ( ( (lv__super_0_0= ruleSuperInvocationExpression ) ) otherlv_1= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6865:1: ( ( (lv__super_0_0= ruleSuperInvocationExpression ) ) otherlv_1= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6865:1: ( ( (lv__super_0_0= ruleSuperInvocationExpression ) ) otherlv_1= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6865:2: ( (lv__super_0_0= ruleSuperInvocationExpression ) ) otherlv_1= ';'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6865:2: ( (lv__super_0_0= ruleSuperInvocationExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6866:1: (lv__super_0_0= ruleSuperInvocationExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6866:1: (lv__super_0_0= ruleSuperInvocationExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6867:3: lv__super_0_0= ruleSuperInvocationExpression
            {
             
            	        newCompositeNode(grammarAccess.getSuperInvocationStatementAccess().get_superSuperInvocationExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleSuperInvocationExpression_in_ruleSuperInvocationStatement14941);
            lv__super_0_0=ruleSuperInvocationExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSuperInvocationStatementRule());
            	        }
                   		set(
                   			current, 
                   			"_super",
                    		lv__super_0_0, 
                    		"SuperInvocationExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,68,FOLLOW_68_in_ruleSuperInvocationStatement14953); 

                	newLeafNode(otherlv_1, grammarAccess.getSuperInvocationStatementAccess().getSemicolonKeyword_1());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSuperInvocationStatement"


    // $ANTLR start "entryRuleThisInvocationStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6895:1: entryRuleThisInvocationStatement returns [EObject current=null] : iv_ruleThisInvocationStatement= ruleThisInvocationStatement EOF ;
    public final EObject entryRuleThisInvocationStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThisInvocationStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6896:2: (iv_ruleThisInvocationStatement= ruleThisInvocationStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6897:2: iv_ruleThisInvocationStatement= ruleThisInvocationStatement EOF
            {
             newCompositeNode(grammarAccess.getThisInvocationStatementRule()); 
            pushFollow(FOLLOW_ruleThisInvocationStatement_in_entryRuleThisInvocationStatement14989);
            iv_ruleThisInvocationStatement=ruleThisInvocationStatement();

            state._fsp--;

             current =iv_ruleThisInvocationStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleThisInvocationStatement14999); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleThisInvocationStatement"


    // $ANTLR start "ruleThisInvocationStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6904:1: ruleThisInvocationStatement returns [EObject current=null] : ( ( (lv__this_0_0= ruleThisExpression ) ) ( (lv_assignmentCompletion_1_0= ruleAssignmentCompletion ) )? otherlv_2= ';' ) ;
    public final EObject ruleThisInvocationStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv__this_0_0 = null;

        EObject lv_assignmentCompletion_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6907:28: ( ( ( (lv__this_0_0= ruleThisExpression ) ) ( (lv_assignmentCompletion_1_0= ruleAssignmentCompletion ) )? otherlv_2= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6908:1: ( ( (lv__this_0_0= ruleThisExpression ) ) ( (lv_assignmentCompletion_1_0= ruleAssignmentCompletion ) )? otherlv_2= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6908:1: ( ( (lv__this_0_0= ruleThisExpression ) ) ( (lv_assignmentCompletion_1_0= ruleAssignmentCompletion ) )? otherlv_2= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6908:2: ( (lv__this_0_0= ruleThisExpression ) ) ( (lv_assignmentCompletion_1_0= ruleAssignmentCompletion ) )? otherlv_2= ';'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6908:2: ( (lv__this_0_0= ruleThisExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6909:1: (lv__this_0_0= ruleThisExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6909:1: (lv__this_0_0= ruleThisExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6910:3: lv__this_0_0= ruleThisExpression
            {
             
            	        newCompositeNode(grammarAccess.getThisInvocationStatementAccess().get_thisThisExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleThisExpression_in_ruleThisInvocationStatement15045);
            lv__this_0_0=ruleThisExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getThisInvocationStatementRule());
            	        }
                   		set(
                   			current, 
                   			"_this",
                    		lv__this_0_0, 
                    		"ThisExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6926:2: ( (lv_assignmentCompletion_1_0= ruleAssignmentCompletion ) )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==70||(LA93_0>=103 && LA93_0<=113)) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6927:1: (lv_assignmentCompletion_1_0= ruleAssignmentCompletion )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6927:1: (lv_assignmentCompletion_1_0= ruleAssignmentCompletion )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6928:3: lv_assignmentCompletion_1_0= ruleAssignmentCompletion
                    {
                     
                    	        newCompositeNode(grammarAccess.getThisInvocationStatementAccess().getAssignmentCompletionAssignmentCompletionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAssignmentCompletion_in_ruleThisInvocationStatement15066);
                    lv_assignmentCompletion_1_0=ruleAssignmentCompletion();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getThisInvocationStatementRule());
                    	        }
                           		set(
                           			current, 
                           			"assignmentCompletion",
                            		lv_assignmentCompletion_1_0, 
                            		"AssignmentCompletion");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,68,FOLLOW_68_in_ruleThisInvocationStatement15079); 

                	newLeafNode(otherlv_2, grammarAccess.getThisInvocationStatementAccess().getSemicolonKeyword_2());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleThisInvocationStatement"


    // $ANTLR start "entryRuleInstanceCreationInvocationStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6956:1: entryRuleInstanceCreationInvocationStatement returns [EObject current=null] : iv_ruleInstanceCreationInvocationStatement= ruleInstanceCreationInvocationStatement EOF ;
    public final EObject entryRuleInstanceCreationInvocationStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstanceCreationInvocationStatement = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6957:2: (iv_ruleInstanceCreationInvocationStatement= ruleInstanceCreationInvocationStatement EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6958:2: iv_ruleInstanceCreationInvocationStatement= ruleInstanceCreationInvocationStatement EOF
            {
             newCompositeNode(grammarAccess.getInstanceCreationInvocationStatementRule()); 
            pushFollow(FOLLOW_ruleInstanceCreationInvocationStatement_in_entryRuleInstanceCreationInvocationStatement15115);
            iv_ruleInstanceCreationInvocationStatement=ruleInstanceCreationInvocationStatement();

            state._fsp--;

             current =iv_ruleInstanceCreationInvocationStatement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInstanceCreationInvocationStatement15125); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInstanceCreationInvocationStatement"


    // $ANTLR start "ruleInstanceCreationInvocationStatement"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6965:1: ruleInstanceCreationInvocationStatement returns [EObject current=null] : ( ( (lv__new_0_0= ruleInstanceCreationExpression ) ) otherlv_1= ';' ) ;
    public final EObject ruleInstanceCreationInvocationStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv__new_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6968:28: ( ( ( (lv__new_0_0= ruleInstanceCreationExpression ) ) otherlv_1= ';' ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6969:1: ( ( (lv__new_0_0= ruleInstanceCreationExpression ) ) otherlv_1= ';' )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6969:1: ( ( (lv__new_0_0= ruleInstanceCreationExpression ) ) otherlv_1= ';' )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6969:2: ( (lv__new_0_0= ruleInstanceCreationExpression ) ) otherlv_1= ';'
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6969:2: ( (lv__new_0_0= ruleInstanceCreationExpression ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6970:1: (lv__new_0_0= ruleInstanceCreationExpression )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6970:1: (lv__new_0_0= ruleInstanceCreationExpression )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6971:3: lv__new_0_0= ruleInstanceCreationExpression
            {
             
            	        newCompositeNode(grammarAccess.getInstanceCreationInvocationStatementAccess().get_newInstanceCreationExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleInstanceCreationExpression_in_ruleInstanceCreationInvocationStatement15171);
            lv__new_0_0=ruleInstanceCreationExpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getInstanceCreationInvocationStatementRule());
            	        }
                   		set(
                   			current, 
                   			"_new",
                    		lv__new_0_0, 
                    		"InstanceCreationExpression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,68,FOLLOW_68_in_ruleInstanceCreationInvocationStatement15183); 

                	newLeafNode(otherlv_1, grammarAccess.getInstanceCreationInvocationStatementAccess().getSemicolonKeyword_1());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInstanceCreationInvocationStatement"


    // $ANTLR start "entryRuleVariableDeclarationCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:6999:1: entryRuleVariableDeclarationCompletion returns [EObject current=null] : iv_ruleVariableDeclarationCompletion= ruleVariableDeclarationCompletion EOF ;
    public final EObject entryRuleVariableDeclarationCompletion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableDeclarationCompletion = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7000:2: (iv_ruleVariableDeclarationCompletion= ruleVariableDeclarationCompletion EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7001:2: iv_ruleVariableDeclarationCompletion= ruleVariableDeclarationCompletion EOF
            {
             newCompositeNode(grammarAccess.getVariableDeclarationCompletionRule()); 
            pushFollow(FOLLOW_ruleVariableDeclarationCompletion_in_entryRuleVariableDeclarationCompletion15219);
            iv_ruleVariableDeclarationCompletion=ruleVariableDeclarationCompletion();

            state._fsp--;

             current =iv_ruleVariableDeclarationCompletion; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVariableDeclarationCompletion15229); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableDeclarationCompletion"


    // $ANTLR start "ruleVariableDeclarationCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7008:1: ruleVariableDeclarationCompletion returns [EObject current=null] : ( ( ( (lv_multiplicityIndicator_0_0= '[' ) ) otherlv_1= ']' )? ( (lv_variableName_2_0= RULE_ID ) ) ( (lv_initValue_3_0= ruleAssignmentCompletion ) ) ) ;
    public final EObject ruleVariableDeclarationCompletion() throws RecognitionException {
        EObject current = null;

        Token lv_multiplicityIndicator_0_0=null;
        Token otherlv_1=null;
        Token lv_variableName_2_0=null;
        EObject lv_initValue_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7011:28: ( ( ( ( (lv_multiplicityIndicator_0_0= '[' ) ) otherlv_1= ']' )? ( (lv_variableName_2_0= RULE_ID ) ) ( (lv_initValue_3_0= ruleAssignmentCompletion ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7012:1: ( ( ( (lv_multiplicityIndicator_0_0= '[' ) ) otherlv_1= ']' )? ( (lv_variableName_2_0= RULE_ID ) ) ( (lv_initValue_3_0= ruleAssignmentCompletion ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7012:1: ( ( ( (lv_multiplicityIndicator_0_0= '[' ) ) otherlv_1= ']' )? ( (lv_variableName_2_0= RULE_ID ) ) ( (lv_initValue_3_0= ruleAssignmentCompletion ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7012:2: ( ( (lv_multiplicityIndicator_0_0= '[' ) ) otherlv_1= ']' )? ( (lv_variableName_2_0= RULE_ID ) ) ( (lv_initValue_3_0= ruleAssignmentCompletion ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7012:2: ( ( (lv_multiplicityIndicator_0_0= '[' ) ) otherlv_1= ']' )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==50) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7012:3: ( (lv_multiplicityIndicator_0_0= '[' ) ) otherlv_1= ']'
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7012:3: ( (lv_multiplicityIndicator_0_0= '[' ) )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7013:1: (lv_multiplicityIndicator_0_0= '[' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7013:1: (lv_multiplicityIndicator_0_0= '[' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7014:3: lv_multiplicityIndicator_0_0= '['
                    {
                    lv_multiplicityIndicator_0_0=(Token)match(input,50,FOLLOW_50_in_ruleVariableDeclarationCompletion15273); 

                            newLeafNode(lv_multiplicityIndicator_0_0, grammarAccess.getVariableDeclarationCompletionAccess().getMultiplicityIndicatorLeftSquareBracketKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getVariableDeclarationCompletionRule());
                    	        }
                           		setWithLastConsumed(current, "multiplicityIndicator", true, "[");
                    	    

                    }


                    }

                    otherlv_1=(Token)match(input,51,FOLLOW_51_in_ruleVariableDeclarationCompletion15298); 

                        	newLeafNode(otherlv_1, grammarAccess.getVariableDeclarationCompletionAccess().getRightSquareBracketKeyword_0_1());
                        

                    }
                    break;

            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7031:3: ( (lv_variableName_2_0= RULE_ID ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7032:1: (lv_variableName_2_0= RULE_ID )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7032:1: (lv_variableName_2_0= RULE_ID )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7033:3: lv_variableName_2_0= RULE_ID
            {
            lv_variableName_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleVariableDeclarationCompletion15317); 

            			newLeafNode(lv_variableName_2_0, grammarAccess.getVariableDeclarationCompletionAccess().getVariableNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getVariableDeclarationCompletionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"variableName",
                    		lv_variableName_2_0, 
                    		"ID");
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7049:2: ( (lv_initValue_3_0= ruleAssignmentCompletion ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7050:1: (lv_initValue_3_0= ruleAssignmentCompletion )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7050:1: (lv_initValue_3_0= ruleAssignmentCompletion )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7051:3: lv_initValue_3_0= ruleAssignmentCompletion
            {
             
            	        newCompositeNode(grammarAccess.getVariableDeclarationCompletionAccess().getInitValueAssignmentCompletionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleAssignmentCompletion_in_ruleVariableDeclarationCompletion15343);
            lv_initValue_3_0=ruleAssignmentCompletion();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getVariableDeclarationCompletionRule());
            	        }
                   		set(
                   			current, 
                   			"initValue",
                    		lv_initValue_3_0, 
                    		"AssignmentCompletion");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableDeclarationCompletion"


    // $ANTLR start "entryRuleAssignmentCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7075:1: entryRuleAssignmentCompletion returns [EObject current=null] : iv_ruleAssignmentCompletion= ruleAssignmentCompletion EOF ;
    public final EObject entryRuleAssignmentCompletion() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignmentCompletion = null;


        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7076:2: (iv_ruleAssignmentCompletion= ruleAssignmentCompletion EOF )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7077:2: iv_ruleAssignmentCompletion= ruleAssignmentCompletion EOF
            {
             newCompositeNode(grammarAccess.getAssignmentCompletionRule()); 
            pushFollow(FOLLOW_ruleAssignmentCompletion_in_entryRuleAssignmentCompletion15379);
            iv_ruleAssignmentCompletion=ruleAssignmentCompletion();

            state._fsp--;

             current =iv_ruleAssignmentCompletion; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAssignmentCompletion15389); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssignmentCompletion"


    // $ANTLR start "ruleAssignmentCompletion"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7084:1: ruleAssignmentCompletion returns [EObject current=null] : ( ( (lv_op_0_0= ruleAssignmentOperator ) ) ( (lv_rightHandSide_1_0= ruleSequenceElement ) ) ) ;
    public final EObject ruleAssignmentCompletion() throws RecognitionException {
        EObject current = null;

        Enumerator lv_op_0_0 = null;

        EObject lv_rightHandSide_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7087:28: ( ( ( (lv_op_0_0= ruleAssignmentOperator ) ) ( (lv_rightHandSide_1_0= ruleSequenceElement ) ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7088:1: ( ( (lv_op_0_0= ruleAssignmentOperator ) ) ( (lv_rightHandSide_1_0= ruleSequenceElement ) ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7088:1: ( ( (lv_op_0_0= ruleAssignmentOperator ) ) ( (lv_rightHandSide_1_0= ruleSequenceElement ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7088:2: ( (lv_op_0_0= ruleAssignmentOperator ) ) ( (lv_rightHandSide_1_0= ruleSequenceElement ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7088:2: ( (lv_op_0_0= ruleAssignmentOperator ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7089:1: (lv_op_0_0= ruleAssignmentOperator )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7089:1: (lv_op_0_0= ruleAssignmentOperator )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7090:3: lv_op_0_0= ruleAssignmentOperator
            {
             
            	        newCompositeNode(grammarAccess.getAssignmentCompletionAccess().getOpAssignmentOperatorEnumRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleAssignmentOperator_in_ruleAssignmentCompletion15435);
            lv_op_0_0=ruleAssignmentOperator();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAssignmentCompletionRule());
            	        }
                   		set(
                   			current, 
                   			"op",
                    		lv_op_0_0, 
                    		"AssignmentOperator");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7106:2: ( (lv_rightHandSide_1_0= ruleSequenceElement ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7107:1: (lv_rightHandSide_1_0= ruleSequenceElement )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7107:1: (lv_rightHandSide_1_0= ruleSequenceElement )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7108:3: lv_rightHandSide_1_0= ruleSequenceElement
            {
             
            	        newCompositeNode(grammarAccess.getAssignmentCompletionAccess().getRightHandSideSequenceElementParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleSequenceElement_in_ruleAssignmentCompletion15456);
            lv_rightHandSide_1_0=ruleSequenceElement();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAssignmentCompletionRule());
            	        }
                   		set(
                   			current, 
                   			"rightHandSide",
                    		lv_rightHandSide_1_0, 
                    		"SequenceElement");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignmentCompletion"


    // $ANTLR start "ruleBooleanValue"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7132:1: ruleBooleanValue returns [Enumerator current=null] : ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) ) ;
    public final Enumerator ruleBooleanValue() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7134:28: ( ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7135:1: ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7135:1: ( (enumLiteral_0= 'true' ) | (enumLiteral_1= 'false' ) )
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==87) ) {
                alt95=1;
            }
            else if ( (LA95_0==88) ) {
                alt95=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }
            switch (alt95) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7135:2: (enumLiteral_0= 'true' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7135:2: (enumLiteral_0= 'true' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7135:4: enumLiteral_0= 'true'
                    {
                    enumLiteral_0=(Token)match(input,87,FOLLOW_87_in_ruleBooleanValue15506); 

                            current = grammarAccess.getBooleanValueAccess().getTRUEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getBooleanValueAccess().getTRUEEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7141:6: (enumLiteral_1= 'false' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7141:6: (enumLiteral_1= 'false' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7141:8: enumLiteral_1= 'false'
                    {
                    enumLiteral_1=(Token)match(input,88,FOLLOW_88_in_ruleBooleanValue15523); 

                            current = grammarAccess.getBooleanValueAccess().getFALSEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getBooleanValueAccess().getFALSEEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanValue"


    // $ANTLR start "ruleLinkOperationKind"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7151:1: ruleLinkOperationKind returns [Enumerator current=null] : ( (enumLiteral_0= 'createLink' ) | (enumLiteral_1= 'destroyLink' ) | (enumLiteral_2= 'clearAssoc' ) ) ;
    public final Enumerator ruleLinkOperationKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7153:28: ( ( (enumLiteral_0= 'createLink' ) | (enumLiteral_1= 'destroyLink' ) | (enumLiteral_2= 'clearAssoc' ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7154:1: ( (enumLiteral_0= 'createLink' ) | (enumLiteral_1= 'destroyLink' ) | (enumLiteral_2= 'clearAssoc' ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7154:1: ( (enumLiteral_0= 'createLink' ) | (enumLiteral_1= 'destroyLink' ) | (enumLiteral_2= 'clearAssoc' ) )
            int alt96=3;
            switch ( input.LA(1) ) {
            case 89:
                {
                alt96=1;
                }
                break;
            case 90:
                {
                alt96=2;
                }
                break;
            case 91:
                {
                alt96=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 96, 0, input);

                throw nvae;
            }

            switch (alt96) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7154:2: (enumLiteral_0= 'createLink' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7154:2: (enumLiteral_0= 'createLink' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7154:4: enumLiteral_0= 'createLink'
                    {
                    enumLiteral_0=(Token)match(input,89,FOLLOW_89_in_ruleLinkOperationKind15568); 

                            current = grammarAccess.getLinkOperationKindAccess().getCREATEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLinkOperationKindAccess().getCREATEEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7160:6: (enumLiteral_1= 'destroyLink' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7160:6: (enumLiteral_1= 'destroyLink' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7160:8: enumLiteral_1= 'destroyLink'
                    {
                    enumLiteral_1=(Token)match(input,90,FOLLOW_90_in_ruleLinkOperationKind15585); 

                            current = grammarAccess.getLinkOperationKindAccess().getDESTROYEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLinkOperationKindAccess().getDESTROYEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7166:6: (enumLiteral_2= 'clearAssoc' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7166:6: (enumLiteral_2= 'clearAssoc' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7166:8: enumLiteral_2= 'clearAssoc'
                    {
                    enumLiteral_2=(Token)match(input,91,FOLLOW_91_in_ruleLinkOperationKind15602); 

                            current = grammarAccess.getLinkOperationKindAccess().getCLEAREnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLinkOperationKindAccess().getCLEAREnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLinkOperationKind"


    // $ANTLR start "ruleSelectOrRejectOperator"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7176:1: ruleSelectOrRejectOperator returns [Enumerator current=null] : ( (enumLiteral_0= 'select' ) | (enumLiteral_1= 'reject' ) ) ;
    public final Enumerator ruleSelectOrRejectOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7178:28: ( ( (enumLiteral_0= 'select' ) | (enumLiteral_1= 'reject' ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7179:1: ( (enumLiteral_0= 'select' ) | (enumLiteral_1= 'reject' ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7179:1: ( (enumLiteral_0= 'select' ) | (enumLiteral_1= 'reject' ) )
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==92) ) {
                alt97=1;
            }
            else if ( (LA97_0==93) ) {
                alt97=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 97, 0, input);

                throw nvae;
            }
            switch (alt97) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7179:2: (enumLiteral_0= 'select' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7179:2: (enumLiteral_0= 'select' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7179:4: enumLiteral_0= 'select'
                    {
                    enumLiteral_0=(Token)match(input,92,FOLLOW_92_in_ruleSelectOrRejectOperator15647); 

                            current = grammarAccess.getSelectOrRejectOperatorAccess().getSELECTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getSelectOrRejectOperatorAccess().getSELECTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7185:6: (enumLiteral_1= 'reject' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7185:6: (enumLiteral_1= 'reject' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7185:8: enumLiteral_1= 'reject'
                    {
                    enumLiteral_1=(Token)match(input,93,FOLLOW_93_in_ruleSelectOrRejectOperator15664); 

                            current = grammarAccess.getSelectOrRejectOperatorAccess().getREJECTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getSelectOrRejectOperatorAccess().getREJECTEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSelectOrRejectOperator"


    // $ANTLR start "ruleCollectOrIterateOperator"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7195:1: ruleCollectOrIterateOperator returns [Enumerator current=null] : ( (enumLiteral_0= 'collect' ) | (enumLiteral_1= 'iterate' ) ) ;
    public final Enumerator ruleCollectOrIterateOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7197:28: ( ( (enumLiteral_0= 'collect' ) | (enumLiteral_1= 'iterate' ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7198:1: ( (enumLiteral_0= 'collect' ) | (enumLiteral_1= 'iterate' ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7198:1: ( (enumLiteral_0= 'collect' ) | (enumLiteral_1= 'iterate' ) )
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==94) ) {
                alt98=1;
            }
            else if ( (LA98_0==95) ) {
                alt98=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }
            switch (alt98) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7198:2: (enumLiteral_0= 'collect' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7198:2: (enumLiteral_0= 'collect' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7198:4: enumLiteral_0= 'collect'
                    {
                    enumLiteral_0=(Token)match(input,94,FOLLOW_94_in_ruleCollectOrIterateOperator15709); 

                            current = grammarAccess.getCollectOrIterateOperatorAccess().getCOLLECTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getCollectOrIterateOperatorAccess().getCOLLECTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7204:6: (enumLiteral_1= 'iterate' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7204:6: (enumLiteral_1= 'iterate' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7204:8: enumLiteral_1= 'iterate'
                    {
                    enumLiteral_1=(Token)match(input,95,FOLLOW_95_in_ruleCollectOrIterateOperator15726); 

                            current = grammarAccess.getCollectOrIterateOperatorAccess().getITERATEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getCollectOrIterateOperatorAccess().getITERATEEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCollectOrIterateOperator"


    // $ANTLR start "ruleForAllOrExistsOrOneOperator"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7214:1: ruleForAllOrExistsOrOneOperator returns [Enumerator current=null] : ( (enumLiteral_0= 'forAll' ) | (enumLiteral_1= 'exists' ) | (enumLiteral_2= 'one' ) ) ;
    public final Enumerator ruleForAllOrExistsOrOneOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7216:28: ( ( (enumLiteral_0= 'forAll' ) | (enumLiteral_1= 'exists' ) | (enumLiteral_2= 'one' ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7217:1: ( (enumLiteral_0= 'forAll' ) | (enumLiteral_1= 'exists' ) | (enumLiteral_2= 'one' ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7217:1: ( (enumLiteral_0= 'forAll' ) | (enumLiteral_1= 'exists' ) | (enumLiteral_2= 'one' ) )
            int alt99=3;
            switch ( input.LA(1) ) {
            case 96:
                {
                alt99=1;
                }
                break;
            case 97:
                {
                alt99=2;
                }
                break;
            case 98:
                {
                alt99=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 99, 0, input);

                throw nvae;
            }

            switch (alt99) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7217:2: (enumLiteral_0= 'forAll' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7217:2: (enumLiteral_0= 'forAll' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7217:4: enumLiteral_0= 'forAll'
                    {
                    enumLiteral_0=(Token)match(input,96,FOLLOW_96_in_ruleForAllOrExistsOrOneOperator15771); 

                            current = grammarAccess.getForAllOrExistsOrOneOperatorAccess().getFORALLEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getForAllOrExistsOrOneOperatorAccess().getFORALLEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7223:6: (enumLiteral_1= 'exists' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7223:6: (enumLiteral_1= 'exists' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7223:8: enumLiteral_1= 'exists'
                    {
                    enumLiteral_1=(Token)match(input,97,FOLLOW_97_in_ruleForAllOrExistsOrOneOperator15788); 

                            current = grammarAccess.getForAllOrExistsOrOneOperatorAccess().getEXISTSEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getForAllOrExistsOrOneOperatorAccess().getEXISTSEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7229:6: (enumLiteral_2= 'one' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7229:6: (enumLiteral_2= 'one' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7229:8: enumLiteral_2= 'one'
                    {
                    enumLiteral_2=(Token)match(input,98,FOLLOW_98_in_ruleForAllOrExistsOrOneOperator15805); 

                            current = grammarAccess.getForAllOrExistsOrOneOperatorAccess().getONEEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getForAllOrExistsOrOneOperatorAccess().getONEEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForAllOrExistsOrOneOperator"


    // $ANTLR start "ruleAnnotationKind"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7239:1: ruleAnnotationKind returns [Enumerator current=null] : ( (enumLiteral_0= 'isolated' ) | (enumLiteral_1= 'determined' ) | (enumLiteral_2= 'assured' ) | (enumLiteral_3= 'parallel' ) ) ;
    public final Enumerator ruleAnnotationKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;

         enterRule(); 
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7241:28: ( ( (enumLiteral_0= 'isolated' ) | (enumLiteral_1= 'determined' ) | (enumLiteral_2= 'assured' ) | (enumLiteral_3= 'parallel' ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7242:1: ( (enumLiteral_0= 'isolated' ) | (enumLiteral_1= 'determined' ) | (enumLiteral_2= 'assured' ) | (enumLiteral_3= 'parallel' ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7242:1: ( (enumLiteral_0= 'isolated' ) | (enumLiteral_1= 'determined' ) | (enumLiteral_2= 'assured' ) | (enumLiteral_3= 'parallel' ) )
            int alt100=4;
            switch ( input.LA(1) ) {
            case 99:
                {
                alt100=1;
                }
                break;
            case 100:
                {
                alt100=2;
                }
                break;
            case 101:
                {
                alt100=3;
                }
                break;
            case 102:
                {
                alt100=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;
            }

            switch (alt100) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7242:2: (enumLiteral_0= 'isolated' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7242:2: (enumLiteral_0= 'isolated' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7242:4: enumLiteral_0= 'isolated'
                    {
                    enumLiteral_0=(Token)match(input,99,FOLLOW_99_in_ruleAnnotationKind15850); 

                            current = grammarAccess.getAnnotationKindAccess().getISOLATEDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getAnnotationKindAccess().getISOLATEDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7248:6: (enumLiteral_1= 'determined' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7248:6: (enumLiteral_1= 'determined' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7248:8: enumLiteral_1= 'determined'
                    {
                    enumLiteral_1=(Token)match(input,100,FOLLOW_100_in_ruleAnnotationKind15867); 

                            current = grammarAccess.getAnnotationKindAccess().getDETERMINEDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getAnnotationKindAccess().getDETERMINEDEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7254:6: (enumLiteral_2= 'assured' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7254:6: (enumLiteral_2= 'assured' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7254:8: enumLiteral_2= 'assured'
                    {
                    enumLiteral_2=(Token)match(input,101,FOLLOW_101_in_ruleAnnotationKind15884); 

                            current = grammarAccess.getAnnotationKindAccess().getASSUREDEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getAnnotationKindAccess().getASSUREDEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7260:6: (enumLiteral_3= 'parallel' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7260:6: (enumLiteral_3= 'parallel' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7260:8: enumLiteral_3= 'parallel'
                    {
                    enumLiteral_3=(Token)match(input,102,FOLLOW_102_in_ruleAnnotationKind15901); 

                            current = grammarAccess.getAnnotationKindAccess().getPARALLELEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getAnnotationKindAccess().getPARALLELEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnnotationKind"


    // $ANTLR start "ruleAssignmentOperator"
    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7270:1: ruleAssignmentOperator returns [Enumerator current=null] : ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '%=' ) | (enumLiteral_5= '/=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) | (enumLiteral_11= '>>>=' ) ) ;
    public final Enumerator ruleAssignmentOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;
        Token enumLiteral_10=null;
        Token enumLiteral_11=null;

         enterRule(); 
        try {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7272:28: ( ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '%=' ) | (enumLiteral_5= '/=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) | (enumLiteral_11= '>>>=' ) ) )
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7273:1: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '%=' ) | (enumLiteral_5= '/=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) | (enumLiteral_11= '>>>=' ) )
            {
            // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7273:1: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '+=' ) | (enumLiteral_2= '-=' ) | (enumLiteral_3= '*=' ) | (enumLiteral_4= '%=' ) | (enumLiteral_5= '/=' ) | (enumLiteral_6= '&=' ) | (enumLiteral_7= '|=' ) | (enumLiteral_8= '^=' ) | (enumLiteral_9= '<<=' ) | (enumLiteral_10= '>>=' ) | (enumLiteral_11= '>>>=' ) )
            int alt101=12;
            switch ( input.LA(1) ) {
            case 70:
                {
                alt101=1;
                }
                break;
            case 103:
                {
                alt101=2;
                }
                break;
            case 104:
                {
                alt101=3;
                }
                break;
            case 105:
                {
                alt101=4;
                }
                break;
            case 106:
                {
                alt101=5;
                }
                break;
            case 107:
                {
                alt101=6;
                }
                break;
            case 108:
                {
                alt101=7;
                }
                break;
            case 109:
                {
                alt101=8;
                }
                break;
            case 110:
                {
                alt101=9;
                }
                break;
            case 111:
                {
                alt101=10;
                }
                break;
            case 112:
                {
                alt101=11;
                }
                break;
            case 113:
                {
                alt101=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }

            switch (alt101) {
                case 1 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7273:2: (enumLiteral_0= '=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7273:2: (enumLiteral_0= '=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7273:4: enumLiteral_0= '='
                    {
                    enumLiteral_0=(Token)match(input,70,FOLLOW_70_in_ruleAssignmentOperator15946); 

                            current = grammarAccess.getAssignmentOperatorAccess().getASSIGNEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getAssignmentOperatorAccess().getASSIGNEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7279:6: (enumLiteral_1= '+=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7279:6: (enumLiteral_1= '+=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7279:8: enumLiteral_1= '+='
                    {
                    enumLiteral_1=(Token)match(input,103,FOLLOW_103_in_ruleAssignmentOperator15963); 

                            current = grammarAccess.getAssignmentOperatorAccess().getPLUSASSIGNEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getAssignmentOperatorAccess().getPLUSASSIGNEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7285:6: (enumLiteral_2= '-=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7285:6: (enumLiteral_2= '-=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7285:8: enumLiteral_2= '-='
                    {
                    enumLiteral_2=(Token)match(input,104,FOLLOW_104_in_ruleAssignmentOperator15980); 

                            current = grammarAccess.getAssignmentOperatorAccess().getMINUSASSIGNEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getAssignmentOperatorAccess().getMINUSASSIGNEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7291:6: (enumLiteral_3= '*=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7291:6: (enumLiteral_3= '*=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7291:8: enumLiteral_3= '*='
                    {
                    enumLiteral_3=(Token)match(input,105,FOLLOW_105_in_ruleAssignmentOperator15997); 

                            current = grammarAccess.getAssignmentOperatorAccess().getMULTASSIGNEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getAssignmentOperatorAccess().getMULTASSIGNEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7297:6: (enumLiteral_4= '%=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7297:6: (enumLiteral_4= '%=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7297:8: enumLiteral_4= '%='
                    {
                    enumLiteral_4=(Token)match(input,106,FOLLOW_106_in_ruleAssignmentOperator16014); 

                            current = grammarAccess.getAssignmentOperatorAccess().getMODASSIGNEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getAssignmentOperatorAccess().getMODASSIGNEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7303:6: (enumLiteral_5= '/=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7303:6: (enumLiteral_5= '/=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7303:8: enumLiteral_5= '/='
                    {
                    enumLiteral_5=(Token)match(input,107,FOLLOW_107_in_ruleAssignmentOperator16031); 

                            current = grammarAccess.getAssignmentOperatorAccess().getDIVASSIGNEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getAssignmentOperatorAccess().getDIVASSIGNEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7309:6: (enumLiteral_6= '&=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7309:6: (enumLiteral_6= '&=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7309:8: enumLiteral_6= '&='
                    {
                    enumLiteral_6=(Token)match(input,108,FOLLOW_108_in_ruleAssignmentOperator16048); 

                            current = grammarAccess.getAssignmentOperatorAccess().getANDASSIGNEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_6, grammarAccess.getAssignmentOperatorAccess().getANDASSIGNEnumLiteralDeclaration_6()); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7315:6: (enumLiteral_7= '|=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7315:6: (enumLiteral_7= '|=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7315:8: enumLiteral_7= '|='
                    {
                    enumLiteral_7=(Token)match(input,109,FOLLOW_109_in_ruleAssignmentOperator16065); 

                            current = grammarAccess.getAssignmentOperatorAccess().getORASSIGNEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_7, grammarAccess.getAssignmentOperatorAccess().getORASSIGNEnumLiteralDeclaration_7()); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7321:6: (enumLiteral_8= '^=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7321:6: (enumLiteral_8= '^=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7321:8: enumLiteral_8= '^='
                    {
                    enumLiteral_8=(Token)match(input,110,FOLLOW_110_in_ruleAssignmentOperator16082); 

                            current = grammarAccess.getAssignmentOperatorAccess().getXORASSIGNEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_8, grammarAccess.getAssignmentOperatorAccess().getXORASSIGNEnumLiteralDeclaration_8()); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7327:6: (enumLiteral_9= '<<=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7327:6: (enumLiteral_9= '<<=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7327:8: enumLiteral_9= '<<='
                    {
                    enumLiteral_9=(Token)match(input,111,FOLLOW_111_in_ruleAssignmentOperator16099); 

                            current = grammarAccess.getAssignmentOperatorAccess().getLSHIFTASSIGNEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_9, grammarAccess.getAssignmentOperatorAccess().getLSHIFTASSIGNEnumLiteralDeclaration_9()); 
                        

                    }


                    }
                    break;
                case 11 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7333:6: (enumLiteral_10= '>>=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7333:6: (enumLiteral_10= '>>=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7333:8: enumLiteral_10= '>>='
                    {
                    enumLiteral_10=(Token)match(input,112,FOLLOW_112_in_ruleAssignmentOperator16116); 

                            current = grammarAccess.getAssignmentOperatorAccess().getRSHIFTASSIGNEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_10, grammarAccess.getAssignmentOperatorAccess().getRSHIFTASSIGNEnumLiteralDeclaration_10()); 
                        

                    }


                    }
                    break;
                case 12 :
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7339:6: (enumLiteral_11= '>>>=' )
                    {
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7339:6: (enumLiteral_11= '>>>=' )
                    // ../org.eclipse.papyrus.uml.alf/src-gen/org/eclipse/papyrus/uml/alf/parser/antlr/internal/InternalAlf.g:7339:8: enumLiteral_11= '>>>='
                    {
                    enumLiteral_11=(Token)match(input,113,FOLLOW_113_in_ruleAssignmentOperator16133); 

                            current = grammarAccess.getAssignmentOperatorAccess().getURSHIFTASSIGNEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_11, grammarAccess.getAssignmentOperatorAccess().getURSHIFTASSIGNEnumLiteralDeclaration_11()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignmentOperator"

    // Delegated rules


    protected DFA40 dfa40 = new DFA40(this);
    static final String DFA40_eotS =
        "\13\uffff";
    static final String DFA40_eofS =
        "\3\uffff\1\12\7\uffff";
    static final String DFA40_minS =
        "\1\61\3\6\7\uffff";
    static final String DFA40_maxS =
        "\1\64\1\133\1\142\1\161\7\uffff";
    static final String DFA40_acceptS =
        "\4\uffff\1\3\1\7\1\5\1\6\1\4\1\1\1\2";
    static final String DFA40_specialS =
        "\13\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\1\2\uffff\1\2",
            "\1\3\70\uffff\1\5\31\uffff\3\4",
            "\1\10\56\uffff\1\6\1\uffff\1\7\44\uffff\7\7",
            "\1\12\5\uffff\5\12\3\uffff\3\12\1\uffff\1\11\25\12\3\uffff"+
            "\4\12\10\uffff\2\12\5\uffff\1\12\1\uffff\1\12\16\uffff\2\12"+
            "\20\uffff\13\12",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA40_eot = DFA.unpackEncodedString(DFA40_eotS);
    static final short[] DFA40_eof = DFA.unpackEncodedString(DFA40_eofS);
    static final char[] DFA40_min = DFA.unpackEncodedStringToUnsignedChars(DFA40_minS);
    static final char[] DFA40_max = DFA.unpackEncodedStringToUnsignedChars(DFA40_maxS);
    static final short[] DFA40_accept = DFA.unpackEncodedString(DFA40_acceptS);
    static final short[] DFA40_special = DFA.unpackEncodedString(DFA40_specialS);
    static final short[][] DFA40_transition;

    static {
        int numStates = DFA40_transitionS.length;
        DFA40_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA40_transition[i] = DFA.unpackEncodedString(DFA40_transitionS[i]);
        }
    }

    class DFA40 extends DFA {

        public DFA40(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 40;
            this.eot = DFA40_eot;
            this.eof = DFA40_eof;
            this.min = DFA40_min;
            this.max = DFA40_max;
            this.accept = DFA40_accept;
            this.special = DFA40_special;
            this.transition = DFA40_transition;
        }
        public String getDescription() {
            return "2198:1: (this_OperationCallExpression_0= ruleOperationCallExpression | this_PropertyCallExpression_1= rulePropertyCallExpression | this_LinkOperationExpression_2= ruleLinkOperationExpression | this_SequenceOperationExpression_3= ruleSequenceOperationExpression | this_SequenceReductionExpression_4= ruleSequenceReductionExpression | this_SequenceExpansionExpression_5= ruleSequenceExpansionExpression | this_ClassExtentExpression_6= ruleClassExtentExpression )";
        }
    }
 

    public static final BitSet FOLLOW_ruleTest_in_entryRuleTest75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTest85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleTest123 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleTest144 = new BitSet(new long[]{0x000000000000F000L});
    public static final BitSet FOLLOW_13_in_ruleTest159 = new BitSet(new long[]{0x0000000000000000L,0x0003FF8000000040L});
    public static final BitSet FOLLOW_ruleAssignmentCompletion_in_ruleTest180 = new BitSet(new long[]{0x000000000000E000L});
    public static final BitSet FOLLOW_14_in_ruleTest195 = new BitSet(new long[]{0x1E00000000060040L,0x00000000001EE4B9L});
    public static final BitSet FOLLOW_ruleStatement_in_ruleTest216 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_15_in_ruleTest231 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ruleBlock_in_ruleTest252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLITERAL_in_entryRuleLITERAL289 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLITERAL299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBOOLEAN_LITERAL_in_ruleLITERAL346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNUMBER_LITERAL_in_ruleLITERAL373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSTRING_LITERAL_in_ruleLITERAL400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBOOLEAN_LITERAL_in_entryRuleBOOLEAN_LITERAL435 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBOOLEAN_LITERAL445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanValue_in_ruleBOOLEAN_LITERAL490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNUMBER_LITERAL_in_entryRuleNUMBER_LITERAL525 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNUMBER_LITERAL535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleINTEGER_LITERAL_in_ruleNUMBER_LITERAL582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUNLIMITED_LITERAL_in_ruleNUMBER_LITERAL609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleINTEGER_LITERAL_in_entryRuleINTEGER_LITERAL644 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleINTEGER_LITERAL654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INTEGERVALUE_in_ruleINTEGER_LITERAL695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUNLIMITED_LITERAL_in_entryRuleUNLIMITED_LITERAL735 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUNLIMITED_LITERAL745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleUNLIMITED_LITERAL787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSTRING_LITERAL_in_entryRuleSTRING_LITERAL835 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSTRING_LITERAL845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSTRING_LITERAL886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNameExpression_in_entryRuleNameExpression926 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNameExpression936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleNameExpression983 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_18_in_ruleNameExpression1012 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNamePath_in_ruleNameExpression1049 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNameExpression1067 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedNamePath_in_ruleNameExpression1101 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNameExpression1119 = new BitSet(new long[]{0x1016000001060002L});
    public static final BitSet FOLLOW_ruleTuple_in_ruleNameExpression1146 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSequenceConstructionOrAccessCompletion_in_ruleNameExpression1173 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_17_in_ruleNameExpression1199 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_18_in_ruleNameExpression1228 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleNameExpression1269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedNamePath_in_entryRuleQualifiedNamePath1306 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedNamePath1316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnqualifiedName_in_ruleQualifiedNamePath1362 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleQualifiedNamePath1374 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_ruleUnqualifiedName_in_entryRuleUnqualifiedName1411 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnqualifiedName1421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleUnqualifiedName1463 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_ruleTemplateBinding_in_ruleUnqualifiedName1489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTemplateBinding_in_entryRuleTemplateBinding1526 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTemplateBinding1536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleTemplateBinding1573 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleNamedTemplateBinding_in_ruleTemplateBinding1594 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_21_in_ruleTemplateBinding1607 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleNamedTemplateBinding_in_ruleTemplateBinding1628 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_22_in_ruleTemplateBinding1642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamedTemplateBinding_in_entryRuleNamedTemplateBinding1678 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamedTemplateBinding1688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNamedTemplateBinding1730 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleNamedTemplateBinding1747 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_ruleNamedTemplateBinding1768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_entryRuleQualifiedNameWithBinding1804 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedNameWithBinding1814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedNameWithBinding1856 = new BitSet(new long[]{0x0000000000180002L});
    public static final BitSet FOLLOW_ruleTemplateBinding_in_ruleQualifiedNameWithBinding1882 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_ruleQualifiedNameWithBinding1896 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_ruleQualifiedNameWithBinding1917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTuple_in_entryRuleTuple1955 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTuple1965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleTuple2011 = new BitSet(new long[]{0x0F01CC0003070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleTupleElement_in_ruleTuple2033 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_21_in_ruleTuple2046 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleTupleElement_in_ruleTuple2067 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_25_in_ruleTuple2083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTupleElement_in_entryRuleTupleElement2119 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTupleElement2129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleTupleElement2174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression2209 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression2219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConditionalTestExpression_in_ruleExpression2265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConditionalTestExpression_in_entryRuleConditionalTestExpression2299 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConditionalTestExpression2309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConditionalOrExpression_in_ruleConditionalTestExpression2355 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_26_in_ruleConditionalTestExpression2368 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleConditionalTestExpression_in_ruleConditionalTestExpression2389 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleConditionalTestExpression2401 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleConditionalTestExpression_in_ruleConditionalTestExpression2422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConditionalOrExpression_in_entryRuleConditionalOrExpression2460 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConditionalOrExpression2470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConditionalAndExpression_in_ruleConditionalOrExpression2516 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_ruleConditionalOrExpression2529 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleConditionalAndExpression_in_ruleConditionalOrExpression2550 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_ruleConditionalAndExpression_in_entryRuleConditionalAndExpression2588 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConditionalAndExpression2598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInclusiveOrExpression_in_ruleConditionalAndExpression2644 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_ruleConditionalAndExpression2657 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleInclusiveOrExpression_in_ruleConditionalAndExpression2678 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ruleInclusiveOrExpression_in_entryRuleInclusiveOrExpression2716 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInclusiveOrExpression2726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExclusiveOrExpression_in_ruleInclusiveOrExpression2772 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_ruleInclusiveOrExpression2785 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExclusiveOrExpression_in_ruleInclusiveOrExpression2806 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ruleExclusiveOrExpression_in_entryRuleExclusiveOrExpression2844 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExclusiveOrExpression2854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleExclusiveOrExpression2900 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_31_in_ruleExclusiveOrExpression2913 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleExclusiveOrExpression2934 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_entryRuleAndExpression2972 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpression2982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEqualityExpression_in_ruleAndExpression3028 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_32_in_ruleAndExpression3041 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleEqualityExpression_in_ruleAndExpression3062 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_ruleEqualityExpression_in_entryRuleEqualityExpression3100 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEqualityExpression3110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassificationExpression_in_ruleEqualityExpression3156 = new BitSet(new long[]{0x0000000600000002L});
    public static final BitSet FOLLOW_33_in_ruleEqualityExpression3177 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_34_in_ruleEqualityExpression3206 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleClassificationExpression_in_ruleEqualityExpression3243 = new BitSet(new long[]{0x0000000600000002L});
    public static final BitSet FOLLOW_ruleClassificationExpression_in_entryRuleClassificationExpression3281 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClassificationExpression3291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleClassificationExpression3337 = new BitSet(new long[]{0x0000001800000002L});
    public static final BitSet FOLLOW_35_in_ruleClassificationExpression3358 = new BitSet(new long[]{0x0000000000060040L});
    public static final BitSet FOLLOW_36_in_ruleClassificationExpression3387 = new BitSet(new long[]{0x0000000000060040L});
    public static final BitSet FOLLOW_ruleNameExpression_in_ruleClassificationExpression3424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression3462 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpression3472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleShiftExpression_in_ruleRelationalExpression3518 = new BitSet(new long[]{0x0000006000500002L});
    public static final BitSet FOLLOW_20_in_ruleRelationalExpression3539 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_22_in_ruleRelationalExpression3568 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_37_in_ruleRelationalExpression3597 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_38_in_ruleRelationalExpression3626 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleShiftExpression_in_ruleRelationalExpression3663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleShiftExpression_in_entryRuleShiftExpression3701 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleShiftExpression3711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleShiftExpression3757 = new BitSet(new long[]{0x0000038000000002L});
    public static final BitSet FOLLOW_39_in_ruleShiftExpression3778 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_40_in_ruleShiftExpression3807 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_41_in_ruleShiftExpression3836 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleShiftExpression3873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression3911 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdditiveExpression3921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression3967 = new BitSet(new long[]{0x00000C0000000002L});
    public static final BitSet FOLLOW_42_in_ruleAdditiveExpression3988 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_43_in_ruleAdditiveExpression4017 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4054 = new BitSet(new long[]{0x00000C0000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression4092 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicativeExpression4102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleMultiplicativeExpression4148 = new BitSet(new long[]{0x0000300000010002L});
    public static final BitSet FOLLOW_16_in_ruleMultiplicativeExpression4169 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_44_in_ruleMultiplicativeExpression4198 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_45_in_ruleMultiplicativeExpression4227 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleMultiplicativeExpression4264 = new BitSet(new long[]{0x0000300000010002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression4302 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpression4312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleUnaryExpression4357 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_43_in_ruleUnaryExpression4386 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_42_in_ruleUnaryExpression4415 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_47_in_ruleUnaryExpression4444 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_48_in_ruleUnaryExpression4473 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_ruleUnaryExpression4511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression4547 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimaryExpression4557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValueSpecification_in_rulePrimaryExpression4602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_entryRuleSuffixExpression4637 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSuffixExpression4647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCallExpression_in_ruleSuffixExpression4694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyCallExpression_in_ruleSuffixExpression4721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLinkOperationExpression_in_ruleSuffixExpression4748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceOperationExpression_in_ruleSuffixExpression4775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceReductionExpression_in_ruleSuffixExpression4802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceExpansionExpression_in_ruleSuffixExpression4829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassExtentExpression_in_ruleSuffixExpression4856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCallExpression_in_entryRuleOperationCallExpression4891 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperationCallExpression4901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleOperationCallExpression4938 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOperationCallExpression4955 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleTuple_in_ruleOperationCallExpression4981 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleOperationCallExpression5002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyCallExpression_in_entryRulePropertyCallExpression5039 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyCallExpression5049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_rulePropertyCallExpression5086 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_rulePropertyCallExpression5103 = new BitSet(new long[]{0x0016000000000002L});
    public static final BitSet FOLLOW_50_in_rulePropertyCallExpression5121 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_rulePropertyCallExpression5142 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_rulePropertyCallExpression5154 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_rulePropertyCallExpression5177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLinkOperationExpression_in_entryRuleLinkOperationExpression5214 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLinkOperationExpression5224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleLinkOperationExpression5261 = new BitSet(new long[]{0x0000000000000000L,0x000000000E000000L});
    public static final BitSet FOLLOW_ruleLinkOperationKind_in_ruleLinkOperationExpression5282 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleLinkOperationTuple_in_ruleLinkOperationExpression5303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLinkOperationTuple_in_entryRuleLinkOperationTuple5339 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLinkOperationTuple5349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleLinkOperationTuple5386 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleLinkOperationTupleElement_in_ruleLinkOperationTuple5407 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_21_in_ruleLinkOperationTuple5420 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleLinkOperationTupleElement_in_ruleLinkOperationTuple5441 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_25_in_ruleLinkOperationTuple5455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLinkOperationTupleElement_in_entryRuleLinkOperationTupleElement5491 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLinkOperationTupleElement5501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLinkOperationTupleElement5543 = new BitSet(new long[]{0x0004000000800000L});
    public static final BitSet FOLLOW_50_in_ruleLinkOperationTupleElement5561 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLinkOperationTupleElement5582 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleLinkOperationTupleElement5594 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleLinkOperationTupleElement5608 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLinkOperationTupleElement5629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceOperationExpression_in_entryRuleSequenceOperationExpression5665 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceOperationExpression5675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleSequenceOperationExpression5712 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_ruleSequenceOperationExpression5733 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleTuple_in_ruleSequenceOperationExpression5754 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleSequenceOperationExpression5775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceReductionExpression_in_entryRuleSequenceReductionExpression5812 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceReductionExpression5822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleSequenceReductionExpression5859 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_ruleSequenceReductionExpression5871 = new BitSet(new long[]{0x0040000000000040L});
    public static final BitSet FOLLOW_54_in_ruleSequenceReductionExpression5889 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_ruleSequenceReductionExpression5924 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleSequenceReductionExpression5945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceExpansionExpression_in_entryRuleSequenceExpansionExpression5982 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceExpansionExpression5992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectOrRejectOperation_in_ruleSequenceExpansionExpression6039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectOrIterateOperation_in_ruleSequenceExpansionExpression6066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForAllOrExistsOrOneOperation_in_ruleSequenceExpansionExpression6093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIsUniqueOperation_in_ruleSequenceExpansionExpression6120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSelectOrRejectOperation_in_entryRuleSelectOrRejectOperation6155 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSelectOrRejectOperation6165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleSelectOrRejectOperation6202 = new BitSet(new long[]{0x0000000000000000L,0x0000000030000000L});
    public static final BitSet FOLLOW_ruleSelectOrRejectOperator_in_ruleSelectOrRejectOperation6223 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSelectOrRejectOperation6240 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleSelectOrRejectOperation6257 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSelectOrRejectOperation6278 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleSelectOrRejectOperation6290 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleSelectOrRejectOperation6311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectOrIterateOperation_in_entryRuleCollectOrIterateOperation6348 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectOrIterateOperation6358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleCollectOrIterateOperation6395 = new BitSet(new long[]{0x0000000000000000L,0x00000000C0000000L});
    public static final BitSet FOLLOW_ruleCollectOrIterateOperator_in_ruleCollectOrIterateOperation6416 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleCollectOrIterateOperation6433 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleCollectOrIterateOperation6450 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCollectOrIterateOperation6471 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleCollectOrIterateOperation6483 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleCollectOrIterateOperation6504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForAllOrExistsOrOneOperation_in_entryRuleForAllOrExistsOrOneOperation6541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleForAllOrExistsOrOneOperation6551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleForAllOrExistsOrOneOperation6588 = new BitSet(new long[]{0x0000000000000000L,0x0000000700000000L});
    public static final BitSet FOLLOW_ruleForAllOrExistsOrOneOperator_in_ruleForAllOrExistsOrOneOperation6609 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleForAllOrExistsOrOneOperation6626 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleForAllOrExistsOrOneOperation6643 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleForAllOrExistsOrOneOperation6664 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleForAllOrExistsOrOneOperation6676 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleForAllOrExistsOrOneOperation6697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIsUniqueOperation_in_entryRuleIsUniqueOperation6734 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIsUniqueOperation6744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleIsUniqueOperation6781 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleIsUniqueOperation6793 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIsUniqueOperation6810 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleIsUniqueOperation6827 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleIsUniqueOperation6848 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleIsUniqueOperation6860 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleIsUniqueOperation6881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleValueSpecification_in_entryRuleValueSpecification6918 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleValueSpecification6928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNameExpression_in_ruleValueSpecification6975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLITERAL_in_ruleValueSpecification7002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleThisExpression_in_ruleValueSpecification7029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuperInvocationExpression_in_ruleValueSpecification7056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstanceCreationExpression_in_ruleValueSpecification7083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParenthesizedExpression_in_ruleValueSpecification7110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullExpression_in_ruleValueSpecification7137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNonLiteralValueSpecification_in_entryRuleNonLiteralValueSpecification7172 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNonLiteralValueSpecification7182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNameExpression_in_ruleNonLiteralValueSpecification7229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParenthesizedExpression_in_ruleNonLiteralValueSpecification7256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstanceCreationExpression_in_ruleNonLiteralValueSpecification7283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleThisExpression_in_ruleNonLiteralValueSpecification7310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuperInvocationExpression_in_ruleNonLiteralValueSpecification7337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParenthesizedExpression_in_entryRuleParenthesizedExpression7372 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParenthesizedExpression7382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleParenthesizedExpression7419 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParenthesizedExpression7440 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleParenthesizedExpression7452 = new BitSet(new long[]{0x0E12000001060042L});
    public static final BitSet FOLLOW_ruleNonLiteralValueSpecification_in_ruleParenthesizedExpression7474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleParenthesizedExpression7501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullExpression_in_entryRuleNullExpression7539 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullExpression7549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ruleNullExpression7595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleThisExpression_in_entryRuleThisExpression7631 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleThisExpression7641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ruleThisExpression7687 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleThisExpression7708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuperInvocationExpression_in_entryRuleSuperInvocationExpression7745 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSuperInvocationExpression7755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ruleSuperInvocationExpression7792 = new BitSet(new long[]{0x0002000001000000L});
    public static final BitSet FOLLOW_ruleTuple_in_ruleSuperInvocationExpression7814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleSuperInvocationExpression7833 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_ruleSuperInvocationExpression7854 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleTuple_in_ruleSuperInvocationExpression7875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstanceCreationExpression_in_entryRuleInstanceCreationExpression7913 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInstanceCreationExpression7923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ruleInstanceCreationExpression7960 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_ruleInstanceCreationExpression7981 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleInstanceCreationTuple_in_ruleInstanceCreationExpression8002 = new BitSet(new long[]{0x0012000000000002L});
    public static final BitSet FOLLOW_ruleSuffixExpression_in_ruleInstanceCreationExpression8023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstanceCreationTuple_in_entryRuleInstanceCreationTuple8060 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInstanceCreationTuple8070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleInstanceCreationTuple8116 = new BitSet(new long[]{0x0000000002000040L});
    public static final BitSet FOLLOW_ruleInstanceCreationTupleElement_in_ruleInstanceCreationTuple8138 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_21_in_ruleInstanceCreationTuple8151 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleInstanceCreationTupleElement_in_ruleInstanceCreationTuple8172 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_25_in_ruleInstanceCreationTuple8188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstanceCreationTupleElement_in_entryRuleInstanceCreationTupleElement8224 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInstanceCreationTupleElement8234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInstanceCreationTupleElement8276 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleInstanceCreationTupleElement8293 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInstanceCreationTupleElement8314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceConstructionOrAccessCompletion_in_entryRuleSequenceConstructionOrAccessCompletion8350 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceConstructionOrAccessCompletion8360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleSequenceConstructionOrAccessCompletion8404 = new BitSet(new long[]{0x0F09CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleAccessCompletion_in_ruleSequenceConstructionOrAccessCompletion8439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartialSequenceConstructionCompletion_in_ruleSequenceConstructionOrAccessCompletion8466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceConstructionExpression_in_ruleSequenceConstructionOrAccessCompletion8495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAccessCompletion_in_entryRuleAccessCompletion8531 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAccessCompletion8541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleAccessCompletion8587 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleAccessCompletion8599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartialSequenceConstructionCompletion_in_entryRulePartialSequenceConstructionCompletion8635 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePartialSequenceConstructionCompletion8645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_rulePartialSequenceConstructionCompletion8682 = new BitSet(new long[]{0x1004000000000000L});
    public static final BitSet FOLLOW_ruleSequenceConstructionExpression_in_rulePartialSequenceConstructionCompletion8703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceConstructionExpression_in_entryRuleSequenceConstructionExpression8739 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceConstructionExpression8749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleSequenceConstructionExpression8786 = new BitSet(new long[]{0x1F05CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleSequenceElement_in_ruleSequenceConstructionExpression8807 = new BitSet(new long[]{0x6000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleSequenceConstructionExpression8821 = new BitSet(new long[]{0x1F05CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleSequenceElement_in_ruleSequenceConstructionExpression8842 = new BitSet(new long[]{0x4000000000200000L});
    public static final BitSet FOLLOW_61_in_ruleSequenceConstructionExpression8863 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSequenceConstructionExpression8884 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_ruleSequenceConstructionExpression8898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceElement_in_entryRuleSequenceElement8934 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequenceElement8944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSequenceElement8991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequenceConstructionExpression_in_ruleSequenceElement9018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassExtentExpression_in_entryRuleClassExtentExpression9053 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClassExtentExpression9063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleClassExtentExpression9109 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_ruleClassExtentExpression9121 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleClassExtentExpression9133 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleClassExtentExpression9145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBlock_in_entryRuleBlock9181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBlock9191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleBlock9228 = new BitSet(new long[]{0x5E000000000601C0L,0x00000000001EE4B9L});
    public static final BitSet FOLLOW_ruleStatementSequence_in_ruleBlock9258 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_ruleBlock9271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStatementSequence_in_entryRuleStatementSequence9307 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStatementSequence9317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDocumentedStatement_in_ruleStatementSequence9362 = new BitSet(new long[]{0x1E000000000601C2L,0x00000000001EE4B9L});
    public static final BitSet FOLLOW_ruleDocumentedStatement_in_entryRuleDocumentedStatement9398 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDocumentedStatement9408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ML_COMMENT_in_ruleDocumentedStatement9452 = new BitSet(new long[]{0x1E00000000060040L,0x00000000001EE4B9L});
    public static final BitSet FOLLOW_RULE_SL_COMMENT_in_ruleDocumentedStatement9472 = new BitSet(new long[]{0x1E00000000060040L,0x00000000001EE4B9L});
    public static final BitSet FOLLOW_ruleStatement_in_ruleDocumentedStatement9502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInlineStatement_in_entryRuleInlineStatement9538 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInlineStatement9548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_ruleInlineStatement9585 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleInlineStatement9597 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleInlineStatement9609 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInlineStatement9626 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleInlineStatement9643 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleInlineStatement9660 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_ruleInlineStatement9677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnnotatedStatement_in_entryRuleAnnotatedStatement9713 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnnotatedStatement9723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_ruleAnnotatedStatement9760 = new BitSet(new long[]{0x0000000000000000L,0x0000007800000000L});
    public static final BitSet FOLLOW_ruleAnnotation_in_ruleAnnotatedStatement9781 = new BitSet(new long[]{0x1E00000000060040L,0x00000000001EE4B9L});
    public static final BitSet FOLLOW_ruleStatement_in_ruleAnnotatedStatement9802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStatement_in_entryRuleStatement9838 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStatement9848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnnotatedStatement_in_ruleStatement9895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInlineStatement_in_ruleStatement9922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBlockStatement_in_ruleStatement9949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEmptyStatement_in_ruleStatement9976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalNameDeclarationStatement_in_ruleStatement10003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfStatement_in_ruleStatement10030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchStatement_in_ruleStatement10057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWhileStatement_in_ruleStatement10084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoStatement_in_ruleStatement10111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForStatement_in_ruleStatement10138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBreakStatement_in_ruleStatement10165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReturnStatement_in_ruleStatement10192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAcceptStatement_in_ruleStatement10219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassifyStatement_in_ruleStatement10246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInvocationOrAssignementOrDeclarationStatement_in_ruleStatement10273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuperInvocationStatement_in_ruleStatement10300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleThisInvocationStatement_in_ruleStatement10327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstanceCreationInvocationStatement_in_ruleStatement10354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnnotation_in_entryRuleAnnotation10389 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnnotation10399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnnotationKind_in_ruleAnnotation10445 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_ruleAnnotation10458 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAnnotation10475 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_21_in_ruleAnnotation10493 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAnnotation10510 = new BitSet(new long[]{0x0000000002200000L});
    public static final BitSet FOLLOW_25_in_ruleAnnotation10529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBlockStatement_in_entryRuleBlockStatement10567 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBlockStatement10577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBlock_in_ruleBlockStatement10622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEmptyStatement_in_entryRuleEmptyStatement10657 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEmptyStatement10667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleEmptyStatement10713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalNameDeclarationStatement_in_entryRuleLocalNameDeclarationStatement10749 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalNameDeclarationStatement10759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_ruleLocalNameDeclarationStatement10796 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLocalNameDeclarationStatement10813 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleLocalNameDeclarationStatement10830 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_ruleLocalNameDeclarationStatement10851 = new BitSet(new long[]{0x0004000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_50_in_ruleLocalNameDeclarationStatement10870 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleLocalNameDeclarationStatement10895 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_ruleLocalNameDeclarationStatement10909 = new BitSet(new long[]{0x1F05CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleSequenceElement_in_ruleLocalNameDeclarationStatement10930 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleLocalNameDeclarationStatement10942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfStatement_in_entryRuleIfStatement10978 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfStatement10988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_ruleIfStatement11025 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleSequentialClauses_in_ruleIfStatement11046 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_ruleFinalClause_in_ruleIfStatement11067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSequentialClauses_in_entryRuleSequentialClauses11104 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSequentialClauses11114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConcurrentClauses_in_ruleSequentialClauses11160 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_ruleSequentialClauses11173 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_ruleSequentialClauses11185 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleConcurrentClauses_in_ruleSequentialClauses11206 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_ruleConcurrentClauses_in_entryRuleConcurrentClauses11244 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConcurrentClauses11254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNonFinalClause_in_ruleConcurrentClauses11300 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_ruleConcurrentClauses11313 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_ruleConcurrentClauses11325 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ruleNonFinalClause_in_ruleConcurrentClauses11346 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleNonFinalClause_in_entryRuleNonFinalClause11384 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNonFinalClause11394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleNonFinalClause11431 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleNonFinalClause11452 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleNonFinalClause11464 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ruleBlock_in_ruleNonFinalClause11485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFinalClause_in_entryRuleFinalClause11521 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFinalClause11531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_ruleFinalClause11568 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ruleBlock_in_ruleFinalClause11589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchStatement_in_entryRuleSwitchStatement11625 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchStatement11635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ruleSwitchStatement11672 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleSwitchStatement11684 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSwitchStatement11705 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleSwitchStatement11717 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_ruleSwitchStatement11729 = new BitSet(new long[]{0x4000000000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_ruleSwitchClause_in_ruleSwitchStatement11750 = new BitSet(new long[]{0x4000000000000000L,0x0000000000001800L});
    public static final BitSet FOLLOW_ruleSwitchDefaultClause_in_ruleSwitchStatement11772 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_ruleSwitchStatement11785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchClause_in_entryRuleSwitchClause11821 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchClause11831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchCase_in_ruleSwitchClause11877 = new BitSet(new long[]{0x1E000000000601C0L,0x00000000001EECB9L});
    public static final BitSet FOLLOW_ruleSwitchCase_in_ruleSwitchClause11898 = new BitSet(new long[]{0x1E000000000601C0L,0x00000000001EECB9L});
    public static final BitSet FOLLOW_ruleNonEmptyStatementSequence_in_ruleSwitchClause11920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchCase_in_entryRuleSwitchCase11956 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchCase11966 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleSwitchCase12003 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSwitchCase12024 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleSwitchCase12036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchDefaultClause_in_entryRuleSwitchDefaultClause12072 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchDefaultClause12082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleSwitchDefaultClause12119 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleSwitchDefaultClause12131 = new BitSet(new long[]{0x1E000000000601C0L,0x00000000001EECB9L});
    public static final BitSet FOLLOW_ruleNonEmptyStatementSequence_in_ruleSwitchDefaultClause12152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNonEmptyStatementSequence_in_entryRuleNonEmptyStatementSequence12188 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNonEmptyStatementSequence12198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDocumentedStatement_in_ruleNonEmptyStatementSequence12243 = new BitSet(new long[]{0x1E000000000601C2L,0x00000000001EE4B9L});
    public static final BitSet FOLLOW_ruleWhileStatement_in_entryRuleWhileStatement12279 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWhileStatement12289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleWhileStatement12326 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleWhileStatement12338 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleWhileStatement12359 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleWhileStatement12371 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ruleBlock_in_ruleWhileStatement12392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDoStatement_in_entryRuleDoStatement12428 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDoStatement12438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleDoStatement12475 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ruleBlock_in_ruleDoStatement12496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_ruleDoStatement12508 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleDoStatement12520 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleDoStatement12541 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleDoStatement12553 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleDoStatement12565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForStatement_in_entryRuleForStatement12601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleForStatement12611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleForStatement12648 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleForStatement12660 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleForControl_in_ruleForStatement12681 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleForStatement12693 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ruleBlock_in_ruleForStatement12714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForControl_in_entryRuleForControl12750 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleForControl12760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLoopVariableDefinition_in_ruleForControl12806 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleForControl12819 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleLoopVariableDefinition_in_ruleForControl12840 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_ruleLoopVariableDefinition_in_entryRuleLoopVariableDefinition12878 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLoopVariableDefinition12888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLoopVariableDefinition12931 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_ruleLoopVariableDefinition12948 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLoopVariableDefinition12969 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleLoopVariableDefinition12982 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLoopVariableDefinition13003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_ruleLoopVariableDefinition13034 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLoopVariableDefinition13051 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleLoopVariableDefinition13068 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLoopVariableDefinition13089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBreakStatement_in_entryRuleBreakStatement13126 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBreakStatement13136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_ruleBreakStatement13182 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleBreakStatement13194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReturnStatement_in_entryRuleReturnStatement13230 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReturnStatement13240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_ruleReturnStatement13277 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleReturnStatement13298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleReturnStatement13310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAcceptStatement_in_entryRuleAcceptStatement13346 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAcceptStatement13356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAcceptClause_in_ruleAcceptStatement13402 = new BitSet(new long[]{0x1000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_ruleSimpleAcceptStatementCompletion_in_ruleAcceptStatement13424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundAcceptStatementCompletion_in_ruleAcceptStatement13451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleAcceptStatementCompletion_in_entryRuleSimpleAcceptStatementCompletion13488 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleAcceptStatementCompletion13498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleSimpleAcceptStatementCompletion13544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCompoundAcceptStatementCompletion_in_entryRuleCompoundAcceptStatementCompletion13580 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCompoundAcceptStatementCompletion13590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBlock_in_ruleCompoundAcceptStatementCompletion13636 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_ruleCompoundAcceptStatementCompletion13649 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_ruleAcceptBlock_in_ruleCompoundAcceptStatementCompletion13670 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_ruleAcceptBlock_in_entryRuleAcceptBlock13708 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAcceptBlock13718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAcceptClause_in_ruleAcceptBlock13764 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ruleBlock_in_ruleAcceptBlock13785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAcceptClause_in_entryRuleAcceptClause13821 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAcceptClause13831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_ruleAcceptClause13868 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleAcceptClause13880 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAcceptClause13898 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleAcceptClause13915 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameList_in_ruleAcceptClause13938 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleAcceptClause13950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassifyStatement_in_entryRuleClassifyStatement13986 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClassifyStatement13996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_ruleClassifyStatement14033 = new BitSet(new long[]{0x0F01CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleClassifyStatement14054 = new BitSet(new long[]{0x0000000000000000L,0x0000000000600000L});
    public static final BitSet FOLLOW_ruleClassificationClause_in_ruleClassifyStatement14075 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleClassifyStatement14087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassificationClause_in_entryRuleClassificationClause14123 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClassificationClause14133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassificationFromClause_in_ruleClassificationClause14180 = new BitSet(new long[]{0x0000000000000002L,0x0000000000600000L});
    public static final BitSet FOLLOW_ruleClassificationToClause_in_ruleClassificationClause14201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReclassifyAllClause_in_ruleClassificationClause14231 = new BitSet(new long[]{0x0000000000000000L,0x0000000000600000L});
    public static final BitSet FOLLOW_ruleClassificationToClause_in_ruleClassificationClause14253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassificationFromClause_in_entryRuleClassificationFromClause14290 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClassificationFromClause14300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_ruleClassificationFromClause14337 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameList_in_ruleClassificationFromClause14358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassificationToClause_in_entryRuleClassificationToClause14394 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClassificationToClause14404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_ruleClassificationToClause14441 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameList_in_ruleClassificationToClause14462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReclassifyAllClause_in_entryRuleReclassifyAllClause14498 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReclassifyAllClause14508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_ruleReclassifyAllClause14554 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleReclassifyAllClause14566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedNameList_in_entryRuleQualifiedNameList14602 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedNameList14612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_ruleQualifiedNameList14658 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleQualifiedNameList14671 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleQualifiedNameWithBinding_in_ruleQualifiedNameList14692 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_ruleInvocationOrAssignementOrDeclarationStatement_in_entryRuleInvocationOrAssignementOrDeclarationStatement14730 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInvocationOrAssignementOrDeclarationStatement14740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNameExpression_in_ruleInvocationOrAssignementOrDeclarationStatement14786 = new BitSet(new long[]{0x0004000000000040L,0x0003FF8000000050L});
    public static final BitSet FOLLOW_ruleVariableDeclarationCompletion_in_ruleInvocationOrAssignementOrDeclarationStatement14808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAssignmentCompletion_in_ruleInvocationOrAssignementOrDeclarationStatement14835 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleInvocationOrAssignementOrDeclarationStatement14849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuperInvocationStatement_in_entryRuleSuperInvocationStatement14885 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSuperInvocationStatement14895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuperInvocationExpression_in_ruleSuperInvocationStatement14941 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleSuperInvocationStatement14953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleThisInvocationStatement_in_entryRuleThisInvocationStatement14989 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleThisInvocationStatement14999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleThisExpression_in_ruleThisInvocationStatement15045 = new BitSet(new long[]{0x0000000000000000L,0x0003FF8000000050L});
    public static final BitSet FOLLOW_ruleAssignmentCompletion_in_ruleThisInvocationStatement15066 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleThisInvocationStatement15079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstanceCreationInvocationStatement_in_entryRuleInstanceCreationInvocationStatement15115 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInstanceCreationInvocationStatement15125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstanceCreationExpression_in_ruleInstanceCreationInvocationStatement15171 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleInstanceCreationInvocationStatement15183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVariableDeclarationCompletion_in_entryRuleVariableDeclarationCompletion15219 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVariableDeclarationCompletion15229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleVariableDeclarationCompletion15273 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleVariableDeclarationCompletion15298 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleVariableDeclarationCompletion15317 = new BitSet(new long[]{0x0000000000000000L,0x0003FF8000000040L});
    public static final BitSet FOLLOW_ruleAssignmentCompletion_in_ruleVariableDeclarationCompletion15343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAssignmentCompletion_in_entryRuleAssignmentCompletion15379 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAssignmentCompletion15389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAssignmentOperator_in_ruleAssignmentCompletion15435 = new BitSet(new long[]{0x1F05CC0001070070L,0x0000000001800000L});
    public static final BitSet FOLLOW_ruleSequenceElement_in_ruleAssignmentCompletion15456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_ruleBooleanValue15506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_ruleBooleanValue15523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_ruleLinkOperationKind15568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_ruleLinkOperationKind15585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_ruleLinkOperationKind15602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_ruleSelectOrRejectOperator15647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_ruleSelectOrRejectOperator15664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_ruleCollectOrIterateOperator15709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_ruleCollectOrIterateOperator15726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_ruleForAllOrExistsOrOneOperator15771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_ruleForAllOrExistsOrOneOperator15788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_ruleForAllOrExistsOrOneOperator15805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_ruleAnnotationKind15850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_ruleAnnotationKind15867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_101_in_ruleAnnotationKind15884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_102_in_ruleAnnotationKind15901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_ruleAssignmentOperator15946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_ruleAssignmentOperator15963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_104_in_ruleAssignmentOperator15980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_105_in_ruleAssignmentOperator15997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_ruleAssignmentOperator16014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_107_in_ruleAssignmentOperator16031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_108_in_ruleAssignmentOperator16048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_109_in_ruleAssignmentOperator16065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_110_in_ruleAssignmentOperator16082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_111_in_ruleAssignmentOperator16099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_112_in_ruleAssignmentOperator16116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_ruleAssignmentOperator16133 = new BitSet(new long[]{0x0000000000000002L});

}