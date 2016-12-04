// This is a generated file. Not intended for manual editing.
package org.plugin.dot.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.plugin.dot.psi.impl.*;

public interface DotTypes {

  IElementType ATTR_LIST = new DotElementType("ATTR_LIST");
  IElementType ATTR_STMT = new DotElementType("ATTR_STMT");
  IElementType A_LIST = new DotElementType("A_LIST");
  IElementType CHARACTER = new DotElementType("CHARACTER");
  IElementType COMMENT_STMT = new DotElementType("COMMENT_STMT");
  IElementType COMPASS_PT = new DotElementType("COMPASS_PT");
  IElementType DOTGRAPH_STMT = new DotElementType("DOTGRAPH_STMT");
  IElementType EDGE_RHS = new DotElementType("EDGE_RHS");
  IElementType EDGE_STMT = new DotElementType("EDGE_STMT");
  IElementType EOL = new DotElementType("EOL");
  IElementType ID = new DotElementType("ID");
  IElementType NODE_ID = new DotElementType("NODE_ID");
  IElementType NODE_STMT = new DotElementType("NODE_STMT");
  IElementType PORT = new DotElementType("PORT");
  IElementType STMT = new DotElementType("STMT");
  IElementType STMT_LIST = new DotElementType("STMT_LIST");
  IElementType SUB_GRAPH = new DotElementType("SUB_GRAPH");
  IElementType SYMBOL = new DotElementType("SYMBOL");

  IElementType BRACHET_LEFT = new DotTokenType("[");
  IElementType BRACKET_RIGHT = new DotTokenType("]");
  IElementType COLON = new DotTokenType(":");
  IElementType COMMA = new DotTokenType(",");
  IElementType COMMENT = new DotTokenType("COMMENT");
  IElementType COMPASS = new DotTokenType("COMPASS");
  IElementType CURLY_BRACHET_LEFT = new DotTokenType("{");
  IElementType CURLY_BRACKET_RIGHT = new DotTokenType("}");
  IElementType DIGITS = new DotTokenType("DIGITS");
  IElementType DIGRAPH_ = new DotTokenType("DIGRAPH_");
  IElementType DOT = new DotTokenType(".");
  IElementType EDGE_ = new DotTokenType("EDGE_");
  IElementType EDGE_OP = new DotTokenType("EDGE_OP");
  IElementType EMPTY = new DotTokenType("EMPTY");
  IElementType EOS = new DotTokenType(";");
  IElementType EQUAL = new DotTokenType("=");
  IElementType EXTENDED_ID = new DotTokenType("EXTENDED_ID");
  IElementType GRAPH_ = new DotTokenType("GRAPH_");
  IElementType HTML_ID = new DotTokenType("HTML_ID");
  IElementType LETTERS = new DotTokenType("LETTERS");
  IElementType MULTILINE_COMMENT = new DotTokenType("MULTILINE_COMMENT");
  IElementType NODE_ = new DotTokenType("NODE_");
  IElementType NUMERAL_ID = new DotTokenType("NUMERAL_ID");
  IElementType QUOTED_ANY = new DotTokenType("QUOTED_ANY");
  IElementType SIMPLE_ID = new DotTokenType("SIMPLE_ID");
  IElementType STRICT_ = new DotTokenType("STRICT_");
  IElementType SUB_GRAPH_ = new DotTokenType("SUB_GRAPH_");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ATTR_LIST) {
        return new DotAttrListImpl(node);
      }
      else if (type == ATTR_STMT) {
        return new DotAttrStmtImpl(node);
      }
      else if (type == A_LIST) {
        return new DotAListImpl(node);
      }
      else if (type == CHARACTER) {
        return new DotCharacterImpl(node);
      }
      else if (type == COMMENT_STMT) {
        return new DotCommentStmtImpl(node);
      }
      else if (type == COMPASS_PT) {
        return new DotCompassPtImpl(node);
      }
      else if (type == DOTGRAPH_STMT) {
        return new DotDotgraphStmtImpl(node);
      }
      else if (type == EDGE_RHS) {
        return new DotEdgeRHSImpl(node);
      }
      else if (type == EDGE_STMT) {
        return new DotEdgeStmtImpl(node);
      }
      else if (type == EOL) {
        return new DotEolImpl(node);
      }
      else if (type == ID) {
        return new DotIdImpl(node);
      }
      else if (type == NODE_ID) {
        return new DotNodeIdImpl(node);
      }
      else if (type == NODE_STMT) {
        return new DotNodeStmtImpl(node);
      }
      else if (type == PORT) {
        return new DotPortImpl(node);
      }
      else if (type == STMT) {
        return new DotStmtImpl(node);
      }
      else if (type == STMT_LIST) {
        return new DotStmtListImpl(node);
      }
      else if (type == SUB_GRAPH) {
        return new DotSubGraphImpl(node);
      }
      else if (type == SYMBOL) {
        return new DotSymbolImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
