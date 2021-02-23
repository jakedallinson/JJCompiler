
package jjcompiler.scanner;

%%

/* DECLARATIONS */

digit 		= [0-9]
letter 		= [a-zA-Z]
number 		= {digit}*
identifier	= {letter}*

%%

/* DEFINITIONS */

{number}		{return NUM;}
{identifier}	{return ID;}

"else" 		{return ELSE;}
"if" 		{return IF;}
"return" 	{return RETURN;}
"void" 		{return VOID;}
"while" 		{return WHILE;}

"+" 		{return PLUS;}
"-" 		{return MINUS;}
"*" 		{return TIMES;}
"/" 		{return DIVIDE;}
"<" 		{return LT;}
"<=" 		{return LTEQ;}
">" 		{return GT;}
">=" 		{return GTEQ;}
"==" 		{return EQ;}
"!=" 		{return NOTEQ;}
"=" 		{return ASSIGN;}
";" 		{return SEMI;}
"," 		{return COMMA;}
"(" 		{return LPAREN;}
")" 		{return RPAREN;}
"[" 		{return LBRACKET;}
"]" 		{return RBRACKET;}
"{" 		{return LCURLY;}
"}" 		{return RCURLY;}
"<<EOF>>"	{return EOF;}
