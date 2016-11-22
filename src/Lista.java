import java.util.ArrayList;

public class Lista {

    private NodoL pr;
    private NodoL ul;
    public Lista(){
        pr = null;
        ul = null;
    }
    public boolean esVacia(){
        if (pr == null) return true;
        else return false;
    }

    public void insertaCabeza(ArrayList<Coordenadas> v){
        if (pr == null) {
            NodoL primero = new NodoL(v);
            pr = primero;
        }
    }
    public void insertaCola(ArrayList<Coordenadas> v){
        if (ul == null) {
            NodoL ultimo = new NodoL(v);
            ul = ultimo;
        }
    }
    public void inserta(ArrayList<Coordenadas> v, int i) throws IndexOutOfBoundsException{
        NodoL nuevo = new NodoL(v);
        NodoL aux = pr;
        if (aux != null) {
            boolean valido = true;
            int j;
            for (j = 0; j < i && valido; j++) {
                if (aux.getNext() != null) pr = aux.getNext();
                else valido = false;
            }
            if (valido) {
                nuevo.cambiaNext(aux.getNext());
                aux.cambiaNext(nuevo);
            } else throw new IndexOutOfBoundsException(""+j);
        }throw new IndexOutOfBoundsException("0");
    }
    public boolean borraCabeza(){
        if (pr != null){
            if (pr.getNext() != null){
                pr = pr.getNext();
            } else pr = null;
            return true;
        } else return false;
    }
    public boolean borraCola(){
        /*quita el ultimo objeto (a la cola) de la lista, devolviendo true si la operacion se ha podido realizar.*/
        NodoL aux = pr;
        if (aux == null) return false;
        while (aux.getNext() != null) {
            if (aux.getNext() == ul) {
                ul = aux;
                return true;
            }
            else aux = aux.getNext();
        }
        return false;
    }
    public void borra(int i) throws IndexOutOfBoundsException{
        NodoL aux = pr;
        if (aux != null) {
            boolean valido = true;
            int j;
            for (j = 0; j < i-1 && valido; j++) {
                if (aux.getNext() != null) pr = aux.getNext();
                else valido = false;
            }
            if (valido) {
                aux.cambiaNext(aux.getNext().getNext());
            } else throw new IndexOutOfBoundsException(""+j);
        } else throw new IndexOutOfBoundsException("0");
    }
    public boolean borra(ArrayList<Coordenadas> v){
        NodoL aux = pr;
        if (aux != null) {
            boolean valido = true;
            while (!aux.getNext().getCamino().equals(v)){
                if (aux.getNext() != null){
                    pr = aux.getNext();
                }
                else valido = false;
            }
            if (valido) {
                aux.cambiaNext(aux.getNext().getNext());
                return true;
            }
        }
        return false;
    }
    public void escribeLista(){
        NodoL aux = pr;
        int j;
        for (j = 0; aux != null; j++) {
            System.out.print("camino "+j+": ");
            aux.escribeCamino();
            aux = aux.getNext();
        }
    }
    public int enLista(ArrayList<Coordenadas> v){
        /*nos dice si v esta en la lista o no. Si esta, devuelve su posicion, en otro caso devuelve -1.*/
        NodoL aux = pr;
        int j = -1;
        if (aux != null) {
            boolean valido = true;
            for (j = 0; !aux.getNext().getCamino().equals(v); j++){
                if (aux.getNext() != null){
                    pr = aux.getNext();
                }
                else valido = false;
            }
            if (valido) {
                return j;
            }
        }
        return j;
    }
    public ArrayList<Coordenadas> getCamino(int pos) throws IndexOutOfBoundsException{
        NodoL aux = pr;
        if (aux != null) {
            boolean valido = true;
            int j;
            for (j = 0; j < pos && valido; j++) {
                if (aux.getNext() != null) pr = aux.getNext();
                else valido = false;
            }
            if (valido) {
                return aux.getCamino();
            } else throw new IndexOutOfBoundsException(""+j);
        } else throw new IndexOutOfBoundsException("0");
    }

    private class NodoL {
        private ArrayList<Coordenadas> camino;
        private NodoL next;

        public NodoL( ArrayList<Coordenadas> v){
            camino = v;
            next = null;
        }
        public NodoL( ArrayList<Coordenadas> v, NodoL n){
            camino = v;
            next = n;
        }
        public void cambiaNext(NodoL n){
            next = n;
        }
        public NodoL getNext(){
            return next;
        }
        public ArrayList<Coordenadas> getCamino() {
            return camino;
        }
        public void escribeCamino(){
            for (Coordenadas c:camino) {
                System.out.print("("+c.getFila()+","+c.getColumna()+")");
            }
        }
        public boolean comparaCamino(ArrayList<Coordenadas> v){
            if (v.size() == camino.size()){
                for (int i = 0; i< v.size(); i++){
                    if ((v.get(i).getFila() != camino.get(i).getFila()) || (v.get(i).getColumna() != camino.get(i).getColumna())){
                        return false;
                    }
                }
            } else return false;
            return true;
        }
    }
}
