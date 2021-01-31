package grammer

import java.io.File

import scala.io.Source


object implicitGrammer extends App {

  // 不能定义两个相同类型的隐式变量
  //  implicit val default: Int = 100;
  //  implicit val defaultName: String = "Marry"
  //
  //  def sayHello(implicit Name: String = "Tom") = println(s"I'm ${Name} I love you")
  //
  //  def add(x: Int)(implicit y:Int) = x + y
  //
  //  def sum(a: Int)(implicit b: Int, c: Int) = a + b +c
  //
  //  sayHello("Jack") // I'm Jack I love you
  //  sayHello //I'm Marry I love you 隐式变量是优先于 默认值的
  //
  //  private val res: Int = add(10)
  //  println(res) // 110, 第二个参数会来自于隐式变量
  //
  //  private val i: Int = sum(10)
  //  println(i) // 210 其他两个参数就会默认使用类中的成员变量用implicit修饰的default的值。


//  // 隐式方法
//  implicit def doubleToInt(double: Double): Int = {
//    println("======= 调用了方法 doubleToInt ==========")
//    double.toInt
//  }
//
//  // 隐式函数
//  implicit val doubleToInt2 = (d: Double) => {
//    println("======= 调用了方法 doubleToInt ==========")
//    d.toInt
//  }
//  /*
//      这里 year 是Int 但是却赋值了一个 Double
//      编译器会优先查看上下文上有没有实现 Double 到Int 转换的函数
//      如果找不到，那么会去找是否有 Double 到Int 转换的方法
//      如果都没有，那么就会报错
//      如果将上面的两个都删除，那么下面的代码就会报错
//   */
//  val year:Int = 2018.7
//  println(s"year 的结果是： ${year}")

  // 定义隐式类，只能在静态对象(使用Object修饰)中使用
  implicit class FileRead(file: File) {
    def myRead(): String = Source.fromFile(file).mkString(",")
  }

  private val file = new File("/Users/xuxliu/Ifoods/Java/leetcode/src/main/scala/grammer/a.txt")
  private val str: String = file.myRead()
  println(str)
}
