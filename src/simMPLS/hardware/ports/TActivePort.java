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

import java.util.Iterator;
import java.util.TreeSet;
import simMPLS.scenario.TSEPacketReceived;
import simMPLS.scenario.TStats;
import simMPLS.scenario.TTopologyNode;
import simMPLS.protocols.TPDU;
import simMPLS.protocols.TPDUMPLS;
import simMPLS.utils.TRotaryIDGenerator;
import simMPLS.utils.TMonitor;


/**
 * Esta clase implementa un puerto de entrada/salida v�lido para cualquiera de
 * los nodos del simulador que sean activos. El puerto es activo.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class TActivePort extends TPort {

    /**
     * Este m�todo es el constructor de la clase. Crea una nueva instancia de
     * TPuertoActivo.
     * @since 1.0
     * @param idp Identificador (n�mero) del puerto.
     * @param cpn Referencia al superconjunto de puertos del que este puerto forma parte.
     */
    public TActivePort(TNodePorts cpn, int idp) {
        super(cpn, idp);
        paqueteDevuelto = null;
        bufferIlimitado = false;
        ordenLlegada = new TRotaryIDGenerator();
        cerrojoPrioridad0 = new TMonitor();
        cerrojoPrioridad1 = new TMonitor();
        cerrojoPrioridad2 = new TMonitor();
        cerrojoPrioridad3 = new TMonitor();
        cerrojoPrioridad4 = new TMonitor();
        cerrojoPrioridad5 = new TMonitor();
        cerrojoPrioridad6 = new TMonitor();
        cerrojoPrioridad7 = new TMonitor();
        cerrojoPrioridad8 = new TMonitor();
        cerrojoPrioridad9 = new TMonitor();
        cerrojoPrioridad10 = new TMonitor();

        bufferPrioridad0 = new TreeSet();
        bufferPrioridad1 = new TreeSet();
        bufferPrioridad2 = new TreeSet();
        bufferPrioridad3 = new TreeSet();
        bufferPrioridad4 = new TreeSet();
        bufferPrioridad5 = new TreeSet();
        bufferPrioridad6 = new TreeSet();
        bufferPrioridad7 = new TreeSet();
        bufferPrioridad8 = new TreeSet();
        bufferPrioridad9 = new TreeSet();
        bufferPrioridad10 = new TreeSet();
        
        bufferSeleccionado = 0;
        siguientePaqueteALeer = null;
        ratioPorBuffer = new int[11];
        actualPorBuffer = new int[11];
        int i;
        for (i=0; i<11; i++) {
            ratioPorBuffer[i] = i+1;
            actualPorBuffer[i] = 0;
        }
    }
    
    private void obtenerSiguientePaquete() {
        boolean fin = false;
        int bufferesSinPaquetes = 0;
        int bufferesCompletos = 0;
        Iterator ite = null;
        TActivePortEntry epa = null;
        if (siguientePaqueteALeer == null) {
            while ((!fin) && (bufferesSinPaquetes < 12)) {
                if (bufferSeleccionado == 10) {
                    if (bufferPrioridad10.size() > 0) {
                        if (actualPorBuffer[10] < ratioPorBuffer[10]) {
                            this.cerrojoPrioridad10.lock();
                            ite = bufferPrioridad10.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad10.unLock();
                            actualPorBuffer[10]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[10] = ratioPorBuffer[10];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                } else if (bufferSeleccionado == 9) {
                    if (bufferPrioridad9.size() > 0) {
                        if (actualPorBuffer[9] < ratioPorBuffer[9]) {
                            this.cerrojoPrioridad9.lock();
                            ite = bufferPrioridad9.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad9.unLock();
                            actualPorBuffer[9]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[9] = ratioPorBuffer[9];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                } else if (bufferSeleccionado == 8) {
                    if (bufferPrioridad8.size() > 0) {
                        if (actualPorBuffer[8] < ratioPorBuffer[8]) {
                            this.cerrojoPrioridad8.lock();
                            ite = bufferPrioridad8.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad8.unLock();
                            actualPorBuffer[8]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[8] = ratioPorBuffer[8];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                } else if (bufferSeleccionado == 7) {
                    if (bufferPrioridad7.size() > 0) {
                        if (actualPorBuffer[7] < ratioPorBuffer[7]) {
                            this.cerrojoPrioridad7.lock();
                            ite = bufferPrioridad7.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad7.unLock();
                            actualPorBuffer[7]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[7] = ratioPorBuffer[7];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                } else if (bufferSeleccionado == 6) {
                    if (bufferPrioridad6.size() > 0) {
                        if (actualPorBuffer[6] < ratioPorBuffer[6]) {
                            this.cerrojoPrioridad6.lock();
                            ite = bufferPrioridad6.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad6.unLock();
                            actualPorBuffer[6]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[6] = ratioPorBuffer[6];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                } else if (bufferSeleccionado == 5) {
                    if (bufferPrioridad5.size() > 0) {
                        if (actualPorBuffer[5] < ratioPorBuffer[5]) {
                            this.cerrojoPrioridad5.lock();
                            ite = bufferPrioridad5.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad5.unLock();
                            actualPorBuffer[5]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[5] = ratioPorBuffer[5];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                } else if (bufferSeleccionado == 4) {
                    if (bufferPrioridad4.size() > 0) {
                        if (actualPorBuffer[4] < ratioPorBuffer[4]) {
                            this.cerrojoPrioridad4.lock();
                            ite = bufferPrioridad4.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad4.unLock();
                            actualPorBuffer[4]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[4] = ratioPorBuffer[4];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                } else if (bufferSeleccionado == 3) {
                    if (bufferPrioridad3.size() > 0) {
                        if (actualPorBuffer[3] < ratioPorBuffer[3]) {
                            this.cerrojoPrioridad3.lock();
                            ite = bufferPrioridad3.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad3.unLock();
                            actualPorBuffer[3]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[3] = ratioPorBuffer[3];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                } else if (bufferSeleccionado == 2) {
                    if (bufferPrioridad2.size() > 0) {
                        if (actualPorBuffer[2] < ratioPorBuffer[2]) {
                            this.cerrojoPrioridad2.lock();
                            ite = bufferPrioridad2.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad2.unLock();
                            actualPorBuffer[2]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[2] = ratioPorBuffer[2];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                } else if (bufferSeleccionado == 1) {
                    if (bufferPrioridad1.size() > 0) {
                        if (actualPorBuffer[1] < ratioPorBuffer[1]) {
                            this.cerrojoPrioridad1.lock();
                            ite = bufferPrioridad1.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad1.unLock();
                            actualPorBuffer[1]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[1] = ratioPorBuffer[1];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                } else if (bufferSeleccionado == 0) {
                    if (bufferPrioridad0.size() > 0) {
                        if (actualPorBuffer[0] < ratioPorBuffer[0]) {
                            this.cerrojoPrioridad0.lock();
                            ite = bufferPrioridad0.iterator();
                            if (ite.hasNext()) {
                                epa = (TActivePortEntry) ite.next();
                                siguientePaqueteALeer = epa.obtenerPaquete();
                                ite.remove();
                            }
                            this.cerrojoPrioridad0.unLock();
                            actualPorBuffer[0]++;
                            fin = true;
                        } else {
                            bufferesCompletos++;
                        }
                    } else {
                        actualPorBuffer[0] = ratioPorBuffer[0];
                        bufferesCompletos++;
                        bufferesSinPaquetes++;
                    }
                }
                bufferSeleccionado = ((bufferSeleccionado+1) % 11);
                if (bufferesCompletos >= 11) {
                    int i;
                    for (i=0; i<11; i++) {
                        actualPorBuffer[i] = 0;
                    }
                }
            }
        }
    }
    
    /**
     * Este m�todo selecciona el siguiente calcula el siguiente paquete que se leer� del
     * puerto y devuelve su prioridad que depende directamente del tipo de tr�fico y la
     * marca de GoS que lleve.
     * @since 1.0
     * @return -1, si no hay paquete que leer. un valor de 0 a 10 (inclusives) dependiendo de
     * la prioridad del paquete.
     */    
    public int obtenerPrioridadSiguientePaquete() {
        int p;
        monitor.lock();
        obtenerSiguientePaquete();
        if (this.siguientePaqueteALeer != null) {
            p = this.calcularPrioridadPaquete(this.siguientePaqueteALeer);
            monitor.unLock();
            return p;
        }
        monitor.unLock();
        return -1;
    }
    
    /**
     * Este m�todo permite saltarse las limitaciones de tama�o del buffer y establecer
     * �ste como un buffer ideal, con capacidad infinita.
     * @param bi TRUE indica que el buffer se tomar� como ilimitado. FALSE indica que el buffer
     * tendr� el tama�o especificado en el resto de m�todos.
     * @since 1.0
     */    
    @Override
    public void setUnlimitedBuffer(boolean bi) {
        this.bufferIlimitado = bi;
    }

    /**
     * Este m�todo deshecha el paquete pasado por parametro.
     * @param paquete El paquete que se desea descartar.
     * @since 1.0
     */    
    @Override
    public void discardPacket(TPDU paquete) {
        this.obtenerCjtoPuertos().getNode().descartarPaquete(paquete);
    }
    
    /**
     * Este m�todo deposita en el buffer del puerto un paquete.
     * @param paquete Paquete que se desea depositar en el buffer del puerto.
     * @since 1.0
     */    
    @Override
    public void ponerPaquete(TPDU paquete) {
        TActiveNodePorts cjtoPuertosAux = (TActiveNodePorts) portsSet;
        cjtoPuertosAux.portSetMonitor.lock();
        monitor.lock();
        
        TTopologyNode nt = this.portsSet.getNode();
        long idEvt = 0;
        int idOrden = 0;
        int prior = this.calcularPrioridadPaquete(paquete);
        try {
            idEvt = nt.gILargo.obtenerNuevo();
            idOrden = this.ordenLlegada.obtenerNuevo();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        int tipo = paquete.obtenerSubTipo();
        if (this.bufferIlimitado) {
            TActivePortEntry epa = new TActivePortEntry(prior, idOrden, paquete);
            insertarEntradaPriorizada(epa);
            cjtoPuertosAux.increasePortSetOccupancySize(paquete.getSize());
            TSEPacketReceived evt = new TSEPacketReceived(nt, idEvt, this.obtenerCjtoPuertos().getNode().obtenerInstanteDeTiempo(), tipo, paquete.getSize());
            nt.suscriptorSimulacion.capturarEventoSimulacion(evt);
            if (this.obtenerCjtoPuertos().getNode().accederAEstadisticas() != null) {
                this.obtenerCjtoPuertos().getNode().accederAEstadisticas().crearEstadistica(paquete, TStats.ENTRADA);
            }
        } else {
            if (!protocoloEPCD(paquete)) {
                this.discardPacket(paquete);
            }
        }
        monitor.unLock();
        cjtoPuertosAux.portSetMonitor.unLock();
    }
    
    /**
     * Este m�todo es la implementaci�n del protocolo EPCD (Early Packet Catch and
     * Discard), que mantiene siempre un umbral fijo libre en el buffer del puerto que
     * le permita capturar la cabecera de un paquete antes de descartarlo e informar al
     * protocolo GPSRP si se trata de un paquete GoS, para que proceda a solicitar su
     * retransmisi�n.
     * @param paquete Paquete que se desea insertar en el buffer y al que se le desea aplicar EPD
     * antes.
     * @return TRUE, si el paquete se inserta correctamente en el buffer. FALSE, en caso de que
     * se descarte el paquete.
     */    
    public boolean protocoloEPCD(TPDU paquete) {
        TActiveNodePorts cjtoPuertosAux = (TActiveNodePorts) portsSet;
        long idEvt = 0;
        int idOrden = 0;
        int prior = this.calcularPrioridadPaquete(paquete);
        TTopologyNode nt = this.portsSet.getNode();
        try {
            idEvt = nt.gILargo.obtenerNuevo();
            idOrden = this.ordenLlegada.obtenerNuevo();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        int tipo = paquete.obtenerSubTipo();
        if ((cjtoPuertosAux.getPortSetOccupancySize() + paquete.getSize()) <= ((cjtoPuertosAux.getBufferSizeInMB()*1024*1024) - UMBRAL)) {
                TActivePortEntry epa = new TActivePortEntry(prior, idOrden, paquete);
                insertarEntradaPriorizada(epa);
                cjtoPuertosAux.increasePortSetOccupancySize(paquete.getSize());
                TSEPacketReceived evt = new TSEPacketReceived(nt, idEvt, this.obtenerCjtoPuertos().getNode().obtenerInstanteDeTiempo(), tipo, paquete.getSize());
                nt.suscriptorSimulacion.capturarEventoSimulacion(evt);
                if (this.obtenerCjtoPuertos().getNode().accederAEstadisticas() != null) {
                    this.obtenerCjtoPuertos().getNode().accederAEstadisticas().crearEstadistica(paquete, TStats.ENTRADA);
                }
                return true;
        } else {
            if (paquete.obtenerSubTipo() == TPDU.MPLS_GOS) {
                cjtoPuertosAux.getNode().solicitarGPSRP((TPDUMPLS) paquete, this.portID);
            }
        }
        return false;
    }
    
    private void insertarEntradaPriorizada(TActivePortEntry ep) {
        int prioridadAux = ep.obtenerPrioridad();
        if (prioridadAux == TActivePort.PRIORIDAD_10) {
            this.cerrojoPrioridad10.lock();
            this.bufferPrioridad10.add(ep);
            this.cerrojoPrioridad10.unLock();
        } else if (prioridadAux == TActivePort.PRIORIDAD_9) {
            this.cerrojoPrioridad9.lock();
            this.bufferPrioridad9.add(ep);
            this.cerrojoPrioridad9.unLock();
        } else if (prioridadAux == TActivePort.PRIORIDAD_8) {
            this.cerrojoPrioridad8.lock();
            this.bufferPrioridad8.add(ep);
            this.cerrojoPrioridad8.unLock();
        } else if (prioridadAux == TActivePort.PRIORIDAD_7) {
            this.cerrojoPrioridad7.lock();
            this.bufferPrioridad7.add(ep);
            this.cerrojoPrioridad7.unLock();
        } else if (prioridadAux == TActivePort.PRIORIDAD_6) {
            this.cerrojoPrioridad6.lock();
            this.bufferPrioridad6.add(ep);
            this.cerrojoPrioridad6.unLock();
        } else if (prioridadAux == TActivePort.PRIORIDAD_5) {
            this.cerrojoPrioridad5.lock();
            this.bufferPrioridad5.add(ep);
            this.cerrojoPrioridad5.unLock();
        } else if (prioridadAux == TActivePort.PRIORIDAD_4) {
            this.cerrojoPrioridad4.lock();
            this.bufferPrioridad4.add(ep);
            this.cerrojoPrioridad4.unLock();
        } else if (prioridadAux == TActivePort.PRIORIDAD_3) {
            this.cerrojoPrioridad3.lock();
            this.bufferPrioridad3.add(ep);
            this.cerrojoPrioridad3.unLock();
        } else if (prioridadAux == TActivePort.PRIORIDAD_2) {
            this.cerrojoPrioridad2.lock();
            this.bufferPrioridad2.add(ep);
            this.cerrojoPrioridad2.unLock();
        } else if (prioridadAux == TActivePort.PRIORIDAD_1) {
            this.cerrojoPrioridad1.lock();
            this.bufferPrioridad1.add(ep);
            this.cerrojoPrioridad1.unLock();
        } else if (prioridadAux == TActivePort.SIN_PRIORIDAD) {
            this.cerrojoPrioridad0.lock();
            this.bufferPrioridad0.add(ep);
            this.cerrojoPrioridad0.unLock();
        }
    }
    
    private int calcularPrioridadPaquete(TPDU p) {
        if (p.obtenerTipo() == TPDU.TLDP) {
            return TActivePort.PRIORIDAD_10;
        }
        if (p.obtenerTipo() == TPDU.GPSRP) {
            return TActivePort.PRIORIDAD_9;
        }
        if (p.obtenerTipo() == TPDU.RLPRP) {
            return TActivePort.PRIORIDAD_8;
        }
        if (p.obtenerTipo() == TPDU.MPLS) {
            TPDUMPLS pMPLS = (TPDUMPLS) p;
            if (pMPLS.obtenerPilaEtiquetas().obtenerEtiqueta().obtenerLabel() == 1) {
                int EXP = pMPLS.obtenerPilaEtiquetas().obtenerEtiqueta().obtenerEXP();
                if (EXP == TPDU.EXP_NIVEL3_CONLSP) {
                    return TActivePort.PRIORIDAD_7;
                }
                if (EXP == TPDU.EXP_NIVEL3_SINLSP) {
                    return TActivePort.PRIORIDAD_6;
                }
                if (EXP == TPDU.EXP_NIVEL2_CONLSP) {
                    return TActivePort.PRIORIDAD_5;
                }
                if (EXP == TPDU.EXP_NIVEL2_SINLSP) {
                    return TActivePort.PRIORIDAD_4;
                }
                if (EXP == TPDU.EXP_NIVEL1_CONLSP) {
                    return TActivePort.PRIORIDAD_3;
                }
                if (EXP == TPDU.EXP_NIVEL1_SINLSP) {
                    return TActivePort.PRIORIDAD_2;
                }
                if (EXP == TPDU.EXP_NIVEL0_CONLSP) {
                    return TActivePort.PRIORIDAD_1;
                }
                if (EXP == TPDU.EXP_NIVEL0_SINLSP) {
                    return TActivePort.SIN_PRIORIDAD;
                }
            } else {
                return TActivePort.SIN_PRIORIDAD;
            }
        }
        if (p.obtenerTipo() == TPDU.IPV4) {
            if (p.obtenerCabecera().obtenerCampoOpciones().estaUsado()) {
                int GoS = p.obtenerCabecera().obtenerCampoOpciones().obtenerNivelGoS();
                if (GoS == TPDU.EXP_NIVEL3_CONLSP) {
                    return TActivePort.PRIORIDAD_7;
                }
                if (GoS == TPDU.EXP_NIVEL3_SINLSP) {
                    return TActivePort.PRIORIDAD_6;
                }
                if (GoS == TPDU.EXP_NIVEL2_CONLSP) {
                    return TActivePort.PRIORIDAD_5;
                }
                if (GoS == TPDU.EXP_NIVEL2_SINLSP) {
                    return TActivePort.PRIORIDAD_4;
                }
                if (GoS == TPDU.EXP_NIVEL1_CONLSP) {
                    return TActivePort.PRIORIDAD_3;
                }
                if (GoS == TPDU.EXP_NIVEL1_SINLSP) {
                    return TActivePort.PRIORIDAD_2;
                }
                if (GoS == TPDU.EXP_NIVEL0_CONLSP) {
                    return TActivePort.PRIORIDAD_1;
                }
                if (GoS == TPDU.EXP_NIVEL0_SINLSP) {
                    return TActivePort.SIN_PRIORIDAD;
                }
            } else {
                return TActivePort.SIN_PRIORIDAD;
            }
        }
        return TActivePort.SIN_PRIORIDAD;
    }

    /**
     * Este m�todo inserta un paquete en el buffer de recepci�n del puerto. Es igual
     * que el m�todo ponerPaquete(), salvo que no genera eventos y lo hace
     * silenciosamente.
     * @param paquete El paquete que queremos que reciba el puerto.
     * @since 1.0
     */    
    @Override
    public void reEnqueuePacket(TPDU paquete) {
        TActiveNodePorts cjtoPuertosAux = (TActiveNodePorts) portsSet;
        cjtoPuertosAux.portSetMonitor.lock();
        monitor.lock();
        TTopologyNode nt = this.portsSet.getNode();
        long idEvt = 0;
        int idOrden = 0;
        int prior = this.calcularPrioridadPaquete(paquete);
        try {
            idEvt = nt.gILargo.obtenerNuevo();
            idOrden = this.ordenLlegada.obtenerNuevo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int tipo = paquete.obtenerSubTipo();
        if (this.bufferIlimitado) {
            TActivePortEntry epa = new TActivePortEntry(prior, idOrden, paquete);
            insertarEntradaPriorizada(epa);
            cjtoPuertosAux.increasePortSetOccupancySize(paquete.getSize());
        } else {
            if ((cjtoPuertosAux.getPortSetOccupancySize() + paquete.getSize()) <= (cjtoPuertosAux.getBufferSizeInMB()*1024*1024)) {
                TActivePortEntry epa = new TActivePortEntry(prior, idOrden, paquete);
                insertarEntradaPriorizada(epa);
                cjtoPuertosAux.increasePortSetOccupancySize(paquete.getSize());
            } else {
                this.discardPacket(paquete);                
            }
        }
        monitor.unLock();
        cjtoPuertosAux.portSetMonitor.unLock();
    }
    
    /**
     * este m�todo lee un paquete del buffer de recepci�n del puerto. El paquete leido
     * depender� del algoritmo de gesti�n de los b�fferes que implemente el puerto. Por
     * defecto, es un FIFO con prioridad por tipo de paquetes.
     * @return El paquete le�do.
     * @since 1.0
     */    
    @Override
    public TPDU getPacket() {
        TActiveNodePorts cjtoPuertosAux = (TActiveNodePorts) portsSet;
        cjtoPuertosAux.portSetMonitor.lock();
        monitor.lock();
        obtenerSiguientePaquete();
        if (siguientePaqueteALeer != null) {
            paqueteDevuelto = siguientePaqueteALeer;
            if (!this.bufferIlimitado) {
                cjtoPuertosAux.decreasePortSetOccupancySize(paqueteDevuelto.getSize());
            }
            siguientePaqueteALeer = null;
        }
        monitor.unLock();
        cjtoPuertosAux.portSetMonitor.unLock();
        return paqueteDevuelto;
    }
    
    /**
     * Este m�todo calcula si podemos conmutar el siguiente paquete del parentNode, dado el
 n�mero de octetos que como mucho podemos conmutar en un momento dado.
     * @param octetos El n�mero de octetos que podemos conmutar.
     * @return TRUE, si podemos conmutar el siguiente paquete. FALSE, en caso contrario.
     * @since 1.0
     */    
    @Override
    public boolean canSwitchPacket(int octetos) {
        monitor.lock();
        obtenerSiguientePaquete();
        if (siguientePaqueteALeer != null) {
            paqueteDevuelto = siguientePaqueteALeer;
            monitor.unLock();
            if (paqueteDevuelto.getSize() <= octetos) {
                return true;
            }
        }
        monitor.unLock();
        return false;
    }

    /**
     * Este m�todo obtiene la congesti�n total el puerto, en porcentaje.
     * @return El porcentaje de ocupaci�n del puerto.
     * @since 1.0
     */    
    @Override
    public long getCongestionLevel() {
        if (this.bufferIlimitado) {
            return 0;
        } 
        TActiveNodePorts tpn = (TActiveNodePorts) portsSet;
        long cong = (tpn.getPortSetOccupancySize()*100) / (tpn.getBufferSizeInMB()*1024*1024);
        return cong;
    }

    /**
     * Este m�todo comprueba si hay paquetes esperando en el buffer de recepci�n o no.
     * @return TRUE, si hay paquetes en el buffer de recepci�n. FALSE en caso contrario.
     * @since 1.0
     */    
    @Override
    public boolean thereIsAPacketWaiting() {
        if (bufferPrioridad10.size() > 0)
            return true;
        if (bufferPrioridad9.size() > 0)
            return true;
        if (bufferPrioridad8.size() > 0)
            return true;
        if (bufferPrioridad7.size() > 0)
            return true;
        if (bufferPrioridad6.size() > 0)
            return true;
        if (bufferPrioridad5.size() > 0)
            return true;
        if (bufferPrioridad4.size() > 0)
            return true;
        if (bufferPrioridad3.size() > 0)
            return true;
        if (bufferPrioridad2.size() > 0)
            return true;
        if (bufferPrioridad1.size() > 0)
            return true;
        if (bufferPrioridad0.size() > 0)
            return true;
        if (siguientePaqueteALeer != null)
            return true;
        return false;
    }

    /**
     * Este m�todo calcula el total de octetos que suman los paquetes que actualmente
     * hay en el buffer de recepci�n del puerto.
     * @return El tama�o en octetos del total de paquetes en el buffer de recepci�n.
     * @since 1.0
     */    
    @Override
    public long getOccupancy() {
        if (this.bufferIlimitado) {
            this.monitor.lock();
            int ocup=0;
            TPDU paquete = null;
            TActivePortEntry epa = null;

            this.cerrojoPrioridad10.lock();
            Iterator it = this.bufferPrioridad10.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad10.unLock();

            this.cerrojoPrioridad9.lock();
            it = this.bufferPrioridad9.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad9.unLock();

            this.cerrojoPrioridad8.lock();
            it = this.bufferPrioridad8.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad8.unLock();

            this.cerrojoPrioridad7.lock();
            it = this.bufferPrioridad7.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad7.unLock();

            this.cerrojoPrioridad6.lock();
            it = this.bufferPrioridad6.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad6.unLock();

            this.cerrojoPrioridad5.lock();
            it = this.bufferPrioridad5.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad5.unLock();

            this.cerrojoPrioridad4.lock();
            it = this.bufferPrioridad4.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad4.unLock();

            this.cerrojoPrioridad3.lock();
            it = this.bufferPrioridad3.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad3.unLock();

            this.cerrojoPrioridad2.lock();
            it = this.bufferPrioridad2.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad2.unLock();

            this.cerrojoPrioridad1.lock();
            it = this.bufferPrioridad1.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad1.unLock();

            this.cerrojoPrioridad0.lock();
            it = this.bufferPrioridad0.iterator();
            while (it.hasNext()) {
                epa = (TActivePortEntry) it.next();
                paquete = epa.obtenerPaquete();
                if (paquete != null)
                    ocup += paquete.getSize();
            }
            this.cerrojoPrioridad0.unLock();

            if (siguientePaqueteALeer != null)
                ocup += siguientePaqueteALeer.getSize();
            this.monitor.unLock();
            return ocup;
        }
        TActiveNodePorts tpn = (TActiveNodePorts) portsSet;
        return tpn.getPortSetOccupancySize();
    }

    /**
     * Este m�todo calcula el n�mero de paquetes total que hay en el buffer del puerto.
     * @return El n�mero total de paquetes que hay en el puerto.
     * @since 1.0
     */    
    @Override
    public int getNumberOfPackets() {
        int numP = 0;
        numP += bufferPrioridad10.size();
        numP += bufferPrioridad9.size();
        numP += bufferPrioridad8.size();
        numP += bufferPrioridad7.size();
        numP += bufferPrioridad6.size();
        numP += bufferPrioridad5.size();
        numP += bufferPrioridad4.size();
        numP += bufferPrioridad3.size();
        numP += bufferPrioridad2.size();
        numP += bufferPrioridad1.size();
        numP += bufferPrioridad0.size();
        if (siguientePaqueteALeer != null)
            numP++;
        return numP;
    }

    /**
     * Este m�todo reinicia los atributos de la clase como si acabasen de ser creados
     * por el constructor.
     * @since 1.0
     */    
    @Override
    public void reset() {
        this.monitor.lock();
        
        this.cerrojoPrioridad10.lock();
        Iterator it = this.bufferPrioridad10.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad10.unLock();
        
        this.cerrojoPrioridad9.lock();
        it = this.bufferPrioridad9.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad9.unLock();

        this.cerrojoPrioridad8.lock();
        it = this.bufferPrioridad8.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad8.unLock();

        this.cerrojoPrioridad7.lock();
        it = this.bufferPrioridad7.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad7.unLock();

        this.cerrojoPrioridad6.lock();
        it = this.bufferPrioridad6.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad6.unLock();

        this.cerrojoPrioridad5.lock();
        it = this.bufferPrioridad5.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad5.unLock();

        this.cerrojoPrioridad4.lock();
        it = this.bufferPrioridad4.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad4.unLock();

        this.cerrojoPrioridad3.lock();
        it = this.bufferPrioridad3.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad3.unLock();

        this.cerrojoPrioridad2.lock();
        it = this.bufferPrioridad2.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad2.unLock();

        this.cerrojoPrioridad1.lock();
        it = this.bufferPrioridad1.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad1.unLock();

        this.cerrojoPrioridad0.lock();
        it = this.bufferPrioridad0.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        this.cerrojoPrioridad0.unLock();

        this.monitor.unLock();
        paqueteDevuelto = null;
        bufferSeleccionado = 0;
        siguientePaqueteALeer = null;
        int i;
        for (i=0; i<11; i++) {
            actualPorBuffer[i] = 0;
        }
    }
    
    private static final int PRIORIDAD_10  = 10;
    private static final int PRIORIDAD_9   = 9;
    private static final int PRIORIDAD_8   = 8;
    private static final int PRIORIDAD_7   = 7;
    private static final int PRIORIDAD_6   = 6;
    private static final int PRIORIDAD_5   = 5;
    private static final int PRIORIDAD_4   = 4;
    private static final int PRIORIDAD_3   = 3;
    private static final int PRIORIDAD_2   = 2;
    private static final int PRIORIDAD_1   = 1;
    private static final int SIN_PRIORIDAD = 0;

    private static final int UMBRAL = 100;
    
    private TMonitor cerrojoPrioridad0;
    private TMonitor cerrojoPrioridad1;
    private TMonitor cerrojoPrioridad2;
    private TMonitor cerrojoPrioridad3;
    private TMonitor cerrojoPrioridad4;
    private TMonitor cerrojoPrioridad5;
    private TMonitor cerrojoPrioridad6;
    private TMonitor cerrojoPrioridad7;
    private TMonitor cerrojoPrioridad8;
    private TMonitor cerrojoPrioridad9;
    private TMonitor cerrojoPrioridad10;
    private TreeSet bufferPrioridad0;
    private TreeSet bufferPrioridad1;
    private TreeSet bufferPrioridad2;
    private TreeSet bufferPrioridad3;
    private TreeSet bufferPrioridad4;
    private TreeSet bufferPrioridad5;
    private TreeSet bufferPrioridad6;
    private TreeSet bufferPrioridad7;
    private TreeSet bufferPrioridad8;
    private TreeSet bufferPrioridad9;
    private TreeSet bufferPrioridad10;

    private int bufferSeleccionado;
    private TPDU paqueteDevuelto;
    private boolean bufferIlimitado;
    private TRotaryIDGenerator ordenLlegada;
    private int ratioPorBuffer[];
    private int actualPorBuffer[];
    private TPDU siguientePaqueteALeer;
}
