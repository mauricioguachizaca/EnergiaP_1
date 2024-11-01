package controller.Dao.servicies;

import controller.Dao.ProyectoDao;
import controller.tda.list.LinkedList;
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


}

