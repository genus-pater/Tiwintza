package ec.gob.tiwintza.controladores;

/**
 *
 * @author wmoina
 */
import ec.gob.tiwintza.entidades.EstadoEntidad;
import ec.gob.tiwintza.modelos.EstadoModelo;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
public class EstadoControlador {

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;
    private EstadoEntidad objEstado;
    public PieChartModel livePieModel;
    private ArrayList<EstadoEntidad> arrLisEstado;

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets"> 
    public EstadoEntidad getObjEstado() {
        return objEstado;
    }

    public void setObjEstado(EstadoEntidad objEstado) {
        this.objEstado = objEstado;
    }

    public ArrayList<EstadoEntidad> getArrLisEstado() {
        return arrLisEstado;
    }

    public void setArrLisEstado(ArrayList<EstadoEntidad> arrLisEstado) {
        this.arrLisEstado = arrLisEstado;
    }

    public PieChartModel getPieModel3() {
        return pieModel3;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    @PostConstruct
    public void reinit() {
        cargarEstado();
        createPieModels();
    }

    public EstadoControlador(PieChartModel pieModel1, PieChartModel pieModel2, EstadoEntidad objEstado, PieChartModel livePieModel, ArrayList<EstadoEntidad> arrLisEstado) {
        this.pieModel1 = pieModel1;
        this.pieModel2 = pieModel2;
        this.objEstado = objEstado;
        this.livePieModel = livePieModel;
        this.arrLisEstado = arrLisEstado;
    }

    public EstadoControlador() {
        pieModel1 = new PieChartModel();
        pieModel2 = new PieChartModel();
        objEstado = new EstadoEntidad();
        livePieModel = new PieChartModel();
        arrLisEstado = new ArrayList<>();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">

    public void cargarEstado() {
        try {
            this.arrLisEstado = EstadoModelo.obtenerEstado();
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
        createPieModel3();
    }

    private void createPieModel1() {
        EstadoEntidad n1 = arrLisEstado.get(0);
        EstadoEntidad n2 = arrLisEstado.get(1);

        pieModel1 = new PieChartModel();

        pieModel1.set("Seguimientos pendientes", n1.getEstado_cadena());
        pieModel1.set("Seguimientos concluidos", n2.getEstado_cadena());

        pieModel1.setTitle("Estado de los seguimientos a trámites");
        pieModel1.setLegendPosition("e");
        pieModel1.setShowDataLabels(true);

    }

    private void createPieModel2() {

        EstadoEntidad n3 = arrLisEstado.get(2);
        EstadoEntidad n4 = arrLisEstado.get(3);

        pieModel2 = new PieChartModel();

        pieModel2.set("Trámites pendientes", n3.getEstado_cadena());
        pieModel2.set("Trámites concluidos", n4.getEstado_cadena());

        pieModel2.setTitle("Estado de los trámites");
        pieModel2.setLegendPosition("e");
        pieModel2.setShowDataLabels(true);
    }

    private void createPieModel3() {

        EstadoEntidad n5 = arrLisEstado.get(4);
        EstadoEntidad n6 = arrLisEstado.get(5);

        pieModel3 = new PieChartModel();

        pieModel3.set("Trámites concluidos con atraso", n5.getEstado_cadena());
        pieModel3.set("Trámites concluidos a tiempo", n6.getEstado_cadena());

        pieModel3.setTitle("Estado trámites concluidos");
        pieModel3.setLegendPosition("e");
        pieModel3.setShowDataLabels(true);
    }
       //</editor-fold>
}
