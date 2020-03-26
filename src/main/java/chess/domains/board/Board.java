package chess.domains.board;

import chess.domains.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Board {
    public static final String INVALID_LOCATION_ERR_MSG = "위치를 잘못 입력하였습니다.";
    private final Set<PlayingPiece> board = BoardFactory.getBoard();

    public List<PlayingPiece> showBoard() {
        List<PlayingPiece> showingBoard = new ArrayList<>(board);
        showingBoard.sort(PlayingPiece::compareTo);
        return showingBoard;
    }

    public PlayingPiece findPiece(String location) {
        Position position = Position.ofPositionName(location);
        return board.stream()
                .filter(piece -> piece.has(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_LOCATION_ERR_MSG));
    }

    public PlayingPiece findPiece(Position location) {
        return board.stream()
                .filter(piece -> piece.has(location))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_LOCATION_ERR_MSG));
    }

    public boolean canGo(List<Position> route) {
        int count = (int) route.stream()
                .map(this::findPiece)
                .map(PlayingPiece::isBlank)
                .filter(x -> !x)
                .count();

        return count == 0;
    }

    public void move(PlayingPiece sourcePiece, PlayingPiece targetPiece) {
        if (sourcePiece.isMine(targetPiece)) {
            throw new IllegalStateException("자신의 말 위치로 이동할 수 없습니다.");
        }
        this.board.remove(targetPiece);
        this.board.remove(sourcePiece);
        this.board.addAll(sourcePiece.moveTo(targetPiece));
    }
}
