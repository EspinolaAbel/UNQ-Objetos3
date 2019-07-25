package ar.edu.unq.o3.model

import ar.edu.unq.o3.{Abajo, Arriba, Direccion}

case class Tablero(posiciones: List[Ubicacion]) {

  def mover(direccion: Direccion, buzo: Buzo): Tablero = {
    Tablero(buscarBuzoYMoverEnDireccion(direccion, buzo, List(), this.posiciones))
  }

  def buscarBuzoYMoverEnDireccion(
                           direccion: Direccion,
                           buzoAMover: Buzo,
                           posicionesInferiores: List[Ubicacion],
                           posicionesSuperiores: List[Ubicacion]
                          ): List[Ubicacion] = (direccion, buzoAMover, posicionesInferiores, posicionesSuperiores) match {
    case (direccion, buzoAMover, posicionesInferiores, List()) => posicionesInferiores
    case (direccion, buzoAMover, posicionesInferiores, posicion::posicionesSuperiores) =>
      if ( posicion.buzos.exists(b => buzoAMover.equals(b)) )
        moverBuzoEnDireccion(direccion, buzoAMover, posicionesInferiores, posicion, posicionesSuperiores)
      else
        buscarBuzoYMoverEnDireccion(direccion, buzoAMover, posicionesInferiores, posicionesSuperiores)
  }

  def moverBuzoEnDireccion(
                   direccion: Direccion,
                   buzo: Buzo,
                   posicionesInferiores: List[Ubicacion],
                   posicion: Ubicacion,
                   posicionesSuperiores: List[Ubicacion]
                  ) : List[Ubicacion] = (direccion, buzo, posicionesInferiores, posicion, posicionesSuperiores) match {
    case (Arriba, buzo, posicionesInferiores, Submarino(buzos, reliquias), posicionesSuperiores) =>
      posicionesInferiores ++ (sacarBuzo(posicion, buzo) :: posicionesSuperiores)
    case (Abajo, buzo, posicionesInferiores, Submarino(buzos, reliquias), posicionesSuperiores) =>
      posicionesInferiores ++ (sacarBuzo(posicion, buzo):: posicionesSuperiores)
  }

  def sacarBuzo(posicion: Ubicacion, buzo: Buzo) : Ubicacion = (posicion, buzo) match {
    case Submarino(buzos, reliquias) => Submarino(buzos.filter(b => buzo.equals(b)), reliquias)
    case Casillero(buzos, reliquias) => Casillero(buzos.filter(b => buzo.equals(b)), reliquias)
  }

  def ponerBuzoEnPrimeraPosicion (posiciones: List[Ubicacion], buzo: Buzo): List[Ubicacion]  = {
    var primerPosicion = posiciones.head
    var restoDeElementos = posiciones.tail
  }

//  def removerBuzoDePosicion(posicion: Ubicacion, buzo: Buzo) : Ubicacion = (posicion, buzo) match {
//    Nil
//  }

}

abstract class Ubicacion {
  val buzos: List[Buzo]
  val reliquias: List[Reliquia]
}

case class Submarino( buzos: List[Buzo], reliquias: List[Reliquia] ) extends Ubicacion
case class Casillero( buzos: List[Buzo], reliquias: List[Reliquia] ) extends Ubicacion
