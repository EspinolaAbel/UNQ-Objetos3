package ar.edu.unq.o3.model

import ar.edu.unq.o3.{Aleatoriedad, Juego}

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

  def nadar(): Unit = {
    val cantidadDePosicionesAMover = arrojarDado()
    var cantidadAMoverConReliquias = cantidadDePosicionesAMover - this.buzo.reliquias.length
    if (cantidadAMoverConReliquias < 0)
      return
    juego.tablero = juego.tablero.mover(this.direccion, this.buzo, cantidadAMoverConReliquias)
  }

  def arrojarDado(): Int = Math.floor(this.aleatoriedad.numeroEnRangoDado(0,5)).toInt

  def recogerTesoro(accion: Accion): Unit = {
    juego.tablero = juego.tablero.accionEnUbicacion(this.buzo, accion)
  }

}

class Accion
case object RecogerReliquia extends Accion
case object AbandonarReliquia extends Accion
case object NoHacerNada extends Accion