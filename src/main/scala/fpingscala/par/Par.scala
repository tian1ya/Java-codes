package fpingscala.par


object Par {

  /*
      sum2 是分支的，可以并行化的
   */
  def sum(ints: Seq[Int]): Int = ints.foldRight(0)((e, z) => z + e)

  def sum2(ints: IndexedSeq[Int]): Int = {
    if (ints.size <= 1)
      ints.headOption.getOrElse(0)
    else {
      val (l,r) = ints.splitAt(ints.length / 2)
      sum2(l) + sum2(r)
    }
  }

}
