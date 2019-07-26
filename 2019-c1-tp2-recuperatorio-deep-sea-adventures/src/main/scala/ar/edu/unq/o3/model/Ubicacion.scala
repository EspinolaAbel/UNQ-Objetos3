package ar.edu.unq.o3.model

class Ubicacion

case class Submarino(buzos: List[Buzo]) extends Ubicacion
case class Casillero( buzo: Buzo, reliquia: Reliquia, profundidad: Profundidad) extends Ubicacion

class Profundidad(minValor: Int, maxValor: Int)

case object Maxima extends Profundidad(12,15)
case object Alta   extends Profundidad( 8,11)
case object Media  extends Profundidad( 4, 7)
case object Baja   extends Profundidad( 0, 3)
