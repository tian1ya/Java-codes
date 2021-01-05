package dateStructure.dsPlay.dsa.stack.algorithems;

import dateStructure.dsPlay.dsa.stack.ArrayStack;

import java.util.Stack;

/*
    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

    有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        注意空字符串可被认为是有效字符串。

 */
public class ValidParentheses {
    public boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char ele = s.charAt(i);
            if (ele == '(' || ele == '[' || ele == '{')
                stack.push(ele);
            else {
                if (stack.isEmpty()) return false;
                else {
                    Character topEle = stack.pop();
                    if (ele == ')' && topEle != '(') return false;
                    if (ele == ']' && topEle != '[') return false;
                    if (ele == '}' && topEle != '{') return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println((new ValidParentheses()).isValid("{[}"));
    }
}


