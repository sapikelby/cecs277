You must create the class ExpressionStore so that it can compile and run with my AssignmentSixMain and produce the output provided with the input provided.

The input is a text file containing lines of the form:

exprName   expression

where exprName is a String

and expression is a String of the form:

<integer>,<integer>,<operator>,..., <operator>

The main is going to read this file and call ExpressionStore::store(expName, expression) to save the expression.

main will later call Expression::getValue(expName) to get the arithmetic value of the expression.

main will also call ExpressionSTore::remove to remove some expressions and ExpressionStore::getLeast & ExpressionStore::getNext to iterate thru the store in sorted sequence of expName.

You must implement ExpressionStore using only Data Structures provided by the Java libraries.  

You will be graded upon your choice of Java library data structure(s).

We will dicuss evaluatuion of a postfix algebraic expression in class. 