public interface Infmat {
    Point allocate(char type);
    boolean remove(Point locate);
    void display();
    char mat[][]=new char [32][32];
}
