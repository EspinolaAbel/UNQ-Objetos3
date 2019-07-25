import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import ar.edu.unq.o3.{Abajo, Arriba, Golondrina}
import ar.edu.unq.o3.model._

class TableroSpec extends FunSpec with Matchers with BeforeAndAfter {

  describe("description") {
    var tablero: Tablero = null
    var buscado: Buzo = null
    var ls: List[Casillero] = null
    var expected: List[Casillero] = null

    before {
      buscado =  Buzo("pepe", List())
      ls = List (
        Casillero(null, null),
        Casillero(null, null),
        Casillero(buscado, null),
        Casillero(null, null),
        Casillero(null, null),
        Casillero(null, null)
      )
      expected = List (
        Casillero(null, null),
        Casillero(null, null),
        Casillero(null, null),
        Casillero(null, null),
        Casillero(null, null),
        Casillero(null, null)
      )

      tablero = Tablero(ls)
    }

    it ("un test") {
      var resultado = tablero.buscar(List(), null, ls, buscado)
      assert(resultado._2 == Casillero(buscado,null))
    }

  }
}
