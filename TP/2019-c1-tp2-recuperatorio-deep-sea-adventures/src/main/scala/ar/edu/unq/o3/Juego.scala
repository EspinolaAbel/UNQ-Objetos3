package ar.edu.unq.o3

import ar.edu.unq.o3.model.{Buzo, Tablero, TanqueDeOxigeno}

class Juego {
  var rondas: List[Ronda] = List()
  var buzos: List[Buzo] = List()
  var tablero: Tablero = null
  var tanqueDeOxigeno: TanqueDeOxigeno = TanqueDeOxigeno(25)
}

class Ronda() {}

class Turno(buzo: Buzo, juego: Juego) {
  var direccion: Direccion = null
  var aleatoriedad: Aleatoriedad = new Aleatoriedad

  def consumirOxigeno() = {
    var unidadesRestantes: Int = juego.tanqueDeOxigeno.unidades
    unidadesRestantes-= buzo.reliquias.length

    if ( unidadesRestantes < 0 )
      unidadesRestantes = 0

    juego.tanqueDeOxigeno = TanqueDeOxigeno(unidadesRestantes)
  }

  def elegirDireccionDeMovimiento(direccionDeMovimiento: Direccion) = {
    direccion = direccionDeMovimiento
  }

  def nadar()= {

  }

  def recogerTesoro() = {

  }

  def abandonarTesoro() = {

  }

}

class Direccion { }

case object Subir extends Direccion {}

case object Bajar extends Direccion {}

class Aleatoriedad() {
  def numeroEnRangoDado(valorMinimo: Int, valorMaximo: Int)= valorMinimo + (Math.random() * (valorMaximo - valorMinimo) )
}

class MockAleatoriedad extends Aleatoriedad {
  var valor: Int = 0
  def definirValorMock(valorMock: Int) = valor = valorMock
  override def numeroEnRangoDado(valorMinimo: Int, valorMaximo: Int)= valorMinimo + (Math.random() * (valorMaximo - valorMinimo) )
}
