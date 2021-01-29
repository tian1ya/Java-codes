package fpingscala.purefunction

trait RNG {
  def nextInt:(Int, RNG)
}

object SideEffect {

  def rollDie: Int = {
    val rng = new scala.util.Random()
    rng.nextInt(6) // 0-5的数字
  }

  def main(args: Array[String]): Unit = {
  /*
     scala 表中库中的一个类，典型的依赖副作用的 API

     Random 在每次调用之后就会某种方式更新内部状态，不是引用透明的

     副作用带来测试的难度加大， 应该该局原色避开副作用

     恢复引用透明的关键是让状态更新是现实的，不要以副作用的方式更新状态， 而是连生成的
     值一起返回一个新的状态
   */
    val rng = new scala.util.Random
    println(rng.nextDouble())
    println(rng.nextInt())
    println(rng.nextInt(10))
}
  }
