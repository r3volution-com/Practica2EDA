import java.util.ArrayList;

public class exPath {
    private Coordenadas coor;
    private ArrayList<Coordenadas> camino;
    public exPath (int x, int y, ArrayList<Coordenadas> v){
        Coordenadas aux = new Coordenadas(x, y);
        ArrayList<Coordenadas> aux2 = new ArrayList<Coordenadas>(v);
        aux2.add(aux);
        camino = aux2;
    }
    public Coordenadas getCoordenadas(){
        return coor;
    }
    public ArrayList<Coordenadas> getCamino(){
        return camino;
    }
}
