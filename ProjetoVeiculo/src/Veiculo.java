/**
 * author: Samara Ferreira
 * date: 03/09/2022
 */

import java.text.DecimalFormat;

public class Veiculo {

   //#region atributos

   private Data dataAquisicao;
   private Double consumoKmPorLitro;
   private int quantidadeRotas;
   private Double KmTotal;

   //#endregion

   //#region construtor

   /**
    * Construtor da classe sem parâmetros.
    */
   public Veiculo(){
      dataAquisicao = null;
      consumoKmPorLitro = 0.0;
      quantidadeRotas = 0;
      KmTotal = 0.0;
   }

   //#endregion

   //#region métodos

   /**
    * Registra a data de aquisição do veículo.
    * @param dia dia da aquisição
    * @param mes mês da aquisição
    * @param ano ano da aquisição
    * @return mensagem de sucesso ou "DATA INVÁLIDA" caso os dados estejam incorretos.
    */
   public String registraDataAquisicao(int dia, int mes, int ano) {
      Data data = new Data(dia, mes, ano);

      if (data.dataFormatada().equals("01/01/1900"))
         return "DATA INVÁLIDA.";
      else {
         this.dataAquisicao = data;
         return "Data de aquisição registrada com sucesso.";
      }
   }

   /**
    * Registra uma nova rota
    * @param km quilometragem da rota
    * @return mensagem de sucesso.
    */
   public String registrarRota(int km) {
      quantidadeRotas++;
      KmTotal += km;
      return "Rota registrada.";
   }

   /**
    * Calcula a quilometragem média por rota realizada
    * @return quilometragem média por rota.
    */

   public double calcularKmMediaRota() {
      return getKmTotal()/quantidadeRotas;
   }

   /**
    * Calcula o gasto total do veículo com abastecimento.
    * @param preco valor em dinheiro do preço do combustível.
    * @return valor total gasto em combustível.
    */
   public String calcularGastoVeiculo(double preco) {
      DecimalFormat df = new DecimalFormat("#,###.00");
      return df.format((getKmTotal()/consumoKmPorLitro) * preco); 
   }

   /**
    * Método para imprimir os dados do veículo.
    */
   public void imprimeDadosCompletos(){
      System.out.println("Data aquisição: "+ dataAquisicao.dataFormatada()+
         "\nConsumo por litro: "+ consumoKmPorLitro +
         "\nQuilometragem total: " + KmTotal);
   }

   //#endregion métodos

   //#region getter's e setter's

   /**
    * Registra o consumo em Km/L do veículo.
    * @param consumo consumo em Km/L
    * @return "true" se o valor do consumo for válido e "false" se for <= 0.
    */
   public boolean setConsumoKmPorLitro(double consumo) {
      if (consumo <= 0)
         return false;
      else{
         this.consumoKmPorLitro = consumo;
         return true;
      }
   }

   public double getKmTotal() {
      return KmTotal;
   }

   public double getConsumoKmPorLitro() {
      return consumoKmPorLitro;
   }

   //#endregion

}
