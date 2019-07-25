package ar.edu.unq.o3.model

case class Buzo(nombre: String, reliquias: List[Reliquia]) { }

case class Reliquia(valor: Int) { }

case class TanqueDeOxigeno(unidades: Int) { }
