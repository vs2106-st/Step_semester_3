import java.util.Arrays;

class BusRoute implements Comparable<BusRoute> {
    private final String routeCode;
    private final String routeName;
    private final int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    public String getRouteCode() {
        return this.routeCode;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public int compareTo(BusRoute other) {
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }

        int codeCompare = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (codeCompare != 0) {
            return codeCompare;
        }

        return this.routeName.compareToIgnoreCase(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] sorted = Arrays.copyOf(routes, routes.length);

        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - i - 1; j++) {
                if (sorted[j].compareTo(sorted[j + 1]) > 0) {
                    BusRoute temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }

        return sorted;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);

        String[] resultCodes = new String[ranked.length];
        for (int i = 0; i < ranked.length; i++) {
            resultCodes[i] = ranked[i].getRouteCode();
        }

        System.out.println(Arrays.toString(resultCodes));
    }
}
