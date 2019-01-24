package com.codecool.view.terminalview;

import com.codecool.model.board.Board;
import com.codecool.model.board.Cell;
import com.codecool.view.View;

public class TerminalView implements View {
    public void draw(Board board) {

        StringBuilder sb = new StringBuilder();
        for (Cell[] row: board.getBoard()) {
            sb.append("\n");
            for(Cell cell: row) {
                if(cell.getCurrentCreature() != null) {
                    sb.append("C ");
                } else
                    if(cell.getFoodAmount() > 0){
                    sb.append("F ");
                    } else{
                    sb.append("_ ");
                    }
            }

        }
        clearScreen();
        System.out.println(sb.toString());

    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
