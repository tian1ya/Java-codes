package dsaPlay

object LongestPalindrome extends App {
  def longestPalindrome(s: String): String = {

    if (s.length == 1) s else {
      var result: String = "";

      for (i <- 1 until s.length - 1) {
        val currentChar: String = s.charAt(i).toString
        val nextChar: String = s.charAt(i + 1).toString
        val prevChar: String = s.charAt(i - 1).toString

        if (nextChar == prevChar) {
          val res = longestPalindromeInner(s, i - 1, i + 1, currentChar)
          result = if (res.length > result.length) res else result
        }
        if (prevChar == currentChar) {
          val res = longestPalindromeInner(s, i - 2, i + 1, prevChar + currentChar)
          result = if (res.length > result.length) res else result
        }
        if (currentChar == nextChar) {
          val res = longestPalindromeInner(s, i - 1, i + 2, currentChar + nextChar)
          result = if (res.length > result.length) res else result
        }
      }
      if (result.nonEmpty) result else s.charAt(0).toString
    }
  }

  def longestPalindromeInner(s: String, prevIndex: Int, nextIndex: Int, result: String): String = {
    if (prevIndex < 0 || nextIndex >= s.length || prevIndex > nextIndex) result
    else {
      val prevStr = s.charAt(prevIndex).toString
      val nextSte = s.charAt(nextIndex).toString

      if (prevStr == nextSte)
        longestPalindromeInner(s, prevIndex - 1, nextIndex + 1, prevStr + result + prevStr)
      else result
    }
  }

  def longestPalindromeDP(s: String): String = {
    if (s.length == 1) s.charAt(0).toString
    else {
      var result: String = ""

      val length = s.length
      val dp: Array[Array[Int]] = Array.ofDim[Int](length, length)

      /*
          初始条件： dp[i][i] = 1 表示已经是回文字
          dp[i][i] = 0 where i != j
       */
      for (index <- 0 until length)
        dp(index)(index) = 1

      /*
         状态转移方程
         dp[i][j] = dp[i][i] && dp[i+1][j-1]
       */
      for (endIndex <- 1 until length) {
        for (startIndex <- 0 until endIndex) {
          if (s.charAt(startIndex) == s.charAt(endIndex)) {
            if (endIndex - startIndex < 3) {
              /*
                会有这样的情况 abb， 这个时候 endIndex = 3， startIndex = 2
                如果使用逻辑 dp(startIndex + 1)(endIndex - 1)， 那么就会得逻辑得到所有差乱
                startIndex=2+1=3
                endIndex=3-1=2
               */
              dp(startIndex)(endIndex) = 1
            } else
              dp(startIndex)(endIndex) = dp(startIndex + 1)(endIndex - 1)

            if (dp(startIndex)(endIndex) == 1 && s.substring(startIndex, endIndex + 1).length > result.length)
              result = s.substring(startIndex, endIndex + 1)
          }
        }
      }
      if (result.nonEmpty) result else s.charAt(0).toString
    }
  }

  //  println(longestPalindrome("babad"))
  //  println(longestPalindrome("b"))
  //  println(longestPalindrome("bbb"))
  //  println(longestPalindrome("bbbb"))
  //  println(longestPalindrome("cbbd"))
  //  println(longestPalindrome("abb"))
  //  println(longestPalindrome("aacabdkacaa"))

  println(longestPalindromeDP("bbbb"))
  println(longestPalindromeDP("aacabdkacaa"))
  println(longestPalindromeDP("cbbd"))
  println(longestPalindromeDP("babad"))
  println(longestPalindromeDP("dsfradc"))


}
