import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import ar.edu.unq.o3.{Bajar, Juego, Subir, Turno}
import ar.edu.unq.o3.model._

class TurnoSpec extends FunSpec with Matchers with BeforeAndAfter {

  var juego: Juego = new Juego

  describe("consumo de oxigeno") {

    var turno: Turno = null

    it ("en un turno un buzo sin reliquias no consume oxigeno") {
      var buzo = Buzo("buzo1", List())
      var tanque = juego.tanqueDeOxigeno
      var expectedUnidadesDeTanque = tanque.unidades
      turno = new Turno(buzo, juego)

      turno.consumirOxigeno()

      assert(tanque.unidades == expectedUnidadesDeTanque)
    }

    it ("en un turno un buzo con 3 reliquias consume 3 unidades de oxigeno") {
      var reliquias = List[Reliquia](Reliquia(0), Reliquia(0), Reliquia(0))
      var buzoCon3Reliquias = Buzo("buzo1", reliquias)
      var expectedUnidadesDeTanque = juego.tanqueDeOxigeno.unidades - 3
      turno = new Turno(buzoCon3Reliquias, juego)

      turno.consumirOxigeno()

      assert(juego.tanqueDeOxigeno.unidades == expectedUnidadesDeTanque)
    }

  }

  describe("declarar intencion de subir o bajar") {

    it ("un buzo en su turno declara su intencion de subir") {
      var buzo = Buzo("buzo1", List())
      var turno = new Turno(buzo, juego)

      turno.elegirDireccionDeMovimiento(Subir)

      assert(turno.direccion == Subir)
    }

    it ("un buzo en su turno declara su intencion de bajar") {
      var buzo = Buzo("buzo1", List())
      var turno = new Turno(buzo, juego)

      turno.elegirDireccionDeMovimiento(Bajar)

      assert(turno.direccion == Bajar)
    }

  }

  describe("un buzo nada en una direccion") {

    var casilleros: List[Casillero] = null

    before {
      casilleros = List (
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Media),
        Casillero(null, null, Media),
        Casillero(null, null, Alta),
        Casillero(null, null, Alta),
        Casillero(null, null, Maxima),
        Casillero(null, null, Maxima)
      )
      juego.tablero = Tablero(casilleros)
    }

    it ("un buzo sin reliquias se mueve 3 casilleros hacia adelante") {

    }

  }

}
