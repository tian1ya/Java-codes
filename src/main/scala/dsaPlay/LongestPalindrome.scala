package dsaPlay

object LongestPalindrome extends App {
  def longestPalindrome(s: String): String = {

    if (s.length == 1)
      s
    else if (s.length == 2) {
      if (s.charAt(0) == s.charAt(1))
        s
      else
        s.charAt(0).toString
    } else {
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
    if (prevIndex < 0 || nextIndex >= s.length || prevIndex > nextIndex)
      result
    else {
      val prevStr = s.charAt(prevIndex).toString
      val nextSte = s.charAt(nextIndex).toString

      if (prevStr == nextSte) {
        val res: String = prevStr + result + prevStr
        longestPalindromeInner(s, prevIndex - 1, nextIndex + 1, res)
      } else result
    }
  }

  //  println(longestPalindrome("babad"))
  //  println(longestPalindrome("b"))
  //  println(longestPalindrome("bbb"))
  println(longestPalindrome("bbbb"))
  //  println(longestPalindrome("cbbd"))
  //  println(longestPalindrome("abb"))
  //  println(longestPalindrome("aacabdkacaa"))

}
