//**************************************************************************
// Nombre......: TPort.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Clase que implementa un puerto de comunicaciones de un no-
// ............: do de la topologia.
// Fecha.......: 12/03/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.hardware.ports;

import simMPLS.scenario.TTopologyLink;
import simMPLS.scenario.TStats;
import simMPLS.protocols.TPDU;
import simMPLS.utils.TMonitor;


/**
 * Esta clase abstracta es la superclase de un puerto de entrada/salida v�lido para
 * cualquiera de los nodos del simulador.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public abstract class TPort {

    /**
     * Este m�todo es el constructor de la clase. Crea una nueva instancia de TPuerto.
     * @since 1.0
     * @param idp Identificador (n�mero) del puerto.
     * @param cpn Referencia al superconjunto de puertos del que este puerto forma parte.
     */
    public TPort(TNodePorts cpn, int idp) {
        identifier = 0;
        link = null;
        portsSet = cpn;
        monitor = new TMonitor();
        portID = idp;
    }
    
    /**
     * Este m�todo establece cu�l es el superconjunto de puertos al que pertenece este
     * `puerto concreto.
     * @param cpn El conjunto de puertos al que pertenece este puerto.
     * @since 1.0
     */    
    public void ponerCjtoPuertos(TNodePorts cpn) {
        portsSet = cpn;
    }

    /**
     * Este m�todo obtiene el superconjunto de puertos al que pertenece este puerto.
     * @return El conjunto de puertos al que pertenece este nodo.
     * @since 1.0
     */    
    public TNodePorts obtenerCjtoPuertos() {
        return portsSet;
    }

    /**
     * Este m�todo establece el identifier del puerto.
     * @param id Identificador que queremos asociar a este puerto.
     * @since 1.0
     */    
    public void ponerIdentificador(int id) {
        identifier = id;
    }
    
    /**
     * Este m�todo obtiene el identifier del puerto.
     * @return El identifier del puerto.
     * @since 1.0
     */    
    public int obtenerIdentificador() {
        return identifier;
    }

    /**
     * Este m�todo averigua si el puerto est� libre o est� conectado a al�n link de
 la topolog�a.
     * @return TRUE, si est� libre. FALSE, si est� conectado a alg�n link de la topolog�a.
     * @since 1.0
     */    
    public boolean isAvailable() {
        if (link == null)
            return true;
        return false;
    }

    /**
     * Este m�todo enlaza este puerto con un link de la topolog�a, dejando por tanto
 de estar libre.
     * @param e El link al que se desea conectar el puerto.
     * @since 1.0
     */    
    public void setLink(TTopologyLink e) {
        link = e;
    }

    /**
     * Este m�todo obtiene el link al que est� unido el puerto.
     * @return El link al que est� unido el puerto, si est� unido. NULL en caso contrario.
     * @since 1.0
     */    
    public TTopologyLink getLink() {
        return link;
    }

    /**
     * Este m�todo libera la conexi�n que existe entre el puerto y un link de la
 topolog�a, dej�ndolo libre.
     * @since 1.0
     */    
    public void disconnectLink() {
        link = null;
    }

    /**
     * Este m�todo coloca en el link al que est� unido el puerto, un paquete de
 datos.
     * @param p El paquete que se desea transmitir por el link.
     * @param destino 1, si el paquete va dirigido al END_NODE_1 del link. 2, si va dirigido al
 END_NODE_2.
     * @since 1.0
     */    
    public void ponerPaqueteEnEnlace(TPDU p, int destino) {
        if (link != null) {
            if (!link.obtenerEnlaceCaido()) {
                if (link.obtenerTipo() == TTopologyLink.INTERNO) {
                    link.ponerPaquete(p, destino);
                    if (this.obtenerCjtoPuertos().getNode().accederAEstadisticas() != null) {
                        this.obtenerCjtoPuertos().getNode().accederAEstadisticas().crearEstadistica(p, TStats.SALIDA);
                    }
                } else {
                    if ((p.obtenerTipo() != TPDU.GPSRP) && (p.obtenerTipo() != TPDU.TLDP)) {
                        link.ponerPaquete(p, destino);
                        if (this.obtenerCjtoPuertos().getNode().accederAEstadisticas() != null) {
                            this.obtenerCjtoPuertos().getNode().accederAEstadisticas().crearEstadistica(p, TStats.SALIDA);
                        }
                    }
                }
            } else {
                discardPacket(p);
            }
        }
    }

    /**
     * Este m�todo deshecha el paquete pasado por parametro.
     * @param paquete El paquete que se desea descartar.
     * @since 1.0
     */    
    public abstract void discardPacket(TPDU paquete);
    /**
     * Este m�todo inserta un paquete en el buffer de recepci�n del puerto.
     * @param paquete El paquete que queremos que sea recivido en el puerto.
     * @since 1.0
     */    
    public abstract void ponerPaquete(TPDU paquete);
    /**
     * Este m�todo inserta un paquete en el buffer de recepci�n del puerto. Es igual
     * que el m�todo ponerPaquete(), salvo que no genera eventos y lo hace
     * silenciosamente.
     * @param paquete El paquete que queremos que reciba el puerto.
     * @since 1.0
     */    
    public abstract void reEnqueuePacket(TPDU paquete);
    /**
     * este m�todo lee un paquete del buffer de recepci�n del puerto. El paquete leido
     * depender� del algoritmo de gesti�n de los b�fferes que implemente el puerto. Por
     * defecto, es un FIFO Droptail.
     * @return El paquete le�do.
     * @since 1.0
     */    
    public abstract TPDU getPacket();
    /**
     * Este m�todo calcula si podemos conmutar el siguiente paquete del nodo, dado el
     * n�mero de octetos que como mucho podemos conmutar en un momento dado.
     * @param octetos El n�mero de octetos que podemos conmutar.
     * @return TRUE, si podemos conmutar el siguiente paquete. FALSE, en caso contrario.
     * @since 1.0
     */    
    public abstract boolean canSwitchPacket(int octetos);
    /**
     * Este m�todo obtiene la congesti�n total el puerto, en porcentaje.
     * @return El porcentaje de ocupaci�n del puerto.
     * @since 1.0
     */    
    public abstract long getCongestionLevel();
    /**
     * Este m�todo comprueba si hay paquetes esperando en el buffer de recepci�n o no.
     * @return TRUE, si hay paquetes en el buffer de recepci�n. FALSE en caso contrario.
     * @since 1.0
     */    
    public abstract boolean thereIsAPacketWaiting();
    /**
     * Este m�todo calcula el total de octetos que suman los paquetes que actualmente
     * hay en el buffer de recepci�n del puerto.
     * @return El tama�o en octetos del total de paquetes en el buffer de recepci�n.
     * @since 1.0
     */    
    public abstract long getOccupancy();
    /**
     * Este m�todo calcula el n�mero de paquetes total que hay en el buffer del puerto.
     * @return El n�mero total de paquetes que hay en el puerto.
     * @since 1.0
     */    
    public abstract int getNumberOfPackets();
    /**
     * Este m�todo reinicia los atributos de la clase como si acabasen de ser creados
     * por el constructor.
     * @since 1.0
     */    
    public abstract void reset();
    /**
     * Este m�todo permite saltarse las limitaciones de tama�o del buffer y establecer
     * �ste como un buffer ideal, con capacidad infinita.
     * @param bi TRUE indica que el buffer se tomar� como ilimitado. FALSE indica que el buffer
     * tendr� el tama�o especificado en el resto de m�todos.
     * @since 1.0
     */    
    public abstract void setUnlimitedBuffer(boolean bi);
    
    /**
     * Este atributo es el identifier del puerto. Como los nodos tienen m�s de un
 puerto, es necesario un identifier para referirse a cada uno de ellos.
     * @since 1.0
     */    
    protected int identifier;
    /**
     * Este atributo es el link de la topolog�a al que est� unido el puerto. Todo
 puerto o est� libre o est� unido a un link, que es este.
     * @since 1.0
     */    
    protected TTopologyLink link;
    /**
     * Este atributo es una referencia al superconjunto de todos los puertos de un nodo
     * al que pertenece este.
     * @since 1.0
     */    
    protected TNodePorts portsSet;
    /**
     * Este atributo es un monitor que sirve para crear secciones cr�ticas, actuando de
     * barrera, para sincronizar el acceso concurrente a algunas zonas del objeto.
     * @since 1.0
     */    
    protected TMonitor monitor;
    /**
     * Este atributo almacenar� el identifier que indica el n�mero de puerto que
 ocupa la instancia actual dentro del conjunto de puertos de un nodo.
     * @since 1.0
     */    
    protected int portID;
    
}
