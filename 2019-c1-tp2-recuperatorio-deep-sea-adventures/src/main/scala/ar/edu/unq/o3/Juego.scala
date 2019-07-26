package ar.edu.unq.o3

import ar.edu.unq.o3.model.{Buzo, Tablero, TanqueDeOxigeno}

class Juego {
  var rondas: List[Ronda] = List()
  var buzos: List[Buzo] = List()
  var tablero: Tablero = null
  var tanqueDeOxigeno: TanqueDeOxigeno = TanqueDeOxigeno(25)
}

class Ronda() {}

class Direccion { }

case object Subir extends Direccion {}
case object Bajar extends Direccion {}

class Accion
case object RecogerReliquia extends Accion
case object AbandonarReliquia extends Accion
case object NoHacerNada extends Accion

class Aleatoriedad() {
  def numeroEnRangoDado(valorMinimo: Int, valorMaximo: Int)= valorMinimo + (Math.random() * (valorMaximo - valorMinimo) )
}

class MockAleatoriedad extends Aleatoriedad {
  var valor: Int = 0
  def definirValorMock(valorMock: Int) = valor = valorMock
  override def numeroEnRangoDado(valorMinimo: Int, valorMaximo: Int)= valor
}
