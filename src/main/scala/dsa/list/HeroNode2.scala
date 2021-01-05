package dsa.list

class HeroNode2(hNo: Int, hName: String, hNickName: String) {
  val no: Int = hNo
  var name: String = hName
  var nickName: String = hNickName
  var next: HeroNode2 = null;
  var prev: HeroNode2 = null
}
