import ar.edu.unq.o3.model._
import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}

class TableroSpec extends FunSpec with Matchers with BeforeAndAfter {

  describe("description") {
    var tablero: Tablero = null
    var buzoBuscado: Buzo = null
    var ls: List[Casillero] = null
    var expected: List[Casillero] = null

    before {
      buzoBuscado =  Buzo("buzo1", List())
      ls = List (
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja)
      )
      expected = List (
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja)
      )

      tablero = Tablero(ls)
    }

    it ("busco y encuentro un buzo") {
      var ls = List (
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja)
      )
      var resultado = tablero.buscar(List(), null, ls, buzoBuscado)
      assert(resultado._2 == Casillero(buzoBuscado,null, Baja))
    }

    it ("muevo al buzo buscado un lugar hacia abajo") {
      var posicionesIniciales = List (
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja)
      )
      var expected = List (
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja)
      )

      var tablero = Tablero(posicionesIniciales).mover(Bajar, buzoBuscado)

      assert(tablero.ubicaciones == expected)
    }

    it ("muevo al buzo buscado un lugar hacia arriba") {
      var posicionesIniciales = List (
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja)
      )

      var expected = List (
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja)
      )

      var tablero = Tablero(posicionesIniciales).mover(Subir, buzoBuscado)

      assert(tablero.ubicaciones == expected)
    }

    it ("muevo al buzo buscado un lugar hacia abajo pero el lugar ya esta ocupado por otro buzo asi que baja 1 mas") {
      var posicionesIniciales = List (
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(Buzo("buzo2",null), null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
      )
      var expected = List (
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(Buzo("buzo2",null), null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
      )

      var tablero = Tablero(posicionesIniciales).mover(Bajar, buzoBuscado)

      assert(tablero.ubicaciones == expected)
    }

    it ("muevo al buzo buscado un lugar hacia arriba pero el lugar ya esta ocupado por otro buzo asi que sube 1 mas") {
      var posicionesIniciales = List (
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(Buzo("buzo2",null), null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
      )

      var expected = List (
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(Buzo("buzo2",null), null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
      )

      var tablero = Tablero(posicionesIniciales).mover(Subir, buzoBuscado)

      assert(tablero.ubicaciones == expected)
    }

    it ("muevo al buzo buscado un lugar hacia abajo pero el lugar ya esta ocupado por otro buzo asi que baja 1 mas y termina en un borde") {
      var posicionesIniciales = List (
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(Buzo("buzo2",null), null, Baja),
        Casillero(null, null, Baja),
      )
      var expected = List (
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(Buzo("buzo2",null), null, Baja),
        Casillero(buzoBuscado, null, Baja),
      )

      var tablero = Tablero(posicionesIniciales).mover(Bajar, buzoBuscado)

      assert(tablero.ubicaciones == expected)
    }

    it ("muevo al buzo buscado un lugar hacia arriba pero el lugar ya esta ocupado por otro buzo asi que sube 1 mas y termina en un borde") {
      var posicionesIniciales = List (
        Casillero(null, null, Baja),
        Casillero(Buzo("buzo2",null), null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
      )

      var expected = List (
        Casillero(buzoBuscado, null, Baja),
        Casillero(Buzo("buzo2",null), null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
      )

      var tablero = Tablero(posicionesIniciales).mover(Subir, buzoBuscado)

      assert(tablero.ubicaciones == expected)
    }

    it ("intento mover al buzo buscado 0 lugares hacia abajo pero queda donde esta") {
      var posicionesIniciales = List (
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
      )
      var expected = List (
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
      )

      var tablero = Tablero(posicionesIniciales).mover(Bajar, buzoBuscado, 0)

      assert(tablero.ubicaciones == expected)
    }

    it ("intento mover al buzo buscado 0 lugares hacia arriba pero queda donde esta") {
      var posicionesIniciales = List (
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
      )

      var expected = List (
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(buzoBuscado, null, Baja),
        Casillero(null, null, Baja),
      )

      var tablero = Tablero(posicionesIniciales).mover(Subir, buzoBuscado, 0)

      assert(tablero.ubicaciones == expected)
    }
  }
}
