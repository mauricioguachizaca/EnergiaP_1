package controller.Dao.servicies;

import controller.Dao.InversionistaDao;
import controller.tda.list.LinkedList;
import models.Inversionista;
public class InversionistaServices {
    private InversionistaDao obj;
    public InversionistaServices(){
        obj = new InversionistaDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }
    
    public LinkedList<Inversionista> listAll(){
        return obj.getListAll();
    }

    public Inversionista getInversionista(){
        return obj.getInversionista();
    }

    public void setIdInversionista(Inversionista inversionista) {
        obj.setProyecto(inversionista);
    }
}
