# Generated from PythonParser.g4 by ANTLR 4.13.2
from antlr4 import *
if "." in __name__:
    from .PythonParser import PythonParser
else:
    from PythonParser import PythonParser

# This class defines a complete generic visitor for a parse tree produced by PythonParser.

class PythonParserVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by PythonParser#single_input.
    def visitSingle_input(self, ctx:PythonParser.Single_inputContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#file_input.
    def visitFile_input(self, ctx:PythonParser.File_inputContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#eval_input.
    def visitEval_input(self, ctx:PythonParser.Eval_inputContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#decorator.
    def visitDecorator(self, ctx:PythonParser.DecoratorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#decorators.
    def visitDecorators(self, ctx:PythonParser.DecoratorsContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#decorated.
    def visitDecorated(self, ctx:PythonParser.DecoratedContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#funcdef.
    def visitFuncdef(self, ctx:PythonParser.FuncdefContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#parameters.
    def visitParameters(self, ctx:PythonParser.ParametersContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#varargslist.
    def visitVarargslist(self, ctx:PythonParser.VarargslistContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#fpdef.
    def visitFpdef(self, ctx:PythonParser.FpdefContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#fplist.
    def visitFplist(self, ctx:PythonParser.FplistContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#stmt.
    def visitStmt(self, ctx:PythonParser.StmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#simple_stmt.
    def visitSimple_stmt(self, ctx:PythonParser.Simple_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#small_stmt.
    def visitSmall_stmt(self, ctx:PythonParser.Small_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#expr_stmt.
    def visitExpr_stmt(self, ctx:PythonParser.Expr_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#augassign.
    def visitAugassign(self, ctx:PythonParser.AugassignContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#print_stmt.
    def visitPrint_stmt(self, ctx:PythonParser.Print_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#del_stmt.
    def visitDel_stmt(self, ctx:PythonParser.Del_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#pass_stmt.
    def visitPass_stmt(self, ctx:PythonParser.Pass_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#flow_stmt.
    def visitFlow_stmt(self, ctx:PythonParser.Flow_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#break_stmt.
    def visitBreak_stmt(self, ctx:PythonParser.Break_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#continue_stmt.
    def visitContinue_stmt(self, ctx:PythonParser.Continue_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#return_stmt.
    def visitReturn_stmt(self, ctx:PythonParser.Return_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#yield_stmt.
    def visitYield_stmt(self, ctx:PythonParser.Yield_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#raise_stmt.
    def visitRaise_stmt(self, ctx:PythonParser.Raise_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#import_stmt.
    def visitImport_stmt(self, ctx:PythonParser.Import_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#import_name.
    def visitImport_name(self, ctx:PythonParser.Import_nameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#import_from.
    def visitImport_from(self, ctx:PythonParser.Import_fromContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#import_as_name.
    def visitImport_as_name(self, ctx:PythonParser.Import_as_nameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#dotted_as_name.
    def visitDotted_as_name(self, ctx:PythonParser.Dotted_as_nameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#import_as_names.
    def visitImport_as_names(self, ctx:PythonParser.Import_as_namesContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#dotted_as_names.
    def visitDotted_as_names(self, ctx:PythonParser.Dotted_as_namesContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#dotted_name.
    def visitDotted_name(self, ctx:PythonParser.Dotted_nameContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#global_stmt.
    def visitGlobal_stmt(self, ctx:PythonParser.Global_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#exec_stmt.
    def visitExec_stmt(self, ctx:PythonParser.Exec_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#assert_stmt.
    def visitAssert_stmt(self, ctx:PythonParser.Assert_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#compound_stmt.
    def visitCompound_stmt(self, ctx:PythonParser.Compound_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#if_stmt.
    def visitIf_stmt(self, ctx:PythonParser.If_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#while_stmt.
    def visitWhile_stmt(self, ctx:PythonParser.While_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#for_stmt.
    def visitFor_stmt(self, ctx:PythonParser.For_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#try_stmt.
    def visitTry_stmt(self, ctx:PythonParser.Try_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#with_stmt.
    def visitWith_stmt(self, ctx:PythonParser.With_stmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#with_item.
    def visitWith_item(self, ctx:PythonParser.With_itemContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#except_clause.
    def visitExcept_clause(self, ctx:PythonParser.Except_clauseContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#suite.
    def visitSuite(self, ctx:PythonParser.SuiteContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#testlist_safe.
    def visitTestlist_safe(self, ctx:PythonParser.Testlist_safeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#old_test.
    def visitOld_test(self, ctx:PythonParser.Old_testContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#old_lambdef.
    def visitOld_lambdef(self, ctx:PythonParser.Old_lambdefContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#test.
    def visitTest(self, ctx:PythonParser.TestContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#or_test.
    def visitOr_test(self, ctx:PythonParser.Or_testContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#and_test.
    def visitAnd_test(self, ctx:PythonParser.And_testContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#not_test.
    def visitNot_test(self, ctx:PythonParser.Not_testContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#comparison.
    def visitComparison(self, ctx:PythonParser.ComparisonContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#comp_op.
    def visitComp_op(self, ctx:PythonParser.Comp_opContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#expr.
    def visitExpr(self, ctx:PythonParser.ExprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#xor_expr.
    def visitXor_expr(self, ctx:PythonParser.Xor_exprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#and_expr.
    def visitAnd_expr(self, ctx:PythonParser.And_exprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#shift_expr.
    def visitShift_expr(self, ctx:PythonParser.Shift_exprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#arith_expr.
    def visitArith_expr(self, ctx:PythonParser.Arith_exprContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#term.
    def visitTerm(self, ctx:PythonParser.TermContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#factor.
    def visitFactor(self, ctx:PythonParser.FactorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#power.
    def visitPower(self, ctx:PythonParser.PowerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#atom.
    def visitAtom(self, ctx:PythonParser.AtomContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#listmaker.
    def visitListmaker(self, ctx:PythonParser.ListmakerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#testlist_comp.
    def visitTestlist_comp(self, ctx:PythonParser.Testlist_compContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#lambdef.
    def visitLambdef(self, ctx:PythonParser.LambdefContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#trailer.
    def visitTrailer(self, ctx:PythonParser.TrailerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#subscriptlist.
    def visitSubscriptlist(self, ctx:PythonParser.SubscriptlistContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#subscript.
    def visitSubscript(self, ctx:PythonParser.SubscriptContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#sliceop.
    def visitSliceop(self, ctx:PythonParser.SliceopContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#exprlist.
    def visitExprlist(self, ctx:PythonParser.ExprlistContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#testlist.
    def visitTestlist(self, ctx:PythonParser.TestlistContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#dictorsetmaker.
    def visitDictorsetmaker(self, ctx:PythonParser.DictorsetmakerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#classdef.
    def visitClassdef(self, ctx:PythonParser.ClassdefContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#arglist.
    def visitArglist(self, ctx:PythonParser.ArglistContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#argument.
    def visitArgument(self, ctx:PythonParser.ArgumentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#list_iter.
    def visitList_iter(self, ctx:PythonParser.List_iterContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#list_for.
    def visitList_for(self, ctx:PythonParser.List_forContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#list_if.
    def visitList_if(self, ctx:PythonParser.List_ifContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#comp_iter.
    def visitComp_iter(self, ctx:PythonParser.Comp_iterContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#comp_for.
    def visitComp_for(self, ctx:PythonParser.Comp_forContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#comp_if.
    def visitComp_if(self, ctx:PythonParser.Comp_ifContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#testlist1.
    def visitTestlist1(self, ctx:PythonParser.Testlist1Context):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#encoding_decl.
    def visitEncoding_decl(self, ctx:PythonParser.Encoding_declContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by PythonParser#yield_expr.
    def visitYield_expr(self, ctx:PythonParser.Yield_exprContext):
        return self.visitChildren(ctx)



del PythonParser