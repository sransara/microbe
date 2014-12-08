// Generated from Microbe.g4 by ANTLR 4.4

    import java.util.LinkedList;
    import Nucleus.*;
    import SymbolScope.*;
    import AST.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MicrobeParser}.
 */
public interface MicrobeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#func_body}.
	 * @param ctx the parse tree
	 */
	void enterFunc_body(@NotNull MicrobeParser.Func_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#func_body}.
	 * @param ctx the parse tree
	 */
	void exitFunc_body(@NotNull MicrobeParser.Func_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void enterStmt_list(@NotNull MicrobeParser.Stmt_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#stmt_list}.
	 * @param ctx the parse tree
	 */
	void exitStmt_list(@NotNull MicrobeParser.Stmt_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull MicrobeParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull MicrobeParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#any_type}.
	 * @param ctx the parse tree
	 */
	void enterAny_type(@NotNull MicrobeParser.Any_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#any_type}.
	 * @param ctx the parse tree
	 */
	void exitAny_type(@NotNull MicrobeParser.Any_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(@NotNull MicrobeParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(@NotNull MicrobeParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#assign_expr}.
	 * @param ctx the parse tree
	 */
	void enterAssign_expr(@NotNull MicrobeParser.Assign_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#assign_expr}.
	 * @param ctx the parse tree
	 */
	void exitAssign_expr(@NotNull MicrobeParser.Assign_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#read_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRead_stmt(@NotNull MicrobeParser.Read_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#read_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRead_stmt(@NotNull MicrobeParser.Read_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(@NotNull MicrobeParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(@NotNull MicrobeParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#call_expr}.
	 * @param ctx the parse tree
	 */
	void enterCall_expr(@NotNull MicrobeParser.Call_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#call_expr}.
	 * @param ctx the parse tree
	 */
	void exitCall_expr(@NotNull MicrobeParser.Call_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#addop}.
	 * @param ctx the parse tree
	 */
	void enterAddop(@NotNull MicrobeParser.AddopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#addop}.
	 * @param ctx the parse tree
	 */
	void exitAddop(@NotNull MicrobeParser.AddopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(@NotNull MicrobeParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(@NotNull MicrobeParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#var_type}.
	 * @param ctx the parse tree
	 */
	void enterVar_type(@NotNull MicrobeParser.Var_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#var_type}.
	 * @param ctx the parse tree
	 */
	void exitVar_type(@NotNull MicrobeParser.Var_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#base_stmt}.
	 * @param ctx the parse tree
	 */
	void enterBase_stmt(@NotNull MicrobeParser.Base_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#base_stmt}.
	 * @param ctx the parse tree
	 */
	void exitBase_stmt(@NotNull MicrobeParser.Base_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#factor_prefix}.
	 * @param ctx the parse tree
	 */
	void enterFactor_prefix(@NotNull MicrobeParser.Factor_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#factor_prefix}.
	 * @param ctx the parse tree
	 */
	void exitFactor_prefix(@NotNull MicrobeParser.Factor_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#else_part}.
	 * @param ctx the parse tree
	 */
	void enterElse_part(@NotNull MicrobeParser.Else_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#else_part}.
	 * @param ctx the parse tree
	 */
	void exitElse_part(@NotNull MicrobeParser.Else_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#expr_prefix}.
	 * @param ctx the parse tree
	 */
	void enterExpr_prefix(@NotNull MicrobeParser.Expr_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#expr_prefix}.
	 * @param ctx the parse tree
	 */
	void exitExpr_prefix(@NotNull MicrobeParser.Expr_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(@NotNull MicrobeParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(@NotNull MicrobeParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#string_decl}.
	 * @param ctx the parse tree
	 */
	void enterString_decl(@NotNull MicrobeParser.String_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#string_decl}.
	 * @param ctx the parse tree
	 */
	void exitString_decl(@NotNull MicrobeParser.String_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#pgm_body}.
	 * @param ctx the parse tree
	 */
	void enterPgm_body(@NotNull MicrobeParser.Pgm_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#pgm_body}.
	 * @param ctx the parse tree
	 */
	void exitPgm_body(@NotNull MicrobeParser.Pgm_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#id_tail}.
	 * @param ctx the parse tree
	 */
	void enterId_tail(@NotNull MicrobeParser.Id_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#id_tail}.
	 * @param ctx the parse tree
	 */
	void exitId_tail(@NotNull MicrobeParser.Id_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#postfix_expr}.
	 * @param ctx the parse tree
	 */
	void enterPostfix_expr(@NotNull MicrobeParser.Postfix_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#postfix_expr}.
	 * @param ctx the parse tree
	 */
	void exitPostfix_expr(@NotNull MicrobeParser.Postfix_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull MicrobeParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull MicrobeParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(@NotNull MicrobeParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(@NotNull MicrobeParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#compop}.
	 * @param ctx the parse tree
	 */
	void enterCompop(@NotNull MicrobeParser.CompopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#compop}.
	 * @param ctx the parse tree
	 */
	void exitCompop(@NotNull MicrobeParser.CompopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl(@NotNull MicrobeParser.Var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl(@NotNull MicrobeParser.Var_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stmt(@NotNull MicrobeParser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stmt(@NotNull MicrobeParser.While_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#param_decl_tail}.
	 * @param ctx the parse tree
	 */
	void enterParam_decl_tail(@NotNull MicrobeParser.Param_decl_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#param_decl_tail}.
	 * @param ctx the parse tree
	 */
	void exitParam_decl_tail(@NotNull MicrobeParser.Param_decl_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#id_list}.
	 * @param ctx the parse tree
	 */
	void enterId_list(@NotNull MicrobeParser.Id_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#id_list}.
	 * @param ctx the parse tree
	 */
	void exitId_list(@NotNull MicrobeParser.Id_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign_stmt(@NotNull MicrobeParser.Assign_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign_stmt(@NotNull MicrobeParser.Assign_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#write_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWrite_stmt(@NotNull MicrobeParser.Write_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#write_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWrite_stmt(@NotNull MicrobeParser.Write_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#func_declarations}.
	 * @param ctx the parse tree
	 */
	void enterFunc_declarations(@NotNull MicrobeParser.Func_declarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#func_declarations}.
	 * @param ctx the parse tree
	 */
	void exitFunc_declarations(@NotNull MicrobeParser.Func_declarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#str}.
	 * @param ctx the parse tree
	 */
	void enterStr(@NotNull MicrobeParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#str}.
	 * @param ctx the parse tree
	 */
	void exitStr(@NotNull MicrobeParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#func_decl}.
	 * @param ctx the parse tree
	 */
	void enterFunc_decl(@NotNull MicrobeParser.Func_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#func_decl}.
	 * @param ctx the parse tree
	 */
	void exitFunc_decl(@NotNull MicrobeParser.Func_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#param_decl_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_decl_list(@NotNull MicrobeParser.Param_decl_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#param_decl_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_decl_list(@NotNull MicrobeParser.Param_decl_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull MicrobeParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull MicrobeParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stmt(@NotNull MicrobeParser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stmt(@NotNull MicrobeParser.Return_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(@NotNull MicrobeParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(@NotNull MicrobeParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#param_decl}.
	 * @param ctx the parse tree
	 */
	void enterParam_decl(@NotNull MicrobeParser.Param_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#param_decl}.
	 * @param ctx the parse tree
	 */
	void exitParam_decl(@NotNull MicrobeParser.Param_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list_tail(@NotNull MicrobeParser.Expr_list_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#expr_list_tail}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list_tail(@NotNull MicrobeParser.Expr_list_tailContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#mulop}.
	 * @param ctx the parse tree
	 */
	void enterMulop(@NotNull MicrobeParser.MulopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#mulop}.
	 * @param ctx the parse tree
	 */
	void exitMulop(@NotNull MicrobeParser.MulopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicrobeParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(@NotNull MicrobeParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicrobeParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(@NotNull MicrobeParser.Expr_listContext ctx);
}