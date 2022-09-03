/*
 * author: Samara Martins Ferreira
 * date: 26/08/2022
 */

public class Microondas {

    // #region atributos
    private int vSegPecorridos,
            vMinPercorridos;

    private boolean vLigado,
            vPortaAberta,
            vPausado;

    // constantes
    private static final int SEGUNDOS_PARA_CONVERSAO = 60;
    private static final String MENSAGEM_PORTA_ABERTA = "FECHE A PORTA PARA LIGAR";
    private static final String MENSAGEM_FORMATO_INVALIDO = "FORMATO INVÁLIDO";
    // #endregion

    // #region construtores
    public Microondas() {
        vLigado = false;
        vPausado = false;
        vPortaAberta = true;
        vSegPecorridos = 0;
        vMinPercorridos = 0;
    }
    // #endregion

    /**
     * Método para ligar o microondas. Deve ser informado o tempo de pausa caso
     * exista.
     * 
     * @param min      quantos minutos irá rodar
     * @param seg      quantos segundos irá rodar
     * @param minPausa em quantos minutos irá pausar
     * @param segPausa em quantos segundos irá pausar
     * @return "00:00" se rodar até o final.
     *         O tempo em que parou caso tenha pausado.
     *         "FORMATO INVÁLIDO" caso "min" ou "minPausa" > 59.
     *         "FECHE A PORTA PARA LIGAR" caso a porta esteja aberta.
     */
    public String iniciaCronometro(int min, int seg, int minPausa, int segPausa) {

        if (vPortaAberta)
            return MENSAGEM_PORTA_ABERTA;

        if (!formatoValido(min, minPausa))
            return MENSAGEM_FORMATO_INVALIDO;

        seg += converteMinParaSeg(min);
        segPausa += converteMinParaSeg(minPausa);

        vSegPecorridos = seg;
        vMinPercorridos = 0;

        ligaDesligar(true);

        while (vSegPecorridos > segPausa) {
            passaTempo();
        }

        if (vSegPecorridos == 0 && vMinPercorridos == 0)
            vLigado = false;
        else {
            vPausado = true;
            converteTempoPecorrido();
        }

        return String.format("%02d", vMinPercorridos) + ":" +
                String.format("%02d", vSegPecorridos);
    }

    /**
     * Método para converter minutos em segundos
     * 
     * @param min minutos para conversão
     * @return segundos convertidos
     */
    private int converteMinParaSeg(int min) {
        if (min > 0)
            return min * SEGUNDOS_PARA_CONVERSAO;
        else
            return 0;
    }

    /**
     * Método para converter o tempo percorrido de segundos para minutos.
     */
    private void converteTempoPecorrido() {
        if (vSegPecorridos >= SEGUNDOS_PARA_CONVERSAO) {
            vMinPercorridos = vSegPecorridos / SEGUNDOS_PARA_CONVERSAO;
            vSegPecorridos = vSegPecorridos % SEGUNDOS_PARA_CONVERSAO;
        }
    }

    /**
     * Método responsável por passar o tempo em contagem regressiva.
     */
    private void passaTempo() {
        vSegPecorridos--;
    }

    /**
     * Método que verifica se o tempo informado é válido.
     * Caso os segundos sejam maior que 59, o tempo será ajustado de forma correta.
     * 
     * @return true se formato válido e false se formato inválido.
     */
    private boolean formatoValido(int minPecorrido, int minPausa) {
        if (minPecorrido >= SEGUNDOS_PARA_CONVERSAO || minPausa >= SEGUNDOS_PARA_CONVERSAO)
            return false;
        else
            return true;
    }

    /**
     * Método responsável por retormar o cronômetro de onde o mesmo tenha parado
     * após a pausa.
     * 
     * @return "00:00" se rodar até o final.
     *         "FECHE A PORTA PARA LIGAR" caso a porta esteja aberta.
     */
    public String retomarCronometro() {
        return iniciaCronometro(vMinPercorridos, vSegPecorridos, 0, 0);
    }

    /**
     * Método responsável pelo controle de abertura e fechamento da porta.
     * A porta só pode ser aberta caso o microondas esteja desligado.
     * 
     * @param abrir true para abrir e false para fechar.
     * @return status da porta (aberta(true) ou fechada(false)).
     */
    public boolean abrirFecharPorta(boolean abrir) {
        if ((abrir && !vLigado) || (abrir && vPausado))
            vPortaAberta = true;
        else if (!abrir)
            vPortaAberta = false;
        return vPortaAberta;
    }

    /**
     * Método para ligar e desligar o microondas.
     * Microondas só poderá ser ligado se a porta estiver fechada.
     * Poderá ser desligado a qualquer momento.
     * 
     * @param ligar true para ligar, false para desligar
     * @return status do microondas (ligado(true) ou desligado(false))
     */
    public Boolean ligaDesligar(boolean ligar) {
        if (ligar && !vPortaAberta)
            vLigado = true;
        else
            vLigado = false;
        return vLigado;
    }

}
