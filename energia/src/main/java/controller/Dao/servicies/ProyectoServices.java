package controller.Dao.servicies;

import controller.Dao.ProyectoDao;
import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;
import models.Proyecto;
public class ProyectoServices {
    private ProyectoDao obj;
    public ProyectoServices() {
        obj = new ProyectoDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList<Proyecto> listAll(){
        return obj.getListAll();
    }

    public Proyecto getProyecto(){
        return obj.getProyecto();
    }

    public void setProyecto(Proyecto proyecto) {
        obj.setProyecto(proyecto);
    }

    public Proyecto get(Integer id) throws Exception {
        return obj.get(id);
    }

    public Boolean delete(Integer id) throws Exception {
        return obj.delete(id);
    }

    public LinkedList<Proyecto> order(Integer type_order, String atributo) {
        return obj.order(type_order, atributo);
    }
    
    public LinkedList<Proyecto> buscarBinario (String criterio, String atributo) throws ListEmptyException {
        return obj.buscarBinario(criterio, atributo);
    }

    public LinkedList<Proyecto> buscarLineal (String criterio, String atributo) throws ListEmptyException {
        return obj.buscarLineal(criterio, atributo);
    }

    public LinkedList<Proyecto> mergeSort(Integer type_order, String atributo) {
        return obj.mergeSort(type_order, atributo);
    }

    public LinkedList<Proyecto> shellSort(Integer type_order, String atributo) {
        return obj.shellSort(type_order, atributo);
    }
    

    

}

