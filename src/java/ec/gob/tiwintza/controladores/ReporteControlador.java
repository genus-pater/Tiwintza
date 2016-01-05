package ec.gob.tiwintza.controladores;

import com.lowagie.text.Anchor;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import static com.lowagie.text.Element.TABLE;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import static com.lowagie.text.PageSize.A4;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import ec.gob.tiwintza.entidades.ReporteEntidad;
import ec.gob.tiwintza.modelos.ReporteModelo;
import ec.gob.tiwintza.sesiones.SesionUsuarioDataManager;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.accessibility.AccessibleRole;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import static javax.print.attribute.standard.MediaPrintableArea.MM;
import javax.servlet.ServletContext;
import org.apache.jasper.compiler.PageInfo;
import org.primefaces.event.ColumnResizeEvent;

/**
 *
 * @author wmoina
 */
@ManagedBean(name = "dtFilterView")
@ViewScoped

public class ReporteControlador {

    private ReporteEntidad objEntidad;
    private ArrayList<ReporteEntidad> arrLisReporte;
    private ArrayList<ReporteEntidad> arrLisReporte2;
    SesionUsuarioDataManager sesion = (SesionUsuarioDataManager) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionUsuarioDataManager");

    //<editor-fold defaultstate="collapsed" desc="Sets y Gets"> 
    public ArrayList<ReporteEntidad> getArrLisReporte2() {
        return arrLisReporte2;
    }

    public void setArrLisReporte2(ArrayList<ReporteEntidad> arrLisReporte2) {
        this.arrLisReporte2 = arrLisReporte2;
    }

    public ReporteEntidad getObjEntidad() {
        return objEntidad;
    }

    public void setObjEntidad(ReporteEntidad objEntidad) {
        this.objEntidad = objEntidad;
    }

    public ArrayList<ReporteEntidad> getArrLisReporte() {
        return arrLisReporte;
    }

    public void setArrLisReporte(ArrayList<ReporteEntidad> arrLisReporte) {
        this.arrLisReporte = arrLisReporte;
    }

    //</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    @PostConstruct
    public void reint() {
        cargarReporte();
    }

    public ReporteControlador(ReporteEntidad objEntidad, ArrayList<ReporteEntidad> arrLisReporte) {
        this.objEntidad = objEntidad;
        this.arrLisReporte = arrLisReporte;
    }

    public ReporteControlador() {
        objEntidad = new ReporteEntidad();
        arrLisReporte = new ArrayList<>();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    public void onResize(ColumnResizeEvent event) {
        FacesMessage msg = new FacesMessage("Column " + event.getColumn().getClientId() + " resized", "W:" + event.getWidth() + ", H:" + event.getHeight());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void cargarReporte() {
        try {
            this.arrLisReporte = ReporteModelo.obtenerReporte();
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
    }

    public void eliminar() {
        objEntidad = null;
        arrLisReporte = null;
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LETTER.rotate());
        HeaderFooter footer = new HeaderFooter(new Phrase("Número de pagina: "), true);
        pdf.setFooter(footer);
        pdf.open();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "imagenes" + File.separator + "logo_doc.png";
        Image image1 = Image.getInstance(logo);
        image1.setAlignment(Element.ALIGN_RIGHT);
        pdf.add(image1);
        
        pdf.add(new Phrase(Chunk.NEWLINE));
        pdf.add(new Phrase(Chunk.NEWLINE));
        String CHUNK2 = "       Descargado por:";

        Chunk chunkTunning = new Chunk(CHUNK2, FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, Color.BLACK));
        pdf.add(chunkTunning);

        String CHUNK3 = " " + sesion.getSesionUsuarioActual().getUsuario_nombre() + " " + sesion.getSesionUsuarioActual().getUsuario_apellido();

        Chunk chunkTunning1 = new Chunk(CHUNK3, FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL, Color.BLACK));
        pdf.add(chunkTunning1);

        Calendar cal = Calendar.getInstance();
        Date fecha = new Date(cal.getTimeInMillis());
        SimpleDateFormat formato = new SimpleDateFormat();

        String Fecha = "        Fecha: ";
        Chunk chunkTunning3 = new Chunk(Fecha, FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, Color.BLACK));
        pdf.add(chunkTunning3);

        String hora = formato.format(fecha);
        Chunk chunkTunning2 = new Chunk(hora, FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL, Color.BLACK));
        pdf.add(chunkTunning2);

        pdf.add(new Phrase(Chunk.NEWLINE));

        String Departamento = "       Rol: ";
        Chunk chunkTunning4 = new Chunk(Departamento, FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, Color.BLACK));
        pdf.add(chunkTunning4);

        String depar = "" + sesion.getSesionRolUsuarioActual().getRol_id().getRol_descripcion();
        Chunk chunkTunning5 = new Chunk(depar, FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL, Color.BLACK));
        pdf.add(chunkTunning5);

        String Acceder = "                                  Acceder: ";
        Chunk chunkTunning6 = new Chunk(Acceder, FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, Color.BLACK));
        pdf.add(chunkTunning6);

        Anchor a = new Anchor("GAD Municipal de Tiwintza");
        a.setReference("http://www.tiwintza.gob.ec/");
        pdf.add(a);
        pdf.add(new Phrase(Chunk.NEWLINE));

        Font fontTitulos = FontFactory.getFont(FontFactory.COURIER, 13, Font.BOLD,
                Color.BLACK);

        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.add(new Phrase("- REPORTE -", fontTitulos));
        pdf.add(paragraph);
        pdf.add(new Phrase(Chunk.NEWLINE));
    }
//</editor-fold>

}
