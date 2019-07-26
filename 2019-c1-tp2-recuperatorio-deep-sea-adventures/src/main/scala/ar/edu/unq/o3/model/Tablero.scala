package ar.edu.unq.o3.model

import ar.edu.unq.o3.{Bajar, Subir, Direccion}

case class Tablero(ubicaciones: List[Ubicacion]) {

  def mover(direccion: Direccion, buzo: Buzo, cantidadDePosicionesAMover: Int): Tablero = {
    var resultadoBusqueda = buscar(List(), null, this.ubicaciones, buzo)
    direccion match {
      case Subir => {
        var ls = resultadoBusqueda._1 :+ sacarBuzo(buzo, resultadoBusqueda._2)
        var desplazamiento = calcularDesplazamiento(ls.reverse, cantidadDePosicionesAMover)
        Tablero(desplazar(buzo, ls.reverse, desplazamiento).reverse ++ resultadoBusqueda._3) //tengo que dar vuelta la lista para que el submarino quede al final
      }
      case Bajar => {
        var ls = sacarBuzo(buzo, resultadoBusqueda._2) :: resultadoBusqueda._3
        var desplazamiento = calcularDesplazamiento(ls.reverse, cantidadDePosicionesAMover)
        Tablero(resultadoBusqueda._1 ++ desplazar(buzo, ls, desplazamiento)) //tengo que dar vuelta la lista para que el submarino quede al final
      }
    }
  }

  def calcularDesplazamiento(ubicaciones: List[Ubicacion], cantidadDePosicionesAMover: Int): Int = {
    var cantidadUbicacionesDisponibles = contarUbicacionesDisponibles(ubicaciones)
    if (cantidadUbicacionesDisponibles < cantidadDePosicionesAMover)
      return cantidadUbicacionesDisponibles
    else
      return cantidadDePosicionesAMover
  }

  def mover(direccion: Direccion, buzo: Buzo): Tablero = {
    mover(direccion, buzo, 1)
  }

  private def contarUbicacionesDisponibles(ubicaciones: List[Ubicacion]): Int = {
    ubicaciones match {
      case List() => 0
      case List(Submarino(_)) => 1
      case Casillero(_,_,_)::ls => 1 + contarUbicacionesDisponibles(ls)
    }
  }

  private def desplazar(buzo: Buzo, ubicaciones: List[Ubicacion], cantidad: Int): List[Ubicacion] = {
    (ubicaciones, cantidad) match {
      case (List(), _) => List()
      case (Submarino(lsBuzos)::ls, 0) => Submarino(buzo::lsBuzos)::desplazar(buzo,ls,cantidad-1)
      case (Casillero(null,rs,pf)::ls, 0) => Casillero(buzo,rs,pf) :: desplazar(buzo,ls,cantidad-1)
      case (ubicacion::ls, 0) => ubicacion :: desplazar(buzo,ls,0)
      case (ubicacion::ls, _) => ubicacion :: desplazar(buzo,ls,cantidad-1)
    }
  }

//  private def moverBuzoAlFinal(buzo: Buzo, casilleros: List[Casillero]): List[Casillero] = {
//    var c = casilleros.last
//    casilleros.init :+ Casillero(buzo, c.reliquia, c.profundidad)
//  }
//  private def moverBuzoAlInicio(buzo: Buzo, casilleros: List[Casillero]): List[Casillero] = {
//    var c = casilleros.head
//    Casillero(buzo, c.reliquia, c.profundidad) :: casilleros.tail
//  }
  private def sacarBuzo(buzo: Buzo, ubicacion: Ubicacion): Ubicacion = {
    ubicacion match {
      case Casillero(unBuzo, reliquia, profundidad) => Casillero(null, reliquia, profundidad)
      case Submarino(ls) => Submarino(ls.filter(_.nombre != buzo.nombre))
    }
  }

  def buscar(
              ubicacionesAnteriores: List[Ubicacion],
              ubicacionActual: Ubicacion,
              ubicacionesSiguientes: List[Ubicacion],
              buzo: Buzo) : (List[Ubicacion], Ubicacion, List[Ubicacion], Buzo) = {

    (ubicacionesAnteriores, ubicacionActual, ubicacionesSiguientes) match {
      case (p, c, List()) => (p, c, List(), buzo)
      case (List(), null, sigCasillero::sigs) => buscar(List(), sigCasillero, sigs, buzo)
      case (prevs, Casillero(Buzo(buzo.nombre,_), r, p), sigs) => (prevs, Casillero(buzo, r, p), sigs, buzo)
      case (prevs, casillero, sigCasillero::sigs) => buscar((prevs :+ casillero), sigCasillero, sigs, buzo)
    }

  }

}

class Ubicacion

case class Submarino(buzos: List[Buzo]) extends Ubicacion
case class Casillero( buzo: Buzo, reliquia: Reliquia, profundidad: Profundidad) extends Ubicacion

class Profundidad(minValor: Int, maxValor: Int)

case object Maxima extends Profundidad(12,15)
case object Alta   extends Profundidad( 8,11)
case object Media  extends Profundidad( 4, 7)
case object Baja   extends Profundidad( 0, 3)
