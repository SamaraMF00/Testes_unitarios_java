import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class MicroondasTestes {

    private Microondas microondas;

    // @BeforeAll
    // public void init(){
    //      microondas = new Microondas();
    // } TENTEI USAR MAS O VS. CODE NÃO RECONHECEU

    @Test
    public void testeIniciarCronometroValorValido(){
        microondas = new Microondas();
        microondas.abrirFecharPorta(false);
        assertEquals("00:00", microondas.iniciaCronometro(00,70, 0, 0));
    }

    @Test
    public void testeIniciarCronometroValorInvalido(){
        microondas = new Microondas();
        microondas.abrirFecharPorta(false);
        assertEquals("FORMATO INVÁLIDO", microondas.iniciaCronometro(70,70, 0, 0));
    } 
    
    @Test 
    public void testePausarCronometroValorValido(){
        microondas = new Microondas();
        microondas.abrirFecharPorta(false);
        assertEquals("01:10", microondas.iniciaCronometro(2, 0, 0, 70));
    }

    @Test 
    public void testePausarCronometroValorInvalido(){
        microondas = new Microondas();
        microondas.abrirFecharPorta(false);
        assertEquals("FORMATO INVÁLIDO", microondas.iniciaCronometro(2, 0, 70, 20));
    }

    @Test
    public void testeRetomarCronometro(){
        testePausarCronometroValorValido();
        assertEquals("00:00", microondas.retomarCronometro());
    }

    @Test
    public void testeRetomarCronometroPortaAberta(){
        testePausarCronometroValorValido();
        microondas.abrirFecharPorta(true);
        assertEquals("FECHE A PORTA PARA LIGAR", microondas.retomarCronometro());
    }

    @Test
    public void testeAbrirPortaDesligado(){
        microondas = new Microondas();
        assertTrue(microondas.abrirFecharPorta(true));    
    }

    @Test
    public void testeAbrirPortaLigado(){
        microondas = new Microondas();
        microondas.abrirFecharPorta(false);
        microondas.ligaDesligar(true);
        assertFalse(microondas.abrirFecharPorta(true));    
    }

    @Test
    public void testeIniciarMicroondasPortaAberta(){
        microondas = new Microondas();
        assertEquals("FECHE A PORTA PARA LIGAR", microondas.iniciaCronometro(00,70, 0, 0));
    }

    @Test
    public void testeDesligarMicroondas(){
        microondas = new Microondas();
        assertFalse(microondas.ligaDesligar(false));        
    }
    
}
