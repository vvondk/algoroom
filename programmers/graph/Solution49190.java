package graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// 방의 개수
public class Solution49190 {
  private final int[] dy = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
  private final int[] dx = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

  class Point {
    private final int x;
    private final int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Point point = (Point) o;
      return x == point.x &&
          y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }
  }

  class DirectionPoint {
    private final Point fromPoint;
    private final Point toPoint;

    public DirectionPoint(Point fromPoint, Point toPoint) {
      this.fromPoint = fromPoint;
      this.toPoint = toPoint;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      DirectionPoint that = (DirectionPoint) o;
      return Objects.equals(fromPoint, that.fromPoint) &&
          Objects.equals(toPoint, that.toPoint);
    }

    @Override
    public int hashCode() {
      return Objects.hash(fromPoint, toPoint);
    }
  }

  public int solution(int[] arrows) {
    int answer = 0;

    Set<Point> visited = new HashSet<>();
    Set<DirectionPoint> direction = new HashSet<>();

    Point prePoint = new Point(0, 0);
    visited.add(prePoint);

    for (int i = 0; i < arrows.length; i++) {
      for (int j = 0; j < 2; j++) {
        int curX = prePoint.getX() + dx[arrows[i]];
        int curY = prePoint.getY() + dy[arrows[i]];
        Point curPoint = new Point(curX, curY);

        if (visited.contains(curPoint)) {
          if (!direction.contains(new DirectionPoint(prePoint, curPoint))
              || !direction.contains(new DirectionPoint(curPoint, prePoint))) {
            answer++;
          }
        }

        visited.add(curPoint);
        direction.add(new DirectionPoint(prePoint, curPoint));
        direction.add(new DirectionPoint(curPoint, prePoint));

        prePoint = curPoint;
      }
    }
    return answer;
  }

  public static void main(String[] args) {
    Solution49190 solution = new Solution49190();
    int[] arrows = new int[]{6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
    System.out.println(solution.solution(arrows));  //3

  }
}
