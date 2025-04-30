# Generated from PythonParser.g4 by ANTLR 4.13.2
from antlr4 import *
if "." in __name__:
    from .PythonParser import PythonParser
else:
    from PythonParser import PythonParser

# This class defines a complete listener for a parse tree produced by PythonParser.
class PythonParserListener(ParseTreeListener):

    # Enter a parse tree produced by PythonParser#single_input.
    def enterSingle_input(self, ctx:PythonParser.Single_inputContext):
        pass

    # Exit a parse tree produced by PythonParser#single_input.
    def exitSingle_input(self, ctx:PythonParser.Single_inputContext):
        pass


    # Enter a parse tree produced by PythonParser#file_input.
    def enterFile_input(self, ctx:PythonParser.File_inputContext):
        pass

    # Exit a parse tree produced by PythonParser#file_input.
    def exitFile_input(self, ctx:PythonParser.File_inputContext):
        pass


    # Enter a parse tree produced by PythonParser#eval_input.
    def enterEval_input(self, ctx:PythonParser.Eval_inputContext):
        pass

    # Exit a parse tree produced by PythonParser#eval_input.
    def exitEval_input(self, ctx:PythonParser.Eval_inputContext):
        pass


    # Enter a parse tree produced by PythonParser#decorator.
    def enterDecorator(self, ctx:PythonParser.DecoratorContext):
        pass

    # Exit a parse tree produced by PythonParser#decorator.
    def exitDecorator(self, ctx:PythonParser.DecoratorContext):
        pass


    # Enter a parse tree produced by PythonParser#decorators.
    def enterDecorators(self, ctx:PythonParser.DecoratorsContext):
        pass

    # Exit a parse tree produced by PythonParser#decorators.
    def exitDecorators(self, ctx:PythonParser.DecoratorsContext):
        pass


    # Enter a parse tree produced by PythonParser#decorated.
    def enterDecorated(self, ctx:PythonParser.DecoratedContext):
        pass

    # Exit a parse tree produced by PythonParser#decorated.
    def exitDecorated(self, ctx:PythonParser.DecoratedContext):
        pass


    # Enter a parse tree produced by PythonParser#funcdef.
    def enterFuncdef(self, ctx:PythonParser.FuncdefContext):
        pass

    # Exit a parse tree produced by PythonParser#funcdef.
    def exitFuncdef(self, ctx:PythonParser.FuncdefContext):
        pass


    # Enter a parse tree produced by PythonParser#parameters.
    def enterParameters(self, ctx:PythonParser.ParametersContext):
        pass

    # Exit a parse tree produced by PythonParser#parameters.
    def exitParameters(self, ctx:PythonParser.ParametersContext):
        pass


    # Enter a parse tree produced by PythonParser#varargslist.
    def enterVarargslist(self, ctx:PythonParser.VarargslistContext):
        pass

    # Exit a parse tree produced by PythonParser#varargslist.
    def exitVarargslist(self, ctx:PythonParser.VarargslistContext):
        pass


    # Enter a parse tree produced by PythonParser#fpdef.
    def enterFpdef(self, ctx:PythonParser.FpdefContext):
        pass

    # Exit a parse tree produced by PythonParser#fpdef.
    def exitFpdef(self, ctx:PythonParser.FpdefContext):
        pass


    # Enter a parse tree produced by PythonParser#fplist.
    def enterFplist(self, ctx:PythonParser.FplistContext):
        pass

    # Exit a parse tree produced by PythonParser#fplist.
    def exitFplist(self, ctx:PythonParser.FplistContext):
        pass


    # Enter a parse tree produced by PythonParser#stmt.
    def enterStmt(self, ctx:PythonParser.StmtContext):
        pass

    # Exit a parse tree produced by PythonParser#stmt.
    def exitStmt(self, ctx:PythonParser.StmtContext):
        pass


    # Enter a parse tree produced by PythonParser#simple_stmt.
    def enterSimple_stmt(self, ctx:PythonParser.Simple_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#simple_stmt.
    def exitSimple_stmt(self, ctx:PythonParser.Simple_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#small_stmt.
    def enterSmall_stmt(self, ctx:PythonParser.Small_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#small_stmt.
    def exitSmall_stmt(self, ctx:PythonParser.Small_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#expr_stmt.
    def enterExpr_stmt(self, ctx:PythonParser.Expr_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#expr_stmt.
    def exitExpr_stmt(self, ctx:PythonParser.Expr_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#augassign.
    def enterAugassign(self, ctx:PythonParser.AugassignContext):
        pass

    # Exit a parse tree produced by PythonParser#augassign.
    def exitAugassign(self, ctx:PythonParser.AugassignContext):
        pass


    # Enter a parse tree produced by PythonParser#print_stmt.
    def enterPrint_stmt(self, ctx:PythonParser.Print_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#print_stmt.
    def exitPrint_stmt(self, ctx:PythonParser.Print_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#del_stmt.
    def enterDel_stmt(self, ctx:PythonParser.Del_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#del_stmt.
    def exitDel_stmt(self, ctx:PythonParser.Del_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#pass_stmt.
    def enterPass_stmt(self, ctx:PythonParser.Pass_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#pass_stmt.
    def exitPass_stmt(self, ctx:PythonParser.Pass_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#flow_stmt.
    def enterFlow_stmt(self, ctx:PythonParser.Flow_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#flow_stmt.
    def exitFlow_stmt(self, ctx:PythonParser.Flow_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#break_stmt.
    def enterBreak_stmt(self, ctx:PythonParser.Break_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#break_stmt.
    def exitBreak_stmt(self, ctx:PythonParser.Break_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#continue_stmt.
    def enterContinue_stmt(self, ctx:PythonParser.Continue_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#continue_stmt.
    def exitContinue_stmt(self, ctx:PythonParser.Continue_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#return_stmt.
    def enterReturn_stmt(self, ctx:PythonParser.Return_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#return_stmt.
    def exitReturn_stmt(self, ctx:PythonParser.Return_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#yield_stmt.
    def enterYield_stmt(self, ctx:PythonParser.Yield_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#yield_stmt.
    def exitYield_stmt(self, ctx:PythonParser.Yield_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#raise_stmt.
    def enterRaise_stmt(self, ctx:PythonParser.Raise_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#raise_stmt.
    def exitRaise_stmt(self, ctx:PythonParser.Raise_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#import_stmt.
    def enterImport_stmt(self, ctx:PythonParser.Import_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#import_stmt.
    def exitImport_stmt(self, ctx:PythonParser.Import_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#import_name.
    def enterImport_name(self, ctx:PythonParser.Import_nameContext):
        pass

    # Exit a parse tree produced by PythonParser#import_name.
    def exitImport_name(self, ctx:PythonParser.Import_nameContext):
        pass


    # Enter a parse tree produced by PythonParser#import_from.
    def enterImport_from(self, ctx:PythonParser.Import_fromContext):
        pass

    # Exit a parse tree produced by PythonParser#import_from.
    def exitImport_from(self, ctx:PythonParser.Import_fromContext):
        pass


    # Enter a parse tree produced by PythonParser#import_as_name.
    def enterImport_as_name(self, ctx:PythonParser.Import_as_nameContext):
        pass

    # Exit a parse tree produced by PythonParser#import_as_name.
    def exitImport_as_name(self, ctx:PythonParser.Import_as_nameContext):
        pass


    # Enter a parse tree produced by PythonParser#dotted_as_name.
    def enterDotted_as_name(self, ctx:PythonParser.Dotted_as_nameContext):
        pass

    # Exit a parse tree produced by PythonParser#dotted_as_name.
    def exitDotted_as_name(self, ctx:PythonParser.Dotted_as_nameContext):
        pass


    # Enter a parse tree produced by PythonParser#import_as_names.
    def enterImport_as_names(self, ctx:PythonParser.Import_as_namesContext):
        pass

    # Exit a parse tree produced by PythonParser#import_as_names.
    def exitImport_as_names(self, ctx:PythonParser.Import_as_namesContext):
        pass


    # Enter a parse tree produced by PythonParser#dotted_as_names.
    def enterDotted_as_names(self, ctx:PythonParser.Dotted_as_namesContext):
        pass

    # Exit a parse tree produced by PythonParser#dotted_as_names.
    def exitDotted_as_names(self, ctx:PythonParser.Dotted_as_namesContext):
        pass


    # Enter a parse tree produced by PythonParser#dotted_name.
    def enterDotted_name(self, ctx:PythonParser.Dotted_nameContext):
        pass

    # Exit a parse tree produced by PythonParser#dotted_name.
    def exitDotted_name(self, ctx:PythonParser.Dotted_nameContext):
        pass


    # Enter a parse tree produced by PythonParser#global_stmt.
    def enterGlobal_stmt(self, ctx:PythonParser.Global_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#global_stmt.
    def exitGlobal_stmt(self, ctx:PythonParser.Global_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#exec_stmt.
    def enterExec_stmt(self, ctx:PythonParser.Exec_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#exec_stmt.
    def exitExec_stmt(self, ctx:PythonParser.Exec_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#assert_stmt.
    def enterAssert_stmt(self, ctx:PythonParser.Assert_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#assert_stmt.
    def exitAssert_stmt(self, ctx:PythonParser.Assert_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#compound_stmt.
    def enterCompound_stmt(self, ctx:PythonParser.Compound_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#compound_stmt.
    def exitCompound_stmt(self, ctx:PythonParser.Compound_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#if_stmt.
    def enterIf_stmt(self, ctx:PythonParser.If_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#if_stmt.
    def exitIf_stmt(self, ctx:PythonParser.If_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#while_stmt.
    def enterWhile_stmt(self, ctx:PythonParser.While_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#while_stmt.
    def exitWhile_stmt(self, ctx:PythonParser.While_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#for_stmt.
    def enterFor_stmt(self, ctx:PythonParser.For_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#for_stmt.
    def exitFor_stmt(self, ctx:PythonParser.For_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#try_stmt.
    def enterTry_stmt(self, ctx:PythonParser.Try_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#try_stmt.
    def exitTry_stmt(self, ctx:PythonParser.Try_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#with_stmt.
    def enterWith_stmt(self, ctx:PythonParser.With_stmtContext):
        pass

    # Exit a parse tree produced by PythonParser#with_stmt.
    def exitWith_stmt(self, ctx:PythonParser.With_stmtContext):
        pass


    # Enter a parse tree produced by PythonParser#with_item.
    def enterWith_item(self, ctx:PythonParser.With_itemContext):
        pass

    # Exit a parse tree produced by PythonParser#with_item.
    def exitWith_item(self, ctx:PythonParser.With_itemContext):
        pass


    # Enter a parse tree produced by PythonParser#except_clause.
    def enterExcept_clause(self, ctx:PythonParser.Except_clauseContext):
        pass

    # Exit a parse tree produced by PythonParser#except_clause.
    def exitExcept_clause(self, ctx:PythonParser.Except_clauseContext):
        pass


    # Enter a parse tree produced by PythonParser#suite.
    def enterSuite(self, ctx:PythonParser.SuiteContext):
        pass

    # Exit a parse tree produced by PythonParser#suite.
    def exitSuite(self, ctx:PythonParser.SuiteContext):
        pass


    # Enter a parse tree produced by PythonParser#testlist_safe.
    def enterTestlist_safe(self, ctx:PythonParser.Testlist_safeContext):
        pass

    # Exit a parse tree produced by PythonParser#testlist_safe.
    def exitTestlist_safe(self, ctx:PythonParser.Testlist_safeContext):
        pass


    # Enter a parse tree produced by PythonParser#old_test.
    def enterOld_test(self, ctx:PythonParser.Old_testContext):
        pass

    # Exit a parse tree produced by PythonParser#old_test.
    def exitOld_test(self, ctx:PythonParser.Old_testContext):
        pass


    # Enter a parse tree produced by PythonParser#old_lambdef.
    def enterOld_lambdef(self, ctx:PythonParser.Old_lambdefContext):
        pass

    # Exit a parse tree produced by PythonParser#old_lambdef.
    def exitOld_lambdef(self, ctx:PythonParser.Old_lambdefContext):
        pass


    # Enter a parse tree produced by PythonParser#test.
    def enterTest(self, ctx:PythonParser.TestContext):
        pass

    # Exit a parse tree produced by PythonParser#test.
    def exitTest(self, ctx:PythonParser.TestContext):
        pass


    # Enter a parse tree produced by PythonParser#or_test.
    def enterOr_test(self, ctx:PythonParser.Or_testContext):
        pass

    # Exit a parse tree produced by PythonParser#or_test.
    def exitOr_test(self, ctx:PythonParser.Or_testContext):
        pass


    # Enter a parse tree produced by PythonParser#and_test.
    def enterAnd_test(self, ctx:PythonParser.And_testContext):
        pass

    # Exit a parse tree produced by PythonParser#and_test.
    def exitAnd_test(self, ctx:PythonParser.And_testContext):
        pass


    # Enter a parse tree produced by PythonParser#not_test.
    def enterNot_test(self, ctx:PythonParser.Not_testContext):
        pass

    # Exit a parse tree produced by PythonParser#not_test.
    def exitNot_test(self, ctx:PythonParser.Not_testContext):
        pass


    # Enter a parse tree produced by PythonParser#comparison.
    def enterComparison(self, ctx:PythonParser.ComparisonContext):
        pass

    # Exit a parse tree produced by PythonParser#comparison.
    def exitComparison(self, ctx:PythonParser.ComparisonContext):
        pass


    # Enter a parse tree produced by PythonParser#comp_op.
    def enterComp_op(self, ctx:PythonParser.Comp_opContext):
        pass

    # Exit a parse tree produced by PythonParser#comp_op.
    def exitComp_op(self, ctx:PythonParser.Comp_opContext):
        pass


    # Enter a parse tree produced by PythonParser#expr.
    def enterExpr(self, ctx:PythonParser.ExprContext):
        pass

    # Exit a parse tree produced by PythonParser#expr.
    def exitExpr(self, ctx:PythonParser.ExprContext):
        pass


    # Enter a parse tree produced by PythonParser#xor_expr.
    def enterXor_expr(self, ctx:PythonParser.Xor_exprContext):
        pass

    # Exit a parse tree produced by PythonParser#xor_expr.
    def exitXor_expr(self, ctx:PythonParser.Xor_exprContext):
        pass


    # Enter a parse tree produced by PythonParser#and_expr.
    def enterAnd_expr(self, ctx:PythonParser.And_exprContext):
        pass

    # Exit a parse tree produced by PythonParser#and_expr.
    def exitAnd_expr(self, ctx:PythonParser.And_exprContext):
        pass


    # Enter a parse tree produced by PythonParser#shift_expr.
    def enterShift_expr(self, ctx:PythonParser.Shift_exprContext):
        pass

    # Exit a parse tree produced by PythonParser#shift_expr.
    def exitShift_expr(self, ctx:PythonParser.Shift_exprContext):
        pass


    # Enter a parse tree produced by PythonParser#arith_expr.
    def enterArith_expr(self, ctx:PythonParser.Arith_exprContext):
        pass

    # Exit a parse tree produced by PythonParser#arith_expr.
    def exitArith_expr(self, ctx:PythonParser.Arith_exprContext):
        pass


    # Enter a parse tree produced by PythonParser#term.
    def enterTerm(self, ctx:PythonParser.TermContext):
        pass

    # Exit a parse tree produced by PythonParser#term.
    def exitTerm(self, ctx:PythonParser.TermContext):
        pass


    # Enter a parse tree produced by PythonParser#factor.
    def enterFactor(self, ctx:PythonParser.FactorContext):
        pass

    # Exit a parse tree produced by PythonParser#factor.
    def exitFactor(self, ctx:PythonParser.FactorContext):
        pass


    # Enter a parse tree produced by PythonParser#power.
    def enterPower(self, ctx:PythonParser.PowerContext):
        pass

    # Exit a parse tree produced by PythonParser#power.
    def exitPower(self, ctx:PythonParser.PowerContext):
        pass


    # Enter a parse tree produced by PythonParser#atom.
    def enterAtom(self, ctx:PythonParser.AtomContext):
        pass

    # Exit a parse tree produced by PythonParser#atom.
    def exitAtom(self, ctx:PythonParser.AtomContext):
        pass


    # Enter a parse tree produced by PythonParser#listmaker.
    def enterListmaker(self, ctx:PythonParser.ListmakerContext):
        pass

    # Exit a parse tree produced by PythonParser#listmaker.
    def exitListmaker(self, ctx:PythonParser.ListmakerContext):
        pass


    # Enter a parse tree produced by PythonParser#testlist_comp.
    def enterTestlist_comp(self, ctx:PythonParser.Testlist_compContext):
        pass

    # Exit a parse tree produced by PythonParser#testlist_comp.
    def exitTestlist_comp(self, ctx:PythonParser.Testlist_compContext):
        pass


    # Enter a parse tree produced by PythonParser#lambdef.
    def enterLambdef(self, ctx:PythonParser.LambdefContext):
        pass

    # Exit a parse tree produced by PythonParser#lambdef.
    def exitLambdef(self, ctx:PythonParser.LambdefContext):
        pass


    # Enter a parse tree produced by PythonParser#trailer.
    def enterTrailer(self, ctx:PythonParser.TrailerContext):
        pass

    # Exit a parse tree produced by PythonParser#trailer.
    def exitTrailer(self, ctx:PythonParser.TrailerContext):
        pass


    # Enter a parse tree produced by PythonParser#subscriptlist.
    def enterSubscriptlist(self, ctx:PythonParser.SubscriptlistContext):
        pass

    # Exit a parse tree produced by PythonParser#subscriptlist.
    def exitSubscriptlist(self, ctx:PythonParser.SubscriptlistContext):
        pass


    # Enter a parse tree produced by PythonParser#subscript.
    def enterSubscript(self, ctx:PythonParser.SubscriptContext):
        pass

    # Exit a parse tree produced by PythonParser#subscript.
    def exitSubscript(self, ctx:PythonParser.SubscriptContext):
        pass


    # Enter a parse tree produced by PythonParser#sliceop.
    def enterSliceop(self, ctx:PythonParser.SliceopContext):
        pass

    # Exit a parse tree produced by PythonParser#sliceop.
    def exitSliceop(self, ctx:PythonParser.SliceopContext):
        pass


    # Enter a parse tree produced by PythonParser#exprlist.
    def enterExprlist(self, ctx:PythonParser.ExprlistContext):
        pass

    # Exit a parse tree produced by PythonParser#exprlist.
    def exitExprlist(self, ctx:PythonParser.ExprlistContext):
        pass


    # Enter a parse tree produced by PythonParser#testlist.
    def enterTestlist(self, ctx:PythonParser.TestlistContext):
        pass

    # Exit a parse tree produced by PythonParser#testlist.
    def exitTestlist(self, ctx:PythonParser.TestlistContext):
        pass


    # Enter a parse tree produced by PythonParser#dictorsetmaker.
    def enterDictorsetmaker(self, ctx:PythonParser.DictorsetmakerContext):
        pass

    # Exit a parse tree produced by PythonParser#dictorsetmaker.
    def exitDictorsetmaker(self, ctx:PythonParser.DictorsetmakerContext):
        pass


    # Enter a parse tree produced by PythonParser#classdef.
    def enterClassdef(self, ctx:PythonParser.ClassdefContext):
        pass

    # Exit a parse tree produced by PythonParser#classdef.
    def exitClassdef(self, ctx:PythonParser.ClassdefContext):
        pass


    # Enter a parse tree produced by PythonParser#arglist.
    def enterArglist(self, ctx:PythonParser.ArglistContext):
        pass

    # Exit a parse tree produced by PythonParser#arglist.
    def exitArglist(self, ctx:PythonParser.ArglistContext):
        pass


    # Enter a parse tree produced by PythonParser#argument.
    def enterArgument(self, ctx:PythonParser.ArgumentContext):
        pass

    # Exit a parse tree produced by PythonParser#argument.
    def exitArgument(self, ctx:PythonParser.ArgumentContext):
        pass


    # Enter a parse tree produced by PythonParser#list_iter.
    def enterList_iter(self, ctx:PythonParser.List_iterContext):
        pass

    # Exit a parse tree produced by PythonParser#list_iter.
    def exitList_iter(self, ctx:PythonParser.List_iterContext):
        pass


    # Enter a parse tree produced by PythonParser#list_for.
    def enterList_for(self, ctx:PythonParser.List_forContext):
        pass

    # Exit a parse tree produced by PythonParser#list_for.
    def exitList_for(self, ctx:PythonParser.List_forContext):
        pass


    # Enter a parse tree produced by PythonParser#list_if.
    def enterList_if(self, ctx:PythonParser.List_ifContext):
        pass

    # Exit a parse tree produced by PythonParser#list_if.
    def exitList_if(self, ctx:PythonParser.List_ifContext):
        pass


    # Enter a parse tree produced by PythonParser#comp_iter.
    def enterComp_iter(self, ctx:PythonParser.Comp_iterContext):
        pass

    # Exit a parse tree produced by PythonParser#comp_iter.
    def exitComp_iter(self, ctx:PythonParser.Comp_iterContext):
        pass


    # Enter a parse tree produced by PythonParser#comp_for.
    def enterComp_for(self, ctx:PythonParser.Comp_forContext):
        pass

    # Exit a parse tree produced by PythonParser#comp_for.
    def exitComp_for(self, ctx:PythonParser.Comp_forContext):
        pass


    # Enter a parse tree produced by PythonParser#comp_if.
    def enterComp_if(self, ctx:PythonParser.Comp_ifContext):
        pass

    # Exit a parse tree produced by PythonParser#comp_if.
    def exitComp_if(self, ctx:PythonParser.Comp_ifContext):
        pass


    # Enter a parse tree produced by PythonParser#testlist1.
    def enterTestlist1(self, ctx:PythonParser.Testlist1Context):
        pass

    # Exit a parse tree produced by PythonParser#testlist1.
    def exitTestlist1(self, ctx:PythonParser.Testlist1Context):
        pass


    # Enter a parse tree produced by PythonParser#encoding_decl.
    def enterEncoding_decl(self, ctx:PythonParser.Encoding_declContext):
        pass

    # Exit a parse tree produced by PythonParser#encoding_decl.
    def exitEncoding_decl(self, ctx:PythonParser.Encoding_declContext):
        pass


    # Enter a parse tree produced by PythonParser#yield_expr.
    def enterYield_expr(self, ctx:PythonParser.Yield_exprContext):
        pass

    # Exit a parse tree produced by PythonParser#yield_expr.
    def exitYield_expr(self, ctx:PythonParser.Yield_exprContext):
        pass



del PythonParser