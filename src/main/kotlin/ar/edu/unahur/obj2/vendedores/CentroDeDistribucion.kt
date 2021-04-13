package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion ( val ciudad: Ciudad ) {
    val vendedores = mutableListOf<Vendedor>() 

    fun contratarVendedor(unVendedor: Vendedor) {
        check (vendedores.contains(unVendedor)) {
            //El vendedor ya está contratado.
        }
        vendedores.add(unVendedor)
    }

    fun vendedorEstrella() = vendedores.maxBy { it.puntajeCertificaciones() }

    fun puedeCubrir(unaCiudad: Ciudad) = vendedores.any {it.puedeTrabajarEn(unaCiudad)}

    fun vendedoresGenericos() = vendedores.filter { it.esGenerico() }

    fun esRobusto() = vendedores.count { it.esFirme() } >= 3
}