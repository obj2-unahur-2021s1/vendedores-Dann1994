package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  val certificacion1 = Certificacion( true, 100)
  val certificacion2 = Certificacion( false, 20)
  val certificacion3 = Certificacion( true, 150)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }
    it ("Es influyente") {
      vendedorFijo.esInfluyente().shouldBeFalse()
    }

    describe("certificaciones") {
      vendedorFijo.agragarCertificacion(certificacion2)

      it ("Es versatil") {
        vendedorFijo.esVersatil().shouldBeFalse()
      }
      it ("Es firme") {
        vendedorFijo.esFirme().shouldBeFalse()
      }
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }
    it ("Es influyente") {
      viajante.esInfluyente().shouldBeFalse()
    }

    describe("certificaciones") {
      viajante.agragarCertificacion(certificacion1)
      viajante.agragarCertificacion(certificacion2)
      viajante.agragarCertificacion(certificacion3)

      it ("Es versatil") {
        viajante.esVersatil().shouldBeTrue()
      }
      it ("Es firme") {
        viajante.esFirme().shouldBeTrue()
      }
    }
  }
})
