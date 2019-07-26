import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}
import ar.edu.unq.o3._
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

    var ubicaciones: List[Ubicacion] = null
    var ubicaciones2: List[Ubicacion] = null
    val buzo = Buzo("buzo1", List())
    val buzoConReliquias = Buzo("buzo2", List(Reliquia(0),Reliquia(0)) )
    val turno = new Turno(buzo, juego)

    before {
      ubicaciones = List (
        Submarino(buzo::List()),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Media),
        Casillero(null, null, Media),
        Casillero(null, null, Alta),
        Casillero(null, null, Alta),
        Casillero(null, null, Maxima),
        Casillero(null, null, Maxima)
      )
      ubicaciones2 = List (
        Submarino(buzoConReliquias::List()),
        Casillero(null, null, Baja),
        Casillero(null, null, Baja),
        Casillero(null, null, Media),
        Casillero(null, null, Media),
        Casillero(null, null, Alta),
        Casillero(null, null, Alta),
        Casillero(null, null, Maxima),
        Casillero(null, null, Maxima)
      )
      juego.tablero = Tablero(ubicaciones)
      turno.aleatoriedad = new MockAleatoriedad
    }

    it ("un buzo sin reliquias se mueve 3 casilleros hacia abajo") {
      turno.aleatoriedad.asInstanceOf[MockAleatoriedad].valor = 3
      turno.elegirDireccionDeMovimiento(Bajar)
      turno.nadar()

      assert( juego.tablero.ubicaciones(3).asInstanceOf[Casillero].buzo == buzo )
    }
    it ("un buzo con 2 reliquias se quiere mover 3 casilleros hacia abajo pero se mueve 1 casillero hacia abajo") {
      var turno = new Turno(buzoConReliquias, juego)
      turno.aleatoriedad = new MockAleatoriedad
      turno.aleatoriedad.asInstanceOf[MockAleatoriedad].valor = 3
      juego.tablero = Tablero(ubicaciones2)
      turno.elegirDireccionDeMovimiento(Bajar)
      turno.nadar()

      assert( juego.tablero.ubicaciones(1).asInstanceOf[Casillero].buzo == buzoConReliquias )
    }

  }

  describe("recoger o abandonar una reliquia") {
    val buzoSinReliquia = Buzo("buzo",List())
    val buzoConReliquia = Buzo("buzoConReliquia",List(Reliquia(0)))
    val ubicacionConReliquia: List[Ubicacion] = List (
      Casillero(buzoSinReliquia, Reliquia(0), Baja),
    )
    val ubicacionSinReliquia: List[Ubicacion] = List (
      Casillero(buzoConReliquia, null, Baja),
    )

    it("un buzo recoge una reliquia de su posicion actual") {
      juego.tablero = Tablero(ubicacionConReliquia)
      val turno = new Turno(buzoSinReliquia, juego)

      turno.recogerTesoro(RecogerReliquia)

      val casillero = juego.tablero.ubicaciones(0).asInstanceOf[Casillero]
      val buzo = casillero.buzo

      assert(casillero.reliquia == null)
      assert(buzo.reliquias.length == 1)
    }

    it("un buzo abandona una reliquia de su posicion actual") {
      juego.tablero = Tablero(ubicacionSinReliquia)
      val turno = new Turno(buzoConReliquia, juego)

      turno.recogerTesoro(AbandonarReliquia)

      val casillero = juego.tablero.ubicaciones(0).asInstanceOf[Casillero]
      val buzo = casillero.buzo

      assert(casillero.reliquia != null)
      assert(buzo.reliquias.length == 0)
    }

  }

}
