package org.plugin.dot;

import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static org.plugin.dot.psi.DotTypes.*;

%%

%{
  public _DotLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _DotLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s

DIGRAPH_=DIGRAPH|Digraph|digraph
GRAPH_=GRAPH|Graph|graph
EDGE_=EDGE|Edge|edge
NODE_=NODE|node|Node
STRICT_=STRICT|Strict|strict
SUB_GRAPH_=SUBGRAPH|Subgraph|subgraph
NEWLINE=\n
EDGE_OP=--|->
COMMENT=("//"|#)(.*)(\n)
MULTILINE_COMMENT="/"\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+"/"
SIMPLE_ID=([a-z]|[A-Z])(_|\.|[a-z]|[A-Z]|[0-9])*
QUOTED_ANY=(\")([^\"]*)(\")
HTML_ID=(<)(.*)(>)
NUMERAL_ID=[-]?(\.[0-9]+ | [0-9]+(\.[0-9]*)? )
EXTENDED_ID=([a-z]|[A-Z][0-9])(_|\.|[a-z]|[A-Z][0-9])*
LETTERS=([a-z]|[A-Z])*
DIGITS=([0-9])*
COMPASS=N|n|S|s|W|w|NE|ne|NW|nw|SE|se|_|c

%%
<YYINITIAL> {
  {WHITE_SPACE}            { return com.intellij.psi.TokenType.WHITE_SPACE; }

  "."                      { return DOT; }
  ","                      { return COMMA; }
  ":"                      { return COLON; }
  ";"                      { return EOS; }
  "="                      { return EQUAL; }
  "}"                      { return CURLY_BRACKET_RIGHT; }
  "{"                      { return CURLY_BRACHET_LEFT; }
  "]"                      { return BRACKET_RIGHT; }
  "["                      { return BRACHET_LEFT; }
  "EMPTY"                  { return EMPTY; }

  {DIGRAPH_}               { return DIGRAPH_; }
  {GRAPH_}                 { return GRAPH_; }
  {EDGE_}                  { return EDGE_; }
  {NODE_}                  { return NODE_; }
  {STRICT_}                { return STRICT_; }
  {SUB_GRAPH_}             { return SUB_GRAPH_; }
  {NEWLINE}                { return NEWLINE; }
  {EDGE_OP}                { return EDGE_OP; }
  {COMMENT}                { return COMMENT; }
  {MULTILINE_COMMENT}      { return MULTILINE_COMMENT; }
  {SIMPLE_ID}              { return SIMPLE_ID; }
  {QUOTED_ANY}             { return QUOTED_ANY; }
  {HTML_ID}                { return HTML_ID; }
  {NUMERAL_ID}             { return NUMERAL_ID; }
  {EXTENDED_ID}            { return EXTENDED_ID; }
  {LETTERS}                { return LETTERS; }
  {DIGITS}                 { return DIGITS; }
  {COMPASS}                { return COMPASS; }

}

[^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }
