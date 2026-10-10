public class Problem2 {
    public static class Summary {
        public int total;
        public int[] maxCoordinate;

        public Summary(int total, int[] maxCoordinate) {
            this.total = total;
            this.maxCoordinate = maxCoordinate;
        }
    }

    public static Summary warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new Summary(0, new int[]{-1, -1});
        }

        int total = 0;
        int maxVal = Integer.MIN_VALUE;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int val = grid[r][c];
                total += val;
                if (val > maxVal) {
                    maxVal = val;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new Summary(total, new int[]{maxRow, maxCol});
    }
}
