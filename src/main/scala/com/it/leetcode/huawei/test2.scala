package com.it.leetcode.huawei

object test2 extends App {

  //求立方根
  def getCubeRoot(input: Double, tol: Double = 0.0001, lr: Double = 0.001, initial: Double): Double = {
    /*
        使用梯度下降法，但是需要多试几个初始点，因为 x^3 不是凸函数

        y = x^3

        f(x) = (x^3 - y)^2
        g(x) = 2(x^3 - y) * 3x^2
        x = x - lr * g(x)

        当初始值为 0的时候就更新不到最优值了
     */

    var root = initial
    while (math.abs(math.pow(root, 3) - input) > tol) {
      val g = 6 * math.pow(root, 2) * (math.pow(root, 3) - input)
      root = root - lr * g
    }
    root
  }


  def getCubeRoot1(input: Double, tol: Double = 0.0001, lr: Double = 0.001, initial: Double): Double = {
    /*
      y = x^3

      f(x) = x^3 - y
      g(x) = 3 * x^2

      g2(x) = 6 * x
      x = x - lr * g(x)/g2(x)

      f(x)=f(x0)+(x-x0)*f'(x0)=0，得出的x=x0-f(x0)/f'(x0)=g(x0)
     */
    var root = initial
    while (math.abs(math.pow(root, 3) - input) > tol) {
      val g = math.pow(root, 3) - input
      val g2 = 3 * math.pow(root, 2)
      root = root - lr * g / (g2 + 0.01)
    }
    root
  }

    println(getCubeRoot1(input = 9, initial = 0))
}
