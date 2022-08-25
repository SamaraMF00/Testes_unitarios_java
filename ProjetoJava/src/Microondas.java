public class Microondas {

    public Microondas() {
        vLigado = false;
        vPortaAberta = true;
    }

    private int vSegPecorridos = 0,
            vMinPercorridos = 0,
            vSegPausa = 0,
            vMinPausa = 0;

    private boolean vLigado,
            vPortaAberta,
            vPausado;

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
            return "FECHE A PORTA PARA LIGAR";

        vSegPecorridos = seg;
        vMinPercorridos = min;
        vMinPausa = minPausa;
        vSegPausa = segPausa;

        if (!formatoValido())
            return "FORMATO INVÁLIDO";

        ligaDesligar(true);

        while ((vSegPecorridos > vSegPausa && vMinPercorridos >= vMinPausa) ||
                (vSegPecorridos == 0 && vMinPercorridos > 0)) {
            passaTempo();
        }

        if (vSegPecorridos == 0 && vMinPercorridos == 0)
            vLigado = false;
        else
            vPausado = true;

        return String.format("%02d", vMinPercorridos) + ":" +
                String.format("%02d", vSegPecorridos);
    }

    /**
     * Método responsável por passar o tempo em contagem regressiva.
     */
    private void passaTempo() {
        vSegPecorridos--;

        if (vSegPecorridos < 0 && vMinPercorridos > 0) {
            vMinPercorridos--;
            vSegPecorridos = 59;
        }
    }

    /**
     * Método que verifica se o tempo informado é válido.
     * Caso os segundos sejam maior que 59, o tempo será ajustado de forma correta.
     * 
     * @return true se formato válido e false se formato inválido.
     */
    private boolean formatoValido() {

        if (vMinPercorridos > 59 || vMinPausa > 59)
            return false;

        if (vSegPecorridos >= 60) {
            vMinPercorridos = vSegPecorridos / 60;
            vSegPecorridos = vSegPecorridos % 60;
        }

        if (vSegPausa >= 60) {
            vMinPausa = vSegPausa / 60;
            vSegPausa = vSegPausa % 60;
        }
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
        return iniciaCronometro(vMinPausa, vSegPausa, 0, 0);
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
