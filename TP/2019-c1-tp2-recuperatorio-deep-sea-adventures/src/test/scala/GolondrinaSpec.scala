import org.scalatest.{FunSpec, Matchers}
import ar.edu.unq.o3.Golondrina
import ar.edu.unq.o3.model.{Buzo, Reliquia}

class GolondrinaSpec extends FunSpec with Matchers {

  describe("Una Golondrina") {
     it("debe inicializarse con energia") {
       val g = new Golondrina()
       g.energia should equal (100)
     }
  }

  describe("Test inmutabilidad") {
    it("Al intentar cambiar el contenido de un objeto inmutable, lanza error") {
      val aux = Testing ("Hola mundo", 1)
      println(aux)
    }

    it("probando listas") {
      var a: List[Int] = List[Int] ()
      assert(a.size == 0)
      var b: List[Int] = List[Int] (1,2,3,4)
      assert(b.size == 4)
    }

    it("sladfjasdkldfjalkfj") {
//      var a = 1 :: List[Int]()
//      var b = List(1,2,3).foldRight(List[Int]()) { (i, acc) => i :: acc }
//      println("right: " + b)
//      var c = List(1,2,3).foldLeft(List[Int]()) { (acc,i) => i :: acc }
//      println("left: " + b)
      var d = List[Int](1,2,3,4,5,6).span( (i:Int) => {println(i!=4); i!=4} )
      println(d)
    }

  }

}

case class Testing (msg: String, nro: Int);
