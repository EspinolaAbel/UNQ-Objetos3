package ar.edu.unq.o3

import ar.edu.unq.o3.model.{Buzo, Tablero}

class Juego {
  var rondas: List[Ronda] = List()
}

case class Ronda() {}

case class Turno() {}

trait Direccion { }

case object Arriba extends Direccion {}

case object Abajo extends Direccion {}
