package ar.edu.unq.o3.model

import ar.edu.unq.o3._

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
        Tablero(resultadoBusqueda._1 ++ desplazar(buzo, ls, desplazamiento))
      }
    }
  }

  def mover(direccion: Direccion, buzo: Buzo): Tablero = {
    mover(direccion, buzo, 1)
  }

  def calcularDesplazamiento(ubicaciones: List[Ubicacion], cantidadDePosicionesAMover: Int): Int = {
    var cantidadUbicacionesDisponibles = contarUbicacionesDisponibles(ubicaciones)
    if (cantidadUbicacionesDisponibles < cantidadDePosicionesAMover)
      return cantidadUbicacionesDisponibles
    else
      return cantidadDePosicionesAMover
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
      case (p, Submarino(buzos), s) if buzos.exists(_.nombre == buzo.nombre) => (p,Submarino(buzos),s,buzo)
      case (p, c, List()) => (p, c, List(), buzo)
      case (List(), null, sigCasillero::sigs) => buscar(List(), sigCasillero, sigs, buzo)
      case (prevs, Casillero(Buzo(buzo.nombre,_), r, p), sigs) => (prevs, Casillero(buzo, r, p), sigs, buzo)
      case (prevs, casillero, sigCasillero::sigs) => buscar((prevs :+ casillero), sigCasillero, sigs, buzo)
    }

  }

  def accionEnUbicacion(buzo: Buzo, accion: Accion): Tablero = {
    val resBusq = buscar(List(), null, ubicaciones, buzo)

    resBusq._2 match {
      case Submarino(_) => this
      case Casillero(_,null,p) => {
        accion match {
          case AbandonarReliquia =>
            Tablero(
              resBusq._1 ++ (Casillero(Buzo(buzo.nombre,buzo.reliquias.tail), buzo.reliquias.head,p)::resBusq._3)
            )
        }
      }
      case Casillero(_,r,p) => {
        accion match {
          case RecogerReliquia =>
            Tablero(
              resBusq._1 ++ (Casillero(Buzo(buzo.nombre,r::buzo.reliquias), null,p)::resBusq._3)
            )
        }
      }
    }
  }


}

class Direccion { }
case object Subir extends Direccion {}
case object Bajar extends Direccion {}
