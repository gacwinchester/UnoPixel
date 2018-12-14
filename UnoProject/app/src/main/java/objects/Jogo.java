package objects;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.androide.bobs.unoproject.GameActivity;
import com.androide.bobs.unoproject.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import enums.Cores;
import enums.TipoCarta;

public class Jogo extends AppCompatActivity {

    final int QT_CARTAS_NORMAIS = 19;
    final int QT_CURINGAS_SIMPLES = 8;
    final int QT_CURINGAS_FODOES = 4;

    private int jogadorAtual = jogadorInicio(); //jogador 0 é a cpu e o jogador 1 o player;
    private Cores corAtual;
    private TipoCarta tipoAtual;

    private ArrayList<Carta> cartasMesa = new ArrayList<>(); //Lista que contém as cartas já jogadas na mesa
    private ArrayList<Carta> cartasComprar = new ArrayList<>(); //Lista que contem as cartas disponíveis para comprar
    private ArrayList<Carta> cartasJogador1 = new ArrayList<>(); //Lista que contem as cartas disponíveis para o jogador 1(player)
    private ArrayList<Carta> cartasCPU = new ArrayList<>(); //Lista de cartas do jogar 2(CPU)

    public Jogo() {
        int j = 0;
        for (int i = 0; i < QT_CARTAS_NORMAIS; i++) {
            int num = i > 9 ? j++ : i;
            cartasComprar.add(new Carta(Cores.AZUL, TipoCarta.NORMAL, num));
            cartasComprar.add(new Carta(Cores.VERMELHO, TipoCarta.NORMAL, num));
            cartasComprar.add(new Carta(Cores.AMARELO, TipoCarta.NORMAL, num));
            cartasComprar.add(new Carta(Cores.VERDE, TipoCarta.NORMAL, num));
        }
        for (int i = 0; i < 2; i++) {
            cartasComprar.add(new Carta(Cores.AZUL, TipoCarta.MAIS_DOIS));
            cartasComprar.add(new Carta(Cores.VERDE, TipoCarta.MAIS_DOIS));
            cartasComprar.add(new Carta(Cores.VERMELHO, TipoCarta.MAIS_DOIS));
            cartasComprar.add(new Carta(Cores.AMARELO, TipoCarta.MAIS_DOIS));
            cartasComprar.add(new Carta(Cores.AZUL, TipoCarta.INVERTER));
            cartasComprar.add(new Carta(Cores.VERDE, TipoCarta.INVERTER));
            cartasComprar.add(new Carta(Cores.VERMELHO, TipoCarta.INVERTER));
            cartasComprar.add(new Carta(Cores.AMARELO, TipoCarta.INVERTER));
            cartasComprar.add(new Carta(Cores.AZUL, TipoCarta.BLOQUEIO));
            cartasComprar.add(new Carta(Cores.VERDE, TipoCarta.BLOQUEIO));
            cartasComprar.add(new Carta(Cores.VERMELHO, TipoCarta.BLOQUEIO));
            cartasComprar.add(new Carta(Cores.AMARELO, TipoCarta.BLOQUEIO));
        }
        for (int i = 0; i < QT_CURINGAS_FODOES; i++) {
            cartasComprar.add(new Carta(null, TipoCarta.MAIS_QUATRO));
        }
        Collections.shuffle(cartasComprar);
        for (int i = 0; i < 9; i++) {
            cartasJogador1.add(cartasComprar.get(i));
            cartasComprar.remove(i);
            cartasCPU.add(cartasComprar.get(i));
            cartasComprar.remove(i);
        }
        if (jogadorInicio() == 0) {
            fazerJogada(null, true);
        }
    }

    public void fazerJogada(Carta carta, boolean cpu) {
        boolean podeJogar = false;
        if (cpu) {
            int num = -1;
            for (int i = 0; i < cartasCPU.size(); i++) {
                if (verificarJogada(cartasCPU.get(i))) {
                    num = i;
                    break;
                }
            }
            if (num > -1) {
                carta = cartasCPU.get(num);
                cartasCPU.remove(num);
                podeJogar = true;
            }
        } else {
            if (verificarJogada(carta)) {
                cartasJogador1.remove(carta);
                podeJogar = true;
            }
        }
        if (podeJogar) {
            cartasMesa.add(carta);
            handleTipo(carta);
        }

        if(cartasJogador1.size() == 0 || cartasCPU.size() == 0){
            startActivity(new Intent(getApplicationContext(), GameActivity.class));
        }
    }

    private boolean verificarJogada(Carta carta) {
        return (carta.getCor().equals(cartasMesa.get((cartasMesa.size() - 1)).getCor()))
                || (carta.getTipo().equals(cartasMesa.get((cartasMesa.size() - 1)).getTipo()))
                || (carta.getNumero().equals(cartasMesa.get((cartasMesa.size() - 1)).getNumero()));
    }

    public ArrayList<Carta> getCartasMesa() {
        return cartasMesa;
    }

    public void setCartasMesa(ArrayList<Carta> cartasMesa) {
        this.cartasMesa = cartasMesa;
    }

    public ArrayList<Carta> getCartasComprar() {
        return cartasComprar;
    }

    public void setCartasComprar(ArrayList<Carta> cartasComprar) {
        this.cartasComprar = cartasComprar;
    }

    public ArrayList<Carta> getCartasJogador1() {
        return cartasJogador1;
    }

    public void setCartasJogador1(ArrayList<Carta> cartasJogador1) {
        this.cartasJogador1 = cartasJogador1;
    }

    public ArrayList<Carta> getCartasCPU() {
        return cartasCPU;
    }

    public void setCartasCPU(ArrayList<Carta> cartasCPU) {
        this.cartasCPU = cartasCPU;
    }

    public int getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadorAtual(int jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    public static int jogadorInicio() {
        return ThreadLocalRandom.current().nextInt(0, 2);
    }

    public Cores getCorAtual() {
        return corAtual;
    }

    public void setCorAtual(Cores corAtual) {
        this.corAtual = corAtual;
    }

    public TipoCarta getTipoAtual() {
        return tipoAtual;
    }

    public void setTipoAtual(TipoCarta tipoAtual) {
        this.tipoAtual = tipoAtual;
    }

    private void handleTipo(Carta carta) {
        int jogadorInverse = this.getJogadorAtual() == 0 ? 1 : 0;
        this.setCorAtual(carta.getCor());
        this.setTipoAtual(carta.getTipo());

        switch (carta.getTipo()) {
            case NORMAL:
            case INVERTER:
                this.setJogadorAtual(jogadorInverse);
                break;
            case BLOQUEIO:
                this.setJogadorAtual(this.getJogadorAtual());
                break;
            case MAIS_DOIS:
                this.setJogadorAtual(jogadorInverse);
                if (jogadorInverse == 0) {
                    cartasCPU.add(comprar());
                    cartasCPU.add(comprar());
                } else {
                    cartasJogador1.add(comprar());
                    cartasJogador1.add(comprar());
                }
                break;
            case MAIS_QUATRO:
                this.setJogadorAtual(jogadorInverse);
                if (jogadorInverse == 0) {
                    for (int i = 0; i < 4; i++) {
                        cartasCPU.add(comprar());
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        cartasJogador1.add(comprar());
                    }
                }
                break;
        }

        if (this.getJogadorAtual() == 0) {
            this.fazerJogada(null, true);
        }
    }

    public void comprarCarta() {
        cartasJogador1.add(cartasComprar.remove(cartasComprar.size() - 1));
    }

    private Carta comprar() {
        return cartasComprar.remove(cartasComprar.size() - 1);
    }
}
