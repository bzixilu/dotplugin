// This is a generated file. Not intended for manual editing.
package org.plugin.dot.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.plugin.dot.psi.DotTypes.*;
import static org.plugin.dot.DotPSITreeUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class DotParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == EOL) {
      r = EOL(b, 0);
    }
    else if (t == ID) {
      r = ID(b, 0);
    }
    else if (t == A_LIST) {
      r = a_list(b, 0);
    }
    else if (t == ATTR_LIST) {
      r = attr_list(b, 0);
    }
    else if (t == ATTR_STMT) {
      r = attr_stmt(b, 0);
    }
    else if (t == CHARACTER) {
      r = character(b, 0);
    }
    else if (t == COMMENT_STMT) {
      r = comment_stmt(b, 0);
    }
    else if (t == COMPASS_PT) {
      r = compass_pt(b, 0);
    }
    else if (t == DOTGRAPH_STMT) {
      r = dotgraph_stmt(b, 0);
    }
    else if (t == EDGE_RHS) {
      r = edgeRHS(b, 0);
    }
    else if (t == EDGE_STMT) {
      r = edge_stmt(b, 0);
    }
    else if (t == NODE_ID) {
      r = node_id(b, 0);
    }
    else if (t == NODE_STMT) {
      r = node_stmt(b, 0);
    }
    else if (t == PORT) {
      r = port(b, 0);
    }
    else if (t == STMT) {
      r = stmt(b, 0);
    }
    else if (t == STMT_LIST) {
      r = stmt_list(b, 0);
    }
    else if (t == SUB_GRAPH) {
      r = sub_graph(b, 0);
    }
    else if (t == SYMBOL) {
      r = symbol(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return dotFile(b, l + 1);
  }

  /* ********************************************************** */
  // "\n"
  public static boolean EOL(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EOL")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EOL, "<eol>");
    r = consumeToken(b, "\n");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // SIMPLE_ID | QUOTED_ANY | HTML_ID | NUMERAL_ID
  public static boolean ID(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ID")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ID, "<id>");
    r = consumeToken(b, SIMPLE_ID);
    if (!r) r = consumeToken(b, QUOTED_ANY);
    if (!r) r = consumeToken(b, HTML_ID);
    if (!r) r = consumeToken(b, NUMERAL_ID);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ID "=" ID [ (EOS | COMMA) ] [ a_list ] | comment_stmt
  public static boolean a_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "a_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, A_LIST, "<a list>");
    r = a_list_0(b, l + 1);
    if (!r) r = comment_stmt(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ID "=" ID [ (EOS | COMMA) ] [ a_list ]
  private static boolean a_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "a_list_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ID(b, l + 1);
    r = r && consumeToken(b, EQUAL);
    r = r && ID(b, l + 1);
    r = r && a_list_0_3(b, l + 1);
    r = r && a_list_0_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ (EOS | COMMA) ]
  private static boolean a_list_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "a_list_0_3")) return false;
    a_list_0_3_0(b, l + 1);
    return true;
  }

  // EOS | COMMA
  private static boolean a_list_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "a_list_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EOS);
    if (!r) r = consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ a_list ]
  private static boolean a_list_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "a_list_0_4")) return false;
    a_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // BRACHET_LEFT [ a_list ] BRACKET_RIGHT [ attr_list ]
  public static boolean attr_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_list")) return false;
    if (!nextTokenIs(b, BRACHET_LEFT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACHET_LEFT);
    r = r && attr_list_1(b, l + 1);
    r = r && consumeToken(b, BRACKET_RIGHT);
    r = r && attr_list_3(b, l + 1);
    exit_section_(b, m, ATTR_LIST, r);
    return r;
  }

  // [ a_list ]
  private static boolean attr_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_list_1")) return false;
    a_list(b, l + 1);
    return true;
  }

  // [ attr_list ]
  private static boolean attr_list_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_list_3")) return false;
    attr_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (GRAPH_ | NODE_ | EDGE_) attr_list | comment_stmt
  public static boolean attr_stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_stmt")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATTR_STMT, "<attr stmt>");
    r = attr_stmt_0(b, l + 1);
    if (!r) r = comment_stmt(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (GRAPH_ | NODE_ | EDGE_) attr_list
  private static boolean attr_stmt_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_stmt_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attr_stmt_0_0(b, l + 1);
    r = r && attr_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // GRAPH_ | NODE_ | EDGE_
  private static boolean attr_stmt_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attr_stmt_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GRAPH_);
    if (!r) r = consumeToken(b, NODE_);
    if (!r) r = consumeToken(b, EDGE_);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LETTERS | DIGITS | symbol
  public static boolean character(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "character")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CHARACTER, "<character>");
    r = consumeToken(b, LETTERS);
    if (!r) r = consumeToken(b, DIGITS);
    if (!r) r = symbol(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MULTILINE_COMMENT | COMMENT
  public static boolean comment_stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_stmt")) return false;
    if (!nextTokenIs(b, "<comment stmt>", COMMENT, MULTILINE_COMMENT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMENT_STMT, "<comment stmt>");
    r = consumeToken(b, MULTILINE_COMMENT);
    if (!r) r = consumeToken(b, COMMENT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // COMPASS
  public static boolean compass_pt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compass_pt")) return false;
    if (!nextTokenIs(b, COMPASS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMPASS);
    exit_section_(b, m, COMPASS_PT, r);
    return r;
  }

  /* ********************************************************** */
  // (comment_stmt|dotgraph_stmt|EMPTY)*
  static boolean dotFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dotFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!dotFile_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dotFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // comment_stmt|dotgraph_stmt|EMPTY
  private static boolean dotFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dotFile_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comment_stmt(b, l + 1);
    if (!r) r = dotgraph_stmt(b, l + 1);
    if (!r) r = consumeToken(b, EMPTY);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // [ STRICT_ ] (GRAPH_ | DIGRAPH_) [ID] CURLY_BRACHET_LEFT [stmt_list] CURLY_BRACKET_RIGHT
  public static boolean dotgraph_stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dotgraph_stmt")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DOTGRAPH_STMT, "<dotgraph stmt>");
    r = dotgraph_stmt_0(b, l + 1);
    r = r && dotgraph_stmt_1(b, l + 1);
    r = r && dotgraph_stmt_2(b, l + 1);
    r = r && consumeToken(b, CURLY_BRACHET_LEFT);
    r = r && dotgraph_stmt_4(b, l + 1);
    r = r && consumeToken(b, CURLY_BRACKET_RIGHT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ STRICT_ ]
  private static boolean dotgraph_stmt_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dotgraph_stmt_0")) return false;
    consumeToken(b, STRICT_);
    return true;
  }

  // GRAPH_ | DIGRAPH_
  private static boolean dotgraph_stmt_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dotgraph_stmt_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GRAPH_);
    if (!r) r = consumeToken(b, DIGRAPH_);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ID]
  private static boolean dotgraph_stmt_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dotgraph_stmt_2")) return false;
    ID(b, l + 1);
    return true;
  }

  // [stmt_list]
  private static boolean dotgraph_stmt_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dotgraph_stmt_4")) return false;
    stmt_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // EDGE_OP (node_id | sub_graph) [ edgeRHS ]
  public static boolean edgeRHS(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "edgeRHS")) return false;
    if (!nextTokenIs(b, EDGE_OP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EDGE_OP);
    r = r && edgeRHS_1(b, l + 1);
    r = r && edgeRHS_2(b, l + 1);
    exit_section_(b, m, EDGE_RHS, r);
    return r;
  }

  // node_id | sub_graph
  private static boolean edgeRHS_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "edgeRHS_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = node_id(b, l + 1);
    if (!r) r = sub_graph(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ edgeRHS ]
  private static boolean edgeRHS_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "edgeRHS_2")) return false;
    edgeRHS(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (node_id | sub_graph) edgeRHS [ attr_list ]
  public static boolean edge_stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "edge_stmt")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EDGE_STMT, "<edge stmt>");
    r = edge_stmt_0(b, l + 1);
    r = r && edgeRHS(b, l + 1);
    r = r && edge_stmt_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // node_id | sub_graph
  private static boolean edge_stmt_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "edge_stmt_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = node_id(b, l + 1);
    if (!r) r = sub_graph(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ attr_list ]
  private static boolean edge_stmt_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "edge_stmt_2")) return false;
    attr_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ID [ port ]
  public static boolean node_id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "node_id")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NODE_ID, "<node id>");
    r = ID(b, l + 1);
    r = r && node_id_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ port ]
  private static boolean node_id_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "node_id_1")) return false;
    port(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // node_id [ attr_list ]
  public static boolean node_stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "node_stmt")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NODE_STMT, "<node stmt>");
    r = node_id(b, l + 1);
    r = r && node_stmt_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ attr_list ]
  private static boolean node_stmt_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "node_stmt_1")) return false;
    attr_list(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COLON ID [ COLON compass_pt ] |	COLON compass_pt
  public static boolean port(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "port")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = port_0(b, l + 1);
    if (!r) r = port_1(b, l + 1);
    exit_section_(b, m, PORT, r);
    return r;
  }

  // COLON ID [ COLON compass_pt ]
  private static boolean port_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "port_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && ID(b, l + 1);
    r = r && port_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ COLON compass_pt ]
  private static boolean port_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "port_0_2")) return false;
    port_0_2_0(b, l + 1);
    return true;
  }

  // COLON compass_pt
  private static boolean port_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "port_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && compass_pt(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COLON compass_pt
  private static boolean port_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "port_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && compass_pt(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // edge_stmt|  sub_graph |attr_stmt| ID EQUAL ID| node_stmt| comment_stmt
  public static boolean stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stmt")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STMT, "<stmt>");
    r = edge_stmt(b, l + 1);
    if (!r) r = sub_graph(b, l + 1);
    if (!r) r = attr_stmt(b, l + 1);
    if (!r) r = stmt_3(b, l + 1);
    if (!r) r = node_stmt(b, l + 1);
    if (!r) r = comment_stmt(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ID EQUAL ID
  private static boolean stmt_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stmt_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ID(b, l + 1);
    r = r && consumeToken(b, EQUAL);
    r = r && ID(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // [stmt [ EOS ] stmt_list]
  public static boolean stmt_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stmt_list")) return false;
    Marker m = enter_section_(b, l, _NONE_, STMT_LIST, "<stmt list>");
    stmt_list_0(b, l + 1);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // stmt [ EOS ] stmt_list
  private static boolean stmt_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stmt_list_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = stmt(b, l + 1);
    r = r && stmt_list_0_1(b, l + 1);
    r = r && stmt_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ EOS ]
  private static boolean stmt_list_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stmt_list_0_1")) return false;
    consumeToken(b, EOS);
    return true;
  }

  /* ********************************************************** */
  // [ SUB_GRAPH_ [ ID ] ] CURLY_BRACHET_LEFT stmt_list CURLY_BRACKET_RIGHT
  public static boolean sub_graph(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_graph")) return false;
    if (!nextTokenIs(b, "<sub graph>", CURLY_BRACHET_LEFT, SUB_GRAPH_)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SUB_GRAPH, "<sub graph>");
    r = sub_graph_0(b, l + 1);
    r = r && consumeToken(b, CURLY_BRACHET_LEFT);
    r = r && stmt_list(b, l + 1);
    r = r && consumeToken(b, CURLY_BRACKET_RIGHT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [ SUB_GRAPH_ [ ID ] ]
  private static boolean sub_graph_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_graph_0")) return false;
    sub_graph_0_0(b, l + 1);
    return true;
  }

  // SUB_GRAPH_ [ ID ]
  private static boolean sub_graph_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_graph_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SUB_GRAPH_);
    r = r && sub_graph_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ ID ]
  private static boolean sub_graph_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_graph_0_0_1")) return false;
    ID(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // "|" | " " | "!" | "#" | "$" | "%" | "&" | "(" | ")" | "*" | "+" | "," | "-" | "." | "/" | ":" | ";" | "<" | "=" | ">" | "?" | "@" | "[" | "{" | "}"
  public static boolean symbol(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "symbol")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SYMBOL, "<symbol>");
    r = consumeToken(b, "|");
    if (!r) r = consumeToken(b, " ");
    if (!r) r = consumeToken(b, "!");
    if (!r) r = consumeToken(b, "#");
    if (!r) r = consumeToken(b, "$");
    if (!r) r = consumeToken(b, "%");
    if (!r) r = consumeToken(b, "&");
    if (!r) r = consumeToken(b, "(");
    if (!r) r = consumeToken(b, ")");
    if (!r) r = consumeToken(b, "*");
    if (!r) r = consumeToken(b, "+");
    if (!r) r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, "-");
    if (!r) r = consumeToken(b, DOT);
    if (!r) r = consumeToken(b, "/");
    if (!r) r = consumeToken(b, COLON);
    if (!r) r = consumeToken(b, EOS);
    if (!r) r = consumeToken(b, "<");
    if (!r) r = consumeToken(b, EQUAL);
    if (!r) r = consumeToken(b, ">");
    if (!r) r = consumeToken(b, "?");
    if (!r) r = consumeToken(b, "@");
    if (!r) r = consumeToken(b, BRACHET_LEFT);
    if (!r) r = consumeToken(b, CURLY_BRACHET_LEFT);
    if (!r) r = consumeToken(b, CURLY_BRACKET_RIGHT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
