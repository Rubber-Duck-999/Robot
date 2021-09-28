package robots;


public class Target {

    private String _name;
    private double _bearing;
    private double _heading;

    public Target(String name, double bearing, double heading) {
        this._name = name;
        this._bearing = bearing;
        this._heading = heading;
    }

    public String getName() {
        return this._name;
    }
}