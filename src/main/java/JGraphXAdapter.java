/*Name         - Chanuka Nimsara Mathagadeera
IIT Student Id - 2017388
UOW Id         - w1698507*/

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;


public class JGraphXAdapter extends JApplet {

    private static int [][]arr2=Tester.getArr2();

    private static final long serialVersionUID = 2202072534703043194L;

    private static final Dimension DEFAULT_SIZE = new Dimension(900, 900);
    private org.jgrapht.ext.JGraphXAdapter<String, DefaultEdge> jgxAdapter;

    public static void myGraph(){

        JGraphXAdapter applet = new JGraphXAdapter();
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("JGraphT Adapter to JGraphX Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    @Override
    public void init(){

        // create a JGraphT graph
        ListenableGraph<String, ChanukaEdge> g =
                new DefaultListenableGraph<String, ChanukaEdge>(new DirectedWeightedMultigraph<String, ChanukaEdge>(ChanukaEdge.class));

        // create a visualization using JGraph, via an adapter
        org.jgrapht.ext.JGraphXAdapter<String, ChanukaEdge> adap= new org.jgrapht.ext.JGraphXAdapter<String, ChanukaEdge>(g);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(adap);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        getContentPane().add(component);
        resize(DEFAULT_SIZE);

        for (int i=1;i<=Tester.getNodes();i++){
            g.addVertex("v"+i);

        }
        for (int c=1;c<=Tester.getNodes();c++) {
            for (int k = c; k <= Tester.getNodes(); k++) {
                if ((c != k) && (k!=1) &&(c!=Tester.getNodes())) {
                    DefaultWeightedEdge v= g.addEdge("v"+c,"v"+k);
                    ((ChanukaEdge) v).p =String.valueOf(arr2[c-1][k-1]);
                }

            }
        }

        for (int c=1;c<=Tester.getNodes();c++) {
            for (int k = 1; k <= c; k++) {
                if ((c != k) && (k!=1) &&(c!=Tester.getNodes())) {
                    DefaultWeightedEdge v= g.addEdge("v"+c,"v"+k);
                    ((ChanukaEdge) v).p ="            #"+String.valueOf(arr2[c-1][k-1]);

                }

            }
        }

        mxCircleLayout layout = new mxCircleLayout(adap);
        // mxParallelEdgeLayout layout = new mxParallelEdgeLayout(adap);
        layout.setRadius(400);
        // center the circle
        layout.execute(adap.getDefaultParent());
    }

    public static class ChanukaEdge extends DefaultWeightedEdge{
        String p ;

        @Override
        public String toString() {
            return String.valueOf(p);
        }
    }

}
