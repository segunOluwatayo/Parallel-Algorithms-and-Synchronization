public class SearchThread implements Runnable {
    //TODO: Define the matrix to be searched and the rows to search in
    private final char[][] matrix;
    private final int startRow;
    private final int endRow;
    //TODO: Create a constructor for this class
    public SearchThread(char[][] matrix, int startRow, int endRow) {
        this.matrix = matrix;
        //TODO: Make sure that the end row doesn't exceed the matrix size that is why there is a Math.min function
        this.startRow = startRow;
        this.endRow = Math.min(endRow, matrix.length);
    }

    @Override
    public void run() {
        //TODO: Calculate number of columns in matrix
        int columns = matrix[0].length;

        //TODO: Horizontal Search
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j <= columns - 3; j++) {
                if (matrix[i][j] == 'f' && matrix[i][j + 1] == 'u' && matrix[i][j + 2] == 'n') {
                    System.out.println("Found 'fun' horizontally at row: " + i + ", column: " + j);
                }
            }
        }

        //TODO: Vertical Search
        for (int i = 0; i < columns; i++) {
            for (int j = startRow; j < Math.min(endRow, matrix.length - 2); j++) {
                if (matrix[j][i] == 'f' && matrix[j + 1][i] == 'u' && matrix[j + 2][i] == 'n') {
                    System.out.println("Found 'fun' vertically at row: " + j + ", column: " + i);
                }
            }
        }

        //TODO: Diagonal Search
        for (int i = startRow; i < Math.min(endRow, matrix.length - 2); i++) {
            for (int j = 0; j <= columns - 3; j++) {
                if (matrix[i][j] == 'f' && matrix[i + 1][j + 1] == 'u' && matrix[i + 2][j + 2] == 'n') {
                    System.out.println("Found 'fun' diagonally at row: " + i + ", column: " + j);
                }
            }
        }
    }
}
