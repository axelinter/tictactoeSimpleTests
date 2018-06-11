import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//Axel Telkamper Queralto
public class JuegoAdvancedTest {
    //Testeo
    @Test
    public void whenBoardIsFullGameIsOver () {
        Juego game = new Juego(createFullBoard(), new Player[0]);
        assertTrue(game.isOver());
    }
    @Test
    public void whoWonWhenXHasColumnReturnsX(){
        Player[] players = createPlayers();
        Juego game = new Juego(createColumnWinnerXBoard(),players);
        assertEquals(game.whoWon(),players[0]);
    }

    @Test
    public void whoWonWhenXHasLineReturnsX(){
        Player[] players = createPlayers();
        Juego game = new Juego(createLineWinnerXBoard(),players);
        assertEquals(game.whoWon(),players[0]);
    }

    @Test
    public void whoWonWhenXHasDiagonalReturnsX(){
        Player[] players = createPlayers();
        Juego game = new Juego(createDiagonalWinnerXBoard(),players);
        assertEquals(game.whoWon(),players[0]);
    }

    @Test
    public void whoWonWhenXHasInvertedDiagonalReturnsX(){
        Player[] players = createPlayers();
        Juego game = new Juego(createInvertedDiagonalWinnerXBoard(),players);
        assertEquals(game.whoWon(),players[0]);
    }

    @Test
    public void whoWonWhenNobodyWonReturnsNull(){
        Player[] players = createPlayers();
        Juego game = new Juego(createFullBoard(),players);
        assertNull(game.whoWon());
    }
// Metodos utilizando mockito
    private Tablero createFullBoard() {
        Tablero board = mock(Tablero.class);
        Casilla occupiedSquare = mock(Casilla.class);
        when(occupiedSquare.isOccupied()).thenReturn(true);

        when(board.getCasilla(any(Posicion.class))).thenReturn(occupiedSquare);
        return board;
    }
    private Player[] createPlayers(){
        Player[] players = new Player[2];
        Player playerX = mock(Player.class);
        when(playerX.getChip()).thenReturn('x');
        Player o = mock(Player.class);
        when(o.getChip()).thenReturn('o');
        players[0] = playerX;
        players[1] = o;
        return players;
    }

    private Casilla createNeutralCasilla() {
        final Casilla cn = mock(Casilla.class);
        when(cn.getChip()).thenReturn(' ');
        when(cn.isOccupied()).thenReturn(false);
        return cn;
    }

    private Casilla createXCasilla() {
        final Casilla cx = mock(Casilla.class);
        when(cx.getChip()).thenReturn('x');
        when(cx.isOccupied()).thenReturn(true);
        return cx;
    }
    private Tablero crearColumnasxGanadoras(){
        final Tablero board = mock(Tablero.class);

        final Casilla cn = createNeutralCasilla();
        final Casilla cx = createXCasilla();


        when(board.getCasilla(any(Posicion.class))).thenAnswer(new Answer<Casilla>() {
            public Casilla answer(InvocationOnMock invocationOnMock) {
                Posicion posicion = (Posicion) invocationOnMock.getArguments()[0];
                if (posicion.getX() == 0 && posicion.getY() == 0) {
                    return cx;
                }

                if (posicion.getX() == 1 && posicion.getY() == 0) {
                    return cx;
                }

                if (posicion.getX() == 2 && posicion.getY() == 0) {
                    return cx;
                }

                return cn;
            }
        });
        return board;
    }
    private Tablero crearLineaxGanadoras(){
        final Tablero board = mock(Tablero.class);

        final Casilla cn = createNeutralCasilla();
        final Casilla cx = createXCasilla();


        when(board.getCasilla(any(Posicion.class))).thenAnswer(new Answer<Casilla>() {
            public Casilla answer(InvocationOnMock invocationOnMock) {
                Posicion posicion = (Posicion) invocationOnMock.getArguments()[0];

                if (posicion.getX() == 0 && posicion.getY() == 0) {
                    return cx;
                }

                if (posicion.getX() == 0 && posicion.getY() == 1) {
                    return cx;
                }

                if (posicion.getX() == 0 && posicion.getY() == 2) {
                    return cx;
                }

                return cn;
            }
        });
        return board;
    }
    private Tablero crearDiagonalGanadora(){
        final Tablero board = mock(Tablero.class);

        final Casilla cn = createNeutralCasilla();
        final Casilla cx = createXCasilla();


        when(board.getCasilla(any(Posicion.class))).thenAnswer(new Answer<Casilla>() {
            public Casilla answer(InvocationOnMock invocationOnMock) {
                Posicion posicion = (Posicion) invocationOnMock.getArguments()[0];

                if (posicion.getX() == 0 && posicion.getY() == 0) {
                    return cx;
                }

                if (posicion.getX() == 1 && posicion.getY() == 1) {
                    return cx;
                }

                if (posicion.getX() == 2 && posicion.getY() == 2) {
                    return cx;
                }

                return cn;
            }
        });
        return board;
    }

    private Tablero crearIndiagonalGanadora(){
        final Tablero board = mock(Tablero.class);

        final Casilla cn = createNeutralCasilla();
        final Casilla cx = createXCasilla();


        when(board.getCasilla(any(Posicion.class))).thenAnswer(new Answer<Casilla>() {
            public Casilla answer(InvocationOnMock invocationOnMock) {
                Posicion posicion = (Posicion) invocationOnMock.getArguments()[0];

                if (posicion.getX() == 0 && posicion.getY() == 2) {
                    return cx;
                }

                if (posicion.getX() == 1 && posicion.getY() == 1) {
                    return cx;
                }

                if (posicion.getX() == 2 && posicion.getY() == 0) {
                    return cx;
                }

                return cn;
            }
        });
        return board;
    }

}
