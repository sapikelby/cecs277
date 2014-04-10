import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Stack;
import java.util.TreeMap;

public class ExpressionStore
{
	// treeMap (string key, expression as string)
	public ExpressionStore()
	{
		_List = new TreeMap<String, String>();
	}
	
	public void store(String expName, String expression)
	{
;		_List.put(expName, expression);
	}
	
	public void remove(String remName)
	{
		if(_List.containsKey(remName))
		{
			_List.remove(remName);
			
		}
	}

	public String getLeast()
	{
		//TreeMap<String, String> temp = new TreeMap<String, String>();
		//temp.putAll(_List);
		
		return _List.firstKey();
	}

	public String getNext(String input)
	{
		return _List.higherKey(input);
	}

	public String getExpression(String name)
	{
		if(_List.containsKey(name))
		{
			return _List.get(name);
		}
		else
		{
			return null;
		}
	}
	
	public LinkedList<String> split(String expression)
	{
		return new LinkedList<String>(Arrays.asList(expression.split(",")));
	}
	
	public int getValue(String name)
	{
		
		
		LinkedList<String> exp = split(_List.get(name));
		
		Stack<Integer> st = new Stack<Integer>();
		String operator;
		int i = 0;
		int num1 = 0; 
		int num2 = 0;
		while(i < exp.size())
		{
			//System.out.println(st.size());
			//System.out.println(st.toString());
			if(exp.get(i).compareTo("+") != 0 && exp.get(i).compareTo("-") != 0 &&
					exp.get(i).compareTo("*") != 0 && exp.get(i).compareTo("/") != 0) //|| exp.get(i) != "*" ||
					//exp.get(i) != "-" || exp.get(i) != "/")
			{
				
				//Integer item = st.push(Integer.parseInt(exp.get(i)));
				//System.out.println("pushed item" + item);
				//System.out.println(st.push(Integer.parseInt(exp.get(i))));
				//System.out.println(expression.get(i));
				st.push(Integer.parseInt(exp.get(i)));
			}
			else 
			{
				
				//int num1 = Integer.parseInt(st.pop());
				num1 = st.pop();
				num2 = st.pop();
				operator = exp.get(i);
				int temp = 0;
				//int answer;
				//int answer = 0;
				
				switch (operator) 
				{

				// case "+": st.push(Integer.toString(num2 + num1));
				// case "+": st.push((num2 + num1));
				case "+":
					temp = num2 + num1;
					break;
				case "-":
					temp = num2 - num1;
					break;
				case "/":
					temp = num2 / num1;
					break;
				case "*":
					temp = num2 * num1;
					break;

				}
				
				st.push(temp);
				
			}
			i++;
			
		}
		return st.pop();
		
		
	}
	
	private TreeMap<String, String> _List;
}
