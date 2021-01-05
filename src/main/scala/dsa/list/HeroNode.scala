package dsa.list

class HeroNode(hNo: Int, hName: String, hNickName:String) {
  val no: Int = hNo
  var name:String = hName
  var nickName: String = hNickName
  var next:HeroNode = null;
}
