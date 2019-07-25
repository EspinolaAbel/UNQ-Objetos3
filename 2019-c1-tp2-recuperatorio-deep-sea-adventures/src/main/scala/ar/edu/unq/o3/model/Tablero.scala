package ar.edu.unq.o3.model

import ar.edu.unq.o3.{Abajo, Arriba, Direccion}

case class Tablero(posiciones: List[Casillero]) {

  def mover(direccion: Direccion, buzo: Buzo): Tablero = {
//    Tablero(buscarBuzoYMoverEnDireccion(direccion, buzo, List(), this.posiciones))
//      Tablero(buscarBuzoYMoverEnDireccion(direccion, buzo, List(), this.posiciones))
//    Option.empty[Ubicacion] {}
null
  }

  def buscarBuzoYMoverEnDireccion(direccion: Direccion, buzo: Buzo, posiciones: List[Casillero]) : List[Casillero] = {
    posiciones.span(_.buzo == buzo)
    direccion match {
      case Arriba => posiciones.foldRight(List[Casillero]()) { (i,acc) => i::acc }
      case Abajo => posiciones.foldLeft(List[Casillero]()) { (acc,i) => i::acc }.reverse
    }
  }

  def buscar(
              casillerosAnteriores: List[Casillero],
              casilleroActual: Casillero,
              casillerosSiguientes: List[Casillero],
              buzo: Buzo) : (List[Casillero], Casillero, List[Casillero], Buzo) = {

    (casillerosAnteriores, casilleroActual, casillerosSiguientes) match {
      case (p, c, List()) => (p, c, List(), buzo)
      case (List(), null, sigCasillero::sigs) => buscar(List(), sigCasillero, sigs, buzo)
      case (prevs, Casillero(Buzo(buzo.nombre,_), r), sigs) => (prevs, Casillero(buzo, r), sigs, buzo)
      case (prevs, casillero, sigCasillero::sigs) => buscar((prevs :+ casillero), sigCasillero, sigs, buzo)
    }

  }

//  def buscarBuzoYMoverEnDireccion(
//                           direccion: Direccion,
//                           buzoAMover: Buzo,
//                           posicionesInferiores: List[Ubicacion],
//                           posicionesSuperiores: List[Ubicacion]
//                          ): List[Ubicacion] = (direccion, buzoAMover, posicionesInferiores, posicionesSuperiores) match {
//    case (direccion, buzoAMover, posicionesInferiores, List()) => posicionesInferiores
//    case (direccion, buzoAMover, posicionesInferiores, posicion::posicionesSuperiores) =>
//      if ( posicion.buzos.exists(b => buzoAMover.equals(b)) )
//        moverBuzoEnDireccion(direccion, buzoAMover, posicionesInferiores, posicion, posicionesSuperiores)
//      else
//        buscarBuzoYMoverEnDireccion(direccion, buzoAMover, posicionesInferiores, posicionesSuperiores)
//  }
//
//  def moverBuzoEnDireccion(
//                   direccion: Direccion,
//                   buzo: Buzo,
//                   posicionesInferiores: List[Ubicacion],
//                   posicion: Ubicacion,
//                   posicionesSuperiores: List[Ubicacion]
//                  ) : List[Ubicacion] = (direccion, buzo, posicionesInferiores, posicion, posicionesSuperiores) match {
//    case (Arriba, buzo, posicionesInferiores, Submarino(buzos, reliquias), posicionesSuperiores) =>
//      posicionesInferiores ++ (sacarBuzo(posicion, buzo) :: posicionesSuperiores)
//    case (Abajo, buzo, posicionesInferiores, Submarino(buzos, reliquias), posicionesSuperiores) =>
//      posicionesInferiores ++ (sacarBuzo(posicion, buzo):: posicionesSuperiores)
//  }
//
//  def sacarBuzo(posicion: Ubicacion, buzo: Buzo) : Ubicacion = (posicion, buzo) match {
//    case Submarino(buzos, reliquias) => Submarino(buzos.filter(b => buzo.equals(b)), reliquias)
//    case Casillero(buzos, reliquias) => Casillero(buzos.filter(b => buzo.equals(b)), reliquias)
//  }
//
//  def ponerBuzoEnPrimeraPosicion (posiciones: List[Ubicacion], buzo: Buzo): List[Ubicacion]  = {
//    var primerPosicion = posiciones.head
//    var restoDeElementos = posiciones.tail
//  }

//  def removerBuzoDePosicion(posicion: Ubicacion, buzo: Buzo) : Ubicacion = (posicion, buzo) match {
//    Nil
//  }

}

case class Casillero( buzo: Buzo, reliquia: Reliquia )
