package dsa.list

import scala.util.control.Breaks._

class DoubleLinkedList {
  val head: HeroNode2 = new HeroNode2(0, "", "")

  // 直接添加到尾部
  def add(hero: HeroNode2): Unit = {
    // 头节点不能动，所以需要有临时节点作为辅助功能
    var tmp = head

    // 找链表最后
    while (tmp.next != null) {
      tmp = tmp.next;
    }
    tmp.next = hero
    hero.prev = tmp
  }

  // 根据排名，插入到指定位置
  def add2(hero: HeroNode2): Unit = {

    var tmp = head
    // 找添加位置时，是将几点加入到tmp 的后面， 在比较的时候，是将当前的heroNode 和 tmp.next 比较
    breakable {
      while (tmp != null) {
        if (tmp.next == null) {
          tmp.next = hero
          hero.prev = tmp
          break()
        } else if (tmp.next.no > hero.no) {
          // 找打待插入的位置
          val currentNext = tmp.next
          tmp.next = hero
          hero.prev = tmp

          hero.next = currentNext
          currentNext.prev = hero
          break()
        } else if (tmp.next.no == hero.no) {
          throw new Exception("hero no " + hero.no + " is already exist")
        }
        tmp = tmp.next
      }
    }
  }

  //遍历
  def list(): Unit = {
    if (head.next == null) {
      throw new Exception("empty list")
    }

    // 头结点并不关心，没有值
    var tmp = head.next
    while (tmp != null) {
      printf("No [%d] hero [%s] [%s]\n", tmp.no, tmp.nickName, tmp.name)
      tmp = tmp.next
    }
  }

  // 修改值 hName 和 hNickName
  def update(newHeroNode2: HeroNode2): Unit = {
    var tmp = head.next
    if (tmp == null) {
      println("empty list")
      return
    }

    while (tmp != null) {
      if (tmp.no == newHeroNode2.no) {

        tmp.name = newHeroNode2.name
        tmp.nickName = newHeroNode2.nickName
        println("更新成功")
        return
      }
      tmp = tmp.next
    }
  }

  def delete(no: Int): Unit = {
    // 注意删除的时候tmp 应该指向的是待删除的节点的前一个节点
    if (head.next == null) return
    var tmp = head
    while (tmp.no != no) {
      tmp = tmp.next
    }

    if (tmp.no == no) {
      val tempPrev = tmp.prev
      val tmpNext = tmp.next

      tempPrev.next = tmpNext

      if(tmpNext != null)
        tmpNext.prev = tempPrev

      tmp = null
    }
  }
}

object DoubleLinkedList {
  def main(args: Array[String]): Unit = {
    val node = new HeroNode2(1, "宋江", "及时雨")
    val node2 = new HeroNode2(2, "宋江2", "及时雨2")
    val node3 = new HeroNode2(3, "宋江3", "及时雨3")
    val node4 = new HeroNode2(4, "宋江4", "及时雨4")
    val node5 = new HeroNode2(5, "宋江5", "及时雨5")

    val newNode = new HeroNode2(3, "无用", "智多星")


    val list = new DoubleLinkedList

    list.add2(node3)
    list.add2(node5)
    list.add2(node2)
    list.add2(node)
    list.add2(node4)

    list.list()

    list.update(newNode)

    list.delete(2)
    list.delete(1)
    list.delete(5)

    list.list()
  }
}

