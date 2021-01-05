package dsa.stack

/*
  3+2*6-1

  1. 设计2个栈，数栈，符号栈
  2. 对表达式进行扫描
  3. 当取出的字符是数值时候，入数栈，
  4. 如果是符号时
    1. 当前符号栈没有数据，那么直接入栈
    2. 如果当前符号的优先级小于等于符号栈的栈顶的优先级，则取出符号，并从数栈一次弹出2个数据，进行运算，将结果重新压回数栈
       再将当前符号push 到符号栈
       大于那么符号之间入栈
  5. 当整个表达式扫描完毕，一次从数栈和符号栈取出数据，进行运算，最后在数栈中的数据就是结果

 */
class Calculator(expr: String) {

  val expression: String = expr

  val numbers = new ArrayStack[Int](8)
  val operators = new ArrayStack[String](5)

  def calcuteByLevel(): Int = {
    calc(numbers.pop(), numbers.pop(), operators.pop())
  }

  def calc(num1: Int, num2: Int, operator: String): Int = {
    operator match {
      case "+" => num1 + num2
      case "-" => num2 - num1
      case "*" => num1 * num2
      case "/" => num1 / num2
    }
  }

  def priority(operator: String): Int = {
    if (operator.equals("+") || operator.equals("-"))
      0
    else if (operator.equals("*") || operator.equals("/"))
      1
    else
      -1
  }

  def isOper(value: String): Boolean = {
    value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")
  }

  def isNumber(value: String): Boolean = {
    !isOper(value)
  }

  def pushNumerStack(ele: Int): Int = {
    numbers.push(ele)
  }

  def pushOperStack(ele: String): String = {
    operators.push(ele)
  }
}

object Calculator extends App {
//  val expr = "2+6*20-100"
  val expr = "7*2*2-5+1-5+3-4"
  val calculator = new Calculator(expr)

  var index = 0
  var num2 = 0
  var res = 0
  var oper = " "
  var exprValue = ""

  // 保存上次的数字，保存，用于拼接多位数值
  var keepNum: String = ""

  while (index < expr.length) {
    exprValue = expr.substring(index, index + 1)
    if (calculator.isOper(exprValue)) {
      //这里压进去的时候变为asic值
      if (!calculator.operators.isEmpty()) {
        if (calculator.priority(exprValue) <= calculator.priority(calculator.operators.getTopValue())) {
          res = calculator.calcuteByLevel()
          calculator.pushNumerStack(res)
          calculator.pushOperStack(exprValue)
        } else {
          // 如果当前的符号的优先级大于符号栈顶的符号，之间入栈
          calculator.pushOperStack(exprValue)
        }
      } else
        calculator.pushOperStack(exprValue)
    } else {
      // 注意这里ch 是 char 类型，如果直接toInt 那么等于将其转为ACIC 码值，需要先将其转为String
      // 探测下一个是不是数字
      keepNum += exprValue
      if ((index + 1) == expr.length || calculator.isOper(expr.substring(index + 1, index + 2))) {
        calculator.pushNumerStack(keepNum.toInt)
        keepNum = ""
      }
    }
      index += 1
    }

    //全部扫描完成之后，依次取出数据，进行运算
    while (!calculator.operators.isEmpty()) {
      val i = calculator.calcuteByLevel()
      calculator.pushNumerStack(i)
    }
    printf("%s = %s", expr, calculator.numbers.pop())

}
