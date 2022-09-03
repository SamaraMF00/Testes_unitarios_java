/**
 * author: Samara Ferreira
 * date: 03/09/2022
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VeiculoTest {

   public static Veiculo veiculo;

   @Test
   public void testeParaRegistrarDataAquisicaoValida(){
      veiculo = new Veiculo();
      assertEquals("Data de aquisição registrada com sucesso.", 
      veiculo.registraDataAquisicao(1,5,2022));
   }

   @Test
   public void testeParaRegistrarDataAquisicaoInvalida(){
      veiculo = new Veiculo();
      assertEquals("DATA INVÁLIDA.", 
      veiculo.registraDataAquisicao(30,2,2022));      
   }

   @Test 
   public void testeParaAdicionarRota(){
      veiculo = new Veiculo();
      assertEquals("Rota registrada.", veiculo.registrarRota(10));   
      assertEquals("Rota registrada.", veiculo.registrarRota(50));
      assertEquals("Rota registrada.", veiculo.registrarRota(96));  
   }

   @Test 
   public void testeParaRegistrarConsumoEmKmPorLitroValido(){
      if (veiculo == null)
         veiculo = new Veiculo();
      assertTrue(veiculo.setConsumoKmPorLitro(12.5));
   }

   @Test 
   public void testeParaRegistrarConsumoEmKmPorLitroInvalido(){
      veiculo = new Veiculo();
      assertFalse(veiculo.setConsumoKmPorLitro(-1.0));
   }

   @Test
   public void testeParaRetornarQuilometragemTotal(){
      testeParaAdicionarRota();
      assertEquals(10.0, veiculo.getKmTotal(), veiculo.getKmTotal());     
   }

   @Test 
   public void testeParaCalcularKmMediaPorRota(){
      testeParaAdicionarRota();
      assertEquals(52.0, veiculo.calcularKmMediaRota(), 0);   
   }

   @Test 
   public void testeParaCalcularGastoTotal(){
      testeParaAdicionarRota();
      testeParaRegistrarConsumoEmKmPorLitroValido();;
      assertEquals("98,59", veiculo.calcularGastoVeiculo(7.9));   
   }
     
}
