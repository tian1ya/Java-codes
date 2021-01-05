package dsa

class DenseArrayToSparse() {


  def toSparseArray(denseArr: Array[Array[Int]]): Array[Array[Int]] = {
    buildSparseArray(denseArr)
  }

  def sparseToDense(sparseArr: Array[Array[Int]]): Array[Array[Int]] = {
    val rows = sparseArr(0)(0)
    val columns = sparseArr(0)(1)
    val denseArr = Array.ofDim[Int](rows, columns)
    val sparseArrRows = sparseArr.length
    for (index <- 1 until sparseArrRows) {
      denseArr(sparseArr(index)(0))(sparseArr(index)(1)) = sparseArr(index)(2);
    }
    denseArr
  }

  private def buildSparseArray(dense: Array[Array[Int]]): Array[Array[Int]] = {
    val rows = dense.length;
    val columns = dense(0).length;

    val nonZeroNumber = calcNonZeroNumber(dense)
    val sparseArr = Array.ofDim[Int](nonZeroNumber + 1, 3)

    sparseArr(0)(0) = rows
    sparseArr(0)(1) = columns
    sparseArr(0)(2) = nonZeroNumber

    var currentTmpIndex = 1;
    for (i <- 0 until rows) {
      for (j <- 0 until columns) {
        if (dense(i)(j) != 0) {
          sparseArr(currentTmpIndex)(0) = i;
          sparseArr(currentTmpIndex)(1) = j;
          sparseArr(currentTmpIndex)(2) = dense(i)(j);
          currentTmpIndex = currentTmpIndex + 1;
        }
      }
    }
    sparseArr
  }

  private def calcNonZeroNumber(dense: Array[Array[Int]]) = {
    var nonZeroNum = 0
    for (elem: Array[Int] <- dense) {
      for (elem: Int <- elem) {
        if (elem != 0)
          nonZeroNum = nonZeroNum + 1
      }
    }
    nonZeroNum;
  }

  def print(array: Array[Array[Int]]) = {
    for (v1 <- array) {
      for (v2 <- v1) {
        printf("%d\t", v2)
      }
      println()
    }
  }
}

object DenseArrayToSparse {
  def main(args: Array[String]): Unit = {
    val rowSize = 11
    val colSize = 11
    val chessMap = Array.ofDim[Int](rowSize, colSize)

    // 初始化数组 1即表示黑棋,2即表示白棋
    chessMap(1)(2) = 1
    chessMap(2)(3) = 2
    chessMap(4)(5) = 2

    val sparseTransformer = new DenseArrayToSparse()
    println("========== original dense arr =============")
    sparseTransformer.print(chessMap)
    println("=========== dense to sparse ================")
    val sparseArr = sparseTransformer.toSparseArray(chessMap)
    sparseTransformer.print(sparseArr)

    println("=========== sparse to Dense ================")
    val sparseToDenseArr = sparseTransformer.sparseToDense(sparseArr)
    sparseTransformer.print(sparseToDenseArr)
  }
}
