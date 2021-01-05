package TDD.TicTocToe;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicTacToeSpec {
    private TicTacToe ticTacToe;

    @Before
    public final void before() {
        ticTacToe = new TicTacToe();
    }

    @Test(expected = RuntimeException.class)
    public void when_out_side_X_board_then_run_time_exception() {
        ticTacToe.play(5,2);
    }

    @Test(expected = RuntimeException.class)
    public void when_out_side_Y_board_then_run_time_exception() {
        ticTacToe.play(2,5);
    }

    @Test(expected = RuntimeException.class)
    public void when_Occupied_then_run_time_exception() {
        ticTacToe.play(2,1);
        ticTacToe.play(2,1);
    }


    @Test
    public void given_first_player_tuen_when_next_player_then_x() {
        assertEquals('x', ticTacToe.nextPlayer());
    }

    @Test
    public void given_last_turn_is_x_when_next_player_then_o() {
        ticTacToe.play(1,1);
        assertEquals('o',ticTacToe.nextPlayer());
    }

    @Test
    public void given_last_turn_is_o_when_next_player_then_x() {
        ticTacToe.play(1,1);
        assertEquals('o',ticTacToe.nextPlayer());
        ticTacToe.play(1,2);
        assertEquals('x',ticTacToe.nextPlayer());
    }

    @Test
    public void when_play_no_winner() {
        String winner = ticTacToe.play(1, 1);
        assertEquals("no winner", winner);
    }

    @Test
    public void when_x_wiiner_given_horizontal_line() {
        ticTacToe.play(1,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,1);
        ticTacToe.play(2,2);
        String winner = ticTacToe.play(3, 1);
        assertEquals("x winner", winner);
    }

    @Test
    public void when_x_wiiner_given_vertical_line() {
        ticTacToe.play(2,1);
        ticTacToe.play(1,1);
        ticTacToe.play(3,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,2);
        String winner = ticTacToe.play(1, 3);
        assertEquals("o winner", winner);
    }

    @Test
    public void when_x_wiiner_given_diagonal_line() {
        ticTacToe.play(1,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,2);
        ticTacToe.play(1,3);
        String winner = ticTacToe.play(3,3);
        assertEquals("x winner", winner);
    }

    @Test
    public void when_x_wiiner_given_diagonal_line_v2() {
        ticTacToe.play(1,3);
        ticTacToe.play(1,1);
        ticTacToe.play(2,2);
        ticTacToe.play(1,2);
        String winner = ticTacToe.play(3,1);
        assertEquals("x winner", winner);
    }

}
