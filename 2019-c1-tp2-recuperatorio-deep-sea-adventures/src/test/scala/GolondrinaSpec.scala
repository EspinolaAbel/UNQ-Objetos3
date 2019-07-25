import org.scalatest.{FunSpec, Matchers}
import ar.edu.unq.o3.Golondrina
import ar.edu.unq.o3.model.{Buzo, Reliquia, Submarino}

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

    it("creando un submarino") {
      println( Submarino(List[Buzo](), List[Reliquia]()) )
    }

  }

}

case class Testing (msg: String, nro: Int);
