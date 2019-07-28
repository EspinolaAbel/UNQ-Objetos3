package ar.edu.unq.o3

import ar.edu.unq.o3.model._

class Juego {
  var rondas: List[Ronda] = List()
  var buzos: List[Buzo] = List()
  var tablero: Tablero = null
  var tanqueDeOxigeno: TanqueDeOxigeno = TanqueDeOxigeno(25)
//  var submarino: Submarino

  def iniciarJuego(): Unit = {
    val random = new Aleatoriedad
    val buzos = (1 to 10).map(i=> Buzo("buzo"+i, List())).toList
    var ubicaciones = List(
      Submarino(buzos),
      Casillero(null,Reliquia(random.numeroEnRangoDado(0,3).toInt),Baja),
      Casillero(null,Reliquia(random.numeroEnRangoDado(0,3).toInt),Baja),
      Casillero(null,Reliquia(random.numeroEnRangoDado(0,3).toInt),Baja),
      Casillero(null,Reliquia(random.numeroEnRangoDado(0,3).toInt),Baja),
      Casillero(null,Reliquia(random.numeroEnRangoDado(0,3).toInt),Baja),
      Casillero(null,Reliquia(random.numeroEnRangoDado(0,3).toInt),Baja),
      Casillero(null,Reliquia(random.numeroEnRangoDado(0,3).toInt),Baja),
      Casillero(null,Reliquia(random.numeroEnRangoDado(0,3).toInt),Baja),
      Casillero(null,Reliquia(random.numeroEnRangoDado(4,7).toInt),Media),
      Casillero(null,Reliquia(random.numeroEnRangoDado(4,7).toInt),Media),
      Casillero(null,Reliquia(random.numeroEnRangoDado(4,7).toInt),Media),
      Casillero(null,Reliquia(random.numeroEnRangoDado(4,7).toInt),Media),
      Casillero(null,Reliquia(random.numeroEnRangoDado(4,7).toInt),Media),
      Casillero(null,Reliquia(random.numeroEnRangoDado(4,7).toInt),Media),
      Casillero(null,Reliquia(random.numeroEnRangoDado(4,7).toInt),Media),
      Casillero(null,Reliquia(random.numeroEnRangoDado(4,7).toInt),Media),
      Casillero(null,Reliquia(random.numeroEnRangoDado(8,11).toInt),Alta),
      Casillero(null,Reliquia(random.numeroEnRangoDado(8,11).toInt),Alta),
      Casillero(null,Reliquia(random.numeroEnRangoDado(8,11).toInt),Alta),
      Casillero(null,Reliquia(random.numeroEnRangoDado(8,11).toInt),Alta),
      Casillero(null,Reliquia(random.numeroEnRangoDado(8,11).toInt),Alta),
      Casillero(null,Reliquia(random.numeroEnRangoDado(8,11).toInt),Alta),
      Casillero(null,Reliquia(random.numeroEnRangoDado(8,11).toInt),Alta),
      Casillero(null,Reliquia(random.numeroEnRangoDado(8,11).toInt),Alta),
      Casillero(null,Reliquia(random.numeroEnRangoDado(12,15).toInt),Maxima),
      Casillero(null,Reliquia(random.numeroEnRangoDado(12,15).toInt),Maxima),
      Casillero(null,Reliquia(random.numeroEnRangoDado(12,15).toInt),Maxima),
      Casillero(null,Reliquia(random.numeroEnRangoDado(12,15).toInt),Maxima),
      Casillero(null,Reliquia(random.numeroEnRangoDado(12,15).toInt),Maxima),
      Casillero(null,Reliquia(random.numeroEnRangoDado(12,15).toInt),Maxima),
      Casillero(null,Reliquia(random.numeroEnRangoDado(12,15).toInt),Maxima),
      Casillero(null,Reliquia(random.numeroEnRangoDado(12,15).toInt),Maxima),
    )

    this.tablero = Tablero(ubicaciones)

    this.rondas = List.fill(3)(new Ronda)
    rondas.foreach( r =>
      r.turnos = buzos.map(
        b => new Turno(b,this)
      )
    )
  }

//  def obtenerSiguienteTurno(): Turno = {
//    if (tanqueDeOxigeno.unidades == 0 || getSubmarino().buzos.length == buzos.length)
//      // pasar a siguiente ronda
//  }

  def getSubmarino(): Submarino = {
    this.tablero.ubicaciones match {
      case Submarino(ls)::_ => Submarino(ls)
    }
  }

}

class Ronda() {
  var turnos: List[Turno] = null
}

class Aleatoriedad() {
  def numeroEnRangoDado(valorMinimo: Int, valorMaximo: Int)= valorMinimo + (Math.random() * (valorMaximo - valorMinimo) )
}

class MockAleatoriedad extends Aleatoriedad {
  var valor: Int = 0
  def definirValorMock(valorMock: Int) = valor = valorMock
  override def numeroEnRangoDado(valorMinimo: Int, valorMaximo: Int)= valor
}
