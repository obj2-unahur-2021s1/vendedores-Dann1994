package ar.edu.unahur.obj2.vendedores

class Certificacion(val esDeProducto: Boolean, val puntaje: Int)

abstract class Vendedor () {
  val certificaciones = mutableListOf<Certificacion>()

  abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean

  abstract fun esInfluyente() : Boolean

  fun esVersatil() =
    certificaciones.size >= 3
    && this.certificacionesDeProducto() >= 1
    && this.otrasCertificaciones() >= 1

  fun esFirme() = this.puntajeCertificaciones() >= 30

  fun puntajeCertificaciones() = certificaciones.sumBy {it.puntaje}

  fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto }
  fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto }
  fun esGenerico() = this.otrasCertificaciones() >= 1

  fun agragarCertificacion (unaCertificacion: Certificacion) {
    certificaciones.add(unaCertificacion)
  }
}

class VendedorFijo (val ciudadDeOrigen: Ciudad) : Vendedor() {

  override fun esInfluyente() = false

  override fun puedeTrabajarEn(ciudad: Ciudad) = ciudad == ciudadDeOrigen

}

class Viajante (val provinciasHabilitadas: List<Provincia>) : Vendedor() {

  override fun esInfluyente() = provinciasHabilitadas.sumBy { it.poblacion } >= 10000000

  override fun puedeTrabajarEn(ciudad: Ciudad) = provinciasHabilitadas.contains(ciudad.provincia)
}

class ComercioCorresponsal (val ciudades: List<Ciudad>) : Vendedor() {

  override fun esInfluyente() = ciudades.size >= 5 || (ciudades.map { it.provincia }.toSet()).size >= 3

  override fun puedeTrabajarEn(ciudad: Ciudad) = ciudades.contains(ciudad)
}