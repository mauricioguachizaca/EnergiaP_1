package controller.Dao;

import java.util.Arrays;

import controller.Dao.implement.AdapterDao;
import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;
import models.Proyecto;

public class ProyectoDao extends AdapterDao<Proyecto> {
    private Proyecto proyecto;
    private LinkedList<Proyecto> listAll;

    public ProyectoDao() {
        super(Proyecto.class);
    }

    public Proyecto getProyecto() {
        if (proyecto == null) {
            proyecto = new Proyecto();
        }
        return this.proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LinkedList<Proyecto> getListAll() {
        if (this.listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        getProyecto().setIdProyecto(id);
        persist(getProyecto());
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getProyecto(), getProyecto().getIdProyecto() - 1);
        this.listAll = listAll();
        return true;
    }

    public Boolean delete(Integer id) throws Exception {
        for (int i = 0; i < getListAll().getSize(); i++) {
            Proyecto pro = getListAll().get(i);
            if (pro.getIdProyecto().equals(id)) {
                getListAll().delete(i);
                return true;
            }
        }
        throw new Exception("Proyecto no encontrado con ID: " + id);
    }
    //Metodos de ordenamientos

    // Método de ordenamiento con Quicksort para tres criterios
    public LinkedList<Proyecto> order(Integer tipoorden, String criterio) {
        LinkedList<Proyecto> listita = getListAll();
        if (!listita.isEmpty()) {
            Proyecto[] lista = (Proyecto[]) listita.toArray();
            listita.reset();
            quickSort(lista, 0, lista.length - 1, tipoorden, criterio); 
            listita.toList(lista);
        }
        return listita;
    }

    private void quickSort(Proyecto[] arr, int low, int high, Integer tipoorden, String criterio) {
        if (low < high) {
            int pi = partition(arr, low, high, tipoorden, criterio);
            quickSort(arr, low, pi - 1, tipoorden, criterio);  // Lado izquierdo
            quickSort(arr, pi + 1, high, tipoorden, criterio); // Lado derecho
        }
    }

    private int partition(Proyecto[] arr, int low, int high, Integer tipoorden, String criterio) {
        Proyecto pivot = arr[high]; // Último elemento como pivote
        int i = low - 1;           // Índice del menor elemento
        for (int j = low; j < high; j++) {
            if (!verify(arr[j], pivot, tipoorden, criterio)) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(Proyecto[] arr, int i, int j) {
        Proyecto temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private Boolean verify(Proyecto a, Proyecto b, Integer tipoorden, String criterio) {
        if (tipoorden == 1) { // Ascendente
            if (criterio.equalsIgnoreCase("nombre")) {
                return a.getNombre().compareTo(b.getNombre()) > 0;
            } else if (criterio.equalsIgnoreCase("inversion")) {
                return a.getInversion().compareTo(b.getInversion()) > 0;
            } else if (criterio.equalsIgnoreCase("costototal")) {
                return a.getCostototal().compareTo(b.getCostototal()) > 0;
            }
        } else { // Descendente
            if (criterio.equalsIgnoreCase("nombre")) {
                return a.getNombre().compareTo(b.getNombre()) < 0;
            } else if (criterio.equalsIgnoreCase("inversion")) {
                return a.getInversion().compareTo(b.getInversion()) < 0;
            } else if (criterio.equalsIgnoreCase("costototal")) {
                return a.getCostototal().compareTo(b.getCostototal()) < 0;
            }
        }
        return false;
    }    

    private Boolean verify2(Proyecto a, Proyecto b, Integer tipoorden, String criterio) {
        if (tipoorden == 1) { // Ascendente
            if (criterio.equalsIgnoreCase("nombre")) {
                return a.getNombre().compareTo(b.getNombre()) < 0;  // Orden ascendente
            } else if (criterio.equalsIgnoreCase("inversion")) {
                return a.getInversion().compareTo(b.getInversion()) < 0;  // Orden ascendente
            } else if (criterio.equalsIgnoreCase("costototal")) {
                return a.getCostototal().compareTo(b.getCostototal()) < 0;  // Orden ascendente
            }
        } else { // Descendente
            if (criterio.equalsIgnoreCase("nombre")) {
                return a.getNombre().compareTo(b.getNombre()) > 0;  // Orden descendente
            } else if (criterio.equalsIgnoreCase("inversion")) {
                return a.getInversion().compareTo(b.getInversion()) > 0;  // Orden descendente
            } else if (criterio.equalsIgnoreCase("costototal")) {
                return a.getCostototal().compareTo(b.getCostototal()) > 0;  // Orden descendente
            }
        }
        return false;
    }
    


    // Método de ordenamiento con MergeSort para tres criterios
public LinkedList<Proyecto> mergeSort(Integer tipoorden, String criterio) {
    LinkedList<Proyecto> listita = getListAll();
    if (!listita.isEmpty()) {
        Proyecto[] lista = (Proyecto[]) listita.toArray();
        listita.reset();
        mergeSortRec(lista, 0, lista.length - 1, tipoorden, criterio); 
        listita.toList(lista);
    }
    return listita;
}

private void mergeSortRec(Proyecto[] arr, int left, int right, Integer tipoorden, String criterio) {
    if (left < right) {
        int mid = (left + right) / 2;

        mergeSortRec(arr, left, mid, tipoorden, criterio);
        mergeSortRec(arr, mid + 1, right, tipoorden, criterio);

        merge(arr, left, mid, right, tipoorden, criterio);
    }
}

private void merge(Proyecto[] arr, int left, int mid, int right, Integer tipoorden, String criterio) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    Proyecto[] leftArr = new Proyecto[n1];
    Proyecto[] rightArr = new Proyecto[n2];

    System.arraycopy(arr, left, leftArr, 0, n1);
    System.arraycopy(arr, mid + 1, rightArr, 0, n2);

    int i = 0, j = 0;
    int k = left;
    while (i < n1 && j < n2) {
        if (verify2(leftArr[i], rightArr[j], tipoorden, criterio)) {
            arr[k] = leftArr[i];
            i++;
        } else {
            arr[k] = rightArr[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = leftArr[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = rightArr[j];
        j++;
        k++;
    }
}

// Método de ordenamiento con ShellSort
public LinkedList<Proyecto> shellSort(Integer tipoorden, String criterio) {
    LinkedList<Proyecto> listita = getListAll();
    if (!listita.isEmpty()) {
        Proyecto[] lista = (Proyecto[]) listita.toArray();
        listita.reset();
        shellSortLogic(lista, tipoorden, criterio);
        listita.toList(lista);
    }
    return listita;
}

// Lógica del ShellSort
private void shellSortLogic(Proyecto[] arr, Integer tipoorden, String criterio) {
    int n = arr.length;
    for (int gap = n / 2; gap > 0; gap /= 2) {
        for (int i = gap; i < n; i++) {
            Proyecto temp = arr[i];
            int j = i;

            while (j >= gap && verify(arr[j - gap], temp, tipoorden, criterio)) {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = temp;
        }
    }
}

public LinkedList<Proyecto> buscarBinario(String criterio, String valor) throws ListEmptyException {
    LinkedList<Proyecto> lista = new LinkedList<>();
    LinkedList<Proyecto> listita = getListAll(); 

    if (listita == null || listita.isEmpty()) {
        throw new ListEmptyException("Error: La lista está vacía.");
    }

    Proyecto[] proyectos = listita.toArray();

    for (int i = 0; i < proyectos.length - 1; i++) {
        for (int j = 0; j < proyectos.length - i - 1; j++) {
            boolean swap = false;
            if (criterio.equalsIgnoreCase("nombre")) {
                if (proyectos[j].getNombre().compareToIgnoreCase(proyectos[j + 1].getNombre()) > 0) {
                    swap = true;
                }
            } else if (criterio.equalsIgnoreCase("inversion")) {
                if (proyectos[j].getInversion() > proyectos[j + 1].getInversion()) {
                    swap = true;
                }
            } else if (criterio.equalsIgnoreCase("acronimo")) {
                if (proyectos[j].getAcronimo().compareToIgnoreCase(proyectos[j + 1].getAcronimo()) > 0) {
                    swap = true;
                }
            } else {
                throw new IllegalArgumentException("Criterio de búsqueda no válido: " + criterio);
            }

            if (swap) {
                Proyecto temp = proyectos[j];
                proyectos[j] = proyectos[j + 1];
                proyectos[j + 1] = temp;
            }
        }
    }

    int low = 0, high = proyectos.length - 1;

    while (low <= high) {
        int mid = (low + high) / 2;
        Proyecto midProyecto = proyectos[mid];
        boolean match = false;

        if (criterio.equalsIgnoreCase("nombre")) {
            match = midProyecto.getNombre().toLowerCase().startsWith(valor.toLowerCase());
        } else if (criterio.equalsIgnoreCase("inversion")) {
            try {
                float inversion = Float.parseFloat(valor);
                match = (midProyecto.getInversion() == inversion ||
                         String.valueOf(midProyecto.getInversion()).startsWith(valor));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El valor para 'inversión' debe ser un número válido.");
            }
        } else if (criterio.equalsIgnoreCase("acronimo")) {
            match = midProyecto.getAcronimo().toLowerCase().startsWith(valor.toLowerCase());
        } else {
            throw new IllegalArgumentException("Criterio de búsqueda no válido: " + criterio);
        }

        if (match) {
            lista.add(midProyecto);

            int left = mid - 1;
            while (left >= 0) {
                Proyecto leftProyecto = proyectos[left];
                boolean leftMatch = false;
                if (criterio.equalsIgnoreCase("nombre")) {
                    leftMatch = leftProyecto.getNombre().toLowerCase().startsWith(valor.toLowerCase());
                } else if (criterio.equalsIgnoreCase("inversion")) {
                    leftMatch = String.valueOf(leftProyecto.getInversion()).startsWith(valor);
                } else if (criterio.equalsIgnoreCase("acronimo")) {
                    leftMatch = leftProyecto.getAcronimo().toLowerCase().startsWith(valor.toLowerCase());
                }
                if (leftMatch) {
                    lista.add(leftProyecto);
                } else {
                    break;
                }
                left--;
            }

            int right = mid + 1;
            while (right < proyectos.length) {
                Proyecto rightProyecto = proyectos[right];
                boolean rightMatch = false;
                if (criterio.equalsIgnoreCase("nombre")) {
                    rightMatch = rightProyecto.getNombre().toLowerCase().startsWith(valor.toLowerCase());
                } else if (criterio.equalsIgnoreCase("inversion")) {
                    rightMatch = String.valueOf(rightProyecto.getInversion()).startsWith(valor);
                } else if (criterio.equalsIgnoreCase("acronimo")) {
                    rightMatch = rightProyecto.getAcronimo().toLowerCase().startsWith(valor.toLowerCase());
                }
                if (rightMatch) {
                    lista.add(rightProyecto);
                } else {
                    break;
                }
                right++;
            }

            break; 
        }

        if (criterio.equalsIgnoreCase("nombre")) {
            if (midProyecto.getNombre().compareToIgnoreCase(valor) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        } else if (criterio.equalsIgnoreCase("inversion")) {
            try {
                float inversion = Float.parseFloat(valor);
                if (midProyecto.getInversion() < inversion) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El valor para 'inversión' debe ser un número válido.");
            }
        } else if (criterio.equalsIgnoreCase("acronimo")) {
            if (midProyecto.getAcronimo().compareToIgnoreCase(valor) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }

    return lista; 
    
}





public LinkedList<Proyecto> buscarLineal(String criterio, String valor) throws ListEmptyException {
    LinkedList<Proyecto> lista = new LinkedList<>();
    LinkedList<Proyecto> listita = getListAll(); 
    if (listita == null || listita.isEmpty()) {
        throw new ListEmptyException("Error: La lista está vacía.");
    }

    Proyecto[] proyectos = listita.toArray();

    for (Proyecto proyecto : proyectos) {
        boolean match = false;

        if (criterio.equalsIgnoreCase("nombre")) {
            match = proyecto.getNombre().toLowerCase().startsWith(valor.toLowerCase());
        } else if (criterio.equalsIgnoreCase("inversion")) {
            try {
                float inversion = Float.parseFloat(valor);
                match = (proyecto.getInversion() == inversion || 
                         String.valueOf(proyecto.getInversion()).startsWith(valor));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El valor para 'inversión' debe ser un número válido.");
            }
        } else if (criterio.equalsIgnoreCase("acronimo")) {
            match = proyecto.getAcronimo().toLowerCase().startsWith(valor.toLowerCase());
        } else {
            throw new IllegalArgumentException("Criterio de búsqueda no válido: " + criterio);
        }

        if (match) {
            lista.add(proyecto);
        }
    }

    return lista; 
}


}

