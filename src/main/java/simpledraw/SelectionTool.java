package simpledraw;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

/**
 * The tool to select, move and delete Shapes in the Drawing
 * @author Rémi Bastide
 * @version 1.0
 */

public class SelectionTool extends DrawingTool {
    
        private Shape pickedShape = null;
        private Set<Shape> myShapeList = new HashSet<Shape>();
	private Point myLastPoint;

	public SelectionTool(DrawingPanel panel) {
		super(panel);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_DELETE) {
                    for ( Shape shape: myShapeList){
                        if (shape.isSelected()){
                            myDrawing.deleteShape(shape);
                            myPanel.repaint();
                        }
                    }
		}
	}

	public void mousePressed(MouseEvent e) {
            
		Shape pickedShape = myDrawing.pickShapeAt(e.getPoint());
		myLastPoint = e.getPoint();
		if (pickedShape != null){
                    myShapeList.add(pickedShape);
                    if (pickedShape.isSelected()){
                            pickedShape.setSelected(false);
                        /*for ( Shape shape: myShapeList){
                            if(shape == pickedShape && shape.isSelected()){
                                shape.setSelected(false);
                            }
                        }*/
                    }else{
                        //myShapeList.add(pickedShape);
                        pickedShape.setSelected(true);
                        /*for ( Shape shape: myShapeList){
                            if(shape == pickedShape && !shape.isSelected()){
                                shape.setSelected(true);
                            }
                        }*/
                            //mySelectedShape.setSelected(true);
                        myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    }
                }
                /*if (pickedShape == null){
                    for ( Shape shape: myShapeList){
                        shape.setSelected(false);
                    }
                }*/
		myPanel.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		mouseMoved(e);
	}

	public void mouseMoved(MouseEvent e) {
		Shape pickedShape = myPanel.myDrawing.pickShapeAt(e.getPoint());
		if (pickedShape != null) {
			myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.
				HAND_CURSOR));
		} else {
			myPanel.setCursor(Cursor.getDefaultCursor());
		}
	}

	public void mouseDragged(MouseEvent e) {
            //Shape pickedShape = myDrawing.pickShapeAt(e.getPoint());
		//if (pickedShape != null) {
                    for ( Shape shape: myShapeList){
                        if( shape.isSelected() ){
                            shape.translateBy(
				e.getX() - myLastPoint.x,
				e.getY() - myLastPoint.y
				);
                            
                        }
                    }
                    myLastPoint = e.getPoint();
			/*mySelectedShape.translateBy(
				e.getX() - myLastPoint.x,
				e.getY() - myLastPoint.y
				);*/
			//myLastPoint = e.getPoint();
        		myPanel.repaint();
		//}
	}

	void draw(Graphics2D g) {
	}

}
